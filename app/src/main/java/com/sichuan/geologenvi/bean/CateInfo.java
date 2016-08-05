package com.sichuan.geologenvi.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/5.
 */
public class CateInfo {
    private String catelog="";
    private ArrayList<PopupInfoItem> infos=new ArrayList<>();

    public String getCatelog() {
        return catelog;
    }

    public void setCatelog(String catelog) {
        this.catelog = catelog;
    }

    public ArrayList<PopupInfoItem> getInfos() {
        return infos;
    }

    public void setInfos(ArrayList<PopupInfoItem> infos) {
        this.infos = infos;
    }
}
