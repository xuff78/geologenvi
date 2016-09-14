package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.widget.ListView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.bean.MapBean;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ItemDetailAct   extends AppFrameAct {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infolist);

        _setHeaderTitle(getIntent().getStringExtra("Title"));

        initView();
    }

    private void initView() {
        MapBean mapBean= (MapBean) getIntent().getSerializableExtra("InfoMap");
        ListView list=(ListView)findViewById(R.id.infoList);


        list.setAdapter(new ActivityInfoAdapter(this,mapBean.getMap(), getIntent().getStringExtra("TableName")));
    }
}
