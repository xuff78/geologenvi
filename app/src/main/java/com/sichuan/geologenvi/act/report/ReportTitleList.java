package com.sichuan.geologenvi.act.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.ItemDetailAct;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/23.
 */
public class ReportTitleList extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<String> list=new ArrayList<>();
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    private SqlHandler handler;
    private int type=0;
    private String titleKey="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        handler=new SqlHandler(this);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> list = new ArrayList<>();
        switch (type) {
            case 3:
                datalist = handler.getQueryResult(ConstantUtil.Mine,
                        "SL_KS_DZHJ_XX left join SL_XMDA on SL_KS_DZHJ_XX.KS_CK_GUID=SL_XMDA.CK_GUID", "");
                titleKey="KSMC";
                break;
            case 4:
                datalist=handler.getQueryResult("SL_TBLJING", "");
                titleKey="QUYU";
                break;
            case 5:
                datalist=handler.getQueryResult("SL_DZYJBH", "");
                titleKey="NAME";
                break;
            case 6:
                Intent i = new Intent(ReportTitleList.this, SelectorAct.class);
                i.putExtra("Type", 6);
                i.putExtra("Title", getIntent().getStringExtra("Title"));
                i.putExtra("Report", true);
                startActivityForResult(i, 0x10);
                return;
        }
        for (Map<String, String> info : datalist) {
            list.add(info.get(titleKey));
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag=(int)view.getTag();
            Intent i=getIntent();
            switch (type) {
                case 3:
                    i.setClass(ReportTitleList.this, ReportHistoryList.class);
                    i.putExtra("Title", datalist.get(tag).get(titleKey));
                    i.putExtra("Id", datalist.get(tag).get("CK_GUID"));
                    break;
                case 4:
                    i.setClass(ReportTitleList.this, ReportHistoryList.class);
                    i.putExtra("Title", datalist.get(tag).get(titleKey));
                    i.putExtra("Id", datalist.get(tag).get("JINGID"));
                    break;
                case 5:
                    i.setClass(ReportTitleList.this, ReportHistoryList.class);
                    i.putExtra("Title", datalist.get(tag).get(titleKey));
                    i.putExtra("Id", datalist.get(tag).get("ID"));
                    break;
                case 6:
                    i.setClass(ReportTitleList.this, ReportHistoryList.class);
                    i.putExtra("Title", datalist.get(tag).get(titleKey));
                    i.putExtra("Id", datalist.get(tag).get("ID"));
                    break;
            }
            i.putExtra("Type", type);
            startActivity(i);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x11){
            datalist=handler.getGeohazardInfo("", 6, data.getStringExtra("Name"), data.getStringExtra("disasterName"),
                    data.getStringExtra("disasterTypeCode"),
                    data.getStringExtra("disasterSizeCode"), data.getStringExtra("areaCode"),
                    data.getStringExtra("avoidCode"), data.getStringExtra("yearCode"));
            titleKey="ZHDD04B020";
            for (Map<String, String> info : datalist) {
                list.add(info.get(titleKey));
            }
            recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
        }
    }
}
