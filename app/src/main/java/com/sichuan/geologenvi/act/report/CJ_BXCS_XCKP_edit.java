package com.sichuan.geologenvi.act.report;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/30.
 */
public class CJ_BXCS_XCKP_edit extends AppFrameAct {

    private Map<String, String> infoMap=new HashMap<>();

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

    private DatePickerDialog datePickerDialog;
    private View yjsbssLayout, shwzcbLayout, spcbLayout, jcdateLayout;
    private TextView  updateDataBtn, delDataBtn, addDataBtn, yjsbss, shwzcb, spcb, jcdate;
    private ImageView xxgsp_yes, xxgsp_no, xlzsp_yes, xlzsp_no;
    private EditText azryssyhdname, jhazrs, sjrnrs, fczryazcs, syxpj, dzzhpgjy, fzr, fzrphone, glry, glryphone, zzzyr, zzzyrphone, sfkztqbr, ylkzcs, czwt, zgjy, bz, jcry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bxcs_xckp_jc);


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
        azryssyhdname.setText(infoMap.get("azryssyhdname".toUpperCase()));
        jhazrs.setText(infoMap.get("jhazrs".toUpperCase()));
        sjrnrs.setText(infoMap.get("sjrnrs".toUpperCase()));
        fczryazcs.setText(infoMap.get("fczryazcs".toUpperCase()));
        syxpj.setText(infoMap.get("syxpj".toUpperCase()));
        dzzhpgjy.setText(infoMap.get("dzzhpgjy".toUpperCase()));
        fzr.setText(infoMap.get("fzr".toUpperCase()));
        fzrphone.setText(infoMap.get("fzrphone".toUpperCase()));
        glry.setText(infoMap.get("glry".toUpperCase()));
        glryphone.setText(infoMap.get("glryphone".toUpperCase()));
        zzzyr.setText(infoMap.get("zzzyr".toUpperCase()));
        zzzyrphone.setText(infoMap.get("zzzyrphone".toUpperCase()));
        sfkztqbr.setText(infoMap.get("sfkztqbr".toUpperCase()));
        ylkzcs.setText(infoMap.get("ylkzcs".toUpperCase()));
        czwt.setText(infoMap.get("czwt".toUpperCase()));
        zgjy.setText(infoMap.get("zgjy".toUpperCase()));
        bz.setText(infoMap.get("bz".toUpperCase()));
        jcry.setText(infoMap.get("jcry".toUpperCase()));

        yjsbss.setText(infoMap.get("yjsbss".toUpperCase()));
        shwzcb.setText(infoMap.get("shwzcb".toUpperCase()));
        spcb.setText(infoMap.get("spcb".toUpperCase()));
        jcdate.setText(infoMap.get("jcdate".toUpperCase()));

        if(infoMap.get("xxgsp".toUpperCase()).equals("有"))
            xxgsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            xxgsp_no.setImageResource(R.mipmap.app_login_remember_sel);
        if(infoMap.get("ydpzdd".toUpperCase()).equals("有"))
            xlzsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else
            xlzsp_no.setImageResource(R.mipmap.app_login_remember_sel);
    }

    private View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.jcdateLayout:
                    String[] dateStart=jcdate.getText().toString().split("-");
                    datePickerDialog=new DatePickerDialog(CJ_BXCS_XCKP_edit.this, mDateSetListener, Integer.valueOf(dateStart[0]),
                            Integer.valueOf(dateStart[1])-1, Integer.valueOf(dateStart[2]));
                    datePickerDialog.show();
                    break;
                case R.id.addDataBtn:
//                    String jsonContent=getInfoString();
//                    handler.addBangqianBaseInfo(jsonContent);
                    break;
                case R.id.updateDataBtn:
                    break;
                case R.id.delDataBtn:
                    break;
                case R.id.districtLayout:
                    Intent selectIntent=new Intent(CJ_BXCS_XCKP_edit.this, AreaInputAct.class);
                    startActivityForResult(selectIntent, 0x11);
                    break;
                case R.id.xxgsp_yes:
                    xxgsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    xxgsp_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.xxgsp_no:
                    xxgsp_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    xxgsp_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.xlzsp_yes:
                    xlzsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    xlzsp_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.xlzsp_no:
                    xlzsp_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    xlzsp_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
            }
        }
    };

    private void initView() {

        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);
        yjsbss=(TextView) findViewById(R.id.yjsbss);
        shwzcb=(TextView) findViewById(R.id.shwzcb);
        spcb=(TextView) findViewById(R.id.spcb);
        jcdate=(TextView) findViewById(R.id.jcdate);

        xxgsp_yes=(ImageView)findViewById(R.id.xxgsp_yes);
        xxgsp_yes.setOnClickListener(listener);
        xxgsp_no=(ImageView)findViewById(R.id.xxgsp_no);
        xxgsp_no.setOnClickListener(listener);
        xlzsp_yes=(ImageView)findViewById(R.id.xlzsp_yes);
        xlzsp_yes.setOnClickListener(listener);
        xlzsp_no=(ImageView)findViewById(R.id.xlzsp_no);
        xlzsp_no.setOnClickListener(listener);

        azryssyhdname=(EditText)findViewById(R.id.azryssyhdname);
        jhazrs=(EditText)findViewById(R.id.jhazrs);
        sjrnrs=(EditText)findViewById(R.id.sjrnrs);
        fczryazcs=(EditText)findViewById(R.id.fczryazcs);
        syxpj=(EditText)findViewById(R.id.syxpj);
        dzzhpgjy=(EditText)findViewById(R.id.dzzhpgjy);
        fzr=(EditText)findViewById(R.id.fzr);
        fzrphone=(EditText)findViewById(R.id.fzrphone);
        glry=(EditText)findViewById(R.id.glry);
        glryphone=(EditText)findViewById(R.id.glryphone);
        zzzyr=(EditText)findViewById(R.id.zzzyr);
        zzzyrphone=(EditText)findViewById(R.id.zzzyrphone);
        sfkztqbr=(EditText)findViewById(R.id.sfkztqbr);
        ylkzcs=(EditText)findViewById(R.id.ylkzcs);
        czwt=(EditText)findViewById(R.id.czwt);
        zgjy=(EditText)findViewById(R.id.zgjy);
        bz=(EditText)findViewById(R.id.bz);
        jcry=(EditText)findViewById(R.id.jcry);
        jcdate.setText(ActUtil.getDate());

        findViewById(R.id.yjsbssLayout).setOnClickListener(listener);
        findViewById(R.id.shwzcbLayout).setOnClickListener(listener);
        findViewById(R.id.spcbLayout).setOnClickListener(listener);
        findViewById(R.id.jcdateLayout).setOnClickListener(listener);
    }

    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            System.out.println("---> 设置后: year="+year+", month="+monthOfYear+",day="+dayOfMonth);
            jcdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };
}
