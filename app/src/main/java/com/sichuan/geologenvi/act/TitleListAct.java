package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.act.report.BanqianbirangEditMain;
import com.sichuan.geologenvi.act.report.BanqianbirangList;
import com.sichuan.geologenvi.act.statistics.ChatAct;
import com.sichuan.geologenvi.act.statistics.FormAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ViewUtil;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class TitleListAct  extends AppFrameAct {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<String> list=new ArrayList<>();
    private String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getStringExtra("Type");
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if(type.equals("Disaster")) {
            list.add("灾害隐患点基础数据查询");
            list.add("重点监测隐患点位基础数据查询");
            list.add("专业监测点位基础数据查询");
            list.add("工程治理点位基础数据查询");
            list.add("隐患点避险场所基础数据查询");
            list.add("重点位避险场所基础数据查询");
            list.add("避险搬迁基础数据查询");

//            list.add("灾害隐患点基础数据查询");
//            list.add("市级监测点位基础数据查询");
//            list.add("隐患点避险场所基础数据查询");
//            list.add("重要点位避险场所基础数据查询");
//            list.add("专业监测点位基础数据查询");
//            list.add("治理点位基础数据查询");
//            list.add("销号点位基础数据查询");
        }else if(type.equals("Report")) {
            list.add("隐患点巡查");
            list.add("治理点位巡查");
            list.add("销号点位复核");
            list.add("搬迁避让数据采集");
            list.add("新增隐患点采集");
            list.add("原有隐患点更新");
        }else if(type.equals("Statistics")){
            list.add("隐患点统计图");
            list.add("隐患点统计表格");
            list.add("地下水统计");
            list.add("避险搬迁统计");
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag=(int)view.getTag();
            if(type.equals("Disaster")) {
                switch (tag) {
                    case 0:
                        LinearLayout subLayout= (LinearLayout) ((View)(view.getParent())).findViewById(R.id.subLayout);
                        if(subLayout.getChildCount()==0) {
                            subLayout.addView(ViewUtil.getGrayLine(TitleListAct.this));
                            addTextView(subLayout, "主管部门", 40, 7);
                            addTextView(subLayout, "其他部门", 20, 8);
                            addTextView(subLayout, "销号点位", 40, 9);
                        }else{
                            subLayout.removeAllViews();
                        }
                        break;
                    default:
                        Intent i = new Intent(TitleListAct.this, SelectorAct.class);
                        i.putExtra("Type", tag);
                        i.putExtra("Title", list.get(tag));
                        startActivity(i);
                        break;
                }
            }else if(type.equals("Report")) {
                if(tag==3){
                    Intent i=new Intent(TitleListAct.this, BanqianbirangList.class);
                    i.putExtra("Title", list.get(tag));
                    startActivity(i);
                }
            }else if(type.equals("Statistics")) {
                if(tag==0) {
                    Intent i = new Intent(TitleListAct.this, ChatAct.class);
                    i.putExtra("Type", tag);
                    i.putExtra("Title", list.get(tag));
                    startActivity(i);
                }else{
                    if(tag!=2) {
                        Intent i = new Intent(TitleListAct.this, FormAct.class);
                        i.putExtra("Type", tag);
                        i.putExtra("Title", list.get(tag));
                        startActivity(i);
                    }
                }
            }
        }

        private void addTextView(LinearLayout subLayout, String title, int height, int type) {
            TextView txt = ViewUtil.getLinearTextView(title, height, TitleListAct.this);
            txt.setTag(type);
            txt.setOnClickListener(subListener);
            txt.setBackgroundResource(R.drawable.press_trans_to_gray);
            subLayout.addView(txt);
        }
    };

    View.OnClickListener subListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (type.equals("Disaster")) {
                int tag = (int) view.getTag();
                Intent i = new Intent(TitleListAct.this, SelectorAct.class);
                i.putExtra("Type", tag);
                i.putExtra("Title", ((TextView)view).getText().toString());
                startActivity(i);
            }else if (type.equals("Statistics")) {
//                int tag = (int) view.getTag();
//                Intent i = new Intent(TitleListAct.this, ChatAct.class);
//                i.putExtra("Type", tag);
//                i.putExtra("Title", ((TextView)view).getText().toString());
//                startActivity(i);
            }
        }
    };
}
