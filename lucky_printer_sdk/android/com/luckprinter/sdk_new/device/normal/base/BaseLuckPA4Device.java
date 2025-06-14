/* 1 */ package com.luckprinter.sdk_new.device.normal.base;public class BaseLuckPA4Device extends BaseA4Device { public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck();
/* 2 */     printerWakeupLuck();
/* 3 */     sendBitmap(paramBitmap);
/* 4 */     printLineDotsLuck(getEndLineDot());
/* 5 */     stopPrintJobLuck(paramResultCallback); } public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot()); stopPrintJobLuck(paramResultCallback); } public void printFolderOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); sendBitmap(paramBitmap); printerPositionLuck(); stopPrintJobLuck(paramResultCallback); }
/*   */ 
/*   */   
/*   */   public int getPrintMaxWidth() {
/*   */     return is300Dpi() ? 2400 : 1616;
/*   */   }
/*   */   
/*   */   public int getA4PrintWidth() {
/*   */     return is300Dpi() ? 2400 : 1616;
/*   */   }
/*   */   
/*   */   public int getA4PrintHeight() {
/*   */     return is300Dpi() ? 3480 : 2300;
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\BaseLuckPA4Device.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */