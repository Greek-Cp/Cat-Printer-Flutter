import 'package:cat_printer_flutter/other/blue_thermal_printer/print_job.dart';

abstract class PrintRepository {
  Future<void> printImage(PrintJob job);
}
