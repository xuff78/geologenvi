package com.sichuan.geologenvi;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sichuan.geologenvi.DataBase.DBManager;
import com.sichuan.geologenvi.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/3/9.
 */
public class FirstPage extends AppCompatActivity {

    private final int BUFFER_SIZE = 400000;
    private boolean ready=false;
//    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_frist_page);

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
        if(ready) {
            Intent i = new Intent(FirstPage.this, MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(0, R.anim.zoom_out);
        }else
            ready=true;
    }
}
