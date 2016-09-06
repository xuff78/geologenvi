package com.sichuan.geologenvi.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 可爱的蘑菇 on 2016/9/7.
 */
public class RainsBean implements Serializable{

    RainBean[] items=null;

    public RainBean[] getItems() {
        return items;
    }

    public void setItems(RainBean[] items) {
        this.items = items;
    }
}
