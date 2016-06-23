package com.sichuan.geologenvi.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/14.
 */
public class PopupInfoItem implements Serializable{

    private String name="";
    private String content="";

    public PopupInfoItem(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
