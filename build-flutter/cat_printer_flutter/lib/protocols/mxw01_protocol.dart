import '../core/interfaces.dart';

/// MXW01 Printer Protocol (0x22 0x21 format)
/// Used by newer MXW01 model with enhanced features
class MXW01Protocol implements IPrinterProtocol {
  @override
  String get protocolName => 'MXW01 BLE Protocol';

  @override
  String get protocolVersion => '2.0';

  // CRC8 table for checksum calculation (same as classic)
  static const List<int> _crc8Table = [
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

  // Protocol constants
  static const int _magicByte1 = 0x22;
  static const int _magicByte2 = 0x21;
  static const int _endMarker = 0xff;

  @override
  List<int> createCommand(String commandId, List<int> data) {
    final commandByte = _getCommandByte(commandId);
    return _makeCommand(commandByte, data);
  }

  @override
  Map<String, dynamic> parseResponse(List<int> response) {
    if (!isValidResponse(response)) {
      return {'error': 'Invalid response format'};
    }

    if (response.length < 8) {
      return {'error': 'Response too short'};
    }

    final commandId = response[2];
    final dataLength = response[4] | (response[5] << 8);
    final responseData = response.length > 6 + dataLength
        ? response.sublist(6, 6 + dataLength)
        : <int>[];

    return {
      'type': 'mxw01_response',
      'command_id': commandId,
      'command_name': _getCommandName(commandId),
      'data_length': dataLength,
      'data': responseData,
      'raw': response,
      'parsed': _parseSpecificResponse(commandId, responseData),
    };
  }

  @override
  List<String> getRequiredCharacteristics() {
    return [
      '0000ae01-0000-1000-8000-00805f9b34fb', // TX characteristic
      '0000ae02-0000-1000-8000-00805f9b34fb', // RX characteristic (notifications)
      '0000ae03-0000-1000-8000-00805f9b34fb', // Data characteristic (image data)
    ];
  }

  @override
  bool isValidResponse(List<int> response) {
    if (response.length < 8) return false;
    return response[0] == _magicByte1 &&
        response[1] == _magicByte2 &&
        response[response.length - 1] == _endMarker;
  }

  /// Calculate CRC8 checksum
  int _calculateCrc8(List<int> data) {
    int crc = 0;
    for (int byte in data) {
      crc = _crc8Table[(crc ^ byte) & 0xFF];
    }
    return crc & 0xFF;
  }

  /// Create MXW01 command format: [0x22, 0x21, commandId, 0x00, dataLength(2 bytes), data, crc8, 0xFF]
  List<int> _makeCommand(int commandByte, List<int> data) {
    List<int> command = List.filled(8 + data.length, 0);

    command[0] = _magicByte1;
    command[1] = _magicByte2;
    command[2] = commandByte;
    command[3] = 0x00;
    command[4] = data.length & 0xFF; // Little endian
    command[5] = (data.length >> 8) & 0xFF;

    // Copy command data
    for (int i = 0; i < data.length; i++) {
      command[6 + i] = data[i];
    }

    // Calculate CRC8 for command data only
    command[6 + data.length] = _calculateCrc8(data);
    command[7 + data.length] = _endMarker;

    return command;
  }

  /// Get command byte from command ID
  int _getCommandByte(String commandId) {
    const commandMap = {
      'get_status': 0xa1,
      'print_intensity': 0xa2,
      'eject_paper': 0xa3,
      'retract_paper': 0xa4,
      'query_count': 0xa7,
      'print': 0xa9,
      'print_complete': 0xaa,
      'battery_level': 0xab,
      'cancel_print': 0xac,
      'print_data_flush': 0xad,
      'unknown_ae': 0xae,
      'get_print_type': 0xb0,
      'get_version': 0xb1,
      'unknown_b2': 0xb2,
      'unknown_b3': 0xb3,
    };

    final commandByte = commandMap[commandId];
    if (commandByte == null) {
      throw ArgumentError('Unknown MXW01 command ID: $commandId');
    }

    return commandByte;
  }

  /// Get command name from command byte
  String _getCommandName(int commandByte) {
    const commandNames = {
      0xa1: 'get_status',
      0xa2: 'print_intensity',
      0xa3: 'eject_paper',
      0xa4: 'retract_paper',
      0xa7: 'query_count',
      0xa9: 'print',
      0xaa: 'print_complete',
      0xab: 'battery_level',
      0xac: 'cancel_print',
      0xad: 'print_data_flush',
      0xae: 'unknown_ae',
      0xb0: 'get_print_type',
      0xb1: 'get_version',
      0xb2: 'unknown_b2',
      0xb3: 'unknown_b3',
    };

    return commandNames[commandByte] ?? 'unknown_command';
  }

  /// Parse specific response based on command
  Map<String, dynamic> _parseSpecificResponse(int commandId, List<int> data) {
    switch (commandId) {
      case 0xa1: // Get Status
        return _parseStatusResponse(data);
      case 0xab: // Battery Level
        return _parseBatteryResponse(data);
      case 0xb1: // Get Version
        return _parseVersionResponse(data);
      case 0xb0: // Get Print Type
        return _parsePrintTypeResponse(data);
      case 0xa7: // Query Count
        return _parseQueryCountResponse(data);
      case 0xa9: // Print
        return _parsePrintResponse(data);
      case 0xaa: // Print Complete
        return {'status': 'print_completed'};
      case 0xa3: // Eject Paper
        return {'status': 'ejecting_paper'};
      case 0xa4: // Retract Paper
        return {'status': 'retracting_paper'};
      default:
        return {'raw_data': data};
    }
  }

  /// Parse status response
  Map<String, dynamic> _parseStatusResponse(List<int> data) {
    if (data.length < 7) return {'error': 'Invalid status data'};

    final batteryLevel = data[3];
    final temperature = data[4];
    final statusOk = data[6] == 0;

    String statusDetails = '';
    if (statusOk) {
      switch (data[0]) {
        case 0x0:
          statusDetails = 'Standby';
          break;
        case 0x1:
          statusDetails = 'Printing';
          break;
        case 0x2:
          statusDetails = 'Feeding paper';
          break;
        case 0x3:
          statusDetails = 'Ejecting paper';
          break;
        default:
          statusDetails = 'Unknown status';
          break;
      }
    } else {
      switch (data[7]) {
        case 0x1:
        case 0x9:
          statusDetails = 'No paper';
          break;
        case 0x4:
          statusDetails = 'Overheated';
          break;
        case 0x8:
          statusDetails = 'Low battery';
          break;
        default:
          statusDetails = 'Unknown error';
          break;
      }
    }

    return {
      'status_ok': statusOk,
      'status_details': statusDetails,
      'battery_level': batteryLevel,
      'temperature': temperature,
    };
  }

  /// Parse battery response
  Map<String, dynamic> _parseBatteryResponse(List<int> data) {
    if (data.isEmpty) return {'error': 'No battery data'};
    return {'battery_level': data[0]};
  }

  /// Parse version response
  Map<String, dynamic> _parseVersionResponse(List<int> data) {
    if (data.length < 9) return {'error': 'Invalid version data'};

    final version = String.fromCharCodes(data.sublist(0, data.length - 1));
    final printType = data[8];

    String typeDescription;
    switch (printType) {
      case 0x32:
        typeDescription = 'High pressure/density';
        break;
      case 0x31:
        typeDescription = 'Low pressure/density';
        break;
      default:
        typeDescription = 'Unknown type';
        break;
    }

    return {
      'version': version,
      'print_type': printType,
      'type_description': typeDescription,
    };
  }

  /// Parse print type response
  Map<String, dynamic> _parsePrintTypeResponse(List<int> data) {
    if (data.isEmpty) return {'error': 'No print type data'};

    String type;
    switch (data[0]) {
      case 0x01:
        type = 'High pressure/voltage/density';
        break;
      case 0xFF:
        type = 'Unrecognized';
        break;
      default:
        type = 'Low pressure/voltage/density';
        break;
    }

    return {'print_type': data[0], 'description': type};
  }

  /// Parse query count response
  Map<String, dynamic> _parseQueryCountResponse(List<int> data) {
    return {'query_count': data};
  }

  /// Parse print response
  Map<String, dynamic> _parsePrintResponse(List<int> data) {
    if (data.isEmpty) return {'error': 'No print response data'};
    final printStatusOk = data[0] == 0;
    return {
      'print_status_ok': printStatusOk,
      'status': printStatusOk ? 'success' : 'failure',
    };
  }

  /// Convert integer to little-endian bytes
  List<int> intToLittleEndianBytes(int value, int length) {
    List<int> bytes = List.filled(length, 0);
    for (int i = 0; i < length; i++) {
      bytes[i] = (value >> (i * 8)) & 0xFF;
    }
    return bytes;
  }
}
