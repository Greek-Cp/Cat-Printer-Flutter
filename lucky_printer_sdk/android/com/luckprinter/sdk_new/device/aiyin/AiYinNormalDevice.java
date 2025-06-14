/*  1 */ package com.luckprinter.sdk_new.device.aiyin;public class AiYinNormalDevice extends BaseNormalDevice { public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setLabelPaper(2);
/*  2 */     printerWakeupLuck();
/*  3 */     enablePrinterLuck();
/*  4 */     sendBitmap(paramBitmap);
/*  5 */     printerPositionLuck();
/*  6 */     stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setLabelPaper(0); printerWakeupLuck(); enablePrinterLuck(); sendBitmap(paramBitmap); printerPositionLuck(); stopPrintJobLuck(paramResultCallback); } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setLabelPaper(1); printerWakeupLuck(); enablePrinterLuck(); sendBitmap(paramBitmap); printerPositionLuck(); stopPrintJobLuck(paramResultCallback); }
/*    */   public void enablePrinterLuck() { byte[] arrayOfByte; (arrayOfByte = new byte[4])[0] = 16; (new byte[4])[1] = -1;
/*    */     (new byte[4])[2] = -2;
/*    */     (new byte[4])[3] = 1;
/*    */     d.b().a(null, this); } public void stopPrintJobLuck(ResultCallback<Integer> paramResultCallback) { super(this);
/*    */     IReceiveDataClaud iReceiveDataClaud;
/* 12 */     d d = d.b(); byte[] arrayOfByte; (arrayOfByte = new byte[4])[0] = 16; (new byte[4])[1] = -1; (new byte[4])[2] = -2; (new byte[4])[3] = 69; ReadDataCallback readDataCallback = paramArrayOfbyte -> { if (paramResultCallback != null) if (paramIReceiveDataClaud.isFilter(paramArrayOfbyte)) { paramResultCallback.onSuccess(Integer.valueOf(1)); } else { paramResultCallback.onFail(); }   }; byte b = 60; this
/* 13 */       .a
/* 14 */       .b(iReceiveDataClaud, arrayOfByte, readDataCallback, b); }
/*    */ 
/*    */   
/*    */   public void setLabelPaper(int paramInt) {
/*    */     byte b = (byte)paramInt;
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[4])[0] = 16;
/*    */     (new byte[4])[1] = -1;
/*    */     (new byte[4])[2] = -124;
/*    */     (new byte[4])[3] = b;
/*    */     d.b().a(null, arrayOfByte);
/*    */   }
/*    */   
/*    */   public void updatePrinterLuck(File paramFile, UpdateListener paramUpdateListener) {
/*    */     if (paramFile == null || !paramFile.exists())
/*    */       return; 
/*    */     super(this, paramFile, paramUpdateListener);
/*    */     Runnable runnable;
/*    */     (d.b()).a.a(runnable);
/*    */   }
/*    */   
/*    */   public int getUploadErrorCode(byte[] paramArrayOfbyte) {
/*    */     return AiYinUploadErrorCode.getUploadErrorCode(paramArrayOfbyte);
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\AiYinNormalDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */