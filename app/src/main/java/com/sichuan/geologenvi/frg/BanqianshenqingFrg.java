package com.sichuan.geologenvi.frg;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
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
    TextView dataTxt1;
    private EditText nameEdt, idEdt;
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
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bqbr_edit_1, container, false);


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
        idEdt= (EditText) v.findViewById(R.id.idEdt);

        v.findViewById(R.id.dateLayout1).setOnClickListener(listener);
        Calendar mCalendar = Calendar.getInstance(Locale.CHINA);
        long todayL=mCalendar.getTimeInMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(todayL);//获取当
        String dateTxt = formatter.format(curDate);
        dataTxt1= (TextView) v.findViewById(R.id.dataTxt1);
        dataTxt1.setText(dateTxt);
        v.findViewById(R.id.addDataBtn).setOnClickListener(listener);
        v.findViewById(R.id.yearLayout).setOnClickListener(listener);
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
}
