package com.planet.emily.elite.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emily on 2018/2/2
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //数据库版本号
    private static Integer Version = 1;

    /**
     * 在 SQLiteOpenHelper 的子类中，必须有该构造函数
     *
     * @param context 上下文对象
     * @param name    数据库名字
     * @param factory factory
     * @param version 当前版本
     */
    private MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private MySQLiteOpenHelper(Context context, String name, int version) {
        this(context, name,null, version);
    }

    public MySQLiteOpenHelper(Context context, String name) {
        this(context, name,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase database){

        String sql = "create table user(id int primary key,name varchar(200))";
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        System.out.println("更新数据库版本为:"+newVersion);

    }


}
