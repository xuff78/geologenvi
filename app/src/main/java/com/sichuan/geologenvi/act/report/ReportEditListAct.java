package com.sichuan.geologenvi.act.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.QueryStr;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.ItemDetailAct;
import com.sichuan.geologenvi.act.geodisaster.BixianbanqianDetail;
import com.sichuan.geologenvi.act.geodisaster.YinhuandianDetail;
import com.sichuan.geologenvi.act.geodisaster.ZhilidianweiDetail;
import com.sichuan.geologenvi.adapter.EditItemAdapter;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.utils.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/28.
 */
public class ReportEditListAct extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    int type=0;

    private SqlHandler handler;
    private HttpHandler httpHandler;

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(ReportEditListAct.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                if(method.equals(ConstantUtil.Method.CJ_DZZHD_XCKP)){
                    datalist.addAll(JsonUtil.getDataMap(jsonData));
                    recyclerView.setAdapter(new EditItemAdapter(ReportEditListAct.this, datalist, "ZDMC", listener));
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        initHandler();
        handler=new SqlHandler(this);
        requestInfo();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void requestInfo() {
        switch (type) {
            case 10:
                httpHandler.getCJ_DZZHD_XCKP(1, "");
                break;
            case 11:
                break;
            case 12:
                break;
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tableName="";
            Intent i=getIntent();
            int tag=(int)view.getTag();
            Map<String, String> map=datalist.get(tag);
            switch (getIntent().getIntExtra("Type", 0)) {
                case 10:
                    i.setClass(ReportEditListAct.this, ItemDetailAct.class);
                    break;
                case 11:
                    i.setClass(ReportEditListAct.this, ItemDetailAct.class);
                    break;
            }
            MapBean mapBean=new MapBean();
            mapBean.setMap(map);
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            startActivity(i);
        }
    };
}
