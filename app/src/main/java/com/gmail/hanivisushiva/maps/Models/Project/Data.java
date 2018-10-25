package com.gmail.hanivisushiva.maps.Models.Project;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("Plots")
    @Expose
    private String plots;
    @SerializedName("Alloted")
    @Expose
    private String alloted;
    @SerializedName("Available")
    @Expose
    private String available;
    @SerializedName("Plot Area")
    @Expose
    private String plotArea;
    @SerializedName("Plot Area Sold")
    @Expose
    private Object plotAreaSold;
    @SerializedName("Area Available")
    @Expose
    private Integer areaAvailable;

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getPlots() {
        return plots;
    }

    public void setPlots(String plots) {
        this.plots = plots;
    }

    public String getAlloted() {
        return alloted;
    }

    public void setAlloted(String alloted) {
        this.alloted = alloted;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(String plotArea) {
        this.plotArea = plotArea;
    }

    public Object getPlotAreaSold() {
        return plotAreaSold;
    }

    public void setPlotAreaSold(Object plotAreaSold) {
        this.plotAreaSold = plotAreaSold;
    }

    public Integer getAreaAvailable() {
        return areaAvailable;
    }

    public void setAreaAvailable(Integer areaAvailable) {
        this.areaAvailable = areaAvailable;
    }

}