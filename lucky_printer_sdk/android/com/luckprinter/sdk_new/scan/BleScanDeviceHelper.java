/*  1 */ package com.luckprinter.sdk_new.scan;public class BleScanDeviceHelper extends a { public BleScanDeviceHelper(Context paramContext) { super(paramContext); }
/*    */   
/*    */   public static final String TAG = "BleScanDeviceHelper";
/*    */   public void init() {
/*  5 */     BleManager.getInstance().initScanRule((new BleScanRuleConfig.Builder()).setScanTimeOut(15000L).build()); } public void startScanDevice() { if (!BleManager.getInstance().isSupportBle()) { PrinterUtil.log("BleScanDeviceHelper", "设备不支持ble"); return; }
/*  6 */      if (!BleManager.getInstance().isBlueEnable())
/*    */     {
/*  8 */       BleManager.getInstance().enableBluetooth();
/*    */     }
/*    */     
/* 11 */     BleManager.getInstance().scan(new b(this)); }
/*    */ 
/*    */   
/*    */   public void stopScanDevice() {
/*    */     BleManager.getInstance().cancelScan();
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\scan\BleScanDeviceHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */