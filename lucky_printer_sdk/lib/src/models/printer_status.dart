enum PrinterConnectionStatus {
  disconnected,
  connecting,
  connected,
  disconnecting,
}

enum PrinterState {
  idle,
  printing,
  error,
  lowBattery,
  noPaper,
  paperJam,
  overheated,
  unknown,
}

class PrinterStatus {
  final PrinterConnectionStatus connectionStatus;
  final PrinterState state;
  final int? batteryLevel;
  final String? errorMessage;
  final bool isPaperPresent;
  final int? temperature;
  final String? firmwareVersion;
  final String? serialNumber;
  final String? macAddress;

  const PrinterStatus({
    required this.connectionStatus,
    required this.state,
    this.batteryLevel,
    this.errorMessage,
    this.isPaperPresent = true,
    this.temperature,
    this.firmwareVersion,
    this.serialNumber,
    this.macAddress,
  });

  PrinterStatus copyWith({
    PrinterConnectionStatus? connectionStatus,
    PrinterState? state,
    int? batteryLevel,
    String? errorMessage,
    bool? isPaperPresent,
    int? temperature,
    String? firmwareVersion,
    String? serialNumber,
    String? macAddress,
  }) {
    return PrinterStatus(
      connectionStatus: connectionStatus ?? this.connectionStatus,
      state: state ?? this.state,
      batteryLevel: batteryLevel ?? this.batteryLevel,
      errorMessage: errorMessage ?? this.errorMessage,
      isPaperPresent: isPaperPresent ?? this.isPaperPresent,
      temperature: temperature ?? this.temperature,
      firmwareVersion: firmwareVersion ?? this.firmwareVersion,
      serialNumber: serialNumber ?? this.serialNumber,
      macAddress: macAddress ?? this.macAddress,
    );
  }

  bool get isConnected => connectionStatus == PrinterConnectionStatus.connected;
  bool get canPrint =>
      isConnected && state == PrinterState.idle && isPaperPresent;
  bool get hasError => state == PrinterState.error || errorMessage != null;

  @override
  String toString() {
    return 'PrinterStatus(connectionStatus: $connectionStatus, state: $state, '
        'batteryLevel: $batteryLevel, isPaperPresent: $isPaperPresent)';
  }
}
