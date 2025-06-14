package com.luckprinter.sdk_new.callback;

import android.graphics.Bitmap;

public interface OnPrintCallback {
  void onStartPrint();
  
  @Deprecated
  void onPrinting(int paramInt1, int paramInt2);
  
  default void onPrintIndexStart(Bitmap paramBitmap, int paramInt1, int paramInt2) {}
  
  default void onPrintIndexEnd(Bitmap paramBitmap, int paramInt1, int paramInt2) {}
  
  void onPrintSuccess();
  
  void onPrintFail(int paramInt);
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\callback\OnPrintCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */