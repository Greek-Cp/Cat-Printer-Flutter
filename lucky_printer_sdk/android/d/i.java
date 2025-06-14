/*   1 */ package d;public final class i extends c { public static volatile i t; public final Object e; public final Object f; public final AtomicReference g; public final AtomicBoolean h; public final AtomicBoolean i; public BleDevice j; public BluetoothGattService k; public BluetoothGattCharacteristic l; public BluetoothGattCharacteristic m; public BluetoothGattCharacteristic n; public int o; public final AtomicInteger p; public final AtomicInteger q; public final AtomicReference r; public ConditionVariable s; public final boolean f() { this(1); CountDownLatch countDownLatch; TDataWrap tDataWrap; c c1;
/*   2 */     this(Boolean.FALSE);
/*   3 */     this(tDataWrap, this, countDownLatch);
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
/*  30 */     if (this.j != null && this.k != null && this.m != null)
/*  31 */     { String str = this.k.getUuid().toString(); BleManager.getInstance()
/*     */         
/*  33 */         .notify(this.j, str, this.m.getUuid().toString(), c1); }  long l = 5000L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     try { TimeUnit timeUnit = TimeUnit.MILLISECONDS; countDownLatch.await(l, timeUnit); }
/*     */     
/*  40 */     catch (InterruptedException interruptedException) { null.printStackTrace(); }  if (((Boolean)tDataWrap
/*     */       
/*  42 */       .getData()).booleanValue()) {
/*  43 */       c.a("消息回调通道初始化成功", 2);
/*     */     } else {
/*  45 */       c.a("消息回调通道初始化失败", 2);
/*     */     }  boolean bool;
/*  47 */     if (bool = ((Boolean)tDataWrap.getData()).booleanValue())
/*  48 */     { CountDownLatch countDownLatch1; TDataWrap tDataWrap1, tDataWrap2; e e; this(2); Boolean bool1;
/*  49 */       this(bool1 = Boolean.FALSE);
/*  50 */       this(bool1);
/*  51 */       this(Boolean.TRUE);
/*  52 */       this(this, tDataWrap1, tDataWrap, countDownLatch1, tDataWrap2);
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
/* 108 */       if (this.j != null && this.k != null && this.n != null)
/* 109 */       { String str = this.k.getUuid().toString(); BleManager.getInstance()
/*     */           
/* 111 */           .notify(this.j, this, this.n.getUuid().toString(), e); }  long l1 = 15000L;
/*     */ 
/*     */ 
/*     */       
/* 115 */       try { TimeUnit timeUnit = TimeUnit.MILLISECONDS; countDownLatch1.await(l1, this); }
/*     */       
/* 117 */       catch (InterruptedException interruptedException) { null.printStackTrace(); }  if (((Boolean)tDataWrap
/*     */         
/* 119 */         .getData()).booleanValue())
/* 120 */       { c.a(24); }
/*     */       else
/* 122 */       { c.a(25); }  if (((Boolean)tDataWrap
/*     */ 
/*     */         
/* 125 */         .getData()).booleanValue() && ((Boolean)tDataWrap2.getData()).booleanValue()) { boolean bool2 = true, bool3 = bool2; } else { boolean bool2 = false; }  }  return bool; } public final boolean a(String paramString1, String paramString2) { String str2 = "ble connect success, name : "; BluetoothAdapter bluetoothAdapter; if ((bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null) return false;  if (!c.a()) return false;  h.a("ble connect start..."); c.a("设备信息: " + a.a(), 2); this("正在连接ble蓝牙:"); String str1; boolean bool; Object object; stringBuilder.append(paramString1); stringBuilder.append(", mac:"); StringBuilder stringBuilder; (stringBuilder = new StringBuilder()).append(paramString2); c.a((new StringBuilder()).toString(), 2); /* monitor enter BinaryOperatorExpression{ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} = FieldReferenceExpression{lastType=ObjectType{java/lang/Object}, expression=ThisExpression{ObjectType{d/i}}, name=f, descriptor=Ljava/lang/Object;}} */ try { if (c() && !b()) { /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */ return false; }  } finally {} if (bluetoothAdapter.isDiscovering()) bluetoothAdapter.cancelDiscovery();  c.a(18); this.p.addAndGet(1); if (a(paramString2) && this.j != null && this.k != null && this.l != null && this.m != null && this.n != null) { bool = true; } else { bool = false; }  if (bool) bool = f();  if (bool) { str1 = this.j.getName(); String str = this.j.getMac(); this.d = Executors.newSingleThreadExecutor(); OnClientConnectionListener onClientConnectionListener; if ((onClientConnectionListener = this.a) != null) onClientConnectionListener.onLuckConnected(this, str);  c.a(19); (new StringBuilder(str2)).append(paramString1); h.a((new StringBuilder(str2)).toString()); } else { if (super.c()) super.b();  c.a(20); h.a("ble connect fail"); }  /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */ h.a("ble connect end!"); return bool; } public final boolean b() { Object object; if (!c()) return true;  h.a("ble disconnect start..."); c.a(26); /* monitor enter BinaryOperatorExpression{ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} = FieldReferenceExpression{lastType=ObjectType{java/lang/Object}, expression=ThisExpression{ObjectType{d/i}}, name=f, descriptor=Ljava/lang/Object;}} */ try { if (c()) { this.s = new ConditionVariable(); BleManager.getInstance().disconnect(this.j); this.s.block(10000L); try { Thread.sleep(500L); } catch (InterruptedException interruptedException) { null.printStackTrace(); }  }  } finally {} /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */ int j; if ((j = c() ^ true) != 0) { c.a(27); } else { c.a(28); }  h.a("ble disconnect end! isSuccess: " + j); return j; } public final boolean c() { return (this.j != null && BleManager.getInstance().isConnected(this.j)); } public static void a(i parami, BleDevice paramBleDevice, BluetoothGatt paramBluetoothGatt) { parami.j = paramBleDevice; for (Iterator<BluetoothGattService> iterator = paramBluetoothGatt.getServices().iterator(); iterator.hasNext();) { if ((bluetoothGattService = iterator.next()).getUuid().toString().startsWith("0000ff00")) { parami.k = bluetoothGattService; for (iterator = bluetoothGattService.getCharacteristics().iterator(); iterator.hasNext(); ) { String str = bluetoothGattCharacteristic.getUuid().toString(); BluetoothGattCharacteristic bluetoothGattCharacteristic; int j; if (((j = (bluetoothGattCharacteristic = (BluetoothGattCharacteristic)iterator.next()).getProperties()) & 0x4) > 0) parami.l = bluetoothGattCharacteristic;  if ((j & 0x10) > 0) { if (str.startsWith("0000ff01")) { parami.m = bluetoothGattCharacteristic; continue; }  if (str.startsWith("0000ff03")) parami
/*     */                 
/* 127 */                 .n = bluetoothGattCharacteristic;  }  }  break; }  }  if (parami
/* 128 */       .j != null && parami.k != null && parami.l != null && parami.m != null && parami.n != null)
/* 129 */     { h.a("connect info init success");
/* 130 */       c.a("connect info init success", 2); }
/*     */     else
/* 132 */     { boolean bool1; StringBuilder stringBuilder1, stringBuilder2; boolean bool2; this("connect info init fail , ");
/* 133 */       this("btDevice: ");
/* 134 */       if (parami.j != null) { bool2 = true; } else { bool2 = false; }  stringBuilder2.append(bool2).append("\nservice: "); if (parami
/* 135 */         .k != null) { bool2 = true; } else { bool2 = false; }  stringBuilder2.append(bool2).append("\nwriteCharacteristic: "); if (parami
/* 136 */         .l != null) { bool2 = true; } else { bool2 = false; }  stringBuilder2.append(bool2).append("\nnotifyCharacteristic: "); if (parami
/* 137 */         .m != null) { bool2 = true; } else { bool2 = false; }  stringBuilder2.append(bool2).append("\nnotifyCreditCharacteristic: "); if (parami
/* 138 */         .n != null) { bool1 = true; } else { bool1 = false; }  stringBuilder2
/* 139 */         .append(bool1).append("\n");
/*     */ 
/*     */       
/* 142 */       h.a(stringBuilder1.append(stringBuilder2.toString()).toString());
/* 143 */       c.a(stringBuilder1.append(stringBuilder2.toString()).toString(), 2); }  }
/* 144 */   public final void g() { if (this.h.get()) synchronized (this.e) { if (this.h.get()) this.e.notify();  }   } public i() { Object object; this(); this.e = this; this(); this.f = this; super(); this.g = (AtomicReference)this; super(false); this.h = (AtomicBoolean)this; super(false); this.i = (AtomicBoolean)this; this.o = 20; super(0); this.p = (AtomicInteger)this; super(0); this.q = (AtomicInteger)this; super(); this.r = (AtomicReference)this; this.s = null; } public static i e() { if (t == null) synchronized (i.class) { if (t == null) t = new i();  }   return t; } public final boolean a(String paramString) { this(1); CountDownLatch countDownLatch; TDataWrap tDataWrap; this(Boolean.FALSE); BleManager.getInstance().connect(paramString, (BleGattCallback)new a(tDataWrap, this, countDownLatch)); try { (new CountDownLatch()).await(12000L, TimeUnit.MILLISECONDS); } catch (InterruptedException interruptedException) { null.printStackTrace(); }  return ((Boolean)tDataWrap.getData()).booleanValue(); } public final void a(int paramInt, byte[] paramArrayOfbyte) { if (paramArrayOfbyte == null || paramArrayOfbyte.length == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 149 */     int j = paramArrayOfbyte.length, k = 0; h h = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (paramArrayOfbyte.length > 1000) {
/* 156 */       this();
/*     */     }
/*     */ 
/*     */     
/* 160 */     String str1 = this.k.getUuid().toString();
/* 161 */     String str2 = this.l.getUuid().toString(); while (true) { int m; if ((m = j - k) != 0)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 170 */         if (!this.i.get())
/* 171 */         { h.a("writing abort !!! "); } else if (this
/*     */ 
/*     */ 
/*     */           
/* 175 */           .p.get() != paramInt)
/* 176 */         { h.a("session error !!! "); } else { if (this
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 181 */             .q.get() == 0) {
/* 182 */             h.a("waiting credit > 0...");
/* 183 */             for (long l = System.nanoTime(); this
/*     */               
/* 185 */               .i.get() && this
/*     */ 
/*     */ 
/*     */               
/* 189 */               .q.get() <= 0;) {
/*     */ 
/*     */               
/* 192 */               if (System.nanoTime() - l > 30000000000L) {
/*     */                 
/* 194 */                 h.a("wait credit timeout...");
/* 195 */                 this.i.set(false);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 201 */           if (!this.i.get()) {
/* 202 */             h.a("writing abort !!! ");
/*     */           } else {
/*     */             CountDownLatch countDownLatch;
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
/*     */ 
/*     */             
/*     */             b b;
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
/*     */ 
/*     */             
/* 266 */             int n = (int)Math.ceil((m / this.o * 1.0F)); int i1;
/* 267 */             if ((i1 = this.q.get()) >= 3) i1--;  if (n > i1) n = i1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 274 */             if ((i1 = n * this.o) <= m) m = i1;
/*     */ 
/*     */ 
/*     */             
/*     */             byte[] arrayOfByte;
/*     */             
/* 280 */             System.arraycopy(paramArrayOfbyte, k, arrayOfByte = new byte[m], 0, m);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 286 */             this(n);
/*     */             
/* 288 */             this(this, h, countDownLatch); BleManager.getInstance().write(this.j, str1, str2, arrayOfByte, true, b);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 308 */             try { (new CountDownLatch()).await(10000L, TimeUnit.MILLISECONDS); }
/*     */             
/* 310 */             catch (InterruptedException interruptedException) { null.printStackTrace(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 317 */             Integer integer1 = Integer.valueOf(j), integer2 = Integer.valueOf(k += m), integer3 = Integer.valueOf(n), integer4 = Integer.valueOf(this.o); Object[] arrayOfObject2; (arrayOfObject2 = new Object[4])[0] = integer1; (new Object[4])[1] = integer2; (new Object[4])[2] = integer3; (new Object[4])[3] = integer4;
/* 318 */             h.a(String.format("totalDataSize:%d, hasWriteLength:%d, packCount:%d, onePackSize:%d", arrayOfObject2));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 323 */             integer1 = Integer.valueOf(this.q.addAndGet(-n)); Object[] arrayOfObject1; (arrayOfObject1 = new Object[1])[0] = integer1; h.a(String.format("after send credit:%d", arrayOfObject1));
/*     */             continue;
/*     */           }  }
/*     */       
/*     */       }
/*     */       h.a("write content end!");
/*     */       return; }
/*     */      }
/*     */ 
/*     */   
/*     */   public final byte[] a(IReceiveDataClaud paramIReceiveDataClaud, byte[] paramArrayOfbyte, boolean paramBoolean, int paramInt) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield j : Lcom/clj/fastble/data/BleDevice;
/*     */     //   4: ifnull -> 238
/*     */     //   7: aload_0
/*     */     //   8: getfield k : Landroid/bluetooth/BluetoothGattService;
/*     */     //   11: ifnull -> 238
/*     */     //   14: aload_0
/*     */     //   15: getfield l : Landroid/bluetooth/BluetoothGattCharacteristic;
/*     */     //   18: ifnull -> 238
/*     */     //   21: aload_0
/*     */     //   22: invokevirtual c : ()Z
/*     */     //   25: ifne -> 33
/*     */     //   28: aconst_null
/*     */     //   29: astore_0
/*     */     //   30: goto -> 236
/*     */     //   33: aload_2
/*     */     //   34: ifnull -> 28
/*     */     //   37: aload_2
/*     */     //   38: arraylength
/*     */     //   39: ifne -> 45
/*     */     //   42: goto -> 28
/*     */     //   45: ldc2_w 10
/*     */     //   48: invokestatic sleep : (J)V
/*     */     //   51: goto -> 57
/*     */     //   54: invokevirtual printStackTrace : ()V
/*     */     //   57: iload_3
/*     */     //   58: aload_0
/*     */     //   59: getfield e : Ljava/lang/Object;
/*     */     //   62: dup
/*     */     //   63: astore #5
/*     */     //   65: monitorenter
/*     */     //   66: aload_0
/*     */     //   67: getfield h : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   70: iload_3
/*     */     //   71: invokevirtual set : (Z)V
/*     */     //   74: aload_0
/*     */     //   75: getfield i : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   78: iconst_1
/*     */     //   79: invokevirtual set : (Z)V
/*     */     //   82: aload_0
/*     */     //   83: getfield g : Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   86: aconst_null
/*     */     //   87: invokevirtual set : (Ljava/lang/Object;)V
/*     */     //   90: aload_0
/*     */     //   91: getfield r : Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   94: aload_1
/*     */     //   95: invokevirtual set : (Ljava/lang/Object;)V
/*     */     //   98: aload_0
/*     */     //   99: getfield p : Ljava/util/concurrent/atomic/AtomicInteger;
/*     */     //   102: invokevirtual get : ()I
/*     */     //   105: aload_0
/*     */     //   106: swap
/*     */     //   107: aload_2
/*     */     //   108: invokevirtual a : (I[B)V
/*     */     //   111: ifeq -> 172
/*     */     //   114: aload #5
/*     */     //   116: aload_0
/*     */     //   117: getfield e : Ljava/lang/Object;
/*     */     //   120: iload #4
/*     */     //   122: i2l
/*     */     //   123: ldc2_w 1000
/*     */     //   126: lmul
/*     */     //   127: invokevirtual wait : (J)V
/*     */     //   130: aload_0
/*     */     //   131: getfield g : Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   134: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   137: checkcast [B
/*     */     //   140: astore_1
/*     */     //   141: aload_0
/*     */     //   142: getfield h : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   145: iconst_0
/*     */     //   146: invokevirtual set : (Z)V
/*     */     //   149: aload_0
/*     */     //   150: getfield i : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   153: iconst_0
/*     */     //   154: invokevirtual set : (Z)V
/*     */     //   157: monitorexit
/*     */     //   158: aload_1
/*     */     //   159: astore_0
/*     */     //   160: goto -> 236
/*     */     //   163: goto -> 232
/*     */     //   166: goto -> 215
/*     */     //   169: goto -> 191
/*     */     //   172: aload_0
/*     */     //   173: getfield h : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   176: iconst_0
/*     */     //   177: invokevirtual set : (Z)V
/*     */     //   180: aload_0
/*     */     //   181: getfield i : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   184: iconst_0
/*     */     //   185: invokevirtual set : (Z)V
/*     */     //   188: goto -> 209
/*     */     //   191: invokevirtual printStackTrace : ()V
/*     */     //   194: aload_0
/*     */     //   195: getfield h : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   198: iconst_0
/*     */     //   199: invokevirtual set : (Z)V
/*     */     //   202: aload_0
/*     */     //   203: getfield i : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   206: goto -> 184
/*     */     //   209: aload #5
/*     */     //   211: monitorexit
/*     */     //   212: goto -> 28
/*     */     //   215: aload_0
/*     */     //   216: getfield h : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   219: iconst_0
/*     */     //   220: invokevirtual set : (Z)V
/*     */     //   223: aload_0
/*     */     //   224: getfield i : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   227: iconst_0
/*     */     //   228: invokevirtual set : (Z)V
/*     */     //   231: athrow
/*     */     //   232: aload #5
/*     */     //   234: monitorexit
/*     */     //   235: athrow
/*     */     //   236: aload_0
/*     */     //   237: areturn
/*     */     //   238: aconst_null
/*     */     //   239: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #324	-> 1
/*     */     //   #325	-> 22
/*     */     //   #329	-> 38
/*     */     //   #330	-> 48
/*     */     //   #332	-> 54
/*     */     //   #333	-> 59
/*     */     //   #335	-> 67
/*     */     //   #336	-> 75
/*     */     //   #337	-> 83
/*     */     //   #338	-> 91
/*     */     //   #339	-> 99
/*     */     //   #342	-> 117
/*     */     //   #343	-> 131
/*     */     //   #348	-> 142
/*     */     //   #349	-> 150
/*     */     //   #350	-> 173
/*     */     //   #351	-> 181
/*     */     //   #352	-> 191
/*     */     //   #354	-> 195
/*     */     //   #355	-> 203
/*     */     //   #357	-> 211
/*     */     //   #358	-> 216
/*     */     //   #359	-> 224
/*     */     //   #360	-> 231
/*     */     //   #361	-> 234
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   48	54	54	java/lang/InterruptedException
/*     */     //   66	111	169	java/lang/Exception
/*     */     //   66	111	166	finally
/*     */     //   114	120	169	java/lang/Exception
/*     */     //   114	120	166	finally
/*     */     //   127	140	169	java/lang/Exception
/*     */     //   127	140	166	finally
/*     */     //   141	158	163	finally
/*     */     //   172	191	163	finally
/*     */     //   191	194	166	finally
/*     */     //   194	235	163	finally
/*     */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */