package com.hnhy.languageswitch_demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hnhy.languageswitch_demo.MainActivity;
import com.hnhy.languageswitch_demo.bean.Lang;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends Activity {
    public static List<Lang> langs = LitePal.findAll(Lang.class);
    public static String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        initView();
        initData();
    }

    protected abstract int LayoutId();

    protected abstract void initView();

    protected abstract void initData();

    //设置控件文本
    public void SetViewLang(List<View> views){
        for (View item : views) {
            if(item.getClass().getName().equals("android.widget.TextView") ||
                    "android.widget.Button".equals(item.getClass().getName())  ||
                    "android.widget.RadioButton".equals(item.getClass().getName())){
//                List<Lang> list = LitePal.findAll(Lang.class);
                List<Lang> lan = LitePal.where("activityName = ? and viewId = ? ",item.getClass().getName(),""+item.getId()).find(Lang.class);
                if (lan.isEmpty()){
                    Lang lang = new Lang(this.getClass().getName(), ((TextView) (item)).getId(), ((TextView) (item)).getClass().getName(),
                            ((TextView) (item)).getText().toString(), ((TextView) (item)).getText().toString());
                    lang.save();
                    langs.add(lang);
                }
                langs.addAll(LitePal.findAll(Lang.class));
            }
        }

        Log.e(TAG, "SetViewLang: "+LitePal.findAll(Lang.class).toString());
    }

    //获取控件文本
    public void GetViewLang(List<View> views){
        for (View item : views) {
            if("android.widget.TextView".equals(item.getClass().getName()) ||
                    "android.widget.Button".equals(item.getClass().getName())  || "android.widget.RadioButton".equals(item.getClass().getName())){
                TextView textView=  ((TextView)findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
//                Log.e(TAG, "GetViewLang: "+textView.getId());
            }
        }
    }

    public String GetLang(int langId){
        String temp= "langId";
        for (Lang ll:langs) {
            if(ll.getViewId() == langId){
                if (ll.getLanguageState() == 0) {
                    Log.e(TAG, "GetLang: 中文");
                    temp = ll.getCnName();
                }
                if (ll.getLanguageState() == 1){
                    Log.e(TAG, "GetLang: 英文");
                    temp = ll.getEnName();
                }
                break;
            }
        }
        return  temp;
    }

    public List<View> getAllChildViews() {
        View view = this.getWindow().getDecorView();
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
