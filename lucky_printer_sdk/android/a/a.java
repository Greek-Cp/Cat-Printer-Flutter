/* 1 */ package a;public final class a { public long a = 1L; public final void b() { this.a = 1L; this
/* 2 */       .b = 0L; }
/*   */   
/*   */   public final long a() {
/*   */     return this.b << 16L | this.a;
/*   */   }
/*   */   
/* 8 */   public long b = 0L;
/*   */   
/*   */   public final void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
/*   */     // Byte code:
/*   */     //   0: iload_3
/*   */     //   1: iconst_1
/*   */     //   2: if_icmpne -> 43
/*   */     //   5: aload_0
/*   */     //   6: dup
/*   */     //   7: dup
/*   */     //   8: getfield a : J
/*   */     //   11: aload_1
/*   */     //   12: iload_2
/*   */     //   13: baload
/*   */     //   14: sipush #255
/*   */     //   17: iand
/*   */     //   18: i2l
/*   */     //   19: ladd
/*   */     //   20: lstore_1
/*   */     //   21: getfield b : J
/*   */     //   24: lload_1
/*   */     //   25: ladd
/*   */     //   26: aload_0
/*   */     //   27: lload_1
/*   */     //   28: ldc2_w 65521
/*   */     //   31: lrem
/*   */     //   32: putfield a : J
/*   */     //   35: ldc2_w 65521
/*   */     //   38: lrem
/*   */     //   39: putfield b : J
/*   */     //   42: return
/*   */     //   43: iload_3
/*   */     //   44: dup
/*   */     //   45: sipush #5552
/*   */     //   48: idiv
/*   */     //   49: istore_3
/*   */     //   50: sipush #5552
/*   */     //   53: irem
/*   */     //   54: istore #4
/*   */     //   56: iload_3
/*   */     //   57: dup
/*   */     //   58: iconst_m1
/*   */     //   59: iadd
/*   */     //   60: istore_3
/*   */     //   61: ifle -> 142
/*   */     //   64: sipush #5552
/*   */     //   67: istore #5
/*   */     //   69: iload #5
/*   */     //   71: dup
/*   */     //   72: iconst_m1
/*   */     //   73: iadd
/*   */     //   74: istore #5
/*   */     //   76: ifle -> 116
/*   */     //   79: aload_0
/*   */     //   80: dup
/*   */     //   81: dup2
/*   */     //   82: getfield a : J
/*   */     //   85: aload_1
/*   */     //   86: iload_2
/*   */     //   87: dup
/*   */     //   88: iconst_1
/*   */     //   89: iadd
/*   */     //   90: istore_2
/*   */     //   91: baload
/*   */     //   92: sipush #255
/*   */     //   95: iand
/*   */     //   96: i2l
/*   */     //   97: ladd
/*   */     //   98: dup2
/*   */     //   99: lstore #6
/*   */     //   101: putfield a : J
/*   */     //   104: getfield b : J
/*   */     //   107: lload #6
/*   */     //   109: ladd
/*   */     //   110: putfield b : J
/*   */     //   113: goto -> 69
/*   */     //   116: aload_0
/*   */     //   117: dup
/*   */     //   118: dup2
/*   */     //   119: getfield a : J
/*   */     //   122: ldc2_w 65521
/*   */     //   125: lrem
/*   */     //   126: putfield a : J
/*   */     //   129: getfield b : J
/*   */     //   132: ldc2_w 65521
/*   */     //   135: lrem
/*   */     //   136: putfield b : J
/*   */     //   139: goto -> 56
/*   */     //   142: iload #4
/*   */     //   144: dup
/*   */     //   145: iconst_m1
/*   */     //   146: iadd
/*   */     //   147: istore #4
/*   */     //   149: ifle -> 189
/*   */     //   152: aload_0
/*   */     //   153: dup
/*   */     //   154: dup2
/*   */     //   155: getfield a : J
/*   */     //   158: aload_1
/*   */     //   159: iload_2
/*   */     //   160: dup
/*   */     //   161: iconst_1
/*   */     //   162: iadd
/*   */     //   163: istore_2
/*   */     //   164: baload
/*   */     //   165: sipush #255
/*   */     //   168: iand
/*   */     //   169: i2l
/*   */     //   170: ladd
/*   */     //   171: dup2
/*   */     //   172: lstore #5
/*   */     //   174: putfield a : J
/*   */     //   177: getfield b : J
/*   */     //   180: lload #5
/*   */     //   182: ladd
/*   */     //   183: putfield b : J
/*   */     //   186: goto -> 142
/*   */     //   189: aload_0
/*   */     //   190: dup
/*   */     //   191: dup2
/*   */     //   192: getfield a : J
/*   */     //   195: ldc2_w 65521
/*   */     //   198: lrem
/*   */     //   199: putfield a : J
/*   */     //   202: getfield b : J
/*   */     //   205: ldc2_w 65521
/*   */     //   208: lrem
/*   */     //   209: putfield b : J
/*   */     //   212: return
/*   */     // Line number table:
/*   */     //   Java source line number -> byte code offset
/*   */     //   #2	-> 8
/*   */     //   #3	-> 31
/*   */     //   #4	-> 38
/*   */     //   #8	-> 48
/*   */     //   #9	-> 53
/*   */     //   #14	-> 82
/*   */     //   #16	-> 119
/*   */     //   #17	-> 129
/*   */     //   #23	-> 155
/*   */     //   #25	-> 192
/*   */     //   #26	-> 202
/*   */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\a\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */