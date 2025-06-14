/*  1 */ package e;public final class b implements BtReceiver.Listener { public final void onBondStateChange(int paramInt) { this.a
/*  2 */       .getClass();
/*  3 */     String str = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     switch (paramInt) {
/*    */       case 12:
/* 12 */         str = "BOND_BONDED"; break;
/* 13 */       case 11: str = "BOND_BONDING";
/*    */         break;
/*    */       case 10:
/* 16 */         str = "BOND_NONE"; break;
/*    */     } 
/* 18 */     h.a("classic onBondStateChange event come! bondState: ".concat(str));
/* 19 */     byte b1 = 2; this.a
/* 20 */       .getClass(); c.a("classic onBondStateChange event come! bondState: ".concat(str), b1); ConditionVariable conditionVariable; if ((paramInt == 10 || paramInt == 12) && (
/*    */       
/* 22 */       conditionVariable = this.a.p) != null)
/* 23 */       open();  }
/*    */ 
/*    */   
/*    */   public b(d paramd) {} }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\e\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */