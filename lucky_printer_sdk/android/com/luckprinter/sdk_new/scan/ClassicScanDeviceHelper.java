/*  1 */ package com.luckprinter.sdk_new.scan;public class ClassicScanDeviceHelper extends a { public ClassicScanDeviceHelper(Context paramContext) { super(paramContext); } private BtReceiver btReceiver; public void init() { if (this.context == null) {
/*    */       return;
/*    */     }
/*    */     
/*  5 */     Context context = this.context; this(this); BtReceiver btReceiver; c c; this(context, c); this.btReceiver = btReceiver; } public void startScanDevice() { boolean bool = false; if (BluetoothAdapter.getDefaultAdapter().isDiscovering()) { bool = true; BluetoothAdapter.getDefaultAdapter().cancelDiscovery(); }
/*  6 */      BluetoothAdapter bluetoothAdapter; if ((bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null) return;  boolean bool1 = true;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 11 */     if (!bluetoothAdapter.isEnabled())
/*    */     {
/* 13 */       bool1 = bluetoothAdapter.enable(); }  if (bool1) if (bool) {
/*    */ 
/*    */ 
/*    */         
/* 17 */         PrinterUtil.getMainHandler().postDelayed(new d(), 500L);
/*    */ 
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */         
/* 24 */         BluetoothAdapter.getDefaultAdapter().startDiscovery();
/*    */       }   }
/*    */ 
/*    */   
/*    */   public void stopScanDevice() {
/*    */     if (BluetoothAdapter.getDefaultAdapter().isDiscovering())
/*    */       BluetoothAdapter.getDefaultAdapter().cancelDiscovery(); 
/*    */   }
/*    */   
/*    */   public void unInit() {
/*    */     Context context;
/*    */     BtReceiver btReceiver;
/*    */     if ((btReceiver = this.btReceiver) != null && (context = this.context) != null)
/*    */       btReceiver.removeReceiver(this); 
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\scan\ClassicScanDeviceHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */