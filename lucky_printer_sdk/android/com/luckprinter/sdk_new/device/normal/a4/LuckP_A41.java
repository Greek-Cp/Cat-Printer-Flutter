/*    */ package com.luckprinter.sdk_new.device.normal.a4;public class LuckP_A41 extends BaseLuckPA4Device { public LuckP_A41() {
/*  2 */     setCompress(false);
/*  3 */     setMinDensity(0);
/*  4 */     setMaxDensity(15);
/*    */   }
/*    */   
/*    */   public void initAfterConnect() {
/*    */     super.initAfterConnect();
/*    */     super(this);
/*    */     ResultCallback<String> resultCallback;
/*    */     printerVersionLuck(resultCallback);
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\a4\LuckP_A41.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */