package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

import java.util.Map;

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
        list.setAdapter(new ActivityInfoAdapter(this, mapBean.getMap(),"SL_ZHAA01A"));
    }
}
