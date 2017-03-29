package com.sichuan.geologenvi.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sichuan.geologenvi.R;

/**
 * Created by tanqq on 2017/3/26.
 */
public class YujingNotDialog extends Dialog {
    private Activity act;
    private String title=null;
private String content=null;
    public YujingNotDialog(Activity act,  CallBack cb){
        super(act, R.style.dialog);
        this.cb=cb;
        this.act=act;
    }

    public YujingNotDialog(Activity act,  CallBack cb, String title,String Content){
        super(act, R.style.dialog);
        this.cb=cb;
        this.act=act;

        this.title=title;
        this.content=Content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.yujingnote_dialog);
        setCancelable(false);
        initView();
    }

    private void initView() {
        TextView titleTxt= (TextView) findViewById(R.id.dialogTitle);
        if(title!=null)
            titleTxt.setText(title);
        TextView YJcontent = (TextView) findViewById(R.id.dialogContent);
        if(content!=null)
            YJcontent.setText(content);
        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb.OK();
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
        void OK();
    }
}
