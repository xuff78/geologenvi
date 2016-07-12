package com.sichuan.geologenvi.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.R;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ViewUtil {

    public static TextView getLinearTextView(String title, int height, Context con){
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(-1, ImageUtil.dip2px(con, height));
        TextView txt=new TextView(con);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        txt.setTextColor(con.getResources().getColor(R.color.normal_gray));
        txt.setTextSize(12);
        txt.setText(title);
        txt.setLayoutParams(llp);
        int padding=ImageUtil.dip2px(con, 20);
        txt.setPadding(padding, 0, padding, 0);
        return txt;
    }

    public static View getGrayLine(Context con){
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(-1, 1);
        View txt=new View(con);
        txt.setBackgroundResource(R.color.light_gray);
        txt.setLayoutParams(llp);
        return txt;
    }
}
