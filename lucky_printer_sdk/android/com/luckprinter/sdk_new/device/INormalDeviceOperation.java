package com.luckprinter.sdk_new.device;

import android.graphics.Bitmap;
import com.luckprinter.sdk_new.callback.ResultCallback;
import com.luckprinter.sdk_new.callback.ResultPageCallback;
import com.luckprinter.sdk_new.callback.UpdateListener;
import java.io.File;

public interface INormalDeviceOperation extends IDeviceOperation {
  void getDeviceBoot(ResultCallback<String> paramResultCallback);
  
  void getShutTimeLuck(ResultCallback<Integer> paramResultCallback);
  
  void getBatteryLuck(ResultCallback<Integer> paramResultCallback);
  
  void getDensityLuck(ResultCallback<Integer> paramResultCallback);
  
  void setDensityLuck(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void setShutTimeLuck(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void setRecoveryLuck(ResultCallback<Integer> paramResultCallback);
  
  void enablePrinterLuck();
  
  void stopPrintJobLuckNoCallback();
  
  void stopPrintJobLuck(ResultCallback<Integer> paramResultCallback);
  
  void printLineDotsLuck(int paramInt);
  
  void printReverseLineDotsLuck(int paramInt);
  
  void printerPositionLuck();
  
  void printerWakeupLuck();
  
  void printerSetWidth(int paramInt);
  
  void setHeatingLevel(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void setPrinterMode(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void setPaperType(int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback);
  
  void setPaperTypeNoCallback(int paramInt1, int paramInt2);
  
  void adjustPositionAuto(int paramInt);
  
  void getSpeedLuck(ResultCallback<Integer> paramResultCallback);
  
  void setSpeedLuck(int paramInt, ResultCallback<Integer> paramResultCallback);
  
  void updatePrinterLuck(File paramFile, UpdateListener paramUpdateListener);
  
  void print(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback);
  
  void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback);
  
  void printTag(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback);
  
  void printBlackTag(Bitmap paramBitmap, int paramInt, ResultPageCallback<Integer> paramResultPageCallback);
  
  void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback);
  
  void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback);
  
  void sendCommand(byte[] paramArrayOfbyte, ResultCallback<Integer> paramResultCallback);
  
  void getTimeFormat(ResultCallback<Integer> paramResultCallback);
  
  void setTimeFormat(int paramInt, long paramLong, ResultCallback<Integer> paramResultCallback);
  
  void setMarkPrintLast();
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\INormalDeviceOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */