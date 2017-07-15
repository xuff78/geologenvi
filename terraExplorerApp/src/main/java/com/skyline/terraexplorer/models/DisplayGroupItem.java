package com.skyline.terraexplorer.models;

import java.util.ArrayList;

import com.skyline.terraexplorer.TEApp;

public class DisplayGroupItem {

	public String name;
	public ArrayList<DisplayItem> childItems;
	public DisplayGroupItem(String name)
	{
		this.name = name;
		childItems = new ArrayList<DisplayItem>();
	}
	public DisplayGroupItem(int stringId)
	{
		this(TEApp.getAppContext().getString(stringId));
	}
}
