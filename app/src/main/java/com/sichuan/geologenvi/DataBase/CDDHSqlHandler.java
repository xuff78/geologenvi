package com.sichuan.geologenvi.DataBase;

import android.app.Activity;
import android.database.Cursor;

import com.sichuan.geologenvi.utils.LogUtil;

import java.security.Timestamp;
import java.util.Date;


/**
 * Created by tanqq on 2017/3/24.
 */
public class CDDHSqlHandler {
    private CDDHDBManager dbManager;
    private Activity act;

    public CDDHSqlHandler(final Activity con) {
        act = con;
        dbManager = new CDDHDBManager(act);
    }
    public String getYJDate(){
        String value="";
        String sql="select yujingtime from yujing";
        Cursor c = dbManager.querySQL(sql, new String[]{});
        if(c!=null) {
            LogUtil.i("SQL", "result num---->:  "+c.getCount());
            while (c.moveToNext()) {
                 value = c.getString(0);
            }
            c.close();
        }
        return value;
    }

    public void execSQL(String sql){
        LogUtil.i("SQL", "result sql---->:  "+sql);
        dbManager.execSQL(sql, new String[]{});
    }
}
