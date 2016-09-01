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
public class BanQianAdapte extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity act;
    private LayoutInflater listInflater;
    private ArrayList<Map<String, String>> datalist;
    //    private LinearLayout.LayoutParams imgLp;

    public BanQianAdapte(Activity act, ArrayList<Map<String, String>> datalist) {
        this.datalist = datalist;
        this.act = act;
        listInflater = LayoutInflater.from(act);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LogUtil.i("totp", "create: " + position);
        RecyclerView.ViewHolder holder = null;
        View convertView = listInflater.inflate(R.layout.banqian_bigform, null);
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
        holder.txtArea.setText(infoMap.get("NAME"));
        holder.txt11.setText(infoMap.get("year1"));
        holder.txt12.setText(infoMap.get("year2"));
        holder.txt13.setText(infoMap.get("year3"));
        holder.txt14.setText(infoMap.get("xiaoji"));
    }

    public int getItemCount() {
        return datalist.size();
    }

    public int getItemViewType(int position) {
        return position;
    }

    public class DisasterHolder extends RecyclerView.ViewHolder {
        TextView txtNum, txtArea, txt11, txt12, txt13, txt14;

        public DisasterHolder(View v, int position) {
            super(v);
            txtNum = (TextView) v.findViewById(R.id.txtNum);
            txtArea = (TextView) v.findViewById(R.id.txtArea);
            txt11 = (TextView) v.findViewById(R.id.txt11);
            txt12 = (TextView) v.findViewById(R.id.txt12);
            txt13 = (TextView) v.findViewById(R.id.txt13);
            txt14 = (TextView) v.findViewById(R.id.txt14);
        }
    }
}
