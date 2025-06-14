/* 1 */ package b;public final class d implements ResultCallback { public final void onFail() { this.a.countDown(); } public d(f paramf, CountDownLatch paramCountDownLatch) {} public final void onSuccess(Object paramObject) { String str = (String)paramObject;
/* 2 */     this.b
/* 3 */       .d = this;
/* 4 */     this.a.countDown(); }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\b\d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */