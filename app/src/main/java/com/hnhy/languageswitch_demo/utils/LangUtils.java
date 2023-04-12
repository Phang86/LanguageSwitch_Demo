package com.hnhy.languageswitch_demo.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hnhy.languageswitch_demo.MainActivity;
import com.hnhy.languageswitch_demo.bean.Lang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LangUtils {
    private static List<Lang> langs = new ArrayList<Lang>();
    private static Activity activity;

    public static void SetViewLang(List<View> views){
        for (View item : views) {
            if(item.getClass().getName().equals("android.widget.TextView")){
                TextView textView=  ((TextView)activity.findViewById(item.getId()));
                langs.add(new
                        Lang(1, MainActivity.class.getName(),textView.getId(),textView.getClass().getName(),
                        textView.getText().toString()+"------",textView.getText().toString()+"------"));
            }else if(item.getClass().getName().equals("android.widget.Button")){
                Button textView =  ((Button)activity.findViewById(item.getId()));
                langs.add(new
                        Lang(2, MainActivity.class.getName(),textView.getId(),textView.getClass().getName(),
                        textView.getText().toString()+"------",textView.getText().toString()+"------"));
                Log.e("TAG", "SetViewLang: "+langs.toString());
            }
        }
    };

    public static void GetViewLang(List<View> views){
        for (View item : views) {
            if(item.getClass().getName().equals("android.widget.TextView")){
                TextView textView=  ((TextView)activity.findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
                Log.e("TAG", "GetViewLang: TextView"+textView.getText().toString());
            } else if(item.getClass().getName().equals("android.widget.Button")){
                Button textView = ((Button)activity.findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
                Log.e("TAG", "GetViewLang: Button"+textView.getText().toString());
            }
        }
    }

    public static String GetLang(int langId){
        String temp= "langId";
        for (Lang ll:langs) {
            if(ll.getViewId() == langId){
                temp = ll.getCnName();
                break;
            }
        }
        return  temp;
    }

    public static List<View> getAllChildViews(Activity activity) {
        activity = activity;
        View view = activity.getWindow().getDecorView();
        return getAllChildViews(view);
    }

    public static List<View> getAllChildViews(View parent) {
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
