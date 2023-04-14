package com.hnhy.languageswitch_demo;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hnhy.languageswitch_demo.base.BaseActivity;
import com.hnhy.languageswitch_demo.bean.Lang;
import com.hnhy.languageswitch_demo.utils.LangUtils;

import org.litepal.FluentQuery;
import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends BaseActivity {
    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        TAG = "MainActivity";
    }

    private void updateLanguage() {
        ContentValues values = new ContentValues();
        int flag = getLanguage();
        Log.e(TAG, "updateLanguage: "+flag);
        if (flag == 0){
            values.put("languageState",1);
            int i = LitePal.updateAll(Lang.class, values);
            if (i > 0){
                Toast.makeText(this, "修改成功 1", Toast.LENGTH_SHORT).show();
                getViewInfo();
                return;
            }
            Toast.makeText(this, "修改失败 1", Toast.LENGTH_SHORT).show();
        } else {
            values.put("languageState", 0);
            int i = LitePal.updateAll(Lang.class, values);
            if (i > 0) {
                Toast.makeText(this, "修改成功 0", Toast.LENGTH_SHORT).show();
                getViewInfo();
                return;
            }
            Toast.makeText(this, "修改失败 0", Toast.LENGTH_SHORT).show();
        }
    }

    private int getLanguage(){
        int languageFlag = 0;
        List<Lang> langs = LitePal.select("languageState").find(Lang.class);
        if (!langs.isEmpty()){
            int languageState = langs.get(0).getLanguageState();
            Log.e(TAG, "getLanguage: "+languageState);
            languageFlag = languageState;
            return languageFlag;
        }
        return languageFlag;
    }

    @Override
    protected void initData() {
//        deleteAll();
        int flag = 0;
    }

    public void jumpIntent(View view) {
        startActivity(new Intent(this,TestActivity.class));
    }

    //删除全部
    public void deleteAll(){
        int i = LitePal.deleteAll(Lang.class);
        if (i > 0) {
            Log.e(TAG, "deleteAll: 成功");
            return;
        }
        Log.e(TAG, "deleteAll: 失败");
    }

    public void updateLanguage(View view) {
        updateLanguage();
    }


}