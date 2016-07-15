package com.sichuan.geologenvi.act.statistics;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ChatAct extends AppFrameAct implements View.OnClickListener{

    private SqlHandler handler;
    private String tableName="";
    private TextView txt1,txt2, txt3, txt4;
    private View selection1, selection2, selection3, selection4;
    private WebView mWebView;
    private int requestType=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail_layout);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        tableName=getIntent().getStringExtra("TableName");
    }

    private void initView() {
        selection1=findViewById(R.id.selection1);
        selection2=findViewById(R.id.selection2);
        selection3=findViewById(R.id.selection3);
        selection4=findViewById(R.id.selection4);
        selection1.setOnClickListener(this);
        selection2.setOnClickListener(this);
        selection3.setOnClickListener(this);
        selection4.setOnClickListener(this);
        mWebView=(WebView)findViewById(R.id.mWebView);
//            mWebView.setBackgroundColor(1);
        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setBackgroundColor(getResources().getColor(R.color.hard_gray));
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.clearCache(false);
        mWebView.setFocusable(false);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String jsonString="";
                switch (requestType){
                    case 0:
//                        jsonString= JsonUtil.getWaterSiteJsonStr(datalist);
                        break;
                }
                mWebView.loadUrl("javascript:setData('" + jsonString + "')");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.selection1:
                DialogUtil.showSelectDialog(ChatAct.this, "统计区域", new String[]{}, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                break;
            case R.id.selection2:
                break;
            case R.id.selection3:
                break;
            case R.id.selection4:
                break;
        }
    }
}
