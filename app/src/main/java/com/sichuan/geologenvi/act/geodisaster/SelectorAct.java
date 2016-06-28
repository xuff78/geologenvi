package com.sichuan.geologenvi.act.geodisaster;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sichuan.geologenvi.MainActivity;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.act.SearchAct;
import com.sichuan.geologenvi.bean.AreaInfo;
import com.sichuan.geologenvi.bean.PopupInfoItem;
import com.sichuan.geologenvi.views.MarkerSupportView;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/27.
 */
public class SelectorAct extends AppFrameAct {

    private EditText nameEdt;
    private TextView areaTxt, disasterTypeTxt, disasterSizeTxt;
    private ArrayList<PopupInfoItem> disasterType=new ArrayList<>();
    private ArrayList<PopupInfoItem> disasterSize=new ArrayList<>();
    private String disasterTypeCode="", disasterSizeCode="", areaCode="";
    private int type=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        type=getIntent().getIntExtra("Type", 0);
        _setHeaderTitle("条件筛选");
        initView();
    }

    private void initView() {
        disasterType.add(new PopupInfoItem("斜坡", "00"));
        disasterType.add(new PopupInfoItem("滑坡", "01"));
        disasterType.add(new PopupInfoItem("泥石流", "02"));
        disasterType.add(new PopupInfoItem("地名塌陷", "03"));
        disasterType.add(new PopupInfoItem("地裂缝", "04"));
        disasterType.add(new PopupInfoItem("地面沉降", "05"));
        disasterType.add(new PopupInfoItem("其他", "06"));
        disasterType.add(new PopupInfoItem("取消", ""));

        disasterSize.add(new PopupInfoItem("特大型", "A"));
        disasterSize.add(new PopupInfoItem("大型", "B"));
        disasterSize.add(new PopupInfoItem("中型", "C"));
        disasterSize.add(new PopupInfoItem("小型", "D"));
        disasterSize.add(new PopupInfoItem("取消", ""));

        nameEdt=(EditText)findViewById(R.id.nameEdt);
        areaTxt=(TextView)findViewById(R.id.areaTxt);
        disasterTypeTxt=(TextView)findViewById(R.id.disasterTypeTxt);
        disasterSizeTxt=(TextView)findViewById(R.id.disasterSizeTxt);
        View disasterTypeLayout=findViewById(R.id.disasterTypeLayout);
        View areaLayout=findViewById(R.id.areaLayout);
        View disasterSizeLayout=findViewById(R.id.disasterSizeLayout);
        findViewById(R.id.confirmBtn).setOnClickListener(listener);

        findViewById(R.id.areaLayout).setVisibility(View.VISIBLE);
        switch (type){
            case 0:
            case 1:
            case 6:
                findViewById(R.id.nameLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.disasterTypeLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.disasterSizeLayout).setVisibility(View.VISIBLE);
                break;
        }
        areaLayout.setOnClickListener(listener);
        disasterTypeLayout.setOnClickListener(listener);
        disasterSizeLayout.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.confirmBtn:
                    String name=nameEdt.getText().toString();
                    Intent intent=getIntent();
                    intent.setClass(SelectorAct.this, TitleResultListAct.class);
                    intent.putExtra("Name", name);
                    intent.putExtra("disasterTypeCode", disasterTypeCode);
                    intent.putExtra("disasterSizeCode", disasterSizeCode);
                    intent.putExtra("areaCode", areaCode);
                    startActivity(intent);
                    break;
                case R.id.areaLayout:
                    Intent intent2=new Intent(SelectorAct.this, AreaSelectorAct.class);
                    startActivityForResult(intent2, 0x11);
                    break;
                case R.id.disasterTypeLayout:
                    final String items[]=new String[disasterType.size()];
                    for (int i=0;i<disasterType.size();i++){
                        items[i]=disasterType.get(i).getName();
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder.setTitle("灾难类型"); //设置标题
                    builder.setItems(items,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            PopupInfoItem item=disasterType.get(which);
                            if(item.getContent().length()>0){
                                disasterTypeTxt.setText(item.getName());
                                disasterTypeCode=item.getContent();
                            }else{
                                disasterTypeTxt.setText("点击选择");
                                disasterTypeCode="";
                            }
                        }
                    });
                    builder.create().show();
                    break;
                case R.id.disasterSizeLayout:
                    final String items2[]=new String[disasterSize.size()];
                    for (int i=0;i<disasterSize.size();i++){
                        items2[i]=disasterSize.get(i).getName();
                    }
                    AlertDialog.Builder builder2=new AlertDialog.Builder(SelectorAct.this);  //先得到构造器
                    builder2.setTitle("灾难规模"); //设置标题
                    builder2.setItems(items2,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            PopupInfoItem item=disasterSize.get(which);
                            if(item.getContent().length()>0){
                                disasterSizeTxt.setText(item.getName());
                                disasterSizeCode=item.getContent();
                            }else{
                                disasterSizeTxt.setText("点击选择");
                                disasterSizeCode="";
                            }
                        }
                    });
                    builder2.create().show();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x22){
            AreaInfo area= (AreaInfo) data.getSerializableExtra("Area");
            areaTxt.setText(area.getName());
            areaCode=area.getCode();
        }
    }
}
