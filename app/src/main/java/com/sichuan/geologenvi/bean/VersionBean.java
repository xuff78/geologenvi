package com.sichuan.geologenvi.bean;

/**
 * Created by 可爱的蘑菇 on 2016/9/8.
 */
public class VersionBean {

    private int Version=0;
    private String VersionName="";
    private String CreateAt="";
    private String Size="";
    private String DownloadUrl="";

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String createAt) {
        CreateAt = createAt;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        DownloadUrl = downloadUrl;
    }
}
