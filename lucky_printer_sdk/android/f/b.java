/*  1 */ package f;public abstract class b { public static void a(String paramString, File paramFile, ZipOutputStream paramZipOutputStream) { if (paramFile.listFiles() == null)
/*    */       return;  File[] arrayOfFile;
/*    */     int i;
/*    */     byte b1;
/*  5 */     for (i = (arrayOfFile = paramFile.listFiles()).length, b1 = 0; b1 < i; ) { byte[] arrayOfByte; File file; if ((file = arrayOfFile[b1])
/*  6 */         .isFile()) { FileInputStream fileInputStream = null;
/*    */         try {
/*    */           FileInputStream fileInputStream1;
/*  9 */           this(file);
/*    */           
/* 11 */           try { paramZipOutputStream.putNextEntry(new ZipEntry(paramString + File.separator + file.getName()));
/*    */             
/* 13 */             arrayOfByte = new byte[1024]; }
/*    */           catch (IOException iOException) {  }
/*    */           finally
/* 16 */           { paramString = null; }  try { fileInputStream1
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */               
/* 23 */               .close(); }
/*    */           
/* 25 */           catch (IOException iOException) { null.printStackTrace(); } 
/* 26 */         } catch (IOException iOException) {
/*    */ 
/*    */           
/*    */           throw null;
/*    */         
/*    */         }
/*    */         finally {}
/*    */          }
/*    */       
/*    */       else
/*    */       
/* 37 */       { a(paramString + File.separator + arrayOfByte.getName(), (File)arrayOfByte, paramZipOutputStream); }  b1++; }
/*    */      }
/*    */    }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\f\b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */