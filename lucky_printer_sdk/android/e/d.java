/*   1 */ package e;public final class d extends c { public static final UUID r = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); public static volatile d s; public final AtomicReference e; public final AtomicBoolean f; public final AtomicReference g; public BluetoothSocket h; public DataOutputStream i; public DataInputStream j; public final Object k; public final Object l; public BluetoothDevice m; public BtReceiver n; public BtReceiver o; public ConditionVariable p; public ConditionVariable q; public final void j() { PrinterUtil.cacheExecutor.execute(this::f); } public final void h() { BtReceiver btReceiver1; if ((btReceiver1 = this.n) != null && btReceiver1 != null) { btReceiver1.removeReceiver(PrinterUtil.getAppContext()); this.n = null; }  Context context = PrinterUtil.getAppContext(); this(this); BtReceiver btReceiver2; b b; this(context, b); this.n = btReceiver2; } public final void f() { if (this.j == null) {
/*     */       return;
/*     */     }
/*     */     
/*     */     label50: while (true)
/*   6 */     { byte[] arrayOfByte = new byte[8192];
/*     */ 
/*     */       
/*   9 */       try { byte[] arrayOfByte1 = null; int i; if ((i = this.j.read(arrayOfByte)) > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  19 */           arrayOfByte1 = new byte[i];
/*  20 */           System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, i);
/*  21 */         }  if (c.a(arrayOfByte1)) { int j = -1; BaseDevice baseDevice;
/*  22 */           if ((baseDevice = PrinterHelper.getInstance().getPrinterDevice()) != null)
/*     */           {
/*  24 */             j = baseDevice.getUploadErrorCode(arrayOfByte1); }  if (j >= 0) {
/*  25 */             OnReceiveDeviceStatusListener onReceiveDeviceStatusListener; if ((onReceiveDeviceStatusListener = this.b) != null) onReceiveDeviceStatusListener
/*  26 */                 .onDeviceStatus(j);
/*     */             
/*  28 */             g(); continue;
/*  29 */           }  byte[] arrayOfByte2; (arrayOfByte2 = new byte[8])[0] = -4; (new byte[8])[1] = -1; (new byte[8])[2] = 0; (new byte[8])[3] = 2; (new byte[8])[4] = 69; (new byte[8])[5] = -2; (new byte[8])[6] = 1; (new byte[8])[7] = -69; if (arrayOfByte1 == null || arrayOfByte1.length != 
/*  30 */             8) continue;  byte b = 0; while (b < arrayOfByte1.length) {
/*     */ 
/*     */ 
/*     */             
/*  34 */             if (arrayOfByte1[b] != 
/*  35 */               arrayOfByte2[b]) continue label50;  b++;
/*  36 */           }  OnEventListener onEventListener; if ((onEventListener = this.c) != null) onEventListener
/*  37 */               .onLabelPaperError();  continue; }  if (this
/*  38 */           .f.get()) synchronized (this
/*  39 */             .k) {
/*  40 */             if (this.f.get()) {
/*  41 */               boolean bool = true; IReceiveDataClaud iReceiveDataClaud; if ((iReceiveDataClaud = this.g.get()) != null)
/*     */               {
/*     */                 
/*  44 */                 bool = iReceiveDataClaud.isFilter(arrayOfByte1); }  if (bool) { this
/*     */ 
/*     */                   
/*  47 */                   .e.set(arrayOfByte1);
/*  48 */                 this.k.notify(); } 
/*     */             } 
/*     */           }   }
/*  51 */       catch (IOException iOException)
/*  52 */       { null.printStackTrace();
/*     */         
/*  54 */         h.a("loopRead end. >> " + this); String str;
/*  55 */         c.a(str = null.getLocalizedMessage(), 17); break; }  }  }
/*  56 */   public final void i() { BtReceiver btReceiver1; if ((btReceiver1 = this.o) != null && btReceiver1 != null) { btReceiver1.removeReceiver(PrinterUtil.getAppContext()); this.o = null; }  Context context = PrinterUtil.getAppContext(); this(this); BtReceiver btReceiver2; c c1; this(context, c1); this.o = btReceiver2; } public final boolean a(String paramString1, String paramString2) { Object object; String str1 = "classic connect success, name: ", str2 = "正在连接传统蓝牙:", str3 = "设备信息: "; BluetoothAdapter bluetoothAdapter; if ((bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null) return false;  if (!c.a()) return false;  boolean bool = false; /* monitor enter BinaryOperatorExpression{ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} = FieldReferenceExpression{lastType=ObjectType{java/lang/Object}, expression=ThisExpression{ObjectType{e/d}}, name=l, descriptor=Ljava/lang/Object;}} */ try { h.a("classic connect start..."); byte b = 2; c.a(str3 + a.a(), b); this(str2); stringBuilder.append(paramString1); stringBuilder.append(", mac:"); StringBuilder stringBuilder; (stringBuilder = new StringBuilder()).append(paramString2); c.a((new StringBuilder()).toString(), b); if (bluetoothAdapter.isDiscovering()) bluetoothAdapter.cancelDiscovery();  } finally {} if (c() && !b()) try { return false; } finally { this = null; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */ }   BluetoothDevice bluetoothDevice; if ((bluetoothDevice = bluetoothAdapter.getRemoteDevice(paramString2)) != null) { if (!a(bluetoothDevice)) { h.a("classic connect fail"); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */ return false; }  bool = a(bluetoothDevice, paramString1, paramString2); }  if (bool) { (new StringBuilder(str1)).append(paramString1); h.a((new StringBuilder(str1)).toString()); }
/*     */     else
/*  58 */     { h.a("classic connect fail"); }  /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */ return bool; } public final boolean b() { if (!c()) return true;  h.a("classic disconnect start..."); c.a(13); synchronized (this.l) { if (c()) { this.q = new ConditionVariable(); try { this.h.close(); this.h = null; } catch (IOException iOException) { h.a(null.getLocalizedMessage()); c.a(null.getLocalizedMessage(), 16); }  this.q.block(10000L); }  int i; if ((i = c() ^ true) != 0) { c.a(14); } else { c.a(15); }  h.a("classic disconnect end! result: " + i); return i; }  }
/*     */   public final void g() { if (this.f.get()) synchronized (this.k) { if (this.f.get())
/*     */           this.k.notify();  }   }
/*     */   public final boolean c() { BluetoothSocket bluetoothSocket; return ((bluetoothSocket = this.h) != null && isConnected()); }
/*     */   public d() { AtomicReference atomicReference2; AtomicBoolean atomicBoolean; AtomicReference atomicReference1; Object object; this(); this.e = this; this(false); this.f = this; this(); this.g = this; this(); this.k = this; this(); this.l = this; this.n = null; this.o = null; this.p = null; this.q = null; }
/*     */   public static d e() { if (s == null)
/*     */       synchronized (d.class) { if (s == null)
/*     */           s = new d();  }   return s; }
/*  66 */   public final boolean a(BluetoothDevice paramBluetoothDevice, String paramString1, String paramString2) { BluetoothSocket bluetoothSocket = null; boolean bool = false; 
/*  67 */     try { c.a(8);
/*  68 */       (bluetoothSocket = paramBluetoothDevice.createInsecureRfcommSocketToServiceRecord(r)).connect();
/*  69 */       if (paramBluetoothDevice.createInsecureRfcommSocketToServiceRecord(r).isConnected())
/*  70 */       { i();
/*  71 */         a(paramBluetoothDevice, bluetoothSocket);
/*  72 */         j();
/*  73 */         this.d = Executors.newSingleThreadExecutor(); OnClientConnectionListener onClientConnectionListener;
/*  74 */         if ((onClientConnectionListener = this.a) != null)
/*  75 */           onLuckConnected(paramString1, paramString2);  bool = true; }  } catch (IOException iOException) {  }
/*  76 */     finally {} if (!bluetoothSocket.isConnected())
/*  77 */       a((Closeable)bluetoothSocket);  if (bool)
/*     */     
/*     */     { 
/*  80 */       c.a(9);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       return bool; }  c.a(10); return bool; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(BluetoothDevice paramBluetoothDevice, BluetoothSocket paramBluetoothSocket)
/*     */   {
/*  99 */     this.m = paramBluetoothDevice; this
/* 100 */       .h = paramBluetoothSocket;
/* 101 */     this.j = new DataInputStream(this.h.getInputStream());
/* 102 */     this.i = new DataOutputStream(this.h.getOutputStream()); } public final boolean a(BluetoothDevice paramBluetoothDevice) {
/* 103 */     if (paramBluetoothDevice.getBondState() == 12) return true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     try { h();
/* 109 */       this.p = new ConditionVariable();
/* 110 */       c.a(4);
/* 111 */       if (paramBluetoothDevice.createBond()) { boolean bool1; this
/*     */           
/* 113 */           .p.block(35000L);
/* 114 */         if (paramBluetoothDevice.getBondState() == 12) { bool1 = true; } else { bool1 = false; }
/* 115 */          BtReceiver btReceiver1; if ((btReceiver1 = this.n) != null) { btReceiver1
/* 116 */             .removeReceiver(PrinterUtil.getAppContext());
/* 117 */           this.n = null; }  if (bool1)
/* 118 */         { c.a(6); }
/*     */         else
/* 120 */         { c.a(5); }  return bool1; }  } catch (Exception exception) {  }
/* 121 */     finally {} c.a(3); boolean bool = false; BtReceiver btReceiver;
/* 122 */     if ((btReceiver = this.n) != null) { btReceiver
/* 123 */         .removeReceiver(PrinterUtil.getAppContext());
/* 124 */       this.n = null; }
/* 125 */      c.a(5);
/*     */     return bool;
/*     */   }
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
/*     */   public final byte[] a(IReceiveDataClaud paramIReceiveDataClaud, byte[] paramArrayOfbyte, boolean paramBoolean, int paramInt) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual c : ()Z
/*     */     //   4: ifne -> 9
/*     */     //   7: aconst_null
/*     */     //   8: areturn
/*     */     //   9: aload_0
/*     */     //   10: getfield i : Ljava/io/DataOutputStream;
/*     */     //   13: ifnonnull -> 18
/*     */     //   16: aconst_null
/*     */     //   17: areturn
/*     */     //   18: aload_2
/*     */     //   19: ifnull -> 239
/*     */     //   22: aload_2
/*     */     //   23: arraylength
/*     */     //   24: ifne -> 30
/*     */     //   27: goto -> 239
/*     */     //   30: ldc2_w 10
/*     */     //   33: invokestatic sleep : (J)V
/*     */     //   36: goto -> 42
/*     */     //   39: invokevirtual printStackTrace : ()V
/*     */     //   42: aload_2
/*     */     //   43: aload_0
/*     */     //   44: dup
/*     */     //   45: dup2
/*     */     //   46: getfield k : Ljava/lang/Object;
/*     */     //   49: dup
/*     */     //   50: astore #5
/*     */     //   52: monitorenter
/*     */     //   53: getfield f : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   56: iload_3
/*     */     //   57: invokevirtual set : (Z)V
/*     */     //   60: getfield g : Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   63: aload_1
/*     */     //   64: invokevirtual set : (Ljava/lang/Object;)V
/*     */     //   67: getfield e : Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   70: aconst_null
/*     */     //   71: invokevirtual set : (Ljava/lang/Object;)V
/*     */     //   74: arraylength
/*     */     //   75: istore_1
/*     */     //   76: iconst_0
/*     */     //   77: istore #6
/*     */     //   79: sipush #16384
/*     */     //   82: istore #7
/*     */     //   84: iload #6
/*     */     //   86: iload_1
/*     */     //   87: if_icmpge -> 157
/*     */     //   90: iload_1
/*     */     //   91: iload #6
/*     */     //   93: isub
/*     */     //   94: dup
/*     */     //   95: istore #8
/*     */     //   97: iload #7
/*     */     //   99: if_icmpge -> 105
/*     */     //   102: goto -> 109
/*     */     //   105: iload #7
/*     */     //   107: istore #8
/*     */     //   109: iload #6
/*     */     //   111: iload #8
/*     */     //   113: aload_0
/*     */     //   114: dup
/*     */     //   115: aload_2
/*     */     //   116: iload #6
/*     */     //   118: iload #8
/*     */     //   120: newarray byte
/*     */     //   122: dup
/*     */     //   123: astore #6
/*     */     //   125: iconst_0
/*     */     //   126: iload #8
/*     */     //   128: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*     */     //   131: getfield i : Ljava/io/DataOutputStream;
/*     */     //   134: aload #6
/*     */     //   136: invokevirtual write : ([B)V
/*     */     //   139: getfield i : Ljava/io/DataOutputStream;
/*     */     //   142: invokevirtual flush : ()V
/*     */     //   145: iadd
/*     */     //   146: istore #6
/*     */     //   148: goto -> 84
/*     */     //   151: goto -> 226
/*     */     //   154: goto -> 211
/*     */     //   157: iload_3
/*     */     //   158: ifeq -> 200
/*     */     //   161: aload_0
/*     */     //   162: dup
/*     */     //   163: getfield k : Ljava/lang/Object;
/*     */     //   166: iload #4
/*     */     //   168: sipush #1000
/*     */     //   171: imul
/*     */     //   172: i2l
/*     */     //   173: invokevirtual wait : (J)V
/*     */     //   176: getfield e : Ljava/util/concurrent/atomic/AtomicReference;
/*     */     //   179: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   182: checkcast [B
/*     */     //   185: aload #5
/*     */     //   187: aload_0
/*     */     //   188: getfield f : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   191: iconst_0
/*     */     //   192: invokevirtual set : (Z)V
/*     */     //   195: monitorexit
/*     */     //   196: areturn
/*     */     //   197: goto -> 235
/*     */     //   200: aload_0
/*     */     //   201: getfield f : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   204: iconst_0
/*     */     //   205: invokevirtual set : (Z)V
/*     */     //   208: goto -> 221
/*     */     //   211: invokevirtual printStackTrace : ()V
/*     */     //   214: aload_0
/*     */     //   215: getfield f : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   218: goto -> 204
/*     */     //   221: aconst_null
/*     */     //   222: aload #5
/*     */     //   224: monitorexit
/*     */     //   225: areturn
/*     */     //   226: aload_0
/*     */     //   227: getfield f : Ljava/util/concurrent/atomic/AtomicBoolean;
/*     */     //   230: iconst_0
/*     */     //   231: invokevirtual set : (Z)V
/*     */     //   234: athrow
/*     */     //   235: aload #5
/*     */     //   237: monitorexit
/*     */     //   238: athrow
/*     */     //   239: aconst_null
/*     */     //   240: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #140	-> 1
/*     */     //   #144	-> 10
/*     */     //   #148	-> 23
/*     */     //   #149	-> 33
/*     */     //   #151	-> 39
/*     */     //   #152	-> 46
/*     */     //   #154	-> 53
/*     */     //   #155	-> 60
/*     */     //   #156	-> 67
/*     */     //   #162	-> 74
/*     */     //   #171	-> 120
/*     */     //   #172	-> 128
/*     */     //   #173	-> 131
/*     */     //   #174	-> 139
/*     */     //   #183	-> 163
/*     */     //   #184	-> 176
/*     */     //   #190	-> 188
/*     */     //   #191	-> 211
/*     */     //   #193	-> 215
/*     */     //   #195	-> 224
/*     */     //   #196	-> 227
/*     */     //   #197	-> 234
/*     */     //   #199	-> 237
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   33	39	39	java/lang/InterruptedException
/*     */     //   53	75	154	java/lang/Exception
/*     */     //   53	75	151	finally
/*     */     //   109	122	154	java/lang/Exception
/*     */     //   109	122	151	finally
/*     */     //   125	145	154	java/lang/Exception
/*     */     //   125	145	151	finally
/*     */     //   161	166	154	java/lang/Exception
/*     */     //   161	166	151	finally
/*     */     //   173	185	154	java/lang/Exception
/*     */     //   173	185	151	finally
/*     */     //   185	196	197	finally
/*     */     //   200	211	197	finally
/*     */     //   211	214	151	finally
/*     */     //   214	221	197	finally
/*     */     //   222	225	197	finally
/*     */     //   226	238	197	finally
/*     */   }
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
/*     */   public static void a(Closeable paramCloseable) {
/* 200 */     if (paramCloseable != null) try { paramCloseable.close(); }
/*     */       
/* 202 */       catch (IOException iOException) { null.printStackTrace(); }
/*     */        
/*     */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\e\d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */