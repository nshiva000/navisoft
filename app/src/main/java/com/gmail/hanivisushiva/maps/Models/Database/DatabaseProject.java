package com.gmail.hanivisushiva.maps.Models.Database;

public class DatabaseProject {

    private String ID;
    private String PID;
    private String NAME;
    private String PCENTER;
    private String PGOOGLE;
    private String PLOGO;
    private String PLOCATION;
    private String PSTATUS;
    private String PCOMPANY;
    private String PLOTS;

    private String DATE;
    private String PROJECT_DES;
    private String T_PLOTS;
    private String T_ALLOTED;
    private String T_AVAILABLE;
    private String T_PLOT_AREA;
    private String T_PLOT_SOLD;


    public DatabaseProject(String ID, String PID, String NAME, String PCENTER, String PGOOGLE, String PLOGO, String PLOCATION, String PSTATUS, String PCOMPANY, String PLOTS, String DATE, String PROJECT_DES, String t_PLOTS, String t_ALLOTED, String t_AVAILABLE, String t_PLOT_AREA, String t_PLOT_SOLD) {
        this.ID = ID;
        this.PID = PID;
        this.NAME = NAME;
        this.PCENTER = PCENTER;
        this.PGOOGLE = PGOOGLE;
        this.PLOGO = PLOGO;
        this.PLOCATION = PLOCATION;
        this.PSTATUS = PSTATUS;
        this.PCOMPANY = PCOMPANY;
        this.PLOTS = PLOTS;
        this.DATE = DATE;
        this.PROJECT_DES = PROJECT_DES;
        T_PLOTS = t_PLOTS;
        T_ALLOTED = t_ALLOTED;
        T_AVAILABLE = t_AVAILABLE;
        T_PLOT_AREA = t_PLOT_AREA;
        T_PLOT_SOLD = t_PLOT_SOLD;
    }

    public String getT_ALLOTED() {
        return T_ALLOTED;
    }

    public void setT_ALLOTED(String t_ALLOTED) {
        T_ALLOTED = t_ALLOTED;
    }


    public void setPCENTER(String PCENTER) {

        this.PCENTER = PCENTER;
    }

    public String getPCENTER() {
        return PCENTER;

    }

    public String getPID() {
        return PID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getPGOOGLE() {
        return PGOOGLE;
    }

    public String getPLOGO() {
        return PLOGO;
    }

    public String getPLOCATION() {
        return PLOCATION;
    }

    public String getPSTATUS() {
        return PSTATUS;
    }

    public String getPCOMPANY() {
        return PCOMPANY;
    }

    public String getPLOTS() {
        return PLOTS;
    }

    public String getPLOTTED_AREA() {
        return T_ALLOTED;
    }

    public String getDATE() {
        return DATE;
    }

    public String getPROJECT_DES() {
        return PROJECT_DES;
    }

    public String getT_PLOTS() {
        return T_PLOTS;
    }

    public String getT_AVAILABLE() {
        return T_AVAILABLE;
    }

    public String getT_PLOT_AREA() {
        return T_PLOT_AREA;
    }

    public String getT_PLOT_SOLD() {
        return T_PLOT_SOLD;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setPGOOGLE(String PGOOGLE) {
        this.PGOOGLE = PGOOGLE;
    }

    public void setPLOGO(String PLOGO) {
        this.PLOGO = PLOGO;
    }

    public void setPLOCATION(String PLOCATION) {
        this.PLOCATION = PLOCATION;
    }

    public void setPSTATUS(String PSTATUS) {
        this.PSTATUS = PSTATUS;
    }

    public void setPCOMPANY(String PCOMPANY) {
        this.PCOMPANY = PCOMPANY;
    }

    public void setPLOTS(String PLOTS) {
        this.PLOTS = PLOTS;
    }

    public void setPLOTTED_AREA(String T_ALLOTED) {
        this.T_ALLOTED = T_ALLOTED;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public void setPROJECT_DES(String PROJECT_DES) {
        this.PROJECT_DES = PROJECT_DES;
    }

    public void setT_PLOTS(String t_PLOTS) {
        T_PLOTS = t_PLOTS;
    }

    public void setT_AVAILABLE(String t_AVAILABLE) {
        T_AVAILABLE = t_AVAILABLE;
    }

    public void setT_PLOT_AREA(String t_PLOT_AREA) {
        T_PLOT_AREA = t_PLOT_AREA;
    }

    public void setT_PLOT_SOLD(String t_PLOT_SOLD) {
        T_PLOT_SOLD = t_PLOT_SOLD;
    }

    @Override
    public String toString() {
        return "DatabaseProject{" +
                "PID='" + PID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", PCENTER='" + PCENTER + '\'' +
                ", PGOOGLE='" + PGOOGLE + '\'' +
                ", PLOGO='" + PLOGO + '\'' +
                ", PLOCATION='" + PLOCATION + '\'' +
                ", PSTATUS='" + PSTATUS + '\'' +
                ", PCOMPANY='" + PCOMPANY + '\'' +
                ", PLOTS='" + PLOTS + '\'' +
                ", DATE='" + DATE + '\'' +
                ", PROJECT_DES='" + PROJECT_DES + '\'' +
                ", T_PLOTS='" + T_PLOTS + '\'' +
                ", T_ALLOTED='" + T_ALLOTED + '\'' +
                ", T_AVAILABLE='" + T_AVAILABLE + '\'' +
                ", T_PLOT_AREA='" + T_PLOT_AREA + '\'' +
                ", T_PLOT_SOLD='" + T_PLOT_SOLD + '\'' +
                '}';
    }
}
