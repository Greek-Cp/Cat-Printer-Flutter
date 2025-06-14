package com.luckprinter.demo;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * @author HuangXiaohui
 * @date 2024-12-07 18:02
 */
public class OpenCVUtils {
    private static volatile OpenCVUtils instance;

    private OpenCVUtils() {

    }

    public static OpenCVUtils getInstance() {
        if(instance == null) {
            synchronized(OpenCVUtils.class) {
                if(instance == null) {
                    instance = new OpenCVUtils();
                }
            }
        }
        return instance;
    }

    public void initOpenCV(Context context, InitOpenCvCallback callback) {
        if (callback != null) {
            callback.onSuccess();
        }

//        org.opencv.algorithm.OpenCVUtils.getInstance().initOpenCV(context, new org.opencv.algorithm.LoaderCallback() {
//            @Override
//            public void onSuccess() {
//                if (callback != null) {
//                    callback.onSuccess();
//                }
//            }
//
//            @Override
//            public void onError(int status) {
//                if (callback != null) {
//                    callback.onError(status);
//                }
//            }
//        });
    }

    public Bitmap getFlyodBitmapNew(Bitmap bitmap) {
        return bitmap;
//        return org.opencv.algorithm.OpenCVUtils.getInstance().getFlyodBitmapNew(bitmap);
    }

    public interface InitOpenCvCallback {
        void onSuccess();
        void onError(int status);
    }
}
