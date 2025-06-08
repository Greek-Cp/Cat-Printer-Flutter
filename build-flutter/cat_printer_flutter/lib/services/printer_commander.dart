// Cat Printer Commander - Communication protocol and commands
// Ported from Python printer.py and commander.py

class PrinterCommander {
  // Bluetooth characteristics from Python code
  static const String txCharacteristic = '0000ae01-0000-1000-8000-00805f9b34fb';
  static const String rxCharacteristic = '0000ae02-0000-1000-8000-00805f9b34fb';
  // Data characteristic for MXW01 model (from C# implementation)
  static const String dataCharacteristic = '0000ae03-0000-1000-8000-00805f9b34fb';

  // Command constants from Python code
  static const int startPrint = 0xa3;
  static const int startPrintNew = 0xa3; // with prefix 0x12
  static const int setEnergy = 0xaf;
  static const int setSpeed = 0xbd;
  static const int applyEnergy = 0xbe;
  static const int setQuality =
      0xa4; // Command untuk set quality (sama dengan setDpiAs200)
  static const int drawingMode = 0xbe; // Command untuk drawing mode
  static const int getDeviceState = 0xa3;
  static const int getDeviceInfo = 0xa8;
  static const int updateDevice = 0xa9;
  static const int setDpiAs200 = 0xa4;
  static const int startLattice = 0xa6;
  static const int endLattice = 0xa6;
  static const int retractPaper = 0xa0;
  static const int feedPaper = 0xa1;
  static const int drawBitmap = 0xa2;

  // MXW01 specific commands (from C# implementation)
  static const int getStatus = 0xa1;
  static const int printIntensity = 0xa2;
  static const int ejectPaper = 0xa3;
  static const int retractPaperMXW01 = 0xa4;
  static const int queryCount = 0xa7;
  static const int print = 0xa9;
  static const int printComplete = 0xaa;
  static const int batteryLevel = 0xab;
  static const int cancelPrint = 0xac;
  static const int printDataFlush = 0xad;
  static const int unknownAE = 0xae;
  static const int getPrintType = 0xb0;
  static const int getVersion = 0xb1;
  static const int unknownB2 = 0xb2;
  static const int unknownB3 = 0xb3;

  // Data flow control from Python code
  static const List<int> dataFlowPause = [
    0x51,
    0x78,
    0xae,
    0x01,
    0x01,
    0x00,
    0x10,
    0x70,
    0xff
  ];
  static const List<int> dataFlowResume = [
    0x51,
    0x78,
    0xae,
    0x01,
    0x01,
    0x00,
    0x00,
    0x00,
    0xff
  ];

  // CRC8 table from Python code
  static const List<int> crc8Table = [
    0x00,
    0x07,
    0x0e,
    0x09,
    0x1c,
    0x1b,
    0x12,
    0x15,
    0x38,
    0x3f,
    0x36,
    0x31,
    0x24,
    0x23,
    0x2a,
    0x2d,
    0x70,
    0x77,
    0x7e,
    0x79,
    0x6c,
    0x6b,
    0x62,
    0x65,
    0x48,
    0x4f,
    0x46,
    0x41,
    0x54,
    0x53,
    0x5a,
    0x5d,
    0xe0,
    0xe7,
    0xee,
    0xe9,
    0xfc,
    0xfb,
    0xf2,
    0xf5,
    0xd8,
    0xdf,
    0xd6,
    0xd1,
    0xc4,
    0xc3,
    0xca,
    0xcd,
    0x90,
    0x97,
    0x9e,
    0x99,
    0x8c,
    0x8b,
    0x82,
    0x85,
    0xa8,
    0xaf,
    0xa6,
    0xa1,
    0xb4,
    0xb3,
    0xba,
    0xbd,
    0xc7,
    0xc0,
    0xc9,
    0xce,
    0xdb,
    0xdc,
    0xd5,
    0xd2,
    0xff,
    0xf8,
    0xf1,
    0xf6,
    0xe3,
    0xe4,
    0xed,
    0xea,
    0xb7,
    0xb0,
    0xb9,
    0xbe,
    0xab,
    0xac,
    0xa5,
    0xa2,
    0x8f,
    0x88,
    0x81,
    0x86,
    0x93,
    0x94,
    0x9d,
    0x9a,
    0x27,
    0x20,
    0x29,
    0x2e,
    0x3b,
    0x3c,
    0x35,
    0x32,
    0x1f,
    0x18,
    0x11,
    0x16,
    0x03,
    0x04,
    0x0d,
    0x0a,
    0x57,
    0x50,
    0x59,
    0x5e,
    0x4b,
    0x4c,
    0x45,
    0x42,
    0x6f,
    0x68,
    0x61,
    0x66,
    0x73,
    0x74,
    0x7d,
    0x7a,
    0x89,
    0x8e,
    0x87,
    0x80,
    0x95,
    0x92,
    0x9b,
    0x9c,
    0xb1,
    0xb6,
    0xbf,
    0xb8,
    0xad,
    0xaa,
    0xa3,
    0xa4,
    0xf9,
    0xfe,
    0xf7,
    0xf0,
    0xe5,
    0xe2,
    0xeb,
    0xec,
    0xc1,
    0xc6,
    0xcf,
    0xc8,
    0xdd,
    0xda,
    0xd3,
    0xd4,
    0x69,
    0x6e,
    0x67,
    0x60,
    0x75,
    0x72,
    0x7b,
    0x7c,
    0x51,
    0x56,
    0x5f,
    0x58,
    0x4d,
    0x4a,
    0x43,
    0x44,
    0x19,
    0x1e,
    0x17,
    0x10,
    0x05,
    0x02,
    0x0b,
    0x0c,
    0x21,
    0x26,
    0x2f,
    0x28,
    0x3d,
    0x3a,
    0x33,
    0x34,
    0x4e,
    0x49,
    0x40,
    0x47,
    0x52,
    0x55,
    0x5c,
    0x5b,
    0x76,
    0x71,
    0x78,
    0x7f,
    0x6a,
    0x6d,
    0x64,
    0x63,
    0x3e,
    0x39,
    0x30,
    0x37,
    0x22,
    0x25,
    0x2c,
    0x2b,
    0x06,
    0x01,
    0x08,
    0x0f,
    0x1a,
    0x1d,
    0x14,
    0x13,
    0xae,
    0xa9,
    0xa0,
    0xa7,
    0xb2,
    0xb5,
    0xbc,
    0xbb,
    0x96,
    0x91,
    0x98,
    0x9f,
    0x8a,
    0x8d,
    0x84,
    0x83,
    0xde,
    0xd9,
    0xd0,
    0xd7,
    0xc2,
    0xc5,
    0xcc,
    0xcb,
    0xe6,
    0xe1,
    0xe8,
    0xef,
    0xfa,
    0xfd,
    0xf4,
    0xf3
  ];

  /// Calculate CRC8 checksum - ported from Python code
  static int calculateCrc8(List<int> data) {
    int crc = 0;
    for (int byte in data) {
      crc = crc8Table[(crc ^ byte) & 0xFF];
    }
    return crc & 0xFF;
  }

  /// Make command with proper format - ported from Python code
  static List<int> makeCommand(int command, List<int> data) {
    List<int> result = [];
    result.addAll([0x51, 0x78]); // Magic number
    result.add(command); // Command
    result.add(0x00); // Reserved
    result.add(data.length); // Data length
    result.add(0x00); // Reserved
    result.addAll(data); // Data
    result.add(calculateCrc8(data)); // CRC8 of data
    result.add(0xFF); // End marker
    return result;
  }

  /// Convert integer to bytes with specified length - ported from Python code
  static List<int> intToBytes(int value, {int length = 1}) {
    if (value < 0) throw ArgumentError('Value must be non-negative: $value');

    int maxValue = (1 << (length * 8)) - 1;
    if (value > maxValue)
      throw ArgumentError('Value too large: $value > $maxValue');

    List<int> bytes = List.filled(length, 0);
    for (int i = 0; i < length; i++) {
      bytes[i] = (value >> (i * 8)) & 0xFF;
    }
    return bytes;
  }

  /// Start print command - ported from Python code
  static List<int> getStartPrintCommand() {
    return makeCommand(startPrint, intToBytes(0x00));
  }

  /// Start print new command - ported from Python code
  static List<int> getStartPrintNewCommand() {
    List<int> command = makeCommand(startPrint, intToBytes(0x00));
    return [0x12] + command; // Add prefix for new printers
  }

  /// Apply energy command - ported from Python code
  static List<int> getApplyEnergyCommand() {
    return makeCommand(applyEnergy, intToBytes(0x01));
  }

  /// Get device state command - ported from Python code
  static List<int> getDeviceStateCommand() {
    return makeCommand(getDeviceState, intToBytes(0x00));
  }

  /// Set DPI as 200 command - ported from Python code
  static List<int> getSetDpiAs200Command() {
    return makeCommand(setDpiAs200, intToBytes(50));
  }

  /// Start lattice command - ported from Python code
  static List<int> getStartLatticeCommand() {
    return makeCommand(startLattice,
        [0xaa, 0x55, 0x17, 0x38, 0x44, 0x5f, 0x5f, 0x5f, 0x44, 0x38, 0x2c]);
  }

  /// End lattice command - ported from Python code
  static List<int> getEndLatticeCommand() {
    return makeCommand(endLattice,
        [0xaa, 0x55, 0x17, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x17]);
  }

  /// Set energy command - ported from Python code
  static List<int> getSetEnergyCommand(int amount) {
    return makeCommand(setEnergy, intToBytes(amount, length: 2));
  }

  /// Set speed command - ported from Python code
  static List<int> getSetSpeedCommand(int value) {
    return makeCommand(setSpeed, intToBytes(value));
  }

  /// Feed paper command - ported from Python code
  static List<int> getFeedPaperCommand(int pixels) {
    return makeCommand(feedPaper, intToBytes(pixels, length: 2));
  }

  /// Retract paper command - ported from Python code
  static List<int> getRetractPaperCommand(int pixels) {
    return makeCommand(retractPaper, intToBytes(pixels, length: 2));
  }

  /// Draw bitmap command - ported from Python code
  static List<int> getDrawBitmapCommand(List<int> bitmapData) {
    return makeCommand(drawBitmap, bitmapData);
  }

  // MXW01 specific command helpers (from C# implementation)
  /// Create MXW01 command format: [0x22, 0x21, commandId, 0x00, dataLength(2 bytes), data, crc8, 0xFF]
  static List<int> makeMXW01Command(int commandId, List<int> commandData) {
    List<int> command = List.filled(8 + commandData.length, 0);
    
    command[0] = 0x22;
    command[1] = 0x21;
    command[2] = commandId;
    command[3] = 0x00;
    command[4] = commandData.length & 0xFF; // Little endian
    command[5] = (commandData.length >> 8) & 0xFF;
    
    // Copy command data
    for (int i = 0; i < commandData.length; i++) {
      command[6 + i] = commandData[i];
    }
    
    // Calculate CRC8 for command data only
    command[6 + commandData.length] = crc8(commandData);
    command[7 + commandData.length] = 0xFF;
    
    return command;
  }

  /// Get status command for MXW01
  static List<int> getMXW01StatusCommand() {
    return makeMXW01Command(getStatus, [0x00]);
  }

  /// Get version command for MXW01
  static List<int> getMXW01VersionCommand() {
    return makeMXW01Command(getVersion, [0x00]);
  }

  /// Get battery level command for MXW01
  static List<int> getMXW01BatteryCommand() {
    return makeMXW01Command(batteryLevel, [0x00]);
  }

  /// Set print intensity command for MXW01
  static List<int> getMXW01PrintIntensityCommand(int intensity) {
    if (intensity > 100) intensity = 100;
    return makeMXW01Command(printIntensity, [intensity]);
  }

  /// Eject paper command for MXW01
  static List<int> getMXW01EjectPaperCommand(int lineCount) {
    return makeMXW01Command(ejectPaper, [
      lineCount & 0xFF,
      (lineCount >> 8) & 0xFF
    ]);
  }

  /// Retract paper command for MXW01
  static List<int> getMXW01RetractPaperCommand(int lineCount) {
    return makeMXW01Command(retractPaperMXW01, [
      lineCount & 0xFF,
      (lineCount >> 8) & 0xFF
    ]);
  }

  /// Print command for MXW01
  static List<int> getMXW01PrintCommand(int lineCount, int printMode) {
    return makeMXW01Command(print, [
      lineCount & 0xFF,
      (lineCount >> 8) & 0xFF,
      0x30,
      printMode
    ]);
  }

  /// Print data flush command for MXW01
  static List<int> getMXW01PrintDataFlushCommand() {
    return makeMXW01Command(printDataFlush, [0x00]);
  }

  /// Update device command - ported from Python code
  static List<int> getUpdateDeviceCommand() {
    return makeCommand(updateDevice, intToBytes(0x00));
  }

  /// Set quality command - ported from blog implementation
  static List<int> getSetQualityCommand(int quality) {
    return makeCommand(setQuality, intToBytes(quality));
  }

  /// Drawing mode command - ported from blog implementation
  static List<int> getDrawingModeCommand(int mode) {
    return makeCommand(drawingMode, intToBytes(mode));
  }

  /// CRC8 calculation function - ported from Python implementation
  static int crc8(List<int> data) {
    int crc = 0;
    for (int byte in data) {
      crc = crc8Table[(crc ^ byte) & 0xFF];
    }
    return crc & 0xFF;
  }
}
