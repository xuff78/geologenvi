package com.sichuan.geologenvi.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.frg.SearchListFrg;
import com.sichuan.geologenvi.frg.SearchListFrg2;
import com.sichuan.geologenvi.utils.ToastUtils;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SearchAct extends AppFrameAct {

    private String title;
    private String type;
    private EditText editSearch;
    private ImageView btnSearch;
    private String word="";
    private SearchListFrg flfg=new SearchListFrg();
   // private SearchListFrg frg=new SearchListFrg();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        title=getIntent().getStringExtra("Title");
        _setHeaderTitle(title);
        type=getIntent().getStringExtra("Type");
        initView();
        Bundle args = new Bundle();

        if(title.equals("基础资料")){
            openFragment(flfg);
        }
        else{
            SearchListFrg2 frg=new  SearchListFrg2();
            args.putString("type",type );
            frg.setArguments(args);
            openFragment(frg);
        }
    }

    private void openFragment(Fragment frg){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frgFrame, frg);
        ft.commit();
    }

    private void initView() {
        btnSearch=(ImageView)findViewById(R.id.store_search_btn);
        btnSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                editSearch.setText("");
                getWords();
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
                        getWords();
                    }else
                        ToastUtils.displayTextShort(SearchAct.this, "请填写搜索关键字");
                }
                return true;
            }
        });
    }

    private void getWords(){
        String keyword=editSearch.getText().toString().trim();
        if (title.equals("基础资料")) {
            flfg.searchFile(keyword);
        }
    }
}
