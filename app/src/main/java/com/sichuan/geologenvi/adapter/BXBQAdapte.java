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
 * Created by tanqq on 2016/9/1.
 */
public class BXBQAdapte extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<Map<String, String>> datalist;
    //    private LinearLayout.LayoutParams imgLp;

    public BXBQAdapte(Activity act, ArrayList<Map<String, String>> datalist) {
        this.datalist = datalist;
        this.act = act;
        listInflater = LayoutInflater.from(act);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LogUtil.i("totp", "create: " + position);
        RecyclerView.ViewHolder holder = null;
        View convertView = listInflater.inflate(R.layout.bxbq_bigform, null);
        RecyclerView.LayoutParams rlp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        convertView.setLayoutParams(rlp);
        holder = new DisasterHolder(convertView, position);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final DisasterHolder holder = (DisasterHolder) viewHolder;
        Map<String, String> infoMap = datalist.get(position);
        holder.txtNum.setText((position+1) + "");


        holder.txt1.setText(infoMap.get("XIAN"));
        holder.txt2.setText(infoMap.get("YHDMC"));
        holder.txt3.setText(infoMap.get("ZU"));
        holder.txt4.setText(infoMap.get("HZXM"));
        holder.txt5.setText(infoMap.get("SFZHM"));
        holder.txt6.setText(infoMap.get("RK"));
        holder.txt7.setText(infoMap.get("GGXMMC"));

        holder.txt8.setText(infoMap.get("JZAZ_LX"));
        holder.txt9.setText(infoMap.get("JZAZ_ZS"));
        holder.txt10.setText(infoMap.get("JZAZ_QDHT"));
        holder.txt11.setText(infoMap.get("JZAZ_QDBCFS"));
        holder.txt12.setText(infoMap.get("JZAZ_QDAZXY"));

        holder.txt13.setText(infoMap.get("HBHAZ_BCZJFF"));


        holder.txt14.setText(infoMap.get("FGG_FSAZ_MFXZ"));
        holder.txt15.setText(infoMap.get("FGG_FSAZ_XFXJ"));
        holder.txt16.setText(infoMap.get("FGG_ZZAZ_ZZAZXY"));
        holder.txt17.setText(infoMap.get("FGG_ZZAZ_BCZJFF"));
        holder.txt18.setText(infoMap.get("FGG_JZAZ_AZQXZ"));
        holder.txt19.setText(infoMap.get("FGG_JZAZ_SG"));

        holder.txt20.setText(infoMap.get("GDAZ"));
        holder.txt21.setText(infoMap.get("XFJC"));
        holder.txt22.setText(infoMap.get("XFRZ"));
        holder.txt23.setText(infoMap.get("JFCC"));
        holder.txt24.setText(infoMap.get("ZJDFK"));
        holder.txt25.setText(infoMap.get("BXBQYS"));
        holder.txt26.setText(infoMap.get("SJKGX"));

        holder.txt27.setText(infoMap.get("CZWT"));
        holder.txt28.setText(infoMap.get("BZ"));



    }

    public int getItemCount() {
        return datalist.size();
    }

    public int getItemViewType(int position) {
        return position;
    }

    public class DisasterHolder extends RecyclerView.ViewHolder {
        TextView txtNum, txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20,txt21,txt22,txt23,txt24,txt25,txt26,txt27,txt28;

        public DisasterHolder(View v, int position) {
            super(v);
            txtNum = (TextView) v.findViewById(R.id.txtNum);

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
            txt11 = (TextView) v.findViewById(R.id.txt11);
            txt12 = (TextView) v.findViewById(R.id.txt12);
            txt13 = (TextView) v.findViewById(R.id.txt13);
            txt14 = (TextView) v.findViewById(R.id.txt14);
            txt15 = (TextView) v.findViewById(R.id.txt15);
            txt16 = (TextView) v.findViewById(R.id.txt16);
            txt17 = (TextView) v.findViewById(R.id.txt17);
            txt18 = (TextView) v.findViewById(R.id.txt18);
            txt19 = (TextView) v.findViewById(R.id.txt19);
            txt20 = (TextView) v.findViewById(R.id.txt20);
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
