/* 1 */ package com.luckprinter.sdk_new.device.normal;public class A2 extends BaseNormalDevice { public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/* 2 */     printerWakeupLuck();
/*   */     
/* 4 */     setPaperTypeNoCallback(1, 32);
/* 5 */     sendBitmap(paramBitmap);
/* 6 */     printerPositionLuck();
/* 7 */     stopPrintJobLuck(paramResultCallback); }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\A2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */