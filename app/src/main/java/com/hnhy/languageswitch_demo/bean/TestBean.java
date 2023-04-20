package com.hnhy.languageswitch_demo.bean;

public class TestBean {
    private static volatile TestBean mInstance;

    public static TestBean getInstance(){
        if (mInstance == null) {
            synchronized (TestBean.class){
                if (mInstance == null) {
                    mInstance = new TestBean();
                }
            }
        }
        return mInstance;
    }

}
