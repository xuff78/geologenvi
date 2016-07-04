package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.adapter.RainAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.RainBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RainAct extends AppFrameAct {


    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.hintTxt21:
                    adapter.setHours(1);
                    break;
                case R.id.hintTxt22:
                    adapter.setHours(3);
                    break;
                case R.id.hintTxt23:
                    adapter.setHours(12);
                    break;
                case R.id.hintTxt24:
                    adapter.setHours(24);
                    break;
            }
        }
    };
    private RainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rain_layout);

        _setHeaderTitle(getIntent().getStringExtra("Title"));

        initView();
    }

    private void initView() {
        findViewById(R.id.hintTxt21).setOnClickListener(listener);
        findViewById(R.id.hintTxt22).setOnClickListener(listener);
        findViewById(R.id.hintTxt23).setOnClickListener(listener);
        findViewById(R.id.hintTxt24).setOnClickListener(listener);
        ListView list=(ListView)findViewById(R.id.infoList);
        adapter=new RainAdapter(this, new ArrayList<RainBean>());
        list.setAdapter(adapter);
    }
}
