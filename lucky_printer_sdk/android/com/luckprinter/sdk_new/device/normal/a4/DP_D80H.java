/*  1 */ package com.luckprinter.sdk_new.device.normal.a4;public class DP_D80H extends DP_D80 { public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 16, null); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot()); stopPrintJobLuck(paramResultCallback); } public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/*  2 */     printerWakeupLuck();
/*    */     
/*  4 */     setPaperType(1, 64, null);
/*  5 */     sendBitmap(paramBitmap);
/*  6 */     printLineDotsLuck(getEndLineDot());
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
/*    */ 
/*    */   
/*    */   public boolean is300Dpi() {
/*    */     return true;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\a4\DP_D80H.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */