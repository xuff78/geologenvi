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
import com.sichuan.geologenvi.http.HttpHandler;

/**
 * Created by 可爱的蘑菇 on 2016/7/30.
 */
public class CJ_GCZL_XCKP2 extends BaseFragment{

    DatePickerDialog datePickerDialog;
    TextView dataTxt1, districtTxt;
    private EditText nameEdt, idEdt;
    private HttpHandler handler;

    private ImageView gj_cd_yes, gj_cd_no, gj_zj_yes, gj_zj_no, gj_jj_yes, gj_jj_no, gj_cchgz_yes, gj_cchgz_no, gj_xccj_yes, gj_xccj_no,
            sy_sfysjxfsnbh_yes, sy_sfysjxfsnbh_no, sy_cchgz_yes, sy_cchgz_no, sy_xccj_yes, sy_xccj_no,
            gl_dx_yes, gl_dx_no, gl_jyd_yes, gl_jyd_no, gl_syhgbg_yes, gl_syhgbg_no, gl_hnl_yes, gl_hnl_no,
            sjphb_czyq_yes, sjphb_czyq_no, sjphb_ssbg_yes, sjphb_ssbg_no, sjphb_phbgp_yes, sjphb_phbgp_no, ycl_sjphbsyjbjjb_yes, ycl_sjphbsyjbjjb_no,
            sl_qd_yes, sl_qd_no, sl_bmpzcd_yes, sl_bmpzcd_no, sl_jyd_yes, sl_jyd_no, sl_lj_yes, sl_lj_no,
            sgjlsfjsyx_yes, sgjlsfjsyx_no, sgsfagfjx_yes, sgsfagfjx_no;
    private EditText gj_bz, sn_bz, gl_bz, ycl_sjphbbz, sl_bz;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gczl_xckp2, container, false);


        initView(view);
        return view;
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
                    sjphb_czyq_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sjphb_czyq_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sjphb_czyq_no:
                    sjphb_czyq_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sjphb_czyq_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sjphb_ssbg_yes:
                    sjphb_ssbg_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sjphb_ssbg_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sjphb_ssbg_no:
                    sjphb_ssbg_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sjphb_ssbg_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sjphb_phbgp_yes:
                    sjphb_phbgp_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sjphb_phbgp_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sjphb_phbgp_no:
                    sjphb_phbgp_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sjphb_phbgp_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.ycl_sjphbsyjbjjb_yes:
                    ycl_sjphbsyjbjjb_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    ycl_sjphbsyjbjjb_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.ycl_sjphbsyjbjjb_no:
                    ycl_sjphbsyjbjjb_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    ycl_sjphbsyjbjjb_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.sgjlsfjsyx_yes:
                    sgjlsfjsyx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sgjlsfjsyx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sgjlsfjsyx_no:
                    sgjlsfjsyx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sgjlsfjsyx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sgsfagfjx_yes:
                    sgsfagfjx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sgsfagfjx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sgsfagfjx_no:
                    sgsfagfjx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sgsfagfjx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.sl_qd_yes:
                    sl_qd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_qd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_qd_no:
                    sl_qd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_qd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sl_bmpzcd_yes:
                    sl_bmpzcd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_bmpzcd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_bmpzcd_no:
                    sl_bmpzcd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_bmpzcd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sl_jyd_yes:
                    sl_jyd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_jyd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_jyd_no:
                    sl_jyd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_jyd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sl_lj_yes:
                    sl_lj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sl_lj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sl_lj_no:
                    sl_lj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sl_lj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.gl_dx_yes:
                    gl_dx_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_dx_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_dx_no:
                    gl_dx_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_dx_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gl_jyd_yes:
                    gl_jyd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_jyd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_jyd_no:
                    gl_jyd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_jyd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gl_syhgbg_yes:
                    gl_syhgbg_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_syhgbg_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_syhgbg_no:
                    gl_syhgbg_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_syhgbg_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gl_hnl_yes:
                    gl_hnl_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gl_hnl_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gl_hnl_no:
                    gl_hnl_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gl_hnl_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.gj_cd_yes:
                    gj_cd_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_cd_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_cd_no:
                    gj_cd_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_cd_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_zj_yes:
                    gj_zj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_zj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_zj_no:
                    gj_zj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_zj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_jj_yes:
                    gj_jj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_jj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_jj_no:
                    gj_jj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_jj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_cchgz_yes:
                    gj_cchgz_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_cchgz_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_cchgz_no:
                    gj_cchgz_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_cchgz_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.gj_xccj_yes:
                    gj_xccj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    gj_xccj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gj_xccj_no:
                    gj_xccj_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    gj_xccj_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;


                case R.id.sy_sfysjxfsnbh_yes:
                    sy_sfysjxfsnbh_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sy_sfysjxfsnbh_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sy_sfysjxfsnbh_no:
                    sy_sfysjxfsnbh_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sy_sfysjxfsnbh_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sy_cchgz_yes:
                    sy_cchgz_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sy_cchgz_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sy_cchgz_no:
                    sy_cchgz_yes.setImageResource(R.mipmap.app_login_remember_unsel);
                    sy_cchgz_no.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.sy_xccj_yes:
                    sy_xccj_yes.setImageResource(R.mipmap.app_login_remember_sel);
                    sy_xccj_no.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.sy_xccj_no:
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
