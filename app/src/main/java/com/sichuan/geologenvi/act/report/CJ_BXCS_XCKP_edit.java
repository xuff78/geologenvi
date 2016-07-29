package com.sichuan.geologenvi.act.report;

import android.os.Bundle;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/30.
 */
public class CJ_BXCS_XCKP_edit extends AppFrameAct {

    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    private SqlHandler handler;
    private HttpHandler httpHandler;

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(CJ_BXCS_XCKP_edit.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                if(method.equals(ConstantUtil.Method.CJ_DZZHD_XCKP)){

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzzh_fzgz_jc);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        initHandler();
        handler=new SqlHandler(this);
    }

    private void initView() {

    }
}
