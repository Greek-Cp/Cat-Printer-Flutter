/*  1 */ package d;public final class c extends BleNotifyCallback { public final void onNotifySuccess() { this.a.setData(Boolean.TRUE);
/*  2 */     h.a("notify success ");
/*  3 */     this.b.countDown(); } public c(TDataWrap paramTDataWrap, i parami, CountDownLatch paramCountDownLatch) {} public final void onNotifyFailure(BleException paramBleException) { h.a("notify fail! " + paramBleException.getDescription()); byte b = 2; this.c
/*  4 */       .getClass(); c.c.a("notify fail! " + paramBleException.getDescription(), b);
/*  5 */     this.b.countDown(); } public final void onCharacteristicChanged(byte[] paramArrayOfbyte) { OnEventListener onEventListener; OnReceiveDeviceStatusListener onReceiveDeviceStatusListener; i i1; (i1 = this.c).getClass(); if (c.c.a(paramArrayOfbyte)) { int j = -1; BaseDevice baseDevice; if ((baseDevice = PrinterHelper.getInstance().getPrinterDevice()) != null)
/*  6 */         j = baseDevice.getUploadErrorCode(paramArrayOfbyte);  if (j >= 0)
/*  7 */       { if ((onReceiveDeviceStatusListener = super.b) != null) onReceiveDeviceStatusListener
/*  8 */             .onDeviceStatus(j);
/*    */         
/* 10 */         g(); }
/* 11 */       else { byte[] arrayOfByte; (arrayOfByte = new byte[8])[0] = -4; (new byte[8])[1] = -1; (new byte[8])[2] = 0; (new byte[8])[3] = 2; (new byte[8])[4] = 69; (new byte[8])[5] = -2; (new byte[8])[6] = 1; (new byte[8])[7] = -69; if (onReceiveDeviceStatusListener != null && onReceiveDeviceStatusListener.length == 
/* 12 */           8) { byte b = 0; while (b < onReceiveDeviceStatusListener.length) {
/*    */ 
/*    */ 
/*    */             
/* 16 */             if (onReceiveDeviceStatusListener[b] != 
/* 17 */               arrayOfByte[b]) // Byte code: goto -> 251  b++;
/* 18 */           }  if ((onEventListener = super.c) != null)
/* 19 */             onLabelPaperError();  }  }  } else if (((i)this)
/* 20 */       .h.get()) { synchronized (((i)this)
/* 21 */         .e) {
/* 22 */         if (((i)this).h.get()) {
/* 23 */           boolean bool = true; IReceiveDataClaud iReceiveDataClaud; if ((iReceiveDataClaud = ((i)this).r.get()) != null)
/*    */           {
/*    */             
/* 26 */             bool = iReceiveDataClaud.isFilter((byte[])onReceiveDeviceStatusListener); }  if (bool) { ((i)this)
/*    */ 
/*    */               
/* 29 */               .g.set(onReceiveDeviceStatusListener);
/* 30 */             ((i)this).e.notify(); }
/*    */         
/*    */         } 
/*    */       }  }
/*    */      }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\d\c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */