/*    */ package com.luckprinter.sdk_new.device.normal;
/*  2 */ public class LuckP_L2 extends BaseNormalDevice { public LuckP_L2() { setEndLineDot(130); } public void initAfterConnect() { super.initAfterConnect(); super(this); ResultCallback<String> resultCallback; printerModelLuck(resultCallback); }
/*    */ 
/*    */   
/*    */   public boolean is300Dpi() {
/*    */     return true;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\LuckP_L2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */