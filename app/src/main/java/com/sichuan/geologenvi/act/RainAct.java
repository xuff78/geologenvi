package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.statistics.ChatRain;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.adapter.RainAdapter;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.RainBean;
import com.sichuan.geologenvi.bean.RainsBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.GlbsNet;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
                    setTopHintTxt(1);
                    adapter.setHours(1);
                    break;
                case R.id.hintTxt22:
                    setTopHintTxt(3);
                    adapter.setHours(3);
                    break;
                case R.id.hintTxt23:
                    setTopHintTxt(12);
                    adapter.setHours(12);
                    break;
                case R.id.hintTxt24:
                    setTopHintTxt(24);
                    adapter.setHours(24);
                    break;
            }
        }
    };
    private RainAdapter adapter;
    private HttpHandler handler;
    private TextView hintTxt21, hintTop;
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
        setTopHintTxt(1);
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


                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            RainBean[] rains=adapter.getData();
                            String[] areaTxt=new String[rains.length];
                            for(int j=0;j<rains.length;j++){
                                areaTxt[j]=rains[j].getName();
                            }
                            RainsBean rainsBean=new RainsBean();
                            rainsBean.setItems(rains);
                            Intent chat=new Intent(RainAct.this, ChatRain.class);
                            chat.putExtra("Names", areaTxt);
                            chat.putExtra("pos", i);
                            chat.putExtra("rains", rainsBean);
                            startActivity(chat);
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        hintTxt21= (TextView) findViewById(R.id.hintTxt21);
        hintTop=(TextView) findViewById(R.id.hintTop);
        pressView=hintTxt21;
        hintTxt21.setOnClickListener(listener);
        findViewById(R.id.hintTxt22).setOnClickListener(listener);
        findViewById(R.id.hintTxt23).setOnClickListener(listener);
        findViewById(R.id.hintTxt24).setOnClickListener(listener);
        list=(ListView)findViewById(R.id.infoList);
    }

    private void setTopHintTxt(int longtime){
        Format dateShow=new SimpleDateFormat("dd日HH时");
        Date date = new Date(System.currentTimeMillis());
        String to=dateShow.format(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -longtime);
        hintTop.setText("成都市观测站"+dateShow.format(cal.getTime())+"至"+to+"（"+longtime+"）小时降水");
    }
}
