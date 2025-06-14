/*  1 */ package com.luckprinter.sdk_new.device.normal;public class LPD50 extends BaseNormalDevice implements ISupportSetLabelPaperType { public LPD50() { this
/*  2 */       .labelPaperType = PaperType.TYPE_NORMAL_TAG;
/*    */     
/*  4 */     setMinDensity(0);
/*  5 */     setMaxDensity(15);
/*  6 */     setCompress(true);
/*  7 */     setTagNeedAdjustPaper(true);
/*  8 */     setEndLineDot(140); } private PaperType labelPaperType; public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); PaperType paperType; if ((paperType = this.labelPaperType) != null) setPaperType(1, paperType.getData(), null);  if (paramInt1 == 1) adjustPositionAuto(81);
/*    */     
/* 10 */     sendBitmap(paramBitmap);
/* 11 */     printerPositionLuck(); if (paramInt1 == paramInt2) {
/*    */ 
/*    */       
/* 14 */       setMarkPrintLast();
/* 15 */       adjustPositionAuto(80);
/*    */     } 
/* 17 */     stopPrintJobLuck(paramResultCallback); }
/*    */ 
/*    */   
/*    */   public void initAfterConnect() {
/*    */     super.initAfterConnect();
/*    */     super(this);
/*    */     ResultCallback<Integer> resultCallback;
/*    */     getTimeFormat(resultCallback);
/*    */   }
/*    */   
/*    */   public void setLabelPaperType(PaperType paramPaperType) {
/*    */     this.labelPaperType = paramPaperType;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\LPD50.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */