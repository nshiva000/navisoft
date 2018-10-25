package com.gmail.hanivisushiva.maps.Models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("dcompany")
    @Expose
    private String dcompany;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("project")
    @Expose
    private String project;
    @SerializedName("phonemacaddress")
    @Expose
    private String phonemacaddress;
    @SerializedName("allowtobook")
    @Expose
    private String allowtobook;
    @SerializedName("viewbuyername")
    @Expose
    private String viewbuyername;
    @SerializedName("viewbuyerdesignation")
    @Expose
    private String viewbuyerdesignation;
    @SerializedName("bookedteamheadname")
    @Expose
    private String bookedteamheadname;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("d_status")
    @Expose
    private Boolean dStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDcompany() {
        return dcompany;
    }

    public void setDcompany(String dcompany) {
        this.dcompany = dcompany;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPhonemacaddress() {
        return phonemacaddress;
    }

    public void setPhonemacaddress(String phonemacaddress) {
        this.phonemacaddress = phonemacaddress;
    }

    public String getAllowtobook() {
        return allowtobook;
    }

    public void setAllowtobook(String allowtobook) {
        this.allowtobook = allowtobook;
    }

    public String getViewbuyername() {
        return viewbuyername;
    }

    public void setViewbuyername(String viewbuyername) {
        this.viewbuyername = viewbuyername;
    }

    public String getViewbuyerdesignation() {
        return viewbuyerdesignation;
    }

    public void setViewbuyerdesignation(String viewbuyerdesignation) {
        this.viewbuyerdesignation = viewbuyerdesignation;
    }

    public String getBookedteamheadname() {
        return bookedteamheadname;
    }

    public void setBookedteamheadname(String bookedteamheadname) {
        this.bookedteamheadname = bookedteamheadname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getDStatus() {
        return dStatus;
    }

    public void setDStatus(Boolean dStatus) {
        this.dStatus = dStatus;
    }

}