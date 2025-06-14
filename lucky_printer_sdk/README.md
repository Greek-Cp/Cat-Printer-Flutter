# Lucky Printer Flutter

Flutter library untuk konektivitas Bluetooth dengan semua model Lucky Printer. Library ini mendukung scanning, koneksi, dan printing ke berbagai model Lucky Printer melalui Bluetooth.

## Features

- ✅ **Bluetooth Scanning**: Scan dan deteksi otomatis Lucky Printer devices
- ✅ **Multi-Model Support**: Mendukung 100+ model Lucky Printer (D, L, A, DP series)
- ✅ **Real-time Connection**: Koneksi Bluetooth real-time dengan status monitoring
- ✅ **Print Functions**: Text printing, image printing, dan QR code
- ✅ **Permission Handling**: Otomatis request permission yang diperlukan
- ✅ **Cross Platform**: Support Android dan iOS
- ✅ **Easy Integration**: API yang sederhana dan mudah digunakan

## Supported Printer Models

Library ini mendukung semua model Lucky Printer termasuk:

### D Series
- LuckP_D1, LuckP_D11, LuckP_D110, LuckP_D101
- DP_D1, DP_D11, DP_D110, DP_D101

### L Series  
- LuckP_L1, LuckP_L2, LuckP_L3
- DP_L1, DP_L2, DP_L3

### A Series
- LuckP_A1, LuckP_A4, LuckP_A6
- DP_A1, DP_A4, DP_A6

### DP Series
- DP_B1, DP_B18, DP_B21
- DP_T8, DP_T9

Dan banyak model lainnya...

## Installation

Tambahkan dependency ke `pubspec.yaml`:

```yaml
dependencies:
  lucky_printer_flutter: ^1.0.0
```

Jalankan:
```bash
flutter pub get
```

## Android Setup

### 1. Permissions

Tambahkan permissions berikut ke `android/app/src/main/AndroidManifest.xml`:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    
    <!-- Bluetooth permissions for Android 12+ (API 31+) -->
    <uses-permission android:name="android.permission.BLUETOOTH" android:maxSdkVersion="30" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" android:maxSdkVersion="30" />
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADVERTISE" />
    
    <!-- Location permissions (required for Bluetooth scanning) -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    
    <!-- For Android 10+ background location -->
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
    
    <!-- Bluetooth features -->
    <uses-feature 
        android:name="android.hardware.bluetooth" 
        android:required="true" />
    <uses-feature 
        android:name="android.hardware.bluetooth_le" 
        android:required="true" />

    <application>
        <!-- Your app content -->
    </application>
</manifest>
```

### 2. Minimum SDK

Pastikan `android/app/build.gradle` memiliki minimum SDK 21:

```gradle
android {
    compileSdkVersion 34
    
    defaultConfig {
        minSdkVersion 21  // Minimum untuk Bluetooth LE
        targetSdkVersion 34
    }
}
```

## iOS Setup

Tambahkan ke `ios/Runner/Info.plist`:

```xml
<key>NSBluetoothAlwaysUsageDescription</key>
<string>This app needs Bluetooth access to connect to Lucky Printers</string>
<key>NSBluetoothPeripheralUsageDescription</key>
<string>This app needs Bluetooth access to connect to Lucky Printers</string>
<key>NSLocationWhenInUseUsageDescription</key>
<string>This app needs location access to scan for Bluetooth devices</string>
```

## Usage

### Basic Usage

```dart
import 'package:lucky_printer_flutter/lucky_printer_flutter.dart';

class PrinterExample extends StatefulWidget {
  @override
  _PrinterExampleState createState() => _PrinterExampleState();
}

class _PrinterExampleState extends State<PrinterExample> {
  final LuckyPrinterManager _printerManager = LuckyPrinterManager();
  List<BluetoothDevice> _discoveredDevices = [];
  
  @override
  void initState() {
    super.initState();
    _initializePrinter();
  }

  Future<void> _initializePrinter() async {
    // Initialize the printer manager
    bool success = await _printerManager.initialize();
    if (success) {
      print('Printer manager initialized');
      
      // Listen to discovered devices
      _printerManager.devicesStream.listen((devices) {
        setState(() {
          _discoveredDevices = devices;
        });
      });
      
      // Listen to logs
      _printerManager.logStream.listen((log) {
        print('Printer Log: $log');
      });
    }
  }

  Future<void> _scanForPrinters() async {
    await _printerManager.startScan(timeout: Duration(seconds: 15));
  }

  Future<void> _connectToPrinter(BluetoothDevice device) async {
    bool success = await _printerManager.connect(device);
    if (success) {
      print('Connected to ${device.platformName}');
    }
  }

  Future<void> _printText(String text) async {
    if (_printerManager.isConnected) {
      bool success = await _printerManager.printText(text);
      if (success) {
        print('Text printed successfully');
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Lucky Printer Demo')),
      body: Column(
        children: [
          ElevatedButton(
            onPressed: _scanForPrinters,
            child: Text('Scan for Printers'),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: _discoveredDevices.length,
              itemBuilder: (context, index) {
                final device = _discoveredDevices[index];
                return ListTile(
                  title: Text(device.platformName),
                  subtitle: Text(device.remoteId.toString()),
                  trailing: ElevatedButton(
                    onPressed: () => _connectToPrinter(device),
                    child: Text('Connect'),
                  ),
                );
              },
            ),
          ),
          if (_printerManager.isConnected)
            ElevatedButton(
              onPressed: () => _printText('Hello Lucky Printer!'),
              child: Text('Print Test'),
            ),
        ],
      ),
    );
  }
}
```

## Troubleshooting

### Printer Tidak Ditemukan Saat Scan

1. **Pastikan Bluetooth aktif** di device Android
2. **Pastikan Location Services aktif** (diperlukan untuk Bluetooth scanning)
3. **Cek permissions** - pastikan semua permission Bluetooth dan Location sudah granted
4. **Pastikan printer dalam mode pairing** - beberapa printer perlu diaktifkan mode pairing
5. **Coba scan lebih lama** - gunakan timeout yang lebih panjang (15-30 detik)

```dart
// Scan dengan timeout lebih panjang
await _printerManager.startScan(timeout: Duration(seconds: 30));
```

### Permission Denied

Jika mendapat error permission denied:

1. **Cek AndroidManifest.xml** - pastikan semua permission sudah ditambahkan
2. **Request permission manual** - gunakan permission_handler untuk request manual
3. **Cek target SDK** - untuk Android 12+ (API 31+) perlu permission BLUETOOTH_SCAN dan BLUETOOTH_CONNECT

### Koneksi Gagal

1. **Pastikan printer sudah discoverable**
2. **Cek jarak** - pastikan device dekat dengan printer
3. **Restart Bluetooth** - matikan dan nyalakan kembali Bluetooth
4. **Clear Bluetooth cache** di Android Settings

### Print Gagal

1. **Cek koneksi** - pastikan masih terhubung ke printer
2. **Cek status printer** - pastikan ada kertas dan battery cukup
3. **Cek format data** - pastikan format image/text sesuai dengan printer

## API Reference

### LuckyPrinterManager

#### Methods

- `Future<bool> initialize()` - Initialize the printer manager
- `Future<void> startScan({Duration timeout})` - Start scanning for printers
- `Future<void> stopScan()` - Stop scanning
- `Future<bool> connect(BluetoothDevice device)` - Connect to a printer
- `Future<void> disconnect()` - Disconnect from printer
- `Future<bool> printText(String text)` - Print text
- `Future<bool> printImage(Uint8List imageData)` - Print image

#### Properties

- `bool isConnected` - Check if connected to a printer
- `bool isScanning` - Check if currently scanning
- `BluetoothDevice? connectedDevice` - Currently connected device
- `List<BluetoothDevice> discoveredDevices` - List of discovered devices

#### Streams

- `Stream<String> logStream` - Stream of log messages
- `Stream<List<BluetoothDevice>> devicesStream` - Stream of discovered devices
- `Stream<PrinterStatus> statusStream` - Stream of printer status updates

## Example

Lihat folder `example/` untuk implementasi lengkap dengan UI yang user-friendly.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Note**: Library ini dikembangkan untuk mendukung semua model Lucky Printer. Jika model printer Anda tidak terdeteksi, silakan buat issue dengan informasi model printer tersebut. 