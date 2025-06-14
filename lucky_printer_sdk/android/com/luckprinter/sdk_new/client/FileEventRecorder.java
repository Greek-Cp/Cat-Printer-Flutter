/*  1 */ package com.luckprinter.sdk_new.client;public class FileEventRecorder { private FileEventRecorder() { File file1, file2; this
/*  2 */       .saveFolder = null;
/*    */     
/*  4 */     this.zipFolder = null;
/*    */     
/*  6 */     this.exec = null;
/*    */ 
/*    */     
/*  9 */     String str1, str2 = PrinterUtil.getAppContext().getFilesDir() + (str1 = File.separator) + "printer_error";
/*    */     
/* 11 */     this(str2); this.saveFolder = new File();
/* 12 */     if (!file2.exists()) this
/* 13 */         .saveFolder.mkdirs();
/*    */     
/* 15 */     str1 = PrinterUtil.getAppContext().getFilesDir() + str1 + "printer_error_zip";
/*    */     
/* 17 */     this(str1); this.zipFolder = new File();
/* 18 */     if (!file1.exists()) this
/* 19 */         .zipFolder.mkdirs();
/*    */ 
/*    */ 
/*    */     
/* 23 */     deleteOldCache(this.saveFolder);
/* 24 */     deleteOldCache(this.zipFolder);
/*    */ 
/*    */     
/* 27 */     this.exec = Executors.newSingleThreadExecutor(); }
/*    */ 
/*    */   
/*    */   private static final String FOLDER = "printer_error";
/*    */   private static final String ZIP_FOLDER = "printer_error_zip";
/*    */   private static volatile FileEventRecorder instance;
/*    */   private File saveFolder;
/*    */   private File zipFolder;
/*    */   private ExecutorService exec;
/*    */   
/*    */   public static FileEventRecorder getInstance() {
/*    */     if (instance == null)
/*    */       synchronized (FileEventRecorder.class) {
/*    */         if (instance == null)
/*    */           instance = new FileEventRecorder(); 
/*    */       }  
/*    */     return instance;
/*    */   }
/*    */   
/*    */   private void deleteOldFiles(File paramFile, long paramLong) {
/*    */     if (paramFile.isDirectory()) {
/*    */       File[] arrayOfFile;
/*    */       int i;
/*    */       byte b;
/*    */       for (i = (arrayOfFile = paramFile.listFiles()).length, b = 0; b < i; ) {
/*    */         File file;
/*    */         if ((file = arrayOfFile[b]).isDirectory()) {
/*    */           deleteOldFiles(file, paramLong);
/*    */           if ((file.listFiles()).length == 0)
/*    */             file.delete(); 
/*    */         } else if (file.lastModified() < paramLong) {
/*    */           file.delete();
/*    */         } 
/*    */         b++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public File getSaveDir() {
/*    */     return this.saveFolder;
/*    */   }
/*    */   
/*    */   public void onEvent(String paramString1, String paramString2) {
/*    */     this.exec.execute(() -> {
/*    */           // Byte code:
/*    */           //   0: new java/text/SimpleDateFormat
/*    */           //   3: dup
/*    */           //   4: astore_2
/*    */           //   5: ldc 'MM:dd HH:mm:ss'
/*    */           //   7: invokespecial <init> : (Ljava/lang/String;)V
/*    */           //   10: new java/text/SimpleDateFormat
/*    */           //   13: dup
/*    */           //   14: aload_2
/*    */           //   15: swap
/*    */           //   16: ldc 'yyyy_MM_dd'
/*    */           //   18: invokespecial <init> : (Ljava/lang/String;)V
/*    */           //   21: new java/util/Date
/*    */           //   24: dup
/*    */           //   25: invokespecial <init> : ()V
/*    */           //   28: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
/*    */           //   31: astore_2
/*    */           //   32: new java/util/Date
/*    */           //   35: dup
/*    */           //   36: invokespecial <init> : ()V
/*    */           //   39: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
/*    */           //   42: new java/lang/StringBuilder
/*    */           //   45: dup
/*    */           //   46: aload_0
/*    */           //   47: swap
/*    */           //   48: invokespecial <init> : ()V
/*    */           //   51: getfield saveFolder : Ljava/io/File;
/*    */           //   54: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*    */           //   57: getstatic java/io/File.separator : Ljava/lang/String;
/*    */           //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */           //   63: swap
/*    */           //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */           //   67: ldc '.txt'
/*    */           //   69: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */           //   72: invokevirtual toString : ()Ljava/lang/String;
/*    */           //   75: astore_0
/*    */           //   76: aconst_null
/*    */           //   77: astore_3
/*    */           //   78: new java/io/BufferedWriter
/*    */           //   81: dup
/*    */           //   82: dup
/*    */           //   83: dup2
/*    */           //   84: astore #4
/*    */           //   86: new java/io/FileWriter
/*    */           //   89: dup
/*    */           //   90: aload_0
/*    */           //   91: iconst_1
/*    */           //   92: invokespecial <init> : (Ljava/lang/String;Z)V
/*    */           //   95: invokespecial <init> : (Ljava/io/Writer;)V
/*    */           //   98: new java/lang/StringBuilder
/*    */           //   101: dup
/*    */           //   102: aload_2
/*    */           //   103: swap
/*    */           //   104: invokespecial <init> : ()V
/*    */           //   107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */           //   110: ldc ' : '
/*    */           //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */           //   115: aload_1
/*    */           //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */           //   119: invokevirtual toString : ()Ljava/lang/String;
/*    */           //   122: invokevirtual append : (Ljava/lang/CharSequence;)Ljava/io/Writer;
/*    */           //   125: pop
/*    */           //   126: invokevirtual newLine : ()V
/*    */           //   129: invokevirtual close : ()V
/*    */           //   132: goto -> 169
/*    */           //   135: invokevirtual printStackTrace : ()V
/*    */           //   138: goto -> 169
/*    */           //   141: astore_0
/*    */           //   142: aload #4
/*    */           //   144: astore_3
/*    */           //   145: goto -> 170
/*    */           //   148: aload #4
/*    */           //   150: astore_3
/*    */           //   151: goto -> 158
/*    */           //   154: astore_0
/*    */           //   155: goto -> 170
/*    */           //   158: invokevirtual printStackTrace : ()V
/*    */           //   161: aload_3
/*    */           //   162: ifnull -> 169
/*    */           //   165: aload_3
/*    */           //   166: invokevirtual close : ()V
/*    */           //   169: return
/*    */           //   170: aload_3
/*    */           //   171: ifnull -> 184
/*    */           //   174: aload_3
/*    */           //   175: invokevirtual close : ()V
/*    */           //   178: goto -> 184
/*    */           //   181: invokevirtual printStackTrace : ()V
/*    */           //   184: aload_0
/*    */           //   185: athrow
/*    */           // Line number table:
/*    */           //   Java source line number -> byte code offset
/*    */           //   #1	-> 0
/*    */           //   #2	-> 10
/*    */           //   #4	-> 21
/*    */           //   #5	-> 32
/*    */           //   #7	-> 42
/*    */           //   #10	-> 78
/*    */           //   #11	-> 98
/*    */           //   #12	-> 126
/*    */           //   #18	-> 129
/*    */           //   #20	-> 135
/*    */           //   #21	-> 158
/*    */           //   #25	-> 166
/*    */           //   #26	-> 175
/*    */           //   #28	-> 181
/*    */           //   #31	-> 185
/*    */           // Exception table:
/*    */           //   from	to	target	type
/*    */           //   78	81	158	java/io/IOException
/*    */           //   78	81	154	finally
/*    */           //   86	98	158	java/io/IOException
/*    */           //   86	98	154	finally
/*    */           //   98	101	148	java/io/IOException
/*    */           //   98	101	141	finally
/*    */           //   102	125	148	java/io/IOException
/*    */           //   102	125	141	finally
/*    */           //   126	129	148	java/io/IOException
/*    */           //   126	129	141	finally
/*    */           //   129	135	135	java/io/IOException
/*    */           //   158	161	154	finally
/*    */           //   165	169	135	java/io/IOException
/*    */           //   174	181	181	java/io/IOException
/*    */         });
/*    */   }
/*    */   
/*    */   public void deleteOldCache(File paramFile) {
/*    */     Calendar.getInstance().add(5, -1);
/*    */     long l = Calendar.getInstance().getTimeInMillis();
/*    */     deleteOldFiles(paramFile, l);
/*    */   }
/*    */   
/*    */   public String getPrinterErrorZipFile() {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: new java/text/SimpleDateFormat
/*    */     //   4: dup
/*    */     //   5: aload_0
/*    */     //   6: swap
/*    */     //   7: ldc 'MM_dd_HH_mm_ss'
/*    */     //   9: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   12: getfield zipFolder : Ljava/io/File;
/*    */     //   15: invokevirtual getAbsolutePath : ()Ljava/lang/String;
/*    */     //   18: astore_0
/*    */     //   19: getstatic java/io/File.separator : Ljava/lang/String;
/*    */     //   22: astore_1
/*    */     //   23: new java/util/Date
/*    */     //   26: dup
/*    */     //   27: invokespecial <init> : ()V
/*    */     //   30: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
/*    */     //   33: astore_2
/*    */     //   34: new java/lang/StringBuilder
/*    */     //   37: dup
/*    */     //   38: dup2
/*    */     //   39: astore_3
/*    */     //   40: aload_2
/*    */     //   41: aload_3
/*    */     //   42: dup
/*    */     //   43: aload_1
/*    */     //   44: aload_3
/*    */     //   45: aload_0
/*    */     //   46: aload_3
/*    */     //   47: invokespecial <init> : ()V
/*    */     //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   53: pop
/*    */     //   54: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   57: pop
/*    */     //   58: ldc 'error_'
/*    */     //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   63: pop
/*    */     //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   67: pop
/*    */     //   68: ldc '.zip'
/*    */     //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*    */     //   73: pop
/*    */     //   74: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   77: astore_0
/*    */     //   78: getfield saveFolder : Ljava/io/File;
/*    */     //   81: invokevirtual getAbsolutePath : ()Ljava/lang/String;
/*    */     //   84: astore_1
/*    */     //   85: ldc 'printer_error'
/*    */     //   87: astore_2
/*    */     //   88: aconst_null
/*    */     //   89: astore_3
/*    */     //   90: aconst_null
/*    */     //   91: astore #4
/*    */     //   93: new java/io/FileOutputStream
/*    */     //   96: dup
/*    */     //   97: astore #5
/*    */     //   99: aload_0
/*    */     //   100: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   103: new java/util/zip/ZipOutputStream
/*    */     //   106: dup
/*    */     //   107: astore_3
/*    */     //   108: aload_2
/*    */     //   109: aload_3
/*    */     //   110: aload #5
/*    */     //   112: invokespecial <init> : (Ljava/io/OutputStream;)V
/*    */     //   115: new java/io/File
/*    */     //   118: dup
/*    */     //   119: aload_1
/*    */     //   120: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   123: aload_3
/*    */     //   124: invokestatic a : (Ljava/lang/String;Ljava/io/File;Ljava/util/zip/ZipOutputStream;)V
/*    */     //   127: invokevirtual close : ()V
/*    */     //   130: goto -> 136
/*    */     //   133: invokevirtual printStackTrace : ()V
/*    */     //   136: aload #5
/*    */     //   138: invokevirtual close : ()V
/*    */     //   141: goto -> 212
/*    */     //   144: invokevirtual printStackTrace : ()V
/*    */     //   147: goto -> 212
/*    */     //   150: astore_0
/*    */     //   151: aload_3
/*    */     //   152: aload #5
/*    */     //   154: astore_3
/*    */     //   155: astore #4
/*    */     //   157: goto -> 214
/*    */     //   160: aload_3
/*    */     //   161: aload #5
/*    */     //   163: astore_3
/*    */     //   164: astore #4
/*    */     //   166: goto -> 186
/*    */     //   169: astore_0
/*    */     //   170: aload #5
/*    */     //   172: astore_3
/*    */     //   173: goto -> 214
/*    */     //   176: aload #5
/*    */     //   178: astore_3
/*    */     //   179: goto -> 186
/*    */     //   182: astore_0
/*    */     //   183: goto -> 214
/*    */     //   186: invokevirtual printStackTrace : ()V
/*    */     //   189: aload #4
/*    */     //   191: invokevirtual close : ()V
/*    */     //   194: goto -> 200
/*    */     //   197: invokevirtual printStackTrace : ()V
/*    */     //   200: aload_3
/*    */     //   201: invokevirtual close : ()V
/*    */     //   204: goto -> 210
/*    */     //   207: invokevirtual printStackTrace : ()V
/*    */     //   210: aconst_null
/*    */     //   211: astore_0
/*    */     //   212: aload_0
/*    */     //   213: areturn
/*    */     //   214: aload #4
/*    */     //   216: invokevirtual close : ()V
/*    */     //   219: goto -> 225
/*    */     //   222: invokevirtual printStackTrace : ()V
/*    */     //   225: aload_3
/*    */     //   226: invokevirtual close : ()V
/*    */     //   229: goto -> 235
/*    */     //   232: invokevirtual printStackTrace : ()V
/*    */     //   235: aload_0
/*    */     //   236: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #1	-> 1
/*    */     //   #2	-> 12
/*    */     //   #3	-> 30
/*    */     //   #4	-> 34
/*    */     //   #6	-> 78
/*    */     //   #7	-> 93
/*    */     //   #8	-> 103
/*    */     //   #9	-> 115
/*    */     //   #16	-> 127
/*    */     //   #18	-> 133
/*    */     //   #21	-> 138
/*    */     //   #23	-> 144
/*    */     //   #24	-> 186
/*    */     //   #28	-> 191
/*    */     //   #30	-> 197
/*    */     //   #33	-> 201
/*    */     //   #35	-> 207
/*    */     //   #36	-> 216
/*    */     //   #38	-> 222
/*    */     //   #41	-> 226
/*    */     //   #43	-> 232
/*    */     //   #45	-> 236
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   93	96	186	java/io/IOException
/*    */     //   93	96	182	finally
/*    */     //   99	103	186	java/io/IOException
/*    */     //   99	103	182	finally
/*    */     //   103	106	176	java/io/IOException
/*    */     //   103	106	169	finally
/*    */     //   108	115	176	java/io/IOException
/*    */     //   108	115	169	finally
/*    */     //   115	127	160	java/io/IOException
/*    */     //   115	127	150	finally
/*    */     //   127	133	133	java/io/IOException
/*    */     //   136	144	144	java/io/IOException
/*    */     //   186	189	182	finally
/*    */     //   189	197	197	java/io/IOException
/*    */     //   200	207	207	java/io/IOException
/*    */     //   214	222	222	java/io/IOException
/*    */     //   225	232	232	java/io/IOException
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\client\FileEventRecorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */