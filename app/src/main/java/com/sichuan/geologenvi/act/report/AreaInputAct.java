package com.sichuan.geologenvi.act.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.AreaInfos;
import com.sichuan.geologenvi.utils.ToastUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 可爱的蘑菇 on 2016/7/7.
 */
public class AreaInputAct  extends AppFrameAct {

    int type=0;
    TextView selectArea;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private SqlHandler handler;
    private ArrayList<AreaInfo> areas=new ArrayList<>();
    private String selectId="";
    private AreaInfos selectedAreaInfo=new AreaInfos();
//    private LinkedList<String> selectedDistrict=new LinkedList<>(); //记录地区的文字
    private LinkedList<AreaInfo> selectedDistrict=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle("选择行政区");
        _setLeftBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backList();
            }
        });
        initView();
        handler=new SqlHandler(this);
        requestArea();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        findViewById(R.id.selectHintLayout).setVisibility(View.VISIBLE);
        selectArea= (TextView) findViewById(R.id.selectArea);
        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedDistrict.size()>0) {
                    Intent intent = new Intent();
                    selectedAreaInfo.setInfos(selectedDistrict);
                    intent.putExtra(AreaInfos.Name, selectedAreaInfo);
                    setResult(0x10, intent);
                    finish();
                }else
                    ToastUtils.displayTextShort(AreaInputAct.this, "至少选择一个区域");
            }
        });
    }

    private void requestArea(){
        ArrayList<AreaInfo> temps=handler.getAreaInfo(selectId);
        if(temps.size()>0) {
            areas=temps;
            ArrayList<String> list = new ArrayList<>();
            for (AreaInfo info : areas) {
                list.add(info.getName());
            }
            recyclerView.setAdapter(new MenuListAdapter(AreaInputAct.this, list, listener));
        }else {
            if(selectedDistrict.size()>0) {
                Intent intent = new Intent();
                selectedAreaInfo.setInfos(selectedDistrict);
                intent.putExtra(AreaInfos.Name, selectedAreaInfo);
                setResult(0x10, intent);
                finish();
            }
        }
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag=(int)view.getTag();
            AreaInfo temp=areas.get(tag);
            selectId = temp.getId();

            selectedDistrict.add(temp);
            String selectTxt="选择区域:  ";
            for(AreaInfo info:selectedDistrict){
                selectTxt=selectTxt+info.getName()+"  ";
            }
            selectArea.setText(selectTxt);
            requestArea();
        }
    };

    private void backList(){
        if(selectId.length()!=0){
            selectedDistrict.removeLast();
            if(selectedDistrict.size()>0){
                selectId=selectedDistrict.getLast().getId();
                String selectTxt="选择区域:  ";
                for(AreaInfo info:selectedDistrict){
                    selectTxt=selectTxt+info.getName()+"  ";
                }
                selectArea.setText(selectTxt);
                requestArea();
            }else{
                selectId="";
                selectArea.setText("请选择区域");
                requestArea();
            }
        }else
            finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            backList();
        }

        return true;
    }
}
