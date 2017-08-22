/**
 *  Author :  hmg25
 *  Description :
 */
package com.sichuan.geologenvi.http;

import android.app.Activity;

import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.ArrayList;
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

	public void getRainInfo2() {
		requestGet(ConstantUtil.Method.TIMERAININFO, "/GetRain", true);
	}

	public void getRainInfo2(String qx) {
		requestGet(ConstantUtil.Method.TIMERAININFO, "/GetRain?quxian="+qx, true);
	}

	public void getRainInfo(String name) {
		requestGet(ConstantUtil.Method.TIMERAININFO, "?stName="+name, true);
	}

	public void checkVersion(int version) {
		requestGet(ConstantUtil.Method.Version, "?v="+version, true);
	}



	public void addBangqianBaseInfo(String json) {
		requestPost(ConstantUtil.Method.ZHDD04B, json, true);
	}

	public void addCJ_GZJL_KS(String id, String desc, String type, String path) {
		String jsonContent=ActUtil.addStringContent(new String[]{"KS_ID", "MS","TYPE","PATH"}, new Object[]{id, desc, type, path});
		requestPost(ConstantUtil.Method.CJ_GZJL_KS, jsonContent, true);
	}

	public void getCJ_GZJL_KS(int page, String id) {
		requestGet(ConstantUtil.Method.CJ_GZJL_KS, "?pageIndex="+page+"&ksid="+id, true);
	}

	public void delCJ_GZJL_KS(String id) {
		requestDelete(ConstantUtil.Method.CJ_GZJL_KS, "/"+id, true);
	}

	public void addCJ_GZJL_DXS(String id, String desc, String type, String path) {
		String jsonContent=ActUtil.addStringContent(new String[]{"DXS_ID", "MS","TYPE","PATH"}, new Object[]{id, desc, type, path});
		requestPost(ConstantUtil.Method.CJ_GZJL_DXS, jsonContent, true);
	}

	public void getCJ_GZJL_DXS(int page, String id) {
		requestGet(ConstantUtil.Method.CJ_GZJL_DXS, "?pageIndex="+page+"&dxsid="+id, true);
	}

	public void delCJ_GZJL_DXS(String id) {
		requestDelete(ConstantUtil.Method.CJ_GZJL_DXS, "/"+id, true);
	}

	public void addCJ_GZJL_DZYJ(String id, String desc, String type, String path) {
		String jsonContent=ActUtil.addStringContent(new String[]{"DZYJ_ID", "MS","TYPE","PATH"}, new Object[]{id, desc, type, path});
		requestPost(ConstantUtil.Method.CJ_GZJL_DZYJ, jsonContent, true);
	}

	public void getCJ_GZJL_DZYJ(int page, String id) {
		requestGet(ConstantUtil.Method.CJ_GZJL_DZYJ, "?pageIndex="+page+"&dzyjid="+id, true);
	}

	public void delCJ_GZJL_DZYJ(String id) {
		requestDelete(ConstantUtil.Method.CJ_GZJL_DZYJ, "/"+id, true);
	}

	public void addCJ_GZJL_BXBQ(String id, String desc, String type, String path) {
		String jsonContent=ActUtil.addStringContent(new String[]{"BXBQ_ID", "MS","TYPE","PATH"}, new Object[]{id, desc, type, path});
		requestPost(ConstantUtil.Method.CJ_GZJL_BXBQ, jsonContent, true);
	}

	public void getCJ_GZJL_BXBQ(int page, String id) {
		requestGet(ConstantUtil.Method.CJ_GZJL_BXBQ, "?pageIndex="+page+"&bxbqid="+id, true);
	}

	//查询预警
	public void getYujing() {
		requestGet(ConstantUtil.Method.Yujing, "", true);

	}
	//查询一个时间以后的预警
	public void getYujing(String fbDate){
		requestGet(ConstantUtil.Method.Yujing, "?fbdate="+fbDate, true);
	}

	public void delCJ_GZJL_BXBQ(String id) {
		requestDelete(ConstantUtil.Method.CJ_GZJL_BXBQ, "/"+id, true);
	}

	//获取最新雷达数据列表
	public void getRadUrl(){
		requestGet(ConstantUtil.Method.Rad, "", true);
	}

	//获取最新雷达数据列表
	public void getRadarUrl(){
		requestGet(ConstantUtil.Method.Rad, "/GetRadar", true);
	}
	//获取值班安排数据列表
	public void getZBAP(){
		requestGet(ConstantUtil.Method.ZBAP, "", true);
	}



	public void addCJ_DZZHD_XCKP(String jsonContent) {
		requestPost(ConstantUtil.Method.CJ_DZZHD_XCKP, jsonContent, true);
	}


	public void getCJ_DZZHD_XCKP(int page, String name,String id) {
		String require="";
		if(name.length()>0)
			require=require+"&zdmc="+name;
		if(id.length()>0)
			require=require+"&zdid="+id;
		requestGet(ConstantUtil.Method.CJ_DZZHD_XCKP, "?pageIndex="+page+require, true);
	}

	public void delCJ_DZZHD_XCKP(String id) {
		requestDelete(ConstantUtil.Method.CJ_DZZHD_XCKP, "/"+id, true);
	}

	public void addCJ_GCZL_XCKP(String jsonContent) {
		requestPost(ConstantUtil.Method.CJ_GCZL_XCKP, jsonContent, true);
	}

	public void getCJ_GCZL_XCKP(int page, String name,String id) {
		String require="";
		if(name.length()>0)
			require=require+"&gczlName="+name;
		if(id.length()>0)
			require=require+"&gczlGuid="+id;
		requestGet(ConstantUtil.Method.CJ_GCZL_XCKP, "?pageIndex="+page+require, true);
	}

	public void delCJ_GCZL_XCKP(String id) {
		requestDelete(ConstantUtil.Method.CJ_GCZL_XCKP, "/"+id, true);
	}

	public void addCJ_BXCS_XCKP(String jsonContent) {
		requestPost(ConstantUtil.Method.CJ_BXCS_XCKP, jsonContent, true);
	}

	public void checkVersion() {
		requestGet(ConstantUtil.Method.CheckVersion, "", true);
	}



	public void getAppUrl(String pwd, int version) {
		requestGet(ConstantUtil.Method.getAppUrl, "?pwd="+pwd+"&v="+version, true);
	}

	public void getCJ_BXCS_XCKP(int page, String name,String id) {
		String require="";
		if(name.length()>0)
			require=require+"&bxcsName="+name;
		if(id.length()>0)
			require=require+"&bxcsGuid="+id;
		requestGet(ConstantUtil.Method.CJ_BXCS_XCKP, "?pageIndex="+page+require, true);
	}

	public void delCJ_BXCS_XCKP(String id) {
		requestDelete(ConstantUtil.Method.CJ_BXCS_XCKP, "/"+id, true);
	}

	public void addCJ_BXBQ_XCKP(String jsonContent) {
		requestPost(ConstantUtil.Method.CJ_BXBQ_XCKP, jsonContent, true);
	}

	public void getCJ_BXBQ_XCKP(int page,int pagesize, String id) {
		String require="";
		if(pagesize!=0)
			require=require+"&pageSize="+pagesize;
		if(id.length()>0)
			require=require+"&Guid="+id;

		requestGet(ConstantUtil.Method.CJ_BXBQ_XCKP, "?pageIndex="+page+require, true);
	}
	public void delCJ_BXBQ_XCKP(String id) {
		requestDelete(ConstantUtil.Method.CJ_BXBQ_XCKP, "/"+id, true);
	}

	public void getBXBQ_JBXX(int page,int pagesize,String quxian,String HZXM,String yhdName, String id) {
		String require="";
		if(pagesize!=0)
			require=require+"&pageSize="+pagesize;
		if(id.length()>0)
			require=require+"&Guid="+id;
		if(quxian.length()>0)
			require=require+"&quxian="+quxian;
		if(HZXM.length()>0)
			require=require+"&HZXM="+HZXM;
		if(yhdName.length()>0)
			require=require+"&yhdName="+yhdName;
		requestGet(ConstantUtil.Method.BXBQ_JBXX, "?pageIndex="+page+require, true);
	}


	public void getFiles() {
		requestGet(ConstantUtil.Method.Files, "", true);
	}

	public void getFiles(String type){
		requestGet(ConstantUtil.Method.Files, "?catelog="+type, true);
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
				.execute(url, method, json, progressInfo, 2);
	}

	protected void requestGet(String method, String param, boolean showDialog) {
		String progressInfo = "";
		String url= ConstantUtil.Api_Url+method+param;
		LogUtil.i("HttpAsyncTask","url: "+url);
		new HttpAsyncTask(mContext, this, showDialog)
				.execute(url, method, null, progressInfo, 0);
	}

	protected void requestDelete(String method, String param, boolean showDialog) {
		String progressInfo = "";
		String url= ConstantUtil.Api_Url+method+param;
		LogUtil.i("HttpAsyncTask","url: "+url);
		new HttpAsyncTask(mContext, this, showDialog)
				.execute(url, method, null, progressInfo, 1);
	}
}
