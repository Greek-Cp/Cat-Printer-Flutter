# 🚀 Cat Printer Flutter - Developer Guide untuk Extensible Architecture

## 📋 Overview

Library ini telah diredesign dengan arsitektur yang **extensible** yang memungkinkan developer tim untuk dengan mudah menambahkan:
- ✅ **Model printer baru**
- ✅ **Protocol komunikasi baru**  
- ✅ **Command baru**
- ✅ **Auto-detection untuk model baru**

## 🏗️ Arsitektur Baru

```
┌─────────────────────────────────────────────────────────────┐
│                   Extensible Architecture                   │
├─────────────────────────────────────────────────────────────┤
│  🎯 Easy API Layer (CatPrinterExtensible)                  │
├─────────────────────────────────────────────────────────────┤
│  📝 Registry System (Models, Protocols, Commands)          │
├─────────────────────────────────────────────────────────────┤
│  🏭 Factory Pattern (Auto-creation & Detection)             │
├─────────────────────────────────────────────────────────────┤
│  🔌 Interface Layer (IPrinterModel, IPrinterProtocol, etc.) │
├─────────────────────────────────────────────────────────────┤
│  ⚙️  Implementation Layer (Concrete Models & Protocols)     │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 Quick Start

### 1. Initialize Library

```dart
import 'package:cat_printer_flutter/cat_printer_flutter_extensible.dart';

void main() {
  // Initialize library dengan semua built-in models/commands
  CatPrinterExtensible.initialize();
  
  runApp(MyApp());
}
```

### 2. Basic Usage

```dart
// Create printer service untuk model tertentu
final printer = await EasyPrinter.create('MXW01');

// Connect ke device
await printer.connect('device_id_here');

// Execute command
await printer.execute('print_intensity', {'intensity': 75});

// Disconnect
await printer.disconnect();
```

## 🆕 Menambahkan Model Printer Baru

### Cara 1: Menggunakan Template (Recommended untuk Model Sederhana)

```dart
// Buat model baru menggunakan template
final newModel = CatPrinterExtensible.createCustomModel(
  modelName: 'NEW01',
  modelVersion: '1.0',
  paperWidth: 384,
  protocolType: 'classic', // atau 'mxw01' atau protocol custom
  supportedCommands: [
    'start_print',
    'set_energy', 
    'set_speed',
    'draw_bitmap'
  ],
  supportsNewCommands: false,
  hasFeedingProblems: false,
  customConfiguration: {
    'has_wifi': true,
    'supports_color': false,
    'max_resolution': 203,
  },
  customConstraints: {
    'max_energy': 8192,
    'recommended_speed': 16,
  },
);

// Register model tersebut
CatPrinterExtensible.registerModel('NEW01', () => newModel);
```

### Cara 2: Membuat Class Model Custom (Recommended untuk Model Kompleks)

```dart
// 1. Buat class model baru
class MyNewPrinterModel extends BasePrinterModel {
  @override
  String get modelName => 'MYNEW01';

  @override
  String get modelVersion => '2.0';

  @override
  int get paperWidth => 576; // Paper width berbeda

  @override
  bool get supportsNewCommands => true;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic'; // atau protocol custom

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'draw_bitmap',
      'custom_command', // Command khusus untuk model ini
    ];
  }

  @override
  Map<String, dynamic> getConfiguration() {
    var config = super.getConfiguration();
    config.addAll({
      'supports_wifi': true,
      'supports_color_printing': true,
      'has_display': true,
      'bluetooth_version': '5.0',
    });
    return config;
  }

  @override
  Map<String, dynamic> getConstraints() {
    return {
      'max_energy': 16384, // Energy lebih tinggi
      'max_speed': 128,
      'max_quality': 20,
      'min_energy': 512,
      'min_speed': 4,
      'min_quality': 1,
      'color_modes': ['monochrome', 'rgb', 'cmyk'],
      'max_image_size': 1024 * 1024, // 1MB max
    };
  }
}

// 2. Register model
CatPrinterExtensible.registerModel('MYNEW01', () => MyNewPrinterModel());
```

## 🔧 Menambahkan Command Baru

### Cara 1: Menggunakan Template (Recommended untuk Command Sederhana)

```dart
// Buat command sederhana menggunakan template
final customCommand = CatPrinterExtensible.createCustomCommand(
  commandId: 'set_wifi_config',
  commandName: 'Set WiFi Configuration',
  supportedModels: ['MYNEW01'], // Model yang support
  parameters: {
    'category': 'network',
    'protocol': 'classic',
  },
  parameterSchema: {
    'type': 'object',
    'properties': {
      'ssid': {
        'type': 'string',
        'description': 'WiFi SSID',
        'maxLength': 32,
      },
      'password': {
        'type': 'string',
        'description': 'WiFi Password',
        'maxLength': 64,
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

// Register command
CatPrinterExtensible.registerCommand('set_wifi_config', () => customCommand);
```

### Cara 2: Membuat Class Command Custom (Recommended untuk Command Kompleks)

```dart
// 1. Buat class command baru
class SetColorModeCommand extends BaseCommand {
  @override
  String get commandId => 'set_color_mode';

  @override
  String get commandName => 'Set Color Mode';

  @override
  List<String> get supportedModels => ['MYNEW01']; // Model yang support

  @override
  Map<String, dynamic> get parameters => {
    'category': 'printing',
    'protocol': 'classic',
  };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final colorMode = params['color_mode'] as String? ?? 'monochrome';
    final quality = params['quality'] as int? ?? 5;
    
    int modeValue;
    switch (colorMode) {
      case 'monochrome':
        modeValue = 0x00;
        break;
      case 'rgb':
        modeValue = 0x01;
        break;
      case 'cmyk':
        modeValue = 0x02;
        break;
      default:
        modeValue = 0x00;
    }
    
    return [modeValue, quality];
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'color_mode': {
          'type': 'string',
          'enum': ['monochrome', 'rgb', 'cmyk'],
          'default': 'monochrome',
          'description': 'Color printing mode',
        },
        'quality': {
          'type': 'integer',
          'minimum': 1,
          'maximum': 20,
          'default': 5,
          'description': 'Print quality (1-20)',
        }
      },
      'required': ['color_mode'],
    };
  }
}

// 2. Register command
CatPrinterExtensible.registerCommand('set_color_mode', () => SetColorModeCommand());
```

## 🌐 Menambahkan Protocol Baru

```dart
// 1. Buat class protocol baru
class MyNewProtocol implements IPrinterProtocol {
  @override
  String get protocolName => 'My New Protocol';

  @override
  String get protocolVersion => '1.0';

  @override
  List<int> createCommand(String commandId, List<int> data) {
    // Implementasi format command protocol baru
    // Contoh: [0xAA, 0xBB, commandByte, dataLength, ...data, checksum, 0xCC]
    
    final commandByte = _getCommandByte(commandId);
    List<int> command = [0xAA, 0xBB]; // Magic bytes
    command.add(commandByte);
    command.add(data.length);
    command.addAll(data);
    command.add(_calculateChecksum(data));
    command.add(0xCC); // End marker
    
    return command;
  }

  @override
  Map<String, dynamic> parseResponse(List<int> response) {
    // Parse response sesuai format protocol
    if (response.length < 5) {
      return {'error': 'Response too short'};
    }
    
    return {
      'type': 'my_protocol_response',
      'command': response[2],
      'data': response.sublist(4, response.length - 2),
      'raw': response,
    };
  }

  @override
  List<String> getRequiredCharacteristics() {
    return [
      '0000ae01-0000-1000-8000-00805f9b34fb', // TX
      '0000ae02-0000-1000-8000-00805f9b34fb', // RX
      '0000ae04-0000-1000-8000-00805f9b34fb', // Custom characteristic
    ];
  }

  @override
  bool isValidResponse(List<int> response) {
    return response.length >= 5 && 
           response[0] == 0xAA && 
           response[1] == 0xBB &&
           response[response.length - 1] == 0xCC;
  }

  int _getCommandByte(String commandId) {
    const commandMap = {
      'my_command': 0xC1,
      'another_command': 0xC2,
    };
    return commandMap[commandId] ?? 0x00;
  }

  int _calculateChecksum(List<int> data) {
    return data.fold(0, (sum, byte) => sum ^ byte) & 0xFF;
  }
}

// 2. Register protocol
CatPrinterExtensible.registerProtocol('my_protocol', () => MyNewProtocol());
```

## 🔍 Auto-Detection untuk Model Baru

```dart
// 1. Buat detector untuk model baru
class MyModelDetector implements IModelDetector {
  @override
  Future<IPrinterModel?> detectModel(IProtocolHandler handler) async {
    try {
      // Kirim command untuk deteksi model
      await handler.sendCommand([0xAA, 0xBB, 0xF0, 0x00, 0x00, 0xFF]); // Get version
      
      // Tunggu response
      await for (final response in handler.responseStream.take(1)) {
        final version = String.fromCharCodes(response.sublist(4, response.length - 2));
        
        if (version.startsWith('MYNEW01')) {
          return MyNewPrinterModel();
        }
      }
    } catch (e) {
      // Detection failed
    }
    
    return null;
  }

  @override
  bool canDetect(String protocolType) {
    return protocolType == 'my_protocol';
  }
}

// 2. Register detector
CatPrinterExtensible.registerModelDetector('my_protocol', MyModelDetector());
```

## 📱 Contoh Implementasi Lengkap dalam App

```dart
import 'package:flutter/material.dart';
import 'package:cat_printer_flutter/cat_printer_flutter_extensible.dart';

class PrinterApp extends StatefulWidget {
  @override
  _PrinterAppState createState() => _PrinterAppState();
}

class _PrinterAppState extends State<PrinterApp> {
  EasyPrinter? printer;
  List<IPrinterModel> availableModels = [];
  List<ICommand> availableCommands = [];

  @override
  void initState() {
    super.initState();
    _initializePrinter();
  }

  void _initializePrinter() {
    // Initialize library
    CatPrinterExtensible.initialize();
    
    // Register model baru
    _registerCustomModels();
    
    // Register command baru
    _registerCustomCommands();
    
    // Load available models
    setState(() {
      availableModels = CatPrinterExtensible.getAvailableModels();
    });
  }

  void _registerCustomModels() {
    // Register model MYNEW01
    CatPrinterExtensible.registerModel('MYNEW01', () => MyNewPrinterModel());
    
    // Register model dari template
    final templateModel = CatPrinterExtensible.createCustomModel(
      modelName: 'TEMPLATE01',
      modelVersion: '1.0',
      paperWidth: 384,
      protocolType: 'classic',
      supportedCommands: ['start_print', 'set_energy'],
    );
    CatPrinterExtensible.registerModel('TEMPLATE01', () => templateModel);
  }

  void _registerCustomCommands() {
    // Register command custom
    CatPrinterExtensible.registerCommand('set_color_mode', () => SetColorModeCommand());
    
    // Register command dari template
    final wifiCommand = CatPrinterExtensible.createCustomCommand(
      commandId: 'set_wifi_config',
      commandName: 'Set WiFi Configuration',
      supportedModels: ['MYNEW01'],
      parameters: {'category': 'network'},
      parameterSchema: {
        'type': 'object',
        'properties': {
          'ssid': {'type': 'string'},
          'password': {'type': 'string'},
        },
        'required': ['ssid', 'password'],
      },
      dataGenerator: (params) {
        return []; // Implementation
      },
    );
    CatPrinterExtensible.registerCommand('set_wifi_config', () => wifiCommand);
  }

  Future<void> _connectToPrinter(String modelName) async {
    printer = await EasyPrinter.create(modelName);
    if (printer != null) {
      final connected = await printer!.connect('device_id_here');
      if (connected) {
        setState(() {
          availableCommands = printer!.availableCommands;
        });
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Connected to $modelName')),
        );
      }
    }
  }

  Future<void> _executeCommand(String commandId, Map<String, dynamic> params) async {
    if (printer != null && printer!.isConnected) {
      try {
        await printer!.execute(commandId, params);
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Command $commandId executed successfully')),
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
      appBar: AppBar(title: Text('Cat Printer Extensible')),
      body: Column(
        children: [
          // Models section
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: EdgeInsets.all(16),
                  child: Text('Available Models:', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                ),
                Expanded(
                  child: ListView.builder(
                    itemCount: availableModels.length,
                    itemBuilder: (context, index) {
                      final model = availableModels[index];
                      return ListTile(
                        title: Text('${model.modelName} (${model.modelVersion})'),
                        subtitle: Text('Protocol: ${model.protocolType}, Width: ${model.paperWidth}px'),
                        trailing: ElevatedButton(
                          onPressed: () => _connectToPrinter(model.modelName),
                          child: Text('Connect'),
                        ),
                      );
                    },
                  ),
                ),
              ],
            ),
          ),
          
          // Commands section
          if (availableCommands.isNotEmpty)
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: EdgeInsets.all(16),
                    child: Text('Available Commands:', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                  ),
                  Expanded(
                    child: ListView.builder(
                      itemCount: availableCommands.length,
                      itemBuilder: (context, index) {
                        final command = availableCommands[index];
                        return ListTile(
                          title: Text(command.commandName),
                          subtitle: Text('ID: ${command.commandId}'),
                          trailing: ElevatedButton(
                            onPressed: () => _executeCommand(command.commandId, {}),
                            child: Text('Execute'),
                          ),
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
        ],
      ),
      
      // Library info
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          final info = CatPrinterExtensible.getLibraryInfo();
          showDialog(
            context: context,
            builder: (context) => AlertDialog(
              title: Text('Library Info'),
              content: Text(info.toString()),
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

## 🛠️ Advanced Features

### 1. Command Validation

```dart
// Validasi parameter sebelum eksekusi
final valid = CatPrinterExtensible.validateCommandParameters(
  'set_color_mode',
  {'color_mode': 'rgb', 'quality': 10},
);

if (valid) {
  // Execute command
}
```

### 2. Get Model Information

```dart
// Get model info
final modelInfo = CatPrinterExtensible.getModelInfo('MYNEW01');
print('Model supports: ${modelInfo?['supports_wifi']}');

// Get model constraints
final constraints = CatPrinterExtensible.getModelConstraints('MYNEW01');
print('Max energy: ${constraints?['max_energy']}');
```

### 3. Get Command Schema

```dart
// Get parameter schema untuk command
final schema = CatPrinterExtensible.getCommandSchema('set_color_mode');
print('Required parameters: ${schema?['required']}');
```

## ✅ Best Practices

### 1. **Naming Convention**
```dart
// Model names: Uppercase, descriptive
'MYNEW01', 'ADVANCED02', 'WIFI03'

// Command IDs: lowercase dengan underscore
'set_color_mode', 'get_wifi_status', 'print_test_page'

// Protocol types: lowercase
'classic', 'mxw01', 'my_protocol'
```

### 2. **Error Handling**
```dart
try {
  await printer.execute('my_command', params);
} catch (e) {
  if (e.toString().contains('not found')) {
    // Command tidak terdaftar
  } else if (e.toString().contains('Invalid parameters')) {
    // Parameter tidak valid
  }
}
```

### 3. **Parameter Validation**
```dart
// Selalu definisikan schema yang lengkap
@override
Map<String, dynamic> getParameterSchema() {
  return {
    'type': 'object',
    'properties': {
      'intensity': {
        'type': 'integer',
        'minimum': 1,
        'maximum': 100,
        'description': 'Print intensity (1-100)',
      }
    },
    'required': ['intensity'],
  };
}
```

## 🚀 Migration dari Library Lama

Jika Anda menggunakan library lama, berikut cara migration:

### Sebelum (Library Lama):
```dart
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

final service = CatPrinterService();
await service.connect(device);
await service.printText('Hello');
```

### Sesudah (Library Baru):
```dart
import 'package:cat_printer_flutter/cat_printer_flutter_extensible.dart';

CatPrinterExtensible.initialize();
final printer = await EasyPrinter.create('MXW01');
await printer.connect('device_id');
await printer.execute('print_text', {'text': 'Hello'});
```

## 📞 Support & Questions

Jika ada pertanyaan atau butuh bantuan implementasi:
1. Lihat contoh-contoh di file ini
2. Check library info: `CatPrinterExtensible.getLibraryInfo()`
3. Gunakan parameter validation: `validateCommandParameters()`
4. Test dengan model template dulu sebelum buat custom class

**Happy Coding! 🎉** 