package com.sichuan.geologenvi.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtil {

	public static int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}

	public static int getScreenWidth(Activity act){
		int ScreenWidth= SharedPreferencesUtil.getInt(act, "ScreenWidth", -1);
		if(ScreenWidth==-1){
			getScreen(act);
			ScreenWidth=SharedPreferencesUtil.getInt(act, "ScreenWidth", -1);
		}
		return ScreenWidth;
	}
	
	public static int getScreenHeight(Activity act){
		int ScreenHeight=SharedPreferencesUtil.getInt(act, "ScreenHeight", -1);
		if(ScreenHeight==-1){
			getScreen(act);
			ScreenHeight=SharedPreferencesUtil.getInt(act, "ScreenHeight", -1);
		}
		return ScreenHeight;
	}
	
	public static void getScreen(Activity act){
		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		SharedPreferencesUtil.setInt(act, "ScreenWidth", dm.widthPixels);
		SharedPreferencesUtil.setInt(act, "ScreenHeight", dm.heightPixels);
	}

}
