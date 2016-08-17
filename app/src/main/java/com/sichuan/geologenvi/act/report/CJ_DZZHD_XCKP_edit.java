package com.sichuan.geologenvi.act.report;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.geodisaster.AreaSelectorAct;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.act.geodisaster.TitleResultListAct;
import com.sichuan.geologenvi.adapter.EditItemAdapter;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import org.json.JSONObject;

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
    private DatePickerDialog datePickerDialog;
    private EditText jcfzr, jcfzrlxdh, jczyjx, jczysdff, ydbjxh, ssmlfbr, ssmlfbrzbdh, qpxdwfzrzbdh, zabwddfzrzbdh, yljhdwfzrzbdh, czzywt, zgyj, remark, xcry;
    private TextView yfys,updateDataBtn, delDataBtn, addDataBtn, lxjqgm, zdwz, wxdx, ljcd, jcrq, zdmc;
    private ImageView jcfzrlxdhkt_yes, jcfzrlxdhkt_no, ydpzdd_yes, ydpzdd_no, ydsslx_yes, ydsslx_no,qpxdwfzr_yes, qpxdwfzr_no,
            zabwdwfzr_yes, zabwdwfzr_no, yljhdwfzr_yes, yljhdwfzr_no;
    private String zdid="";
    private String jcfzrlxdhkt="", ydpzdd="", ydsslx="", qpxdwfzr="", zabwdwfzr="", yljhdwfzr="";
    private String requesType="";
    private String Update="update", Delete="delete", Add="add";

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(CJ_DZZHD_XCKP_edit.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                if(method.equals(ConstantUtil.Method.CJ_DZZHD_XCKP)){
                    if(requesType.equals(Add)){

                    }
                    ToastUtils.displayTextShort(CJ_DZZHD_XCKP_edit.this, "操作成功");
                    setResult(0x99);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzzh_fzgz_jc);

        _setHeaderTitle("添加记录");
        handler=new SqlHandler(this);
        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            initData();
            addDataBtn.setVisibility(View.GONE);
            updateDataBtn.setVisibility(View.GONE);
            findViewById(R.id.arrowRight6).setVisibility(View.INVISIBLE);
        }else{
            findViewById(R.id.zdmcLayout).setOnClickListener(listener);
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);
        }
        initHandler();
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
        String wzString=infoMap.get("zdwz".toUpperCase());
        if(wzString!=null) {
//            zdwz.setText(handler.getDistrictName(wzString));
            zdwz.setText(wzString);
        }
        wxdx.setText(infoMap.get("wxdx".toUpperCase()));
        ljcd.setText(infoMap.get("ljcd".toUpperCase()));
        jcrq.setText(ActUtil.getFormatDate(infoMap.get("jcrq".toUpperCase())));

        jcfzrlxdhkt=infoMap.get("jcfzrlxdhkt".toUpperCase());
        if(jcfzrlxdhkt.equals("是")) {
            jcfzrlxdhkt_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(jcfzrlxdhkt.equals("否")) {
            jcfzrlxdhkt_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        ydpzdd=infoMap.get("ydpzdd".toUpperCase());
        if(ydpzdd.equals("有"))
            ydpzdd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else  if(ydpzdd.equals("无"))
            ydpzdd_no.setImageResource(R.mipmap.app_login_remember_sel);

        ydsslx=infoMap.get("ydsslx".toUpperCase());
        if(ydsslx.equals("有"))
            ydsslx_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else  if(ydsslx.equals("无"))
            ydsslx_no.setImageResource(R.mipmap.app_login_remember_sel);

        qpxdwfzr=infoMap.get("qpxdwfzr".toUpperCase());
        if(qpxdwfzr.equals("有"))
            qpxdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else if(qpxdwfzr.equals("无"))
            qpxdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);

        zabwdwfzr=infoMap.get("zabwdwfzr".toUpperCase());
        if(zabwdwfzr.equals("有"))
            zabwdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else if(zabwdwfzr.equals("无"))
            zabwdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);

        yljhdwfzr=infoMap.get("yljhdwfzr".toUpperCase());
        if(yljhdwfzr.equals("有"))
            yljhdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
        else if(yljhdwfzr.equals("无"))
            yljhdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
    }

    private void initView() {
        zdmc= (TextView) findViewById(R.id.zdmc);
        yfys= (TextView) findViewById(R.id.yfys);
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
        jcrq.setText(ActUtil.getDate());

        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        updateDataBtn.setOnClickListener(listener);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        delDataBtn.setOnClickListener(listener);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);
        addDataBtn.setOnClickListener(listener);

        jcfzrlxdhkt_yes= (ImageView) findViewById(R.id.jcfzrlxdhkt_yes);
        jcfzrlxdhkt_yes.setOnClickListener(listener);
        jcfzrlxdhkt_no= (ImageView) findViewById(R.id.jcfzrlxdhkt_no);
        jcfzrlxdhkt_no.setOnClickListener(listener);
        ydpzdd_yes= (ImageView) findViewById(R.id.ydpzdd_yes);
        ydpzdd_yes.setOnClickListener(listener);
        ydpzdd_no= (ImageView) findViewById(R.id.ydpzdd_no);
        ydpzdd_no.setOnClickListener(listener);
        ydsslx_yes= (ImageView) findViewById(R.id.ydsslx_yes);
        ydsslx_yes.setOnClickListener(listener);
        ydsslx_no= (ImageView) findViewById(R.id.ydsslx_no);
        ydsslx_no.setOnClickListener(listener);
        qpxdwfzr_yes= (ImageView) findViewById(R.id.qpxdwfzr_yes);
        qpxdwfzr_yes.setOnClickListener(listener);
        qpxdwfzr_no= (ImageView) findViewById(R.id.qpxdwfzr_no);
        qpxdwfzr_no.setOnClickListener(listener);
        zabwdwfzr_yes= (ImageView) findViewById(R.id.zabwdwfzr_yes);
        zabwdwfzr_yes.setOnClickListener(listener);
        zabwdwfzr_no= (ImageView) findViewById(R.id.zabwdwfzr_no);
        zabwdwfzr_no.setOnClickListener(listener);
        yljhdwfzr_yes= (ImageView) findViewById(R.id.yljhdwfzr_yes);
        yljhdwfzr_yes.setOnClickListener(listener);
        yljhdwfzr_no= (ImageView) findViewById(R.id.yljhdwfzr_no);
        yljhdwfzr_no.setOnClickListener(listener);

        findViewById(R.id.lxjqgmLayout).setOnClickListener(listener);
        findViewById(R.id.wxdxLayout).setOnClickListener(listener);
        findViewById(R.id.ljcdLayout).setOnClickListener(listener);
        findViewById(R.id.jcrqLayout).setOnClickListener(listener);

    }

    private View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.jcrqLayout:
                    String[] dateStart=jcrq.getText().toString().split("-");
                    datePickerDialog=new DatePickerDialog(CJ_DZZHD_XCKP_edit.this, mDateSetListener, Integer.valueOf(dateStart[0]),
                            Integer.valueOf(dateStart[1])-1, Integer.valueOf(dateStart[2]));
                    datePickerDialog.show();
                    break;
                case R.id.addDataBtn:
                    if(zdid==null||zdid.length()==0){
                        ToastUtils.displayTextShort(CJ_DZZHD_XCKP_edit.this, "请选择一个灾点");
                    }else if(xcry.getText().toString().length()==0){
                        ToastUtils.displayTextShort(CJ_DZZHD_XCKP_edit.this, "请输入检查人员姓名");
                    }else if(jcrq.getText().toString().length()==0) {
                        ToastUtils.displayTextShort(CJ_DZZHD_XCKP_edit.this, "请输入检查日期");
                    }else{
                        JSONObject jsonObj=new JSONObject();
                        JsonUtil.addJsonData(jsonObj, "zdid", zdid);
                        JsonUtil.addJsonData(jsonObj, "zdmc", zdmc.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "yfys", yfys.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "lxjqgm", lxjqgm.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "zdwz", zdwz.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "wxdx", wxdx.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "ljcd", ljcd.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jcfzr", jcfzr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jcfzrlxdh", jcfzrlxdh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jczyjx", jczyjx.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jczysdff", jczysdff.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jcfzrlxdhkt", jcfzrlxdhkt);  //
                        JsonUtil.addJsonData(jsonObj, "ydpzdd", ydpzdd);  //
                        JsonUtil.addJsonData(jsonObj, "ydsslx", ydsslx);  //
                        JsonUtil.addJsonData(jsonObj, "qpxdwfzr", qpxdwfzr);  //
                        JsonUtil.addJsonData(jsonObj, "zabwdwfzr", zabwdwfzr);  //
                        JsonUtil.addJsonData(jsonObj, "yljhdwfzr", yljhdwfzr);  //
                        JsonUtil.addJsonData(jsonObj, "ydbjxh", ydbjxh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "ssmlfbr", ssmlfbr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "ssmlfbrzbdh", ssmlfbrzbdh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "qpxdwfzrzbdh", qpxdwfzrzbdh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "zabwddfzrzbdh", zabwddfzrzbdh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "yljhdwfzrzbdh", yljhdwfzrzbdh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "czzywt", czzywt.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "zgyj", zgyj.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "remark", remark.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "xcry", xcry.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jcrq", jcrq.getText().toString());
                        requesType=Add;
                        httpHandler.addCJ_DZZHD_XCKP(jsonObj.toString());
                    }
//                    String jsonContent=getInfoString();
//                    handler.addBangqianBaseInfo(jsonContent);
                    break;
                case R.id.updateDataBtn:
                    break;
                case R.id.delDataBtn:
                    DialogUtil.showActionDialog(CJ_DZZHD_XCKP_edit.this, "提示", "确认要删除", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requesType=Delete;
                            httpHandler.delCJ_DZZHD_XCKP(infoMap.get("ID"));
                        }
                    });
                    break;
                case R.id.districtLayout:
                    Intent selectIntent=new Intent(CJ_DZZHD_XCKP_edit.this, AreaInputAct.class);
                    startActivityForResult(selectIntent, 0x11);
                    break;
                case R.id.jcfzrlxdhkt_yes:
                    jcfzrlxdhkt="是";
                    jcfzrlxdhkt_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    jcfzrlxdhkt_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.jcfzrlxdhkt_no:
                    jcfzrlxdhkt="否";
                    jcfzrlxdhkt_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    jcfzrlxdhkt_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.ydpzdd_yes:
                    ydpzdd="有";
                    ydpzdd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    ydpzdd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.ydpzdd_no:
                    ydpzdd="无";
                    ydpzdd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    ydpzdd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.ydsslx_yes:
                    ydsslx="有";
                    ydsslx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    ydsslx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.ydsslx_no:
                    ydsslx="无";
                    ydsslx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    ydsslx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.zabwdwfzr_yes:
                    zabwdwfzr="有";
                    zabwdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    zabwdwfzr_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.zabwdwfzr_no:
                    zabwdwfzr="无";
                    zabwdwfzr_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    zabwdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.qpxdwfzr_yes:
                    qpxdwfzr="有";
                    qpxdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    qpxdwfzr_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.qpxdwfzr_no:
                    qpxdwfzr="无";
                    qpxdwfzr_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    qpxdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.yljhdwfzr_yes:
                    yljhdwfzr="有";
                    yljhdwfzr_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    yljhdwfzr_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.yljhdwfzr_no:
                    yljhdwfzr="无";
                    yljhdwfzr_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    yljhdwfzr_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.ljcdLayout:
                    final String[] ljcdTxt={"了解","基本了解","不了解"};
                    DialogUtil.showSelectDialog(CJ_DZZHD_XCKP_edit.this, "了解程度", ljcdTxt, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ljcd.setText(ljcdTxt[i]);
                        }
                    });
                    break;
                case R.id.zdmcLayout:
                    Intent zdmcIntent=new Intent(CJ_DZZHD_XCKP_edit.this, SelectorAct.class);
                    zdmcIntent.putExtra("Report", true);
                    zdmcIntent.putExtra("Type", 7);
                    startActivityForResult(zdmcIntent, 0x10);
                    break;
                case R.id.zdwzLayout:

                    Intent intent2=new Intent(CJ_DZZHD_XCKP_edit.this, AreaSelectorAct.class);
                    startActivityForResult(intent2, 0x11);
                    break;
            }
        }
    };

    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            System.out.println("---> 设置后: year="+year+", month="+monthOfYear+",day="+dayOfMonth);
            jcrq.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    String[] disasterNames={"斜坡", "滑坡", "崩塌", "泥石流", "地面塌陷", "地裂缝", "地面沉降", "其它"};
    String[] sizeName={"特大型", "大型", "中型", "小型"};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x11) {
            data.setClass(CJ_DZZHD_XCKP_edit.this, TitleResultListAct.class); //查看线下数据库内容
            data.putExtra("TakeDataBack", true);
            data.putExtra("Type", 7);
            data.putExtra("Title", "灾害点名称");
            startActivityForResult(data, 0x20);
        }else if(resultCode==0x21) {
            MapBean mapBean= (MapBean) data.getSerializableExtra("InfoMap");
            zdmc.setText(mapBean.getMap().get("ZHAA01A020"));
            yfys.setText(mapBean.getMap().get("ZHAA01A830"));
            zdwz.setText(mapBean.getMap().get("ZHAA01A150"));
            String type=mapBean.getMap().get("ZHAA01A210");
            String size=mapBean.getMap().get("ZHAA01A890");
//            if(type!=null)
//                type=disasterNames[Integer.valueOf(type)];
            if(size!=null) {
                if(size.equals("A"))
                    size="特大型";
                else if(size.equals("B"))
                    size="大型";
                else if(size.equals("C"))
                    size="中型";
                else if(size.equals("D"))
                    size="小型";

            }
            lxjqgm.setText(type+"  "+size);
            zdid=mapBean.getMap().get("ZHAA01A010");

        }else if(resultCode==0x22){
            AreaInfo area= (AreaInfo) data.getSerializableExtra("Area");
            zdwz.setText(area.getName());
        }
    }
}
