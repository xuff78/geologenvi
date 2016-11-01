package com.sichuan.geologenvi.act.report;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/30.
 */
public class CJ_GCZL_XCKP_edit extends AppFrameAct {

    private Map<String, String> infoMap=new HashMap<>();

    private SqlHandler handler;
    private HttpHandler httpHandler;

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(CJ_GCZL_XCKP_edit.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                if(method.equals(ConstantUtil.Method.CJ_DZZHD_XCKP)){

                }
            }
        });
    }

    private TextView updateDataBtn, delDataBtn, addDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gczl_xckp_edit);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            initData();
//            addDataBtn.setVisibility(View.GONE);
        }else{
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);
        }
        initHandler();
        handler=new SqlHandler(this);
    }

    private void initData() {

    }

    private void initView() {
        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);

    }
}
