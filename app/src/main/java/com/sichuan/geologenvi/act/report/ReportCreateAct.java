package com.sichuan.geologenvi.act.report;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sichuan.geologenvi.DataBase.SqlHandler;
import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.AppFrameAct;
import com.sichuan.geologenvi.http.CallBack;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.ActUtil;
import com.sichuan.geologenvi.utils.ConstantUtil;
import com.sichuan.geologenvi.utils.DialogUtil;
import com.sichuan.geologenvi.utils.ImageUtil;
import com.sichuan.geologenvi.utils.JsonUtil;
import com.sichuan.geologenvi.utils.LogUtil;
import com.sichuan.geologenvi.utils.ScreenUtil;
import com.sichuan.geologenvi.utils.ToastUtils;
import com.sichuan.geologenvi.utils.UploadUtil;
import com.sichuan.geologenvi.views.Photo9Layout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 可爱的蘑菇 on 2016/7/24.
 */
public class ReportCreateAct extends AppFrameAct implements View.OnClickListener{

    ArrayList<Map<String, String>> datalist=new ArrayList<>();
    Map<String, ArrayList<Map<String, String>>>  stations=new HashMap<>();
    TextView titleTxt, timeDate, publishTxt, videoFileTxt, dataTxt1;
    Photo9Layout photo9Layout;
    int type=0;
    private int imgItemWidth = 0;
    private LinkedHashMap<String, String> imgs=new LinkedHashMap<>();
    private String videoUrl=null;
    private LinearLayout photoLayout;
    private View addIconView;
    private LayoutInflater inflater;
    private SqlHandler sqlHandler;
    private HttpHandler httpHandler;
    private DatePickerDialog datePickerDialog;
    private String id="";
    private ProgressDialog dialog;
    private HorizontalScrollView horiScroller;
    private File videoFile = null;

    private void initHandler() {
        httpHandler=new HttpHandler(this, new CallBack(ReportCreateAct.this){

            @Override
            public void doSuccess(String method, String jsonData) {
                ToastUtils.displayTextShort(ReportCreateAct.this, "发布成功");
                setResult(0x11);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_create_detail);

        _setHeaderTitle("创建记录");
        _setRightHomeText("发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt=publishTxt.getText().toString();
                String typesTmp="";
                String types="";

                String imgUrls="";
                String urlStr="";
                for (String url:imgs.values()){
                    imgUrls=imgUrls+url+"|";
                    typesTmp=typesTmp+"0|";
                }
                if(videoUrl!=null&&videoUrl.length()>0){
                    imgUrls=imgUrls+videoUrl+"|";
                    typesTmp=typesTmp+"1|";
                }
                if(imgUrls.length()>0)
                    urlStr=imgUrls.substring(0, imgUrls.length()-1);
                if(typesTmp.length()>0)
                    types=typesTmp.substring(0, typesTmp.length()-1);
                if(txt.length()>0){
                    switch (type){
                        case 3:
                            httpHandler.addCJ_GZJL_KS(id, txt, types, urlStr);
                            break;
                        case 4:
                            httpHandler.addCJ_GZJL_DXS(id, txt, types, urlStr);
                            break;
                        case 5:
                            httpHandler.addCJ_GZJL_DZYJ(id, txt, types, urlStr);
                            break;
                        case 6:
                            httpHandler.addCJ_GZJL_BXBQ(id, txt, types, urlStr);
                            break;
                    }
                }else{
                    ToastUtils.displayTextShort(ReportCreateAct.this, "请输入内容");
                }

            }
        });
        initHandler();
        id=getIntent().getStringExtra("Id");
        inflater = LayoutInflater.from(this);
        int scrennWidth = getWindowManager().getDefaultDisplay().getWidth();
        imgItemWidth = (scrennWidth - ImageUtil.dip2px(this, 20) - 6) / 4;
        type=getIntent().getIntExtra("Type", 0);
        initView();
        sqlHandler=new SqlHandler(this);
        videoFile=getVideoFile();
    }

    private void initView() {
        horiScroller= (HorizontalScrollView) findViewById(R.id.horiScroller);
        titleTxt= (TextView) findViewById(R.id.titleTxt);
        timeDate= (TextView) findViewById(R.id.timeDate);
        publishTxt= (TextView) findViewById(R.id.publishTxt);
        Calendar mCalendar = Calendar.getInstance(Locale.CHINA);
        long todayL=mCalendar.getTimeInMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(todayL);//获取当
        String dateTxt = formatter.format(curDate);
        dataTxt1= (TextView) findViewById(R.id.dataTxt1);
        dataTxt1.setText(dateTxt);
        videoFileTxt= (TextView) findViewById(R.id.videoFileTxt);
        photoLayout= (LinearLayout) findViewById(R.id.photoLayout);
        findViewById(R.id.dateLayout1).setOnClickListener(this);
        findViewById(R.id.videoLayout).setOnClickListener(this);
        findViewById(R.id.cancelVideoBtn).setOnClickListener(this);
        setAddView();
    }

    DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            System.out.println("---> 设置后: year="+year+", month="+monthOfYear+",day="+dayOfMonth);
            dataTxt1.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.videoLayout:
                if(videoUrl!=null){
                    ActUtil.playVideo(ReportCreateAct.this, videoFile.getAbsolutePath());
                }else {
                    try {
                        videoFile.delete();
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30000);
                        Uri uri = Uri.fromFile(videoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        startActivityForResult(intent, TO_SELECT_VIDEO);
                    } catch (Exception e) {
                        ToastUtils.displayTextShort(ReportCreateAct.this, "暂不支持");
                    }
                }
                break;
            case R.id.dateLayout1:
                String[] dateStart=dataTxt1.getText().toString().split("-");
                datePickerDialog=new DatePickerDialog(ReportCreateAct.this, mDateSetListener, Integer.valueOf(dateStart[0]),
                        Integer.valueOf(dateStart[1])-1, Integer.valueOf(dateStart[2]));
                datePickerDialog.show();
                break;
            case R.id.cancelVideoBtn:
                if(videoUrl!=null&&videoUrl.length()>0){
                    videoUrl=null;
                    videoFileTxt.setText("点击上传视频");
                }
                break;
        }
    }
    private void setAddView() {
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imgItemWidth, imgItemWidth);
        llp.rightMargin = 2;
        addIconView = inflater.inflate(R.layout.bill_image_item, null);
        ImageView img = (ImageView) addIconView.findViewById(R.id.img);
//        img.setBackgroundResource(R.color.trans_white);
        img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        img.setImageResource(R.mipmap.tianjia);
        photoLayout.addView(addIconView, llp);

        addIconView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                new PhotoDialog(ReportCreateAct.this).show();
                Intent intent = new Intent(ReportCreateAct.this, SelectPicActivity.class);
                startActivityForResult(intent, TO_SELECT_PHOTO);
            }
        });
    }

    private void seImageView(Bitmap bmp, final String imgkey) {
//        urls.add(imgUrl);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(imgItemWidth, imgItemWidth);
        llp.rightMargin = 2;
        photoLayout.removeView(addIconView);
        final View v = inflater.inflate(R.layout.bill_image_item, null);
        final ImageView img = (ImageView) v.findViewById(R.id.img);
//        img.setImageResource(R.mipmap.zhaopian);
//        imageloader.displayImage(imgUrl, img);
        img.setImageBitmap(bmp);
        photoLayout.addView(v, llp);
        View del = v.findViewById(R.id.deleteIcon);
        del.setVisibility(View.VISIBLE);
        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                DialogUtil.showInfoDialog(ReportCreateAct.this, "确认删除?", "确定" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imgs.remove(imgkey);
                        removeImage(v, imgItemWidth, 0);
                    }
                });
            }
        });
        setAddView();
    }

    private void removeImage(final View item, int start, int end) {
        item.setVisibility(View.INVISIBLE);
        ValueAnimator anima = ValueAnimator.ofInt(start, end);
        anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                // TODO Auto-generated method stub
                LinearLayout.LayoutParams llpitem = (LinearLayout.LayoutParams) item.getLayoutParams();
                llpitem.width = (Integer) arg0.getAnimatedValue();
                item.setLayoutParams(llpitem);
            }
        });
        anima.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub
                photoLayout.removeView(item);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub

            }
        });

        anima.setInterpolator(AnimationUtils.loadInterpolator(this,
                android.R.anim.decelerate_interpolator));
        anima.setDuration(300);
        anima.start();
    }


    public static final int RequestAddress=0x11;
    public static final int TO_SELECT_PHOTO=0x12;
    public static final int TO_SELECT_VIDEO=0x13;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == RequestAddress){
            double lat = data.getDoubleExtra("lat", 0.0);
            double lng = data.getDoubleExtra("lng", 0.0);
//            initSearch(lat, lng);
        }else if (resultCode == RESULT_OK && requestCode == TO_SELECT_PHOTO) {
            final String picPath = data.getStringExtra(ConstantUtil.Photo_Path);
//            imgs.add(picPath);
            Log.i("Upload", "最终选择的图片=" + picPath);
            final Bitmap bitmap=ImageUtil.getSmallBitmap(picPath);
            final String imgkey= String.valueOf(System.currentTimeMillis());
            seImageView(bitmap, imgkey);
            horiScroller.scrollBy(imgItemWidth,0);
            dialog=ProgressDialog.show(ReportCreateAct.this, "", "处理中");
            dialog.setCancelable(false);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    int bitmapSize=getBitmapSize(bitmap);
//                        String result=UploadUtil.uploadFile(new File(picPath), ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
                    String result=UploadUtil.uploadBitmap(bitmap, "upload.jpg",ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
                    String url="";
                    if(result!=null)
                        url=JsonUtil.getString(result, "data");
                    if(url.length()>0){
                        imgs.put(imgkey, url);
                        handler.sendEmptyMessage(1);
                    }else
                        handler.sendEmptyMessage(0);
                    LogUtil.i("Upload", "size: " + bitmapSize + "Response: "+result);
                }
            }.start();
        }else if (resultCode == RESULT_OK && requestCode == TO_SELECT_VIDEO) {
            if(videoFile!=null&&videoFile.exists()){
                final String name=videoFile.getName();
                final Double videoSize=Double.valueOf(videoFile.length());
                String videoSizeString=ActUtil.twoDecimal(videoSize/1000000);
                if(videoSize<30000000) {
                    ToastUtils.displayTextShort(ReportCreateAct.this, "视频大小："+videoSizeString+"MB");
                    dialog = ProgressDialog.show(this, "", "处理中。。");
                    dialog.show();
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            String result=UploadUtil.uploadFile(videoFile, ConstantUtil.Api_Url+ConstantUtil.Method.Upload);
                            String url="";
                            if(result!=null)
                                url=JsonUtil.getString(result, "data");
                            if(url.length()>0){
                                videoUrl=url;
                                handler.sendEmptyMessage(2);
                            }else
                                handler.sendEmptyMessage(0);
                            LogUtil.i("Upload", "size: " + videoSize + "Response: "+result);
                        }
                    }.start();

                }else{
                    ToastUtils.displayTextShort(ReportCreateAct.this, "视频大小："+videoSizeString+"MB, 超过30MB");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public int getBitmapSize(Bitmap bitmap){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if(dialog!=null)
                        dialog.dismiss();
                    ToastUtils.displayTextShort(ReportCreateAct.this, "上传失败");
                    break;
                case 1:
                    if(dialog!=null)
                        dialog.dismiss();
                    break;
                case 2:
                    if(dialog!=null)
                        dialog.dismiss();
                    videoFileTxt.setText(videoUrl);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    protected File getVideoFile() {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()) + ".mp4";
        File out = new File(ConstantUtil.OfflinePath);
        if (!out.exists()) {
            out.mkdirs();
        }

        return new File(ConstantUtil.OfflinePath+fileName);
    }
}
