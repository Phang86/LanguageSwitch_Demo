package com.hnhy.languageswitch_demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hnhy.languageswitch_demo.base.BaseActivity;
import com.hnhy.languageswitch_demo.bean.Lang;
import com.hnhy.languageswitch_demo.utils.LangUtils;

import org.litepal.FluentQuery;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends BaseActivity {

    //private static String TAG = "MainActivity";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();

//        int i = LitePal.deleteAll(Lang.class);
//        if (i>0){
//            Log.e(TAG, "onCreate: 删除成功");
//        }else{
//            Log.e(TAG, "onCreate: 删除失败");
//        }
//        List<Lang> lan = LitePal.where("activityName = ? and viewId = ?",this.getClass().getName(),""+2131231054).find(Lang.class);
//        if (lan.isEmpty()){
//            Log.e(TAG, "onCreate: 空的");
//        }else{
//            Log.e(TAG, "onCreate: "+lan.size()+lan.toString());
//        }
//        Log.e(TAG, "onCreate: "+langs.size());
//    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        TAG = "MainActivity";
//        langs = new ArrayList<>();
        List<View> list = getAllChildViews();
        SetViewLang(list);
        GetViewLang(list);
    }

    @Override
    protected void initData() {

    }

    public void jumpIntent(View view) {
        startActivity(new Intent(this,TestActivity.class));
    }

//    private void SetViewLang(List<View> views){
//        for (View item : views) {
//            if(item.getClass().getName() == "android.widget.TextView"){
//                TextView textView=  ((TextView)findViewById(item.getId()));
//                langs.add(new
//                        Lang(1, MainActivity.class.getName(),textView.getId(),textView.getClass().getName(),
//                        textView.getText().toString()+"------",textView.getText().toString()+"------"));
//            }else if(item.getClass().getName() == "android.widget.Button"){
//                Button textView =  ((Button)findViewById(item.getId()));
//                langs.add(new
//                        Lang(1, MainActivity.class.getName(),textView.getId(),textView.getClass().getName(),
//                        textView.getText().toString()+"------",textView.getText().toString()+"------"));
//                Log.e(TAG, "SetViewLang: "+langs.toString());
//            }
//        }
//    };
//
//    private void GetViewLang(List<View> views){
//        for (View item : views) {
//            if(item.getClass().getName() == "android.widget.TextView"){
//                TextView textView=  ((TextView)findViewById(item.getId()));
//                textView.setText(GetLang(textView.getId()));
//            } else if(item.getClass().getName() == "android.widget.Button"){
//                Button textView = ((Button)findViewById(item.getId()));
//                textView.setText(GetLang(textView.getId()));
//            }
//        }
//    }

//    //设置控件文本
//    private void SetViewLang(List<View> views){
//        for (View item : views) {
//            if(item.getClass().getName().equals("android.widget.TextView") ||
//                    "android.widget.Button".equals(item.getClass().getName())  ||
//                    "android.widget.RadioButton".equals(item.getClass().getName())){
////                List<Lang> list = LitePal.findAll(Lang.class);
//                List<Lang> lan = LitePal.where("activityName = ? and viewId = ?",item.getClass().getName(),""+item.getId()).find(Lang.class);
//                if (lan.isEmpty()){
//                    Lang lang = new Lang(MainActivity.class.getName(), ((TextView) (item)).getId(), ((TextView) (item)).getClass().getName(),
//                            ((TextView) (item)).getText().toString(), ((TextView) (item)).getText().toString());
//                    lang.save();
//                    langs.add(lang);
//                }
//            }
//        }
//        Log.e(TAG, "SetViewLang: "+LitePal.findAll(Lang.class).toString());
//    }
//
//    //获取控件文本
//    private void GetViewLang(List<View> views){
//        for (View item : views) {
//            if("android.widget.TextView".equals(item.getClass().getName()) ||
//                    "android.widget.Button".equals(item.getClass().getName())  || "android.widget.RadioButton".equals(item.getClass().getName())){
//                TextView textView=  ((TextView)findViewById(item.getId()));
//                textView.setText(GetLang(textView.getId()));
////                Log.e(TAG, "GetViewLang: "+textView.getId());
//            }
//        }
//    }
//
//    private String GetLang(int langId){
//        String temp= "langId";
//        for (Lang ll:langs) {
//            if(ll.getViewId() == langId){
//                if (ll.getLanguageState() == 0) {
//                    Log.e(TAG, "GetLang: 中文");
//                    temp = ll.getCnName();
//                }
//                if (ll.getLanguageState() == 1){
//                    Log.e(TAG, "GetLang: 英文");
//                    temp = ll.getEnName();
//                }
//                break;
//            }
//        }
//        return  temp;
//    }
//
//    private void initView() {
//        langs = new ArrayList<>();
//        List<View> list = getAllChildViews();
//        SetViewLang(list);
//        GetViewLang(list);
//    }
//
//    private List<View> getAllChildViews() {
//        View view = this.getWindow().getDecorView();
//        return getAllChildViews(view);
//    }
//
//    private List<View> getAllChildViews(View parent) {
//        List<View> allchildren = new ArrayList<View>();
//        if (parent instanceof ViewGroup) {
//            ViewGroup vp = (ViewGroup) parent;
//            for (int i = 0; i < vp.getChildCount(); i++) {
//                View viewchild = vp.getChildAt(i);
//                allchildren.add(viewchild);
//                allchildren.addAll(getAllChildViews(viewchild));
//            }
//        }
//        return allchildren;
//    }
}