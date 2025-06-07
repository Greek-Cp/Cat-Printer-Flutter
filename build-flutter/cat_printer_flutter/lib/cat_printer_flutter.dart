/// Cat Printer Flutter Library
///
/// A Flutter library for connecting and printing to Cat Printer via Bluetooth.
/// Ported from Python Cat-Printer with all data and communication protocols.
///
/// Usage:
/// ```dart
/// import 'package:cat_printer_flutter/cat_printer_flutter.dart';
///
/// final printerService = CatPrinterService();
/// await printerService.connect(device);
/// await printerService.printText('Hello Cat Printer!');
/// ```
library cat_printer_flutter;

// Export all public APIs
export 'services/cat_printer_service.dart';
export 'models/printer_models.dart';
export 'services/printer_commander.dart';
