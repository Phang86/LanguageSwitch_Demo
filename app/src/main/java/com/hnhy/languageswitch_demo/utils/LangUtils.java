package com.hnhy.languageswitch_demo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hnhy.languageswitch_demo.MainActivity;
import com.hnhy.languageswitch_demo.R;
import com.hnhy.languageswitch_demo.bean.Lang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class  LangUtils{
    private static List<Lang> langs = new ArrayList<Lang>();
    public Activity activity;

    public LangUtils(Activity activity){
        this.activity = activity;
    }

    public void SetViewLang(List<View> views) {
        for (View item : views) {
            if ("android.widget.TextView" == item.getClass().getName()) {
                TextView textView = ((TextView) activity.findViewById(item.getId()));
//                MainActivity.class.getName()+((TextView)(item)).getId()
                langs.add(new
                        Lang(2, activity.getClass().getName(), textView.getId(), textView.getClass().getName(),
                        textView.getText().toString() + "------1111", textView.getText().toString() + "------"));
            } else if ("android.widget.Button" == item.getClass().getName()) {
                Button textView = ((Button) activity.findViewById(item.getId()));
                langs.add(new
                        Lang(2, activity.getClass().getName(), textView.getId(), textView.getClass().getName(),
                        textView.getText().toString() + "------1111", textView.getText().toString() + "------"));
            }
        }
    }

    public void GetViewLang(List<View> views){
        for (View item : views) {
            if("android.widget.TextView" == item.getClass().getName()){
                TextView textView=  ((TextView)activity.findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
            } else if("android.widget.Button" == item.getClass().getName()){
                Button textView = ((Button)activity.findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
            }
        }
    }

    public String GetLang(int langId){
        String temp= "langId";
        for (Lang ll:langs) {
            if(ll.getViewId() == langId){
                temp = ll.getCnName();
                break;
            }
        }
        return  temp;
    }

    public List<View> getAllChildViews() {
        View view = activity.getWindow().getDecorView();
        return getAllChildViews(view);
    }

    public List<View> getAllChildViews(View parent) {
        List<View> allchildren = new ArrayList<View>();
        if (parent instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) parent;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }
}
