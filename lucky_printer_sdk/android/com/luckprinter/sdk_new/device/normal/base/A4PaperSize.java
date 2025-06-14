/*  1 */ package com.luckprinter.sdk_new.device.normal.base;public enum A4PaperSize { SIZE_210_297(210, 297, 1616, 2300, 2400, 3480),
/*    */ 
/*    */ 
/*    */   
/*  5 */   SIZE_148_210(148, 210, 1108, 1616, 1692, 2400),
/*    */ 
/*    */ 
/*    */   
/*  9 */   SIZE_216_279(216, 279, 1648, 2160, 2496, 3240),
/*    */ 
/*    */ 
/*    */   
/* 13 */   SIZE_216_355(216, 355, 1648, 2768, 2496, 4152);
/*    */   
/*    */   A4PaperSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*    */     this.widthMm = paramInt1;
/*    */     this.heightMm = paramInt2;
/*    */     this.widthDots200dpi = paramInt3;
/*    */     this.heightDots200dpi = paramInt4;
/*    */     this.widthDots300dpi = paramInt5;
/*    */     this.heightDots300dpi = paramInt6;
/*    */   }
/*    */   
/*    */   public static A4PaperSize getByWidthHeightMm(int paramInt1, int paramInt2) {
/*    */     A4PaperSize[] arrayOfA4PaperSize;
/*    */     int i;
/*    */     byte b;
/*    */     for (i = (arrayOfA4PaperSize = values()).length, b = 0; b < i; ) {
/*    */       A4PaperSize a4PaperSize;
/*    */       if ((a4PaperSize = arrayOfA4PaperSize[b]).widthMm == paramInt1 && a4PaperSize.heightMm == paramInt2)
/*    */         return a4PaperSize; 
/*    */       b++;
/*    */     } 
/*    */     return null;
/*    */   }
/*    */   
/*    */   public int getWidthMm() {
/*    */     return this.widthMm;
/*    */   }
/*    */   
/*    */   public int getHeightMm() {
/*    */     return this.heightMm;
/*    */   }
/*    */   
/*    */   public int getWidthDots200dpi() {
/*    */     return this.widthDots200dpi;
/*    */   }
/*    */   
/*    */   public int getHeightDots200dpi() {
/*    */     return this.heightDots200dpi;
/*    */   }
/*    */   
/*    */   public int getWidthDots300dpi() {
/*    */     return this.widthDots300dpi;
/*    */   }
/*    */   
/*    */   public int getHeightDots300dpi() {
/*    */     return this.heightDots300dpi;
/*    */   }
/*    */   
/*    */   private int widthMm;
/*    */   private int heightMm;
/*    */   private int widthDots200dpi;
/*    */   private int heightDots200dpi;
/*    */   private int widthDots300dpi;
/*    */   private int heightDots300dpi; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\A4PaperSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */