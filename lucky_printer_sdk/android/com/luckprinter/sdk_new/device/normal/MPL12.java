/*  1 */ package com.luckprinter.sdk_new.device.normal;public class MPL12 extends L12 { public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/*  2 */     printerWakeupLuck();
/*    */     
/*  4 */     sendBitmap(paramBitmap);
/*  5 */     printLineDotsLuck(getEndLineDot());
/*    */     if (paramInt1 == paramInt2) {
/*  7 */       setMarkPrintLast();
/*    */     }
/*  9 */     stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); sendBitmap(paramBitmap); setPaperType(1, 32, null); printerPositionLuck(); if (paramInt1 == paramInt2) { setMarkPrintLast(); printLineDotsLuck(40); }
/*    */     
/* 11 */     stopPrintJobLuck(paramResultCallback); }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\MPL12.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */