/* 1 */ package com.luckprinter.sdk_new.device.custom;public class PrinterCommand { public String getLanguage() { return this.language; } public void setLanguage(String paramString) { this.language = paramString; } public String getCompressWay() { return this.compressWay; } public void setCompressWay(String paramString) { this.compressWay = paramString; } public List<Command> getPrint() { return this.print; } public void setPrint(List<Command> paramList) { this.print = paramList; } public List<Command> getPrintFold() { return this.printFold; } public void setPrintFold(List<Command> paramList) { this.printFold = paramList; } public List<Command> getPrintTag() { return this.printTag; } public void setPrintTag(List<Command> paramList) { this.printTag = paramList; } public List<Command> getPrintBlackTag() { return this.printBlackTag; } public void setPrintBlackTag(List<Command> paramList) { this.printBlackTag = paramList; } public List<Command> getPrintTattoo() { return this.printTattoo; } public void setPrintTattoo(List<Command> paramList) { this.printTattoo = paramList; } public boolean isSupportPrintMulti() { return this.supportPrintMulti; } public void setSupportPrintMulti(boolean paramBoolean) { this.supportPrintMulti = paramBoolean; }
/* 2 */    private String language = "esc"; private String compressWay = null;
/*   */   private boolean supportPrintMulti = false;
/*   */   private List<Command> print;
/*   */   private List<Command> printFold;
/*   */   private List<Command> printTag;
/*   */   private List<Command> printBlackTag;
/*   */   private List<Command> printTattoo; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\custom\PrinterCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */