// Cat Printer Flutter App - Main entry point
// Simple UI for testing Cat Printer connection and printing

import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:file_picker/file_picker.dart';
import 'package:image/image.dart' as img;
import 'dart:io';
import 'dart:typed_data';

import 'package:cat_printer_flutter/cat_printer_flutter.dart';

void main() {
  runApp(const CatPrinterApp());
}

class CatPrinterApp extends StatelessWidget {
  const CatPrinterApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cat Printer Flutter',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
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

class _CatPrinterHomePageState extends State<CatPrinterHomePage> {
  final CatPrinterService _printerService = CatPrinterService();
  final TextEditingController _textController = TextEditingController();

  List<BluetoothDevice> _availableDevices = [];
  BluetoothDevice? _selectedDevice;
  bool _isScanning = false;
  bool _isConnecting = false;
  bool _showAllDevices = false;
  String _statusMessage = 'Ready';
  img.Image? _selectedImage;

  // Pengaturan optimal berdasarkan implementasi Python
  double _bitmapThreshold =
      150.0; // Threshold optimal untuk hasil lebih jelas (dinaikkan dari 128)
  int _energyLevel =
      6000; // Energy level optimal untuk hasil lebih gelap (dinaikkan dari 4096)
  String _ditherType = 'threshold'; // Algoritma sederhana seperti Python

  // Konfigurasi Image - diperbaiki untuk hasil lebih jelas
  double _imageWidthScale = 0.8; // 80% dari lebar printer (dinaikkan dari 0.6)
  double _imageHeightScale = 0.7; // 70% pengurangan tinggi (dinaikkan dari 0.5)
  bool _useCustomImageSize = false; // Toggle untuk menggunakan ukuran custom

  @override
  void initState() {
    super.initState();
    _textController.text = 'Hello Cat Printer!';
    _requestPermissions();
  }

  @override
  void dispose() {
    _printerService.dispose();
    _textController.dispose();
    super.dispose();
  }

  /// Request necessary permissions
  Future<void> _requestPermissions() async {
    if (Platform.isAndroid) {
      await [
        Permission.bluetooth,
        Permission.bluetoothScan,
        Permission.bluetoothConnect,
        Permission.location,
      ].request();
    }
  }

  /// Scan for Cat Printer devices
  Future<void> _scanForDevices() async {
    setState(() {
      _isScanning = true;
      _statusMessage = _showAllDevices
          ? 'Scanning for all Bluetooth devices...'
          : 'Scanning for Cat Printers...';
    });

    try {
      List<BluetoothDevice> devices = await _printerService.scanForDevices(
        timeout: Duration(seconds: 10),
        showAllDevices: _showAllDevices,
      );

      setState(() {
        _availableDevices = devices;
        _statusMessage = _showAllDevices
            ? 'Found ${devices.length} Bluetooth device(s)'
            : 'Found ${devices.length} Cat Printer(s)';
      });
    } catch (e) {
      setState(() {
        _statusMessage = 'Scan error: $e';
      });
    } finally {
      setState(() {
        _isScanning = false;
      });
    }
  }

  /// Connect to selected device
  Future<void> _connectToDevice(BluetoothDevice device) async {
    setState(() {
      _isConnecting = true;
      _statusMessage = 'Connecting to ${device.platformName}...';
    });

    try {
      await _printerService.connect(device);
      setState(() {
        _selectedDevice = device;
        _statusMessage = 'Connected to ${device.platformName}';
      });
    } catch (e) {
      setState(() {
        _statusMessage = 'Connection error: $e';
      });
    } finally {
      setState(() {
        _isConnecting = false;
      });
    }
  }

  /// Disconnect from printer
  Future<void> _disconnect() async {
    await _printerService.disconnect();
    setState(() {
      _selectedDevice = null;
      _statusMessage = 'Disconnected';
    });
  }

  /// Print text
  Future<void> _printText() async {
    if (!_printerService.isConnected) {
      _showSnackBar('Please connect to a printer first');
      return;
    }

    String text = _textController.text.trim();
    if (text.isEmpty) {
      _showSnackBar('Please enter text to print');
      return;
    }

    setState(() {
      _statusMessage =
          'Printing text with threshold: ${_bitmapThreshold.round()}, energy: 0x${_energyLevel.toRadixString(16).toUpperCase()}...';
    });

    try {
      await _printerService.printText(
        text,
        fontSize: 24,
        threshold: _bitmapThreshold,
        energy: _energyLevel,
        ditherType: _ditherType,
      );
      setState(() {
        _statusMessage = 'Text printed successfully';
      });
    } catch (e) {
      setState(() {
        _statusMessage = 'Print error: $e';
      });
    }
  }

  /// Pick and print image
  Future<void> _pickAndPrintImage() async {
    if (!_printerService.isConnected) {
      _showSnackBar('Please connect to a printer first');
      return;
    }

    try {
      FilePickerResult? result = await FilePicker.platform.pickFiles(
        type: FileType.image,
      );

      if (result != null && result.files.single.path != null) {
        setState(() {
          _statusMessage = 'Loading image...';
        });

        File imageFile = File(result.files.single.path!);
        Uint8List imageBytes = await imageFile.readAsBytes();
        img.Image? image = img.decodeImage(imageBytes);

        if (image != null) {
          setState(() {
            _selectedImage = image;
            _statusMessage = 'Printing image...';
          });

          await _printerService.printImage(
            image,
            threshold: _bitmapThreshold,
            energy: _energyLevel,
            ditherType: _ditherType,
            widthScale: _useCustomImageSize ? _imageWidthScale : 0.6,
            heightScale: _useCustomImageSize ? _imageHeightScale : 0.5,
          );
          setState(() {
            _statusMessage = 'Image printed successfully';
          });
        } else {
          setState(() {
            _statusMessage = 'Failed to decode image';
          });
        }
      }
    } catch (e) {
      setState(() {
        _statusMessage = 'Image print error: $e';
      });
    }
  }

  /// Show snackbar message
  void _showSnackBar(String message) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(message)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('Cat Printer Flutter'),
        actions: [
          if (_printerService.isConnected)
            IconButton(
              icon: const Icon(Icons.bluetooth_disabled),
              onPressed: _disconnect,
              tooltip: 'Disconnect',
            ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              // Status Card
              Card(
                child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Status',
                        style: Theme.of(context).textTheme.titleMedium,
                      ),
                      const SizedBox(height: 8),
                      Text(_statusMessage),
                      if (_printerService.isConnected &&
                          _printerService.model != null) ...[
                        const SizedBox(height: 8),
                        Text(
                          'Connected: ${_selectedDevice?.platformName}',
                          style: const TextStyle(color: Colors.green),
                        ),
                        Text('Model: ${_printerService.model!.name}'),
                        Text(
                            'Paper Width: ${_printerService.model!.paperWidth}px'),
                      ],
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 16),

              // Device Selection
              Card(
                child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Devices',
                        style: Theme.of(context).textTheme.titleMedium,
                      ),
                      const SizedBox(height: 8),

                      // Toggle for showing all devices
                      Row(
                        children: [
                          Checkbox(
                            value: _showAllDevices,
                            onChanged: (value) {
                              setState(() {
                                _showAllDevices = value ?? false;
                              });
                            },
                          ),
                          const Text('Show all Bluetooth devices'),
                        ],
                      ),
                      const SizedBox(height: 8),

                      ElevatedButton.icon(
                        onPressed: _isScanning ? null : _scanForDevices,
                        icon: _isScanning
                            ? const SizedBox(
                                width: 16,
                                height: 16,
                                child:
                                    CircularProgressIndicator(strokeWidth: 2),
                              )
                            : const Icon(Icons.search),
                        label: Text(_isScanning
                            ? 'Scanning...'
                            : (_showAllDevices
                                ? 'Scan All Devices'
                                : 'Scan for Printers')),
                      ),
                      const SizedBox(height: 8),
                      if (_availableDevices.isNotEmpty) ...[
                        const Text('Available Devices:'),
                        const SizedBox(height: 8),
                        ..._availableDevices.map((device) => ListTile(
                              title: Text(device.platformName),
                              subtitle: Text(device.remoteId.toString()),
                              trailing: _isConnecting
                                  ? const CircularProgressIndicator()
                                  : ElevatedButton(
                                      onPressed: () => _connectToDevice(device),
                                      child: const Text('Connect'),
                                    ),
                              leading: const Icon(Icons.print),
                            )),
                      ],
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 16),

              // Print Controls
              if (_printerService.isConnected) ...[
                Card(
                  child: Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Print Controls',
                          style: Theme.of(context).textTheme.titleMedium,
                        ),
                        const SizedBox(height: 16),

                        // Pengaturan Print (Dioptimalkan berdasarkan Python)
                        Text(
                          'Pengaturan Print (Dioptimalkan untuk hasil terbaik)',
                          style: Theme.of(context).textTheme.titleSmall,
                        ),
                        const SizedBox(height: 8),

                        // Informasi pengaturan optimal
                        Container(
                          padding: const EdgeInsets.all(12),
                          decoration: BoxDecoration(
                            color: Colors.green.shade50,
                            border: Border.all(color: Colors.green.shade200),
                            borderRadius: BorderRadius.circular(8),
                          ),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                'Pengaturan Optimal Aktif:',
                                style: TextStyle(
                                  fontWeight: FontWeight.bold,
                                  color: Colors.green.shade700,
                                ),
                              ),
                              const SizedBox(height: 4),
                              Text(
                                  '• Threshold: ${_bitmapThreshold.round()} (optimal untuk hasil jelas)'),
                              Text(
                                  '• Energy: ${_energyLevel} (0x${_energyLevel.toRadixString(16).toUpperCase()}) - lebih gelap'),
                              Text('• Algoritma: Threshold sederhana'),
                              Text(
                                  '• Scale: ${(_imageWidthScale * 100).round()}% x ${(_imageHeightScale * 100).round()}% - lebih besar'),
                              Text(
                                  '• Delay: 40ms antar baris + 100ms antar tugas'),
                              const SizedBox(height: 4),
                              Text(
                                'Pengaturan ini menghasilkan kualitas print terbaik berdasarkan implementasi Python yang terbukti.',
                                style: TextStyle(
                                  fontSize: 12,
                                  color: Colors.green.shade600,
                                ),
                              ),
                            ],
                          ),
                        ),

                        // Optional: Advanced settings toggle
                        const SizedBox(height: 8),
                        ExpansionTile(
                          title: const Text('Pengaturan Lanjutan (Opsional)'),
                          children: [
                            // Konfigurasi Ukuran Image
                            const Padding(
                              padding: EdgeInsets.all(8.0),
                              child: Text(
                                'Konfigurasi Ukuran Gambar',
                                style: TextStyle(fontWeight: FontWeight.bold),
                              ),
                            ),

                            // Toggle untuk custom image size
                            CheckboxListTile(
                              title: const Text('Gunakan Ukuran Custom'),
                              subtitle: const Text(
                                  'Aktifkan untuk mengatur ukuran gambar manual'),
                              value: _useCustomImageSize,
                              onChanged: (value) {
                                setState(() {
                                  _useCustomImageSize = value ?? false;
                                });
                              },
                            ),
                            if (_useCustomImageSize) ...[
                              // Image Width Scale Slider
                              Padding(
                                padding:
                                    const EdgeInsets.symmetric(horizontal: 16),
                                child: Row(
                                  children: [
                                    const Text('Width: '),
                                    Expanded(
                                      child: Slider(
                                        value: _imageWidthScale,
                                        min: 0.3,
                                        max: 1.0,
                                        divisions: 14,
                                        label:
                                            '${(_imageWidthScale * 100).round()}%',
                                        onChanged: (value) {
                                          setState(() {
                                            _imageWidthScale = value;
                                          });
                                        },
                                      ),
                                    ),
                                    SizedBox(
                                      width: 50,
                                      child: Text(
                                        '${(_imageWidthScale * 100).round()}%',
                                        textAlign: TextAlign.right,
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                              // Image Height Scale Slider
                              Padding(
                                padding:
                                    const EdgeInsets.symmetric(horizontal: 16),
                                child: Row(
                                  children: [
                                    const Text('Tinggi: '),
                                    Expanded(
                                      child: Slider(
                                        value: _imageHeightScale,
                                        min: 0.3,
                                        max: 1.0,
                                        divisions: 14,
                                        label:
                                            '${(_imageHeightScale * 100).round()}%',
                                        onChanged: (value) {
                                          setState(() {
                                            _imageHeightScale = value;
                                          });
                                        },
                                      ),
                                    ),
                                    Text(
                                        '${(_imageHeightScale * 100).round()}%'),
                                  ],
                                ),
                              ),

                              const Padding(
                                padding: EdgeInsets.symmetric(horizontal: 16),
                                child: Text(
                                  'Default: Lebar 60%, Tinggi 50% (ukuran kecil dan hemat kertas)',
                                  style: TextStyle(
                                      fontSize: 12, color: Colors.grey),
                                ),
                              ),
                            ],

                            if (!_useCustomImageSize)
                              const Padding(
                                padding: EdgeInsets.symmetric(horizontal: 16),
                                child: Text(
                                  'Menggunakan ukuran default: Lebar 60%, Tinggi 50%',
                                  style: TextStyle(
                                      fontSize: 12, color: Colors.green),
                                ),
                              ),

                            // Divider
                            const Divider(),

                            // Konfigurasi Energy Level
                            const Padding(
                              padding: EdgeInsets.all(8.0),
                              child: Text(
                                'Pengaturan Energy Level',
                                style: TextStyle(fontWeight: FontWeight.bold),
                              ),
                            ),

                            // Energy Level Slider
                            Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 16),
                              child: Row(
                                children: [
                                  const Text('Energy: '),
                                  Expanded(
                                    child: Slider(
                                      value: _energyLevel.toDouble(),
                                      min:
                                          3000.0, // Dinaikkan dari 1000.0 untuk hasil lebih gelap
                                      max:
                                          10000.0, // Dinaikkan dari 8000.0 untuk range lebih luas
                                      divisions:
                                          35, // Disesuaikan untuk step yang tepat
                                      label: '${_energyLevel}',
                                      onChanged: (value) {
                                        setState(() {
                                          _energyLevel = value.round();
                                        });
                                      },
                                    ),
                                  ),
                                  SizedBox(
                                    width: 80,
                                    child: Text(
                                      '${_energyLevel}',
                                      textAlign: TextAlign.right,
                                    ),
                                  ),
                                ],
                              ),
                            ),

                            const Padding(
                              padding: EdgeInsets.symmetric(horizontal: 16),
                              child: Text(
                                'Energy level mengontrol intensitas panas printer. Nilai tinggi = lebih gelap, nilai rendah = lebih terang. Optimal: 6000 (0x1770)',
                                style:
                                    TextStyle(fontSize: 12, color: Colors.grey),
                              ),
                            ),

                            const Divider(),

                            // Bitmap Threshold Slider
                            const Padding(
                              padding: EdgeInsets.all(8.0),
                              child: Text(
                                'Konfigurasi Kualitas Print',
                                style: TextStyle(fontWeight: FontWeight.bold),
                              ),
                            ),

                            Padding(
                              padding:
                                  const EdgeInsets.symmetric(horizontal: 16),
                              child: Row(
                                children: [
                                  const Text('Threshold: '),
                                  Expanded(
                                    child: Slider(
                                      value: _bitmapThreshold,
                                      min:
                                          100.0, // Dinaikkan dari 50.0 untuk hasil lebih baik
                                      max: 200.0,
                                      divisions:
                                          20, // Dikurangi dari 30 untuk step yang lebih besar
                                      label:
                                          _bitmapThreshold.round().toString(),
                                      onChanged: (value) {
                                        setState(() {
                                          _bitmapThreshold = value;
                                        });
                                      },
                                    ),
                                  ),
                                  Text('${_bitmapThreshold.round()}'),
                                ],
                              ),
                            ),
                            const Padding(
                              padding: EdgeInsets.symmetric(horizontal: 16),
                              child: Text(
                                'Catatan: Nilai 128 adalah optimal. Ubah hanya jika diperlukan.',
                                style:
                                    TextStyle(fontSize: 12, color: Colors.grey),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 16),

                        // Text Printing
                        TextField(
                          controller: _textController,
                          decoration: const InputDecoration(
                            labelText: 'Text to Print',
                            border: OutlineInputBorder(),
                          ),
                          maxLines: 3,
                        ),
                        const SizedBox(height: 8),
                        ElevatedButton.icon(
                          onPressed: _printText,
                          icon: const Icon(Icons.text_fields),
                          label: const Text('Print Text'),
                        ),
                        const SizedBox(height: 16),

                        // Image Printing
                        ElevatedButton.icon(
                          onPressed: _pickAndPrintImage,
                          icon: const Icon(Icons.image),
                          label: const Text('Pick & Print Image'),
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ],
          ),
        ),
      ),
    );
  }
}
