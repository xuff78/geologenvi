package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.PopupInfoAdapter;
import com.sichuan.geologenvi.bean.PopupInfoItem;

import java.util.ArrayList;

/**
 * 显示公共设施状况详情
 * Created by haiyunlong on 2015/11/27.
 */
public class MarkerSupportView{

    private Activity mContext;
    private ListView infoList;
    private TextView titleTxt;
    private String title;
    private int columnSize=1;
    private  float density;
    private ArrayList<PopupInfoItem> datas;
    private View.OnClickListener clickListener;
    private boolean showDetail=true;
    private View contentView=null;

    public MarkerSupportView(Activity context, String title, View.OnClickListener clickListener) {
        this.mContext=context;
        this.title=title;
        this.clickListener=clickListener;
        setPopView();
    }

    public View getPopView(){
        return contentView;
    }


    private void setPopView(){
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.mark_detail_view, null);
        titleTxt = (TextView) contentView.findViewById(R.id.titleTxt);
        titleTxt.setText(title);
        infoList = (ListView) contentView.findViewById(R.id.infoList);
        contentView.findViewById(R.id.detailBtn).setOnClickListener(clickListener);

        //获取屏幕大小，以及图片的数量，来控制滚动图片的容器宽度
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) (display.getWidth() * 0.6), (int) (display.getHeight() * 0.25));
        infoList.setLayoutParams(layoutParams);
    }

    public void setListView(ArrayList<PopupInfoItem> datas){
        this.datas=datas;
        infoList.setAdapter(new PopupInfoAdapter(mContext, datas));
    }

    public void setVisibility(int visibility) {
        contentView.setVisibility(visibility);
    }
}
