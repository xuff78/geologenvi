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
 * Created by 可爱的蘑菇 on 2016/7/17.
 */
public class FormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<Map<String, String>> datalist;
    //    private LinearLayout.LayoutParams imgLp;

    public FormAdapter(Activity act, ArrayList<Map<String, String>> datalist) {
        this.datalist = datalist;
        this.act = act;
        listInflater = LayoutInflater.from(act);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LogUtil.i("totp", "create: " + position);
        RecyclerView.ViewHolder holder = null;
        View convertView = listInflater.inflate(R.layout.form_item1_layout, null);
        RecyclerView.LayoutParams rlp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(rlp);
        holder = new DisasterHolder(convertView, position);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final DisasterHolder holder = (DisasterHolder) viewHolder;
        Map<String, String> infoMap=datalist.get(position);
        holder.txt1.setText(infoMap.get("NAME"));
        holder.txt2.setText(infoMap.get("XiePo"));
        holder.txt3.setText(infoMap.get("HuaPo"));
        holder.txt4.setText(infoMap.get("BengTa"));
        holder.txt5.setText(infoMap.get("NiShiLiu"));
        holder.txt6.setText(infoMap.get("DiMianTaXian"));
        holder.txt7.setText(infoMap.get("DiLieFeng"));
        holder.txt8.setText(infoMap.get("DiMianChenJiang"));
        holder.txt9.setText(infoMap.get("QiTa"));
        holder.txt10.setText(infoMap.get("Suoyou"));

    }

    public int getItemCount() {
        return datalist.size();
    }

    public int getItemViewType(int position) {
        return position;
    }

    public class DisasterHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;

        public DisasterHolder(View v, int position) {
            super(v);
            txt1 = (TextView) v.findViewById(R.id.txt1);
            txt2 = (TextView) v.findViewById(R.id.txt2);
            txt3 = (TextView) v.findViewById(R.id.txt3);
            txt4 = (TextView) v.findViewById(R.id.txt4);
            txt5 = (TextView) v.findViewById(R.id.txt5);
            txt6 = (TextView) v.findViewById(R.id.txt6);
            txt7 = (TextView) v.findViewById(R.id.txt7);
            txt8 = (TextView) v.findViewById(R.id.txt8);
            txt9 = (TextView) v.findViewById(R.id.txt9);
            txt10 = (TextView) v.findViewById(R.id.txt10);
        }
    }
}
