/*  1 */ package com.luckprinter.sdk_new.device.aiyin.firmupdate;public class DataAnalyzeESC { public static byte getType() { return type; } private static byte type = 0; public static void setType(byte paramByte) {} public static byte[] Package(byte paramByte, byte[] paramArrayOfbyte, int paramInt) { if (paramInt > paramArrayOfbyte.length) {
/*  2 */       return new byte[0];
/*    */     }
/*    */ 
/*    */     
/*  6 */     arrayOfByte2[0] = paramByte; arrayOfByte2[
/*  7 */         1] = 0;
/*  8 */     arrayOfByte2[2] = 0;
/*  9 */     arrayOfByte2[3] = 0;
/*    */     
/* 11 */     arrayOfByte2[4] = (byte)(paramInt / 256); int i; byte[] arrayOfByte2;
/* 12 */     for ((arrayOfByte2 = new byte[i = paramInt + 6])[5] = (byte)(paramInt % 256), paramByte = 0; paramByte < paramInt; ) { int k = paramByte + 6; arrayOfByte2[
/*    */           
/* 14 */           k] = paramArrayOfbyte[paramByte]; paramByte++; }
/*    */ 
/*    */ 
/*    */     
/* 18 */     arrayOfByte1[0] = 27; arrayOfByte1[
/* 19 */         1] = 16;
/*    */     
/* 21 */     arrayOfByte1[2] = (byte)(i / 256); byte[] arrayOfByte1; byte b;
/* 22 */     for ((arrayOfByte1 = new byte[paramInt + 13])[3] = (byte)(i % 256), b = 0; b < i; ) { int k = b + 6; arrayOfByte1[
/*    */           
/* 24 */           k] = arrayOfByte2[b]; b++; }  int j; for (b = 0, i = 0; i < (j = paramInt + 12); )
/*    */     
/*    */     { 
/*    */       
/* 28 */       b = (byte)(b + arrayOfByte1[i]); i++; }  arrayOfByte1[j] = b; return arrayOfByte1; }
/*    */ 
/*    */   
/*    */   public static byte[] analyzePacket(byte[] paramArrayOfbyte) {
/*    */     int i;
/*    */     if ((i = paramArrayOfbyte.length) < 13 || i > 320)
/*    */       return null; 
/*    */     if (paramArrayOfbyte[0] != 27 || paramArrayOfbyte[1] != 16)
/*    */       return null; 
/*    */     byte b;
/*    */     byte b1;
/*    */     int j;
/*    */     for (b = 0, b1 = 0; b1 < (j = i - 1); ) {
/*    */       b = (byte)(b + paramArrayOfbyte[b1]);
/*    */       b1++;
/*    */     } 
/*    */     if (b != paramArrayOfbyte[j])
/*    */       return null; 
/*    */     if ((i = paramArrayOfbyte[10] * 256 + paramArrayOfbyte[11]) > 300)
/*    */       return null; 
/*    */     type = paramArrayOfbyte[6];
/*    */     byte[] arrayOfByte;
/*    */     for (arrayOfByte = new byte[i], b1 = 0; b1 < i; ) {
/*    */       arrayOfByte[b1] = paramArrayOfbyte[b1 + 12];
/*    */       b1++;
/*    */     } 
/*    */     return arrayOfByte;
/*    */   }
/*    */   
/*    */   public static void Uint32ToByte(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
/*    */     paramArrayOfbyte[paramInt1] = (byte)(paramInt2 >> 24);
/*    */     int i = paramInt1 + 1;
/*    */     paramArrayOfbyte[i] = (byte)(paramInt2 >> 16);
/*    */     i = paramInt1 + 2;
/*    */     paramArrayOfbyte[i] = (byte)(paramInt2 >> 8);
/*    */     i = paramInt1 + 3;
/*    */     paramArrayOfbyte[i] = (byte)paramInt2;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\firmupdate\DataAnalyzeESC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */