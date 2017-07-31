package com.sichuan.geologenvi.adapter;

        import android.content.ContentValues;
        import android.content.Context;
        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.sichuan.geologenvi.R;
        import com.sichuan.geologenvi.bean.RainBean;
        import com.sichuan.geologenvi.utils.LogUtil;
        import com.sichuan.geologenvi.utils.XmlParse;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Comparator;
        import java.util.List;
        import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RainQXAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context con;
    private int itemHeight = 0;
    private RainBean[] dataList;
    private ArrayList<RainBean> dataListQx;
    private int hour=1;

    public RainQXAdapter(Context context, ArrayList<RainBean> dataList) {
        this.mInflater = LayoutInflater.from(context);

        this.dataList = dataList.toArray(new RainBean[dataList.size()]);
        con = context;
        setHours(1);
    }

    public RainBean[] getData(){
//        return dataList;
        return dataListQx.toArray(new RainBean[dataListQx.size()]);
    }

    public void setHours(int hour){
        this.hour=hour;


        Arrays.sort(dataList, new ComparatorValues());

        dataListQx=new ArrayList<RainBean>();

        for(int i=0;i<dataList.length;i++) {
            int j=0;
            for (; j < dataListQx.size(); j++) {
                if (dataList[i].getQx().equals( dataListQx.get(j).getQx())) {
                    break;
                }

            }
            if (j == dataListQx.size()) {
                dataListQx.add(dataList[i]);
            }
        }


        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dataListQx.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  v = mInflater.inflate(R.layout.rainqx_item_layout, null);
//        RainBean bean=dataList[position];
        RainBean bean=dataListQx.get(position);
        TextView title = (TextView)v.findViewById(R.id.hintTxt1);
        TextView contentTxt = (TextView)v.findViewById(R.id.hintTxt2);
        TextView num = (TextView)v.findViewById(R.id.hintTxt3);


        num.setText((position+1)+"");
        title.setText(bean.getQx());

        contentTxt.setText(bean.getRainInfo(hour));

        return v;
    }

    public final class ComparatorValues implements Comparator<RainBean> {

        @Override
        public int compare(RainBean rain1, RainBean rian2) {
            float m1=Float.valueOf(rain1.getRainInfo(hour));
            float m2=Float.valueOf(rian2.getRainInfo(hour));
            int result=0;
            if(m1>m2)
            {
                result=-1;
            }
            if(m1<m2)
            {
                result=1;
            }
            return result;
        }

    }
}
