package com.sichuan.geologenvi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/3/9.
 */
public class FirstPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_frist_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(FirstPage.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(0, R.anim.zoom_out);
            }
        }, 2000);
    }
}
