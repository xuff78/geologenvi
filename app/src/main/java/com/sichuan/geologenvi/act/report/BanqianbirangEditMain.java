package com.sichuan.geologenvi.act.report;

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
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.frg.BanqianbirangBase;
import com.sichuan.geologenvi.frg.BanqianshenqingFrg;
import com.sichuan.geologenvi.frg.BanqianyanshouFrg;
import com.sichuan.geologenvi.frg.CJ_GCZL_XCKP1;
import com.sichuan.geologenvi.frg.CJ_GCZL_XCKP2;
import com.sichuan.geologenvi.frg.CJ_GCZL_XCKP3;
import com.sichuan.geologenvi.frg.FormInfoFrg;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_muti_detal_main);


        _setHeaderTitle("添加记录");
        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            addDataBtn.setVisibility(View.GONE);
            updateDataBtn.setVisibility(View.GONE);
        }else{
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);
        }
        _setHeaderTitle("成都市重大地质灾害防治工程项目现场检查记录表");
        handler=new SqlHandler(this);
        initView();
    }

    private void initView() {
        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);
        mIndicator = (PagerTabStrip) findViewById(R.id.pagertab);
        mIndicator.setTabIndicatorColorResource(R.color.normal_gray);
        mIndicator.setBackgroundResource(R.color.whitesmoke);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }


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
                    curfragment=new CJ_GCZL_XCKP1();
                    break;
                case 1:
                    curfragment=new CJ_GCZL_XCKP2();
                    break;
                case 2:
                    curfragment=new CJ_GCZL_XCKP3();
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
