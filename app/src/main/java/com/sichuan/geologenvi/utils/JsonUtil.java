package com.sichuan.geologenvi.utils;

import android.util.Log;


import com.esri.core.geometry.Point;
import com.sichuan.geologenvi.bean.CateInfo;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.MapPoint;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.bean.RainBean;
import com.sichuan.geologenvi.bean.RainHourItem;
import com.sichuan.geologenvi.bean.ReportBean;
import com.sichuan.geologenvi.bean.VersionBean;
import com.sichuan.geologenvi.bean.ZBAP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public static void addJsonData(JSONObject jsonStr, String key, Object value) {

        try {
            if (key != null && value != null) {
                jsonStr.put(key.toUpperCase(), value).toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public static boolean getJsonBoolean(String jsonStr, String key) {
        boolean data=false;
        try {
            JSONObject json=new JSONObject(jsonStr);
            if(!json.isNull(key))
                data=json.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<RainBean> getRainInfo(String jsonStr) {
        ArrayList<RainBean> infos=new ArrayList<>();
        try {
                JSONArray array = new JSONArray(jsonStr);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    RainBean bean = new RainBean();


//                    "编号": "S1602",
//                            "站名": "悦来",
//                            "经度X": "103°27′12″",
//                            "纬度Y": "30°37′13″",
//                            "区域": "路边菜地内",
//                            "行政区域": "大邑县",
//                            "一小时雨量": 0.1,
//                            "十二小时雨量": 1.4,
//                            "二十四小时雨量": 2.5,
//                            "周期雨量": 1.4,
//                            "三小时雨量": 0.3,
//                            "乡镇": "悦来镇"

                    if(!item.isNull("编号"))
                        bean.setBH(item.getString("编号"));
                    if(!item.isNull("行政区域"))
                        bean.setQx(item.getString("行政区域"));
                    if (!item.isNull("站名"))
                        bean.setName(item.getString("站名"));
                    if (!item.isNull("一小时雨量"))
                        bean.setHour1(item.getString("一小时雨量"));
                    if (!item.isNull("三小时雨量"))
                        bean.setHour3(item.getString("三小时雨量"));
                    if (!item.isNull("十二小时雨量"))
                        bean.setHour12(item.getString("十二小时雨量"));
                    if (!item.isNull("二十四小时雨量"))
                        bean.setHour24(item.getString("二十四小时雨量"));
                    if (!item.isNull("区域"))
                        bean.setArea(item.getString("区域"));



//                    if (!item.isNull("name"))
//                        bean.setName(item.getString("name"));
//                    if (!item.isNull("h1"))
//                        bean.setHour1(item.getString("h1"));
//                    if (!item.isNull("h3"))
//                        bean.setHour3(item.getString("h3"));
//                    if (!item.isNull("h12"))
//                        bean.setHour12(item.getString("h12"));
//                    if (!item.isNull("h24"))
//                        bean.setHour24(item.getString("h24"));
//                    if (!item.isNull("area"))
//                        bean.setArea(item.getString("area"));
                    infos.add(bean);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infos;
    }

    public static ArrayList<RainHourItem> getRainInfoFor24h(String jsonStr) {
        ArrayList<RainHourItem> infos=new ArrayList<>();
        try {
            JSONObject data=new JSONObject(jsonStr);
            JSONArray array = data.getJSONArray("last24");
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                String datetime="";
                String rain="";
                if (!item.isNull("hour")) {
                    String hourTxt = item.getString("hour");
                    if(hourTxt!=null&&hourTxt.length()>0) {
                        datetime = Integer.valueOf(hourTxt.substring(4,6))+"-"+Integer.valueOf(hourTxt.substring(6,8))+"\\n"+
                                Integer.valueOf(hourTxt.substring(8,10))+"时";
                    }
                }
                if (!item.isNull("rain"))
                    rain=item.getString("rain");
                RainHourItem bean = new RainHourItem(datetime, rain);
                infos.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return infos;
    }

    public static VersionBean getVersionInfo(String jsonStr) {
        VersionBean bean=new VersionBean();
        try {
            JSONObject item=new JSONObject(jsonStr);
            if (!item.isNull("CreateAt"))
                bean.setCreateAt(item.getString("CreateAt"));
            if (!item.isNull("DownloadUrl"))
                bean.setDownloadUrl(item.getString("DownloadUrl"));
            if (!item.isNull("Size"))
                bean.setSize(item.getString("Size"));
            if (!item.isNull("Version"))
                bean.setVersion(item.getInt("Version"));
            if (!item.isNull("VersionName"))
                bean.setVersionName(item.getString("VersionName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static ArrayList<ReportBean> getMSRecords(String jsonStr) {
        ArrayList<ReportBean> infos=new ArrayList<>();
        try {
            JSONObject obj=new JSONObject(jsonStr);
            if(!obj.isNull("data")) {
                JSONArray array = obj.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    ReportBean bean = new ReportBean();
                    if (!item.isNull("ID"))
                        bean.setID(item.getString("ID"));
                    if (!item.isNull("KS_ID"))
                        bean.setKS_ID(item.getString("KS_ID"));
                    if (!item.isNull("DXS_ID"))
                        bean.setKS_ID(item.getString("DXS_ID"));
                    if (!item.isNull("DZYJ_ID"))
                        bean.setKS_ID(item.getString("DZYJ_ID"));
                    if (!item.isNull("BXBQ_ID"))
                        bean.setKS_ID(item.getString("BXBQ_ID"));
                    if (!item.isNull("DATETIME"))
                        bean.setDATETIME(item.getString("DATETIME"));
                    if (!item.isNull("MS"))
                        bean.setMS(item.getString("MS"));
                    if (!item.isNull("TYPE"))
                        bean.setTYPE(item.getString("TYPE"));
                    if (!item.isNull("PATH"))
                        bean.setPATH(item.getString("PATH"));
                    infos.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }

    public static ArrayList<Map<String, String>> getDataMap(String jsonStr) {
        ArrayList<Map<String, String>> infos=new ArrayList<>();
        try {
            JSONObject obj=new JSONObject(jsonStr);
            if(!obj.isNull("data")) {
                JSONArray array = obj.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    Map<String, String> infomap=new HashMap<>();
                    JSONObject item = array.getJSONObject(i);
                    Iterator<String> keys=item.keys();
                    while (keys.hasNext()){
                        String key=keys.next();
                        String value=item.getString(key);
                        if(!value.equals("null"))
                            infomap.put(key, value);
                        else
                            infomap.put(key, "");
                    }
                    infos.add(infomap);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }

    public static ArrayList<CateInfo> getBookList(String jsonStr) {
        ArrayList<CateInfo> infos=new ArrayList<>();
        try {
                JSONArray array = new JSONArray(jsonStr);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    CateInfo bean = new CateInfo();
                    if (!item.isNull("catelog"))
                        bean.setCatelog(item.getString("catelog"));
                    if (!item.isNull("files")){
                        JSONArray subarray = item.getJSONArray("files");
                        ArrayList<PopupInfoItem> books=new ArrayList<>();
                        for (int j=0;j<subarray.length();j++){
                            JSONObject subItem = subarray.getJSONObject(j);
                            if (!subItem.isNull("name")&&!subItem.isNull("url")) {
                                PopupInfoItem book = new PopupInfoItem(subItem.getString("name"), subItem.getString("url"));
                                books.add(book);
                            }
                        }
                        bean.setInfos(books);
                    }
                    infos.add(bean);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }

    public static ArrayList<String> getUrl(String jsonStr) {
        ArrayList<String> infos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonStr);
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(array.length()-1-i);
                if (!item.isNull("url"))
                    infos.add(item.getString("url"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }

    public static ArrayList<ZBAP> getZBAP(String jsonStr){
        ArrayList<ZBAP> infos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonStr);
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(array.length()-1-i);
                if (!item.isNull("name")&&!item.isNull("content")) {
                    ZBAP zbap = new ZBAP(item.getString("name"), item.getString("content"));
                    infos.add(zbap);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infos;
    }


    public static String getJsonStrUserPoint(Map<Long, MapPoint> points) {
        String json=SharedPreferencesUtil.FAILURE_STRING;
        if(points.size()>0) {
            try {
                JSONArray array=new JSONArray();
                for (Long key:points.keySet()) {

                    JSONObject jsonObj = new JSONObject();
                    MapPoint point=points.get(key);

                    jsonObj.put("desc", point.getDesc());
                    jsonObj.put("id", point.getId());
                    JSONObject subjosn=new JSONObject();
                    subjosn.put("x", point.getP().getX());
                    subjosn.put("y", point.getP().getY());
                    jsonObj.put("point",subjosn);
                    array.put(jsonObj);
                }
                json=array.toString();
                Log.i("Json", "user point:  "+json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public static Map<Long, MapPoint> getUserPointByJson(String jsonStr) {
        Map<Long, MapPoint> mapPointMap=new LinkedHashMap<>();
        try {
            JSONArray array = new JSONArray(jsonStr);
            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                MapPoint bean = new MapPoint();
                if (!item.isNull("desc"))
                    bean.setDesc(item.getString("desc"));
                if (!item.isNull("id"))
                    bean.setId(item.getLong("id"));
                if (!item.isNull("point")){
                    Point p=new Point();
                    JSONObject subobj = item.getJSONObject("point");
                    if (!subobj.isNull("x"))
                        p.setX(subobj.getDouble("x"));
                    if (!subobj.isNull("y"))
                        p.setY(subobj.getDouble("y"));
                    bean.setP(p);
                }
                mapPointMap.put(bean.getId(), bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mapPointMap;
    }
}
