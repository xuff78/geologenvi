package com.sichuan.geologenvi.act.statistics;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ChatAct extends AppFrameAct implements View.OnClickListener{

    private SqlHandler handler;
    private String tableName="";
    private TextView txt1,txt2, txt3, txt4;
    private View selection1, selection2, selection3, selection4;
    private WebView mWebView;
    private int requestType=0;
    private String area="", disasterType="", isCancel="";
    private int statisticsType=0;
    private String jsonString="";

    private TextView txtCount;

    private int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_detail_layout);

        type=getIntent().getIntExtra("Type", 1);


        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        handler=new SqlHandler(this);
        tableName=getIntent().getStringExtra("TableName");
        queryForStatistics();
    }

    private void initView() {

        txtCount=(TextView) findViewById(R.id.countLayout);
        txt1= (TextView) findViewById(R.id.txt1);
        txt2= (TextView) findViewById(R.id.txt2);
        txt3= (TextView) findViewById(R.id.txt3);
        txt4= (TextView) findViewById(R.id.txt4);

        selection1=findViewById(R.id.selection1);
        selection2=findViewById(R.id.selection2);
        selection3=findViewById(R.id.selection3);
        selection4=findViewById(R.id.selection4);
        selection1.setOnClickListener(this);
        selection2.setOnClickListener(this);
        selection3.setOnClickListener(this);
        selection4.setOnClickListener(this);
        mWebView=(WebView)findViewById(R.id.mWebView);
//            mWebView.setBackgroundColor(1);
        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setBackgroundColor(getResources().getColor(R.color.hard_gray));
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.clearCache(false);
        mWebView.setFocusable(false);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebView.loadUrl("javascript:setData('" + jsonString + "')");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.selection1:
                final LinkedList<PopupInfoItem> areainfos= handler.getTypesQueryWithCode("SL_TATTR_DZZH_XZQH", "CODE", "NAME", "length(CODE) = 6");
                final String[] areaTxt=new String[areainfos.size()+1];
                areaTxt[0]="成都";
                for(int i=0;i<areainfos.size();i++){
                    areaTxt[i+1]=areainfos.get(i).getName();
                }
                DialogUtil.showSelectDialog(ChatAct.this, "统计区域", areaTxt, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txt1.setText(areaTxt[i]);
                        if(i==0)
                            area="";
                        else
                            area=areainfos.get(i-1).getContent();
                        queryForStatistics();
                    }
                });
                break;
            case R.id.selection2:
                final String[] typeTxt=new String[]{"类别统计", "规模等级", "险情等级"};

                DialogUtil.showSelectDialog(ChatAct.this, "统计类型", typeTxt, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txt2.setText(typeTxt[i]);
                        statisticsType=i;
                        queryForStatistics();
                        if(i==0){
                            disasterType="";
                            txt3.setText("全部");
                        }
                    }
                });
                break;
            case R.id.selection3:
                if(statisticsType!=0) {
                    final String[] disasterInfos = new String[]{"全部", "斜坡", "滑坡", "崩塌", "泥石流", "地面塌陷", "地裂缝", "地面沉降", "其他"};
                    DialogUtil.showSelectDialog(ChatAct.this, "统计区域", disasterInfos, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            txt3.setText(disasterInfos[i]);
                            if (i == 0)
                                disasterType = "";
                            else
                                disasterType = "0" + (i - 1);
                            queryForStatistics();
                        }
                    });
                }else
                    ToastUtils.displayTextShort(ChatAct.this, "类别统计只能选择全部");
                break;
            case R.id.selection4:
                final String[] cancel=new String[]{"全部","是","否"};
                DialogUtil.showSelectDialog(ChatAct.this, "是否销号", cancel, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txt4.setText(cancel[i]);
                        if(i==0)
                            isCancel="";
                        else if(i==1)
                            isCancel="1";
                        else if(i==2)
                            isCancel="0";
                        queryForStatistics();
                    }
                });
                break;
        }
    }

    private void queryForStatistics(){
        ArrayList<Map<String, String>> data=new ArrayList<>();
        String typeStr="";

        //UPDATE CUIKAILEI START
        if(type==1)
            typeStr=" ZHAA01A810=2 ";
        //END

        if(area.length()>0){
            if(typeStr.length()>0)
                typeStr+=" AND ";

            typeStr+=" ZHAA01A110 = '"+area+"'";
        }
        if(statisticsType!=0&&disasterType.length()>0){
            if(typeStr.length()>0)
                typeStr+=" AND ";
            typeStr += " ZHAA01A210 = '" + disasterType+"'";
        }
        if(isCancel.length()>0) {
            if(typeStr.length()>0)
                typeStr+=" AND ";
            typeStr += " ZHAA01A875 = '" + isCancel +"'";
        }

        if(typeStr.length()>0)
            typeStr = " WHERE " + typeStr;

        ArrayList<Map<String, String>> data2=new ArrayList<>();
        data2=handler.getQueryResult("count(*) as count,sum(ZHAA01A390) as renkou,sum(ZHAA01A400) as hushu,sum(ZHAA01A410) as caichan",
                                "SL_ZHAA01A",
                                typeStr);

        txtCount.setText("共有隐患点："+data2.get(0).get("count")+"个    威胁人口："+data2.get(0).get("renkou")+"人\n威胁户数："+data2.get(0).get("hushu")+"户    威胁财产："+data2.get(0).get("caichan")+"万元");

        switch (statisticsType){
            case 0:
                data=handler.getQueryResult("SUM(CASE WHEN ZHAA01A210 = '00' THEN 1 ELSE 0 END) as XiePo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '01' THEN 1 ELSE 0 END) as HuaPo,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '02' THEN 1 ELSE 0 END) as BengTa,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '03' THEN 1 ELSE 0 END) as NiShiLiu,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '04' THEN 1 ELSE 0 END) as DiMianTaXian,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '05' THEN 1 ELSE 0 END) as DiLieFeng,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '06' THEN 1 ELSE 0 END) as DiMianChenJiang,\n" +
                                "SUM(CASE WHEN ZHAA01A210 = '07' THEN 1 ELSE 0 END) as QiTa",
                        "SL_ZHAA01A",
                        typeStr);
                jsonString=getTypeJson(data, disasterKey, disasterNames);
                break;
            case 1:
                data=handler.getQueryResult(
                                "SUM(CASE WHEN ZHAA01A890 = 'A' THEN 1 ELSE 0 END) as TedaXing,\n" +
                                "SUM(CASE WHEN ZHAA01A890 = 'B' THEN 1 ELSE 0 END) as DaXing,\n" +
                                "SUM(CASE WHEN ZHAA01A890 = 'C' THEN 1 ELSE 0 END) as ZhongXing,\n" +
                                "SUM(CASE WHEN ZHAA01A890 = 'D' THEN 1 ELSE 0 END) as XiaoXing",
                        "SL_ZHAA01A",
                        typeStr);
                jsonString=getTypeJson(data, sizeKey, sizeName);
                break;
            case 2:
                data=handler.getQueryResult(
                                "SUM(CASE WHEN ZHAA01A420 = 'A' THEN 1 ELSE 0 END) as TedaXing,\n" +
                                "SUM(CASE WHEN ZHAA01A420 = 'B' THEN 1 ELSE 0 END) as DaXing,\n" +
                                "SUM(CASE WHEN ZHAA01A420 = 'C' THEN 1 ELSE 0 END) as ZhongXing,\n" +
                                "SUM(CASE WHEN ZHAA01A420 = 'D' THEN 1 ELSE 0 END) as XiaoXing",
                        "SL_ZHAA01A",
                        typeStr);
                jsonString=getTypeJson(data, sizeKey, sizeName);
                break;
        }
        mWebView.loadUrl("file:///android_asset/form1.html");
    }

    String[] disasterNames={"不稳定斜坡", "滑坡", "崩塌", "泥石流", "地面塌陷", "地裂缝", "地面沉降", "其它"};
    String[] disasterKey={"XiePo", "HuaPo", "BengTa", "NiShiLiu", "DiMianTaXian", "DiLieFeng", "DiMianChenJiang", "QiTa"};
    String[] sizeName={"特大型", "大型", "中型", "小型"};
    String[] sizeKey={"TedaXing", "DaXing", "ZhongXing", "XiaoXing"};
    public String getTypeJson(ArrayList<Map<String, String>> datalist, String[] key, String[] names) {
        String jsonData="";
        if(datalist.size()>0) {
            Map<String, String> info=datalist.get(0);
            try {
                JSONObject jsonObj = new JSONObject();

                JSONArray array1 = new JSONArray();
                JSONArray arrayTag = new JSONArray();

                for (int i=0;i<names.length; i++) {

                    JSONObject subValue = new JSONObject();
                    JSONObject subjosn=new JSONObject();
                    subjosn.put("name", info.get(key[i]));
                    subjosn.put("value", info.get(key[i]));

//                    JSONObject labeljosn=new JSONObject();
//                    labeljosn.put("show", true);
//                    labeljosn.put("position", "top");
//                    JSONObject normalJson=new JSONObject();
//                    normalJson.put("normal", labeljosn);
//                    subjosn.put("label", normalJson);

                    subValue.put("data", subjosn);
                    array1.put(i, subValue);

                    JSONObject subName = new JSONObject();
                    subName.put("data", names[i]);
                    arrayTag.put(i, subName);

                }

                jsonObj.put("FristFormInfo1", array1);
                jsonObj.put("tags", arrayTag);

                jsonData = jsonObj.toString();
                Log.i("Json", jsonObj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonData;
    }
}
