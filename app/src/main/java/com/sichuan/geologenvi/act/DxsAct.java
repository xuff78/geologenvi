package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.util.Log;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by tanqq on 2016/8/30.
 */
public class DxsAct   extends AppFrameAct {

    private TextView content;

    private int type=0;
    private String title;
    private SqlHandler handler;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dxs);

        title=getIntent().getStringExtra("Title");
        _setHeaderTitle(title);
        type=getIntent().getIntExtra("Type", 0);


        initView();

        handler=new SqlHandler(this);
        datalist=handler.getQueryResult("SL_TBLMIAOSHU", "");
        String neirong="";
        for(int i=0;i<datalist.size();i++)
        {
            String s=datalist.get(i).get("TYPE");
            if(s.equals(title)){
                neirong=datalist.get(i).get("MIAOSHU");
                content.setText(neirong);
                break;
            }

        }
    }

    private void initView() {

        content = (TextView) this.findViewById(R.id.Layer_content);

    }
}