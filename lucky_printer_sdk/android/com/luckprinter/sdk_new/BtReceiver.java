/*  1 */ package com.luckprinter.sdk_new;public class BtReceiver extends BroadcastReceiver { public BtReceiver(Context paramContext, Listener paramListener) { IntentFilter intentFilter; this
/*  2 */       .mListener = paramListener;
/*  3 */     this(); (new IntentFilter())
/*  4 */       .addAction("android.bluetooth.adapter.action.STATE_CHANGED");
/*  5 */     (new IntentFilter()).addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
/*  6 */     (new IntentFilter()).addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
/*    */     
/*  8 */     (new IntentFilter()).addAction("android.bluetooth.device.action.FOUND");
/*  9 */     (new IntentFilter()).addAction("android.bluetooth.device.action.PAIRING_REQUEST");
/* 10 */     (new IntentFilter()).addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
/* 11 */     (new IntentFilter()).addAction("android.bluetooth.device.action.ACL_CONNECTED");
/* 12 */     (new IntentFilter()).addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
/*    */     
/* 14 */     (new IntentFilter()).addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
/* 15 */     (new IntentFilter()).addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
/* 16 */     (new IntentFilter()).addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
/* 17 */     if (Build.VERSION.SDK_INT >= 33) { paramContext
/* 18 */         .registerReceiver(this, intentFilter, 2); } else { paramContext
/*    */         
/* 20 */         .registerReceiver(this, intentFilter); }
/*    */      }
/*    */ 
/*    */   
/*    */   private static final String TAG = "BtReceiver";
/*    */   private final Listener mListener;
/*    */   
/*    */   public void onReceive(Context paramContext, Intent paramIntent) {
/*    */     // Byte code:
/*    */     //   0: aload_2
/*    */     //   1: invokevirtual getAction : ()Ljava/lang/String;
/*    */     //   4: dup
/*    */     //   5: astore_1
/*    */     //   6: ifnonnull -> 10
/*    */     //   9: return
/*    */     //   10: aload_1
/*    */     //   11: aload_2
/*    */     //   12: getstatic com/luckprinter/sdk_new/BtReceiver.TAG : Ljava/lang/String;
/*    */     //   15: dup
/*    */     //   16: astore_3
/*    */     //   17: ldc '==='
/*    */     //   19: aload_1
/*    */     //   20: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
/*    */     //   23: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   26: pop
/*    */     //   27: ldc 'android.bluetooth.device.extra.DEVICE'
/*    */     //   29: invokevirtual getParcelableExtra : (Ljava/lang/String;)Landroid/os/Parcelable;
/*    */     //   32: checkcast android/bluetooth/BluetoothDevice
/*    */     //   35: astore #4
/*    */     //   37: iconst_m1
/*    */     //   38: istore #5
/*    */     //   40: invokevirtual hashCode : ()I
/*    */     //   43: lookupswitch default -> 132, -1780914469 -> 301, -1530327060 -> 283, -301431627 -> 265, 6759640 -> 247, 545516589 -> 229, 1123270207 -> 211, 1167529923 -> 192, 1244161670 -> 173, 1821585647 -> 154, 2116862345 -> 135
/*    */     //   132: goto -> 316
/*    */     //   135: aload_1
/*    */     //   136: ldc 'android.bluetooth.device.action.BOND_STATE_CHANGED'
/*    */     //   138: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   141: ifne -> 147
/*    */     //   144: goto -> 316
/*    */     //   147: bipush #9
/*    */     //   149: istore #5
/*    */     //   151: goto -> 316
/*    */     //   154: aload_1
/*    */     //   155: ldc 'android.bluetooth.device.action.ACL_DISCONNECTED'
/*    */     //   157: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   160: ifne -> 166
/*    */     //   163: goto -> 316
/*    */     //   166: bipush #8
/*    */     //   168: istore #5
/*    */     //   170: goto -> 316
/*    */     //   173: aload_1
/*    */     //   174: ldc 'android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED'
/*    */     //   176: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   179: ifne -> 185
/*    */     //   182: goto -> 316
/*    */     //   185: bipush #7
/*    */     //   187: istore #5
/*    */     //   189: goto -> 316
/*    */     //   192: aload_1
/*    */     //   193: ldc 'android.bluetooth.device.action.FOUND'
/*    */     //   195: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   198: ifne -> 204
/*    */     //   201: goto -> 316
/*    */     //   204: bipush #6
/*    */     //   206: istore #5
/*    */     //   208: goto -> 316
/*    */     //   211: aload_1
/*    */     //   212: ldc 'android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED'
/*    */     //   214: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   217: ifne -> 223
/*    */     //   220: goto -> 316
/*    */     //   223: iconst_5
/*    */     //   224: istore #5
/*    */     //   226: goto -> 316
/*    */     //   229: aload_1
/*    */     //   230: ldc 'android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED'
/*    */     //   232: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   235: ifne -> 241
/*    */     //   238: goto -> 316
/*    */     //   241: iconst_4
/*    */     //   242: istore #5
/*    */     //   244: goto -> 316
/*    */     //   247: aload_1
/*    */     //   248: ldc 'android.bluetooth.adapter.action.DISCOVERY_STARTED'
/*    */     //   250: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   253: ifne -> 259
/*    */     //   256: goto -> 316
/*    */     //   259: iconst_3
/*    */     //   260: istore #5
/*    */     //   262: goto -> 316
/*    */     //   265: aload_1
/*    */     //   266: ldc 'android.bluetooth.device.action.ACL_CONNECTED'
/*    */     //   268: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   271: ifne -> 277
/*    */     //   274: goto -> 316
/*    */     //   277: iconst_2
/*    */     //   278: istore #5
/*    */     //   280: goto -> 316
/*    */     //   283: aload_1
/*    */     //   284: ldc 'android.bluetooth.adapter.action.STATE_CHANGED'
/*    */     //   286: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   289: ifne -> 295
/*    */     //   292: goto -> 316
/*    */     //   295: iconst_1
/*    */     //   296: istore #5
/*    */     //   298: goto -> 316
/*    */     //   301: aload_1
/*    */     //   302: ldc 'android.bluetooth.adapter.action.DISCOVERY_FINISHED'
/*    */     //   304: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   307: ifne -> 313
/*    */     //   310: goto -> 316
/*    */     //   313: iconst_0
/*    */     //   314: istore #5
/*    */     //   316: iload #5
/*    */     //   318: tableswitch default -> 372, 0 -> 692, 1 -> 638, 2 -> 618, 3 -> 600, 4 -> 569, 5 -> 538, 6 -> 475, 7 -> 444, 8 -> 424, 9 -> 375
/*    */     //   372: goto -> 707
/*    */     //   375: aload_0
/*    */     //   376: aload_2
/*    */     //   377: ldc 'android.bluetooth.device.extra.BOND_STATE'
/*    */     //   379: iconst_0
/*    */     //   380: invokevirtual getIntExtra : (Ljava/lang/String;I)I
/*    */     //   383: istore_0
/*    */     //   384: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   387: dup
/*    */     //   388: astore_1
/*    */     //   389: ifnull -> 399
/*    */     //   392: aload_1
/*    */     //   393: iload_0
/*    */     //   394: invokeinterface onBondStateChange : (I)V
/*    */     //   399: aload_3
/*    */     //   400: new java/lang/StringBuilder
/*    */     //   403: dup
/*    */     //   404: iload_0
/*    */     //   405: swap
/*    */     //   406: ldc 'BOND_STATE: '
/*    */     //   408: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   411: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*    */     //   414: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   417: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   420: pop
/*    */     //   421: goto -> 707
/*    */     //   424: aload_0
/*    */     //   425: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   428: dup
/*    */     //   429: astore_0
/*    */     //   430: ifnull -> 707
/*    */     //   433: aload_0
/*    */     //   434: aload #4
/*    */     //   436: invokeinterface onDisConnected : (Landroid/bluetooth/BluetoothDevice;)V
/*    */     //   441: goto -> 707
/*    */     //   444: aload_3
/*    */     //   445: new java/lang/StringBuilder
/*    */     //   448: dup
/*    */     //   449: aload_2
/*    */     //   450: swap
/*    */     //   451: ldc 'CONN_STATE: '
/*    */     //   453: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   456: ldc 'android.bluetooth.profile.extra.STATE'
/*    */     //   458: iconst_0
/*    */     //   459: invokevirtual getIntExtra : (Ljava/lang/String;I)I
/*    */     //   462: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*    */     //   465: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   468: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   471: pop
/*    */     //   472: goto -> 707
/*    */     //   475: aload_0
/*    */     //   476: aload_3
/*    */     //   477: aload_2
/*    */     //   478: ldc 'android.bluetooth.device.extra.RSSI'
/*    */     //   480: sipush #32767
/*    */     //   483: invokevirtual getShortExtra : (Ljava/lang/String;S)S
/*    */     //   486: istore_0
/*    */     //   487: new java/lang/StringBuilder
/*    */     //   490: dup
/*    */     //   491: iload_0
/*    */     //   492: swap
/*    */     //   493: ldc 'EXTRA_RSSI:'
/*    */     //   495: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   498: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*    */     //   501: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   504: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   507: pop
/*    */     //   508: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   511: aload #4
/*    */     //   513: dup
/*    */     //   514: dup
/*    */     //   515: invokevirtual getType : ()I
/*    */     //   518: istore_0
/*    */     //   519: invokevirtual getName : ()Ljava/lang/String;
/*    */     //   522: astore_1
/*    */     //   523: invokevirtual getAddress : ()Ljava/lang/String;
/*    */     //   526: astore_2
/*    */     //   527: iload_0
/*    */     //   528: aload_1
/*    */     //   529: aload_2
/*    */     //   530: invokeinterface foundDev : (ILjava/lang/String;Ljava/lang/String;)V
/*    */     //   535: goto -> 707
/*    */     //   538: aload_3
/*    */     //   539: new java/lang/StringBuilder
/*    */     //   542: dup
/*    */     //   543: aload_2
/*    */     //   544: swap
/*    */     //   545: ldc 'CONN_STATE: '
/*    */     //   547: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   550: ldc 'android.bluetooth.adapter.extra.CONNECTION_STATE'
/*    */     //   552: iconst_0
/*    */     //   553: invokevirtual getIntExtra : (Ljava/lang/String;I)I
/*    */     //   556: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*    */     //   559: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   562: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   565: pop
/*    */     //   566: goto -> 707
/*    */     //   569: aload_3
/*    */     //   570: new java/lang/StringBuilder
/*    */     //   573: dup
/*    */     //   574: aload_2
/*    */     //   575: swap
/*    */     //   576: ldc 'CONN_STATE: '
/*    */     //   578: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   581: ldc 'android.bluetooth.profile.extra.STATE'
/*    */     //   583: iconst_0
/*    */     //   584: invokevirtual getIntExtra : (Ljava/lang/String;I)I
/*    */     //   587: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*    */     //   590: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   593: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   596: pop
/*    */     //   597: goto -> 707
/*    */     //   600: aload_0
/*    */     //   601: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   604: dup
/*    */     //   605: astore_0
/*    */     //   606: ifnull -> 707
/*    */     //   609: aload_0
/*    */     //   610: invokeinterface startDiscovery : ()V
/*    */     //   615: goto -> 707
/*    */     //   618: aload_0
/*    */     //   619: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   622: dup
/*    */     //   623: astore_0
/*    */     //   624: ifnull -> 707
/*    */     //   627: aload_0
/*    */     //   628: aload #4
/*    */     //   630: invokeinterface onConnected : (Landroid/bluetooth/BluetoothDevice;)V
/*    */     //   635: goto -> 707
/*    */     //   638: aload_2
/*    */     //   639: ldc 'android.bluetooth.adapter.extra.STATE'
/*    */     //   641: iconst_0
/*    */     //   642: invokevirtual getIntExtra : (Ljava/lang/String;I)I
/*    */     //   645: dup
/*    */     //   646: istore_1
/*    */     //   647: bipush #10
/*    */     //   649: if_icmpne -> 667
/*    */     //   652: aload_0
/*    */     //   653: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   656: dup
/*    */     //   657: astore_0
/*    */     //   658: ifnull -> 667
/*    */     //   661: aload_0
/*    */     //   662: invokeinterface onStateOff : ()V
/*    */     //   667: aload_3
/*    */     //   668: new java/lang/StringBuilder
/*    */     //   671: dup
/*    */     //   672: iload_1
/*    */     //   673: swap
/*    */     //   674: ldc 'STATE: '
/*    */     //   676: invokespecial <init> : (Ljava/lang/String;)V
/*    */     //   679: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*    */     //   682: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   685: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
/*    */     //   688: pop
/*    */     //   689: goto -> 707
/*    */     //   692: aload_0
/*    */     //   693: getfield mListener : Lcom/luckprinter/sdk_new/BtReceiver$Listener;
/*    */     //   696: dup
/*    */     //   697: astore_0
/*    */     //   698: ifnull -> 707
/*    */     //   701: aload_0
/*    */     //   702: invokeinterface finishDiscovery : ()V
/*    */     //   707: return
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #1	-> 1
/*    */     //   #4	-> 12
/*    */     //   #5	-> 27
/*    */     //   #6	-> 40
/*    */     //   #41	-> 377
/*    */     //   #42	-> 384
/*    */     //   #43	-> 394
/*    */     //   #45	-> 400
/*    */     //   #53	-> 425
/*    */     //   #54	-> 436
/*    */     //   #65	-> 445
/*    */     //   #66	-> 478
/*    */     //   #67	-> 487
/*    */     //   #68	-> 508
/*    */     //   #97	-> 539
/*    */     //   #100	-> 570
/*    */     //   #101	-> 601
/*    */     //   #102	-> 610
/*    */     //   #132	-> 619
/*    */     //   #133	-> 630
/*    */     //   #134	-> 639
/*    */     //   #136	-> 653
/*    */     //   #137	-> 662
/*    */     //   #140	-> 668
/*    */     //   #148	-> 693
/*    */     //   #149	-> 702
/*    */   }
/*    */   
/*    */   public void removeReceiver(Context paramContext) {
/*    */     try {
/*    */       paramContext.unregisterReceiver(this);
/*    */     } catch (Exception exception) {
/*    */       null.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static interface Listener {
/*    */     default void foundDev(int param1Int, String param1String1, String param1String2) {}
/*    */     
/*    */     default void startDiscovery() {}
/*    */     
/*    */     default void finishDiscovery() {}
/*    */     
/*    */     default void onConnected(BluetoothDevice param1BluetoothDevice) {}
/*    */     
/*    */     default void onDisConnected(BluetoothDevice param1BluetoothDevice) {}
/*    */     
/*    */     default void onStateOff() {}
/*    */     
/*    */     default void onBondStateChange(int param1Int) {}
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\BtReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */