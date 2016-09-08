package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.act.DownloadInterface;
import com.sichuan.geologenvi.http.HttpHandler;
import com.sichuan.geologenvi.utils.AppDownLoadTask;
import com.sichuan.geologenvi.utils.ToastUtils;

/**
 * Created by 可爱的蘑菇 on 2016/9/9.
 */
public class PSDdialog extends Dialog {

    private Activity act;
    private TextView app_update_tv_progress, app_update_tv_file_size, dataAsycn, cancelBtn, contentTxt;
    private HttpHandler httpHandler;
    private ProgressBar app_update_pb_progress;
    private String url, content;
    private EditText edt;

    public PSDdialog(Activity act,  CallBack cb){
        super(act, R.style.dialog);
        this.cb=cb;
        this.act=act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.psw_dialog);
        setCancelable(false);
        initView();
    }

    private void initView() {
        edt = (EditText) findViewById(R.id.pswEdt);
        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String psw = edt.getText().toString().trim();
                if (psw.length() > 0) {
                    dismiss();
                    cb.editfinish(psw);
                } else
                    ToastUtils.displayTextShort(act, "请输入密码");
            }
        });
        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                cb.cancel();
            }
        });
    }

    CallBack cb;
    public interface CallBack{
        void cancel();
        void editfinish(String psw);
    }
}
