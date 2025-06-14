/*   1 */ package com.luckprinter.sdk_new.device;public class PrinterHelper { public static final String TAG = "PrinterManager"; private static volatile PrinterHelper instance; private Context context; private BaseDevice printerDevice; private HashMap<String, PrinterProperty> customPropertyMap; private ConcurrentLinkedQueue<OnClientConnectionListener> connectListeners; private ConcurrentLinkedQueue<OnReceiveDeviceStatusListener> deviceStatusListeners; private ConcurrentLinkedQueue<OnEventListener> eventListeners; private ConcurrentLinkedQueue<DeviceForbiddenListener> deviceForbiddenListeners; private Handler handler; private f deviceForbiddenChecker; public BaseDevice getPrinter() { BaseDevice baseDevice; return ((baseDevice = this.printerDevice) == null) ? null : this; } public void setEventRecorder(IEventRecorder paramIEventRecorder) { f.a = paramIEventRecorder; } public void setLogFilter(ILogFilter paramILogFilter) { d.b().getClass(); (h.a()).a = paramILogFilter; } public void setEnableBle(boolean paramBoolean) { a.a = paramBoolean; } public synchronized boolean connectLuck(String paramString1, String paramString2) { if ((this.printerDevice = initDevice(paramString1, paramString2)) != null) return d.b().a(this.printerDevice);  return false; } public synchronized boolean disconnectLuck() { return d.b().a(); } public boolean isConnectedLuck() { return (d.b()).a.c(); } public void print(Bitmap paramBitmap, int paramInt, OnPrintCallback paramOnPrintCallback) { if (!isConnectedLuck()) return;  getStatusBeforePrint(paramInteger -> { if (paramInteger.intValue() >= 0) { paramOnPrintCallback.onPrintFail(paramInteger.intValue()); } else { ResultPageCallback<Integer> resultPageCallback; paramOnPrintCallback.onStartPrint(); if (isNormalPrinter()) { super(this, paramOnPrintCallback); ((BaseNormalDevice)this.printerDevice).print(paramBitmap, paramInt, resultPageCallback); } else if (isSheetLabelPrinter()) { ((BaseSheetLabelDevice)this.printerDevice).print((Bitmap)resultPageCallback, paramInt); for (byte b = 1; b <= paramInt; b++) { paramOnPrintCallback.onPrintIndexStart((Bitmap)resultPageCallback, b, paramInt); paramOnPrintCallback.onPrintIndexEnd((Bitmap)resultPageCallback, b, paramInt); }  paramOnPrintCallback.onPrintSuccess(); }  }  }); } public void init(Context paramContext, String paramString, boolean paramBoolean) { a.b = paramString;
/*   2 */     a.c = paramBoolean;
/*     */     Context context;
/*   4 */     PrinterUtil.initContext(context = paramContext.getApplicationContext());
/*     */ 
/*     */     
/*   7 */     BleManager.getInstance().init((Application)this);
/*   8 */     BleManager.getInstance()
/*   9 */       .enableLog(paramBoolean)
/*  10 */       .setSplitWriteNum(20)
/*  11 */       .setConnectOverTime(10000L)
/*  12 */       .setOperateTimeout(5000); } public void printOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { if (!isConnectedLuck()) return;  if (isNormalPrinter()) { ((BaseNormalDevice)this.printerDevice).printOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback); } else { ((BaseSheetLabelDevice)this.printerDevice).printOnce(paramBitmap); if (isSheetLabelPrinter() && paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(1));  }  } public void printTattoo(Bitmap paramBitmap, int paramInt, OnPrintCallback paramOnPrintCallback) { if (!isConnectedLuck()) return;  BaseDevice baseDevice; if (!(baseDevice = this.printerDevice instanceof BaseA4Device)) return;  getStatusBeforePrint(paramInteger -> { if (paramInteger.intValue() >= 0) { paramOnPrintCallback.onPrintFail(paramInteger.intValue()); } else { ResultPageCallback<Integer> resultPageCallback; paramOnPrintCallback.onStartPrint(); super(this, paramOnPrintCallback); paramBaseA4Device.printTattoo(paramBitmap, paramInt, resultPageCallback); }  }); } public void printTattooOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { if (!isConnectedLuck()) return;  BaseDevice baseDevice; if (!(baseDevice = this.printerDevice instanceof BaseA4Device)) return;  ((BaseA4Device)this).printTattooOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback); } public void printBlackTag(Bitmap paramBitmap, int paramInt, OnPrintCallback paramOnPrintCallback) { if (!isConnectedLuck()) return;  BaseDevice baseDevice; if (!(baseDevice = this.printerDevice instanceof BaseNormalDevice)) return;  getStatusBeforePrint(paramInteger -> { if (paramInteger.intValue() >= 0) { paramOnPrintCallback.onPrintFail(paramInteger.intValue()); } else { ResultPageCallback<Integer> resultPageCallback; paramOnPrintCallback.onStartPrint(); super(this, paramOnPrintCallback); paramBaseNormalDevice.printBlackTag(paramBitmap, paramInt, resultPageCallback); }  }); } public void printTag(Bitmap paramBitmap, int paramInt, OnPrintCallback paramOnPrintCallback) { if (!isConnectedLuck()) return;  getStatusBeforePrint(paramInteger -> { if (paramInteger.intValue() >= 0) { paramOnPrintCallback.onPrintFail(paramInteger.intValue()); } else { ResultPageCallback<Integer> resultPageCallback; paramOnPrintCallback.onStartPrint(); if (isNormalPrinter()) { super(this, paramOnPrintCallback); ((BaseNormalDevice)this.printerDevice).printTag(paramBitmap, paramInt, resultPageCallback); } else if (isSheetLabelPrinter()) { ((BaseSheetLabelDevice)this.printerDevice).printTag((Bitmap)resultPageCallback, paramInt); for (byte b = 1; b <= paramInt; b++) { paramOnPrintCallback.onPrintIndexStart((Bitmap)resultPageCallback, b, paramInt); paramOnPrintCallback.onPrintIndexEnd((Bitmap)resultPageCallback, b, paramInt); }  paramOnPrintCallback.onPrintSuccess(); }  }  }); } public void printTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { if (!isConnectedLuck()) return;  if (isNormalPrinter()) { ((BaseNormalDevice)this.printerDevice).printTagOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback); } else { ((BaseSheetLabelDevice)this.printerDevice).printTagOnce(paramBitmap); if (isSheetLabelPrinter() && paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(1));  }  } public void printBlackTagOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { if (!isConnectedLuck()) return;  if (isNormalPrinter()) { ((BaseNormalDevice)this.printerDevice).printBlackTagOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback); } else { ((BaseSheetLabelDevice)this.printerDevice).printTagOnce(paramBitmap); if (isSheetLabelPrinter() && paramResultCallback != null) paramResultCallback.onSuccess(Integer.valueOf(1));  }  } public void printFolder(Bitmap paramBitmap, int paramInt, OnPrintCallback paramOnPrintCallback) { if (!isConnectedLuck()) return;  if (isA4Printer()) getStatusBeforePrint(paramInteger -> { if (paramInteger.intValue() >= 0) { paramOnPrintCallback.onPrintFail(paramInteger.intValue()); } else { ResultPageCallback<Integer> resultPageCallback; paramOnPrintCallback.onStartPrint(); super(this, paramOnPrintCallback); paramBaseA4Device.printFolder(paramBitmap, paramInt, resultPageCallback); }  });  } public void printFolderOnce(Bitmap paramBitmap, int paramInt1, int paramInt2, ResultCallback<Integer> paramResultCallback) { if (!isConnectedLuck()) return;  if (isA4Printer()) ((BaseA4Device)this.printerDevice).printFolderOnce(paramBitmap, paramInt1, paramInt2, paramResultCallback);  } @Deprecated public void printSheetLabel(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Bitmap paramBitmap, int paramInt5) { if (!isConnectedLuck()) return;  if (isSheetLabelPrinter()) ((BaseSheetLabelDevice)this.printerDevice).printTag(paramInt1, paramInt2, paramInt3, paramInt4, paramBitmap, paramInt5);  } public void addBitmapCache(Bitmap paramBitmap) { PrintBitmapDataCache.getInstance().addBitmapCache(paramBitmap, false); } public void getAllInfo(final OnPrinterInfoCallback callback) { if (!isConnectedLuck()) return;  if (callback == null) return;  if (isNormalPrinter()) { PrinterUtil.runOnUi(() -> paramOnPrinterInfoCallback.onStart()); final BaseNormalDevice normalDevice; callback.onName((baseNormalDevice = (BaseNormalDevice)this.printerDevice).getDeviceName()); callback.onMac(baseNormalDevice.getDeviceMac());
/*  13 */       PrinterUtil.cacheExecutor.execute(new Runnable() { public void run() { this(6); final CountDownLatch cd; normalDevice.getBatteryLuck(new ResultCallback<Integer>() { public void onFail() {}
/*     */                     public void onSuccess(Integer param2Integer) { callback.onBattery(param2Integer + ""); cd.countDown(); } });
/*  15 */               normalDevice.printerSNLuck(new ResultCallback<String>()
/*     */                   {
/*     */                     public void onFail() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     public void onSuccess(String param2String) {
/*     */                       callback.onSn(param2String);
/*     */                       cd.countDown();
/*     */                     }
/*     */                   });
/*  28 */               normalDevice.printerModelLuck(new ResultCallback<String>()
/*     */                   {
/*     */                     public void onFail() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     public void onSuccess(String param2String) {
/*     */                       callback.onModel(param2String);
/*     */                       cd.countDown();
/*     */                     }
/*     */                   });
/*  41 */               normalDevice.printerVersionLuck(new ResultCallback<String>()
/*     */                   {
/*     */                     public void onFail() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     public void onSuccess(String param2String) {
/*     */                       callback.onVersion(param2String);
/*     */                       cd.countDown();
/*     */                     }
/*     */                   });
/*  54 */               normalDevice.getShutTimeLuck(new ResultCallback<Integer>()
/*     */                   {
/*     */                     public void onFail() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     public void onSuccess(Integer param2Integer) {
/*     */                       callback.onShutTime(param2Integer.intValue());
/*     */                       cd.countDown();
/*     */                     }
/*     */                   });
/*  67 */               normalDevice.getDensityLuck(new ResultCallback<Integer>()
/*     */                   {
/*     */                     public void onFail() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     public void onSuccess(Integer param2Integer) {
/*     */                       callback.onDensity(param2Integer.intValue());
/*     */                       cd.countDown();
/*     */                     }
/*     */                   });
/*     */               
/*  81 */               try { (countDownLatch = new CountDownLatch()).await(5000L, TimeUnit.MILLISECONDS); }
/*     */               
/*  83 */               catch (InterruptedException interruptedException) { null.printStackTrace(); }
/*     */               
/*  85 */               PrinterUtil.runOnUi(() -> param1OnPrinterInfoCallback.onFinish()); }
/*     */              }
/*     */         );
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */        }
/*     */     
/* 103 */     else if (isSheetLabelPrinter())
/*     */     
/* 105 */     { PrinterUtil.runOnUi(() -> paramOnPrinterInfoCallback.onStart()); BaseSheetLabelDevice baseSheetLabelDevice;
/* 106 */       callback.onName((baseSheetLabelDevice = (BaseSheetLabelDevice)this.printerDevice).getDeviceName());
/* 107 */       callback.onMac(baseSheetLabelDevice.getDeviceMac());
/* 108 */       PrinterUtil.cacheExecutor.execute(() -> {
/*     */             this(3);
/*     */             ResultCallback<String> resultCallback;
/*     */             CountDownLatch countDownLatch;
/*     */             super(this, paramOnPrinterInfoCallback, countDownLatch);
/*     */             paramBaseSheetLabelDevice.printerSNLuck(resultCallback);
/*     */             super(this, paramOnPrinterInfoCallback, countDownLatch);
/*     */             paramBaseSheetLabelDevice.printerVersionLuck(resultCallback);
/*     */             super(this, paramOnPrinterInfoCallback, countDownLatch);
/*     */             paramBaseSheetLabelDevice.printerModelLuck(resultCallback);
/*     */             long l = 5000L;
/*     */             try {
/*     */               TimeUnit timeUnit = TimeUnit.MILLISECONDS;
/*     */               (countDownLatch = new CountDownLatch()).await(l, timeUnit);
/*     */             } catch (InterruptedException interruptedException) {
/*     */               null.printStackTrace();
/*     */             } 
/*     */             PrinterUtil.runOnUi(());
/*     */           }); }
/*     */      }
/*     */ 
/*     */   
/*     */   public void updatePrinterLuck(File paramFile, UpdateListener paramUpdateListener) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).updatePrinterLuck(paramFile, paramUpdateListener); 
/*     */   }
/*     */   
/*     */   public void getShutTimeLuck(ResultCallback<Integer> paramResultCallback) {
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).getShutTimeLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void setShutTimeLuck(int paramInt, ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).setShutTimeLuck(paramInt, paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void getDensityLuck(ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter()) {
/*     */       ((BaseNormalDevice)this.printerDevice).getDensityLuck(paramResultCallback);
/*     */     } else if (isSheetLabelPrinter()) {
/*     */       ((BaseSheetLabelDevice)this.printerDevice).getDensityLuck(paramResultCallback);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDensityLuck(int paramInt, ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter()) {
/*     */       ((BaseNormalDevice)this.printerDevice).setDensityLuck(paramInt, paramResultCallback);
/*     */     } else if (isSheetLabelPrinter()) {
/*     */       ((BaseSheetLabelDevice)this.printerDevice).setDensityLuck(paramInt, paramResultCallback);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printerSettingLuck(final ResultCallback<String> callback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     this.printerDevice.printerSettingLuck(new ResultCallback<String>() {
/*     */           public void onFail() {
/*     */             callback.onFail();
/*     */           }
/*     */           
/*     */           public void onSuccess(String param1String) {
/*     */             ResultCallback resultCallback;
/*     */             if ((resultCallback = callback) != null)
/*     */               onSuccess(param1String); 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void setPaperTypeLuck(int paramInt, ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     PaperType paperType;
/*     */     if ((paperType = PaperType.getFromType(paramInt)) == null)
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).setPaperType(1, paperType.getData(), paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void getSpeed(ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter()) {
/*     */       ((BaseNormalDevice)this.printerDevice).getSpeedLuck(paramResultCallback);
/*     */     } else if (isSheetLabelPrinter()) {
/*     */       ((BaseSheetLabelDevice)this.printerDevice).getSpeedLuck(paramResultCallback);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSpeedLuck(int paramInt, ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter()) {
/*     */       ((BaseNormalDevice)this.printerDevice).setSpeedLuck(paramInt, paramResultCallback);
/*     */     } else if (isSheetLabelPrinter()) {
/*     */       ((BaseSheetLabelDevice)this.printerDevice).setSpeedLuck(paramInt, paramResultCallback);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getTimeFormat(ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).getTimeFormat(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void setTimeFormat(int paramInt, long paramLong, ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).setTimeFormat(paramInt, paramLong, paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void getBatteryLuck(ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).getBatteryLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void setL2ProPrinterMode(int paramInt, ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).setPrinterMode(paramInt, paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void printerModelLuck(ResultCallback<String> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) != null)
/*     */       super.printerModelLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void printerBootLuck(ResultCallback<String> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).getDeviceBoot(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void getPrinterStatus(ResultCallback<PrinterStatusData> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) != null)
/*     */       super.printerStatusLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void printerVersionLuck(ResultCallback<String> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) != null)
/*     */       super.printerVersionLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void printerSNLuck(ResultCallback<String> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) != null)
/*     */       super.printerSNLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void printerStatusLuck(ResultCallback<PrinterStatusData> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) != null)
/*     */       super.printerStatusLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void printLineDotsLuck(int paramInt) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).printLineDotsLuck(paramInt); 
/*     */   }
/*     */   
/*     */   public void printReverseLineDotsLuck(int paramInt) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).printReverseLineDotsLuck(paramInt); 
/*     */   }
/*     */   
/*     */   public void setRecoveryLuck(ResultCallback<Integer> paramResultCallback) {
/*     */     if (!isConnectedLuck())
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).setRecoveryLuck(paramResultCallback); 
/*     */   }
/*     */   
/*     */   public void addConnectListener(OnClientConnectionListener paramOnClientConnectionListener) {
/*     */     if (!this.connectListeners.contains(paramOnClientConnectionListener))
/*     */       this.connectListeners.add(paramOnClientConnectionListener); 
/*     */   }
/*     */   
/*     */   public void removeConnectListener(OnClientConnectionListener paramOnClientConnectionListener) {
/*     */     this.connectListeners.remove(paramOnClientConnectionListener);
/*     */   }
/*     */   
/*     */   public void addDeviceStatusListener(OnReceiveDeviceStatusListener paramOnReceiveDeviceStatusListener) {
/*     */     if (!this.deviceStatusListeners.contains(paramOnReceiveDeviceStatusListener))
/*     */       this.deviceStatusListeners.add(paramOnReceiveDeviceStatusListener); 
/*     */   }
/*     */   
/*     */   public void removeDeviceStatusListener(OnReceiveDeviceStatusListener paramOnReceiveDeviceStatusListener) {
/*     */     this.deviceStatusListeners.remove(paramOnReceiveDeviceStatusListener);
/*     */   }
/*     */   
/*     */   public void addEventListener(OnEventListener paramOnEventListener) {
/*     */     if (!this.eventListeners.contains(paramOnEventListener))
/*     */       this.eventListeners.add(paramOnEventListener); 
/*     */   }
/*     */   
/*     */   public void removeEventListener(OnEventListener paramOnEventListener) {
/*     */     this.eventListeners.remove(paramOnEventListener);
/*     */   }
/*     */   
/*     */   public void addDeviceForbiddenListener(DeviceForbiddenListener paramDeviceForbiddenListener) {
/*     */     if (!this.deviceForbiddenListeners.contains(paramDeviceForbiddenListener))
/*     */       this.deviceForbiddenListeners.add(paramDeviceForbiddenListener); 
/*     */   }
/*     */   
/*     */   public void removeDeviceForbiddenListener(DeviceForbiddenListener paramDeviceForbiddenListener) {
/*     */     this.deviceForbiddenListeners.remove(paramDeviceForbiddenListener);
/*     */   }
/*     */   
/*     */   public BaseDevice getPrinterDevice() {
/*     */     return this.printerDevice;
/*     */   }
/*     */   
/*     */   public boolean is304Dpi() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return false; 
/*     */     return is300Dpi();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getMinDensity() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 0; 
/*     */     return getMinDensity();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getMaxDensity() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 2; 
/*     */     return getMaxDensity();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getMinSpeed() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 0; 
/*     */     return getMinSpeed();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getMaxSpeed() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 8; 
/*     */     return getMaxSpeed();
/*     */   }
/*     */   
/*     */   public List<Integer> getDensityList() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return List.of(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2)); 
/*     */     return getDensityList();
/*     */   }
/*     */   
/*     */   public List<Integer> getSpeedList() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return List.of(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8)); 
/*     */     return getSpeedList();
/*     */   }
/*     */   
/*     */   public String getNamePrefix() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return ""; 
/*     */     return getNamePrefix();
/*     */   }
/*     */   
/*     */   public boolean isSupportSetSpeed() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return false; 
/*     */     return isSupportSetSpeed();
/*     */   }
/*     */   
/*     */   public boolean isL3Printer() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return false; 
/*     */     return this instanceof com.luckprinter.sdk_new.device.normal.LuckP_L3;
/*     */   }
/*     */   
/*     */   public boolean isA4Printer() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) != null)
/*     */       return this instanceof BaseA4Device; 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public boolean isL90Printer() {
/*     */     return this.printerDevice instanceof com.luckprinter.sdk_new.device.normal.a4.LuckP_L90;
/*     */   }
/*     */   
/*     */   public boolean isAvailableLocalDevice(String paramString) {
/*     */     if (isNormalPrinter(paramString) || isSheetLabelPrinter(paramString))
/*     */       return true; 
/*     */   }
/*     */   
/*     */   public boolean isNormalPrinter(String paramString) {
/*     */     boolean bool2 = false;
/*     */     PrinterEnum.BluetoothType bluetoothType = null;
/*     */     boolean bool3 = false;
/*     */     PrinterProperty printerProperty;
/*     */     if ((printerProperty = getCustomProperty(paramString)) != null) {
/*     */       String str1 = getPrinterType();
/*     */       bluetoothType = PrinterEnum.BluetoothType.fromName(getBtType());
/*     */       bool2 = isBleEnable();
/*     */       if ("normal".equals(this) || "a4".equals(this)) {
/*     */         boolean bool = true;
/*     */       } else {
/*     */         boolean bool = false;
/*     */       } 
/*     */     } else {
/*     */       PrinterEnum printerEnum;
/*     */       if ((printerEnum = PrinterEnum.getByStartWithName(paramString)) != null) {
/*     */         bluetoothType = this.btType;
/*     */         bool2 = this.isBleEnable;
/*     */         bool3 = BaseNormalDevice.class.isAssignableFrom(getPrinterClass());
/*     */       } 
/*     */     } 
/*     */     boolean bool1 = PrinterUtil.isNameEndWithBle(paramString);
/*     */     if (bool3 && (bluetoothType != PrinterEnum.BluetoothType.CLASSIC_BLE || !a.a || !bool2))
/*     */       i = bool1 ^ true; 
/*     */     String str = i + "";
/*     */     this("btfilter name:");
/*     */     int i;
/*     */     stringBuilder.append(paramString);
/*     */     stringBuilder.append(", flag:");
/*     */     StringBuilder stringBuilder;
/*     */     (stringBuilder = new StringBuilder()).append(this);
/*     */     PrinterUtil.log("PrinterManager", (new StringBuilder()).toString());
/*     */     return i;
/*     */   }
/*     */   
/*     */   public boolean isSheetLabelPrinter(String paramString) {
/*     */     boolean bool2 = false;
/*     */     PrinterEnum.BluetoothType bluetoothType = null;
/*     */     boolean bool3 = false;
/*     */     PrinterProperty printerProperty;
/*     */     if ((printerProperty = getCustomProperty(paramString)) != null) {
/*     */       String str1 = getPrinterType();
/*     */       bluetoothType = PrinterEnum.BluetoothType.fromName(getBtType());
/*     */       bool2 = isBleEnable();
/*     */       bool3 = "sheet_label".equals(this);
/*     */     } else {
/*     */       PrinterEnum printerEnum;
/*     */       if ((printerEnum = PrinterEnum.getByStartWithName(paramString)) != null) {
/*     */         bluetoothType = this.btType;
/*     */         bool2 = this.isBleEnable;
/*     */         bool3 = BaseSheetLabelDevice.class.isAssignableFrom(getPrinterClass());
/*     */       } 
/*     */     } 
/*     */     boolean bool1 = PrinterUtil.isNameEndWithBle(paramString);
/*     */     if (bool3 && (bluetoothType != PrinterEnum.BluetoothType.CLASSIC_BLE || !a.a || !bool2))
/*     */       i = bool1 ^ true; 
/*     */     String str = i + "";
/*     */     this("btfilter name:");
/*     */     int i;
/*     */     stringBuilder.append(paramString);
/*     */     stringBuilder.append(", flag:");
/*     */     StringBuilder stringBuilder;
/*     */     (stringBuilder = new StringBuilder()).append(this);
/*     */     PrinterUtil.log("PrinterManager", (new StringBuilder()).toString());
/*     */     return i;
/*     */   }
/*     */   
/*     */   public boolean isDeviceConnectUseBle(String paramString) {
/*     */     boolean bool2 = false;
/*     */     PrinterEnum.BluetoothType bluetoothType = null;
/*     */     PrinterProperty printerProperty;
/*     */     if ((printerProperty = getCustomProperty(paramString)) != null) {
/*     */       bluetoothType = PrinterEnum.BluetoothType.fromName(getBtType());
/*     */       bool2 = isBleEnable();
/*     */     } else {
/*     */       PrinterEnum printerEnum;
/*     */       if ((printerEnum = PrinterEnum.getByStartWithName(paramString)) != null) {
/*     */         bluetoothType = this.btType;
/*     */         bool2 = this.isBleEnable;
/*     */       } 
/*     */     } 
/*     */     boolean bool1 = PrinterUtil.isNameEndWithBle(paramString);
/*     */     return (BleManager.getInstance().isSupportBle() && a.a && bool2 && (bluetoothType == PrinterEnum.BluetoothType.BLE_DUAL || (bluetoothType == PrinterEnum.BluetoothType.CLASSIC_BLE && bool1)));
/*     */   }
/*     */   
/*     */   public int getA4PrintWidth() {
/*     */     BaseDevice baseDevice;
/*     */     if (baseDevice = this.printerDevice instanceof BaseA4Device)
/*     */       return ((BaseA4Device)this).getA4PrintWidth(); 
/*     */     return 1616;
/*     */   }
/*     */   
/*     */   public int getA4PrintHeight() {
/*     */     BaseDevice baseDevice;
/*     */     if (baseDevice = this.printerDevice instanceof BaseA4Device)
/*     */       return ((BaseA4Device)this).getA4PrintHeight(); 
/*     */     return 2300;
/*     */   }
/*     */   
/*     */   public int getPrintWidth() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 384; 
/*     */     return getPrintWidth();
/*     */   }
/*     */   
/*     */   public int getPrintMaxWidth() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 384; 
/*     */     return getPrintMaxWidth();
/*     */   }
/*     */   
/*     */   public float getPrintWidthCM() {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return 4.8F; 
/*     */     int i = baseDevice.getPrintWidth();
/*     */     return this.printerDevice.is300Dpi() ? ((i / 12) / 10.0F) : ((i / 8) / 10.0F);
/*     */   }
/*     */   
/*     */   public void setDeviceSelectPaperSize(int paramInt) {
/*     */     BaseDevice baseDevice;
/*     */     if (baseDevice = this.printerDevice instanceof ISupportSelectPaper)
/*     */       ((ISupportSelectPaper)this).setSelectPaperSize(paramInt); 
/*     */   }
/*     */   
/*     */   public int getDeviceSelectPaperSize() {
/*     */     BaseDevice baseDevice;
/*     */     if (baseDevice = this.printerDevice instanceof ISupportSelectPaper)
/*     */       return ((ISupportSelectPaper)this).getSelectPaperSize(); 
/*     */     return 50;
/*     */   }
/*     */   
/*     */   public void setDeviceLabelPaperType(PaperType paramPaperType) {
/*     */     BaseDevice baseDevice;
/*     */     if (baseDevice = this.printerDevice instanceof ISupportSetLabelPaperType)
/*     */       ((ISupportSetLabelPaperType)this).setLabelPaperType(paramPaperType); 
/*     */   }
/*     */   
/*     */   public void setA4PaperSize(int paramInt1, int paramInt2) {
/*     */     BaseDevice baseDevice;
/*     */     if (baseDevice = this.printerDevice instanceof BaseA4Device)
/*     */       ((BaseA4Device)this).setA4PaperSize(paramInt1, paramInt2); 
/*     */   }
/*     */   
/*     */   public void sendCommand(byte[] paramArrayOfbyte, ResultCallback<Integer> paramResultCallback) {
/*     */     if (this.printerDevice == null || paramArrayOfbyte.length <= 0)
/*     */       return; 
/*     */     if (isNormalPrinter())
/*     */       ((BaseNormalDevice)this.printerDevice).sendCommand(paramArrayOfbyte, paramResultCallback); 
/*     */   }
/*     */   
/*     */   public c getClientPort() {
/*     */     return (d.b()).a;
/*     */   }
/*     */   
/*     */   public void setCustomPropertyMap(HashMap<String, PrinterProperty> paramHashMap) {
/*     */     this.customPropertyMap = paramHashMap;
/*     */   }
/*     */   
/*     */   public void addCustomPropertyMap(HashMap<String, PrinterProperty> paramHashMap) {
/*     */     this.customPropertyMap.putAll(paramHashMap);
/*     */   }
/*     */   
/*     */   public static PrinterHelper getInstance() {
/*     */     if (instance == null)
/*     */       synchronized (PrinterHelper.class) {
/*     */         if (instance == null)
/*     */           instance = new PrinterHelper(); 
/*     */       }  
/*     */     return instance;
/*     */   }
/*     */   
/*     */   private PrinterHelper() {
/*     */     OnClientConnectionListener onClientConnectionListener;
/*     */     this.printerDevice = (BaseDevice)new BaseNormalDevice();
/*     */     this.customPropertyMap = new HashMap<>();
/*     */     this.connectListeners = new ConcurrentLinkedQueue<>();
/*     */     this.deviceStatusListeners = new ConcurrentLinkedQueue<>();
/*     */     this.eventListeners = new ConcurrentLinkedQueue<>();
/*     */     this.deviceForbiddenListeners = new ConcurrentLinkedQueue<>();
/*     */     this.deviceForbiddenChecker = new f();
/*     */     this.handler = new Handler();
/*     */     this.context = PrinterUtil.getAppContext();
/*     */     super(this);
/*     */     (d.b()).a.a = onClientConnectionListener;
/*     */     initReceiveDeviceStatusListener();
/*     */     initEventListener();
/*     */     initDeviceForbiddenCallback();
/*     */   }
/*     */   
/*     */   private void initReceiveDeviceStatusListener() {
/*     */     d d = d.b();
/*     */     this.a.b = (paramInt -> {
/*     */         if (this.deviceStatusListeners != null)
/*     */           PrinterUtil.runOnUi(()); 
/*     */       });
/*     */   }
/*     */   
/*     */   private void initEventListener() {
/*     */     super(this);
/*     */     OnEventListener onEventListener;
/*     */     (d.b()).a.c = onEventListener;
/*     */   }
/*     */   
/*     */   private void initDeviceForbiddenCallback() {
/*     */     f f1;
/*     */     if ((f1 = this.deviceForbiddenChecker) != null)
/*     */       f1.b = (() -> {
/*     */           Iterator<DeviceForbiddenListener> iterator = this.deviceForbiddenListeners.iterator();
/*     */           while (hasNext())
/*     */             ((DeviceForbiddenListener)next()).onDeviceForbidden(); 
/*     */         }); 
/*     */   }
/*     */   
/*     */   private BaseDevice initDevice(String paramString1, String paramString2) {
/*     */     BaseNormalDevice baseNormalDevice;
/*     */     String str = null;
/*     */     PrinterProperty printerProperty;
/*     */     if ((printerProperty = getCustomProperty(paramString1)) != null) {
/*     */       CustomA4Device customA4Device;
/*     */       CustomNormalDevice customNormalDevice;
/*     */       str = printerProperty.getPrinterType();
/*     */       if ("a4".equals(str)) {
/*     */         this();
/*     */       } else if ("sheet_label".equals(customA4Device)) {
/*     */         CustomSheetLabelDevice customSheetLabelDevice;
/*     */         this();
/*     */       } else {
/*     */         this();
/*     */       } 
/*     */       customNormalDevice.setProperty(printerProperty);
/*     */       customNormalDevice.setNamePrefix(getCustomNamePrefix(paramString1));
/*     */     } else {
/*     */       PrinterEnum printerEnum;
/*     */       if ((printerEnum = PrinterEnum.getByStartWithName(paramString1)) != null) {
/*     */         try {
/*     */           if (printerProperty = (PrinterProperty)getPrinterClass().newInstance() instanceof BaseSheetLabelDevice) {
/*     */             BaseSheetLabelDevice baseSheetLabelDevice = (BaseSheetLabelDevice)printerProperty;
/*     */           } else if (printerProperty instanceof BaseNormalDevice) {
/*     */             baseNormalDevice = (BaseNormalDevice)printerProperty;
/*     */           } 
/*     */         } catch (IllegalAccessException illegalAccessException) {
/*     */           null.printStackTrace();
/*     */         } catch (InstantiationException instantiationException) {
/*     */           null.printStackTrace();
/*     */         } 
/*     */         if (baseNormalDevice != null)
/*     */           baseNormalDevice.setNamePrefix(getName()); 
/*     */       } 
/*     */     } 
/*     */     if (baseNormalDevice != null) {
/*     */       baseNormalDevice.setDeviceName(paramString1);
/*     */       baseNormalDevice.setDeviceMac(paramString2);
/*     */     } 
/*     */     return (BaseDevice)baseNormalDevice;
/*     */   }
/*     */   
/*     */   private void getStatusBeforePrint(ResultSingleCallback<Integer> paramResultSingleCallback) {
/*     */     BaseDevice baseDevice;
/*     */     if ((baseDevice = this.printerDevice) == null)
/*     */       return; 
/*     */     super(this, paramResultSingleCallback);
/*     */     ResultCallback<PrinterStatusData> resultCallback;
/*     */     baseDevice.printerStatusLuck(resultCallback);
/*     */   }
/*     */   
/*     */   private PrinterProperty getCustomProperty(String paramString) {
/*     */     HashMap<String, PrinterProperty> hashMap;
/*     */     if ((hashMap = this.customPropertyMap) == null)
/*     */       return null; 
/*     */     for (Iterator<Map.Entry<K, V>> iterator = entrySet().iterator(); hasNext(); ) {
/*     */       Map.Entry entry = (Map.Entry)next();
/*     */       if (paramString != null && paramString.startsWith((String)entry.getKey()))
/*     */         return (PrinterProperty)entry.getValue(); 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   private String getCustomNamePrefix(String paramString) {
/*     */     HashMap<String, PrinterProperty> hashMap;
/*     */     if ((hashMap = this.customPropertyMap) == null)
/*     */       return null; 
/*     */     for (Iterator<K> iterator = keySet().iterator(); hasNext(); ) {
/*     */       String str = (String)next();
/*     */       if (paramString != null && paramString.startsWith(str))
/*     */         return str; 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public void addBitmapCache(Bitmap paramBitmap, boolean paramBoolean) {
/*     */     PrintBitmapDataCache.getInstance().addBitmapCache(paramBitmap, paramBoolean);
/*     */   }
/*     */   
/*     */   public boolean isA4Printer(String paramString) {
/*     */     boolean bool2 = false;
/*     */     PrinterEnum.BluetoothType bluetoothType = null;
/*     */     boolean bool3 = false;
/*     */     PrinterProperty printerProperty;
/*     */     if ((printerProperty = getCustomProperty(paramString)) != null) {
/*     */       String str1 = getPrinterType();
/*     */       bluetoothType = PrinterEnum.BluetoothType.fromName(getBtType());
/*     */       bool2 = isBleEnable();
/*     */       bool3 = "a4".equals(this);
/*     */     } else {
/*     */       PrinterEnum printerEnum;
/*     */       if ((printerEnum = PrinterEnum.getByStartWithName(paramString)) != null) {
/*     */         bluetoothType = this.btType;
/*     */         bool2 = this.isBleEnable;
/*     */         bool3 = BaseA4Device.class.isAssignableFrom(getPrinterClass());
/*     */       } 
/*     */     } 
/*     */     boolean bool1 = PrinterUtil.isNameEndWithBle(paramString);
/*     */     if (bool3 && (bluetoothType != PrinterEnum.BluetoothType.CLASSIC_BLE || !a.a || !bool2))
/*     */       i = bool1 ^ true; 
/*     */     String str = i + "";
/*     */     this("btfilter name:");
/*     */     int i;
/*     */     stringBuilder.append(paramString);
/*     */     stringBuilder.append(", flag:");
/*     */     StringBuilder stringBuilder;
/*     */     (stringBuilder = new StringBuilder()).append(this);
/*     */     PrinterUtil.log("PrinterManager", (new StringBuilder()).toString());
/*     */     return i;
/*     */   }
/*     */   
/*     */   public boolean isSheetLabelPrinter() {
/*     */     return this.printerDevice instanceof BaseSheetLabelDevice;
/*     */   }
/*     */   
/*     */   public boolean isNormalPrinter() {
/*     */     return this.printerDevice instanceof BaseNormalDevice;
/*     */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\PrinterHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */