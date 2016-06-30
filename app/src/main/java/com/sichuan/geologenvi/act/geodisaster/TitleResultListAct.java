package com.sichuan.geologenvi.act.geodisaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.ItemDetailAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.GeohazardBean;
import com.sichuan.geologenvi.bean.MapBean;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/27.
 */
public class TitleResultListAct  extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    int type=0;

    private SqlHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        requestInfo();
    }

    private void requestInfo() {
        datalist=handler.getGeohazardInfo(type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterTypeCode"),
                getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"),
                getIntent().getStringExtra("avoidCode"));
        ArrayList<String> list = new ArrayList<>();
        for (Map<String, String> info : datalist) {
            switch (type) {
                case 0:
                case 1:
                case 6:
                    list.add(info.get("ZHAA01A020"));
                    break;
                case 5:
                    list.add(info.get("ZHCA01A020"));
                    break;
                case 4:
                    list.add(info.get("DISASTERNAME"));
                    break;
                case 2:
                case 3:
                    list.add(info.get("ZHDD02A020"));
                    break;
            }
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tableName="";
            Intent i=getIntent();
            switch (getIntent().getIntExtra("Type", 0)){
                case 0:
                    i.setClass(TitleResultListAct.this, YinhuandianDetail.class);
                    tableName="SL_ZHAA01A";
                    break;
                case 1:
                case 6:
                    i.setClass(TitleResultListAct.this, ItemDetailAct.class);
                    tableName="SL_ZHAA01A";
                    break;
                case 2:
                case 3:
                    i.setClass(TitleResultListAct.this, ItemDetailAct.class);
                    tableName="SL_ZHDD02A";
                    break;
                case 4:
                    i.setClass(TitleResultListAct.this, ItemDetailAct.class);
                    tableName="SL_ZHAA01A";
                    break;
                case 5:
                    i.setClass(TitleResultListAct.this, ZhilidianweiDetail.class);
                    tableName="SL_ZHCA01A";
                    break;
            }
            MapBean mapBean=new MapBean();
            int tag=(int)view.getTag();
            mapBean.setMap(datalist.get(tag));
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            startActivity(i);
        }
    };
}
