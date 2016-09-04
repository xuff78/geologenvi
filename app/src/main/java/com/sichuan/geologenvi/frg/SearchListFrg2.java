package com.sichuan.geologenvi.frg;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tianditu.android.maps.MapView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SearchListFrg2 extends BaseFragment{

    String type;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private HttpHandler httpHandler;
    private ArrayList<CateInfo> cates=new ArrayList<>();
    ProgressDialog progressDialog;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            String bookName=msg.getData().getString("Name");
            File file=FileUtil.getFile(bookName, getActivity());
            if(file.exists()){
                String type="pdf";
                if(bookName.contains("."))
                    type=bookName.split("\\.")[1];
                FileUtil.openFile(file, getActivity(), "application/"+type);
            }else
                ToastUtils.displayTextShort(getActivity(), "下载失败，请稍后重试");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_list, container, false);

       type = getArguments().getString("Type");



        recyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        initHandler();

        httpHandler.getFiles(type);

        return view;
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             int tag=(int)view.getTag();
            final PopupInfoItem book= cates.get(0).getInfos().get(tag);

            File file=FileUtil.getFile(book.getName(), getActivity());
            if(file.exists()){
                String type="pdf";
                if(book.getName().contains("."))
                    type=book.getName().split("\\.")[1];
                FileUtil.openFile(file, getActivity(), "application/"+type);
            }else {
                progressDialog=ProgressDialog.show(getActivity(), "提示", "下载中请稍后");
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        FileUtil.downloadFile(book.getName(), book.getContent(), getActivity());
                        Message msg=new Message();
                        Bundle b=new Bundle();
                        b.putString("Name", book.getName());
                        msg.setData(b);
                        handler.sendMessage(msg);
                    }
                }.start();
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

                    ArrayList<PopupInfoItem> bookes=cates.get(0).getInfos();
                    if(bookes.size()>0) {
                        for (PopupInfoItem book : bookes) {
                            list.add(book.getName());
                        }
                    }

                    recyclerView.setAdapter(new MenuListAdapter(getActivity(), list, listener));

                }
            }
        });
    }
}
