// Cat Printer Service - Main service for Cat Printer operations
// Ported from Python printer.py

import 'dart:async';
import 'dart:typed_data';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:image/image.dart' as img;
import '../models/printer_models.dart';
import 'printer_commander.dart';

class CatPrinterService {
  BluetoothDevice? _device;
  BluetoothCharacteristic? _txCharacteristic;
  BluetoothCharacteristic? _rxCharacteristic;
  PrinterModel? _model;
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

      print('Found supported model: ${_model!.name}');

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
          bool isTxChar = uuid == PrinterCommander.txCharacteristic ||
              uuid == 'ae01' ||
              uuid.contains('ae01');
          bool isRxChar = uuid == PrinterCommander.rxCharacteristic ||
              uuid == 'ae02' ||
              uuid.contains('ae02');

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
          }
        }
      }

      if (_txCharacteristic == null || _rxCharacteristic == null) {
        print('TX Characteristic found: ${_txCharacteristic != null}');
        print('RX Characteristic found: ${_rxCharacteristic != null}');
        print('Expected TX UUID: ${PrinterCommander.txCharacteristic}');
        print('Expected RX UUID: ${PrinterCommander.rxCharacteristic}');
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
      _model = null;
      _isConnected = false;
      _isPaused = false;
      _pendingData.clear();
    }
  }

  /// Handle notifications from printer - ported from Python code
  void _handleNotification(List<int> data) {
    if (_listEquals(data, PrinterCommander.dataFlowPause)) {
      _isPaused = true;
    } else if (_listEquals(data, PrinterCommander.dataFlowResume)) {
      _isPaused = false;
    }
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
    await _sendCommand(PrinterCommander.getDeviceStateCommand());

    if (_model!.isNewKind) {
      await _sendCommand(PrinterCommander.getStartPrintNewCommand());
    } else {
      await _sendCommand(PrinterCommander.getStartPrintCommand());
    }

    await _sendCommand(PrinterCommander.getSetDpiAs200Command());

    // Use energy from parameter or default from Python implementation
    int energyLevel =
        energy ?? 4096; // Default: 0x1000 (4096) - moderate level like Python
    await _sendCommand(PrinterCommander.getSetEnergyCommand(energyLevel));

    // Quality: 5 - maximum quality like Python
    if (_config.speed > 0) {
      await _sendCommand(PrinterCommander.getSetSpeedCommand(_config.speed));
    }

    await _sendCommand(PrinterCommander.getApplyEnergyCommand());
    await _sendCommand(PrinterCommander.getUpdateDeviceCommand());
    await _flush();

    // Add delay like Python (100ms between tasks)
    await Future.delayed(Duration(milliseconds: 100));

    await _sendCommand(PrinterCommander.getStartLatticeCommand());
  }

  /// Finish printing - optimized based on Python implementation
  Future<void> _finish() async {
    await _sendCommand(PrinterCommander.getEndLatticeCommand());
    await _sendCommand(PrinterCommander.getSetSpeedCommand(8));

    // Feed paper like Python (0x5, 0x00 = 5 steps)
    await _sendCommand(PrinterCommander.getFeedPaperCommand(5));

    if (_model!.problemFeeding) {
      // Send empty bitmap data for problematic models
      List<int> emptyLine = List.filled(_model!.paperWidth ~/ 8, 0);
      for (int i = 0; i < 128; i++) {
        await _sendCommand(PrinterCommander.getDrawBitmapCommand(emptyLine));
      }
    }

    await _sendCommand(PrinterCommander.getDeviceStateCommand());
    await _flush();

    // Add delay like Python (100ms between tasks)
    await Future.delayed(Duration(milliseconds: 100));
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

    await _prepare(energy: energy);

    // Set default values seperti blog
    // Energy: 0x10, 0x00 = 4096 (moderate level)
    // Quality: 5 (high)
    // Drawing Mode: 1 (image mode)
    await _sendCommand(PrinterCommander.getSetEnergyCommand(energy ?? 4096));
    await _sendCommand(PrinterCommander.getSetQualityCommand(5));
    await _sendCommand(PrinterCommander.getDrawingModeCommand(1));

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
      await _sendCommand(PrinterCommander.getDrawBitmapCommand(bmp));
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

  /// Update printer configuration
  void updateConfig(PrinterConfig newConfig) {
    _config = newConfig;
  }

  /// Dispose resources
  void dispose() {
    disconnect();
  }

  /// Helper function to compare lists
  bool _listEquals(List<int> a, List<int> b) {
    if (a.length != b.length) return false;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) return false;
    }
    return true;
  }
}
