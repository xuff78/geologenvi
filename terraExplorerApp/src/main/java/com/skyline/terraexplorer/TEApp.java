package com.skyline.terraexplorer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

public class TEApp{

    private static Context appContext;
    public static Context activityContext;
	public static Context mainActivityContext;


    
    public void onCreate(){
        

    }

    public static Context getAppContext() {
        return TEApp.appContext;
    }

	public static void setAppContext(Context con) {
        if(appContext==null)
		    appContext=con;
	}
    public static Context getCurrentActivityContext() {
    	if(TEApp.activityContext == null)
    		return mainActivityContext;
        return TEApp.activityContext;
    }
    
    public static void setMainActivityContext(Context ctx)
    {
    	mainActivityContext = ctx;
    }

	public static boolean isDebug() {
		return System.getProperty("DEBUG") != null;
	}
}