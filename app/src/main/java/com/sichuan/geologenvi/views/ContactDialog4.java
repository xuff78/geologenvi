package com.sichuan.geologenvi.views;

        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import com.sichuan.geologenvi.R;
        import com.sichuan.geologenvi.bean.MapBean;

public class ContactDialog4 extends Dialog implements View.OnClickListener{

    private MapBean contact;
    private String[] phones;
    private String otherInfoName;
    private TextView phone1, phone2, phone3,phone4;
    private int Type;

    public ContactDialog4(Context context, MapBean contact,int type) {
        super(context, R.style.dialog);
        this.contact=contact;
        this.Type=type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCanceledOnTouchOutside(true);
        setContentView(R.layout.contact_dialog4);
        initView();
    }

    private void initView() {


        View phoneLayout1=findViewById(R.id.phoneLayout1);
        phoneLayout1.setOnClickListener(this);
        View phoneLayout2=findViewById(R.id.phoneLayout2);
        phoneLayout2.setOnClickListener(this);

        View bangongLayout1=findViewById(R.id.bangongLayout1);
        bangongLayout1.setOnClickListener(this);
        View bangongLayout2=findViewById(R.id.bangongLayout2);
        bangongLayout2.setOnClickListener(this);


        //姓名
        TextView nameTxt = (TextView) findViewById(R.id.nameTxt);
        nameTxt.setText(contact.getMap().get("NAME"));

        //单位
        switch(Type) {
            case 30:
            case 31:
                ((TextView) findViewById(R.id.hintTxt1)).setText("单位");
                break;
            case 32:
                ((TextView) findViewById(R.id.hintTxt1)).setText("处室");
                break;
            case 33:
                ((TextView) findViewById(R.id.hintTxt1)).setText("科室");
                break;
        }
        ((TextView)findViewById(R.id.danweiTxt)).setText(contact.getMap().get("DANWEI"));



        //手机
        phone1=(TextView)findViewById(R.id.phone1);
        phone2=(TextView)findViewById(R.id.phone2);


        String phoneTxt0=contact.getMap().get("PHONE");
        if(phoneTxt0!=null&&phoneTxt0.contains(",")){
            phoneLayout2.setVisibility(View.VISIBLE);
            String[] phones=phoneTxt0.split(",");
            phone1.setText(phones[0]);
            phone2.setText(phones[1]);
        }else
            phone1.setText(phoneTxt0);


        //办公室号码

        phone3=(TextView)findViewById(R.id.bangongphone1);
        phone4=(TextView)findViewById(R.id.bangongphone2);

        String phoneTxt1=contact.getMap().get("BANGONGSHIHAOMA");
        if(phoneTxt1!=null&&phoneTxt1.contains(",")){
            bangongLayout2.setVisibility(View.VISIBLE);
            String[] bangongs=phoneTxt1.split(",");
            phone3.setText(bangongs[0]);
            phone4.setText(bangongs[1]);
        }else
            phone3.setText(phoneTxt1);



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
            case R.id.bangongLayout1:
                phone=phone3.getText().toString();
                if(phone.length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phone));
                    getContext().startActivity(call);
                }
                break;
            case R.id.bangongLayout2:
                phone=phone4.getText().toString();
                if(phone.length()>0) {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phone));
                    getContext().startActivity(call);
                }
                break;
        }
    }
}
