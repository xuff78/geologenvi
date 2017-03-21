
package com.sichuan.geologenvi.act.TXL;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.SectionIndexer;
        import android.widget.TextView;

        import com.sichuan.geologenvi.DataBase.SqlHandler;
        import com.sichuan.geologenvi.R;

        import com.sichuan.geologenvi.act.AppFrameAct;
        import com.sichuan.geologenvi.act.ItemDetailAct;
        import com.sichuan.geologenvi.adapter.MenuListAdapter;
        import com.sichuan.geologenvi.bean.MapBean;


        import java.util.ArrayList;
        import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
public class TXLListAct   extends AppFrameAct implements SectionIndexer {
    TextView txtcount;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<Map<String, String>> datalist=new ArrayList<>();

    private SqlHandler handler;
    private String tableName="";
    private String title="";
    private int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_list);

        title = getIntent().getStringExtra("Title");
        _setHeaderTitle(title);

        type=getIntent().getIntExtra("Type", 0);
        String t="";
        switch(type){
            case 0:
                tableName="SL_TXL_SHENG";
                t="name";
                break;
            case 1:
                tableName="SL_TXL_SHI";
                t="name";
                break;
            case 2:
                tableName="SL_TXL_SHIGUOTUJU";
                t="name";
                break;
            case 3:
                tableName="SL_TXL_SHIDHZ";
                t="name";
                break;
            case 4:
                tableName="SL_TXL_SHILD";
                t="name";
                break;
            case 5:
                tableName="SL_TXL_DZZHFZPQFG";
                t="name";
                break;
        }
        initView();

        handler = new SqlHandler(this);
        datalist=handler.getQueryResult(tableName, "");
        ArrayList<String> list = new ArrayList<>();

        for (Map<String, String> info : datalist) {
            list.add(info.get(t));
        }
        recyclerView.setAdapter(new MenuListAdapter(this, list, listener));

    }


    private void initView() {

        txtcount=(TextView)findViewById(R.id.count);
        txtcount.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=getIntent();
            if(tableName.equals("SL_KS_XX"))
                i.setClass(TXLListAct.this, ItemDetailAct.class);
            else if(tableName.equals("SL_DZYJBH")) {
                i.setClass(TXLListAct.this, ItemDetailAct.class);
            }
            else if(tableName.equals("SL_TBLJING"))
                i.setClass(TXLListAct.this, ItemDetailAct.class);
            else if(tableName.equals("SL_SOILSTASION"))
                i.setClass(TXLListAct.this, ItemDetailAct.class);
            MapBean mapBean=new MapBean();
            int tag=(int)view.getTag();
            mapBean.setMap(datalist.get(tag));
            i.putExtra("InfoMap",mapBean);
            i.putExtra("TableName", tableName);
            startActivity(i);
        }
    };

    @Override
    public Object[] getSections() {
        return null;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return 0;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {

        return -1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

    }
}
