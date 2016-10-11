package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.QueryStr;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.geodisaster.AreaSelectorAct;
import com.sichuan.geologenvi.act.geodisaster.QuXianSelectorAct;
import com.sichuan.geologenvi.act.geodisaster.YinhuandianDetail;
import com.sichuan.geologenvi.act.geodisaster.ZhilidianweiDetail;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MineListAct   extends AppFrameAct implements SectionIndexer {
    TextView txtcount;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    private SqlHandler handler;
    private String tableName="";
    private String title="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        title=getIntent().getStringExtra("Title");
        _setHeaderTitle(title);
        initView();


        handler=new SqlHandler(this);
        tableName=getIntent().getStringExtra("TableName");
        requestInfo();

        if(tableName.equals("SL_DZYJBH")||tableName.equals("SL_SOILSTASION")) {
            txtcount.setVisibility(View.GONE);

        }
        else {
            _setRightHomeText("筛选", new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(MineListAct.this, QuXianSelectorAct.class);
                    startActivityForResult(intent2, 0x11);
                }
            });
        }
    }

    private void requestInfo() {
        if(tableName.equals("SL_KS_XX")) {   //矿山的
            String whereStr=" where IS_GUOJIA is not'是' and IS_LVSE is not'是' ";
            if(title.equals("国家专项资金恢复治理项目"))
                whereStr=" where IS_GUOJIA is '是'";
            else  if(title.equals("绿色矿山"))
                whereStr=" where IS_LVSE is '是'";
            datalist = handler.getQueryResult(
                    "SL_KS_XX", whereStr);

        }
        else if(tableName.equals("SL_DZYJBH")) //地址遗迹
            datalist=handler.getQueryResult(tableName, "");
        else if(tableName.equals("SL_TBLJING")) //地下水
            datalist=handler.getQueryResult(tableName, "");
        else if(tableName.equals("SL_SOILSTASION"))
            datalist=handler.getQueryResult(tableName, "");
        ArrayList<String> list = new ArrayList<>();
        String title="";
        if(tableName.equals("SL_KS_XX"))
            title="KS_NAME";
        else if(tableName.equals("SL_DZYJBH"))
            title="NAME";
        else if(tableName.equals("SL_TBLJING"))
            title="QUYU";
        else if(tableName.equals("SL_SOILSTASION"))
            title="PIANQU";
        txtcount.setText("共：   "+ datalist.size()+"    条记录");
        for (Map<String, String> info : datalist) {
            list.add(info.get(title));

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
            Intent i=getIntent();
            if(tableName.equals("SL_KS_XX"))
                i.setClass(MineListAct.this, ItemDetailAct.class);
            else if(tableName.equals("SL_DZYJBH")) {
                i.setClass(MineListAct.this, ItemDetailAct.class);
            }
            else if(tableName.equals("SL_TBLJING"))
                i.setClass(MineListAct.this, ItemDetailAct.class);
            else if(tableName.equals("SL_SOILSTASION"))
                i.setClass(MineListAct.this, ItemDetailAct.class);
            MapBean mapBean=new MapBean();
            int tag=(int)view.getTag();
            mapBean.setMap(datalist.get(tag));
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            startActivity(i);
        }
    };

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

        if(tableName.equals("SL_KS_XX"))   //矿山的
            datalist=handler.getQueryResult(tableName, " where KS_SSQX='"+intent.getStringExtra("Area")+"'");
        else if(tableName.equals("SL_DZYJBH")) //地址遗迹
            datalist=handler.getQueryResult(tableName, "");
        else if(tableName.equals("SL_TBLJING")) //地下水
            datalist=handler.getQueryResult(tableName, " where QUXIAN='"+intent.getStringExtra("Area")+"'");
        else if(tableName.equals("SL_SOILSTASION")) //地下水
            datalist=handler.getQueryResult(tableName, "");
        ArrayList<String> list = new ArrayList<>();
        String title="";
        if(tableName.equals("SL_KS_XX"))
            title="KS_NAME";
        else if(tableName.equals("SL_DZYJBH"))
            title="NAME";
        else if(tableName.equals("SL_TBLJING"))
            title="QUYU";
        else if(tableName.equals("SL_SOILSTATION"))
            title="PIANQU";
        txtcount.setText("共：   "+ datalist.size()+"    条记录");
        for (Map<String, String> info : datalist) {
            list.add(info.get(title));

        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));


    }
}
