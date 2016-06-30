package com.sichuan.geologenvi.frg;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.ActivityInfoAdapter;
import com.sichuan.geologenvi.adapter.MenuListAdapter;
import com.sichuan.geologenvi.bean.MapBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/29.
 */
public class FormInfoFrg extends BaseFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_infolist, container, false);

        MapBean mapBean= (MapBean) getArguments().getSerializable("InfoMap");
        ListView list=(ListView)view.findViewById(R.id.infoList);

        if(mapBean!=null)
            list.setAdapter(new ActivityInfoAdapter(getActivity(),mapBean.getMap(), getArguments().getString("TableName")));

        return view;
    }
}
