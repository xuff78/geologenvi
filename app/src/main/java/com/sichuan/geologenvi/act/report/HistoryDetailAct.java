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
import com.sichuan.geologenvi.bean.ReportBean;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.views.Photo9Layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/23.
 */
public class HistoryDetailAct extends AppFrameAct {

    TextView titleTxt, timeDate, descTxt, videoFileTxt;
    Photo9Layout photo9Layout;
    int type=0;
    private ReportBean bean;
    private String videoPath="";
    private SqlHandler handler;
    private HttpHandler httpHandler;
    private boolean video=false;

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(HistoryDetailAct.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                ToastUtils.displayTextShort(HistoryDetailAct.this, "删除成功");
                setResult(0x11);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_detail_layout);


        final String id=getIntent().getStringExtra("Id");
        bean= (ReportBean) getIntent().getSerializableExtra(ReportBean.Name);
        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        _setRightHomeText("删除", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case 3:
                        httpHandler.delCJ_GZJL_KS(bean.getID());
                        break;
                    case 4:
                        httpHandler.delCJ_GZJL_DXS(bean.getID());
                        break;
                    case 5:
                        httpHandler.delCJ_GZJL_DZYJ(bean.getID());
                        break;
                    case 6:
                        httpHandler.delCJ_GZJL_BXBQ(bean.getID());
                        break;
                }
            }
        });
        initHandler();
        initView();
        handler=new SqlHandler(this);
    }

    private void initView() {
        findViewById(R.id.videoLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActUtil.playVideoByUrl(HistoryDetailAct.this, videoPath);
            }
        });
        titleTxt= (TextView) findViewById(R.id.titleTxt);
        timeDate= (TextView) findViewById(R.id.timeDate);
        timeDate.setText(bean.getDATETIME());
        descTxt= (TextView) findViewById(R.id.descTxt);
        descTxt.setText(bean.getMS());
        videoFileTxt= (TextView) findViewById(R.id.videoFileTxt);
        photo9Layout= (Photo9Layout) findViewById(R.id.photoLayout);
        String[] paths=bean.getPATH().split("\\|");
        String[] types=bean.getTYPE().split("\\|");
        final ArrayList<String> imgUrls=new ArrayList<>();
        for (int i=0;i<paths.length;i++){
            if(types[i].equals("0")){
                imgUrls.add(paths[i]);
            }else{
                videoPath=paths[i];
            }
        }

        photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(video) {
                    Intent intent = new Intent(HistoryDetailAct.this, ViewPagerExampleActivity.class);
                    intent.putExtra("Images", imgUrls);
                    intent.putExtra("pos", position);
                    startActivity(intent);
                }
            }
        });
        if(videoPath!=null&&videoPath.length()>0) {
            videoFileTxt.setText("点击播放视频");
            video=true;
        }else
            videoFileTxt.setText("暂无视频");
        photo9Layout.setImageUrl(ScreenUtil.getScreenWidth(this)- ImageUtil.dip2px(this, 40), imgUrls);
    }
}
