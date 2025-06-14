/*  1 */ package com.luckprinter.sdk_new.device.custom;public class PrinterProperty { private String printerType; private int printerDpi; private int printerMaxWidth; private List<Integer> densityList; private List<Integer> speedList; public PrinterProperty() { ArrayList arrayList; this
/*  2 */       .printerType = "normal";
/*    */     
/*  4 */     this.printerDpi = 203;
/*    */     
/*  6 */     this.printerMaxWidth = 48;
/*    */     
/*  8 */     this(); this.densityList = this;
/*    */     
/* 10 */     this(); this.speedList = this;
/*    */     
/* 12 */     this.supportSetSpeed = false;
/*    */     
/* 14 */     this.btType = "classic_ble";
/*    */     
/* 16 */     this.bleEnable = false;
/*    */     
/* 18 */     this.supportPrintTattoo = false;
/*    */     
/* 20 */     this.supportGetMac = false; }
/* 21 */   private boolean supportSetSpeed; private String btType; private boolean bleEnable; private boolean supportPrintTattoo; private boolean supportGetMac; public String getPrinterType() { return this.printerType; } public void setPrinterType(String paramString) { this.printerType = paramString; } public PrinterProperty(Builder paramBuilder) { ArrayList<Integer> arrayList; this
/* 22 */       .printerType = "normal"; this
/*    */       
/* 24 */       .printerDpi = 203; this
/*    */       
/* 26 */       .printerMaxWidth = 48;
/*    */     
/* 28 */     this(); this.densityList = arrayList;
/*    */     
/* 30 */     this(); this.speedList = arrayList; this
/*    */       
/* 32 */       .supportSetSpeed = false; this
/*    */       
/* 34 */       .btType = "classic_ble"; this
/*    */       
/* 36 */       .bleEnable = false; this
/*    */       
/* 38 */       .supportPrintTattoo = false; this
/*    */       
/* 40 */       .supportGetMac = false;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     this.printerType = paramBuilder.printerType;
/* 48 */     this.btType = paramBuilder.btType;
/* 49 */     this.bleEnable = paramBuilder.bleEnable;
/* 50 */     this.printerDpi = paramBuilder.printerDpi;
/* 51 */     this.printerMaxWidth = paramBuilder.printerMaxWidth;
/* 52 */     this.densityList = paramBuilder.densityList;
/* 53 */     this.speedList = paramBuilder.speedList;
/* 54 */     this.supportSetSpeed = paramBuilder.supportSetSpeed;
/* 55 */     this.supportPrintTattoo = paramBuilder.supportPrintTattoo;
/* 56 */     this.supportGetMac = paramBuilder.supportGetMac; }
/*    */ 
/*    */   
/*    */   public String getBtType() {
/*    */     return this.btType;
/*    */   }
/*    */   
/*    */   public void setBtType(String paramString) {
/*    */     this.btType = paramString;
/*    */   }
/*    */   
/*    */   public boolean isBleEnable() {
/*    */     return this.bleEnable;
/*    */   }
/*    */   
/*    */   public void setBleEnable(boolean paramBoolean) {
/*    */     this.bleEnable = paramBoolean;
/*    */   }
/*    */   
/*    */   public int getPrinterDpi() {
/*    */     return this.printerDpi;
/*    */   }
/*    */   
/*    */   public void setPrinterDpi(int paramInt) {
/*    */     this.printerDpi = paramInt;
/*    */   }
/*    */   
/*    */   public int getPrinterMaxWidth() {
/*    */     return this.printerMaxWidth;
/*    */   }
/*    */   
/*    */   public void setPrinterMaxWidth(int paramInt) {
/*    */     this.printerMaxWidth = paramInt;
/*    */   }
/*    */   
/*    */   public List<Integer> getDensityList() {
/*    */     return this.densityList;
/*    */   }
/*    */   
/*    */   public void setDensityList(List<Integer> paramList) {
/*    */     this.densityList = paramList;
/*    */   }
/*    */   
/*    */   public List<Integer> getSpeedList() {
/*    */     return this.speedList;
/*    */   }
/*    */   
/*    */   public void setSpeedList(List<Integer> paramList) {
/*    */     this.speedList = paramList;
/*    */   }
/*    */   
/*    */   public boolean isSupportSetSpeed() {
/*    */     return this.supportSetSpeed;
/*    */   }
/*    */   
/*    */   public void setSupportSetSpeed(boolean paramBoolean) {
/*    */     this.supportSetSpeed = paramBoolean;
/*    */   }
/*    */   
/*    */   public boolean isSupportPrintTattoo() {
/*    */     return this.supportPrintTattoo;
/*    */   }
/*    */   
/*    */   public void setSupportPrintTattoo(boolean paramBoolean) {
/*    */     this.supportPrintTattoo = paramBoolean;
/*    */   }
/*    */   
/*    */   public boolean isSupportGetMac() {
/*    */     return this.supportGetMac;
/*    */   }
/*    */   
/*    */   public void setSupportGetMac(boolean paramBoolean) {
/*    */     this.supportGetMac = paramBoolean;
/*    */   }
/*    */   
/*    */   public static class Builder {
/*    */     private int version;
/*    */     private String printerType;
/*    */     private String btType;
/*    */     private boolean bleEnable;
/*    */     private int printerDpi;
/*    */     private int printerMaxWidth;
/*    */     private List<Integer> densityList;
/*    */     private List<Integer> speedList;
/*    */     private boolean supportSetSpeed;
/*    */     private boolean supportPrintTattoo;
/*    */     private boolean supportGetMac;
/*    */     
/*    */     public Builder() {
/*    */       ArrayList arrayList;
/*    */       this.printerType = "normal";
/*    */       this.btType = "classic_ble";
/*    */       this.bleEnable = false;
/*    */       this.printerDpi = 203;
/*    */       this.printerMaxWidth = 48;
/*    */       this();
/*    */       this.densityList = this;
/*    */       this();
/*    */       this.speedList = this;
/*    */       this.supportSetSpeed = false;
/*    */       this.supportPrintTattoo = false;
/*    */       this.supportGetMac = false;
/*    */     }
/*    */     
/*    */     public Builder version(int param1Int) {
/*    */       this.version = param1Int;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder printerType(String param1String) {
/*    */       this.printerType = param1String;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder btType(String param1String) {
/*    */       this.btType = param1String;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder bleEnable(boolean param1Boolean) {
/*    */       this.bleEnable = param1Boolean;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder printerDpi(int param1Int) {
/*    */       this.printerDpi = param1Int;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder printerMaxWidth(int param1Int) {
/*    */       this.printerMaxWidth = param1Int;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder densityList(List<Integer> param1List) {
/*    */       this.densityList = param1List;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder speedList(List<Integer> param1List) {
/*    */       this.speedList = param1List;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder supportSetSpeed(boolean param1Boolean) {
/*    */       this.supportSetSpeed = param1Boolean;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder supportPrintTattoo(boolean param1Boolean) {
/*    */       this.supportPrintTattoo = param1Boolean;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public Builder supportGetMac(boolean param1Boolean) {
/*    */       this.supportGetMac = param1Boolean;
/*    */       return this;
/*    */     }
/*    */     
/*    */     public PrinterProperty build() {
/*    */       return new PrinterProperty(this);
/*    */     }
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\custom\PrinterProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */