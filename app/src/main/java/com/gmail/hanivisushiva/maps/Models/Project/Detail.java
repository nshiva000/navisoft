package com.gmail.hanivisushiva.maps.Models.Project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

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
    @SerializedName("pcompany")
    @Expose
    private String pcompany;
    @SerializedName("dcompany")
    @Expose
    private String dcompany;
    @SerializedName("plots")
    @Expose
    private String plots;
    @SerializedName("plottedarea")
    @Expose
    private String plottedarea;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("projectdesc")
    @Expose
    private Object projectdesc;

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

    public String getPcompany() {
        return pcompany;
    }

    public void setPcompany(String pcompany) {
        this.pcompany = pcompany;
    }

    public String getDcompany() {
        return dcompany;
    }

    public void setDcompany(String dcompany) {
        this.dcompany = dcompany;
    }

    public String getPlots() {
        return plots;
    }

    public void setPlots(String plots) {
        this.plots = plots;
    }

    public String getPlottedarea() {
        return plottedarea;
    }

    public void setPlottedarea(String plottedarea) {
        this.plottedarea = plottedarea;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getProjectdesc() {
        return projectdesc;
    }

    public void setProjectdesc(Object projectdesc) {
        this.projectdesc = projectdesc;
    }

}