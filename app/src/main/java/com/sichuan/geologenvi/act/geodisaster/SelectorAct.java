package com.sichuan.geologenvi.act.geodisaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.report.ReportEditListAct;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.PopupInfoItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/27.
 */
public class SelectorAct extends AppFrameAct {

    private EditText nameEdt, disasterNameEdt;
    private TextView areaTxt, disasterTypeTxt, disasterSizeTxt, avoidTxt, watchingAreaTxt, equipmentTxt, yearTxt;
    private ArrayList<PopupInfoItem> disasterType=new ArrayList<>();
    private ArrayList<PopupInfoItem> disasterSize=new ArrayList<>();
    private ArrayList<PopupInfoItem> avoidLevel=new ArrayList<>();
    private String disasterTypeCode="", disasterSizeCode="", areaCode="", avoidCode="", yearCode="";
    private int type=0;
    private SqlHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle("条件筛选");
        handler=new SqlHandler(this);
        initView();
    }

    private void initView() {
        disasterType.add(new PopupInfoItem("斜坡", "00"));
        disasterType.add(new PopupInfoItem("滑坡", "01"));
        disasterType.add(new PopupInfoItem("崩塌", "02"));
        disasterType.add(new PopupInfoItem("泥石流", "03"));
        disasterType.add(new PopupInfoItem("地面塌陷", "04"));
        disasterType.add(new PopupInfoItem("地裂缝", "05"));
        disasterType.add(new PopupInfoItem("地面沉降", "06"));
        disasterType.add(new PopupInfoItem("其他", "07"));
        disasterType.add(new PopupInfoItem("取消", ""));

        disasterSize.add(new PopupInfoItem("特大型", "A"));
        disasterSize.add(new PopupInfoItem("大型", "B"));
        disasterSize.add(new PopupInfoItem("中型", "C"));
        disasterSize.add(new PopupInfoItem("小型", "D"));
        disasterSize.add(new PopupInfoItem("取消", ""));

        avoidLevel.add(new PopupInfoItem("市", "A"));
        avoidLevel.add(new PopupInfoItem("区县", "B"));
        avoidLevel.add(new PopupInfoItem("乡镇", "C"));
        avoidLevel.add(new PopupInfoItem("街道社区", "D"));
        avoidLevel.add(new PopupInfoItem("村", "E"));
        avoidLevel.add(new PopupInfoItem("取消", ""));

        nameEdt=(EditText)findViewById(R.id.nameEdt);
        disasterNameEdt=(EditText)findViewById(R.id.disasterNameEdt);
        areaTxt=(TextView)findViewById(R.id.areaTxt);
        avoidTxt=(TextView)findViewById(R.id.avoidTxt);
        watchingAreaTxt=(TextView)findViewById(R.id.watchingAreaTxt);
        equipmentTxt=(TextView)findViewById(R.id.equipmentTxt);
        disasterTypeTxt=(TextView)findViewById(R.id.disasterTypeTxt);
        disasterSizeTxt=(TextView)findViewById(R.id.disasterSizeTxt);
        yearTxt=(TextView)findViewById(R.id.yearTxt);
        View disasterTypeLayout=findViewById(R.id.disasterTypeLayout);
        View areaLayout=findViewById(R.id.areaLayout);
        View disasterSizeLayout=findViewById(R.id.disasterSizeLayout);
        View avoidLayout=findViewById(R.id.avoidLayout);
        View equipmentLayout=findViewById(R.id.equipmentLayout);
        View watchingAreaLayout=findViewById(R.id.watchingAreaLayout);
        View yearLayout=findViewById(R.id.yearLayout);
        yearLayout.setOnClickListener(listener);
        findViewById(R.id.confirmBtn).setOnClickListener(listener);

        findViewById(R.id.areaLayout).setVisibility(View.VISIBLE);
        switch (type){
            case 1:
            case 7:
            case 8:
            case 9:
            case 10:
            case 20:
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                disasterTypeLayout.setVisibility(View.VISIBLE);
                disasterSizeLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                equipmentLayout.setVisibility(View.VISIBLE);
                watchingAreaLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.areaLayout).setVisibility(View.GONE);
                break;
            case 4:
            case 5:
            case 22:
                findViewById(R.id.disasterNameLayout).setVisibility(View.VISIBLE);
                avoidLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                break;
            case 6:
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                yearLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
            case 21:
                break;
            case 23:
                findViewById(R.id.areaLayout).setVisibility(View.GONE);
                findViewById(R.id.disasterNameLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.nameTxt)).setText("户主姓名");

                break;
//            case 10:
//                findViewById(R.id.areaLayout).setVisibility(View.GONE);
//                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
//                break;
            case 11:
                findViewById(R.id.areaLayout).setVisibility(View.GONE);
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                break;
            case 12:
                findViewById(R.id.areaLayout).setVisibility(View.GONE);
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                break;
        }
        equipmentLayout.setOnClickListener(listener);
        watchingAreaLayout.setOnClickListener(listener);
        avoidLayout.setOnClickListener(listener);
        areaLayout.setOnClickListener(listener);
        disasterTypeLayout.setOnClickListener(listener);
        disasterSizeLayout.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.confirmBtn:
                    String name=nameEdt.getText().toString();
                    String disasterName=disasterNameEdt.getText().toString();
                    Intent intent=getIntent();
                    if(type<=10||type>=20)
                        //intent.setClass(SelectorAct.this, TitleResultListAct.class); //查看线下数据库内容
                    intent=new Intent();
                    else
                        intent.setClass(SelectorAct.this, ReportEditListAct.class); //编辑线上数据库内容

                    intent.putExtra("Name", name);
                    intent.putExtra("disasterName", disasterName);
                    intent.putExtra("disasterTypeCode", disasterTypeCode);
                    intent.putExtra("disasterSizeCode", disasterSizeCode);
                    intent.putExtra("areaCode", areaCode);
                    intent.putExtra("avoidCode", avoidCode);
                    intent.putExtra("yearCode", yearCode);
                    if(getIntent().hasExtra("Report")){
                        setResult(0x11, intent);
                        finish();
                    }else {
                       // startActivity(intent);
                        setResult(0x11, intent);
                        finish();
                    }
                    break;
                case R.id.areaLayout:
                    Intent intent2=new Intent(SelectorAct.this, AreaSelectorAct.class);
                    startActivityForResult(intent2, 0x11);
                    break;
                case R.id.disasterTypeLayout:
                    final String items[]=new String[disasterType.size()];
                    for (int i=0;i<disasterType.size();i++){
                        items[i]=disasterType.get(i).getName();
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder.setTitle("灾害点类型"); //设置标题
                    builder.setItems(items,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            PopupInfoItem item=disasterType.get(which);
                            if(item.getContent().length()>0){
                                disasterTypeTxt.setText(item.getName());
                                disasterTypeCode=item.getContent();
                            }else{
                                disasterTypeTxt.setText("点击选择");
                                disasterTypeCode="";
                            }
                        }
                    });
                    builder.create().show();
                    break;
                case R.id.disasterSizeLayout:
                    final String items2[]=new String[disasterSize.size()];
                    for (int i=0;i<disasterSize.size();i++){
                        items2[i]=disasterSize.get(i).getName();
                    }
                    AlertDialog.Builder builder2=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder2.setTitle("灾害体规模"); //设置标题
                    builder2.setItems(items2,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            PopupInfoItem item=disasterSize.get(which);
                            if(item.getContent().length()>0){
                                disasterSizeTxt.setText(item.getName());
                                disasterSizeCode=item.getContent();
                            }else{
                                disasterSizeTxt.setText("点击选择");
                                disasterSizeCode="";
                            }
                        }
                    });
                    builder2.create().show();
                    break;
                case R.id.avoidLayout:
                    final String items3[]=new String[avoidLevel.size()];
                    for (int i=0;i<avoidLevel.size();i++){
                        items3[i]=avoidLevel.get(i).getName();
                    }
                    AlertDialog.Builder builder3=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder3.setTitle("避难等级"); //设置标题
                    builder3.setItems(items3,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            PopupInfoItem item=avoidLevel.get(which);
                            if(item.getContent().length()>0){
                                avoidTxt.setText(item.getName());
                                avoidCode=item.getContent();
                            }else{
                                avoidTxt.setText("点击选择");
                                avoidCode="";
                            }
                        }
                    });
                    builder3.create().show();
                    break;
                case R.id.equipmentLayout:
                    final String equipmentTypes[]=handler.getTypesQuery("SL_STATIONMETERS", "METERTYPE");
                    AlertDialog.Builder builder4=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder4.setTitle("设备类型"); //设置标题
                    builder4.setItems(equipmentTypes,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(which<equipmentTypes.length-1){
                                equipmentTxt.setText(equipmentTypes[which]);
                                disasterTypeCode=equipmentTypes[which];
                            }else{
                                equipmentTxt.setText("点击选择");
                                disasterTypeCode="";
                            }
                        }
                    });
                    builder4.create().show();
                    break;
                case R.id.watchingAreaLayout:
                    final String watchingAreas[]=handler.getTypesQuery("SL_STATIONMETERS", "CITY");
                    AlertDialog.Builder builder5=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder5.setTitle("行政区域"); //设置标题
                    builder5.setItems(watchingAreas,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(which<watchingAreas.length-1){
                                watchingAreaTxt.setText(watchingAreas[which]);
                                areaCode=watchingAreas[which];
                            }else{
                                watchingAreaTxt.setText("点击选择");
                                areaCode="";
                            }
                        }
                    });
                    builder5.create().show();
                    break;
                case R.id.yearLayout:
                    final String years[]=handler.getTypesQuery("SL_ZHDD04B", "ZHDD04B013");
                    AlertDialog.Builder builder6=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder6.setTitle("年份"); //设置标题
                    builder6.setItems(years,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if(which<years.length-1){
                                yearTxt.setText(years[which]);
                                yearCode=years[which];
                            }else{
                                yearTxt.setText("点击选择");
                                yearCode="";
                            }
                        }
                    });
                    builder6.create().show();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x22){
            AreaInfo area= (AreaInfo) data.getSerializableExtra("Area");
            areaTxt.setText(area.getName());
            areaCode=area.getCode();
        }
    }
}
