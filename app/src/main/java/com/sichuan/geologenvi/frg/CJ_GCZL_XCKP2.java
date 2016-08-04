package com.sichuan.geologenvi.frg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.report.AreaInputAct;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.AreaInfos;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.JsonUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/30.
 */
public class CJ_GCZL_XCKP2 extends BaseFragment{

    DatePickerDialog datePickerDialog;
    TextView dataTxt1, districtTxt;
    private EditText nameEdt, idEdt;
    private HttpHandler handler;
    private Map<String, String> infoMap=new HashMap<>();

    private ImageView gj_cd_yes, gj_cd_no, gj_zj_yes, gj_zj_no, gj_jj_yes, gj_jj_no, gj_cchgz_yes, gj_cchgz_no, gj_xccj_yes, gj_xccj_no,
            sy_sfysjxfsnbh_yes, sy_sfysjxfsnbh_no, sy_cchgz_yes, sy_cchgz_no, sy_xccj_yes, sy_xccj_no,
            gl_dx_yes, gl_dx_no, gl_jyd_yes, gl_jyd_no, gl_syhgbg_yes, gl_syhgbg_no, gl_hnl_yes, gl_hnl_no,
            sjphb_czyq_yes, sjphb_czyq_no, sjphb_ssbg_yes, sjphb_ssbg_no, sjphb_phbgp_yes, sjphb_phbgp_no, ycl_sjphbsyjbjjb_yes, ycl_sjphbsyjbjjb_no,
            sl_qd_yes, sl_qd_no, sl_bmpzcd_yes, sl_bmpzcd_no, sl_jyd_yes, sl_jyd_no, sl_lj_yes, sl_lj_no,
            sgjlsfjsyx_yes, sgjlsfjsyx_no, sgsfagfjx_yes, sgsfagfjx_no;
    private EditText gj_bz, sn_bz, gl_bz, ycl_sjphbbz, sl_bz;
    private String gj_cd,gj_zj,gj_jj,gj_cchgz,gj_xccj,   sy_sfysjxfsnbh,sy_cchgz,sy_xccj,    gl_dx,gl_jyd,gl_syhgbg,gl_hnl,
            sjphb_czyq, sjphb_ssbg,sjphb_phbgp, ycl_sjphbsyjbjjb,    sl_qd,sl_bmpzcd,sl_jyd,sl_lj,     sgjlsfjsyx,sgsfagfjx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gczl_xckp2, container, false);


        initView(view);
        if(getActivity().getIntent().hasExtra("InfoMap")) {
            infoMap=((MapBean)(getActivity().getIntent().getSerializableExtra("InfoMap"))).getMap();
            initData();
        }
        return view;
    }

    public void getDataByJson(JSONObject jsonObj){

        JsonUtil.addJsonData(jsonObj, "gj_cd", gj_cd);
        JsonUtil.addJsonData(jsonObj, "gj_zj", gj_zj);
        JsonUtil.addJsonData(jsonObj, "gj_jj", gj_jj);
        JsonUtil.addJsonData(jsonObj, "gj_cchgz", gj_cchgz);
        JsonUtil.addJsonData(jsonObj, "gj_xccj", gj_xccj);

        JsonUtil.addJsonData(jsonObj, "sy_sfysjxfsnbh", sy_sfysjxfsnbh);
        JsonUtil.addJsonData(jsonObj, "sy_cchgz", sy_cchgz);
        JsonUtil.addJsonData(jsonObj, "sy_xccj", sy_xccj);

        JsonUtil.addJsonData(jsonObj, "gl_dx", gl_dx);
        JsonUtil.addJsonData(jsonObj, "gl_jyd", gl_jyd);
        JsonUtil.addJsonData(jsonObj, "gl_syhgbg", gl_syhgbg);
        JsonUtil.addJsonData(jsonObj, "gl_hnl", gl_hnl);

        JsonUtil.addJsonData(jsonObj, "sjphb_czyq", sjphb_czyq);
        JsonUtil.addJsonData(jsonObj, "sjphb_ssbg", sjphb_ssbg);
        JsonUtil.addJsonData(jsonObj, "sjphb_phbgp", sjphb_phbgp);
        JsonUtil.addJsonData(jsonObj, "ycl_sjphbsyjbjjb", ycl_sjphbsyjbjjb);

        JsonUtil.addJsonData(jsonObj, "sl_qd", sl_qd);
        JsonUtil.addJsonData(jsonObj, "sl_bmpzcd", sl_bmpzcd);
        JsonUtil.addJsonData(jsonObj, "sl_jyd", sl_jyd);
        JsonUtil.addJsonData(jsonObj, "sl_lj", sl_lj);

        JsonUtil.addJsonData(jsonObj, "sgjlsfjsyx", sgjlsfjsyx);
        JsonUtil.addJsonData(jsonObj, "sgsfagfjx", sgsfagfjx);

        JsonUtil.addJsonData(jsonObj, "gj_bz", gj_bz.getText().toString());
        JsonUtil.addJsonData(jsonObj, "sn_bz", sn_bz.getText().toString());
        JsonUtil.addJsonData(jsonObj, "gl_bz", gl_bz.getText().toString());

        JsonUtil.addJsonData(jsonObj, "ycl_sjphbbz", ycl_sjphbbz.getText().toString());
        JsonUtil.addJsonData(jsonObj, "sl_bz", sl_bz.getText().toString());

    }

    private void initData() {
        gj_bz.setText(infoMap.get("gj_bz".toUpperCase()));
        sn_bz.setText(infoMap.get("sn_bz".toUpperCase()));
        gl_bz.setText(infoMap.get("gl_bz".toUpperCase()));
        ycl_sjphbbz.setText(infoMap.get("ycl_sjphbbz".toUpperCase()));
        sl_bz.setText(infoMap.get("sl_bz".toUpperCase()));

        sgjlsfjsyx=infoMap.get("sgjlsfjsyx".toUpperCase());
        if(sgjlsfjsyx.equals("是")) {
            sgjlsfjsyx_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sgjlsfjsyx.equals("否")) {
            sgjlsfjsyx_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sgsfagfjx=infoMap.get("sgsfagfjx".toUpperCase());
        if(sgsfagfjx.equals("是")) {
            sgsfagfjx_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sgsfagfjx.equals("否")) {
            sgsfagfjx_no.setImageResource(R.mipmap.app_login_remember_sel);
        }


        sl_qd=infoMap.get("sl_qd".toUpperCase());
        if(sl_qd.equals("符合要求")) {
            sl_qd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sl_qd.equals("不符合要求")) {
            sl_qd_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sl_bmpzcd=infoMap.get("sl_bmpzcd".toUpperCase());
        if(sl_bmpzcd.equals("平整")) {
            sl_bmpzcd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sl_bmpzcd.equals("不平整")) {
            sl_bmpzcd_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sl_jyd=infoMap.get("sl_jyd".toUpperCase());
        if(sl_jyd.equals("均匀")) {
            sl_jyd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sl_jyd.equals("不均匀")) {
            sl_jyd_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sl_lj=infoMap.get("sl_lj".toUpperCase());
        if(sl_lj.equals("有")) {
            sl_lj_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sl_lj.equals("无")) {
            sl_lj_no.setImageResource(R.mipmap.app_login_remember_sel);
        }


        sjphb_czyq=infoMap.get("sjphb_czyq".toUpperCase());
        if(sjphb_czyq.equals("有")) {
            sjphb_czyq_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sjphb_czyq.equals("无")) {
            sjphb_czyq_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sjphb_ssbg=infoMap.get("sjphb_ssbg".toUpperCase());
        if(sjphb_ssbg.equals("有")) {
            sjphb_ssbg_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sjphb_ssbg.equals("无")) {
            sjphb_ssbg_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sjphb_phbgp=infoMap.get("sjphb_phbgp".toUpperCase());
        if(sjphb_phbgp.equals("有")) {
            sjphb_phbgp_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sjphb_phbgp.equals("无")) {
            sjphb_phbgp_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        ycl_sjphbsyjbjjb=infoMap.get("ycl_sjphbsyjbjjb".toUpperCase());
        if(ycl_sjphbsyjbjjb.equals("有")) {
            ycl_sjphbsyjbjjb_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(ycl_sjphbsyjbjjb.equals("无")) {
            ycl_sjphbsyjbjjb_no.setImageResource(R.mipmap.app_login_remember_sel);
        }


        gl_dx=infoMap.get("gl_dx".toUpperCase());
        if(gl_dx.equals("符合要求")) {
            gl_dx_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gl_dx.equals("不符合要求")) {
            gl_dx_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gl_jyd=infoMap.get("gl_jyd".toUpperCase());
        if(gl_jyd.equals("符合要求")) {
            gl_jyd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gl_jyd.equals("不符合要求")) {
            gl_jyd_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gl_syhgbg=infoMap.get("gl_syhgbg".toUpperCase());
        if(gl_syhgbg.equals("有")) {
            gl_syhgbg_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gl_syhgbg.equals("无")) {
            gl_syhgbg_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gl_hnl=infoMap.get("gl_hnl".toUpperCase());
        if(gl_hnl.equals("符合要求")) {
            gl_hnl_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gl_hnl.equals("不符合要求")) {
            gl_hnl_no.setImageResource(R.mipmap.app_login_remember_sel);
        }



        sy_sfysjxfsnbh=infoMap.get("sy_sfysjxfsnbh".toUpperCase());
        if(sy_sfysjxfsnbh.equals("是")) {
            sy_sfysjxfsnbh_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sy_sfysjxfsnbh.equals("否")) {
            sy_sfysjxfsnbh_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sy_cchgz=infoMap.get("sy_cchgz".toUpperCase());
        if(sy_cchgz.equals("有")) {
            sy_cchgz_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sy_cchgz.equals("无")) {
            sy_cchgz_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        sy_xccj=infoMap.get("sy_xccj".toUpperCase());
        if(sy_xccj.equals("有")) {
            sy_xccj_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(sy_xccj.equals("无")) {
            sy_xccj_no.setImageResource(R.mipmap.app_login_remember_sel);
        }


        gj_cd=infoMap.get("gj_cd".toUpperCase());
        if(gj_cd.equals("是")) {
            gj_cd_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gj_cd.equals("否")) {
            gj_cd_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gj_zj=infoMap.get("gj_zj".toUpperCase());
        if(gj_zj.equals("是")) {
            gj_zj_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gj_zj.equals("否")) {
            gj_zj_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gj_jj=infoMap.get("gj_jj".toUpperCase());
        if(gj_jj.equals("是")) {
            gj_jj_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gj_jj.equals("否")) {
            gj_jj_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gj_cchgz=infoMap.get("gj_cchgz".toUpperCase());
        if(gj_cchgz.equals("有")) {
            gj_cchgz_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gj_cchgz.equals("无")) {
            gj_cchgz_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
        gj_xccj=infoMap.get("gj_xccj".toUpperCase());
        if(gj_xccj.equals("有")) {
            gj_xccj_yes.setImageResource(R.mipmap.app_login_remember_sel);
        }else if(gj_xccj.equals("无")) {
            gj_xccj_no.setImageResource(R.mipmap.app_login_remember_sel);
        }
    }

    private void initView(View v) {
        sgjlsfjsyx_yes= (ImageView) v.findViewById(R.id.sgjlsfjsyx_yes);
        sgjlsfjsyx_yes.setOnClickListener(listener);
        sgjlsfjsyx_no= (ImageView) v.findViewById(R.id.sgjlsfjsyx_no);
        sgjlsfjsyx_no.setOnClickListener(listener);
        sgsfagfjx_yes= (ImageView) v.findViewById(R.id.sgsfagfjx_yes);
        sgsfagfjx_yes.setOnClickListener(listener);
        sgsfagfjx_no= (ImageView) v.findViewById(R.id.sgsfagfjx_no);
        sgsfagfjx_no.setOnClickListener(listener);

        sl_qd_yes= (ImageView) v.findViewById(R.id.sl_qd_yes);
        sl_qd_yes.setOnClickListener(listener);
        sl_qd_no= (ImageView) v.findViewById(R.id.sl_qd_no);
        sl_qd_no.setOnClickListener(listener);
        sl_bmpzcd_yes= (ImageView) v.findViewById(R.id.sl_bmpzcd_yes);
        sl_bmpzcd_yes.setOnClickListener(listener);
        sl_bmpzcd_no= (ImageView) v.findViewById(R.id.sl_bmpzcd_no);
        sl_bmpzcd_no.setOnClickListener(listener);
        sl_jyd_yes= (ImageView) v.findViewById(R.id.sl_jyd_yes);
        sl_jyd_yes.setOnClickListener(listener);
        sl_jyd_no= (ImageView) v.findViewById(R.id.sl_jyd_no);
        sl_jyd_no.setOnClickListener(listener);
        sl_lj_yes= (ImageView) v.findViewById(R.id.sl_lj_yes);
        sl_lj_yes.setOnClickListener(listener);
        sl_lj_no= (ImageView) v.findViewById(R.id.sl_lj_no);
        sl_lj_no.setOnClickListener(listener);

        sjphb_czyq_yes= (ImageView) v.findViewById(R.id.sjphb_czyq_yes);
        sjphb_czyq_yes.setOnClickListener(listener);
        sjphb_czyq_no= (ImageView) v.findViewById(R.id.sjphb_czyq_no);
        sjphb_czyq_no.setOnClickListener(listener);
        sjphb_ssbg_yes= (ImageView) v.findViewById(R.id.sjphb_ssbg_yes);
        sjphb_ssbg_yes.setOnClickListener(listener);
        sjphb_ssbg_no= (ImageView) v.findViewById(R.id.sjphb_ssbg_no);
        sjphb_ssbg_no.setOnClickListener(listener);
        sjphb_phbgp_yes= (ImageView) v.findViewById(R.id.sjphb_phbgp_yes);
        sjphb_phbgp_yes.setOnClickListener(listener);
        sjphb_phbgp_no= (ImageView) v.findViewById(R.id.sjphb_phbgp_no);
        sjphb_phbgp_no.setOnClickListener(listener);
        ycl_sjphbsyjbjjb_yes= (ImageView) v.findViewById(R.id.ycl_sjphbsyjbjjb_yes);
        ycl_sjphbsyjbjjb_yes.setOnClickListener(listener);
        ycl_sjphbsyjbjjb_no= (ImageView) v.findViewById(R.id.ycl_sjphbsyjbjjb_no);
        ycl_sjphbsyjbjjb_no.setOnClickListener(listener);

        gl_dx_yes= (ImageView) v.findViewById(R.id.gl_dx_yes);
        gl_dx_yes.setOnClickListener(listener);
        gl_dx_no= (ImageView) v.findViewById(R.id.gl_dx_no);
        gl_dx_no.setOnClickListener(listener);
        gl_jyd_yes= (ImageView) v.findViewById(R.id.gl_jyd_yes);
        gl_jyd_yes.setOnClickListener(listener);
        gl_jyd_no= (ImageView) v.findViewById(R.id.gl_jyd_no);
        gl_jyd_no.setOnClickListener(listener);
        gl_syhgbg_yes= (ImageView) v.findViewById(R.id.gl_syhgbg_yes);
        gl_syhgbg_yes.setOnClickListener(listener);
        gl_syhgbg_no= (ImageView) v.findViewById(R.id.gl_syhgbg_no);
        gl_syhgbg_no.setOnClickListener(listener);
        gl_hnl_yes= (ImageView) v.findViewById(R.id.gl_hnl_yes);
        gl_hnl_yes.setOnClickListener(listener);
        gl_hnl_no= (ImageView) v.findViewById(R.id.gl_hnl_no);
        gl_hnl_no.setOnClickListener(listener);

        sy_sfysjxfsnbh_yes= (ImageView) v.findViewById(R.id.sy_sfysjxfsnbh_yes);
        sy_sfysjxfsnbh_yes.setOnClickListener(listener);
        sy_sfysjxfsnbh_no= (ImageView) v.findViewById(R.id.sy_sfysjxfsnbh_no);
        sy_sfysjxfsnbh_no.setOnClickListener(listener);
        sy_cchgz_yes= (ImageView) v.findViewById(R.id.sy_cchgz_yes);
        sy_cchgz_yes.setOnClickListener(listener);
        sy_cchgz_no= (ImageView) v.findViewById(R.id.sy_cchgz_no);
        sy_cchgz_no.setOnClickListener(listener);
        sy_xccj_yes= (ImageView) v.findViewById(R.id.sy_xccj_yes);
        sy_xccj_yes.setOnClickListener(listener);
        sy_xccj_no= (ImageView) v.findViewById(R.id.sy_xccj_no);
        sy_xccj_no.setOnClickListener(listener);

        gj_cd_yes= (ImageView) v.findViewById(R.id.gj_cd_yes);
        gj_cd_yes.setOnClickListener(listener);
        gj_cd_no= (ImageView) v.findViewById(R.id.gj_cd_no);
        gj_cd_no.setOnClickListener(listener);
        gj_zj_yes= (ImageView) v.findViewById(R.id.gj_zj_yes);
        gj_zj_yes.setOnClickListener(listener);
        gj_zj_no= (ImageView) v.findViewById(R.id.gj_zj_no);
        gj_zj_no.setOnClickListener(listener);
        gj_jj_yes= (ImageView) v.findViewById(R.id.gj_jj_yes);
        gj_jj_yes.setOnClickListener(listener);
        gj_jj_no= (ImageView) v.findViewById(R.id.gj_jj_no);
        gj_jj_no.setOnClickListener(listener);
        gj_cchgz_yes= (ImageView) v.findViewById(R.id.gj_cchgz_yes);
        gj_cchgz_yes.setOnClickListener(listener);
        gj_cchgz_no= (ImageView) v.findViewById(R.id.gj_cchgz_no);
        gj_cchgz_no.setOnClickListener(listener);
        gj_xccj_yes= (ImageView) v.findViewById(R.id.gj_xccj_yes);
        gj_xccj_yes.setOnClickListener(listener);
        gj_xccj_no= (ImageView) v.findViewById(R.id.gj_xccj_no);
        gj_xccj_no.setOnClickListener(listener);

        gj_bz= (EditText) v.findViewById(R.id.gj_bz);
        sn_bz= (EditText) v.findViewById(R.id.sn_bz);
        gl_bz= (EditText) v.findViewById(R.id.gl_bz);
        ycl_sjphbbz= (EditText) v.findViewById(R.id.ycl_sjphbbz);
        sl_bz= (EditText) v.findViewById(R.id.sl_bz);

    }

    private View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.dateLayout1:
                    String[] dateStart=dataTxt1.getText().toString().split("-");
                    datePickerDialog=new DatePickerDialog(getActivity(), mDateSetListener, Integer.valueOf(dateStart[0]),
                            Integer.valueOf(dateStart[1])-1, Integer.valueOf(dateStart[2]));
                    datePickerDialog.show();
                    break;
                case R.id.districtLayout:
                    Intent selectIntent=new Intent(getActivity(), AreaInputAct.class);
                    startActivityForResult(selectIntent, 0x11);
                    break;

                case R.id.sjphb_czyq_yes:
                    sjphb_czyq="有";
                    sjphb_czyq_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sjphb_czyq_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sjphb_czyq_no:
                    sjphb_czyq="无";
                    sjphb_czyq_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sjphb_czyq_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sjphb_ssbg_yes:
                    sjphb_ssbg="有";
                    sjphb_ssbg_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sjphb_ssbg_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sjphb_ssbg_no:
                    sjphb_ssbg="无";
                    sjphb_ssbg_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sjphb_ssbg_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sjphb_phbgp_yes:
                    sjphb_phbgp="有";
                    sjphb_phbgp_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sjphb_phbgp_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sjphb_phbgp_no:
                    sjphb_phbgp="无";
                    sjphb_phbgp_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sjphb_phbgp_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.ycl_sjphbsyjbjjb_yes:
                    ycl_sjphbsyjbjjb="有";
                    ycl_sjphbsyjbjjb_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    ycl_sjphbsyjbjjb_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.ycl_sjphbsyjbjjb_no:
                    ycl_sjphbsyjbjjb="无";
                    ycl_sjphbsyjbjjb_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    ycl_sjphbsyjbjjb_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.sgjlsfjsyx_yes:
                    sgjlsfjsyx="是";
                    sgjlsfjsyx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sgjlsfjsyx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sgjlsfjsyx_no:
                    sgjlsfjsyx="否";
                    sgjlsfjsyx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sgjlsfjsyx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sgsfagfjx_yes:
                    sgsfagfjx="是";
                    sgsfagfjx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sgsfagfjx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sgsfagfjx_no:
                    sgsfagfjx="否";
                    sgsfagfjx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sgsfagfjx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.sl_qd_yes:
                    sl_qd="符合要求";
                    sl_qd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_qd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_qd_no:
                    sl_qd="不符合要求";
                    sl_qd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_qd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sl_bmpzcd_yes:
                    sl_bmpzcd="平整";
                    sl_bmpzcd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_bmpzcd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_bmpzcd_no:
                    sl_bmpzcd="不平整";
                    sl_bmpzcd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_bmpzcd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sl_jyd_yes:
                    sl_jyd="均匀";
                    sl_jyd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_jyd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_jyd_no:
                    sl_jyd="不均匀";
                    sl_jyd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_jyd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sl_lj_yes:
                    sl_lj="有";
                    sl_lj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_lj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_lj_no:
                    sl_lj="无";
                    sl_lj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_lj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.gl_dx_yes:
                    gl_dx="符合要求";
                    gl_dx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_dx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_dx_no:
                    gl_dx="不符合要求";
                    gl_dx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_dx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gl_jyd_yes:
                    gl_jyd="符合要求";
                    gl_jyd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_jyd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_jyd_no:
                    gl_jyd="不符合要求";
                    gl_jyd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_jyd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gl_syhgbg_yes:
                    gl_syhgbg="有";
                    gl_syhgbg_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_syhgbg_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_syhgbg_no:
                    gl_syhgbg="无";
                    gl_syhgbg_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_syhgbg_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gl_hnl_yes:
                    gl_hnl="符合要求";
                    gl_hnl_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_hnl_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_hnl_no:
                    gl_hnl="不符合要求";
                    gl_hnl_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_hnl_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.gj_cd_yes:
                    gj_cd="是";
                    gj_cd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_cd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_cd_no:
                    gj_cd="否";
                    gj_cd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_cd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_zj_yes:
                    gj_zj="是";
                    gj_zj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_zj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_zj_no:
                    gj_zj="否";
                    gj_zj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_zj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_jj_yes:
                    gj_jj="是";
                    gj_jj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_jj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_jj_no:
                    gj_jj="否";
                    gj_jj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_jj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_cchgz_yes:
                    gj_cchgz="有";
                    gj_cchgz_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_cchgz_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_cchgz_no:
                    gj_cchgz="无";
                    gj_cchgz_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_cchgz_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_xccj_yes:
                    gj_xccj="有";
                    gj_xccj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_xccj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_xccj_no:
                    gj_xccj="无";
                    gj_xccj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_xccj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.sy_sfysjxfsnbh_yes:
                    sy_sfysjxfsnbh="是";
                    sy_sfysjxfsnbh_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sy_sfysjxfsnbh_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sy_sfysjxfsnbh_no:
                    sy_sfysjxfsnbh="否";
                    sy_sfysjxfsnbh_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sy_sfysjxfsnbh_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sy_cchgz_yes:
                    sy_cchgz="有";
                    sy_cchgz_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sy_cchgz_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sy_cchgz_no:
                    sy_cchgz="无";
                    sy_cchgz_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sy_cchgz_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sy_xccj_yes:
                    sy_xccj="有";
                    sy_xccj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sy_xccj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sy_xccj_no:
                    sy_xccj="无";
                    sy_xccj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sy_xccj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
            }
        }
    };

    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            System.out.println("---> 设置后: year="+year+", month="+monthOfYear+",day="+dayOfMonth);
            dataTxt1.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0x11&&resultCode==0x10){
            AreaInfos infos= (AreaInfos) data.getSerializableExtra(AreaInfos.Name);
            String selectTxt="";
            for(AreaInfo info:infos.getInfos()){
                selectTxt=selectTxt+info.getName()+"  ";
            }
            districtTxt.setText(selectTxt);
        }
    }
}
