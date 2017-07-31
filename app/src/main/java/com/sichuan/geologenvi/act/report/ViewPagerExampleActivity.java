package com.sichuan.geologenvi.act.report;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.adapter.EditItemAdapter1;
import com.sichuan.geologenvi.bean.CateInfo;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.views.ScrollPoints;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ViewPagerExampleActivity extends AppFrameAct {


    private Activity mActivity;
    private ArrayList<String> imgs=new ArrayList<String>();
    private ScrollPoints scrollPoints;

    private HttpHandler httpHandler;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(ViewPagerExampleActivity.this){

            @Override
            public void doSuccess(String method, String jsonData) {

              imgs=JsonUtil.getUrl(jsonData);

                int pos=getIntent().getIntExtra("pos", imgs.size()-1);

                ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
                mViewPager.setAdapter(new TouchImageAdapter());
                mViewPager.setCurrentItem(pos);
                scrollPoints= (ScrollPoints) findViewById(R.id.scrollPoints);
                scrollPoints.initPoints(mActivity, imgs.size(), pos);
                mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        scrollPoints.changeSelectedPoint(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });
    }




	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().hasExtra("Title")) {
            _setHeaderTitle(getIntent().getStringExtra("Title"));
            setContentView(R.layout.activity_viewpager_example);
            mActivity=this;

            if(getIntent().hasExtra("Images")){
                imgs= (ArrayList<String>) getIntent().getSerializableExtra("imgUrls");
//            imgs=getIntent().getExtras("imgUrls");
            }
            initHandler();

            requestInfo();
        }
        else {
            _setHeaderGone();
            ImageUtil.initImageLoader(this);
            int pos = getIntent().getIntExtra("pos", 0);
            imgs = getIntent().getStringArrayListExtra("Images");
            setContentView(R.layout.activity_viewpager_example);
            ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
            mViewPager.setAdapter(new TouchImageAdapter());
            mViewPager.setCurrentItem(pos);
        }







    }

    private void requestInfo() {
        httpHandler.getRadarUrl();
    }


    @Override
    protected void onResume() {
        super.onResume();
        ImageUtil.initImageLoader(this);
    }

    class TouchImageAdapter extends PagerAdapter {

        ImageLoader loader=ImageLoader.getInstance();

//        private static int[] images = { R.mipmap.background_img, R.mipmap.scrollview_header, R.mipmap.default_image, R.mipmap.default_head_img};

        @Override
        public int getCount() {
        	return imgs.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final TouchImageView img = new TouchImageView(container.getContext());

            loader.loadImage(imgs.get(position), ImageUtil.getImageOption(R.mipmap.icon_menu_4), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    img.setImageBitmap(loadedImage);
                }
            });
            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
