package com.gmail.hanivisushiva.maps.Models.dCompany;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DDatum {

    @SerializedName("did")
    @Expose
    private String did;
    @SerializedName("dname")
    @Expose
    private String dname;
    @SerializedName("ddescription")
    @Expose
    private String ddescription;
    @SerializedName("dtheme")
    @Expose
    private String dtheme;
    @SerializedName("dfile")
    @Expose
    private String dfile;
    @SerializedName("dstatus")
    @Expose
    private String dstatus;
    @SerializedName("ddate")
    @Expose
    private String ddate;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDdescription() {
        return ddescription;
    }

    public void setDdescription(String ddescription) {
        this.ddescription = ddescription;
    }

    public String getDtheme() {
        return dtheme;
    }

    public void setDtheme(String dtheme) {
        this.dtheme = dtheme;
    }

    public String getDfile() {
        return dfile;
    }

    public void setDfile(String dfile) {
        this.dfile = dfile;
    }

    public String getDstatus() {
        return dstatus;
    }

    public void setDstatus(String dstatus) {
        this.dstatus = dstatus;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

}