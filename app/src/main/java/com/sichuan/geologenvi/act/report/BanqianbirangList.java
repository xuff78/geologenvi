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
import com.sichuan.geologenvi.bean.AreaInfo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 可爱的蘑菇 on 2016/7/2.
 */
public class BanqianbirangList extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SqlHandler handler;
    private ArrayList<AreaInfo> areas=new ArrayList<>();
    private LinkedList<String> selectedDistrict=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_info_list);

        _setHeaderTitle("搬迁避让信息列表");
        initView();
        handler=new SqlHandler(this);
    }

    private void initView() {
        _setRightHomeText("筛选", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        findViewById(R.id.bottomLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BanqianbirangList.this, BanqianbirangEditMain.class));
            }
        });
    }
}
