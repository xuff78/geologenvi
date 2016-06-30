package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.geodisaster.YinhuandianDetail;
import com.sichuan.geologenvi.act.geodisaster.ZhilidianweiDetail;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MineListAct   extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    private SqlHandler handler;
    private String tableName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        tableName=getIntent().getStringExtra("TableName");
        requestInfo();
    }

    private void requestInfo() {
        datalist=handler.getQueryResult(tableName, "");
        ArrayList<String> list = new ArrayList<>();
        for (Map<String, String> info : datalist) {
            if(tableName.equals("SL_KS_DZHJ_XX"))
                list.add(info.get("DZHJ_GK"));
            else if(tableName.equals("SL_DZYJBH"))
                list.add(info.get("NAME"));
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
            Intent i=getIntent();
            if(tableName.equals("SL_KS_DZHJ_XX"))
                i.setClass(MineListAct.this, ItemDetailAct.class);
            else if(tableName.equals("SL_DZYJBH"))
                i.setClass(MineListAct.this, ItemDetailAct.class);
            MapBean mapBean=new MapBean();
            int tag=(int)view.getTag();
            mapBean.setMap(datalist.get(tag));
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            startActivity(i);
        }
    };
}
