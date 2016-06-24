package com.sichuan.geologenvi.act;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.frg.SearchListFrg;
import com.sichuan.geologenvi.utils.ToastUtils;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SearchAct extends AppFrameAct {

    private EditText editSearch;
    private ImageView btnSearch;
    private String word="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        _setHeaderTitle(getIntent().getStringExtra("Title"));
        initView();
        openFragment(new SearchListFrg());
    }

    private void openFragment(Fragment frg){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frgFrame, frg);
        ft.commit();
    }

    private void initView() {
        btnSearch=(ImageView)findViewById(R.id.store_search_btn);
        btnSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

            }

        });
        editSearch=(EditText)findViewById(R.id.search_text);
        if(word.length()>0)
            editSearch.setHint(word);
        editSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editSearch.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if(actionId==EditorInfo.IME_ACTION_DONE||actionId==EditorInfo.IME_ACTION_UNSPECIFIED||actionId==EditorInfo.IME_ACTION_SEARCH){
                    if(!editSearch.getText().toString().trim().equals(""))
                    {
                        String keyword=editSearch.getText().toString();

                    }else
                        ToastUtils.displayTextShort(SearchAct.this, "请填写搜索关键字");
                }
                return true;
            }
        });
    }
}
