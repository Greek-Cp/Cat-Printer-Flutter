enum PrinterModel {
  // D Series
  luckPD1('LuckP_D1_', 'LuckP D1 Printer'),
  luckPD1C('LuckP_D1C_', 'LuckP D1C Printer'),
  luckPD1S('LuckP_D1S', 'LuckP D1S Printer'),
  luckPD1X('LuckP_D1X_', 'LuckP D1X Printer'),
  luckPD1W('LuckP_D1W_', 'LuckP D1W Printer'),
  luckPD1w('LuckP_D1w_', 'LuckP D1w Printer'),

  // L Series
  luckPL1('LuckP_L1_', 'LuckP L1 Printer'),
  luckPL1S('LuckP_L1S_', 'LuckP L1S Printer'),
  luckPL1F('LuckP_L1F_', 'LuckP L1F Printer'),
  luckPL2('LuckP_L2_', 'LuckP L2 Printer'),
  luckPL3('LuckP_L3_', 'LuckP L3 Printer'),
  luckP2L3('L3_', 'LuckP2 L3 Printer'),
  luckPL4('LuckP_L4_', 'LuckP L4 Printer'),
  luckPL80('LuckP_L80_', 'LuckP L80 Printer'),
  luckPL81('LuckP_L81_', 'LuckP L81 Printer'),
  luckPL90('LuckP_L90_', 'LuckP L90 Printer'),

  // A Series
  luckPA41('LuckP_A41_', 'LuckP A41 Printer'),
  luckPA42('LuckP_A42_', 'LuckP A42 Printer'),
  luckPA80('LuckP_A80_', 'LuckP A80 Printer'),
  luckPA81('LuckP_A81_', 'LuckP A81 Printer'),

  // DP Series
  dpD1('DP_D1_', 'DP D1 Printer'),
  dpD1H('DP_D1H_', 'DP D1H Printer'),
  dpD2('DP_D2_', 'DP D2 Printer'),
  dpD2H('DP_D2H_', 'DP D2H Printer'),
  dpD50('LPD50_', 'DP D50 Printer'),
  dpD80('DP_D80_', 'DP D80 Printer'),
  dpD80H('DP_D80H_', 'DP D80H Printer'),

  // A Series DP
  dpA3('DP_A3_', 'DP A3 Printer'),
  dpA4('DP_A4_', 'DP A4 Printer'),
  dpA40('A40_', 'DP A40 Printer'),
  dpA40a('D82_', 'DP A40a Printer'),
  dpA41('DP_A41_', 'DP A41 Printer'),
  dpA46('DP_A46_', 'DP A46 Printer'),
  dpA46H('DP_A46H_', 'DP A46H Printer'),
  dpA47('DP_A47_', 'DP A47 Printer'),
  dpA47H('DP_A47H_', 'DP A47H Printer'),
  dpA49('APA49_', 'DP A49 Printer'),
  dpA49H('APA49H_', 'DP A49H Printer'),
  dpA80('DP_A80_', 'DP A80 Printer'),
  dpA80S('DP_A80S_', 'DP A80S Printer'),
  dpA80H('DP_A80H_', 'DP A80H Printer'),
  dpA81('DP_A81_', 'DP A81 Printer'),
  dpA81S('DP_A81S_', 'DP A81S Printer'),

  // L Series DP
  dpL1('DP_L1_', 'DP L1 Printer'),
  dpL1S('DP_L1S_', 'DP L1S Printer'),
  dpL12('DP_L12_', 'DP L12 Printer'),
  dpL80('DP_L80_', 'DP L80 Printer'),
  dpL80S('DP_L80S_', 'DP L80S Printer'),
  dpL81('DP_L81_', 'DP L81 Printer'),
  dpL81S('DP_L81S_', 'DP L81S Printer'),
  dpL90('DP_L90_', 'DP L90 Printer'),
  dpL2Pro('DP_L2Pro_', 'DP L2 Pro Printer'),

  // Special Models
  fichero('Fichero 3561', 'Fichero Printer'),
  shandian('shandian_', 'Shandian Printer'),
  qiruiQ1('QIRUI_Q1_', 'QIRUI Q1 Printer'),
  qiruiQ2('QIRUI_Q2_', 'QIRUI Q2 Printer'),
  mmggG1('MMGG_G1_', 'MMGG G1 Printer'),
  mmggG2('MMGG_G2_', 'MMGG G2 Printer'),

  // TPA Series
  tpa46('TPA46_', 'TPA46 Printer'),
  tpa46Pro('TPA46Pro_', 'TPA46 Pro Printer'),

  // APA Series
  apa41('APA41_', 'APA41 Printer'),
  apa46('APA46_', 'APA46 Printer'),
  apa46Y('APA46Y_', 'APA46Y Printer'),

  // Other Models
  craftsco4777('CRAFTS&CO|4777', 'Crafts & Co 4777'),
  c21e('C21E_', 'C21E Printer'),
  sam02('SAM-02', 'SAM-02 Printer'),
  pps1('PPS1_', 'PPS1 Printer'),
  grayPps1('GrayPPS1_', 'Gray PPS1 Printer'),

  // Generic/Unknown
  unknown('', 'Unknown Printer');

  const PrinterModel(this.namePrefix, this.displayName);

  final String namePrefix;
  final String displayName;

  static PrinterModel fromDeviceName(String deviceName) {
    for (PrinterModel model in PrinterModel.values) {
      if (model.namePrefix.isNotEmpty &&
          deviceName.startsWith(model.namePrefix)) {
        return model;
      }
    }
    return PrinterModel.unknown;
  }

  bool get supportsBLE => true; // Most Lucky printers support BLE

  bool get supportsClassicBluetooth {
    switch (this) {
      case PrinterModel.luckPD1:
      case PrinterModel.luckPD1S:
      case PrinterModel.luckPL2:
      case PrinterModel.luckPL3:
      case PrinterModel.luckPL4:
      case PrinterModel.luckPL80:
      case PrinterModel.luckPL81:
      case PrinterModel.luckPL90:
      case PrinterModel.luckPA80:
      case PrinterModel.luckPA81:
        return true;
      default:
        return false;
    }
  }

  int get defaultDPI => 203;

  int get maxPrintWidth {
    switch (this) {
      case PrinterModel.dpA4:
      case PrinterModel.dpA3:
        return 1616; // A4 printers
      case PrinterModel.luckPL80:
      case PrinterModel.luckPL81:
      case PrinterModel.luckPL90:
      case PrinterModel.dpL80:
      case PrinterModel.dpL80S:
      case PrinterModel.dpL81:
      case PrinterModel.dpL81S:
      case PrinterModel.dpL90:
        return 576; // 80mm printers
      default:
        return 384; // 58mm printers (default)
    }
  }
}
