/* 1 */ package com.luckprinter.sdk_new.scan;public final class b extends BleScanCallback { public final void onScanStarted(boolean paramBoolean) { BtReceiver.Listener listener; if ((listener = this.a.scanDeviceListener) != null)
/* 2 */       startDiscovery();  } public b(BleScanDeviceHelper paramBleScanDeviceHelper) {} public final void onScanning(BleDevice paramBleDevice) { if (this.a.scanDeviceListener != null) { String str1 = paramBleDevice.getName();
/* 3 */       String str2 = paramBleDevice.getMac();
/* 4 */       if (PrinterHelper.getInstance().isAvailableLocalDevice(str1)) {
/* 5 */         BluetoothDevice bluetoothDevice = paramBleDevice.getDevice();
/* 6 */         this.a.scanDeviceListener.foundDev(getType(), str1, str2);
/*   */       }  }
/*   */      }
/*   */ 
/*   */   
/*   */   public final void onScanFinished(List paramList) {
/*   */     BtReceiver.Listener listener;
/*   */     if ((listener = this.a.scanDeviceListener) != null)
/*   */       finishDiscovery(); 
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\scan\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */