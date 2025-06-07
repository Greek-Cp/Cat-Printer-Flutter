// Basic Flutter widget test for Cat Printer Flutter app

import 'package:flutter_test/flutter_test.dart';

import 'package:cat_printer_flutter/main.dart';

void main() {
  testWidgets('Cat Printer app smoke test', (WidgetTester tester) async {
    // Build our app and trigger a frame.
    await tester.pumpWidget(const CatPrinterApp());

    // Verify that our app loads with the correct title.
    expect(find.text('Cat Printer Flutter'), findsOneWidget);
    expect(find.text('Status'), findsOneWidget);
    expect(find.text('Ready'), findsOneWidget);

    // Verify scan button exists
    expect(find.text('Scan for Printers'), findsOneWidget);
  });
}
