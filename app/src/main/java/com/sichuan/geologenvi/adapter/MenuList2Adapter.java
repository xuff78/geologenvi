package com.sichuan.geologenvi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.MenuListItem2;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by 可爱的蘑菇 on 2016/7/23.
 */
public class MenuList2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<MenuListItem2> datalist=new ArrayList<>();
    private ImageLoader imageDownloader = ImageLoader.getInstance();
    //    private LinearLayout.LayoutParams imgLp;
    private TextView footer;
    private View.OnClickListener itemListener;

    public MenuList2Adapter(Activity act, ArrayList<MenuListItem2> datalist, View.OnClickListener itemListener)
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
        if(position==datalist.size()){
            RecyclerView.LayoutParams rlp=new RecyclerView.LayoutParams(-1, ImageUtil.dip2px(act, 40));
            footer=new TextView(act);
            footer.setLayoutParams(rlp);
            footer.setGravity(Gravity.CENTER);
            footer.setText("加载中");
            holder = new FootHolder(footer);
        }else {
            View convertView = listInflater.inflate(R.layout.listitem_2line_txt, null);
            RecyclerView.LayoutParams rlp = new RecyclerView.LayoutParams(-1, RecyclerView.LayoutParams.WRAP_CONTENT);
            convertView.setLayoutParams(rlp);
            holder = new TitleHolder(convertView, position);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position)
    {
        if(position<datalist.size()) {
            final TitleHolder holder = (TitleHolder) viewHolder;
            MenuListItem2 info = datalist.get(position);
            if (info.getTitle() != null && info.getTitle().length() > 0)
                holder.titleTxt.setText(info.getTitle());
            else
                holder.titleTxt.setText("缺省名称");
            holder.subTxtL.setText(info.getSubTxtL());
            holder.subTxtR.setText(info.getSubTxtR());
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size()==0?0:datalist.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        return position;
    }

    public class TitleHolder extends RecyclerView.ViewHolder
    {
        TextView titleTxt, subTxtL, subTxtR;

        public TitleHolder(View v, int position)
        {
            super(v);
            titleTxt = (TextView) v.findViewById(R.id.titleTxt);
            subTxtL = (TextView) v.findViewById(R.id.subTxtL);
            subTxtR = (TextView) v.findViewById(R.id.subTxtR);
            v.setTag(position);
            v.setOnClickListener(itemListener);
        }
    }

    public class FootHolder extends RecyclerView.ViewHolder
    {

        public FootHolder(View v)
        {
            super(v);
        }
    }

    public void setRefresh(boolean refresh){
        if(refresh)
            footer.setText("加载中");
        else
            footer.setText("已全部加载");
    }
}
