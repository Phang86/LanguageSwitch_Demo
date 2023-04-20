package com.gy.testlibrary;

import android.util.Log;

/*
* 类说明：Log工具类，打印开发中打印错误的日志
* @author Ph
* */
public class Test {
    private static String TAG = "Test";

    public static void showLoge(String msg){
        Log.e(TAG, "showLoge: "+msg);
    }

    public static void showLogi(String msg){
        Log.i(TAG, "showLogi: "+msg);
    }
}
