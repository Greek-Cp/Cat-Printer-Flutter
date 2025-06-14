import 'dart:typed_data';
import 'package:flutter/material.dart';
import 'package:flutter_blue_plus/flutter_blue_plus.dart';
import 'package:lucky_printer_flutter/lucky_printer_flutter.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lucky Printer Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Lucky Printer Demo'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final LuckyPrinterManager _printerManager = LuckyPrinterManager();
  final TextEditingController _textController = TextEditingController();

  final List<String> _logs = [];
  List<BluetoothDevice> _discoveredDevices = [];
  bool _isInitialized = false;
  bool _isScanning = false;

  @override
  void initState() {
    super.initState();
    _initializePrinter();
    _listenToStreams();
  }

  Future<void> _initializePrinter() async {
    try {
      bool success = await _printerManager.initialize();
      setState(() {
        _isInitialized = success;
      });
      if (success) {
        _addLog(
            'Printer manager initialized successfully with dual connection support');
      } else {
        _addLog('Failed to initialize printer manager');
      }
    } catch (e) {
      _addLog('Failed to initialize printer manager: $e');
    }
  }

  void _listenToStreams() {
    // Listen to logs
    _printerManager.logStream.listen((log) {
      _addLog(log);
    });

    // Listen to discovered devices
    _printerManager.devicesStream.listen((devices) {
      setState(() {
        _discoveredDevices = devices;
      });
    });
  }

  void _addLog(String message) {
    setState(() {
      _logs.insert(
          0, '${DateTime.now().toString().substring(11, 19)} $message');
      if (_logs.length > 100) {
        _logs.removeLast();
      }
    });
  }

  Future<void> _startScan() async {
    if (!_isInitialized) {
      _addLog('Manager not initialized');
      return;
    }

    try {
      setState(() {
        _isScanning = true;
      });
      await _printerManager.startScanning(scanAllDevices: false);
    } catch (e) {
      setState(() {
        _isScanning = false;
      });
      _addLog('Error starting scan: $e');
    }
  }

  Future<void> _startScanAll() async {
    if (!_isInitialized) {
      _addLog('Manager not initialized');
      return;
    }

    try {
      setState(() {
        _isScanning = true;
      });
      await _printerManager.startScanning(scanAllDevices: true);
    } catch (e) {
      setState(() {
        _isScanning = false;
      });
      _addLog('Error starting scan all: $e');
    }
  }

  Future<void> _stopScan() async {
    try {
      await _printerManager.stopScanning();
      setState(() {
        _isScanning = false;
      });
    } catch (e) {
      _addLog('Error stopping scan: $e');
    }
  }

  Future<void> _connectToPrinter(BluetoothDevice device,
      {ConnectionType? forceType}) async {
    try {
      bool success = await _printerManager.connectToDevice(device,
          forceConnectionType: forceType);
      if (success) {
        _addLog(
            'Connected to ${device.platformName} via ${_printerManager.currentConnectionType.name.toUpperCase()}');
      } else {
        _addLog('Failed to connect to ${device.platformName}');
      }
      setState(() {}); // Refresh UI
    } catch (e) {
      _addLog('Error connecting to printer: $e');
    }
  }

  Future<void> _disconnect() async {
    try {
      await _printerManager.disconnect();
      setState(() {}); // Refresh UI
    } catch (e) {
      _addLog('Error disconnecting: $e');
    }
  }

  Future<void> _printText() async {
    final text = _textController.text.trim();
    if (text.isEmpty) {
      _addLog('No text to print');
      return;
    }

    try {
      bool success = await _printerManager.printText(text);
      if (success) {
        _addLog(
            'Text printed successfully via ${_printerManager.currentConnectionType.name.toUpperCase()}');
      } else {
        _addLog('Failed to print text');
      }
    } catch (e) {
      _addLog('Error printing text: $e');
    }
  }

  Future<void> _printTestPage() async {
    try {
      bool success = await _printerManager.printTestPage();
      if (success) {
        _addLog('Test page printed successfully');
      } else {
        _addLog('Failed to print test page');
      }
    } catch (e) {
      _addLog('Error printing test page: $e');
    }
  }

  Future<void> _printSimpleTest() async {
    try {
      bool success = await _printerManager.simpleTestPrint();
      if (success) {
        _addLog('Simple test sent successfully');
      } else {
        _addLog('Simple test failed');
      }
    } catch (e) {
      _addLog('Error in simple test: $e');
    }
  }

  Future<void> _debugConnection() async {
    try {
      await _printerManager.debugCharacteristics();
      _addLog('Debug information logged');
    } catch (e) {
      _addLog('Debug error: $e');
    }
  }

  String _getConnectionTypeDisplay(String deviceName) {
    if (deviceName.toLowerCase().contains('ppd')) {
      return '(Classic BT)';
    } else if (deviceName.toLowerCase().contains('luckp') ||
        deviceName.toLowerCase().contains('lucky')) {
      return '(BLE)';
    }
    return '(Auto)';
  }

  void _showResult(String testName, bool success) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text('$testName: ${success ? 'SUCCESS' : 'FAILED'}'),
        backgroundColor: success ? Colors.green : Colors.red,
        duration: const Duration(seconds: 2),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
        actions: [
          if (_printerManager.isConnected)
            Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                Chip(
                  label: Text(
                      _printerManager.currentConnectionType.name.toUpperCase()),
                  backgroundColor: _printerManager.currentConnectionType ==
                          ConnectionType.classic
                      ? Colors.orange.shade100
                      : Colors.blue.shade100,
                ),
                const SizedBox(width: 8),
                IconButton(
                  icon: const Icon(Icons.bluetooth_connected),
                  onPressed: _disconnect,
                  tooltip: 'Disconnect',
                ),
              ],
            )
          else
            IconButton(
              icon: const Icon(Icons.bluetooth_disabled),
              onPressed: null,
            ),
        ],
      ),
      body: !_isInitialized
          ? const Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  CircularProgressIndicator(),
                  SizedBox(height: 16),
                  Text('Initializing Bluetooth...'),
                ],
              ),
            )
          : SingleChildScrollView(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  // Header
                  Card(
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Lucky Printer Flutter Demo (Dual Connection)',
                            style: Theme.of(context).textTheme.titleLarge,
                          ),
                          const SizedBox(height: 8),
                          const Text(
                            'This demo supports both Classic Bluetooth and BLE connections. '
                            'PPD printers use Classic Bluetooth, modern Lucky Printers use BLE.',
                          ),
                        ],
                      ),
                    ),
                  ),

                  const SizedBox(height: 16),

                  // Scanner Section
                  Card(
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              Text(
                                'Device Scanner',
                                style: Theme.of(context).textTheme.titleMedium,
                              ),
                              Wrap(
                                spacing: 8,
                                children: [
                                  ElevatedButton.icon(
                                    onPressed: _isScanning ? null : _startScan,
                                    icon: _isScanning
                                        ? const SizedBox(
                                            width: 16,
                                            height: 16,
                                            child: CircularProgressIndicator(
                                                strokeWidth: 2),
                                          )
                                        : const Icon(Icons.search),
                                    label: const Text('Lucky Printers'),
                                  ),
                                  ElevatedButton.icon(
                                    onPressed:
                                        _isScanning ? null : _startScanAll,
                                    icon: _isScanning
                                        ? const SizedBox(
                                            width: 16,
                                            height: 16,
                                            child: CircularProgressIndicator(
                                                strokeWidth: 2),
                                          )
                                        : const Icon(Icons.bluetooth_searching),
                                    label: const Text('All Devices'),
                                  ),
                                  if (_isScanning)
                                    ElevatedButton(
                                      onPressed: _stopScan,
                                      child: const Text('Stop'),
                                    ),
                                ],
                              ),
                            ],
                          ),
                          const SizedBox(height: 16),
                          if (_discoveredDevices.isEmpty && !_isScanning)
                            const Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text('No devices found. Try scanning:'),
                                SizedBox(height: 4),
                                Text(
                                    '• "Lucky Printers" - Scan for known printer patterns'),
                                Text(
                                    '• "All Devices" - Scan all Bluetooth devices (debug mode)'),
                              ],
                            )
                          else if (_discoveredDevices.isEmpty && _isScanning)
                            const Text('Searching for devices...')
                          else
                            Column(
                              children: _discoveredDevices.map((device) {
                                bool isConnected =
                                    _printerManager.connectedDevice?.remoteId ==
                                        device.remoteId;
                                String connectionType =
                                    _getConnectionTypeDisplay(
                                        device.platformName);

                                return Card(
                                  color:
                                      isConnected ? Colors.green.shade50 : null,
                                  child: ListTile(
                                    leading: Icon(
                                      isConnected
                                          ? Icons.bluetooth_connected
                                          : Icons.bluetooth,
                                      color: isConnected ? Colors.green : null,
                                    ),
                                    title: Row(
                                      children: [
                                        Expanded(
                                          child: Text(
                                            device.platformName.isNotEmpty
                                                ? device.platformName
                                                : 'Unknown Device',
                                          ),
                                        ),
                                        Text(
                                          connectionType,
                                          style: TextStyle(
                                            fontSize: 12,
                                            color: Colors.grey.shade600,
                                          ),
                                        ),
                                      ],
                                    ),
                                    subtitle: Text(device.remoteId.toString()),
                                    trailing: isConnected
                                        ? ElevatedButton(
                                            onPressed: _disconnect,
                                            child: const Text('Disconnect'),
                                          )
                                        : Row(
                                            mainAxisSize: MainAxisSize.min,
                                            children: [
                                              ElevatedButton(
                                                onPressed: () =>
                                                    _connectToPrinter(device),
                                                child: const Text('Auto'),
                                              ),
                                              const SizedBox(width: 4),
                                              PopupMenuButton<ConnectionType>(
                                                icon: const Icon(
                                                    Icons.more_vert,
                                                    size: 16),
                                                onSelected: (type) =>
                                                    _connectToPrinter(device,
                                                        forceType: type),
                                                itemBuilder: (context) => [
                                                  const PopupMenuItem(
                                                    value:
                                                        ConnectionType.classic,
                                                    child: Text(
                                                        'Force Classic BT'),
                                                  ),
                                                  const PopupMenuItem(
                                                    value: ConnectionType.ble,
                                                    child: Text('Force BLE'),
                                                  ),
                                                ],
                                              ),
                                            ],
                                          ),
                                  ),
                                );
                              }).toList(),
                            ),
                        ],
                      ),
                    ),
                  ),

                  const SizedBox(height: 16),

                  // Print Section
                  Card(
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Print Functions',
                            style: Theme.of(context).textTheme.titleMedium,
                          ),
                          const SizedBox(height: 16),
                          TextField(
                            controller: _textController,
                            decoration: const InputDecoration(
                              labelText: 'Text to print',
                              hintText: 'Enter text to print',
                              border: OutlineInputBorder(),
                            ),
                            maxLines: 3,
                          ),
                          const SizedBox(height: 16),
                          Wrap(
                            spacing: 8,
                            runSpacing: 8,
                            children: [
                              ElevatedButton.icon(
                                onPressed: _printerManager.isConnected
                                    ? _printText
                                    : null,
                                icon: const Icon(Icons.text_fields),
                                label: const Text('Print Text'),
                              ),
                              ElevatedButton.icon(
                                onPressed: _printerManager.isConnected
                                    ? _printTestPage
                                    : null,
                                icon: const Icon(Icons.description),
                                label: const Text('Print Test Page'),
                              ),
                              ElevatedButton(
                                onPressed: _printerManager.isConnected
                                    ? () async {
                                        bool success = await _printerManager
                                            .simpleTestPrint();
                                        _showResult('Simple Test', success);
                                      }
                                    : null,
                                child: const Text('Simple Test'),
                              ),
                              ElevatedButton(
                                onPressed: _printerManager.isConnected
                                    ? () async {
                                        bool success = await _printerManager
                                            .ppd1AlternativeTest();
                                        _showResult(
                                            'PPD1 Alternative Test', success);
                                      }
                                    : null,
                                child: const Text('PPD1 Alt Test'),
                              ),
                              ElevatedButton(
                                onPressed: _printerManager.isConnected
                                    ? () async {
                                        bool success = await _printerManager
                                            .printTextAsBitmap(
                                                'BITMAP TEST\nHello World!');
                                        _showResult('Bitmap Print', success);
                                      }
                                    : null,
                                child: const Text('Bitmap Print'),
                              ),
                              ElevatedButton(
                                onPressed: _printerManager.isConnected
                                    ? () async {
                                        bool success = await _printerManager
                                            .sendRawTestCommand();
                                        _showResult('Raw Command', success);
                                      }
                                    : null,
                                child: const Text('Raw Command'),
                              ),
                              ElevatedButton.icon(
                                onPressed: _printerManager.isConnected
                                    ? _debugConnection
                                    : null,
                                icon: const Icon(Icons.bug_report),
                                label: const Text('Debug'),
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                  ),

                  const SizedBox(height: 16),

                  // Status Section
                  Card(
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Connection Status',
                            style: Theme.of(context).textTheme.titleMedium,
                          ),
                          const SizedBox(height: 8),
                          Row(
                            children: [
                              Icon(
                                _printerManager.isConnected
                                    ? Icons.bluetooth_connected
                                    : Icons.bluetooth_disabled,
                                color: _printerManager.isConnected
                                    ? Colors.green
                                    : Colors.grey,
                              ),
                              const SizedBox(width: 8),
                              Expanded(
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                      _printerManager.isConnected
                                          ? 'Connected to ${_printerManager.connectedDevice?.platformName ?? "Unknown"}'
                                          : 'Not connected',
                                    ),
                                    if (_printerManager.isConnected)
                                      Text(
                                        'Connection: ${_printerManager.currentConnectionType.name.toUpperCase()}',
                                        style: TextStyle(
                                          fontSize: 12,
                                          color: Colors.grey.shade600,
                                        ),
                                      ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                  ),

                  const SizedBox(height: 16),

                  // Activity Log
                  Card(
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              Text(
                                'Activity Log',
                                style: Theme.of(context).textTheme.titleMedium,
                              ),
                              TextButton(
                                onPressed: () {
                                  setState(() {
                                    _logs.clear();
                                  });
                                },
                                child: const Text('Clear'),
                              ),
                            ],
                          ),
                          const SizedBox(height: 8),
                          Container(
                            height: 250,
                            decoration: BoxDecoration(
                              border: Border.all(color: Colors.grey.shade300),
                              borderRadius: BorderRadius.circular(4),
                            ),
                            child: _logs.isEmpty
                                ? const Center(child: Text('No activity yet'))
                                : ListView.builder(
                                    itemCount: _logs.length,
                                    itemBuilder: (context, index) {
                                      return Padding(
                                        padding: const EdgeInsets.symmetric(
                                          horizontal: 8,
                                          vertical: 2,
                                        ),
                                        child: Text(
                                          _logs[index],
                                          style: const TextStyle(
                                            fontSize: 11,
                                            fontFamily: 'monospace',
                                          ),
                                        ),
                                      );
                                    },
                                  ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
    );
  }

  @override
  void dispose() {
    _textController.dispose();
    super.dispose();
  }
}
