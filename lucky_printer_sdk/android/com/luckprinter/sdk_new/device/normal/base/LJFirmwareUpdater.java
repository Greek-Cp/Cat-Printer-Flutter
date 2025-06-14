/* 1 */ package com.luckprinter.sdk_new.device.normal.base;public class LJFirmwareUpdater { private static byte calculateEnc(byte[] paramArrayOfbyte) { int i; byte b; byte b1; for (i = paramArrayOfbyte.length, b = 0, b1 = 0; b1 < i - 1; )
/*   */     
/*   */     { 
/* 4 */       b = (byte)(b + paramArrayOfbyte[b1]); b1++; }  return b; }
/*   */ 
/*   */   
/*   */   public static void doUpdateFirmware(File paramFile, UpdateListener paramUpdateListener) {
/*   */     // Byte code:
/*   */     //   0: invokestatic b : ()Lc/d;
/*   */     //   3: dup
/*   */     //   4: astore_2
/*   */     //   5: invokestatic getInstance : ()Lcom/luckprinter/sdk_new/device/PrinterHelper;
/*   */     //   8: invokevirtual getPrinterDevice : ()Lcom/luckprinter/sdk_new/device/BaseDevice;
/*   */     //   11: astore_3
/*   */     //   12: ifnull -> 1147
/*   */     //   15: aload_2
/*   */     //   16: getfield a : Lc/c;
/*   */     //   19: invokevirtual c : ()Z
/*   */     //   22: ifeq -> 1147
/*   */     //   25: aload_3
/*   */     //   26: ifnonnull -> 32
/*   */     //   29: goto -> 1147
/*   */     //   32: aload_0
/*   */     //   33: ifnull -> 1146
/*   */     //   36: aload_0
/*   */     //   37: invokevirtual exists : ()Z
/*   */     //   40: ifne -> 46
/*   */     //   43: goto -> 1146
/*   */     //   46: aload_3
/*   */     //   47: aload_2
/*   */     //   48: dup
/*   */     //   49: aload_0
/*   */     //   50: aload_1
/*   */     //   51: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   56: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   59: invokevirtual length : ()J
/*   */     //   62: lstore_3
/*   */     //   63: iconst_5
/*   */     //   64: newarray byte
/*   */     //   66: dup
/*   */     //   67: dup2
/*   */     //   68: dup2
/*   */     //   69: astore #5
/*   */     //   71: iconst_0
/*   */     //   72: bipush #16
/*   */     //   74: bastore
/*   */     //   75: iconst_1
/*   */     //   76: iconst_m1
/*   */     //   77: bastore
/*   */     //   78: iconst_2
/*   */     //   79: bipush #-32
/*   */     //   81: bastore
/*   */     //   82: iconst_3
/*   */     //   83: bipush #-86
/*   */     //   85: bastore
/*   */     //   86: iconst_4
/*   */     //   87: bipush #-86
/*   */     //   89: bastore
/*   */     //   90: iconst_0
/*   */     //   91: istore #6
/*   */     //   93: getfield a : Lc/c;
/*   */     //   96: aconst_null
/*   */     //   97: aload #5
/*   */     //   99: iload #6
/*   */     //   101: iconst_3
/*   */     //   102: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   105: pop
/*   */     //   106: bipush #13
/*   */     //   108: newarray byte
/*   */     //   110: dup
/*   */     //   111: dup2
/*   */     //   112: dup2
/*   */     //   113: dup2
/*   */     //   114: dup2
/*   */     //   115: dup2
/*   */     //   116: dup2
/*   */     //   117: dup2
/*   */     //   118: astore #5
/*   */     //   120: iconst_0
/*   */     //   121: bipush #27
/*   */     //   123: bastore
/*   */     //   124: iconst_1
/*   */     //   125: bipush #16
/*   */     //   127: bastore
/*   */     //   128: iconst_2
/*   */     //   129: iconst_0
/*   */     //   130: bastore
/*   */     //   131: iconst_3
/*   */     //   132: bipush #6
/*   */     //   134: bastore
/*   */     //   135: iconst_4
/*   */     //   136: iconst_0
/*   */     //   137: bastore
/*   */     //   138: iconst_5
/*   */     //   139: iconst_0
/*   */     //   140: bastore
/*   */     //   141: bipush #6
/*   */     //   143: iconst_2
/*   */     //   144: bastore
/*   */     //   145: bipush #7
/*   */     //   147: iconst_0
/*   */     //   148: bastore
/*   */     //   149: bipush #8
/*   */     //   151: iconst_0
/*   */     //   152: bastore
/*   */     //   153: bipush #9
/*   */     //   155: iconst_0
/*   */     //   156: bastore
/*   */     //   157: bipush #10
/*   */     //   159: iconst_0
/*   */     //   160: bastore
/*   */     //   161: bipush #11
/*   */     //   163: iconst_0
/*   */     //   164: bastore
/*   */     //   165: bipush #12
/*   */     //   167: iconst_0
/*   */     //   168: bastore
/*   */     //   169: invokestatic calculateEnc : ([B)B
/*   */     //   172: istore #6
/*   */     //   174: bipush #12
/*   */     //   176: dup
/*   */     //   177: istore #7
/*   */     //   179: iload #6
/*   */     //   181: bastore
/*   */     //   182: iconst_1
/*   */     //   183: istore #6
/*   */     //   185: getfield a : Lc/c;
/*   */     //   188: aconst_null
/*   */     //   189: aload #5
/*   */     //   191: iload #6
/*   */     //   193: iconst_3
/*   */     //   194: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   197: pop
/*   */     //   198: ldc 134250496
/*   */     //   200: i2l
/*   */     //   201: lload_3
/*   */     //   202: ladd
/*   */     //   203: l2i
/*   */     //   204: invokestatic intToByteArray4 : (I)[B
/*   */     //   207: dup
/*   */     //   208: dup2
/*   */     //   209: bipush #22
/*   */     //   211: newarray byte
/*   */     //   213: dup
/*   */     //   214: astore #5
/*   */     //   216: iconst_0
/*   */     //   217: bipush #27
/*   */     //   219: bastore
/*   */     //   220: aload #5
/*   */     //   222: iconst_1
/*   */     //   223: bipush #16
/*   */     //   225: bastore
/*   */     //   226: aload #5
/*   */     //   228: iconst_2
/*   */     //   229: iconst_0
/*   */     //   230: bastore
/*   */     //   231: aload #5
/*   */     //   233: iconst_3
/*   */     //   234: bipush #15
/*   */     //   236: bastore
/*   */     //   237: aload #5
/*   */     //   239: iconst_4
/*   */     //   240: iconst_0
/*   */     //   241: bastore
/*   */     //   242: aload #5
/*   */     //   244: iconst_5
/*   */     //   245: iconst_0
/*   */     //   246: bastore
/*   */     //   247: aload #5
/*   */     //   249: bipush #6
/*   */     //   251: iconst_3
/*   */     //   252: bastore
/*   */     //   253: aload #5
/*   */     //   255: bipush #7
/*   */     //   257: iconst_0
/*   */     //   258: bastore
/*   */     //   259: aload #5
/*   */     //   261: bipush #8
/*   */     //   263: iconst_0
/*   */     //   264: bastore
/*   */     //   265: aload #5
/*   */     //   267: bipush #9
/*   */     //   269: iconst_0
/*   */     //   270: bastore
/*   */     //   271: aload #5
/*   */     //   273: bipush #10
/*   */     //   275: iconst_0
/*   */     //   276: bastore
/*   */     //   277: aload #5
/*   */     //   279: bipush #11
/*   */     //   281: bipush #9
/*   */     //   283: bastore
/*   */     //   284: aload #5
/*   */     //   286: bipush #12
/*   */     //   288: iconst_0
/*   */     //   289: bastore
/*   */     //   290: aload #5
/*   */     //   292: bipush #13
/*   */     //   294: bipush #8
/*   */     //   296: bastore
/*   */     //   297: aload #5
/*   */     //   299: bipush #14
/*   */     //   301: iconst_0
/*   */     //   302: bastore
/*   */     //   303: aload #5
/*   */     //   305: bipush #15
/*   */     //   307: bipush #-128
/*   */     //   309: bastore
/*   */     //   310: aload #5
/*   */     //   312: bipush #16
/*   */     //   314: iconst_0
/*   */     //   315: bastore
/*   */     //   316: aload #5
/*   */     //   318: bipush #17
/*   */     //   320: iconst_0
/*   */     //   321: bastore
/*   */     //   322: aload #5
/*   */     //   324: bipush #18
/*   */     //   326: iconst_0
/*   */     //   327: bastore
/*   */     //   328: aload #5
/*   */     //   330: bipush #19
/*   */     //   332: iconst_0
/*   */     //   333: bastore
/*   */     //   334: aload #5
/*   */     //   336: bipush #20
/*   */     //   338: iconst_0
/*   */     //   339: bastore
/*   */     //   340: aload #5
/*   */     //   342: bipush #21
/*   */     //   344: iconst_0
/*   */     //   345: bastore
/*   */     //   346: iconst_0
/*   */     //   347: baload
/*   */     //   348: istore #6
/*   */     //   350: aload #5
/*   */     //   352: bipush #17
/*   */     //   354: iload #6
/*   */     //   356: bastore
/*   */     //   357: iconst_1
/*   */     //   358: baload
/*   */     //   359: istore #6
/*   */     //   361: aload #5
/*   */     //   363: bipush #18
/*   */     //   365: iload #6
/*   */     //   367: bastore
/*   */     //   368: iconst_2
/*   */     //   369: baload
/*   */     //   370: istore #6
/*   */     //   372: aload #5
/*   */     //   374: bipush #19
/*   */     //   376: iload #6
/*   */     //   378: bastore
/*   */     //   379: iconst_3
/*   */     //   380: baload
/*   */     //   381: istore #6
/*   */     //   383: aload #5
/*   */     //   385: bipush #20
/*   */     //   387: iload #6
/*   */     //   389: bastore
/*   */     //   390: aload #5
/*   */     //   392: invokestatic calculateEnc : ([B)B
/*   */     //   395: istore #6
/*   */     //   397: aload #5
/*   */     //   399: bipush #21
/*   */     //   401: iload #6
/*   */     //   403: bastore
/*   */     //   404: invokevirtual isHasFirmwareEraseCallbackData : ()Z
/*   */     //   407: ifeq -> 525
/*   */     //   410: aload_2
/*   */     //   411: iconst_1
/*   */     //   412: istore #6
/*   */     //   414: bipush #30
/*   */     //   416: istore #8
/*   */     //   418: getfield a : Lc/c;
/*   */     //   421: aconst_null
/*   */     //   422: aload #5
/*   */     //   424: iload #6
/*   */     //   426: iload #8
/*   */     //   428: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   431: dup
/*   */     //   432: astore #5
/*   */     //   434: bipush #14
/*   */     //   436: newarray byte
/*   */     //   438: dup
/*   */     //   439: dup
/*   */     //   440: dup2
/*   */     //   441: dup2
/*   */     //   442: dup2
/*   */     //   443: dup2
/*   */     //   444: dup2
/*   */     //   445: dup2
/*   */     //   446: astore #6
/*   */     //   448: iconst_0
/*   */     //   449: bipush #27
/*   */     //   451: bastore
/*   */     //   452: iconst_1
/*   */     //   453: bipush #16
/*   */     //   455: bastore
/*   */     //   456: iconst_2
/*   */     //   457: iconst_0
/*   */     //   458: bastore
/*   */     //   459: iconst_3
/*   */     //   460: bipush #7
/*   */     //   462: bastore
/*   */     //   463: iconst_4
/*   */     //   464: iconst_0
/*   */     //   465: bastore
/*   */     //   466: iconst_5
/*   */     //   467: iconst_0
/*   */     //   468: bastore
/*   */     //   469: bipush #6
/*   */     //   471: iconst_3
/*   */     //   472: bastore
/*   */     //   473: bipush #7
/*   */     //   475: iconst_1
/*   */     //   476: bastore
/*   */     //   477: bipush #8
/*   */     //   479: iconst_0
/*   */     //   480: bastore
/*   */     //   481: bipush #9
/*   */     //   483: iconst_0
/*   */     //   484: bastore
/*   */     //   485: bipush #10
/*   */     //   487: iconst_0
/*   */     //   488: bastore
/*   */     //   489: bipush #11
/*   */     //   491: iconst_1
/*   */     //   492: bastore
/*   */     //   493: bipush #12
/*   */     //   495: iconst_0
/*   */     //   496: bastore
/*   */     //   497: bipush #13
/*   */     //   499: bipush #55
/*   */     //   501: bastore
/*   */     //   502: ifnull -> 515
/*   */     //   505: aload #5
/*   */     //   507: aload #6
/*   */     //   509: invokestatic compareBytes : ([B[B)Z
/*   */     //   512: ifne -> 542
/*   */     //   515: aload_1
/*   */     //   516: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   521: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   524: return
/*   */     //   525: aload_2
/*   */     //   526: iconst_0
/*   */     //   527: istore #6
/*   */     //   529: getfield a : Lc/c;
/*   */     //   532: aconst_null
/*   */     //   533: aload #5
/*   */     //   535: iload #6
/*   */     //   537: iconst_3
/*   */     //   538: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   541: pop
/*   */     //   542: lload_3
/*   */     //   543: dup2
/*   */     //   544: new java/io/FileInputStream
/*   */     //   547: dup
/*   */     //   548: astore_3
/*   */     //   549: aload_0
/*   */     //   550: invokespecial <init> : (Ljava/io/File;)V
/*   */     //   553: sipush #256
/*   */     //   556: dup
/*   */     //   557: istore_0
/*   */     //   558: iconst_0
/*   */     //   559: istore #4
/*   */     //   561: i2l
/*   */     //   562: dup2
/*   */     //   563: lstore #5
/*   */     //   565: ldiv
/*   */     //   566: l2i
/*   */     //   567: istore #8
/*   */     //   569: lload #5
/*   */     //   571: lrem
/*   */     //   572: dup2
/*   */     //   573: lstore #5
/*   */     //   575: lconst_0
/*   */     //   576: lcmp
/*   */     //   577: ifle -> 586
/*   */     //   580: iinc #8, 1
/*   */     //   583: iconst_1
/*   */     //   584: istore #4
/*   */     //   586: iconst_0
/*   */     //   587: istore #9
/*   */     //   589: iload #9
/*   */     //   591: iload #8
/*   */     //   593: if_icmpge -> 950
/*   */     //   596: iload #4
/*   */     //   598: ifeq -> 618
/*   */     //   601: iload #9
/*   */     //   603: iload #8
/*   */     //   605: iconst_1
/*   */     //   606: isub
/*   */     //   607: if_icmpne -> 618
/*   */     //   610: lload #5
/*   */     //   612: l2i
/*   */     //   613: istore #10
/*   */     //   615: goto -> 621
/*   */     //   618: iload_0
/*   */     //   619: istore #10
/*   */     //   621: aload_2
/*   */     //   622: iload #10
/*   */     //   624: dup
/*   */     //   625: dup2
/*   */     //   626: aload_3
/*   */     //   627: iload #10
/*   */     //   629: newarray byte
/*   */     //   631: dup
/*   */     //   632: astore #11
/*   */     //   634: invokevirtual read : ([B)I
/*   */     //   637: pop
/*   */     //   638: bipush #13
/*   */     //   640: iadd
/*   */     //   641: invokestatic intToByteArray2 : (I)[B
/*   */     //   644: astore #12
/*   */     //   646: bipush #7
/*   */     //   648: iadd
/*   */     //   649: invokestatic intToByteArray2 : (I)[B
/*   */     //   652: astore #13
/*   */     //   654: invokestatic intToByteArray2 : (I)[B
/*   */     //   657: dup
/*   */     //   658: iload #9
/*   */     //   660: invokestatic intToByteArray4 : (I)[B
/*   */     //   663: dup
/*   */     //   664: dup2
/*   */     //   665: aload #13
/*   */     //   667: dup
/*   */     //   668: aload #12
/*   */     //   670: dup
/*   */     //   671: bipush #19
/*   */     //   673: istore #12
/*   */     //   675: iconst_0
/*   */     //   676: baload
/*   */     //   677: istore #13
/*   */     //   679: iconst_1
/*   */     //   680: baload
/*   */     //   681: istore #14
/*   */     //   683: iconst_0
/*   */     //   684: baload
/*   */     //   685: istore #15
/*   */     //   687: iconst_1
/*   */     //   688: baload
/*   */     //   689: istore #16
/*   */     //   691: iconst_0
/*   */     //   692: baload
/*   */     //   693: istore #17
/*   */     //   695: iconst_1
/*   */     //   696: baload
/*   */     //   697: istore #18
/*   */     //   699: iconst_2
/*   */     //   700: baload
/*   */     //   701: istore #19
/*   */     //   703: iconst_3
/*   */     //   704: baload
/*   */     //   705: istore #20
/*   */     //   707: iconst_0
/*   */     //   708: baload
/*   */     //   709: istore #21
/*   */     //   711: iconst_1
/*   */     //   712: baload
/*   */     //   713: istore #22
/*   */     //   715: bipush #19
/*   */     //   717: newarray byte
/*   */     //   719: dup
/*   */     //   720: dup2
/*   */     //   721: dup2
/*   */     //   722: dup2
/*   */     //   723: dup2
/*   */     //   724: dup2
/*   */     //   725: dup2
/*   */     //   726: dup2
/*   */     //   727: dup2
/*   */     //   728: dup2
/*   */     //   729: astore #23
/*   */     //   731: bipush #27
/*   */     //   733: iconst_0
/*   */     //   734: swap
/*   */     //   735: bastore
/*   */     //   736: bipush #16
/*   */     //   738: iconst_1
/*   */     //   739: swap
/*   */     //   740: bastore
/*   */     //   741: iconst_2
/*   */     //   742: iload #13
/*   */     //   744: bastore
/*   */     //   745: iconst_3
/*   */     //   746: iload #14
/*   */     //   748: bastore
/*   */     //   749: iconst_0
/*   */     //   750: iconst_4
/*   */     //   751: swap
/*   */     //   752: bastore
/*   */     //   753: iconst_0
/*   */     //   754: iconst_5
/*   */     //   755: swap
/*   */     //   756: bastore
/*   */     //   757: iconst_4
/*   */     //   758: bipush #6
/*   */     //   760: swap
/*   */     //   761: bastore
/*   */     //   762: iconst_0
/*   */     //   763: bipush #7
/*   */     //   765: swap
/*   */     //   766: bastore
/*   */     //   767: iconst_0
/*   */     //   768: bipush #8
/*   */     //   770: swap
/*   */     //   771: bastore
/*   */     //   772: iconst_0
/*   */     //   773: bipush #9
/*   */     //   775: swap
/*   */     //   776: bastore
/*   */     //   777: bipush #10
/*   */     //   779: iload #15
/*   */     //   781: bastore
/*   */     //   782: bipush #11
/*   */     //   784: iload #16
/*   */     //   786: bastore
/*   */     //   787: iconst_0
/*   */     //   788: bipush #12
/*   */     //   790: swap
/*   */     //   791: bastore
/*   */     //   792: bipush #13
/*   */     //   794: iload #17
/*   */     //   796: bastore
/*   */     //   797: bipush #14
/*   */     //   799: iload #18
/*   */     //   801: bastore
/*   */     //   802: bipush #15
/*   */     //   804: iload #19
/*   */     //   806: bastore
/*   */     //   807: bipush #16
/*   */     //   809: iload #20
/*   */     //   811: bastore
/*   */     //   812: bipush #17
/*   */     //   814: iload #21
/*   */     //   816: bastore
/*   */     //   817: bipush #18
/*   */     //   819: iload #22
/*   */     //   821: bastore
/*   */     //   822: bipush #20
/*   */     //   824: iadd
/*   */     //   825: newarray byte
/*   */     //   827: dup
/*   */     //   828: astore #13
/*   */     //   830: iload #10
/*   */     //   832: aload #13
/*   */     //   834: aload #11
/*   */     //   836: aload #23
/*   */     //   838: iconst_0
/*   */     //   839: aload #13
/*   */     //   841: iconst_0
/*   */     //   842: iload #12
/*   */     //   844: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*   */     //   847: iconst_0
/*   */     //   848: aload #13
/*   */     //   850: iload #12
/*   */     //   852: iload #10
/*   */     //   854: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*   */     //   857: invokestatic calculateEnc : ([B)B
/*   */     //   860: istore #10
/*   */     //   862: bipush #19
/*   */     //   864: iadd
/*   */     //   865: iload #10
/*   */     //   867: bastore
/*   */     //   868: iconst_1
/*   */     //   869: istore #10
/*   */     //   871: getfield a : Lc/c;
/*   */     //   874: aconst_null
/*   */     //   875: aload #13
/*   */     //   877: iload #10
/*   */     //   879: iconst_3
/*   */     //   880: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   883: ifnull -> 926
/*   */     //   886: aload_1
/*   */     //   887: iload #9
/*   */     //   889: iconst_1
/*   */     //   890: iadd
/*   */     //   891: dup
/*   */     //   892: istore #9
/*   */     //   894: i2d
/*   */     //   895: dconst_1
/*   */     //   896: dmul
/*   */     //   897: iload #8
/*   */     //   899: i2d
/*   */     //   900: ddiv
/*   */     //   901: ldc2_w 100.0
/*   */     //   904: dmul
/*   */     //   905: d2i
/*   */     //   906: ldc 'firm package ack ok!'
/*   */     //   908: invokestatic a : (Ljava/lang/String;)V
/*   */     //   911: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;I)Ljava/lang/Runnable;
/*   */     //   916: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   919: goto -> 589
/*   */     //   922: astore_0
/*   */     //   923: goto -> 934
/*   */     //   926: ldc 'firm package ack error!'
/*   */     //   928: invokestatic a : (Ljava/lang/String;)V
/*   */     //   931: goto -> 938
/*   */     //   934: aload_0
/*   */     //   935: invokevirtual printStackTrace : ()V
/*   */     //   938: aload_1
/*   */     //   939: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   944: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   947: goto -> 1135
/*   */     //   950: aload_1
/*   */     //   951: aload_2
/*   */     //   952: dup
/*   */     //   953: aload_1
/*   */     //   954: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   959: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   962: bipush #13
/*   */     //   964: newarray byte
/*   */     //   966: dup
/*   */     //   967: astore_0
/*   */     //   968: iload #7
/*   */     //   970: aload_0
/*   */     //   971: dup
/*   */     //   972: dup2
/*   */     //   973: dup2
/*   */     //   974: dup2
/*   */     //   975: dup2
/*   */     //   976: dup2
/*   */     //   977: dup2
/*   */     //   978: iconst_0
/*   */     //   979: bipush #27
/*   */     //   981: bastore
/*   */     //   982: iconst_1
/*   */     //   983: bipush #16
/*   */     //   985: bastore
/*   */     //   986: iconst_2
/*   */     //   987: iconst_0
/*   */     //   988: bastore
/*   */     //   989: iconst_3
/*   */     //   990: bipush #6
/*   */     //   992: bastore
/*   */     //   993: iconst_4
/*   */     //   994: iconst_0
/*   */     //   995: bastore
/*   */     //   996: iconst_5
/*   */     //   997: iconst_0
/*   */     //   998: bastore
/*   */     //   999: bipush #6
/*   */     //   1001: iconst_2
/*   */     //   1002: bastore
/*   */     //   1003: bipush #7
/*   */     //   1005: iconst_0
/*   */     //   1006: bastore
/*   */     //   1007: bipush #8
/*   */     //   1009: iconst_0
/*   */     //   1010: bastore
/*   */     //   1011: bipush #9
/*   */     //   1013: iconst_0
/*   */     //   1014: bastore
/*   */     //   1015: bipush #10
/*   */     //   1017: iconst_0
/*   */     //   1018: bastore
/*   */     //   1019: bipush #11
/*   */     //   1021: iconst_0
/*   */     //   1022: bastore
/*   */     //   1023: bipush #12
/*   */     //   1025: iconst_0
/*   */     //   1026: bastore
/*   */     //   1027: invokestatic calculateEnc : ([B)B
/*   */     //   1030: bastore
/*   */     //   1031: iconst_1
/*   */     //   1032: istore_1
/*   */     //   1033: getfield a : Lc/c;
/*   */     //   1036: aconst_null
/*   */     //   1037: aload_0
/*   */     //   1038: iload_1
/*   */     //   1039: iconst_3
/*   */     //   1040: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   1043: pop
/*   */     //   1044: bipush #13
/*   */     //   1046: newarray byte
/*   */     //   1048: dup
/*   */     //   1049: astore_0
/*   */     //   1050: iload #7
/*   */     //   1052: aload_0
/*   */     //   1053: dup
/*   */     //   1054: dup2
/*   */     //   1055: dup2
/*   */     //   1056: dup2
/*   */     //   1057: dup2
/*   */     //   1058: dup2
/*   */     //   1059: dup2
/*   */     //   1060: iconst_0
/*   */     //   1061: bipush #27
/*   */     //   1063: bastore
/*   */     //   1064: iconst_1
/*   */     //   1065: bipush #16
/*   */     //   1067: bastore
/*   */     //   1068: iconst_2
/*   */     //   1069: iconst_0
/*   */     //   1070: bastore
/*   */     //   1071: iconst_3
/*   */     //   1072: bipush #6
/*   */     //   1074: bastore
/*   */     //   1075: iconst_4
/*   */     //   1076: iconst_0
/*   */     //   1077: bastore
/*   */     //   1078: iconst_5
/*   */     //   1079: iconst_0
/*   */     //   1080: bastore
/*   */     //   1081: bipush #6
/*   */     //   1083: bipush #7
/*   */     //   1085: bastore
/*   */     //   1086: bipush #7
/*   */     //   1088: iconst_0
/*   */     //   1089: bastore
/*   */     //   1090: bipush #8
/*   */     //   1092: iconst_0
/*   */     //   1093: bastore
/*   */     //   1094: bipush #9
/*   */     //   1096: iconst_0
/*   */     //   1097: bastore
/*   */     //   1098: bipush #10
/*   */     //   1100: iconst_0
/*   */     //   1101: bastore
/*   */     //   1102: bipush #11
/*   */     //   1104: iconst_0
/*   */     //   1105: bastore
/*   */     //   1106: bipush #12
/*   */     //   1108: iconst_0
/*   */     //   1109: bastore
/*   */     //   1110: invokestatic calculateEnc : ([B)B
/*   */     //   1113: bastore
/*   */     //   1114: iconst_0
/*   */     //   1115: istore_1
/*   */     //   1116: getfield a : Lc/c;
/*   */     //   1119: aconst_null
/*   */     //   1120: aload_0
/*   */     //   1121: iload_1
/*   */     //   1122: iconst_3
/*   */     //   1123: invokevirtual a : (Lcom/luckprinter/sdk_new/callback/IReceiveDataClaud;[BZI)[B
/*   */     //   1126: pop
/*   */     //   1127: <illegal opcode> run : (Lcom/luckprinter/sdk_new/callback/UpdateListener;)Ljava/lang/Runnable;
/*   */     //   1132: invokestatic runOnUi : (Ljava/lang/Runnable;)V
/*   */     //   1135: return
/*   */     //   1136: astore_0
/*   */     //   1137: new java/lang/RuntimeException
/*   */     //   1140: dup
/*   */     //   1141: aload_0
/*   */     //   1142: invokespecial <init> : (Ljava/lang/Throwable;)V
/*   */     //   1145: athrow
/*   */     //   1146: return
/*   */     //   1147: return
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #1	-> 0
/*   */     //   #2	-> 5
/*   */     //   #3	-> 16
/*   */     //   #4	-> 37
/*   */     //   #8	-> 51
/*   */     //   #14	-> 59
/*   */     //   #17	-> 64
/*   */     //   #18	-> 93
/*   */     //   #19	-> 108
/*   */     //   #21	-> 169
/*   */     //   #22	-> 181
/*   */     //   #23	-> 185
/*   */     //   #24	-> 204
/*   */     //   #25	-> 211
/*   */     //   #28	-> 347
/*   */     //   #29	-> 358
/*   */     //   #30	-> 369
/*   */     //   #31	-> 380
/*   */     //   #32	-> 392
/*   */     //   #33	-> 403
/*   */     //   #35	-> 404
/*   */     //   #36	-> 418
/*   */     //   #37	-> 436
/*   */     //   #43	-> 509
/*   */     //   #44	-> 516
/*   */     //   #45	-> 529
/*   */     //   #46	-> 544
/*   */     //   #52	-> 565
/*   */     //   #53	-> 571
/*   */     //   #63	-> 629
/*   */     //   #65	-> 634
/*   */     //   #67	-> 641
/*   */     //   #68	-> 649
/*   */     //   #69	-> 654
/*   */     //   #70	-> 660
/*   */     //   #71	-> 676
/*   */     //   #75	-> 825
/*   */     //   #76	-> 844
/*   */     //   #77	-> 854
/*   */     //   #78	-> 857
/*   */     //   #79	-> 867
/*   */     //   #80	-> 871
/*   */     //   #81	-> 906
/*   */     //   #82	-> 911
/*   */     //   #88	-> 926
/*   */     //   #93	-> 935
/*   */     //   #126	-> 939
/*   */     //   #127	-> 954
/*   */     //   #134	-> 964
/*   */     //   #136	-> 1027
/*   */     //   #137	-> 1030
/*   */     //   #138	-> 1033
/*   */     //   #139	-> 1046
/*   */     //   #141	-> 1110
/*   */     //   #142	-> 1113
/*   */     //   #143	-> 1116
/*   */     //   #144	-> 1127
/*   */     //   #145	-> 1137
/*   */     // Exception table:
/*   */     //   from	to	target	type
/*   */     //   542	547	1136	java/io/FileNotFoundException
/*   */     //   549	553	1136	java/io/FileNotFoundException
/*   */     //   634	637	922	java/io/IOException
/*   */     //   641	644	922	java/io/IOException
/*   */     //   649	652	922	java/io/IOException
/*   */     //   654	663	922	java/io/IOException
/*   */     //   675	677	922	java/io/IOException
/*   */     //   679	681	922	java/io/IOException
/*   */     //   683	685	922	java/io/IOException
/*   */     //   687	689	922	java/io/IOException
/*   */     //   691	693	922	java/io/IOException
/*   */     //   695	697	922	java/io/IOException
/*   */     //   699	701	922	java/io/IOException
/*   */     //   703	705	922	java/io/IOException
/*   */     //   707	709	922	java/io/IOException
/*   */     //   711	713	922	java/io/IOException
/*   */     //   715	719	922	java/io/IOException
/*   */     //   733	736	922	java/io/IOException
/*   */     //   738	749	922	java/io/IOException
/*   */     //   750	753	922	java/io/IOException
/*   */     //   754	757	922	java/io/IOException
/*   */     //   758	762	922	java/io/IOException
/*   */     //   763	767	922	java/io/IOException
/*   */     //   768	772	922	java/io/IOException
/*   */     //   773	787	922	java/io/IOException
/*   */     //   788	822	922	java/io/IOException
/*   */     //   825	827	922	java/io/IOException
/*   */     //   842	847	922	java/io/IOException
/*   */     //   848	860	922	java/io/IOException
/*   */     //   865	868	922	java/io/IOException
/*   */     //   871	883	922	java/io/IOException
/*   */     //   906	922	922	java/io/IOException
/*   */     //   926	934	922	java/io/IOException
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\normal\base\LJFirmwareUpdater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */