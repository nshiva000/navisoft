package com.gmail.hanivisushiva.maps.Models;

import java.util.ArrayList;

public class DatabaseModel {
    private int id;
    private String pid;
    private String plot_no;
    private String plot_status;
    private String size;
    private String facing;
    private String status;
    private String dim_a;
    private String dim_b;
    private String dim_c;
    private String dim_d;
    private String customer_name;
    private String customer_desig;
    private String sold_team;
    private String booked_team;
    private String points;


    public DatabaseModel(int id, String pid, String plot_no, String plot_status, String size, String facing, String status, String dim_a, String dim_b, String dim_c, String dim_d, String customer_name, String customer_desig, String sold_team, String booked_team, String points) {
        this.id = id;
        this.pid = pid;
        this.plot_no = plot_no;
        this.plot_status = plot_status;
        this.size = size;
        this.facing = facing;
        this.status = status;
        this.dim_a = dim_a;
        this.dim_b = dim_b;
        this.dim_c = dim_c;
        this.dim_d = dim_d;
        this.customer_name = customer_name;
        this.customer_desig = customer_desig;
        this.sold_team = sold_team;
        this.booked_team = booked_team;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public String getPid() {
        return pid;
    }

    public String getPlot_no() {
        return plot_no;
    }

    public String getPlot_status() {
        return plot_status;
    }

    public String getSize() {
        return size;
    }

    public String getFacing() {
        return facing;
    }

    public String getStatus() {
        return status;
    }

    public String getDim_a() {
        return dim_a;
    }

    public String getDim_b() {
        return dim_b;
    }

    public String getDim_c() {
        return dim_c;
    }

    public String getDim_d() {
        return dim_d;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_desig() {
        return customer_desig;
    }

    public String getSold_team() {
        return sold_team;
    }

    public String getBooked_team() {
        return booked_team;
    }

    public String getPoints() {
        return points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPlot_no(String plot_no) {
        this.plot_no = plot_no;
    }

    public void setPlot_status(String plot_status) {
        this.plot_status = plot_status;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDim_a(String dim_a) {
        this.dim_a = dim_a;
    }

    public void setDim_b(String dim_b) {
        this.dim_b = dim_b;
    }

    public void setDim_c(String dim_c) {
        this.dim_c = dim_c;
    }

    public void setDim_d(String dim_d) {
        this.dim_d = dim_d;
    }

    @Override
    public String toString() {
        return "DatabaseModel{" +
                "id=" + id +
                ", pid='" + pid + '\'' +
                ", plot_no='" + plot_no + '\'' +
                ", plot_status='" + plot_status + '\'' +
                ", size='" + size + '\'' +
                ", facing='" + facing + '\'' +
                ", status='" + status + '\'' +
                ", dim_a='" + dim_a + '\'' +
                ", dim_b='" + dim_b + '\'' +
                ", dim_c='" + dim_c + '\'' +
                ", dim_d='" + dim_d + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_desig='" + customer_desig + '\'' +
                ", sold_team='" + sold_team + '\'' +
                ", booked_team='" + booked_team + '\'' +
                ", points='" + points + '\'' +
                '}';
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setCustomer_desig(String customer_desig) {
        this.customer_desig = customer_desig;
    }

    public void setSold_team(String sold_team) {
        this.sold_team = sold_team;
    }

    public void setBooked_team(String booked_team) {
        this.booked_team = booked_team;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}