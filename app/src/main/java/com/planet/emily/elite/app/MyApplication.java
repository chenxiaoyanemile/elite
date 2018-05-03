package com.planet.emily.elite.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.netcircle.imageloader.gen.DaoMaster;
import com.netcircle.imageloader.gen.DaoSession;
import com.netcircle.imageloader.gen.UserLoginDao;

/**
 * Created by emily on 2018/1/30
 */

public class MyApplication extends Application{

    private SQLiteDatabase mDatabase;
    private DaoSession mDaoSession;
    private UserLoginDao mUser;

    public static MyApplication instances;

    @Override
    public void onCreate(){
        super.onCreate();
        instances = this;
        setDatabase();

    }

    public static MyApplication getInstances(){
        return instances;
    }

    private void setDatabase(){
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "user_date", null);
        mDatabase = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(mDatabase);
        mDaoSession = mDaoMaster.newSession();
        mUser = mDaoSession.getUserLoginDao();
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public UserLoginDao getUser(){
        return mUser;
    }

    public SQLiteDatabase getDatabase(){
        return mDatabase;
    }
}
