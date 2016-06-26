package com.sichuan.geologenvi.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sichuan.geologenvi.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SqlHandler {

    private DBManager dbManager;

    public SqlHandler(Context con, String dbName){
        dbManager=new DBManager(con, dbName);
    }

    public void getPersonInfo(){
        Cursor c = dbManager.querySQL("SELECT * FROM USER_INFO", new String[]{});
        if(c!=null) {
            c.moveToFirst();
            while (c.moveToNext()) {
                String _id = c.getString(c.getColumnIndex("USERNAME"));
                String name = c.getString(c.getColumnIndex("USERCODE"));
                LogUtil.i("db", "_id=>" + _id + ", name=>" + name);
            }
            c.close();
        }
    }
}
