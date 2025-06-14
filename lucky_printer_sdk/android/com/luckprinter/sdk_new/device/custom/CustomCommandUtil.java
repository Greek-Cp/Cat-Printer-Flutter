/*  1 */ package com.luckprinter.sdk_new.device.custom;public class CustomCommandUtil { public static void sendCommand(Command paramCommand) { sendCommand(paramCommand, (List<String>)null, (ReadDataCallback)null); }
/*  2 */   public static void sendCommand(Command paramCommand, ReadDataCallback paramReadDataCallback) { sendCommand(paramCommand, (List<String>)null, paramReadDataCallback); }
/*  3 */   public static void sendCommand(Command paramCommand, byte[][] paramArrayOfbyte, ReadDataCallback paramReadDataCallback) { ArrayList<String> arrayList = null; if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0) {
/*  4 */       this(); int i; byte b;
/*  5 */       for (i = paramArrayOfbyte.length, b = 0; b < i; ) { arrayList
/*  6 */           .add(PrinterUtil.byteToHexString(paramArrayOfbyte[b])); b++; }
/*    */     
/*    */     } 
/*  9 */     sendCommand(paramCommand, arrayList, paramReadDataCallback); }
/* 10 */   public static void sendCommand(Command paramCommand, String[] paramArrayOfString, ReadDataCallback paramReadDataCallback) { List<String> list = null; if (paramArrayOfString != null) list = List.of(paramArrayOfString);
/*    */     
/* 12 */     sendCommand(paramCommand, list, paramReadDataCallback); } public static void sendCommand(Command paramCommand, List<String> paramList, ReadDataCallback paramReadDataCallback) {
/* 13 */     if (paramCommand.getData() == null) paramCommand
/* 14 */         .setData("");
/*    */     
/* 16 */     String str = paramCommand.getData().toLowerCase().replace(" ", "");
/* 17 */     if (paramList != null && !paramList.isEmpty()) {
/* 18 */       int j; byte b; for (j = paramList.size(), b = 0; b < j; ) {
/*    */         
/* 20 */         str = "{" + b + "}"; str = str.replace(str, paramList.get(b)); b++;
/*    */       } 
/*    */     } 
/* 23 */     byte[] arrayOfByte = PrinterUtil.hexStringToByteArray(str);
/* 24 */     int i = paramCommand.getCallbackTime() / 1000;
/* 25 */     List<String> list = paramCommand.getCallbackData(); IReceiveDataClaud iReceiveDataClaud = null; ReadDataCallback readDataCallback = null;
/*    */ 
/*    */     
/* 28 */     if (paramCommand.isCallback()) { if (list != null && 
/* 29 */         !list.isEmpty()) {
/* 30 */         iReceiveDataClaud = (paramArrayOfbyte -> {
/*    */             boolean bool = false;
/*    */             if (paramArrayOfbyte != null) {
/*    */               String str = PrinterUtil.byteToHexString(paramArrayOfbyte);
/*    */               Iterator<String> iterator = paramList.iterator();
/*    */               while (iterator.hasNext()) {
/*    */                 if (str.startsWith(iterator.next())) {
/*    */                   bool = true;
/*    */                   break;
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */             return bool;
/*    */           });
/*    */       }
/* 45 */       readDataCallback = (paramArrayOfbyte -> {
/*    */           if (paramReadDataCallback != null) {
/*    */             paramReadDataCallback.onReadData(paramArrayOfbyte);
/*    */           }
/*    */         }); }
/*    */ 
/*    */     
/* 52 */     (d.b())
/* 53 */       .a
/* 54 */       .b(iReceiveDataClaud, arrayOfByte, readDataCallback, i);
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\custom\CustomCommandUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */