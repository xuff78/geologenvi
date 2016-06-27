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
    private final int BUFFER_SIZE = 400000;
    public String DB_NAME = "countries.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "ttt";
    public static final String DB_PATH = Environment.getExternalStorageDirectory() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置

    private static SQLiteDatabase database=null;
    private Context context;

    public DBManager(final Activity context, String dbName, final Runnable callback) {
        this.context = context;
        this.DB_NAME = dbName;
        if(database==null)
        new Thread(){
            @Override
            public void run() {
                openDatabase(DB_PATH+"/"+DB_NAME);
                context.runOnUiThread(callback);
            }
        }.start();
        else{
            callback.run();
        }
    }

    private void openDatabase(String dbfile) {
        try {
            if (!(new File(dbfile).exists())) {
                FileUtil.isExist(DB_PATH);

                File file = new File(dbfile);
                InputStream is = context.getResources().getAssets().open("db.db3");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized SQLiteDatabase getDatebase(){
        if (database != null && database.isOpen()) {
            if(database.isReadOnly()){
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
