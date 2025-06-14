/*     */ package com.luckprinter.sdk_new.device;
/*     */ import com.luckprinter.sdk_new.device.normal.DP_D1H;
/*     */ import com.luckprinter.sdk_new.device.normal.L13;
/*     */ import com.luckprinter.sdk_new.device.normal.a4.DP_A4;
/*     */ 
/*     */ public enum PrinterEnum {
/*   7 */   LuckP_D1_PRINTER, CRAFTS_CO_4777_PRINTER, LuckP_D1C_PRINTER, LuckP_D1S_PRINTER, FICHERO_PRINTER, LuckP_D1X_PRINTER, LuckP_D1W_PRINTER, LuckP_D1w_PRINTER, LuckP_L1_PRINTER, shandian_PRINTER, QIRUI_Q1_PRINTER, MMGG_G1_PRINTER, QIRUI_Q2_PRINTER, MMGG_G2_PRINTER, LuckP_L1S_PRINTER, LuckP_L1X_PRINTER, LuckP_L1F_PRINTER, LuckP_L2_PRINTER, LuckP_L3_PRINTER, LuckP2_L3_PRINTER, TPA46Pro_PRINTER, APA46_PRINTER, APA46Y_PRINTER, DP_A49_PRINTER, APA41_PRINTER, DP_ITP06_PRINTER, DP_A49H_PRINTER, DP_A3_PRINTER, PPL3H_PRINTER, LuckP_L4_PRINTER, LuckP_A41_PRINTER, LuckP_MRG1_PRINTER, DP_A41_PRINTER, CXP80C_PRINTER, LuckP_A42_PRINTER, DP_A46_PRINTER, DP_A46H_PRINTER, DP_A47_PRINTER, DP_A47H_PRINTER, LuckP_L90_PRINTER, LuckP_L80_PRINTER, LuckP_L81_PRINTER, LuckP_A81_PRINTER, LuckP_A80_PRINTER, LuckP_rg2_PRINTER, DP_L1_PRINTER, DP_L1S_PRINTER, CO_3128_PRINTER, DP_A80_PRINTER, PD_A4_PRINTER, DP_A4_PRINTER, DP_A40_PRINTER, DP_A40a_PRINTER, DP_A4M_PRINTER, DP_8038_PRINTER, DP_A4_PRINTER_TEST, DP_8028_PRINTER, D80_PRINTER, D80_PRINTER1, D80_2_PRINTER, D80_3_PRINTER, D80H_PRINTER, APL86_PRINTER, APL86H_PRINTER, DP_ITP05_PRINTER, DP_ITP05S_PRINTER, TPA46_PRINTER, D210_2_PRINTER, S8_PRINTER, G88_PRINTER, DP_A80S_PRINTER, DP_A81_PRINTER, DP_A81S_PRINTER, DP_L80_PRINTER, DP_L80S_PRINTER, DP_L90_PRINTER, DP_L81_PRINTER, DP_L81S_PRINTER, DP_A80H_PRINTER, DP_A81H_PRINTER, DP_L81H_PRINTER, FICHERO_4437_PRINTER, DP_A80W_PRINTER, APL82_PRINTER, APL82H_PRINTER, DP_D1_PRINTER, DP_A2_PRINTER, DP_A2H_PRINTER, DP_D1BR_PRINTER, DP_Fichero_PRINTER, DP_Fichero2_PRINTER, DP_MiniPocketPrinter_PRINTER, DP_D1S_PRINTER, D11s_PRINTER, D15_PRINTER, PPD1_PRINTER, PPD1H_PRINTER, PPA2L_PRINTER, DP_D1H_PRINTER, DP_D1H_1_PRINTER, C21E_PRINTER, SAM_02_PRINTER, DP_D2_PRINTER, DP_D2H_PRINTER, DP_S1_PRINTER, Seznik_MiniX_PRINTER, PPS1_PRINTER, GrayPPS1_PRINTER, L12_PRINTER, DPL12_PRINTER, MPL12_PRINTER, A12_PRINTER, A50_PRINTER, AL200_PRINTER, L13_PRINTER, D11_PRINTER, MPL13_PRINTER, Y12_PRINTER, MPL13K_PRINTER, L13_MLPrinter_PRINTER, DP_Y50_PRINTER, DP_Y50P_PRINTER, P15_PRINTER, L15_PRINTER, DP_L2Pro_PRINTER, DP_L2ProJTKerry_PRINTER, DP_L2ProFlash_PRINTER, DP_D50_PRINTER, DP_D50H_PRINTER, QR380A_PRINTER, MPT_II_PRINTER, QR380A2_PRINTER, TB41_PRINTER, QR_386A_PRINTER, QR_386A_1_PRINTER, Y810BT_PRINTER, ITPP941_PRINTER, ITPP130B_PRINTER, D100_PRINTER, D100_1_PRINTER, GD985_PRINTER, GD985_2_PRINTER, P80S_PRINTER; PrinterEnum(String paramString1, Class<?> paramClass, BluetoothType paramBluetoothType, boolean paramBoolean) { this.name = paramString1; this.printerClass = paramClass; this.btType = paramBluetoothType; this.isBleEnable = paramBoolean; } public static PrinterEnum getByStartWithName(String paramString) { if (paramString == null || paramString.isEmpty()) return null;  PrinterEnum[] arrayOfPrinterEnum; int i; byte b; for (i = (arrayOfPrinterEnum = values()).length, b = 0; b < i; ) { PrinterEnum printerEnum; if (paramString.startsWith((printerEnum = arrayOfPrinterEnum[b]).name)) return printerEnum;  b++; }  return null; } public String getName() { return this.name; } public Class<?> getPrinterClass() { return this.printerClass; } public BluetoothType getBtType() { return this.btType; } public boolean isBleEnable() { return this.isBleEnable; } static { BluetoothType bluetoothType1 = BluetoothType.CLASSIC_BLE; LuckP_D1_PRINTER = new PrinterEnum("LuckP_D1_PRINTER", 0, "LuckP_D1_", LuckP_D1.class, bluetoothType1, false); CRAFTS_CO_4777_PRINTER = new PrinterEnum("CRAFTS_CO_4777_PRINTER", 1, "CRAFTS&CO|4777", CRAFTS_CO_4777.class, bluetoothType1, false); BluetoothType bluetoothType2 = BluetoothType.BLE_DUAL; LuckP_D1C_PRINTER = new PrinterEnum("LuckP_D1C_PRINTER", 2, "LuckP_D1C_", LuckP_D1C.class, bluetoothType2, false); LuckP_D1S_PRINTER = new PrinterEnum("LuckP_D1S_PRINTER", 3, "LuckP_D1S", LuckP_D1S.class, bluetoothType1, false); FICHERO_PRINTER = new PrinterEnum("FICHERO_PRINTER", 4, "Fichero 3561", DP_D1.class, bluetoothType2, false); LuckP_D1X_PRINTER = new PrinterEnum("LuckP_D1X_PRINTER", 5, "LuckP_D1X_", LuckP_D1X.class, bluetoothType1, false); LuckP_D1W_PRINTER = new PrinterEnum("LuckP_D1W_PRINTER", 6, "LuckP_D1W_", LuckP_D1W.class, bluetoothType2, false);
/*   8 */     LuckP_D1w_PRINTER = new PrinterEnum("LuckP_D1w_PRINTER", 7, "LuckP_D1w_", LuckP_D1W.class, bluetoothType2, false);
/*   9 */     LuckP_L1_PRINTER = new PrinterEnum("LuckP_L1_PRINTER", 8, "LuckP_L1_", LuckP_L1.class, bluetoothType2, false);
/*  10 */     shandian_PRINTER = new PrinterEnum("shandian_PRINTER", 9, "shandian_", LuckP_L1.class, bluetoothType2, false);
/*  11 */     QIRUI_Q1_PRINTER = new PrinterEnum("QIRUI_Q1_PRINTER", 10, "QIRUI_Q1_", QIRUI_Q1.class, bluetoothType2, false);
/*  12 */     MMGG_G1_PRINTER = new PrinterEnum("MMGG_G1_PRINTER", 11, "MMGG_G1_", QIRUI_Q1.class, bluetoothType2, false);
/*  13 */     QIRUI_Q2_PRINTER = new PrinterEnum("QIRUI_Q2_PRINTER", 12, "QIRUI_Q2_", QIRUI_Q2.class, bluetoothType2, false);
/*  14 */     MMGG_G2_PRINTER = new PrinterEnum("MMGG_G2_PRINTER", 13, "MMGG_G2_", QIRUI_Q2.class, bluetoothType2, false);
/*  15 */     LuckP_L1S_PRINTER = new PrinterEnum("LuckP_L1S_PRINTER", 14, "LuckP_L1S_", LuckP_L1S.class, bluetoothType2, false);
/*  16 */     LuckP_L1X_PRINTER = new PrinterEnum("LuckP_L1X_PRINTER", 15, "LuckP_L1X_", LuckP_L1X.class, bluetoothType1, false);
/*  17 */     LuckP_L1F_PRINTER = new PrinterEnum("LuckP_L1F_PRINTER", 16, "LuckP_L1F_", LuckP_L1F.class, bluetoothType2, false);
/*  18 */     LuckP_L2_PRINTER = new PrinterEnum("LuckP_L2_PRINTER", 17, "LuckP_L2_", LuckP_L2.class, bluetoothType1, false);
/*  19 */     LuckP_L3_PRINTER = new PrinterEnum("LuckP_L3_PRINTER", 18, "LuckP_L3_", LuckP_L3.class, bluetoothType1, false);
/*  20 */     LuckP2_L3_PRINTER = new PrinterEnum("LuckP2_L3_PRINTER", 19, "L3_", LuckP_L3.class, bluetoothType1, false);
/*  21 */     TPA46Pro_PRINTER = new PrinterEnum("TPA46Pro_PRINTER", 20, "TPA46Pro_", TPA46Pro.class, bluetoothType1, false);
/*  22 */     APA46_PRINTER = new PrinterEnum("APA46_PRINTER", 21, "APA46_", APA46.class, bluetoothType1, false);
/*  23 */     APA46Y_PRINTER = new PrinterEnum("APA46Y_PRINTER", 22, "APA46Y_", APA46.class, bluetoothType1, false);
/*  24 */     DP_A49_PRINTER = new PrinterEnum("DP_A49_PRINTER", 23, "APA49_", APA49.class, bluetoothType1, false);
/*  25 */     APA41_PRINTER = new PrinterEnum("APA41_PRINTER", 24, "APA41_", APA41.class, bluetoothType1, false);
/*  26 */     DP_ITP06_PRINTER = new PrinterEnum("DP_ITP06_PRINTER", 25, "DP_ITP06_", DP_ITP06.class, bluetoothType1, false);
/*  27 */     DP_A49H_PRINTER = new PrinterEnum("DP_A49H_PRINTER", 26, "APA49H_", APA49H.class, bluetoothType1, false);
/*  28 */     DP_A3_PRINTER = new PrinterEnum("DP_A3_PRINTER", 27, "DP_A3_", DP_A3.class, bluetoothType1, false);
/*  29 */     PPL3H_PRINTER = new PrinterEnum("PPL3H_PRINTER", 28, "PPL3H_", DP_A3.class, bluetoothType1, false);
/*  30 */     LuckP_L4_PRINTER = new PrinterEnum("LuckP_L4_PRINTER", 29, "LuckP_L4_", LuckP_L4.class, bluetoothType1, false);
/*  31 */     LuckP_A41_PRINTER = new PrinterEnum("LuckP_A41_PRINTER", 30, "LuckP_A41_", LuckP_A41.class, bluetoothType2, false);
/*  32 */     LuckP_MRG1_PRINTER = new PrinterEnum("LuckP_MRG1_PRINTER", 31, "MR_G1_", LuckP_A41.class, bluetoothType2, false);
/*  33 */     DP_A41_PRINTER = new PrinterEnum("DP_A41_PRINTER", 32, "DP_A41_", LuckP_A41.class, bluetoothType2, false);
/*  34 */     CXP80C_PRINTER = new PrinterEnum("CXP80C_PRINTER", 33, "CX-P80C_", LuckP_A41.class, bluetoothType2, false);
/*  35 */     LuckP_A42_PRINTER = new PrinterEnum("LuckP_A42_PRINTER", 34, "LuckP_A42_", LuckP_A42.class, bluetoothType2, false);
/*  36 */     DP_A46_PRINTER = new PrinterEnum("DP_A46_PRINTER", 35, "DP_A46_", DP_A46.class, bluetoothType2, false);
/*  37 */     DP_A46H_PRINTER = new PrinterEnum("DP_A46H_PRINTER", 36, "DP_A46H_", DP_A46H.class, bluetoothType2, false);
/*  38 */     DP_A47_PRINTER = new PrinterEnum("DP_A47_PRINTER", 37, "DP_A47_", DP_A47.class, bluetoothType2, false);
/*  39 */     DP_A47H_PRINTER = new PrinterEnum("DP_A47H_PRINTER", 38, "DP_A47H_", DP_A47H.class, bluetoothType2, false);
/*  40 */     LuckP_L90_PRINTER = new PrinterEnum("LuckP_L90_PRINTER", 39, "LuckP_L90_", LuckP_L90.class, bluetoothType1, false);
/*  41 */     LuckP_L80_PRINTER = new PrinterEnum("LuckP_L80_PRINTER", 40, "LuckP_L80_", LuckP_L80.class, bluetoothType1, false);
/*  42 */     LuckP_L81_PRINTER = new PrinterEnum("LuckP_L81_PRINTER", 41, "LuckP_L81_", LuckP_L81.class, bluetoothType1, false);
/*  43 */     LuckP_A81_PRINTER = new PrinterEnum("LuckP_A81_PRINTER", 42, "LuckP_A81_", LuckP_A81.class, bluetoothType1, false);
/*  44 */     LuckP_A80_PRINTER = new PrinterEnum("LuckP_A80_PRINTER", 43, "LuckP_A80_", LuckP_A80.class, bluetoothType1, false);
/*  45 */     LuckP_rg2_PRINTER = new PrinterEnum("LuckP_rg2_PRINTER", 44, "LuckP_RG2_", LuckP_RG2.class, bluetoothType1, false);
/*  46 */     DP_L1_PRINTER = new PrinterEnum("DP_L1_PRINTER", 45, "DP_L1_", DP_L1.class, bluetoothType1, false);
/*  47 */     DP_L1S_PRINTER = new PrinterEnum("DP_L1S_PRINTER", 46, "DP_L1S_", DP_L1S.class, bluetoothType1, false);
/*  48 */     CO_3128_PRINTER = new PrinterEnum("CO_3128_PRINTER", 47, "C&Co 3128", DP_L1S.class, bluetoothType1, false);
/*  49 */     DP_A80_PRINTER = new PrinterEnum("DP_A80_PRINTER", 48, "DP_A80_", DP_A80.class, bluetoothType1, false);
/*  50 */     PD_A4_PRINTER = new PrinterEnum("PD_A4_PRINTER", 49, "PD_A4", DP_A4.class, bluetoothType1, false);
/*  51 */     DP_A4_PRINTER = new PrinterEnum("DP_A4_PRINTER", 50, "DP_A4_", DP_A4.class, bluetoothType1, false);
/*  52 */     DP_A40_PRINTER = new PrinterEnum("DP_A40_PRINTER", 51, "A40_", A40.class, bluetoothType1, false);
/*  53 */     DP_A40a_PRINTER = new PrinterEnum("DP_A40a_PRINTER", 52, "D82_", A40a.class, bluetoothType1, false);
/*  54 */     DP_A4M_PRINTER = new PrinterEnum("DP_A4M_PRINTER", 53, "MP850_", DP_A4.class, bluetoothType1, false);
/*  55 */     DP_8038_PRINTER = new PrinterEnum("DP_8038_PRINTER", 54, "DP_8038_", DP_8038.class, bluetoothType1, false);
/*  56 */     DP_A4_PRINTER_TEST = new PrinterEnum("DP_A4_PRINTER_TEST", 55, "DP-A4_", DP_A4.class, bluetoothType1, false);
/*  57 */     DP_8028_PRINTER = new PrinterEnum("DP_8028_PRINTER", 56, "DP_8028_", DP_8028.class, bluetoothType1, false);
/*     */     
/*  59 */     D80_PRINTER = new PrinterEnum("D80_PRINTER", 57, "DP_D80_", DP_D80.class, bluetoothType1, false);
/*  60 */     D80_PRINTER1 = new PrinterEnum("D80_PRINTER1", 58, "DP-D80_", DP_D80.class, bluetoothType1, false);
/*  61 */     D80_2_PRINTER = new PrinterEnum("D80_2_PRINTER", 59, "D80-", DP_D80.class, bluetoothType1, false);
/*  62 */     D80_3_PRINTER = new PrinterEnum("D80_3_PRINTER", 60, "D80_", DP_D80.class, bluetoothType1, false);
/*  63 */     D80H_PRINTER = new PrinterEnum("D80H_PRINTER", 61, "DP_D80H_", DP_D80H.class, bluetoothType1, false);
/*  64 */     APL86_PRINTER = new PrinterEnum("APL86_PRINTER", 62, "APL86_", APL86.class, bluetoothType1, false);
/*  65 */     APL86H_PRINTER = new PrinterEnum("APL86H_PRINTER", 63, "APL86H_", APL86H.class, bluetoothType1, false);
/*  66 */     DP_ITP05_PRINTER = new PrinterEnum("DP_ITP05_PRINTER", 64, "DP_ITP05_", DP_ITP05.class, bluetoothType1, false);
/*  67 */     DP_ITP05S_PRINTER = new PrinterEnum("DP_ITP05S_PRINTER", 65, "DP_ITP05S_", DP_ITP05.class, bluetoothType1, false);
/*  68 */     TPA46_PRINTER = new PrinterEnum("TPA46_PRINTER", 66, "TPA46_", TPA46.class, bluetoothType1, false);
/*  69 */     D210_2_PRINTER = new PrinterEnum("D210_2_PRINTER", 67, "D210_", DP_A4.class, bluetoothType1, false);
/*     */     
/*  71 */     S8_PRINTER = new PrinterEnum("S8_PRINTER", 68, "S8_", DP_A4.class, bluetoothType1, false);
/*  72 */     G88_PRINTER = new PrinterEnum("G88_PRINTER", 69, "GD-88_", DP_A4.class, bluetoothType1, false);
/*  73 */     DP_A80S_PRINTER = new PrinterEnum("DP_A80S_PRINTER", 70, "DP_A80S_", DP_A80S.class, bluetoothType1, false);
/*  74 */     DP_A81_PRINTER = new PrinterEnum("DP_A81_PRINTER", 71, "DP_A81_", DP_A81.class, bluetoothType1, false);
/*  75 */     DP_A81S_PRINTER = new PrinterEnum("DP_A81S_PRINTER", 72, "DP_A81S_", DP_A81S.class, bluetoothType1, false);
/*  76 */     DP_L80_PRINTER = new PrinterEnum("DP_L80_PRINTER", 73, "DP_L80_", DP_L80.class, bluetoothType1, false);
/*  77 */     DP_L80S_PRINTER = new PrinterEnum("DP_L80S_PRINTER", 74, "DP_L80S_", DP_L80S.class, bluetoothType1, false);
/*  78 */     DP_L90_PRINTER = new PrinterEnum("DP_L90_PRINTER", 75, "DP_L90_", DP_L90.class, bluetoothType1, false);
/*  79 */     DP_L81_PRINTER = new PrinterEnum("DP_L81_PRINTER", 76, "DP_L81_", DP_L81.class, bluetoothType1, false);
/*  80 */     DP_L81S_PRINTER = new PrinterEnum("DP_L81S_PRINTER", 77, "DP_L81S_", DP_L81S.class, bluetoothType1, false);
/*  81 */     DP_A80H_PRINTER = new PrinterEnum("DP_A80H_PRINTER", 78, "DP_A80H_", DP_A80H.class, bluetoothType1, false);
/*  82 */     DP_A81H_PRINTER = new PrinterEnum("DP_A81H_PRINTER", 79, "DP_A81H_", DP_A81H.class, bluetoothType1, false);
/*  83 */     DP_L81H_PRINTER = new PrinterEnum("DP_L81H_PRINTER", 80, "DP_L81H_", DP_L81H.class, bluetoothType1, false);
/*  84 */     FICHERO_4437_PRINTER = new PrinterEnum("FICHERO_4437_PRINTER", 81, "Fichero 4437", DP_L81H.class, bluetoothType1, false);
/*  85 */     DP_A80W_PRINTER = new PrinterEnum("DP_A80W_PRINTER", 82, "DP_A80W_", DP_A80W.class, bluetoothType1, false);
/*  86 */     APL82_PRINTER = new PrinterEnum("APL82_PRINTER", 83, "APL82_", DP_A80W.class, bluetoothType1, false);
/*  87 */     APL82H_PRINTER = new PrinterEnum("APL82H_PRINTER", 84, "APL82H_", DP_A80WH.class, bluetoothType1, false);
/*  88 */     DP_D1_PRINTER = new PrinterEnum("DP_D1_PRINTER", 85, "DP_D1_", DP_D1.class, bluetoothType2, false);
/*  89 */     DP_A2_PRINTER = new PrinterEnum("DP_A2_PRINTER", 86, "PPA2_", A2.class, bluetoothType1, false);
/*  90 */     DP_A2H_PRINTER = new PrinterEnum("DP_A2H_PRINTER", 87, "PPA2H_", A2H.class, bluetoothType1, false);
/*  91 */     DP_D1BR_PRINTER = new PrinterEnum("DP_D1BR_PRINTER", 88, "br8051a01", DP_D1.class, bluetoothType2, false);
/*  92 */     DP_Fichero_PRINTER = new PrinterEnum("DP_Fichero_PRINTER", 89, "Fichero 4575", DP_D1H.class, bluetoothType2, false);
/*  93 */     DP_Fichero2_PRINTER = new PrinterEnum("DP_Fichero2_PRINTER", 90, "Fichero_4575", DP_D1H.class, bluetoothType2, false);
/*  94 */     DP_MiniPocketPrinter_PRINTER = new PrinterEnum("DP_MiniPocketPrinter_PRINTER", 91, "Mini Pocket Printer", MiniPocketPrinter.class, bluetoothType2, false);
/*  95 */     DP_D1S_PRINTER = new PrinterEnum("DP_D1S_PRINTER", 92, "DP_D1S_", DP_D1S.class, bluetoothType2, false);
/*  96 */     D11s_PRINTER = new PrinterEnum("D11s_PRINTER", 93, "D11s_", D11s.class, bluetoothType1, false);
/*  97 */     D15_PRINTER = new PrinterEnum("D15_PRINTER", 94, "D12_", D12.class, bluetoothType1, false);
/*  98 */     PPD1_PRINTER = new PrinterEnum("PPD1_PRINTER", 95, "PPD1_", PPD1.class, bluetoothType1, false);
/*  99 */     PPD1H_PRINTER = new PrinterEnum("PPD1H_PRINTER", 96, "PPD1H_", PPD1H.class, bluetoothType1, false);
/* 100 */     PPA2L_PRINTER = new PrinterEnum("PPA2L_PRINTER", 97, "PPA2L_", PPA2L.class, bluetoothType1, false);
/*     */     
/* 102 */     DP_D1H_PRINTER = new PrinterEnum("DP_D1H_PRINTER", 98, "DP_D1H_", DP_D1H.class, bluetoothType2, false);
/* 103 */     DP_D1H_1_PRINTER = new PrinterEnum("DP_D1H_1_PRINTER", 99, "DP_D1H", DP_D1H.class, bluetoothType2, false);
/* 104 */     C21E_PRINTER = new PrinterEnum("C21E_PRINTER", 100, "C21E_", C21E.class, bluetoothType2, false);
/* 105 */     SAM_02_PRINTER = new PrinterEnum("SAM_02_PRINTER", 101, "SAM-02", C21E.class, bluetoothType2, false);
/* 106 */     DP_D2_PRINTER = new PrinterEnum("DP_D2_PRINTER", 102, "DP_D2_", C21E.class, bluetoothType2, false);
/* 107 */     DP_D2H_PRINTER = new PrinterEnum("DP_D2H_PRINTER", 103, "DP_D2H_", DP_D2H.class, bluetoothType1, false);
/* 108 */     DP_S1_PRINTER = new PrinterEnum("DP_S1_PRINTER", 104, "DP_S1_", DP_S1.class, bluetoothType2, false);
/* 109 */     Seznik_MiniX_PRINTER = new PrinterEnum("Seznik_MiniX_PRINTER", 105, "Seznik MiniX_", SeznikMiniX.class, bluetoothType2, false);
/* 110 */     PPS1_PRINTER = new PrinterEnum("PPS1_PRINTER", 106, "PPS1_", PPS1.class, bluetoothType1, false);
/* 111 */     GrayPPS1_PRINTER = new PrinterEnum("GrayPPS1_PRINTER", 107, "GrayPPS1_", GrayPPS1.class, bluetoothType2, false);
/* 112 */     L12_PRINTER = new PrinterEnum("L12_PRINTER", 108, "L12_", L12.class, bluetoothType1, false);
/* 113 */     DPL12_PRINTER = new PrinterEnum("DPL12_PRINTER", 109, "DP_L12_", DPL12.class, bluetoothType1, false);
/* 114 */     MPL12_PRINTER = new PrinterEnum("MPL12_PRINTER", 110, "MPL12_", MPL12.class, bluetoothType1, false);
/* 115 */     A12_PRINTER = new PrinterEnum("A12_PRINTER", 111, "A12_", A12.class, bluetoothType1, false);
/* 116 */     A50_PRINTER = new PrinterEnum("A50_PRINTER", 112, "A50_", A50.class, bluetoothType1, false);
/* 117 */     AL200_PRINTER = new PrinterEnum("AL200_PRINTER", 113, "AL200-", AL200.class, bluetoothType1, false);
/* 118 */     L13_PRINTER = new PrinterEnum("L13_PRINTER", 114, "L13_", L13.class, bluetoothType1, false);
/* 119 */     D11_PRINTER = new PrinterEnum("D11_PRINTER", 115, "D11s_", L13.class, bluetoothType1, false);
/* 120 */     MPL13_PRINTER = new PrinterEnum("MPL13_PRINTER", 116, "MPL13_", L13.class, bluetoothType1, false);
/* 121 */     Y12_PRINTER = new PrinterEnum("Y12_PRINTER", 117, "Y12_", Y12.class, bluetoothType1, false);
/* 122 */     MPL13K_PRINTER = new PrinterEnum("MPL13K_PRINTER", 118, "MPL13K_", L13.class, bluetoothType1, false);
/* 123 */     L13_MLPrinter_PRINTER = new PrinterEnum("L13_MLPrinter_PRINTER", 119, "ML Printer", L13.class, bluetoothType1, false);
/* 124 */     DP_Y50_PRINTER = new PrinterEnum("DP_Y50_PRINTER", 120, "DP_Y50_", DP_Y50.class, bluetoothType1, false);
/* 125 */     DP_Y50P_PRINTER = new PrinterEnum("DP_Y50P_PRINTER", 121, "DP_Y50P_", DP_Y50P.class, bluetoothType1, false);
/* 126 */     P15_PRINTER = new PrinterEnum("P15_PRINTER", 122, "P15_", P15.class, bluetoothType1, false);
/* 127 */     L15_PRINTER = new PrinterEnum("L15_PRINTER", 123, "MPL15_", MPL15.class, bluetoothType1, false);
/* 128 */     DP_L2Pro_PRINTER = new PrinterEnum("DP_L2Pro_PRINTER", 124, "DP_L2Pro_", DP_L2Pro.class, bluetoothType1, false);
/* 129 */     DP_L2ProJTKerry_PRINTER = new PrinterEnum("DP_L2ProJTKerry_PRINTER", 125, "PeriPage+", DP_L2Pro_JTKerry.class, bluetoothType1, false);
/* 130 */     DP_L2ProFlash_PRINTER = new PrinterEnum("DP_L2ProFlash_PRINTER", 126, "FlashToy-F2_", DP_L2Pro_Flash.class, bluetoothType1, false);
/* 131 */     DP_D50_PRINTER = new PrinterEnum("DP_D50_PRINTER", 127, "LPD50_", LPD50.class, bluetoothType2, false);
/* 132 */     DP_D50H_PRINTER = new PrinterEnum("DP_D50H_PRINTER", 128, "LPD50H_", LPD50H.class, bluetoothType2, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     QR380A_PRINTER = new PrinterEnum("QR380A_PRINTER", 129, "QR380A_", TB41.class, bluetoothType2, false);
/* 138 */     MPT_II_PRINTER = new PrinterEnum("MPT_II_PRINTER", 130, "MPT-II", DP_D1.class, bluetoothType2, false);
/*     */     
/* 140 */     QR380A2_PRINTER = new PrinterEnum("QR380A2_PRINTER", 131, "QR380A-", TB41.class, bluetoothType2, false);
/* 141 */     TB41_PRINTER = new PrinterEnum("TB41_PRINTER", 132, "TB41_", TB41.class, bluetoothType2, false);
/* 142 */     QR_386A_PRINTER = new PrinterEnum("QR_386A_PRINTER", 133, "QR_386A_", QR_386A.class, bluetoothType1, false);
/* 143 */     QR_386A_1_PRINTER = new PrinterEnum("QR_386A_1_PRINTER", 134, "QR-386A-", QR_386A.class, bluetoothType1, false);
/* 144 */     Y810BT_PRINTER = new PrinterEnum("Y810BT_PRINTER", 135, "Y810BT-", Y810BT.class, bluetoothType1, false);
/* 145 */     ITPP941_PRINTER = new PrinterEnum("ITPP941_PRINTER", 136, "ITPP941_", ITPP941.class, bluetoothType2, false);
/* 146 */     ITPP130B_PRINTER = new PrinterEnum("ITPP130B_PRINTER", 137, "ITPP130B-", ITPP130B.class, bluetoothType1, false);
/* 147 */     D100_PRINTER = new PrinterEnum("D100_PRINTER", 138, "D100_", D100.class, bluetoothType2, false);
/* 148 */     D100_1_PRINTER = new PrinterEnum("D100_1_PRINTER", 139, "D100-", D100.class, bluetoothType2, false);
/* 149 */     GD985_PRINTER = new PrinterEnum("GD985_PRINTER", 140, "GD-985_", GD985.class, bluetoothType2, false);
/* 150 */     GD985_2_PRINTER = new PrinterEnum("GD985_2_PRINTER", 141, "GD-985", GD985.class, bluetoothType2, false);
/* 151 */     P80S_PRINTER = new PrinterEnum("P80S_PRINTER", 142, "P80S-", P80S.class, bluetoothType1, false); }
/*     */ 
/*     */   
/*     */   public enum BluetoothType {
/*     */     CLASSIC, BLE_DUAL, CLASSIC_BLE;
/*     */     
/*     */     public static BluetoothType fromName(String param1String) {
/*     */       BluetoothType bluetoothType = null;
/*     */       if (param1String != null)
/*     */         if (!param1String.equals("classic_ble")) {
/*     */           if (!param1String.equals("blue_dual")) {
/*     */             bluetoothType = CLASSIC;
/*     */           } else {
/*     */             bluetoothType = BLE_DUAL;
/*     */           } 
/*     */         } else {
/*     */           bluetoothType = CLASSIC_BLE;
/*     */         }  
/*     */       return bluetoothType;
/*     */     }
/*     */   }
/*     */   String name;
/*     */   Class<?> printerClass;
/*     */   BluetoothType btType;
/*     */   boolean isBleEnable;
/*     */ }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\PrinterEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */