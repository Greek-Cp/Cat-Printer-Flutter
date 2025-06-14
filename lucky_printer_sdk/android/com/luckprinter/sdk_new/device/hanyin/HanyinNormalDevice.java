/*    */ package com.luckprinter.sdk_new.device.hanyin;
/*  2 */ public class HanyinNormalDevice extends BaseNormalDevice { private byte[] hexStringToByteArray(String paramString) { return PrinterUtil.hexStringToByteArray(paramString.toLowerCase().replace(" ", "")); } public void getBatteryLuck(ResultCallback<Integer> paramResultCallback) { byte[] arrayOfByte = hexStringToByteArray("1B 1C 26 20 56 31 20 67 65 74 76 61 6C 20 22 70 72 69 6E 74 65 72 5F 76 6F 6C 74 61 67 65 22 0D 0A"); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null) { float f; paramResultCallback.onSuccess(Integer.valueOf(((f = Float.parseFloat(byteToString(paramArrayOfbyte).trim().replace("V", ""))) > 3.96F) ? 100 : ((f >= 3.84F) ? 75 : ((f >= 3.72F) ? 50 : ((f >= 3.53F) ? 25 : 0))))); } else { paramResultCallback.onFail(); }   }arrayOfByte); } public void printerVersionLuck(ResultCallback<String> paramResultCallback) { byte[] arrayOfByte = hexStringToByteArray("1B 1C 26 20 56 31 20 67 65 74 76 61 6C 20 22 70 72 69 6E 74 65 72 5F 76 65 72 73 69 6F 6E 22 0D 0A"); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null) { String str; setVersion(str = byteToString(paramArrayOfbyte).trim()); paramResultCallback.onSuccess(this); } else { paramResultCallback.onFail(); }   }arrayOfByte); } public void printerModelLuck(ResultCallback<String> paramResultCallback) { byte[] arrayOfByte = hexStringToByteArray("1b 1c 26 20 56 31 20 67 65 74 6B 65 79 0d 0a 01 04 00 20"); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null) { String str; setModel(str = byteToString(paramArrayOfbyte).trim()); paramResultCallback.onSuccess(this); } else { paramResultCallback.onFail(); }   }arrayOfByte); } public void printerSNLuck(ResultCallback<String> paramResultCallback) { byte[] arrayOfByte = hexStringToByteArray("1b 1c 26 20 56 31 20 67 65 74 6B 65 79 0d 0a 01 01 00 20"); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null) { String str; setSn(str = byteToString(paramArrayOfbyte).trim()); paramResultCallback.onSuccess(this); } else { paramResultCallback.onFail(); }   }arrayOfByte); } public void getDensityLuck(ResultCallback<Integer> paramResultCallback) { byte[] arrayOfByte = hexStringToByteArray("1b 1c 26 20 56 31 20 67 65 74 6B 65 79 0d 0a 01 cb 00 01"); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0) { paramResultCallback.onSuccess(Integer.valueOf(paramArrayOfbyte[0])); } else { paramResultCallback.onFail(); }   }this); }
/*  3 */   public void getShutTimeLuck(ResultCallback<Integer> paramResultCallback) { byte[] arrayOfByte = hexStringToByteArray("1b 1c 26 20 56 31 20 67 65 74 6B 65 79 0d 0a 01 91 01 04"); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0) { paramResultCallback.onSuccess(Integer.valueOf(PrinterUtil.byteArray4ToInt(paramArrayOfbyte))); } else { paramResultCallback.onFail(); }   }this); } public void setDensityLuck(int paramInt, ResultCallback<Integer> paramResultCallback) { byte[] arrayOfByte; (arrayOfByte = hexStringToByteArray("1B 1C 26 20 56 31 20 73 65 74 6B 65 79 0D 0A 01 cb 00 01 00"))[19] = (byte)(paramInt & 0xFF); d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0) { if (PrinterUtil.compareBytes(new byte[] { -53, 0, 0 }, paramArrayOfbyte)) { ResultCallback<Integer> resultCallback; super(this, paramResultCallback); saveSetting(resultCallback); } else { paramResultCallback.onFail(); }  } else { paramResultCallback.onFail(); }   }arrayOfByte); } public void setShutTimeLuck(int paramInt, ResultCallback<Integer> paramResultCallback) {
/*  4 */     arrayOfByte2[19] = arrayOfByte1[3];
/*  5 */     arrayOfByte2[20] = arrayOfByte1[2];
/*  6 */     arrayOfByte2[21] = arrayOfByte1[1]; byte[] arrayOfByte1, arrayOfByte2;
/*  7 */     (arrayOfByte2 = hexStringToByteArray("1B 1C 26 20 56 31 20 73 65 74 6B 65 79 0D 0A 01 91 01 0400 00 00 00"))[22] = (arrayOfByte1 = PrinterUtil.intToByteArray4(paramInt))[0];
/*  8 */     d.b().a(paramArrayOfbyte -> { if (paramResultCallback != null) if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0) { if (PrinterUtil.compareBytes(new byte[] { -111, 1, 0 }, paramArrayOfbyte)) { ResultCallback<Integer> resultCallback; super(this, paramResultCallback); saveSetting(resultCallback); } else { paramResultCallback.onFail(); }  } else { paramResultCallback.onFail(); }   }arrayOfByte2); } public void printerStatusLuck(final ResultCallback<PrinterStatusData> callback) { byte[] arrayOfByte = hexStringToByteArray("1D 61 01"); d.b().a(new ReadDataCallback() { public void onReadData(byte[] param1ArrayOfbyte) { if (param1ArrayOfbyte != null && param1ArrayOfbyte.length == 4) { PrinterStatusData printerStatusData; this(); if ((param1ArrayOfbyte[0] & 0x20) > 0) printerStatusData.setIsOpen(1);  if ((param1ArrayOfbyte[2] & 0xC) == 12)
/*  9 */                 printerStatusData.setIsLackPaper(1);  callback.onSuccess(printerStatusData); }
/*    */             else
/* 11 */             { callback.onFail(); }
/*    */              }
/*    */         
/*    */         },  arrayOfByte); }
/*    */ 
/*    */   
/*    */   public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     setPaperType(0, null);
/*    */     saveSetting(null);
/*    */     sendBitmap(paramBitmap);
/*    */     printLineDotsLuck(getEndLineDot());
/*    */     paramResultCallback.onSuccess(Integer.valueOf(1));
/*    */   }
/*    */   
/*    */   public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     setPaperType(2, null);
/*    */     saveSetting(null);
/*    */     sendBitmap(paramBitmap);
/*    */     printerPositionLuck();
/*    */     paramResultCallback.onSuccess(Integer.valueOf(1));
/*    */   }
/*    */   
/*    */   public void printerPositionLuck() {
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[1])[0] = 12;
/*    */     d.b().a(null, this);
/*    */   }
/*    */   
/*    */   public void setPaperType(int paramInt, ResultCallback<byte[]> paramResultCallback) {
/*    */     byte[] arrayOfByte1 = hexStringToByteArray("1b 1c 26 20 56 31 20 73 65 74 6b 65  79 0d 0a 01 ce 00 01 ");
/*    */     paramInt = (byte)paramInt;
/*    */     byte[] arrayOfByte2;
/*    */     (arrayOfByte2 = new byte[1])[0] = paramInt;
/*    */     arrayOfByte1 = PrinterUtil.combineBytes(new byte[][] { this, arrayOfByte2 });
/*    */     d.b().a(paramArrayOfbyte -> {
/*    */           if (paramResultCallback != null)
/*    */             if (paramArrayOfbyte != null) {
/*    */               paramResultCallback.onSuccess(paramArrayOfbyte);
/*    */             } else {
/*    */               paramResultCallback.onFail();
/*    */             }  
/*    */         }this);
/*    */   }
/*    */   
/*    */   public void saveSetting(ResultCallback<Integer> paramResultCallback) {
/*    */     byte[] arrayOfByte = hexStringToByteArray("1B 1C 26 20 56 31 20 64 6F 20 22 73 61 76 65 5F 70 61 72 61 6D 5F 7A 6F 6E 65 22 0D 0A ");
/*    */     d.b().a(paramArrayOfbyte -> {
/*    */           if (paramResultCallback != null)
/*    */             if (paramArrayOfbyte != null && paramArrayOfbyte.length == 1 && paramArrayOfbyte[0] == 0) {
/*    */               paramResultCallback.onSuccess(Integer.valueOf(1));
/*    */             } else {
/*    */               paramResultCallback.onFail();
/*    */             }  
/*    */         }this);
/*    */   }
/*    */   
/*    */   public void updatePrinterLuck(File paramFile, UpdateListener paramUpdateListener) {
/*    */     if (paramFile == null || !paramFile.exists())
/*    */       return; 
/*    */     super(this, paramFile, paramUpdateListener);
/*    */     Runnable runnable;
/*    */     (d.b()).a.a(runnable);
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\hanyin\HanyinNormalDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */