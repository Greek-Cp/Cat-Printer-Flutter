/*   */ package d;public final class e extends BleNotifyCallback { public final void onNotifyFailure(BleException paramBleException) {
/* 2 */     h.a("notify credit fail! " + paramBleException.getDescription());
/* 3 */     byte b = 2; this.e
/* 4 */       .getClass(); c.a("notify credit fail! " + paramBleException.getDescription(), b);
/*   */   }
/*   */   
/*   */   public e(i parami, TDataWrap paramTDataWrap1, TDataWrap paramTDataWrap2, CountDownLatch paramCountDownLatch, TDataWrap paramTDataWrap3) {}
/*   */   
/*   */   public final void onNotifySuccess() {}
/*   */   
/*   */   public final void onCharacteristicChanged(byte[] paramArrayOfbyte) {
/*   */     // Byte code:
/*   */     //   0: aload_1
/*   */     //   1: ifnonnull -> 5
/*   */     //   4: return
/*   */     //   5: aload_1
/*   */     //   6: arraylength
/*   */     //   7: iconst_2
/*   */     //   8: if_icmpne -> 140
/*   */     //   11: aload_1
/*   */     //   12: iconst_0
/*   */     //   13: baload
/*   */     //   14: iconst_1
/*   */     //   15: if_icmpne -> 140
/*   */     //   18: aload_0
/*   */     //   19: dup
/*   */     //   20: aload_1
/*   */     //   21: iconst_1
/*   */     //   22: baload
/*   */     //   23: sipush #255
/*   */     //   26: iand
/*   */     //   27: istore_1
/*   */     //   28: getfield e : Ld/i;
/*   */     //   31: getfield q : Ljava/util/concurrent/atomic/AtomicInteger;
/*   */     //   34: iload_1
/*   */     //   35: invokevirtual addAndGet : (I)I
/*   */     //   38: pop
/*   */     //   39: ldc 'addCredit:%d, credit:%d'
/*   */     //   41: aload_0
/*   */     //   42: iload_1
/*   */     //   43: invokestatic valueOf : (I)Ljava/lang/Integer;
/*   */     //   46: astore_1
/*   */     //   47: getfield e : Ld/i;
/*   */     //   50: getfield q : Ljava/util/concurrent/atomic/AtomicInteger;
/*   */     //   53: invokevirtual get : ()I
/*   */     //   56: invokestatic valueOf : (I)Ljava/lang/Integer;
/*   */     //   59: astore_2
/*   */     //   60: iconst_2
/*   */     //   61: anewarray java/lang/Object
/*   */     //   64: dup
/*   */     //   65: dup
/*   */     //   66: iconst_0
/*   */     //   67: aload_1
/*   */     //   68: aastore
/*   */     //   69: iconst_1
/*   */     //   70: aload_2
/*   */     //   71: aastore
/*   */     //   72: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
/*   */     //   75: dup
/*   */     //   76: astore_1
/*   */     //   77: invokestatic a : (Ljava/lang/String;)V
/*   */     //   80: getfield a : Lcom/luckprinter/sdk_new/bean/TDataWrap;
/*   */     //   83: invokevirtual getData : ()Ljava/lang/Object;
/*   */     //   86: checkcast java/lang/Boolean
/*   */     //   89: invokevirtual booleanValue : ()Z
/*   */     //   92: ifeq -> 274
/*   */     //   95: aload_0
/*   */     //   96: dup
/*   */     //   97: aload_1
/*   */     //   98: aload_0
/*   */     //   99: dup
/*   */     //   100: getfield a : Lcom/luckprinter/sdk_new/bean/TDataWrap;
/*   */     //   103: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
/*   */     //   106: invokevirtual setData : (Ljava/lang/Object;)V
/*   */     //   109: getfield e : Ld/i;
/*   */     //   112: iconst_2
/*   */     //   113: istore_0
/*   */     //   114: invokevirtual getClass : ()Ljava/lang/Class;
/*   */     //   117: pop
/*   */     //   118: iload_0
/*   */     //   119: invokestatic a : (Ljava/lang/String;I)V
/*   */     //   122: getfield b : Lcom/luckprinter/sdk_new/bean/TDataWrap;
/*   */     //   125: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
/*   */     //   128: invokevirtual setData : (Ljava/lang/Object;)V
/*   */     //   131: getfield c : Ljava/util/concurrent/CountDownLatch;
/*   */     //   134: invokevirtual countDown : ()V
/*   */     //   137: goto -> 274
/*   */     //   140: aload_1
/*   */     //   141: arraylength
/*   */     //   142: iconst_3
/*   */     //   143: if_icmpne -> 274
/*   */     //   146: aload_1
/*   */     //   147: iconst_0
/*   */     //   148: baload
/*   */     //   149: iconst_2
/*   */     //   150: if_icmpne -> 274
/*   */     //   153: aload_1
/*   */     //   154: iconst_2
/*   */     //   155: baload
/*   */     //   156: sipush #255
/*   */     //   159: iand
/*   */     //   160: bipush #8
/*   */     //   162: ishl
/*   */     //   163: aload_1
/*   */     //   164: iconst_1
/*   */     //   165: baload
/*   */     //   166: sipush #255
/*   */     //   169: iand
/*   */     //   170: ior
/*   */     //   171: dup
/*   */     //   172: dup
/*   */     //   173: istore_1
/*   */     //   174: aload_0
/*   */     //   175: getfield e : Ld/i;
/*   */     //   178: dup
/*   */     //   179: astore_2
/*   */     //   180: new d/d
/*   */     //   183: dup
/*   */     //   184: astore_3
/*   */     //   185: aload_0
/*   */     //   186: invokespecial <init> : (Ld/e;)V
/*   */     //   189: invokevirtual getClass : ()Ljava/lang/Class;
/*   */     //   192: pop
/*   */     //   193: invokestatic valueOf : (I)Ljava/lang/Integer;
/*   */     //   196: astore_0
/*   */     //   197: iconst_1
/*   */     //   198: anewarray java/lang/Object
/*   */     //   201: dup
/*   */     //   202: astore #4
/*   */     //   204: iconst_0
/*   */     //   205: aload_0
/*   */     //   206: aastore
/*   */     //   207: ldc 'receive mtu:%d'
/*   */     //   209: aload #4
/*   */     //   211: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
/*   */     //   214: dup
/*   */     //   215: invokestatic a : (Ljava/lang/String;)V
/*   */     //   218: iconst_2
/*   */     //   219: invokestatic a : (Ljava/lang/String;I)V
/*   */     //   222: bipush #21
/*   */     //   224: invokestatic a : (I)V
/*   */     //   227: iconst_1
/*   */     //   228: istore_0
/*   */     //   229: new d/f
/*   */     //   232: dup
/*   */     //   233: astore #4
/*   */     //   235: aload_2
/*   */     //   236: iload_1
/*   */     //   237: aload_3
/*   */     //   238: invokespecial <init> : (Ld/i;ILd/d;)V
/*   */     //   241: bipush #20
/*   */     //   243: if_icmpge -> 249
/*   */     //   246: goto -> 274
/*   */     //   249: invokestatic getInstance : ()Lcom/clj/fastble/BleManager;
/*   */     //   252: aload_2
/*   */     //   253: getfield j : Lcom/clj/fastble/data/BleDevice;
/*   */     //   256: iload_1
/*   */     //   257: iconst_3
/*   */     //   258: iadd
/*   */     //   259: new d/g
/*   */     //   262: dup
/*   */     //   263: aload_2
/*   */     //   264: iload_0
/*   */     //   265: iload_1
/*   */     //   266: aload #4
/*   */     //   268: invokespecial <init> : (Ld/i;IILd/f;)V
/*   */     //   271: invokevirtual setMtu : (Lcom/clj/fastble/data/BleDevice;ILcom/clj/fastble/callback/BleMtuChangedCallback;)V
/*   */     //   274: return
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #1	-> 6
/*   */     //   #2	-> 22
/*   */     //   #3	-> 28
/*   */     //   #4	-> 31
/*   */     //   #5	-> 35
/*   */     //   #6	-> 39
/*   */     //   #7	-> 43
/*   */     //   #8	-> 50
/*   */     //   #9	-> 53
/*   */     //   #10	-> 72
/*   */     //   #12	-> 77
/*   */     //   #13	-> 80
/*   */     //   #14	-> 100
/*   */     //   #15	-> 109
/*   */     //   #16	-> 114
/*   */     //   #17	-> 122
/*   */     //   #18	-> 131
/*   */     //   #20	-> 141
/*   */     //   #22	-> 155
/*   */     //   #24	-> 175
/*   */     //   #25	-> 189
/*   */     //   #26	-> 193
/*   */     //   #27	-> 215
/*   */     //   #28	-> 219
/*   */     //   #30	-> 224
/*   */     //   #31	-> 229
/*   */     //   #32	-> 249
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */