/*  1 */ package com.luckprinter.sdk_new.device.custom;public enum CmdType { ENABLE("enable"),
/*  2 */   WAKE_UP("wake_up"),
/*  3 */   SET_PAPER_TYPE("set_paper_type"),
/*  4 */   PRINT_BITMAP("print_bitmap"),
/*  5 */   FEED_PAPER("feed_paper"),
/*  6 */   POSITION("position"),
/*  7 */   ADJUST_PAPER("adjust_paper"),
/*  8 */   DISABLE("disable"),
/*    */   
/* 10 */   SIZE_TSPL("size_tspl"),
/* 11 */   GAP_TSPL("gap_tspl"),
/* 12 */   CLEAR_TSPL("clear_tspl"),
/* 13 */   SET_SPEED_TSPL("set_speed_tspl"),
/* 14 */   SET_DENSITY_TSPL("set_density_tspl"),
/* 15 */   SET_PRINT_COUNT_TSPL("set_print_count_tspl"),
/*    */   
/* 17 */   NO_SET("no_set");
/*    */   
/*    */   CmdType(String paramString1) {
/*    */     this.value = paramString1;
/*    */   }
/*    */   
/*    */   public String getValue() {
/*    */     return this.value;
/*    */   }
/*    */   
/*    */   String value; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\custom\CmdType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */