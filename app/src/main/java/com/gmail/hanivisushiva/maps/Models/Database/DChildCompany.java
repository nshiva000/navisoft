package com.gmail.hanivisushiva.maps.Models.Database;

public class DChildCompany {


    private String CID;
    private String NAME;
    private String CDESCRIPTION;
    private String CLOGO;
    private String CTHEME;
    private String CSTATUS;
    private String CDATE;


    public DChildCompany(String CID, String NAME, String CDESCRIPTION, String CLOGO, String CTHEME, String CSTATUS, String CDATE) {
        this.CID = CID;
        this.NAME = NAME;
        this.CDESCRIPTION = CDESCRIPTION;
        this.CLOGO = CLOGO;
        this.CTHEME = CTHEME;
        this.CSTATUS = CSTATUS;
        this.CDATE = CDATE;
    }

    @Override
    public String toString() {
        return "DChildCompany{" +
                "CID='" + CID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", CDESCRIPTION='" + CDESCRIPTION + '\'' +
                ", CLOGO='" + CLOGO + '\'' +
                ", CTHEME='" + CTHEME + '\'' +
                ", CSTATUS='" + CSTATUS + '\'' +
                ", CDATE='" + CDATE + '\'' +
                '}';
    }

    public String getCID() {
        return CID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getCDESCRIPTION() {
        return CDESCRIPTION;
    }

    public String getCLOGO() {
        return CLOGO;
    }

    public String getCTHEME() {
        return CTHEME;
    }

    public String getCSTATUS() {
        return CSTATUS;
    }

    public String getCDATE() {
        return CDATE;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setCDESCRIPTION(String CDESCRIPTION) {
        this.CDESCRIPTION = CDESCRIPTION;
    }

    public void setCLOGO(String CLOGO) {
        this.CLOGO = CLOGO;
    }

    public void setCTHEME(String CTHEME) {
        this.CTHEME = CTHEME;
    }

    public void setCSTATUS(String CSTATUS) {
        this.CSTATUS = CSTATUS;
    }

    public void setCDATE(String CDATE) {
        this.CDATE = CDATE;
    }
}