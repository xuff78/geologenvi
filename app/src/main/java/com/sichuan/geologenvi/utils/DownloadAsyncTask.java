package com.sichuan.geologenvi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.DBManager;
import com.sichuan.geologenvi.act.DownloadInterface;

/**
 * 异步下载数据
 */
public class DownloadAsyncTask extends AsyncTask<String, Integer, String> {

	private Activity act;
	private TextView onLoadingSize;
	private boolean cancel=false;
	private int Version=-1, fileSize=0;
	private ProgressBar progressBar;
	private DownloadInterface callback;

	public DownloadAsyncTask(Activity act, int Version, TextView onLoadingSize, ProgressBar progressBar, DownloadInterface callback) {
		this.Version=Version;
		this.act=act;
		this.onLoadingSize=onLoadingSize;
		this.progressBar=progressBar;
		this.callback=callback;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... args) {

		String url=args[0];
		String result = "";
		String fileName = "";
		InputStream is = null;
		FileOutputStream fos = null;
		try {

			url = URLEncoder.encode(url,"utf-8").replaceAll("\\+", "%20");
			url = url.replaceAll("%3A", ":").replaceAll("%2F", "/");
			HttpURLConnection urlConn = getURLConnection(url);
			is = urlConn.getInputStream();
			fileSize = urlConn.getContentLength(); // 文件大小
			String header = urlConn.getHeaderField("Content-Disposition");
			if (header == null || header.equals(""))
				fileName = url.substring(url.lastIndexOf("/") + 1);
			else {
				String[] info = header.split("filename=");
				fileName = info[1];
			}
			File file = new File(act.getExternalFilesDir(null)+"/"+ DBManager.TMP_DB_NAME);
			if (file != null && !file.exists())
				file.createNewFile();
			fos = new FileOutputStream(file);

			int count = 0, numread = 0;
			byte buf[] = new byte[1024];

			while (!cancel && (numread = is.read(buf)) != -1) {
				count += numread;
//				int progressCount = (int) (((float) count / length) * 100);
				publishProgress(count);
				fos.write(buf, 0, numread);
			}
			fos.flush();
			result = "success";
		} catch (Exception e) {
			result = "fail";
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
				result = "fail";
			}
		}
		return result;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		int downloadSize=values[0];
		onLoadingSize.setText(downloadSize/1000+"KB");
		int progressCount = (int) (((float) downloadSize / fileSize) * 100);
		progressBar.setProgress(progressCount);
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		callback.onComplete();
		if(result.equals("success")) {
			File file = new File(act.getExternalFilesDir(null)+"/"+ DBManager.DB_NAME);
			file.delete();
			File filetmp = new File(act.getExternalFilesDir(null)+"/"+ DBManager.TMP_DB_NAME);
			filetmp.renameTo(file);
			ToastUtils.displayTextShort(act, "同步完成");
			progressBar.setProgress(100);
			SharedPreferencesUtil.setInt(act, ConstantUtil.Version, Version);
		}
	}

	@Override
	protected void onCancelled() {
		cancel=true;
		callback.onComplete();
		ToastUtils.displayTextShort(act, "已取消");
		super.onCancelled();
	}
	
	private HttpURLConnection getURLConnection(String url) throws Exception {
		String proxyHost = android.net.Proxy.getDefaultHost();
		if (proxyHost != null) {
			java.net.Proxy p = new java.net.Proxy(java.net.Proxy.Type.HTTP,
					new InetSocketAddress(android.net.Proxy.getDefaultHost(),
							android.net.Proxy.getDefaultPort()));

			return (HttpURLConnection) new URL(url).openConnection(p);

		} else {
			return (HttpURLConnection) new URL(url).openConnection();
		}
	}
	
}
