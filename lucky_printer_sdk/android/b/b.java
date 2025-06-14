package b;

import java.util.TimerTask;

public final class b extends TimerTask {
  public b(f paramf) {}
  
  public final void run() {
    // Byte code:
    //   0: invokestatic getInstance : ()Lcom/luckprinter/sdk_new/device/PrinterHelper;
    //   3: invokevirtual isConnectedLuck : ()Z
    //   6: ifne -> 10
    //   9: return
    //   10: invokestatic getInstance : ()Lcom/luckprinter/sdk_new/device/PrinterHelper;
    //   13: invokevirtual getPrinterDevice : ()Lcom/luckprinter/sdk_new/device/BaseDevice;
    //   16: dup
    //   17: astore_1
    //   18: ifnonnull -> 22
    //   21: return
    //   22: aload_0
    //   23: dup
    //   24: dup
    //   25: getfield a : Lb/f;
    //   28: aload_1
    //   29: invokevirtual getSn : ()Ljava/lang/String;
    //   32: putfield c : Ljava/lang/String;
    //   35: getfield a : Lb/f;
    //   38: aload_1
    //   39: invokevirtual getModel : ()Ljava/lang/String;
    //   42: putfield e : Ljava/lang/String;
    //   45: getfield a : Lb/f;
    //   48: aload_1
    //   49: invokevirtual getVersion : ()Ljava/lang/String;
    //   52: putfield d : Ljava/lang/String;
    //   55: getstatic b/a.b : Ljava/lang/String;
    //   58: ifnonnull -> 69
    //   61: aload_0
    //   62: getfield a : Lb/f;
    //   65: invokevirtual a : ()V
    //   68: return
    //   69: aload_0
    //   70: getfield a : Lb/f;
    //   73: dup
    //   74: astore_2
    //   75: iconst_0
    //   76: istore_3
    //   77: getfield c : Ljava/lang/String;
    //   80: ifnonnull -> 85
    //   83: iconst_1
    //   84: istore_3
    //   85: aload_2
    //   86: getfield d : Ljava/lang/String;
    //   89: ifnonnull -> 95
    //   92: iinc #3, 1
    //   95: aload_2
    //   96: getfield e : Ljava/lang/String;
    //   99: ifnonnull -> 105
    //   102: iinc #3, 1
    //   105: iload_3
    //   106: ifgt -> 112
    //   109: goto -> 216
    //   112: aload_2
    //   113: new java/util/concurrent/CountDownLatch
    //   116: dup
    //   117: astore #4
    //   119: iload_3
    //   120: invokespecial <init> : (I)V
    //   123: getfield c : Ljava/lang/String;
    //   126: ifnonnull -> 145
    //   129: invokestatic getInstance : ()Lcom/luckprinter/sdk_new/device/PrinterHelper;
    //   132: new b/c
    //   135: dup
    //   136: aload_2
    //   137: aload #4
    //   139: invokespecial <init> : (Lb/f;Ljava/util/concurrent/CountDownLatch;)V
    //   142: invokevirtual printerSNLuck : (Lcom/luckprinter/sdk_new/callback/ResultCallback;)V
    //   145: aload_2
    //   146: getfield d : Ljava/lang/String;
    //   149: ifnonnull -> 168
    //   152: invokestatic getInstance : ()Lcom/luckprinter/sdk_new/device/PrinterHelper;
    //   155: new b/d
    //   158: dup
    //   159: aload_2
    //   160: aload #4
    //   162: invokespecial <init> : (Lb/f;Ljava/util/concurrent/CountDownLatch;)V
    //   165: invokevirtual printerVersionLuck : (Lcom/luckprinter/sdk_new/callback/ResultCallback;)V
    //   168: aload_2
    //   169: getfield e : Ljava/lang/String;
    //   172: ifnonnull -> 191
    //   175: invokestatic getInstance : ()Lcom/luckprinter/sdk_new/device/PrinterHelper;
    //   178: new b/e
    //   181: dup
    //   182: aload_2
    //   183: aload #4
    //   185: invokespecial <init> : (Lb/f;Ljava/util/concurrent/CountDownLatch;)V
    //   188: invokevirtual printerModelLuck : (Lcom/luckprinter/sdk_new/callback/ResultCallback;)V
    //   191: aload #4
    //   193: ldc2_w 3
    //   196: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   199: invokevirtual await : (JLjava/util/concurrent/TimeUnit;)Z
    //   202: pop
    //   203: goto -> 216
    //   206: invokevirtual getLocalizedMessage : ()Ljava/lang/String;
    //   209: ldc 'DeviceChecker'
    //   211: swap
    //   212: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   215: pop
    //   216: aload_0
    //   217: getfield a : Lb/f;
    //   220: getfield c : Ljava/lang/String;
    //   223: ifnonnull -> 232
    //   226: ldc 'sn get null'
    //   228: invokestatic a : (Ljava/lang/String;)V
    //   231: return
    //   232: aload_1
    //   233: invokevirtual getDeviceName : ()Ljava/lang/String;
    //   236: astore_1
    //   237: new java/util/HashMap
    //   240: dup
    //   241: aload_0
    //   242: swap
    //   243: dup
    //   244: dup2
    //   245: dup2
    //   246: invokespecial <init> : ()V
    //   249: getstatic b/a.b : Ljava/lang/String;
    //   252: ldc 'asKey'
    //   254: swap
    //   255: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   258: pop
    //   259: ldc 'sn'
    //   261: aload_0
    //   262: getfield a : Lb/f;
    //   265: getfield c : Ljava/lang/String;
    //   268: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   271: pop
    //   272: ldc 'softwareVersion'
    //   274: aload_0
    //   275: getfield a : Lb/f;
    //   278: getfield d : Ljava/lang/String;
    //   281: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   284: pop
    //   285: ldc 'model'
    //   287: aload_0
    //   288: getfield a : Lb/f;
    //   291: getfield e : Ljava/lang/String;
    //   294: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   297: pop
    //   298: ldc 'bluetoothname'
    //   300: aload_1
    //   301: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   304: pop
    //   305: getfield a : Lb/f;
    //   308: invokevirtual getClass : ()Ljava/lang/Class;
    //   311: pop
    //   312: ldc 'https://api.ddnew.luckprint.com/api/sdk/check2'
    //   314: astore_1
    //   315: new okhttp3/OkHttpClient
    //   318: dup
    //   319: astore_2
    //   320: invokespecial <init> : ()V
    //   323: new java/lang/StringBuilder
    //   326: dup
    //   327: astore_3
    //   328: invokespecial <init> : ()V
    //   331: invokevirtual entrySet : ()Ljava/util/Set;
    //   334: invokeinterface iterator : ()Ljava/util/Iterator;
    //   339: astore #4
    //   341: aload #4
    //   343: invokeinterface hasNext : ()Z
    //   348: ifeq -> 422
    //   351: aload_3
    //   352: aload #4
    //   354: invokeinterface next : ()Ljava/lang/Object;
    //   359: checkcast java/util/Map$Entry
    //   362: dup
    //   363: invokeinterface getKey : ()Ljava/lang/Object;
    //   368: checkcast java/lang/String
    //   371: astore #5
    //   373: invokeinterface getValue : ()Ljava/lang/Object;
    //   378: astore #6
    //   380: invokevirtual length : ()I
    //   383: ifeq -> 393
    //   386: aload_3
    //   387: bipush #38
    //   389: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload #6
    //   395: aload_3
    //   396: aload #5
    //   398: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: pop
    //   402: ifnull -> 341
    //   405: aload_3
    //   406: dup
    //   407: bipush #61
    //   409: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   412: pop
    //   413: aload #6
    //   415: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   418: pop
    //   419: goto -> 341
    //   422: aload_2
    //   423: ldc 'application/x-www-form-urlencoded'
    //   425: invokestatic get : (Ljava/lang/String;)Lokhttp3/MediaType;
    //   428: aload_3
    //   429: invokevirtual toString : ()Ljava/lang/String;
    //   432: invokestatic create : (Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;
    //   435: new okhttp3/Request$Builder
    //   438: dup
    //   439: aload_1
    //   440: swap
    //   441: invokespecial <init> : ()V
    //   444: invokevirtual url : (Ljava/lang/String;)Lokhttp3/Request$Builder;
    //   447: swap
    //   448: invokevirtual post : (Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;
    //   451: invokevirtual build : ()Lokhttp3/Request;
    //   454: invokevirtual newCall : (Lokhttp3/Request;)Lokhttp3/Call;
    //   457: invokeinterface execute : ()Lokhttp3/Response;
    //   462: dup
    //   463: astore_1
    //   464: invokevirtual isSuccessful : ()Z
    //   467: ifeq -> 484
    //   470: aload_1
    //   471: invokevirtual body : ()Lokhttp3/ResponseBody;
    //   474: invokevirtual string : ()Ljava/lang/String;
    //   477: astore_1
    //   478: goto -> 488
    //   481: invokevirtual printStackTrace : ()V
    //   484: ldc_w '{}'
    //   487: astore_1
    //   488: aload_0
    //   489: dup
    //   490: getfield a : Lb/f;
    //   493: invokevirtual getClass : ()Ljava/lang/Class;
    //   496: pop
    //   497: getfield a : Lb/f;
    //   500: getfield c : Ljava/lang/String;
    //   503: astore_2
    //   504: new java/lang/StringBuilder
    //   507: dup
    //   508: dup2
    //   509: astore_3
    //   510: aload_1
    //   511: aload_3
    //   512: dup
    //   513: aload_2
    //   514: aload_3
    //   515: ldc_w '\\nurl: https://api.ddnew.luckprint.com/api/sdk/check2 \\nrequest sn: '
    //   518: invokespecial <init> : (Ljava/lang/String;)V
    //   521: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: pop
    //   525: ldc_w ' \\nresponse: '
    //   528: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: pop
    //   532: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   535: pop
    //   536: ldc_w ' \\n'
    //   539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: pop
    //   543: invokevirtual toString : ()Ljava/lang/String;
    //   546: invokestatic a : (Ljava/lang/String;)V
    //   549: aconst_null
    //   550: astore_2
    //   551: new org/json/JSONObject
    //   554: dup
    //   555: dup
    //   556: astore_3
    //   557: aload_1
    //   558: invokespecial <init> : (Ljava/lang/String;)V
    //   561: ldc_w 'code'
    //   564: invokevirtual has : (Ljava/lang/String;)Z
    //   567: ifeq -> 595
    //   570: aload_3
    //   571: ldc_w 'code'
    //   574: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   577: astore_2
    //   578: goto -> 595
    //   581: dup
    //   582: invokevirtual printStackTrace : ()V
    //   585: invokevirtual getLocalizedMessage : ()Ljava/lang/String;
    //   588: ldc 'DeviceChecker'
    //   590: swap
    //   591: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   594: pop
    //   595: aload_2
    //   596: ifnull -> 639
    //   599: aload_0
    //   600: getfield a : Lb/f;
    //   603: dup
    //   604: astore_1
    //   605: getfield a : Ljava/util/Timer;
    //   608: dup
    //   609: astore_3
    //   610: ifnull -> 622
    //   613: aload_1
    //   614: aload_3
    //   615: invokevirtual cancel : ()V
    //   618: aconst_null
    //   619: putfield a : Ljava/util/Timer;
    //   622: aload_2
    //   623: ldc_w '501'
    //   626: invokevirtual equals : (Ljava/lang/Object;)Z
    //   629: ifeq -> 639
    //   632: aload_0
    //   633: getfield a : Lb/f;
    //   636: invokevirtual a : ()V
    //   639: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #1	-> 0
    //   #5	-> 10
    //   #10	-> 25
    //   #11	-> 32
    //   #12	-> 35
    //   #13	-> 42
    //   #14	-> 45
    //   #15	-> 52
    //   #16	-> 55
    //   #17	-> 62
    //   #18	-> 65
    //   #19	-> 70
    //   #20	-> 77
    //   #23	-> 86
    //   #26	-> 96
    //   #32	-> 113
    //   #33	-> 123
    //   #34	-> 129
    //   #48	-> 146
    //   #49	-> 152
    //   #63	-> 169
    //   #64	-> 175
    //   #79	-> 196
    //   #81	-> 206
    //   #82	-> 217
    //   #83	-> 220
    //   #84	-> 226
    //   #88	-> 233
    //   #89	-> 237
    //   #90	-> 249
    //   #91	-> 259
    //   #92	-> 265
    //   #93	-> 268
    //   #94	-> 272
    //   #95	-> 278
    //   #96	-> 281
    //   #97	-> 285
    //   #98	-> 291
    //   #99	-> 294
    //   #100	-> 298
    //   #101	-> 305
    //   #102	-> 308
    //   #103	-> 312
    //   #104	-> 315
    //   #105	-> 323
    //   #106	-> 331
    //   #107	-> 363
    //   #108	-> 373
    //   #109	-> 380
    //   #110	-> 389
    //   #112	-> 398
    //   #114	-> 409
    //   #115	-> 415
    //   #119	-> 423
    //   #120	-> 429
    //   #121	-> 432
    //   #124	-> 435
    //   #126	-> 454
    //   #127	-> 464
    //   #128	-> 471
    //   #132	-> 481
    //   #134	-> 484
    //   #135	-> 490
    //   #136	-> 493
    //   #137	-> 497
    //   #138	-> 500
    //   #139	-> 504
    //   #145	-> 551
    //   #146	-> 561
    //   #147	-> 571
    //   #150	-> 582
    //   #151	-> 585
    //   #155	-> 600
    //   #156	-> 605
    //   #157	-> 615
    //   #158	-> 619
    //   #159	-> 623
    //   #160	-> 633
    //   #161	-> 636
    // Exception table:
    //   from	to	target	type
    //   196	202	206	java/lang/InterruptedException
    //   454	462	481	java/io/IOException
    //   464	467	481	java/io/IOException
    //   470	477	481	java/io/IOException
    //   551	554	581	org/json/JSONException
    //   557	567	581	org/json/JSONException
    //   570	577	581	org/json/JSONException
  }
}


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\b\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */