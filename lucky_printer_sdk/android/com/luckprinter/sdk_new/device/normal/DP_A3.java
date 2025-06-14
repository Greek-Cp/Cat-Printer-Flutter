/* 1 */ package com.luckprinter.sdk_new.device.normal;public class DP_A3 extends BaseNormalDevice implements ISupportSelectPaper { public static final int PAPER_SIZE_56 = 56; public DP_A3() { this
/* 2 */       .selectPaperSize = 77;
/*   */ 
/*   */     
/* 5 */     setEndLineDot(120);
/* 6 */     setCompress(true);
/* 7 */     setHasFirmwareEraseCallbackData(true); }
/* 8 */   public static final int PAPER_SIZE_77 = 77; protected int selectPaperSize; public boolean is300Dpi() { return true; } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) printLineDotsLuck(60);  stopPrintJobLuck(paramResultCallback); }
/*   */ 
/*   */   
/*   */   public int getPrintWidth() {
/*   */     return (this.selectPaperSize == 56) ? 576 : 864;
/*   */   }
/*   */   
/*   */   public int getPrintMaxWidth() {
/*   */     return 864;
/*   */   }
/*   */   
/*   */   public void setSelectPaperSize(int paramInt) {
/*   */     this.selectPaperSize = paramInt;
/*   */   }
/*   */   
/*   */   public int getSelectPaperSize() {
/*   */     return this.selectPaperSize;
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\DP_A3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */