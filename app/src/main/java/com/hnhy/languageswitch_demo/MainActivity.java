package com.hnhy.languageswitch_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hnhy.languageswitch_demo.bean.Lang;
import com.hnhy.languageswitch_demo.utils.LangUtils;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends Activity {

    private final String TAG = "MainActivity";
    private List<Lang> langs = new ArrayList<Lang>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void SetViewLang(List<View> views){
        for (View item : views) {
            if(item.getClass().getName().equals("android.widget.TextView")){
                TextView textView=  ((TextView)findViewById(item.getId()));
                langs.add(new
                        Lang(1, MainActivity.class.getName(),textView.getId(),textView.getClass().getName(),
                        textView.getText().toString()+"------",textView.getText().toString()+"------"));
            }else if(item.getClass().getName().equals("android.widget.Button")){
                Button textView =  ((Button)findViewById(item.getId()));
                langs.add(new
                        Lang(2, MainActivity.class.getName(),textView.getId(),textView.getClass().getName(),
                        textView.getText().toString()+"------",textView.getText().toString()+"------"));
                Log.e(TAG, "SetViewLang: "+langs.toString());
            }
        }
    };

    private void GetViewLang(List<View> views){
        for (View item : views) {
            if(item.getClass().getName().equals("android.widget.TextView")){
                TextView textView=  ((TextView)findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
                Log.e(TAG, "GetViewLang: TextView"+textView.getText().toString());
            } else if(item.getClass().getName().equals("android.widget.Button")){
                Button textView = ((Button)findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
                Log.e(TAG, "GetViewLang: Button"+textView.getText().toString());
            }
        }
    }

    private String GetLang(int langId){
        String temp= "langId";
        for (Lang ll:langs) {
            if(ll.getViewId() == langId){
                temp = ll.getCnName();
                break;
            }
        }
        return  temp;
    }


    private void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //List<View> ddd = getAllChildViews();
                //SetViewLang(ddd);
                //GetViewLang(ddd);
                List<View> list = LangUtils.getAllChildViews(MainActivity);
                LangUtils.SetViewLang(list);
                LangUtils.GetViewLang(list);
            }
        }).start();
    }

    private List<View> getAllChildViews() {
        View view = this.getWindow().getDecorView();
        return getAllChildViews(view);
    }

    private List<View> getAllChildViews(View parent) {
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