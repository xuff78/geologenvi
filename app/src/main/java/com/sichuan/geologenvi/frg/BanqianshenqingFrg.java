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
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/7/6.
 */
public class BanqianshenqingFrg extends BaseFragment{

    DatePickerDialog datePickerDialog;
    TextView dataTxt1,districtTxt;
    private EditText nameEdt, addrEdt;
    private ImageView gongshi_yes_icon, gongshi_no_icon, jiancedian_city_icon, jiancedian_quxian_icon;
    private HttpHandler handler;
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
                case R.id.addDataBtn:
                    String jsonContent=getInfoString();
                    handler.addBangqianBaseInfo(jsonContent);
                    break;
                case R.id.updateDataBtn:
                    break;
                case R.id.delDataBtn:
                    break;
                case R.id.districtLayout:
                    Intent selectIntent=new Intent(getActivity(), AreaInputAct.class);
                    startActivityForResult(selectIntent, 0x12);
                    break;
                case R.id.gongshi_yes_txt:
                case R.id.gongshi_yes_icon:
                    gongshi_yes_icon.setImageResource(R.mipmap.app_login_remember_sel);
                    gongshi_no_icon.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.gongshi_no_txt:
                case R.id.gongshi_no_icon:
                    gongshi_yes_icon.setImageResource(R.mipmap.app_login_remember_unsel);
                    gongshi_no_icon.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
                case R.id.jiancedian_city_txt:
                case R.id.jiancedian_city_icon:
                    jiancedian_city_icon.setImageResource(R.mipmap.app_login_remember_sel);
                    jiancedian_quxian_icon.setImageResource(R.mipmap.app_login_remember_unsel);
                    break;
                case R.id.jiancedian_quxian_txt:
                case R.id.jiancedian_quxian_icon:
                    jiancedian_city_icon.setImageResource(R.mipmap.app_login_remember_unsel);
                    jiancedian_quxian_icon.setImageResource(R.mipmap.app_login_remember_sel);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bqbr_edit_3, container, false);


        initView(view);
        initHandler();
        return view;
    }

    private void initHandler() {
        handler=new HttpHandler(getActivity(), new CallBack(getActivity()){

            @Override
            public void doSuccess(String method, String jsonData) {

            }
        });
    }

    private void initView(View v) {
        nameEdt= (EditText) v.findViewById(R.id.nameEdt);
        addrEdt= (EditText) v.findViewById(R.id.addrEdt);
        districtTxt= (TextView) v.findViewById(R.id.districtTxt);
        gongshi_yes_icon= (ImageView) v.findViewById(R.id.gongshi_yes_icon);
        gongshi_no_icon= (ImageView) v.findViewById(R.id.gongshi_no_icon);
        jiancedian_city_icon= (ImageView) v.findViewById(R.id.jiancedian_city_icon);
        jiancedian_quxian_icon= (ImageView) v.findViewById(R.id.jiancedian_quxian_icon);

        gongshi_yes_icon.setOnClickListener(listener);
        gongshi_no_icon.setOnClickListener(listener);
        jiancedian_city_icon.setOnClickListener(listener);
        jiancedian_quxian_icon.setOnClickListener(listener);
        v.findViewById(R.id.gongshi_yes_txt).setOnClickListener(listener);
        v.findViewById(R.id.gongshi_no_txt).setOnClickListener(listener);
        v.findViewById(R.id.jiancedian_city_txt).setOnClickListener(listener);
        v.findViewById(R.id.jiancedian_quxian_txt).setOnClickListener(listener);

        v.findViewById(R.id.dateLayout1).setOnClickListener(listener);
        Calendar mCalendar = Calendar.getInstance(Locale.CHINA);
        long todayL=mCalendar.getTimeInMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(todayL);//获取当
        String dateTxt = formatter.format(curDate);
        dataTxt1= (TextView) v.findViewById(R.id.dataTxt1);
        dataTxt1.setText(dateTxt);
        v.findViewById(R.id.addDataBtn).setOnClickListener(listener);
        v.findViewById(R.id.nameLayout).setOnClickListener(listener);
        v.findViewById(R.id.districtLayout).setOnClickListener(listener);
    }

    private String getInfoString() {
        String jsonContent="";
        jsonContent= ActUtil.addStringContent("ZHDD04B020", nameEdt, jsonContent);
        return jsonContent;
    }

    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            System.out.println("---> 设置后: year="+year+", month="+monthOfYear+",day="+dayOfMonth);
            dataTxt1.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0x12&&resultCode==0x10){
            AreaInfos infos= (AreaInfos) data.getSerializableExtra(AreaInfos.Name);
            String selectTxt="";
            for(AreaInfo info:infos.getInfos()){
                selectTxt=selectTxt+info.getName()+"  ";
            }
            districtTxt.setText(selectTxt);
        }
    }
}
