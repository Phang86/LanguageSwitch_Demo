package com.hnhy.languageswitch_demo;


import android.app.Activity;
import android.app.Application;
import android.app.usage.UsageEvents;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.hnhy.languageswitch_demo.base.BaseActivity;
import com.hnhy.languageswitch_demo.bean.Lang;
import com.hnhy.languageswitch_demo.greenDao.bean.Student;
import com.hnhy.languageswitch_demo.greenDao.db.DaoSession;
import com.hnhy.languageswitch_demo.greenDao.db.StudentDao;
import com.hnhy.languageswitch_demo.utils.LangUtils;
import com.hnhy.languageswitch_demo.view.CircularProgressBar;

import org.litepal.FluentQuery;
import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity {


    private static final int FAST_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;
    private CircularProgressBar cpbTest;
    private Button btn;

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
        String flag = getLanguage();
        Log.e(TAG, "updateLanguage: "+flag);
        if ("zh-cn".equals(flag)){
            values.put("languageState","en-us");
            int i = LitePal.updateAll(Lang.class, values);
            if (i > 0){
                Toast.makeText(this, "修改成功 en-US", Toast.LENGTH_SHORT).show();
                getViewInfo();
                return;
            }
            Toast.makeText(this, "修改失败 en-US", Toast.LENGTH_SHORT).show();
        } else {
            values.put("languageState", "zh-cn");
            if (LitePal.updateAll(Lang.class, values) > 0) {
                Toast.makeText(this, "修改成功 zh-CN", Toast.LENGTH_SHORT).show();
                getViewInfo();
                return;
            }
            Toast.makeText(this, "修改失败 zh-CN", Toast.LENGTH_SHORT).show();
        }
    }

    private String getLanguage(){
        String languageFlag = "zh-cn";
        List<Lang> langs = LitePal.select("languageState").find(Lang.class);
        if (!langs.isEmpty()){
            String languageState = langs.get(0).getLanguageState();
            languageFlag = languageState;
            return languageFlag;
        }
        return languageFlag;
    }

    @Override
    protected void initData() {
//        deleteAll();
        //updateLanguage();
        insertData();

        //圆形进度条操作
         cpbTest = findViewById(R.id.cpb_test);
        randomProgress();

        btn = findViewById(R.id.btn_random_progress);
        btn.setOnClickListener(v -> {
            randomProgress();
        });
    }

    private void randomProgress() {
        int progress = Math.abs(new Random().nextInt() % 100);
        Toast.makeText(this, "" + progress, Toast.LENGTH_SHORT).show();
        cpbTest.setText(progress + "%");
        cpbTest.setProgress(progress);
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

    private void insertData(){
        BaseApplication application = (BaseApplication) getApplication();
        DaoSession session = application.getDaoSession();
        StudentDao dao = session.getStudentDao();

//        for (int i = 0; i < 1000; i++) {
//            Student student = new Student();
//            student.setId(i);
//            student.setEnName("zhangsan"+i);
//            student.setCnName("张三"+i);
//            student.setViewId(i);
//            student.setLanguageState("zh-cn");
//            dao.insertOrReplace(student);
//        }

//        dao.insert(student);

        List<Student> list = session.loadAll(Student.class);
        Log.e(TAG, "删除数据前: "+list.size());

        session.deleteAll(Student.class);
        list = session.loadAll(Student.class);
        Log.e(TAG, "删除数据后: "+list.size());
    }

    private long exitTime = 0;
    private void exit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次推出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else{
//            System.exit(0);
            finish();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}