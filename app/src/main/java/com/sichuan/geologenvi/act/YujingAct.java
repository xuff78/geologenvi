package com.sichuan.geologenvi.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.report.ViewPagerExampleActivity;
import com.sichuan.geologenvi.bean.MapBean;
import com.sichuan.geologenvi.bean.ReportBean;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.views.Photo9Layout;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tanqq on 2016/11/5.
 */
public class YujingAct extends AppFrameAct {

    private Map<String, String> infoMap = new HashMap<>();
    private TextView txtTitle;
    private TextView txtContent;
    private TextView txtTimeDate;
    Photo9Layout photo9Layout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yujing);

        _setHeaderTitle("预警");
        infoMap = ((MapBean) getIntent().getSerializableExtra("InfoMap")).getMap();
        initView();
        SetDate();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        txtTitle = (TextView) findViewById(R.id.titleTxt);
        txtContent = (TextView) findViewById(R.id.contentTxt);
        txtTimeDate = (TextView) findViewById(R.id.timeDate);
        photo9Layout= (Photo9Layout) findViewById(R.id.photoLayout);
    }

    private void SetDate() {
        txtTitle.setText(infoMap.get("TITLE"));
        txtContent.setText(infoMap.get("CONTENT"));
        txtTimeDate.setText("发布时间：" + ActUtil.getFormatDate(infoMap.get("FBDATE")));
        String path = infoMap.get("PATH");
        //得到可用的图片
        String url = "http://223.85.242.114:8090/Files/预警/" + path;

        final ArrayList<String> imgUrls=new ArrayList<>();
        imgUrls.add(url);
        photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(YujingAct.this, ViewPagerExampleActivity.class);
                intent.putExtra("Images", imgUrls);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
        int width=ScreenUtil.getScreenWidth(this)*3/2;
        photo9Layout.setImageUrl(width, imgUrls);




//        txtTitle.setText("成都市发布第12号地址灾害期限风险预警");
//        txtContent.setText("  成都市国土资源局，成都市气象局25日16时10分共同发布第12号地址灾害期限风险预警：预计从25日20点00分起的24小时内，蒲江、邛崃、大邑地质灾害风险预警等级为三级，发生地质灾害的风险较高。");
//        txtTimeDate.setText("发布时间：2016-08-25 16:16;00");
//        img.setImageURI(new Uri);
    }



}
