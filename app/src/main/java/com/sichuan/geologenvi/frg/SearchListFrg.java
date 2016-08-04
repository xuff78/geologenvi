package com.sichuan.geologenvi.frg;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sichuan.geologenvi.R;
import com.sichuan.geologenvi.adapter.MenuListAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SearchListFrg extends BaseFragment{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> list=new ArrayList<>();
        recyclerView.setAdapter(new MenuListAdapter(getActivity(), list, null));

        return view;
    }
}
