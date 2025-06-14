/*   */ package com.luckprinter.sdk_new.device.normal;
/* 2 */ public class MPL15 extends BaseNormalDevice { public MPL15() { setEnablePrinterMode(2);
/* 3 */     setMinDensity(0);
/* 4 */     setMaxDensity(15); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2)
/*   */     {
/* 6 */       setMarkPrintLast();
/*   */     }
/* 8 */     stopPrintJobLuck(paramResultCallback); }
/*   */ 
/*   */   
/*   */   public int getPrintWidth() {
/*   */     return 96;
/*   */   }
/*   */   
/*   */   public void initAfterConnect() {
/*   */     super.initAfterConnect();
/*   */     super(this);
/*   */     ResultCallback<Integer> resultCallback;
/*   */     getTimeFormat(resultCallback);
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\MPL15.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */