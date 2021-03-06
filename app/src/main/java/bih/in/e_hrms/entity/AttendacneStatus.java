package bih.in.e_hrms.entity;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;

public class AttendacneStatus implements Serializable {
    private static Class<AttendacneStatus> AttendacneStatus_CLASS = AttendacneStatus.class;

    private String EmpId = "";
    private String IsIn = "";
    private String IsOut = "";
    private String UserID = "";
    private String SiteId = "";
    private String SiteName = "";

    public AttendacneStatus() {
    }

    @SuppressWarnings("deprecation")
    public AttendacneStatus(SoapObject obj) {

        this.EmpId = obj.getProperty("EmpId").toString();
        this.IsIn = obj.getProperty("IsIn").toString();
        this.IsOut = obj.getProperty("IsOut").toString();
        this.UserID = obj.getProperty("UserID").toString();
        this.SiteId = obj.getProperty("SiteId").toString();
        this.SiteName = obj.getProperty("SiteName").toString();
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getIsIn() {
        return IsIn;
    }

    public void setIsIn(String isIn) {
        IsIn = isIn;
    }

    public String getIsOut() {
        return IsOut;
    }

    public void setIsOut(String isOut) {
        IsOut = isOut;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getSiteId() {
        return SiteId;
    }

    public void setSiteId(String siteId) {
        SiteId = siteId;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }
}

