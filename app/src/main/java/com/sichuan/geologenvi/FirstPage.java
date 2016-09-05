package com.sichuan.geologenvi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.sichuan.geologenvi.DataBase.DBManager;
import com.sichuan.geologenvi.act.statistics.ChatRain;
import com.sichuan.geologenvi.adapter.RainAdapter;
import com.sichuan.geologenvi.bean.JsonMessage;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.GlbsNet;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.SharedPreferencesUtil;
import com.sichuan.geologenvi.views.UpdateDailog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/3/9.
 */
public class FirstPage extends AppCompatActivity {

    private HttpHandler handler;
    private final int BUFFER_SIZE = 400000;
    private int ready=0;
    private UpdateDailog updateDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_frist_page);

        initHandler();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainPage();
            }
        }, 2000);

        new Thread(){
            @Override
            public void run() {
                copyDBtoSDCard(getExternalFilesDir(null)+"/"+DBManager.DB_NAME);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toMainPage();
                    }
                });
            }
        }.start();
        handler.checkVersion();
    }



    private void copyDBtoSDCard(String dbfile) {
        try {
            if (!(new File(dbfile).exists())) {
//                FileUtil.isExist(DBManager.DB_PATH);

                File file = new File(dbfile);
                InputStream is = getResources().getAssets().open("db.db3");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toMainPage(){
        ready++;
        if(ready==2) {
            Intent i = new Intent(FirstPage.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(0, R.anim.zoom_out);
        }
    }

    private void initHandler() {
        handler=new HttpHandler(this, new CallBack(this){
            @Override
            public void onHTTPException(String method, String jsonMessage) {
                toMainPage();
            }

            @Override
            public void doSuccess(String method, String jsonData) {
                updateDailog=new UpdateDailog(FirstPage.this, "", "");
                updateDailog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        toMainPage();
                    }
                });
                updateDailog.show();
            }

            @Override
            public void onFailure(String method, JsonMessage jsonMessage) {
                toMainPage();
            }

            @Override
            public void oServerException(String method, String jsonMessage) {
                toMainPage();
            }

        });
    }
}
