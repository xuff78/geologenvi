package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by tanqq on 2016/8/30.
 */
public class DxsAct   extends AppFrameAct {

    private TextView content;

    private int type=0;
    private SqlHandler handler;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dxs);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        type=getIntent().getIntExtra("Type", 0);


        initView();

        handler=new SqlHandler(this);
        datalist=handler.getQueryResult("SL_TBLDXS", "");
        String neirong="";
         switch (type) {
           case 0:
                neirong=datalist.get(0).get("jbgk");
                content.setText(neirong);
                break;
            case 1:
                neirong=datalist.get(0).get("jcqk");
                content.setText(neirong);
                break;
        }
    }

    private void initView() {

        content = (TextView) this.findViewById(R.id.Layer_content);

    }
}