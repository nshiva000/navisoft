package com.gmail.hanivisushiva.maps.Models.Project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectsDatum {

    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("pgoogle")
    @Expose
    private String pgoogle;
    @SerializedName("plogo")
    @Expose
    private String plogo;
    @SerializedName("plocation")
    @Expose
    private String plocation;
    @SerializedName("pstatus")
    @Expose
    private String pstatus;
    @SerializedName("dcompany")
    @Expose
    private String dcompany;
    @SerializedName("pplots")
    @Expose
    private String pplots;
    @SerializedName("parea")
    @Expose
    private String parea;
    @SerializedName("pdate")
    @Expose
    private String pdate;
    @SerializedName("pdescription")
    @Expose
    private String pdescription;
    @SerializedName("pcenter")
    @Expose
    private String pcenter;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("sold")
    @Expose
    private String sold;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("booked")
    @Expose
    private String booked;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPgoogle() {
        return pgoogle;
    }

    public void setPgoogle(String pgoogle) {
        this.pgoogle = pgoogle;
    }

    public String getPlogo() {
        return plogo;
    }

    public void setPlogo(String plogo) {
        this.plogo = plogo;
    }

    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getDcompany() {
        return dcompany;
    }

    public void setDcompany(String dcompany) {
        this.dcompany = dcompany;
    }

    public String getPplots() {
        return pplots;
    }

    public void setPplots(String pplots) {
        this.pplots = pplots;
    }

    public String getParea() {
        return parea;
    }

    public void setParea(String parea) {
        this.parea = parea;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getPcenter() {
        return pcenter;
    }

    public void setPcenter(String pcenter) {
        this.pcenter = pcenter;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

}