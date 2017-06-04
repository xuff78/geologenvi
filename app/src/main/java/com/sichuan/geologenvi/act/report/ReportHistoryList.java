package com.sichuan.geologenvi.act.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.YujingAct;
import com.sichuan.geologenvi.adapter.MenuList2Adapter;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.MenuListItem2;
import com.sichuan.geologenvi.bean.ReportBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/23.
 */
public class ReportHistoryList extends AppFrameAct {
    TextView txtcount;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<MenuListItem2> datalist=new ArrayList<>();
    ArrayList<ReportBean> infos=new ArrayList<>();
    int type=0;
    private HttpHandler httpHandler;
    private SqlHandler handler;
    private int page=0;
    private MenuList2Adapter adapter;
    int lastVisibleItem=0;
    private String id="";
    boolean onloading=false, hasMore=true;



    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(ReportHistoryList.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                onloading=false;
                ArrayList<ReportBean> beans= JsonUtil.getMSRecords(jsonData);
                page=Integer.valueOf(JsonUtil.getString(jsonData, "pageIndex"));
                hasMore=JsonUtil.getJsonBoolean(jsonData, "hasMore");
                if(beans.size()>0) {
                    if (page == 1) {
                        infos=beans;
                        datalist.clear();
                        for(ReportBean bean:beans){
                            MenuListItem2 item=new MenuListItem2();
                            item.setTitle(bean.getMS());
                            item.setSubTxtL(bean.getDATETIME());
                            datalist.add(item);
                        }
                        adapter = new MenuList2Adapter(ReportHistoryList.this, datalist, listener);
                        recyclerView.setAdapter(adapter);
                        if (datalist.size() > 5)
                            recyclerView.setOnScrollListener(scrollListener);
                    } else {
                        infos.addAll(beans);
                        for(ReportBean bean:beans){
                            MenuListItem2 item=new MenuListItem2();
                            item.setTitle(bean.getMS());
                            item.setSubTxtL(bean.getDATETIME());
                            datalist.add(item);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(String method, JsonMessage jsonMessage) {
                super.onFailure(method, jsonMessage);
                onloading=false;
            }

            @Override
            public void onHTTPException(String method, String jsonMessage) {
                super.onHTTPException(method, jsonMessage);
                onloading=false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        id=getIntent().getStringExtra("Id");
        type=getIntent().getIntExtra("Type", 0);
        _setRightHomeText("添加记录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=getIntent();
                i.setClass(ReportHistoryList.this, ReportCreateAct.class);
                startActivityForResult(i, 0x10);
            }
        });
        initHandler();
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        onloading=true;
        switch (type) {
            case 4:
                httpHandler.getCJ_GZJL_KS(page+1, id);
                break;
            case 5:
                httpHandler.getCJ_GZJL_DXS(page+1, id);
                break;
            case 6:
                httpHandler.getCJ_GZJL_DZYJ(page+1, id);
                break;
            case 7:
                httpHandler.getCJ_GZJL_BXBQ(page+1, id);
                break;
        }
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

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=getIntent();
            int tag=(int)view.getTag();
            switch (getIntent().getIntExtra("Type", 0)){

                case 4:
                case 5:
                case 6:
                case 7:
                    i.setClass(ReportHistoryList.this, HistoryDetailAct.class);
                    i.putExtra(ReportBean.Name, infos.get(tag));
                    break;
            }
            startActivityForResult(i, 0x20);
        }
    };

    RecyclerView.OnScrollListener scrollListener=new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == adapter.getItemCount()) {
                if(hasMore)
                    adapter.setRefresh(true);
                else
                    adapter.setRefresh(false);
                if(!onloading&&hasMore){
                    onloading=true;
                    switch (type) {
                        case 4:
                            httpHandler.getCJ_GZJL_KS(page+1, id);
                            break;
                        case 5:
                            httpHandler.getCJ_GZJL_DXS(page+1, id);
                            break;
                        case 6:
                            httpHandler.getCJ_GZJL_DZYJ(page+1, id);
                            break;
                        case 7:
                            httpHandler.getCJ_GZJL_BXBQ(page+1, id);
                            break;
                    }
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        }

    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x11){
            page=0;
            onloading=true;
            switch (type) {
                case 4:
                    httpHandler.getCJ_GZJL_KS(page + 1, id);
                    break;
                case 5:
                    httpHandler.getCJ_GZJL_DXS(page + 1, id);
                    break;
                case 6:
                    httpHandler.getCJ_GZJL_DZYJ(page + 1, id);
                    break;
                case 7:
                    httpHandler.getCJ_GZJL_BXBQ(page + 1, id);
                    break;
            }
        }
    }
}
