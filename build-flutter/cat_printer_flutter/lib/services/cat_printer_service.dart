import 'dart:async';
import 'dart:typed_data';
import 'package:flutter/services.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:image/image.dart' as img;
import 'package:flutter/widgets.dart';
import 'package:flutter/rendering.dart';
import 'dart:ui' as ui;

// Import extensible architecture
import '../cat_printer_flutter_extensible.dart';
import '../core/interfaces.dart';

/// Legacy Cat Printer Service for Backward Compatibility
///
/// This class maintains the old API while using the new extensible
/// architecture behind the scenes.
class CatPrinterService {
  EasyPrinter? _extensiblePrinter;
  BluetoothDevice? _device;
  bool _isConnected = false;

  /// Configuration for printer settings
  PrinterConfig _config = PrinterConfig();

  /// Check if printer is connected
  bool get isConnected => _isConnected;

  /// Get current device
  BluetoothDevice? get device => _device;

  /// Initialize the service (auto-initializes extensible architecture)
  CatPrinterService() {
    // Auto-initialize extensible architecture
    CatPrinterExtensible.initialize();
  }

  /// Connect to a Bluetooth device
  Future<bool> connect(BluetoothDevice device) async {
    try {
      // Disconnect if already connected
      if (_isConnected) {
        await disconnect();
      }

      _device = device;
      String deviceName = device.platformName.isNotEmpty
          ? device.platformName
          : (device.name ?? 'Unknown');

      print('Attempting to connect to device: $deviceName');

      // Try to detect model and create appropriate printer
      final modelName = _detectModelFromDevice(device);
      print('Detected printer model: $modelName');

      // For now, use a simplified connection approach
      // In a real implementation, this would connect to the actual device
      try {
        // Connect to device using FlutterBluePlus
        print('Connecting to device...');
        await device.connect(
          timeout: Duration(seconds: _config.connectionTimeout.toInt()),
        );
        print('Device connected successfully');

        // Create extensible printer instance
        _extensiblePrinter = await EasyPrinter.create(modelName);

        if (_extensiblePrinter != null) {
          // For MXW01, we might need special initialization
          if (modelName == 'MXW01') {
            print('Initializing MXW01 printer...');
            // Add any MXW01-specific initialization here
          }

          _isConnected = true;
          print('Printer service connected successfully');
          return true;
        }

        print('Failed to create extensible printer instance');
        await device.disconnect();
        return false;
      } catch (e) {
        print('Connection error: $e');
        await device.disconnect();
        throw e;
      }
    } catch (e) {
      print('Connect method error: $e');
      _isConnected = false;
      _device = null;
      _extensiblePrinter = null;
      return false;
    }
  }

  /// Disconnect from device
  Future<void> disconnect() async {
    try {
      print('Disconnecting from printer...');

      if (_device != null && _device!.isConnected) {
        await _device!.disconnect();
        print('Device disconnected successfully');
      }

      if (_extensiblePrinter != null) {
        // Any extensible printer cleanup if needed
        print('Extensible printer cleaned up');
      }
    } catch (e) {
      print('Disconnect error (ignored): $e');
      // Ignore disconnect errors
    } finally {
      _extensiblePrinter = null;
      _device = null;
      _isConnected = false;
      print('Printer service disconnected');
    }
  }

  /// Print text (legacy API)
  Future<void> printText(
    String text, {
    int fontSize = 24,
    bool bold = false,
    bool center = false,
    double? threshold,
    int? energy,
    String ditherType = 'threshold',
  }) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    // Convert text to image and print using extensible API
    final textImage = await _textToImage(text, fontSize, bold, center);
    await printImage(textImage);
  }

  /// Print image (legacy API)
  Future<void> printImage(
    img.Image image, {
    double? threshold,
    int? energy,
    String ditherType = 'threshold',
    double widthScale = 1.0,
    double heightScale = 1.0,
  }) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      // Use appropriate command based on printer model
      final model = _extensiblePrinter!.model;
      if (model?.modelName == 'MXW01') {
        await _extensiblePrinter!.execute('print', {
          'image_data': _imageToBytes(image),
          'line_count': image.height,
          'print_mode': 0, // Monochrome
        });
      } else {
        // Classic models
        await _extensiblePrinter!.execute('start_print');
        await _extensiblePrinter!.execute('start_lattice');
        await _extensiblePrinter!.execute('draw_bitmap', {
          'bitmap_data': _imageToBytes(image),
        });
        await _extensiblePrinter!.execute('end_lattice');
      }
    } catch (e) {
      throw Exception('Failed to print image: $e');
    }
  }

  /// Print Flutter widget (legacy API)
  Future<void> printWidget(
    Widget widget, {
    double? threshold,
    int? energy,
    String ditherType = 'threshold',
    double widthScale = 1.0,
    double heightScale = 1.0,
    double pixelRatio = 1.0,
    Size? customSize,
  }) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      final image =
          await _widgetToImage(widget, customSize?.width, customSize?.height);
      await printImage(image);
    } catch (e) {
      throw Exception('Failed to print widget: $e');
    }
  }

  /// Set printer energy (legacy API)
  Future<void> setEnergy(int energy) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      final model = _extensiblePrinter!.model;
      if (model?.modelName == 'MXW01') {
        // MXW01 uses intensity instead of energy
        final intensity = (energy / 655.35).round().clamp(1, 100);
        await _extensiblePrinter!
            .execute('print_intensity', {'intensity': intensity});
      } else {
        // Classic models
        await _extensiblePrinter!.execute('set_energy', {'energy': energy});
        await _extensiblePrinter!.execute('apply_energy');
      }
    } catch (e) {
      throw Exception('Failed to set energy: $e');
    }
  }

  /// Set printer speed (legacy API)
  Future<void> setSpeed(int speed) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      final model = _extensiblePrinter!.model;
      if (model?.modelName != 'MXW01') {
        // Only classic models support speed setting
        await _extensiblePrinter!.execute('set_speed', {'speed': speed});
      }
    } catch (e) {
      throw Exception('Failed to set speed: $e');
    }
  }

  /// Feed paper (legacy API)
  Future<void> feedPaper(int pixels) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      final model = _extensiblePrinter!.model;
      if (model?.modelName == 'MXW01') {
        await _extensiblePrinter!
            .execute('eject_paper', {'line_count': pixels});
      } else {
        await _extensiblePrinter!.execute('feed_paper', {'pixels': pixels});
      }
    } catch (e) {
      throw Exception('Failed to feed paper: $e');
    }
  }

  /// Retract paper (legacy API)
  Future<void> retractPaper(int pixels) async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      await _extensiblePrinter!.execute('retract_paper', {'pixels': pixels});
    } catch (e) {
      throw Exception('Failed to retract paper: $e');
    }
  }

  /// Get printer status (legacy API)
  Future<void> getPrinterStatus() async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      final model = _extensiblePrinter!.model;
      if (model?.modelName == 'MXW01') {
        await _extensiblePrinter!.execute('get_status');
      } else {
        await _extensiblePrinter!.execute('get_device_state');
      }
    } catch (e) {
      throw Exception('Failed to get printer status: $e');
    }
  }

  /// Get battery level (MXW01 only, legacy API)
  Future<void> getBatteryLevel() async {
    if (!_isConnected || _extensiblePrinter == null) {
      throw Exception('Printer not connected');
    }

    try {
      final model = _extensiblePrinter!.model;
      if (model?.modelName == 'MXW01') {
        await _extensiblePrinter!.execute('battery_level');
      }
    } catch (e) {
      throw Exception('Failed to get battery level: $e');
    }
  }

  /// Update printer configuration
  void updateConfig(PrinterConfig newConfig) {
    _config = newConfig;
  }

  /// Dispose resources
  void dispose() {
    disconnect();
  }

  /// Scan for Cat Printer devices (legacy API)
  Future<List<BluetoothDevice>> scanForDevices({
    Duration? timeout,
    bool showAllDevices = false,
  }) async {
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
            bool isSupported = _isSupportedPrinter(platformName) ||
                _isSupportedPrinter(advertisedName);

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

  /// Check if device name indicates a supported printer
  bool _isSupportedPrinter(String name) {
    if (name.isEmpty) return false;

    final upperName = name.toUpperCase();

    // Specific model patterns
    final supportedPatterns = [
      'GB01',
      'GB02',
      'GB03',
      'GT01',
      'MX05',
      'MX06',
      'MX07',
      'MX08',
      'MX09',
      'MX10',
      'MX11',
      'YT01',
      'MXW01',
      'CAT',
      'PRINTER'
    ];

    for (final pattern in supportedPatterns) {
      if (upperName.contains(pattern)) {
        return true;
      }
    }

    // General patterns for cat printers
    return upperName.contains('GB') ||
        upperName.contains('MX') ||
        upperName.contains('GT') ||
        upperName.contains('YT');
  }

  /// Detect printer model from device name (simple heuristic)
  String _detectModelFromDevice(BluetoothDevice device) {
    // Check both platform name and advertised name
    final platformName = device.platformName.toUpperCase();
    final advertisedName = device.name?.toUpperCase() ?? '';

    print(
        'Detecting model for device: platformName="$platformName", advertisedName="$advertisedName"');

    // Check platform name first
    String? detectedModel = _getModelFromName(platformName);
    if (detectedModel != null) {
      print('Detected model from platform name: $detectedModel');
      return detectedModel;
    }

    // Check advertised name
    detectedModel = _getModelFromName(advertisedName);
    if (detectedModel != null) {
      print('Detected model from advertised name: $detectedModel');
      return detectedModel;
    }

    print('Could not detect specific model, defaulting to GB01');
    return 'GB01'; // Default fallback
  }

  /// Extract model name from device name string
  String? _getModelFromName(String name) {
    if (name.isEmpty) return null;

    // MXW01 detection (highest priority)
    if (name.contains('MXW01')) return 'MXW01';

    // Specific model patterns
    final models = ['GB01', 'GB02', 'GB03', 'GT01', 'YT01'];
    for (final model in models) {
      if (name.contains(model)) return model;
    }

    // MX series (MX05-MX11)
    for (int i = 5; i <= 11; i++) {
      final model = 'MX${i.toString().padLeft(2, '0')}';
      if (name.contains(model)) return model;
    }

    return null;
  }

  /// Convert text to image
  Future<img.Image> _textToImage(
      String text, int fontSize, bool bold, bool center) async {
    // This is a simplified implementation
    // In a real implementation, you'd use proper text rendering
    final width = 384; // Standard paper width
    final height = (text.length / 20).ceil() * fontSize + 20;

    final image = img.Image(width: width, height: height);
    img.fill(image, color: img.ColorRgb8(255, 255, 255)); // White background

    // Simple text rendering (this is a placeholder)
    // In real implementation, you'd use proper font rendering

    return image;
  }

  /// Convert Flutter widget to image
  Future<img.Image> _widgetToImage(
      Widget widget, double? width, double? height) async {
    final renderRepaintBoundary = RenderRepaintBoundary();
    final renderView = RenderView(
      view: WidgetsBinding.instance.platformDispatcher.views.first,
      child: RenderPositionedBox(
        alignment: Alignment.center,
        child: renderRepaintBoundary,
      ),
    );

    final pipelineOwner = PipelineOwner();
    final buildOwner = BuildOwner(focusManager: FocusManager());

    pipelineOwner.rootNode = renderView;
    renderView.prepareInitialFrame();

    final rootElement = RenderObjectToWidgetAdapter<RenderBox>(
      container: renderRepaintBoundary,
      child: Directionality(
        textDirection: TextDirection.ltr,
        child: widget,
      ),
    ).attachToRenderTree(buildOwner);

    buildOwner.buildScope(rootElement);
    buildOwner.finalizeTree();

    pipelineOwner.flushLayout();
    pipelineOwner.flushCompositingBits();
    pipelineOwner.flushPaint();

    final image = await renderRepaintBoundary.toImage(pixelRatio: 1.0);
    final byteData = await image.toByteData(format: ui.ImageByteFormat.png);
    final uint8List = byteData!.buffer.asUint8List();

    return img.decodeImage(uint8List)!;
  }

  /// Convert image to bytes for printing
  List<int> _imageToBytes(img.Image image) {
    // Convert image to 1-bit bitmap
    final grayscale = img.grayscale(image);
    final resized = img.copyResize(grayscale, width: 384);

    List<int> bytes = [];
    for (int y = 0; y < resized.height; y++) {
      for (int x = 0; x < resized.width; x += 8) {
        int byte = 0;
        for (int bit = 0; bit < 8; bit++) {
          if (x + bit < resized.width) {
            final pixel = resized.getPixel(x + bit, y);
            final luminance = img.getLuminance(pixel);
            if (luminance < 128) {
              // Threshold for black/white
              byte |= (1 << (7 - bit));
            }
          }
        }
        bytes.add(byte);
      }
    }
    return bytes;
  }
}

/// Printer configuration class (legacy)
class PrinterConfig {
  int energy;
  int speed;
  int quality;
  int mtu;
  double scanTime;
  double connectionTimeout;
  bool flipH;
  bool flipV;
  bool dryRun;
  bool fake;
  bool dump;

  PrinterConfig({
    this.energy = 4096,
    this.speed = 32,
    this.quality = 5,
    this.mtu = 200,
    this.scanTime = 4.0,
    this.connectionTimeout = 5.0,
    this.flipH = false,
    this.flipV = false,
    this.dryRun = false,
    this.fake = false,
    this.dump = false,
  });
}
