/* 1 */ package com.luckprinter.sdk_new.device.normal;public class GrayPPS1 extends BaseNormalDevice { public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/* 2 */     printerWakeupLuck();
/*   */     
/* 4 */     sendBitmap(paramBitmap, true);
/* 5 */     printLineDotsLuck(getEndLineDot());
/*   */     if (paramInt1 == paramInt2) {
/* 7 */       setMarkPrintLast();
/*   */     }
/* 9 */     stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 32, null); sendBitmap(paramBitmap, true); printerPositionLuck(); if (paramInt1 == paramInt2) setMarkPrintLast();  stopPrintJobLuck(paramResultCallback); } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 80, null); sendBitmap(paramBitmap, true); printerPositionLuck(); if (paramInt1 == paramInt2) setMarkPrintLast();  stopPrintJobLuck(paramResultCallback); }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\GrayPPS1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */