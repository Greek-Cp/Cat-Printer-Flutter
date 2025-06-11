import '../core/interfaces.dart';

/// Base class for all printer models with common functionality
abstract class BasePrinterModel implements IPrinterModel {
  @override
  Map<String, dynamic> getConfiguration() {
    return {
      'model_name': modelName,
      'paper_width': paperWidth,
      'supports_new_commands': supportsNewCommands,
      'has_feeding_problems': hasFeedingProblems,
      'protocol_type': protocolType,
    };
  }

  @override
  Map<String, dynamic> getConstraints() {
    return {
      'max_energy': 0xFFFF,
      'max_speed': 255,
      'max_quality': 10,
      'min_energy': 0,
      'min_speed': 1,
      'min_quality': 1,
    };
  }
}

/// Classic GB01 model implementation
class GB01Model extends BasePrinterModel {
  @override
  String get modelName => 'GB01';

  @override
  String get modelVersion => '1.0';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => false;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      'feed_paper',
      'draw_bitmap',
      'get_device_state',
      'get_device_info',
      'update_device',
    ];
  }
}

/// Enhanced GB02 model implementation
class GB02Model extends BasePrinterModel {
  @override
  String get modelName => 'GB02';

  @override
  String get modelVersion => '1.1';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => false;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'drawing_mode',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      'feed_paper',
      'draw_bitmap',
      'get_device_state',
      'get_device_info',
      'update_device',
    ];
  }
}

/// Advanced GB03 model with new command support
class GB03Model extends BasePrinterModel {
  @override
  String get modelName => 'GB03';

  @override
  String get modelVersion => '2.0';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => true;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'start_print_new',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'drawing_mode',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      'feed_paper',
      'draw_bitmap',
      'get_device_state',
      'get_device_info',
      'update_device',
    ];
  }

  @override
  Map<String, dynamic> getConstraints() {
    var constraints = super.getConstraints();
    constraints['supports_compression'] = true;
    constraints['max_quality'] = 15; // Enhanced quality range
    return constraints;
  }
}

/// GT01 model implementation
class GT01Model extends BasePrinterModel {
  @override
  String get modelName => 'GT01';

  @override
  String get modelVersion => '1.0';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => false;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      'feed_paper',
      'draw_bitmap',
      'get_device_state',
    ];
  }
}

/// MX05 model with feeding problems
class MX05Model extends BasePrinterModel {
  @override
  String get modelName => 'MX05';

  @override
  String get modelVersion => '1.0';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => false;

  @override
  bool get hasFeedingProblems => true;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      // Note: feed_paper is problematic for this model
      'draw_bitmap',
      'get_device_state',
    ];
  }

  @override
  Map<String, dynamic> getConstraints() {
    var constraints = super.getConstraints();
    constraints['feeding_issues'] = true;
    constraints['recommended_speed'] = 32; // Slower speed for stability
    return constraints;
  }
}

/// MX06 model with feeding problems
class MX06Model extends BasePrinterModel {
  @override
  String get modelName => 'MX06';

  @override
  String get modelVersion => '1.1';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => false;

  @override
  bool get hasFeedingProblems => true;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      'draw_bitmap',
      'get_device_state',
    ];
  }

  @override
  Map<String, dynamic> getConstraints() {
    var constraints = super.getConstraints();
    constraints['feeding_issues'] = true;
    constraints['recommended_speed'] = 32;
    return constraints;
  }
}

/// Advanced MXW01 model with BLE protocol
class MXW01Model extends BasePrinterModel {
  @override
  String get modelName => 'MXW01';

  @override
  String get modelVersion => '3.0';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => true;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'mxw01';

  @override
  List<String> getSupportedCommands() {
    return [
      'get_status',
      'print_intensity',
      'eject_paper',
      'retract_paper',
      'query_count',
      'print',
      'print_complete',
      'battery_level',
      'cancel_print',
      'print_data_flush',
      'get_print_type',
      'get_version',
    ];
  }

  @override
  Map<String, dynamic> getConfiguration() {
    var config = super.getConfiguration();
    config['has_battery'] = true;
    config['has_temperature_sensor'] = true;
    config['supports_grayscale'] = true;
    config['supports_intensity_control'] = true;
    return config;
  }

  @override
  Map<String, dynamic> getConstraints() {
    return {
      'max_intensity': 100,
      'min_intensity': 1,
      'max_line_count': 65535,
      'min_line_count': 1,
      'print_modes': ['monochrome', 'grayscale'],
      'has_battery_monitoring': true,
      'has_status_reporting': true,
    };
  }
}

/// YT01 model implementation
class YT01Model extends BasePrinterModel {
  @override
  String get modelName => 'YT01';

  @override
  String get modelVersion => '1.0';

  @override
  int get paperWidth => 384;

  @override
  bool get supportsNewCommands => false;

  @override
  bool get hasFeedingProblems => false;

  @override
  String get protocolType => 'classic';

  @override
  List<String> getSupportedCommands() {
    return [
      'start_print',
      'set_energy',
      'set_speed',
      'apply_energy',
      'set_quality',
      'start_lattice',
      'end_lattice',
      'retract_paper',
      'feed_paper',
      'draw_bitmap',
      'get_device_state',
    ];
  }
}

/// Template for creating new printer models
/// Developers can extend this class to add new models easily
class TemplatePrinterModel extends BasePrinterModel {
  final String _modelName;
  final String _modelVersion;
  final int _paperWidth;
  final bool _supportsNewCommands;
  final bool _hasFeedingProblems;
  final String _protocolType;
  final List<String> _supportedCommands;
  final Map<String, dynamic>? _customConfiguration;
  final Map<String, dynamic>? _customConstraints;

  TemplatePrinterModel({
    required String modelName,
    required String modelVersion,
    required int paperWidth,
    required bool supportsNewCommands,
    required bool hasFeedingProblems,
    required String protocolType,
    required List<String> supportedCommands,
    Map<String, dynamic>? customConfiguration,
    Map<String, dynamic>? customConstraints,
  })  : _modelName = modelName,
        _modelVersion = modelVersion,
        _paperWidth = paperWidth,
        _supportsNewCommands = supportsNewCommands,
        _hasFeedingProblems = hasFeedingProblems,
        _protocolType = protocolType,
        _supportedCommands = supportedCommands,
        _customConfiguration = customConfiguration,
        _customConstraints = customConstraints;

  @override
  String get modelName => _modelName;

  @override
  String get modelVersion => _modelVersion;

  @override
  int get paperWidth => _paperWidth;

  @override
  bool get supportsNewCommands => _supportsNewCommands;

  @override
  bool get hasFeedingProblems => _hasFeedingProblems;

  @override
  String get protocolType => _protocolType;

  @override
  List<String> getSupportedCommands() => List.from(_supportedCommands);

  @override
  Map<String, dynamic> getConfiguration() {
    var config = super.getConfiguration();
    if (_customConfiguration != null) {
      config.addAll(_customConfiguration!);
    }
    return config;
  }

  @override
  Map<String, dynamic> getConstraints() {
    var constraints = super.getConstraints();
    if (_customConstraints != null) {
      constraints.addAll(_customConstraints!);
    }
    return constraints;
  }
}

/// Model detector for classic protocol
class ClassicModelDetector implements IModelDetector {
  @override
  Future<IPrinterModel?> detectModel(IProtocolHandler handler) async {
    // Implementation would involve sending detection commands
    // and analyzing responses to determine the exact model
    // This is a simplified placeholder
    return null; // Would return detected model
  }

  @override
  bool canDetect(String protocolType) {
    return protocolType == 'classic';
  }
}

/// Model detector for MXW01 protocol
class MXW01ModelDetector implements IModelDetector {
  @override
  Future<IPrinterModel?> detectModel(IProtocolHandler handler) async {
    // Implementation would send version command and parse response
    // to confirm it's an MXW01 model
    return MXW01Model();
  }

  @override
  bool canDetect(String protocolType) {
    return protocolType == 'mxw01';
  }
}
