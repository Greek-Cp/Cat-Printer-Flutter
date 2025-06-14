/*  1 */ package d;public final class a extends BleScanAndConnectCallback { public a(TDataWrap paramTDataWrap, i parami, CountDownLatch paramCountDownLatch) {} public final void onConnectFail(BleDevice paramBleDevice, BleException paramBleException) { this.a.countDown(); } public final void onScanFinished(BleDevice paramBleDevice) {} public final void onStartConnect() {} public final void onConnectSuccess(BleDevice paramBleDevice, BluetoothGatt paramBluetoothGatt, int paramInt) {
/*  2 */     h.a("ble onConnectSuccess callback event come!");
/*  3 */     byte b = 2; this.c
/*  4 */       .getClass(); c.a("ble onConnectSuccess callback event come!", b);
/*  5 */     i.a(this.c, paramBleDevice, paramBluetoothGatt);
/*  6 */     this.b.setData(Boolean.TRUE);
/*  7 */     this.a.countDown(); } public final void onDisConnected(boolean paramBoolean, BleDevice paramBleDevice, BluetoothGatt paramBluetoothGatt, int paramInt) { h.a("ble onDisConnected event come!");
/*  8 */     byte b = 2; this.c
/*  9 */       .getClass(); c.a("ble onDisConnected event come!", b);
/* 10 */     this.c
/* 11 */       .q
/* 12 */       .set(0);
/* 13 */     this.c.j = null;
/* 14 */     this.c.l = null;
/* 15 */     this.c.m = null;
/* 16 */     this.c.n = null;
/* 17 */     this.c.i.set(false);
/* 18 */     h.a("ble reset data!");
/* 19 */     this.c
/* 20 */       .d();
/*    */     ConditionVariable conditionVariable;
/* 22 */     if (this.c.j != null && paramBleDevice.getMac().equals(this.c.j.getMac()) && (conditionVariable = this.c.s) != null)
/* 23 */       open();  }
/*    */ 
/*    */   
/*    */   public final void onScanStarted(boolean paramBoolean) {}
/*    */   
/*    */   public final void onScanning(BleDevice paramBleDevice) {} }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */