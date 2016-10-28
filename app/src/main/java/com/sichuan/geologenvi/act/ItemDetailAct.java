package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.bean.MapBean;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ItemDetailAct   extends AppFrameAct {

private MapBean mapBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infolist);

        _setHeaderTitle(getIntent().getStringExtra("Title"));

        initView();

        _setRightHomeText("定位", new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ItemDetailAct.this, MapAct.class);
                intent2.putExtra("InfoMap", mapBean);
                startActivityForResult(intent2, 0x11);
            }
        });
    }

    private void initView() {
         mapBean= (MapBean) getIntent().getSerializableExtra("InfoMap");
        ListView list=(ListView)findViewById(R.id.infoList);


        list.setAdapter(new ActivityInfoAdapter(this,mapBean.getMap(), getIntent().getStringExtra("TableName")));
    }
}
