// Cat Printer Flutter - Enhanced UI with Modern Design
import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:file_picker/file_picker.dart';
import 'package:image/image.dart' as img;
import 'dart:io';
import 'dart:typed_data';
import 'dart:ui' as ui;
import 'package:flutter/rendering.dart';
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

void main() {
  runApp(const CatPrinterApp());
}

class CatPrinterApp extends StatelessWidget {
  const CatPrinterApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cat Printer Pro',
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.deepPurple,
          brightness: Brightness.light,
        ),
        cardTheme: CardTheme(
          elevation: 4,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16),
          ),
        ),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(12),
            ),
          ),
        ),
      ),
      home: const CatPrinterHomePage(),
    );
  }
}

class CatPrinterHomePage extends StatefulWidget {
  const CatPrinterHomePage({super.key});

  @override
  State<CatPrinterHomePage> createState() => _CatPrinterHomePageState();
}

class _CatPrinterHomePageState extends State<CatPrinterHomePage>
    with SingleTickerProviderStateMixin {
  final CatPrinterService _printer = CatPrinterService();
  final TextEditingController _textController =
      TextEditingController(text: 'Hello Cat Printer! 🐱');

  List<BluetoothDevice> _devices = [];
  BluetoothDevice? _connectedDevice;
  String _status = 'Ready to connect';
  bool _isScanning = false;
  bool _showAllDevices = false;

  // Settings
  double _threshold = 150.0;
  int _energy = 6000;

  // Image handling
  img.Image? _selectedImage;
  img.Image? _previewImage;
  bool _isProcessingImage = false;

  // Screenshot functionality
  final GlobalKey _screenshotKey = GlobalKey();
  Uint8List? _screenshotBytes;
  bool _isTakingScreenshot = false;

  // Animation
  late AnimationController _animationController;
  late Animation<double> _fadeAnimation;

  // Tab controller
  int _currentIndex = 0;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      duration: const Duration(milliseconds: 500),
      vsync: this,
    );
    _fadeAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(
      CurvedAnimation(parent: _animationController, curve: Curves.easeInOut),
    );
    _animationController.forward();
    _requestPermissions();
  }

  @override
  void dispose() {
    _printer.dispose();
    _textController.dispose();
    _animationController.dispose();
    super.dispose();
  }

  Future<void> _requestPermissions() async {
    if (Platform.isAndroid) {
      await [
        Permission.bluetooth,
        Permission.bluetoothScan,
        Permission.bluetoothConnect,
        Permission.location
      ].request();
    }
  }

  Future<void> _scanDevices() async {
    setState(() {
      _isScanning = true;
      _status = 'Scanning for devices...';
    });

    try {
      final devices = await _printer.scanForDevices(
        timeout: Duration(seconds: 10),
        showAllDevices: _showAllDevices,
      );
      setState(() {
        _devices = devices;
        _status = _showAllDevices
            ? 'Found ${devices.length} device(s)'
            : 'Found ${devices.length} Cat Printer(s)';
      });
    } catch (e) {
      setState(() {
        _status = 'Scan error: $e';
      });
      _showSnackBar('Scan failed: $e', isError: true);
    } finally {
      setState(() {
        _isScanning = false;
      });
    }
  }

  Future<void> _connect(BluetoothDevice device) async {
    setState(() {
      _status = 'Connecting to ${device.platformName}...';
    });

    try {
      await _printer.connect(device);
      setState(() {
        _connectedDevice = device;
        _status = 'Connected to ${device.platformName}';
      });
      _showSnackBar('Successfully connected!', isError: false);
    } catch (e) {
      setState(() {
        _status = 'Connection failed';
      });
      _showSnackBar('Connection failed: $e', isError: true);
    }
  }

  Future<void> _disconnect() async {
    await _printer.disconnect();
    setState(() {
      _connectedDevice = null;
      _status = 'Disconnected';
    });
    _showSnackBar('Disconnected', isError: false);
  }

  Future<void> _printText() async {
    if (!_printer.isConnected) {
      _showSnackBar('Please connect to a printer first', isError: true);
      return;
    }

    setState(() {
      _status = 'Printing text...';
    });

    try {
      await _printer.printText(
        _textController.text,
        fontSize: 24,
        threshold: _threshold,
        energy: _energy,
      );
      setState(() {
        _status = 'Text printed successfully';
      });
      _showSnackBar('Text printed successfully!', isError: false);
    } catch (e) {
      setState(() {
        _status = 'Print failed';
      });
      _showSnackBar('Print failed: $e', isError: true);
    }
  }

  Future<void> _pickImage() async {
    try {
      FilePickerResult? result = await FilePicker.platform.pickFiles(
        type: FileType.image,
      );

      if (result != null && result.files.single.path != null) {
        setState(() {
          _status = 'Processing image...';
          _isProcessingImage = true;
        });

        File imageFile = File(result.files.single.path!);
        Uint8List imageBytes = await imageFile.readAsBytes();
        img.Image? image = img.decodeImage(imageBytes);

        if (image != null) {
          setState(() {
            _selectedImage = image;
            _screenshotBytes = null; // Clear screenshot when image is selected
            _status = 'Image loaded successfully';
          });
          _updatePreview();
          _showSnackBar('Image loaded successfully!', isError: false);
        } else {
          setState(() {
            _status = 'Failed to decode image';
          });
          _showSnackBar('Failed to decode image', isError: true);
        }
      }
    } catch (e) {
      setState(() {
        _status = 'Error picking image';
      });
      _showSnackBar('Error picking image: $e', isError: true);
    } finally {
      setState(() {
        _isProcessingImage = false;
      });
    }
  }

  void _updatePreview() async {
    if (_selectedImage == null && _screenshotBytes == null) return;

    setState(() {
      _isProcessingImage = true;
    });

    try {
      img.Image? imageToProcess;
      
      if (_screenshotBytes != null) {
        imageToProcess = img.decodeImage(_screenshotBytes!);
      } else {
        imageToProcess = _selectedImage;
      }

      if (imageToProcess == null) return;

      img.Image preview = img.copyResize(imageToProcess, width: 384);

      for (int y = 0; y < preview.height; y++) {
        for (int x = 0; x < preview.width; x++) {
          img.Pixel pixel = preview.getPixel(x, y);
          int gray = ((pixel.r + pixel.g + pixel.b) / 3).round();
          int newValue = gray > _threshold ? 255 : 0;
          preview.setPixel(x, y, img.ColorRgb8(newValue, newValue, newValue));
        }
      }

      setState(() {
        _previewImage = preview;
        _isProcessingImage = false;
      });
    } catch (e) {
      setState(() {
        _status = 'Error processing preview';
        _isProcessingImage = false;
      });
      _showSnackBar('Error processing preview: $e', isError: true);
    }
  }

  Future<void> _takeScreenshot() async {
    setState(() {
      _isTakingScreenshot = true;
      _status = 'Taking screenshot...';
    });

    try {
      RenderRepaintBoundary boundary = _screenshotKey.currentContext
          ?.findRenderObject() as RenderRepaintBoundary;

      ui.Image image = await boundary.toImage(pixelRatio: 2.0);
      ByteData? byteData = await image.toByteData(format: ui.ImageByteFormat.png);
      
      if (byteData != null) {
        setState(() {
          _screenshotBytes = byteData.buffer.asUint8List();
          _selectedImage = null; // Clear selected image
          _status = 'Screenshot captured successfully';
        });
        _updatePreview();
        _showSnackBar('Screenshot captured!', isError: false);
      } else {
        setState(() {
          _status = 'Failed to capture screenshot';
        });
        _showSnackBar('Failed to capture screenshot', isError: true);
      }
    } catch (e) {
      setState(() {
        _status = 'Screenshot error';
      });
      _showSnackBar('Screenshot error: $e', isError: true);
    } finally {
      setState(() {
        _isTakingScreenshot = false;
      });
    }
  }

  Future<void> _printImage() async {
    if (!_printer.isConnected) {
      _showSnackBar('Please connect to a printer first', isError: true);
      return;
    }

    img.Image? imageToPrint;
    
    if (_screenshotBytes != null) {
      imageToPrint = img.decodeImage(_screenshotBytes!);
    } else if (_selectedImage != null) {
      imageToPrint = _selectedImage;
    } else {
      _showSnackBar('Please select an image or take a screenshot first', isError: true);
      return;
    }

    if (imageToPrint == null) {
      _showSnackBar('Failed to process image', isError: true);
      return;
    }

    setState(() {
      _status = 'Printing image...';
    });

    try {
      await _printer.printImage(
        imageToPrint,
        threshold: _threshold,
        energy: _energy,
        widthScale: 1.0,
        heightScale: 1.0,
      );

      setState(() {
        _status = 'Image printed successfully';
      });
      _showSnackBar('Image printed successfully!', isError: false);
    } catch (e) {
      setState(() {
        _status = 'Image print failed';
      });
      _showSnackBar('Image print failed: $e', isError: true);
    }
  }

  Future<void> _printWidget() async {
    if (!_printer.isConnected) {
      _showSnackBar('Please connect to a printer first', isError: true);
      return;
    }

    setState(() {
      _status = 'Printing receipt...';
    });

    try {
      Widget receipt = Container(
        padding: EdgeInsets.all(20),
        color: Colors.white,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('🐱 Cat Printer Receipt',
                style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                    color: Colors.black)),
            SizedBox(height: 16),
            Divider(color: Colors.black),
            SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Coffee', style: TextStyle(fontSize: 16, color: Colors.black)),
                Text('\$5.00', style: TextStyle(fontSize: 16, color: Colors.black)),
              ],
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Pastry', style: TextStyle(fontSize: 16, color: Colors.black)),
                Text('\$3.50', style: TextStyle(fontSize: 16, color: Colors.black)),
              ],
            ),
            SizedBox(height: 8),
            Divider(color: Colors.black),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Total', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.black)),
                Text('\$8.50', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.black)),
              ],
            ),
            SizedBox(height: 16),
            Text('Thank you for your purchase!',
                style: TextStyle(fontSize: 14, color: Colors.black)),
            Text('${DateTime.now().toString().substring(0, 19)}',
                style: TextStyle(fontSize: 12, color: Colors.grey)),
          ],
        ),
      );

      await _printer.printWidget(
        receipt,
        threshold: _threshold,
        energy: _energy,
        widthScale: 1.0,
        heightScale: 1.0,
      );

      setState(() {
        _status = 'Receipt printed successfully';
      });
      _showSnackBar('Receipt printed successfully!', isError: false);
    } catch (e) {
      setState(() {
        _status = 'Receipt print failed';
      });
      _showSnackBar('Receipt print failed: $e', isError: true);
    }
  }

  void _clearImage() {
    setState(() {
      _selectedImage = null;
      _screenshotBytes = null;
      _previewImage = null;
      _status = 'Image cleared';
    });
    _showSnackBar('Image cleared', isError: false);
  }

  void _showSnackBar(String message, {required bool isError}) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(message),
        backgroundColor: isError ? Colors.red : Colors.green,
        behavior: SnackBarBehavior.floating,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        duration: Duration(seconds: 3),
      ),
    );
  }

  Widget _buildConnectionTab() {
    return SingleChildScrollView(
      padding: EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          // Status Card
          Card(
            color: _printer.isConnected ? Colors.green.shade50 : Colors.orange.shade50,
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                children: [
                  Icon(
                    _printer.isConnected ? Icons.bluetooth_connected : Icons.bluetooth_disabled,
                    size: 48,
                    color: _printer.isConnected ? Colors.green : Colors.orange,
                  ),
                  SizedBox(height: 12),
                  Text(
                    _printer.isConnected ? 'Connected' : 'Not Connected',
                    style: TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: _printer.isConnected ? Colors.green : Colors.orange,
                    ),
                  ),
                  SizedBox(height: 8),
                  Text(
                    _status,
                    style: TextStyle(fontSize: 14, color: Colors.grey[600]),
                    textAlign: TextAlign.center,
                  ),
                  if (_connectedDevice != null) ...[
                    SizedBox(height: 16),
                    Container(
                      padding: EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                      decoration: BoxDecoration(
                        color: Colors.green.shade100,
                        borderRadius: BorderRadius.circular(20),
                      ),
                      child: Text(
                        _connectedDevice!.platformName,
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.green.shade800,
                        ),
                      ),
                    ),
                  ],
                ],
              ),
            ),
          ),
          SizedBox(height: 20),

          // Settings Card
          Card(
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Device Settings',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 16),
                  SwitchListTile(
                    title: Text('Show All Devices'),
                    subtitle: Text(_showAllDevices
                        ? 'All Bluetooth devices'
                        : 'Cat Printers only'),
                    value: _showAllDevices,
                    onChanged: (bool value) {
                      setState(() {
                        _showAllDevices = value;
                      });
                    },
                  ),
                ],
              ),
            ),
          ),
          SizedBox(height: 20),

          // Scan Button
          SizedBox(
            height: 56,
            child: ElevatedButton.icon(
              onPressed: _isScanning ? null : _scanDevices,
              icon: _isScanning
                  ? SizedBox(
                      width: 20,
                      height: 20,
                      child: CircularProgressIndicator(strokeWidth: 2))
                  : Icon(Icons.search, size: 24),
              label: Text(
                _isScanning ? 'Scanning...' : 'Scan for Devices',
                style: TextStyle(fontSize: 16),
              ),
              style: ElevatedButton.styleFrom(
                backgroundColor: Theme.of(context).colorScheme.primary,
                foregroundColor: Theme.of(context).colorScheme.onPrimary,
              ),
            ),
          ),
          SizedBox(height: 20),

          // Device List
          if (_devices.isNotEmpty) ...[
            Text(
              'Available Devices',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 12),
            ...(_devices.map((device) => Card(
              child: ListTile(
                leading: CircleAvatar(
                  backgroundColor: Theme.of(context).colorScheme.primary,
                  child: Icon(
                    Icons.print,
                    color: Theme.of(context).colorScheme.onPrimary,
                  ),
                ),
                title: Text(
                  device.platformName.isNotEmpty
                      ? device.platformName
                      : 'Unknown Device',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
                subtitle: _showAllDevices
                    ? Text('ID: ${device.remoteId.str}')
                    : Text('Tap to connect'),
                trailing: ElevatedButton(
                  onPressed: () => _connect(device),
                  child: Text('Connect'),
                  style: ElevatedButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20),
                    ),
                  ),
                ),
              ),
            ))).toList(),
          ],

          // Disconnect Button
          if (_printer.isConnected) ...[
            SizedBox(height: 20),
            OutlinedButton.icon(
              onPressed: _disconnect,
              icon: Icon(Icons.bluetooth_disabled),
              label: Text('Disconnect'),
              style: OutlinedButton.styleFrom(
                foregroundColor: Colors.red,
                side: BorderSide(color: Colors.red),
                padding: EdgeInsets.symmetric(vertical: 12),
              ),
            ),
          ],
        ],
      ),
    );
  }

  Widget _buildPrintTab() {
    return SingleChildScrollView(
      padding: EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          // Print Settings Card
          Card(
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Print Settings',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 16),
                  Text('Threshold: ${_threshold.round()}'),
                  Slider(
                    value: _threshold,
                    min: 50,
                    max: 255,
                    divisions: 20,
                    onChanged: (value) {
                      setState(() => _threshold = value);
                      _updatePreview();
                    },
                  ),
                  SizedBox(height: 8),
                  Text('Energy: $_energy'),
                  Slider(
                    value: _energy.toDouble(),
                    min: 1000,
                    max: 10000,
                    divisions: 18,
                    onChanged: (value) =>
                        setState(() => _energy = value.round()),
                  ),
                ],
              ),
            ),
          ),
          SizedBox(height: 20),

          // Text Print Card
          Card(
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Text Printing',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 16),
                  TextField(
                    controller: _textController,
                    decoration: InputDecoration(
                      labelText: 'Enter text to print',
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(12),
                      ),
                      prefixIcon: Icon(Icons.text_fields),
                    ),
                    maxLines: 3,
                  ),
                  SizedBox(height: 16),
                  SizedBox(
                    width: double.infinity,
                    height: 48,
                    child: ElevatedButton.icon(
                      onPressed: _printer.isConnected ? _printText : null,
                      icon: Icon(Icons.print),
                      label: Text('Print Text'),
                    ),
                  ),
                ],
              ),
            ),
          ),
          SizedBox(height: 20),

          // Quick Print Card
          Card(
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Quick Print',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 16),
                  SizedBox(
                    width: double.infinity,
                    height: 48,
                    child: ElevatedButton.icon(
                      onPressed: _printer.isConnected ? _printWidget : null,
                      icon: Icon(Icons.receipt),
                      label: Text('Print Sample Receipt'),
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.orange,
                        foregroundColor: Colors.white,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildImageTab() {
    return SingleChildScrollView(
      padding: EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          // Image Source Card
          Card(
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Image Source',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 16),
                  Row(
                    children: [
                      Expanded(
                        child: ElevatedButton.icon(
                          onPressed: _pickImage,
                          icon: Icon(Icons.image),
                          label: Text('Pick Image'),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.blue,
                            foregroundColor: Colors.white,
                          ),
                        ),
                      ),
                      SizedBox(width: 12),
                      Expanded(
                        child: ElevatedButton.icon(
                          onPressed: _isTakingScreenshot ? null : _takeScreenshot,
                          icon: _isTakingScreenshot
                              ? SizedBox(
                                  width: 16,
                                  height: 16,
                                  child: CircularProgressIndicator(
                                      strokeWidth: 2, color: Colors.white))
                              : Icon(Icons.camera_alt),
                          label: Text('Screenshot'),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.green,
                            foregroundColor: Colors.white,
                          ),
                        ),
                      ),
                    ],
                  ),
                  if (_selectedImage != null || _screenshotBytes != null) ...[
                    SizedBox(height: 12),
                    ElevatedButton.icon(
                      onPressed: _clearImage,
                      icon: Icon(Icons.clear),
                      label: Text('Clear Image'),
                      style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.red,
                        foregroundColor: Colors.white,
                      ),
                    ),
                  ],
                ],
              ),
            ),
          ),
          SizedBox(height: 20),

          // Screenshot Widget
          Card(
            child: Padding(
              padding: EdgeInsets.all(20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Sample Widget',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  SizedBox(height: 16),
                  RepaintBoundary(
                    key: _screenshotKey,
                    child: Container(
                      width: double.infinity,
                      padding: EdgeInsets.all(20),
                      decoration: BoxDecoration(
                        color: Colors.white,
                        border: Border.all(color: Colors.grey.shade300),
                        borderRadius: BorderRadius.circular(12),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.grey.shade200,
                            blurRadius: 4,
                            offset: Offset(0, 2),
                          ),
                        ],
                      ),
                      child: Column(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          Icon(Icons.pets, size: 50, color: Colors.purple),
                          SizedBox(height: 12),
                          Text(
                            'Cat Printer Pro',
                            style: TextStyle(
                              fontSize: 24,
                              fontWeight: FontWeight.bold,
                              color: Colors.black,
                            ),
                          ),
                          SizedBox(height: 8),
                          Text(
                            'This widget can be captured and printed!',
                            style: TextStyle(fontSize: 16, color: Colors.black),
                            textAlign: TextAlign.center,
                          ),
                          SizedBox(height: 12),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              Icon(Icons.star, color: Colors.orange, size: 30),
                              Icon(Icons.favorite, color: Colors.red, size: 30),
                              Icon(Icons.thumb_up, color: Colors.green, size: 30),
                            ],
                          ),
                          SizedBox(height: 12),
                          Container(
                            padding: EdgeInsets.symmetric(horizontal: 12, vertical: 6),
                            decoration: BoxDecoration(
                              color: Colors.grey.shade100,
                              borderRadius: BorderRadius.circular(16),
                            ),
                            child: Text(
                              DateTime.now().toString().substring(0, 19),
                              style: TextStyle(fontSize: 12, color: Colors.grey[600]),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
          SizedBox(height: 20),

          // Preview Card
          if (_selectedImage != null || _screenshotBytes != null) ...[
            Card(
              child: Padding(
                padding: EdgeInsets.all(20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Preview',
                      style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                    ),
                    SizedBox(height: 16),
                    Container(
                      width: double.infinity,
                      height: 250,
                      decoration: BoxDecoration(
                        border: Border.all(color: Colors.grey.shade300),
                        borderRadius: BorderRadius.circular(12),
                        color: Colors.grey.shade50,
                      ),
                      child: _isProcessingImage
                          ? Center(
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  CircularProgressIndicator(),
                                  SizedBox(height: 16),
                                  Text('Processing image...'),
                                ],
                              ),
                            )
                          : _previewImage != null
                              ? ClipRRect(
                                  borderRadius: BorderRadius.circular(12),
                                  child: Image.memory(
                                    Uint8List.fromList(
                                        img.encodePng(_previewImage!)),
                                    fit: BoxFit.contain,
                                  ),
                                )
                              : Center(
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      Icon(Icons.image, size: 48, color: Colors.grey),
                                      SizedBox(height: 8),
                                      Text(
                                        'Adjust threshold to see preview',
                                        style: TextStyle(color: Colors.grey),
                                      ),
                                    ],
                                  ),
                                ),
                    ),
                    SizedBox(height: 16),
                    SizedBox(
                      width: double.infinity,
                      height: 48,
                      child: ElevatedButton.icon(
                        onPressed: (_selectedImage != null || _screenshotBytes != null) && _printer.isConnected
                            ? _printImage
                            : null,
                        icon: Icon(Icons.print),
                        label: Text(_screenshotBytes != null
                            ? 'Print Screenshot'
                            : 'Print Image'),
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Theme.of(context).colorScheme.primary,
                          foregroundColor: Theme.of(context).colorScheme.onPrimary,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Cat Printer Pro',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        backgroundColor: Theme.of(context).colorScheme.surface,
        elevation: 0,
        centerTitle: true,
        actions: [
          if (_printer.isConnected)
            Container(
              margin: EdgeInsets.only(right: 16),
              child: Center(
                child: Container(
                  padding: EdgeInsets.symmetric(horizontal: 12, vertical: 6),
                  decoration: BoxDecoration(
                    color: Colors.green,
                    borderRadius: BorderRadius.circular(16),
                  ),
                  child: Text(
                    'Connected',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 12,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ),
        ],
      ),
      body: FadeTransition(
        opacity: _fadeAnimation,
        child: IndexedStack(
          index: _currentIndex,
          children: [
            _buildConnectionTab(),
            _buildPrintTab(),
            _buildImageTab(),
          ],
        ),
      ),
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
        destinations: [
          NavigationDestination(
            icon: Icon(Icons.bluetooth),
            selectedIcon: Icon(Icons.bluetooth_connected),
            label: 'Connection',
          ),
          NavigationDestination(
            icon: Icon(Icons.print),
            selectedIcon: Icon(Icons.print),
            label: 'Print',
          ),
          NavigationDestination(
            icon: Icon(Icons.image),
            selectedIcon: Icon(Icons.image),
            label: 'Image',
          ),
        ],
      ),
    );
  }
}