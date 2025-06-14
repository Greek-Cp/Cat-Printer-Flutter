/*  1 */ package com.luckprinter.sdk_new.device.normal.base;public class CustomNormalDevice extends BaseNormalDevice implements ICustomPrinter, ISupportSelectPaper { private PrinterCommand printerCommand; private int rollPaperSize = -1; private PrinterProperty printerProperty; private void sendPrintCommands(List<Command> paramList, Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { for (Iterator<Command> iterator = paramList.iterator(); iterator.hasNext(); ) {
/*  2 */       boolean bool1, bool2, bool3; Command command; String str1 = (command = iterator.next()).getType();
/*  3 */       String str2 = ((Command)iterator.next()).getPosition();
/*  4 */       if ("first".equals(str2) && paramInt1 == 1) { bool2 = true; } else { bool2 = false; }
/*  5 */        if ("last".equals(str2) && paramInt1 == paramInt2) { bool3 = true; } else { bool3 = false; }
/*  6 */        if ("always".equals(str2) || TextUtils.isEmpty(str2)) { bool1 = true; } else { bool1 = false; }  if (bool2 || bool3 || bool1) { ReadDataCallback readDataCallback; if (str1
/*    */ 
/*    */           
/*  9 */           .equals(CmdType.DISABLE.getValue())) { if (command
/* 10 */             .isCallback()) {
/* 11 */             super(this, paramResultCallback); CustomCommandUtil.sendCommand(command, readDataCallback);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             continue;
/*    */           } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 24 */           CustomCommandUtil.sendCommand((Command)readDataCallback); if (paramResultCallback != null) paramResultCallback
/*    */               
/* 26 */               .onSuccess(Integer.valueOf(1));  continue; }  if (str1
/*    */ 
/*    */           
/* 29 */           .equals(CmdType.PRINT_BITMAP.getValue())) {
/* 30 */           sendBitmap(paramBitmap); continue;
/*    */         } 
/* 32 */         CustomCommandUtil.sendCommand((Command)readDataCallback); }
/*    */     
/*    */     }  }
/*    */ 
/*    */   
/*    */   public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     PrinterCommand printerCommand;
/*    */     if ((printerCommand = this.printerCommand) == null || printerCommand.getPrint() == null) {
/*    */       super.printOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback);
/*    */       return;
/*    */     } 
/*    */     sendPrintCommands(this.printerCommand.getPrint(), paramBitmap, paramInt1, paramInt2, paramResultCallback);
/*    */   }
/*    */   
/*    */   public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     PrinterCommand printerCommand;
/*    */     if ((printerCommand = this.printerCommand) == null || printerCommand.getPrintTag() == null) {
/*    */       super.printTagOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback);
/*    */       return;
/*    */     } 
/*    */     sendPrintCommands(this.printerCommand.getPrintTag(), paramBitmap, paramInt1, paramInt2, paramResultCallback);
/*    */   }
/*    */   
/*    */   public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) {
/*    */     PrinterCommand printerCommand;
/*    */     if ((printerCommand = this.printerCommand) == null || printerCommand.getPrintBlackTag() == null) {
/*    */       super.printBlackTagOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback);
/*    */       return;
/*    */     } 
/*    */     sendPrintCommands(this.printerCommand.getPrintBlackTag(), paramBitmap, paramInt1, paramInt2, paramResultCallback);
/*    */   }
/*    */   
/*    */   public boolean is300Dpi() {
/*    */     PrinterProperty printerProperty;
/*    */     if ((printerProperty = this.printerProperty) == null)
/*    */       return super.is300Dpi(); 
/*    */     return (printerProperty.getPrinterDpi() == 300);
/*    */   }
/*    */   
/*    */   public int getPrintWidth() {
/*    */     PrinterProperty printerProperty;
/*    */     if ((printerProperty = this.printerProperty) == null)
/*    */       return super.getPrintWidth(); 
/*    */     int j = printerProperty.getPrinterMaxWidth();
/*    */     j = getDpiMultiplier() * j;
/*    */     int k;
/*    */     if ((k = this.rollPaperSize) == -1)
/*    */       return j; 
/*    */     int i;
/*    */     if ((i = getDpiMultiplier() * k) <= j)
/*    */       j = i; 
/*    */     return j;
/*    */   }
/*    */   
/*    */   public int getPrintMaxWidth() {
/*    */     PrinterProperty printerProperty;
/*    */     if ((printerProperty = this.printerProperty) == null)
/*    */       return super.getPrintMaxWidth(); 
/*    */     int i = printerProperty.getPrinterMaxWidth();
/*    */     return getDpiMultiplier() * i;
/*    */   }
/*    */   
/*    */   public void setCommand(PrinterCommand paramPrinterCommand) {
/*    */     this.printerCommand = paramPrinterCommand;
/*    */     if (paramPrinterCommand != null) {
/*    */       String str;
/*    */       if (!TextUtils.isEmpty(str = paramPrinterCommand.getCompressWay())) {
/*    */         if (str.equals(CompressWayEnum.ANGYIN.getKey())) {
/*    */           setCompress(true);
/*    */           setCompressWay(0);
/*    */         } else if (str.equals(CompressWayEnum.ANGYIN_FAST.getKey())) {
/*    */           setCompress(true);
/*    */           setCompressWay(1);
/*    */         } else {
/*    */           setCompress(false);
/*    */         } 
/*    */       } else {
/*    */         setCompress(false);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setProperty(PrinterProperty paramPrinterProperty) {
/*    */     this.printerProperty = paramPrinterProperty;
/*    */     if (paramPrinterProperty != null) {
/*    */       setSupportSetSpeed(paramPrinterProperty.isSupportSetSpeed());
/*    */       setDensityList(paramPrinterProperty.getDensityList());
/*    */       setSpeedList(paramPrinterProperty.getSpeedList());
/*    */     } 
/*    */   }
/*    */   
/*    */   public PrinterCommand getCommand() {
/*    */     return this.printerCommand;
/*    */   }
/*    */   
/*    */   public PrinterProperty getProperty() {
/*    */     return this.printerProperty;
/*    */   }
/*    */   
/*    */   public void setSelectPaperSize(int paramInt) {
/*    */     this.rollPaperSize = paramInt;
/*    */   }
/*    */   
/*    */   public int getSelectPaperSize() {
/*    */     return this.rollPaperSize;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\CustomNormalDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */