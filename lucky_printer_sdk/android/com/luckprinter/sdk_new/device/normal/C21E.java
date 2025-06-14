/* 1 */ package com.luckprinter.sdk_new.device.normal;public class C21E extends DP_D1 { public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/* 2 */     printerWakeupLuck();
/* 3 */     printerSetWidth(paramBitmap.getWidth());
/* 4 */     sendBitmap(paramBitmap);
/* 5 */     printLineDotsLuck(getEndLineDot());
/* 6 */     stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperTypeNoCallback(1, 32); printerSetWidth(paramBitmap.getWidth()); sendBitmap(paramBitmap);
/* 7 */     printerPositionLuck();
/* 8 */     stopPrintJobLuck(paramResultCallback); }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\C21E.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */