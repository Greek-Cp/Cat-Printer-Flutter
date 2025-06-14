/* 1 */ package com.luckprinter.sdk_new.device.normal;public class UploadErrorCode { public static int getUploadErrorCode(byte[] paramArrayOfbyte) { byte b = -1; if (paramArrayOfbyte != null && paramArrayOfbyte.length == 2 && paramArrayOfbyte[0] == -1)
/* 2 */     { byte b1; if ((b1 = paramArrayOfbyte[1]) == 1) { b = 0; } else if (b1 == 2) { b = 1; } else if (b1 == 3) { b = 2; } else if (b1 == 4) { b = 3; }  }  return b; }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\UploadErrorCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */