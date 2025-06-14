/*    */ package com.luckprinter.sdk_new.device.normal;
/*  2 */ public class DP_Y50 extends BaseNormalDevice { public DP_Y50() { setMinDensity(0);
/*  3 */     setMaxDensity(16);
/*  4 */     setCompress(true);
/*  5 */     setTagNeedAdjustPaper(true);
/*  6 */     setEndLineDot(140); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); if (paramInt1 == 1)
/*  7 */       adjustPositionAuto(81);  sendBitmap(paramBitmap);
/*  8 */     printerPositionLuck(); if (paramInt1 == paramInt2)
/*    */     {
/*    */       
/* 11 */       adjustPositionAuto(80);
/*    */     }
/* 13 */     stopPrintJobLuckNoCallback(); }
/*    */ 
/*    */   
/*    */   public void printTag(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback) {
/*    */     for (byte b = 1; b <= paramInt; ) {
/*    */       paramResultPageCallback.onPageIndexStart(paramBitmap, b, paramInt);
/*    */       printTagOnce(paramBitmap, b, paramInt, null);
/*    */       paramResultPageCallback.onPageIndexEnd(paramBitmap, b, paramInt);
/*    */       b++;
/*    */     } 
/*    */     paramResultPageCallback.onSuccess(Integer.valueOf(0));
/*    */   }
/*    */   
/*    */   public void printReverseLineDotsLuck(int paramInt) {
/*    */     byte b = (byte)paramInt;
/*    */     byte[] arrayOfByte;
/*    */     (arrayOfByte = new byte[4])[0] = 31;
/*    */     (new byte[4])[1] = 17;
/*    */     (new byte[4])[2] = 16;
/*    */     (new byte[4])[3] = b;
/*    */     d.b().a(null, arrayOfByte);
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\DP_Y50.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */