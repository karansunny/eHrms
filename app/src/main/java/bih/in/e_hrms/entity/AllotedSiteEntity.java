package bih.in.e_hrms.entity;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;

public class AllotedSiteEntity implements Serializable {
    public static Class<AllotedSiteEntity> AllotedSiteEntity_CLASS = AllotedSiteEntity.class;

    private String IsApproved = "";
    private String IsSiteActive = "";
    private String MoveId = "";
    private String EmpId = "";
    private String Name = "";
    private String SiteId = "";
    private String SiteName = "";
    private String EquId = "";
    private String EquipmentName = "";



    public AllotedSiteEntity() {
    }

    @SuppressWarnings("deprecation")
    public AllotedSiteEntity(SoapObject obj) {

        this.IsApproved = obj.getProperty("IsApproved").toString();
        this.IsSiteActive = obj.getProperty("IsSiteActive").toString();
        this.MoveId = obj.getProperty("MoveId").toString();
        this.EmpId = obj.getProperty("EmpId").toString();
        this.Name = obj.getProperty("Name").toString();
        this.SiteId = obj.getProperty("SiteId").toString();
        this.SiteName = obj.getProperty("SiteName").toString();
        this.EquId = obj.getProperty("EquId").toString();
        this.EquipmentName = obj.getProperty("EquipmentName").toString();
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(String isApproved) {
        IsApproved = isApproved;
    }

    public String getIsSiteActive() {
        return IsSiteActive;
    }

    public void setIsSiteActive(String isSiteActive) {
        IsSiteActive = isSiteActive;
    }

    public String getMoveId() {
        return MoveId;
    }

    public void setMoveId(String moveId) {
        MoveId = moveId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getEquId() {
        return EquId;
    }

    public void setEquId(String equId) {
        EquId = equId;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }
}

