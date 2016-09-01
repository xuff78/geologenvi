package com.sichuan.geologenvi.bean;

/**
 * Created by 可爱的蘑菇 on 2016/9/2.
 */
public class RainHourItem {

    String dateTime="";
    String rain="";

    public RainHourItem(String dateTime, String rain) {
        this.dateTime = dateTime;
        this.rain = rain;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }
}
