package com.sichuan.geologenvi.act.statistics;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.FormAdapter;
import com.sichuan.geologenvi.adapter.FormBQBRAdapter;

import java.util.ArrayList;
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

        if(type==1)
            setContentView(R.layout.form_layout);
        else
            setContentView(R.layout.form2_layout);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        queryForStatistics();
    }

    private void queryForStatistics() {
        switch (type){
            case 1:
                datalist=handler.getQueryResult("NAME,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '00' THEN 1 ELSE 0 END) as XiePo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '01' THEN 1 ELSE 0 END) as HuaPo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '02' THEN 1 ELSE 0 END) as BengTa,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '03' THEN 1 ELSE 0 END) as NiShiLiu,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '04' THEN 1 ELSE 0 END) as DiMianTaXian,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '05' THEN 1 ELSE 0 END) as DiLieFeng,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '06' THEN 1 ELSE 0 END) as DiMianChenJiang,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '07' THEN 1 ELSE 0 END) as QiTa,\n" +
                                "SUM(CASE WHEN ZHAA01A210 is not null THEN 1 ELSE 0 END) as Suoyou ",
                        "SL_ZHAA01A as a left join SL_TATTR_DZZH_XZQH as b on (a.ZHAA01A110=b.CODE) group by ZHAA01A110",
                        "");
                recyclerView.setAdapter(new FormAdapter(this, datalist));
                break;
            case 2:
                String queryStr="select NAME,\n" +
                        "SUM(CASE WHEN QIYONG = '0' THEN 1 ELSE 0 END) as QiYong,\n" +
                        "SUM(CASE WHEN ZHAA01A210 = '01' THEN 1 ELSE 0 END) as HuaPo,\n" +
                        "SUM(CASE WHEN ZHAA01A210 = '02' THEN 1 ELSE 0 END) as BengTa\n" +
                        "from SL_TBLJING as a left join SL_TATTR_DZZH_XZQH as b on (a.ZHAA01A110=b.CODE) group by ZHAA01A110;";
                break;
            case 3:
                datalist=handler.getQueryResult("NAME,\n" +
                                "count(1) as ZongGong,\n" +
                                "SUM(CASE WHEN ZHDD04B230 = '1' THEN 1 ELSE 0 END) as WanCheng,\n" +
                                "SUM(CASE WHEN ZHDD04B240 = '1' THEN 1 ELSE 0 END) as YanShou ",
                        "SL_ZHDD04B as a left join SL_TATTR_DZZH_XZQH as b on (a.ZHDD04B040=b.CODE) group by ZHDD04B040",
                        "");
                recyclerView.setAdapter(new FormBQBRAdapter(this, datalist));
                break;
        }

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        if(type==1)
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        else
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void onClick(View view) {

    }
}
