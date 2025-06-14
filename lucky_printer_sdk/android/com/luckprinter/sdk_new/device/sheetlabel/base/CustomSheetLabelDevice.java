/*  1 */ package com.luckprinter.sdk_new.device.sheetlabel.base;public class CustomSheetLabelDevice extends BaseSheetLabelDevice implements ISheetLabelDeviceOperation, ICustomPrinter { private PrinterCommand printerCommand; private boolean isSupportPrintMulti() { PrinterCommand printerCommand; if ((printerCommand = this.printerCommand) == null) return false;
/*    */ 
/*    */     
/*  4 */     return isSupportPrintMulti(); }
/*    */    private PrinterProperty printerProperty; private String compressWay;
/*    */   private void sendPrintCommands(List<Command> paramList, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*    */     for (Iterator<Command> iterator = paramList.iterator(); iterator.hasNext(); ) {
/*    */       Command command;
/*    */       String str;
/*    */       if ((str = (command = iterator.next()).getType()).equals(CmdType.SIZE_TSPL.getValue()))
/* 11 */       { CustomCommandUtil.sendCommand(command, List.of(textToHexString(paramInt1 + ""), textToHexString(paramInt2 + "")), null); continue; }  if (str
/* 12 */         .equals(CmdType.SET_SPEED_TSPL.getValue()))
/*    */       
/*    */       { 
/*    */ 
/*    */ 
/*    */         
/* 18 */         CustomCommandUtil.sendCommand(command, List.of(textToHexString(paramInt3 + "")), null); continue; }  if (str
/* 19 */         .equals(CmdType.SET_DENSITY_TSPL.getValue()))
/*    */       
/*    */       { 
/*    */ 
/*    */ 
/*    */         
/* 25 */         CustomCommandUtil.sendCommand(command, List.of(textToHexString(paramInt4 + "")), null); continue; }  if (str
/* 26 */         .equals(CmdType.SET_PRINT_COUNT_TSPL.getValue()))
/*    */       
/*    */       { 
/*    */ 
/*    */ 
/*    */         
/* 32 */         CustomCommandUtil.sendCommand(command, List.of(textToHexString(paramInt5 + "")), null); continue; }  if (str
/* 33 */         .equals(CmdType.PRINT_BITMAP.getValue())) {
/* 34 */         sendBitmap(paramBitmap); continue;
/*    */       } 
/* 36 */       CustomCommandUtil.sendCommand(command);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void sendBitmap(Bitmap paramBitmap) {
/*    */     CompressWayEnum compressWayEnum;
/*    */     if ((compressWayEnum = CompressWayEnum.getCompressWayByKey(this.compressWay)) != null) {
/*    */       int i;
/*    */       if ((i = null.$SwitchMap$com$luckprinter$sdk_new$device$CompressWayEnum[compressWayEnum.ordinal()]) != 1) {
/*    */         if (i != 2) {
/*    */           drawPic(paramBitmap);
/*    */         } else {
/*    */           drawPicAngyin(paramBitmap);
/*    */         } 
/*    */       } else {
/*    */         drawPicZLib(paramBitmap);
/*    */       } 
/*    */     } else {
/*    */       drawPic(paramBitmap);
/*    */     } 
/*    */   }
/*    */   
/*    */   private String textToHexString(String paramString) {
/*    */     return PrinterUtil.textToHexString(paramString);
/*    */   }
/*    */   
/*    */   private void setCompressWay(String paramString) {
/*    */     this.compressWay = paramString;
/*    */   }
/*    */   
/*    */   public void printTag(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Bitmap paramBitmap, int paramInt5) {
/*    */     PrinterCommand printerCommand;
/*    */     if ((printerCommand = this.printerCommand) == null || printerCommand.getPrintTag() == null) {
/*    */       super.printTag(paramInt1, paramInt2, paramInt3, paramInt4, paramBitmap, paramInt5);
/*    */       return;
/*    */     } 
/*    */     List<Command> list = this.printerCommand.getPrintTag();
/*    */     if (isSupportPrintMulti()) {
/*    */       sendPrintCommands(list, paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*    */     } else {
/*    */       for (byte b = 0; b < paramInt5; ) {
/*    */         sendPrintCommands(list, paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4, 1);
/*    */         b++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean is300Dpi() {
/*    */     PrinterProperty printerProperty;
/*    */     if ((printerProperty = this.printerProperty) == null)
/*    */       return false; 
/*    */     return (getPrinterDpi() == 300);
/*    */   }
/*    */   
/*    */   public int getPrintWidth() {
/*    */     PrinterProperty printerProperty;
/*    */     if ((printerProperty = this.printerProperty) == null)
/*    */       return super.getPrintWidth(); 
/*    */     int i = printerProperty.getPrinterMaxWidth();
/*    */     return getDpiMultiplier() * i;
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
/*    */     if (paramPrinterCommand != null)
/*    */       setCompressWay(paramPrinterCommand.getCompressWay()); 
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
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\sheetlabel\base\CustomSheetLabelDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */