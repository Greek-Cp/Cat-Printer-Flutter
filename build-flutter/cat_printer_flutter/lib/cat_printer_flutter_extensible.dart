// ignore_for_file: library_private_types_in_public_api
///
/// Extensible Cat Printer Flutter Library
///
/// This is the new extensible version that makes it easy for developers
/// to add new printer models, protocols, and commands.
///
/// Key Features:
/// - Easy model registration
/// - Protocol extensibility
/// - Command system
/// - Auto-detection
/// - Type-safe APIs
///
/// Usage:
/// ```dart
/// // Initialize the library
/// CatPrinterExtensible.initialize();
///
/// // Add a new model
/// CatPrinterExtensible.registerModel('NEW01', () => NewPrinterModel());
///
/// // Add a new command
/// CatPrinterExtensible.registerCommand('custom_command', () => CustomCommand());
///
/// // Use the printer
/// final service = await CatPrinterExtensible.createPrinterService('MXW01');
/// await service.connect(deviceId);
/// await service.executeCommand('print_intensity', {'intensity': 75});
/// ```
library cat_printer_flutter_extensible;

// Export core interfaces
export 'core/interfaces.dart';
export 'core/registry.dart';

// Export protocol implementations
export 'protocols/classic_protocol.dart';
export 'protocols/mxw01_protocol.dart';

// Export model implementations
export 'models/extensible_printer_models.dart';

// Export command implementations
export 'commands/extensible_commands.dart';

import 'core/interfaces.dart';
import 'core/registry.dart';
import 'protocols/classic_protocol.dart';
import 'protocols/mxw01_protocol.dart';
import 'models/extensible_printer_models.dart';
import 'commands/extensible_commands.dart';

/// Main class for the extensible Cat Printer library
class CatPrinterExtensible {
  static bool _initialized = false;

  /// Initialize the library with all built-in models, protocols, and commands
  static void initialize() {
    if (_initialized) return;

    _registerBuiltInProtocols();
    _registerBuiltInModels();
    _registerBuiltInCommands();

    _initialized = true;
  }

  /// Register built-in protocols
  static void _registerBuiltInProtocols() {
    final protocolRegistry = ProtocolRegistry();

    protocolRegistry.registerProtocol('classic', () => ClassicProtocol());
    protocolRegistry.registerProtocol('mxw01', () => MXW01Protocol());
  }

  /// Register built-in models
  static void _registerBuiltInModels() {
    final modelRegistry = ModelRegistry();

    // Register classic models
    modelRegistry.registerModel('GB01', () => GB01Model());
    modelRegistry.registerModel('GB02', () => GB02Model());
    modelRegistry.registerModel('GB03', () => GB03Model());
    modelRegistry.registerModel('GT01', () => GT01Model());
    modelRegistry.registerModel('MX05', () => MX05Model());
    modelRegistry.registerModel('MX06', () => MX06Model());
    modelRegistry.registerModel('YT01', () => YT01Model());

    // Register MXW01 model
    modelRegistry.registerModel('MXW01', () => MXW01Model());

    // Register model detectors
    modelRegistry.registerDetector('classic', ClassicModelDetector());
    modelRegistry.registerDetector('mxw01', MXW01ModelDetector());
  }

  /// Register built-in commands
  static void _registerBuiltInCommands() {
    final commandRegistry = CommandRegistry();

    // Classic protocol commands
    commandRegistry.registerCommand('start_print', () => StartPrintCommand());
    commandRegistry.registerCommand('set_energy', () => SetEnergyCommand());

    // MXW01 protocol commands
    commandRegistry.registerCommand(
        'print_intensity', () => MXW01PrintIntensityCommand());
  }

  /// Register a new printer model
  ///
  /// Example:
  /// ```dart
  /// CatPrinterExtensible.registerModel('NEW01', () => MyNewPrinterModel());
  /// ```
  static void registerModel(
      String modelName, IPrinterModel Function() factory) {
    _ensureInitialized();
    ModelRegistry().registerModel(modelName, factory);
  }

  /// Register a new protocol
  ///
  /// Example:
  /// ```dart
  /// CatPrinterExtensible.registerProtocol('new_protocol', () => MyNewProtocol());
  /// ```
  static void registerProtocol(
      String protocolType, IPrinterProtocol Function() factory) {
    _ensureInitialized();
    ProtocolRegistry().registerProtocol(protocolType, factory);
  }

  /// Register a new command
  ///
  /// Example:
  /// ```dart
  /// CatPrinterExtensible.registerCommand('my_command', () => MyCustomCommand());
  /// ```
  static void registerCommand(String commandId, ICommand Function() factory) {
    _ensureInitialized();
    CommandRegistry().registerCommand(commandId, factory);
  }

  /// Register a model detector for a protocol
  static void registerModelDetector(
      String protocolType, IModelDetector detector) {
    _ensureInitialized();
    ModelRegistry().registerDetector(protocolType, detector);
  }

  /// Create a printer service for a specific model
  ///
  /// Example:
  /// ```dart
  /// final service = await CatPrinterExtensible.createPrinterService('MXW01');
  /// ```
  static Future<IPrinterService?> createPrinterService(String modelName) async {
    _ensureInitialized();
    return await PrinterFactory().createPrinterService(modelName);
  }

  /// Auto-detect printer model and create service
  ///
  /// Example:
  /// ```dart
  /// final service = await CatPrinterExtensible.autoDetectPrinter(protocolHandler);
  /// ```
  static Future<IPrinterService?> autoDetectPrinter(
      IProtocolHandler handler) async {
    _ensureInitialized();
    return await PrinterFactory().autoDetectPrinter(handler);
  }

  /// Get all available printer models
  static List<IPrinterModel> getAvailableModels() {
    _ensureInitialized();
    return PrinterFactory().getAvailableModels();
  }

  /// Get all available commands for a specific model
  static List<ICommand> getAvailableCommands(String modelName) {
    _ensureInitialized();
    return CommandFactory().getAvailableCommands(modelName);
  }

  /// Get commands by category
  static List<String> getCommandsByCategory(String category) {
    _ensureInitialized();
    return CommandRegistry().getCommandsByCategory(category);
  }

  /// Execute a command with validation
  static Future<Map<String, dynamic>> executeCommand(
    String commandId,
    Map<String, dynamic> parameters,
    IProtocolHandler handler,
  ) async {
    _ensureInitialized();
    return await CommandFactory()
        .executeCommand(commandId, parameters, handler);
  }

  /// Check if a command is supported by a model
  static bool isCommandSupported(String commandId, String modelName) {
    _ensureInitialized();
    return CommandRegistry().isCommandSupportedByModel(commandId, modelName);
  }

  /// Get information about a specific model
  static Map<String, dynamic>? getModelInfo(String modelName) {
    _ensureInitialized();
    final model = ModelRegistry().createModel(modelName);
    return model?.getConfiguration();
  }

  /// Get constraints for a specific model
  static Map<String, dynamic>? getModelConstraints(String modelName) {
    _ensureInitialized();
    final model = ModelRegistry().createModel(modelName);
    return model?.getConstraints();
  }

  /// Get parameter schema for a command
  static Map<String, dynamic>? getCommandSchema(String commandId) {
    _ensureInitialized();
    final command = CommandRegistry().createCommand(commandId);
    return command?.getParameterSchema();
  }

  /// Validate command parameters
  static bool validateCommandParameters(
      String commandId, Map<String, dynamic> parameters) {
    _ensureInitialized();
    final command = CommandRegistry().createCommand(commandId);
    return command?.validateParameters(parameters) ?? false;
  }

  /// Create a custom model using the template
  ///
  /// Example:
  /// ```dart
  /// final model = CatPrinterExtensible.createCustomModel(
  ///   modelName: 'CUSTOM01',
  ///   modelVersion: '1.0',
  ///   paperWidth: 384,
  ///   protocolType: 'classic',
  ///   supportedCommands: ['start_print', 'set_energy'],
  /// );
  /// ```
  static IPrinterModel createCustomModel({
    required String modelName,
    required String modelVersion,
    required int paperWidth,
    required String protocolType,
    required List<String> supportedCommands,
    bool supportsNewCommands = false,
    bool hasFeedingProblems = false,
    Map<String, dynamic>? customConfiguration,
    Map<String, dynamic>? customConstraints,
  }) {
    return TemplatePrinterModel(
      modelName: modelName,
      modelVersion: modelVersion,
      paperWidth: paperWidth,
      supportsNewCommands: supportsNewCommands,
      hasFeedingProblems: hasFeedingProblems,
      protocolType: protocolType,
      supportedCommands: supportedCommands,
      customConfiguration: customConfiguration,
      customConstraints: customConstraints,
    );
  }

  /// Create a custom command using the template
  ///
  /// Example:
  /// ```dart
  /// final command = CatPrinterExtensible.createCustomCommand(
  ///   commandId: 'my_command',
  ///   commandName: 'My Custom Command',
  ///   supportedModels: ['GB01', 'GB02'],
  ///   parameters: {'category': 'custom'},
  ///   parameterSchema: {...},
  ///   dataGenerator: (params) => [0x01, 0x02],
  /// );
  /// ```
  static ICommand createCustomCommand({
    required String commandId,
    required String commandName,
    required List<String> supportedModels,
    required Map<String, dynamic> parameters,
    required Map<String, dynamic> parameterSchema,
    required List<int> Function(Map<String, dynamic>) dataGenerator,
  }) {
    return CustomCommand(
      commandId: commandId,
      commandName: commandName,
      supportedModels: supportedModels,
      parameters: parameters,
      parameterSchema: parameterSchema,
      dataGenerator: dataGenerator,
    );
  }

  /// Get library information
  static Map<String, dynamic> getLibraryInfo() {
    _ensureInitialized();

    final modelRegistry = ModelRegistry();
    final protocolRegistry = ProtocolRegistry();
    final commandRegistry = CommandRegistry();

    return {
      'version': '2.0.0-extensible',
      'initialized': _initialized,
      'registered_models': modelRegistry.getRegisteredModels(),
      'registered_protocols': protocolRegistry.getRegisteredProtocols(),
      'registered_commands': commandRegistry.getRegisteredCommands(),
      'features': [
        'extensible_models',
        'extensible_protocols',
        'extensible_commands',
        'auto_detection',
        'parameter_validation',
        'type_safety',
      ],
    };
  }

  static void _ensureInitialized() {
    if (!_initialized) {
      initialize();
    }
  }
}

/// Helper class for easy printer operations
class EasyPrinter {
  final IPrinterService _service;

  EasyPrinter._(this._service);

  /// Create an easy printer instance for a specific model
  static Future<EasyPrinter?> create(String modelName) async {
    final service = await CatPrinterExtensible.createPrinterService(modelName);
    return service != null ? EasyPrinter._(service) : null;
  }

  /// Connect to a device
  Future<bool> connect(String deviceId) => _service.connect(deviceId);

  /// Disconnect from device
  Future<void> disconnect() => _service.disconnect();

  /// Check if connected
  bool get isConnected => _service.isConnected;

  /// Get current model
  IPrinterModel? get model => _service.currentModel;

  /// Execute a command with simplified API
  Future<void> execute(String command, [Map<String, dynamic>? params]) {
    return _service.executeCommand(command, params);
  }

  /// Get available commands for current model
  List<ICommand> get availableCommands {
    final modelName = _service.currentModel?.modelName;
    return modelName != null
        ? CatPrinterExtensible.getAvailableCommands(modelName)
        : [];
  }
}
