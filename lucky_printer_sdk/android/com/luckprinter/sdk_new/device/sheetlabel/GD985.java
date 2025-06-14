/* 1 */ package com.luckprinter.sdk_new.device.sheetlabel;public class GD985 extends BaseSheetLabelDevice { public void drawPic(Bitmap paramBitmap) { drawPicZLib(paramBitmap); } public int getPrintWidth() { return 864; } public void printTag(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Bitmap paramBitmap, int paramInt5) { for (byte b = 0; b < paramInt5; ) { super.printTag(paramInt1, paramInt2, paramInt3, paramInt4, paramBitmap, 1); b++; }
/*   */      }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\sheetlabel\GD985.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */