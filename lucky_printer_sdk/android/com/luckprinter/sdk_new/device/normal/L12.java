/* 1 */ package com.luckprinter.sdk_new.device.normal;public class L12 extends BaseNormalDevice { public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/* 2 */     printerWakeupLuck();
/* 3 */     sendBitmap(paramBitmap);
/* 4 */     printerPositionLuck(); if (paramInt1 == paramInt2)
/*   */     {
/*   */       
/* 7 */       printLineDotsLuck(40);
/*   */     }
/* 9 */     stopPrintJobLuck(paramResultCallback); }
/*   */ 
/*   */   
/*   */   public int getPrintWidth() {
/*   */     return 96;
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\L12.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */