/*  1 */ package e;public final class a extends c { public static final a f = new a(); public final boolean a(String paramString1, String paramString2) { boolean bool; Pointer pointer = AutoReplyPrint.INSTANCE.CP_Port_OpenBtSpp(paramString2, 1);
/*  2 */     if (Pointer.NULL != pointer) { bool = true; } else { bool = false; }  this
/*  3 */       .d = Executors.newSingleThreadExecutor(); OnClientConnectionListener onClientConnectionListener;
/*  4 */     if (bool && (onClientConnectionListener = this.a) != null)
/*  5 */       onLuckConnected(paramString1, paramString2);  if (bool)
/*  6 */     { this("caysn classic connect success, name: "); append(paramString1); append(", mac: "); StringBuilder stringBuilder; (stringBuilder = new StringBuilder()).append(paramString2); h.a((new StringBuilder()).toString()); }
/*    */     else
/*    */     
/*  9 */     { h.a("classic connect fail"); }  return bool; }
/* 10 */   public final boolean b() { Pointer pointer1; Pointer pointer2; if ((pointer1 = this.e) != (pointer2 = Pointer.NULL)) { AutoReplyPrint.INSTANCE.CP_Port_Close(pointer1); this.e = pointer2; d(); return true; }  return false; } public final byte[] a(IReceiveDataClaud paramIReceiveDataClaud, byte[] paramArrayOfbyte, boolean paramBoolean, int paramInt) { if (!c()) return null;  if (paramArrayOfbyte == null) return null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 18 */     Pointer pointer = this.e; int i = paramArrayOfbyte.length; AutoReplyPrint autoReplyPrint; if ((autoReplyPrint = AutoReplyPrint.INSTANCE).CP_Port_Write(pointer, paramArrayOfbyte, i, 1000) != paramArrayOfbyte.length)
/* 19 */       return null;  if (paramBoolean) { autoReplyPrint
/*    */ 
/*    */ 
/*    */         
/* 23 */         .CP_Port_SkipAvailable(this.e);
/*    */       
/* 25 */       long l = System.currentTimeMillis(); while (true)
/*    */       { AutoReplyPrint autoReplyPrint1; int m;
/* 27 */         if ((m = (autoReplyPrint1 = AutoReplyPrint.INSTANCE).CP_Port_Available(this.e)) <= 0)
/*    */         {
/*    */ 
/*    */           
/* 31 */           if (System.currentTimeMillis() - l <= paramInt * 1000L)
/*    */           
/*    */           { 
/*    */             
/*    */             try { 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */               
/* 47 */               Thread.sleep(50L); }
/*    */             
/* 49 */             catch (InterruptedException interruptedException) { null.printStackTrace(); }  continue; }  }  byte[] arrayOfByte = new byte[m]; Pointer pointer1 = this.e; int k = paramInt * 1000; int j; if ((j = autoReplyPrint1.CP_Port_Read(pointer1, this, m, k)) > 0) { if (j <= m) m = j;  System.arraycopy(this, 0, arrayOfByte1, 0, m); byte[] arrayOfByte1; return arrayOfByte1 = new byte[m]; }  return null; }  }  return null; }
/*    */ 
/*    */   
/*    */   public final boolean c() {
/*    */     Pointer pointer;
/*    */     if ((pointer = this.e) == null)
/*    */       return false; 
/*    */     return AutoReplyPrint.INSTANCE.CP_Port_IsConnectionValid(this);
/*    */   }
/*    */   
/*    */   public Pointer e = Pointer.NULL; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\e\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */