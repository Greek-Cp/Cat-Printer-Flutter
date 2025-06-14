/*   1 */ package a;public final class c implements Cloneable { public static final b[] d0; public final d a; public int b; public byte[] c; public int d; public int e; public int f; public int g; public byte h; public int i; public int j; public int k; public byte[] l; public int m; public short[] n; public short[] o; public int p; public int q; public int r; public int s; public int t; public int u; public int v; public int w; public int x; public int y; public int z; public int A; public int B; public int C; public int D; public int E; public int F; public int G; public short[] H; public short[] I; public short[] J; public final g K; public final g L; public final g M; public short[] N; public short[] O; public int[] P; public int Q; public int R; public byte[] S; public byte[] T; public int U; public int V; public int W; public int X; public int Y; public int Z; public short a0; public int b0; public e c0; public final void c() { byte b1; for (b1 = 0; b1 < 'Ğ'; ) { this.H[b1 * 2] = 0; b1++; }  for (b1 = 0; b1 < 30; ) { this
/*   2 */         .I[b1 * 2] = 0; b1++; }  for (b1 = 0; b1 < 19; ) { this
/*   3 */         .J[b1 * 2] = 0; b1++; }  this
/*     */       
/*   5 */       .H[512] = 1;
/*   6 */     this.Y = 0; this.X = 0;
/*   7 */     this.Z = 0; this.V = 0; } public final Object clone() { int i; byte[] arrayOfByte2 = new byte[i = (this = (c)super.clone()).c.length]; System.arraycopy((this = (c)super.clone()).c, 0, arrayOfByte2, 0, i); this.c = arrayOfByte2; this.W = this.W;
/*     */     
/*   9 */     arrayOfByte2 = new byte[i = this.T.length];
/*  10 */     System.arraycopy(this.T, 0, arrayOfByte2, 0, i); this
/*  11 */       .T = arrayOfByte2;
/*     */     
/*  13 */     arrayOfByte2 = new byte[i = this.l.length];
/*  14 */     System.arraycopy(this.l, 0, arrayOfByte2, 0, i); this
/*  15 */       .l = arrayOfByte2;
/*     */     
/*  17 */     this.n = a(this.n);
/*  18 */     this.o = a(this.o);
/*  19 */     this.H = a(this.H);
/*  20 */     this.I = a(this.I);
/*  21 */     this.J = a(this.J);
/*     */     
/*  23 */     this.N = a(this.N);
/*  24 */     this.O = a(this.O);
/*     */     
/*  26 */     int[] arrayOfInt = new int[i = this.P.length];
/*  27 */     System.arraycopy(this.P, 0, arrayOfInt, 0, i); this
/*  28 */       .P = arrayOfInt;
/*     */     
/*  30 */     byte[] arrayOfByte1 = new byte[i = this.S.length];
/*  31 */     System.arraycopy(this.S, 0, arrayOfByte1, 0, i); this
/*  32 */       .S = arrayOfByte1;
/*     */     
/*  34 */     this.K.a = this.H;
/*  35 */     this.L.a = this.I;
/*  36 */     this.M.a = this.J;
/*     */ 
/*     */ 
/*     */     
/*     */     e e1;
/*     */ 
/*     */ 
/*     */     
/*  44 */     if ((e1 = this.c0) != null)
/*  45 */       this.c0 = (e)e1.clone();  return this; } public final void a(int paramInt, short[] paramArrayOfshort) { int i = this.P[paramInt], j = paramInt << 1; int k; while (j <= (k = this.Q)) { int m = arrayOfInt2[j]; byte[] arrayOfByte2 = this.S; int arrayOfInt2[], n, i1; short s2, s3; if (j < k && ((s2 = paramArrayOfshort[(i1 = (arrayOfInt2 = this.P)[n = j + 1]) * 2]) < (s3 = paramArrayOfshort[m * 2]) || (s2 == s3 && arrayOfByte2[i1] <= arrayOfByte2[m])))
/*     */         j = n;  int[] arrayOfInt1; n = (arrayOfInt1 = this.P)[j]; byte[] arrayOfByte1 = this.S; short s1; if ((s1 = paramArrayOfshort[i * 2]) < (s2 = paramArrayOfshort[n * 2]) || (s1 == s2 && arrayOfByte1[i] <= arrayOfByte1[n]))
/*     */         break;  arrayOfInt1[paramInt] = n; paramInt = j << 1; j = paramInt = j; }
/*     */      this.P[paramInt] = i; }
/*     */   public final void b(int paramInt, short[] paramArrayOfshort) { int i = -1, j = 0, k = 7, m = 4; short s; if ((s = paramArrayOfshort[1]) == 0) { k = 138; m = 3; }
/*     */      int n; for (paramArrayOfshort[(paramInt + 1) * 2 + 1] = -1, n = 0; n <= paramInt; ) { short s1 = paramArrayOfshort[++n * 2 + 1]; if (++j >= k || s != s1) { if (j < m) { this.J[i] = (short)(this.J[i = s * 2] + j); }
/*     */         else if (s != 0) { if (s != i)
/*     */             this.J[i] = (short)(this.J[i = s * 2] + 1);  this.J[32] = (short)(this.J[32] + 1); }
/*     */         else if (j <= 10) { this.J[34] = (short)(this.J[34] + 1); }
/*     */         else { this.J[36] = (short)(this.J[36] + 1); }
/*     */          i = 0; if (s1 == 0) { j = 138; m = k = 3; k = j; j = i; i = s; }
/*     */         else if (s == s1) { j = 6; m = k = 3; k = j; j = i; i = s; }
/*     */         else { j = 7; m = k = 4; k = j; j = i; i = s; }
/*     */          }
/*     */        s = s1; }
/*     */      }
/*     */   public final void d(int paramInt, short[] paramArrayOfshort) { short s = -1; int i = 0, j = 7, k = 4; short s1; if ((s1 = paramArrayOfshort[1]) == 0) { j = 138; k = 3; }
/*     */      for (int m = 0; m <= paramInt; ) { short s2 = paramArrayOfshort[++m * 2 + 1]; int n; if ((n = i + 1) < j && s1 == s2) { i = n; }
/*     */       else { if (n < k) { do { c(s1, this.J); }
/*     */           while (--n != 0); }
/*     */         else if (s1 != 0) { if (s1 != s) { c(s1, this.J); }
/*     */           else { i = n; }
/*     */            c(16, this.J); b(i - 3, 2); }
/*     */         else if (n <= 10)
/*     */         { c(17, this.J); b(i + -2, 3); }
/*     */         else
/*     */         { c(18, this.J); b(i + -10, 7); }
/*     */          s = 0; if (s2 == 0) {
/*     */           i = 138; k = j = 3; j = i; i = s; s = s1;
/*     */         } else if (s1 == s2) {
/*     */           i = 6; k = j = 3; j = i; i = s; s = s1;
/*     */         } else {
/*     */           i = 7; k = j = 4; j = i; i = s; s = s1;
/*     */         }  }
/*     */        s1 = s2; }
/*     */      }
/*     */   static { this(0, 0, 0, 0, 0); b b1; (d0 = new b[10])[0] = b1; this(4, 4, 8, 4, 1); (new b[10])[1] = b1; this(4, 5, 16, 8, 1); (new b[10])[2] = b1; this(4, 6, 32, 32, 1); (new b[10])[3] = b1; this(4, 4, 16, 16, 2); (new b[10])[4] = b1; this(8, 16, 32, 32, 2); (new b[10])[5] = b1; this(8, 16, 128, 128, 2); (new b[10])[6] = b1; this(8, 32, 128, 256, 2); (new b[10])[7] = b1; this(32, 128, 258, 1024, 2); (new b[10])[8] = b1; this(32, 258, 258, 4096, 2); (new b[10])[9] = b1; }
/*     */   public final void c(int paramInt, short[] paramArrayOfshort) { int i = paramArrayOfshort[paramInt * 2] & 0xFFFF; b(i, paramArrayOfshort[paramInt * 2 + 1] & 0xFFFF); }
/*     */   public final void a(byte paramByte) { int i; this.f = (i = this.f) + 1; this.c[i] = paramByte; }
/*  84 */   public c(d paramd) { this.g = 1; this.K = new g();
/*  85 */     this.L = new g();
/*  86 */     this.M = new g();
/*     */ 
/*     */     
/*  89 */     this.N = new short[16];
/*     */     
/*  91 */     this.O = new short[16];
/*     */ 
/*     */     
/*  94 */     this.P = new int[573];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 102 */     this.S = new byte[573];
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
/* 146 */     this.c0 = null;
/*     */ 
/*     */     
/* 149 */     this.a = paramd;
/* 150 */     this.H = new short[1146];
/* 151 */     this.I = new short[122];
/* 152 */     this.J = new short[78]; }
/* 153 */   public final boolean a(int paramInt1, int paramInt2) { int i = this.W; int j; i = (j = this.V) * 2 + i; this.c[i] = (byte)(paramInt1 >>> 8); this.c[++i] = (byte)paramInt1; this.T[j] = (byte)paramInt2; this.V = i = j + 1; if (paramInt1 == 0) { this.H[paramInt1] = (short)(this.H[paramInt1 = paramInt2 * 2] + 1); } else { this.Z++; this.H[paramInt2] = (short)(this.H[paramInt2 = (g.i[paramInt2] + 257) * 2] + 1); short[] arrayOfShort = this.I; paramInt1 = ((--paramInt1 < 256) ? g.h[paramInt1] : g.h[(paramInt1 >>> 7) + 256]) * 2; arrayOfShort[paramInt1] = (short)(arrayOfShort[paramInt1] + 1); }  if ((i & 0x1FFF) == 0 && this.D > 2) { paramInt1 = i * 8; for (paramInt2 = this.x - this.t, i = 0; i < 30; ) { long l1 = paramInt1; long l2 = this.I[i * 2]; paramInt1 = (int)((g.e[i] + 5L) * l2 + l1); i++; }  paramInt1 >>>= 3; if (this.Z < this.V / 2 && paramInt1 < paramInt2 / 2) return true;  }  return (this.V == this.U - 1); } public final void b(int paramInt) { a((byte)paramInt); a((byte)(paramInt >>> 8)); } public final void a(boolean paramBoolean) { int j, m, n, i; if ((i = this.t) >= 0) { j = i; } else { j = -1; }  i = this.x - i; int k = 0; if (this.D > 0) { if (this.h == 2) { int i1; int i2; for (k = 0, i1 = 0, i2 = 0; k < 7; ) { i2 += this.H[k * 2]; k++; }  while (k < 128) { i1 += this.H[k * 2]; k++; }  while (k < 256) { i2 += this.H[k * 2]; k++; }  this.h = (byte)((i2 > i1 >>> 2) ? 0 : 1); }  this.K.a(this); this.L.a(this); short[] arrayOfShort = this.H; b(this.K.b, arrayOfShort); arrayOfShort = this.I; b(this.L.b, arrayOfShort); this.M.a(this); byte b1; for (b1 = 18; b1 >= 3 && this.J[g.g[b1] * 2 + 1] == 0;) b1--;  m = this.X; m = (this.X = (b1 + 1) * 3 + 14 + m) + 10 >>> 3; if ((n = this.Y + 10 >>> 3) <= m) m = n;  } else { m = n = i + 5; }  if (i + 4 <= m && j != -1) { b(paramBoolean, 3); if ((k = this.b0) > 8) { b(this.a0); } else if (k > 0) { a((byte)this.a0); }  this.a0 = 0; this.b0 = 0; b((short)i); b((short)(i ^ 0xFFFFFFFF)); System.arraycopy(this.l, j, this.c, this.f, i); this.f += i; } else if (n == m) { b(paramBoolean + 2, 3); short[] arrayOfShort = f.g; a(f.f, arrayOfShort); } else { b(paramBoolean + 4, 3); j = this.L.b; m = k + 1; b((i = this.K.b) + -256, 5); b(j, 5); b(k + -3, 4); for (k = 0; k < m; ) { b(this.J[g.g[k] * 2 + 1], 3); k++; }  short[] arrayOfShort2 = this.H; d(i, arrayOfShort2); short[] arrayOfShort1 = this.I; d(j, arrayOfShort1); arrayOfShort1 = this.I; a(this.H, arrayOfShort1); }  c(); if (paramBoolean) {
/* 154 */       int i1; if ((i1 = this.b0) > 8)
/* 155 */       { b(this.a0); } else if (i1 > 0)
/*     */       
/* 157 */       { a((byte)this.a0); }  this
/*     */         
/* 159 */         .a0 = 0; this
/* 160 */         .b0 = 0;
/* 161 */     }  this.t = this.x; this
/* 162 */       .a.c(); }
/*     */   public final void b(int paramInt1, int paramInt2) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield b0 : I
/*     */     //   4: dup
/*     */     //   5: istore_3
/*     */     //   6: bipush #16
/*     */     //   8: iload_2
/*     */     //   9: isub
/*     */     //   10: if_icmple -> 64
/*     */     //   13: aload_0
/*     */     //   14: iload_2
/*     */     //   15: aload_0
/*     */     //   16: iload_1
/*     */     //   17: aload_0
/*     */     //   18: dup
/*     */     //   19: dup2
/*     */     //   20: getfield a0 : S
/*     */     //   23: iload_1
/*     */     //   24: iload_3
/*     */     //   25: ishl
/*     */     //   26: ldc 65535
/*     */     //   28: iand
/*     */     //   29: ior
/*     */     //   30: i2s
/*     */     //   31: dup
/*     */     //   32: istore_0
/*     */     //   33: putfield a0 : S
/*     */     //   36: iload_0
/*     */     //   37: invokevirtual b : (I)V
/*     */     //   40: getfield b0 : I
/*     */     //   43: istore_0
/*     */     //   44: bipush #16
/*     */     //   46: iload_0
/*     */     //   47: isub
/*     */     //   48: iushr
/*     */     //   49: i2s
/*     */     //   50: putfield a0 : S
/*     */     //   53: bipush #16
/*     */     //   55: isub
/*     */     //   56: iload_0
/*     */     //   57: iadd
/*     */     //   58: putfield b0 : I
/*     */     //   61: goto -> 87
/*     */     //   64: aload_0
/*     */     //   65: iload_3
/*     */     //   66: iload_2
/*     */     //   67: aload_0
/*     */     //   68: dup
/*     */     //   69: getfield a0 : S
/*     */     //   72: iload_1
/*     */     //   73: iload_3
/*     */     //   74: ishl
/*     */     //   75: ldc 65535
/*     */     //   77: iand
/*     */     //   78: ior
/*     */     //   79: i2s
/*     */     //   80: putfield a0 : S
/*     */     //   83: iadd
/*     */     //   84: putfield b0 : I
/*     */     //   87: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #28	-> 1
/*     */     //   #31	-> 20
/*     */     //   #32	-> 37
/*     */     //   #33	-> 40
/*     */     //   #34	-> 58
/*     */     //   #37	-> 69
/* 163 */     //   #38	-> 84 } public final synchronized e b() { if (this.c0 == null) { e e1; this(); this.c0 = e1; }  return this.c0; } public final void a(short[] paramArrayOfshort1, short[] paramArrayOfshort2) { byte b1 = 0; if (this.V != 0) do { int j = this.W; j = this.T[b1] & 0xFF; b1++; byte[] arrayOfByte; int i; if ((i = (arrayOfByte = this.c)[j = b1 * 2 + j] << 8 & 0xFF00 | arrayOfByte[j + 1] & 0xFF) == 0) { c(j, paramArrayOfshort1); } else { byte b2; c((b2 = g.i[j]) + 257, paramArrayOfshort1); int m; if ((m = g.d[b2]) != 0) b(j - g.j[b2], m);  if (--i < 256) { j = g.h[i]; } else { j = g.h[(i >>> 7) + 256]; }  c(j, paramArrayOfshort2); int k; if ((k = g.e[j]) != 0) b(i - g.k[j], k);  }  } while (b1 < this.V);  c(256, paramArrayOfshort1); paramArrayOfshort1[513]; } public final void a() { int i; do { int j, k, m; if ((m = this.m - (j = this.z) - (k = this.x)) == 0 && k == 0 && j == 0)
/*     */       
/*     */       { 
/*     */         
/* 167 */         m = this.i; } else if (m == -1) { m--; } else if (k >= this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 177 */         .i + (j = this.i) - 262)
/* 178 */       { System.arraycopy(this.l, j, this.l, 0, j); this
/* 179 */           .y -= j = this.i; this
/* 180 */           .x -= j; this
/* 181 */           .t -= j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 189 */         k = j = this.q; while (true) {
/*     */           short[] arrayOfShort; int i1;
/*     */           int i2;
/* 192 */           if ((i1 = (arrayOfShort = this.o)[--j] & 0xFFFF) >= (
/* 193 */             i2 = this.i)) { i1 = (short)(i1 - i2); } else { i1 = 0; }  arrayOfShort[j] = i1; if (--k == 0) { j = i2;
/*     */             
/*     */             while (true) {
/*     */               short[] arrayOfShort1;
/*     */               
/*     */               int i3;
/*     */               
/* 200 */               if ((i3 = (arrayOfShort1 = this.n)[--i2] & 0xFFFF) >= (
/* 201 */                 i1 = this.i)) { i3 = (short)(i3 - i1); } else { i3 = 0; }  arrayOfShort1[i2] = i3; if (--j == 0) { m += i1; break; }
/*     */             
/*     */             } 
/*     */             break; }
/*     */         
/*     */         }  }
/*     */       
/*     */       d d1;
/* 209 */       if ((k = (d1 = this.a).c) == 0) {
/*     */         return;
/*     */       }
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
/* 222 */       byte[] arrayOfByte2 = this.l; int n = this.x + this.z; if (k <= m) m = k;  if (m == 0) { m = 0; } else { d1
/* 223 */           .c = k - m;
/*     */         
/* 225 */         if (d1.i.g != 0) {
/* 226 */           byte[] arrayOfByte = d1.a; d1.j.a(arrayOfByte, d1.b, m);
/*     */         } 
/* 228 */         System.arraycopy(d1.a, d1.b, arrayOfByte2, n, m);
/* 229 */         d1.b += m;
/* 230 */         d1.d += m; }
/* 231 */        this.z = i; if ((i = this.z + m) < 3) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       byte[] arrayOfByte1;
/* 236 */       this.p = (((arrayOfByte1 = this.l)[m = this.x] & 0xFF) << this.s ^ arrayOfByte1[m + 1] & 0xFF) & this.r; } while (i < 262 && 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 241 */       this.a.c != 0); } public final int a(int paramInt) {
/* 242 */     int j = this.B;
/*     */ 
/*     */ 
/*     */     
/* 246 */     int m = this.A; int k, n;
/* 247 */     if ((k = this.x) > (n = this.i) - 262) { n = k - n - 262; } else { n = 0; }
/*     */     
/* 249 */     int i1 = this.G;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     int i2 = this.k, i3 = k + 258;
/*     */     
/*     */     int i4;
/* 257 */     byte b1 = this.l[(i4 = k + m) - 1];
/* 258 */     i4 = this.l[i4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     if (m >= this.F) j >>= 2;  int i5; if (i1 > (
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 270 */       i5 = this.z)) { i1 = i5; i5 = k; } else { i5 = k; }
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       byte[] arrayOfByte;
/*     */       
/*     */       int i6;
/* 277 */       if ((arrayOfByte = this.l)[i6 = paramInt + m] == i4 && arrayOfByte[i6 - 1] == b1 && arrayOfByte[paramInt] == arrayOfByte[i5] && arrayOfByte[paramInt + 1] == arrayOfByte[i5 + 1]) { int i7; byte[] arrayOfByte1; int i8; for (i5 += 2, i7 = paramInt + 2; (
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
/* 292 */           arrayOfByte1 = this.l)[i8 = i5 + 1] == arrayOfByte1[i7 + 1] && arrayOfByte1[i8 = i5 + 2] == arrayOfByte1[i7 + 2] && arrayOfByte1[i8 = i5 + 3] == arrayOfByte1[i7 + 3] && arrayOfByte1[i8 = i5 + 4] == arrayOfByte1[i7 + 4] && arrayOfByte1[i8 = i5 + 5] == arrayOfByte1[i7 + 5] && arrayOfByte1[i8 = i5 + 6] == arrayOfByte1[i7 + 6] && arrayOfByte1[i8 = i5 + 7] == arrayOfByte1[i7 + 7] && arrayOfByte1[i8 = i5 + 8] == arrayOfByte1[i7 += 8] && i8 < i3;) i5 = i8;  if ((i5 = 258 - i3 - i8) > m) { this
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
/* 306 */             .y = paramInt; if (i5 >= i1) {
/*     */             break;
/*     */           }
/* 309 */           i4 = arrayOfByte1[(m = k + i5) - 1];
/* 310 */           int i9 = i4 = m = arrayOfByte1[m]; m = i5; i5 = k; } else { i5 = k; }
/*     */          }
/*     */       
/* 313 */       if ((paramInt = this.n[paramInt & i2] & 0xFFFF) <= n || --j == 0) { i5 = m; break; }
/*     */     
/*     */     }  int i;
/* 316 */     return (i5 <= (i = this.z)) ? i5 : i;
/*     */   } public static short[] a(short[] paramArrayOfshort) {
/* 318 */     System.arraycopy(paramArrayOfshort, 0, arrayOfShort, 0, i);
/*     */     int i;
/*     */     short[] arrayOfShort;
/*     */     return arrayOfShort = new short[i = paramArrayOfshort.length];
/*     */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\a\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */