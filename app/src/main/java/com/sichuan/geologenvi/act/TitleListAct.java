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
import com.sichuan.geologenvi.act.contact.ActivityAddFriends;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.act.geodisaster.TitleResultListAct;
import com.sichuan.geologenvi.act.report.BanqianbirangEditMain;
import com.sichuan.geologenvi.act.report.BanqianbirangList;
import com.sichuan.geologenvi.act.report.ReportEditListAct;
import com.sichuan.geologenvi.act.report.ReportTitleList;
import com.sichuan.geologenvi.act.statistics.ChatAct;
import com.sichuan.geologenvi.act.statistics.FormAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ViewUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class TitleListAct  extends AppFrameAct {
    TextView txtcount;
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

        txtcount=(TextView)findViewById(R.id.count);
        txtcount.setVisibility(View.GONE);

        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if(type.equals("Disaster")) {
                list.add("地质灾害隐患点位");
                list.add("重点监测隐患点位");
                list.add("专业监测点位");
                list.add("工程治理点位");
                list.add("隐患点避险场所");
                list.add("重点避险场所");
                list.add("避险搬迁");

//            list.add("灾害隐患点基础数据查询");
//            list.add("市级监测点位基础数据查询");
//            list.add("隐患点避险场所基础数据查询");
//            list.add("重要点位避险场所基础数据查询");
//            list.add("专业监测点位基础数据查询");
//            list.add("治理点位基础数据查询");
//            list.add("销号点位基础数据查询");
        }else if(type.equals("Report")) {
            list.add("地质灾害防治工作检查");
            list.add("重大地质灾害防治工程项目现场检查");
            list.add("应急避险场所检查");
            list.add("矿山工作记录");
            list.add("地下水工作记录");
            list.add("地质遗迹工作记录");
            list.add("避险搬迁工作记录");
        }else if(type.equals("Statistics")){
            list.add("隐患点统计图");
            list.add("重点点位信息统计图");
            list.add("隐患点统计表格");
            list.add("地下水统计");
            list.add("避险搬迁统计");
            list.add("工程治理点位统计");
            list.add("避险场所信息统计");
            list.add("专业监测信息统计");
        }else if(type.equals("Contact")){

            list.add("四川省地质灾害防治工作责任单位通讯录");
            list.add("成都市地质灾害防治工作责任单位通讯录");
            list.add("区市县地质灾害防治工作通讯录");
            list.add("成都市国土资源局通讯录");
            list.add("成都市地质环境监测站通讯录");
            list.add("地质灾害防治片区分工通讯录");
            //list.add("地环站站长通讯录");
            //list.add("成都市专职监测人员通讯录");
        }else if(type.equals("Jing")){//地下水
            list.add("地下水基本概况");
            list.add("地下水监测情况");
            list.add("地下水监测点位");
        }else if(type.equals("kuangshan")){//地下水
            list.add("国家专项资金恢复治理项目");
            list.add("绿色矿山");
            list.add("一般矿山");
        }
        else if(type.equals("soil")){//水土地质
            list.add("土壤环境调查");
            list.add("土壤环境监测");
            list.add("土壤治理与修复");
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            if (type.equals("Disaster")) {
                switch (tag) {
                    case 0:
                        LinearLayout subLayout = (LinearLayout) ((View) (view.getParent())).findViewById(R.id.subLayout);
                        if (subLayout.getChildCount() == 0) {
                            subLayout.addView(ViewUtil.getGrayLine(TitleListAct.this));
                            addTextView(subLayout, "国土部门负责点位", 40, 7);
                            addTextView(subLayout, "其他部门负责点位", 20, 8);
                            addTextView(subLayout, "销号点位", 40, 9);
                        } else {
                            subLayout.removeAllViews();
                        }
                        break;
                    default:
                        Intent i = new Intent(TitleListAct.this, TitleResultListAct.class);
                        i.putExtra("Type", tag);
                        i.putExtra("Title", list.get(tag));
                        i.putExtra("Name", "");
                        i.putExtra("disasterName", "");
                        i.putExtra("disasterTypeCode", "");
                        i.putExtra("disasterSizeCode", "");
                        i.putExtra("areaCode", "");
                        i.putExtra("avoidCode", "");
                        i.putExtra("yearCode", "");
                        startActivity(i);
                        break;
                }
            } else if (type.equals("Report")) {
                if (tag > 2) {
                    Intent i = new Intent(TitleListAct.this, ReportTitleList.class);
                    i.putExtra("Title", list.get(tag));
                    i.putExtra("Type", tag);
                    startActivity(i);
                } else {
                    Intent i = new Intent(TitleListAct.this, TitleResultListAct.class);
                    i.putExtra("Type", tag+20);
                    i.putExtra("Title", ((TextView)view).getText().toString());
                    i.putExtra("Name", "");
                    i.putExtra("disasterName", "");
                    i.putExtra("disasterTypeCode", "");
                    i.putExtra("disasterSizeCode", "");
                    i.putExtra("areaCode", "");
                    i.putExtra("avoidCode", "");
                    i.putExtra("yearCode", "");
                    startActivity(i);


//                    Intent i = new Intent(TitleListAct.this, ReportEditListAct.class);
//
//                    i.putExtra("Title", list.get(tag));
//                    i.putExtra("Type", tag + 10);
//
//                    i.putExtra("Name", "");
//                    i.putExtra("disasterName", "");
//                    i.putExtra("disasterTypeCode", "");
//                    i.putExtra("disasterSizeCode", "");
//                    i.putExtra("areaCode", "");
//                    i.putExtra("avoidCode", "");
//                    i.putExtra("yearCode", "");
//
//                    startActivity(i);
                }
            } else if (type.equals("Statistics")) {
                if (tag <= 1) {
                    Intent i = new Intent(TitleListAct.this, ChatAct.class);
                    i.putExtra("Type", tag);
                    i.putExtra("Title", list.get(tag));
                    startActivity(i);
                } else {
                    Intent i = new Intent(TitleListAct.this, FormAct.class);
                    i.putExtra("Type", tag);
                    i.putExtra("Title", list.get(tag));
                    startActivity(i);
                }
            } else if (type.equals("Contact")) {
                Intent i = new Intent(TitleListAct.this, ActivityAddFriends.class);
                i.putExtra("Type", tag);
                i.putExtra("Title", list.get(tag));
                startActivity(i);
            } else if (type.equals("Jing")) {//地下水
                if (tag <= 1) {
                    Intent i = new Intent(TitleListAct.this, DxsAct.class);
                    i.putExtra("Type", tag);
                    i.putExtra("Title", list.get(tag));
                    startActivity(i);
                } else {
                    Intent i = new Intent(TitleListAct.this, MineListAct.class);
                    i.putExtra("Title", list.get(tag));
                    i.putExtra("TableName", "SL_TBLJING");
                    startActivity(i);
                }
            } else if (type.equals("soil")) {//水土地质
                 if(tag!=1){
                    Intent i = new Intent(TitleListAct.this, DxsAct.class);
                    i.putExtra("Type", tag);
                    i.putExtra("Title", list.get(tag));
                    startActivity(i);
                } else {
                    Intent i = new Intent(TitleListAct.this, MineListAct.class);
                    i.putExtra("Title", list.get(tag));
                    i.putExtra("TableName", "SL_SOILSTASION");
                    startActivity(i);
                }
            }else if (type.equals("kuangshan")) {//矿山地质
                Intent i = new Intent(TitleListAct.this, MineListAct.class);
                i.putExtra("Title", list.get(tag));
                i.putExtra("TableName", "SL_KS_XX");
                startActivity(i);
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
                Intent i = new Intent(TitleListAct.this, TitleResultListAct.class);
                i.putExtra("Type", tag);
                i.putExtra("Title", ((TextView)view).getText().toString());
                i.putExtra("Name", "");
                i.putExtra("disasterName", "");
                i.putExtra("disasterTypeCode", "");
                i.putExtra("disasterSizeCode", "");
                i.putExtra("areaCode", "");
                i.putExtra("avoidCode", "");
                i.putExtra("yearCode", "");
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
