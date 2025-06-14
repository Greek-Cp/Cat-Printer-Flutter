/* 1 */ package com.luckprinter.sdk_new.bean;public class RecordTimeBean { private long startTime; public long getStartTime() { return this.startTime; } private long endTime; public long getEndTime() { return this.endTime; } public void recordStart() { this.startTime = System.nanoTime(); } public void recordEnd() { this.endTime = System.nanoTime(); } public long getDurationMs() { return (this.endTime - this.startTime) / 1000000L; }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\bean\RecordTimeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */