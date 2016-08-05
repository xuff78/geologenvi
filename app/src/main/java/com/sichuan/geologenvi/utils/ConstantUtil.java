package com.sichuan.geologenvi.utils;

import android.os.Environment;

public class ConstantUtil {

	/** 经度 */
	public static final String lon="lon";
	/** 纬度 */
	public static final String lat="lat";
	/** 当前城市 */
	public static final String current_city="current_city";
	/** 当前地址 */
	public static final String current_addr="current_addr";
	public static final String rain_info="rain_info";
	public static final String Version="version";
	public static final String map_open="map_open";
	public static final String map_download="map_download";
	public static final String Photo_Path="photo_path";
	public static final String OfflinePath= "/sdcard/tianditu/offlinemap/"; //Environment.getExternalStorageDirectory().getAbsolutePath()+"/TianDiTu/offline/";

	public static final String BookInfo="BookInfo";
	public static final String AreaInfo="AreaInfo";
	public static final String TongliangMap="TongliangMap";

	public static final String Api_Url="http://bitcoin.beewonder.com/api/";

	public static final class Method{
		public static final String TIMERAININFO="TIMERAININFO";
		public static final String ZHDD04B="ZHDD04B";
		public static final String Version="version";
		public static final String CJ_GZJL_KS="CJ_GZJL_KS";
		public static final String CJ_GZJL_DXS="CJ_GZJL_DXS";
		public static final String CJ_GZJL_DZYJ="CJ_GZJL_DZYJ";
		public static final String CJ_GZJL_BXBQ="CJ_GZJL_BXBQ";
		public static final String Files="Files";
		public static final String Upload="Upload";
		public static final String CJ_DZZHD_XCKP="CJ_DZZHD_XCKP";
		public static final String CJ_GCZL_XCKP="CJ_GCZL_XCKP";
		public static final String CJ_BXCS_XCKP="CJ_BXCS_XCKP";
	}


	public static final String[] Mine={"KSMC", "SFHJPG", "CKMJ", "SZXZQMC", "DZHJ_GK", "HFZL_GK", "DJQ", "XJQ", "BWQ",
			"BQZ", "KCFS", "CK_GUID"};

}
