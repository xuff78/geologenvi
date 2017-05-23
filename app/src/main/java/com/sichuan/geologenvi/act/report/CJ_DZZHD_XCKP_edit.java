package com.sichuan.geologenvi.act.report;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.utils.UploadUtil;
import com.sichuan.geologenvi.views.Photo9Layout;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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


     private String lon="";
    private String lat="";

    private LinearLayout photoLayout;
    private View addIconView;
    private LayoutInflater inflater;

    Photo9Layout photo9Layout;
    private int imgItemWidth = 0;
    private LinkedHashMap<String, String> imgs=new LinkedHashMap<>();
    private ProgressDialog dialog;
    private HorizontalScrollView horiScroller;
    private String imgpath="";


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



//add cuikailei 20170522
        inflater = LayoutInflater.from(this);
        int scrennWidth = getWindowManager().getDefaultDisplay().getWidth();
        imgItemWidth = (scrennWidth - ImageUtil.dip2px(this, 20) - 6) / 4;







        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            initData();
            //addDataBtn.setVisibility(View.GONE);
            updateDataBtn.setVisibility(View.GONE);
            findViewById(R.id.arrowRight6).setVisibility(View.INVISIBLE);

            if(imgpath!=null&&imgpath.length()>0) {
                String[] paths=imgpath.split("\\|");
                final ArrayList<String> imgUrls = new ArrayList<>();
                for (int i = 0; i < paths.length; i++) {
                    imgUrls.add(paths[i]);
                }
                photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent(CJ_DZZHD_XCKP_edit.this, ViewPagerExampleActivity.class);
                        intent.putExtra("Images", imgUrls);
                        intent.putExtra("pos", position);
                        startActivity(intent);
                    }
                });
                photo9Layout.setImageUrl(ScreenUtil.getScreenWidth(this)- ImageUtil.dip2px(this, 40), imgUrls);
            }

        }else{
            findViewById(R.id.zdmcLayout).setOnClickListener(listener);
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);

            setAddView();
            photo9Layout.setVisibility(View.GONE);
        }

        if(getIntent().hasExtra("Map")) {
            MapBean mapBean= (MapBean) getIntent().getSerializableExtra("Map");
            zdmc.setText(mapBean.getMap().get("ZHAA01A020"));
            String temp=mapBean.getMap().get("ZHAA01A830");
            temp=temp.replaceAll("1","地震");
            temp=temp.replaceAll("2","降雨");
            temp=temp.replaceAll("3","人为因素");
            temp=temp.replaceAll("4","其它");
            yfys.setText(temp);
//            yfys.setText(mapBean.getMap().get("ZHAA01A830"));
            zdwz.setText(mapBean.getMap().get("ZHAA01A150"));
            lon=mapBean.getMap().get("ZHAA01A190");
            lat=mapBean.getMap().get("ZHAA01A200");
//            00：斜坡
//            01：滑坡
//            02：崩塌
//            03：泥石流
//            04：地面塌陷
//            05：地裂缝
//            06：地面沉降
//            07：其它

            String type = mapBean.getMap().get("ZHAA01A210");
            type=type.replaceAll("00","斜坡");
            type=type.replaceAll("01","滑坡");
            type=type.replaceAll("02","崩塌");
            type=type.replaceAll("03","泥石流");
            type=type.replaceAll("04","地面塌陷");
            type=type.replaceAll("05","地裂缝");
            type=type.replaceAll("06","地面沉降");
            type=type.replaceAll("07","其它");
            String size = mapBean.getMap().get("ZHAA01A890");
//            if(type!=null)
//                type=disasterNames[Integer.valueOf(type)];
            if (size != null) {
                if (size.equals("A"))
                    size = "特大型";
                else if (size.equals("B"))
                    size = "大型";
                else if (size.equals("C"))
                    size = "中型";
                else if (size.equals("D"))
                    size = "小型";

            }
            lxjqgm.setText("灾害类型:"+type + ";规模等级:" + size);

            String p=mapBean.getMap().get("ZHAA01A390");//威胁人口（人）
            String h=mapBean.getMap().get("ZHAA01A400");//威胁户数（户）
            String cc=mapBean.getMap().get("ZHAA01A410");//威胁财产（万元）
//            A:分散农户
//            B:聚集区
//            C:学校
//            D:场镇
//            E:县城
//            F:公路
//            G:河道
//            H:景区
//            I:其它

            String dx=mapBean.getMap().get("ZHAA01A380");//威胁对象
            dx=dx.replaceAll("A","分散农户");
            dx=dx.replaceAll("B","聚集区");
            dx=dx.replaceAll("C","学校");
            dx=dx.replaceAll("D","场镇");
            dx=dx.replaceAll("E","县城");
            dx=dx.replaceAll("F","公路");
            dx=dx.replaceAll("G","河道");
            dx=dx.replaceAll("H","景区");
            dx=dx.replaceAll("I","其它");
            wxdx.setText(h+"户;"+p+"人;"+cc+"万元;"+dx);
            zdid = mapBean.getMap().get("ZHAA01A010");





        }

        initHandler();




    }

    private void initData() {
        lon=infoMap.get("xzb".toUpperCase());
        lat=infoMap.get("yzb".toUpperCase());
        zdid=infoMap.get("zhdbh".toUpperCase());
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


         imgpath=infoMap.get("path".toUpperCase());


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





        horiScroller= (HorizontalScrollView) findViewById(R.id.horiScroller);
        photoLayout= (LinearLayout) findViewById(R.id.photoLayout_dzzh);
        photo9Layout= (Photo9Layout) findViewById(R.id.photoLayout_dzzh_show);




    }


    private void setAddView() {
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imgItemWidth, imgItemWidth);
        llp.rightMargin = 2;
        addIconView = inflater.inflate(R.layout.bill_image_item, null);
        ImageView img = (ImageView) addIconView.findViewById(R.id.img);
//        img.setBackgroundResource(R.color.trans_white);
        img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        img.setImageResource(R.mipmap.tianjia);
        photoLayout.addView(addIconView, llp);

        addIconView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                new PhotoDialog(ReportCreateAct.this).show();
                Intent intent = new Intent(CJ_DZZHD_XCKP_edit.this, SelectPicActivity.class);
                startActivityForResult(intent, TO_SELECT_PHOTO);
            }
        });
    }
    private void seImageView(Bitmap bmp, final String imgkey) {
//        urls.add(imgUrl);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imgItemWidth, imgItemWidth);
        llp.rightMargin = 2;
        photoLayout.removeView(addIconView);
        final View v = inflater.inflate(R.layout.bill_image_item, null);
        final ImageView img = (ImageView) v.findViewById(R.id.img);
//        img.setImageResource(R.mipmap.zhaopian);
//        imageloader.displayImage(imgUrl, img);
        img.setImageBitmap(bmp);
        photoLayout.addView(v, llp);
        View del = v.findViewById(R.id.deleteIcon);
        del.setVisibility(View.VISIBLE);
        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                DialogUtil.showInfoDialog(CJ_DZZHD_XCKP_edit.this, "确认删除?", "确定" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imgs.remove(imgkey);
                        removeImage(v, imgItemWidth, 0);
                    }
                });
            }
        });
        setAddView();
    }

    private void removeImage(final View item, int start, int end) {
        item.setVisibility(View.INVISIBLE);
        ValueAnimator anima = ValueAnimator.ofInt(start, end);
        anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                // TODO Auto-generated method stub
                LinearLayout.LayoutParams llpitem = (LinearLayout.LayoutParams) item.getLayoutParams();
                llpitem.width = (Integer) arg0.getAnimatedValue();
                item.setLayoutParams(llpitem);
            }
        });
        anima.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub
                photoLayout.removeView(item);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub

            }
        });

        anima.setInterpolator(AnimationUtils.loadInterpolator(this,
                android.R.anim.decelerate_interpolator));
        anima.setDuration(300);
        anima.start();
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
//                        JsonUtil.addJsonData(jsonObj, "id", "");//巡查卡片编号
                        JsonUtil.addJsonData(jsonObj, "zhdbh", zdid);//灾害点编号
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
                        JsonUtil.addJsonData(jsonObj,"xzb",lon);
                        JsonUtil.addJsonData(jsonObj,"yzb",lat);


                        String imgUrls="";
                        String urlStr="";
                        for (String url:imgs.values()){
                            imgUrls=imgUrls+url+"|";
                        }
                        if(imgUrls.length()>0)
                            urlStr=imgUrls.substring(0, imgUrls.length()-1);
                        JsonUtil.addJsonData(jsonObj,"path",urlStr);
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




    public static final int RequestAddress=0x11;
    public static final int TO_SELECT_PHOTO=0x12;
    public static final int TO_SELECT_VIDEO=0x13;


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

            MapBean mapBean= (MapBean) getIntent().getSerializableExtra("InfoMap");
            zdmc.setText(mapBean.getMap().get("ZHAA01A020"));
            String temp=mapBean.getMap().get("ZHAA01A830");
            temp=temp.replaceAll("1","地震");
            temp=temp.replaceAll("2","降雨");
            temp=temp.replaceAll("3","人为因素");
            temp=temp.replaceAll("4","其它");
            yfys.setText(temp);

            zdwz.setText(mapBean.getMap().get("ZHAA01A150"));
            lon=mapBean.getMap().get("ZHAA01A190");
            lat=mapBean.getMap().get("ZHAA01A200");


            String type = mapBean.getMap().get("ZHAA01A210");
            type=type.replaceAll("00","斜坡");
            type=type.replaceAll("01","滑坡");
            type=type.replaceAll("02","崩塌");
            type=type.replaceAll("03","泥石流");
            type=type.replaceAll("04","地面塌陷");
            type=type.replaceAll("05","地裂缝");
            type=type.replaceAll("06","地面沉降");
            type=type.replaceAll("07","其它");
            String size = mapBean.getMap().get("ZHAA01A890");
//            if(type!=null)
//                type=disasterNames[Integer.valueOf(type)];
            if (size != null) {
                if (size.equals("A"))
                    size = "特大型";
                else if (size.equals("B"))
                    size = "大型";
                else if (size.equals("C"))
                    size = "中型";
                else if (size.equals("D"))
                    size = "小型";

            }
            lxjqgm.setText("灾害类型:"+type + ";规模等级:" + size);

            String p=mapBean.getMap().get("ZHAA01A390");//威胁人口（人）
            String h=mapBean.getMap().get("ZHAA01A400");//威胁户数（户）
            String cc=mapBean.getMap().get("ZHAA01A410");//威胁财产（万元）

            String dx=mapBean.getMap().get("ZHAA01A380");//威胁对象
            dx=dx.replaceAll("A","分散农户");
            dx=dx.replaceAll("B","聚集区");
            dx=dx.replaceAll("C","学校");
            dx=dx.replaceAll("D","场镇");
            dx=dx.replaceAll("E","县城");
            dx=dx.replaceAll("F","公路");
            dx=dx.replaceAll("G","河道");
            dx=dx.replaceAll("H","景区");
            dx=dx.replaceAll("I","其它");
            wxdx.setText(h+"户;"+p+"人;"+cc+"万元;"+dx);
            zdid = mapBean.getMap().get("ZHAA01A010");

        }else if(resultCode==0x22){
            AreaInfo area= (AreaInfo) data.getSerializableExtra("Area");
            zdwz.setText(area.getName());
        }







//add cuikailei 20170522

        else if (resultCode == RESULT_OK && requestCode == TO_SELECT_PHOTO) {
            final String picPath = data.getStringExtra(ConstantUtil.Photo_Path);
//            imgs.add(picPath);
            Log.i("Upload", "最终选择的图片=" + picPath);
            final Bitmap bitmap=ImageUtil.getSmallBitmap(picPath);
            final String imgkey= String.valueOf(System.currentTimeMillis());
            seImageView(bitmap, imgkey);
            horiScroller.scrollBy(imgItemWidth,0);
            dialog= ProgressDialog.show(CJ_DZZHD_XCKP_edit.this, "", "处理中");
            dialog.setCancelable(false);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    int bitmapSize=getBitmapSize(bitmap);
//                        String result=UploadUtil.uploadFile(new File(picPath), ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
//                    String result= UploadUtil.uploadBitmap(bitmap, zdmc.getText().toString()+".jpg",ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
                    String result= UploadUtil.uploadBitmap(bitmap, "upload.jpg",ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
                    String url="";
                    if(result!=null)
                        url=JsonUtil.getString(result, "data");
                    if(url.length()>0){
                        imgs.put(imgkey, url);
                        handlerUpdate.sendEmptyMessage(1);
                    }else
                        handlerUpdate.sendEmptyMessage(0);
                    LogUtil.i("Upload", "size: " + bitmapSize + "Response: "+result);
                }
            }.start();
        }

    }



    public int getBitmapSize(Bitmap bitmap){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    Handler handlerUpdate=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if(dialog!=null)
                        dialog.dismiss();
                    ToastUtils.displayTextShort(CJ_DZZHD_XCKP_edit.this, "上传失败");
                    break;
                case 1:
                    if(dialog!=null)
                        dialog.dismiss();
                    break;

            }
            super.handleMessage(msg);
        }
    };


}
