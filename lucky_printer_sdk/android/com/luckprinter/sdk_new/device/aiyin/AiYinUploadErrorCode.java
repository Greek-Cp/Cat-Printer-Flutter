/* 1 */ package com.luckprinter.sdk_new.device.aiyin;public class AiYinUploadErrorCode { public static int getUploadErrorCode(byte[] paramArrayOfbyte) { byte b = -1; if (paramArrayOfbyte != null && paramArrayOfbyte.length == 2 && paramArrayOfbyte[0] == -1)
/* 2 */     { byte b1; if (((b1 = paramArrayOfbyte[1]) & 0x1) > 0) { b = 2; } else if ((b1 & 0x2) > 0) { b = 1; } else if ((b1 & 0x4) > 0) { b = 0; } else if ((b1 & 0x8) > 0) { b = 3; }  }  return b; }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\AiYinUploadErrorCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */