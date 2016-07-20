package com.sichuan.geologenvi.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.Contact;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ContactDialog  extends Dialog {

    private Contact contact;
    private String[] phones;

    public ContactDialog(Context context, Contact contact) {
        super(context, R.style.dialog);
        this.contact=contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCanceledOnTouchOutside(true);
        setContentView(R.layout.contact_dialog);
        initView();
    }

    private void initView() {
        TextView phoneTxt = (TextView) findViewById(R.id.phoneTxt);
        TextView phoneTxt2 = (TextView) findViewById(R.id.phoneTxt2);
        TextView positionTxt = (TextView) findViewById(R.id.positionTxt);
        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        TextView areaTxt = (TextView) findViewById(R.id.areaTxt);
        View phone2Lyout=findViewById(R.id.dialPhone2);
        View otherInfoLayout=findViewById(R.id.otherInfoLayout);
        View addrLayout=findViewById(R.id.addrLayout);
        if(contact.getPhone().contains(",")){
            phone2Lyout.setVisibility(View.VISIBLE);
            phones=contact.getPhone().split(",");
            phoneTxt.setText(phones[0]);
            phoneTxt2.setText(phones[1]);
        }else
            phoneTxt.setText(contact.getPhone());

        if(contact.getAddress()!=null&&contact.getAddress().length()>0)
            areaTxt.setText(contact.getAddress());
        else
            addrLayout.setVisibility(View.GONE);
        if(contact.getPosition()!=null&&contact.getPosition().length()>0)
            positionTxt.setText(contact.getPosition());
        else
            otherInfoLayout.setVisibility(View.GONE);
        nameTxt.setText(contact.getName());
        findViewById(R.id.dialPhone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contact.getPhone().length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phones[0]));
                    getContext().startActivity(call);
                }
            }
        });
        phone2Lyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contact.getPhone().length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phones[1]));
                    getContext().startActivity(call);
                }
            }
        });
        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
