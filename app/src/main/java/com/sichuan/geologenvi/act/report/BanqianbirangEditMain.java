package com.sichuan.geologenvi.act.report;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.frg.BanqianbirangBase;
import com.sichuan.geologenvi.frg.BanqianshenqingFrg;
import com.sichuan.geologenvi.frg.BanqianyanshouFrg;
import com.sichuan.geologenvi.frg.CJ_GCZL_XCKP1;
import com.sichuan.geologenvi.frg.CJ_GCZL_XCKP2;
import com.sichuan.geologenvi.frg.CJ_GCZL_XCKP3;
import com.sichuan.geologenvi.frg.FormInfoFrg;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/2.
 */
public class BanqianbirangEditMain extends AppFrameAct {

    private PagerTabStrip mIndicator;
    private ViewPager mViewPager;
    private TabAdapter mAdapter;
    private MapBean mapBean;
    private SqlHandler handler;
    private TextView updateDataBtn, delDataBtn, addDataBtn;
    private Map<String, String> infoMap=new HashMap<>();
    private CJ_GCZL_XCKP1 frg1;
    private CJ_GCZL_XCKP2 frg2;
    private CJ_GCZL_XCKP3 frg3;
    private HttpHandler httpHandler;
    private String requesType="";
    private String Update="update", Delete="delete", Add="add";

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(BanqianbirangEditMain.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                ToastUtils.displayTextShort(BanqianbirangEditMain.this, "操作成功");
                setResult(0x99);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_muti_detal_main);


        _setHeaderTitle("添加记录");
        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            //addDataBtn.setVisibility(View.GONE);
            updateDataBtn.setVisibility(View.GONE);
        }else{
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);
        }
        _setHeaderTitle("成都市重大地质灾害防治工程项目现场检查记录表");
        initHandler();
        handler=new SqlHandler(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.bottomLayout).setVisibility(View.VISIBLE);
        frg1=new CJ_GCZL_XCKP1();
        frg2=new CJ_GCZL_XCKP2();
        frg3=new CJ_GCZL_XCKP3();
        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        updateDataBtn.setOnClickListener(listener);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        delDataBtn.setOnClickListener(listener);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);
        addDataBtn.setOnClickListener(listener);
        mIndicator = (PagerTabStrip) findViewById(R.id.pagertab);
        mIndicator.setTabIndicatorColorResource(R.color.normal_gray);
        mIndicator.setBackgroundResource(R.color.whitesmoke);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(4);
        mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    private View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.addDataBtn:
                    if(frg1.canbeCreate()){
                        JSONObject jsonObj=new JSONObject();
                        frg1.getDataByJson(jsonObj);
                        frg2.getDataByJson(jsonObj);
                        frg3.getDataByJson(jsonObj);
                        requesType=Add;
                        httpHandler.addCJ_GCZL_XCKP(jsonObj.toString());
                    }
                    break;
                case R.id.updateDataBtn:
                    break;
                case R.id.delDataBtn:
                    DialogUtil.showActionDialog(BanqianbirangEditMain.this, "提示", "确认要删除", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requesType=Delete;
                            httpHandler.delCJ_GCZL_XCKP(infoMap.get("ID"));
                        }
                    });
                    break;
            }
        }
    };

    public class TabAdapter extends FragmentPagerAdapter
    {

        Fragment curfragment;
        public String[] TITLES = new String[] { "基本信息", "质量控制", "安全措施" };


        public TabAdapter(FragmentManager fm)
        {
            super(fm);
        }


        @Override
        public Fragment getItem(int arg0)
        {

            Bundle b=new Bundle();
            switch (arg0){
                case 0:
                    curfragment=frg1;
                    break;
                case 1:
                    curfragment=frg2;
                    break;
                case 2:
                    curfragment=frg3;
                    break;
            }
            curfragment.setArguments(b);
            return curfragment;
        }


        @Override
        public CharSequence getPageTitle(int position)
        {
            return TITLES[position % TITLES.length];
        }


        @Override
        public int getCount()
        {
            return TITLES.length;
        }


    }
}
