/* 1 */ package f;public abstract class a { public static String a() { (new StringBuilder())
/* 2 */       .append("厂商: " + Build.MANUFACTURER + ",");
/* 3 */     (new StringBuilder()).append("型号: " + Build.MODEL + ",");
/* 4 */     (new StringBuilder()).append("版本: " + Build.VERSION.RELEASE + ",");
/* 5 */     (new StringBuilder()).append("API Level: " + Build.VERSION.SDK_INT);
/* 6 */     return (new StringBuilder()).toString(); }
/*   */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\f\a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */