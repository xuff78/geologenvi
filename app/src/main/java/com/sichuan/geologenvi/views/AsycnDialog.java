package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sichuan.geologenvi.DataBase.DBManager;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.DownloadInterface;
import com.sichuan.geologenvi.act.SearchAct;
import com.sichuan.geologenvi.act.contact.ActivityAddFriends;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DownloadAsyncTask;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import java.util.Timer;
import java.util.TimerTask;


public class AsycnDialog extends Dialog implements DownloadInterface{

	private View.OnClickListener ok=null;
	private Activity act;
	private TextView app_update_tv_progress, app_update_tv_file_size, dataAsycn, offlineMap, docDownload;
	private HttpHandler httpHandler;
	private ProgressBar app_update_pb_progress;
	private int Status=0;
	public final static int IDLE=0;
	public final static int DataInfo=1;
	public final static int OfflineMap=2;
	public final static int DocDownload=3;
	private Timer mTimer = null;
	private TimerTask mTimerTask = null;
	private int mapOpen=0;
	private void initHandler() {
		httpHandler=new HttpHandler(act, new CallBack(act){

			@Override
			public void doSuccess(String method, String jsonData) {
				int version = Integer.valueOf(JsonUtil.getString(jsonData, "Version"));
				int curVersion = SharedPreferencesUtil.getInt(act, ConstantUtil.Version, 0);
//				if(version>curVersion) {
					String url=JsonUtil.getString(jsonData, "DownloadUrl");
					int size = Integer.valueOf(JsonUtil.getString(jsonData, "Size"));
					app_update_tv_file_size.setText(size/1000+"KB");
					downloadAsyncTask=new DownloadAsyncTask(act, version, app_update_tv_progress, app_update_pb_progress, AsycnDialog.this);
					downloadAsyncTask.execute(url);
					dataAsycn.setText("取消");
					Status=DataInfo;
//				}else
//					ToastUtils.displayTextShort(getContext(), "数据已是最新");
			}
		});
	}
	private DownloadAsyncTask downloadAsyncTask;

	public AsycnDialog(Activity act){
		super(act, R.style.dialog);
		this.act=act;
		initHandler();
	}
	
	public AsycnDialog(Context context, View.OnClickListener listener){
		super(context, R.style.dialog);
		ok=listener;
		initHandler();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.asyc_dialog);
		setCancelable(false);
		initView();
	}

	private void initView() {
		app_update_tv_progress= (TextView) findViewById(R.id.app_update_tv_progress);
		app_update_tv_file_size= (TextView) findViewById(R.id.app_update_tv_file_size);
		app_update_pb_progress= (ProgressBar) findViewById(R.id.app_update_pb_progress);
		app_update_pb_progress.setMax(100);
		dataAsycn= (TextView) findViewById(R.id.dataAsycn);
		dataAsycn.setOnClickListener(listener);
		offlineMap= (TextView) findViewById(R.id.offlineMap);
		offlineMap.setOnClickListener(listener);
		docDownload= (TextView) findViewById(R.id.docDownload);
		docDownload.setOnClickListener(listener);
		findViewById(R.id.okBtn).setOnClickListener(listener);
	}

	View.OnClickListener listener=new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
				case R.id.dataAsycn:
					if(Status==IDLE){
						int curVersion = SharedPreferencesUtil.getInt(getContext(), ConstantUtil.Version, 0);
						httpHandler.checkVersion(0);
					}else if(Status==DataInfo){
						downloadAsyncTask.cancel(true);
					}
					break;
				case R.id.docDownload:
					break;
				case R.id.okBtn:
					if(Status!=IDLE){
						ToastUtils.displayTextShort(act, "请先停止下载");
					}else
						dismiss();
					break;
			}
		}
	};

	@Override
	public void onComplete() {
		switch (Status){
			case DataInfo:
				dataAsycn.setText("同步");
				break;
		}
		Status=IDLE;
	}

	@Override
	public void onFailure() {

	}


}
