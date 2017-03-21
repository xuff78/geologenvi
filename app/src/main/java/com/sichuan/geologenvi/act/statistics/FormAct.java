package com.sichuan.geologenvi.act.statistics;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.BanQianAdapte;
import com.sichuan.geologenvi.adapter.DisasterStatisticsAdapter;
import com.sichuan.geologenvi.adapter.FormAdapter;
import com.sichuan.geologenvi.adapter.FormBQBRAdapter;
import com.sichuan.geologenvi.adapter.FormGCZLAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


/**
 * Created by 可爱的蘑菇 on 2016/7/17.
 */
public class FormAct extends AppFrameAct implements View.OnClickListener{

    private SqlHandler handler;
    private int type=0;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type=getIntent().getIntExtra("Type", 1);
        if(type==102)
            setContentView(R.layout.zaihaidian_tongji);
        else if(type==103)
            setContentView(R.layout.banqian_tongji);
        else if(type==104)
            setContentView(R.layout.gczl_tongji);
        else
            setContentView(R.layout.form2_layout);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        queryForStatistics();
    }

    private void queryForStatistics() {
        switch (type){
            case 102://隐患点统计表格
                datalist=handler.getQueryResult2("NAME,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '00' THEN 1 ELSE 0 END) as XiePo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '01' THEN 1 ELSE 0 END) as HuaPo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '02' THEN 1 ELSE 0 END) as BengTa,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '03' THEN 1 ELSE 0 END) as NiShiLiu,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '04' THEN 1 ELSE 0 END) as DiMianTaXian,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '05' THEN 1 ELSE 0 END) as DiLieFeng,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '06' THEN 1 ELSE 0 END) as DiMianChenJiang,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '07' THEN 1 ELSE 0 END) as QiTa,\n" +
                                "SUM(ZHAA01A400) as HuShu,\n" +
                                "SUM(ZHAA01A390) as RenShu,\n" +
                                "SUM(ZHAA01A410) as ZiChan,\n" +
                                "SUM(CASE WHEN ZHAA01A210 is not null THEN 1 ELSE 0 END) as Suoyou ",
                        "SL_ZHAA01A as a left join SL_TATTR_DZZH_XZQH as b on (a.ZHAA01A110=b.CODE)   where  (a.ZHAA01A875 = 0 or a.ZHAA01A875 is null) group by ZHAA01A110",
                        "");
                DisasterStatisticsAdapter adapter=new DisasterStatisticsAdapter(this, datalist);
                recyclerView.setAdapter(adapter);
                break;
            case 1://监测水井统计
                datalist=handler.getQueryResult("QUXIAN,count(1) as ShuLiang,\n" +
                                " SUM(CASE WHEN QIYONG = '01' THEN 1 ELSE 0 END) as QIYONG ",
                        " SL_TBLJING group by QUXIAN",
                        "");
                recyclerView.setAdapter(new FormBQBRAdapter(this, datalist, 3));
                break;
            case 2://水土地质统计
//                datalist=handler.getQueryResult("QUXIAN,count(1) as ShuLiang,\n" +
//                                " SUM(CASE WHEN QIYONG = '01' THEN 1 ELSE 0 END) as QIYONG ",
//                        " SL_TBLJING group by QUXIAN",
//                        "");
//                recyclerView.setAdapter(new FormBQBRAdapter(this, datalist, 3));
                break;
            case 3://矿山统计
                datalist=handler.getQueryResult("KS_SSQX as NAME,\n"+
                                "sum(1) as ZongGong ",
                        "SL_KS_XX  group by KS_SSQX ",
                        "");
                recyclerView.setAdapter(new FormBQBRAdapter(this, datalist, 6));
                break;


            case 103://避险搬迁统计
                Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                int year = c.get(Calendar.YEAR);
                datalist=handler.getQueryResult2("NAME,\n"+
                                                "sum(case when zhdd04B013='"+String.valueOf(year-2)+"' then 1 else 0 end) as year1,\n"+
                                                "sum(case when zhdd04B013='"+String.valueOf(year-1)+"' then 1 else 0 end) as year2,\n"+
                                                "sum(case when zhdd04B013='"+String.valueOf(year)+"' then 1 else 0 end) as year3,\n"+
                                                "sum(case when zhdd04B013>='"+String.valueOf(year-2)+"' then 1 else 0 end) as xiaoji",
                        "SL_ZHDD04B as a left join SL_TATTR_DZZH_XZQH as b on (a.ZHDD04B040=b.CODE) group by ZHDD04B040",
                "");
                BanQianAdapte banqian=new BanQianAdapte(this, datalist);
                recyclerView.setAdapter(banqian);
                break;
            case 104://工程治理点位统计
                datalist=handler.getQueryResult2("NAME,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '00' THEN 1 ELSE 0 END) as XiePo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '01' THEN 1 ELSE 0 END) as HuaPo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '02' THEN 1 ELSE 0 END) as BengTa,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '03' THEN 1 ELSE 0 END) as NiShiLiu,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '04' THEN 1 ELSE 0 END) as DiMianTaXian,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '05' THEN 1 ELSE 0 END) as DiLieFeng,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '06' THEN 1 ELSE 0 END) as DiMianChenJiang,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '07' THEN 1 ELSE 0 END) as QiTa",
                        "SL_ZHCA01A as a left join SL_ZHAA01A as c on (a.ZHCA01A015=c.ZHAA01A010)  left join SL_TATTR_DZZH_XZQH as b on (a.ZHCA01A040=b.CODE) group by ZHCA01A040",
                        "");
                recyclerView.setAdapter(new FormGCZLAdapter(this, datalist));
                break;
            case 105://避险场所信息统计
                datalist=handler.getQueryResult("NAME,\n"+
                        "sum(1) as ZongGong ",
                        "SL_ZHDD02A as a left join SL_TATTR_DZZH_XZQH as b on (a.ZHDD02A040=b.CODE) group by ZHDD02A040 ",
                        "");
                recyclerView.setAdapter(new FormBQBRAdapter(this, datalist, 6));
                break;
            case 4://专业监测信息统计
                datalist=handler.getQueryResult2("city as NAME,\n" +
                                "count(distinct(disastername)) as zhandian,count(meterid) as shebei",
                        "sl_stationmeters group by city",
                        "");
                recyclerView.setAdapter(new FormBQBRAdapter(this, datalist, 7));
                break;

        }

    }

    //            list.add("隐患点统计图");
//            list.add("重点点位信息统计图");
//            list.add("隐患点统计表格");
//            list.add("地下水统计");
//            list.add("避险搬迁统计");
//            list.add("工程治理点位统计");
//            list.add("避险场所信息统计");
//            list.add("专业监测信息统计");
    private void initView() {
        TextView title1= (TextView) findViewById(R.id.title1);
        TextView title2= (TextView) findViewById(R.id.title2);
        TextView title3= (TextView) findViewById(R.id.title3);
        TextView title4= (TextView) findViewById(R.id.title4);
        if(type==1) {//地下水统计
            title2.setText("数量");
            title3.setText("弃用");
            title4.setVisibility(View.GONE);
        }
        else if(type==105) {//避险场所信息统计
            title2.setText("数量");
            title3.setVisibility(View.GONE);
            title4.setVisibility(View.GONE);
        }
        else if(type==3) {//矿山统计
            title2.setText("数量");
            title3.setVisibility(View.GONE);
            title4.setVisibility(View.GONE);
        }
        else if(type==4) {//专业监测信息统计
            title2.setText("站点数");
            title3.setText("专业设备数");
            title4.setVisibility(View.GONE);
        }
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
       // if(type==5)
       //     layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
       // else
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        if(type==2||type==5)
//            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        else
//            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void onClick(View view) {

    }
}
