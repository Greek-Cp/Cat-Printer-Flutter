/*  1 */ package com.luckprinter.sdk_new.device.normal.a4;public class DP_D80 extends BaseA4Device { private int heatingLevelVar; private ArrayList<String[]> snRangeList; private String sn; public DP_D80() { ArrayList arrayList; this
/*  2 */       .heatingLevelVar = 9;
/*  3 */     this(); this.snRangeList = this;
/*  4 */     this.sn = null;
/*    */ 
/*    */     
/*  7 */     setCompress(true);
/*  8 */     setCompressWay(1); }
/*    */   private boolean isSnInRange(String paramString) { ArrayList<String[]> arrayList; if (paramString == null || (arrayList = this.snRangeList) == null || arrayList.isEmpty()) return false;  for (Iterator<String> iterator = this.snRangeList.iterator(); hasNext();) { if ((arrayOfString = (String[])next()) != null && arrayOfString.length == 2 && paramString.compareTo(arrayOfString[0]) >= 0 && paramString.compareTo(arrayOfString[1]) <= 0) return true;  }  return false; }
/* 10 */   public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { if (this.sn != null) { doPrintTattooOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback); } else { ResultCallback<String> resultCallback; super(this, paramBitmap, paramInt1, paramInt2, paramResultCallback); printerSNLuck(resultCallback); }  } private void doPrintTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); if (isSnInRange(this.sn)) { setPaperType(1, 16, null); setHeatingLevel(this.heatingLevelVar, null); } else { setPaperType(1, 64, null); }
/*    */     
/* 12 */     sendBitmap(paramBitmap);
/* 13 */     printLineDotsLuck(getEndLineDot());
/* 14 */     stopPrintJobLuck(paramResultCallback); }
/* 15 */   public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 16, null); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot()); stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 32, null); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) adjustPositionAuto(80);
/*    */     
/* 17 */     stopPrintJobLuck(paramResultCallback); } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 80, null); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) adjustPositionAuto(80);  stopPrintJobLuck(paramResultCallback); }
/*    */ 
/*    */   
/*    */   public void setSnRangeList(ArrayList<String[]> paramArrayList) {
/*    */     this.snRangeList = paramArrayList;
/*    */   }
/*    */   
/*    */   public ArrayList<String[]> getSnRangeList() {
/*    */     return this.snRangeList;
/*    */   }
/*    */   
/*    */   public void setHeatingLevelVar(int paramInt) {
/*    */     this.heatingLevelVar = paramInt;
/*    */   }
/*    */   
/*    */   public int getHeatingLevelVar() {
/*    */     return this.heatingLevelVar;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\a4\DP_D80.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */