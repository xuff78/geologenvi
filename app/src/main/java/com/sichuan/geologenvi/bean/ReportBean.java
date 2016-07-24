package com.sichuan.geologenvi.bean;

import java.io.Serializable;

/**
 * Created by 可爱的蘑菇 on 2016/7/24.
 */
public class ReportBean implements Serializable{

    private String ID="";
    private String KS_ID="";
    private String DATETIME="";
    private String MS="";
    private String TYPE="";
    private String PATH="";

    public static final String Name="ReportBean";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKS_ID() {
        return KS_ID;
    }

    public void setKS_ID(String KS_ID) {
        this.KS_ID = KS_ID;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }
}
