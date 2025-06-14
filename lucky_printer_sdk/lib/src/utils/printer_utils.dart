import 'dart:typed_data';
import 'dart:ui' as ui;
import 'package:flutter/services.dart';

class PrinterUtils {
  /// Convert Flutter Image to monochrome bitmap data
  static Future<Uint8List> imageToMonochrome(ui.Image image,
      {int threshold = 128}) async {
    final ByteData? byteData =
        await image.toByteData(format: ui.ImageByteFormat.rawRgba);
    if (byteData == null) throw Exception('Failed to convert image to bytes');

    final Uint8List pixels = byteData.buffer.asUint8List();
    final int width = image.width;
    final int height = image.height;
    final int bytesPerLine = (width + 7) ~/ 8;

    final List<int> monoData = [];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < bytesPerLine; x++) {
        int byte = 0;
        for (int bit = 0; bit < 8; bit++) {
          final int pixelX = x * 8 + bit;
          if (pixelX < width) {
            final int pixelIndex = (y * width + pixelX) * 4;
            if (pixelIndex + 2 < pixels.length) {
              final int r = pixels[pixelIndex];
              final int g = pixels[pixelIndex + 1];
              final int b = pixels[pixelIndex + 2];
              final int gray = (r * 0.299 + g * 0.587 + b * 0.114).round();
              if (gray < threshold) {
                byte |= (1 << (7 - bit));
              }
            }
          }
        }
        monoData.add(byte);
      }
    }

    return Uint8List.fromList(monoData);
  }

  /// Resize image to fit printer width
  static Future<ui.Image> resizeImage(ui.Image image, int maxWidth) async {
    if (image.width <= maxWidth) return image;

    final double ratio = maxWidth / image.width;
    final int newHeight = (image.height * ratio).round();

    final ui.PictureRecorder recorder = ui.PictureRecorder();
    final ui.Canvas canvas = ui.Canvas(recorder);

    canvas.scale(ratio);
    canvas.drawImage(image, ui.Offset.zero, ui.Paint());

    final ui.Picture picture = recorder.endRecording();
    return await picture.toImage(maxWidth, newHeight);
  }

  /// Load image from assets
  static Future<ui.Image> loadImageFromAssets(String assetPath) async {
    final ByteData data = await rootBundle.load(assetPath);
    final ui.Codec codec =
        await ui.instantiateImageCodec(data.buffer.asUint8List());
    final ui.FrameInfo frameInfo = await codec.getNextFrame();
    return frameInfo.image;
  }

  /// Convert string to bytes with proper encoding
  static Uint8List stringToBytes(String text) {
    return Uint8List.fromList(text.codeUnits);
  }

  /// Generate QR code data (simplified)
  static String generateQRData(String content) {
    // This is a simplified version. In a real implementation,
    // you might want to use a proper QR code library
    return content;
  }

  /// Calculate print time estimation
  static Duration estimatePrintTime(int dataSize, {int speed = 2}) {
    // Rough estimation based on data size and speed
    final int baseTime = 1000; // 1 second base time
    final int dataTime = (dataSize / 1024 * 500).round(); // 500ms per KB
    final int speedFactor = speed == 1
        ? 2
        : speed == 3
            ? 1
            : 1; // Adjust for speed

    return Duration(milliseconds: (baseTime + dataTime) * speedFactor);
  }

  /// Validate printer name format
  static bool isValidPrinterName(String name) {
    if (name.isEmpty) return false;

    // Check for common Lucky Printer prefixes
    final List<String> validPrefixes = [
      'LuckP_',
      'DP_',
      'APA',
      'TPA',
      'QIRUI_',
      'MMGG_',
      'CRAFTS&CO',
      'C21E_',
      'SAM-',
      'PPS1_',
      'Fichero'
    ];

    return validPrefixes.any((prefix) => name.startsWith(prefix));
  }

  /// Format MAC address
  static String formatMacAddress(String mac) {
    if (mac.length != 12) return mac;

    return mac
        .replaceAllMapped(
          RegExp(r'(.{2})'),
          (match) => '${match.group(1)}:',
        )
        .substring(0, 17);
  }

  /// Parse battery level from response
  static int parseBatteryLevel(Uint8List response) {
    if (response.length < 5) return -1;
    return response[4];
  }

  /// Convert density enum to printer value
  static int densityToPrinterValue(int density) {
    switch (density) {
      case 0:
        return 0x00; // Light
      case 1:
        return 0x01; // Normal
      case 2:
        return 0x02; // Dark
      case 3:
        return 0x03; // Very Dark
      default:
        return 0x01; // Default to normal
    }
  }

  /// Convert speed enum to printer value
  static int speedToPrinterValue(int speed) {
    switch (speed) {
      case 1:
        return 0x01; // Slow
      case 2:
        return 0x02; // Normal
      case 3:
        return 0x03; // Fast
      default:
        return 0x02; // Default to normal
    }
  }

  /// Check if device supports BLE
  static bool deviceSupportsBLE(String deviceName) {
    // Most modern Lucky printers support BLE
    return !deviceName.endsWith('_CLASSIC');
  }

  /// Generate unique print job ID
  static String generatePrintJobId() {
    return DateTime.now().millisecondsSinceEpoch.toString();
  }

  /// Chunk data for transmission
  static List<Uint8List> chunkData(Uint8List data, int chunkSize) {
    final List<Uint8List> chunks = [];
    for (int i = 0; i < data.length; i += chunkSize) {
      final int end =
          (i + chunkSize < data.length) ? i + chunkSize : data.length;
      chunks.add(data.sublist(i, end));
    }
    return chunks;
  }

  /// Validate checksum
  static bool validateChecksum(Uint8List data) {
    if (data.length < 2) return false;

    int calculatedChecksum = 0;
    for (int i = 0; i < data.length - 1; i++) {
      calculatedChecksum += data[i];
    }

    return (calculatedChecksum & 0xFF) == data.last;
  }

  /// Create error message from status code
  static String getErrorMessage(int statusCode) {
    switch (statusCode) {
      case 0x00:
        return 'Success';
      case 0x01:
        return 'Low battery';
      case 0x02:
        return 'No paper';
      case 0x03:
        return 'Paper jam';
      case 0x04:
        return 'Overheated';
      case 0x05:
        return 'Communication error';
      case 0x06:
        return 'Invalid command';
      case 0x07:
        return 'Printer busy';
      case 0x08:
        return 'Hardware error';
      default:
        return 'Unknown error ($statusCode)';
    }
  }
}
