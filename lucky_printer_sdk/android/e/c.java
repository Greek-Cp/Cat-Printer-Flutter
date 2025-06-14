/*    */ package e;public final class c implements BtReceiver.Listener { public final void onDisConnected(BluetoothDevice paramBluetoothDevice) { BluetoothDevice bluetoothDevice;
/*  2 */     if ((bluetoothDevice = this.a.m) != null && paramBluetoothDevice
/*  3 */       .getAddress().equals(bluetoothDevice.getAddress())) {
/*    */       
/*  5 */       h.a("classic onDisConnected event come!");
/*  6 */       byte b = 12; this.a
/*  7 */         .getClass(); c.c.a("classic onDisConnected event come!", b); d d1;
/*    */       DataInputStream dataInputStream;
/*  9 */       if ((dataInputStream = (d1 = this.a).j) != null) {
/* 10 */         d.a(dataInputStream);
/* 11 */         d1.j = null;
/*    */       } 
/*    */       DataOutputStream dataOutputStream;
/* 14 */       if ((dataOutputStream = d1.i) != null) {
/* 15 */         d.a(dataOutputStream);
/* 16 */         d1.i = null;
/*    */       } 
/*    */       BluetoothSocket bluetoothSocket;
/* 19 */       if ((bluetoothSocket = d1.h) != null) {
/* 20 */         d.a((Closeable)bluetoothSocket);
/* 21 */         d1.h = null;
/*    */       }  BtReceiver btReceiver;
/* 23 */       if ((btReceiver = (d1 = this.a).o) != null) { btReceiver
/* 24 */           .removeReceiver(PrinterUtil.getAppContext());
/* 25 */         d1.o = null; }  this
/* 26 */         .a
/* 27 */         .d();
/*    */       ConditionVariable conditionVariable;
/* 29 */       if ((conditionVariable = this.a.q) != null)
/* 30 */         open(); 
/*    */     }  }
/*    */ 
/*    */   
/*    */   public c(d paramd) {}
/*    */   
/*    */   public final void onStateOff() {
/*    */     h.a("classic onDisConnected event come!");
/*    */     byte b = 12;
/*    */     this.a.getClass();
/*    */     c.c.a("classic onDisConnected event come!", b);
/*    */     d d1;
/*    */     DataInputStream dataInputStream;
/*    */     if ((dataInputStream = (d1 = this.a).j) != null) {
/*    */       d.a(dataInputStream);
/*    */       d1.j = null;
/*    */     } 
/*    */     DataOutputStream dataOutputStream;
/*    */     if ((dataOutputStream = d1.i) != null) {
/*    */       d.a(dataOutputStream);
/*    */       d1.i = null;
/*    */     } 
/*    */     BluetoothSocket bluetoothSocket;
/*    */     if ((bluetoothSocket = d1.h) != null) {
/*    */       d.a((Closeable)bluetoothSocket);
/*    */       d1.h = null;
/*    */     } 
/*    */     BtReceiver btReceiver;
/*    */     if ((btReceiver = (d1 = this.a).o) != null) {
/*    */       btReceiver.removeReceiver(PrinterUtil.getAppContext());
/*    */       d1.o = null;
/*    */     } 
/*    */     this.a.d();
/*    */     ConditionVariable conditionVariable;
/*    */     if ((conditionVariable = this.a.q) != null)
/*    */       open(); 
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\e\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */