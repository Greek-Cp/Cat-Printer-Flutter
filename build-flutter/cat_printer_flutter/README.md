# Cat Printer Flutter

Aplikasi Flutter untuk menghubungkan dan mencetak ke Cat Printer melalui Bluetooth. Aplikasi ini diporting dari kode Python Cat-Printer dengan semua data dan protokol komunikasi yang sama.

## Fitur

- ✅ Scan dan deteksi Cat Printer via Bluetooth
- ✅ Koneksi ke berbagai model Cat Printer (GB01, GB02, GB03, MX05, MX06, MX10, dll)
- ✅ Print teks sederhana
- ✅ Print gambar dari galeri
- ✅ Konfigurasi printer (energy, speed, quality, dll)
- ✅ Flow control untuk komunikasi yang stabil
- ✅ Support untuk semua karakteristik Bluetooth dari kode Python

## Model Printer yang Didukung

Aplikasi ini mendukung semua model yang sama dengan kode Python:
- **GB01** - Paper width: 384px
- **GB02** - Paper width: 384px  
- **GB03** - Paper width: 384px
- **MX05** - Paper width: 384px
- **MX06** - Paper width: 384px
- **MX10** - Paper width: 576px
- **YT01** - Paper width: 384px

## Persyaratan

- Flutter SDK (>=3.0.0)
- Android SDK (untuk Android)
- Bluetooth Low Energy (BLE) support
- Permissions: Bluetooth, Location (untuk Android)

## Dependencies

Semua dependencies sudah dikonfigurasi di `pubspec.yaml`:

```yaml
dependencies:
  flutter_blue_plus: ^1.14.6    # Bluetooth communication
  image: ^4.1.3                 # Image processing
  permission_handler: ^11.0.1   # Permissions
  file_picker: ^6.1.1          # File selection
  path_provider: ^2.1.1        # File paths
```

## Instalasi

1. **Clone repository dan masuk ke folder Flutter:**
   ```bash
   cd build-flutter/cat_printer_flutter
   ```

2. **Install dependencies:**
   ```bash
   flutter pub get
   ```

3. **Jalankan aplikasi:**
   ```bash
   flutter run
   ```

## Cara Penggunaan

### 1. Scan untuk Cat Printer
- Buka aplikasi
- Tap tombol "Scan for Printers"
- Tunggu hingga Cat Printer terdeteksi

### 2. Koneksi ke Printer
- Pilih printer dari daftar yang terdeteksi
- Tap tombol "Connect"
- Status akan berubah menjadi "Connected" jika berhasil

### 3. Print Teks
- Masukkan teks di field "Text to Print"
- Tap tombol "Print Text"
- Teks akan dicetak ke printer

### 4. Print Gambar
- Tap tombol "Pick & Print Image"
- Pilih gambar dari galeri
- Gambar akan otomatis dikonversi dan dicetak

## Struktur Kode

### Data Models (`lib/models/`)
- `printer_models.dart` - Model printer dan konfigurasi (ported dari Python)

### Services (`lib/services/`)
- `cat_printer_service.dart` - Service utama untuk komunikasi printer
- `printer_commander.dart` - Command dan protokol komunikasi (ported dari Python)

### Main App
- `main.dart` - UI utama aplikasi

## Protokol Komunikasi

Aplikasi ini menggunakan protokol komunikasi yang sama persis dengan kode Python:

### Bluetooth Characteristics
- **TX Characteristic:** `0000ae01-0000-1000-8000-00805f9b34fb`
- **RX Characteristic:** `0000ae02-0000-1000-8000-00805f9b34fb`

### Commands (dari Python)
- Device state, start/end printing
- Set DPI, speed, energy
- Draw bitmap, feed paper
- Flow control (pause/resume)

### Data Processing
- CRC8 checksum calculation
- Image to bitmap conversion
- MTU-based data chunking
- Flow control handling

## Konfigurasi Printer

Konfigurasi default (sama dengan Python):
```dart
class PrinterConfig {
  double energy = 10000;        // Printer energy
  int speed = 3;               // Print speed
  int quality = 200;           // Print quality (DPI)
  int mtu = 20;               // MTU size
  double scanTime = 15;        // Scan timeout
  int connectionTimeout = 10;  // Connection timeout
  bool flipH = false;          // Horizontal flip
  bool flipV = false;          // Vertical flip
  bool dryRun = false;         // Dry run mode
  bool fake = false;           // Fake printer mode
  bool dump = false;           // Dump mode
}
```

## Troubleshooting

### Bluetooth Issues
- Pastikan Bluetooth aktif
- Grant permission Bluetooth dan Location
- Restart aplikasi jika scan tidak menemukan device

### Connection Issues
- Pastikan printer dalam mode pairing
- Coba disconnect dan connect ulang
- Restart printer jika perlu

### Print Issues
- Pastikan printer memiliki kertas
- Cek battery printer
- Coba dengan teks sederhana dulu sebelum gambar

## Perbedaan dengan Kode Python

1. **Text Rendering:** Implementasi sederhana (placeholder), bisa ditingkatkan dengan library text rendering
2. **Image Processing:** Menggunakan package `image` Dart instead of PIL
3. **Bluetooth:** Menggunakan `flutter_blue_plus` instead of `bleak`
4. **UI:** Native Flutter UI instead of web interface

## Pengembangan Selanjutnya

- [ ] Implementasi text rendering yang lebih baik
- [ ] Support untuk QR code printing
- [ ] Batch printing
- [ ] Printer settings UI
- [ ] Print history
- [ ] Custom image filters

## Lisensi

Sama dengan proyek Python Cat-Printer.
