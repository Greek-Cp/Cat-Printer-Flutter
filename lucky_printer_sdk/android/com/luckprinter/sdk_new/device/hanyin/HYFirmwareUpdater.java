/*   */ package com.luckprinter.sdk_new.device.hanyin;
/* 2 */ public class HYFirmwareUpdater { public static void doUpdateFirmware(File paramFile, UpdateListener paramUpdateListener) { startUpdate(paramUpdateListener, PrinterUtil.readByteArrayFromFile(paramFile)); } private static final int[] crc32tab; private static void startUpdate(UpdateListener paramUpdateListener, byte[] paramArrayOfbyte) { // Byte code:
/*   */     //   0: invokestatic getMaxPackageSize : ()I
/*   */     //   3: dup
/*   */     //   4: istore_2
/*   */     //   5: iconst_m1
/*   */     //   6: if_icmpne -> 43
/*   */     //   9: aload_0
/*   */     //   10: new java/lang/StringBuilder
/*   */     //   13: dup
/*   */     //   14: iload_2
/*   */     //   15: swap
/*   */     //   16: ldc 'firmUpdate maxPackageSize: '
/*   */     //   18: invokespecial <init> : (Ljava/lang/String;)V
/*   */     //   21: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*   */     //   24: invokevirtual toString : ()Ljava/lang/String;
/*   */     //   27: invokestatic a : (Ljava/lang/String;)V
/*   */     //   30: ifnull -> 42
/*   */     //   33: aload_0
/*   */     //   34: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   39: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   42: return
/*   */     //   43: aload_0
/*   */     //   44: ifnull -> 56
/*   */     //   47: aload_0
/*   */     //   48: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   53: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   56: aload_1
/*   */     //   57: arraylength
/*   */     //   58: istore_3
/*   */     //   59: iconst_0
/*   */     //   60: istore #4
/*   */     //   62: iload #4
/*   */     //   64: iload_3
/*   */     //   65: if_icmpge -> 169
/*   */     //   68: iload_2
/*   */     //   69: iload_3
/*   */     //   70: iload #4
/*   */     //   72: isub
/*   */     //   73: dup
/*   */     //   74: istore #5
/*   */     //   76: if_icmple -> 82
/*   */     //   79: goto -> 85
/*   */     //   82: iload_2
/*   */     //   83: istore #5
/*   */     //   85: aload_1
/*   */     //   86: iload #4
/*   */     //   88: iload #5
/*   */     //   90: invokestatic getSubByteArray : ([BII)[B
/*   */     //   93: iload #4
/*   */     //   95: invokestatic writeOnePackData : ([BI)[B
/*   */     //   98: dup
/*   */     //   99: astore #6
/*   */     //   101: ifnull -> 153
/*   */     //   104: aload #6
/*   */     //   106: arraylength
/*   */     //   107: ifle -> 153
/*   */     //   110: aload #6
/*   */     //   112: dup
/*   */     //   113: arraylength
/*   */     //   114: iconst_1
/*   */     //   115: isub
/*   */     //   116: baload
/*   */     //   117: ifne -> 153
/*   */     //   120: aload_0
/*   */     //   121: iload #4
/*   */     //   123: iload #5
/*   */     //   125: iadd
/*   */     //   126: istore #4
/*   */     //   128: ifnull -> 62
/*   */     //   131: aload_0
/*   */     //   132: iload #4
/*   */     //   134: i2f
/*   */     //   135: ldc 100.0
/*   */     //   137: fmul
/*   */     //   138: iload_3
/*   */     //   139: i2f
/*   */     //   140: fdiv
/*   */     //   141: f2i
/*   */     //   142: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;I)Ljava/lang/Runnable;
/*   */     //   147: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   150: goto -> 62
/*   */     //   153: aload_0
/*   */     //   154: ifnull -> 182
/*   */     //   157: aload_0
/*   */     //   158: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   163: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   166: goto -> 182
/*   */     //   169: aload_0
/*   */     //   170: ifnull -> 182
/*   */     //   173: aload_0
/*   */     //   174: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   179: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   182: return
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #1	-> 0
/*   */     //   #3	-> 10
/*   */     //   #5	-> 34
/*   */     //   #10	-> 48
/*   */     //   #12	-> 57
/*   */     //   #20	-> 90
/*   */     //   #21	-> 95
/*   */     //   #22	-> 106
/*   */     //   #27	-> 142
/*   */     //   #43	-> 158
/* 2 */     //   #44	-> 174 } private static byte[] writeOnePackData(byte[] paramArrayOfbyte, int paramInt) { byte[] arrayOfByte1 = PrinterUtil.hexStringToByteArray("1b 1c 26 20 56 31 20 64 6f 20 22 6f 74 61 22 0d 0a".toLowerCase().replace(" ", "")); byte[] arrayOfByte2 = PrinterUtil.intToByteArray4LowToHeight(paramInt);
/* 3 */     byte[] arrayOfByte3 = PrinterUtil.intToByteArray4LowToHeight(paramArrayOfbyte.length);
/*   */     
/* 5 */     byte[] arrayOfByte4 = PrinterUtil.intToByteArray4LowToHeight(crc32(paramArrayOfbyte));
/* 6 */     paramArrayOfbyte = PrinterUtil.combineBytes(new byte[][] { arrayOfByte1, arrayOfByte2, arrayOfByte3, arrayOfByte4, paramArrayOfbyte });
/* 7 */     boolean bool = true;
/*   */     
/* 9 */     return (d.b()).a.a(null, paramArrayOfbyte, bool, 3); }
/*   */ 
/*   */   
/*   */   private static int getMaxPackageSize() {
/*   */     byte[] arrayOfByte = PrinterUtil.hexStringToByteArray("1B 1C 26 20 56 31 20 67 65 74 76 61 6C 20 22 70 61 63 6B 5F 6C 65 6E 67 74 68 22 0D 0A".toLowerCase().replace(" ", ""));
/*   */     int i = 1;
/*   */     i = -1;
/*   */     if ((arrayOfByte = (d.b()).a.a(null, arrayOfByte, i, 3)) != null && arrayOfByte.length == 4)
/*   */       i = PrinterUtil.byteArray4ToInt(arrayOfByte); 
/*   */     return i;
/*   */   }
/*   */   
/*   */   public static int crc32(byte[] paramArrayOfbyte) {
/*   */     int i;
/*   */     int j;
/*   */     byte b;
/*   */     for (i = paramArrayOfbyte.length, j = 0, b = 0; b < i; ) {
/*   */       j = crc32tab[(j ^ paramArrayOfbyte[b] & 0xFF) & 0xFF] ^ j >>> 8;
/*   */       b++;
/*   */     } 
/*   */     return j ^ 0xFFFFFFFF;
/*   */   }
/*   */   
/*   */   static {
/*   */     int[] arrayOfInt;
/*   */     (arrayOfInt = new int[256])[0] = 0;
/*   */     arrayOfInt[1] = 1996959894;
/*   */     arrayOfInt[2] = -301047508;
/*   */     arrayOfInt[3] = -1727442502;
/*   */     arrayOfInt[4] = 124634137;
/*   */     arrayOfInt[5] = 1886057615;
/*   */     arrayOfInt[6] = -379345611;
/*   */     arrayOfInt[7] = -1637575261;
/*   */     arrayOfInt[8] = 249268274;
/*   */     arrayOfInt[9] = 2044508324;
/*   */     arrayOfInt[10] = -522852066;
/*   */     arrayOfInt[11] = -1747789432;
/*   */     arrayOfInt[12] = 162941995;
/*   */     arrayOfInt[13] = 2125561021;
/*   */     arrayOfInt[14] = -407360249;
/*   */     arrayOfInt[15] = -1866523247;
/*   */     arrayOfInt[16] = 498536548;
/*   */     arrayOfInt[17] = 1789927666;
/*   */     arrayOfInt[18] = -205950648;
/*   */     arrayOfInt[19] = -2067906082;
/*   */     arrayOfInt[20] = 450548861;
/*   */     arrayOfInt[21] = 1843258603;
/*   */     arrayOfInt[22] = -187386543;
/*   */     arrayOfInt[23] = -2083289657;
/*   */     arrayOfInt[24] = 325883990;
/*   */     arrayOfInt[25] = 1684777152;
/*   */     arrayOfInt[26] = -43845254;
/*   */     arrayOfInt[27] = -1973040660;
/*   */     arrayOfInt[28] = 335633487;
/*   */     arrayOfInt[29] = 1661365465;
/*   */     arrayOfInt[30] = -99664541;
/*   */     arrayOfInt[31] = -1928851979;
/*   */     arrayOfInt[32] = 997073096;
/*   */     arrayOfInt[33] = 1281953886;
/*   */     arrayOfInt[34] = -715111964;
/*   */     arrayOfInt[35] = -1570279054;
/*   */     arrayOfInt[36] = 1006888145;
/*   */     arrayOfInt[37] = 1258607687;
/*   */     arrayOfInt[38] = -770865667;
/*   */     arrayOfInt[39] = -1526024853;
/*   */     arrayOfInt[40] = 901097722;
/*   */     arrayOfInt[41] = 1119000684;
/*   */     arrayOfInt[42] = -608450090;
/*   */     arrayOfInt[43] = -1396901568;
/*   */     arrayOfInt[44] = 853044451;
/*   */     arrayOfInt[45] = 1172266101;
/*   */     arrayOfInt[46] = -589951537;
/*   */     arrayOfInt[47] = -1412350631;
/*   */     arrayOfInt[48] = 651767980;
/*   */     arrayOfInt[49] = 1373503546;
/*   */     arrayOfInt[50] = -925412992;
/*   */     arrayOfInt[51] = -1076862698;
/*   */     arrayOfInt[52] = 565507253;
/*   */     arrayOfInt[53] = 1454621731;
/*   */     arrayOfInt[54] = -809855591;
/*   */     arrayOfInt[55] = -1195530993;
/*   */     arrayOfInt[56] = 671266974;
/*   */     arrayOfInt[57] = 1594198024;
/*   */     arrayOfInt[58] = -972236366;
/*   */     arrayOfInt[59] = -1324619484;
/*   */     arrayOfInt[60] = 795835527;
/*   */     arrayOfInt[61] = 1483230225;
/*   */     arrayOfInt[62] = -1050600021;
/*   */     arrayOfInt[63] = -1234817731;
/*   */     arrayOfInt[64] = 1994146192;
/*   */     arrayOfInt[65] = 31158534;
/*   */     arrayOfInt[66] = -1731059524;
/*   */     arrayOfInt[67] = -271249366;
/*   */     arrayOfInt[68] = 1907459465;
/*   */     arrayOfInt[69] = 112637215;
/*   */     arrayOfInt[70] = -1614814043;
/*   */     arrayOfInt[71] = -390540237;
/*   */     arrayOfInt[72] = 2013776290;
/*   */     arrayOfInt[73] = 251722036;
/*   */     arrayOfInt[74] = -1777751922;
/*   */     arrayOfInt[75] = -519137256;
/*   */     arrayOfInt[76] = 2137656763;
/*   */     arrayOfInt[77] = 141376813;
/*   */     arrayOfInt[78] = -1855689577;
/*   */     arrayOfInt[79] = -429695999;
/*   */     arrayOfInt[80] = 1802195444;
/*   */     arrayOfInt[81] = 476864866;
/*   */     arrayOfInt[82] = -2056965928;
/*   */     arrayOfInt[83] = -228458418;
/*   */     arrayOfInt[84] = 1812370925;
/*   */     arrayOfInt[85] = 453092731;
/*   */     arrayOfInt[86] = -2113342271;
/*   */     arrayOfInt[87] = -183516073;
/*   */     arrayOfInt[88] = 1706088902;
/*   */     arrayOfInt[89] = 314042704;
/*   */     arrayOfInt[90] = -1950435094;
/*   */     arrayOfInt[91] = -54949764;
/*   */     arrayOfInt[92] = 1658658271;
/*   */     arrayOfInt[93] = 366619977;
/*   */     arrayOfInt[94] = -1932296973;
/*   */     arrayOfInt[95] = -69972891;
/*   */     arrayOfInt[96] = 1303535960;
/*   */     arrayOfInt[97] = 984961486;
/*   */     arrayOfInt[98] = -1547960204;
/*   */     arrayOfInt[99] = -725929758;
/*   */     arrayOfInt[100] = 1256170817;
/*   */     arrayOfInt[101] = 1037604311;
/*   */     arrayOfInt[102] = -1529756563;
/*   */     arrayOfInt[103] = -740887301;
/*   */     arrayOfInt[104] = 1131014506;
/*   */     arrayOfInt[105] = 879679996;
/*   */     arrayOfInt[106] = -1385723834;
/*   */     arrayOfInt[107] = -631195440;
/*   */     arrayOfInt[108] = 1141124467;
/*   */     arrayOfInt[109] = 855842277;
/*   */     arrayOfInt[110] = -1442165665;
/*   */     arrayOfInt[111] = -586318647;
/*   */     arrayOfInt[112] = 1342533948;
/*   */     arrayOfInt[113] = 654459306;
/*   */     arrayOfInt[114] = -1106571248;
/*   */     arrayOfInt[115] = -921952122;
/*   */     arrayOfInt[116] = 1466479909;
/*   */     arrayOfInt[117] = 544179635;
/*   */     arrayOfInt[118] = -1184443383;
/*   */     arrayOfInt[119] = -832445281;
/*   */     arrayOfInt[120] = 1591671054;
/*   */     arrayOfInt[121] = 702138776;
/*   */     arrayOfInt[122] = -1328506846;
/*   */     arrayOfInt[123] = -942167884;
/*   */     arrayOfInt[124] = 1504918807;
/*   */     arrayOfInt[125] = 783551873;
/*   */     arrayOfInt[126] = -1212326853;
/*   */     arrayOfInt[127] = -1061524307;
/*   */     arrayOfInt[128] = -306674912;
/*   */     arrayOfInt[129] = -1698712650;
/*   */     arrayOfInt[130] = 62317068;
/*   */     arrayOfInt[131] = 1957810842;
/*   */     arrayOfInt[132] = -355121351;
/*   */     arrayOfInt[133] = -1647151185;
/*   */     arrayOfInt[134] = 81470997;
/*   */     arrayOfInt[135] = 1943803523;
/*   */     arrayOfInt[136] = -480048366;
/*   */     arrayOfInt[137] = -1805370492;
/*   */     arrayOfInt[138] = 225274430;
/*   */     arrayOfInt[139] = 2053790376;
/*   */     arrayOfInt[140] = -468791541;
/*   */     arrayOfInt[141] = -1828061283;
/*   */     arrayOfInt[142] = 167816743;
/*   */     arrayOfInt[143] = 2097651377;
/*   */     arrayOfInt[144] = -267414716;
/*   */     arrayOfInt[145] = -2029476910;
/*   */     arrayOfInt[146] = 503444072;
/*   */     arrayOfInt[147] = 1762050814;
/*   */     arrayOfInt[148] = -144550051;
/*   */     arrayOfInt[149] = -2140837941;
/*   */     arrayOfInt[150] = 426522225;
/*   */     arrayOfInt[151] = 1852507879;
/*   */     arrayOfInt[152] = -19653770;
/*   */     arrayOfInt[153] = -1982649376;
/*   */     arrayOfInt[154] = 282753626;
/*   */     arrayOfInt[155] = 1742555852;
/*   */     arrayOfInt[156] = -105259153;
/*   */     arrayOfInt[157] = -1900089351;
/*   */     arrayOfInt[158] = 397917763;
/*   */     arrayOfInt[159] = 1622183637;
/*   */     arrayOfInt[160] = -690576408;
/*   */     arrayOfInt[161] = -1580100738;
/*   */     arrayOfInt[162] = 953729732;
/*   */     arrayOfInt[163] = 1340076626;
/*   */     arrayOfInt[164] = -776247311;
/*   */     arrayOfInt[165] = -1497606297;
/*   */     arrayOfInt[166] = 1068828381;
/*   */     arrayOfInt[167] = 1219638859;
/*   */     arrayOfInt[168] = -670225446;
/*   */     arrayOfInt[169] = -1358292148;
/*   */     arrayOfInt[170] = 906185462;
/*   */     arrayOfInt[171] = 1090812512;
/*   */     arrayOfInt[172] = -547295293;
/*   */     arrayOfInt[173] = -1469587627;
/*   */     arrayOfInt[174] = 829329135;
/*   */     arrayOfInt[175] = 1181335161;
/*   */     arrayOfInt[176] = -882789492;
/*   */     arrayOfInt[177] = -1134132454;
/*   */     arrayOfInt[178] = 628085408;
/*   */     arrayOfInt[179] = 1382605366;
/*   */     arrayOfInt[180] = -871598187;
/*   */     arrayOfInt[181] = -1156888829;
/*   */     arrayOfInt[182] = 570562233;
/*   */     arrayOfInt[183] = 1426400815;
/*   */     arrayOfInt[184] = -977650754;
/*   */     arrayOfInt[185] = -1296233688;
/*   */     arrayOfInt[186] = 733239954;
/*   */     arrayOfInt[187] = 1555261956;
/*   */     arrayOfInt[188] = -1026031705;
/*   */     arrayOfInt[189] = -1244606671;
/*   */     arrayOfInt[190] = 752459403;
/*   */     arrayOfInt[191] = 1541320221;
/*   */     arrayOfInt[192] = -1687895376;
/*   */     arrayOfInt[193] = -328994266;
/*   */     arrayOfInt[194] = 1969922972;
/*   */     arrayOfInt[195] = 40735498;
/*   */     arrayOfInt[196] = -1677130071;
/*   */     arrayOfInt[197] = -351390145;
/*   */     arrayOfInt[198] = 1913087877;
/*   */     arrayOfInt[199] = 83908371;
/*   */     arrayOfInt[200] = -1782625662;
/*   */     arrayOfInt[201] = -491226604;
/*   */     arrayOfInt[202] = 2075208622;
/*   */     arrayOfInt[203] = 213261112;
/*   */     arrayOfInt[204] = -1831694693;
/*   */     arrayOfInt[205] = -438977011;
/*   */     arrayOfInt[206] = 2094854071;
/*   */     arrayOfInt[207] = 198958881;
/*   */     arrayOfInt[208] = -2032938284;
/*   */     arrayOfInt[209] = -237706686;
/*   */     arrayOfInt[210] = 1759359992;
/*   */     arrayOfInt[211] = 534414190;
/*   */     arrayOfInt[212] = -2118248755;
/*   */     arrayOfInt[213] = -155638181;
/*   */     arrayOfInt[214] = 1873836001;
/*   */     arrayOfInt[215] = 414664567;
/*   */     arrayOfInt[216] = -2012718362;
/*   */     arrayOfInt[217] = -15766928;
/*   */     arrayOfInt[218] = 1711684554;
/*   */     arrayOfInt[219] = 285281116;
/*   */     arrayOfInt[220] = -1889165569;
/*   */     arrayOfInt[221] = -127750551;
/*   */     arrayOfInt[222] = 1634467795;
/*   */     arrayOfInt[223] = 376229701;
/*   */     arrayOfInt[224] = -1609899400;
/*   */     arrayOfInt[225] = -686959890;
/*   */     arrayOfInt[226] = 1308918612;
/*   */     arrayOfInt[227] = 956543938;
/*   */     arrayOfInt[228] = -1486412191;
/*   */     arrayOfInt[229] = -799009033;
/*   */     arrayOfInt[230] = 1231636301;
/*   */     arrayOfInt[231] = 1047427035;
/*   */     arrayOfInt[232] = -1362007478;
/*   */     arrayOfInt[233] = -640263460;
/*   */     arrayOfInt[234] = 1088359270;
/*   */     arrayOfInt[235] = 936918000;
/*   */     arrayOfInt[236] = -1447252397;
/*   */     arrayOfInt[237] = -558129467;
/*   */     arrayOfInt[238] = 1202900863;
/*   */     arrayOfInt[239] = 817233897;
/*   */     arrayOfInt[240] = -1111625188;
/*   */     arrayOfInt[241] = -893730166;
/*   */     arrayOfInt[242] = 1404277552;
/*   */     arrayOfInt[243] = 615818150;
/*   */     arrayOfInt[244] = -1160759803;
/*   */     arrayOfInt[245] = -841546093;
/*   */     arrayOfInt[246] = 1423857449;
/*   */     arrayOfInt[247] = 601450431;
/*   */     arrayOfInt[248] = -1285129682;
/*   */     arrayOfInt[249] = -1000256840;
/*   */     arrayOfInt[250] = 1567103746;
/*   */     arrayOfInt[251] = 711928724;
/*   */     arrayOfInt[252] = -1274298825;
/*   */     arrayOfInt[253] = -1022587231;
/*   */     arrayOfInt[254] = 1510334235;
/*   */     arrayOfInt[255] = 755167117;
/*   */     crc32tab = arrayOfInt;
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\hanyin\HYFirmwareUpdater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */