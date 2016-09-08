package com.sichuan.geologenvi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.report.ViewPagerExampleActivity;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.FileUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.XmlParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ActivityInfoAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context con;
    private Map<String, String> dataList;
    private int itemHeight = 0;
    private List<String> mapKeyList;
    private String xmlName;
    private int blue;

    public ActivityInfoAdapter(Context context, Map<String, String> dataList, String xmlName) {
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        con = context;
        mapKeyList = new ArrayList<>(dataList.keySet());
        this.xmlName=xmlName;
        blue=context.getResources().getColor(R.color.normal_blue);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dataList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        String name=XmlParse.getPapers(con, mapKeyList.get(position), xmlName);
        String content=dataList.get(mapKeyList.get(position));
        View v = null;
        if((name!=null&&name.length()>16)||(content!=null&&content.length()>24))
            v = mInflater.inflate(R.layout.listitem_info_inact2, null);
        else
            v = mInflater.inflate(R.layout.listitem_info_inact, null);

        TextView title = (TextView)v.findViewById(R.id.nameTxt);
        TextView contentTxt = (TextView)v.findViewById(R.id.contentTxt);
        title.setText(name);
        if(content!=null&&content.length()>0) {
            if(content.startsWith("tel")){
                contentTxt.setText(content.substring(3));
                contentTxt.setTextColor(blue);
                contentTxt.setOnClickListener(listener);
            }else if(mapKeyList.get(position).equals("FILESNAME")){
                final String[] names=content.split("\\|");
                contentTxt.setText(content);
                contentTxt.setTextColor(blue);
                contentTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtil.showSelectDialog(con, "附件图片", names, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String[] codes=dataList.get("FILESCODE").split("\\|");
                                final ArrayList<String> imgUrls=new ArrayList<>();
                                for (int j=0;j<codes.length;j++){
                                    imgUrls.add(ConstantUtil.YIJI_Pic_Url+codes[j]+".jpg");
                                }

                                //只能是图片
                                Intent intent = new Intent(con, ViewPagerExampleActivity.class);
                                intent.putExtra("Images", imgUrls);
                                intent.putExtra("pos", i);
                                con.startActivity(intent);

                                //如果有其他类型，可选择用浏览器打开，可能会有点慢
//                                Intent intent=new Intent();
//                                intent.setAction("android.intent.action.VIEW");
//                                Uri CONTENT_URI_BROWSERS = Uri.parse(imgUrls.get(i));
//                                intent.setData(CONTENT_URI_BROWSERS);
//                                con.startActivity(intent);
                            }
                        });
                    }
                });
            }else
                contentTxt.setText(content);
        }else
            contentTxt.setTextColor(Color.LTGRAY);
        return v;
    }

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String phone=((TextView)view).getText().toString();
            if(phone.length()>0) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + phone));
                con.startActivity(call);
            }
        }
    };
}
