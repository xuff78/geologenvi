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
	public void userLogin(String name, String password) {
		HashMap<String, String> params=new HashMap<>();
		params.put("name", name);
		params.put("password", password);
		request(ConstantUtil.method_Login, params, true);
	}

	public void getSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_SiteList, params, true);
	}

	public void getSiteDetail(String hsname, String rsname) {
		HashMap<String, String> params=new HashMap<>();
		params.put("hsname", hsname);
		params.put("rsname", rsname);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_SiteDetail, params, true);
	}

	public void getSiteChart(String hsname, String rsname, String beginDate, String endDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("hsname", hsname);
		params.put("rsname", rsname);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		request(ConstantUtil.method_SiteChart, params, true);
	}


	public void getRainSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_RainSiteList, params, true);
	}

	public void getRainSiteDetail(String hsname, String rsname) {
		HashMap<String, String> params=new HashMap<>();
		params.put("rfname", hsname);
		params.put("rsname", rsname);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_RainSiteDetail, params, true);
	}

	public void getRainSiteChart(String hsname, String rsname, String beginDate, String endDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("rfname", hsname);
		params.put("rsname", rsname);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		request(ConstantUtil.method_RainSiteChart, params, true);
	}


	public void getGateDamSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_GateDamSiteList, params, true);
	}

	public void getGateDamDetail(String hsname, String rsname) {
		HashMap<String, String> params=new HashMap<>();
		params.put("dname", hsname);
		params.put("rsname", rsname);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_GateDamDetail, params, true);
	}

	public void getGateDamChart(String hsname, String rsname, String beginDate, String endDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("dname", hsname);
		params.put("rsname", rsname);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		request(ConstantUtil.method_GateDamChart, params, true);
	}



	public void getKuajieSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_KuajieSiteList, params, true);
	}

	public void getZidong() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_zidong, params, true);
	}

	public void yinshuiyuan() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_Yinshuiyuan, params, true);
	}

	public void getYingjiJigouList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_YingjiJigouList, params, true);
	}

	public void getYingjiQiyeList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_YingjiQiyeList, params, true);
	}

	public void getYingjiWuziList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_YingjiWuziList, params, true);
	}

	public void getShuizhiInfo(String materialType, String queryDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("materialType", materialType);
		params.put("queryDate", queryDate);
		request(ConstantUtil.method_Shuizhi, params, true);
	}

	public void getKuajieDetail(String hsname, String rsname) {
		HashMap<String, String> params=new HashMap<>();
		params.put("river", hsname);
		params.put("provices", rsname);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_KuajieSiteDetail, params, true);
	}

	public void getKuajieChart(String hsname, String rsname, String beginDate, String endDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("river", hsname);
		params.put("provices", rsname);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		request(ConstantUtil.method_KuajieSiteChart, params, true);
	}



	public void getGuokongSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_GuokongSiteList, params, true);
	}

	public void getPDFlist(String typeCode, int page) {
		HashMap<String, String> params=new HashMap<>();
		params.put("typeCode", typeCode);
		params.put("page", page+"");
		params.put("rows", "20");
		request(ConstantUtil.method_PDF, params, false);
	}

	public void getPDFType() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_PDFTypes, params, true);
	}

	public void getPDFlistEv(String cateName, int page) {
		HashMap<String, String> params=new HashMap<>();
		params.put("cateName", cateName);
		params.put("page", page+"");
		params.put("rows", "20");
		request(ConstantUtil.method_PDFEv, params, true);
	}

	public void getPDFTypeEv() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_PDFCateEv, params, true);
	}

	public void getGuokongSiteDetail(String hsname, String rsname) {
		HashMap<String, String> params=new HashMap<>();
		params.put("pname", hsname);
		params.put("city", rsname);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_GuokongSiteDetail, params, true);
	}

	public void getGuokongSiteChart(String hsname, String rsname, String beginDate, String endDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("pname", hsname);
		params.put("city", rsname);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		request(ConstantUtil.method_GuokongSiteChart, params, true);
	}

	public void getYibanSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_YibanSiteList, params, true);
	}

	public void getYibanSiteDetail(String pscode) {
		HashMap<String, String> params=new HashMap<>();
		params.put("pscode", pscode);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_YibanSiteDetail, params, true);
	}

	public void getWushuizdSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_WushuizdSiteList, params, true);
	}

	public void getWushuizdSiteDetail(String pscode) {
		HashMap<String, String> params=new HashMap<>();
		params.put("pscode", pscode);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_WushuizdSiteDetail, params, true);
	}

	public void getGongyeSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_GongyeSiteList, params, true);
	}

	public void getGongyeSiteList(String fourCoords) {
		HashMap<String, String> params=new HashMap<>();
		params.put("fourCoords", fourCoords);
		request(ConstantUtil.method_GongyeSiteList, params, true);
	}

	public void getGongyeSiteDetail(String runit) {
		HashMap<String, String> params=new HashMap<>();
		params.put("runit", runit);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_GongyeSiteDetail, params, true);
	}

	public void getWushuipcSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_WushuipcSiteList, params, true);
	}

	public void getWushuipcDetail(String name) {
		HashMap<String, String> params=new HashMap<>();
		params.put("name", name);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_WushuipcSiteDetail, params, true);
	}

	public void getChuqinSiteList() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_ChuqinSiteList, params, true);
	}

	public void getChuqinSiteList(String fourCoords) {
		HashMap<String, String> params=new HashMap<>();
		params.put("fourCoords", fourCoords);
		request(ConstantUtil.method_ChuqinSiteList, params, true);
	}

	public void getChuqinSiteDetail(String farm) {
		HashMap<String, String> params=new HashMap<>();
		params.put("farm", farm);
		params.put("date", ActUtil.getCurrentDate());
		request(ConstantUtil.method_ChuqinSiteDetail, params, true);
	}

	public void getTongliangList(String beginDate, String endDate) {
		HashMap<String, String> params=new HashMap<>();
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		request(ConstantUtil.method_TongliangList, params, true);
	}

	public void getTongliangMap() {
		HashMap<String, String> params=new HashMap<>();
		request(ConstantUtil.method_TongliangMap, params, true);
	}

	public void getTongliangChart(String beginDate, String endDate, String keyPointId) {
		HashMap<String, String> params=new HashMap<>();
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		params.put("keyPointId", keyPointId);
		request(ConstantUtil.method_TongliangChart, params, true);
	}

	public void getShengdm(String id) {
		HashMap<String, String> params=new HashMap<>();
		params.put("id", id);
		request(ConstantUtil.method_TongliangShengdm, params, true);
	}

	public void getKuajiedm(String id) {
		HashMap<String, String> params=new HashMap<>();
		params.put("id", id);
		request(ConstantUtil.method_TongliangKuajiedm, params, true);
	}

	public void getWarningList(String beginDate, String endDate, String queryType) {
		HashMap<String, String> params=new HashMap<>();
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		params.put("queryType", queryType);
		request(ConstantUtil.method_warningList, params, true);
	}

	public void getWarningChart(String beginDate, String endDate, String keyPointId, String queryType) {
		HashMap<String, String> params=new HashMap<>();
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		params.put("keyPointId", keyPointId);
		params.put("queryType", queryType);
		request(ConstantUtil.method_warningChart, params, true);
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
}
