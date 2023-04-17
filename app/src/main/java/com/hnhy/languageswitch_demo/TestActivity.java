package com.hnhy.languageswitch_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hnhy.languageswitch_demo.api.CallBackOnclick;
import com.hnhy.languageswitch_demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity {

    @Override
    protected int LayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        TAG = "TestActivity";
    }

    @Override
    protected void initData() {

    }


}