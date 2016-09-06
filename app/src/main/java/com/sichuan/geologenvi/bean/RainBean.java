package com.sichuan.geologenvi.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RainBean implements Serializable{
    private String name="缺省";
    private String hour1="0";
    private String hour3="0";
    private String hour12="0";
    private String hour24="0";
    private String area="";

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour1() {
        return hour1;
    }

    public void setHour1(String hour1) {
        this.hour1 = hour1;
    }

    public String getHour3() {
        return hour3;
    }

    public void setHour3(String hour3) {
        this.hour3 = hour3;
    }

    public String getHour12() {
        return hour12;
    }

    public void setHour12(String hour12) {
        this.hour12 = hour12;
    }

    public String getHour24() {
        return hour24;
    }

    public void setHour24(String hour24) {
        this.hour24 = hour24;
    }

    public String getRainInfo(int hour){
        String raininfo="0";
        switch (hour){
            case 1:
                raininfo=getHour1();
                break;
            case 3:
                raininfo=getHour3();
                break;
            case 12:
                raininfo=getHour12();
                break;
            case 24:
                raininfo=getHour24();
                break;
        }
        return raininfo;
    }
}
