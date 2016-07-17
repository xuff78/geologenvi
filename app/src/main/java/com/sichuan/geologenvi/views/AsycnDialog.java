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
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.TErrorCode;
import com.tianditu.android.maps.TOfflineMapInfo;
import com.tianditu.android.maps.TOfflineMapManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class AsycnDialog extends Dialog implements DownloadInterface, TOfflineMapManager.OnGetMapsResult {

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
	private TOfflineMapManager offlineMapMng= null;
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
		mapOpen=SharedPreferencesUtil.getInt(act, ConstantUtil.map_open, 0);
		if(mapOpen==1)
			inttMapDownload();
	}

	private void inttMapDownload() {
		offlineMapMng = new TOfflineMapManager(this);
		FileUtil.isExist(ConstantUtil.OfflinePath);
		offlineMapMng.setMapPath(ConstantUtil.OfflinePath); //act.getExternalFilesDir(null).getPath());
		try {
			offlineMapMng.getMapList();
		}catch (Exception e){
			ToastUtils.displayTextShort(act, "连接失败，请稍后再试");
		}
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
				case R.id.offlineMap:
					if(mapOpen==1)
					if(Status==IDLE) {
						new Thread() {
							@Override
							public void run() {
								super.run();
								offlineMapMng.startDownload("成都", MapView.TMapType.MAP_TYPE_IMG);
								Status = OfflineMap;
							}
						}.start();
						offlineMap.setText("停止");
						mTimer = new Timer();
						mTimerTask = new TimerTask() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								mUpdateHandler.sendEmptyMessage(1);
							}
						};
						mTimer.schedule(mTimerTask, 1000, 300);
					}else{
						mTimerTask.cancel();
						offlineMapMng.pauseDownload();
						offlineMap.setText("下载");
						Status=IDLE;
					}
					else
						ToastUtils.displayTextShort(act, "请先启动一次地图");
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
	public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> maps, int error) {
		if(error != TErrorCode.OK)
			return;
		int size = maps.size();
		String str = "";
		for(int i = 0;i < size;i++){
			TOfflineMapManager.MapAdminSet adminSet = maps.get(i);
			str += adminSet.getName();
			ArrayList<TOfflineMapManager.City> citys = adminSet.getCitys();
			str += "����:";
			for(int k=0; k<citys.size(); k++)
			{
				str += citys.get(k).getName() + " ";
			}
			str += "\n";
		}
		LogUtil.i("City", str);
	}

	Handler mUpdateHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what)
			{
				case 1:
					TOfflineMapInfo info = offlineMapMng.getDownloadInfo("成都", MapView.TMapType.MAP_TYPE_IMG);
					if(info != null) {
						int progress= (int) ((long)info.getDownloadedSize() * 100 / info.getSize());
						LogUtil.i("download", "d: "+info.getDownloadedSize()+"  t:  "+info.getSize()+ "   progress:  "+progress);
						app_update_pb_progress.setProgress(progress);
						app_update_tv_progress.setText(info.getDownloadedSize()/1000+"KB");
						app_update_tv_file_size.setText(info.getSize()/1000+"KB");
						if(info.getState() == TOfflineMapManager.OFFLINEMAP_DOWNLOAD_FINISHED)
						{
							SharedPreferencesUtil.setInt(act, ConstantUtil.map_download, 1);
							ToastUtils.displayTextShort(act, "离线地图下载完成");
							offlineMap.setText("下载");
							mTimer.cancel();
							Status=IDLE;
						}
					}else{
						int mapDownload=SharedPreferencesUtil.getInt(act, ConstantUtil.map_download, 0);
						if(mapDownload==1)
							ToastUtils.displayTextShort(act, "已下载");
						else
							ToastUtils.displayTextShort(act, "无法连接下载");
						offlineMap.setText("下载");
						mTimer.cancel();
						Status=IDLE;
					}
//					if(info == null || info.getState() != TOfflineMapManager.OFFLINEMAP_DOWNLOADING)
//					{
//						return;
//					}

					break;

			}

		}

	};
}
