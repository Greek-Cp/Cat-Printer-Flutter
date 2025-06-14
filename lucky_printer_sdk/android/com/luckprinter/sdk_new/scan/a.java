/* 1 */ package com.luckprinter.sdk_new.scan;public abstract class a { public a(Context paramContext) { this
/* 2 */       .context = paramContext; }
/*   */ 
/*   */   
/*   */   protected final Context context;
/*   */   protected BtReceiver.Listener scanDeviceListener;
/*   */   
/*   */   public void setScanDeviceListener(BtReceiver.Listener paramListener) {
/*   */     this.scanDeviceListener = paramListener;
/*   */   }
/*   */   
/*   */   public void unInit() {} }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\scan\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */