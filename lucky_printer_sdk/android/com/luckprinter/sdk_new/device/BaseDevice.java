/*  1 */ package com.luckprinter.sdk_new.device;public abstract class BaseDevice implements IDeviceOperation { private String deviceName; private String deviceMac; private String namePrefix; private boolean isSupportSetSpeed = false; public void printerSettingLuck(ResultCallback<String> paramResultCallback) { byte[] arrayOfByte; (arrayOfByte = new byte[2])[0] = -16; (new byte[2])[1] = 0; super(this, paramResultCallback); ResultCallback<byte[]> resultCallback; sendDataNew(arrayOfByte, null, resultCallback); } public void initAfterConnect() {} public boolean is300Dpi() { return false; } public int getPrintWidth() { return is300Dpi() ? 576 : 384; } public int getPrintMaxWidth() { return getPrintWidth(); } public void setDeviceName(String paramString) { this.deviceName = paramString; } public String getDeviceName() { return this.deviceName; } public void setDeviceMac(String paramString) { this.deviceMac = paramString; } public String getDeviceMac() { return this.deviceMac; } public void setNamePrefix(String paramString) { this.namePrefix = paramString; } public String getNamePrefix() { return this.namePrefix; } public void sendDataNew(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, ResultCallback<byte[]> paramResultCallback) { if (paramArrayOfbyte2 == null) paramArrayOfbyte2 = new byte[0];
/*    */ 
/*    */     
/*  4 */     byte[] arrayOfByte1 = PrinterUtil.intToByteArray2(paramArrayOfbyte2.length + 1); byte[] arrayOfByte2;
/*  5 */     (arrayOfByte2 = new byte[2])[0] = -4; (new byte[2])[1] = -1; (new byte[5][])[0] = arrayOfByte2; (new byte[5][])[1] = this; (new byte[5][])[2] = paramArrayOfbyte1; (new byte[5][])[3] = paramArrayOfbyte2; (arrayOfByte1 = new byte[1])[0] = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 12 */     PrinterUtil.xorEndByte(arrayOfByte1 = PrinterUtil.combineBytes(new byte[][] { null, null, null, null, this }));
/*    */     if (paramResultCallback == null)
/* 14 */     { d.b().a(null, this); }
/*    */     else
/* 16 */     { d.b().a(paramArrayOfbyte -> { if (paramArrayOfbyte != null && paramArrayOfbyte.length >= 8) { byte b1 = paramArrayOfbyte[paramArrayOfbyte.length - 1]; byte b2 = paramArrayOfbyte[2]; byte b3 = paramArrayOfbyte[3]; b3 = PrinterUtil.xor(PrinterUtil.getSubByteArray(paramArrayOfbyte, 0, paramArrayOfbyte.length - 1)); int i; if ((i = PrinterUtil.byteArray2ToIntBigEndian(new byte[] { b2, b3 })) > 1 && b1 == b3) { paramResultCallback.onSuccess(PrinterUtil.getSubByteArray(paramArrayOfbyte, 6, i - 1)); } else { paramResultCallback.onFail(); }  } else { paramResultCallback.onFail(); }  }this); }  }
/*    */   public void setSupportSetSpeed(boolean paramBoolean) { this.isSupportSetSpeed = paramBoolean; }
/*    */   public boolean isSupportSetSpeed() { return this.isSupportSetSpeed; }
/*    */   public int getMinDensity() { return this.minDensity; }
/* 20 */   public void setMinDensity(int paramInt) { this.minDensity = paramInt; } public int getMaxDensity() { return this.maxDensity; } public void setMaxDensity(int paramInt) { this.maxDensity = paramInt; } public int getMinSpeed() { return this.minSpeed; } public void setMinSpeed(int paramInt) { this.minSpeed = paramInt; } public int getMaxSpeed() { return this.maxSpeed; } private int minDensity = 0; public void setMaxSpeed(int paramInt) { this.maxSpeed = paramInt; } public String getSn() { return this.sn; } public void setSn(String paramString) { this.sn = paramString; } public String getModel() { return this.model; } public void setModel(String paramString) { this.model = paramString; }
/*    */   public String getVersion() { return this.version; }
/*    */   public void setVersion(String paramString) { this.version = paramString; }
/*    */   public void setConnectWay(ConnectWayEnum paramConnectWayEnum) { this.connectWay = paramConnectWayEnum; }
/*    */   public void setHasFirmwareEraseCallbackData(boolean paramBoolean) { this.isHasFirmwareEraseCallbackData = paramBoolean; }
/* 25 */   private int maxDensity = 2, minSpeed = 0; public boolean isHasFirmwareEraseCallbackData() { return this.isHasFirmwareEraseCallbackData; } public int getDpiMultiplier() { return is300Dpi() ? 12 : 8; } public ConnectWayEnum getConnectWay() { return this.connectWay; } public void setSpeedList(List<Integer> paramList) { this.speedList = paramList; } public void setDensityList(List<Integer> paramList) { this.densityList = paramList; } public List<Integer> getDensityList() { List<Integer> list; if ((list = this.densityList) == null) { super(); for (int i = this.minDensity; i <= this.maxDensity; ) { list.add(Integer.valueOf(i)); i++; }  return list; }  return list; }
/*    */   public List<Integer> getSpeedList() { List<Integer> list; if ((list = this.speedList) == null) { super(); for (int i = this.minSpeed; i <= this.maxSpeed; ) { list.add(Integer.valueOf(i)); i++; }  return list; }  return list; }
/*    */   public String byteToString(byte[] paramArrayOfbyte) { try { return new String(paramArrayOfbyte, "GB2312"); } catch (UnsupportedEncodingException unsupportedEncodingException) { return ""; }  }
/*    */   public Object getTag() { return this.tag; }
/*    */   public void setTag(Object paramObject) { this.tag = paramObject; }
/* 30 */   private int maxSpeed = 8; private List<Integer> densityList = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   private List<Integer> speedList = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String sn;
/*    */ 
/*    */ 
/*    */   
/*    */   private String model;
/*    */ 
/*    */ 
/*    */   
/*    */   private String version;
/*    */ 
/*    */ 
/*    */   
/*    */   private ConnectWayEnum connectWay;
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean isHasFirmwareEraseCallbackData = false;
/*    */ 
/*    */ 
/*    */   
/* 60 */   private Object tag = null; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\BaseDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */