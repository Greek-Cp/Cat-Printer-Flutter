/*  1 */ package com.luckprinter.sdk_new;public class PrinterUtil { private static final String TAG = "PrinterUtil"; public static final ExecutorService cacheExecutor = Executors.newCachedThreadPool(); private static Context appContext; private static Handler mainHandler = null; public static void initContext(Context paramContext) { appContext = paramContext.getApplicationContext();
/*  2 */     mainHandler = new Handler(); } public static void mkdirs(String paramString) { boolean bool = (new File(paramString)).mkdirs(); Log.d(TAG, "mkdirs: " + bool); }
/*    */   public static void showToast(String paramString) { Toast.makeText(appContext, paramString, 0).show(); }
/*    */   public static void runOnUi(Runnable paramRunnable) { mainHandler.post(paramRunnable); }
/*    */   public static void runOnUiDelay(Runnable paramRunnable, long paramLong) { mainHandler.postDelayed(paramRunnable, paramLong); }
/*    */   public static Context getAppContext() { return appContext; }
/*    */   public static String textToHexString(String paramString) { return byteToHexString(paramString.getBytes(StandardCharsets.UTF_8)); }
/*    */   public static int dp2px(float paramFloat) { DisplayMetrics displayMetrics = appContext.getResources().getDisplayMetrics(); return (int)TypedValue.applyDimension(1, paramFloat, displayMetrics); }
/*  9 */   public static byte[] intToByteArray4(int paramInt) { paramInt = (byte)(paramInt & 0xFF); byte b1 = (byte)(paramInt >> 8 & 0xFF), b2 = (byte)(paramInt >> 16 & 0xFF), b3 = (byte)(paramInt >> 24 & 0xFF); return new byte[] { b3, b2, b1, paramInt }; } public static byte[] intToByteArray4LowToHeight(int paramInt) { paramInt = (byte)(paramInt & 0xFF); byte b1 = (byte)(paramInt >> 8 & 0xFF), b2 = (byte)(paramInt >> 16 & 0xFF), b3 = (byte)(paramInt >> 24 & 0xFF); return new byte[] { paramInt, b1, b2, b3 }; } public static int byteArray4ToInt(byte[] paramArrayOfbyte) { return paramArrayOfbyte[0] & 0xFF | (paramArrayOfbyte[1] & 0xFF) << 8 | (paramArrayOfbyte[2] & 0xFF) << 16 | (paramArrayOfbyte[3] & 0xFF) << 24; } public static void xorEndByte(byte[] paramArrayOfbyte) { if (paramArrayOfbyte == null || paramArrayOfbyte.length <= 1) return;  System.arraycopy(paramArrayOfbyte, 0, arrayOfByte, 0, i); int i; byte[] arrayOfByte; paramArrayOfbyte[paramArrayOfbyte.length - 1] = xor(arrayOfByte = new byte[i = paramArrayOfbyte.length - 1]); } public static String byteToHexString(byte[] paramArrayOfbyte) { if (paramArrayOfbyte == null || paramArrayOfbyte.length == 0) return "";  this(); StringBuffer stringBuffer; int i; byte b; for (i = paramArrayOfbyte.length, b = 0; b < i; ) { Byte byte_ = Byte.valueOf(paramArrayOfbyte[b]); Object[] arrayOfObject; (arrayOfObject = new Object[1])[0] = byte_; stringBuffer.append(String.format("%02x", arrayOfObject)).append(""); b++; }  return stringBuffer.toString(); } public static byte xor(byte[] paramArrayOfbyte) { if (paramArrayOfbyte == null || paramArrayOfbyte.length < 1) return 0;  byte b = 0; int i; byte b1; for (i = paramArrayOfbyte.length, b1 = 0; b1 < i; ) { b = (byte)(b ^ paramArrayOfbyte[b1]); b1++; }  return b; } public static byte[] getSubByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) { System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, paramInt2); byte[] arrayOfByte; return arrayOfByte = new byte[paramInt2]; } public static byte[] intToByteArray2(int paramInt) { paramInt = (byte)(paramInt & 0xFF); byte b = (byte)(paramInt >> 8 & 0xFF); return new byte[] { b, paramInt }; } public static byte[] intToByteArray2LittleEndian(int paramInt) { paramInt = (byte)(paramInt & 0xFF); byte b = (byte)(paramInt >> 8 & 0xFF); return new byte[] { paramInt, b }; } public static int byteArray2ToIntLittleEndian(byte[] paramArrayOfbyte) { if (paramArrayOfbyte != null && paramArrayOfbyte.length == 2) return (paramArrayOfbyte[1] & 0xFF) << 8 | paramArrayOfbyte[0] & 0xFF;  throw new IllegalArgumentException("Byte array must be non-null and have exactly 2 elements"); } public static int byteArray2ToIntBigEndian(byte[] paramArrayOfbyte) { if (paramArrayOfbyte != null && paramArrayOfbyte.length == 2) return paramArrayOfbyte[1] & 0xFF | (paramArrayOfbyte[0] & 0xFF) << 8;  throw new IllegalArgumentException("Byte array must be non-null and have exactly 2 elements"); } public static byte[] hexStringToByteArray(String paramString) { if (TextUtils.isEmpty(paramString)) return null;  int i; if ((i = paramString.length()) % 2 != 0) return null;  byte[] arrayOfByte = new byte[i / 2]; char[] arrayOfChar; byte b; for (arrayOfChar = paramString.toCharArray(), b = 0; b < i; )
/*    */     
/*    */     { 
/* 12 */       int k = Character.digit(arrayOfChar[b + 1], 16); int j; if ((j = Character.digit(arrayOfChar[b], 16)) == -1 || k == -1)
/*    */       {
/*    */ 
/*    */         
/* 16 */         return null; }  j = b / 2; arrayOfByte[j] = (byte)(j << 4 | k); b += 2; }  return arrayOfByte; } public static String getHexString(byte[] paramArrayOfbyte) { return getHexString(paramArrayOfbyte, 40); } public static boolean compareBytes(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) { if (paramArrayOfbyte1 == null || paramArrayOfbyte2 == null) return false;  if (paramArrayOfbyte1.length != paramArrayOfbyte2.length) return false;  boolean bool = true; byte b = 0; while (b < paramArrayOfbyte1.length) { if (paramArrayOfbyte1[b] != paramArrayOfbyte2[b]) { bool = false; break; }  b++; }  return bool; } public static boolean isNameEndWithBle(String paramString) { if (TextUtils.isEmpty(paramString)) return false;  if ((paramString = paramString.trim()).endsWith("_BLE") || paramString.endsWith("_LE") || paramString.endsWith("-BLE") || paramString.endsWith("-LE")) return true;  } public static void log(String paramString1, String paramString2) { Log.d(paramString1, paramString2); } public static Handler getMainHandler() { return mainHandler; } public static byte[] combineBytes(byte[]... paramVarArgs) { int i, j, k; for (i = 0, j = paramVarArgs.length, k = 0; k < j; ) { i += (paramVarArgs[k]).length; k++; }  byte[] arrayOfByte = new byte[i]; j = 0; k = 0; while (j < paramVarArgs.length) { int m = (paramVarArgs[j]).length; System.arraycopy(paramVarArgs[j], 0, arrayOfByte, k, m); k += (paramVarArgs[j]).length; j++; }  return arrayOfByte; } public static byte[] readByteArrayFromFile(File paramFile) { byte[] arrayOfByte1 = new byte[1048576]; byte[] arrayOfByte2 = new byte[(int)paramFile.length()]; try { FileInputStream fileInputStream; this(paramFile); int i = 0; try { int j; while ((j = fileInputStream.read(arrayOfByte1)) != -1) { System.arraycopy(arrayOfByte1, 0, arrayOfByte2, i, j); i += j; }  } finally { Exception exception; }  fileInputStream.close();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */        }
/*    */     
/* 27 */     catch (IOException iOException) { null.printStackTrace(); arrayOfByte2 = null; }  return arrayOfByte2; }
/*    */ 
/*    */   
/*    */   public static String readTextFromAssets(Context paramContext, String paramString) {
/*    */     this();
/*    */     StringBuilder stringBuilder;
/*    */     InputStream inputStream;
/*    */     BufferedReader bufferedReader;
/*    */     try {
/*    */       inputStream = paramContext.getAssets().open(paramString);
/*    */       this(new InputStreamReader(inputStream));
/*    */       String str;
/*    */       while ((str = bufferedReader.readLine()) != null)
/*    */         stringBuilder.append(str).append("\n"); 
/*    */     } catch (IOException iOException) {}
/*    */     bufferedReader.close();
/*    */     inputStream.close();
/*    */     return iOException.toString();
/*    */   }
/*    */   
/*    */   public static byte[] hexStringToByteArray1(String paramString) {
/*    */     if (paramString != null && !paramString.isEmpty()) {
/*    */       String[] arrayOfString;
/*    */       byte[] arrayOfByte = new byte[(arrayOfString = paramString.trim().split("\\s+")).length];
/*    */       byte b = 0;
/*    */       try {
/*    */         while (b < arrayOfString.length) {
/*    */           arrayOfByte[b] = (byte)Integer.parseInt(arrayOfString[b], 16);
/*    */           b++;
/*    */         } 
/*    */       } catch (NumberFormatException numberFormatException) {}
/*    */       return arrayOfByte;
/*    */     } 
/*    */     throw new IllegalArgumentException("输入的十六进制字符串不能为空");
/*    */   }
/*    */   
/*    */   public static String getHexString(byte[] paramArrayOfbyte, int paramInt) {
/*    */     this();
/*    */     StringBuilder stringBuilder;
/*    */     boolean bool = true;
/*    */     if (paramArrayOfbyte.length <= paramInt) {
/*    */       paramInt = paramArrayOfbyte.length;
/*    */       bool = false;
/*    */     } 
/*    */     for (byte b = 0; b < paramInt; ) {
/*    */       Byte byte_ = Byte.valueOf(paramArrayOfbyte[b]);
/*    */       Object[] arrayOfObject;
/*    */       (arrayOfObject = new Object[1])[0] = byte_;
/*    */       stringBuilder.append(String.format("%02x", arrayOfObject)).append(" ");
/*    */       b++;
/*    */     } 
/*    */     if (bool)
/*    */       stringBuilder.append(" ..."); 
/*    */     return stringBuilder.toString();
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\PrinterUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */