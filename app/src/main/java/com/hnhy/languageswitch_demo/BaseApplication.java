package com.hnhy.languageswitch_demo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.hnhy.languageswitch_demo.bean.TestBean;
import com.hnhy.languageswitch_demo.greenDao.db.DaoMaster;
import com.hnhy.languageswitch_demo.greenDao.db.DaoSession;

import org.litepal.LitePal;

public class BaseApplication extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化LitePal数据库框架
        LitePal.initialize(this);
        //初始化GreenDao
        initGreenDao();
    }

    //初始化GreenDao
    private void initGreenDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "language.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

}
