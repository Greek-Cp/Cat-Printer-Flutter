import 'package:cat_printer_flutter/other/blue_thermal_printer/print_job.dart';
import 'package:cat_printer_flutter/other/blue_thermal_printer/print_repository.dart';

class PrintImageUseCase {
  final PrintRepository repository;
  PrintImageUseCase(this.repository);

  Future<void> execute(PrintJob job) async {
    await repository.printImage(job);
  }
}
