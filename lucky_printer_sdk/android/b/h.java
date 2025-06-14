/*  1 */ package b;public final class h { public static volatile h b; public static h a() { if (b == null) {
/*  2 */       synchronized (h.class) {
/*  3 */         if (b == null) {
/*  4 */           b = new h();
/*    */         }
/*    */       } 
/*    */     }
/*  8 */     return b; } public ILogFilter a; public static void a(String paramString) { h h1;
/*  9 */     (h1 = a()).getClass();
/*    */     
/* 11 */     Log.d("BtClient", paramString);
/* 12 */     if (a.c && h1.a != null) {
/* 13 */       String str = (new SimpleDateFormat("MM-dd HH:mm:ss")).format(new Date());
/* 14 */       paramString = str + " :: " + paramString;
/* 15 */       h1.a.onLog(paramString);
/*    */     }  }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\b\h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */