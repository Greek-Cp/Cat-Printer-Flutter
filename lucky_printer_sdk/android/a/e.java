/*    */ package a;public final class e implements Cloneable { public final Object clone() {
/*    */     byte[] arrayOfByte;
/*  3 */     if ((arrayOfByte = (this = (e)super.clone()).a) != null) {
/*  4 */       int i; byte[] arrayOfByte1 = new byte[i = arrayOfByte.length];
/*  5 */       System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, i);
/*  6 */       this.a = arrayOfByte1;
/*    */     } 
/*    */     
/*  9 */     if ((arrayOfByte = this.b) != null) {
/* 10 */       int i; byte[] arrayOfByte1 = new byte[i = arrayOfByte.length];
/* 11 */       System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, i);
/* 12 */       this.b = arrayOfByte1;
/*    */     } 
/*    */     
/* 15 */     if ((arrayOfByte = this.c) != null)
/* 16 */     { int i; byte[] arrayOfByte1 = new byte[i = arrayOfByte.length];
/* 17 */       System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, i);
/* 18 */       this.c = arrayOfByte1; }  return this;
/*    */   }
/*    */   
/*    */   public byte[] a;
/*    */   public byte[] b;
/*    */   public byte[] c; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\a\e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */