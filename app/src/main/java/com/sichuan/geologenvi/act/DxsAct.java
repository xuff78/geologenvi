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

                content.setText("成都市红层区面积为4075.42km2，占全市面积近1/3，覆盖11个区（市）县的94个乡（镇）,2014年,我站利用在用红层水井建立了3口监测井，2015年，通过18口红层地下水监测井的建设，初步形成成都市红层地下水监测网络,2016年继续新增了10口红层地下水监测井，加密了监测区域。");
                break;
            case 1:

                content.setText("根据目前监测情况，红层地下水类型主要为浅层红层风化裂隙水，水量、水质分布不均匀，整体情况良好。监测井点的布设在满足基本监测目的同时，点优先布设在易于维护、交通便利的地方，便于后期的维护和管理。");
                break;
        }
    }

    private void initView() {

        content = (TextView) this.findViewById(R.id.Layer_content);

    }
}