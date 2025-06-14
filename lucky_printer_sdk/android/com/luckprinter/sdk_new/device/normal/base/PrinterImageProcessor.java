/*  1 */ package com.luckprinter.sdk_new.device.normal.base;public class PrinterImageProcessor { public static byte[] getBitmapByteArrayGray(Bitmap paramBitmap, int paramInt1, int paramInt2) { int i1; if (paramBitmap == null || paramBitmap.isRecycled())
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 65 */       return null; }  if ((paramInt1 = (byte)paramInt1) < 0 || paramInt1 > 3) paramInt1 = 0;  int j = 8 / paramInt2; double d = paramInt2; int k = (int)Math.pow(2.0D, d); int m = 256 / k; int i, arrayOfInt[]; paramBitmap.getPixels(arrayOfInt = new int[n * (i = paramBitmap.getHeight())], 0, n, 0, 0, n, i); int n; if ((n = paramBitmap.getWidth()) % j != 0) { i1 = n / j + 1; } else { i1 = n / j; }  byte b = 8; byte b1 = (byte)(i1 % 256); byte b2 = (byte)(i1 / 256); byte b3 = (byte)(i % 256); byte b4 = (byte)(i / 256); byte[] arrayOfByte3; (arrayOfByte3 = new byte[8])[0] = 29; (new byte[8])[1] = 71; (new byte[8])[2] = 89; (new byte[8])[3] = paramInt1; (new byte[8])[4] = b1; (new byte[8])[5] = b2; (new byte[8])[6] = b3; (new byte[8])[7] = b4; byte[] arrayOfByte2; for (arrayOfByte2 = new byte[paramInt1 = i1 * i], b2 = 0; b2 < i; ) { for (b3 = 0; b3 < i1; ) { int i2; int i3; byte b5; for (i2 = b2 * i1 + b3, i3 = 0, b5 = 0; b5 < j; ) { i3 = b2 * n; i3 = Color.green(i3); int i4 = Color.blue(i3); i3 = (Color.red(i3 = arrayOfInt[b3 * j + i3 + b5]) + i3 + i4) / 3 / m; i3 = (byte)(i3 | k - 1 - i3 << (j - 1 - b5) * paramInt2); b5++; }  arrayOfByte2[i2] = i3; b3++; }  b2++; }  Integer integer1 = Integer.valueOf(paramInt1), integer2 = Integer.valueOf(i1), integer3 = Integer.valueOf(i); Object[] arrayOfObject; (arrayOfObject = new Object[3])[0] = integer1; (new Object[3])[1] = integer2; (new Object[3])[2] = integer3; h.a(String.format("bmpSize: %d, w:%d, h:%d", arrayOfObject)); System.arraycopy(arrayOfByte3, 0, arrayOfByte1, 0, b); System.arraycopy(arrayOfByte2, 0, arrayOfByte1, b, paramInt1); byte[] arrayOfByte1; writeFileByteArray(arrayOfByte1 = new byte[b + paramInt1], i1, "imagedata.txt"); return new byte[b + paramInt1]; } public static byte[] getBitmapByteArray(Bitmap paramBitmap, int paramInt) { int k;
/*    */     if (paramBitmap == null || paramBitmap.isRecycled())
/* 67 */       return null;  if ((paramInt = (byte)paramInt) < 0 || paramInt > 3) paramInt = 0;  int i, arrayOfInt[]; paramBitmap.getPixels(arrayOfInt = new int[j * (i = paramBitmap.getHeight())], 0, j, 0, 0, j, i); int j; if ((j = paramBitmap.getWidth()) % 8 != 0) { k = j / 8 + 1; } else { k = j / 8; }  byte b = 8; byte b1 = (byte)(k % 256); byte b2 = (byte)(k / 256); byte b3 = (byte)(i % 256); byte b4 = (byte)(i / 256); byte[] arrayOfByte3; (arrayOfByte3 = new byte[8])[0] = 29; (new byte[8])[1] = 118; (new byte[8])[2] = 48; (new byte[8])[3] = paramInt; (new byte[8])[4] = b1; (new byte[8])[5] = b2; (new byte[8])[6] = b3; (new byte[8])[7] = b4; byte[] arrayOfByte2; for (arrayOfByte2 = new byte[paramInt = k * i], b2 = 0, b3 = 0; b3 < i; ) { for (b4 = 0; b4 < k; ) { byte b5; int n; for (b5 = 0, n = 0; n < 8; ) { int i1 = Color.green(i1); int i2 = Color.blue(i1); Color.alpha(i1); if ((i1 = b4 * 8 + n) < j && (Color.red(i1 = arrayOfInt[b3 * j + i1]) + i1 + i2) / 3 < 128) b5 = (byte)(b5 | (byte)(128 >> n));  n++; }  n = b2 + 1; arrayOfByte2[b2] = b5; b4++; int m = n; }  b3++; }  Integer integer1 = Integer.valueOf(paramInt), integer2 = Integer.valueOf(k), integer3 = Integer.valueOf(i); Object[] arrayOfObject; (arrayOfObject = new Object[3])[0] = integer1; (new Object[3])[1] = integer2; (new Object[3])[2] = integer3; h.a(String.format("bmpSize: %d, w:%d, h:%d", arrayOfObject)); System.arraycopy(arrayOfByte3, 0, arrayOfByte1, 0, b); System.arraycopy(arrayOfByte2, 0, arrayOfByte1, b, paramInt); byte[] arrayOfByte1; writeFileByteArray(arrayOfByte1 = new byte[b + paramInt], k, "imagedata.txt");
/*    */     return new byte[b + paramInt]; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static byte[] getBitmapByteArrayCompress(Bitmap paramBitmap, int paramInt) {
/*    */     if (paramBitmap == null || paramBitmap.isRecycled())
/* 83 */       return null;  int i, arrayOfInt[]; paramBitmap.getPixels(arrayOfInt = new int[k * (i = paramBitmap.getHeight())], 0, k, 0, 0, k, i); int k; if ((k = paramBitmap.getWidth()) % 8 != 0) { m = k / 8 + 1; } else { m = k / 8; }  arrayOfByte3[0] = 31; arrayOfByte3[1] = 16; arrayOfByte3[2] = (byte)(m / 256); arrayOfByte3[3] = (byte)(m % 256); arrayOfByte3[4] = (byte)(i / 256); byte b1; byte[] arrayOfByte3; (arrayOfByte3 = new byte[b1 = 10])[5] = (byte)(i % 256); int n; byte[] arrayOfByte4 = new byte[n = m * i]; int i1 = 0; this(); RecordTimeBean recordTimeBean1; byte[] arrayOfByte2; int m; RecordTimeBean recordTimeBean2; (new RecordTimeBean()).recordStart(); for (byte b2 = 0; b2 < i; ) { for (byte b = 0; b < m; ) { byte b3; int i2; for (b3 = 0, i2 = 0; i2 < 8; ) { int i3 = Color.green(i3); int i4 = Color.blue(i3); Color.alpha(i3); if ((i3 = b * 8 + i2) < k && (Color.red(i3 = arrayOfInt[b2 * k + i3]) + i3 + i4) / 3 < 128) b3 = (byte)(b3 | (byte)(128 >> i2));  i2++; }  i2 = i1 + 1; arrayOfByte4[i1] = b3; b++; i1 = i2; }  b2++; }  recordTimeBean2.recordEnd(); h.a("getPixelTime >>>>> :" + recordTimeBean2.getDurationMs() + " ms"); Integer integer1 = Integer.valueOf(n), integer2 = Integer.valueOf(m), integer3 = Integer.valueOf(i); Object[] arrayOfObject; (arrayOfObject = new Object[3])[0] = integer1; (new Object[3])[1] = integer2; (new Object[3])[2] = integer3; h.a(String.format("bmpSize: %d, w:%d, h:%d", arrayOfObject)); this(); (new RecordTimeBean()).recordStart(); if (paramInt == 1) { arrayOfByte2 = Compress.codeLihu(arrayOfByte4); } else { arrayOfByte2 = Compress.codeESC(arrayOfByte4); }  recordTimeBean1.recordEnd(); h.a("compressTime >>>>> : " + recordTimeBean1.getDurationMs() + " ms"); System.arraycopy(PrinterUtil.intToByteArray4(arrayOfByte2.length), 0, arrayOfByte3, 6, 4); System.arraycopy(arrayOfByte3, 0, arrayOfByte1, 0, b1); int j = arrayOfByte2.length; System.arraycopy(arrayOfByte2, 0, arrayOfByte1, b1, j); byte[] arrayOfByte1; writeFileByteArray(arrayOfByte1 = new byte[b1 + arrayOfByte2.length], m, "imagedata.txt");
/*    */     return new byte[b1 + arrayOfByte2.length];
/*    */   }
/*    */   
/*    */   private static void writeFileByteArray(byte[] paramArrayOfbyte, int paramInt, String paramString) {} }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\PrinterImageProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */