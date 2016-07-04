package com.sichuan.geologenvi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.RainBean;
import com.sichuan.geologenvi.utils.XmlParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RainAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context con;
    private ArrayList<RainBean> dataList;
    private int itemHeight = 0;
    private List<String> mapKeyList;
    private String xmlName;
    private int hour=1;

    public RainAdapter(Context context, ArrayList<RainBean> dataList) {
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        con = context;
    }

    public void setHours(int hour){
        this.hour=hour;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dataList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  v = mInflater.inflate(R.layout.rain_item_layout, null);
        RainBean bean=dataList.get(position);
        TextView title = (TextView)v.findViewById(R.id.hintTxt1);
        TextView contentTxt = (TextView)v.findViewById(R.id.hintTxt2);
        title.setText(bean.getName());
        switch (hour){
            case 1:
                contentTxt.setText(bean.getHour1());
                break;
            case 3:
                contentTxt.setText(bean.getHour3());
                break;
            case 12:
                contentTxt.setText(bean.getHour12());
                break;
            case 24:
                contentTxt.setText(bean.getHour24());
                break;
        }
        return v;
    }
}
