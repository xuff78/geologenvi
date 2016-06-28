package com.sichuan.geologenvi.DataBase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class DBManager {
    public static final String DB_NAME = "info.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "ttt";
    public static final String DB_PATH = Environment.getExternalStorageDirectory() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置

    private SQLiteDatabase database=null;
    private Context context;

    public DBManager(final Activity context) {
        this.context = context;
        openDatabase(DB_PATH+"/"+DB_NAME);
    }

    private void openDatabase(String dbfile) {
        try {
            database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.displayTextShort(context, "数据库打开失败");
        }
    }

    private synchronized SQLiteDatabase getDatebase(){
        if (database != null && database.isOpen()) {
            if(database.isReadOnly()){
                ToastUtils.displayTextShort(context, "请稍后再试");
                return null;
            }else
                return database;
        }else{
            return null;
        }
    }

    public void execSQL(String sqlStr, Object[] bindArgs){
        SQLiteDatabase mSQLiteDatabase=getDatebase();
        if(mSQLiteDatabase!=null)
            mSQLiteDatabase.execSQL(sqlStr, bindArgs);
    }

    public Cursor querySQL(String sqlStr, String[] bindArgs){
        SQLiteDatabase mSQLiteDatabase=getDatebase();
        if(mSQLiteDatabase!=null) {
            return mSQLiteDatabase.rawQuery(sqlStr, bindArgs);
        }else
            return null;
    }

    public void closeDatabase() {
        this.database.close();
    }
}
