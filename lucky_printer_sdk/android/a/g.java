/*   1 */ package a;public final class g { public static final int[] d; static { int[] arrayOfInt2; (arrayOfInt2 = new int[29])[0] = 0; arrayOfInt2[1] = 0; arrayOfInt2[2] = 0; arrayOfInt2[3] = 0; arrayOfInt2[4] = 0; arrayOfInt2[5] = 0; arrayOfInt2[6] = 0; arrayOfInt2[7] = 0; arrayOfInt2[8] = 1; arrayOfInt2[9] = 1; arrayOfInt2[10] = 1; arrayOfInt2[11] = 1; arrayOfInt2[12] = 2; arrayOfInt2[13] = 2; arrayOfInt2[14] = 2; arrayOfInt2[15] = 2; arrayOfInt2[16] = 3; arrayOfInt2[17] = 3; arrayOfInt2[18] = 3; arrayOfInt2[19] = 3; arrayOfInt2[20] = 4; arrayOfInt2[21] = 4; arrayOfInt2[22] = 4; arrayOfInt2[23] = 4; arrayOfInt2[24] = 5; arrayOfInt2[25] = 5; arrayOfInt2[26] = 5; arrayOfInt2[27] = 5; arrayOfInt2[28] = 0; d = arrayOfInt2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*   6 */     (arrayOfInt2 = new int[30])[0] = 0; arrayOfInt2[1] = 0; arrayOfInt2[2] = 0; arrayOfInt2[3] = 0; arrayOfInt2[4] = 1; arrayOfInt2[5] = 1; arrayOfInt2[6] = 2; arrayOfInt2[7] = 2; arrayOfInt2[8] = 3; arrayOfInt2[9] = 3; arrayOfInt2[10] = 4; arrayOfInt2[11] = 4; arrayOfInt2[12] = 5; arrayOfInt2[13] = 5; arrayOfInt2[14] = 6; arrayOfInt2[15] = 6; arrayOfInt2[16] = 7; arrayOfInt2[17] = 7; arrayOfInt2[18] = 8; arrayOfInt2[19] = 8; arrayOfInt2[20] = 9; arrayOfInt2[21] = 9; arrayOfInt2[22] = 10; arrayOfInt2[23] = 10; arrayOfInt2[24] = 11; arrayOfInt2[25] = 11; arrayOfInt2[26] = 12; arrayOfInt2[27] = 12; arrayOfInt2[28] = 13; arrayOfInt2[29] = 13; e = arrayOfInt2; }
/*     */    public static final int[] e; public final void a(c paramc) {
/*     */     short[] arrayOfShort1 = this.a;
/*     */     short[] arrayOfShort2 = this.c.a;
/*     */     int j = this.c.d, k = -1;
/*  11 */     paramc.Q = 0; int m; for (paramc
/*  12 */       .R = 573, m = 0; m < j; ) { int n; if (arrayOfShort1[n = m * 2] != 
/*     */ 
/*     */         
/*  15 */         0) { paramc
/*  16 */           .P[++paramc.Q] = m; paramc
/*  17 */           .S[m] = 0; k = m; } else { arrayOfShort1[n + 1] = 0; }
/*     */ 
/*     */       
/*  20 */       m++; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  28 */     while ((m = paramc.Q) < 2)
/*  29 */     { int n, arrayOfInt[] = paramc.P; paramc.Q = ++m; if (k < 2) { n = ++k; } else { n = 0; k = n = k; }  arrayOfInt[m] = k; arrayOfShort1[m = k * 2] = 1;
/*  30 */       paramc
/*  31 */         .S[k] = 0; paramc
/*  32 */         .X--; if (arrayOfShort2 != null) paramc.Y -= arrayOfShort2[m + 1];  k = n; }  this
/*     */ 
/*     */       
/*  35 */       .b = k;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     for (int i = m / 2; i >= 1; ) { paramc
/*  41 */         .a(i, arrayOfShort1); i--; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/*  49 */       arrayOfInt[
/*  50 */           1] = arrayOfInt[paramc.Q--]; paramc
/*  51 */         .a(1, arrayOfShort1);
/*  52 */       int arrayOfInt[], i2 = (arrayOfInt = paramc.P)[1];
/*     */       
/*  54 */       arrayOfInt[paramc.R - 1] = m; paramc
/*  55 */         .R = i1; int i1; paramc.P[i1 = paramc.R - 2] = i2; i1 = j * 2;
/*     */       
/*     */       int i3, i4;
/*  58 */       arrayOfShort1[i1] = (short)(arrayOfShort1[i3 = (m = (arrayOfInt = paramc.P)[1]) * 2] + arrayOfShort1[i4 = i2 * 2]); byte[] arrayOfByte;
/*  59 */       (arrayOfByte = paramc.S)[j] = (byte)(Math.max(arrayOfByte[m], arrayOfByte[i2]) + 1); int n = i3 + 1; m = i4 + 1; i2 = (short)j; arrayOfShort1[m] = i2;
/*  60 */       arrayOfShort1[n] = i2;
/*     */ 
/*     */       
/*  63 */       n = j + 1; paramc.P[1] = j; paramc
/*  64 */         .a(1, arrayOfShort1); if (paramc
/*     */         
/*  66 */         .Q < 2)
/*     */       
/*  68 */       { paramc.R = n = paramc.R - 1; paramc.P[n] = paramc.P[1];
/*  69 */         short[] arrayOfShort6 = this.a;
/*  70 */         short[] arrayOfShort5 = this.c.a;
/*  71 */         int[] arrayOfInt1 = this.c.b;
/*  72 */         i2 = this.c.c; int i6;
/*  73 */         for (i3 = this.c.e, i4 = 0, i6 = 0; i6 <= 15; ) { paramc
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  81 */             .N[i6] = 0; i6++; }  for (arrayOfShort6[paramc
/*     */ 
/*     */ 
/*     */             
/*  85 */             .P[i6 = paramc.R] * 2 + 1] = 0; ++i6 < 573; )
/*     */         { int i7; int i8;
/*     */           int i9;
/*     */           int i10;
/*  89 */           if ((i10 = arrayOfShort6[arrayOfShort6[i9 = (i8 = (i7 = paramc.P[i6]) * 2) + 1] * 2 + 1] + 1) > i3) { i4++; i10 = i3; }  arrayOfShort6[i9] = (short)i10; if (i7 <= this
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  94 */             .b) { paramc
/*     */               
/*  96 */               .N[i10] = (short)(paramc.N[i10] + 1); int i11 = 0;
/*     */             if (i7 >= i2)
/*  98 */               i11 = arrayOfInt1[i7 - i2]; 
/*  99 */             i7 = arrayOfShort6[i8];
/* 100 */             i8 = paramc.X; paramc.X = (i10 + i11) * i7 + i8; if (arrayOfShort5 != null)
/* 101 */             { i8 = paramc.Y; paramc.Y = (arrayOfShort5[i9] + i11) * i7 + i8; }  }  i6++; }  if (i4 != 0) { int i7; short[] arrayOfShort; do { i7 = i3 - 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 109 */             for (; (i2 = (arrayOfShort = paramc.N)[i7]) == 0; i7--); arrayOfShort[i7] = (short)(i2 - 1); arrayOfShort[
/*     */                 
/* 111 */                 i7] = (short)(arrayOfShort[++i7] + 2);
/* 112 */             arrayOfShort[i3] = (short)(arrayOfShort[i3] - 1); } while ((i4 += -2) > 0); while (i3 != 0)
/*     */           
/*     */           { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 120 */             for (i7 = paramc.N[i3]; i7 != 0; )
/*     */             { int i8;
/* 122 */               if ((i2 = paramc.P[i8 = i6 + -1]) > this
/* 123 */                 .b) { i6 = i8; continue; }
/* 124 */                if ((i6 = arrayOfShort6[i4 = (i2 *= 2) + 1]) != i3) {
/* 125 */                 long l = paramc.X; paramc.X = (int)((i3 - i6) * arrayOfShort6[i2] + l); arrayOfShort6[i4] = (short)i3;
/* 126 */               }  i7--; i6 = i8; }  i3--; }  }
/* 127 */          short[] arrayOfShort3 = paramc.N; n = 0; short[] arrayOfShort4; int i5; for ((arrayOfShort4 = paramc.O)[0] = 0, 
/* 128 */           i5 = 1; i5 <= 15; ) { arrayOfShort4[i5] = 
/*     */             
/* 130 */             n = (short)(n + this[i5 - 1] << 1); i5++; }  for (byte b = 0; b <= k; )
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 140 */           if ((i5 = arrayOfShort1[(n = b * 2) + 1]) != 0) { int i7; short s; arrayOfShort4[i5] = 
/*     */ 
/*     */               
/* 143 */               (short)((s = arrayOfShort4[i5]) + 1); i2 = 0; do { i7 = s >>> 1; i2 = (i2 | s & 0x1) << 1; } while (--i5 > 0); arrayOfShort1[n] = (short)(i2 >>> 1); }  b++; }  return; }  j = n;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final int[] f = new int[] { 
/*     */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*     */       0, 0, 0, 0, 0, 0, 2, 3, 7 };
/*     */   public static final byte[] g = new byte[] { 
/*     */       16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 
/*     */       11, 4, 12, 3, 13, 2, 14, 1, 15 };
/*     */   public static final byte[] h;
/*     */   public static final byte[] i;
/*     */   public static final int[] j;
/*     */   public static final int[] k;
/*     */   public short[] a;
/*     */   public int b;
/*     */   public f c;
/*     */   
/*     */   static {
/*     */     byte[] arrayOfByte;
/*     */     (arrayOfByte = new byte[512])[0] = 0;
/*     */     arrayOfByte[1] = 1;
/*     */     arrayOfByte[2] = 2;
/*     */     arrayOfByte[3] = 3;
/*     */     arrayOfByte[4] = 4;
/*     */     arrayOfByte[5] = 4;
/*     */     arrayOfByte[6] = 5;
/*     */     arrayOfByte[7] = 5;
/*     */     arrayOfByte[8] = 6;
/*     */     arrayOfByte[9] = 6;
/*     */     arrayOfByte[10] = 6;
/*     */     arrayOfByte[11] = 6;
/*     */     arrayOfByte[12] = 7;
/*     */     arrayOfByte[13] = 7;
/*     */     arrayOfByte[14] = 7;
/*     */     arrayOfByte[15] = 7;
/*     */     arrayOfByte[16] = 8;
/*     */     arrayOfByte[17] = 8;
/*     */     arrayOfByte[18] = 8;
/*     */     arrayOfByte[19] = 8;
/*     */     arrayOfByte[20] = 8;
/*     */     arrayOfByte[21] = 8;
/*     */     arrayOfByte[22] = 8;
/*     */     arrayOfByte[23] = 8;
/*     */     arrayOfByte[24] = 9;
/*     */     arrayOfByte[25] = 9;
/*     */     arrayOfByte[26] = 9;
/*     */     arrayOfByte[27] = 9;
/*     */     arrayOfByte[28] = 9;
/*     */     arrayOfByte[29] = 9;
/*     */     arrayOfByte[30] = 9;
/*     */     arrayOfByte[31] = 9;
/*     */     arrayOfByte[32] = 10;
/*     */     arrayOfByte[33] = 10;
/*     */     arrayOfByte[34] = 10;
/*     */     arrayOfByte[35] = 10;
/*     */     arrayOfByte[36] = 10;
/*     */     arrayOfByte[37] = 10;
/*     */     arrayOfByte[38] = 10;
/*     */     arrayOfByte[39] = 10;
/*     */     arrayOfByte[40] = 10;
/*     */     arrayOfByte[41] = 10;
/*     */     arrayOfByte[42] = 10;
/*     */     arrayOfByte[43] = 10;
/*     */     arrayOfByte[44] = 10;
/*     */     arrayOfByte[45] = 10;
/*     */     arrayOfByte[46] = 10;
/*     */     arrayOfByte[47] = 10;
/*     */     arrayOfByte[48] = 11;
/*     */     arrayOfByte[49] = 11;
/*     */     arrayOfByte[50] = 11;
/*     */     arrayOfByte[51] = 11;
/*     */     arrayOfByte[52] = 11;
/*     */     arrayOfByte[53] = 11;
/*     */     arrayOfByte[54] = 11;
/*     */     arrayOfByte[55] = 11;
/*     */     arrayOfByte[56] = 11;
/*     */     arrayOfByte[57] = 11;
/*     */     arrayOfByte[58] = 11;
/*     */     arrayOfByte[59] = 11;
/*     */     arrayOfByte[60] = 11;
/*     */     arrayOfByte[61] = 11;
/*     */     arrayOfByte[62] = 11;
/*     */     arrayOfByte[63] = 11;
/*     */     arrayOfByte[64] = 12;
/*     */     arrayOfByte[65] = 12;
/*     */     arrayOfByte[66] = 12;
/*     */     arrayOfByte[67] = 12;
/*     */     arrayOfByte[68] = 12;
/*     */     arrayOfByte[69] = 12;
/*     */     arrayOfByte[70] = 12;
/*     */     arrayOfByte[71] = 12;
/*     */     arrayOfByte[72] = 12;
/*     */     arrayOfByte[73] = 12;
/*     */     arrayOfByte[74] = 12;
/*     */     arrayOfByte[75] = 12;
/*     */     arrayOfByte[76] = 12;
/*     */     arrayOfByte[77] = 12;
/*     */     arrayOfByte[78] = 12;
/*     */     arrayOfByte[79] = 12;
/*     */     arrayOfByte[80] = 12;
/*     */     arrayOfByte[81] = 12;
/*     */     arrayOfByte[82] = 12;
/*     */     arrayOfByte[83] = 12;
/*     */     arrayOfByte[84] = 12;
/*     */     arrayOfByte[85] = 12;
/*     */     arrayOfByte[86] = 12;
/*     */     arrayOfByte[87] = 12;
/*     */     arrayOfByte[88] = 12;
/*     */     arrayOfByte[89] = 12;
/*     */     arrayOfByte[90] = 12;
/*     */     arrayOfByte[91] = 12;
/*     */     arrayOfByte[92] = 12;
/*     */     arrayOfByte[93] = 12;
/*     */     arrayOfByte[94] = 12;
/*     */     arrayOfByte[95] = 12;
/*     */     arrayOfByte[96] = 13;
/*     */     arrayOfByte[97] = 13;
/*     */     arrayOfByte[98] = 13;
/*     */     arrayOfByte[99] = 13;
/*     */     arrayOfByte[100] = 13;
/*     */     arrayOfByte[101] = 13;
/*     */     arrayOfByte[102] = 13;
/*     */     arrayOfByte[103] = 13;
/*     */     arrayOfByte[104] = 13;
/*     */     arrayOfByte[105] = 13;
/*     */     arrayOfByte[106] = 13;
/*     */     arrayOfByte[107] = 13;
/*     */     arrayOfByte[108] = 13;
/*     */     arrayOfByte[109] = 13;
/*     */     arrayOfByte[110] = 13;
/*     */     arrayOfByte[111] = 13;
/*     */     arrayOfByte[112] = 13;
/*     */     arrayOfByte[113] = 13;
/*     */     arrayOfByte[114] = 13;
/*     */     arrayOfByte[115] = 13;
/*     */     arrayOfByte[116] = 13;
/*     */     arrayOfByte[117] = 13;
/*     */     arrayOfByte[118] = 13;
/*     */     arrayOfByte[119] = 13;
/*     */     arrayOfByte[120] = 13;
/*     */     arrayOfByte[121] = 13;
/*     */     arrayOfByte[122] = 13;
/*     */     arrayOfByte[123] = 13;
/*     */     arrayOfByte[124] = 13;
/*     */     arrayOfByte[125] = 13;
/*     */     arrayOfByte[126] = 13;
/*     */     arrayOfByte[127] = 13;
/*     */     arrayOfByte[128] = 14;
/*     */     arrayOfByte[129] = 14;
/*     */     arrayOfByte[130] = 14;
/*     */     arrayOfByte[131] = 14;
/*     */     arrayOfByte[132] = 14;
/*     */     arrayOfByte[133] = 14;
/*     */     arrayOfByte[134] = 14;
/*     */     arrayOfByte[135] = 14;
/*     */     arrayOfByte[136] = 14;
/*     */     arrayOfByte[137] = 14;
/*     */     arrayOfByte[138] = 14;
/*     */     arrayOfByte[139] = 14;
/*     */     arrayOfByte[140] = 14;
/*     */     arrayOfByte[141] = 14;
/*     */     arrayOfByte[142] = 14;
/*     */     arrayOfByte[143] = 14;
/*     */     arrayOfByte[144] = 14;
/*     */     arrayOfByte[145] = 14;
/*     */     arrayOfByte[146] = 14;
/*     */     arrayOfByte[147] = 14;
/*     */     arrayOfByte[148] = 14;
/*     */     arrayOfByte[149] = 14;
/*     */     arrayOfByte[150] = 14;
/*     */     arrayOfByte[151] = 14;
/*     */     arrayOfByte[152] = 14;
/*     */     arrayOfByte[153] = 14;
/*     */     arrayOfByte[154] = 14;
/*     */     arrayOfByte[155] = 14;
/*     */     arrayOfByte[156] = 14;
/*     */     arrayOfByte[157] = 14;
/*     */     arrayOfByte[158] = 14;
/*     */     arrayOfByte[159] = 14;
/*     */     arrayOfByte[160] = 14;
/*     */     arrayOfByte[161] = 14;
/*     */     arrayOfByte[162] = 14;
/*     */     arrayOfByte[163] = 14;
/*     */     arrayOfByte[164] = 14;
/*     */     arrayOfByte[165] = 14;
/*     */     arrayOfByte[166] = 14;
/*     */     arrayOfByte[167] = 14;
/*     */     arrayOfByte[168] = 14;
/*     */     arrayOfByte[169] = 14;
/*     */     arrayOfByte[170] = 14;
/*     */     arrayOfByte[171] = 14;
/*     */     arrayOfByte[172] = 14;
/*     */     arrayOfByte[173] = 14;
/*     */     arrayOfByte[174] = 14;
/*     */     arrayOfByte[175] = 14;
/*     */     arrayOfByte[176] = 14;
/*     */     arrayOfByte[177] = 14;
/*     */     arrayOfByte[178] = 14;
/*     */     arrayOfByte[179] = 14;
/*     */     arrayOfByte[180] = 14;
/*     */     arrayOfByte[181] = 14;
/*     */     arrayOfByte[182] = 14;
/*     */     arrayOfByte[183] = 14;
/*     */     arrayOfByte[184] = 14;
/*     */     arrayOfByte[185] = 14;
/*     */     arrayOfByte[186] = 14;
/*     */     arrayOfByte[187] = 14;
/*     */     arrayOfByte[188] = 14;
/*     */     arrayOfByte[189] = 14;
/*     */     arrayOfByte[190] = 14;
/*     */     arrayOfByte[191] = 14;
/*     */     arrayOfByte[192] = 15;
/*     */     arrayOfByte[193] = 15;
/*     */     arrayOfByte[194] = 15;
/*     */     arrayOfByte[195] = 15;
/*     */     arrayOfByte[196] = 15;
/*     */     arrayOfByte[197] = 15;
/*     */     arrayOfByte[198] = 15;
/*     */     arrayOfByte[199] = 15;
/*     */     arrayOfByte[200] = 15;
/*     */     arrayOfByte[201] = 15;
/*     */     arrayOfByte[202] = 15;
/*     */     arrayOfByte[203] = 15;
/*     */     arrayOfByte[204] = 15;
/*     */     arrayOfByte[205] = 15;
/*     */     arrayOfByte[206] = 15;
/*     */     arrayOfByte[207] = 15;
/*     */     arrayOfByte[208] = 15;
/*     */     arrayOfByte[209] = 15;
/*     */     arrayOfByte[210] = 15;
/*     */     arrayOfByte[211] = 15;
/*     */     arrayOfByte[212] = 15;
/*     */     arrayOfByte[213] = 15;
/*     */     arrayOfByte[214] = 15;
/*     */     arrayOfByte[215] = 15;
/*     */     arrayOfByte[216] = 15;
/*     */     arrayOfByte[217] = 15;
/*     */     arrayOfByte[218] = 15;
/*     */     arrayOfByte[219] = 15;
/*     */     arrayOfByte[220] = 15;
/*     */     arrayOfByte[221] = 15;
/*     */     arrayOfByte[222] = 15;
/*     */     arrayOfByte[223] = 15;
/*     */     arrayOfByte[224] = 15;
/*     */     arrayOfByte[225] = 15;
/*     */     arrayOfByte[226] = 15;
/*     */     arrayOfByte[227] = 15;
/*     */     arrayOfByte[228] = 15;
/*     */     arrayOfByte[229] = 15;
/*     */     arrayOfByte[230] = 15;
/*     */     arrayOfByte[231] = 15;
/*     */     arrayOfByte[232] = 15;
/*     */     arrayOfByte[233] = 15;
/*     */     arrayOfByte[234] = 15;
/*     */     arrayOfByte[235] = 15;
/*     */     arrayOfByte[236] = 15;
/*     */     arrayOfByte[237] = 15;
/*     */     arrayOfByte[238] = 15;
/*     */     arrayOfByte[239] = 15;
/*     */     arrayOfByte[240] = 15;
/*     */     arrayOfByte[241] = 15;
/*     */     arrayOfByte[242] = 15;
/*     */     arrayOfByte[243] = 15;
/*     */     arrayOfByte[244] = 15;
/*     */     arrayOfByte[245] = 15;
/*     */     arrayOfByte[246] = 15;
/*     */     arrayOfByte[247] = 15;
/*     */     arrayOfByte[248] = 15;
/*     */     arrayOfByte[249] = 15;
/*     */     arrayOfByte[250] = 15;
/*     */     arrayOfByte[251] = 15;
/*     */     arrayOfByte[252] = 15;
/*     */     arrayOfByte[253] = 15;
/*     */     arrayOfByte[254] = 15;
/*     */     arrayOfByte[255] = 15;
/*     */     arrayOfByte[256] = 0;
/*     */     arrayOfByte[257] = 0;
/*     */     arrayOfByte[258] = 16;
/*     */     arrayOfByte[259] = 17;
/*     */     arrayOfByte[260] = 18;
/*     */     arrayOfByte[261] = 18;
/*     */     arrayOfByte[262] = 19;
/*     */     arrayOfByte[263] = 19;
/*     */     arrayOfByte[264] = 20;
/*     */     arrayOfByte[265] = 20;
/*     */     arrayOfByte[266] = 20;
/*     */     arrayOfByte[267] = 20;
/*     */     arrayOfByte[268] = 21;
/*     */     arrayOfByte[269] = 21;
/*     */     arrayOfByte[270] = 21;
/*     */     arrayOfByte[271] = 21;
/*     */     arrayOfByte[272] = 22;
/*     */     arrayOfByte[273] = 22;
/*     */     arrayOfByte[274] = 22;
/*     */     arrayOfByte[275] = 22;
/*     */     arrayOfByte[276] = 22;
/*     */     arrayOfByte[277] = 22;
/*     */     arrayOfByte[278] = 22;
/*     */     arrayOfByte[279] = 22;
/*     */     arrayOfByte[280] = 23;
/*     */     arrayOfByte[281] = 23;
/*     */     arrayOfByte[282] = 23;
/*     */     arrayOfByte[283] = 23;
/*     */     arrayOfByte[284] = 23;
/*     */     arrayOfByte[285] = 23;
/*     */     arrayOfByte[286] = 23;
/*     */     arrayOfByte[287] = 23;
/*     */     arrayOfByte[288] = 24;
/*     */     arrayOfByte[289] = 24;
/*     */     arrayOfByte[290] = 24;
/*     */     arrayOfByte[291] = 24;
/*     */     arrayOfByte[292] = 24;
/*     */     arrayOfByte[293] = 24;
/*     */     arrayOfByte[294] = 24;
/*     */     arrayOfByte[295] = 24;
/*     */     arrayOfByte[296] = 24;
/*     */     arrayOfByte[297] = 24;
/*     */     arrayOfByte[298] = 24;
/*     */     arrayOfByte[299] = 24;
/*     */     arrayOfByte[300] = 24;
/*     */     arrayOfByte[301] = 24;
/*     */     arrayOfByte[302] = 24;
/*     */     arrayOfByte[303] = 24;
/*     */     arrayOfByte[304] = 25;
/*     */     arrayOfByte[305] = 25;
/*     */     arrayOfByte[306] = 25;
/*     */     arrayOfByte[307] = 25;
/*     */     arrayOfByte[308] = 25;
/*     */     arrayOfByte[309] = 25;
/*     */     arrayOfByte[310] = 25;
/*     */     arrayOfByte[311] = 25;
/*     */     arrayOfByte[312] = 25;
/*     */     arrayOfByte[313] = 25;
/*     */     arrayOfByte[314] = 25;
/*     */     arrayOfByte[315] = 25;
/*     */     arrayOfByte[316] = 25;
/*     */     arrayOfByte[317] = 25;
/*     */     arrayOfByte[318] = 25;
/*     */     arrayOfByte[319] = 25;
/*     */     arrayOfByte[320] = 26;
/*     */     arrayOfByte[321] = 26;
/*     */     arrayOfByte[322] = 26;
/*     */     arrayOfByte[323] = 26;
/*     */     arrayOfByte[324] = 26;
/*     */     arrayOfByte[325] = 26;
/*     */     arrayOfByte[326] = 26;
/*     */     arrayOfByte[327] = 26;
/*     */     arrayOfByte[328] = 26;
/*     */     arrayOfByte[329] = 26;
/*     */     arrayOfByte[330] = 26;
/*     */     arrayOfByte[331] = 26;
/*     */     arrayOfByte[332] = 26;
/*     */     arrayOfByte[333] = 26;
/*     */     arrayOfByte[334] = 26;
/*     */     arrayOfByte[335] = 26;
/*     */     arrayOfByte[336] = 26;
/*     */     arrayOfByte[337] = 26;
/*     */     arrayOfByte[338] = 26;
/*     */     arrayOfByte[339] = 26;
/*     */     arrayOfByte[340] = 26;
/*     */     arrayOfByte[341] = 26;
/*     */     arrayOfByte[342] = 26;
/*     */     arrayOfByte[343] = 26;
/*     */     arrayOfByte[344] = 26;
/*     */     arrayOfByte[345] = 26;
/*     */     arrayOfByte[346] = 26;
/*     */     arrayOfByte[347] = 26;
/*     */     arrayOfByte[348] = 26;
/*     */     arrayOfByte[349] = 26;
/*     */     arrayOfByte[350] = 26;
/*     */     arrayOfByte[351] = 26;
/*     */     arrayOfByte[352] = 27;
/*     */     arrayOfByte[353] = 27;
/*     */     arrayOfByte[354] = 27;
/*     */     arrayOfByte[355] = 27;
/*     */     arrayOfByte[356] = 27;
/*     */     arrayOfByte[357] = 27;
/*     */     arrayOfByte[358] = 27;
/*     */     arrayOfByte[359] = 27;
/*     */     arrayOfByte[360] = 27;
/*     */     arrayOfByte[361] = 27;
/*     */     arrayOfByte[362] = 27;
/*     */     arrayOfByte[363] = 27;
/*     */     arrayOfByte[364] = 27;
/*     */     arrayOfByte[365] = 27;
/*     */     arrayOfByte[366] = 27;
/*     */     arrayOfByte[367] = 27;
/*     */     arrayOfByte[368] = 27;
/*     */     arrayOfByte[369] = 27;
/*     */     arrayOfByte[370] = 27;
/*     */     arrayOfByte[371] = 27;
/*     */     arrayOfByte[372] = 27;
/*     */     arrayOfByte[373] = 27;
/*     */     arrayOfByte[374] = 27;
/*     */     arrayOfByte[375] = 27;
/*     */     arrayOfByte[376] = 27;
/*     */     arrayOfByte[377] = 27;
/*     */     arrayOfByte[378] = 27;
/*     */     arrayOfByte[379] = 27;
/*     */     arrayOfByte[380] = 27;
/*     */     arrayOfByte[381] = 27;
/*     */     arrayOfByte[382] = 27;
/*     */     arrayOfByte[383] = 27;
/*     */     arrayOfByte[384] = 28;
/*     */     arrayOfByte[385] = 28;
/*     */     arrayOfByte[386] = 28;
/*     */     arrayOfByte[387] = 28;
/*     */     arrayOfByte[388] = 28;
/*     */     arrayOfByte[389] = 28;
/*     */     arrayOfByte[390] = 28;
/*     */     arrayOfByte[391] = 28;
/*     */     arrayOfByte[392] = 28;
/*     */     arrayOfByte[393] = 28;
/*     */     arrayOfByte[394] = 28;
/*     */     arrayOfByte[395] = 28;
/*     */     arrayOfByte[396] = 28;
/*     */     arrayOfByte[397] = 28;
/*     */     arrayOfByte[398] = 28;
/*     */     arrayOfByte[399] = 28;
/*     */     arrayOfByte[400] = 28;
/*     */     arrayOfByte[401] = 28;
/*     */     arrayOfByte[402] = 28;
/*     */     arrayOfByte[403] = 28;
/*     */     arrayOfByte[404] = 28;
/*     */     arrayOfByte[405] = 28;
/*     */     arrayOfByte[406] = 28;
/*     */     arrayOfByte[407] = 28;
/*     */     arrayOfByte[408] = 28;
/*     */     arrayOfByte[409] = 28;
/*     */     arrayOfByte[410] = 28;
/*     */     arrayOfByte[411] = 28;
/*     */     arrayOfByte[412] = 28;
/*     */     arrayOfByte[413] = 28;
/*     */     arrayOfByte[414] = 28;
/*     */     arrayOfByte[415] = 28;
/*     */     arrayOfByte[416] = 28;
/*     */     arrayOfByte[417] = 28;
/*     */     arrayOfByte[418] = 28;
/*     */     arrayOfByte[419] = 28;
/*     */     arrayOfByte[420] = 28;
/*     */     arrayOfByte[421] = 28;
/*     */     arrayOfByte[422] = 28;
/*     */     arrayOfByte[423] = 28;
/*     */     arrayOfByte[424] = 28;
/*     */     arrayOfByte[425] = 28;
/*     */     arrayOfByte[426] = 28;
/*     */     arrayOfByte[427] = 28;
/*     */     arrayOfByte[428] = 28;
/*     */     arrayOfByte[429] = 28;
/*     */     arrayOfByte[430] = 28;
/*     */     arrayOfByte[431] = 28;
/*     */     arrayOfByte[432] = 28;
/*     */     arrayOfByte[433] = 28;
/*     */     arrayOfByte[434] = 28;
/*     */     arrayOfByte[435] = 28;
/*     */     arrayOfByte[436] = 28;
/*     */     arrayOfByte[437] = 28;
/*     */     arrayOfByte[438] = 28;
/*     */     arrayOfByte[439] = 28;
/*     */     arrayOfByte[440] = 28;
/*     */     arrayOfByte[441] = 28;
/*     */     arrayOfByte[442] = 28;
/*     */     arrayOfByte[443] = 28;
/*     */     arrayOfByte[444] = 28;
/*     */     arrayOfByte[445] = 28;
/*     */     arrayOfByte[446] = 28;
/*     */     arrayOfByte[447] = 28;
/*     */     arrayOfByte[448] = 29;
/*     */     arrayOfByte[449] = 29;
/*     */     arrayOfByte[450] = 29;
/*     */     arrayOfByte[451] = 29;
/*     */     arrayOfByte[452] = 29;
/*     */     arrayOfByte[453] = 29;
/*     */     arrayOfByte[454] = 29;
/*     */     arrayOfByte[455] = 29;
/*     */     arrayOfByte[456] = 29;
/*     */     arrayOfByte[457] = 29;
/*     */     arrayOfByte[458] = 29;
/*     */     arrayOfByte[459] = 29;
/*     */     arrayOfByte[460] = 29;
/*     */     arrayOfByte[461] = 29;
/*     */     arrayOfByte[462] = 29;
/*     */     arrayOfByte[463] = 29;
/*     */     arrayOfByte[464] = 29;
/*     */     arrayOfByte[465] = 29;
/*     */     arrayOfByte[466] = 29;
/*     */     arrayOfByte[467] = 29;
/*     */     arrayOfByte[468] = 29;
/*     */     arrayOfByte[469] = 29;
/*     */     arrayOfByte[470] = 29;
/*     */     arrayOfByte[471] = 29;
/*     */     arrayOfByte[472] = 29;
/*     */     arrayOfByte[473] = 29;
/*     */     arrayOfByte[474] = 29;
/*     */     arrayOfByte[475] = 29;
/*     */     arrayOfByte[476] = 29;
/*     */     arrayOfByte[477] = 29;
/*     */     arrayOfByte[478] = 29;
/*     */     arrayOfByte[479] = 29;
/*     */     arrayOfByte[480] = 29;
/*     */     arrayOfByte[481] = 29;
/*     */     arrayOfByte[482] = 29;
/*     */     arrayOfByte[483] = 29;
/*     */     arrayOfByte[484] = 29;
/*     */     arrayOfByte[485] = 29;
/*     */     arrayOfByte[486] = 29;
/*     */     arrayOfByte[487] = 29;
/*     */     arrayOfByte[488] = 29;
/*     */     arrayOfByte[489] = 29;
/*     */     arrayOfByte[490] = 29;
/*     */     arrayOfByte[491] = 29;
/*     */     arrayOfByte[492] = 29;
/*     */     arrayOfByte[493] = 29;
/*     */     arrayOfByte[494] = 29;
/*     */     arrayOfByte[495] = 29;
/*     */     arrayOfByte[496] = 29;
/*     */     arrayOfByte[497] = 29;
/*     */     arrayOfByte[498] = 29;
/*     */     arrayOfByte[499] = 29;
/*     */     arrayOfByte[500] = 29;
/*     */     arrayOfByte[501] = 29;
/*     */     arrayOfByte[502] = 29;
/*     */     arrayOfByte[503] = 29;
/*     */     arrayOfByte[504] = 29;
/*     */     arrayOfByte[505] = 29;
/*     */     arrayOfByte[506] = 29;
/*     */     arrayOfByte[507] = 29;
/*     */     arrayOfByte[508] = 29;
/*     */     arrayOfByte[509] = 29;
/*     */     arrayOfByte[510] = 29;
/*     */     arrayOfByte[511] = 29;
/*     */     h = arrayOfByte;
/*     */     (arrayOfByte = new byte[256])[0] = 0;
/*     */     arrayOfByte[1] = 1;
/*     */     arrayOfByte[2] = 2;
/*     */     arrayOfByte[3] = 3;
/*     */     arrayOfByte[4] = 4;
/*     */     arrayOfByte[5] = 5;
/*     */     arrayOfByte[6] = 6;
/*     */     arrayOfByte[7] = 7;
/*     */     arrayOfByte[8] = 8;
/*     */     arrayOfByte[9] = 8;
/*     */     arrayOfByte[10] = 9;
/*     */     arrayOfByte[11] = 9;
/*     */     arrayOfByte[12] = 10;
/*     */     arrayOfByte[13] = 10;
/*     */     arrayOfByte[14] = 11;
/*     */     arrayOfByte[15] = 11;
/*     */     arrayOfByte[16] = 12;
/*     */     arrayOfByte[17] = 12;
/*     */     arrayOfByte[18] = 12;
/*     */     arrayOfByte[19] = 12;
/*     */     arrayOfByte[20] = 13;
/*     */     arrayOfByte[21] = 13;
/*     */     arrayOfByte[22] = 13;
/*     */     arrayOfByte[23] = 13;
/*     */     arrayOfByte[24] = 14;
/*     */     arrayOfByte[25] = 14;
/*     */     arrayOfByte[26] = 14;
/*     */     arrayOfByte[27] = 14;
/*     */     arrayOfByte[28] = 15;
/*     */     arrayOfByte[29] = 15;
/*     */     arrayOfByte[30] = 15;
/*     */     arrayOfByte[31] = 15;
/*     */     arrayOfByte[32] = 16;
/*     */     arrayOfByte[33] = 16;
/*     */     arrayOfByte[34] = 16;
/*     */     arrayOfByte[35] = 16;
/*     */     arrayOfByte[36] = 16;
/*     */     arrayOfByte[37] = 16;
/*     */     arrayOfByte[38] = 16;
/*     */     arrayOfByte[39] = 16;
/*     */     arrayOfByte[40] = 17;
/*     */     arrayOfByte[41] = 17;
/*     */     arrayOfByte[42] = 17;
/*     */     arrayOfByte[43] = 17;
/*     */     arrayOfByte[44] = 17;
/*     */     arrayOfByte[45] = 17;
/*     */     arrayOfByte[46] = 17;
/*     */     arrayOfByte[47] = 17;
/*     */     arrayOfByte[48] = 18;
/*     */     arrayOfByte[49] = 18;
/*     */     arrayOfByte[50] = 18;
/*     */     arrayOfByte[51] = 18;
/*     */     arrayOfByte[52] = 18;
/*     */     arrayOfByte[53] = 18;
/*     */     arrayOfByte[54] = 18;
/*     */     arrayOfByte[55] = 18;
/*     */     arrayOfByte[56] = 19;
/*     */     arrayOfByte[57] = 19;
/*     */     arrayOfByte[58] = 19;
/*     */     arrayOfByte[59] = 19;
/*     */     arrayOfByte[60] = 19;
/*     */     arrayOfByte[61] = 19;
/*     */     arrayOfByte[62] = 19;
/*     */     arrayOfByte[63] = 19;
/*     */     arrayOfByte[64] = 20;
/*     */     arrayOfByte[65] = 20;
/*     */     arrayOfByte[66] = 20;
/*     */     arrayOfByte[67] = 20;
/*     */     arrayOfByte[68] = 20;
/*     */     arrayOfByte[69] = 20;
/*     */     arrayOfByte[70] = 20;
/*     */     arrayOfByte[71] = 20;
/*     */     arrayOfByte[72] = 20;
/*     */     arrayOfByte[73] = 20;
/*     */     arrayOfByte[74] = 20;
/*     */     arrayOfByte[75] = 20;
/*     */     arrayOfByte[76] = 20;
/*     */     arrayOfByte[77] = 20;
/*     */     arrayOfByte[78] = 20;
/*     */     arrayOfByte[79] = 20;
/*     */     arrayOfByte[80] = 21;
/*     */     arrayOfByte[81] = 21;
/*     */     arrayOfByte[82] = 21;
/*     */     arrayOfByte[83] = 21;
/*     */     arrayOfByte[84] = 21;
/*     */     arrayOfByte[85] = 21;
/*     */     arrayOfByte[86] = 21;
/*     */     arrayOfByte[87] = 21;
/*     */     arrayOfByte[88] = 21;
/*     */     arrayOfByte[89] = 21;
/*     */     arrayOfByte[90] = 21;
/*     */     arrayOfByte[91] = 21;
/*     */     arrayOfByte[92] = 21;
/*     */     arrayOfByte[93] = 21;
/*     */     arrayOfByte[94] = 21;
/*     */     arrayOfByte[95] = 21;
/*     */     arrayOfByte[96] = 22;
/*     */     arrayOfByte[97] = 22;
/*     */     arrayOfByte[98] = 22;
/*     */     arrayOfByte[99] = 22;
/*     */     arrayOfByte[100] = 22;
/*     */     arrayOfByte[101] = 22;
/*     */     arrayOfByte[102] = 22;
/*     */     arrayOfByte[103] = 22;
/*     */     arrayOfByte[104] = 22;
/*     */     arrayOfByte[105] = 22;
/*     */     arrayOfByte[106] = 22;
/*     */     arrayOfByte[107] = 22;
/*     */     arrayOfByte[108] = 22;
/*     */     arrayOfByte[109] = 22;
/*     */     arrayOfByte[110] = 22;
/*     */     arrayOfByte[111] = 22;
/*     */     arrayOfByte[112] = 23;
/*     */     arrayOfByte[113] = 23;
/*     */     arrayOfByte[114] = 23;
/*     */     arrayOfByte[115] = 23;
/*     */     arrayOfByte[116] = 23;
/*     */     arrayOfByte[117] = 23;
/*     */     arrayOfByte[118] = 23;
/*     */     arrayOfByte[119] = 23;
/*     */     arrayOfByte[120] = 23;
/*     */     arrayOfByte[121] = 23;
/*     */     arrayOfByte[122] = 23;
/*     */     arrayOfByte[123] = 23;
/*     */     arrayOfByte[124] = 23;
/*     */     arrayOfByte[125] = 23;
/*     */     arrayOfByte[126] = 23;
/*     */     arrayOfByte[127] = 23;
/*     */     arrayOfByte[128] = 24;
/*     */     arrayOfByte[129] = 24;
/*     */     arrayOfByte[130] = 24;
/*     */     arrayOfByte[131] = 24;
/*     */     arrayOfByte[132] = 24;
/*     */     arrayOfByte[133] = 24;
/*     */     arrayOfByte[134] = 24;
/*     */     arrayOfByte[135] = 24;
/*     */     arrayOfByte[136] = 24;
/*     */     arrayOfByte[137] = 24;
/*     */     arrayOfByte[138] = 24;
/*     */     arrayOfByte[139] = 24;
/*     */     arrayOfByte[140] = 24;
/*     */     arrayOfByte[141] = 24;
/*     */     arrayOfByte[142] = 24;
/*     */     arrayOfByte[143] = 24;
/*     */     arrayOfByte[144] = 24;
/*     */     arrayOfByte[145] = 24;
/*     */     arrayOfByte[146] = 24;
/*     */     arrayOfByte[147] = 24;
/*     */     arrayOfByte[148] = 24;
/*     */     arrayOfByte[149] = 24;
/*     */     arrayOfByte[150] = 24;
/*     */     arrayOfByte[151] = 24;
/*     */     arrayOfByte[152] = 24;
/*     */     arrayOfByte[153] = 24;
/*     */     arrayOfByte[154] = 24;
/*     */     arrayOfByte[155] = 24;
/*     */     arrayOfByte[156] = 24;
/*     */     arrayOfByte[157] = 24;
/*     */     arrayOfByte[158] = 24;
/*     */     arrayOfByte[159] = 24;
/*     */     arrayOfByte[160] = 25;
/*     */     arrayOfByte[161] = 25;
/*     */     arrayOfByte[162] = 25;
/*     */     arrayOfByte[163] = 25;
/*     */     arrayOfByte[164] = 25;
/*     */     arrayOfByte[165] = 25;
/*     */     arrayOfByte[166] = 25;
/*     */     arrayOfByte[167] = 25;
/*     */     arrayOfByte[168] = 25;
/*     */     arrayOfByte[169] = 25;
/*     */     arrayOfByte[170] = 25;
/*     */     arrayOfByte[171] = 25;
/*     */     arrayOfByte[172] = 25;
/*     */     arrayOfByte[173] = 25;
/*     */     arrayOfByte[174] = 25;
/*     */     arrayOfByte[175] = 25;
/*     */     arrayOfByte[176] = 25;
/*     */     arrayOfByte[177] = 25;
/*     */     arrayOfByte[178] = 25;
/*     */     arrayOfByte[179] = 25;
/*     */     arrayOfByte[180] = 25;
/*     */     arrayOfByte[181] = 25;
/*     */     arrayOfByte[182] = 25;
/*     */     arrayOfByte[183] = 25;
/*     */     arrayOfByte[184] = 25;
/*     */     arrayOfByte[185] = 25;
/*     */     arrayOfByte[186] = 25;
/*     */     arrayOfByte[187] = 25;
/*     */     arrayOfByte[188] = 25;
/*     */     arrayOfByte[189] = 25;
/*     */     arrayOfByte[190] = 25;
/*     */     arrayOfByte[191] = 25;
/*     */     arrayOfByte[192] = 26;
/*     */     arrayOfByte[193] = 26;
/*     */     arrayOfByte[194] = 26;
/*     */     arrayOfByte[195] = 26;
/*     */     arrayOfByte[196] = 26;
/*     */     arrayOfByte[197] = 26;
/*     */     arrayOfByte[198] = 26;
/*     */     arrayOfByte[199] = 26;
/*     */     arrayOfByte[200] = 26;
/*     */     arrayOfByte[201] = 26;
/*     */     arrayOfByte[202] = 26;
/*     */     arrayOfByte[203] = 26;
/*     */     arrayOfByte[204] = 26;
/*     */     arrayOfByte[205] = 26;
/*     */     arrayOfByte[206] = 26;
/*     */     arrayOfByte[207] = 26;
/*     */     arrayOfByte[208] = 26;
/*     */     arrayOfByte[209] = 26;
/*     */     arrayOfByte[210] = 26;
/*     */     arrayOfByte[211] = 26;
/*     */     arrayOfByte[212] = 26;
/*     */     arrayOfByte[213] = 26;
/*     */     arrayOfByte[214] = 26;
/*     */     arrayOfByte[215] = 26;
/*     */     arrayOfByte[216] = 26;
/*     */     arrayOfByte[217] = 26;
/*     */     arrayOfByte[218] = 26;
/*     */     arrayOfByte[219] = 26;
/*     */     arrayOfByte[220] = 26;
/*     */     arrayOfByte[221] = 26;
/*     */     arrayOfByte[222] = 26;
/*     */     arrayOfByte[223] = 26;
/*     */     arrayOfByte[224] = 27;
/*     */     arrayOfByte[225] = 27;
/*     */     arrayOfByte[226] = 27;
/*     */     arrayOfByte[227] = 27;
/*     */     arrayOfByte[228] = 27;
/*     */     arrayOfByte[229] = 27;
/*     */     arrayOfByte[230] = 27;
/*     */     arrayOfByte[231] = 27;
/*     */     arrayOfByte[232] = 27;
/*     */     arrayOfByte[233] = 27;
/*     */     arrayOfByte[234] = 27;
/*     */     arrayOfByte[235] = 27;
/*     */     arrayOfByte[236] = 27;
/*     */     arrayOfByte[237] = 27;
/*     */     arrayOfByte[238] = 27;
/*     */     arrayOfByte[239] = 27;
/*     */     arrayOfByte[240] = 27;
/*     */     arrayOfByte[241] = 27;
/*     */     arrayOfByte[242] = 27;
/*     */     arrayOfByte[243] = 27;
/*     */     arrayOfByte[244] = 27;
/*     */     arrayOfByte[245] = 27;
/*     */     arrayOfByte[246] = 27;
/*     */     arrayOfByte[247] = 27;
/*     */     arrayOfByte[248] = 27;
/*     */     arrayOfByte[249] = 27;
/*     */     arrayOfByte[250] = 27;
/*     */     arrayOfByte[251] = 27;
/*     */     arrayOfByte[252] = 27;
/*     */     arrayOfByte[253] = 27;
/*     */     arrayOfByte[254] = 27;
/*     */     arrayOfByte[255] = 28;
/*     */     i = arrayOfByte;
/*     */     int[] arrayOfInt1;
/*     */     (arrayOfInt1 = new int[29])[0] = 0;
/*     */     arrayOfInt1[1] = 1;
/*     */     arrayOfInt1[2] = 2;
/*     */     arrayOfInt1[3] = 3;
/*     */     arrayOfInt1[4] = 4;
/*     */     arrayOfInt1[5] = 5;
/*     */     arrayOfInt1[6] = 6;
/*     */     arrayOfInt1[7] = 7;
/*     */     arrayOfInt1[8] = 8;
/*     */     arrayOfInt1[9] = 10;
/*     */     arrayOfInt1[10] = 12;
/*     */     arrayOfInt1[11] = 14;
/*     */     arrayOfInt1[12] = 16;
/*     */     arrayOfInt1[13] = 20;
/*     */     arrayOfInt1[14] = 24;
/*     */     arrayOfInt1[15] = 28;
/*     */     arrayOfInt1[16] = 32;
/*     */     arrayOfInt1[17] = 40;
/*     */     arrayOfInt1[18] = 48;
/*     */     arrayOfInt1[19] = 56;
/*     */     arrayOfInt1[20] = 64;
/*     */     arrayOfInt1[21] = 80;
/*     */     arrayOfInt1[22] = 96;
/*     */     arrayOfInt1[23] = 112;
/*     */     arrayOfInt1[24] = 128;
/*     */     arrayOfInt1[25] = 160;
/*     */     arrayOfInt1[26] = 192;
/*     */     arrayOfInt1[27] = 224;
/*     */     arrayOfInt1[28] = 0;
/*     */     j = arrayOfInt1;
/*     */     (arrayOfInt1 = new int[30])[0] = 0;
/*     */     arrayOfInt1[1] = 1;
/*     */     arrayOfInt1[2] = 2;
/*     */     arrayOfInt1[3] = 3;
/*     */     arrayOfInt1[4] = 4;
/*     */     arrayOfInt1[5] = 6;
/*     */     arrayOfInt1[6] = 8;
/*     */     arrayOfInt1[7] = 12;
/*     */     arrayOfInt1[8] = 16;
/*     */     arrayOfInt1[9] = 24;
/*     */     arrayOfInt1[10] = 32;
/*     */     arrayOfInt1[11] = 48;
/*     */     arrayOfInt1[12] = 64;
/*     */     arrayOfInt1[13] = 96;
/*     */     arrayOfInt1[14] = 128;
/*     */     arrayOfInt1[15] = 192;
/*     */     arrayOfInt1[16] = 256;
/*     */     arrayOfInt1[17] = 384;
/*     */     arrayOfInt1[18] = 512;
/*     */     arrayOfInt1[19] = 768;
/*     */     arrayOfInt1[20] = 1024;
/*     */     arrayOfInt1[21] = 1536;
/*     */     arrayOfInt1[22] = 2048;
/*     */     arrayOfInt1[23] = 3072;
/*     */     arrayOfInt1[24] = 4096;
/*     */     arrayOfInt1[25] = 6144;
/*     */     arrayOfInt1[26] = 8192;
/*     */     arrayOfInt1[27] = 12288;
/*     */     arrayOfInt1[28] = 16384;
/*     */     arrayOfInt1[29] = 24576;
/*     */     k = arrayOfInt1;
/*     */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\a\g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */