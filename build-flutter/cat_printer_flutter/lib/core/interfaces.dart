import 'dart:typed_data';

/// Base interface for all printer protocols
abstract class IPrinterProtocol {
  String get protocolName;
  String get protocolVersion;

  /// Create command bytes from command data
  List<int> createCommand(String commandId, List<int> data);

  /// Parse response from printer
  Map<String, dynamic> parseResponse(List<int> response);

  /// Get required Bluetooth characteristics
  List<String> getRequiredCharacteristics();

  /// Validate if response matches expected format
  bool isValidResponse(List<int> response);
}

/// Base interface for printer models
abstract class IPrinterModel {
  String get modelName;
  String get modelVersion;
  int get paperWidth;
  bool get supportsNewCommands;
  bool get hasFeedingProblems;
  String get protocolType;

  /// Get model-specific configuration
  Map<String, dynamic> getConfiguration();

  /// Get supported commands for this model
  List<String> getSupportedCommands();

  /// Get model-specific constraints
  Map<String, dynamic> getConstraints();
}

/// Base interface for commands
abstract class ICommand {
  String get commandId;
  String get commandName;
  List<String> get supportedModels;
  Map<String, dynamic> get parameters;

  /// Create command data
  List<int> createCommandData(Map<String, dynamic> params);

  /// Validate parameters
  bool validateParameters(Map<String, dynamic> params);

  /// Get parameter schema
  Map<String, dynamic> getParameterSchema();
}

/// Base interface for printer service
abstract class IPrinterService {
  bool get isConnected;
  IPrinterModel? get currentModel;

  Future<bool> connect(String deviceId);
  Future<void> disconnect();
  Future<void> executeCommand(String commandId, [Map<String, dynamic>? params]);
  Future<List<IPrinterModel>> getAvailableModels();
}

/// Interface for protocol handlers
abstract class IProtocolHandler {
  Future<void> sendCommand(List<int> command);
  Stream<List<int>> get responseStream;
  Future<void> initialize();
  Future<void> dispose();
}

/// Interface for command executors
abstract class ICommandExecutor {
  Future<Map<String, dynamic>> execute(
    ICommand command,
    Map<String, dynamic> parameters,
    IProtocolHandler handler,
  );
}

/// Interface for model detectors
abstract class IModelDetector {
  Future<IPrinterModel?> detectModel(IProtocolHandler handler);
  bool canDetect(String protocolType);
}
