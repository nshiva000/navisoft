package com.gmail.hanivisushiva.maps.Models;

import java.util.ArrayList;

public class DatabaseModel {
    private int id;
    private String plot_no;
    private String type;
    private String plot_status;
    private String size;
    private String facing;
    private String points;

    public int getId() {
        return id;
    }



    public DatabaseModel() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public DatabaseModel(int id, String plot_no, String type, String plot_status, String size, String facing, String points) {
        this.id = id;
        this.plot_no = plot_no;
        this.type = type;
        this.plot_status = plot_status;
        this.size = size;
        this.facing = facing;
        this.points = points;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public void setId(int id) {


        this.id = id;
    }

    public String getPlot_no() {
        return plot_no;
    }

    public void setPlot_no(String plot_no) {
        this.plot_no = plot_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlot_status() {
        return plot_status;
    }

    public void setPlot_status(String plot_status) {
        this.plot_status = plot_status;
    }


    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
