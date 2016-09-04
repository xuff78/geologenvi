package com.sichuan.geologenvi.act.geodisaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.QueryStr;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.ItemDetailAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.Contact;
import com.sichuan.geologenvi.bean.GeohazardBean;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.utils.ViewUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/27.
 */
public class TitleResultListAct  extends AppFrameAct  implements SectionIndexer {
    TextView txtcount;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    Map<String, ArrayList<Map<String, String>>>  stations=new HashMap<>();
    int type=0;


    private String sqlStr="";

    private SqlHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle(getIntent().getStringExtra("Title"));





        initView();
        handler=new SqlHandler(this);
        requestInfo();


        _setRightHomeText("筛选", new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(TitleResultListAct.this, SelectorAct.class);
                intent2.putExtra("Type", type);
                intent2.putExtra("Title",getIntent().getStringExtra("Title"));
                startActivityForResult(intent2, 0x11);
            }
        });

    }

    private void requestInfo() {
        switch (type) {
            case 1:
            case 7:
            case 8:
            case 9:
                datalist=handler.getGeohazardInfo(QueryStr.yinhuandian, type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterName"),
                        getIntent().getStringExtra("disasterTypeCode"),
                        getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"),
                        getIntent().getStringExtra("avoidCode"), getIntent().getStringExtra("yearCode"));
                break;
            case 3:
                datalist=handler.getGeohazardInfo("", type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterName"),
                        getIntent().getStringExtra("disasterTypeCode"),
                        getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"),
                        getIntent().getStringExtra("avoidCode"), getIntent().getStringExtra("yearCode"));
                break;
            case 2:
                datalist=handler.getGeohazardInfo(QueryStr.zhuanyeshebei, type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterName"),
                        getIntent().getStringExtra("disasterTypeCode"),
                        getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"),
                        getIntent().getStringExtra("avoidCode"), getIntent().getStringExtra("yearCode"));
                break;
            case 4:
            case 5:
                datalist=handler.getGeohazardInfo(QueryStr.yhdbixiancs, type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterName"),
                        getIntent().getStringExtra("disasterTypeCode"),
                        getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"),
                        getIntent().getStringExtra("avoidCode"), getIntent().getStringExtra("yearCode"));
                break;
            case 6:
                datalist=handler.getGeohazardInfo(QueryStr.bxbq, type, getIntent().getStringExtra("Name"), getIntent().getStringExtra("disasterName"),
                        getIntent().getStringExtra("disasterTypeCode"),
                        getIntent().getStringExtra("disasterSizeCode"), getIntent().getStringExtra("areaCode"),
                        getIntent().getStringExtra("avoidCode"), getIntent().getStringExtra("yearCode"));
                break;
        }
        ArrayList<String> list = new ArrayList<>();

        txtcount.setText("共：   "+ datalist.size()+"    条记录");
        stations.clear();
        for (Map<String, String> info : datalist) {
            switch (type) {
                case 1:
                case 7:
                case 8:
                case 9:
                    list.add(info.get("ZHAA01A020"));
                    break;
                case 3:
                    list.add(info.get("ZHCA01A020"));
                    break;
                case 2:
                    String key=info.get("DISASTERNAME");
                    if(stations.containsKey(key)) {
                        ArrayList<Map<String, String>> data=stations.get(key);
                        data.add(info);
                    }else{
                        list.add(key);
                        ArrayList<Map<String, String>> data=new ArrayList<>();
                        data.add(info);
                        stations.put(key, data);
                    }
                    txtcount.setText("共：   "+ stations.size()+"    条记录");
                    break;
                case 4:
                case 5:
                    list.add(info.get("ZHDD02A020")+"\n"+info.get("ZHDD02A310"));
                    break;
                case 6:
                    list.add(info.get("ZHDD04B020"));
                    break;
            }
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
    }

    private void initView() {
        txtcount=(TextView)findViewById(R.id.count);
        txtcount.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String tableName="";
            Intent i=getIntent();
            int tag=(int)view.getTag();
            Map<String, String> map=datalist.get(tag);
            switch (getIntent().getIntExtra("Type", 0)){
                case 1:
                case 9:
                    i.setClass(TitleResultListAct.this, ItemDetailAct.class);
                    tableName="SL_ZHAA01A";
                    break;
                case 7:
                case 8:
                    i.setClass(TitleResultListAct.this, YinhuandianDetail.class);
                    tableName="SL_ZHAA01A";
                    break;
                case 6:
                    map.put("ZHDD04B040", handler.getDistrictName(map.get("ZHDD04B040")));
                    map.put("ZHDD04B060", handler.getDistrictName(map.get("ZHDD04B060")));
                    i.setClass(TitleResultListAct.this, BixianbanqianDetail.class);
                    tableName="SL_ZHDD04B";
                    break;
                case 4:
                case 5:
                    i.setClass(TitleResultListAct.this, ItemDetailAct.class);
                    tableName="SL_ZHDD02A";
                    break;
                case 2:
                    String title=((TextView)view).getText().toString();
                    ArrayList<Map<String, String>> points=stations.get(title);
                    if(points.size()>0){
                        LinearLayout subLayout= (LinearLayout) ((View)(view.getParent())).findViewById(R.id.subLayout);
                        if(subLayout.getChildCount()==0) {
                            subLayout.addView(ViewUtil.getGrayLine(TitleResultListAct.this));
                            for (Map<String, String> point:points) {
                                addTextView(subLayout, point.get("METERTYPE")+"  "+point.get("METERID"), 40, point);
                            }
                        }else{
                            subLayout.removeAllViews();
                        }
                    }
//                    else if(points.size()==1){
//                        i.setClass(TitleResultListAct.this, ItemDetailAct.class);
//                        tableName = "SL_STATIONMETERS";
//                        MapBean mapBean=new MapBean();
//                        mapBean.setMap(points.get(0));
//                        i.putExtra("InfoMap", mapBean);
//                        i.putExtra("TableName", tableName);
//                        startActivity(i);
//                    }
                    return;
                case 3:
                    i.setClass(TitleResultListAct.this, ZhilidianweiDetail.class);
                    tableName="SL_ZHCA01A";
                    break;
            }

            String[] telNames = null;
            if(type==1||type==7||type==8||type==9) {
                telNames= new String[]{"ZHAA01A510", "ZHAA01A720", "ZHAA01A735", "ZHAA01A750", "ZHAA01A751", "ZHAA01A752", "ZHAA01A753", "ZHAA01A754", "ZHAA01A758"};
            }else if(type==3){
                telNames= new String[]{"ZHCA01A220", "ZHCA01A240", "ZHCA01A250"};
            }else if(type==4||type==5){
                telNames= new String[]{"ZHDD02A220"};
            }else if(type==2){
                telNames= new String[]{"PHONENUMBER"};
            }else if(type==6){

            }
            if(telNames!=null)
                for (int j=0;j<telNames.length;j++){
                    String key=telNames[j];
                    String value=map.get(key);
                    if(value!=null&&value.length()>0)
                        map.put(key, "tel"+value);
                }
            MapBean mapBean=new MapBean();
            mapBean.setMap(map);
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            if(getIntent().hasExtra("TakeDataBack")){  //给数据采集选择灾害点
                setResult(0x21, i);
                finish();
            }else
                startActivity(i);
        }
    };

    private void addTextView(LinearLayout subLayout, final String title, int height, final Map<String, String> point) {
        TextView txt = ViewUtil.getLinearTextView(title, height, TitleResultListAct.this);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=getIntent();
                i.setClass(TitleResultListAct.this, ItemDetailAct.class);
                MapBean mapBean=new MapBean();
                mapBean.setMap(point);
                i.putExtra("InfoMap", mapBean);
                i.putExtra("TableName", "SL_STATIONMETERS");
                startActivity(i);
            }
        });
        subLayout.addView(txt);
    }

    @Override
    public Object[] getSections() {
        return null;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return 0;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {

        return -1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (type) {
            case 1:
            case 7:
            case 8:
            case 9:
                datalist=handler.getGeohazardInfo(QueryStr.yinhuandian, type, intent.getStringExtra("Name"), intent.getStringExtra("disasterName"),
                        intent.getStringExtra("disasterTypeCode"),
                        intent.getStringExtra("disasterSizeCode"),intent.getStringExtra("areaCode"),
                        intent.getStringExtra("avoidCode"), intent.getStringExtra("yearCode"));
                break;
            case 3:
                datalist=handler.getGeohazardInfo("", type, intent.getStringExtra("Name"), intent.getStringExtra("disasterName"),
                        intent.getStringExtra("disasterTypeCode"),
                        intent.getStringExtra("disasterSizeCode"),intent.getStringExtra("areaCode"),
                        intent.getStringExtra("avoidCode"), intent.getStringExtra("yearCode"));
                break;
            case 2:
                datalist=handler.getGeohazardInfo(QueryStr.zhuanyeshebei, type, intent.getStringExtra("Name"), intent.getStringExtra("disasterName"),
                        intent.getStringExtra("disasterTypeCode"),
                        intent.getStringExtra("disasterSizeCode"),intent.getStringExtra("areaCode"),
                        intent.getStringExtra("avoidCode"), intent.getStringExtra("yearCode"));
                break;
            case 4:
            case 5:
                datalist=handler.getGeohazardInfo(QueryStr.yhdbixiancs, type, intent.getStringExtra("Name"), intent.getStringExtra("disasterName"),
                        intent.getStringExtra("disasterTypeCode"),
                        intent.getStringExtra("disasterSizeCode"),intent.getStringExtra("areaCode"),
                        intent.getStringExtra("avoidCode"), intent.getStringExtra("yearCode"));
                break;
            case 6:
                datalist=handler.getGeohazardInfo(QueryStr.bxbq, type,intent.getStringExtra("Name"), intent.getStringExtra("disasterName"),
                        intent.getStringExtra("disasterTypeCode"),
                        intent.getStringExtra("disasterSizeCode"),intent.getStringExtra("areaCode"),
                        intent.getStringExtra("avoidCode"), intent.getStringExtra("yearCode"));
                break;
        }
        ArrayList<String> list = new ArrayList<>();

        txtcount.setText("共：   "+ datalist.size()+"    条记录");
        stations.clear();
        for (Map<String, String> info : datalist) {
            switch (type) {
                case 1:
                case 7:
                case 8:
                case 9:
                    list.add(info.get("ZHAA01A020"));
                    break;
                case 3:
                    list.add(info.get("ZHCA01A020"));
                    break;
                case 2:
                    String key=info.get("DISASTERNAME");

                    if(stations.containsKey(key)) {
                        ArrayList<Map<String, String>> data=stations.get(key);
                        data.add(info);
                    }else{
                        list.add(key);
                        ArrayList<Map<String, String>> data=new ArrayList<>();
                        data.add(info);
                        stations.put(key, data);
                    }
                    txtcount.setText("共：   "+ stations.size()+"    条记录");
                    break;
                case 4:
                case 5:
                    list.add(info.get("ZHDD02A020")+"\n"+info.get("ZHDD02A310"));
                    break;
                case 6:
                    list.add(info.get("ZHDD04B020"));
                    break;
            }
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
        }

}
