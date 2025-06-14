package com.luckprinter.sdk_new.callback;

import android.graphics.Bitmap;

public interface ResultPageCallback<T> {
  void onSuccess(T paramT);
  
  void onPageIndexStart(Bitmap paramBitmap, int paramInt1, int paramInt2);
  
  void onPageIndexEnd(Bitmap paramBitmap, int paramInt1, int paramInt2);
  
  void onFail();
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\callback\ResultPageCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */