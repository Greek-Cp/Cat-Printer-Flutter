/*    */ package com.luckprinter.sdk_new.device.normal.a4;
/*  2 */ public class APL86 extends BaseA4Device { public APL86() { setCompress(true);
/*  3 */     setCompressWay(1); } public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setPaperType(1, 64, null); enablePrinterLuck(); printerWakeupLuck();
/*  4 */     sendBitmap(paramBitmap);
/*  5 */     printLineDotsLuck(getEndLineDot());
/*  6 */     stopPrintJobLuck(paramResultCallback); } public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setPaperType(1, 16, null); enablePrinterLuck(); printerWakeupLuck(); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot()); stopPrintJobLuck(paramResultCallback); } public void printFolderOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setPaperType(1, 48, null); enablePrinterLuck(); printerWakeupLuck(); adjustPositionAuto(81); sendBitmap(paramBitmap);
/*  7 */     printerPositionLuck();
/*    */     
/*  9 */     adjustPositionAuto(80);
/* 10 */     stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setPaperType(1, 32, null); enablePrinterLuck(); printerWakeupLuck(); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2)
/*    */     {
/*    */ 
/*    */       
/* 14 */       adjustPositionAuto(80);
/*    */     }
/* 16 */     stopPrintJobLuck(paramResultCallback); } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { setPaperType(1, 80, null); enablePrinterLuck(); printerWakeupLuck(); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) adjustPositionAuto(80);  stopPrintJobLuck(paramResultCallback); }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\a4\APL86.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */