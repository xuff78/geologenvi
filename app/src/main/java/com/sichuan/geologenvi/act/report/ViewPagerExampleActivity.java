package com.sichuan.geologenvi.act.report;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.ImageUtil;

import java.util.ArrayList;

public class ViewPagerExampleActivity extends Activity {
	
	/**
	 * Step 1: Download and set up v4 support library: http://developer.android.com/tools/support-library/setup.html
	 * Step 2: Create ExtendedViewPager wrapper which calls TouchImageView.canScrollHorizontallyFroyo
	 * Step 3: ExtendedViewPager is a custom view and must be referred to by its full package name in XML
	 * Step 4: Write TouchImageAdapter, located below
	 * Step 5. The ViewPager in the XML should be ExtendedViewPager
	 */

    private ArrayList<String> imgs=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageUtil.initImageLoader(this);
        int pos=getIntent().getIntExtra("pos", 0);
        imgs=getIntent().getStringArrayListExtra("Images");
        setContentView(R.layout.activity_viewpager_example);
        ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TouchImageAdapter());
        mViewPager.setCurrentItem(pos);
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
