package com.skyline.terraexplorer.models;

import java.io.InputStream;
import java.net.URL;

import android.os.Handler;

public class AppLinks {
	private static String tutorialLink;
	private static String searchLinkPostfix;
	private static String defaultFlyFile;
	public static String getTutorialUrl()
	{
		if(tutorialLink == null)
		{
			tutorialLink = resolveAppUrl("tutorial");
		}
		if(tutorialLink == null)
			tutorialLink = "http://www.youtube.com/watch?v=hUSSchj53Z4";
		return tutorialLink;
	}
	public static String getSearchUrlPostfix()
	{
		if(searchLinkPostfix == null)
		{
			searchLinkPostfix = resolveAppUrl("search_postfix");
		}
		if(searchLinkPostfix == null)
			searchLinkPostfix = "AddressSearch/AddressSearch.ashx";
		return searchLinkPostfix;
	}

	public static String getDefaultFlyFile()
	{
//	    if(defaultFlyFile == null)
//	    {
//	    	defaultFlyFile = resolveAppUrl("default_fly");
//	    }
	    if(defaultFlyFile == null)
	    	defaultFlyFile =  "http://218.246.202.44:808/DefaultMobile.FLY";
	    return defaultFlyFile;
	}

	public static String getDefaultSearchServer()
	{
		return "http://www.skylineglobe.com";
	}
	
	private static String resolveAppUrl(String id) {
		
		try {
			URL url = new URL("http://www.skylineglobe.com/mobilelinks.ashx?linkid=" + id);
			InputStream in = url.openStream();
	        return new java.util.Scanner(in).useDelimiter("\\A").next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void initializeAsync()
	{
		new Handler().post(new Runnable() {			
			@Override
			public void run() {
				AppLinks.getDefaultFlyFile();
				AppLinks.getTutorialUrl();				
				AppLinks.getSearchUrlPostfix();
			}
		});
	}
}
