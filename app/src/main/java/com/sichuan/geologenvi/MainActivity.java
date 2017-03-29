package com.sichuan.geologenvi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sichuan.geologenvi.DataBase.CDDHSqlHandler;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.MapAct;
import com.sichuan.geologenvi.act.MineListAct;
import com.sichuan.geologenvi.act.RadAct;
import com.sichuan.geologenvi.act.RainAct;
import com.sichuan.geologenvi.act.SearchAct;
import com.sichuan.geologenvi.act.TitleListAct;
import com.sichuan.geologenvi.act.YujingAct;
import com.sichuan.geologenvi.act.report.ReportEditListAct;
import com.sichuan.geologenvi.act.report.ReportHistoryList;
import com.sichuan.geologenvi.act.report.ViewPagerExampleActivity;
import com.sichuan.geologenvi.adapter.TopImgAdapter;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.VersionBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.views.AsycnDialog;
import com.sichuan.geologenvi.views.AutoScrollViewPager;
import com.sichuan.geologenvi.views.PSDdialog;
import com.sichuan.geologenvi.views.UpdateDailog;
import com.sichuan.geologenvi.views.YujingNotDialog;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppFrameAct {


    private String[] names1={"通讯录","地图","地质灾害","统计分析","数据采集","矿山地质","地质遗迹",
            "地下水","水土地质","雨量监测","预警","更多..."};
    private String[] names={"通讯录","地图","地质灾害","统计分析","数据采集","矿山地质","地质遗迹",
            "地下水","水土地质","雨量监测","预警","雷达回波","基础资料","数据同步","值班安排","收回"};
    private int[] ress={R.mipmap.icon_menu_2,R.mipmap.icon_menu_3,
            R.mipmap.icon_menu_5,R.mipmap.icon_menu_6,R.mipmap.icon_menu_7,R.mipmap.icon_menu_8,
            R.mipmap.icon_menu_9,R.mipmap.icon_menu_10,R.mipmap.icon_menu_11,R.mipmap.icon_menu_12,
             R.mipmap.icon_menu_14,R.mipmap.icon_menu_15,
            R.mipmap.icon_menu_1,R.mipmap.icon_menu_4,R.mipmap.icon_menu_13, R.mipmap.more1};

    private int[] ress1={R.mipmap.icon_menu_2,R.mipmap.icon_menu_3,
            R.mipmap.icon_menu_5,R.mipmap.icon_menu_6,R.mipmap.icon_menu_7,R.mipmap.icon_menu_8,
            R.mipmap.icon_menu_9,R.mipmap.icon_menu_10,R.mipmap.icon_menu_11,R.mipmap.icon_menu_12,
             R.mipmap.icon_menu_14,R.mipmap.more1 };
    private AutoScrollViewPager viewPager;
    private AsycnDialog dialog;

    private HttpHandler handler;
    private CDDHSqlHandler cddhhandler;
    private int ready=0;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    private YujingNotDialog yjDdialog;
    private String YJFBDate;
    String flag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _setHeaderGone();
        _setHeaderTitle(getResources().getString(R.string.app_name));
        initView();
        initHandler();
        String clear=SharedPreferencesUtil.getString(this, "ClearMapData");
        if(!clear.equals("1")){
            SharedPreferencesUtil.setString(this, "ClearMapData", "1");
            FileUtil.deleteAllFile("com.sichuan.geologenvi/sctiledatabase/");
        }
//        FileUtil.verifyStoragePermissions(this);

        flag=getIntent().getStringExtra("flag");

        if(flag!="") {
            LogUtil.i("flag", "reques flag---->:  "+flag);
            cddhhandler = new CDDHSqlHandler(this);
            YJFBDate=cddhhandler.getYJDate();
            if(YJFBDate=="")
                YJFBDate="2016-03-03";
            handler.getYujing(YJFBDate);
        }
    }

    private void initView() {
        viewPager= (AutoScrollViewPager) findViewById(R.id.galleryImgs);
        int galleryWidth=ScreenUtil.getScreenWidth(this);
        int galleryHeight= (int) (galleryWidth*(380/640f));
        TopImgAdapter adapter=new TopImgAdapter(this, new int[]{R.mipmap.test_pic1, R.mipmap.test_pic2});
        viewPager.setAdapter(adapter);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(galleryWidth, galleryHeight));
//        viewPager.startAutoScroll(2000);
        LinearLayout menuLayout= (LinearLayout) findViewById(R.id.menuLayout);
        int paddtop = ImageUtil.dip2px(this,20);
        int itemWidth = (ScreenUtil.getScreenWidth(this)-3)/4;
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(itemWidth, itemWidth);
        llp.topMargin=1;
        llp.rightMargin=1;
        LayoutInflater inflater=LayoutInflater.from(this);
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i=0;i<names1.length;i++){
            View v=inflater.inflate(R.layout.item_main_menu, null);
            ImageView menuIcon= (ImageView) v.findViewById(R.id.menuIcon);
            TextView menuName= (TextView) v.findViewById(R.id.menuName);
            menuName.setText(names1[i]);
            menuIcon.setImageResource(ress1[i]);
            v.setLayoutParams(llp);
            layout.addView(v);
            v.setTag(i);
            v.setOnClickListener(listener1);
            if(i%4==3){
                menuLayout.addView(layout);
                layout=new LinearLayout(this);
//                layout.setPadding(0,paddtop,0,0);
                layout.setOrientation(LinearLayout.HORIZONTAL);
            }
            if(names1[i].equals("预警")){
                setNews(v);
            }
        }
    }


    private void initHandler() {
        handler=new HttpHandler(this, new CallBack(this){
            @Override
            public void onHTTPException(String method, String jsonMessage) {
                toMainPage();
            }

            @Override
            public void doSuccess(String method, String jsonData) {
                doUpdate(method, jsonData);
            }

            @Override
            public void onFailure(String method, JsonMessage jsonMessage) {
                toMainPage();
            }

            @Override
            public void oServerException(String method, String jsonMessage) {
                toMainPage();
            }

        });
    }

    private void toMainPage(){
//        ready++;
//        if(ready==3) {
//            Intent i = new Intent(MainActivity.this, MainActivity.class);
//            startActivity(i);
//            finish();
//            overridePendingTransition(0, R.anim.zoom_out);
//        }
    }

    private void doUpdate(String method, String jsonData){
        //获取预警
        if(method.equals(ConstantUtil.Method.Yujing)) {
            datalist.addAll(JsonUtil.getDataMap(jsonData));
            if(datalist.size()>0){
                LogUtil.i("Yujing","count: "+datalist.size());
                yjDdialog=new YujingNotDialog(this, new YujingNotDialog.CallBack(){

                    @Override
                    public void cancel() {
                        toMainPage();
                    }

                    @Override
                    public void OK() {
                        Intent ii = new Intent(MainActivity.this, MainActivity.class);
                        ii.putExtra("flag", "");
                        startActivity(ii);
                        Intent i=new Intent();
                        i.setClass(MainActivity.this,ReportEditListAct.class);
                        i.putExtra("YJDate",YJFBDate);
                        i.putExtra("Title", "预警");
                        i.putExtra("Type",30);
//                        startActivity(i);
                        startActivityForResult(i,0);
                    }
                },"提示","有"+datalist.size()+"条新的预警信息，是否进行查看");
                yjDdialog.show();
            }else
                toMainPage();
        }
    }


    public void setImage(int k){
        LinearLayout menuLayout= (LinearLayout) findViewById(R.id.menuLayout);
        int paddtop = ImageUtil.dip2px(this,20);
        int itemWidth = (ScreenUtil.getScreenWidth(this)-3)/4;
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(itemWidth, itemWidth);
        llp.topMargin=1;
        llp.rightMargin=1;
        LayoutInflater inflater=LayoutInflater.from(this);
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        menuLayout.removeAllViews();
        if(k==1) {
            for (int i = 0; i < names.length; i++) {
                View v = inflater.inflate(R.layout.item_main_menu, null);
                ImageView menuIcon = (ImageView) v.findViewById(R.id.menuIcon);
                TextView menuName = (TextView) v.findViewById(R.id.menuName);
                menuName.setText(names[i]);
                menuIcon.setImageResource(ress[i]);
                v.setLayoutParams(llp);
                layout.addView(v);
                v.setTag(i);
                v.setOnClickListener(listener);
                if (i % 4 == 3) {
                    menuLayout.addView(layout);
                    layout = new LinearLayout(this);
//                layout.setPadding(0,paddtop,0,0);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                }

                if(names[i].equals("预警")){
                    setNews(v);
                }
            }
        }
        else{
            for (int i=0;i<names1.length;i++){
                View v=inflater.inflate(R.layout.item_main_menu, null);
                ImageView menuIcon= (ImageView) v.findViewById(R.id.menuIcon);
                TextView menuName= (TextView) v.findViewById(R.id.menuName);
                menuName.setText(names1[i]);
                menuIcon.setImageResource(ress1[i]);
                v.setLayoutParams(llp);
                layout.addView(v);
                v.setTag(i);
                v.setOnClickListener(listener1);
                if(i%4==3){
                    menuLayout.addView(layout);
                    layout=new LinearLayout(this);
//                layout.setPadding(0,paddtop,0,0);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                }
                if(names1[i].equals("预警")){
                    setNews(v);
                }
            }
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent();
            switch ((int)view.getTag()){
                case 0:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "通讯录");
                    i.putExtra("Type", "Contact");
                    startActivity(i);
                    break;
                case 1:
                    i.setClass(MainActivity.this, MapAct.class);
                    startActivity(i);
                    break;
                case 2:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "地质灾害");
                    i.putExtra("Type", "Disaster");
                    startActivity(i);
                    break;
                case 3:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "统计分析");
                    i.putExtra("Type", "Statistics");
                    startActivity(i);
                    break;
                case 4:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "数据采集");
                    i.putExtra("Type", "Report");
                    startActivity(i);
                    break;
                case 5:
                    //i.setClass(MainActivity.this, MineListAct.class);
                    //i.putExtra("Title", "矿山地质");
                    //i.putExtra("TableName", "SL_KS_DZHJ_XX");
                    //startActivity(i);
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "矿山地质");
                    i.putExtra("Type", "kuangshan");
                    startActivity(i);
                    break;
                case 6:
                    i.setClass(MainActivity.this, MineListAct.class);
                    i.putExtra("Title", "地质遗迹");
                    i.putExtra("TableName", "SL_DZYJBH");
                    startActivity(i);
                    break;
                case 7:
                    //i.setClass(MainActivity.this, MineListAct.class);
                    //i.putExtra("Title", "地下水");
                    //i.putExtra("TableName", "SL_TBLJING");
                    //startActivity(i);
                    //break;

                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "地下水");
                    i.putExtra("Type", "Jing");
                    startActivity(i);
                    break;
                case 8:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "水土地质");
                    i.putExtra("Type", "soil");
                    startActivity(i);
                    break;
                case 9:
                    i.setClass(MainActivity.this, RainAct.class);
                    i.putExtra("Title", "雨量监测");
                    startActivity(i);
                    break;
                case 10:
                    i.setClass(MainActivity.this,ReportEditListAct.class);
                    i.putExtra("Title", "预警");
                    i.putExtra("Type",30);
                    startActivity(i);
                    break;
                case 11:
//                    i.setClass(MainActivity.this, RadAct.class);
//                    i.putExtra("Title", "雷达回波");
//                    startActivity(i);
                    i.setClass(MainActivity.this, ViewPagerExampleActivity.class);
                    final ArrayList<String> imgUrls=new ArrayList<>();
                    imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329042400000.png");
                    imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329043000000.png");
                    imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329043600000.png");
                    imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329044200000.png");
                    i.putExtra("Images", imgUrls);
                    i.putExtra("Title", "雷达回波");
                    startActivity(i);
                    break;
                case 12:
                    i.setClass(MainActivity.this, SearchAct.class);
                    i.putExtra("Title", "基础资料");
                    i.putExtra("Type","zcfg");
                    startActivity(i);
                    break;
                case 13:
                    dialog=new AsycnDialog(MainActivity.this);
                    dialog.show();
                    break;
                case 14:
                    i.setClass(MainActivity.this, SearchAct.class);
                    i.putExtra("Title", "值班安排");
                    i.putExtra("Type","zhibananpai");
                    startActivity(i);
                    break;
                case 15:
                    setImage(0);
                    break;

            }
        }
    };

    View.OnClickListener listener1=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent();
            switch ((int)view.getTag()){
                case 0:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "通讯录");
                    i.putExtra("Type", "Contact");
                    startActivity(i);
                    break;
                case 1:
                    i.setClass(MainActivity.this, MapAct.class);
                    startActivity(i);
                    break;
                case 2:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "地质灾害");
                    i.putExtra("Type", "Disaster");
                    startActivity(i);
                    break;
                case 3:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "统计分析");
                    i.putExtra("Type", "Statistics");
                    startActivity(i);
                    break;
                case 4:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "数据采集");
                    i.putExtra("Type", "Report");
                    startActivity(i);
                    break;
                case 5:
                    //i.setClass(MainActivity.this, MineListAct.class);
                    //i.putExtra("Title", "矿山地质");
                    //i.putExtra("TableName", "SL_KS_DZHJ_XX");
                    //startActivity(i);
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "矿山地质");
                    i.putExtra("Type", "kuangshan");
                    startActivity(i);
                    break;
                case 6:
                    i.setClass(MainActivity.this, MineListAct.class);
                    i.putExtra("Title", "地质遗迹");
                    i.putExtra("TableName", "SL_DZYJBH");
                    startActivity(i);
                    break;
                case 7:
                    //i.setClass(MainActivity.this, MineListAct.class);
                    //i.putExtra("Title", "地下水");
                    //i.putExtra("TableName", "SL_TBLJING");
                    //startActivity(i);
                    //break;

                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "地下水");
                    i.putExtra("Type", "Jing");
                    startActivity(i);
                    break;
                case 8:
                    i.setClass(MainActivity.this, TitleListAct.class);
                    i.putExtra("Title", "水土地质");
                    i.putExtra("Type", "soil");
                    startActivity(i);
                    break;
                case 9:
                    i.setClass(MainActivity.this, RainAct.class);
                    i.putExtra("Title", "雨量监测");
                    startActivity(i);
                    break;
                case 10:
                    i.setClass(MainActivity.this,ReportEditListAct.class);
                    i.putExtra("Title", "预警");
                    i.putExtra("Type",30);
                    startActivity(i);
                    break;
                case 11:
                    setImage(1);
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

    public void setNews(View v) {
        TextView news= (TextView) v.findViewById(R.id.news);
        news.setVisibility(View.VISIBLE);
        news.setText("1");
    }
}
