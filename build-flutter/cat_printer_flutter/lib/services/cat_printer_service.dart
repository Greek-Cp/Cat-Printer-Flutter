// Cat Printer Service - Main service for Cat Printer operations
// Ported from Python printer.py

import 'dart:async';
import 'dart:typed_data';
import 'dart:ui' as ui;
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:image/image.dart' as img;
import '../models/printer_models.dart';
import 'printer_commander.dart';
import 'commands/base_commands.dart';
import 'commands/mxw01_commands.dart';
import 'commands/generic_commands.dart';

class CatPrinterService {
  BluetoothDevice? _device;
  BluetoothCharacteristic? _txCharacteristic;
  BluetoothCharacteristic? _rxCharacteristic;
  BluetoothCharacteristic? _dataCharacteristic; // For MXW01 model
  PrinterModel? _model;
  PrinterCommandInterface? _commands; // Command interface for current model
  PrinterConfig _config = PrinterConfig();

  bool _isConnected = false;
  bool _isPaused = false;
  List<int> _pendingData = [];

  StreamSubscription? _deviceStateSubscription;
  StreamSubscription? _rxSubscription;

  // Getters
  bool get isConnected => _isConnected;
  PrinterModel? get model => _model;
  PrinterConfig get config => _config;
  BluetoothDevice? get device => _device;

  /// Scan for Cat Printer devices - ported from Python code
  /// If showAllDevices is true, returns all Bluetooth devices found (similar to Python's everything=True)
  Future<List<BluetoothDevice>> scanForDevices(
      {Duration? timeout, bool showAllDevices = false}) async {
    List<BluetoothDevice> catPrinters = [];

    try {
      // Check if Bluetooth is available
      if (await FlutterBluePlus.isAvailable == false) {
        throw Exception('Bluetooth not available');
      }

      // Check if Bluetooth is on
      if (await FlutterBluePlus.isOn == false) {
        throw Exception('Bluetooth is turned off');
      }

      // Start scanning with timeout
      Duration scanDuration =
          timeout ?? Duration(seconds: _config.scanTime.toInt());
      print('Starting Bluetooth scan for ${scanDuration.inSeconds} seconds...');

      await FlutterBluePlus.startScan(
        timeout: scanDuration,
      );

      // Listen to scan results during the entire scan period
      StreamSubscription? scanSubscription;
      Completer<void> scanCompleter = Completer<void>();

      scanSubscription = FlutterBluePlus.scanResults.listen((results) {
        for (ScanResult result in results) {
          String platformName = result.device.platformName;
          String advertisedName = result.advertisementData.advName;

          // Debug: print all discovered devices
          print(
              'Found device: platformName="$platformName", advertisedName="$advertisedName", rssi=${result.rssi}');

          // If showAllDevices is true, add all devices (like Python's everything=True)
          if (showAllDevices) {
            if (!catPrinters.any((d) => d.remoteId == result.device.remoteId)) {
              print('Added device: $platformName / $advertisedName');
              catPrinters.add(result.device);
            }
          } else {
            // Check both platform name and advertised name for supported models
            bool isSupported = PrinterModels.isSupported(platformName) ||
                PrinterModels.isSupported(advertisedName);

            if (isSupported) {
              if (!catPrinters
                  .any((d) => d.remoteId == result.device.remoteId)) {
                print('Added Cat Printer: $platformName / $advertisedName');
                catPrinters.add(result.device);
              }
            }
          }
        }
      });

      // Wait for scan to complete
      FlutterBluePlus.isScanning.listen((isScanning) {
        if (!isScanning && !scanCompleter.isCompleted) {
          scanCompleter.complete();
        }
      });

      await scanCompleter.future;
      await scanSubscription?.cancel();

      print('Scan completed. Found ${catPrinters.length} Cat Printer(s)');

      await FlutterBluePlus.stopScan();
    } catch (e) {
      await FlutterBluePlus.stopScan();
      rethrow;
    }

    return catPrinters;
  }

  /// Connect to a Cat Printer device - ported from Python code
  Future<void> connect(BluetoothDevice device) async {
    try {
      // Disconnect if already connected
      if (_isConnected) {
        await disconnect();
      }

      _device = device;
      String deviceName =
          device.platformName.isNotEmpty ? device.platformName : device.advName;
      print('Attempting to connect to device: $deviceName');

      _model = PrinterModels.getModel(deviceName);

      if (_model == null) {
        print('Unsupported printer model: $deviceName');
        print('Supported models: ${PrinterModels.getSupportedModels()}');
        throw Exception('Unsupported printer model: $deviceName');
      }

      // Create command interface for this model
      _commands = _model!.createCommandInterface();
      print('Found supported model: ${_model!.name} (${_model!.type})');

      // Connect to device
      print('Connecting to device...');
      await device.connect(
        timeout: Duration(seconds: _config.connectionTimeout.toInt()),
      );
      print('Device connected successfully');

      // Listen to device state changes
      _deviceStateSubscription = device.connectionState.listen((state) {
        _isConnected = (state == BluetoothConnectionState.connected);
      });

      // Discover services
      print('Discovering services...');
      List<BluetoothService> services = await device.discoverServices();
      print('Found ${services.length} services');

      // Find TX and RX characteristics
      for (BluetoothService service in services) {
        print('Service UUID: ${service.uuid}');
        for (BluetoothCharacteristic characteristic
            in service.characteristics) {
          String uuid = characteristic.uuid.toString().toLowerCase();
          print('Characteristic UUID: $uuid');

          // Check for both short and full UUID formats
          bool isTxChar = uuid == _model!.txCharacteristic ||
              uuid == 'ae01' ||
              uuid.contains('ae01');
          bool isRxChar = uuid == _model!.rxCharacteristic ||
              uuid == 'ae02' ||
              uuid.contains('ae02');
          bool isDataChar = (_model!.dataCharacteristic != null &&
                  uuid == _model!.dataCharacteristic!) ||
              uuid == 'ae03' ||
              uuid.contains('ae03');

          if (isTxChar) {
            _txCharacteristic = characteristic;
            print('Found TX characteristic: $uuid');
          } else if (isRxChar) {
            _rxCharacteristic = characteristic;
            print('Found RX characteristic: $uuid');

            // Subscribe to notifications for flow control
            await characteristic.setNotifyValue(true);
            _rxSubscription =
                characteristic.lastValueStream.listen(_handleNotification);
          } else if (isDataChar) {
            _dataCharacteristic = characteristic;
            print('Found Data characteristic: $uuid (for MXW01)');
          }
        }
      }

      if (_txCharacteristic == null || _rxCharacteristic == null) {
        print('TX Characteristic found: ${_txCharacteristic != null}');
        print('RX Characteristic found: ${_rxCharacteristic != null}');
        print('Expected TX UUID: ${_model!.txCharacteristic}');
        print('Expected RX UUID: ${_model!.rxCharacteristic}');
        throw Exception('Required characteristics not found');
      }

      _isConnected = true;
      _pendingData.clear();
    } catch (e) {
      await disconnect();
      rethrow;
    }
  }

  /// Disconnect from printer - ported from Python code
  Future<void> disconnect() async {
    try {
      _rxSubscription?.cancel();
      _deviceStateSubscription?.cancel();

      if (_rxCharacteristic != null) {
        await _rxCharacteristic!.setNotifyValue(false);
      }

      if (_device != null && _device!.isConnected) {
        await _device!.disconnect();
      }
    } catch (e) {
      // Ignore disconnect errors
    } finally {
      _device = null;
      _txCharacteristic = null;
      _rxCharacteristic = null;
      _dataCharacteristic = null;
      _model = null;
      _commands = null;
      _isConnected = false;
      _isPaused = false;
      _pendingData.clear();
    }
  }

  /// Handle notifications from printer - ported from Python code
  void _handleNotification(List<int> data) {
    if (_listEquals(data, BaseCommands.dataFlowPause)) {
      _isPaused = true;
    } else if (_listEquals(data, BaseCommands.dataFlowResume)) {
      _isPaused = false;
    }
  }

  /// Check if connected printer is MXW01 model
  bool get _isMXW01 => _model?.type == PrinterType.mxw01;

  /// Send command using appropriate protocol based on printer model
  Future<void> _sendCommandForModel(List<int> command) async {
    if (!_isConnected || _txCharacteristic == null) {
      throw Exception('Printer not connected');
    }

    if (_config.dryRun) {
      return; // Skip sending in dry run mode
    }

    // For MXW01, send directly without flow control
    if (_isMXW01) {
      await _txCharacteristic!.write(command, withoutResponse: true);
      return;
    }

    // For other models, use existing flow control
    _pendingData.addAll(command);
    await _flush();
  }

  /// Send command to printer - ported from Python code
  Future<void> _sendCommand(List<int> command) async {
    if (!_isConnected || _txCharacteristic == null) {
      throw Exception('Printer not connected');
    }

    if (_config.dryRun) {
      return; // Skip sending in dry run mode
    }

    _pendingData.addAll(command);

    // Send data if buffer is large enough or not paused
    if (_pendingData.length > _config.mtu * 16 && !_isPaused) {
      await _flush();
    }
  }

  /// Flush pending data - ported from Python code
  Future<void> _flush() async {
    if (_txCharacteristic == null || _pendingData.isEmpty) return;

    int offset = 0;
    while (offset < _pendingData.length) {
      // Wait if paused
      while (_isPaused) {
        await Future.delayed(Duration(milliseconds: 200));
      }

      int chunkSize = (_pendingData.length - offset).clamp(0, _config.mtu);
      List<int> chunk = _pendingData.sublist(offset, offset + chunkSize);

      await _txCharacteristic!.write(chunk, withoutResponse: true);
      await Future.delayed(Duration(milliseconds: 20));

      offset += chunkSize;
    }

    _pendingData.clear();
  }

  /// Prepare printer for printing - optimized based on Python settings
  Future<void> _prepare({int? energy}) async {
    if (_commands == null) return;

    // For MXW01, preparation is different
    if (_isMXW01) {
      await _prepareMXW01(energy: energy);
      return;
    }

    await _sendCommand(_commands!.getDeviceStateCommand());
    await _sendCommand(_commands!.getStartPrintCommand());
    await _sendCommand(_commands!.getSetDpiCommand());

    // Use energy from parameter or default from Python implementation
    int energyLevel =
        energy ?? 4096; // Default: 0x1000 (4096) - moderate level like Python
    await _sendCommand(_commands!.getSetEnergyCommand(energyLevel));

    // Quality: 5 - maximum quality like Python
    if (_config.speed > 0) {
      await _sendCommand(_commands!.getSetSpeedCommand(_config.speed));
    }

    await _sendCommand(_commands!.getApplyEnergyCommand());
    await _sendCommand(_commands!.getUpdateDeviceCommand());
    await _flush();

    // Add delay like Python (100ms between tasks)
    await Future.delayed(Duration(milliseconds: 100));

    await _sendCommand(_commands!.getStartLatticeCommand());
  }

  /// Prepare MXW01 printer for printing
  Future<void> _prepareMXW01({int? energy}) async {
    if (_commands == null) return;

    // Set print intensity for MXW01
    int intensity = energy != null ? (energy * 100 / 4096).round() : 50;
    if (intensity > 100) intensity = 100;

    final mxw01Commands = _commands as MXW01PrinterCommands;
    await _sendCommandForModel(
        mxw01Commands.getPrintIntensityCommand(intensity));
  }

  /// Finish printing - optimized based on Python implementation
  Future<void> _finish() async {
    if (_commands == null) return;

    // For MXW01, finishing is different
    if (_isMXW01) {
      await _finishMXW01();
      return;
    }

    await _sendCommand(_commands!.getEndLatticeCommand());
    await _sendCommand(_commands!.getSetSpeedCommand(8));

    // Feed paper like Python (0x5, 0x00 = 5 steps)
    await _sendCommand(_commands!.getFeedPaperCommand(5));

    if (_model!.problemFeeding) {
      // Send empty bitmap data for problematic models
      List<int> emptyLine = List.filled(_model!.paperWidth ~/ 8, 0);
      for (int i = 0; i < 128; i++) {
        await _sendCommand(_commands!.getDrawBitmapCommand(emptyLine));
      }
    }

    await _sendCommand(_commands!.getDeviceStateCommand());
    await _flush();

    // Add delay like Python (100ms between tasks)
    await Future.delayed(Duration(milliseconds: 100));
  }

  /// Finish MXW01 printing
  Future<void> _finishMXW01() async {
    // MXW01 specific finishing - flush and complete
    final mxw01Commands = _commands as MXW01PrinterCommands;
    await _sendCommandForModel(mxw01Commands.getPrintDataFlushCommand());
  }

  /// Print text - improved bitmap-based implementation
  /// Based on WerWolv's findings: printer doesn't support direct text, only bitmaps
  Future<void> printText(String text,
      {int fontSize = 16,
      double? threshold,
      int? energy,
      String ditherType = 'threshold'}) async {
    if (!_isConnected || _model == null) {
      throw Exception('Printer not connected');
    }

    // Split text into lines and calculate dimensions
    List<String> lines = text.split('\n');
    int lineHeight = fontSize + 6; // More spacing between lines
    int totalHeight = lines.length * lineHeight + 20;

    // Create image for text rendering
    img.Image textImage = img.Image(
      width: _model!.paperWidth,
      height: totalHeight,
    );

    // Fill with white background (important for thermal printing)
    img.fill(textImage, color: img.ColorRgb8(255, 255, 255));

    // Improved character rendering based on bitmap approach
    int charWidth = (fontSize * 0.6).round(); // Better proportions
    int yOffset = 10;

    for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
      String line = lines[lineIndex];
      int y = yOffset + (lineIndex * lineHeight);

      for (int i = 0; i < line.length; i++) {
        int x = i * (charWidth + 2) + 5; // Add spacing between chars

        if (x + charWidth < _model!.paperWidth) {
          String char = line[i];

          // Create character bitmap pattern based on ASCII
          _drawCharacterBitmap(textImage, char, x, y, charWidth, fontSize);
        }
      }
    }

    await printImage(textImage,
        threshold: threshold, energy: energy, ditherType: ditherType);
  }

  /// Draw character as bitmap pattern - mimics font rendering
  void _drawCharacterBitmap(
      img.Image image, String char, int x, int y, int width, int height) {
    // Simple bitmap patterns for common characters
    // This is a basic implementation - in production, use proper font rendering

    if (char == ' ') return; // Space - no drawing needed

    // Default pattern for most characters - solid rectangle with internal pattern
    img.fillRect(
      image,
      x1: x,
      y1: y,
      x2: x + width,
      y2: y + height,
      color: img.ColorRgb8(0, 0, 0),
    );

    // Add character-specific patterns to make text more readable
    switch (char.toLowerCase()) {
      case 'a':
      case 'e':
      case 'o':
        // Vowels - hollow center
        img.fillRect(
          image,
          x1: x + 2,
          y1: y + 3,
          x2: x + width - 2,
          y2: y + height - 3,
          color: img.ColorRgb8(255, 255, 255),
        );
        break;
      case 'i':
      case 'l':
        // Thin characters
        img.fillRect(
          image,
          x1: x + width ~/ 3,
          y1: y,
          x2: x + (width * 2) ~/ 3,
          y2: y + height,
          color: img.ColorRgb8(0, 0, 0),
        );
        img.fillRect(
          image,
          x1: x,
          y1: y,
          x2: x + width,
          y2: y + height,
          color: img.ColorRgb8(255, 255, 255),
        );
        img.fillRect(
          image,
          x1: x + width ~/ 3,
          y1: y,
          x2: x + (width * 2) ~/ 3,
          y2: y + height,
          color: img.ColorRgb8(0, 0, 0),
        );
        break;
      default:
        // Other characters - add some internal white space for readability
        if (width > 4 && height > 6) {
          img.fillRect(
            image,
            x1: x + 1,
            y1: y + 2,
            x2: x + width - 1,
            y2: y + height - 2,
            color: img.ColorRgb8(255, 255, 255),
          );
          // Add some black dots for texture
          for (int dy = 2; dy < height - 2; dy += 3) {
            for (int dx = 1; dx < width - 1; dx += 2) {
              image.setPixel(x + dx, y + dy, img.ColorRgb8(0, 0, 0));
            }
          }
        }
    }
  }

  /// Print image - menggunakan pendekatan blog untuk hasil lebih baik
  Future<void> printImage(img.Image image,
      {double? threshold,
      int? energy,
      String ditherType = 'threshold',
      double widthScale = 0.6,
      double heightScale = 0.5}) async {
    if (!_isConnected || _model == null) {
      throw Exception('Printer not connected');
    }

    // Resize image using configurable scale factors
    int targetWidth = (_model!.paperWidth * widthScale).round();
    img.Image processedImage;

    if (image.width > targetWidth) {
      // Calculate height with configurable reduction factor
      int proportionalHeight =
          (image.height * targetWidth / image.width).round();
      int reducedHeight = (proportionalHeight * heightScale).round();

      processedImage = img.copyResize(
        image,
        width: targetWidth,
        height: reducedHeight,
        interpolation: img.Interpolation.cubic,
      );
    } else {
      // Image is already narrow enough, but still apply scale factors
      int reducedWidth = (image.width * widthScale).round();
      int reducedHeight = (image.height * heightScale).round();

      processedImage = img.copyResize(
        image,
        width: reducedWidth,
        height: reducedHeight,
        interpolation: img.Interpolation.cubic,
      );
    }

    // Konversi ke RGBA untuk processing seperti blog (jangan langsung grayscale)
    // processedImage sudah dalam format yang tepat setelah copyResize
    img.Image rgbaImage = processedImage;

    // Apply flip if configured
    if (_config.flipH || _config.flipV) {
      if (_config.flipH) {
        rgbaImage = img.flipHorizontal(rgbaImage);
      }
      if (_config.flipV) {
        rgbaImage = img.flipVertical(rgbaImage);
      }
    }

    // Use different printing approach for MXW01
    if (_isMXW01) {
      await _printImageMXW01(rgbaImage,
          energy: energy ?? 50, threshold: threshold ?? 128.0);
      return;
    }

    await _prepare(energy: energy);

    // Set default values seperti blog
    // Energy: 0x10, 0x00 = 4096 (moderate level)
    // Quality: 5 (high)
    // Drawing Mode: 1 (image mode)
    if (_commands != null) {
      await _sendCommand(_commands!.getSetEnergyCommand(energy ?? 4096));
      if (_commands! is GenericPrinterCommands) {
        final genericCommands = _commands as GenericPrinterCommands;
        await _sendCommand(genericCommands.getSetQualityCommand(5));
        await _sendCommand(genericCommands.getDrawingModeCommand(1));
      }
    }

    // Process image line by line seperti blog - pixel by pixel approach
    for (int y = 0; y < rgbaImage.height; y++) {
      List<int> bmp = [];
      int bit = 0;

      // Process setiap pixel seperti blog (bukan 8 pixel sekaligus)
      // Tapi kita perlu memastikan ukuran bitmap sesuai dengan paper width
      for (int x = 0; x < _model!.paperWidth; x++) {
        if (bit % 8 == 0) {
          bmp.add(0x00);
        }

        // Shift right dulu seperti blog
        bmp[bit ~/ 8] >>= 1;

        // Check if we're within image bounds
        if (x < rgbaImage.width) {
          // Get RGBA values seperti blog
          img.Pixel pixel = rgbaImage.getPixel(x, y);
          int r = pixel.r.toInt();
          int g = pixel.g.toInt();
          int b = pixel.b.toInt();
          int a = pixel.a.toInt();

          // Apply threshold seperti blog: (r < 0x80 and g < 0x80 and b < 0x80 and a > 0x80)
          double thresholdValue =
              threshold ?? 128.0; // Default 0x80 = 128 seperti blog
          if (r < thresholdValue &&
              g < thresholdValue &&
              b < thresholdValue &&
              a > thresholdValue) {
            bmp[bit ~/ 8] |= 0x80; // Set MSB seperti blog
          }
        }
        // Jika di luar bounds image, biarkan sebagai 0 (putih)

        bit++;
      }

      // Check if line is empty (optimization)
      bool lineEmpty = bmp.every((byte) => byte == 0);
      if (lineEmpty && !_config.dryRun) {
        continue; // Skip empty lines
      }

      if (_config.dryRun) {
        bmp = List.filled(bmp.length, 0); // Empty data for dry run
      }

      // Send bitmap line
      if (_commands != null) {
        await _sendCommand(_commands!.getDrawBitmapCommand(bmp));
      }
      // Tidak perlu feed paper setiap baris - ini yang menyebabkan jarak
      // await _sendCommand(PrinterCommander.getFeedPaperCommand(1));

      // Add delay seperti blog - 40ms untuk prevent printer jamming
      // await Future.delayed(Duration(milliseconds: 40));
    }

    await _finish();
  }

  /// Convert image to bitmap data using simple threshold (Python approach)
  /// This simple approach produces better results than complex dithering
  List<int> _imageToBitmapSimple(img.Image image, {required double threshold}) {
    List<int> bitmapData = [];

    // Process line by line like Python implementation
    for (int y = 0; y < image.height; y++) {
      for (int x = 0; x < _model!.paperWidth; x += 8) {
        int byte = 0;

        // Process 8 pixels at a time to create one byte
        for (int bit = 0; bit < 8; bit++) {
          byte >>= 1; // Shift right first (like Python)

          if (x + bit < image.width) {
            img.Pixel pixel = image.getPixel(x + bit, y);
            int gray = pixel.r.toInt(); // Already grayscale

            // Simple threshold check like Python (r < 0x80 and g < 0x80 and b < 0x80)
            if (gray < threshold) {
              byte |= 0x80; // Set the MSB
            }
          }
        }

        bitmapData.add(byte);
      }
    }

    return bitmapData;
  }

  /// Get printer status (MXW01 specific)
  Future<void> getPrinterStatus() async {
    if (!_isConnected || _commands == null) {
      throw Exception('Printer not connected');
    }

    if (_isMXW01) {
      final statusCmd = _commands!.getStatusCommand();
      final versionCmd = _commands!.getVersionCommand();
      if (statusCmd != null) await _sendCommandForModel(statusCmd);
      if (versionCmd != null) await _sendCommandForModel(versionCmd);
    } else {
      await _sendCommand(_commands!.getDeviceStateCommand());
    }
  }

  /// Get battery level (MXW01 specific)
  Future<void> getBatteryLevel() async {
    if (!_isConnected || _commands == null) {
      throw Exception('Printer not connected');
    }

    if (_isMXW01) {
      final batteryCmd = _commands!.getBatteryCommand();
      if (batteryCmd != null) await _sendCommandForModel(batteryCmd);
    }
  }

  /// Eject paper (MXW01 specific)
  Future<void> ejectPaper(int lineCount) async {
    if (!_isConnected || _commands == null) {
      throw Exception('Printer not connected');
    }

    await _sendCommand(_commands!.getFeedPaperCommand(lineCount));
  }

  /// Retract paper (MXW01 specific)
  Future<void> retractPaper(int lineCount) async {
    if (!_isConnected || _commands == null) {
      throw Exception('Printer not connected');
    }

    await _sendCommand(_commands!.getRetractPaperCommand(lineCount));
  }

  /// Update printer configuration
  void updateConfig(PrinterConfig newConfig) {
    _config = newConfig;
  }

  /// Dispose resources
  void dispose() {
    disconnect();
  }

  /// Print image using MXW01 specific protocol
  Future<void> _printImageMXW01(img.Image image,
      {required int energy, required double threshold}) async {
    if (_dataCharacteristic == null) {
      throw Exception('Data characteristic not found for MXW01');
    }

    // Set print intensity (0-100)
    if (_commands != null && _commands! is MXW01PrinterCommands) {
      final mxw01Commands = _commands! as MXW01PrinterCommands;
      await _sendCommandForModel(
          mxw01Commands.getPrintIntensityCommand(energy));
    }

    // Convert image to bitmap data
    List<int> bitmapData = [];
    int bytesPerLine =
        _model!.paperWidth ~/ 8; // 384 pixels = 48 bytes per line

    for (int y = 0; y < image.height; y++) {
      for (int x = 0; x < _model!.paperWidth; x += 8) {
        int byte = 0;

        for (int bit = 0; bit < 8; bit++) {
          byte >>= 1;

          if (x + bit < image.width) {
            img.Pixel pixel = image.getPixel(x + bit, y);
            int r = pixel.r.toInt();
            int g = pixel.g.toInt();
            int b = pixel.b.toInt();
            int a = pixel.a.toInt();

            // Apply threshold like C# implementation
            if (r < threshold &&
                g < threshold &&
                b < threshold &&
                a > threshold) {
              byte |= 0x80;
            }
          }
        }

        bitmapData.add(byte);
      }
    }

    int lineCount = image.height;

    // Send print command with line count and print mode (0x0 = Monochrome)
    if (_commands != null && _commands! is MXW01PrinterCommands) {
      final mxw01Commands = _commands! as MXW01PrinterCommands;
      await _sendCommandForModel(mxw01Commands.getPrintCommand(lineCount, 0x0));
    }

    // Send bitmap data line by line using data characteristic
    for (int line = 0; line < lineCount; line++) {
      int startIndex = line * bytesPerLine;
      int endIndex = startIndex + bytesPerLine;

      if (endIndex <= bitmapData.length) {
        List<int> lineData = bitmapData.sublist(startIndex, endIndex);
        await _dataCharacteristic!.write(lineData, withoutResponse: true);

        // Small delay to prevent overwhelming the printer
        await Future.delayed(Duration(milliseconds: 10));
      }
    }

    // Flush print data
    if (_commands != null && _commands! is MXW01PrinterCommands) {
      final mxw01Commands = _commands! as MXW01PrinterCommands;
      await _sendCommandForModel(mxw01Commands.getPrintDataFlushCommand());
    }

    // Wait for print completion (simplified - in C# this waits for notification)
    await Future.delayed(Duration(seconds: 2));
  }

  /// Helper function to compare lists
  bool _listEquals(List<int> a, List<int> b) {
    if (a.length != b.length) return false;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) return false;
    }
    return true;
  }

  /// Print widget - converts widget to image and prints it
  /// This function allows developers to directly print any Flutter widget
  Future<void> printWidget(
    Widget widget, {
    double? threshold,
    int? energy,
    String ditherType = 'threshold',
    double widthScale = 0.6,
    double heightScale = 0.5,
    double pixelRatio = 1.0,
    Size? customSize,
  }) async {
    if (!_isConnected || _model == null) {
      throw Exception('Printer not connected');
    }

    try {
      // Convert widget to image
      img.Image? image = await _widgetToImage(
        widget,
        pixelRatio: pixelRatio,
        customSize: customSize,
      );

      if (image == null) {
        throw Exception('Failed to convert widget to image');
      }

      // Use existing printImage function
      await printImage(
        image,
        threshold: threshold,
        energy: energy,
        ditherType: ditherType,
        widthScale: widthScale,
        heightScale: heightScale,
      );
    } catch (e) {
      throw Exception('Failed to print widget: $e');
    }
  }

  /// Convert widget to image using RenderRepaintBoundary
  Future<img.Image?> _widgetToImage(
    Widget widget, {
    double pixelRatio = 1.0,
    Size? customSize,
  }) async {
    try {
      // Calculate size based on printer paper width if not provided
      Size targetSize = customSize ??
          Size(
            _model!.paperWidth.toDouble(),
            _model!.paperWidth.toDouble(), // Square by default
          );

      // Create a repaint boundary to capture the widget
      final RenderRepaintBoundary repaintBoundary = RenderRepaintBoundary();

      // Create a pipeline owner and build owner
      final PipelineOwner pipelineOwner = PipelineOwner();
      final BuildOwner buildOwner = BuildOwner(focusManager: FocusManager());

      // Create a render view to render the widget
      final RenderView renderView = RenderView(
        configuration: ViewConfiguration(
          logicalConstraints: BoxConstraints.tight(targetSize),
          devicePixelRatio: pixelRatio,
        ),
        view: WidgetsBinding.instance.platformDispatcher.views.first,
      );

      // Set up the render tree properly
      renderView.child = repaintBoundary;
      pipelineOwner.rootNode = renderView;
      renderView.prepareInitialFrame();

      // Build the widget tree
      final RenderObjectToWidgetElement<RenderBox> rootElement =
          RenderObjectToWidgetAdapter<RenderBox>(
        container: repaintBoundary,
        child: Directionality(
          textDirection: TextDirection.ltr,
          child: MediaQuery(
            data: MediaQueryData(
              size: targetSize,
              devicePixelRatio: pixelRatio,
            ),
            child: Material(
              color: Colors.white,
              child: widget,
            ),
          ),
        ),
      ).attachToRenderTree(buildOwner);

      // Build and layout with proper sequence
      buildOwner.buildScope(rootElement);
      buildOwner.finalizeTree();

      // Flush the pipeline in correct order
      pipelineOwner.flushLayout();
      pipelineOwner.flushCompositingBits();
      pipelineOwner.flushPaint();

      // Capture the image
      final ui.Image uiImage = await repaintBoundary.toImage(
        pixelRatio: pixelRatio,
      );

      // Convert to byte data
      final ByteData? byteData = await uiImage.toByteData(
        format: ui.ImageByteFormat.png,
      );

      if (byteData == null) {
        return null;
      }

      // Convert to image package format
      final Uint8List pngBytes = byteData.buffer.asUint8List();
      final img.Image? image = img.decodePng(pngBytes);

      // Clean up
      uiImage.dispose();
      // Note: BuildOwner doesn't have dispose method in current Flutter version

      return image;
    } catch (e) {
      print('Error converting widget to image: $e');
      return null;
    }
  }
}
