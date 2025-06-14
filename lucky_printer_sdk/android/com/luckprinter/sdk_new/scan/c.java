/* 1 */ package com.luckprinter.sdk_new.scan;public final class c implements BtReceiver.Listener { public final void foundDev(int paramInt, String paramString1, String paramString2) { if (this.a.scanDeviceListener != null && 
/* 2 */       PrinterHelper.getInstance().isAvailableLocalDevice(paramString1))
/* 3 */       this.a.scanDeviceListener.foundDev(paramInt, paramString1, paramString2);  }
/*   */ 
/*   */   
/*   */   public c(ClassicScanDeviceHelper paramClassicScanDeviceHelper) {}
/*   */   
/*   */   public final void startDiscovery() {
/*   */     BtReceiver.Listener listener;
/*   */     if ((listener = this.a.scanDeviceListener) != null)
/*   */       startDiscovery(); 
/*   */   }
/*   */   
/*   */   public final void finishDiscovery() {
/*   */     BtReceiver.Listener listener;
/*   */     if ((listener = this.a.scanDeviceListener) != null)
/*   */       finishDiscovery(); 
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\scan\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */