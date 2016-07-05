package com.sichuan.geologenvi.utils;

import android.util.Log;


import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.RainBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public class JsonUtil {

    public static JsonMessage getJsonMessage(String jsonStr){
        JsonMessage jsonMsg=new JsonMessage();
        try {
            JSONObject json=new JSONObject(jsonStr);
            if(!json.isNull("code"))
                jsonMsg.setCode(json.getString("code"));
            if(!json.isNull("error"))
                jsonMsg.setMsg(json.getString("error"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }

    public static String getJsonData(String jsonStr) {
        String data="";
        try {
            JSONObject json=new JSONObject(jsonStr);
            if(!json.isNull("data"))
                data=json.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getString(String jsonStr, String key) {
        String data="";
        try {
            JSONObject json=new JSONObject(jsonStr);
            if(!json.isNull(key))
                data=json.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static int getJsonInt(String jsonStr, String key) {
        int data=0;
        try {
            JSONObject json=new JSONObject(jsonStr);
            if(!json.isNull(key))
                data=json.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<RainBean> getRainInfo(String jsonStr) {
        ArrayList<RainBean> infos=new ArrayList<>();
        try {
            JSONArray array=new JSONArray(jsonStr);
                for(int i=0; i<array.length();i++){
                    JSONObject item=array.getJSONObject(i);
                    RainBean bean = new RainBean();
                    if(!item.isNull("name"))
                        bean.setName(item.getString("name"));
                    if(!item.isNull("h1"))
                        bean.setHour1(item.getString("h1"));
                    if(!item.isNull("h3"))
                        bean.setHour3(item.getString("h3"));
                    if(!item.isNull("h12"))
                        bean.setHour12(item.getString("h12"));
                    if(!item.isNull("h24"))
                        bean.setHour24(item.getString("h24"));
                    infos.add(bean);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infos;
    }


}
