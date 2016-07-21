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
public class ContactDialog3 extends Dialog implements View.OnClickListener{

    private Contact contact;
    private String[] phones;
    private String otherInfoName;
    private TextView phone1,phone12, phone2,phone22, phone3, phone32;

    public ContactDialog3(Context context, Contact contact) {
        super(context, R.style.dialog);
        this.contact=contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCanceledOnTouchOutside(true);
        setContentView(R.layout.contact_dialog3);
        initView();
    }

    private void initView() {
        findViewById(R.id.phoneLayout1).setOnClickListener(this);
        View phoneLayout12=findViewById(R.id.phoneLayout12);
        phoneLayout12.setOnClickListener(this);
        findViewById(R.id.phoneLayout2).setOnClickListener(this);
        View phoneLayout22=findViewById(R.id.phoneLayout22);
        phoneLayout22.setOnClickListener(this);
        findViewById(R.id.phoneLayout3).setOnClickListener(this);
        View phoneLayout32=findViewById(R.id.phoneLayout32);
        phoneLayout32.setOnClickListener(this);

        TextView titleTxt = (TextView) findViewById(R.id.titleTxt);
        titleTxt.setText(contact.getName());
        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        nameTxt.setText(contact.getMaps().get("DHZZZNAME"));

        phone1=(TextView)findViewById(R.id.phone1);
        phone12=(TextView)findViewById(R.id.phone12);
        String phoneTxt0=contact.getMaps().get("DHZZZPHONE");
        if(phoneTxt0!=null&&phoneTxt0.contains(",")){
            phoneLayout12.setVisibility(View.VISIBLE);
            String[] phones=phoneTxt0.split(",");
            phone1.setText(phones[0]);
            phone12.setText(phones[1]);
        }else
            phone1.setText(contact.getMaps().get("DHZZZPHONE"));

        phone2=(TextView)findViewById(R.id.phone2);
        phone22=(TextView)findViewById(R.id.phone22);
        String phoneTxt1=contact.getMaps().get("DHZKZPHONE");
        if(phoneTxt1!=null&&phoneTxt1.contains(",")){
            phoneLayout22.setVisibility(View.VISIBLE);
            String[] phones=phoneTxt1.split(",");
            phone2.setText(phones[0]);
            phone22.setText(phones[1]);
        }else
            phone2.setText(contact.getMaps().get("DHZKZPHONE"));

        phone3=(TextView)findViewById(R.id.phone3);
        phone32=(TextView)findViewById(R.id.phone32);
        String phoneTxt2=contact.getMaps().get("DHZZBSPHONE");
        if(phoneTxt2!=null&&phoneTxt2.contains(",")){
            phoneLayout32.setVisibility(View.VISIBLE);
            String[] phones=phoneTxt2.split(",");
            phone3.setText(phones[0]);
            phone32.setText(phones[1]);
        }else
            phone3.setText(contact.getMaps().get("DHZZBSPHONE"));


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
                break;
            case R.id.phoneLayout2:
                phone=phone2.getText().toString();
                break;
            case R.id.phoneLayout22:
                phone=phone22.getText().toString();
                break;
            case R.id.phoneLayout3:
                phone=phone3.getText().toString();
                break;
            case R.id.phoneLayout32:
                phone=phone32.getText().toString();
                break;
        }
        if(phone.length()>0) {
            Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                    + phone));
            getContext().startActivity(call);
        }
    }
}
