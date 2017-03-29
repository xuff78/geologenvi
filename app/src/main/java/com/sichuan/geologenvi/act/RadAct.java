package com.sichuan.geologenvi.act;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.sichuan.geologenvi.R;

import java.io.InputStream;
import java.net.URL;


/**
 * Created by tanqq on 2017/3/21.
 */
public class RadAct extends AppFrameAct implements ViewFactory, OnTouchListener {

    /**
     * ImagaSwitcher 的引用
     */
    private ImageSwitcher mImageSwitcher;
    /**
     * 图片id数组
     */
    private String[] uris;
    /**
     * 当前选中的图片id序号
     */
    private int currentPosition;
    /**
     * 按下点的X坐标
     */
    private float downX;
    /**
     * 装载点点的容器
     */
    private LinearLayout linearLayout;
    /**
     * 点点数组
     */
    private ImageView[] tips;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rad);
        _setHeaderTitle(getIntent().getStringExtra("Title"));
        uris = new String[]{"http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329042400000.png",
                "http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329043000000.png",
                "http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329043600000.png",
                "http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170329044200000.png"};
        //实例化ImageSwitcher
        mImageSwitcher  = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        //设置Factory
        mImageSwitcher.setFactory(this);
        //设置OnTouchListener，我们通过Touch事件来切换图片
        mImageSwitcher.setOnTouchListener(this);

        linearLayout = (LinearLayout) findViewById(R.id.viewGroup);

        tips = new ImageView[uris.length];
        for(int i=0; i<uris.length; i++){
            ImageView mImageView = new ImageView(this);
            tips[i] = mImageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            layoutParams.rightMargin = 3;
            layoutParams.leftMargin = 3;

            mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            linearLayout.addView(mImageView, layoutParams);
        }

        currentPosition=uris.length-1;



//        mImageSwitcher.setImageResource(uris[currentPosition]);
//mImageSwitcher.setImageDrawable(Drawable.createFromStream());
        mImageSwitcher.setImageURI(Uri.parse(uris[currentPosition]));
        setImageBackground(currentPosition);


    }


    private void SetDate() {

//        final ArrayList<String> imgUrls=new ArrayList<>();
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325030600000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325031200000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325031800000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325032400000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325033000000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325033600000.png");
//
//
//        int galleryWidth=ScreenUtil.getScreenWidth(this);
//        int galleryHeight= (int) (galleryWidth*(380/640f));
//        TopImgAdapter adapter=new TopImgAdapter(this, imgUrls);
//        viewPager.setAdapter(adapter);
//        viewPager.setLayoutParams(new LinearLayout.LayoutParams(galleryWidth, galleryHeight));


//        String url = "http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325030600000.png";

//        final ArrayList<String> imgUrls=new ArrayList<>();
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325030600000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325031200000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325031800000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325032400000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325033000000.png");
//        imgUrls.add("http://223.85.242.114:8090//Rad/sevp_aoc_rdcp_sldas_ebref_az9280_l88_pi_20170325033600000.png");
//        photo9Layout.setImgCallback(new Photo9Layout.ClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent intent = new Intent(RadAct.this, ViewPagerExampleActivity.class);
//                intent.putExtra("Images", imgUrls);
//                intent.putExtra("pos", position);
//                startActivity(intent);
//            }
//        });
//        int width= ScreenUtil.getScreenWidth(this)*3/2;
//        photo9Layout.setImageUrl(width, imgUrls);

    }


    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public View makeView() {
        final ImageView i = new ImageView(this);
        i.setBackgroundColor(0xff000000);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        return i ;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                //手指按下的X坐标
                downX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:{
                float lastX = event.getX();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    if(currentPosition > 0){
                        //设置动画，这里的动画比较简单，不明白的去网上看看相关内容
                        mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.push_right_in));
                        mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.push_right_out));
                        currentPosition --;
//                        mImageSwitcher.setImageResource(imgIds[currentPosition % imgIds.length]);
                        mImageSwitcher.setImageURI(Uri.parse(uris[currentPosition% uris.length]));
                        setImageBackground(currentPosition);
                    }else{
                        Toast.makeText(getApplication(), "已经是第一张", Toast.LENGTH_SHORT).show();
                    }
                }

                if(lastX < downX){
                    if(currentPosition < uris.length - 1){
                        mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.push_left_in));
                        mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.push_left_out));
                        currentPosition ++ ;
//                        mImageSwitcher.setImageResource(imgIds[currentPosition]);
                        mImageSwitcher.setImageURI(Uri.parse(uris[currentPosition]));
                        setImageBackground(currentPosition);
                    }else{
                        Toast.makeText(getApplication(), "已经是最新数据", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            break;
        }

        return true;
    }



}
