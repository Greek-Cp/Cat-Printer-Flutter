// ignore_for_file: library_private_types_in_public_api
///
/// A Flutter library for connecting and printing to Cat Printer via Bluetooth.
/// Ported from Python Cat-Printer with all data and communication protocols.
///
/// This library now supports EXTENSIBLE ARCHITECTURE for easy addition of:
/// - New printer models
/// - New communication protocols
/// - New commands
/// - Auto-detection capabilities
///
/// ## Quick Start (New Extensible API):
/// ```dart
/// import 'package:cat_printer_flutter/cat_printer_flutter.dart';
///
/// // Initialize extensible library
/// CatPrinterExtensible.initialize();
///
/// // Create printer for specific model
/// final printer = await EasyPrinter.create('MXW01');
/// await printer.connect(deviceId);
///
/// // Execute commands
/// await printer.execute('print_intensity', {'intensity': 75});
/// await printer.execute('get_status');
///
/// // Print image/widget (backward compatible)
/// await printer.execute('print_image', {'image': imageData});
/// ```
///
/// ## Adding New Models (Developer Team):
/// ```dart
/// // Method 1: Using template (simple)
/// final newModel = CatPrinterExtensible.createCustomModel(
///   modelName: 'NEW01',
///   modelVersion: '1.0',
///   paperWidth: 384,
///   protocolType: 'classic',
///   supportedCommands: ['start_print', 'set_energy'],
/// );
/// CatPrinterExtensible.registerModel('NEW01', () => newModel);
///
/// // Method 2: Custom class (advanced)
/// class MyNewModel extends BasePrinterModel {
///   @override
///   String get modelName => 'MYNEW01';
///   // ... implement other methods
/// }
/// CatPrinterExtensible.registerModel('MYNEW01', () => MyNewModel());
/// ```
///
/// ## Adding New Commands:
/// ```dart
/// // Method 1: Using template
/// final customCmd = CatPrinterExtensible.createCustomCommand(
///   commandId: 'my_command',
///   commandName: 'My Custom Command',
///   supportedModels: ['NEW01'],
///   parameters: {'category': 'custom'},
///   parameterSchema: {...},
///   dataGenerator: (params) => [0x01, 0x02],
/// );
/// CatPrinterExtensible.registerCommand('my_command', () => customCmd);
///
/// // Method 2: Custom class
/// class MyCustomCommand extends BaseCommand {
///   // ... implement command logic
/// }
/// CatPrinterExtensible.registerCommand('my_command', () => MyCustomCommand());
/// ```
///
/// ## Legacy API (Backward Compatible):
/// ```dart
/// final printerService = CatPrinterService();
/// await printerService.connect(device);
/// await printerService.printText('Hello Cat Printer!');
/// await printerService.printImage(image);
/// await printerService.printWidget(widget);
/// ```
///
library cat_printer_flutter;

// Export NEW EXTENSIBLE APIs (Primary)
export 'cat_printer_flutter_extensible.dart';

// Export LEGACY APIs (Backward Compatibility)
export 'services/cat_printer_service.dart';
export 'models/printer_models.dart';
export 'services/printer_commander.dart';

// Export Core Components for Advanced Users
export 'core/interfaces.dart';
export 'core/registry.dart';
export 'protocols/classic_protocol.dart';
export 'protocols/mxw01_protocol.dart';
export 'models/extensible_printer_models.dart';
export 'commands/extensible_commands.dart';
