import 'dart:io';
import 'package:image/image.dart' as img;
import 'package:path_provider/path_provider.dart';

Future<String> prepareImage(String inputPath,
    {int headWidth = 384, double threshold = 180}) async {
  final imageBytes = await File(inputPath).readAsBytes();
  img.Image? original = img.decodeImage(imageBytes);
  if (original == null) throw Exception('Gagal decode image!');

  // Hitung tinggi baru berdasarkan aspect ratio
  double aspectRatio = original.height / original.width;
  int newHeight = (headWidth * aspectRatio).round();

  // Resize dengan mempertahankan aspect ratio
  final img.Image resized = img.copyResize(
    original,
    width: headWidth,
    height: newHeight,
  );

  // Lanjutkan dengan konversi ke grayscale dan thresholding...
  final img.Image bw = img.grayscale(resized);

  for (int y = 0; y < bw.height; y++) {
    for (int x = 0; x < bw.width; x++) {
      final pixel = bw.getPixel(x, y);
      final luma = img.getLuminance(pixel);

      if (luma > threshold) {
        bw.setPixel(x, y, img.ColorRgb8(255, 255, 255));
      } else {
        bw.setPixel(x, y, img.ColorRgb8(0, 0, 0));
      }
    }
  }

  final tempDir = await getTemporaryDirectory();
  final outputPath = '${tempDir.path}/print_ready_a8c.png';
  await File(outputPath).writeAsBytes(img.encodePng(bw));
  return outputPath;
}
