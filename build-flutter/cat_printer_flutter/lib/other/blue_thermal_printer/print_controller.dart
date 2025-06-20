import 'dart:io';

import 'package:cat_printer_flutter/other/blue_thermal_printer/image_helper.dart';
import 'package:cat_printer_flutter/other/blue_thermal_printer/print_image_usecase.dart';
import 'package:cat_printer_flutter/other/blue_thermal_printer/print_job.dart';
import 'package:get/get.dart';
import 'package:blue_thermal_printer/blue_thermal_printer.dart';
import 'package:image_picker/image_picker.dart'; // Untuk image picker

class PrintController extends GetxController {
  final PrintImageUseCase usecase;
  PrintController(this.usecase);

  RxString imagePath = ''.obs;
  RxList<BluetoothDevice> devices = <BluetoothDevice>[].obs;
  Rx<BluetoothDevice?> selectedDevice = Rx<BluetoothDevice?>(null);

  final BlueThermalPrinter _printer = BlueThermalPrinter.instance;

  @override
  void onInit() {
    super.onInit();
    getBondedDevices();
  }

  /// Retrieve all bonded Bluetooth devices
  Future<void> getBondedDevices() async {
    List<BluetoothDevice> bondedDevices = [];
    try {
      bondedDevices = await _printer.getBondedDevices();
      print("bondedDevices: ${bondedDevices.first.name}");
    } catch (e) {
      print('Error fetching bonded devices: $e');
    }
    devices.value = bondedDevices;
    // print('getBondedDevices: ${devices.length}');
    // print("devices: $bondedDevices");
  }

  /// Pick an image from the gallery
  Future<void> pickImage() async {
    final picker = ImagePicker();
    final picked = await picker.pickImage(source: ImageSource.gallery);
    if (picked != null) {
      imagePath.value = picked.path;
    }
  }

  /// Connect to the printer by name
  Future<bool> connectPrinterByName(String printerName) async {
    print("Try to connect to: $printerName using blue_thermal_printer");
    BluetoothDevice? deviceToConnect;

    // Find the printer by name from the bonded devices
    deviceToConnect = devices.value.firstWhere(
      (device) => device.name == printerName,
    );

    if (deviceToConnect != null) {
      try {
        // Attempt to connect to the selected printer
        await _printer.connect(deviceToConnect);
        selectedDevice.value = deviceToConnect;
        print("Connected to printer: ${deviceToConnect.name}");
        return true;
      } catch (e) {
        print("Error connecting to printer: $e");
      }
    } else {
      print('No printer found with name: $printerName');
    }
    return false;
  }

  /// Disconnect the printer
  Future<void> disconnectPrinter() async {
    await _printer.disconnect();
    selectedDevice.value = null;
  }

  /// Print the image to the selected printer
  Future<void> printImage(PrintJob job, double? threshold) async {
    bool? isConnected = await _printer.isConnected;
    if (isConnected == true) {
      if (job.imagePath.isNotEmpty && await File(job.imagePath).exists()) {
        // Tambahkan pengaturan printer
        // await _printer.setAlignment(BlueThermalPrinter.ALIGNMENT_CENTER);
        // await _printer.setFontSize(0); // Reset font size

        // await _printer.paperCut();
        String printReadyImagePath =
            await prepareImage(job.imagePath, threshold: threshold!);
        await _printer.printImage(printReadyImagePath);

        // Beri jeda dan feed paper
        await _printer.printNewLine();
        await _printer.printNewLine();
        await _printer.printNewLine();
        await _printer.printNewLine();
        await _printer.printCustom("message", 4, 1);
        await _printer.paperCut();
      } else {
        throw Exception('Image file not found!');
      }
    } else {
      throw Exception('Printer not connected!');
    }
  }
}
