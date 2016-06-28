package com.sichuan.geologenvi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.XmlParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ActivityInfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context con;
    private Map<String, String> dataList;
    private int itemHeight = 0;
    private List<String> mapKeyList;
    private String xmlName;

    public ActivityInfoAdapter(Context context, Map<String, String> dataList, String xmlName) {
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        con = context;
        mapKeyList = new ArrayList<>(dataList.keySet());
        this.xmlName=xmlName;
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

        View v = mInflater.inflate(R.layout.listitem_info_inact, null);
        TextView title = (TextView)v.findViewById(R.id.nameTxt);
        TextView contentTxt = (TextView)v.findViewById(R.id.contentTxt);
        title.setText(XmlParse.getPapers(con, mapKeyList.get(position), xmlName));
        contentTxt.setText(dataList.get(mapKeyList.get(position)));
        return v;
    }
}
