package com.sichuan.geologenvi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sichuan.geologenvi.DataBase.DBManager;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.MapAct;
import com.sichuan.geologenvi.act.SearchAct;
import com.sichuan.geologenvi.act.TitleListAct;
import com.sichuan.geologenvi.act.contact.ActivityAddFriends;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.views.AutoScrollViewPager;

public class MainActivity extends AppFrameAct {

    private String[] names={"法律法规","通讯录","地图","数据同步","地质灾害","统计分析","数据采集","矿山地质","地址遗迹",
            "红层水井","水土地质","雨量监测"};
    private int[] ress={R.mipmap.icon_menu_1,R.mipmap.icon_menu_2,R.mipmap.icon_menu_3,R.mipmap.icon_menu_4,
            R.mipmap.icon_menu_5,R.mipmap.icon_menu_6,R.mipmap.icon_menu_7,R.mipmap.icon_menu_8,
            R.mipmap.icon_menu_9,R.mipmap.icon_menu_10,R.mipmap.icon_menu_11,R.mipmap.icon_menu_12};
    private AutoScrollViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _setHeaderGone();
        _setHeaderTitle(getResources().getString(R.string.app_name));
        initView();

//        FileUtil.verifyStoragePermissions(this);
    }

    private void initView() {
        viewPager= (AutoScrollViewPager) findViewById(R.id.galleryImgs);
        LinearLayout menuLayout= (LinearLayout) findViewById(R.id.menuLayout);
        int paddtop = ImageUtil.dip2px(this,20);
        int itemWidth = (ScreenUtil.getScreenWidth(this)-3)/4;
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(itemWidth, itemWidth);
        llp.topMargin=1;
        llp.rightMargin=1;
        LayoutInflater inflater=LayoutInflater.from(this);
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i=0;i<12;i++){
            View v=inflater.inflate(R.layout.item_main_menu, null);
            ImageView menuIcon= (ImageView) v.findViewById(R.id.menuIcon);
            TextView menuName= (TextView) v.findViewById(R.id.menuName);
            menuName.setText(names[i]);
            menuIcon.setImageResource(ress[i]);
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
            Intent i=new Intent();
            switch ((int)view.getTag()){
                case 0:
                    i.setClass(MainActivity.this, SearchAct.class);
                    i.putExtra("Title", "法律法规");
                    startActivity(i);
                    break;
                case 1:
                    i.setClass(MainActivity.this, ActivityAddFriends.class);
                    i.putExtra("Title", "通讯录");
                    startActivity(i);
                    break;
                case 2:
                    i.setClass(MainActivity.this, MapAct.class);
                    startActivity(i);
                    break;
                case 3:
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            SqlHandler handlr=new SqlHandler(MainActivity.this, "DBT.db");
                            handlr.getPersonInfo();
                        }
                    }.start();
                    break;
                case 4:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "地质灾害");
                    startActivity(i);
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                exithandler.sendEmptyMessageDelayed(0, 2000);
                return false;
            } else {
                ImageUtil.initImageLoader(this);
                ImageLoader imageloader=ImageLoader.getInstance();
                imageloader.clearMemoryCache();
                imageloader.destroy();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private static boolean isExit = false;

    Handler exithandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
