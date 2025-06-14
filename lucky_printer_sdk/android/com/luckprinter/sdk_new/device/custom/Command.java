/*  1 */ package com.luckprinter.sdk_new.device.custom;public class Command { private String type; private String data; private boolean callback; public String getType() { return this.type; } private int callbackTime; private List<String> callbackData; private String position; public void setType(String paramString) { this.type = paramString; } public String getData() { return this.data; } public void setData(String paramString) { this.data = paramString; } public boolean isCallback() { return this.callback; } public void setCallback(boolean paramBoolean) { this.callback = paramBoolean; } public int getCallbackTime() { return this.callbackTime; } public void setCallbackTime(int paramInt) { this.callbackTime = paramInt; } public List<String> getCallbackData() { return this.callbackData; } public void setCallbackData(List<String> paramList) { this.callbackData = paramList; } public String getPosition() { return this.position; } public void setPosition(String paramString) { this.position = paramString; } public static class Builder { private String type; private String data; private boolean callback = false; public Builder type(String param1String) { this.type = param1String; return this; } public Builder data(String param1String) { this.data = param1String; return this; } public Builder callback(boolean param1Boolean) { this.callback = param1Boolean; return this; } public Builder callbackTime(int param1Int) { this.callbackTime = param1Int; return this; } public Builder callbackData(List<String> param1List) { this.callbackData = param1List; return this; } public Builder position(String param1String) { this.position = param1String; return this; } public Command build() { return new Command(this); }
/*    */ 
/*    */     
/*  4 */     private int callbackTime = 3000; private List<String> callbackData; private String position; } private Command() { this.callback = false;
/*  5 */     this.callbackTime = 3000;
/*    */     
/*  7 */     this.callbackData = List.of("4f4b"); } private Command(Builder paramBuilder) {
/*  8 */     this
/*  9 */       .callback = false;
/*    */     
/* 11 */     this.callbackTime = 3000;
/*    */     
/* 13 */     this.callbackData = List.of("4f4b");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.type = paramBuilder.type;
/* 24 */     this.data = paramBuilder.data;
/* 25 */     this.callback = paramBuilder.callback;
/* 26 */     this.callbackTime = paramBuilder.callbackTime;
/* 27 */     this.callbackData = paramBuilder.callbackData;
/* 28 */     this.position = paramBuilder.position;
/*    */   } }


/* Location:              C:\Users\Omen\Downloads\app lucky printer\android-printing-sdk-demo-doc\android-printing-sdk-demo\app\libs\test\classes.jar!\com\luckprinter\sdk_new\device\custom\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */