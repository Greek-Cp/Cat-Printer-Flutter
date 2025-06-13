import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'dart:ui' as ui;
import 'package:image/image.dart' as img;
import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:cat_printer_flutter/cat_printer_flutter.dart';

class PrinterController {
  final CatPrinterService _printer;
  final GlobalKey _screenshotKey = GlobalKey();
  double _threshold = 150.0;
  int _energy = 6000;
  double _widthScale = 1.0;
  double _heightScale = 1.0;
  bool _isDisposed = false;

  PrinterController({
    required CatPrinterService printer,
    double? threshold,
    int? energy,
    double? widthScale,
    double? heightScale,
  }) : _printer = printer {
    if (threshold != null) _threshold = threshold;
    if (energy != null) _energy = energy;
    if (widthScale != null) _widthScale = widthScale;
    if (heightScale != null) _heightScale = heightScale;
  }

  // Getter untuk key yang akan digunakan di ScreenshotWidget
  GlobalKey get screenshotKey => _screenshotKey;

  // Setter untuk konfigurasi print
  set threshold(double value) => _threshold = value;
  set energy(int value) => _energy = value;
  set widthScale(double value) => _widthScale = value;
  set heightScale(double value) => _heightScale = value;

  // Capture screenshot dari widget
  Future<img.Image?> capture() async {
    if (_isDisposed) {
      throw Exception('PrinterController has been disposed');
    }

    try {
      RenderRepaintBoundary boundary = _screenshotKey.currentContext!
          .findRenderObject() as RenderRepaintBoundary;
      final image = await boundary.toImage(pixelRatio: 3.0);
      final byteData = await image.toByteData(format: ui.ImageByteFormat.png);
      if (byteData != null) {
        final pngBytes = byteData.buffer.asUint8List();
        return img.decodeImage(pngBytes);
      }
    } catch (e, stack) {
      print('[PrinterController] Error capturing screenshot: $e');
      print('[PrinterController] Stack trace: $stack');
    }
    return null;
  }

  // Simpan screenshot ke file
  Future<String?> saveScreenshot() async {
    if (_isDisposed) {
      throw Exception('PrinterController has been disposed');
    }

    try {
      final image = await capture();
      if (image != null) {
        final directory = await getTemporaryDirectory();
        final fileName =
            'screenshot_${DateTime.now().millisecondsSinceEpoch}.png';
        final filePath = '${directory.path}/$fileName';
        final file = File(filePath);
        await file.writeAsBytes(img.encodePng(image));
        return filePath;
      }
    } catch (e, stack) {
      print('[PrinterController] Error saving screenshot: $e');
      print('[PrinterController] Stack trace: $stack');
    }
    return null;
  }

  // Print widget yang di-capture
  Future<void> printWidget() async {
    if (_isDisposed) {
      throw Exception('PrinterController has been disposed');
    }

    if (!_printer.isConnected) {
      throw Exception('Printer not connected');
    }

    try {
      final image = await capture();
      if (image != null) {
        await _printer.printImage(
          image,
          threshold: _threshold,
          energy: _energy,
          widthScale: _widthScale,
          heightScale: _heightScale,
        );
      } else {
        throw Exception('Failed to capture widget');
      }
    } catch (e) {
      print('[PrinterController] Error printing widget: $e');
      rethrow;
    }
  }

  // Clean up resources
  void dispose() {
    if (!_isDisposed) {
      _isDisposed = true;
      // Clear any cached data or resources
      _screenshotKey.currentState?.dispose();
    }
  }
}
