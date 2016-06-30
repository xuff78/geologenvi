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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SqlHandler {

    private DBManager dbManager;
    private Activity act;

    public SqlHandler(final Activity con){
        act=con;
        dbManager=new DBManager(act);
    }

    public ArrayList<Contact> getPersonInfo(){
        Cursor c = dbManager.querySQL("SELECT * FROM SL_JCBA05A WHERE JCBA05A090 is not null OR JCBA05A130 is not null", new String[]{});
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
        if(parent.length()==0)
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

    public ArrayList<Map<String, String>> getGeohazardInfo(int type, String name, String disasterTypeCode,
                                                           String disasterSizeCode, String areaCode, String avoidCode){
        ArrayList<Map<String, String>> datas=new ArrayList<>();
        String typeStr="";
        String formName="";
        switch (type){
            case 0:
                typeStr=" where ZHAA01A020 is not null";
                formName="SL_ZHAA01A";
                break;
            case 1:
                typeStr=" where ZHAA01A810 = 2";
                formName="SL_ZHAA01A";
                break;
            case 2:
                typeStr=" where ZHDD02A020 is not null";
                formName="SL_ZHDD02A";
                break;
            case 3:
                typeStr=" where ZHDD02A020 is not null";
                formName="SL_ZHDD02A";
                break;
            case 4:
                typeStr=" where METERTYPE is not null";
                formName="SL_STATIONMETERS";
                break;
            case 5:
                typeStr=" where ZHCA01A020 is not null";
                formName="SL_ZHCA01A";
                break;
            case 6:
                typeStr=" where ZHAA01A875 = 1";
                formName="SL_ZHAA01A";
                break;
        }
        if(type==0||type==1||type==6) {
            if (name.length() > 0)
                typeStr = typeStr + " and ZHAA01A020 like '%" + name + "%'";
            if (disasterTypeCode.length() > 0)
                typeStr = typeStr + " and ZHAA01A210 = '" + disasterTypeCode + "'";
            if (disasterSizeCode.length() > 0)
                typeStr = typeStr + " and ZHAA01A370 = '" + disasterSizeCode + "'";
            if (areaCode.length() == 6)
                typeStr = typeStr + " and ZHAA01A110 = '" + areaCode + "'";
            else if (areaCode.length() == 9)
                typeStr = typeStr + " and ZHAA01A120 = '" + areaCode + "'";
        }else if(type==5){
            if (areaCode.length() == 6)
                typeStr = typeStr + " and ZHCA01A040 = '" + areaCode + "'";
            else if (areaCode.length() == 9)
                typeStr = typeStr + " and ZHCA01A050 = '" + areaCode + "'";
        }else if(type==2||type==3){
            if (areaCode.length() == 6)
                typeStr = typeStr + " and ZHDD02A040 = '" + areaCode + "'";
            else if (areaCode.length() == 9)
                typeStr = typeStr + " and ZHDD02A050 = '" + areaCode + "'";
            if(avoidCode.length()>0){
                typeStr = typeStr + " and ZHDD02A150 = '" + avoidCode + "'";
            }
        }else if(type==4){
            if (areaCode.length()>0)
                typeStr = typeStr + " and CITY = '" + areaCode + "'";
            if (disasterTypeCode.length()>0)
                typeStr = typeStr + " and METERTYPE = '" + disasterTypeCode + "'";
        }
        return getQueryResult(formName, typeStr);
    }

    public ArrayList<Map<String, String>> getQueryResult(String tableName, String typeStr){

        ArrayList<Map<String, String>> datas=new ArrayList<>();
        String sqlStr="select * from "+tableName+typeStr;
        LogUtil.i("SQL", "reques sql---->:  "+sqlStr);
        Cursor c = dbManager.querySQL(sqlStr, new String[]{});
        if(c!=null) {
            LogUtil.i("SQL", "result num---->:  "+c.getCount());
            String columnNames[]=c.getColumnNames();
            while (c.moveToNext()) {
                Map<String, String> maps=new HashMap<>();
                for (int i=0;i<columnNames.length;i++) {
                    String key = columnNames[i];
                    String value = c.getString(c.getColumnIndex(key));
                    maps.put(key, value);
                }
                datas.add(maps);
            }
            c.close();
        }
        return datas;
    }

    public String[] getTypesQuery(String tableName, String typeString){

        String[] datas=null;
        String sqlStr="SELECT DISTINCT "+typeString+" FROM "+tableName;
        LogUtil.i("SQL", "reques sql---->:  "+sqlStr);
        Cursor c = dbManager.querySQL(sqlStr, new String[]{});
        if(c!=null) {
            LogUtil.i("SQL", "result num---->:  "+c.getCount());
            datas=new String[c.getCount()+1];
            int i=0;
            while (c.moveToNext()) {
                String value = c.getString(0);
                datas[i]=value;
                i++;
            }
            datas[datas.length-1]="取消";
            c.close();
        }
        return datas;
    }
}
