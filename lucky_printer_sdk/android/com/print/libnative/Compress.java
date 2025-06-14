/* 1 */ package com.print.libnative;public class Compress { static { System.loadLibrary("PrinterNative"); }
/*   */ 
/*   */   
/*   */   public static native byte[] codeESC(byte[] paramArrayOfbyte);
/*   */   
/*   */   public static native byte[] codeCPCL(byte[] paramArrayOfbyte);
/*   */   
/*   */   public static native byte[] codeTSPL(byte[] paramArrayOfbyte);
/*   */   
/*   */   public static native byte[] codeLihu(byte[] paramArrayOfbyte); }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\print\libnative\Compress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */