import 'interfaces.dart';

/// Registry for managing printer models
class ModelRegistry {
  static final ModelRegistry _instance = ModelRegistry._internal();
  factory ModelRegistry() => _instance;
  ModelRegistry._internal();

  final Map<String, IPrinterModel Function()> _modelFactories = {};
  final Map<String, IModelDetector> _modelDetectors = {};

  /// Register a new printer model
  void registerModel(String modelName, IPrinterModel Function() factory) {
    _modelFactories[modelName] = factory;
  }

  /// Register a model detector
  void registerDetector(String protocolType, IModelDetector detector) {
    _modelDetectors[protocolType] = detector;
  }

  /// Create model instance by name
  IPrinterModel? createModel(String modelName) {
    final factory = _modelFactories[modelName];
    return factory?.call();
  }

  /// Get all registered models
  List<String> getRegisteredModels() {
    return _modelFactories.keys.toList();
  }

  /// Get model detector for protocol type
  IModelDetector? getDetector(String protocolType) {
    return _modelDetectors[protocolType];
  }

  /// Detect model from protocol handler
  Future<IPrinterModel?> detectModel(
      IProtocolHandler handler, String protocolType) async {
    final detector = _modelDetectors[protocolType];
    return await detector?.detectModel(handler);
  }
}

/// Registry for managing printer protocols
class ProtocolRegistry {
  static final ProtocolRegistry _instance = ProtocolRegistry._internal();
  factory ProtocolRegistry() => _instance;
  ProtocolRegistry._internal();

  final Map<String, IPrinterProtocol Function()> _protocolFactories = {};

  /// Register a new protocol
  void registerProtocol(
      String protocolType, IPrinterProtocol Function() factory) {
    _protocolFactories[protocolType] = factory;
  }

  /// Create protocol instance by type
  IPrinterProtocol? createProtocol(String protocolType) {
    final factory = _protocolFactories[protocolType];
    return factory?.call();
  }

  /// Get all registered protocols
  List<String> getRegisteredProtocols() {
    return _protocolFactories.keys.toList();
  }

  /// Check if protocol is registered
  bool isProtocolRegistered(String protocolType) {
    return _protocolFactories.containsKey(protocolType);
  }
}

/// Registry for managing commands
class CommandRegistry {
  static final CommandRegistry _instance = CommandRegistry._internal();
  factory CommandRegistry() => _instance;
  CommandRegistry._internal();

  final Map<String, ICommand Function()> _commandFactories = {};
  final Map<String, List<String>> _modelCommands = {};

  /// Register a new command
  void registerCommand(String commandId, ICommand Function() factory) {
    _commandFactories[commandId] = factory;

    // Update model-command mapping
    final command = factory();
    for (String model in command.supportedModels) {
      _modelCommands.putIfAbsent(model, () => []).add(commandId);
    }
  }

  /// Create command instance by ID
  ICommand? createCommand(String commandId) {
    final factory = _commandFactories[commandId];
    return factory?.call();
  }

  /// Get all registered commands
  List<String> getRegisteredCommands() {
    return _commandFactories.keys.toList();
  }

  /// Get commands supported by model
  List<String> getCommandsForModel(String modelName) {
    return _modelCommands[modelName] ?? [];
  }

  /// Check if command is supported by model
  bool isCommandSupportedByModel(String commandId, String modelName) {
    final command = createCommand(commandId);
    return command?.supportedModels.contains(modelName) ?? false;
  }

  /// Get commands by category
  List<String> getCommandsByCategory(String category) {
    return _commandFactories.keys.where((commandId) {
      final command = createCommand(commandId);
      return command?.parameters['category'] == category;
    }).toList();
  }
}

/// Factory for creating printer instances
class PrinterFactory {
  static final PrinterFactory _instance = PrinterFactory._internal();
  factory PrinterFactory() => _instance;
  PrinterFactory._internal();

  final ModelRegistry _modelRegistry = ModelRegistry();
  final ProtocolRegistry _protocolRegistry = ProtocolRegistry();

  /// Create printer service for specific model
  Future<IPrinterService?> createPrinterService(String modelName) async {
    final model = _modelRegistry.createModel(modelName);
    if (model == null) return null;

    final protocol = _protocolRegistry.createProtocol(model.protocolType);
    if (protocol == null) return null;

    // Return configured printer service
    return ExtensiblePrinterService(model, protocol);
  }

  /// Auto-detect and create printer service
  Future<IPrinterService?> autoDetectPrinter(IProtocolHandler handler) async {
    // Try each registered protocol
    for (String protocolType in _protocolRegistry.getRegisteredProtocols()) {
      final model = await _modelRegistry.detectModel(handler, protocolType);
      if (model != null) {
        final protocol = _protocolRegistry.createProtocol(protocolType);
        if (protocol != null) {
          return ExtensiblePrinterService(model, protocol);
        }
      }
    }
    return null;
  }

  /// Get available models
  List<IPrinterModel> getAvailableModels() {
    return _modelRegistry
        .getRegisteredModels()
        .map((name) => _modelRegistry.createModel(name))
        .where((model) => model != null)
        .cast<IPrinterModel>()
        .toList();
  }
}

/// Command factory for creating command instances
class CommandFactory {
  static final CommandFactory _instance = CommandFactory._internal();
  factory CommandFactory() => _instance;
  CommandFactory._internal();

  final CommandRegistry _commandRegistry = CommandRegistry();

  /// Create command executor for specific command
  ICommandExecutor createExecutor(String commandId) {
    return StandardCommandExecutor();
  }

  /// Execute command with validation
  Future<Map<String, dynamic>> executeCommand(
    String commandId,
    Map<String, dynamic> parameters,
    IProtocolHandler handler,
  ) async {
    final command = _commandRegistry.createCommand(commandId);
    if (command == null) {
      throw Exception('Command not found: $commandId');
    }

    if (!command.validateParameters(parameters)) {
      throw Exception('Invalid parameters for command: $commandId');
    }

    final executor = createExecutor(commandId);
    return await executor.execute(command, parameters, handler);
  }

  /// Get available commands for model
  List<ICommand> getAvailableCommands(String modelName) {
    final commandIds = _commandRegistry.getCommandsForModel(modelName);
    return commandIds
        .map((id) => _commandRegistry.createCommand(id))
        .where((cmd) => cmd != null)
        .cast<ICommand>()
        .toList();
  }
}

/// Extensible printer service implementation
class ExtensiblePrinterService implements IPrinterService {
  final IPrinterModel _model;
  final IPrinterProtocol _protocol;
  IProtocolHandler? _handler;

  ExtensiblePrinterService(this._model, this._protocol);

  @override
  bool get isConnected => _handler != null;

  @override
  IPrinterModel? get currentModel => _model;

  @override
  Future<bool> connect(String deviceId) async {
    // Implementation would depend on specific Bluetooth handler
    // This is a placeholder for the actual connection logic
    return false;
  }

  @override
  Future<void> disconnect() async {
    await _handler?.dispose();
    _handler = null;
  }

  @override
  Future<void> executeCommand(String commandId,
      [Map<String, dynamic>? params]) async {
    if (_handler == null) throw Exception('Not connected');

    final factory = CommandFactory();
    await factory.executeCommand(commandId, params ?? {}, _handler!);
  }

  @override
  Future<List<IPrinterModel>> getAvailableModels() async {
    return PrinterFactory().getAvailableModels();
  }
}

/// Standard command executor implementation
class StandardCommandExecutor implements ICommandExecutor {
  @override
  Future<Map<String, dynamic>> execute(
    ICommand command,
    Map<String, dynamic> parameters,
    IProtocolHandler handler,
  ) async {
    final commandData = command.createCommandData(parameters);
    await handler.sendCommand(commandData);

    // Wait for response and return result
    // This is a simplified implementation
    return {'status': 'success', 'command': command.commandId};
  }
}
