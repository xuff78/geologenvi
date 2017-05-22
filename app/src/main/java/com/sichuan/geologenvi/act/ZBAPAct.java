package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.report.ExtendedViewPager;
import com.sichuan.geologenvi.act.report.TouchImageView;
import com.sichuan.geologenvi.act.report.ViewPagerExampleActivity;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.CateInfo;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.ZBAP;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.GlbsNet;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.views.Photo9Layout;
import com.sichuan.geologenvi.views.ScrollPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanqq on 2017/4/14.
 */
public class ZBAPAct extends AppFrameAct {
    private ArrayList<ZBAP> lstZBAP=new ArrayList<>();
    private TextView txtTitle;
    private WebView mView;
    private HttpHandler httpHandler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zbap_layout);

        _setHeaderTitle("值班安排");
        initView();

        initHandler();
        requestInfo();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        txtTitle = (TextView) findViewById(R.id.titleTxt);

        mView= (WebView) findViewById(R.id.mWebView);
    }



    private void requestInfo() {
        httpHandler.getZBAP();
    }

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(ZBAPAct.this){

            @Override
            public void doSuccess(String method, String jsonData) {

                lstZBAP=JsonUtil.getZBAP(jsonData);
                String html=lstZBAP.get(0).getContent();
                mView.loadDataWithBaseURL(null,html,"text/html","utf-8", null);


                int pos=getIntent().getIntExtra("pos", lstZBAP.size()-1);

//                WebView mViewPager = (WebView) findViewById(R.id.mWebView);
//                mViewPager.setAdapter(new TouchImageAdapter());
//                mViewPager.setCurrentItem(pos);
//                scrollPoints= (ScrollPoints) findViewById(R.id.scrollPoints);
//                scrollPoints.initPoints(mActivity, imgs.size(), pos);
//                mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                    }
//
//                    @Override
//                    public void onPageSelected(int position) {
//                        scrollPoints.changeSelectedPoint(position);
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//
//                    }
//                }
//            );
            }
        });
    }



}
