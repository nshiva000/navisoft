
package com.gmail.hanivisushiva.maps.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("plot_no")
    @Expose
    private String plotNo;
    @SerializedName("plot_status")
    @Expose
    private String plotStatus;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("facing")
    @Expose
    private String facing;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("dimension1")
    @Expose
    private String dimension1;
    @SerializedName("dimension2")
    @Expose
    private String dimension2;
    @SerializedName("dimension3")
    @Expose
    private String dimension3;
    @SerializedName("dimension4")
    @Expose
    private String dimension4;
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("customerdesi")
    @Expose
    private String customerdesi;
    @SerializedName("soldteam")
    @Expose
    private String soldteam;
    @SerializedName("bookedteam")
    @Expose
    private String bookedteam;

    private String string_points;
    @SerializedName("points")
    @Expose
    private List<String> points = null;

    public String getString_points() {
        return string_points;
    }

    public void setString_points(String string_points) {
        this.string_points = string_points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getPlotStatus() {
        return plotStatus;
    }

    public void setPlotStatus(String plotStatus) {
        this.plotStatus = plotStatus;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDimension1() {
        return dimension1;
    }

    public void setDimension1(String dimension1) {
        this.dimension1 = dimension1;
    }

    public String getDimension2() {
        return dimension2;
    }

    public void setDimension2(String dimension2) {
        this.dimension2 = dimension2;
    }

    public String getDimension3() {
        return dimension3;
    }

    public void setDimension3(String dimension3) {
        this.dimension3 = dimension3;
    }

    public String getDimension4() {
        return dimension4;
    }

    public void setDimension4(String dimension4) {
        this.dimension4 = dimension4;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerdesi() {
        return customerdesi;
    }

    public void setCustomerdesi(String customerdesi) {
        this.customerdesi = customerdesi;
    }

    public String getSoldteam() {
        return soldteam;
    }

    public void setSoldteam(String soldteam) {
        this.soldteam = soldteam;
    }

    public String getBookedteam() {
        return bookedteam;
    }

    public void setBookedteam(String bookedteam) {
        this.bookedteam = bookedteam;
    }

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }


}