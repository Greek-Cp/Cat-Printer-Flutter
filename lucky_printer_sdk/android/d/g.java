/*   */ package d;public final class g extends BleMtuChangedCallback { public final void onSetMTUFailure(BleException paramBleException) {
/* 2 */     h.a("set mtu fail! " + paramBleException.getDescription());
/* 3 */     byte b = 2; this.d
/* 4 */       .getClass(); c.a("set mtu fail! " + paramBleException.getDescription(), b);
/* 5 */     i i1 = this.d; int j, m = (j = this.b) - 20;
/* 6 */     f f = (f)this.c; i1.getClass(); int k; if ((k = this.a + 1) <= 3 && m >= 20) {
/* 7 */       g g1; BleDevice bleDevice = i1.j; int n = j - 17; this(i1, k, m, f); BleManager.getInstance().setMtu(this, n, this);
/*   */     } 
/*   */   }
/*   */   
/*   */   public g(i parami, int paramInt1, int paramInt2, f paramf) {}
/*   */   
/*   */   public final void onMtuChanged(int paramInt) {
/*   */     int j = paramInt - 3;
/*   */     h.a("set mtu result:" + j);
/*   */     this.c.onSuccess(Integer.valueOf(j));
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */