package com.sichuan.geologenvi.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.views.FooterViewHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/7.
 */
public class MenuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<String> datalist=new ArrayList<>();
    private ImageLoader imageDownloader = ImageLoader.getInstance();
//    private LinearLayout.LayoutParams imgLp;
    private TextView footer;
    private View.OnClickListener itemListener;

    public MenuListAdapter(Activity act, ArrayList<String> datalist, View.OnClickListener itemListener)
    {
        this.itemListener=itemListener;
        this.datalist=datalist;
        this.act=act;
        listInflater= LayoutInflater.from(act);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position)
    {
        LogUtil.i("totp", "create: "+position);
        RecyclerView.ViewHolder holder=null;
        View convertView = listInflater.inflate(R.layout.listitem_simple_txt, null);
        holder = new TitleHolder(convertView, position);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position)
    {
        final TitleHolder holder = (TitleHolder)viewHolder;
        holder.titleTxt.setText(datalist.get(position));
    }

    public int getItemCount() {
        return datalist.size();
    }

    public int getItemViewType(int position) {
        return position;
    }

    public class TitleHolder extends RecyclerView.ViewHolder
    {
        TextView titleTxt;

        public TitleHolder(View v, int position)
        {
            super(v);
            titleTxt = (TextView) v.findViewById(R.id.titleTxt);
            v.setTag(position);
            v.setOnClickListener(itemListener);
        }
    }
}
