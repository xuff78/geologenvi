package com.sichuan.geologenvi.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.bean.Contact;

/**
 * Created by 可爱的蘑菇 on 2016/7/21.
 */
public class ContactDialog2 extends Dialog implements View.OnClickListener{

    private Contact contact;
    private String[] phones;
    private String otherInfoName;
    private TextView phone1, phone2, phone3;

    public ContactDialog2(Context context, Contact contact) {
        super(context, R.style.dialog);
        this.contact=contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCanceledOnTouchOutside(true);
        setContentView(R.layout.contact_dialog2);
        initView();
    }

    private void initView() {
        findViewById(R.id.phoneLayout1).setOnClickListener(this);
        findViewById(R.id.phoneLayout2).setOnClickListener(this);
        findViewById(R.id.phoneLayout3).setOnClickListener(this);

        ((TextView)findViewById(R.id.phoneTxt1)).setText(contact.getMaps().get("FZRNAME"));
        ((TextView)findViewById(R.id.phoneTxt2)).setText(contact.getMaps().get("CYNAME1"));
        ((TextView)findViewById(R.id.phoneTxt3)).setText(contact.getMaps().get("CYNAME2"));


        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        nameTxt.setText(contact.getName());
        phone1=(TextView)findViewById(R.id.phone1);
        phone1.setText(contact.getMaps().get("FZRPHONE"));
        phone2=(TextView)findViewById(R.id.phone2);
        phone2.setText(contact.getMaps().get("CYPHONE1"));
        phone3=(TextView)findViewById(R.id.phone3);
        phone3.setText(contact.getMaps().get("CYPHONE2"));


        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        String phone="";
        switch (view.getId()){
            case R.id.phoneLayout1:
                phone=phone1.getText().toString();
                if(phone.length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phone));
                    getContext().startActivity(call);
                }
                break;
            case R.id.phoneLayout2:
                phone=phone2.getText().toString();
                if(phone.length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phone));
                    getContext().startActivity(call);
                }
                break;
            case R.id.phoneLayout3:
                phone=phone3.getText().toString();
                if(phone.length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phone));
                    getContext().startActivity(call);
                }
                break;
        }
    }
}
