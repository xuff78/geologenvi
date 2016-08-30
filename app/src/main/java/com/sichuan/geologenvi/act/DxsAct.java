package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sichuan.geologenvi.R;


/**
 * Created by tanqq on 2016/8/30.
 */
public class DxsAct   extends AppFrameAct {

    private TextView content;

    private int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dxs);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        type=getIntent().getIntExtra("Type", 0);


        initView();
         switch (type) {
           case 0:
                content.setText("地下水基本概况");
                break;
            case 1:
                content.setText("地下水监测情况");
                break;
        }
    }

    private void initView() {

        content = (TextView) this.findViewById(R.id.Layer_content);

    }
}