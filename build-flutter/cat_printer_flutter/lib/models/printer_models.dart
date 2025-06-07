// Cat Printer Models - Ported from Python code
// Based on printer_lib/models.py and printer_lib/commander.py

class PrinterModel {
  final String name;
  final int paperWidth;
  final bool isNewKind;
  final bool problemFeeding;

  const PrinterModel({
    required this.name,
    required this.paperWidth,
    required this.isNewKind,
    required this.problemFeeding,
  });
}

class PrinterModels {
  // All known supported models from Python code
  static const Map<String, PrinterModel> models = {
    '_ZZ00': PrinterModel(
        name: '_ZZ00',
        paperWidth: 384,
        isNewKind: false,
        problemFeeding: false),
    'GB01': PrinterModel(
        name: 'GB01', paperWidth: 384, isNewKind: false, problemFeeding: false),
    'GB02': PrinterModel(
        name: 'GB02', paperWidth: 384, isNewKind: false, problemFeeding: false),
    'GB03': PrinterModel(
        name: 'GB03', paperWidth: 384, isNewKind: true, problemFeeding: false),
    'GT01': PrinterModel(
        name: 'GT01', paperWidth: 384, isNewKind: false, problemFeeding: false),
    'MX05': PrinterModel(
        name: 'MX05', paperWidth: 384, isNewKind: false, problemFeeding: true),
    'MX06': PrinterModel(
        name: 'MX06', paperWidth: 384, isNewKind: false, problemFeeding: true),
    'MX08': PrinterModel(
        name: 'MX08', paperWidth: 384, isNewKind: false, problemFeeding: true),
    'MX09': PrinterModel(
        name: 'MX09', paperWidth: 384, isNewKind: false, problemFeeding: true),
    'MX10': PrinterModel(
        name: 'MX10', paperWidth: 384, isNewKind: false, problemFeeding: true),
    'MX11': PrinterModel(
        name: 'MX11', paperWidth: 384, isNewKind: false, problemFeeding: true),
    'YT01': PrinterModel(
        name: 'YT01', paperWidth: 384, isNewKind: false, problemFeeding: false),
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
