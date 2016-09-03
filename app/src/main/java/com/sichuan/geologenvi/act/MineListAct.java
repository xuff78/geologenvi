package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.geodisaster.AreaSelectorAct;
import com.sichuan.geologenvi.act.geodisaster.YinhuandianDetail;
import com.sichuan.geologenvi.act.geodisaster.ZhilidianweiDetail;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MineListAct   extends AppFrameAct {
    TextView txtcount;
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

        if(tableName.equals("SL_DZYJBH")) {
            txtcount.setVisibility(View.GONE);

        }
        else {
            _setRightHomeText("筛选", new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(MineListAct.this, AreaSelectorAct.class);
                    startActivityForResult(intent2, 0x11);
                }
            });
        }
    }

    private void requestInfo() {
        if(tableName.equals("SL_KS_DZHJ_XX"))   //矿山的
            datalist=handler.getQueryResult(ConstantUtil.Mine,
                    "SL_KS_DZHJ_XX left join SL_XMDA on SL_KS_DZHJ_XX.KS_CK_GUID=SL_XMDA.CK_GUID", "");
        else if(tableName.equals("SL_DZYJBH")) //地址遗迹
            datalist=handler.getQueryResult(tableName, "");
        else if(tableName.equals("SL_TBLJING")) //地下水
            datalist=handler.getQueryResult(tableName, "");
        ArrayList<String> list = new ArrayList<>();
        String title="";
        if(tableName.equals("SL_KS_DZHJ_XX"))
            title="KSMC";
        else if(tableName.equals("SL_DZYJBH"))
            title="NAME";
        else if(tableName.equals("SL_TBLJING"))
            title="QUYU";
        txtcount.setText("共：   "+ datalist.size()+"    条记录");
        for (Map<String, String> info : datalist) {
            list.add(info.get(title));

        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
    }

    private void initView() {

        txtcount=(TextView)findViewById(R.id.count);
        txtcount.setVisibility(View.VISIBLE);
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
            else if(tableName.equals("SL_DZYJBH")) {
                i.setClass(MineListAct.this, ItemDetailAct.class);
            }
            else if(tableName.equals("SL_TBLJING"))
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
