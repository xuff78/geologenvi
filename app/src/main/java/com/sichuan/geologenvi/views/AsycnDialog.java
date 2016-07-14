package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.DBManager;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.SearchAct;
import com.sichuan.geologenvi.act.contact.ActivityAddFriends;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DownloadAsyncTask;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;


public class AsycnDialog extends Dialog {

	private View.OnClickListener ok=null;
	private Activity act;
	private TextView app_update_tv_progress, app_update_tv_file_size;
	private HttpHandler httpHandler;
	private ProgressBar app_update_pb_progress;
	private void initHandler() {
		httpHandler=new HttpHandler(act, new CallBack(act){

			@Override
			public void doSuccess(String method, String jsonData) {
				int version = Integer.valueOf(JsonUtil.getString(jsonData, "Version"));
				int curVersion = SharedPreferencesUtil.getInt(act, ConstantUtil.Version, 0);
				if(version>curVersion) {
					String url=JsonUtil.getString(jsonData, "DownloadUrl");
					int size = Integer.valueOf(JsonUtil.getString(jsonData, "Size"));
					app_update_tv_file_size.setText(size/1000+"KB");
					new DownloadAsyncTask(act, version, app_update_tv_progress, app_update_pb_progress).execute(url);
				}else
					ToastUtils.displayTextShort(getContext(), "数据已是最新");
			}
		});
	}

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
		initView();
	}

	private void initView() {
		app_update_tv_progress= (TextView) findViewById(R.id.app_update_tv_progress);
		app_update_tv_file_size= (TextView) findViewById(R.id.app_update_tv_file_size);
		app_update_pb_progress= (ProgressBar) findViewById(R.id.app_update_pb_progress);
		app_update_pb_progress.setMax(100);
		findViewById(R.id.dataAsycn).setOnClickListener(listener);
		findViewById(R.id.offlineMap).setOnClickListener(listener);
		findViewById(R.id.docDownload).setOnClickListener(listener);
	}

	View.OnClickListener listener=new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
				case R.id.dataAsycn:
					int curVersion = SharedPreferencesUtil.getInt(getContext(), ConstantUtil.Version, 0);
					httpHandler.checkVersion(0);
					break;
				case R.id.offlineMap:
					break;
				case R.id.docDownload:
					break;
			}
		}
	};
}
