package bih.in.e_niwas.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

public class UserDetails implements KvmSerializable, Serializable {
    private static Class<UserDetails> USER_CLASS = UserDetails.class;

    private boolean isAuthenticated = true;

    private String Password = "";
    private String UserID = "";

    private String LastVisitedOn = "";
    private String MobileNo = "";
    private String Address = "";
    private String Email = "";
    private String DistrictCode = "";
    private String DistName = "";
    private String BlockCode = "";
    private String BlockName = "";
    private String PanchayatName = "";
    private String PanchayatCode = "";
    private String Degignation = "";
    private String Userrole = "";
    private String UserroleId = "";
    private String Name = "";


    public UserDetails() {
    }

    @SuppressWarnings("deprecation")
    public UserDetails(SoapObject obj) {
        this.setAuthenticated(Boolean.parseBoolean(obj.getProperty("IS_authenticate").toString()));
        this.setUserID(obj.getProperty("ID").toString());
        //this.setPassword(obj.getProperty("Password").toString());

        this.setName(obj.getProperty("Name").toString());
        this.setMobileNo(obj.getProperty("Phone").toString());
        this.setEmail(obj.getProperty("Email").toString());
//        this.setDistrictCode(obj.getProperty("DistrictCode").toString());
//        this.setDistName(obj.getProperty("DistName").toString());
//        this.setBlockCode(obj.getProperty("BlockCode").toString());
//        this.setBlockName(obj.getProperty("BlockName").toString());
//        this.setPanchayatCode(obj.getProperty("PanchayatCode").toString());
//        this.setPanchayatName(obj.getProperty("PanchayatName").toString());
//        //this.setDegignation(obj.getProperty("Degignation").toString());
//        this.setUserrole(obj.getProperty("Userrole").toString());
//        this.setName(obj.getProperty("Name").toString());
    }

    public static Class<UserDetails> getUserClass() {
        return USER_CLASS;
    }

    public static void setUserClass(Class<UserDetails> userClass) {
        USER_CLASS = userClass;
    }


    @Override
    public int getPropertyCount() {
        // TODO Auto-generated method stub
        return 8;
    }

    @Override
    public Object getProperty(int index) {
        Object object = null;
        switch (index) {
            case 0: {
                object = this.isAuthenticated;
                break;
            }
            case 1: {
                object = this.Password;
                break;
            }
            case 2: {
                object = this.UserID;
                break;
            }
            case 3: {
                object = this.LastVisitedOn;
                break;
            }
            case 4: {
                object = this.MobileNo;
                break;
            }

            case 5: {
                object = this.Address;
                break;
            }

            case 6: {
                object = this.Email;
                break;

            }
            case 7: {
                object = this.DistrictCode;
                break;
            }
            case 8: {
                object = this.DistName;
                break;
            }
            case 9: {
                object = this.BlockCode;
                break;
            }
            case 10: {
                object = this.BlockName;
                break;
            }

            case 11: {
                object = this.Degignation;
                break;
            }

            case 12: {
                object = this.Userrole;
                break;

            }
            case 13: {
                object = this.Name;
                break;

            }
        }
        return object;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable arg1,
                                PropertyInfo propertyInfo) {
        switch (index) {
            case 0: {
                propertyInfo.name = "isAuthenticated";
                propertyInfo.type = PropertyInfo.BOOLEAN_CLASS;
                break;
            }
            case 1: {
                propertyInfo.name = "Password";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 2: {
                propertyInfo.name = "UserID";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 3: {
                propertyInfo.name = "LastVisitedOn";
                propertyInfo.type = Integer.class;
                break;
            }
            case 4: {
                propertyInfo.name = "MobileNo";
                propertyInfo.type = Date.class;
                break;
            }

            case 5: {
                propertyInfo.name = "Address";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 6: {
                propertyInfo.name = "Email";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 7: {
                propertyInfo.name = "DistrictCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 8: {
                propertyInfo.name = "DistName";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 9: {
                propertyInfo.name = "BlockCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 10: {
                propertyInfo.name = "BlockName";
                propertyInfo.type = Integer.class;
                break;
            }
            case 11: {
                propertyInfo.name = "Degignation";
                propertyInfo.type = Date.class;
                break;
            }

            case 12: {
                propertyInfo.name = "Userrole";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 13: {
                propertyInfo.name = "Name";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
        }
    }

    @Override
    public void setProperty(int index, Object obj) {
        switch (index) {
            case 0: {
                this.isAuthenticated = Boolean.parseBoolean(obj.toString());
                break;
            }
            case 1: {
                this.Password = obj.toString();
                break;
            }
            case 2: {
                this.UserID = obj.toString();
                break;
            }
            case 3: {
                this.LastVisitedOn = obj.toString();
                break;
            }
            case 4: {
                this.MobileNo = obj.toString();
                break;
            }

            case 5: {
                this.Address = obj.toString();
                break;
            }

            case 6: {
                this.Email = obj.toString();
                break;
            }
            case 7: {
                this.DistrictCode = obj.toString();
                break;
            }
            case 8: {
                this.DistName = obj.toString();
                break;
            }
            case 9: {
                this.BlockCode = obj.toString();
                break;
            }
            case 10: {
                this.BlockName = obj.toString();
                break;
            }

            case 11: {
                this.Degignation = obj.toString();
                break;
            }

            case 12: {
                this.Userrole = obj.toString();
                break;
            }

            case 13: {
                this.Name = obj.toString();
                break;
            }
        }
    }

    public String getUserroleId() {
        return UserroleId;
    }

    public void setUserroleId(String userroleId) {
        UserroleId = userroleId;
    }

    public String getPanchayatName() {
        return PanchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        PanchayatName = panchayatName;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getLastVisitedOn() {
        return LastVisitedOn;
    }

    public void setLastVisitedOn(String lastVisitedOn) {
        LastVisitedOn = lastVisitedOn;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getDegignation() {
        return Degignation;
    }

    public void setDegignation(String degignation) {
        Degignation = degignation;
    }

    public String getUserrole() {
        return Userrole;
    }

    public void setUserrole(String userrole) {
        Userrole = userrole;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

