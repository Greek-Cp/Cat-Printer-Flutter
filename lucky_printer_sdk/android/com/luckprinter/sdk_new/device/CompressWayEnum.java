/* 1 */ package com.luckprinter.sdk_new.device;public enum CompressWayEnum { NORMAL("normal"),
/* 2 */   ANGYIN("angyin"),
/* 3 */   ANGYIN_FAST("angyin_fast"),
/* 4 */   ANGYIN_TSPL("angyin_tspl"),
/* 5 */   ZLIB("zlib");
/*   */   
/*   */   CompressWayEnum(String paramString1) {
/*   */     this.key = paramString1;
/*   */   }
/*   */   
/*   */   public static CompressWayEnum getCompressWayByKey(String paramString) {
/*   */     CompressWayEnum[] arrayOfCompressWayEnum;
/*   */     int i;
/*   */     byte b;
/*   */     for (i = (arrayOfCompressWayEnum = values()).length, b = 0; b < i; ) {
/*   */       CompressWayEnum compressWayEnum;
/*   */       if ((compressWayEnum = arrayOfCompressWayEnum[b]).getKey().equals(paramString))
/*   */         return compressWayEnum; 
/*   */       b++;
/*   */     } 
/*   */     return null;
/*   */   }
/*   */   
/*   */   public String getKey() {
/*   */     return this.key;
/*   */   }
/*   */   
/*   */   String key; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\CompressWayEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */