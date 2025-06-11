// Legacy Printer Models for Backward Compatibility
// This file provides access to printer models through the new extensible architecture

// Re-export for backward compatibility
export '../models/extensible_printer_models.dart';

/// Legacy PrinterModels class for backward compatibility
///
/// This class maintains the old API while providing static access to model data.
class PrinterModels {
  /// Get list of supported printer models
  static List<String> getSupportedModels() {
    return [
      'GB01',
      'GB02',
      'GB03',
      'GT01',
      'MX05',
      'MX06',
      'MX07',
      'MX08',
      'MX09',
      'MX10',
      'MX11',
      'YT01',
      'MXW01'
    ];
  }

  /// Check if a device name is supported
  static bool isSupported(String deviceName) {
    final upperName = deviceName.toUpperCase();

    // Check if device name contains any supported model name
    for (final modelName in getSupportedModels()) {
      if (upperName.contains(modelName)) {
        return true;
      }
    }

    // Check for common patterns
    if (upperName.contains('CAT') ||
        upperName.contains('PRINTER') ||
        upperName.contains('GB') ||
        upperName.contains('MX') ||
        upperName.contains('GT') ||
        upperName.contains('YT')) {
      return true;
    }

    return false;
  }

  /// Get printer model by device name (simplified)
  static PrinterModelLegacy? getModel(String deviceName) {
    final upperName = deviceName.toUpperCase();

    // Detect specific models
    if (upperName.contains('MXW01')) {
      return PrinterModelLegacy('MXW01', 384, true, false, false);
    }

    if (upperName.contains('GB01')) {
      return PrinterModelLegacy('GB01', 384, false, false, true);
    }

    if (upperName.contains('GB02')) {
      return PrinterModelLegacy('GB02', 384, false, false, false);
    }

    if (upperName.contains('GB03')) {
      return PrinterModelLegacy('GB03', 384, false, false, false);
    }

    if (upperName.contains('GT01')) {
      return PrinterModelLegacy('GT01', 384, false, false, false);
    }

    if (upperName.contains('YT01')) {
      return PrinterModelLegacy('YT01', 384, false, false, false);
    }

    // MX series (MX05-MX11)
    for (int i = 5; i <= 11; i++) {
      if (upperName.contains('MX${i.toString().padLeft(2, '0')}')) {
        return PrinterModelLegacy(
            'MX${i.toString().padLeft(2, '0')}', 384, false, true, false);
      }
    }

    // Default for unknown but supported devices
    if (isSupported(deviceName)) {
      return PrinterModelLegacy('Unknown', 384, false, false, false);
    }

    return null;
  }

  /// Get all available printer models
  static List<PrinterModelLegacy> getAllModels() {
    return getSupportedModels().map((name) {
      return getModel(name) ??
          PrinterModelLegacy(name, 384, false, false, false);
    }).toList();
  }

  /// Get paper width for a specific model
  static int getPaperWidth(String modelName) {
    final model = getModel(modelName);
    return model?.paperWidth ?? 384; // Default width
  }

  /// Check if model has feeding problems
  static bool hasFeedingProblems(String modelName) {
    final model = getModel(modelName);
    return model?.problemFeeding ?? false;
  }

  /// Check if model is new kind
  static bool isNewKind(String modelName) {
    final model = getModel(modelName);
    return model?.isNewKind ?? false;
  }

  /// Check if model supports data characteristic (MXW01)
  static bool supportsDataCharacteristic(String modelName) {
    return modelName.toUpperCase() == 'MXW01';
  }

  /// Get model protocol type
  static String getProtocolType(String modelName) {
    if (modelName.toUpperCase() == 'MXW01') {
      return 'MXW01';
    }
    return 'Classic';
  }

  /// Legacy constants for backward compatibility
  static const String classicProtocol = 'Classic';
  static const String mxw01Protocol = 'MXW01';

  /// Default paper width
  static const int defaultPaperWidth = 384;

  /// Default model names
  static const List<String> defaultSupportedModels = [
    'GB01',
    'GB02',
    'GB03',
    'GT01',
    'MX05',
    'MX06',
    'MX07',
    'MX08',
    'MX09',
    'MX10',
    'MX11',
    'YT01',
    'MXW01'
  ];
}

/// Legacy printer model class for backward compatibility
class PrinterModelLegacy {
  final String name;
  final int paperWidth;
  final bool problemFeeding;
  final bool isNewKind;
  final bool isClassic;

  const PrinterModelLegacy(
    this.name,
    this.paperWidth,
    this.problemFeeding,
    this.isNewKind,
    this.isClassic,
  );

  String get modelName => name;
  bool get isMXW01 => name == 'MXW01';
  String get protocolType => isMXW01 ? 'MXW01' : 'Classic';
}
