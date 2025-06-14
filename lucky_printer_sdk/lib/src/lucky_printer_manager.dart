import 'dart:async';
import 'dart:io';
import 'dart:typed_data';
import 'dart:ui' as ui;
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:permission_handler/permission_handler.dart';
import 'models/printer_model.dart';
import 'models/printer_status.dart';
import 'models/print_job.dart';
import 'commands/printer_commands.dart';
import 'utils/printer_utils.dart';

enum ConnectionType {
  classic,
  ble,
  auto // Auto-detect based on device
}

class LuckyPrinterManager {
  static final LuckyPrinterManager _instance = LuckyPrinterManager._internal();
  factory LuckyPrinterManager() => _instance;
  LuckyPrinterManager._internal();

  final StreamController<String> _logController =
      StreamController<String>.broadcast();
  final StreamController<List<BluetoothDevice>> _devicesController =
      StreamController<List<BluetoothDevice>>.broadcast();
  final StreamController<PrinterStatus> _statusController =
      StreamController<PrinterStatus>.broadcast();

  Stream<String> get logStream => _logController.stream;
  Stream<List<BluetoothDevice>> get devicesStream => _devicesController.stream;
  Stream<PrinterStatus> get statusStream => _statusController.stream;

  // BLE connection variables
  BluetoothDevice? _connectedDevice;
  BluetoothCharacteristic? _writeCharacteristic;
  BluetoothCharacteristic? _notifyCharacteristic;
  StreamSubscription<List<int>>? _notifySubscription;

  // Classic Bluetooth connection variables
  Socket? _classicSocket;
  StreamSubscription<Uint8List>? _classicSubscription;

  bool _isInitialized = false;
  bool _isScanning = false;
  List<BluetoothDevice> _discoveredDevices = [];
  ConnectionType _currentConnectionType = ConnectionType.auto;

  // Lucky Printer service and characteristic UUIDs for BLE
  static const String _serviceUuid = "49535343-fe7d-4ae5-8fa9-9fafd205e455";
  static const String _writeCharacteristicUuid =
      "49535343-8841-43f4-a8d4-ecbe34729bb3";
  static const String _notifyCharacteristicUuid =
      "49535343-1e4d-4bd9-ba61-23c647249616";

  // Classic Bluetooth port for SPP (Serial Port Profile)
  static const int _classicBluetoothPort = 1;

  Future<bool> initialize() async {
    try {
      _log('Initializing Lucky Printer Manager with dual connection support');

      // Check Bluetooth permissions
      bool permissionGranted = await _requestPermissions();
      if (!permissionGranted) {
        _log('Bluetooth permissions not granted');
        return false;
      }

      // Initialize Flutter Blue Plus for BLE scanning
      if (await FlutterBluePlus.isSupported) {
        _log('BLE support detected');
      } else {
        _log('BLE not supported on this device');
      }

      _isInitialized = true;
      _log('Lucky Printer Manager initialized successfully');
      return true;
    } catch (e) {
      _log('Failed to initialize: $e');
      return false;
    }
  }

  Future<bool> _requestPermissions() async {
    try {
      if (Platform.isAndroid) {
        Map<Permission, PermissionStatus> statuses = await [
          Permission.bluetoothScan,
          Permission.bluetoothConnect,
          Permission.bluetoothAdvertise,
          Permission.location,
        ].request();

        bool allGranted = statuses.values
            .every((status) => status == PermissionStatus.granted);

        if (!allGranted) {
          _log('Some permissions not granted: $statuses');
        }

        return allGranted;
      }
      return true;
    } catch (e) {
      _log('Permission request failed: $e');
      return false;
    }
  }

  Future<void> startScanning({bool scanAllDevices = false}) async {
    if (!_isInitialized) {
      _log('Manager not initialized');
      return;
    }

    if (_isScanning) {
      _log('Already scanning');
      return;
    }

    try {
      _isScanning = true;
      _discoveredDevices.clear();
      _devicesController.add(_discoveredDevices);

      _log('Starting device scan...');

      // Start BLE scan
      await FlutterBluePlus.startScan(timeout: const Duration(seconds: 10));

      // Listen to scan results
      FlutterBluePlus.scanResults.listen((results) {
        for (ScanResult result in results) {
          BluetoothDevice device = result.device;

          if (device.platformName.isNotEmpty) {
            bool isLuckyPrinter =
                scanAllDevices || _isLuckyPrinter(device.platformName);

            if (isLuckyPrinter &&
                !_discoveredDevices.any((d) => d.remoteId == device.remoteId)) {
              _discoveredDevices.add(device);
              _devicesController.add(List.from(_discoveredDevices));

              ConnectionType detectedType =
                  _detectConnectionType(device.platformName);
              _log(
                  'Found ${detectedType.name.toUpperCase()} device: ${device.platformName} (${device.remoteId})');
            }
          }
        }
      });

      // Auto-stop scanning after timeout
      Timer(const Duration(seconds: 10), () async {
        await stopScanning();
      });
    } catch (e) {
      _log('Scan failed: $e');
      _isScanning = false;
    }
  }

  ConnectionType _detectConnectionType(String deviceName) {
    // Based on Java SDK logic: check device name to determine connection type
    String name = deviceName.toLowerCase();

    // PPD series and similar devices use Classic Bluetooth despite "BLE" in name
    // This is the key insight from the Java SDK
    if (name.contains('ppd') ||
        name.contains('peripage') ||
        name.contains('paperang')) {
      return ConnectionType.classic;
    }

    // Traditional Lucky Printer models that use Classic (from Java SDK)
    if (name.contains('d100') || name.contains('gd985')) {
      return ConnectionType.classic;
    }

    // Modern Lucky Printer models that use BLE
    if (name.contains('luckp') || name.contains('lucky')) {
      return ConnectionType.ble;
    }

    // For PPD devices, even if they advertise BLE, they actually use Classic protocol
    // This is why PPD1_47E9_BLE should use Classic connection type
    if (name.contains('ppd') && name.contains('ble')) {
      return ConnectionType.classic;
    }

    // Default to BLE for unknown devices
    return ConnectionType.ble;
  }

  bool _isLuckyPrinter(String deviceName) {
    List<String> patterns = [
      'ppd',
      'luckp',
      'lucky',
      'peripage',
      'paperang',
      'd100',
      'gd985',
      'dp_',
      'cat'
    ];

    String name = deviceName.toLowerCase();
    return patterns.any((pattern) => name.contains(pattern));
  }

  Future<void> stopScanning() async {
    if (_isScanning) {
      await FlutterBluePlus.stopScan();
      _isScanning = false;
      _log('Scan stopped');
    }
  }

  Future<bool> connectToDevice(BluetoothDevice device,
      {ConnectionType? forceConnectionType}) async {
    try {
      if (_connectedDevice != null || _classicSocket != null) {
        await disconnect();
      }

      ConnectionType connectionType =
          forceConnectionType ?? _detectConnectionType(device.platformName);
      _currentConnectionType = connectionType;

      _log(
          'Attempting ${connectionType.name.toUpperCase()} connection to ${device.platformName}');

      bool success = false;

      if (connectionType == ConnectionType.classic) {
        success = await _connectClassicBluetooth(device);
      } else {
        success = await _connectBLE(device);
      }

      if (success) {
        _connectedDevice = device;
        _log(
            'Successfully connected to ${device.platformName} via ${connectionType.name.toUpperCase()}');
        _updatePrinterStatus();
      }

      return success;
    } catch (e) {
      _log('Connection failed: $e');
      return false;
    }
  }

  Future<bool> _connectClassicBluetooth(BluetoothDevice device) async {
    try {
      _log('Connecting via Classic Bluetooth protocol...');

      // IMPORTANT: PPD1 and similar devices advertise as BLE but need Classic protocol
      // We'll connect via BLE but use Classic Bluetooth commands and data format
      _log(
          'PPD device detected - using Classic Bluetooth protocol over BLE transport');

      // Connect using BLE transport but Classic protocol
      bool connected = await _connectBLE(device);

      if (connected) {
        _log('BLE transport established, switching to Classic protocol mode');
        // We're now connected via BLE but will use Classic Bluetooth commands
        return true;
      }

      return false;
    } catch (e) {
      _log('Classic Bluetooth connection failed: $e');
      return false;
    }
  }

  Future<bool> _connectBLE(BluetoothDevice device) async {
    try {
      _log('Connecting via BLE...');
      await device.connect();
      _connectedDevice = device;

      // Discover services
      List<BluetoothService> services = await device.discoverServices();
      _log('Discovered ${services.length} services');

      // DEBUG: Show all services and characteristics
      _log('=== PPD1 FULL SERVICE DEBUG ===');
      for (BluetoothService service in services) {
        _log('Service: ${service.uuid}');
        for (BluetoothCharacteristic char in service.characteristics) {
          _log(
              '  Char: ${char.uuid} - Write:${char.properties.write} WriteNoResp:${char.properties.writeWithoutResponse} Notify:${char.properties.notify}');
        }
      }
      _log('=== END FULL SERVICE DEBUG ===');

      // Find write characteristic - CORRECTED TO USE PROPER PRINTER CHARACTERISTICS
      List<String> writeCharUuids = [
        'ff02', // Lucky Printer primary write characteristic
        '49535343-8841-43f4-a8d4-ecbe34729bb3', // Alternative write
        '6e400002-b5a3-f393-e0a9-e50e24dcca9e', // Nordic UART TX
      ];

      // Find notify characteristic
      List<String> notifyCharUuids = [
        'ff01', // Lucky Printer primary notify characteristic
        'ff03', // Lucky Printer secondary notify characteristic
        '49535343-1e4d-4bd9-ba61-23c647249616', // Alternative notify
        '6e400003-b5a3-f393-e0a9-e50e24dcca9e', // Nordic UART RX
      ];

      // CRITICAL: Blacklist characteristics that should NEVER be used for printing
      List<String> blacklist = [
        '2a00', // Device Name - NEVER USE FOR PRINTING!
        '2a01', // Appearance
        '2a04', // Peripheral Preferred Connection Parameters
        '2a05', // Service Changed
      ];

      _log(
          'Looking for proper printer characteristics (avoiding blacklisted ones)...');

      // Find the service that contains printer characteristics (ff00 service)
      BluetoothService? targetService;
      for (BluetoothService service in services) {
        _log('Checking service: ${service.uuid}');
        if (service.uuid.toString().toLowerCase().contains('ff00')) {
          targetService = service;
          _log('Found Lucky Printer service: ${service.uuid}');
          break;
        }
      }

      // If no ff00 service, look for any service with writable characteristics
      if (targetService == null) {
        for (BluetoothService service in services) {
          for (BluetoothCharacteristic char in service.characteristics) {
            String charUuid = char.uuid.toString().toLowerCase();
            if (!blacklist.any((bl) => charUuid.contains(bl.toLowerCase())) &&
                (char.properties.write ||
                    char.properties.writeWithoutResponse)) {
              targetService = service;
              _log('Using service with writable char: ${service.uuid}');
              break;
            }
          }
          if (targetService != null) break;
        }
      }

      if (targetService == null) {
        _log('CRITICAL: No suitable service found!');
        await device.disconnect();
        return false;
      }

      // Find specific write characteristic (prioritize ff02)
      BluetoothCharacteristic? writeChar;
      for (String writeUuid in writeCharUuids) {
        for (BluetoothCharacteristic char in targetService.characteristics) {
          if (char.uuid
              .toString()
              .toLowerCase()
              .contains(writeUuid.toLowerCase())) {
            writeChar = char;
            _log('Found specific write char: ${char.uuid}');
            break;
          }
        }
        if (writeChar != null) break;
      }

      // Find notify characteristic (prioritize ff01, ff03)
      BluetoothCharacteristic? notifyChar;
      for (String notifyUuid in notifyCharUuids) {
        for (BluetoothCharacteristic char in targetService.characteristics) {
          if (char.uuid
              .toString()
              .toLowerCase()
              .contains(notifyUuid.toLowerCase())) {
            notifyChar = char;
            _log('Found specific notify char: ${char.uuid}');
            break;
          }
        }
        if (notifyChar != null) break;
      }

      if (writeChar == null) {
        _log('CRITICAL: No suitable write characteristic found!');
        _log('Available characteristics in service ${targetService.uuid}:');
        for (BluetoothCharacteristic char in targetService.characteristics) {
          _log(
              '  ${char.uuid} - Write:${char.properties.write} WriteNoResp:${char.properties.writeWithoutResponse}');
        }
        await device.disconnect();
        return false;
      }

      _writeCharacteristic = writeChar;
      _notifyCharacteristic = notifyChar;

      _log('Final selection:');
      _log('  Write char: ${_writeCharacteristic!.uuid}');
      _log('  Notify char: ${_notifyCharacteristic?.uuid ?? "None"}');

      // Subscribe to notifications if available
      if (_notifyCharacteristic != null) {
        try {
          await _notifyCharacteristic!.setNotifyValue(true);
          _notifySubscription =
              _notifyCharacteristic!.lastValueStream.listen((data) {
            _handleNotification(data);
          });
          _log('Subscribed to notifications');
        } catch (e) {
          _log('Failed to subscribe to notifications: $e');
        }
      }

      // Test connection with a simple command
      await Future.delayed(const Duration(milliseconds: 500));
      await _testConnection();

      return true;
    } catch (e) {
      _log('BLE connection failed: $e');
      return false;
    }
  }

  Future<void> _testConnection() async {
    try {
      _log('Testing connection...');
      bool sent = await sendCommand(PrinterCommands.getPrinterStatus());
      if (sent) {
        _log('Connection test successful');
      } else {
        _log('Connection test failed');
      }
    } catch (e) {
      _log('Connection test error: $e');
    }
  }

  Future<bool> sendCommand(Uint8List command) async {
    if (_currentConnectionType == ConnectionType.classic &&
        _classicSocket != null) {
      return await _sendClassicCommand(command);
    } else if (_connectedDevice != null && _writeCharacteristic != null) {
      return await _sendBLECommand(command);
    } else {
      _log('No active connection');
      return false;
    }
  }

  Future<bool> _sendClassicCommand(Uint8List command) async {
    try {
      if (_classicSocket == null) {
        _log('Classic socket not available');
        return false;
      }

      String hexCommand =
          command.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' ');
      _log('Sending Classic command (${command.length} bytes): $hexCommand');

      // Send data in chunks like the Java SDK does (16384 bytes max)
      const int chunkSize = 16384;
      int offset = 0;

      while (offset < command.length) {
        int end = (offset + chunkSize < command.length)
            ? offset + chunkSize
            : command.length;
        Uint8List chunk = command.sublist(offset, end);

        _classicSocket!.add(chunk);
        await _classicSocket!.flush();

        offset = end;

        // Small delay between chunks
        await Future.delayed(const Duration(milliseconds: 10));
      }

      _log('Classic command sent successfully');
      return true;
    } catch (e) {
      _log('Failed to send Classic command: $e');
      return false;
    }
  }

  Future<bool> _sendBLECommand(Uint8List command) async {
    if (_connectedDevice == null || _writeCharacteristic == null) {
      _log('Not connected to printer or no write characteristic');
      return false;
    }

    if (command.isEmpty) {
      _log('Empty command, skipping');
      return true;
    }

    try {
      String hexCommand =
          command.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' ');
      _log(
          'Sending BLE command (${command.length} bytes): ${hexCommand.length > 200 ? hexCommand.substring(0, 200) + "..." : hexCommand}');

      // CRITICAL FIX: Split large data into chunks for BLE transmission
      const int maxChunkSize =
          200; // Safe chunk size for BLE (well below 237 MTU)

      if (command.length <= maxChunkSize) {
        // Small command - send directly
        return await _sendSingleBLEChunk(command);
      } else {
        // Large command - split into chunks
        _log(
            'Large command detected (${command.length} bytes), splitting into chunks...');

        int offset = 0;
        int chunkNumber = 1;
        int totalChunks = (command.length / maxChunkSize).ceil();

        while (offset < command.length) {
          int end = (offset + maxChunkSize < command.length)
              ? offset + maxChunkSize
              : command.length;

          Uint8List chunk = command.sublist(offset, end);
          _log(
              'Sending chunk $chunkNumber/$totalChunks (${chunk.length} bytes)');

          bool success = await _sendSingleBLEChunk(chunk);
          if (!success) {
            _log('Failed to send chunk $chunkNumber/$totalChunks');
            return false;
          }

          offset = end;
          chunkNumber++;

          // Small delay between chunks to avoid overwhelming the printer
          await Future.delayed(const Duration(milliseconds: 20));
        }

        _log('All chunks sent successfully ($totalChunks chunks)');
        return true;
      }
    } catch (e) {
      _log('Send BLE command failed: $e');
      return false;
    }
  }

  Future<bool> _sendSingleBLEChunk(Uint8List chunk) async {
    try {
      // Try different write methods
      bool success = false;

      try {
        await _writeCharacteristic!.write(chunk, withoutResponse: false);
        success = true;
      } catch (e) {
        try {
          await _writeCharacteristic!.write(chunk, withoutResponse: true);
          success = true;
        } catch (e2) {
          _log('Both BLE write methods failed for chunk: $e2');
        }
      }

      if (success) {
        await Future.delayed(const Duration(milliseconds: 10));
        return true;
      } else {
        return false;
      }
    } catch (e) {
      _log('Send BLE chunk failed: $e');
      return false;
    }
  }

  void _handleNotification(List<int> data) {
    String hexData =
        data.map((b) => b.toRadixString(16).padLeft(2, '0')).join(' ');
    _log('Received notification: $hexData');

    // Process printer response
    // Update printer status based on response
  }

  Future<void> disconnect() async {
    try {
      // Disconnect BLE
      if (_notifySubscription != null) {
        await _notifySubscription!.cancel();
        _notifySubscription = null;
      }

      if (_connectedDevice != null) {
        await _connectedDevice!.disconnect();
        _connectedDevice = null;
        _writeCharacteristic = null;
        _notifyCharacteristic = null;
      }

      // Disconnect Classic
      if (_classicSubscription != null) {
        await _classicSubscription!.cancel();
        _classicSubscription = null;
      }

      if (_classicSocket != null) {
        await _classicSocket!.close();
        _classicSocket = null;
      }

      _log('Disconnected from printer');
    } catch (e) {
      _log('Disconnect error: $e');
    }
  }

  Future<void> _updatePrinterStatus() async {
    try {
      bool sent = await sendCommand(PrinterCommands.getPrinterStatus());
      if (sent) {
        _log('Status request sent');
      }
    } catch (e) {
      _log('Failed to get status: $e');
    }
  }

  // Print methods using appropriate connection type
  Future<bool> printText(String text) async {
    try {
      _log('Converting text to bitmap and printing (Java SDK approach): $text');

      if (_connectedDevice == null) {
        _log('No device connected');
        return false;
      }

      // CRITICAL: Lucky Printers ONLY accept BITMAP data, not raw text!
      // This is the key insight from Java SDK analysis

      // Step 1: Convert text to bitmap (like Android demo does)
      ui.Image textImage = await _textToBitmap(text);
      Uint8List bitmapData = await _imageToBitmapData(textImage);

      // Step 2: Follow exact Java SDK sequence for PPD1
      String deviceName = _connectedDevice?.platformName ?? "";
      if (deviceName.toLowerCase().contains('ppd')) {
        return await _printBitmapPPD1(bitmapData, text);
      } else {
        return await _printBitmapGeneric(bitmapData, text);
      }
    } catch (e) {
      _log('Print text error: $e');
      return false;
    }
  }

  // Convert text to bitmap using Flutter's Canvas (CORRECTED VERSION)
  Future<ui.Image> _textToBitmap(String text,
      {int maxWidth = 384,
      double fontSize = 16.0,
      Color textColor = Colors.black,
      Color backgroundColor = Colors.white}) async {
    final recorder = ui.PictureRecorder();
    final canvas = ui.Canvas(recorder);

    // Create text painter
    final textPainter = TextPainter(
      text: TextSpan(
        text: text,
        style: TextStyle(
          fontSize: fontSize,
          color: textColor,
          fontFamily: 'monospace',
        ),
      ),
      textDirection: TextDirection.ltr,
      maxLines: null,
    );

    textPainter.layout(
        maxWidth: maxWidth.toDouble() - 20); // 10px padding each side

    // Calculate canvas size
    final canvasWidth = maxWidth.toDouble();
    final canvasHeight = textPainter.height + 20; // Add some padding

    // Fill background
    final backgroundPaint = Paint()..color = backgroundColor;
    canvas.drawRect(
      Rect.fromLTWH(0, 0, canvasWidth, canvasHeight),
      backgroundPaint,
    );

    // Draw text
    textPainter.paint(canvas, const Offset(10, 10)); // 10px padding

    // Convert to image
    final picture = recorder.endRecording();
    final image = await picture.toImage(
      canvasWidth.toInt(),
      canvasHeight.toInt(),
    );

    return image;
  }

  // Convert UI Image to Lucky Printer bitmap format (EXACT JAVA SDK IMPLEMENTATION)
  Future<Uint8List> _imageToBitmapData(ui.Image image) async {
    final byteData = await image.toByteData(format: ui.ImageByteFormat.rawRgba);
    if (byteData == null) throw Exception('Failed to convert image to bytes');

    final Uint8List pixels = byteData.buffer.asUint8List();
    final int width = image.width;
    final int height = image.height;

    // Calculate byte width (must be divisible by 8)
    int byteWidth;
    if (width % 8 == 0) {
      byteWidth = width ~/ 8;
    } else {
      byteWidth = width ~/ 8 + 1;
    }

    // Create Lucky Printer bitmap header (following Java SDK getBitmapByteArray)
    List<int> header = [
      29, // ESC GS
      118, // v
      48, // 0
      0, // mode (0 = normal)
      byteWidth & 0xFF, // width_low
      (byteWidth >> 8) & 0xFF, // width_high
      height & 0xFF, // height_low
      (height >> 8) & 0xFF, // height_high
    ];

    // Convert pixels to monochrome bitmap data (following Java SDK algorithm)
    List<int> bitmapData = [];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < byteWidth; x++) {
        int byte = 0;
        for (int bit = 0; bit < 8; bit++) {
          int pixelX = x * 8 + bit;
          if (pixelX < width) {
            int pixelIndex = (y * width + pixelX) * 4;
            if (pixelIndex + 2 < pixels.length) {
              int r = pixels[pixelIndex];
              int g = pixels[pixelIndex + 1];
              int b = pixels[pixelIndex + 2];

              // Java SDK algorithm: (r + g + b) / 3 < 128 means black pixel
              int gray = (r + g + b) ~/ 3;
              if (gray < 128) {
                byte |= (128 >> bit); // Set bit for black pixel
              }
            }
          }
        }
        bitmapData.add(byte);
      }
    }

    _log(
        'Bitmap conversion: ${width}x${height} -> ${byteWidth} bytes/line, ${bitmapData.length} total bytes');

    // Combine header + bitmap data
    List<int> result = [];
    result.addAll(header);
    result.addAll(bitmapData);

    return Uint8List.fromList(result);
  }

  // PPD1 bitmap printing (CORRECTED WITH PROPER BITMAP FORMAT AND PROTOCOL)
  Future<bool> _printBitmapPPD1(
      Uint8List bitmapData, String originalText) async {
    try {
      _log('PPD1 printing sequence (corrected protocol)');

      // PPD1 uses a different sequence than other Lucky Printers
      // Based on Java SDK: PPD1.printOnce() method

      List<int> commands = [];

      // 1. enablePrinterLuck() - PPD1 uses mode 3
      commands.addAll([16, 255, 241, 3]);
      _log('1. enablePrinterLuck(3) sent');

      // 2. printerWakeupLuck() - 12 null bytes
      commands.addAll([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
      _log('2. printerWakeupLuck() sent');

      // 3. sendBitmap() - Direct bitmap data (no additional headers needed)
      commands.addAll(bitmapData);
      _log('3. sendBitmap() sent (${bitmapData.length} bytes)');

      // 4. printLineDotsLuck(getEndLineDot()) - PPD1 uses 80 dots
      commands.addAll([27, 74, 80]); // ESC J 80
      _log('4. printLineDotsLuck(80) sent');

      // 5. setMarkPrintLast() - PPD1 specific command
      commands.addAll([16, 255, 241, 68]); // Mark print last
      _log('5. setMarkPrintLast() sent');

      // 6. stopPrintJobLuck() - Final command
      commands.addAll([16, 255, 241, 69]);
      _log('6. stopPrintJobLuck() sent');

      Uint8List finalCommands = Uint8List.fromList(commands);
      bool result = await sendCommand(finalCommands);

      if (result) {
        _log('PPD1 print sequence completed successfully');
        return true;
      } else {
        _log('PPD1 print sequence failed');
        return false;
      }
    } catch (e) {
      _log('PPD1 print error: $e');
      return false;
    }
  }

  // Alternative PPD1 method - try simpler approach
  Future<bool> _printSimplePPD1(String text) async {
    try {
      _log('PPD1 simple print approach');

      List<int> commands = [];

      // Simple PPD1 sequence based on working Android demo
      commands.addAll([27, 64]); // ESC @ - Initialize printer
      commands.addAll([16, 255, 241, 3]); // Enable printer
      commands.addAll(text.codeUnits); // Direct text
      commands.add(10); // Line feed
      commands.addAll([27, 74, 80]); // Paper feed
      commands.addAll([16, 255, 241, 69]); // Stop

      Uint8List finalCommands = Uint8List.fromList(commands);
      bool result = await sendCommand(finalCommands);

      if (result) {
        _log('PPD1 simple print completed successfully');
        return true;
      } else {
        _log('PPD1 simple print failed');
        return false;
      }
    } catch (e) {
      _log('PPD1 simple print error: $e');
      return false;
    }
  }

  // Generic Lucky Printer bitmap printing (CORRECTED WITH PROPER BITMAP FORMAT)
  Future<bool> _printBitmapGeneric(
      Uint8List bitmapData, String originalText) async {
    try {
      _log(
          'Generic bitmap printing sequence (corrected with proper bitmap format)');

      List<int> commands = [];

      // Generic Lucky Printer sequence:
      // 1. printerWakeup
      commands.addAll([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
      _log('1. printerWakeup() sent');

      // 2. enablePrinter
      commands.addAll([16, 255, 241, 3]);
      _log('2. enablePrinter() sent');

      // 3. sendBitmap - NOW WITH PROPER BITMAP FORMAT!
      commands.addAll(bitmapData);
      _log(
          '3. sendBitmap() sent (${bitmapData.length} bytes with proper format)');

      // 4. printLineDots
      commands.addAll([27, 74, 80]);
      _log('4. printLineDots() sent');

      // 5. stopPrintJob
      commands.addAll([16, 255, 241, 69]);
      _log('5. stopPrintJob() sent');

      Uint8List finalCommands = Uint8List.fromList(commands);
      bool result = await sendCommand(finalCommands);

      if (result) {
        _log('Generic bitmap print sequence completed successfully');
        return true;
      } else {
        _log('Generic bitmap print sequence failed');
        return false;
      }
    } catch (e) {
      _log('Generic bitmap print error: $e');
      return false;
    }
  }

  Future<bool> printTestPage() async {
    try {
      _log('Printing test page with Java SDK approach...');

      if (_connectedDevice == null) {
        _log('No device connected');
        return false;
      }

      String testText = "=== LUCKY PRINTER TEST ===\n";
      testText += "Java SDK Approach\n";
      testText += DateTime.now().toString().substring(0, 19) + "\n";
      testText += "Bitmap Printing\n";
      testText += "SUCCESS!\n";
      testText += "========================\n";

      return await printText(testText);
    } catch (e) {
      _log('Print test page error: $e');
      return false;
    }
  }

  Future<bool> simpleTestPrint() async {
    try {
      _log('Running simple test with Java SDK sequence...');

      if (_connectedDevice == null) {
        _log('No device connected');
        return false;
      }

      // Use the exact sequence from Java SDK
      return await printText("SIMPLE TEST\nJava SDK\nOK!");
    } catch (e) {
      _log('Simple test print error: $e');
      return false;
    }
  }

  // New method specifically for PPD1 alternative test
  Future<bool> ppd1AlternativeTest() async {
    try {
      _log('Running PPD1 alternative test...');

      if (_connectedDevice == null) {
        _log('No device connected');
        return false;
      }

      Uint8List commands = PrinterCommands.ppd1AlternativeTest();
      bool result = await sendCommand(commands);

      if (result) {
        _log('PPD1 alternative test sent successfully');
        return true;
      } else {
        _log('PPD1 alternative test failed');
        return false;
      }
    } catch (e) {
      _log('PPD1 alternative test error: $e');
      return false;
    }
  }

  // Print text as bitmap (proper way for Lucky Printers) - CORRECTED VERSION
  Future<bool> printTextAsBitmap(String text) async {
    try {
      _log(
          'Starting bitmap-based text printing (corrected with proper bitmap format)');

      if (_connectedDevice == null) {
        _log('No device connected');
        return false;
      }

      String deviceName = _connectedDevice!.platformName;

      // For PPD1, try simple approach first, then bitmap if needed
      if (deviceName.contains('PPD1')) {
        _log('PPD1 detected - trying simple text approach first');
        bool simpleSuccess = await _printSimplePPD1(text);
        if (simpleSuccess) {
          return true;
        }

        _log('Simple approach failed, trying bitmap approach');
        // Convert text to bitmap using corrected method
        ui.Image image = await _textToBitmap(text);
        Uint8List bitmapData = await _imageToBitmapData(image);
        _log(
            'Text converted to bitmap: ${image.width}x${image.height}, bitmap data: ${bitmapData.length} bytes');

        return await _printBitmapPPD1(bitmapData, text);
      } else {
        // For other printers, use bitmap approach
        ui.Image image = await _textToBitmap(text);
        Uint8List bitmapData = await _imageToBitmapData(image);
        _log(
            'Text converted to bitmap: ${image.width}x${image.height}, bitmap data: ${bitmapData.length} bytes');

        return await _printBitmapGeneric(bitmapData, text);
      }
    } catch (e) {
      _log('Bitmap text printing error: $e');
      return false;
    }
  }

  // Simple raw command test (like Android demo doTest method)
  Future<bool> sendRawTestCommand() async {
    try {
      _log('Sending raw test command...');

      if (_connectedDevice == null) {
        _log('No device connected');
        return false;
      }

      // Send the same command as Android demo: {0x10, 0xff, 0x70}
      Uint8List testCommand = Uint8List.fromList([0x10, 0xff, 0x70]);
      bool result = await sendCommand(testCommand);

      if (result) {
        _log('Raw test command sent successfully');
        return true;
      } else {
        _log('Raw test command failed');
        return false;
      }
    } catch (e) {
      _log('Raw test command error: $e');
      return false;
    }
  }

  Future<void> debugCharacteristics() async {
    if (_connectedDevice == null) {
      _log('No device connected');
      return;
    }

    try {
      _log('=== Connection Debug Info ===');
      _log('Connected device: ${_connectedDevice!.platformName}');
      _log('Connection type: ${_currentConnectionType.name.toUpperCase()}');

      if (_currentConnectionType == ConnectionType.classic) {
        _log(
            'Classic socket status: ${_classicSocket != null ? "Connected" : "Disconnected"}');
      } else {
        if (_writeCharacteristic != null) {
          _log('Write characteristic: ${_writeCharacteristic!.uuid}');
          _log('Write properties: ${_writeCharacteristic!.properties}');
        } else {
          _log('No write characteristic found');
        }

        if (_notifyCharacteristic != null) {
          _log('Notify characteristic: ${_notifyCharacteristic!.uuid}');
          _log('Notify properties: ${_notifyCharacteristic!.properties}');
        } else {
          _log('No notify characteristic found');
        }
      }

      _log('=== End Debug Info ===');
    } catch (e) {
      _log('Debug error: $e');
    }
  }

  void _log(String message) {
    print('[LuckyPrinter] $message');
    _logController.add(message);
  }

  // Getters
  List<BluetoothDevice> get discoveredDevices => List.from(_discoveredDevices);
  bool get isConnected =>
      _connectedDevice != null &&
      (_classicSocket != null || _writeCharacteristic != null);
  bool get isScanning => _isScanning;
  BluetoothDevice? get connectedDevice => _connectedDevice;
  ConnectionType get currentConnectionType => _currentConnectionType;

  void dispose() {
    _logController.close();
    _devicesController.close();
    _statusController.close();
  }
}
