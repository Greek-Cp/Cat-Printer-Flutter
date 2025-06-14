/*  1 */ package com.luckprinter.sdk_new;public class Base64 { private static char[] base64EncodeChars; private static byte[] base64DecodeChars; public static String encode(byte[] paramArrayOfbyte) { this(); StringBuffer stringBuffer; int i; byte b;
/*  2 */     for (i = paramArrayOfbyte.length, b = 0; b < i; ) {
/*    */       byte b1;
/*    */ 
/*    */       
/*  6 */       int k = (b1 = paramArrayOfbyte[b]) & 0xFF; int j; if ((j = b + 1) == i) { stringBuffer
/*    */           
/*  8 */           .append(base64EncodeChars[k >>> 2]);
/*  9 */         stringBuffer.append(base64EncodeChars[(b1 & 0x3) << 4]);
/* 10 */         stringBuffer.append("==");
/*    */         break; }
/*    */       
/* 13 */       j = paramArrayOfbyte[j]; int m; if ((m = b + 2) == i) { stringBuffer
/*    */           
/* 15 */           .append(base64EncodeChars[k >>> 2]);
/* 16 */         stringBuffer.append(base64EncodeChars[(b1 & 0x3) << 4 | (j & 0xF0) >>> 4]);
/*    */         
/* 18 */         stringBuffer.append(base64EncodeChars[(j & 0xF) << 2]);
/* 19 */         stringBuffer.append("="); break; }  b += 3;
/*    */ 
/*    */       
/* 22 */       m = paramArrayOfbyte[m];
/* 23 */       stringBuffer.append(base64EncodeChars[k >>> 2]);
/* 24 */       stringBuffer.append(base64EncodeChars[(b1 & 0x3) << 4 | (j & 0xF0) >>> 4]);
/*    */       
/* 26 */       stringBuffer.append(base64EncodeChars[(j & 0xF) << 2 | (m & 0xC0) >>> 6]);
/*    */       
/* 28 */       stringBuffer.append(base64EncodeChars[m & 0x3F]);
/*    */     } 
/* 30 */     return stringBuffer.toString(); } public static byte[] decode(String paramString) throws UnsupportedEncodingException { this(); StringBuffer stringBuffer; byte[] arrayOfByte; for (int i = (arrayOfByte = paramString.getBytes("US-ASCII")).length, j = 0; j < i; ) { byte[] arrayOfByte1; int m; for (arrayOfByte1 = base64DecodeChars, j = arrayOfByte1[arrayOfByte[j]]; (m = j + 1) < i && j == -1; j = m); if (j == -1) break;  int k; int n; for (arrayOfByte1 = base64DecodeChars, k = arrayOfByte1[arrayOfByte[m]]; (n = m + 1) < i && k == -1; m = n); if (k == -1) break;  stringBuffer.append((char)(j << 2 | (k & 0x30) >>> 4)); while (true) { j = n + 1; if ((m = arrayOfByte[n]) == 61) return stringBuffer.toString().getBytes("ISO-8859-1");  m = base64DecodeChars[m]; if (j >= i || m != -1)
/*    */           break;  n = j; }  if (m == -1)
/* 32 */         break;  stringBuffer.append((char)((k & 0xF) << 4 | (m & 0x3C) >>> 2)); while (true) { k = j + 1; if ((j = arrayOfByte[j]) == 61)
/*    */         {
/* 34 */           return stringBuffer.toString().getBytes("ISO-8859-1"); } 
/* 35 */         j = base64DecodeChars[j]; if (k >= i || j != -1) break;  j = k; }  if (j == -1) break;  stringBuffer
/*    */ 
/*    */ 
/*    */         
/* 39 */         .append((char)((m & 0x3) << 6 | j)); j = k; }
/*    */     
/* 41 */     return stringBuffer.toString().getBytes("ISO-8859-1"); }
/*    */ 
/*    */   
/*    */   static {
/*    */     char[] arrayOfChar;
/*    */     (arrayOfChar = new char[64])[0] = 'A';
/*    */     arrayOfChar[1] = 'B';
/*    */     arrayOfChar[2] = 'C';
/*    */     arrayOfChar[3] = 'D';
/*    */     arrayOfChar[4] = 'E';
/*    */     arrayOfChar[5] = 'F';
/*    */     arrayOfChar[6] = 'G';
/*    */     arrayOfChar[7] = 'H';
/*    */     arrayOfChar[8] = 'I';
/*    */     arrayOfChar[9] = 'J';
/*    */     arrayOfChar[10] = 'K';
/*    */     arrayOfChar[11] = 'L';
/*    */     arrayOfChar[12] = 'M';
/*    */     arrayOfChar[13] = 'N';
/*    */     arrayOfChar[14] = 'O';
/*    */     arrayOfChar[15] = 'P';
/*    */     arrayOfChar[16] = 'Q';
/*    */     arrayOfChar[17] = 'R';
/*    */     arrayOfChar[18] = 'S';
/*    */     arrayOfChar[19] = 'T';
/*    */     arrayOfChar[20] = 'U';
/*    */     arrayOfChar[21] = 'V';
/*    */     arrayOfChar[22] = 'W';
/*    */     arrayOfChar[23] = 'X';
/*    */     arrayOfChar[24] = 'Y';
/*    */     arrayOfChar[25] = 'Z';
/*    */     arrayOfChar[26] = 'a';
/*    */     arrayOfChar[27] = 'b';
/*    */     arrayOfChar[28] = 'c';
/*    */     arrayOfChar[29] = 'd';
/*    */     arrayOfChar[30] = 'e';
/*    */     arrayOfChar[31] = 'f';
/*    */     arrayOfChar[32] = 'g';
/*    */     arrayOfChar[33] = 'h';
/*    */     arrayOfChar[34] = 'i';
/*    */     arrayOfChar[35] = 'j';
/*    */     arrayOfChar[36] = 'k';
/*    */     arrayOfChar[37] = 'l';
/*    */     arrayOfChar[38] = 'm';
/*    */     arrayOfChar[39] = 'n';
/*    */     arrayOfChar[40] = 'o';
/*    */     arrayOfChar[41] = 'p';
/*    */     arrayOfChar[42] = 'q';
/*    */     arrayOfChar[43] = 'r';
/*    */     arrayOfChar[44] = 's';
/*    */     arrayOfChar[45] = 't';
/*    */     arrayOfChar[46] = 'u';
/*    */     arrayOfChar[47] = 'v';
/*    */     arrayOfChar[48] = 'w';
/*    */     arrayOfChar[49] = 'x';
/*    */     arrayOfChar[50] = 'y';
/*    */     arrayOfChar[51] = 'z';
/*    */     arrayOfChar[52] = '0';
/*    */     arrayOfChar[53] = '1';
/*    */     arrayOfChar[54] = '2';
/*    */     arrayOfChar[55] = '3';
/*    */     arrayOfChar[56] = '4';
/*    */     arrayOfChar[57] = '5';
/*    */     arrayOfChar[58] = '6';
/*    */     arrayOfChar[59] = '7';
/*    */     arrayOfChar[60] = '8';
/*    */     arrayOfChar[61] = '9';
/*    */     arrayOfChar[62] = '+';
/*    */     arrayOfChar[63] = '/';
/*    */     base64EncodeChars = arrayOfChar;
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[128])[0] = -1;
/*    */     arrayOfByte[1] = -1;
/*    */     arrayOfByte[2] = -1;
/*    */     arrayOfByte[3] = -1;
/*    */     arrayOfByte[4] = -1;
/*    */     arrayOfByte[5] = -1;
/*    */     arrayOfByte[6] = -1;
/*    */     arrayOfByte[7] = -1;
/*    */     arrayOfByte[8] = -1;
/*    */     arrayOfByte[9] = -1;
/*    */     arrayOfByte[10] = -1;
/*    */     arrayOfByte[11] = -1;
/*    */     arrayOfByte[12] = -1;
/*    */     arrayOfByte[13] = -1;
/*    */     arrayOfByte[14] = -1;
/*    */     arrayOfByte[15] = -1;
/*    */     arrayOfByte[16] = -1;
/*    */     arrayOfByte[17] = -1;
/*    */     arrayOfByte[18] = -1;
/*    */     arrayOfByte[19] = -1;
/*    */     arrayOfByte[20] = -1;
/*    */     arrayOfByte[21] = -1;
/*    */     arrayOfByte[22] = -1;
/*    */     arrayOfByte[23] = -1;
/*    */     arrayOfByte[24] = -1;
/*    */     arrayOfByte[25] = -1;
/*    */     arrayOfByte[26] = -1;
/*    */     arrayOfByte[27] = -1;
/*    */     arrayOfByte[28] = -1;
/*    */     arrayOfByte[29] = -1;
/*    */     arrayOfByte[30] = -1;
/*    */     arrayOfByte[31] = -1;
/*    */     arrayOfByte[32] = -1;
/*    */     arrayOfByte[33] = -1;
/*    */     arrayOfByte[34] = -1;
/*    */     arrayOfByte[35] = -1;
/*    */     arrayOfByte[36] = -1;
/*    */     arrayOfByte[37] = -1;
/*    */     arrayOfByte[38] = -1;
/*    */     arrayOfByte[39] = -1;
/*    */     arrayOfByte[40] = -1;
/*    */     arrayOfByte[41] = -1;
/*    */     arrayOfByte[42] = -1;
/*    */     arrayOfByte[43] = 62;
/*    */     arrayOfByte[44] = -1;
/*    */     arrayOfByte[45] = -1;
/*    */     arrayOfByte[46] = -1;
/*    */     arrayOfByte[47] = 63;
/*    */     arrayOfByte[48] = 52;
/*    */     arrayOfByte[49] = 53;
/*    */     arrayOfByte[50] = 54;
/*    */     arrayOfByte[51] = 55;
/*    */     arrayOfByte[52] = 56;
/*    */     arrayOfByte[53] = 57;
/*    */     arrayOfByte[54] = 58;
/*    */     arrayOfByte[55] = 59;
/*    */     arrayOfByte[56] = 60;
/*    */     arrayOfByte[57] = 61;
/*    */     arrayOfByte[58] = -1;
/*    */     arrayOfByte[59] = -1;
/*    */     arrayOfByte[60] = -1;
/*    */     arrayOfByte[61] = -1;
/*    */     arrayOfByte[62] = -1;
/*    */     arrayOfByte[63] = -1;
/*    */     arrayOfByte[64] = -1;
/*    */     arrayOfByte[65] = 0;
/*    */     arrayOfByte[66] = 1;
/*    */     arrayOfByte[67] = 2;
/*    */     arrayOfByte[68] = 3;
/*    */     arrayOfByte[69] = 4;
/*    */     arrayOfByte[70] = 5;
/*    */     arrayOfByte[71] = 6;
/*    */     arrayOfByte[72] = 7;
/*    */     arrayOfByte[73] = 8;
/*    */     arrayOfByte[74] = 9;
/*    */     arrayOfByte[75] = 10;
/*    */     arrayOfByte[76] = 11;
/*    */     arrayOfByte[77] = 12;
/*    */     arrayOfByte[78] = 13;
/*    */     arrayOfByte[79] = 14;
/*    */     arrayOfByte[80] = 15;
/*    */     arrayOfByte[81] = 16;
/*    */     arrayOfByte[82] = 17;
/*    */     arrayOfByte[83] = 18;
/*    */     arrayOfByte[84] = 19;
/*    */     arrayOfByte[85] = 20;
/*    */     arrayOfByte[86] = 21;
/*    */     arrayOfByte[87] = 22;
/*    */     arrayOfByte[88] = 23;
/*    */     arrayOfByte[89] = 24;
/*    */     arrayOfByte[90] = 25;
/*    */     arrayOfByte[91] = -1;
/*    */     arrayOfByte[92] = -1;
/*    */     arrayOfByte[93] = -1;
/*    */     arrayOfByte[94] = -1;
/*    */     arrayOfByte[95] = -1;
/*    */     arrayOfByte[96] = -1;
/*    */     arrayOfByte[97] = 26;
/*    */     arrayOfByte[98] = 27;
/*    */     arrayOfByte[99] = 28;
/*    */     arrayOfByte[100] = 29;
/*    */     arrayOfByte[101] = 30;
/*    */     arrayOfByte[102] = 31;
/*    */     arrayOfByte[103] = 32;
/*    */     arrayOfByte[104] = 33;
/*    */     arrayOfByte[105] = 34;
/*    */     arrayOfByte[106] = 35;
/*    */     arrayOfByte[107] = 36;
/*    */     arrayOfByte[108] = 37;
/*    */     arrayOfByte[109] = 38;
/*    */     arrayOfByte[110] = 39;
/*    */     arrayOfByte[111] = 40;
/*    */     arrayOfByte[112] = 41;
/*    */     arrayOfByte[113] = 42;
/*    */     arrayOfByte[114] = 43;
/*    */     arrayOfByte[115] = 44;
/*    */     arrayOfByte[116] = 45;
/*    */     arrayOfByte[117] = 46;
/*    */     arrayOfByte[118] = 47;
/*    */     arrayOfByte[119] = 48;
/*    */     arrayOfByte[120] = 49;
/*    */     arrayOfByte[121] = 50;
/*    */     arrayOfByte[122] = 51;
/*    */     arrayOfByte[123] = -1;
/*    */     arrayOfByte[124] = -1;
/*    */     arrayOfByte[125] = -1;
/*    */     arrayOfByte[126] = -1;
/*    */     arrayOfByte[127] = -1;
/*    */     base64DecodeChars = arrayOfByte;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\Base64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */