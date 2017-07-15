package com.skyline.terraexplorer.models;

import java.util.UUID;

import android.content.IntentFilter;

import com.skyline.teapi.IPosition;

public class FavoriteItem {
	
	public static final String FAVORITE_ID = "com.skyline.terraexplorer.FAVORITE_ID";
	public static final String FAVORITE_ICON = "com.skyline.terraexplorer.FAVORITE_ICON";
	public static final IntentFilter FavoriteChanged = new IntentFilter("com.skyline.terraexplorer.FavoriteChanged");



	public String id;
	public String name;
	public String desc;
	public boolean showOn3D;
	public int icon;
	public IPosition position;
	
	public FavoriteItem()
	{
		id = UUID.randomUUID().toString();
		name = "";
		icon = 0;
		desc = "";
	}
}
