package com.sichuan.geologenvi.act;


import android.os.Bundle;

import android.webkit.WebView;



import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sichuan.geologenvi.R;



/**
 * Created by tanqq on 2017/3/21.
 */
public class RadAct extends AppFrameAct {
//    private TextView  hintTxt;
    private WebView mWebView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rad);

        _setHeaderTitle("雷达回波");

        initView();
        SetDate();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {

        mWebView=(WebView)findViewById(R.id.mWebView);
    }

    private void SetDate() {

        String path = "";
        //得到可用的图片
        String url ="ftp://dihuanzhan:dihuanzhan87017762@218.89.201.18:65021/rad/Z_RADR_C_BCCD_20170313233000_P_DOR_SC_VIL_40X40_230_NUL.CCD.BIN.GIF";

        mWebView.loadUrl("file:///android_asset/gifshow.html");


    }



}
