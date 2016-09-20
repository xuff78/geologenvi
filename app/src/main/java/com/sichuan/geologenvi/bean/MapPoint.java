package com.sichuan.geologenvi.bean;

import com.esri.core.geometry.Point;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MapPoint {

    private String desc="";
    private Point p=null;
    private long id=-1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }
}
