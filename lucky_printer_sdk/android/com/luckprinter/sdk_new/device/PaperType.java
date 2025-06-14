/* 1 */ package com.luckprinter.sdk_new.device;public enum PaperType { TYPE_CONTINUOUS(1, (byte)16),
/* 2 */   TYPE_NORMAL_TAG(2, (byte)32),
/* 3 */   TYPE_BLACK(3, (byte)48),
/* 4 */   TYPE_TATTOO(4, (byte)64),
/* 5 */   TYPE_BLACK_TAG(5, (byte)80);
/*   */   
/*   */   PaperType(int paramInt1, byte paramByte) {
/*   */     this.type = paramInt1;
/*   */     this.data = paramByte;
/*   */   }
/*   */   
/*   */   public static PaperType getFromType(int paramInt) {
/*   */     PaperType[] arrayOfPaperType;
/*   */     int i;
/*   */     byte b;
/*   */     for (i = (arrayOfPaperType = values()).length, b = 0; b < i; ) {
/*   */       PaperType paperType;
/*   */       if ((paperType = arrayOfPaperType[b]).getType() == paramInt)
/*   */         return paperType; 
/*   */       b++;
/*   */     } 
/*   */     return null;
/*   */   }
/*   */   
/*   */   public int getType() {
/*   */     return this.type;
/*   */   }
/*   */   
/*   */   public byte getData() {
/*   */     return this.data;
/*   */   }
/*   */   
/*   */   int type;
/*   */   byte data; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\PaperType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */