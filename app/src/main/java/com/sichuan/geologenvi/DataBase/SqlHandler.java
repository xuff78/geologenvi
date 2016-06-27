package com.sichuan.geologenvi.DataBase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.Contact;
import com.sichuan.geologenvi.bean.GeohazardBean;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SqlHandler {

    private DBManager dbManager;
    private Activity act;

    public SqlHandler(final Activity con, final String dbName, final Runnable callback){
        act=con;
        new Thread(){
            @Override
            public void run() {
                dbManager=new DBManager(act, dbName);
                act.runOnUiThread(callback);
            }
        }.start();
    }

    public ArrayList<Contact> getPersonInfo(){
        Cursor c = dbManager.querySQL("SELECT * FROM SL_JCBA05A WHERE JCBA05A090 is not null AND JCBA05A130 is not null", new String[]{});
        ArrayList<Contact> contacts=new ArrayList<>();
        if(c!=null) {
            c.moveToFirst();
            while (c.moveToNext()) {
                Contact contact=new Contact();
                String phone = c.getString(c.getColumnIndex("JCBA05A130"));
                String name = c.getString(c.getColumnIndex("JCBA05A090"));
                String position = c.getString(c.getColumnIndex("JCBA05A180"));
                if(phone==null) {
                    contact.setName(name);
                    contact.setPhone("");
                }else if(name==null){
                    contact.setName(phone);
                    contact.setPhone(phone);
                }else{
                    contact.setName(name);
                    contact.setPhone(phone);
                }
                if(position!=null)
                    contact.setPosition(position);
                contacts.add(contact);
            }
            c.close();
        }
        return contacts;
    }

    public ArrayList<AreaInfo> getAreaInfo(String parent){
        Cursor c;
        if(parent==null)
            c = dbManager.querySQL("select * from SL_TATTR_DZZH_XZQH where length(code) = 6", new String[]{});
        else
            c = dbManager.querySQL("select * from SL_TATTR_DZZH_XZQH where parentcodeid = ?", new String[]{parent});
        ArrayList<AreaInfo> contacts=new ArrayList<>();
        if(c!=null) {
            c.moveToFirst();
            while (c.moveToNext()) {
                AreaInfo contact=new AreaInfo();
                String NAME = c.getString(c.getColumnIndex("NAME"));
                String ID = c.getString(c.getColumnIndex("ID"));
                String CODE = c.getString(c.getColumnIndex("CODE"));
                if(NAME!=null) {
                    contact.setName(NAME);
                }
                if(ID!=null){
                    contact.setId(ID);
                }
                if(CODE!=null){
                    contact.setCode(CODE);
                }
                contacts.add(contact);
            }
            c.close();
        }
        return contacts;
    }

    public ArrayList<GeohazardBean> getGeohazardInfo(int type, String name, String disasterTypeCode,
                                                     String disasterSizeCode, String areaCode){
        String typeStr="";
        switch (type){
            case 0:
                typeStr=" where ZHAA01A020 is not null";
                break;
            case 1:
                typeStr=" where ZHAA01A810 = 4";
                break;
            case 6:
                typeStr=" where ZHAA01A875 = 1";
                break;
        }
        if(name.length()>0)
            typeStr=typeStr+" and ZHAA01A020 like '%"+name+"%'";
//        if(disasterTypeCode.length()>0)
//            typeStr=typeStr+" and ZHAA01A875 = '"+disasterTypeCode+"'";
        Cursor c = dbManager.querySQL("select * from SL_ZHAA01A"+typeStr, new String[]{});
        ArrayList<GeohazardBean> contacts=new ArrayList<>();
        if(c!=null) {
            c.moveToFirst();
            while (c.moveToNext()) {
                GeohazardBean contact=new GeohazardBean();
                String NAME = c.getString(c.getColumnIndex("ZHAA01A020"));
                if(NAME!=null) {
                    contact.setName(NAME);
                }
                contacts.add(contact);
            }
            c.close();
        }
        return contacts;
    }
}
