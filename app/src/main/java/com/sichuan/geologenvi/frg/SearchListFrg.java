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
    ProgressDialog progressDialog;
    String[] names={"法律法规","国家级文件","四川省级文件","成都市级文件","成都市国土局文件"};
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            String bookName=msg.getData().getString("Name");
            File file=FileUtil.getFile(bookName, getActivity());
            if(file.exists()){
//                String type="pdf";
//                if(bookName.contains("."))
//                    type=bookName.split("\\.")[1];
//                FileUtil.openFile(file, getActivity(), "application/"+type);
                startActivity(FileUtil.openFile(file.getPath()));
            }else
                ToastUtils.displayTextShort(getActivity(), "下载失败，请稍后重试");
        }
    };

    ArrayList<CateInfo> tempCates=new ArrayList<>();

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

    public void searchFile(String keyword){
        cates.clear();
        for (int i=0;i<names.length;i++){
            for (CateInfo cate:tempCates){
                if(cate.getCatelog().equals(names[i])) {
                    if(keyword.length()>0) {
                        CateInfo tmpcate = new CateInfo();
                        tmpcate.setCatelog(cate.getCatelog());
                        ArrayList<PopupInfoItem> infos = tmpcate.getInfos();
                        for (PopupInfoItem info : cate.getInfos()) {
                            if (info.getName().contains(keyword))
                                infos.add(info);
                        }
                        cates.add(tmpcate);
                    }else
                        cates.add(cate);
                }
            }
        }
        ArrayList<String> list=new ArrayList<>();
        for (CateInfo cate:cates){
            if(cate.getInfos().size()>0)
                list.add(cate.getCatelog());
        }
        recyclerView.setAdapter(new MenuListAdapter(getActivity(), list, listener));
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
            final PopupInfoItem book= (PopupInfoItem) view.getTag();
            File file=FileUtil.getFile(book.getName(), getActivity());
            if(file.exists()){
//                String type="pdf";
//                if(book.getName().contains("."))
//                    type=book.getName().split("\\.")[1];
//                FileUtil.openFile(file, getActivity(), "application/"+type);

                startActivity(FileUtil.openFile(file.getPath()));
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
                     tempCates = JsonUtil.getBookList(jsonData);
                    for (int i=0;i<names.length;i++){
                        for (CateInfo cate:tempCates){
                            if(cate.getCatelog().equals(names[i]))
                                cates.add(cate);
                        }
                    }
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
