package com.hnhy.languageswitch_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hnhy.languageswitch_demo.base.BaseActivity;

import java.util.List;

public class TestActivity extends BaseActivity {

    @Override
    protected int LayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        TAG = "TestActivity";
        List<View> list = getAllChildViews();
        SetViewLang(list);
        GetViewLang(list);
    }

    @Override
    protected void initData() {

    }
}