package com.gmail.hanivisushiva.maps.Models.Project;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("projects_data")
    @Expose
    private List<ProjectsDatum> projectsData = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ProjectsDatum> getProjectsData() {
        return projectsData;
    }

    public void setProjectsData(List<ProjectsDatum> projectsData) {
        this.projectsData = projectsData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}