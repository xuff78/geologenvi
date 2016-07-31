package com.sichuan.geologenvi.act.report;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.EditItemAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/29.
 */
public class CJ_DZZHD_XCKP_edit extends AppFrameAct {

    Map<String, String> infoMap=new HashMap<>();

    private SqlHandler handler;
    private HttpHandler httpHandler;
    private EditText zdmc, yfys, jcfzr, jcfzrlxdh, jczyjx, jczysdff, ydbjxh, ssmlfbr, ssmlfbrzbdh, qpxdwfzrzbdh, zabwddfzrzbdh, yljhdwfzrzbdh, czzywt, zgyj, remark, xcry;
    private TextView updateDataBtn, delDataBtn, addDataBtn, lxjqgm, zdwz, wxdx, ljcd, jcrq;
    private ImageView jcfzrlxdhkt_yes, jcfzrlxdhkt_no, ydpzdd_yes, ydpzdd_no, ydsslx_yes, ydsslx_no,qpxdwfzr_yes, qpxdwfzr_no,
            zabwdwfzr_yes, zabwdwfzr_no, yljhdwfzr_yes, yljhdwfzr_no;

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(CJ_DZZHD_XCKP_edit.this){

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
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            initData();
            addDataBtn.setVisibility(View.GONE);
        }else{
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);
        }
        initHandler();
        handler=new SqlHandler(this);
    }

    private void initData() {
        zdmc.setText(infoMap.get("zdmc".toUpperCase()));
        yfys.setText(infoMap.get("yfys".toUpperCase()));
        jcfzr.setText(infoMap.get("jcfzr".toUpperCase()));
        jcfzrlxdh.setText(infoMap.get("jcfzrlxdh".toUpperCase()));
        jczyjx.setText(infoMap.get("jczyjx".toUpperCase()));
        jczysdff.setText(infoMap.get("jczysdff".toUpperCase()));
        ydbjxh.setText(infoMap.get("ydbjxh".toUpperCase()));
        ssmlfbr.setText(infoMap.get("ssmlfbr".toUpperCase()));
        ssmlfbrzbdh.setText(infoMap.get("ssmlfbrzbdh".toUpperCase()));
        qpxdwfzrzbdh.setText(infoMap.get("qpxdwfzrzbdh".toUpperCase()));
        zabwddfzrzbdh.setText(infoMap.get("zabwddfzrzbdh".toUpperCase()));
        yljhdwfzrzbdh.setText(infoMap.get("yljhdwfzrzbdh".toUpperCase()));
        czzywt.setText(infoMap.get("czzywt".toUpperCase()));
        remark.setText(infoMap.get("remark".toUpperCase()));
        zgyj.setText(infoMap.get("zgyj".toUpperCase()));
        xcry.setText(infoMap.get("xcry".toUpperCase()));

        lxjqgm.setText(infoMap.get("lxjqgm".toUpperCase()));
        zdwz.setText(infoMap.get("zdwz".toUpperCase()));
        wxdx.setText(infoMap.get("wxdx".toUpperCase()));
        ljcd.setText(infoMap.get("ljcd".toUpperCase()));
        jcrq.setText(infoMap.get("jcrq".toUpperCase()));

        if(infoMap.get("jcfzrlxdhkt".toUpperCase()).equals("是"))
            jcfzrlxdhkt_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            jcfzrlxdhkt_no.setImageResource(R.mipmap.app_login_remember_sel);
        if(infoMap.get("ydpzdd".toUpperCase()).equals("有"))
            ydpzdd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            ydpzdd_no.setImageResource(R.mipmap.app_login_remember_sel);
        if(infoMap.get("ydsslx".toUpperCase()).equals("有"))
            ydsslx_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            ydsslx_no.setImageResource(R.mipmap.app_login_remember_sel);
        if(infoMap.get("qpxdwfzr".toUpperCase()).equals("有"))
            qpxdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            qpxdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
        if(infoMap.get("zabwdwfzr".toUpperCase()).equals("有"))
            zabwdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            zabwdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
        if(infoMap.get("yljhdwfzr".toUpperCase()).equals("有"))
            yljhdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            yljhdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
    }

    private void initView() {
        zdmc= (EditText) findViewById(R.id.zdmc);
        yfys= (EditText) findViewById(R.id.yfys);
        jcfzr= (EditText) findViewById(R.id.jcfzr);
        jcfzrlxdh= (EditText) findViewById(R.id.jcfzrlxdh);
        jczyjx= (EditText) findViewById(R.id.jczyjx);
        jczysdff= (EditText) findViewById(R.id.jczysdff);
        ydbjxh= (EditText) findViewById(R.id.ydbjxh);
        ssmlfbr= (EditText) findViewById(R.id.ssmlfbr);
        ssmlfbrzbdh= (EditText) findViewById(R.id.ssmlfbrzbdh);
        qpxdwfzrzbdh= (EditText) findViewById(R.id.qpxdwfzrzbdh);
        zabwddfzrzbdh= (EditText) findViewById(R.id.zabwddfzrzbdh);
        yljhdwfzrzbdh= (EditText) findViewById(R.id.yljhdwfzrzbdh);
        czzywt= (EditText) findViewById(R.id.czzywt);
        zgyj= (EditText) findViewById(R.id.zgyj);
        remark= (EditText) findViewById(R.id.remark);
        xcry= (EditText) findViewById(R.id.xcry);

        lxjqgm= (TextView) findViewById(R.id.lxjqgm);
        zdwz= (TextView) findViewById(R.id.zdwz);
        wxdx= (TextView) findViewById(R.id.wxdx);
        ljcd= (TextView) findViewById(R.id.ljcd);
        jcrq= (TextView) findViewById(R.id.jcrq);

        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);

        jcfzrlxdhkt_yes= (ImageView) findViewById(R.id.jcfzrlxdhkt_yes);
        jcfzrlxdhkt_no= (ImageView) findViewById(R.id.jcfzrlxdhkt_no);
        ydpzdd_yes= (ImageView) findViewById(R.id.ydpzdd_yes);
        ydpzdd_no= (ImageView) findViewById(R.id.ydpzdd_no);
        ydsslx_yes= (ImageView) findViewById(R.id.ydsslx_yes);
        ydsslx_no= (ImageView) findViewById(R.id.ydsslx_no);
        qpxdwfzr_yes= (ImageView) findViewById(R.id.qpxdwfzr_yes);
        qpxdwfzr_no= (ImageView) findViewById(R.id.qpxdwfzr_no);
        zabwdwfzr_yes= (ImageView) findViewById(R.id.zabwdwfzr_yes);
        zabwdwfzr_no= (ImageView) findViewById(R.id.zabwdwfzr_no);
        yljhdwfzr_yes= (ImageView) findViewById(R.id.yljhdwfzr_yes);
        yljhdwfzr_no= (ImageView) findViewById(R.id.yljhdwfzr_no);
    }
}
