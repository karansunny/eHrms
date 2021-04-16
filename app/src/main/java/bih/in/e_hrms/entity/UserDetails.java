package bih.in.e_hrms.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

public class UserDetails implements Serializable {
    private static Class<UserDetails> USER_CLASS = UserDetails.class;

    private boolean isAuthenticated = true;

    private String _FName = "";
    private String EmpId = "";
    private String EmpCode = "";
    private String Name = "";
    private String FName = "";
    private String ActJoinDate = "";
    private String ActReleaveDate = "";
    private String Address = "";
    private String HQualification = "";
    private String ContactNo = "";
    private String EmailId = "";
    private String IsActive = "";
    private String Designation = "";
    private String DistName = "";
    private String UserID = "";
    private String UserName = "";
    private String Userrole = "";

    public UserDetails() {
    }

    @SuppressWarnings("deprecation")
    public UserDetails(SoapObject obj) {
        this.isAuthenticated = Boolean.parseBoolean(obj.getProperty("isAuthenticated").toString());

        this._FName = obj.getProperty("_FName").toString();
        this.EmpId = obj.getProperty("EmpId").toString();
        this.EmpCode = obj.getProperty("EmpCode").toString();
        this.Name = obj.getProperty("Name").toString();
        this.FName = obj.getProperty("FName").toString();
        this.ActJoinDate = obj.getProperty("ActJoinDate").toString();
        this.ActReleaveDate = obj.getProperty("ActReleaveDate").toString();
        this.Address = obj.getProperty("Address").toString();
        this.HQualification = obj.getProperty("HQualification").toString();
        this.ContactNo = obj.getProperty("ContactNo").toString();
        this.EmailId = obj.getProperty("EmailId").toString();
        this.IsActive = obj.getProperty("IsActive").toString();
        this.Designation = obj.getProperty("Designation").toString();
        this.DistName = obj.getProperty("DistName").toString();
        this.UserID = obj.getProperty("UserID").toString();
        this.UserName = obj.getProperty("UserName").toString();
        this.Userrole = obj.getProperty("Userrole").toString();
    }

    public static Class<UserDetails> getUserClass() {
        return USER_CLASS;
    }

    public static void setUserClass(Class<UserDetails> userClass) {
        USER_CLASS = userClass;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String get_FName() {
        return _FName;
    }

    public void set_FName(String _FName) {
        this._FName = _FName;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getEmpCode() {
        return EmpCode;
    }

    public void setEmpCode(String empCode) {
        EmpCode = empCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getActJoinDate() {
        return ActJoinDate;
    }

    public void setActJoinDate(String actJoinDate) {
        ActJoinDate = actJoinDate;
    }

    public String getActReleaveDate() {
        return ActReleaveDate;
    }

    public void setActReleaveDate(String actReleaveDate) {
        ActReleaveDate = actReleaveDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getHQualification() {
        return HQualification;
    }

    public void setHQualification(String HQualification) {
        this.HQualification = HQualification;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserrole() {
        return Userrole;
    }

    public void setUserrole(String userrole) {
        Userrole = userrole;
    }
}

