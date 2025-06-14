/*  1 */ package com.luckprinter.sdk_new.device.aiyin.firmupdate;public enum UpgradeMarker { MSG_UPDATE_PROGRESS_BAR_BT(1),
/*    */ 
/*    */ 
/*    */   
/*  5 */   MSG_OTA_START_COMMAND_DATA_INVALID_BT(2),
/*    */ 
/*    */ 
/*    */   
/*  9 */   MSG_OTA_START_COMMAND_SEND_FAILED_BT(3),
/*    */ 
/*    */ 
/*    */   
/* 13 */   MSG_OTA_DATA_COMMAND_SEND_FAILED_BT(4),
/*    */ 
/*    */ 
/*    */   
/* 17 */   MSG_OTA_DATA_START_BT(5),
/*    */ 
/*    */ 
/*    */   
/* 21 */   MSG_OTA_FINISHED_BT(6),
/*    */ 
/*    */ 
/*    */   
/* 25 */   MSG_UPDATE_PROGRESS_BAR_PRINTER(241),
/*    */ 
/*    */ 
/*    */   
/* 29 */   MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER(242),
/*    */ 
/*    */ 
/*    */   
/* 33 */   MSG_OTA_DATA_START_PRINTER(243),
/*    */ 
/*    */ 
/*    */   
/* 37 */   MSG_OTA_FINISHED_PRINTER(244);
/*    */   
/*    */   UpgradeMarker(int paramInt1) {
/*    */     this.code = paramInt1;
/*    */   }
/*    */   
/*    */   public static UpgradeMarker getByCode(Integer paramInteger) {
/*    */     UpgradeMarker[] arrayOfUpgradeMarker;
/*    */     int i;
/*    */     byte b;
/*    */     for (i = (arrayOfUpgradeMarker = values()).length, b = 0; b < i; ) {
/*    */       UpgradeMarker upgradeMarker;
/*    */       if ((upgradeMarker = arrayOfUpgradeMarker[b]).getCode() == paramInteger.intValue())
/*    */         return upgradeMarker; 
/*    */       b++;
/*    */     } 
/*    */     return null;
/*    */   }
/*    */   
/*    */   public int getCode() {
/*    */     return this.code;
/*    */   }
/*    */   
/*    */   private final int code; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\firmupdate\UpgradeMarker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */