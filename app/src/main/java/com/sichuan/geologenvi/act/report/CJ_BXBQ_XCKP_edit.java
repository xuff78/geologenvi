package com.sichuan.geologenvi.act.report;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.utils.UploadUtil;
import com.sichuan.geologenvi.views.Photo9Layout;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tanqq on 2017/5/31.
 */
public class CJ_BXBQ_XCKP_edit extends AppFrameAct {

    Map<String, String> infoMap=new HashMap<>();

    private SqlHandler handler;
    private HttpHandler httpHandler;
    private DatePickerDialog datePickerDialog;

    private TextView bqhbh,hzxm,hzrk,sfzhm,yhdmc;
    private TextView quxian,xiangzhen,cun,zu;
    private EditText ggxmmc,czwt,bz,jcr,wxdx_hu,wxdx_ren;

    private TextView updateDataBtn, delDataBtn, addDataBtn,jcrq;
    private ImageView jzaz_chk,hbhaz_chk,fsaz_chk,fgg_jzaz_chk,zzaz_chk,gdaz_chk,xfjc_chk,xfrz_chk,jfcc_chk,zjdfk_chk,bxbqys_chk,sjkgx_chk;
    private ImageView jzaz_lx_chk,jzaz_zs_chk,jzaz_qdht_chk,jzaz_qdbcfs_chk,jzaz_qdazxy_chk,hbhaz_bczjff_chk;
    private ImageView   fsaz_mfxz_chk,fsaz_xfxj_chk,        zzaz_zzazxy_chk,zzaz_bczjff_chk,        fgg_jzaz_azqxz_chk,fgg_jzaz_sg_chk;
    private String bxbq_id="";
    private String jzaz="", hbhaz="", fsaz="", zzaz="", fgg_jzaz="", gdaz="",xfjc="",xfrz="",jfcc="",zjdfk="",bxbqys="",sjkgx="";
    private String jzaz_lx="",jzaz_zs="",jzaz_qdht="",jzaz_qdbcfs="",jzaz_qdazxy="",hbhaz_bczjff="";
    private String   fsaz_mfxz="",fsaz_xfxj="",        zzaz_zzazxy="",zzaz_bczjff="",        fgg_jzaz_azqxz="",fgg_jzaz_sg="";
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
    private String imgpath="",GX_PATH="",BQQ_PATH="",BQH_PATH="";

    private String m_type="添加记录";
    public final ArrayList<String> imgUrls = new ArrayList<>();

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(CJ_BXBQ_XCKP_edit.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                if(method.equals(ConstantUtil.Method.CJ_BXBQ_XCKP)){
                    if(requesType.equals(Add)){

                    }
                    ToastUtils.displayTextShort(CJ_BXBQ_XCKP_edit.this, "操作成功");
                    setResult(0x99);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bxbq_fzgz_jc);

        m_type=getIntent().getStringExtra("type");
        _setHeaderTitle(m_type);
        handler=new SqlHandler(this);



        //add cuikailei 20170522
        inflater = LayoutInflater.from(this);
        int scrennWidth = getWindowManager().getDefaultDisplay().getWidth();
        imgItemWidth = (scrennWidth - ImageUtil.dip2px(this, 20) - 6) / 4;


        initView();
        if(getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)getIntent().getSerializableExtra("InfoMap")).getMap();
            initData();
            addDataBtn.setVisibility(View.GONE);
//            updateDataBtn.setVisibility(View.GONE);

//            if(imgpath!=null&&imgpath.length()>0) {
//                String[] paths=imgpath.split("\\|");
//                final ArrayList<String> imgUrls = new ArrayList<>();
//                for (int i = 0; i < paths.length; i++) {
//                    imgUrls.add(paths[i]);
//                }
//                photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
//                    @Override
//                    public void onClick(View v, int position) {
//                        Intent intent = new Intent(CJ_BXBQ_XCKP_edit.this, ViewPagerExampleActivity.class);
//                        intent.putExtra("Images", imgUrls);
//                        intent.putExtra("pos", position);
//                        startActivity(intent);
//                    }
//                });
//                photo9Layout.setImageUrl(ScreenUtil.getScreenWidth(this)- ImageUtil.dip2px(this, 40), imgUrls);
//            }

        }else{
//            findViewById(R.id.zdmcLayout).setOnClickListener(listener);
            updateDataBtn.setVisibility(View.GONE);
            delDataBtn.setVisibility(View.GONE);
            setAddView();
            photo9Layout.setVisibility(View.GONE);
        }
        if(getIntent().hasExtra("Map")) {
            infoMap= ((MapBean) getIntent().getSerializableExtra("Map")).getMap();
            bxbq_id=infoMap.get("ID".toUpperCase());

            bqhbh.setText(infoMap.get("BQHZBH".toUpperCase()));
            hzxm.setText(infoMap.get("hzxm".toUpperCase()));
            hzrk.setText(infoMap.get("RK".toUpperCase()));
            sfzhm.setText(infoMap.get("SFZHM".toUpperCase()));

            quxian.setText(infoMap.get("XIAN".toUpperCase()));
            xiangzhen.setText(infoMap.get("XIANGZHEN".toUpperCase()));
            cun.setText(infoMap.get("CUN".toUpperCase()));
            zu.setText(infoMap.get("ZU".toUpperCase()));

            yhdmc.setText(infoMap.get("YHDMC".toUpperCase()));
            wxdx_hu.setText(infoMap.get("HUSHU".toUpperCase()));
            wxdx_ren.setText(infoMap.get("RENSHU".toUpperCase()));
            ggxmmc.setText(infoMap.get("GGXMMC".toUpperCase()));

            jzaz=infoMap.get("GGXMJZAZ".toUpperCase());
            if(jzaz.equals("1")) {
                jzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }

            jzaz_lx=infoMap.get("JZAZ_LX".toUpperCase());
            if(jzaz_lx.equals("1")) {
                jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            jzaz_zs=infoMap.get("JZAZ_ZS".toUpperCase());
            if(jzaz_zs.equals("1")) {
                jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            jzaz_qdht=infoMap.get("JZAZ_QDHT".toUpperCase());
            if(jzaz_qdht.equals("1")) {
                jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            jzaz_qdbcfs=infoMap.get("JZAZ_QDBCFS".toUpperCase());
            if(jzaz_qdbcfs.equals("1")) {
                jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            jzaz_qdazxy=infoMap.get("JZAZ_QDAZXY".toUpperCase());
            if(jzaz_qdazxy.equals("1")) {
                jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }


            hbhaz=infoMap.get("HBHAZ".toUpperCase());
            if(hbhaz.equals("1")) {
                hbhaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            hbhaz_bczjff=infoMap.get("HBHAZ_BCZJFF".toUpperCase());
            if(hbhaz_bczjff.equals("1")) {
                hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }



            fsaz=infoMap.get("FGG_FSAZ".toUpperCase());
            if(fsaz.equals("1")) {
                fsaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            fsaz_mfxz=infoMap.get("FGG_FSAZ_MFXZ".toUpperCase());
            if(fsaz_mfxz.equals("1")) {
                fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            fsaz_xfxj=infoMap.get("FGG_FSAZ_XFXJ".toUpperCase());
            if(fsaz_xfxj.equals("1")) {
                fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }


            zzaz=infoMap.get("FGG_ZZAZ".toUpperCase());
            if(zzaz.equals("1")) {
                zzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            zzaz_zzazxy=infoMap.get("FGG_ZZAZ_ZZAZXY".toUpperCase());
            if(zzaz_zzazxy.equals("1")) {
                zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            zzaz_bczjff=infoMap.get("FGG_ZZAZ_BCZJFF".toUpperCase());
            if(zzaz_bczjff.equals("1")) {
                zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }



            fgg_jzaz=infoMap.get("FGG_JZAZ".toUpperCase());
            if(fgg_jzaz.equals("1")) {
                fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            fgg_jzaz_azqxz=infoMap.get("FGG_JZAZ_AZQXZ".toUpperCase());
            if(fgg_jzaz_azqxz.equals("1")) {
                fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            fgg_jzaz_sg=infoMap.get("FGG_JZAZ_SG".toUpperCase());
            if(fgg_jzaz_sg.equals("1")) {
                fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }



            gdaz=infoMap.get("GDAZ".toUpperCase());
            if(gdaz.equals("1")) {
                gdaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                gdaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            xfjc=infoMap.get("XFJC".toUpperCase());
            if(xfjc.equals("1")) {
                xfjc_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                xfjc_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            xfrz=infoMap.get("XFRZ".toUpperCase());
            if(xfrz.equals("1")) {
                xfrz_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                xfrz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            jfcc=infoMap.get("JFCC".toUpperCase());
            if(jfcc.equals("1")) {
                jfcc_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                jfcc_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            zjdfk=infoMap.get("ZJDFK".toUpperCase());
            if(zjdfk.equals("1")) {
                zjdfk_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                zjdfk_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            bxbqys=infoMap.get("BXBQYS".toUpperCase());
            if(bxbqys.equals("1")) {
                bxbqys_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                bxbqys_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }
            sjkgx=infoMap.get("SJKGX".toUpperCase());
            if(sjkgx.equals("1")) {
                sjkgx_chk.setImageResource(R.mipmap.app_login_remember_sel);
            }else {
                sjkgx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
            }


            czwt.setText(infoMap.get("CZWT".toUpperCase()));
            bz.setText(infoMap.get("BZ".toUpperCase()));
        }


        initHandler();




    }

    private void initData() {


        bxbq_id = infoMap.get("BXBQ_ID".toUpperCase());

        bqhbh.setText(infoMap.get("BQHZBH".toUpperCase()));
        hzxm.setText(infoMap.get("hzxm".toUpperCase()));
        hzrk.setText(infoMap.get("RK".toUpperCase()));
        sfzhm.setText(infoMap.get("SFZHM".toUpperCase()));

        quxian.setText(infoMap.get("XIAN".toUpperCase()));
        xiangzhen.setText(infoMap.get("XIANGZHEN".toUpperCase()));
        cun.setText(infoMap.get("CUN".toUpperCase()));
        zu.setText(infoMap.get("ZU".toUpperCase()));

        yhdmc.setText(infoMap.get("YHDMC".toUpperCase()));
        wxdx_hu.setText(infoMap.get("HUSHU".toUpperCase()));
        wxdx_ren.setText(infoMap.get("RENSHU".toUpperCase()));
        ggxmmc.setText(infoMap.get("GGXMMC".toUpperCase()));

        jzaz = infoMap.get("GGXMJZAZ".toUpperCase());
        if (jzaz.equals("1")) {
            jzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }

        jzaz_lx = infoMap.get("JZAZ_LX".toUpperCase());
        if (jzaz_lx.equals("1")) {
            jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        jzaz_zs = infoMap.get("JZAZ_ZS".toUpperCase());
        if (jzaz_zs.equals("1")) {
            jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        jzaz_qdht = infoMap.get("JZAZ_QDHT".toUpperCase());
        if (jzaz_qdht.equals("1")) {
            jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        jzaz_qdbcfs = infoMap.get("JZAZ_QDBCFS".toUpperCase());
        if (jzaz_qdbcfs.equals("1")) {
            jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        jzaz_qdazxy = infoMap.get("JZAZ_QDAZXY".toUpperCase());
        if (jzaz_qdazxy.equals("1")) {
            jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }


        hbhaz = infoMap.get("HBHAZ".toUpperCase());
        if (hbhaz.equals("1")) {
            hbhaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        hbhaz_bczjff = infoMap.get("HBHAZ_BCZJFF".toUpperCase());
        if (hbhaz_bczjff.equals("1")) {
            hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }


        fsaz = infoMap.get("FGG_FSAZ".toUpperCase());
        if (fsaz.equals("1")) {
            fsaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        fsaz_mfxz = infoMap.get("FGG_FSAZ_MFXZ".toUpperCase());
        if (fsaz_mfxz.equals("1")) {
            fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        fsaz_xfxj = infoMap.get("FGG_FSAZ_XFXJ".toUpperCase());
        if (fsaz_xfxj.equals("1")) {
            fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }


        zzaz = infoMap.get("FGG_ZZAZ".toUpperCase());
        if (zzaz.equals("1")) {
            zzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        zzaz_zzazxy = infoMap.get("FGG_ZZAZ_ZZAZXY".toUpperCase());
        if (zzaz_zzazxy.equals("1")) {
            zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        zzaz_bczjff = infoMap.get("FGG_ZZAZ_BCZJFF".toUpperCase());
        if (zzaz_bczjff.equals("1")) {
            zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }


        fgg_jzaz = infoMap.get("FGG_JZAZ".toUpperCase());
        if (fgg_jzaz.equals("1")) {
            fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        fgg_jzaz_azqxz = infoMap.get("FGG_JZAZ_AZQXZ".toUpperCase());
        if (fgg_jzaz_azqxz.equals("1")) {
            fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        fgg_jzaz_sg = infoMap.get("FGG_JZAZ_SG".toUpperCase());
        if (fgg_jzaz_sg.equals("1")) {
            fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }


        gdaz = infoMap.get("GDAZ".toUpperCase());
        if (gdaz.equals("1")) {
            gdaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            gdaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        xfjc = infoMap.get("XFJC".toUpperCase());
        if (xfjc.equals("1")) {
            xfjc_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            xfjc_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        xfrz = infoMap.get("XFRZ".toUpperCase());
        if (xfrz.equals("1")) {
            xfrz_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            xfrz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        jfcc = infoMap.get("JFCC".toUpperCase());
        if (jfcc.equals("1")) {
            jfcc_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            jfcc_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        zjdfk = infoMap.get("ZJDFK".toUpperCase());
        if (zjdfk.equals("1")) {
            zjdfk_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            zjdfk_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        bxbqys = infoMap.get("BXBQYS".toUpperCase());
        if (bxbqys.equals("1")) {
            bxbqys_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            bxbqys_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }
        sjkgx = infoMap.get("SJKGX".toUpperCase());
        if (sjkgx.equals("1")) {
            sjkgx_chk.setImageResource(R.mipmap.app_login_remember_sel);
        } else {
            sjkgx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
        }


        czwt.setText(infoMap.get("CZWT".toUpperCase()));
        bz.setText(infoMap.get("BZ".toUpperCase()));

        jcr.setText(infoMap.get("JCR".toUpperCase()));
        jcrq.setText(infoMap.get("JCRQ".toUpperCase()));


        GX_PATH = infoMap.get("GX_PATH".toUpperCase());
        BQQ_PATH = infoMap.get("BQQ_PATH".toUpperCase());
        BQH_PATH = infoMap.get("BQH_PATH".toUpperCase());

        imgpath = infoMap.get("GZ_PATH".toUpperCase());
        if (imgpath.equals("")) {
            setAddView();
        }
        imgUrls.clear();
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (imgpath != null && imgpath.length() > 0) {
                    String[] paths = imgpath.split("\\|");

                    ArrayList<Bitmap> lstBt = new ArrayList<Bitmap>();
                    for (int i = 0; i < paths.length; i++) {
                        imgUrls.add(paths[i]);
                        // TODO Auto-generated method stub
                        lstBt.add(getURLimage(paths[i]));
                    }
                    //                        Bitmap bmp = getURLimage(paths[i]);
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = lstBt;
                    handle.sendMessage(msg);
                }
            }
        }).start();


    }

    private void initView() {





        bqhbh= (TextView) findViewById(R.id.bqhbh);//搬迁户主编号
        hzxm=(TextView) findViewById(R.id.hzxm);//户主姓名
        hzrk=(TextView) findViewById(R.id.hzrk);//户主人口
        sfzhm=(TextView) findViewById(R.id.sfzhm);//身份证号码



        quxian=(TextView) findViewById(R.id.quxian);
        xiangzhen=(TextView) findViewById(R.id.xiangzhen);
        cun=(TextView) findViewById(R.id.cun);
        zu=(TextView) findViewById(R.id.zu);



        yhdmc=(TextView) findViewById(R.id.yhdmc);//隐患点名称
        wxdx_hu=(EditText) findViewById(R.id.wxdx_hu);//户数
        wxdx_ren=(EditText) findViewById(R.id.wxdx_ren);//人数

        ggxmmc=(EditText) findViewById(R.id.ggxmmc);//挂钩项目名称


        jzaz_chk= (ImageView) findViewById(R.id.jzaz_chk);//集中安置
        jzaz_chk.setOnClickListener(listener);
//        □立项
//        □招商
//        □签订合同
//        □确定补偿方式
//        □签订安置协议



        jzaz_lx_chk= (ImageView) findViewById(R.id.jzaz_lx_chk);//立项
        jzaz_lx_chk.setOnClickListener(listener);

        jzaz_zs_chk= (ImageView) findViewById(R.id.jzaz_zs_chk);//招商
        jzaz_zs_chk.setOnClickListener(listener);


        jzaz_qdht_chk= (ImageView) findViewById(R.id.jzaz_qdht_chk);//签订合同
        jzaz_qdht_chk.setOnClickListener(listener);


        jzaz_qdbcfs_chk= (ImageView) findViewById(R.id.jzaz_qdbcfs_chk);//确定补偿方式
        jzaz_qdbcfs_chk.setOnClickListener(listener);

        jzaz_qdazxy_chk= (ImageView) findViewById(R.id.jzaz_qdazxy_chk);//签订安置协议
        jzaz_qdazxy_chk.setOnClickListener(listener);


        hbhaz_chk= (ImageView) findViewById(R.id.hbhaz_chk);//货币化安置
        hbhaz_chk.setOnClickListener(listener);

//        补偿资金发放
        hbhaz_bczjff_chk= (ImageView) findViewById(R.id.hbhaz_bczjff_chk);// 补偿资金发放
        hbhaz_bczjff_chk.setOnClickListener(listener);



        fsaz_chk= (ImageView) findViewById(R.id.fsaz_chk);//分散安置
        fsaz_chk.setOnClickListener(listener);


//        □民房选址
//        □新房修建
        fsaz_mfxz_chk= (ImageView) findViewById(R.id.fsaz_mfxz_chk);//民房选址
        fsaz_mfxz_chk.setOnClickListener(listener);
        fsaz_xfxj_chk= (ImageView) findViewById(R.id.fsaz_xfxj_chk);//新房修建
        fsaz_xfxj_chk.setOnClickListener(listener);

        zzaz_chk= (ImageView) findViewById(R.id.zzaz_chk);//自主安置
        zzaz_chk.setOnClickListener(listener);
//        □自主安置协议
//        □补偿资金发放
        zzaz_zzazxy_chk= (ImageView) findViewById(R.id.zzaz_zzazxy_chk);//自主安置协议
        zzaz_zzazxy_chk.setOnClickListener(listener);
        zzaz_bczjff_chk= (ImageView) findViewById(R.id.zzaz_bczjff_chk);//补偿资金发放
        zzaz_bczjff_chk.setOnClickListener(listener);

        fgg_jzaz_chk= (ImageView) findViewById(R.id.fgg_jzaz_chk);//集中安置
        fgg_jzaz_chk.setOnClickListener(listener);
//        □安置区选址
//        □施工
        fgg_jzaz_azqxz_chk= (ImageView) findViewById(R.id.fgg_jzaz_azqxz_chk);//集中安置
        fgg_jzaz_azqxz_chk.setOnClickListener(listener);
        fgg_jzaz_sg_chk= (ImageView) findViewById(R.id.fgg_jzaz_sg_chk);//集中安置
        fgg_jzaz_sg_chk.setOnClickListener(listener);



        gdaz_chk= (ImageView) findViewById(R.id.gdaz_chk);//过渡安置
        gdaz_chk.setOnClickListener(listener);

        xfjc_chk= (ImageView) findViewById(R.id.xfjc_chk);//新房建成
        xfjc_chk.setOnClickListener(listener);
        xfrz_chk= (ImageView) findViewById(R.id.xfrz_chk);//新房入住
        xfrz_chk.setOnClickListener(listener);
        jfcc_chk= (ImageView) findViewById(R.id.jfcc_chk);//旧房拆除
        jfcc_chk.setOnClickListener(listener);
        zjdfk_chk= (ImageView) findViewById(R.id.zjdfk_chk);//宅基地复垦
        zjdfk_chk.setOnClickListener(listener);
        bxbqys_chk= (ImageView) findViewById(R.id.bxbqys_chk);//避险搬迁验收
        bxbqys_chk.setOnClickListener(listener);
        sjkgx_chk= (ImageView) findViewById(R.id.sjkgx_chk);//数据库更新
        sjkgx_chk.setOnClickListener(listener);





        czwt=(EditText) findViewById(R.id.czwt);//存在问题
        bz=(EditText) findViewById(R.id.bz);//备注



        jcr=(EditText) findViewById(R.id.jcr);//检查人

        jcrq= (TextView) findViewById(R.id.jcrq);
        jcrq.setText(ActUtil.getDate());




        findViewById(R.id.bxbq_jcrqLayout).setOnClickListener(listener);

        updateDataBtn=(TextView) findViewById(R.id.updateDataBtn);
        updateDataBtn.setOnClickListener(listener);
        delDataBtn=(TextView) findViewById(R.id.delDataBtn);
        delDataBtn.setOnClickListener(listener);
        addDataBtn=(TextView) findViewById(R.id.addDataBtn);
        addDataBtn.setOnClickListener(listener);




        horiScroller= (HorizontalScrollView) findViewById(R.id.horiScroller);
        photoLayout= (LinearLayout) findViewById(R.id.photoLayout_bxbq);
        photo9Layout= (Photo9Layout) findViewById(R.id.photoLayout_bxbq_show);




    }


    //加载图片
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }


    //在消息队列中实现对控件的更改
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ArrayList<Bitmap> lst=(ArrayList<Bitmap>)msg.obj;
                    for(int i=0;i<lst.size();i++) {
                        final Bitmap bitmap = lst.get(i);
                        final String imgkey = String.valueOf(System.currentTimeMillis());

                        seImageView(bitmap, imgkey);
                        imgs.put(imgkey, imgUrls.get(i));
                    }
                    break;
            }
        };
    };


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
                Intent intent = new Intent(CJ_BXBQ_XCKP_edit.this, SelectPicActivity.class);
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
                DialogUtil.showInfoDialog(CJ_BXBQ_XCKP_edit.this, "确认删除?", "确定" , new DialogInterface.OnClickListener() {
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
                case R.id.bxbq_jcrqLayout:
                    String[] dateStart=jcrq.getText().toString().split("-");
                    datePickerDialog=new DatePickerDialog(CJ_BXBQ_XCKP_edit.this, mDateSetListener, Integer.valueOf(dateStart[0]),
                            Integer.valueOf(dateStart[1])-1, Integer.valueOf(dateStart[2]));
                    datePickerDialog.show();
                    break;
                case R.id.addDataBtn:

                    if(jcr.getText().toString().length()==0){
                        ToastUtils.displayTextShort(CJ_BXBQ_XCKP_edit.this, "请输入检查人员姓名");
                    }else if(jcrq.getText().toString().length()==0) {
                        ToastUtils.displayTextShort(CJ_BXBQ_XCKP_edit.this, "请输入检查日期");
                    }else{

                        JSONObject jsonObj=new JSONObject();

                        JsonUtil.addJsonData(jsonObj, "id", "");

                        JsonUtil.addJsonData(jsonObj, "bh", "");
                        JsonUtil.addJsonData(jsonObj, "bxbq_id", bxbq_id);

                        JsonUtil.addJsonData(jsonObj, "BQHZBH", bqhbh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "HZXM", hzxm.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "RK", hzrk.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "SFZHM", sfzhm.getText().toString());

                        JsonUtil.addJsonData(jsonObj, "XIAN", quxian.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "XIANGZHEN", xiangzhen.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "CUN", cun.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "ZU", zu.getText().toString());

                        JsonUtil.addJsonData(jsonObj, "YHDMC", yhdmc.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "HUSHU", wxdx_hu.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "RENSHU", wxdx_ren.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "GGXMMC", ggxmmc.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "GGXMJZAZ", jzaz);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_LX", jzaz_lx);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_ZS", jzaz_zs);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_QDHT", jzaz_qdht);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_QDBCFS", jzaz_qdbcfs);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_QDAZXY", jzaz_qdazxy);
                        JsonUtil.addJsonData(jsonObj, "HBHAZ", hbhaz);
                        JsonUtil.addJsonData(jsonObj, "HBHAZ_BCZJFF", hbhaz_bczjff);
                        JsonUtil.addJsonData(jsonObj, "FGG_FSAZ", fsaz);
                        JsonUtil.addJsonData(jsonObj, "FGG_FSAZ_MFXZ", fsaz_mfxz);
                        JsonUtil.addJsonData(jsonObj, "FGG_FSAZ_XFXJ", fsaz_xfxj);

                        JsonUtil.addJsonData(jsonObj, "FGG_ZZAZ", zzaz);
                        JsonUtil.addJsonData(jsonObj, "FGG_ZZAZ_ZZAZXY", zzaz_zzazxy);
                        JsonUtil.addJsonData(jsonObj, "FGG_ZZAZ_BCZJFF", zzaz_bczjff);

                        JsonUtil.addJsonData(jsonObj, "FGG_JZAZ", fgg_jzaz);
                        JsonUtil.addJsonData(jsonObj, "FGG_JZAZ_AZQXZ", fgg_jzaz_azqxz);
                        JsonUtil.addJsonData(jsonObj, "FGG_JZAZ_SG", fgg_jzaz_sg);


                        JsonUtil.addJsonData(jsonObj, "GDAZ", gdaz);
                        JsonUtil.addJsonData(jsonObj, "XFJC", xfjc);
                        JsonUtil.addJsonData(jsonObj, "XFRZ", xfrz);
                        JsonUtil.addJsonData(jsonObj, "JFCC", jfcc);
                        JsonUtil.addJsonData(jsonObj, "ZJDFK", zjdfk);
                        JsonUtil.addJsonData(jsonObj, "BXBQYS", bxbqys);
                        JsonUtil.addJsonData(jsonObj, "SJKGX", sjkgx);

                        JsonUtil.addJsonData(jsonObj, "CZWT", czwt.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "BZ", bz.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "JCR", jcr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "JCRQ", jcrq.getText().toString());


                        JsonUtil.addJsonData(jsonObj, "GX_PATH",GX_PATH);
                        JsonUtil.addJsonData(jsonObj, "BQQ_PATH", BQQ_PATH);
                        JsonUtil.addJsonData(jsonObj, "BQH_PATH", BQH_PATH);


                        String imgUrls="";
                        String urlStr="";
                        for (String url:imgs.values()){
                            imgUrls=imgUrls+url+"|";
                        }
                        if(imgUrls.length()>0)
                            urlStr=imgUrls.substring(0, imgUrls.length()-1);
                        JsonUtil.addJsonData(jsonObj, "GZ_PATH", urlStr);



                        requesType=Add;
                        httpHandler.addCJ_BXBQ_XCKP(jsonObj.toString());
                    }
//                    String jsonContent=getInfoString();
//                    handler.addBangqianBaseInfo(jsonContent);
                    break;
                case R.id.updateDataBtn:
                    if(jcr.getText().toString().length()==0){
                        ToastUtils.displayTextShort(CJ_BXBQ_XCKP_edit.this, "请输入检查人员姓名");
                    }else if(jcrq.getText().toString().length()==0) {
                        ToastUtils.displayTextShort(CJ_BXBQ_XCKP_edit.this, "请输入检查日期");
                    }else{

                        JSONObject jsonObj=new JSONObject();

                        JsonUtil.addJsonData(jsonObj, "id",  infoMap.get("ID"));

                        JsonUtil.addJsonData(jsonObj, "bh", infoMap.get("BH"));
                        JsonUtil.addJsonData(jsonObj, "bxbq_id", bxbq_id);

                        JsonUtil.addJsonData(jsonObj, "BQHZBH", bqhbh.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "HZXM", hzxm.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "RK", hzrk.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "SFZHM", sfzhm.getText().toString());

                        JsonUtil.addJsonData(jsonObj, "XIAN", quxian.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "XIANGZHEN", xiangzhen.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "CUN", cun.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "ZU", zu.getText().toString());

                        JsonUtil.addJsonData(jsonObj, "YHDMC", yhdmc.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "HUSHU", wxdx_hu.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "RENSHU", wxdx_ren.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "GGXMMC", ggxmmc.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "GGXMJZAZ", jzaz);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_LX", jzaz_lx);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_ZS", jzaz_zs);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_QDHT", jzaz_qdht);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_QDBCFS", jzaz_qdbcfs);
                        JsonUtil.addJsonData(jsonObj, "JZAZ_QDAZXY", jzaz_qdazxy);
                        JsonUtil.addJsonData(jsonObj, "HBHAZ", hbhaz);
                        JsonUtil.addJsonData(jsonObj, "HBHAZ_BCZJFF", hbhaz_bczjff);
                        JsonUtil.addJsonData(jsonObj, "FGG_FSAZ", fsaz);
                        JsonUtil.addJsonData(jsonObj, "FGG_FSAZ_MFXZ", fsaz_mfxz);
                        JsonUtil.addJsonData(jsonObj, "FGG_FSAZ_XFXJ", fsaz_xfxj);

                        JsonUtil.addJsonData(jsonObj, "FGG_ZZAZ", zzaz);
                        JsonUtil.addJsonData(jsonObj, "FGG_ZZAZ_ZZAZXY", zzaz_zzazxy);
                        JsonUtil.addJsonData(jsonObj, "FGG_ZZAZ_BCZJFF", zzaz_bczjff);

                        JsonUtil.addJsonData(jsonObj, "FGG_JZAZ", fgg_jzaz);
                        JsonUtil.addJsonData(jsonObj, "FGG_JZAZ_AZQXZ", fgg_jzaz_azqxz);
                        JsonUtil.addJsonData(jsonObj, "FGG_JZAZ_SG", fgg_jzaz_sg);


                        JsonUtil.addJsonData(jsonObj, "GDAZ", gdaz);
                        JsonUtil.addJsonData(jsonObj, "XFJC", xfjc);
                        JsonUtil.addJsonData(jsonObj, "XFRZ", xfrz);
                        JsonUtil.addJsonData(jsonObj, "JFCC", jfcc);
                        JsonUtil.addJsonData(jsonObj, "ZJDFK", zjdfk);
                        JsonUtil.addJsonData(jsonObj, "BXBQYS", bxbqys);
                        JsonUtil.addJsonData(jsonObj, "SJKGX", sjkgx);

                        JsonUtil.addJsonData(jsonObj, "CZWT", czwt.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "BZ", bz.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "JCR", jcr.getText().toString());
                        JsonUtil.addJsonData(jsonObj, "JCRQ", jcrq.getText().toString());


                        JsonUtil.addJsonData(jsonObj, "GX_PATH",GX_PATH);
                        JsonUtil.addJsonData(jsonObj, "BQQ_PATH", BQQ_PATH);
                        JsonUtil.addJsonData(jsonObj, "BQH_PATH", BQH_PATH);


                        String imgUrls="";
                        String urlStr="";
                        for (String url:imgs.values()){
                            imgUrls=imgUrls+url+"|";
                        }
                        if(imgUrls.length()>0)
                            urlStr=imgUrls.substring(0, imgUrls.length()-1);
                        JsonUtil.addJsonData(jsonObj, "GZ_PATH", urlStr);



                        requesType=Add;
                        httpHandler.addCJ_BXBQ_XCKP(jsonObj.toString());
                    }
                    break;
                case R.id.delDataBtn:
                    DialogUtil.showActionDialog(CJ_BXBQ_XCKP_edit.this, "提示", "确认要删除", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requesType=Delete;
                            httpHandler.delCJ_BXBQ_XCKP(infoMap.get("ID"));
                        }
                    });
                    break;

                case R.id.jzaz_chk://集中安置
                    if(jzaz!="1") {
                        jzaz="1";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);


                        hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        hbhaz_bczjff="0";
                        fsaz_mfxz=fsaz_xfxj=zzaz_zzazxy=zzaz_bczjff= fgg_jzaz_azqxz=fgg_jzaz_sg="0";


                    }else{
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);


                        jzaz_lx="";
                        jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_zs="";
                        jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdht="";
                        jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdbcfs="";
                        jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdazxy="";
                        jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                    }
                    break;
                case R.id.jzaz_lx_chk://立项
                    if(jzaz=="1"){
                        if(jzaz_lx!="1"){
                            jzaz_lx="1";
                            jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_sel);

                        }else {
                            jzaz_lx="";
                            jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.jzaz_zs_chk://招商
                    if(jzaz=="1"){
                        if(jzaz_zs!="1"){
                            jzaz_zs="1";
                            jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        }else {
                            jzaz_zs="";
                            jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;

                case R.id.jzaz_qdht_chk://签订合同
                    if(jzaz=="1"){
                        if(jzaz_qdht!="1"){
                            jzaz_qdht="1";
                            jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_sel);

                        }else {
                            jzaz_qdht="";
                            jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;

                case R.id.jzaz_qdbcfs_chk://确定补偿方式
                    if(jzaz=="1"){
                        if(jzaz_qdbcfs!="1"){
                            jzaz_qdbcfs="1";

                            jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_sel);

                        }else {
                            jzaz_qdbcfs="";
                            jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;

                case R.id.jzaz_qdazxy_chk://签订安置协议
                    if(jzaz=="1"){
                        if(jzaz_qdazxy!="1"){
                            jzaz_qdazxy="1";

                            jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_sel);

                        }else {
                            jzaz_qdazxy="";
                            jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.hbhaz_chk://货币化安置
                    if(hbhaz!="1") {
                        jzaz="";
                        hbhaz="1";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);



                        fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        jzaz_lx=jzaz_zs=jzaz_qdht=jzaz_qdbcfs=jzaz_qdazxy="0";
                        fsaz_mfxz=fsaz_xfxj=zzaz_zzazxy=zzaz_bczjff= fgg_jzaz_azqxz=fgg_jzaz_sg="0";


                    }else{
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        hbhaz_bczjff="";
                        hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;

                case R.id.hbhaz_bczjff_chk://补偿资金发放
                    if(hbhaz=="1"){
                        if(hbhaz_bczjff!="1"){
                            hbhaz_bczjff="1";

                            hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        }else {
                            hbhaz_bczjff="";
                            hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.fsaz_chk://分散安置
                    if(fsaz!="1") {
                        jzaz="";
                        hbhaz="";
                        fsaz="1";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);


                        jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        jzaz_lx=jzaz_zs=jzaz_qdht=jzaz_qdbcfs=jzaz_qdazxy=hbhaz_bczjff="0";
                        zzaz_zzazxy=zzaz_bczjff= fgg_jzaz_azqxz=fgg_jzaz_sg="0";

                    }else{
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);


                        fsaz_mfxz="";
                        fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fsaz_xfxj="";
                        fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.fsaz_mfxz_chk://民房选址
                    if(fsaz=="1"){
                        if(fsaz_mfxz!="1"){
                            fsaz_mfxz="1";
                            fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_sel);



                        }else {
                            fsaz_mfxz="";
                            fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.fsaz_xfxj_chk://新房修建
                    if(fsaz=="1"){
                        if(fsaz_xfxj!="1"){
                            fsaz_xfxj="1";
                            fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        }else {
                            fsaz_xfxj="";
                            fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.zzaz_chk://自主安置
                    if(zzaz!="1") {
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="1";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);


                        fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        jzaz_lx=jzaz_zs=jzaz_qdht=jzaz_qdbcfs=jzaz_qdazxy=hbhaz_bczjff="0";
                        fsaz_mfxz=fsaz_xfxj= fgg_jzaz_azqxz=fgg_jzaz_sg="0";


                    }else{
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        zzaz_zzazxy="";
                        zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_bczjff="";
                        zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.zzaz_zzazxy_chk://自主安置协议
                    if(zzaz=="1"){
                        if(zzaz_zzazxy!="1"){
                            zzaz_zzazxy="1";
                            zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        }else {
                            zzaz_zzazxy="";
                            zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.zzaz_bczjff_chk://补偿资金发放
                    if(zzaz=="1"){
                        if(zzaz_bczjff!="1"){
                            zzaz_bczjff="1";

                            zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        }else {
                            zzaz_bczjff="";
                            zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.fgg_jzaz_chk://集中安置
                    if(fgg_jzaz!="1") {
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="1";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        jzaz_lx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_zs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdht_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdbcfs_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        jzaz_qdazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        hbhaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fsaz_mfxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_xfxj_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        zzaz_zzazxy_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_bczjff_chk.setImageResource(R.mipmap.app_login_remember_unsel);


                        jzaz_lx=jzaz_zs=jzaz_qdht=jzaz_qdbcfs=jzaz_qdazxy=hbhaz_bczjff="0";
                        fsaz_mfxz=fsaz_xfxj=zzaz_zzazxy=zzaz_bczjff= "0";


                    }else{
                        jzaz="";
                        hbhaz="";
                        fsaz="";
                        zzaz="";
                        fgg_jzaz="";
                        jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        hbhaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fsaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        zzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        fgg_jzaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fgg_jzaz_azqxz="";
                        fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);

                        fgg_jzaz_sg="";
                        fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.fgg_jzaz_azqxz_chk://安置区选址
                    if(fgg_jzaz=="1"){
                        if(fgg_jzaz_azqxz!="1"){
                            fgg_jzaz_azqxz="1";
                            fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_sel);


                        }else {
                            fgg_jzaz_azqxz="";
                            fgg_jzaz_azqxz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.fgg_jzaz_sg_chk://施工
                    if(fgg_jzaz=="1"){
                        if(fgg_jzaz_sg!="1"){
                            fgg_jzaz_sg="1";
                            fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_sel);

                        }else {
                            fgg_jzaz_sg="";
                            fgg_jzaz_sg_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                        }
                    }
                    break;
                case R.id.gdaz_chk://过渡安置
                    if(gdaz!="1") {
                        gdaz="1";
                        gdaz_chk.setImageResource(R.mipmap.app_login_remember_sel);
                    }
                    else {
                        gdaz = "";
                        gdaz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;

                case R.id.xfjc_chk://新房建成
                    if(xfjc!="1") {
                        xfjc = "1";
                        xfjc_chk.setImageResource(R.mipmap.app_login_remember_sel);
                    }
                    else {
                        xfjc="";
                        xfjc_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.xfrz_chk://新房入住
                    if(xfrz!="1") {
                        xfrz="1";
                        xfrz_chk.setImageResource(R.mipmap.app_login_remember_sel);
                    }
                    else {
                        xfrz="";
                        xfrz_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.jfcc_chk://旧房拆除
                    if(jfcc!="1") {
                        jfcc="1";
                        jfcc_chk.setImageResource(R.mipmap.app_login_remember_sel);
                    }
                    else {
                        jfcc="";
                        jfcc_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.zjdfk_chk://宅基地复垦
                    if(zjdfk!="1") {
                        zjdfk="1";
                        zjdfk_chk.setImageResource(R.mipmap.app_login_remember_sel);
                    }
                    else {
                        zjdfk="";
                        zjdfk_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.bxbqys_chk://避险搬迁验收
                    if(bxbqys!="1") {
                        bxbqys = "1";
                        bxbqys_chk.setImageResource(R.mipmap.app_login_remember_sel);

                    }
                    else {
                        bxbqys="";
                        bxbqys_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
                    break;
                case R.id.sjkgx_chk://数据库更新
                    if(sjkgx!="1") {
                        sjkgx="1";
                        sjkgx_chk.setImageResource(R.mipmap.app_login_remember_sel);
                    }
                    else {
                        sjkgx="";
                        sjkgx_chk.setImageResource(R.mipmap.app_login_remember_unsel);
                    }
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

         if (resultCode == RESULT_OK && requestCode == TO_SELECT_PHOTO) {
            final String picPath = data.getStringExtra(ConstantUtil.Photo_Path);


            final Bitmap bitmap=ImageUtil.getSmallBitmap(picPath);
            final String imgkey= String.valueOf(System.currentTimeMillis());
            seImageView(bitmap, imgkey);
            horiScroller.scrollBy(imgItemWidth,0);
            dialog= ProgressDialog.show(CJ_BXBQ_XCKP_edit.this, "", "处理中");
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
                    ToastUtils.displayTextShort(CJ_BXBQ_XCKP_edit.this, "上传失败");
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
