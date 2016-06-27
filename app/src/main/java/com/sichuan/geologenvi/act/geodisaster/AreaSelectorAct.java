package com.sichuan.geologenvi.act.geodisaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.PopupInfoItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/27.
 */
public class AreaSelectorAct extends AppFrameAct {

    private Runnable callback=new Runnable() {
        @Override
        public void run() {
            requestArea();
        }
    };

    int type=0;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SqlHandler handler;
    private ArrayList<AreaInfo> areas=new ArrayList<>();
    private String selectId=null;
    private AreaInfo selectedAreaInfo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle("选择行政区");
        initView();
        handler=new SqlHandler(this, "info.db", callback);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void requestArea(){
        areas=handler.getAreaInfo(selectId);
        if(areas.size()==0&&selectId!=null){
            Intent intent=new Intent();
            intent.putExtra("Area", selectedAreaInfo);
            setResult(0x22, intent);
            finish();
        }else if(areas.size()>0) {
            ArrayList<String> list = new ArrayList<>();
            for (AreaInfo info : areas) {
                list.add(info.getName());
            }
            recyclerView.setAdapter(new MenuListAdapter(AreaSelectorAct.this, list, listener));
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag=(int)view.getTag();
            selectedAreaInfo=areas.get(tag);
            selectId=selectedAreaInfo.getId();
            requestArea();
        }
    };
}
