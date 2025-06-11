import '../core/interfaces.dart';
import '../protocols/classic_protocol.dart';
import '../protocols/mxw01_protocol.dart';

/// Base class for all commands with common functionality
abstract class BaseCommand implements ICommand {
  @override
  bool validateParameters(Map<String, dynamic> params) {
    final schema = getParameterSchema();

    // Check required parameters
    for (String key in schema['required'] ?? []) {
      if (!params.containsKey(key)) {
        return false;
      }
    }

    // Validate parameter types and ranges
    final properties = schema['properties'] as Map<String, dynamic>? ?? {};
    for (String key in params.keys) {
      if (properties.containsKey(key)) {
        final property = properties[key] as Map<String, dynamic>;
        if (!_validateParameterValue(params[key], property)) {
          return false;
        }
      }
    }

    return true;
  }

  bool _validateParameterValue(dynamic value, Map<String, dynamic> property) {
    final type = property['type'] as String?;
    final minimum = property['minimum'] as num?;
    final maximum = property['maximum'] as num?;

    switch (type) {
      case 'integer':
        if (value is! int) return false;
        if (minimum != null && value < minimum) return false;
        if (maximum != null && value > maximum) return false;
        break;
      case 'number':
        if (value is! num) return false;
        if (minimum != null && value < minimum) return false;
        if (maximum != null && value > maximum) return false;
        break;
      case 'string':
        if (value is! String) return false;
        break;
      case 'boolean':
        if (value is! bool) return false;
        break;
      case 'array':
        if (value is! List) return false;
        break;
    }

    return true;
  }
}

/// Start print command for classic protocol
class StartPrintCommand extends BaseCommand {
  @override
  String get commandId => 'start_print';

  @override
  String get commandName => 'Start Print';

  @override
  List<String> get supportedModels => [
        'GB01',
        'GB02',
        'GB03',
        'GT01',
        'MX05',
        'MX06',
        'MX08',
        'MX09',
        'MX10',
        'YT01'
      ];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'control',
        'protocol': 'classic',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    return [0x00]; // Simple start print command
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {},
      'required': [],
    };
  }
}

/// Start print new command for newer classic printers
class StartPrintNewCommand extends BaseCommand {
  @override
  String get commandId => 'start_print_new';

  @override
  String get commandName => 'Start Print (New)';

  @override
  List<String> get supportedModels => ['GB03'];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'control',
        'protocol': 'classic',
        'uses_prefix': true,
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    return [0x00]; // Command data, prefix will be added by protocol
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {},
      'required': [],
    };
  }
}

/// Set energy command
class SetEnergyCommand extends BaseCommand {
  @override
  String get commandId => 'set_energy';

  @override
  String get commandName => 'Set Energy';

  @override
  List<String> get supportedModels => [
        'GB01',
        'GB02',
        'GB03',
        'GT01',
        'MX05',
        'MX06',
        'MX08',
        'MX09',
        'MX10',
        'YT01'
      ];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'configuration',
        'protocol': 'classic',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final energy = params['energy'] as int? ?? 4096;
    final protocol = ClassicProtocol();
    return protocol.intToBytes(energy, length: 2);
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'energy': {
          'type': 'integer',
          'minimum': 0,
          'maximum': 65535,
          'default': 4096,
          'description': 'Thermal energy amount (0-65535)',
        }
      },
      'required': ['energy'],
    };
  }
}

/// Set speed command
class SetSpeedCommand extends BaseCommand {
  @override
  String get commandId => 'set_speed';

  @override
  String get commandName => 'Set Speed';

  @override
  List<String> get supportedModels => [
        'GB01',
        'GB02',
        'GB03',
        'GT01',
        'MX05',
        'MX06',
        'MX08',
        'MX09',
        'MX10',
        'YT01'
      ];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'configuration',
        'protocol': 'classic',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final speed = params['speed'] as int? ?? 32;
    return [speed];
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'speed': {
          'type': 'integer',
          'minimum': 1,
          'maximum': 255,
          'default': 32,
          'description': 'Paper feed speed (lower = faster)',
        }
      },
      'required': ['speed'],
    };
  }
}

/// Draw bitmap command
class DrawBitmapCommand extends BaseCommand {
  @override
  String get commandId => 'draw_bitmap';

  @override
  String get commandName => 'Draw Bitmap';

  @override
  List<String> get supportedModels => [
        'GB01',
        'GB02',
        'GB03',
        'GT01',
        'MX05',
        'MX06',
        'MX08',
        'MX09',
        'MX10',
        'YT01'
      ];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'printing',
        'protocol': 'classic',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final bitmapData = params['bitmap_data'] as List<int>? ?? [];
    final protocol = ClassicProtocol();

    // Reverse bits for each byte as required by protocol
    return bitmapData.map((byte) => protocol.reverseBits(byte)).toList();
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'bitmap_data': {
          'type': 'array',
          'items': {'type': 'integer', 'minimum': 0, 'maximum': 255},
          'description': 'Bitmap data as byte array',
        }
      },
      'required': ['bitmap_data'],
    };
  }
}

/// Feed paper command
class FeedPaperCommand extends BaseCommand {
  @override
  String get commandId => 'feed_paper';

  @override
  String get commandName => 'Feed Paper';

  @override
  List<String> get supportedModels => [
        'GB01', 'GB02', 'GB03', 'GT01', 'YT01'
        // Note: MX models excluded due to feeding problems
      ];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'paper_control',
        'protocol': 'classic',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final pixels = params['pixels'] as int? ?? 10;
    final protocol = ClassicProtocol();
    return protocol.intToBytes(pixels, length: 2);
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'pixels': {
          'type': 'integer',
          'minimum': 1,
          'maximum': 65535,
          'default': 10,
          'description': 'Number of pixels to feed',
        }
      },
      'required': ['pixels'],
    };
  }
}

/// Retract paper command
class RetractPaperCommand extends BaseCommand {
  @override
  String get commandId => 'retract_paper';

  @override
  String get commandName => 'Retract Paper';

  @override
  List<String> get supportedModels => [
        'GB01',
        'GB02',
        'GB03',
        'GT01',
        'MX05',
        'MX06',
        'MX08',
        'MX09',
        'MX10',
        'YT01'
      ];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'paper_control',
        'protocol': 'classic',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final pixels = params['pixels'] as int? ?? 10;
    final protocol = ClassicProtocol();
    return protocol.intToBytes(pixels, length: 2);
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'pixels': {
          'type': 'integer',
          'minimum': 1,
          'maximum': 65535,
          'default': 10,
          'description': 'Number of pixels to retract',
        }
      },
      'required': ['pixels'],
    };
  }
}

/// MXW01 Get Status command
class MXW01GetStatusCommand extends BaseCommand {
  @override
  String get commandId => 'get_status';

  @override
  String get commandName => 'Get Status';

  @override
  List<String> get supportedModels => ['MXW01'];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'status',
        'protocol': 'mxw01',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    return [0x00];
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {},
      'required': [],
    };
  }
}

/// MXW01 Print Intensity command
class MXW01PrintIntensityCommand extends BaseCommand {
  @override
  String get commandId => 'print_intensity';

  @override
  String get commandName => 'Set Print Intensity';

  @override
  List<String> get supportedModels => ['MXW01'];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'configuration',
        'protocol': 'mxw01',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final intensity = params['intensity'] as int? ?? 50;
    final clampedIntensity = intensity.clamp(1, 100);
    return [clampedIntensity];
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'intensity': {
          'type': 'integer',
          'minimum': 1,
          'maximum': 100,
          'default': 50,
          'description': 'Print intensity (1-100)',
        }
      },
      'required': ['intensity'],
    };
  }
}

/// MXW01 Battery Level command
class MXW01BatteryLevelCommand extends BaseCommand {
  @override
  String get commandId => 'battery_level';

  @override
  String get commandName => 'Get Battery Level';

  @override
  List<String> get supportedModels => ['MXW01'];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'status',
        'protocol': 'mxw01',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    return [0x00];
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {},
      'required': [],
    };
  }
}

/// MXW01 Print command
class MXW01PrintCommand extends BaseCommand {
  @override
  String get commandId => 'print';

  @override
  String get commandName => 'Print';

  @override
  List<String> get supportedModels => ['MXW01'];

  @override
  Map<String, dynamic> get parameters => {
        'category': 'printing',
        'protocol': 'mxw01',
      };

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    final lineCount = params['line_count'] as int? ?? 1;
    final printMode = params['print_mode'] as int? ?? 0; // 0 = monochrome

    final protocol = MXW01Protocol();
    List<int> data = [];
    data.addAll(protocol.intToLittleEndianBytes(lineCount, 2));
    data.add(0x30); // Fixed byte
    data.add(printMode);

    return data;
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return {
      'type': 'object',
      'properties': {
        'line_count': {
          'type': 'integer',
          'minimum': 1,
          'maximum': 65535,
          'description': 'Number of lines to print',
        },
        'print_mode': {
          'type': 'integer',
          'minimum': 0,
          'maximum': 2,
          'default': 0,
          'description': 'Print mode (0=monochrome, 1=unknown, 2=grayscale)',
        }
      },
      'required': ['line_count'],
    };
  }
}

/// Template for creating custom commands
/// Developers can use this to easily create new commands
class CustomCommand extends BaseCommand {
  final String _commandId;
  final String _commandName;
  final List<String> _supportedModels;
  final Map<String, dynamic> _parameters;
  final Map<String, dynamic> _parameterSchema;
  final List<int> Function(Map<String, dynamic>) _dataGenerator;

  CustomCommand({
    required String commandId,
    required String commandName,
    required List<String> supportedModels,
    required Map<String, dynamic> parameters,
    required Map<String, dynamic> parameterSchema,
    required List<int> Function(Map<String, dynamic>) dataGenerator,
  })  : _commandId = commandId,
        _commandName = commandName,
        _supportedModels = supportedModels,
        _parameters = parameters,
        _parameterSchema = parameterSchema,
        _dataGenerator = dataGenerator;

  @override
  String get commandId => _commandId;

  @override
  String get commandName => _commandName;

  @override
  List<String> get supportedModels => List.from(_supportedModels);

  @override
  Map<String, dynamic> get parameters => Map.from(_parameters);

  @override
  List<int> createCommandData(Map<String, dynamic> params) {
    return _dataGenerator(params);
  }

  @override
  Map<String, dynamic> getParameterSchema() {
    return Map.from(_parameterSchema);
  }
}
