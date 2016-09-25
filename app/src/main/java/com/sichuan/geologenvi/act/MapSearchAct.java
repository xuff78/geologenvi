package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.QueryStr;
import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.frg.SearchListFrg;
import com.sichuan.geologenvi.frg.SearchListFrg2;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/9/25.
 */
public class MapSearchAct extends AppFrameAct {

    private EditText editSearch;
    private ImageView btnSearch;
    private String word="";
    private SearchListFrg flfg=new SearchListFrg();
    // private SearchListFrg frg=new SearchListFrg();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    int type=0;
    private SqlHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_searchlist);

        handler=new SqlHandler(this);
        _setHeaderTitle("搜索");
        initView();
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        btnSearch=(ImageView)findViewById(R.id.store_search_btn);
        btnSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                editSearch.setText("");
                getWords();
            }

        });
        editSearch=(EditText)findViewById(R.id.search_text);
        if(word.length()>0)
            editSearch.setHint(word);
        editSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editSearch.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if(actionId==EditorInfo.IME_ACTION_DONE||actionId==EditorInfo.IME_ACTION_UNSPECIFIED||actionId==EditorInfo.IME_ACTION_SEARCH){
                    if(!editSearch.getText().toString().trim().equals(""))
                    {
                        getWords();
                    }else
                        ToastUtils.displayTextShort(MapSearchAct.this, "请填写搜索关键字");
                }
                return true;
            }
        });
    }

    private void getWords(){
        ArrayList<String> list = new ArrayList<>();
        String keyword=editSearch.getText().toString().trim();
        ArrayList<Map<String, String>> datalist0=handler.getQueryResult(QueryStr.yinhuandian, "SL_ZHAA01A", " where ZHAA01A020 like '%" + keyword + "%'");
        datalist.addAll(datalist0);
        for (Map<String, String> info : datalist0) {
            list.add(info.get("ZHAA01A020"));
        }

        ArrayList<Map<String, String>> datalist1=handler.getQueryResult("SL_TBLJING", " where QUYU like '%" + keyword + "%'");
        datalist.addAll(datalist1);
        for (Map<String, String> info : datalist1) {
            list.add(info.get("QUYU"));
        }

        ArrayList<Map<String, String>> datalist3=handler.getQueryResult("SL_DZYJBH", " where NAME like '%" + keyword + "%'");
        datalist.addAll(datalist3);
        for (Map<String, String> info : datalist3) {
            list.add(info.get("NAME"));
        }

        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));
        ActUtil.closeSoftPan(this);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            Map<String, String> map = datalist.get(tag);
            Intent i = new Intent();
            MapBean mapBean = new MapBean();
            mapBean.setMap(map);
            i.putExtra("InfoMap", mapBean);
            setResult(0x11, i);
            finish();
        }
    };
}
