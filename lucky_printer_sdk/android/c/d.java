/*  1 */ package c;public final class d { public static d b() { if (b == null)
/*  2 */       synchronized (d.class) {
/*  3 */         b = new d();
/*    */         /* monitor exit TypeReferenceDotClassExpression{ObjectType{c/d}} */
/*    */       }  
/*  6 */     return b; } public static volatile d b; public final synchronized boolean a(BaseDevice paramBaseDevice) { if (paramBaseDevice == null)
/*  7 */       return false;  String str1 = paramBaseDevice.getDeviceName(); String str2 = paramBaseDevice.getDeviceMac(); OnClientConnectionListener onClientConnectionListener = null; OnReceiveDeviceStatusListener onReceiveDeviceStatusListener = null; OnEventListener onEventListener = null; onClientConnectionListener = c2.a;
/*  8 */     onReceiveDeviceStatusListener = c2.b;
/*  9 */     onEventListener = c2.c; c c2;
/* 10 */     if ((c2 = this.a) != null && c2.c() && 
/* 11 */       !this.a.b()) return false;  if (paramBaseDevice instanceof com.luckprinter.sdk_new.device.sheetlabel.GD985 || 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 19 */       paramBaseDevice instanceof com.luckprinter.sdk_new.device.sheetlabel.D100)
/*    */     
/*    */     { 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 27 */       this
/*    */         
/* 29 */         .a = (c)a.f;
/* 30 */       paramBaseDevice.setConnectWay(ConnectWayEnum.CLASSIC);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 38 */       this
/* 39 */         .a = onClientConnectionListener;
/* 40 */       this.b = onReceiveDeviceStatusListener;
/* 41 */       this.c = onEventListener;
/* 42 */       return (c1 = this.a).a(str1, str2); }  if (PrinterHelper.getInstance().isDeviceConnectUseBle(str1)) { ((d)super).a = (c)i.e(); paramBaseDevice.setConnectWay(ConnectWayEnum.BLE); } else { ((d)super).a = (c)e.d.e(); paramBaseDevice.setConnectWay(ConnectWayEnum.CLASSIC); }  this.a = onClientConnectionListener; this.b = onReceiveDeviceStatusListener; this.c = onEventListener; c c1; return (c1 = ((d)super).a).a(str1, str2); } public c a = (c)e.d.e();
/* 43 */   public final synchronized boolean a() { return this.a.b(); } public final void a(ReadDataCallback paramReadDataCallback, byte[] paramArrayOfbyte) {
/* 44 */     byte b = 3; this.a
/* 45 */       .b((IReceiveDataClaud)null, paramArrayOfbyte, paramReadDataCallback, b);
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\c\d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */