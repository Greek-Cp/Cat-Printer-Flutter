/*    */ package com.luckprinter.sdk_new.device.normal.a4;
/*  2 */ public class APA49 extends BaseA4Device { public APA49() { setCompress(true);
/*  3 */     setEndLineDot(96);
/*  4 */     setMinDensity(0);
/*  5 */     setMaxDensity(5);
/*  6 */     setSupportSetSpeed(false); } public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 16, null); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot()); stopPrintJobLuck(paramResultCallback); } public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 64, null); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot());
/*  7 */     stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 32, null); if (paramInt1 == 1) {
/*  8 */       adjustPositionAuto(81);
/*    */     }
/* 10 */     sendBitmap(paramBitmap);
/* 11 */     printerPositionLuck(); if (paramInt1 == paramInt2)
/*    */     {
/*    */ 
/*    */       
/* 15 */       adjustPositionAuto(80);
/*    */     }
/* 17 */     stopPrintJobLuck(paramResultCallback); } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 80, null); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) adjustPositionAuto(80);  stopPrintJobLuck(paramResultCallback); }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\a4\APA49.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */