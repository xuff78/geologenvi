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
        Cursor c = dbManager.querySQL("SELECT * FROM person WHERE age >= ?", new String[]{});
        if(c!=null) {
            while (c.moveToNext()) {
                int _id = c.getInt(c.getColumnIndex("_id"));
                String name = c.getString(c.getColumnIndex("name"));
                int age = c.getInt(c.getColumnIndex("age"));
                LogUtil.i("db", "_id=>" + _id + ", name=>" + name + ", age=>" + age);
            }
            c.close();
        }
    }
}
