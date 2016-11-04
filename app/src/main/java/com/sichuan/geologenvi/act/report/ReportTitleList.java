package com.sichuan.geologenvi.act.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.ItemDetailAct;
import com.sichuan.geologenvi.act.geodisaster.QuXianSelectorAct;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/23.
 */
public class ReportTitleList extends AppFrameAct {
    TextView txtcount;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<String> list=new ArrayList<>();
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    private SqlHandler handler;
    private int type=0;
    private String titleKey="";
    private String tableName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type = getIntent().getIntExtra("Type", 0);
        tableName = getIntent().getStringExtra("TableName");
        handler = new SqlHandler(this);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        if (type == 5) {

        } else {
            _setRightHomeText("筛选", new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (type == 6) {
                        Intent i = new Intent(ReportTitleList.this, SelectorAct.class);
                        i.putExtra("Type", type);
                        i.putExtra("Title", getIntent().getStringExtra("Title"));
                        i.putExtra("Report", true);
                        startActivityForResult(i, 0x10);
                    } else {
                        Intent intent2 = new Intent(ReportTitleList.this, QuXianSelectorAct.class);
                        startActivityForResult(intent2, 0x11);
                    }
                }
            });
        }
        initView();

    }

    private void initView() {
        txtcount=(TextView)findViewById(R.id.count);
        txtcount.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> list = new ArrayList<>();
        switch (type) {
            case 3:
                tableName = "SL_KS_XX";
                datalist = handler.getQueryResult("SL_KS_XX", "");
                titleKey = "KS_NAME";
                break;
            case 4:
                tableName = "SL_TBLJING";
                datalist = handler.getQueryResult("SL_TBLJING", "");
                titleKey = "QUYU";
                break;
            case 5:
                tableName = "SL_DZYJBH";
                datalist = handler.getQueryResult("SL_DZYJBH", "");
                titleKey = "NAME";
                break;
            case 6:
                tableName = "SL_ZHDD04B";
                datalist = handler.getGeohazardInfo("", 6, "", "", "", "", "", "", "");
                titleKey = "ZHDD04B020";
                break;


//                Intent i = new Intent(ReportTitleList.this, SelectorAct.class);
//                i.putExtra("Type", 6);
//                i.putExtra("Title", getIntent().getStringExtra("Title"));
//                i.putExtra("Report", true);
//                startActivityForResult(i, 0x10);
//                return;
        }
        txtcount.setText("共：   "+ datalist.size()+"    条记录");
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
                    i.putExtra("Id", datalist.get(tag).get("ID"));
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
                    i.putExtra("Id", datalist.get(tag).get("ZHDD04B010"));
                    break;
            }
            i.putExtra("Type", type);
            startActivity(i);
        }
    };

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==0x11){
//            datalist=handler.getGeohazardInfo("", 6, data.getStringExtra("Name"), data.getStringExtra("disasterName"),
//                    data.getStringExtra("disasterTypeCode"),
//                    data.getStringExtra("disasterSizeCode"), data.getStringExtra("areaCode"),
//                    data.getStringExtra("avoidCode"), data.getStringExtra("yearCode"));
//            titleKey="ZHDD04B020";
//            for (Map<String, String> info : datalist) {
//                list.add(info.get(titleKey));
//            }
//            recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(tableName.equals("SL_KS_XX"))   //矿山的
            datalist=handler.getQueryResult(tableName, " where KS_SSQX='"+intent.getStringExtra("Area")+"'");
        else if(tableName.equals("SL_DZYJBH")) //地址遗迹
            datalist=handler.getQueryResult(tableName, "");
        else if(tableName.equals("SL_TBLJING")) //地下水
            datalist=handler.getQueryResult(tableName, " where QUXIAN='"+intent.getStringExtra("Area")+"'");
        else if(tableName.equals("SL_ZHDD04B")) //搬迁避让
        {
            datalist=handler.getGeohazardInfo("", 6, intent.getStringExtra("Name"), intent.getStringExtra("disasterName"),
                    intent.getStringExtra("disasterTypeCode"),
                    intent.getStringExtra("disasterSizeCode"), intent.getStringExtra("areaCode"),
                    intent.getStringExtra("avoidCode"), intent.getStringExtra("yearCode"));
        }
        ArrayList<String> list = new ArrayList<>();
        String title="";
        if(tableName.equals("SL_KS_XX"))
            title="KS_NAME";
        else if(tableName.equals("SL_DZYJBH"))
            title="NAME";
        else if(tableName.equals("SL_TBLJING"))
            title="QUYU";
        else if(tableName.equals("SL_ZHDD04B"))
            title="ZHDD04B020";
        txtcount.setText("共：   "+ datalist.size()+"    条记录");
        for (Map<String, String> info : datalist) {
            list.add(info.get(title));

        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));

    }
}
