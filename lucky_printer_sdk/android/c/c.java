/*  1 */ package c;public abstract class c { public OnClientConnectionListener a; public OnReceiveDeviceStatusListener b; public final void d() { ExecutorService executorService; if ((executorService = this.d) != null && !executorService.isShutdown()) this
/*  2 */         .d.shutdownNow(); 
/*    */     OnClientConnectionListener onClientConnectionListener;
/*  4 */     if ((onClientConnectionListener = this.a) != null)
/*  5 */       onLuckDisConnected();  } public OnEventListener c; public ExecutorService d; public static void a(int paramInt) { String str1 = e.b(paramInt); String str2 = e.a(paramInt); IEventRecorder iEventRecorder; if (paramInt != 0 && (iEventRecorder = f.a) != null) { FileEventRecorder.getInstance().onEvent(str1, str2); iEventRecorder.onEvent(str1, str2); }  } public final void b(g paramg, IReceiveDataClaud paramIReceiveDataClaud, ReadDataCallback paramReadDataCallback, int paramInt) { // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ifnonnull -> 5
/*    */     //   4: return
/*    */     //   5: aload_0
/*    */     //   6: dup
/*    */     //   7: aload_1
/*    */     //   8: aload_2
/*    */     //   9: aload_3
/*    */     //   10: iload #4
/*    */     //   12: <illegal opcode> run : (Lc/c;Lb/g;Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;Lcom/luckprinter/sdk_new/callback/ReadDataCallback;I)Ljava/lang/Runnable;
/*    */     //   17: invokevirtual a : (Ljava/lang/Runnable;)V
/*    */     //   20: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #1	-> 12 } public final void b(IReceiveDataClaud paramIReceiveDataClaud, byte[] paramArrayOfbyte, ReadDataCallback paramReadDataCallback, int paramInt) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: dup
/*    */     //   2: aload_1
/*    */     //   3: aload_2
/*    */     //   4: aload_3
/*    */     //   5: iload #4
/*    */     //   7: <illegal opcode> run : (Lc/c;Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BLcom/luckprinter/sdk_new/callback/ReadDataCallback;I)Ljava/lang/Runnable;
/*    */     //   12: invokevirtual a : (Ljava/lang/Runnable;)V
/*    */     //   15: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #2	-> 7 }
/*  6 */   public static void a(String paramString, int paramInt) { String str = e.b(paramInt);
/*    */ 
/*    */     
/*  9 */     paramString = e.a(paramInt) + " : " + paramString; IEventRecorder iEventRecorder;
/* 10 */     if (paramInt != 0 && (iEventRecorder = f.a) != null) {
/* 11 */       FileEventRecorder.getInstance().onEvent(str, paramString);
/* 12 */       iEventRecorder.onEvent(str, paramString);
/*    */     }  } public static boolean a() {
/* 14 */     if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
/* 15 */       h.a("bluetooth not enable");
/* 16 */       a(29); return false;
/* 17 */     }  int i; if ((i = Build.VERSION.SDK_INT) < 31 || ((i < 31 || 
/* 18 */       PrinterUtil.getAppContext()
/* 19 */       .checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0) && (i < 31 || 
/* 20 */       PrinterUtil.getAppContext()
/* 21 */       .checkSelfPermission("android.permission.BLUETOOTH_CONNECT") == 0)))
/*    */     {
/* 23 */       return true; }  h.a("bluetooth permission not grant"); a(30); return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void a(Runnable paramRunnable)
/*    */   {
/*    */     ExecutorService executorService;
/* 34 */     if ((executorService = this.d) != null && !executorService.isShutdown() && paramRunnable != null) this
/* 35 */         .d.execute(paramRunnable);  } public static boolean a(byte[] paramArrayOfbyte) {
/* 36 */     byte b2 = 9; byte[] arrayOfByte2; (arrayOfByte2 = new byte[9])[0] = -6; (new byte[9])[1] = 1; (new byte[9])[2] = 0; (new byte[9])[3] = 0; (new byte[9])[4] = 0; (new byte[9])[5] = 0; (new byte[9])[6] = 0; (new byte[9])[7] = 0; (new byte[9])[8] = 0; if (paramArrayOfbyte != null && paramArrayOfbyte.length == 
/* 37 */       b2 && paramArrayOfbyte[0] == 
/* 38 */       arrayOfByte2[0] && paramArrayOfbyte[1] == arrayOfByte2[1]) {
/* 39 */       h.a("nfc signal"); return true;
/* 40 */     }  byte[] arrayOfByte1; (arrayOfByte1 = new byte[8])[0] = -4; (new byte[8])[1] = -1; (new byte[8])[2] = 0; (new byte[8])[3] = 2; (new byte[8])[4] = 69; (new byte[8])[5] = -2; (new byte[8])[6] = 1; (new byte[8])[7] = -69; if (paramArrayOfbyte != null && paramArrayOfbyte.length == 
/* 41 */       8) { byte b = 0; while (true) { if (b < paramArrayOfbyte.length)
/*    */         
/*    */         { 
/*    */           
/* 45 */           if (paramArrayOfbyte[b] != 
/* 46 */             arrayOfByte1[b]) break;  b++; continue; }  return true; }  }  if (paramArrayOfbyte != null && paramArrayOfbyte.length == 
/* 47 */       2 && paramArrayOfbyte[0] == -1) return true;  byte b1 = 7; byte[][] arrayOfByte; boolean bool;
/*    */     byte[] arrayOfByte3;
/*    */     byte b3;
/* 50 */     label44: for ((arrayOfByte3 = new byte[1])[0] = -1, (arrayOfByte = new byte[7][])[0] = arrayOfByte3, (arrayOfByte3 = new byte[2])[0] = -1, (new byte[2])[1] = 0, (new byte[7][])[1] = arrayOfByte3, (arrayOfByte3 = new byte[2])[0] = -1, (new byte[2])[1] = 1, (new byte[7][])[2] = arrayOfByte3, (arrayOfByte3 = new byte[2])[0] = -1, (new byte[2])[1] = 2, (new byte[7][])[3] = arrayOfByte3, (arrayOfByte3 = new byte[2])[0] = -1, (new byte[2])[1] = 3, (new byte[7][])[4] = arrayOfByte3, (arrayOfByte3 = new byte[2])[0] = -1, (new byte[2])[1] = 4, (new byte[7][])[5] = arrayOfByte3, (arrayOfByte3 = new byte[2])[0] = -1, (new byte[2])[1] = 5, (new byte[7][])[6] = arrayOfByte3, bool = false, b3 = 0; b3 < b1; )
/*    */     
/*    */     { 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 62 */       byte[] arrayOfByte4 = arrayOfByte[b3]; if (paramArrayOfbyte == null || arrayOfByte4 == null || paramArrayOfbyte.length != 
/* 63 */         arrayOfByte4.length)
/*    */       
/*    */       { 
/*    */ 
/*    */         
/* 68 */         b3++; continue; }  byte b = 0; while (b < paramArrayOfbyte.length) { if (paramArrayOfbyte[b] != arrayOfByte4[b]) continue label44;  b++; }  bool = true; }  return bool;
/*    */   }
/*    */   
/*    */   public abstract boolean a(String paramString1, String paramString2);
/*    */   
/*    */   public abstract boolean b();
/*    */   
/*    */   public abstract byte[] a(IReceiveDataClaud paramIReceiveDataClaud, byte[] paramArrayOfbyte, boolean paramBoolean, int paramInt);
/*    */   
/*    */   public abstract boolean c(); }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\c\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */