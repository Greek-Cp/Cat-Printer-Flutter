// Cat Printer Flutter - Simple Example
import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:file_picker/file_picker.dart';
import 'package:image/image.dart' as img;
import 'dart:io';
import 'dart:typed_data';
import 'package:cat_printer_flutter/cat_printer_flutter.dart';
import 'package:cat_printer_flutter/utils/helper_screenshot.dart';
import 'package:cat_printer_flutter/utils/printer_controller.dart';

void main() {
  runApp(const CatPrinterApp());
}

class CatPrinterApp extends StatelessWidget {
  const CatPrinterApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cat Printer Simple',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const CatPrinterHomePage(),
    );
  }
}

class CatPrinterHomePage extends StatefulWidget {
  const CatPrinterHomePage({super.key});

  @override
  State<CatPrinterHomePage> createState() => _CatPrinterHomePageState();
}

class _CatPrinterHomePageState extends State<CatPrinterHomePage> {
  final CatPrinterService _printer = CatPrinterService();
  final TextEditingController _textController =
      TextEditingController(text: 'Hello Cat Printer!');

  // Add printer controller
  late final PrinterController _printerController;

  List<BluetoothDevice> _devices = [];
  BluetoothDevice? _connectedDevice;
  String _status = 'Ready';
  bool _isScanning = false;
  bool _showAllDevices = false;

  // Simple settings - dapat diubah sesuai kebutuhan
  double _threshold = 150.0; // Threshold untuk kualitas print
  int _energy = 6000; // Energy level untuk ketebalan

  // Image preview
  img.Image? _selectedImage;
  img.Image? _previewImage;
  bool _isProcessingImage = false;

  @override
  void initState() {
    super.initState();
    _requestPermissions();
    _printerController = PrinterController(
      printer: _printer,
      threshold: _threshold,
      energy: _energy,
    );
  }

  @override
  void dispose() {
    _printer.dispose();
    _textController.dispose();
    _printerController.dispose();
    super.dispose();
  }

  // Request permissions
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

  // Scan for devices
  Future<void> _scanDevices() async {
    setState(() {
      _isScanning = true;
      _status = 'Scanning...';
    });

    try {
      final devices = await _printer.scanForDevices(
        timeout: Duration(seconds: 10),
        showAllDevices: _showAllDevices,
      );
      setState(() {
        _devices = devices;
        _status = _showAllDevices
            ? 'Found ${devices.length} device(s) (all devices)'
            : 'Found ${devices.length} Cat Printer(s)';
      });
    } catch (e) {
      setState(() {
        _status = 'Scan error: $e';
      });
    } finally {
      setState(() {
        _isScanning = false;
      });
    }
  }

  // Connect to device
  Future<void> _connect(BluetoothDevice device) async {
    setState(() {
      _status = 'Connecting...';
    });

    try {
      await _printer.connect(device);
      setState(() {
        _connectedDevice = device;
        _status = 'Connected to ${device.platformName}';
      });
    } catch (e) {
      setState(() {
        _status = 'Connection error: $e';
      });
    }
  }

  // Disconnect
  Future<void> _disconnect() async {
    await _printer.disconnect();
    setState(() {
      _connectedDevice = null;
      _status = 'Disconnected';
    });
  }

  // Print text
  Future<void> _printText() async {
    if (!_printer.isConnected) {
      setState(() {
        _status = 'Please connect first';
      });
      return;
    }

    setState(() {
      _status = 'Printing...';
    });

    try {
      await _printer.printText(
        _textController.text,
        fontSize: 24,
        threshold: _threshold,
        energy: _energy,
      );
      setState(() {
        _status = 'Print successful';
      });
    } catch (e) {
      setState(() {
        _status = 'Print error: $e';
      });
    }
  }

  // Pick image file
  Future<void> _pickImage() async {
    try {
      FilePickerResult? result = await FilePicker.platform.pickFiles(
        type: FileType.image,
      );

      if (result != null && result.files.single.path != null) {
        setState(() {
          _status = 'Loading image...';
          _isProcessingImage = true;
        });

        File imageFile = File(result.files.single.path!);
        Uint8List imageBytes = await imageFile.readAsBytes();
        img.Image? image = img.decodeImage(imageBytes);

        if (image != null) {
          setState(() {
            _selectedImage = image;
            _status = 'Image loaded. Adjust threshold to see preview.';
          });
          _updatePreview();
        } else {
          setState(() {
            _status = 'Failed to decode image';
          });
        }
      }
    } catch (e) {
      setState(() {
        _status = 'Error picking image: $e';
      });
    } finally {
      setState(() {
        _isProcessingImage = false;
      });
    }
  }

  // Update preview when threshold changes
  void _updatePreview() async {
    if (_selectedImage == null) return;

    setState(() {
      _isProcessingImage = true;
    });

    try {
      // Apply threshold to create preview
      img.Image preview = img.copyResize(_selectedImage!, width: 384);

      // Convert to grayscale and apply threshold
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
        _status = 'Error processing preview: $e';
        _isProcessingImage = false;
      });
    }
  }

  // Print image
  Future<void> _printImage() async {
    if (!_printer.isConnected) {
      setState(() {
        _status = 'Please connect first';
      });
      return;
    }

    if (_selectedImage == null) {
      setState(() {
        _status = 'Please select an image first';
      });
      return;
    }

    setState(() {
      _status = 'Printing image...';
    });

    try {
      await _printer.printImage(
        _selectedImage!,
        threshold: _threshold,
        energy: _energy,
        widthScale: 1.0, // 100% width
        heightScale: 1.0, // 100% height
      );

      setState(() {
        _status = 'Image printed successfully';
      });
    } catch (e) {
      setState(() {
        _status = 'Image print error: $e';
      });
    }
  }

  // Print simple widget
  Future<void> _printWidget() async {
    if (!_printer.isConnected) {
      setState(() {
        _status = 'Please connect first';
      });
      return;
    }

    setState(() {
      _status = 'Printing widget...';
    });

    try {
      Widget simpleWidget = Container(
        padding: EdgeInsets.all(20),
        color: Colors.white,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('Simple Receipt',
                style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    color: Colors.black)),
            SizedBox(height: 10),
            Text('Item: Coffee',
                style: TextStyle(fontSize: 16, color: Colors.black)),
            Text('Price: \$5.00',
                style: TextStyle(fontSize: 16, color: Colors.black)),
            SizedBox(height: 10),
            Text('Thank you!',
                style: TextStyle(fontSize: 14, color: Colors.black)),
          ],
        ),
      );

      await _printer.printWidget(
        simpleWidget,
        threshold: _threshold,
        energy: _energy,
        widthScale: 1.0, // 100% width
        heightScale: 1.0, // 100% height
      );

      setState(() {
        _status = 'Widget printed successfully';
      });
    } catch (e) {
      setState(() {
        _status = 'Widget print error: $e';
      });
    }
  }

  // Update threshold and energy setters
  void _updateThreshold(double value) {
    setState(() {
      _threshold = value;
      _printerController.threshold = value;
    });
    _updatePreview();
  }

  void _updateEnergy(double value) {
    setState(() {
      _energy = value.round();
      _printerController.energy = value.round();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Cat Printer Simple'),
        actions: [
          if (_printer.isConnected)
            IconButton(
                icon: Icon(Icons.bluetooth_disabled), onPressed: _disconnect),
        ],
      ),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              // Status
              Card(
                child: Padding(
                  padding: EdgeInsets.all(16),
                  child:
                      Text('Status: $_status', style: TextStyle(fontSize: 16)),
                ),
              ),
              SizedBox(height: 16),

              // Show all devices checkbox
              CheckboxListTile(
                title: Text('Show all Bluetooth devices'),
                subtitle: Text(_showAllDevices
                    ? 'Will show all discoverable devices'
                    : 'Will show only Cat Printers'),
                value: _showAllDevices,
                onChanged: (bool? value) {
                  setState(() {
                    _showAllDevices = value ?? false;
                  });
                },
                controlAffinity: ListTileControlAffinity.leading,
              ),
              SizedBox(height: 8),

              // Scan button
              ElevatedButton.icon(
                onPressed: _isScanning ? null : _scanDevices,
                icon: _isScanning
                    ? SizedBox(
                        width: 16,
                        height: 16,
                        child: CircularProgressIndicator(strokeWidth: 2))
                    : Icon(Icons.search),
                label: Text(_isScanning ? 'Scanning...' : 'Scan Devices'),
              ),
              SizedBox(height: 16),

              // Device list
              if (_devices.isNotEmpty) ...[
                Text(
                    _showAllDevices
                        ? 'All Bluetooth Devices:'
                        : 'Cat Printers:',
                    style:
                        TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
                SizedBox(height: 8),
                ..._devices.map((device) => Card(
                      child: ListTile(
                        title: Text(device.platformName.isNotEmpty
                            ? device.platformName
                            : 'Unknown Device'),
                        subtitle: _showAllDevices
                            ? Text('ID: ${device.remoteId.str}')
                            : null,
                        trailing: ElevatedButton(
                          onPressed: () => _connect(device),
                          child: Text('Connect'),
                        ),
                      ),
                    )),
              ],

              if (_printer.isConnected) ...[
                SizedBox(height: 16),

                // Settings
                Text('Settings:',
                    style:
                        TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
                Row(
                  children: [
                    Text('Threshold: '),
                    Expanded(
                      child: Slider(
                        value: _threshold,
                        min: 50,
                        max: 255,
                        onChanged: _updateThreshold,
                      ),
                    ),
                    Text('${_threshold.round()}'),
                  ],
                ),
                Row(
                  children: [
                    Text('Energy: '),
                    Expanded(
                      child: Slider(
                        value: _energy.toDouble(),
                        min: 1000,
                        max: 10000,
                        onChanged: _updateEnergy,
                      ),
                    ),
                    Text('$_energy'),
                  ],
                ),
                SizedBox(height: 16),

                // Text input
                TextField(
                  controller: _textController,
                  decoration: InputDecoration(
                    labelText: 'Text to print',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 16),

                // Image section
                ElevatedButton.icon(
                  onPressed: _pickImage,
                  icon: Icon(Icons.image),
                  label: Text('Pick Image'),
                ),
                SizedBox(height: 16),

                // Image preview
                if (_selectedImage != null) ...[
                  Text('Preview:',
                      style:
                          TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
                  SizedBox(height: 8),
                  Container(
                    width: double.infinity,
                    height: 200,
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.grey),
                      borderRadius: BorderRadius.circular(8),
                    ),
                    child: _isProcessingImage
                        ? Center(child: CircularProgressIndicator())
                        : _previewImage != null
                            ? ClipRRect(
                                borderRadius: BorderRadius.circular(8),
                                child: Image.memory(
                                  Uint8List.fromList(
                                      img.encodePng(_previewImage!)),
                                  fit: BoxFit.contain,
                                ),
                              )
                            : Center(
                                child: Text('Adjust threshold to see preview')),
                  ),
                  SizedBox(height: 16),
                  ElevatedButton.icon(
                    onPressed: _selectedImage != null ? _printImage : null,
                    icon: Icon(Icons.print),
                    label: Text('Print Image'),
                  ),
                  SizedBox(height: 16),
                ],

                // Print buttons
                ElevatedButton(
                  onPressed: _printText,
                  child: Text('Print Text'),
                ),
                SizedBox(height: 8),
                ElevatedButton(
                  onPressed: _printWidget,
                  child: Text('Print Simple Widget'),
                ),
                SizedBox(height: 16),

                // Screenshot Example Section
                Text('Screenshot Example:',
                    style:
                        TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
                SizedBox(height: 8),
                ScreenshotWidget(
                  controller: _printerController,
                  child: Container(
                    padding: EdgeInsets.all(16),
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.black, width: 4),
                      borderRadius: BorderRadius.circular(8),
                    ),
                    child: Text(
                      'Hello, this widget will be screenshot!',
                      style: TextStyle(fontSize: 18),
                    ),
                  ),
                ),
                SizedBox(height: 16),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    ElevatedButton.icon(
                      onPressed: () async {
                        setState(() {
                          _status = 'Capturing screenshot...';
                        });

                        try {
                          final image = await _printerController.capture();
                          if (image != null) {
                            setState(() {
                              _selectedImage = image;
                              _status = 'Screenshot captured successfully';
                            });
                            _updatePreview();
                          } else {
                            setState(() {
                              _status = 'Failed to capture screenshot';
                            });
                          }
                        } catch (e) {
                          setState(() {
                            _status = 'Error: $e';
                          });
                        }
                      },
                      icon: Icon(Icons.screenshot),
                      label: Text('Capture'),
                    ),
                    ElevatedButton.icon(
                      onPressed: () async {
                        setState(() {
                          _status = 'Printing...';
                        });

                        try {
                          await _printerController.printWidget();
                          setState(() {
                            _status = 'Print successful';
                          });
                        } catch (e) {
                          setState(() {
                            _status = 'Print error: $e';
                          });
                        }
                      },
                      icon: Icon(Icons.print),
                      label: Text('Print'),
                    ),
                    ElevatedButton.icon(
                      onPressed: () async {
                        setState(() {
                          _status = 'Saving...';
                        });

                        try {
                          final path =
                              await _printerController.saveScreenshot();
                          if (path != null) {
                            setState(() {
                              _status = 'Saved to: $path';
                            });
                          } else {
                            setState(() {
                              _status = 'Failed to save screenshot';
                            });
                          }
                        } catch (e) {
                          setState(() {
                            _status = 'Save error: $e';
                          });
                        }
                      },
                      icon: Icon(Icons.save),
                      label: Text('Save'),
                    ),
                  ],
                ),
              ],
            ],
          ),
        ),
      ),
    );
  }
}
