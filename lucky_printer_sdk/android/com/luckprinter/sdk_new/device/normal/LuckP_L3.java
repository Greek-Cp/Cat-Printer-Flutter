/* 1 */ package com.luckprinter.sdk_new.device.normal;public class LuckP_L3 extends BaseNormalDevice implements ISupportSelectPaper { public static final int PAPER_SIZE_56 = 56; protected int selectPaperSize = 77; public static final int PAPER_SIZE_77 = 77; public int getPrintWidth() { return (this.selectPaperSize == 56) ? 384 : 576; } public int getPrintMaxWidth() { return 576; } public void setSelectPaperSize(int paramInt) { this.selectPaperSize = paramInt; } public int getSelectPaperSize() { return this.selectPaperSize; }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\LuckP_L3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */