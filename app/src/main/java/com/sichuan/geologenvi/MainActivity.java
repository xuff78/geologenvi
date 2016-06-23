package com.sichuan.geologenvi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.MapAct;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;

public class MainActivity extends AppFrameAct {

    private String[] names={"法律法规","通讯录","地图","数据同步","地质灾害","统计分析","数据采集","矿山地质","地址遗迹",
            "红层水井","水土地质","雨量监测"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _setHeaderTitle(getResources().getString(R.string.app_name));
        initView();
    }

    private void initView() {
        LinearLayout menuLayout= (LinearLayout) findViewById(R.id.menuLayout);
        int paddtop = ImageUtil.dip2px(this,20);
        int itemWidth = ScreenUtil.getScreenWidth(this)/4;
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(itemWidth, itemWidth);
        LayoutInflater inflater=LayoutInflater.from(this);
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i=0;i<12;i++){
            View v=inflater.inflate(R.layout.item_main_menu, null);
            ImageView menuIcon= (ImageView) v.findViewById(R.id.menuIcon);
            TextView menuName= (TextView) v.findViewById(R.id.menuName);
            menuName.setText(names[i]);
            v.setLayoutParams(llp);
            layout.addView(v);
            v.setTag(i);
            v.setOnClickListener(listener);
            if(i%4==3){
                menuLayout.addView(layout);
                layout=new LinearLayout(this);
//                layout.setPadding(0,paddtop,0,0);
                layout.setOrientation(LinearLayout.HORIZONTAL);
            }
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch ((int)view.getTag()){
                case 2:
                    startActivity(new Intent(MainActivity.this, MapAct.class));
                    break;
            }
        }
    };
}
