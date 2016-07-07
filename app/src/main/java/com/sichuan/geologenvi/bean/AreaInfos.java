package com.sichuan.geologenvi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 可爱的蘑菇 on 2016/7/7.
 */
public class AreaInfos implements Serializable{

    public final static String Name="AreaInfos";

    private LinkedList<AreaInfo> infos=new LinkedList<>();

    public LinkedList<AreaInfo> getInfos() {
        return infos;
    }

    public void setInfos(LinkedList<AreaInfo> infos) {
        this.infos = infos;
    }
}
