/*   */ package com.luckprinter.sdk_new.device.normal;
/* 2 */ public class DP_S1 extends BaseNormalDevice { public DP_S1() { setEndLineDot(112); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck();
/*   */     
/* 4 */     setPaperTypeNoCallback(1, 32);
/* 5 */     sendBitmap(paramBitmap);
/* 6 */     printerPositionLuck();
/* 7 */     stopPrintJobLuck(paramResultCallback); }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\DP_S1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */