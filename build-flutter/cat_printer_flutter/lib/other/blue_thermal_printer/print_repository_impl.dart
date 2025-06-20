import 'dart:io';
import 'package:blue_thermal_printer/blue_thermal_printer.dart';
import 'package:cat_printer_flutter/other/blue_thermal_printer/print_job.dart';
import 'package:cat_printer_flutter/other/blue_thermal_printer/print_repository.dart';
import 'package:permission_handler/permission_handler.dart';

class PrintRepositoryImpl implements PrintRepository {
  final BlueThermalPrinter _printer = BlueThermalPrinter.instance;

  @override
  Future<void> printImage(PrintJob job) async {
    // Cek & minta permission (storage & bluetooth)
    await _handlePermission();

    // Koneksi ke printer (misal printer sudah dipilih/paired)
    // Di UI nanti user akan memilih printer

    if (job.imagePath.isNotEmpty && await File(job.imagePath).exists()) {
      // Print gambar
      await _printer.printImage(job.imagePath);
      await _printer.printNewLine();
      await _printer.paperCut(); // Jika printer support
    } else {
      throw Exception('File image tidak ditemukan!');
    }
  }

  Future<void> _handlePermission() async {
    await Permission.bluetooth.request();
    await Permission.bluetoothConnect.request();
    await Permission.bluetoothScan.request();
    await Permission.storage.request();
  }
}
