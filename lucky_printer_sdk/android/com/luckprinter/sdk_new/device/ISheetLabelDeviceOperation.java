package com.luckprinter.sdk_new.device;

import android.graphics.Bitmap;
import com.luckprinter.sdk_new.callback.ResultCallback;

public interface ISheetLabelDeviceOperation extends IDeviceOperation {
  void setDensityLuck(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void setSpeedLuck(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void getDensityLuck(ResultCallback<Integer> paramResultCallback);
  
  void getSpeedLuck(ResultCallback<Integer> paramResultCallback);
  
  void print(Bitmap paramBitmap, int paramInt);
  
  void printOnce(Bitmap paramBitmap);
  
  void printTag(Bitmap paramBitmap, int paramInt);
  
  void printTagOnce(Bitmap paramBitmap);
  
  @Deprecated
  void printTag(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Bitmap paramBitmap, int paramInt5);
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\ISheetLabelDeviceOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */