import 'dart:typed_data';

enum PrintJobType {
  image,
  text,
  qrCode,
  barcode,
}

enum PrintDensity {
  light(0x00),
  normal(0x01),
  dark(0x02),
  veryDark(0x03);

  const PrintDensity(this.value);
  final int value;
}

enum PrintSpeed {
  slow(0x01),
  normal(0x02),
  fast(0x03);

  const PrintSpeed(this.value);
  final int value;
}

class PrintJob {
  final String id;
  final PrintJobType type;
  final Uint8List? imageData;
  final String? text;
  final int copies;
  final PrintDensity density;
  final PrintSpeed speed;
  final int? width;
  final int? height;
  final DateTime createdAt;

  PrintJob({
    String? id,
    required this.type,
    this.imageData,
    this.text,
    this.copies = 1,
    this.density = PrintDensity.normal,
    this.speed = PrintSpeed.normal,
    this.width,
    this.height,
    DateTime? createdAt,
  })  : id = id ?? DateTime.now().millisecondsSinceEpoch.toString(),
        createdAt = createdAt ?? DateTime.now();

  PrintJob.image({
    required Uint8List imageData,
    int copies = 1,
    PrintDensity density = PrintDensity.normal,
    PrintSpeed speed = PrintSpeed.normal,
    int? width,
    int? height,
  }) : this(
          type: PrintJobType.image,
          imageData: imageData,
          copies: copies,
          density: density,
          speed: speed,
          width: width,
          height: height,
        );

  PrintJob.text({
    required String text,
    int copies = 1,
    PrintDensity density = PrintDensity.normal,
    PrintSpeed speed = PrintSpeed.normal,
  }) : this(
          type: PrintJobType.text,
          text: text,
          copies: copies,
          density: density,
          speed: speed,
        );

  PrintJob.qrCode({
    required String data,
    int copies = 1,
    PrintDensity density = PrintDensity.normal,
    PrintSpeed speed = PrintSpeed.normal,
  }) : this(
          type: PrintJobType.qrCode,
          text: data,
          copies: copies,
          density: density,
          speed: speed,
        );

  PrintJob copyWith({
    String? id,
    PrintJobType? type,
    Uint8List? imageData,
    String? text,
    int? copies,
    PrintDensity? density,
    PrintSpeed? speed,
    int? width,
    int? height,
  }) {
    return PrintJob(
      id: id ?? this.id,
      type: type ?? this.type,
      imageData: imageData ?? this.imageData,
      text: text ?? this.text,
      copies: copies ?? this.copies,
      density: density ?? this.density,
      speed: speed ?? this.speed,
      width: width ?? this.width,
      height: height ?? this.height,
      createdAt: createdAt,
    );
  }

  @override
  String toString() {
    return 'PrintJob(id: $id, type: $type, copies: $copies, density: $density)';
  }
}
