package com.hnhy.languageswitch_demo.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hnhy.languageswitch_demo.MainActivity;
import com.hnhy.languageswitch_demo.R;
import com.hnhy.languageswitch_demo.api.CallBackOnclick;
import com.hnhy.languageswitch_demo.bean.Lang;
import com.hnhy.languageswitch_demo.view.UpdateDialog;

import org.litepal.FluentQuery;
import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity{
    public static List<Lang> langs = LitePal.findAll(Lang.class);
    public static String TAG = "BaseActivity";
    private String zh = "zh";
    private String CN = "CN";
    private String en = "en";
    private String US = "US";
    private int viewId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        initView();
        initData();
        getViewInfo();
    }

    protected abstract int LayoutId();

    protected abstract void initView();

    protected abstract void initData();

//    public abstract void viewsOnclick();

    public void getViewInfo() {
        List<View> list = getAllChildViews();
        SetViewLang(list);
        GetViewLang(list);
    }

    //设置控件文本
    public void SetViewLang(List<View> views) {
        for (View item : views) {
            Log.e(TAG, "SetViewLang: " + item.getClass().getName().toString());
            if (("androidx.appcompat.widget.AppCompatTextView").equals(item.getClass().getName()) ||
                    "androidx.appcompat.widget.AppCompatButton".equals(item.getClass().getName()) ||
                    "androidx.appcompat.widget.AppCompatRadioButton".equals(item.getClass().getName()) ||
                    "com.hnhy.languageswitch_demo.view.MyPasswordEditText".equals(item.getClass().getName()) ||
            "com.hnhy.languageswitch_demo.view.MyEditText".equals(item.getClass().getName())) {
                List<Lang> lan = LitePal.where("activityName = ? and viewId = ? ", ((TextView) (item)).getClass().getName(), "" + ((TextView) (item)).getId()).find(Lang.class);
                List<Lang> language = LitePal.where("languageState = ?", "en-us").find(Lang.class);
                Lang lang;
                if (lan.isEmpty()) {
                    if (language.isEmpty()) {
                        lang = new Lang(this.getClass().getName(), ((TextView) (item)).getId(), ((TextView) (item)).getClass().getName(),
                                getLanguage(item.getId(), en, US), getLanguage(item.getId(), zh, CN));
                    } else {
                        lang = new Lang(this.getClass().getName(), ((TextView) (item)).getId(), ((TextView) (item)).getClass().getName(),
                                getLanguage(item.getId(), en, US), getLanguage(item.getId(), zh, CN), "en-us");
                    }
                    lang.save();
                    langs.add(lang);
                }
                langs.addAll(LitePal.findAll(Lang.class));
            }
        }
        Log.e(TAG, "SetViewLang: " + LitePal.findAll(Lang.class).toString());
    }

    //获取控件文本
    public void GetViewLang(List<View> views) {
        for (View item : views) {
            if ("androidx.appcompat.widget.AppCompatTextView".equals(item.getClass().getName()) ||
                    "androidx.appcompat.widget.AppCompatButton".equals(item.getClass().getName()) ||
                    "androidx.appcompat.widget.AppCompatRadioButton".equals(item.getClass().getName())) {
                TextView textView = ((TextView) findViewById(item.getId()));
                textView.setText(GetLang(textView.getId()));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(GetLang(textView.getId()));
                    }
                });

            }
            if ("com.hnhy.languageswitch_demo.view.MyPasswordEditText".equals(item.getClass().getName()) ||
            "com.hnhy.languageswitch_demo.view.MyEditText".equals(item.getClass().getName())) {
                TextView textView = ((TextView) findViewById(item.getId()));
                textView.setHint(GetLang(textView.getId()));
            }
        }
    }

    public String GetLang(int langId) {
        String temp = "langId";
        langs = LitePal.findAll(Lang.class);
        for (Lang ll : langs) {
            if (ll.getViewId() == langId) {
                if ("zh-cn".equals(ll.getLanguageState())) {
                    Log.e(TAG, "GetLang: 中文");
                    temp = ll.getCnName();
                }
                if ("en-us".equals(ll.getLanguageState())) {
                    Log.e(TAG, "GetLang: 英文");
                    temp = ll.getEnName();
                }
                break;
            }
        }
        return temp;
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

    //获取语言  viewId:控件id  language:语言  country:国家
    private String getLanguage(int viewId, String language, String country) {
        Locale locale = new Locale(language, country);
        Configuration configuration = new Configuration(getResources().getConfiguration());
        configuration.setLocale(locale);
        // 获取 text_view
        String name = getResources().getResourceEntryName(viewId);
        // 获取 text_view 对应的 string 资源的 id
        int stringId = getResources().getIdentifier(name, "string", getPackageName());
        // 获取字符串资源
        //String stringRes = getResources().getString(stringId);
        //Log.e(TAG, "stringRes："+stringRes+"\t\tlanguage："+language);
        return createConfigurationContext(configuration).getResources().getString(stringId);
    }

    private void showDialog(String etShowContent){
        UpdateDialog dialog = new UpdateDialog(this,etShowContent);
        dialog.show();
        dialog.showConfirmDialog(new UpdateDialog.OnDialogButtonClickListener() {
            @Override
            public void onPositiveButtonClick(String content) {
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(BaseActivity.this, "内容为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BaseActivity.this, ""+content, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onNegativeButtonClick() {
                dialog.dismiss();
            }
        });
    }


}



