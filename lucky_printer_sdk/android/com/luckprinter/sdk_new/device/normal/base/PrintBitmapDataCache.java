/*  1 */ package com.luckprinter.sdk_new.device.normal.base;public class PrintBitmapDataCache { public static final int MAX_CAPACITY = 5; public static PrintBitmapDataCache getInstance() { if (instance == null) {
/*  2 */       synchronized (PrintBitmapDataCache.class) {
/*  3 */         if (instance == null) {
/*  4 */           instance = new PrintBitmapDataCache();
/*    */         }
/*    */       } 
/*    */     }
/*  8 */     return instance; } public static final float DEFAULT_LOAD_FACTOR = 0.75F; private static volatile PrintBitmapDataCache instance; private void trimSize() { if (this.bitmapCache.size() < 5) return;  int i = this.bitmapCache.size() - 3; Iterator iterator; byte b; for (iterator = this.bitmapCache.keySet().iterator(), b = 0; b < i; ) { if (iterator.hasNext()) { iterator
/*  9 */           .next();
/* 10 */         iterator.remove(); }  b++; }
/*    */      }
/*    */ 
/*    */   
/*    */   public void addBitmapCache(Bitmap paramBitmap, boolean paramBoolean) {
/*    */     trimSize();
/*    */     g g;
/*    */     BaseDevice baseDevice;
/*    */     if (baseDevice = PrinterHelper.getInstance().getPrinterDevice() instanceof BaseNormalDevice && (g = ((BaseNormalDevice)baseDevice).getBitmapConvert(paramBitmap, paramBoolean)) != null) {
/*    */       byte[] arrayOfByte = g.a();
/*    */       this.bitmapCache.put(paramBitmap, this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public byte[] getBitmapCache(Bitmap paramBitmap) {
/*    */     return this.bitmapCache.get(paramBitmap);
/*    */   }
/*    */   
/*    */   public void removeBitmapCache(Bitmap paramBitmap) {
/*    */     this.bitmapCache.remove(paramBitmap);
/*    */   }
/*    */   
/*    */   private Map<Bitmap, byte[]> bitmapCache = (Map)Collections.synchronizedMap(new LinkedHashMap<>()); }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\PrintBitmapDataCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */