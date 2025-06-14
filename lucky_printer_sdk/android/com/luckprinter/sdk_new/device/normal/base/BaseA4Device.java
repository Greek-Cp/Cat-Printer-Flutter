/*  1 */ package com.luckprinter.sdk_new.device.normal.base;public class BaseA4Device extends BaseNormalDevice implements ISupportSelectPaper, IA4DeviceOperation { protected int rollPaperWidth; private int folderA4PaperWidth; private int folderA4PaperHeight; public BaseA4Device() { this
/*  2 */       .rollPaperWidth = 210;
/*    */     
/*  4 */     this.folderA4PaperWidth = 210;
/*    */     
/*  6 */     this.folderA4PaperHeight = 297;
/*    */ 
/*    */     
/*  9 */     setCompress(true);
/* 10 */     if (is300Dpi())
/* 11 */     { setEndLineDot(216); }
/*    */     else
/* 13 */     { setEndLineDot(144); }  }
/*    */   public int getPrintWidth() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: dup
/*    */     //   2: invokevirtual getPrintMaxWidth : ()I
/*    */     //   5: istore_1
/*    */     //   6: getfield rollPaperWidth : I
/*    */     //   9: dup
/*    */     //   10: istore_2
/*    */     //   11: bipush #56
/*    */     //   13: if_icmpne -> 37
/*    */     //   16: aload_0
/*    */     //   17: invokevirtual is300Dpi : ()Z
/*    */     //   20: ifeq -> 30
/*    */     //   23: sipush #648
/*    */     //   26: istore_0
/*    */     //   27: goto -> 154
/*    */     //   30: sipush #432
/*    */     //   33: istore_0
/*    */     //   34: goto -> 154
/*    */     //   37: iload_2
/*    */     //   38: bipush #77
/*    */     //   40: if_icmpne -> 64
/*    */     //   43: aload_0
/*    */     //   44: invokevirtual is300Dpi : ()Z
/*    */     //   47: ifeq -> 57
/*    */     //   50: sipush #887
/*    */     //   53: istore_0
/*    */     //   54: goto -> 154
/*    */     //   57: sipush #591
/*    */     //   60: istore_0
/*    */     //   61: goto -> 154
/*    */     //   64: iload_2
/*    */     //   65: bipush #107
/*    */     //   67: if_icmpne -> 91
/*    */     //   70: aload_0
/*    */     //   71: invokevirtual is300Dpi : ()Z
/*    */     //   74: ifeq -> 84
/*    */     //   77: sipush #1248
/*    */     //   80: istore_0
/*    */     //   81: goto -> 154
/*    */     //   84: sipush #832
/*    */     //   87: istore_0
/*    */     //   88: goto -> 154
/*    */     //   91: iload_2
/*    */     //   92: sipush #210
/*    */     //   95: if_icmpne -> 119
/*    */     //   98: aload_0
/*    */     //   99: invokevirtual is300Dpi : ()Z
/*    */     //   102: ifeq -> 112
/*    */     //   105: sipush #2400
/*    */     //   108: istore_0
/*    */     //   109: goto -> 154
/*    */     //   112: sipush #1616
/*    */     //   115: istore_0
/*    */     //   116: goto -> 154
/*    */     //   119: iload_2
/*    */     //   120: sipush #216
/*    */     //   123: if_icmpne -> 147
/*    */     //   126: aload_0
/*    */     //   127: invokevirtual is300Dpi : ()Z
/*    */     //   130: ifeq -> 140
/*    */     //   133: sipush #2496
/*    */     //   136: istore_0
/*    */     //   137: goto -> 154
/*    */     //   140: sipush #1648
/*    */     //   143: istore_0
/*    */     //   144: goto -> 154
/*    */     //   147: aload_0
/*    */     //   148: invokevirtual getDpiMultiplier : ()I
/*    */     //   151: iload_2
/*    */     //   152: imul
/*    */     //   153: istore_0
/*    */     //   154: iload_0
/*    */     //   155: iload_1
/*    */     //   156: if_icmple -> 162
/*    */     //   159: goto -> 164
/*    */     //   162: iload_0
/*    */     //   163: istore_1
/*    */     //   164: iload_1
/*    */     //   165: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #1	-> 2
/*    */     //   #3	-> 6
/*    */     //   #4	-> 17
/*    */     //   #6	-> 44
/*    */     //   #8	-> 71
/*    */     //   #10	-> 99
/*    */     //   #12	-> 127
/* 14 */     //   #14	-> 148 } public int getPrintMaxWidth() { return is300Dpi() ? 2496 : 1648; } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 80, null); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) adjustPositionAuto(80);  stopPrintJobLuck(paramResultCallback); } public int getA4PrintWidth() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield folderA4PaperWidth : I
/*    */     //   4: aload_0
/*    */     //   5: getfield folderA4PaperHeight : I
/*    */     //   8: invokestatic getByWidthHeightMm : (II)Lcom/luckprinter/sdk_new/device/normal/base/A4PaperSize;
/*    */     //   11: dup
/*    */     //   12: astore_1
/*    */     //   13: ifnonnull -> 31
/*    */     //   16: aload_0
/*    */     //   17: dup
/*    */     //   18: getfield folderA4PaperWidth : I
/*    */     //   21: istore_1
/*    */     //   22: invokevirtual getDpiMultiplier : ()I
/*    */     //   25: iload_1
/*    */     //   26: imul
/*    */     //   27: istore_1
/*    */     //   28: goto -> 51
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual is300Dpi : ()Z
/*    */     //   35: ifeq -> 46
/*    */     //   38: aload_1
/*    */     //   39: invokevirtual getWidthDots300dpi : ()I
/*    */     //   42: istore_1
/*    */     //   43: goto -> 51
/*    */     //   46: aload_1
/*    */     //   47: invokevirtual getWidthDots200dpi : ()I
/*    */     //   50: istore_1
/*    */     //   51: iload_1
/*    */     //   52: aload_0
/*    */     //   53: invokevirtual getPrintMaxWidth : ()I
/*    */     //   56: if_icmple -> 64
/*    */     //   59: aload_0
/*    */     //   60: invokevirtual getPrintMaxWidth : ()I
/*    */     //   63: istore_1
/*    */     //   64: iload_1
/*    */     //   65: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #1	-> 1
/*    */     //   #4	-> 18
/*    */     //   #6	-> 32
/*    */     //   #8	-> 53
/*    */     //   #9	-> 60 } public int getA4PrintHeight() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield folderA4PaperWidth : I
/*    */     //   4: aload_0
/*    */     //   5: getfield folderA4PaperHeight : I
/*    */     //   8: invokestatic getByWidthHeightMm : (II)Lcom/luckprinter/sdk_new/device/normal/base/A4PaperSize;
/*    */     //   11: dup
/*    */     //   12: astore_1
/*    */     //   13: ifnonnull -> 28
/*    */     //   16: aload_0
/*    */     //   17: dup
/*    */     //   18: getfield folderA4PaperHeight : I
/*    */     //   21: istore_0
/*    */     //   22: invokevirtual getDpiMultiplier : ()I
/*    */     //   25: iload_0
/*    */     //   26: imul
/*    */     //   27: ireturn
/*    */     //   28: aload_0
/*    */     //   29: invokevirtual is300Dpi : ()Z
/*    */     //   32: ifeq -> 42
/*    */     //   35: aload_1
/*    */     //   36: invokevirtual getHeightDots300dpi : ()I
/*    */     //   39: goto -> 46
/*    */     //   42: aload_1
/*    */     //   43: invokevirtual getHeightDots200dpi : ()I
/*    */     //   46: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #1	-> 1
/*    */     //   #3	-> 18
/* 14 */     //   #5	-> 29 } public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 16, null); sendBitmap(paramBitmap); printLineDotsLuck(getEndLineDot()); stopPrintJobLuck(paramResultCallback); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { enablePrinterLuck(); printerWakeupLuck(); setPaperType(1, 32, null); if (paramInt1 == 1) adjustPositionAuto(81);  sendBitmap(paramBitmap); printerPositionLuck(); if (paramInt1 == paramInt2) adjustPositionAuto(80);  stopPrintJobLuck(paramResultCallback); }
/*    */ 
/*    */   
/*    */   public void printFolder(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback) {
/*    */     OnDoPrintCallback onDoPrintCallback = (paramInt2, paramResultCallback) -> printFolderOnce(paramBitmap, paramInt2, paramInt1, paramResultCallback);
/*    */     doPrint(paramBitmap, paramInt, paramResultPageCallback, this);
/*    */   }
/*    */   
/*    */   public void printFolderOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     enablePrinterLuck();
/*    */     printerWakeupLuck();
/*    */     setPaperType(1, 48, null);
/*    */     adjustPositionAuto(81);
/*    */     sendBitmap(paramBitmap);
/*    */     printerPositionLuck();
/*    */     adjustPositionAuto(80);
/*    */     stopPrintJobLuck(paramResultCallback);
/*    */   }
/*    */   
/*    */   public void printTattoo(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback) {
/*    */     OnDoPrintCallback onDoPrintCallback = (paramInt2, paramResultCallback) -> printTattooOnce(paramBitmap, paramInt2, paramInt1, paramResultCallback);
/*    */     doPrint(paramBitmap, paramInt, paramResultPageCallback, this);
/*    */   }
/*    */   
/*    */   public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     enablePrinterLuck();
/*    */     printerWakeupLuck();
/*    */     setPaperType(1, 16, null);
/*    */     sendBitmap(paramBitmap);
/*    */     printLineDotsLuck(getEndLineDot());
/*    */     stopPrintJobLuck(paramResultCallback);
/*    */   }
/*    */   
/*    */   public void setSelectPaperSize(int paramInt) {
/*    */     this.rollPaperWidth = paramInt;
/*    */   }
/*    */   
/*    */   public int getSelectPaperSize() {
/*    */     return this.rollPaperWidth;
/*    */   }
/*    */   
/*    */   public void setA4PaperSize(int paramInt1, int paramInt2) {
/*    */     this.folderA4PaperWidth = paramInt1;
/*    */     this.folderA4PaperHeight = paramInt2;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\BaseA4Device.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */