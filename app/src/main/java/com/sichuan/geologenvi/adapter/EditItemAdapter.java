package com.sichuan.geologenvi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/28.
 */
public class EditItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<Map<String, String>> datalist=new ArrayList<>();
    private ImageLoader imageDownloader = ImageLoader.getInstance();
    //    private LinearLayout.LayoutParams imgLp;
    private TextView footer;
    private View.OnClickListener itemListener;
    private String titleKey;

    public EditItemAdapter(Activity act, ArrayList<Map<String, String>> datalist, String titleKey, View.OnClickListener itemListener)
    {
        this.itemListener=itemListener;
        this.datalist=datalist;
        this.act=act;
        this.titleKey=titleKey;
        listInflater= LayoutInflater.from(act);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position)
    {
        LogUtil.i("totp", "create: "+position);
        RecyclerView.ViewHolder holder=null;
        View convertView = listInflater.inflate(R.layout.listitem_simple_txt, null);
        RecyclerView.LayoutParams rlp=new RecyclerView.LayoutParams(-1, RecyclerView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(rlp);
        holder = new TitleHolder(convertView, position);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position)
    {
        final TitleHolder holder = (TitleHolder)viewHolder;
        String name=datalist.get(position).get(titleKey);
        if(name!=null&&name.length()>0)
            holder.titleTxt.setText(name);
        else
            holder.titleTxt.setText("缺省名称");
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
            titleTxt.setTag(position);
            titleTxt.setOnClickListener(itemListener);
        }
    }
}