/*   1 */ package com.luckprinter.sdk_new.device.aiyin.firmupdate;public class UpdatePrinterESC { private int mnProgress; private final Handler mHandler; public UpdatePrinterESC(byte[] paramArrayOfbyte, Handler paramHandler) { DataOperate dataOperate; this
/*   2 */       .mnProgress = 0;
/*     */ 
/*     */     
/*   5 */     this(); this.ud = this;
/*   6 */     this.startAddress = 16908288;
/*     */ 
/*     */     
/*   9 */     this.fileData = paramArrayOfbyte;
/*  10 */     this.mHandler = paramHandler; } private final byte[] fileData; private final DataOperate ud; private int startAddress; private int update(DataOperate paramDataOperate) { byte[] arrayOfByte2; (arrayOfByte2 = new byte[5])[0] = 16; (new byte[5])[1] = -1; (new byte[5])[2] = -32; (new byte[5])[3] = -86; (new byte[5])[4] = -86; safeWrite(arrayOfByte2); try { Thread.sleep(1000L); }
/*     */     catch (InterruptedException interruptedException) { null.printStackTrace(); }
/*  12 */      byte b = (byte)((new Random()).nextInt(255) % 256);
/*     */ 
/*     */     
/*     */     byte[] arrayOfByte3;
/*     */     
/*  17 */     (arrayOfByte3 = new byte[4])[0] = 16; (new byte[4])[1] = -1; (new byte[4])[2] = -1; (new byte[4])[3] = b;
/*  18 */     safeWriteAndRead(arrayOfByte3); for (b = 0; b < 10; ) {
/*     */       
/*  20 */       if ((arrayOfByte3 = sendComm((byte)2)) != null)
/*  21 */       { if (DataAnalyzeESC.analyzePacket(arrayOfByte3) != null)
/*  22 */         { if (DataAnalyzeESC.getType() == 2) { this
/*  23 */               .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_START_PRINTER.getCode()); break; }  b++; continue; }  this
/*     */ 
/*     */ 
/*     */           
/*  27 */           .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 6; }  this
/*     */ 
/*     */ 
/*     */         
/*  31 */         .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 6;
/*     */     } 
/*     */ 
/*     */     
/*     */     byte[] arrayOfByte1;
/*     */     
/*  37 */     if ((arrayOfByte1 = sendComm((byte)2)) != null)
/*  38 */     { if (DataAnalyzeESC.analyzePacket(arrayOfByte1) != null)
/*     */       
/*  40 */       { if (DataAnalyzeESC.getType() == 2)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  54 */           arrayOfByte1[
/*  55 */               0] = paramDataOperate.getFlashSel();
/*  56 */           paramDataOperate.getStartAddr();
/*  57 */           paramDataOperate.getEndAddr();
/*  58 */           DataAnalyzeESC.Uint32ToByte(arrayOfByte1, 1, paramDataOperate.getStartAddr());
/*  59 */           DataAnalyzeESC.Uint32ToByte(arrayOfByte1 = new byte[9], 5, paramDataOperate.getEndAddr());
/*     */ 
/*     */           
/*  62 */           if ((arrayOfByte3 = sendComm((byte)3, arrayOfByte1)) != null)
/*  63 */           { if (DataAnalyzeESC.analyzePacket(arrayOfByte3) != null)
/*     */             
/*  65 */             { if (DataAnalyzeESC.getType() == 3)
/*     */               { byte b1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 byte[] arrayOfByte;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*  78 */                 (arrayOfByte = new byte[b1 = 7])[
/*  79 */                     0] = paramDataOperate.getFlashSel(); byte b2 = 0; while (b2 < paramDataOperate
/*  80 */                   .getPacketNum())
/*  81 */                 { DataAnalyzeESC.Uint32ToByte(arrayOfByte, 1, b2);
/*     */                   byte[] arrayOfByte4;
/*  83 */                   arrayOfByte[5] = (byte)((arrayOfByte4 = paramDataOperate.GetPacket(b2)).length / 256);
/*  84 */                   arrayOfByte[6] = (byte)(arrayOfByte4.length % 256); byte[] arrayOfByte5;
/*     */                   byte b3;
/*  86 */                   for (arrayOfByte5 = new byte[b1 + arrayOfByte4.length], b3 = 0; b3 < b1; ) { arrayOfByte5[
/*     */                         
/*  88 */                         b3] = arrayOfByte[b3]; b3++; }  b3 = 0; while (b3 < arrayOfByte4.length) {
/*     */                     
/*  90 */                     int i = b1 + b3; arrayOfByte5[
/*  91 */                         i] = arrayOfByte4[b3]; b3++;
/*     */                   } 
/*     */                   
/*  94 */                   if ((arrayOfByte4 = sendComm((byte)4, arrayOfByte5)) != null)
/*  95 */                   { if (DataAnalyzeESC.analyzePacket(arrayOfByte4) != null)
/*  96 */                     { if (DataAnalyzeESC.getType() != 4) { this
/*  97 */                           .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 8; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 107 */                       this();
/* 108 */                       this.mnProgress = b2 * 100 / paramDataOperate.getPacketNum(); Message message;
/* 109 */                       (message = new Message()).what = UpgradeMarker.MSG_UPDATE_PROGRESS_BAR_PRINTER.getCode();
/* 110 */                       (new Message()).obj = Integer.valueOf(this.mnProgress); this
/* 111 */                         .mHandler.sendMessage(message); b2++; continue; }  return 8; }  return 8; }  arrayOfByte1[
/*     */ 
/*     */ 
/*     */                     
/* 115 */                     0] = paramDataOperate.getFlashSel();
/* 116 */                 DataAnalyzeESC.Uint32ToByte(arrayOfByte1, 1, paramDataOperate.getStartAddr());
/* 117 */                 DataAnalyzeESC.Uint32ToByte(arrayOfByte1, 5, paramDataOperate.getEndAddr());
/*     */                 
/* 119 */                 if ((arrayOfByte1 = sendComm((byte)6, arrayOfByte1)) != null)
/* 120 */                 { if ((arrayOfByte1 = DataAnalyzeESC.analyzePacket(arrayOfByte1)) != null)
/* 121 */                   { if (DataAnalyzeESC.getType() == 6 && arrayOfByte1[0] == 0) { long l2, l7, l6, l5; b1 = 0;
/*     */                       
/*     */                       byte b5;
/*     */                       
/* 125 */                       if ((b5 = arrayOfByte1[1]) < 0) { l7 = (b5 + 256); } else { l7 = l7; }
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 130 */                       long l4 = l7 * (long)Math.pow(2.0D, 24.0D); int m = (int)(b1 + l4);
/*     */                       
/*     */                       byte b4;
/* 133 */                       if ((b4 = arrayOfByte1[2]) < 0) { l6 = (b4 + 256); } else { l6 = l6; }
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 138 */                       long l3 = l6 * (long)Math.pow(2.0D, 16.0D); int k = (int)(m + l3);
/*     */                       
/*     */                       byte b3;
/* 141 */                       if ((b3 = arrayOfByte1[3]) < 0) { l5 = (b3 + 256); } else { l5 = l5; }
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 146 */                       long l1 = l5 * (long)Math.pow(2.0D, 8.0D); int j = (int)(k + l1);
/*     */ 
/*     */                       
/* 149 */                       if ((k = arrayOfByte1[4]) < 0) { l2 = (k + 256); } else { l2 = l2; }  int i = (int)(j + l2); if (paramDataOperate
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/* 156 */                         .getCheck() == i)
/*     */                       { byte[] arrayOfByte4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/* 172 */                         if ((arrayOfByte4 = sendComm((byte)2)) != null)
/* 173 */                         { if (DataAnalyzeESC.analyzePacket(arrayOfByte4) != null)
/* 174 */                           { if (DataAnalyzeESC.getType() == 2)
/*     */                             
/*     */                             { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                               
/* 189 */                               sendComm((byte)7); this
/* 190 */                                 .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_FINISHED_PRINTER.getCode());
/* 191 */                               this.mnProgress = (int)(this.mnProgress + 0.01D); return 0; }  this
/* 192 */                               .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 10; }  return 10; }  return 10; }  return 9; }  this
/* 193 */                       .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 9; }  return 9; }  return 9; }  this
/* 194 */                 .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 7; }  return 7; }  return 7; }  this
/* 195 */           .mHandler.sendEmptyMessage(UpgradeMarker.MSG_OTA_DATA_COMMAND_SEND_FAILED_PRINTER.getCode()); return 6; }  return 6; }  return 6; }
/*     */ 
/*     */   
/*     */   private byte[] sendComm(byte paramByte) {
/*     */     return sendComm(paramByte, new byte[0]);
/*     */   }
/*     */   
/*     */   private byte[] safeWriteAndRead(byte[] paramArrayOfbyte) {
/*     */     boolean bool = true;
/*     */     return (d.b()).a.a(null, paramArrayOfbyte, bool, 3);
/*     */   }
/*     */   
/*     */   private void safeWrite(byte[] paramArrayOfbyte) {
/*     */     boolean bool = false;
/*     */     (d.b()).a.a(null, paramArrayOfbyte, bool, 3);
/*     */   }
/*     */   
/*     */   public static String Bytes2HexString(byte[] paramArrayOfbyte) {
/*     */     String str = "";
/*     */     byte b = 0;
/*     */     while (b < paramArrayOfbyte.length) {
/*     */       String str1;
/*     */       if ((str1 = Integer.toHexString(paramArrayOfbyte[b] & 0xFF).toUpperCase()).length() == 1)
/*     */         str1 = "0".concat(str1); 
/*     */       str = str + "0x" + str1 + ",";
/*     */       b++;
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   public void setStartAddress(int paramInt) {
/*     */     this.startAddress = paramInt;
/*     */   }
/*     */   
/*     */   public void startUpdate() {
/*     */     this.ud.setBeiZhu("APP ");
/*     */     this.ud.setVer("未知");
/*     */     this.ud.setFlashSel((byte)0);
/*     */     int i = this.startAddress;
/*     */     if (this.fileData != null && this.ud.ReadFile(i, this.fileData) == 0) {
/*     */       Runnable runnable;
/*     */       super(this);
/*     */       (new Thread(runnable)).start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] readFiledata(String paramString) {
/*     */     String str;
/*     */     UpdatePrinterESC updatePrinterESC;
/*     */     this = null;
/*     */     int i;
/*     */     byte[] arrayOfByte1 = new byte[i = (int)(new File(paramString)).length()];
/*     */     byte[] arrayOfByte2 = new byte[(int)(new File(paramString)).length()];
/*     */     int j = 0;
/*     */     try {
/*     */       FileInputStream fileInputStream;
/*     */       this(paramString);
/*     */     } catch (Exception exception) {
/*     */       null.printStackTrace();
/*     */       return (byte[])paramString;
/*     */     } finally {
/*     */       paramString = null;
/*     */       updatePrinterESC = this;
/*     */     } 
/*     */     if (updatePrinterESC != null)
/*     */       try {
/*     */         updatePrinterESC.close();
/*     */       } catch (IOException iOException) {
/*     */         null.printStackTrace();
/*     */         return null;
/*     */       }  
/*     */     throw this;
/*     */   }
/*     */   
/*     */   private byte[] sendComm(byte paramByte, byte[] paramArrayOfbyte) {
/*     */     return safeWriteAndRead(DataAnalyzeESC.Package(paramByte, paramArrayOfbyte, paramArrayOfbyte.length));
/*     */   }
/*     */   
/*     */   private byte Protocol_Encry(byte paramByte) {
/*     */     i &= j;
/*     */     int i;
/*     */     int j;
/*     */     return (byte)(((i = paramByte >> 4) | (j = paramByte & 0xF)) << 4 | i);
/*     */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\aiyin\firmupdate\UpdatePrinterESC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */