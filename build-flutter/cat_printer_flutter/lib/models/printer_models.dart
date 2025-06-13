// Cat Printer Models - Refactored for better extensibility
// Based on printer_lib/models.py and printer_lib/commander.py

import '../services/commands/generic_commands.dart';
import '../services/commands/mxw01_commands.dart';

abstract class PrinterCommandInterface {
  /// Get start print command
  List<int> getStartPrintCommand();

  /// Get device state command
  List<int> getDeviceStateCommand();

  /// Get set DPI command
  List<int> getSetDpiCommand();

  /// Get set energy command
  List<int> getSetEnergyCommand(int energy);

  /// Get set speed command
  List<int> getSetSpeedCommand(int speed);

  /// Get apply energy command
  List<int> getApplyEnergyCommand();

  /// Get update device command
  List<int> getUpdateDeviceCommand();

  /// Get start lattice command
  List<int> getStartLatticeCommand();

  /// Get end lattice command
  List<int> getEndLatticeCommand();

  /// Get feed paper command
  List<int> getFeedPaperCommand(int pixels);

  /// Get retract paper command
  List<int> getRetractPaperCommand(int pixels);

  /// Get draw bitmap command
  List<int> getDrawBitmapCommand(List<int> bitmapData);

  /// Get status command (optional for some models)
  List<int>? getStatusCommand() => null;

  /// Get battery command (optional for some models)
  List<int>? getBatteryCommand() => null;

  /// Get version command (optional for some models)
  List<int>? getVersionCommand() => null;
}

enum PrinterType {
  generic, // GB01, GB02, GT01, YT01, etc.
  genericNew, // GB03 (supports compressed data)
  mxSeries, // MX05, MX06, MX08, MX09, MX10, MX11 (with feeding problems)
  mxw01, // MXW01 (different protocol)
}

class PrinterModel {
  final String name;
  final int paperWidth;
  final bool isNewKind;
  final bool problemFeeding;
  final PrinterType type;
  final String txCharacteristic;
  final String rxCharacteristic;
  final String? dataCharacteristic; // Only for some models like MXW01

  const PrinterModel({
    required this.name,
    required this.paperWidth,
    required this.isNewKind,
    required this.problemFeeding,
    required this.type,
    this.txCharacteristic = '0000ae01-0000-1000-8000-00805f9b34fb',
    this.rxCharacteristic = '0000ae02-0000-1000-8000-00805f9b34fb',
    this.dataCharacteristic,
  });

  /// Create command interface for this model
  PrinterCommandInterface createCommandInterface() {
    return PrinterCommandFactory.createForModel(this);
  }
}

class PrinterModels {
  // All known supported models from Python code
  static const Map<String, PrinterModel> models = {
    '_ZZ00': PrinterModel(
      name: '_ZZ00',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: false,
      type: PrinterType.generic,
    ),
    'GB01': PrinterModel(
      name: 'GB01',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: false,
      type: PrinterType.generic,
    ),
    'GB02': PrinterModel(
      name: 'GB02',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: false,
      type: PrinterType.generic,
    ),
    'GB03': PrinterModel(
      name: 'GB03',
      paperWidth: 384,
      isNewKind: true,
      problemFeeding: false,
      type: PrinterType.genericNew,
    ),
    'GT01': PrinterModel(
      name: 'GT01',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: false,
      type: PrinterType.generic,
    ),
    'MX05': PrinterModel(
      name: 'MX05',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: true,
      type: PrinterType.mxSeries,
    ),
    'MX06': PrinterModel(
      name: 'MX06',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: true,
      type: PrinterType.mxSeries,
    ),
    'MX08': PrinterModel(
      name: 'MX08',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: true,
      type: PrinterType.mxSeries,
    ),
    'MX09': PrinterModel(
      name: 'MX09',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: true,
      type: PrinterType.mxSeries,
    ),
    'MX10': PrinterModel(
      name: 'MX10',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: true,
      type: PrinterType.mxSeries,
    ),
    'MX11': PrinterModel(
      name: 'MX11',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: true,
      type: PrinterType.mxSeries,
    ),
    'YT01': PrinterModel(
      name: 'YT01',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: false,
      type: PrinterType.generic,
    ),
    'MXW01': PrinterModel(
      name: 'MXW01',
      paperWidth: 384,
      isNewKind: false,
      problemFeeding: false,
      type: PrinterType.mxw01,
      dataCharacteristic: '0000ae03-0000-1000-8000-00805f9b34fb',
    ),
  };

  static PrinterModel? getModel(String name) {
    return models[name];
  }

  static bool isSupported(String name) {
    return models.containsKey(name);
  }

  static List<String> getSupportedModels() {
    return models.keys.toList();
  }

  static List<PrinterModel> getModelsByType(PrinterType type) {
    return models.values.where((model) => model.type == type).toList();
  }
}

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
    this.energy = 4096, // Optimal energy from Python (0x1000)
    this.speed = 32, // Paper feed speed
    this.quality = 5, // Maximum quality like Python
    this.mtu = 200, // MTU size for Bluetooth
    this.scanTime = 4.0, // Bluetooth scan time
    this.connectionTimeout = 5.0, // Connection timeout
    this.flipH = false, // Flip horizontal
    this.flipV = false, // Flip vertical
    this.dryRun = false, // Test mode
    this.fake = false, // Fake mode for testing
    this.dump = false, // Dump traffic data
  });
}

// Factory untuk membuat command interface berdasarkan model
class PrinterCommandFactory {
  static PrinterCommandInterface createForModel(PrinterModel model) {
    switch (model.type) {
      case PrinterType.generic:
        return GenericPrinterCommands();
      case PrinterType.genericNew:
        return GenericNewPrinterCommands();
      case PrinterType.mxSeries:
        return MXSeriesPrinterCommands();
      case PrinterType.mxw01:
        return MXW01PrinterCommands();
    }
  }
}
