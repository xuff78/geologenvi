package com.sichuan.geologenvi;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;

import com.skyline.terraexplorer.TEApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;


/**
 * Created by Administrator on 2015/11/16.
 */
public class GEApplication extends Application {
//    public BDLocationService locationService;

    private Thread.UncaughtExceptionHandler androidDefaultUEH;

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            String path = Environment.getExternalStorageDirectory() + File.separator + "com.skyline.terraexplorer/files";
            File file = new File(path, "errors.txt");
            FileOutputStream stream = null;
            try {
                stream =  new FileOutputStream(file,true);
                stream.write((new Date().toGMTString() + ": ").getBytes());
                ex.printStackTrace(new PrintStream(stream));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally {
                if(stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            //Log.e("TestApplication", "Uncaught exception is: ", ex);
            androidDefaultUEH.uncaughtException(thread, ex);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                // TODO Auto-generated method stub
                if(TEApp.activityContext == activity)
                    TEApp.activityContext = null;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                // TODO Auto-generated method stub
                TEApp.activityContext = activity;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityResumed(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityPaused(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // TODO Auto-generated method stub

            }
        });
    }
}
