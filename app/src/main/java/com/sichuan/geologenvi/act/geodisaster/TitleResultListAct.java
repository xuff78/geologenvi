package com.sichuan.geologenvi.act.geodisaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.GeohazardBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/27.
 */
public class TitleResultListAct  extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<GeohazardBean> datalist=new ArrayList<>();
    int type=0;

    private SqlHandler handler;
    private Runnable callback=new Runnable() {
        @Override
        public void run() {
            requestInfo();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this, "info.db", callback);
    }

    private void requestInfo() {
        switch (type){
            case 0:
            case 1:
            case 6:
                datalist=handler.getGeohazardInfo(type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterTypeCode"),
                        getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"));
                ArrayList<String> list = new ArrayList<>();
                for (GeohazardBean info : datalist) {
                    list.add(info.getName());
                }
                recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
                break;
        }
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
            int tag=(int)view.getTag();
//            Intent i=new Intent(TitleResultListAct.this, SelectorAct.class);
//            i.putExtra("Type", tag);
//            startActivity(i);
        }
    };
}
