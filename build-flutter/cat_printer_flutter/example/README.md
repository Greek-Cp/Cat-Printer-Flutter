# 🚀 Cat Printer Flutter - Examples

## 📋 Overview

Contoh-contoh penggunaan library Cat Printer Flutter dengan **Extensible Architecture**.

## 🆕 New Extensible API Examples

### 1. Basic Usage

```dart
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

void main() async {
  // Initialize library
  CatPrinterExtensible.initialize();
  
  // Create printer instance
  final printer = await EasyPrinter.create('MXW01');
  
  if (printer != null) {
    // Connect to device
    final connected = await printer.connect('device_bluetooth_id');
    
    if (connected) {
      // Execute commands
      await printer.execute('print_intensity', {'intensity': 75});
      await printer.execute('get_status');
      await printer.execute('battery_level');
      
      // Disconnect
      await printer.disconnect();
    }
  }
}
```

### 2. Adding Custom Model

```dart
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

void main() {
  CatPrinterExtensible.initialize();
  
  // Add new model using template
  final customModel = CatPrinterExtensible.createCustomModel(
    modelName: 'CUSTOM01',
    modelVersion: '1.0',
    paperWidth: 384,
    protocolType: 'classic',
    supportedCommands: [
      'start_print',
      'set_energy',
      'set_speed',
      'draw_bitmap',
    ],
    customConfiguration: {
      'has_wifi': true,
      'supports_color': false,
    },
  );
  
  // Register the model
  CatPrinterExtensible.registerModel('CUSTOM01', () => customModel);
  
  // Now you can use it
  final printer = await EasyPrinter.create('CUSTOM01');
}
```

### 3. Adding Custom Command

```dart
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

void main() {
  CatPrinterExtensible.initialize();
  
  // Add custom command using template
  final wifiCommand = CatPrinterExtensible.createCustomCommand(
    commandId: 'set_wifi_config',
    commandName: 'Set WiFi Configuration',
    supportedModels: ['CUSTOM01'],
    parameters: {
      'category': 'network',
      'protocol': 'classic',
    },
    parameterSchema: {
      'type': 'object',
      'properties': {
        'ssid': {
          'type': 'string',
          'maxLength': 32,
          'description': 'WiFi SSID',
        },
        'password': {
          'type': 'string',
          'maxLength': 64,
          'description': 'WiFi Password',
        },
      },
      'required': ['ssid', 'password'],
    },
    dataGenerator: (params) {
      final ssid = params['ssid'] as String;
      final password = params['password'] as String;
      
      List<int> data = [];
      data.addAll(ssid.codeUnits);
      data.add(0x00); // Separator
      data.addAll(password.codeUnits);
      
      return data;
    },
  );
  
  // Register the command
  CatPrinterExtensible.registerCommand('set_wifi_config', () => wifiCommand);
  
  // Use the command
  final printer = await EasyPrinter.create('CUSTOM01');
  await printer.connect('device_id');
  await printer.execute('set_wifi_config', {
    'ssid': 'MyWiFi',
    'password': 'MyPassword123',
  });
}
```

### 4. Advanced Custom Model Class

```dart
class AdvancedPrinterModel extends BasePrinterModel {
  @override
  String get modelName => 'ADV01';

  @override
  String get modelVersion => '2.0';

  @override
  int get paperWidth => 576; // Wider paper

  @override
  bool get supportsNewCommands => true;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'draw_bitmap',
      'set_color_mode', // Custom command
      'get_temperature', // Custom command
    ];
  }

  @override
  Map<String, dynamic> getConfiguration() {
    var config = super.getConfiguration();
    config.addAll({
      'supports_color_printing': true,
      'has_temperature_sensor': true,
      'max_resolution': 300,
      'bluetooth_version': '5.0',
    });
    return config;
  }

  @override
  Map<String, dynamic> getConstraints() {
    return {
      'max_energy': 32768,
      'max_speed': 64,
      'max_quality': 25,
      'color_modes': ['monochrome', 'rgb'],
      'temperature_range': {'min': -10, 'max': 60},
    };
  }
}

// Register the advanced model
CatPrinterExtensible.registerModel('ADV01', () => AdvancedPrinterModel());
```

### 5. Flutter Widget Integration

```dart
import 'package:flutter/material.dart';
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

class PrinterDemoPage extends StatefulWidget {
  @override
  _PrinterDemoPageState createState() => _PrinterDemoPageState();
}

class _PrinterDemoPageState extends State<PrinterDemoPage> {
  EasyPrinter? printer;
  List<IPrinterModel> availableModels = [];
  String? selectedModel;

  @override
  void initState() {
    super.initState();
    _initializePrinter();
  }

  void _initializePrinter() {
    // Initialize with custom models
    CatPrinterExtensible.initialize();
    
    // Register custom model
    final customModel = CatPrinterExtensible.createCustomModel(
      modelName: 'DEMO01',
      modelVersion: '1.0',
      paperWidth: 384,
      protocolType: 'classic',
      supportedCommands: ['start_print', 'set_energy'],
    );
    CatPrinterExtensible.registerModel('DEMO01', () => customModel);
    
    // Load available models
    setState(() {
      availableModels = CatPrinterExtensible.getAvailableModels();
    });
  }

  Future<void> _connectToPrinter(String modelName) async {
    printer = await EasyPrinter.create(modelName);
    if (printer != null) {
      // Simulate connection (replace with real device ID)
      final connected = await printer!.connect('simulated_device_id');
      
      setState(() {
        selectedModel = connected ? modelName : null;
      });
      
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(connected 
            ? 'Connected to $modelName' 
            : 'Failed to connect to $modelName'
          ),
        ),
      );
    }
  }

  Future<void> _executeTestCommand() async {
    if (printer != null && printer!.isConnected) {
      try {
        await printer!.execute('set_energy', {'energy': 5000});
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Test command executed successfully')),
        );
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Error: $e')),
        );
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Cat Printer Demo'),
      ),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Available Printer Models:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16),
            
            // Models list
            Expanded(
              child: ListView.builder(
                itemCount: availableModels.length,
                itemBuilder: (context, index) {
                  final model = availableModels[index];
                  final isSelected = selectedModel == model.modelName;
                  
                  return Card(
                    color: isSelected ? Colors.green[100] : null,
                    child: ListTile(
                      title: Text('${model.modelName} (v${model.modelVersion})'),
                      subtitle: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text('Protocol: ${model.protocolType}'),
                          Text('Paper Width: ${model.paperWidth}px'),
                          Text('Commands: ${model.getSupportedCommands().length}'),
                        ],
                      ),
                      trailing: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          if (isSelected)
                            Icon(Icons.check_circle, color: Colors.green)
                          else
                            ElevatedButton(
                              onPressed: () => _connectToPrinter(model.modelName),
                              child: Text('Connect'),
                            ),
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
            
            // Test commands
            if (selectedModel != null) ...[
              SizedBox(height: 16),
              Text(
                'Test Commands:',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              SizedBox(height: 8),
              Row(
                children: [
                  ElevatedButton(
                    onPressed: _executeTestCommand,
                    child: Text('Set Energy'),
                  ),
                  SizedBox(width: 8),
                  ElevatedButton(
                    onPressed: () async {
                      final commands = printer!.availableCommands;
                      showDialog(
                        context: context,
                        builder: (context) => AlertDialog(
                          title: Text('Available Commands'),
                          content: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: commands.map((cmd) => 
                              Text('• ${cmd.commandName} (${cmd.commandId})')
                            ).toList(),
                          ),
                          actions: [
                            TextButton(
                              onPressed: () => Navigator.pop(context),
                              child: Text('OK'),
                            ),
                          ],
                        ),
                      );
                    },
                    child: Text('Show Commands'),
                  ),
                ],
              ),
            ],
          ],
        ),
      ),
      
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          final info = CatPrinterExtensible.getLibraryInfo();
          showDialog(
            context: context,
            builder: (context) => AlertDialog(
              title: Text('Library Information'),
              content: SingleChildScrollView(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text('Version: ${info['version']}'),
                    Text('Models: ${info['registered_models']?.length ?? 0}'),
                    Text('Commands: ${info['registered_commands']?.length ?? 0}'),
                    Text('Protocols: ${info['registered_protocols']?.length ?? 0}'),
                    SizedBox(height: 8),
                    Text('Features:', style: TextStyle(fontWeight: FontWeight.bold)),
                    ...((info['features'] as List?)?.map((f) => Text('• $f')) ?? []),
                  ],
                ),
              ),
              actions: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: Text('OK'),
                ),
              ],
            ),
          );
        },
        child: Icon(Icons.info),
      ),
    );
  }

  @override
  void dispose() {
    printer?.disconnect();
    super.dispose();
  }
}
```

## 🔄 Legacy API Examples (Backward Compatible)

### Basic Legacy Usage

```dart
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

void main() async {
  final printerService = CatPrinterService();
  
  // Connect to device
  await printerService.connect(device);
  
  // Print text
  await printerService.printText('Hello Cat Printer!');
  
  // Print image
  await printerService.printImage(imageData);
  
  // Disconnect
  await printerService.disconnect();
}
```

## 🧪 Testing Your Extensions

### 1. Validate Parameters

```dart
// Test parameter validation
final valid = CatPrinterExtensible.validateCommandParameters(
  'set_wifi_config',
  {'ssid': 'TestWiFi', 'password': 'test123'},
);
print('Parameters valid: $valid');
```

### 2. Check Model Info

```dart
// Get model information
final modelInfo = CatPrinterExtensible.getModelInfo('CUSTOM01');
print('Model config: $modelInfo');

final constraints = CatPrinterExtensible.getModelConstraints('CUSTOM01');
print('Model constraints: $constraints');
```

### 3. Command Schema

```dart
// Get command schema
final schema = CatPrinterExtensible.getCommandSchema('set_wifi_config');
print('Command schema: $schema');
```

## 📱 Complete Demo App

Lihat file `example/lib/main.dart` untuk demo app lengkap yang menunjukkan:
- ✅ Model registration
- ✅ Command registration  
- ✅ Auto-detection
- ✅ Parameter validation
- ✅ Error handling
- ✅ UI integration

## 🔧 Development Tips

1. **Always initialize** library sebelum register model/command baru
2. **Use templates** untuk model/command sederhana
3. **Create custom classes** untuk logic kompleks
4. **Validate parameters** sebelum eksekusi command
5. **Handle errors** dengan proper try-catch
6. **Test thoroughly** sebelum production

Happy coding! 🎉