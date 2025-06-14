package com.luckprinter.sdk_new.device;

import com.luckprinter.sdk_new.bean.PrinterStatusData;
import com.luckprinter.sdk_new.callback.ResultCallback;

public interface IDeviceOperation {
  void printerModelLuck(ResultCallback<String> paramResultCallback);
  
  void printerSNLuck(ResultCallback<String> paramResultCallback);
  
  void printerVersionLuck(ResultCallback<String> paramResultCallback);
  
  void printerStatusLuck(ResultCallback<PrinterStatusData> paramResultCallback);
  
  void printerSettingLuck(ResultCallback<String> paramResultCallback);
  
  int getUploadErrorCode(byte[] paramArrayOfbyte);
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\IDeviceOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */