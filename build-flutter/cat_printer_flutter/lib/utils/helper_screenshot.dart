import 'dart:ui' as ui;
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:image/image.dart' as img;
import 'dart:typed_data';
import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'printer_controller.dart';

class ScreenshotWidget extends StatelessWidget {
  final Widget child;
  final PrinterController controller;

  const ScreenshotWidget({
    Key? key,
    required this.child,
    required this.controller,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return RepaintBoundary(
      key: controller.screenshotKey,
      child: child,
    );
  }
}
