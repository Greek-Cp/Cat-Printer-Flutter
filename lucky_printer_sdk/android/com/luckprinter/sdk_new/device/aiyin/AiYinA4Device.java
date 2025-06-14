/*    */ package com.luckprinter.sdk_new.device.aiyin;
/*  2 */ public class AiYinA4Device extends BaseA4Device { public AiYinA4Device() { setCompress(false); }
/*    */    private void sendBitmapWaitOk(Bitmap paramBitmap, ResultCallback<Integer> paramResultCallback) {
/*    */     super(this);
/*    */     IReceiveDataClaud iReceiveDataClaud;
/*    */     ReadDataCallback readDataCallback;
/*    */     super(this, paramResultCallback);
/*    */     sendBitmap(iReceiveDataClaud, paramBitmap, readDataCallback, false, 1);
/*    */   } public void stopPrintJobLuck(ResultCallback<Integer> paramResultCallback) {
/*    */     super(this);
/*    */     IReceiveDataClaud iReceiveDataClaud;
/* 12 */     d d = d.b(); byte[] arrayOfByte; (arrayOfByte = new byte[4])[0] = 16; (new byte[4])[1] = -1; (new byte[4])[2] = -2; (new byte[4])[3] = 69; ReadDataCallback readDataCallback = paramArrayOfbyte -> { if (paramResultCallback != null) if (paramIReceiveDataClaud.isFilter(paramArrayOfbyte)) { paramResultCallback.onSuccess(Integer.valueOf(1)); } else { paramResultCallback.onFail(); }   }; byte b = 60; this
/* 13 */       .a
/* 14 */       .b(iReceiveDataClaud, arrayOfByte, readDataCallback, b);
/*    */   }
/*    */   
/*    */   private void setA40aPaperTypeNoCallback(int paramInt) {
/*    */     byte b = (byte)paramInt;
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[5])[0] = 16;
/*    */     (new byte[5])[1] = -1;
/*    */     (new byte[5])[2] = 16;
/*    */     (new byte[5])[3] = 3;
/*    */     (new byte[5])[4] = b;
/*    */     d.b().a(null, arrayOfByte);
/*    */   }
/*    */   
/*    */   public void enablePrinterLuck() {
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[4])[0] = 16;
/*    */     (new byte[4])[1] = -1;
/*    */     (new byte[4])[2] = -2;
/*    */     (new byte[4])[3] = 1;
/*    */     d.b().a(null, this);
/*    */   }
/*    */   
/*    */   public void stopPrintJobLuckSimple() {
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[4])[0] = 16;
/*    */     (new byte[4])[1] = -1;
/*    */     (new byte[4])[2] = -2;
/*    */     (new byte[4])[3] = 69;
/*    */     d.b().a(null, this);
/*    */   }
/*    */   
/*    */   public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     setA40aPaperTypeNoCallback(1);
/*    */     printerWakeupLuck();
/*    */     enablePrinterLuck();
/*    */     super(this, paramResultCallback);
/*    */     ResultCallback<Integer> resultCallback;
/*    */     sendBitmapWaitOk(paramBitmap, resultCallback);
/*    */   }
/*    */   
/*    */   public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     setA40aPaperTypeNoCallback(2);
/*    */     printerWakeupLuck();
/*    */     enablePrinterLuck();
/*    */     super(this, paramResultCallback);
/*    */     ResultCallback<Integer> resultCallback;
/*    */     sendBitmapWaitOk(paramBitmap, resultCallback);
/*    */   }
/*    */   
/*    */   public void printFolderOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     setA40aPaperTypeNoCallback(3);
/*    */     printerWakeupLuck();
/*    */     enablePrinterLuck();
/*    */     super(this, paramResultCallback);
/*    */     ResultCallback<Integer> resultCallback;
/*    */     sendBitmapWaitOk(paramBitmap, resultCallback);
/*    */   }
/*    */   
/*    */   public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     setA40aPaperTypeNoCallback(4);
/*    */     printerWakeupLuck();
/*    */     enablePrinterLuck();
/*    */     super(this, paramResultCallback);
/*    */     ResultCallback<Integer> resultCallback;
/*    */     sendBitmapWaitOk(paramBitmap, resultCallback);
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


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\AiYinA4Device.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */