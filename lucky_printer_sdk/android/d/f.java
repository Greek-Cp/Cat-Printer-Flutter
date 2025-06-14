/*  1 */ package d;public final class f implements ResultCallback { public final void onFail() { ResultCallback resultCallback; if ((resultCallback = this.b) != null) resultCallback
/*  2 */         .onFail();  this
/*    */       
/*  4 */       .c
/*  5 */       .getClass(); c.a(23); } public f(i parami, int paramInt, d paramd) {} public final void onSuccess(Integer paramInteger) { int j; if (paramInteger.intValue() > (j = this.a)) paramInteger = Integer.valueOf(j);  this.c
/*  6 */       .o = paramInteger.intValue();
/*    */ 
/*    */     
/*  9 */     h.a("onePackSize : " + this.c.o);
/*    */     
/* 11 */     BleManager.getInstance()
/*    */       
/* 13 */       .setSplitWriteNum(this.c.o); ResultCallback resultCallback;
/* 14 */     if ((resultCallback = this.b) != null) resultCallback
/* 15 */         .onSuccess(Integer.valueOf(1));  this
/*    */       
/* 17 */       .c
/* 18 */       .getClass(); c.a(22); }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */