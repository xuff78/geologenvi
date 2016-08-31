package com.sichuan.geologenvi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.utils.LogUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/8/31.
 */
public class DisasterStatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<Map<String, String>> datalist;
    private Map<String, String> totalInfo;
    //    private LinearLayout.LayoutParams imgLp;

    public DisasterStatisticsAdapter(Activity act, ArrayList<Map<String, String>> datalist) {
        this.datalist = datalist;
        this.act = act;
        listInflater = LayoutInflater.from(act);
    }

    public void setTotal(Map<String, String> totalInfo){
        this.totalInfo=totalInfo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LogUtil.i("totp", "create: " + position);
        RecyclerView.ViewHolder holder = null;
        View convertView = listInflater.inflate(R.layout.disaster_bigform, null);
        RecyclerView.LayoutParams rlp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(rlp);
        holder = new DisasterHolder(convertView, position);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final DisasterHolder holder = (DisasterHolder) viewHolder;
        if(position<datalist.size()) {
            Map<String, String> infoMap = datalist.get(position);
            holder.txtNum.setText(position + "");
            holder.txtArea.setText(infoMap.get("NAME"));
            holder.txt21.setText(infoMap.get("XiePo"));
            holder.txt22.setText(infoMap.get("HuaPo"));
            holder.txt23.setText(infoMap.get("BengTa"));
            holder.txt24.setText(infoMap.get("NiShiLiu"));
            holder.txt25.setText(infoMap.get("DiMianTaXian"));
            holder.txt26.setText(infoMap.get("DiLieFeng"));
            holder.txt27.setText(infoMap.get("DiMianChenJiang"));
            holder.txt28.setText(infoMap.get("QiTa"));
            holder.txt11.setText(infoMap.get("Suoyou"));
            holder.txt12.setText(infoMap.get("HuShu"));
            holder.txt13.setText(infoMap.get("RenShu"));
            holder.txt14.setText(infoMap.get("ZiChan"));
        }else{
//            holder.txtNum.setText(position + "");
            holder.txtArea.setText("合计");
            if(totalInfo!=null){
                holder.txt21.setText(totalInfo.get("XiePo"));
                holder.txt22.setText(totalInfo.get("HuaPo"));
                holder.txt23.setText(totalInfo.get("BengTa"));
                holder.txt24.setText(totalInfo.get("NiShiLiu"));
                holder.txt25.setText(totalInfo.get("DiMianTaXian"));
                holder.txt26.setText(totalInfo.get("DiLieFeng"));
                holder.txt27.setText(totalInfo.get("DiMianChenJiang"));
                holder.txt28.setText(totalInfo.get("QiTa"));
                holder.txt11.setText(totalInfo.get("Suoyou"));
                holder.txt12.setText(totalInfo.get("HuShu"));
                holder.txt13.setText(totalInfo.get("RenShu"));
                holder.txt14.setText(totalInfo.get("ZiChan"));
            }
        }
    }

    public int getItemCount() {
        return datalist.size()+1;
    }

    public int getItemViewType(int position) {
        return position;
    }

    public class DisasterHolder extends RecyclerView.ViewHolder {
        TextView txtNum, txtArea, txt11, txt12, txt13, txt14, txt21, txt22, txt23, txt24, txt25, txt26, txt27, txt28;

        public DisasterHolder(View v, int position) {
            super(v);
            txtNum = (TextView) v.findViewById(R.id.txtNum);
            txtArea = (TextView) v.findViewById(R.id.txtArea);
            txt11 = (TextView) v.findViewById(R.id.txt11);
            txt12 = (TextView) v.findViewById(R.id.txt12);
            txt13 = (TextView) v.findViewById(R.id.txt13);
            txt14 = (TextView) v.findViewById(R.id.txt14);
            txt21 = (TextView) v.findViewById(R.id.txt21);
            txt22 = (TextView) v.findViewById(R.id.txt22);
            txt23 = (TextView) v.findViewById(R.id.txt23);
            txt24 = (TextView) v.findViewById(R.id.txt24);
            txt25 = (TextView) v.findViewById(R.id.txt25);
            txt26 = (TextView) v.findViewById(R.id.txt26);
            txt27 = (TextView) v.findViewById(R.id.txt27);
            txt28 = (TextView) v.findViewById(R.id.txt28);
        }
    }
}
