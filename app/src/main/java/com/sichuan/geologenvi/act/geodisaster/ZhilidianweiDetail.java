package com.sichuan.geologenvi.act.geodisaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.frg.FormInfoFrg;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ZhilidianweiDetail  extends AppFrameAct {

    private PagerTabStrip mIndicator;
    private ViewPager mViewPager;
    private TabAdapter mAdapter;
    private MapBean mapBean;
    private SqlHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_muti_detal_main);


        mapBean= (MapBean) getIntent().getSerializableExtra("InfoMap");
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        handler=new SqlHandler(this);
        initView();
        _setRightHomeText("定位", new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent intent2 = new Intent(TitleResultListAct.this, SelectorAct.class);
//                intent2.putExtra("Type", type);
//                intent2.putExtra("Title",getIntent().getStringExtra("Title"));
//                startActivityForResult(intent2, 0x11);
            }
        });
    }

    private void initView() {
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
        public String[] TITLES = new String[] { "基本信息", "隐患点", "防灾预案", "工作明白卡", "避灾明白卡" };


        public TabAdapter(FragmentManager fm)
        {
            super(fm);
        }


        @Override
        public Fragment getItem(int arg0)
        {

            Bundle b=new Bundle();
            MapBean mBean=new MapBean();
            String tableName="";
            String id=mapBean.getMap().get("ZHCA01A015"); //隐患点ID
            curfragment = new FormInfoFrg();
            ArrayList<Map<String, String>> maps;
            switch (arg0){
                case 0:
                    tableName="SL_ZHCA01A";
                    mBean=mapBean;
                    b.putSerializable("InfoMap", mBean);
                    break;
                case 1:
                    tableName="SL_ZHAA01A";
                    maps=handler.getQueryResult("SL_ZHAA01A", " where ZHAA01A010 = '"+id+"'");
                    if(maps.size()>0) {
                        mBean.setMap(maps.get(0));
                        b.putSerializable("InfoMap", mBean);
                    }
                    break;
                case 2:
                    tableName="SL_JCBA02A";
                    maps=handler.getQueryResult("SL_JCBA02A", " where JCBA02A010 = '"+id+"'");
                    if(maps.size()>0) {
                        mBean.setMap(maps.get(0));
                        b.putSerializable("InfoMap", mBean);
                    }
                    break;
                case 3:
                    tableName="SL_JCBA03A";
                    maps=handler.getQueryResult("SL_JCBA03A", " where JCBA03A010 = '"+id+"'");
                    if(maps.size()>0) {
                        mBean.setMap(maps.get(0));
                        b.putSerializable("InfoMap", mBean);
                    }
                    break;
                case 4:
                    tableName="SL_JCBA04A";
                    maps=handler.getQueryResult("SL_JCBA04A", " where JCBA04A010 = '"+id+"'");
                    if(maps.size()>0) {
                        mBean.setMap(maps.get(0));
                        b.putSerializable("InfoMap", mBean);
                    }
                    break;
            }
            b.putString("TableName", tableName);
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
