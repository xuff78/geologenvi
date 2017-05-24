package com.sichuan.geologenvi.act.report;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
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
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.act.geodisaster.TitleResultListAct;
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
                ToastUtils.displayTextShort(CJ_BXCS_XCKP_edit.this, "操作成功");
                setResult(0x99);
                finish();
            }
        });
    }

    private DatePickerDialog datePickerDialog;
    private View yjsbssLayout, shwzcbLayout, spcbLayout, jcdateLayout, arrowRight9;
    private TextView  updateDataBtn, delDataBtn, addDataBtn, yjsbss, shwzcb, spcb, jcdate, bxcs_name, bxcs_xzqh,
            lon1,lon2,lon3,lat1,lat2,lat3;
    private ImageView xxgsp_yes, xxgsp_no, xlzsp_yes, xlzsp_no;
    private EditText azryssyhdname, jhazrs, sjrnrs, fczryazcs, syxpj, dzzhpgjy, fzr, fzrphone, glry, glryphone, zzzyr, zzzyrphone, sfkztqbr, ylkzcs, czwt, zgjy, bz, jcry;
    private String bxcs_guid="", lon="", lat="", xxgsp="", xlzsp="";
    private String requesType="";
    private String Update="update", Delete="delete", Add="add";



    private LinearLayout photoLayout;
    private View addIconView;
    private LayoutInflater inflater;

    Photo9Layout photo9Layout;
    private int imgItemWidth = 0;
    private LinkedHashMap<String, String> imgs=new LinkedHashMap<>();
    private ProgressDialog dialog;
    private HorizontalScrollView horiScroller;
    private String imgpath="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bxcs_xckp_jc);


        _setHeaderTitle("添加记录");


        //add cuikailei 20170522
        inflater = LayoutInflater.from(this);
        int scrennWidth = getWindowManager().getDefaultDisplay().getWidth();
        imgItemWidth = (scrennWidth - ImageUtil.dip2px(this, 20) - 6) / 4;


        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            initData();
//            addDataBtn.setVisibility(View.GONE);
            updateDataBtn.setVisibility(View.GONE);
            arrowRight9.setVisibility(View.GONE);


            if(imgpath!=null&&imgpath.length()>0) {
                String[] paths=imgpath.split("\\|");
                final ArrayList<String> imgUrls = new ArrayList<>();
                for (int i = 0; i < paths.length; i++) {
                    imgUrls.add(paths[i]);
                }
                photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Intent intent = new Intent(CJ_BXCS_XCKP_edit.this, ViewPagerExampleActivity.class);
                        intent.putExtra("Images", imgUrls);
                        intent.putExtra("pos", position);
                        startActivity(intent);
                    }
                });
                photo9Layout.setImageUrl(ScreenUtil.getScreenWidth(this)- ImageUtil.dip2px(this, 40), imgUrls);
            }



        }else {

            if (getIntent().hasExtra("Map")) {

                MapBean mapBean = (MapBean) getIntent().getSerializableExtra("Map");
                bxcs_guid = mapBean.getMap().get("ZHDD02A010");
                bxcs_name.setText(mapBean.getMap().get("ZHDD02A020"));
                bxcs_xzqh.setText(mapBean.getMap().get("ZHDD02A080"));
                String lon = mapBean.getMap().get("ZHDD02A120");
                this.lon = lon;
                String lat = mapBean.getMap().get("ZHDD02A130");
                this.lat = lat;
                setLoaction(lon, lat);
            }

            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);

            setAddView();
            photo9Layout.setVisibility(View.GONE);
        }

        initHandler();
        handler=new SqlHandler(this);
    }

    private void initData() {
        bxcs_guid=infoMap.get("bxcs_guid".toUpperCase());
        bxcs_name.setText(infoMap.get("bxcs_name".toUpperCase()));
        bxcs_xzqh.setText(infoMap.get("bxcs_xzqh".toUpperCase()));
        lat=infoMap.get("lat".toUpperCase());
        lon=infoMap.get("lon".toUpperCase());
        setLoaction(lon, lat);

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
        String dataStr=infoMap.get("jcdate".toUpperCase());
        jcdate.setText(ActUtil.getFormatDate(dataStr));

        xxgsp=infoMap.get("xxgsp".toUpperCase());
        if(xxgsp.equals("有")) {
            xxgsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(xxgsp.equals("无")) {
            xxgsp_no.setImageResource(R.mipmap.app_login_remember_sel);
        }

        xlzsp=infoMap.get("xlzsp".toUpperCase());
        if(xlzsp.equals("有")) {
            xlzsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(xlzsp.equals("无")) {
            xlzsp_no.setImageResource(R.mipmap.app_login_remember_sel);
        }


        imgpath=infoMap.get("path".toUpperCase());

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
                    if(bxcs_guid==null||bxcs_guid.length()==0){
                        ToastUtils.displayTextShort(CJ_BXCS_XCKP_edit.this, "请选择一个避难所");
                    }else if(jcry.getText().toString().length()==0){
                        ToastUtils.displayTextShort(CJ_BXCS_XCKP_edit.this, "请输入检查人员姓名");
                    }else if(jcdate.getText().toString().length()==0) {
                        ToastUtils.displayTextShort(CJ_BXCS_XCKP_edit.this, "请输入检查日期");
                    }else{

                        JSONObject jsonObj=new JSONObject();
                        JsonUtil.addJsonData(jsonObj, "bxcs_guid", bxcs_guid);
                        JsonUtil.addJsonData(jsonObj, "bxcs_name", bxcs_name.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "bxcs_xzqh", bxcs_xzqh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "lon", lon);
                        JsonUtil.addJsonData(jsonObj, "lat", lat);
                        JsonUtil.addJsonData(jsonObj, "azryssyhdname", azryssyhdname.getText().toString());
                        String jhazrsStr=jhazrs.getText().toString();
                        if(jhazrsStr.length()>0)
                            JsonUtil.addJsonData(jsonObj, "jhazrs", Integer.valueOf(jhazrsStr));
                        String sjrnrsStr=jhazrs.getText().toString();
                        if(sjrnrsStr.length()>0)
                            JsonUtil.addJsonData(jsonObj, "sjrnrs", Integer.valueOf(sjrnrsStr));
                        JsonUtil.addJsonData(jsonObj, "fczryazcs", fczryazcs.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "syxpj", syxpj.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "dzzhpgjy", dzzhpgjy.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "fzr", fzr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "fzrphone", fzrphone.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "glry", glry.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "glryphone", glryphone.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "zzzyr", zzzyr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "zzzyrphone", zzzyrphone.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "sfkztqbr", sfkztqbr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "ylkzcs", ylkzcs.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "xxgsp", xxgsp); //
                        JsonUtil.addJsonData(jsonObj, "xlzsp", xlzsp); //
                        JsonUtil.addJsonData(jsonObj, "yjsbss", yjsbss.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "shwzcb", shwzcb.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "spcb", spcb.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "czwt", czwt.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "zgjy", zgjy.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "bz", bz.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jcry", jcry.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "jcdate", jcdate.getText().toString());




                        String imgUrls="";
                        String urlStr="";
                        for (String url:imgs.values()){
                            imgUrls=imgUrls+url+"|";
                        }
                        if(imgUrls.length()>0)
                            urlStr=imgUrls.substring(0, imgUrls.length()-1);
                        JsonUtil.addJsonData(jsonObj,"path",urlStr);




                        requesType=Add;
                        httpHandler.addCJ_BXCS_XCKP(jsonObj.toString());
                    }
                    break;
                case R.id.updateDataBtn:
                    break;
                case R.id.delDataBtn:
                    DialogUtil.showActionDialog(CJ_BXCS_XCKP_edit.this, "提示", "确认要删除", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requesType=Delete;
                            httpHandler.delCJ_BXCS_XCKP(infoMap.get("ID"));
                        }
                    });
                    break;
                case R.id.districtLayout:
                    Intent selectIntent=new Intent(CJ_BXCS_XCKP_edit.this, AreaInputAct.class);
                    startActivityForResult(selectIntent, 0x11);
                    break;
                case R.id.xxgsp_yes:
                    xxgsp="有";
                    xxgsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    xxgsp_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.xxgsp_no:
                    xxgsp="无";
                    xxgsp_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    xxgsp_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.xlzsp_yes:
                    xlzsp="有";
                    xlzsp_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    xlzsp_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.xlzsp_no:
                    xlzsp="无";
                    xlzsp_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    xlzsp_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.bnsmcLayout:
                    Intent zdmcIntent=new Intent(CJ_BXCS_XCKP_edit.this, SelectorAct.class);
                    zdmcIntent.putExtra("Report", true);
                    zdmcIntent.putExtra("Type", 4);
                    startActivityForResult(zdmcIntent, 0x10);
                    break;
                case R.id.yjsbssLayout:
                    final String[] yjsbssTxt={"正常使用","不能使用","其他"};
                    DialogUtil.showSelectDialog(CJ_BXCS_XCKP_edit.this, "使用情况", yjsbssTxt, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            yjsbss.setText(yjsbssTxt[i]);
                        }
                    });
                    break;
                case R.id.shwzcbLayout:
                    final String[] shwzcbTxt={"满足需要","基本满足需要","不满足需要","无"};
                    DialogUtil.showSelectDialog(CJ_BXCS_XCKP_edit.this, "生活物资储备", shwzcbTxt, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            shwzcb.setText(shwzcbTxt[i]);
                        }
                    });
                    break;
                case R.id.spcbLayout:
                    final String[] spcbTxt={"实物储备","协议储备","协议生产储备", "无"};
                    DialogUtil.showSelectDialog(CJ_BXCS_XCKP_edit.this, "食品储备", spcbTxt, new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            spcb.setText(spcbTxt[i]);
                        }
                    });
                    break;
            }
        }
    };

    private void initView() {
        bxcs_name=(TextView)findViewById(R.id.bxcs_name);
        bxcs_xzqh=(TextView)findViewById(R.id.bxcs_xzqh);
        lon1=(TextView)findViewById(R.id.lon1);
        lon2=(TextView)findViewById(R.id.lon2);
        lon3=(TextView)findViewById(R.id.lon3);
        lat1=(TextView)findViewById(R.id.lat1);
        lat2=(TextView)findViewById(R.id.lat2);
        lat3=(TextView)findViewById(R.id.lat3);

        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);
        yjsbss=(TextView) findViewById(R.id.yjsbss);
        shwzcb=(TextView) findViewById(R.id.shwzcb);
        spcb=(TextView) findViewById(R.id.spcb);
        jcdate=(TextView) findViewById(R.id.jcdate);
        arrowRight9=findViewById(R.id.arrowRight9);

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
        findViewById(R.id.bnsmcLayout).setOnClickListener(listener);
        findViewById(R.id.addDataBtn).setOnClickListener(listener);
        findViewById(R.id.updateDataBtn).setOnClickListener(listener);
        findViewById(R.id.delDataBtn).setOnClickListener(listener);



        horiScroller= (HorizontalScrollView) findViewById(R.id.horiScroller);
        photoLayout= (LinearLayout) findViewById(R.id.photoLayout_bxcs);
        photo9Layout= (Photo9Layout) findViewById(R.id.photoLayout_bxcs_show);
    }

    public static final int RequestAddress=0x11;
    public static final int TO_SELECT_PHOTO=0x12;
    public static final int TO_SELECT_VIDEO=0x13;

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
                Intent intent = new Intent(CJ_BXCS_XCKP_edit.this, SelectPicActivity.class);
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
                DialogUtil.showInfoDialog(CJ_BXCS_XCKP_edit.this, "确认删除?", "确定" , new DialogInterface.OnClickListener() {
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







    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            System.out.println("---> 设置后: year="+year+", month="+monthOfYear+",day="+dayOfMonth);
            jcdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x11) {
            data.setClass(CJ_BXCS_XCKP_edit.this, TitleResultListAct.class); //查看线下数据库内容
            data.putExtra("TakeDataBack", true);
            data.putExtra("Type", 4);
            data.putExtra("Title", "避难所");
            startActivityForResult(data, 0x20);
        } else if (resultCode == 0x21) {
            MapBean mapBean = (MapBean) data.getSerializableExtra("InfoMap");
            bxcs_guid=mapBean.getMap().get("ZHDD02A010");
            bxcs_name.setText(mapBean.getMap().get("ZHDD02A020"));
            bxcs_xzqh.setText(mapBean.getMap().get("ZHDD02A080"));
            String lon=mapBean.getMap().get("ZHDD02A120");
            this.lon=lon;
            String lat=mapBean.getMap().get("ZHDD02A130");
            this.lat=lat;
            setLoaction(lon, lat);

//            String size = mapBean.getMap().get("ZHAA01A890");
//            if (type != null)
//                type = disasterNames[Integer.valueOf(type)];
//            if (size != null) {
//                if (size.equals("A"))
//                    size = "特大型";
//                else if (size.equals("B"))
//                    size = "大型";
//                else if (size.equals("C"))
//                    size = "中型";
//                else if (size.equals("D"))
//                    size = "小型";
//
//            }
//            lxjqgm.setText(type + "  " + size);
//            zdid = mapBean.getMap().get("ZHAA01A010");

        }
        //add cuikailei 20170522

        else if (resultCode == RESULT_OK && requestCode == TO_SELECT_PHOTO) {
            final String picPath = data.getStringExtra(ConstantUtil.Photo_Path);
//            String[] p=picPath.split("/");
//            String temp=p[p.length-1];
//            temp=temp.substring(0,temp.length()-4);
//            Date date = new Date();
//
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//            String newtemp=bxcs_name.getText().toString()+sdf.format(date);
//            String newPath=picPath.replaceAll(temp,newtemp);
//            File file = new File(picPath);
//            file.renameTo(new File(newPath));

                String newPath=picPath;
//            imgs.add(picPath);
            Log.i("Upload", "最终选择的图片=" + newPath);
            final Bitmap bitmap=ImageUtil.getSmallBitmap(newPath);
            final String imgkey= String.valueOf(System.currentTimeMillis());
            seImageView(bitmap, imgkey);
            horiScroller.scrollBy(imgItemWidth,0);
            dialog= ProgressDialog.show(CJ_BXCS_XCKP_edit.this, "", "处理中");
            dialog.setCancelable(false);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    int bitmapSize=getBitmapSize(bitmap);
//                        String result=UploadUtil.uploadFile(new File(picPath), ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
//                    String result= UploadUtil.uploadBitmap(bitmap, bxcs_name.getText().toString()+".jpg",ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
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
                    ToastUtils.displayTextShort(CJ_BXCS_XCKP_edit.this, "上传失败");
                    break;
                case 1:
                    if(dialog!=null)
                        dialog.dismiss();
                    break;

            }
            super.handleMessage(msg);
        }
    };




    private void setLoaction(String lon, String lat){
        if(lon!=null&&lon.contains(".")) {
            String[] lons=lon.split("\\.");
            lon1.setText(lons[0]);
            if(lons[1]!=null){
                if(lons[1].length()>2){
                    lon2.setText(lons[1].substring(0,2));
                    lon3.setText(lons[1].substring(2,lons[1].length()));
                }else{
                    lon2.setText(lons[1]);
                    lon3.setText("00");
                }
            }else{
                lon2.setText("00");
                lon3.setText("00");
            }
        }
        if(lat!=null&&lat.contains(".")) {
            String[] lats=lat.split("\\.");
            lat1.setText(lats[0]);
            if(lats[1]!=null){
                if(lats[1].length()>2){
                    lat2.setText(lats[1].substring(0,2));
                    lat3.setText(lats[1].substring(2,lats[1].length()));
                }else{
                    lat2.setText(lats[1]);
                    lat3.setText("00");
                }
            }else{
                lat2.setText("00");
                lat3.setText("00");
            }
        }
    }

}
