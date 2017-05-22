package com.sichuan.geologenvi.bean;

import java.io.Serializable;

/**
 * Created by tanqq on 2017/4/15.
 */
public class ZBAP  implements Serializable {
    private String name = "";
    private String content = "";

    public ZBAP(String name, String content) {
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
