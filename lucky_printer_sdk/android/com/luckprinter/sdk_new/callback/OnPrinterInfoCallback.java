package com.luckprinter.sdk_new.callback;

public interface OnPrinterInfoCallback {
  void onStart();
  
  void onVersion(String paramString);
  
  void onName(String paramString);
  
  void onMac(String paramString);
  
  void onSn(String paramString);
  
  void onModel(String paramString);
  
  void onBattery(String paramString);
  
  void onShutTime(int paramInt);
  
  void onDensity(int paramInt);
  
  void onFinish();
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\callback\OnPrinterInfoCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */