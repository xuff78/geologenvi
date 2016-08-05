package com.sichuan.geologenvi.frg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.geodisaster.SelectorAct;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.adapter.RainAdapter;
import com.sichuan.geologenvi.bean.CateInfo;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.GlbsNet;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.utils.ViewUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SearchListFrg extends BaseFragment{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private HttpHandler httpHandler;
    private ArrayList<CateInfo> cates=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_list, container, false);

        
        recyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        initHandler();
        httpHandler.getFiles();

        return view;
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag= (int) view.getTag();
            ArrayList<PopupInfoItem> bookes=cates.get(tag).getInfos();
            if(bookes.size()>0) {
                LinearLayout subLayout = (LinearLayout) ((View) (view.getParent())).findViewById(R.id.subLayout);
                if (subLayout.getChildCount() == 0) {
                    subLayout.addView(ViewUtil.getGrayLine(getActivity()));
                    for(PopupInfoItem book:bookes) {
                        addTextView(subLayout, book.getName(), 40, book);
                    }
                } else {
                    subLayout.removeAllViews();
                }
            }
        }

        private void addTextView(LinearLayout subLayout, String title, int height, PopupInfoItem book) {
            TextView txt = ViewUtil.getLinearTextView(title, height, getActivity());
            txt.setTag(book);
            txt.setOnClickListener(subListener);
            txt.setBackgroundResource(R.drawable.press_trans_to_gray);
            subLayout.addView(txt);
        }
    };

    View.OnClickListener subListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PopupInfoItem book= (PopupInfoItem) view.getTag();
            File file=FileUtil.getFile(book.getName(), getActivity());
            if(file.exists()){

            }else {
                ToastUtils.displayTextShort(getActivity(), "文件不存在，正在为您下载");
            }
        }
    };

    private void initHandler() {
        httpHandler=new HttpHandler(getActivity(), new CallBack(getActivity()){
            @Override
            public void onHTTPException(String method, String jsonMessage) {
                String jsonData= SharedPreferencesUtil.getString(getActivity(), ConstantUtil.BookInfo);
                showResult(jsonData);
            }

            @Override
            public void doSuccess(String method, String jsonData) {
                SharedPreferencesUtil.setString(getActivity(), ConstantUtil.BookInfo, jsonData);
                showResult(jsonData);
            }

            @Override
            public void onFailure(String method, JsonMessage jsonMessage) {
                String jsonData=SharedPreferencesUtil.getString(getActivity(), ConstantUtil.BookInfo);
                showResult(jsonData);
            }

            @Override
            public void oServerException(String method, String jsonMessage) {
                String jsonData=SharedPreferencesUtil.getString(getActivity(), ConstantUtil.BookInfo);
                showResult(jsonData);
            }

            private void showResult(String jsonData){
                if(jsonData.equals(SharedPreferencesUtil.FAILURE_STRING)){
                    DialogUtil.showInfoDailog(getActivity(), "提示", GlbsNet.HTTP_ERROR_MESSAGE);
                }else {
                    cates = JsonUtil.getBookList(jsonData);
                    ArrayList<String> list=new ArrayList<>();
                    for (CateInfo cate:cates){
                        list.add(cate.getCatelog());
                    }
                    recyclerView.setAdapter(new MenuListAdapter(getActivity(), list, listener));

                }
            }
        });
    }
}
