package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.DownloadInterface;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.AppDownLoadTask;
import com.sichuan.geologenvi.utils.ToastUtils;

import java.io.File;

/**
 * Created by 可爱的蘑菇 on 2016/9/4.
 */
public class UpdateDailog extends Dialog implements DownloadInterface{

    private Activity act;
    private TextView app_update_tv_progress, app_update_tv_file_size, dataAsycn, cancelBtn, contentTxt;
    private HttpHandler httpHandler;
    private ProgressBar app_update_pb_progress;
    private int Status=0;
    public final static int IDLE=0;
    public final static int DataInfo=1;
    public final static int Finish=2;
    private String url, content;
    public final String updateFileName="cddz.apk";

    private AppDownLoadTask downloadAsyncTask;

    public UpdateDailog(Activity act, String url, String content){
        super(act, R.style.dialog);
        this.act=act;
        this.url=url;
        this.content=content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.update_dialog);
        setCancelable(false);
        initView();
    }

    private void initView() {
        contentTxt= (TextView) findViewById(R.id.contentTxt);
        contentTxt.setText(content);
        app_update_tv_progress= (TextView) findViewById(R.id.app_update_tv_progress);
        app_update_tv_file_size= (TextView) findViewById(R.id.app_update_tv_file_size);
        app_update_pb_progress= (ProgressBar) findViewById(R.id.app_update_pb_progress);
        app_update_pb_progress.setMax(100);
        dataAsycn= (TextView) findViewById(R.id.dataAsycn);
        dataAsycn.setOnClickListener(listener);
        cancelBtn= (TextView) findViewById(R.id.okBtn);
        cancelBtn.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.dataAsycn:
                    if(Status==IDLE){
//                        int size = Integer.valueOf(JsonUtil.getString(jsonData, "Size"));
//                        app_update_tv_file_size.setText(size/1000+"KB");
                        downloadAsyncTask=new AppDownLoadTask(act, app_update_tv_progress, app_update_pb_progress, UpdateDailog.this, updateFileName);
                        downloadAsyncTask.execute(url);
                        Status=DataInfo;
                        dataAsycn.setText("下载中");
                        cancelBtn.setText("停止");
                    }else if(Status==DataInfo){
//                        downloadAsyncTask.cancel(true);
                    }else if(Status==Finish){
                        dismiss();
                    }
                    break;
                case R.id.okBtn:
                    if(Status==DataInfo){
                        downloadAsyncTask.cancel(true);
                        ToastUtils.displayTextShort(getContext(), "已停止");
                        cancelBtn.setText("取消");
                        dataAsycn.setText("更新");
                        Status=IDLE;
                    }else
                        dismiss();
                    break;
            }
        }
    };

    @Override
    public void onComplete() {

        dataAsycn.setText("完成");
        cancelBtn.setText("取消");

        File file = new File(act.getExternalFilesDir(null)+"/"+ updateFileName);
        if(file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            act.startActivity(intent);
        }
        Status=Finish;
    }

    @Override
    public void onFailure() {
        dataAsycn.setText("更新");
        cancelBtn.setText("取消");
        Status=IDLE;
    }
}
