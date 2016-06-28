package com.sichuan.geologenvi.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MapBean implements Serializable {
    private Map<String, String> map=null;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
