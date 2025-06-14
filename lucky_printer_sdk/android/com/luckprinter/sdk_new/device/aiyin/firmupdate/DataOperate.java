/*  1 */ package com.luckprinter.sdk_new.device.aiyin.firmupdate;public class DataOperate { private int startAddr = 0; public int getStartAddr() { return this.startAddr; } public void setStartAddr(int paramInt) { this.startAddr = paramInt; } public int getEndAddr() { return this.endAddr; } public void setEndAddr(int paramInt) { this.endAddr = paramInt; } public int getDataLen() { return this.dataLen; } public void setDataLen(int paramInt) { this.dataLen = paramInt; } public int getCheck() { return this.check; } public void setCheck(int paramInt) { this.check = paramInt; } public byte getFlashSel() { return this.flashSel; } public void setFlashSel(byte paramByte) { this.flashSel = paramByte; } public String getFileName() { return this.fileName; } public void setFileName(String paramString) { this.fileName = paramString; } public String getBeiZhu() { return this.beiZhu; } public void setBeiZhu(String paramString) { this.beiZhu = paramString; } public String getVer() { return this.ver; } public void setVer(String paramString) { this.ver = paramString; } public int getPacketNum() { return this.packetNum; } public void setPacketNum(int paramInt) { this.packetNum = paramInt; } public byte[] GetPacket(int paramInt) { return this.packetData[paramInt]; } public int ReadFile(int paramInt, byte[] paramArrayOfbyte) { this.startAddr = paramInt;
/*    */     
/*  3 */     this.dataLen = i; this
/*  4 */       .endAddr = paramInt + i; int i; if ((i = paramArrayOfbyte.length) % 256 != 
/*  5 */       0)
/*  6 */     { this.packetNum = i / 256 + 1; }
/*    */     else
/*  8 */     { this.packetNum = i / 256; }  this
/*    */ 
/*    */ 
/*    */       
/* 12 */       .check = 0; paramInt = 0; while (paramInt < this
/*    */       
/* 14 */       .dataLen) {
/* 15 */       if ((i = paramArrayOfbyte[paramInt]) < 0) i += 256;  this
/*    */ 
/*    */         
/* 18 */         .check += i; paramInt++;
/*    */     } 
/*    */ 
/*    */     
/* 22 */     this.packetData = new byte[this.packetNum][]; paramInt = 0; while (paramInt < (
/* 23 */       i = this.packetNum) - 1) { for (this
/* 24 */         .packetData[paramInt] = new byte[256], i = 0; i < 256; ) { this
/*    */           
/* 26 */           .packetData[paramInt][i] = paramArrayOfbyte[paramInt * 256 + i]; i++; }  paramInt++; }
/*    */ 
/*    */ 
/*    */     
/* 30 */     if ((paramInt = this.dataLen) % 256 == 0) { for (this
/* 31 */         .packetData[i - 1] = new byte[256], paramInt = 0; paramInt < 256; ) { this
/*    */           
/* 33 */           .packetData[(i = this.packetNum) - 1][paramInt] = paramArrayOfbyte[(i - 1) * 256 + paramInt]; paramInt++; }
/*    */        }
/*    */     else
/* 36 */     { paramInt = i - 1; this.packetData[paramInt] = new byte[paramInt % 256]; paramInt = 0; while (paramInt < this
/* 37 */         .dataLen % 256) { this
/* 38 */           .packetData[(i = this.packetNum) - 1][paramInt] = paramArrayOfbyte[(i - 1) * 256 + paramInt]; paramInt++; }  }  return 0; }
/*    */ 
/*    */   
/*    */   private int endAddr = 0;
/*    */   private int dataLen = 0;
/*    */   private int check = 0;
/*    */   private byte flashSel = 0;
/*    */   private String fileName = "";
/*    */   private String beiZhu = "";
/*    */   private String ver = "";
/*    */   private int packetNum = 0;
/*    */   private byte[][] packetData = null; }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\firmupdate\DataOperate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */