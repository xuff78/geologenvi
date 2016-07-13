/**
 *  Author :  hmg25
 *  Description :
 */
package com.sichuan.geologenvi.http;

import android.app.Activity;

import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * hmg25's android Type
 * 
 * @author Administrator
 * 
 */
public class HttpHandler extends Handle {

	private Activity mContext;

	public HttpHandler(Activity mContext, CallBack mCallBack) {
		super(mContext, mCallBack);
		this.mContext = mContext;
	}

	/**
	 * 获取所有列表
	 * 
	 */
	public void getRainInfo() {
		requestGet(ConstantUtil.Method.TIMERAININFO, "", true);
	}

	public void checkVersion(int version) {
		requestGet(ConstantUtil.Method.Version, "?v="+version, true);
	}

	public void addBangqianBaseInfo(String json) {
		requestPost(ConstantUtil.Method.ZHDD04B, json, true);
	}

	protected void request(String method, HashMap<String, String> params, boolean showDialog) {
		String progressInfo = "";
		String url= ConstantUtil.Api_Url+method;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			LogUtil.i("HttpAsyncTask", entry.getKey() + "--->" + entry.getValue());
		}
		LogUtil.i("HttpAsyncTask","url: "+url);
		new HttpAsyncTask(mContext, this, showDialog)
				.execute(url, method, params, progressInfo, false);
	}

	protected void requestPost(String method, String json, boolean showDialog) {
		String progressInfo = "";
		String url= ConstantUtil.Api_Url+method;
		LogUtil.i("HttpAsyncTask","url: "+url+"\n"+json);
		new HttpAsyncTask(mContext, this, showDialog)
				.execute(url, method, json, progressInfo, false);
	}

	protected void requestGet(String method, String param, boolean showDialog) {
		String progressInfo = "";
		String url= ConstantUtil.Api_Url+method+param;
		LogUtil.i("HttpAsyncTask","url: "+url);
		new HttpAsyncTask(mContext, this, showDialog)
				.execute(url, null, null, progressInfo, true);
	}
}
