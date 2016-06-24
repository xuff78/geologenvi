package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class TitleListAct  extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> list=new ArrayList<>();
        list.add("灾害隐患点基础数据查询");
        list.add("市级监测点位基础数据查询");
        list.add("隐患点避险场所基础数据查询");
        list.add("重要点位避险场所基础数据查询");
        list.add("专业监测点位基础数据查询");
        list.add("治理点位基础数据查询");
        list.add("销号点位基础数据查询");
        recyclerView.setAdapter(new MenuListAdapter(this, list, null));
    }
}
