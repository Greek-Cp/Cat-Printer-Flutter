package com.luckprinter.demo;

import android.app.Application;
import android.util.Log;

import com.luckjingle.printersdk.BuildConfig;
import com.luckprinter.sdk_new.device.PrinterHelper;

public class App extends Application {
    public static final String TAG = "App";

    private static App instance = null;

    public static App getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String asKey = "";
        //国内版demo的asKey
        asKey = "91f047455bab4e94bc243b08326e607e";
        PrinterHelper.getInstance().init(this, asKey, BuildConfig.DEBUG);
        PrinterHelper.getInstance().setEnableBle(true);
        PrinterHelper.getInstance().setEventRecorder(new EventRecorder());
        OpenCVUtils.getInstance().initOpenCV(this, new OpenCVUtils.InitOpenCvCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "opencv init success");
            }

            @Override
            public void onError(int status) {
                Log.d(TAG, "opencv init error, status: " + status);
            }
        });
    }
}
