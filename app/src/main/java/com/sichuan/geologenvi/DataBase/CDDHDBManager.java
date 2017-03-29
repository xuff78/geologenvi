package com.sichuan.geologenvi.DataBase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sichuan.geologenvi.utils.ToastUtils;

/**
 * Created by tanqq on 2017/3/24.
 */
public class CDDHDBManager {
    public static final String DB_NAME = "cddh.db"; //保存的数据库文件名
    public static final String TMP_DB_NAME = "tmpinfo.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "ttt";
//    public static final String DB_PATH = Environment.getExternalStorageDirectory() + "/"
//            + PACKAGE_NAME;  //在手机里存放数据库的位置

    private SQLiteDatabase database=null;
    private Context context;

    public CDDHDBManager(final Activity context) {
        this.context = context;
        openDatabase(context.getExternalFilesDir(null)+"/"+DB_NAME);
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

