package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.ImageUtil;

import java.util.ArrayList;

/**
 * Created by 可爱的蘑菇 on 2015/8/28.
 */
public class Photo9Layout extends LinearLayout {

    private ArrayList<String> urls;
    private int imgWith=0;
    private ImageLoader loader=ImageLoader.getInstance();

    public Photo9Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageUrl(int width, ArrayList<String> urls) {
        this.urls=urls;
        setOrientation(LinearLayout.VERTICAL);
        if(urls.size()==1)
            imgWith = width / 3 * 2;
        else if(urls.size()==2)
            imgWith=width/2-4;
        else
            imgWith=width/3-6;
        if(urls.size()>0)
            setImageView();
        else
            setVisibility(View.GONE);
    }


    public void setImgCallback(ClickListener callback){
        this.callback=callback;
    }

    private void setImageView(){
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(imgWith, imgWith);
        llp.leftMargin = 2;
        LinearLayout itemLayout=null;
        for(int i=0;i<urls.size();i++) {
            final int j=i;
            if(i%3==0){
                itemLayout=new LinearLayout(getContext());
                itemLayout.setPadding(0,0,0,2);
                itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                addView(itemLayout);
            }
            TouchGrayImageView img = new TouchGrayImageView(getContext());
            img.setLayoutParams(llp);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setBackgroundResource(R.color.whitesmoke);
            itemLayout.addView(img);
            loader.displayImage(urls.get(i), img, ImageUtil.getImageOption(0));
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(callback!=null)
                        callback.onClick(view, j);
                }
            });
        }
    }

    private  ClickListener callback=null;
    public interface ClickListener{
        public void onClick(View v, int position);
    }
}
