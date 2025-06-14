/* 1 */ package d;public final class b extends BleWriteCallback { public final void onWriteSuccess(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) { h h1; if ((h1 = this.a) != null) h1
/* 2 */         .getClass();
/*   */ 
/*   */     
/* 5 */     Integer integer1 = Integer.valueOf(paramArrayOfbyte.length), integer2 = Integer.valueOf(paramInt1), integer3 = Integer.valueOf(paramInt2); Object[] arrayOfObject; (arrayOfObject = new Object[3])[0] = this; (new Object[3])[1] = integer2; (new Object[3])[2] = integer3;
/* 6 */     h.a(String.format("write success, justWrite:%d, progress:%d/%d", arrayOfObject));
/*   */     
/* 8 */     this.b.countDown(); }
/*   */ 
/*   */   
/*   */   public b(i parami, h paramh, CountDownLatch paramCountDownLatch) {}
/*   */   
/*   */   public final void onWriteFailure(BleException paramBleException) {
/*   */     this.c.i.set(false);
/*   */     h.a("write failure : " + paramBleException.getDescription());
/*   */     this.b.countDown();
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */