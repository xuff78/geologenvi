package com.sichuan.geologenvi.act.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.CDDHSqlHandler;
import com.sichuan.geologenvi.DataBase.QueryStr;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.ItemDetailAct;
import com.sichuan.geologenvi.act.YujingAct;
import com.sichuan.geologenvi.act.geodisaster.BixianbanqianDetail;
import com.sichuan.geologenvi.act.geodisaster.YinhuandianDetail;
import com.sichuan.geologenvi.act.geodisaster.ZhilidianweiDetail;
import com.sichuan.geologenvi.adapter.EditItemAdapter;
import com.sichuan.geologenvi.adapter.EditItemAdapter1;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.utils.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/28.
 */
public class ReportEditListAct extends AppFrameAct {
    TextView txtcount;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    int type=0;
    String fbDate="";
    private SqlHandler handler;
    private CDDHSqlHandler cddhHandler;
    private HttpHandler httpHandler;
    private String name="";
    private String id="";
    private MapBean mapBean=new MapBean();
    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(ReportEditListAct.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                datalist.addAll(JsonUtil.getDataMap(jsonData));
                if(method.equals(ConstantUtil.Method.CJ_DZZHD_XCKP)){
                    recyclerView.setAdapter(new EditItemAdapter1(ReportEditListAct.this, datalist, new String[]{"ZDMC","JCRQ"}, listener));
                }else if(method.equals(ConstantUtil.Method.CJ_GCZL_XCKP)){
                    recyclerView.setAdapter(new EditItemAdapter1(ReportEditListAct.this, datalist, new String[]{"GCZL_NAME","TBRQ"}, listener));
                }else if(method.equals(ConstantUtil.Method.CJ_BXCS_XCKP)){
                    recyclerView.setAdapter(new EditItemAdapter1(ReportEditListAct.this, datalist, new String[]{"BXCS_NAME","JCDATE"}, listener));
                }else if(method.equals(ConstantUtil.Method.Yujing)){
//                    LogUtil.i("SQL", "result jsonData---->:  "+jsonData);
                    String yujingtime="";
                    for(int i=0;i<datalist.size();i++){
//                        LogUtil.i("SQL", "result FBDATE---->:  "+datalist.get(i).get("FBDATE"));
                        if(yujingtime.compareTo(datalist.get(i).get("FBDATE"))<0){
                            yujingtime=datalist.get(i).get("FBDATE");
                        }
                    }


                    cddhHandler.execSQL("update yujing set yujingtime='"+yujingtime+"' where id=1");
                    recyclerView.setAdapter(new EditItemAdapter1(ReportEditListAct.this, datalist, new String[]{"TITLE","FBDATE"}, listener));
                }else if(method.equals(ConstantUtil.Method.CJ_BXBQ_XCKP)){//避险搬迁现场检查
                    recyclerView.setAdapter(new EditItemAdapter1(ReportEditListAct.this, datalist, new String[]{"YHDMC","HZXM","JCRQ"}, listener));
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        name=getIntent().getStringExtra("Name");
        id=getIntent().getStringExtra("ID");
        mapBean= (MapBean) getIntent().getSerializableExtra("InfoMap");
        if(name==null)
            name="";
        if(id==null)
            id="";
        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        fbDate=getIntent().getStringExtra("YJDate");
        initView();
        initHandler();
        handler=new SqlHandler(this);
        cddhHandler=new CDDHSqlHandler(this);
        requestInfo();
        if(type!=30) {
            _setRightHomeText("添加", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    i.putExtra("Map", mapBean);
                    switch (type) {
                        case 20:
                            i.setClass(ReportEditListAct.this, CJ_DZZHD_XCKP_edit.class);
                            break;
                        case 21:
                            i.setClass(ReportEditListAct.this, BanqianbirangEditMain.class);
                            break;
                        case 22:
                            i.setClass(ReportEditListAct.this, CJ_BXCS_XCKP_edit.class);
                            break;
                        case 23:
                            i.setClass(ReportEditListAct.this, CJ_BXBQ_XCKP_edit.class);
                            break;
                    }
                    startActivityForResult(i, 0x82);
                }
            });
        }
//        _setRightHomeText("筛选", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent();
//                switch (type) {
//                    case 10:
//                        i.setClass(ReportEditListAct.this, CJ_DZZHD_XCKP_edit.class);
//                        break;
//                    case 11:
//                        i.setClass(ReportEditListAct.this, BanqianbirangEditMain.class);
//                        break;
//                    case 12:
//                        i.setClass(ReportEditListAct.this, CJ_BXCS_XCKP_edit.class);
//                        break;
//                }
//                startActivityForResult(i, 0x82);
//            }
//        });
    }

    private void initView() {
        txtcount=(TextView)findViewById(R.id.count);
        txtcount.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void requestInfo() {
        switch (type) {
            case 20:
                httpHandler.getCJ_DZZHD_XCKP(1, name,id);
                break;
            case 21:
                httpHandler.getCJ_GCZL_XCKP(1, name,id);
                break;
            case 22:
                httpHandler.getCJ_BXCS_XCKP(1, name,id);
                break;
            case 23:
                httpHandler.getCJ_BXBQ_XCKP(1,5000,id);
                break;
            case 30:
                if(fbDate!=null&&fbDate!=""){

                    httpHandler.getYujing(fbDate);
                }
                else{
                    httpHandler.getYujing();
                }

                break;
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tableName="";
            Intent i=getIntent();
            int tag=(int)view.getTag();
            Map<String, String> map=datalist.get(tag);
            switch (getIntent().getIntExtra("Type", 0)) {
                case 20:
                    i.setClass(ReportEditListAct.this, CJ_DZZHD_XCKP_edit.class);
                    break;
                case 21:
                    i.setClass(ReportEditListAct.this, BanqianbirangEditMain.class);
                    break;
                case 22:
                    i.setClass(ReportEditListAct.this, CJ_BXCS_XCKP_edit.class);
                    break;
                case 23:
                    i.setClass(ReportEditListAct.this, CJ_BXBQ_XCKP_edit.class);
                    break;
                case 30://预警
                    i.setClass(ReportEditListAct.this, YujingAct.class);

            }
            MapBean mapBean=new MapBean();
            mapBean.setMap(map);
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            startActivityForResult(i, 0x81);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x99) {
            datalist.clear();
            requestInfo();
        }
    }
}
