
package com.sichuan.geologenvi.act;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import com.sichuan.geologenvi.R;
        import com.sichuan.geologenvi.act.report.ViewPagerExampleActivity;
        import com.sichuan.geologenvi.utils.ScreenUtil;
        import com.sichuan.geologenvi.views.Photo9Layout;

        import java.util.ArrayList;


/**
 * Created by tanqq on 2016/8/30.
 */
public class SupportAct   extends AppFrameAct {

//    private TextView content;
    private String title;
    Photo9Layout photo9Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        title=getIntent().getStringExtra("Title");
        _setHeaderTitle(title);


        initView();

        //得到可用的图片
        String url = "http://223.85.242.114:8090/support/support.jpg";

        final ArrayList<String> imgUrls=new ArrayList<>();
        imgUrls.add(url);
//        photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent intent = new Intent(SupportAct.this, ViewPagerExampleActivity.class);
//                intent.putExtra("Images", imgUrls);
//                intent.putExtra("pos", position);
//                startActivity(intent);
//            }
//        });
        int width= ScreenUtil.getScreenWidth(this)*3/2;
        photo9Layout.setImageUrl(width, imgUrls);
    }

    private void initView() {

//        content = (TextView) this.findViewById(R.id.Layer_support);
        photo9Layout= (Photo9Layout) findViewById(R.id.photoLayout);
    }
}