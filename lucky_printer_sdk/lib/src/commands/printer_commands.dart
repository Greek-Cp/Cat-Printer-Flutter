import 'dart:typed_data';

/// Lucky Printer Command Protocol
/// Based on actual Java SDK implementation
class PrinterCommands {
  // ============== Core Lucky Printer Protocol Commands ==============

  /// Enable printer - The core command to activate printer
  /// Byte pattern: [16, -1, -15, mode] where mode is enablePrinterMode (default 3)
  static Uint8List enablePrinter([int mode = 3]) {
    return Uint8List.fromList([
      16, // 0x10
      255, // -1 as unsigned = 255 (0xFF)
      241, // -15 as unsigned = 241 (0xF1)
      mode, // Default mode = 3
    ]);
  }

  /// Printer wakeup command - Send null bytes to wake up printer
  /// Byte pattern: 12 zero bytes [0,0,0,0,0,0,0,0,0,0,0,0]
  static Uint8List printerWakeup() {
    return Uint8List.fromList([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
  }

  /// Stop print job command
  /// Byte pattern: [16, -1, -15, 69]
  static Uint8List stopPrintJob() {
    return Uint8List.fromList([
      16, // 0x10
      255, // -1 as unsigned = 255 (0xFF)
      241, // -15 as unsigned = 241 (0xF1)
      69, // 0x45
    ]);
  }

  /// Print line dots command (paper feed)
  /// Byte pattern: [27, 74, dots] where dots is number of dot lines to feed
  static Uint8List printLineDots(int dots) {
    return Uint8List.fromList([
      27, // 0x1B (ESC)
      74, // 0x4A (J)
      dots & 0xFF,
    ]);
  }

  /// Printer position command (for tag printing)
  /// Byte pattern: [29, 12]
  static Uint8List printerPosition() {
    return Uint8List.fromList([
      29, // 0x1D (GS)
      12, // 0x0C
    ]);
  }

  /// Set paper type command
  /// Byte pattern: [31, 128, type, param] for setPaperType
  static Uint8List setPaperType(int type, int param) {
    return Uint8List.fromList([
      31, // 0x1F
      128, // 0x80
      type & 0xFF,
      param & 0xFF,
    ]);
  }

  /// Set mark print last (end of printing sequence)
  /// Byte pattern: [27, -69, -69] = [27, 187, 187]
  static Uint8List setMarkPrintLast() {
    return Uint8List.fromList([
      27, // 0x1B (ESC)
      187, // -69 as unsigned = 187 (0xBB)
      187, // -69 as unsigned = 187 (0xBB)
    ]);
  }

  /// Adjust position auto command
  /// Byte pattern: [31, 17, position]
  static Uint8List adjustPositionAuto(int position) {
    return Uint8List.fromList([
      31, // 0x1F
      17, // 0x11
      position & 0xFF,
    ]);
  }

  // ============== PPD1 and Image Printing ==============

  /// PPD1 Enable Printer - Critical first step
  /// From Java SDK: enablePrinter with mode 3 for PPD1
  static Uint8List ppd1EnablePrinter() {
    return Uint8List.fromList([16, 255, 241, 3]); // Mode 3 for PPD1
  }

  /// PPD1 Wakeup - 12 null bytes (CRITICAL for PPD1)
  /// This is the key command that wakes up PPD1 printer
  static Uint8List ppd1Wakeup() {
    return Uint8List.fromList([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
  }

  /// PPD1 Paper Feed - Use ESC/POS standard
  /// PPD1 responds better to ESC/POS commands for paper feed
  static Uint8List ppd1PaperFeed(int lines) {
    return Uint8List.fromList([27, 74, lines]); // ESC J n
  }

  /// PPD1 Line Feed - Simple line feed
  static Uint8List ppd1LineFeed() {
    return Uint8List.fromList([10]); // LF
  }

  /// PPD1 Complete Print Sequence - The working sequence from Java SDK
  static Uint8List ppd1PrintText(String text) {
    List<int> commands = [];

    // Step 1: Wake up printer (CRITICAL - this is what makes PPD1 work)
    commands.addAll(ppd1Wakeup());

    // Step 2: Enable printer with PPD1 mode
    commands.addAll(ppd1EnablePrinter());

    // Step 3: Send text data
    commands.addAll(text.codeUnits);

    // Step 4: Add line feed if not present
    if (!text.endsWith('\n')) {
      commands.addAll(ppd1LineFeed());
    }

    // Step 5: Feed paper to push text out
    commands
        .addAll(ppd1PaperFeed(60)); // 60 lines should be enough to see output

    // Step 6: Stop print job
    commands.addAll(stopPrintJob());

    return Uint8List.fromList(commands);
  }

  /// PPD1 Simple Test - Minimal working test
  static Uint8List ppd1SimpleTest() {
    List<int> commands = [];

    // Minimal sequence that should work on PPD1
    commands.addAll(ppd1Wakeup()); // Wake up
    commands.addAll(ppd1EnablePrinter()); // Enable
    commands.addAll("PPD1 TEST\n".codeUnits); // Text
    commands.addAll(ppd1PaperFeed(40)); // Feed paper
    commands.addAll(stopPrintJob()); // Stop

    return Uint8List.fromList(commands);
  }

  /// PPD1 Alternative sequence - If main sequence doesn't work
  static Uint8List ppd1AlternativeTest() {
    List<int> commands = [];

    // Try with ESC/POS initialization
    commands.addAll([27, 64]); // ESC @ (Initialize printer)
    commands.addAll(ppd1Wakeup());
    commands.addAll(ppd1EnablePrinter());
    commands.addAll("ALT TEST\n".codeUnits);
    commands.addAll([27, 74, 80]); // ESC J 80 (Feed 80 lines)

    return Uint8List.fromList(commands);
  }

  /// PPD1 complete print sequence for images
  static List<Uint8List> ppd1PrintImageSequence(Uint8List imageData) {
    List<Uint8List> sequence = [];

    // 1. Wakeup printer
    sequence.add(printerWakeup());

    // 2. Enable printer
    sequence.add(enablePrinter(3));

    // 3. Send image data directly (already processed bitmap)
    sequence.add(imageData);

    // 4. Feed paper
    sequence.add(printLineDots(80));

    // 5. Stop print job
    sequence.add(stopPrintJob());

    return sequence;
  }

  // ============== Status and Info Commands ==============

  /// Get printer status
  static Uint8List getPrinterStatus() {
    return Uint8List.fromList([
      16, // 0x10
      255, // 0xFF
      64, // 0x40
    ]);
  }

  /// Get battery level
  static Uint8List getBattery() {
    return Uint8List.fromList([
      16, // 0x10
      255, // 0xFF
      80, // 0x50
      241, // 0xF1
    ]);
  }

  /// Get printer model
  static Uint8List getPrinterModel() {
    return Uint8List.fromList([
      16, // 0x10
      255, // 0xFF
      32, // 0x20
      240, // 0xF0
    ]);
  }

  // ============== Testing Commands ==============

  /// Simple connectivity test commands (in order of compatibility)
  static List<Uint8List> getConnectivityTestCommands() {
    return [
      // Most basic - just wakeup
      printerWakeup(),

      // Simple single byte
      Uint8List.fromList([0x0A]), // Line feed

      // Enable printer (core command)
      enablePrinter(3),

      // ESC/POS reset (fallback)
      Uint8List.fromList([0x1B, 0x40]), // ESC @
    ];
  }

  /// Text print commands (in order of compatibility)
  static List<Uint8List> getTextPrintCommands(String text) {
    List<int> textBytes = text.codeUnits;

    return [
      // Raw text only (most compatible)
      Uint8List.fromList(textBytes),

      // Text with line feed
      Uint8List.fromList(textBytes + [0x0A]),

      // Text with double line feed
      Uint8List.fromList(textBytes + [0x0A, 0x0A]),

      // Lucky Printer text with feeds
      Uint8List.fromList(textBytes + [0x0A, 0x0A, 0x0A]),
    ];
  }

  /// Paper feed commands (in order of compatibility)
  static List<Uint8List> getPaperFeedCommands() {
    return [
      // Simple line feeds
      Uint8List.fromList([0x0A, 0x0A, 0x0A]),

      // Lucky Printer line dots
      printLineDots(80),
      printLineDots(120),

      // ESC/POS paper feed
      Uint8List.fromList([0x1B, 0x64, 0x03]), // ESC d 3

      // Alternative feeds
      Uint8List.fromList([0x1B, 0x4A, 0x08]), // ESC J 8
    ];
  }

  // ============== Alternative/Compatibility Commands ==============

  /// Legacy initialize command (kept for compatibility)
  static Uint8List get initialize => enablePrinter(3);

  /// Alternative print text (simple)
  static Uint8List alternativePrintText(String text) {
    List<int> textBytes = text.codeUnits;
    return Uint8List.fromList(textBytes + [0x0A]);
  }

  /// Alternative print image (simple image header + data)
  static Uint8List alternativePrintImage(Uint8List imageData) {
    List<int> command = [];

    // Simple Lucky Printer image header
    command.addAll([0xAA, 0x55, 0x52, 0x90, 0x00]);

    // Add image data
    command.addAll(imageData);

    // Simple checksum (sum of all bytes)
    int checksum = command.fold(0, (sum, byte) => (sum + byte) & 0xFF);
    command.add(checksum);

    return Uint8List.fromList(command);
  }

  /// Get all initialization commands (ordered by compatibility)
  static List<Uint8List> getAllInitCommands() {
    return [
      // Lucky Printer specific (most compatible for Lucky printers)
      printerWakeup(),
      enablePrinter(3),

      // ESC/POS standard (fallback)
      Uint8List.fromList([0x1B, 0x40]), // ESC @ (Reset)

      // No init (some printers work without initialization)
      Uint8List.fromList([]),

      // Alternative modes
      enablePrinter(2),
      enablePrinter(1),
    ];
  }

  /// Get all text commands (ordered by compatibility)
  static List<Uint8List> getAllTextCommands(String text) {
    return getTextPrintCommands(text);
  }

  /// Get all feed commands (ordered by compatibility)
  static List<Uint8List> getAllFeedCommands() {
    return getPaperFeedCommands();
  }

  // ESC/POS Commands for Classic Bluetooth compatibility
  static Uint8List escInit() {
    return Uint8List.fromList([27, 64]); // ESC @
  }

  static Uint8List escLineFeed() {
    return Uint8List.fromList([10]); // LF
  }

  static Uint8List escCut() {
    return Uint8List.fromList([29, 86, 0]); // GS V 0
  }

  static Uint8List escSelectCharacterCodeTable() {
    return Uint8List.fromList([27, 116, 0]); // ESC t 0
  }

  // === NEW METHODS FOR DUAL CONNECTION SUPPORT ===

  // Classic Bluetooth printing methods (ESC/POS + Lucky Protocol)
  static Uint8List createClassicPrintJob(String text) {
    List<int> commands = [];

    // PPD1 Specific sequence based on Java SDK analysis
    // 1. Wakeup printer (12 null bytes) - CRITICAL for PPD1
    commands.addAll(printerWakeup());

    // 2. Enable printer with Lucky Protocol
    commands.addAll(enablePrinter(3)); // Mode 3 for PPD1

    // 3. Add text data with proper line ending
    commands.addAll(text.codeUnits);
    if (!text.endsWith('\n')) {
      commands.add(10); // Ensure line feed
    }

    // 4. Feed paper with Lucky Protocol (IMPORTANT: Use exact Java SDK pattern)
    commands.addAll(printLineDots(80)); // Standard paper feed for PPD1

    // 5. Stop print job
    commands.addAll(stopPrintJob());

    return Uint8List.fromList(commands);
  }

  static Uint8List createClassicTestPage() {
    List<int> commands = [];

    // PPD1 test sequence - keep it simple and reliable
    commands.addAll(printerWakeup());
    commands.addAll(enablePrinter(3));

    // Simple test content that works reliably on PPD1
    String testContent = "PPD1 CLASSIC TEST\n";
    testContent += DateTime.now().toString().substring(11, 19) + "\n";
    testContent += "Print Success!\n";

    commands.addAll(testContent.codeUnits);
    commands.addAll(printLineDots(80));
    commands.addAll(stopPrintJob());

    return Uint8List.fromList(commands);
  }

  static Uint8List createClassicSimpleTest() {
    List<int> commands = [];

    // Minimal PPD1 test - exactly like Java SDK
    commands.addAll(printerWakeup()); // 12 null bytes
    commands.addAll(enablePrinter(3)); // [16, 255, 241, 3]
    commands.addAll("PPD1 OK\n".codeUnits); // Simple text
    commands.addAll(printLineDots(40)); // Paper feed
    commands.addAll(stopPrintJob()); // Stop command

    return Uint8List.fromList(commands);
  }

  // BLE printing methods (Pure Lucky Protocol)
  static Uint8List createBLEPrintJob(String text) {
    List<int> commands = [];

    // BLE uses Lucky Protocol sequence
    commands.addAll(printerWakeup());
    commands.addAll(enablePrinter());

    // Text data for BLE
    commands.addAll(text.codeUnits);
    commands.add(10); // Line feed

    // Feed and finish
    commands.addAll(printLineDots(3));
    commands.addAll(stopPrintJob());

    return Uint8List.fromList(commands);
  }

  static Uint8List createBLETestPage() {
    List<int> commands = [];

    // BLE test sequence
    commands.addAll(printerWakeup());
    commands.addAll(enablePrinter());

    String testContent = "=== BLE TEST ===\n";
    testContent += "Lucky Printer BLE\n";
    testContent += "Print Test\n";
    testContent += "Success!\n";

    commands.addAll(testContent.codeUnits);
    commands.addAll(printLineDots(5));
    commands.addAll(stopPrintJob());

    return Uint8List.fromList(commands);
  }

  static Uint8List createBLESimpleTest() {
    List<int> commands = [];

    // Simple BLE test
    commands.addAll(printerWakeup());
    commands.addAll(enablePrinter());
    commands.addAll("BLE OK\n".codeUnits);
    commands.addAll(printLineDots(2));
    commands.addAll(stopPrintJob());

    return Uint8List.fromList(commands);
  }

  // === LEGACY METHODS FOR COMPATIBILITY ===

  // Send Bitmap command for image printing
  static Uint8List sendBitmap(Uint8List imageData,
      {int width = 384, int height = 0}) {
    List<int> command = [16, 255, 250]; // Lucky Printer bitmap command

    // Add image dimensions
    command.addAll([
      width & 0xFF,
      (width >> 8) & 0xFF,
      height & 0xFF,
      (height >> 8) & 0xFF,
    ]);

    // Add image data
    command.addAll(imageData);

    return Uint8List.fromList(command);
  }

  // Text encoding utilities
  static Uint8List encodeText(String text, {bool addLineFeed = true}) {
    List<int> encoded = text.codeUnits;
    if (addLineFeed) {
      encoded.add(10); // LF
    }
    return Uint8List.fromList(encoded);
  }

  // Print density control
  static Uint8List setPrintDensity(int density) {
    // density: 0-15 (0=lightest, 15=darkest)
    return Uint8List.fromList([16, 255, 247, density.clamp(0, 15)]);
  }

  // Print speed control
  static Uint8List setPrintSpeed(int speed) {
    // speed: 0-9 (0=slowest, 9=fastest)
    return Uint8List.fromList([16, 255, 248, speed.clamp(0, 9)]);
  }

  // Test patterns
  static Uint8List printTestPattern() {
    String pattern = "";
    for (int i = 0; i < 48; i++) {
      pattern += (i % 2 == 0) ? "#" : " ";
    }
    pattern += "\n";

    return Uint8List.fromList(pattern.codeUnits);
  }

  // Debug command to test connectivity
  static Uint8List createConnectivityTest() {
    List<int> commands = [];

    // Minimal test sequence
    commands.addAll([16, 255, 244, 1]); // Status request

    return Uint8List.fromList(commands);
  }

  // Paper status check
  static Uint8List checkPaperStatus() {
    return Uint8List.fromList([16, 255, 249, 1]);
  }

  // Temperature check
  static Uint8List checkTemperature() {
    return Uint8List.fromList([16, 255, 251, 1]);
  }
}
