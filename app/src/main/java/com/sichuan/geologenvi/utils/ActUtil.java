package com.sichuan.geologenvi.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 可爱的蘑菇 on 2016/3/13.
 */
public class ActUtil {

    public static void closeSoftPan(Activity act) {
        View view = act.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager)act.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    public static String addStringContent(String columnName, EditText edt, String content){
        JSONObject json= new JSONObject();
        try {
            if(content.length()>0)
                json = new JSONObject(content);
            String edtTxt=edt.getText().toString().trim();
            if(edtTxt.length()>0)
                json.put(columnName, edtTxt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String addStringContent(String[] columnName, Object[] values){
        JSONObject json= new JSONObject();
        try {
            for (int i=0;i<columnName.length;i++) {
                Object item=values[i];
                if(item!=null)
                    json.put(columnName[i], item);
//                if(item instanceof String) {
//                    String value=String.valueOf(item) ;
//                    json.put(columnName[i], value);
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
