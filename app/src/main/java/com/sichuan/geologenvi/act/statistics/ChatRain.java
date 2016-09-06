package com.sichuan.geologenvi.act.statistics;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.RainAdapter;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.bean.RainHourItem;
import com.sichuan.geologenvi.bean.RainsBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.GlbsNet;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/9/1.
 */
public class ChatRain extends AppFrameAct implements View.OnClickListener{

    private HttpHandler handler;
    private String tableName="";
    private TextView txt1,txt2, txt3, txt4;
    private View selection1, selection2, selection3, selection4;
    private WebView mWebView;
    private int requestType=0;
    private String area="", disasterType="", isCancel="";
    private int statisticsType=0;
    private String jsonString="";

    private String[] areaTxt;
    private TextView txtCount;
    private int pos=0;
    private Dialog dialog;
    RainsBean rains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_rain_layout);

        areaTxt=getIntent().getStringArrayExtra("Names");
        pos=getIntent().getIntExtra("pos", 0);
        rains= (RainsBean) getIntent().getSerializableExtra("rains");
        _setHeaderTitle("雨量统计");
        initView();
        initHandler();

        handler.getRainInfo(areaTxt[pos]);
    }

    private void initView() {

        txtCount=(TextView) findViewById(R.id.countLayout);
        txt1= (TextView) findViewById(R.id.txt1);
        txt2= (TextView) findViewById(R.id.txt2);
        txt3= (TextView) findViewById(R.id.txt3);
        txt4= (TextView) findViewById(R.id.txt4);
        txt1.setText(rains.getItems()[pos].getArea());
        txt2.setText(areaTxt[pos]);
        txt3.setText(rains.getItems()[pos].getHour24());

        selection2=findViewById(R.id.selection2);
        selection2.setOnClickListener(this);
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
            case R.id.selection2:
                DialogUtil.showSelectDialog(ChatRain.this, "选择站点", areaTxt, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txt2.setText(areaTxt[i]);
                        txt1.setText(rains.getItems()[i].getArea());
                        txt3.setText(rains.getItems()[i].getHour24());
                        pos=i;
                        handler.getRainInfo(areaTxt[pos]);
                    }
                });
                break;
        }
    }

    public String getTypeJson(ArrayList<RainHourItem> datalist) {
        String jsonData="";
        if(datalist.size()>0) {
            try {
                JSONObject jsonObj = new JSONObject();

                JSONArray array1 = new JSONArray();
                JSONArray arrayTag = new JSONArray();

                for (int i=0;i<datalist.size(); i++) {
                    RainHourItem rainHour=datalist.get(i);

                    JSONObject subValue = new JSONObject();
                    JSONObject subjosn=new JSONObject();
                    subjosn.put("name", rainHour.getRain());
                    subjosn.put("value", rainHour.getRain());

                    JSONObject labeljosn=new JSONObject();
                    labeljosn.put("show", true);
                    labeljosn.put("position", "top");
                    subjosn.put("label", labeljosn);

                    subValue.put("data", subjosn);
                    array1.put(i, subValue);

                    JSONObject subName = new JSONObject();
                    subName.put("data", rainHour.getDateTime());
                    arrayTag.put(i, subName);

                }

                jsonObj.put("FristFormInfo1", array1);
                jsonObj.put("tags", arrayTag);

                jsonData = jsonObj.toString();
                Log.i("Json", jsonObj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonData;
    }

    private void initHandler() {
        handler=new HttpHandler(this, new CallBack(this){
            @Override
            public void onHTTPException(String method, String jsonMessage) {
                showDialog();
            }

            @Override
            public void doSuccess(String method, String jsonData) {
                ArrayList<RainHourItem> rainInfo = JsonUtil.getRainInfoFor24h(jsonData);
                jsonString=getTypeJson(rainInfo);
                mWebView.loadUrl("file:///android_asset/form2.html");

//                ArrayList<RainHourItem> datalist=new ArrayList<>();
//                for (int i=0;i<24;i++) {
//                    datalist.add(new RainHourItem("9-2\\n"+i+"时", i+""));
//                }
//                jsonString=getTypeJson(datalist);
//                mWebView.loadUrl("file:///android_asset/form2.html");
            }

            @Override
            public void onFailure(String method, JsonMessage jsonMessage) {
                showDialog();
            }

            @Override
            public void oServerException(String method, String jsonMessage) {
                showDialog();
            }

            private void showDialog(){
                DialogUtil.showActionDialog(ChatRain.this, "提示", "请求失败，是否重试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        handler.getRainInfo(areaTxt[pos]);
                    }
                });
            }
        });
    }
}
