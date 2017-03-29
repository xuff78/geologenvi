package com.sichuan.geologenvi.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/22.
 */
public class TopImgAdapter extends PagerAdapter{

    int[] pic;
    ArrayList<String> imgUrls;
    private Activity act;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private String imgUrl="http://adimages.b0.upaiyun.com/cbs/";

    public TopImgAdapter(Activity act, int[] pic) {
        this.pic=pic;
        this.act=act;
        imageLoader=ImageLoader.getInstance();
    }

    public TopImgAdapter(Activity act,ArrayList<String> ImgUrls){
        this.act=act;
        this.imgUrls=ImgUrls;
        imageLoader=ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return pic.length;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {

        ImageView img = new ImageView(act);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageLoader.displayImage(imgUrl+info.getFilename(), img, options);
        container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        if(imgUrls!=null){
//            String url=imgUrls.get(position);
//            URL picUrl = new URL (url);
//
//            Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
//            img.setImageURI(Uri.parse(imgUrls.get(position)));
//        }
//        else{
            img.setImageResource(pic[position]);
//        }
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
