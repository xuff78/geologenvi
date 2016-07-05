package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.adapter.RainAdapter;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.RainBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.GlbsNet;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RainAct extends AppFrameAct {


    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pressView.setBackgroundResource(R.color.trans);
            view.setBackgroundResource(R.color.light_trans_white);
            pressView=view;
            if(adapter!=null)
            switch (view.getId()){
                case R.id.hintTxt21:
                    adapter.setHours(1);
                    break;
                case R.id.hintTxt22:
                    adapter.setHours(3);
                    break;
                case R.id.hintTxt23:
                    adapter.setHours(12);
                    break;
                case R.id.hintTxt24:
                    adapter.setHours(24);
                    break;
            }
        }
    };
    private RainAdapter adapter;
    private HttpHandler handler;
    private TextView hintTxt21;
    private ArrayList<RainBean> rainInfo=new ArrayList<>();
    private ListView list;
    private View pressView=hintTxt21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rain_layout);

        _setHeaderTitle(getIntent().getStringExtra("Title"));

        initHandler();
        initView();
        handler.getRainInfo();
    }

    private void initHandler() {
        handler=new HttpHandler(this, new CallBack(this){
            @Override
            public void onHTTPException(String method, String jsonMessage) {
                String jsonData=SharedPreferencesUtil.getString(RainAct.this, ConstantUtil.rain_info);
                showResult(jsonData);
            }

            @Override
            public void doSuccess(String method, String jsonData) {
                SharedPreferencesUtil.setString(RainAct.this, ConstantUtil.rain_info, jsonData);
                showResult(jsonData);
            }

            @Override
            public void onFailure(String method, JsonMessage jsonMessage) {
                String jsonData=SharedPreferencesUtil.getString(RainAct.this, ConstantUtil.rain_info);
                showResult(jsonData);
            }

            @Override
            public void oServerException(String method, String jsonMessage) {
                String jsonData=SharedPreferencesUtil.getString(RainAct.this, ConstantUtil.rain_info);
                showResult(jsonData);
            }

            private void showResult(String jsonData){
                if(jsonData.equals(SharedPreferencesUtil.FAILURE_STRING)){
                    DialogUtil.showInfoDailog(RainAct.this, "提示", GlbsNet.HTTP_ERROR_MESSAGE);
                }else {
                    rainInfo = JsonUtil.getRainInfo(jsonData);
                    adapter = new RainAdapter(RainAct.this, rainInfo);
                    list.setAdapter(adapter);
                }
            }
        });
    }

    private void initView() {
        hintTxt21= (TextView) findViewById(R.id.hintTxt21);
        pressView=hintTxt21;
        hintTxt21.setOnClickListener(listener);
        findViewById(R.id.hintTxt22).setOnClickListener(listener);
        findViewById(R.id.hintTxt23).setOnClickListener(listener);
        findViewById(R.id.hintTxt24).setOnClickListener(listener);
        list=(ListView)findViewById(R.id.infoList);
    }
}
