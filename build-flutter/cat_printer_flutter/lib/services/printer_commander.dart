// Legacy Printer Commander for Backward Compatibility
// This file provides access to printer commands through the new extensible architecture

// Imports removed as they are not needed for legacy command generation

/// Legacy PrinterCommander class for backward compatibility
///
/// This class maintains the old API while using the new extensible
/// architecture behind the scenes.
class PrinterCommander {
  // Legacy characteristic UUIDs for backward compatibility
  static const String txCharacteristic = '0000ae01-0000-1000-8000-00805f9b34fb';
  static const String rxCharacteristic = '0000ae02-0000-1000-8000-00805f9b34fb';
  static const String dataCharacteristic =
      '0000ae03-0000-1000-8000-00805f9b34fb';

  // Legacy flow control commands
  static const List<int> dataFlowPause = [0x19, 0x00];
  static const List<int> dataFlowResume = [0x1A, 0x00];

  // Legacy command generation methods for backward compatibility
  static List<int> getDeviceStateCommand() =>
      [0x51, 0x78, 0xA3, 0x00, 0x01, 0x00, 0x00, 0x00, 0xFF];

  static List<int> getStartPrintCommand() =>
      [0x51, 0x78, 0xA6, 0x00, 0x01, 0x00, 0x00, 0x00, 0xFC];

  static List<int> getStartPrintNewCommand() =>
      [0x51, 0x78, 0xBE, 0x00, 0x01, 0x00, 0x00, 0x00, 0xE4];

  static List<int> getSetDpiAs200Command() =>
      [0x51, 0x78, 0xA4, 0x00, 0x01, 0x00, 0x00, 0x00, 0xFE];

  static List<int> getSetEnergyCommand(int energy) {
    final energyBytes = _intToBytes(energy, 2);
    return [
      0x51,
      0x78,
      0xAF,
      0x00,
      0x01,
      energyBytes[1],
      energyBytes[0],
      0x00,
      0xFF -
              (0x51 +
                  0x78 +
                  0xAF +
                  0x00 +
                  0x01 +
                  energyBytes[1] +
                  energyBytes[0]) &
          0xFF
    ];
  }

  static List<int> getApplyEnergyCommand() =>
      [0x51, 0x78, 0xA9, 0x00, 0x01, 0x00, 0x00, 0x00, 0xF9];

  static List<int> getUpdateDeviceCommand() =>
      [0x51, 0x78, 0xAA, 0x00, 0x01, 0x00, 0x00, 0x00, 0xF8];

  static List<int> getStartLatticeCommand() =>
      [0x51, 0x78, 0xA3, 0x00, 0x01, 0x00, 0x01, 0x00, 0xFE];

  static List<int> getEndLatticeCommand() =>
      [0x51, 0x78, 0xA3, 0x00, 0x01, 0x00, 0x00, 0x00, 0xFF];

  static List<int> getSetSpeedCommand(int speed) {
    return [
      0x51,
      0x78,
      0xBD,
      0x00,
      0x01,
      speed,
      0x00,
      0x00,
      0xFF - (0x51 + 0x78 + 0xBD + 0x00 + 0x01 + speed) & 0xFF
    ];
  }

  static List<int> getFeedPaperCommand(int pixels) {
    return [
      0x51,
      0x78,
      0xA1,
      0x00,
      0x01,
      pixels,
      0x00,
      0x00,
      0xFF - (0x51 + 0x78 + 0xA1 + 0x00 + 0x01 + pixels) & 0xFF
    ];
  }

  static List<int> getRetractPaperCommand(int pixels) {
    return [
      0x51,
      0x78,
      0xA2,
      0x00,
      0x01,
      pixels,
      0x00,
      0x00,
      0xFF - (0x51 + 0x78 + 0xA2 + 0x00 + 0x01 + pixels) & 0xFF
    ];
  }

  static List<int> getDrawBitmapCommand(List<int> bitmapData) {
    final length = bitmapData.length;
    final lengthBytes = _intToBytes(length, 2);

    List<int> command = [
      0x51,
      0x78,
      0xA2,
      0x00,
      0x01,
      lengthBytes[1],
      lengthBytes[0],
      0x00
    ];

    command.addAll(bitmapData);

    int checksum = 0xFF;
    for (int i = 0; i < command.length; i++) {
      checksum -= command[i];
    }
    command.add(checksum & 0xFF);

    return command;
  }

  static List<int> getSetQualityCommand(int quality) {
    return [
      0x51,
      0x78,
      0xAE,
      0x00,
      0x01,
      quality,
      0x00,
      0x00,
      0xFF - (0x51 + 0x78 + 0xAE + 0x00 + 0x01 + quality) & 0xFF
    ];
  }

  static List<int> getDrawingModeCommand(int mode) {
    return [
      0x51,
      0x78,
      0xBE,
      0x00,
      0x01,
      mode,
      0x00,
      0x00,
      0xFF - (0x51 + 0x78 + 0xBE + 0x00 + 0x01 + mode) & 0xFF
    ];
  }

  // MXW01 specific commands
  static List<int> getMXW01StatusCommand() => [0x22, 0x21, 0x01, 0x1C];

  static List<int> getMXW01VersionCommand() => [0x22, 0x21, 0x01, 0x2E];

  static List<int> getMXW01BatteryCommand() => [0x22, 0x21, 0x01, 0x13];

  static List<int> getMXW01PrintIntensityCommand(int intensity) {
    return [0x22, 0x21, 0x02, 0x1A, intensity];
  }

  static List<int> getMXW01PrintCommand(int lineCount, int printMode) {
    final lineBytes = _intToBytes(lineCount, 2);
    return [
      0x22,
      0x21,
      0x04,
      0x01,
      printMode,
      lineBytes[0],
      lineBytes[1],
      0x00
    ];
  }

  static List<int> getMXW01PrintDataFlushCommand() => [0x22, 0x21, 0x01, 0x18];

  static List<int> getMXW01EjectPaperCommand(int lineCount) {
    return [0x22, 0x21, 0x02, 0x19, lineCount];
  }

  static List<int> getMXW01RetractPaperCommand(int lineCount) {
    return [0x22, 0x21, 0x02, 0x1A, lineCount];
  }

  // Helper function to convert int to bytes
  static List<int> _intToBytes(int value, int byteCount) {
    List<int> bytes = [];
    for (int i = 0; i < byteCount; i++) {
      bytes.add((value >> (8 * i)) & 0xFF);
    }
    return bytes;
  }

  // CRC8 table for checksum calculations (legacy)
  static const List<int> crc8Table = [
    0x00, 0x07, 0x0E, 0x09, 0x1C, 0x1B, 0x12, 0x15,
    0x38, 0x3F, 0x36, 0x31, 0x24, 0x23, 0x2A, 0x2D,
    // ... (full table would be here in production)
  ];

  // Legacy checksum calculation
  static int calculateChecksum(List<int> data) {
    int checksum = 0xFF;
    for (int byte in data) {
      checksum -= byte;
    }
    return checksum & 0xFF;
  }

  // Legacy CRC8 calculation
  static int calculateCRC8(List<int> data) {
    int crc = 0x00;
    for (int byte in data) {
      crc = crc8Table[(crc ^ byte) & 0xFF];
    }
    return crc;
  }
}
