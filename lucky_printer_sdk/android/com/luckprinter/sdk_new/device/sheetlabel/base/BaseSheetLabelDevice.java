/*  1 */ package com.luckprinter.sdk_new.device.sheetlabel.base;public class BaseSheetLabelDevice extends BaseDevice implements ISheetLabelDeviceOperation { public static final String TAG = "BaseSheetLabelDevice"; private int speed; private int density; public void setPrintCount(int paramInt) { String str = "PRINT " + paramInt + ",1\r\n"; d.b().a(null, stringToByte(str)); } public void printerModelLuck(final ResultCallback<String> callback) { byte[] arrayOfByte; (arrayOfByte = new byte[3])[0] = 31; (new byte[3])[1] = -80; (new byte[3])[2] = 0; d.b().a(new ReadDataCallback() { public void onReadData(byte[] param1ArrayOfbyte) { ResultCallback resultCallback; if ((resultCallback = callback) != null) if (param1ArrayOfbyte != null && param1ArrayOfbyte.length > 0) { String str = BaseSheetLabelDevice.this.byteToString(param1ArrayOfbyte).trim(); BaseSheetLabelDevice.this.setModel(this); callback.onSuccess(this); } else { resultCallback.onFail(); }   } }, arrayOfByte); } public void initAfterConnect() { super.initAfterConnect();
/*  2 */     List<Integer> list = getSpeedList();
/*    */     
/*  4 */     int i = list.size() / 2; List<?> list1;
/*  5 */     int j = (list1 = getDensityList()).size() / 2;
/*  6 */     if (!getDensityList().isEmpty()) this
/*  7 */         .density = ((Integer)list1.get(j)).intValue();
/*    */     
/*  9 */     if (!list.isEmpty()) this
/* 10 */         .speed = ((Integer)list.get(i)).intValue();  } public void printerVersionLuck(final ResultCallback<String> callback) { byte[] arrayOfByte; (arrayOfByte = new byte[3])[0] = 31; (new byte[3])[1] = -80; (new byte[3])[2] = 4; d.b().a(new ReadDataCallback() { public void onReadData(byte[] param1ArrayOfbyte) { ResultCallback resultCallback; if ((resultCallback = callback) != null) if (param1ArrayOfbyte != null && param1ArrayOfbyte.length > 0) { String str = BaseSheetLabelDevice.this.byteToString(param1ArrayOfbyte).trim(); BaseSheetLabelDevice.this.setVersion(this); callback.onSuccess(this); } else { resultCallback.onFail(); }   } }, arrayOfByte); } public void printerSNLuck(final ResultCallback<String> callback) { byte[] arrayOfByte; (arrayOfByte = new byte[3])[0] = 31; (new byte[3])[1] = -80; (new byte[3])[2] = 6; d.b().a(new ReadDataCallback() { public void onReadData(byte[] param1ArrayOfbyte) { ResultCallback resultCallback; if ((resultCallback = callback) != null) if (param1ArrayOfbyte != null && param1ArrayOfbyte.length > 0) { String str = BaseSheetLabelDevice.this.byteToString(param1ArrayOfbyte).trim(); BaseSheetLabelDevice.this.setSn(this); callback.onSuccess(this); } else { resultCallback.onFail(); }   } }, arrayOfByte); } public void printerStatusLuck(final ResultCallback<PrinterStatusData> callback) { byte[] arrayOfByte; (arrayOfByte = new byte[2])[0] = 27; (new byte[2])[1] = 104; d.b().a(new ReadDataCallback() { public void onReadData(byte[] param1ArrayOfbyte) { ResultCallback resultCallback; if ((resultCallback = callback) != null) { PrinterStatusData printerStatusData; if (param1ArrayOfbyte != null && param1ArrayOfbyte.length > 0) { this(); if ((param1ArrayOfbyte[0] & 0x1) > 0) printerStatusData.setIsPrinting(1);  if ((param1ArrayOfbyte[0] & 0x2) > 0) printerStatusData.setIsOpen(1);  if ((param1ArrayOfbyte[0] & 0x4) > 0) printerStatusData
/* 11 */                     .setIsLackPaper(1);
/*    */                 
/* 13 */                 callback.onSuccess(printerStatusData); } else { printerStatusData
/*    */                   
/* 15 */                   .onFail(); }  }  } }, arrayOfByte); } public int getUploadErrorCode(byte[] paramArrayOfbyte) { return UploadErrorCode.getUploadErrorCode(paramArrayOfbyte); } public void setDensityLuck(int paramInt, ResultCallback<Integer> paramResultCallback) { this.density = formatDensity(paramInt); if (paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(1));  } public void setSpeedLuck(int paramInt, ResultCallback<Integer> paramResultCallback) { this.speed = formatSpeed(paramInt); if (paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(1));  } public void getDensityLuck(ResultCallback<Integer> paramResultCallback) { int i = formatDensity(this.density); if (paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(i));  } public void getSpeedLuck(ResultCallback<Integer> paramResultCallback) { int i = formatSpeed(this.speed); if (paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(i));  } public void print(Bitmap paramBitmap, int paramInt) { int i = getDpiMultiplier(); int j = paramBitmap.getWidth() / i; i = paramBitmap.getHeight() / i; int k = this.speed, m = this.density; printTag(j, i, k, m, paramBitmap, paramInt); } public void printOnce(Bitmap paramBitmap) { print(paramBitmap, 1); } public void printTag(Bitmap paramBitmap, int paramInt) { int i = getDpiMultiplier(); int j = paramBitmap.getWidth() / i; i = paramBitmap.getHeight() / i; int k = this.speed, m = this.density; printTag(j, i, k, m, paramBitmap, paramInt); }
/*    */   public void printTagOnce(Bitmap paramBitmap) { printTag(paramBitmap, 1); }
/* 17 */   public void drawPic(Bitmap paramBitmap) { if (paramBitmap == null || paramBitmap.isRecycled()) return;  int j = paramBitmap.getHeight(); int i; if ((i = paramBitmap.getWidth()) % 8 == 0) { i /= 8; } else { i = i / 8 + 1; }  byte[] arrayOfByte1 = getBitmapByteArrayReverse(paramBitmap);
/* 18 */     byte[] arrayOfByte2 = "\r\n".getBytes(StandardCharsets.UTF_8);
/* 19 */     arrayOfByte1 = appendByte(("BITMAP 0,0," + i + "," + j + ",1,").getBytes(StandardCharsets.UTF_8), arrayOfByte1);
/* 20 */     d.b().a(null, appendByte(arrayOfByte1, arrayOfByte2)); } public void drawPicZLib(Bitmap paramBitmap) { byte[] arrayOfByte3; if (paramBitmap == null || paramBitmap.isRecycled())
/* 21 */       return;  int j = paramBitmap.getHeight(); int i; if ((i = paramBitmap.getWidth()) % 8 == 0) { i /= 8; } else { i = i / 8 + 1; }  byte[] arrayOfByte1 = getBitmapByteArray(paramBitmap); try { this(); d.b(); d.a = arrayOfByte1;
/* 22 */       d.c = arrayOfByte1.length;
/*    */ 
/*    */ 
/*    */       
/* 26 */       d
/* 27 */         .e = arrayOfByte3;
/* 28 */       d.g = k;
/*    */ 
/*    */       
/* 31 */       d.a();
/*    */       int k;
/* 33 */       arrayOfByte3 = new byte[k = (int)d.h];
/* 34 */       System.arraycopy(arrayOfByte3 = new byte[k = arrayOfByte1.length], 0, arrayOfByte3, 0, k);
/*    */       
/* 36 */       Integer integer1 = Integer.valueOf(arrayOfByte1.length), integer2 = Integer.valueOf((int)d.h); System.out.println(String.format("字节数, 压缩前:%d 压缩后:%d", new Object[] { integer1, integer2 })); c c; d d;
/* 37 */       if ((c = (d = new d()).i) != null)
/* 38 */       { int m; if ((m = c.b) == 42 || m == 113 || m == 666) { c
/*    */ 
/*    */ 
/*    */             
/* 42 */             .c = null;
/* 43 */           c.T = null;
/* 44 */           c.o = null;
/* 45 */           c.n = null;
/* 46 */           c.l = null; }  d
/* 47 */           .i = null; }  } catch (Exception exception) { exception
/* 48 */         .printStackTrace(); arrayOfByte3 = null; }
/*    */ 
/*    */ 
/*    */     
/* 52 */     arrayOfByte1 = "\r\n".getBytes(StandardCharsets.UTF_8);
/* 53 */     byte[] arrayOfByte2 = appendByte(("BITMAP 0,0," + i + "," + j + ",3," + arrayOfByte3.length + ",").getBytes(StandardCharsets.UTF_8), arrayOfByte3);
/* 54 */     d.b().a(null, appendByte(arrayOfByte2, arrayOfByte1)); }
/*    */ 
/*    */   
/*    */   public void drawPicAngyin(Bitmap paramBitmap) {
/*    */     if (paramBitmap == null || paramBitmap.isRecycled())
/*    */       return; 
/*    */     int j = paramBitmap.getHeight();
/*    */     int i;
/*    */     if ((i = paramBitmap.getWidth()) % 8 == 0) {
/*    */       i /= 8;
/*    */     } else {
/*    */       i = i / 8 + 1;
/*    */     } 
/*    */     byte[] arrayOfByte1 = Code941.code(getBitmapByteArray(paramBitmap));
/*    */     byte[] arrayOfByte2 = "\r\n".getBytes(StandardCharsets.UTF_8);
/*    */     arrayOfByte1 = appendByte(("BITMAP 0,0," + i + "," + j + ",3," + arrayOfByte1.length + ",").getBytes(StandardCharsets.UTF_8), arrayOfByte1);
/*    */     d.b().a(null, appendByte(arrayOfByte1, arrayOfByte2));
/*    */   }
/*    */   
/*    */   public BaseSheetLabelDevice() {
/*    */     this.speed = 8;
/*    */     this.density = 1;
/*    */     setMinDensity(0);
/*    */     setMaxDensity(15);
/*    */     setMinSpeed(1);
/*    */     setMaxSpeed(8);
/*    */     setSupportSetSpeed(true);
/*    */   }
/*    */   
/*    */   private void createPage(int paramInt1, int paramInt2) {
/*    */     String str = "SIZE " + paramInt1 + " mm," + paramInt2 + " mm\r\n";
/*    */     d.b().a(null, stringToByte(str));
/*    */   }
/*    */   
/*    */   private void clear() {
/*    */     d.b().a(null, stringToByte("CLS\r\n"));
/*    */   }
/*    */   
/*    */   private void setGap(int paramInt1, int paramInt2) {
/*    */     String str = "GAP " + paramInt1 + " mm," + paramInt2 + " mm\r\n";
/*    */     d.b().a(null, stringToByte(str));
/*    */   }
/*    */   
/*    */   private void setDensity(int paramInt) {
/*    */     paramInt = formatDensity(paramInt);
/*    */     String str = "DENSITY " + paramInt + "\r\n";
/*    */     d.b().a(null, stringToByte(str));
/*    */   }
/*    */   
/*    */   private void setSpeed(int paramInt) {
/*    */     paramInt = formatSpeed(paramInt);
/*    */     String str = "SPEED " + paramInt + "\r\n";
/*    */     d.b().a(null, stringToByte(str));
/*    */   }
/*    */   
/*    */   private byte[] appendByte(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
/*    */     int i = paramArrayOfbyte1.length;
/*    */     System.arraycopy(paramArrayOfbyte1, 0, this, 0, i);
/*    */     i = paramArrayOfbyte1.length;
/*    */     int j = paramArrayOfbyte2.length;
/*    */     System.arraycopy(paramArrayOfbyte2, 0, this, i, j);
/*    */     byte[] arrayOfByte;
/*    */     return arrayOfByte = new byte[paramArrayOfbyte1.length + paramArrayOfbyte2.length];
/*    */   }
/*    */   
/*    */   private byte[] getBitmapByteArrayReverse(Bitmap paramBitmap) {
/*    */     int k, j, arrayOfInt[];
/*    */     paramBitmap.getPixels(arrayOfInt = new int[i * (j = paramBitmap.getHeight())], 0, i, 0, 0, i, j);
/*    */     int i;
/*    */     if ((i = paramBitmap.getWidth()) % 8 != 0) {
/*    */       k = i / 8 + 1;
/*    */     } else {
/*    */       k = i / 8;
/*    */     } 
/*    */     byte[] arrayOfByte;
/*    */     int m;
/*    */     byte b;
/*    */     for (arrayOfByte = new byte[k * j], m = 0, b = 0; b < j; ) {
/*    */       for (byte b1 = 0; b1 < k; ) {
/*    */         byte b2;
/*    */         int n;
/*    */         for (b2 = 0, n = 0; n < 8; ) {
/*    */           int i1 = Color.green(i1);
/*    */           int i2 = Color.blue(i1);
/*    */           Color.alpha(i1);
/*    */           if ((i1 = b1 * 8 + n) < i && (Color.red(i1 = arrayOfInt[b * i + i1]) + i1 + i2) / 3 >= 128)
/*    */             b2 = (byte)(b2 | (byte)(128 >> n)); 
/*    */           n++;
/*    */         } 
/*    */         n = m + 1;
/*    */         arrayOfByte[m] = b2;
/*    */         b1++;
/*    */         m = n;
/*    */       } 
/*    */       b++;
/*    */     } 
/*    */     return arrayOfByte;
/*    */   }
/*    */   
/*    */   private byte[] getBitmapByteArray(Bitmap paramBitmap) {
/*    */     int k, j, arrayOfInt[];
/*    */     paramBitmap.getPixels(arrayOfInt = new int[i * (j = paramBitmap.getHeight())], 0, i, 0, 0, i, j);
/*    */     int i;
/*    */     if ((i = paramBitmap.getWidth()) % 8 != 0) {
/*    */       k = i / 8 + 1;
/*    */     } else {
/*    */       k = i / 8;
/*    */     } 
/*    */     byte[] arrayOfByte;
/*    */     int m;
/*    */     byte b;
/*    */     for (arrayOfByte = new byte[k * j], m = 0, b = 0; b < j; ) {
/*    */       for (byte b1 = 0; b1 < k; ) {
/*    */         byte b2;
/*    */         int n;
/*    */         for (b2 = 0, n = 0; n < 8; ) {
/*    */           int i1 = Color.green(i1);
/*    */           int i2 = Color.blue(i1);
/*    */           Color.alpha(i1);
/*    */           if ((i1 = b1 * 8 + n) < i && (Color.red(i1 = arrayOfInt[b * i + i1]) + i1 + i2) / 3 < 128)
/*    */             b2 = (byte)(b2 | (byte)(128 >> n)); 
/*    */           n++;
/*    */         } 
/*    */         n = m + 1;
/*    */         arrayOfByte[m] = b2;
/*    */         b1++;
/*    */         m = n;
/*    */       } 
/*    */       b++;
/*    */     } 
/*    */     return arrayOfByte;
/*    */   }
/*    */   
/*    */   private byte[] stringToByte(String paramString) {
/*    */     return paramString.getBytes(StandardCharsets.UTF_8);
/*    */   }
/*    */   
/*    */   public void printTag(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Bitmap paramBitmap, int paramInt5) {
/*    */     if (paramBitmap == null || paramBitmap.isRecycled())
/*    */       return; 
/*    */     createPage(paramInt1, paramInt2);
/*    */     setGap(2, 0);
/*    */     clear();
/*    */     if (isSupportSetSpeed())
/*    */       setSpeed(paramInt3); 
/*    */     setDensity(paramInt4);
/*    */     drawPic(paramBitmap);
/*    */     setPrintCount(paramInt5);
/*    */   }
/*    */   
/*    */   private int formatDensity(int paramInt) {
/*    */     return paramInt;
/*    */   }
/*    */   
/*    */   private int formatSpeed(int paramInt) {
/*    */     return paramInt;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\sheetlabel\base\BaseSheetLabelDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */