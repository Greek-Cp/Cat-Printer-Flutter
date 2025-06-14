package com.luckprinter.sdk_new.device;

import android.graphics.Bitmap;
import com.luckprinter.sdk_new.callback.ResultCallback;
import com.luckprinter.sdk_new.callback.ResultPageCallback;

public interface IA4DeviceOperation extends INormalDeviceOperation {
  void printFolder(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback);
  
  void printFolderOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback);
  
  void printTattoo(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback);
  
  void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback);
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\IA4DeviceOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */