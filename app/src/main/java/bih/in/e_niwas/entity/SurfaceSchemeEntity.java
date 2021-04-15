package bih.in.e_niwas.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class SurfaceSchemeEntity implements KvmSerializable, Serializable {

    public static Class<SurfaceSchemeEntity> SurfaceSchemeEntity_CLASS = SurfaceSchemeEntity.class;

    boolean isAuthenticated = true;

    String SCHEME_ID;
    private String SCHEME_NAME;
    private String TYPE_OF_SCHEME;
    private String District;
    private String Block;
    private String Panchayat;
    private String SOURCE_OF_WATER;
    private String Fund_Type;
    private String FINANCIAL_YEAR;
    private String Administrative_Approval_Amount;
    private String NIT_No;
    private String SC_ST_Majority_Village;

    private String Updated;
    private String CrossVerification = "";
    private String InspectionBy;
    private String SurveyorName;
    private String SurvyorPhone;
    private String InspectionDate;
    private String WorkStatus;
    private String WorkCompletionPer;
    private String WorkDone;
    private String ObservationCategory;
    private String Latitude;
    private String Longitude;
    private String Photo1;
    private String Photo2;
    private String Photo3;
    private String photo4;
    private String Comment1;
    private String Comment2;
    private String Comment3;
    private String Comment4;
    private String AppVersion;

    public SurfaceSchemeEntity(SoapObject res1) {
        this.isAuthenticated = Boolean.parseBoolean(res1.getProperty("IS_authenticate").toString());
        this.SCHEME_ID=res1.getProperty("SCHEME_ID").toString();
        this.SCHEME_NAME=res1.getProperty("SCHEME_NAME").toString();
        this.TYPE_OF_SCHEME=res1.getProperty("TYPE_OF_SCHEME").toString();
        this.District=res1.getProperty("District").toString();
        this.Block=res1.getProperty("Block").toString();
        this.Panchayat=res1.getProperty("Panchayat").toString();
        this.SOURCE_OF_WATER=res1.getProperty("SOURCE_OF_WATER").toString();
        this.Fund_Type=res1.getProperty("Fund_Type").toString();
        this.FINANCIAL_YEAR=res1.getProperty("FINANCIAL_YEAR").toString();
        this.Administrative_Approval_Amount=res1.getProperty("Administrative_Approval_Amount").toString();
        this.NIT_No=res1.getProperty("NIT_No").toString();
        this.SC_ST_Majority_Village=res1.getProperty("SC_ST_Majority_Village").toString();

    }

    public SurfaceSchemeEntity() {
    }

    public static Class<SurfaceSchemeEntity> getSurfaceSchemeEntity_CLASS() {
        return SurfaceSchemeEntity_CLASS;
    }

    public static void setSurfaceSchemeEntity_CLASS(Class<SurfaceSchemeEntity> surfaceSchemeEntity_CLASS) {
        SurfaceSchemeEntity_CLASS = surfaceSchemeEntity_CLASS;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }

    public String getCrossVerification() {
        return CrossVerification;
    }

    public void setCrossVerification(String crossVerification) {
        CrossVerification = crossVerification;
    }

    public String getInspectionBy() {
        return InspectionBy;
    }

    public void setInspectionBy(String inspectionBy) {
        InspectionBy = inspectionBy;
    }

    public String getSurveyorName() {
        return SurveyorName;
    }

    public void setSurveyorName(String surveyorName) {
        SurveyorName = surveyorName;
    }

    public String getSurvyorPhone() {
        return SurvyorPhone;
    }

    public void setSurvyorPhone(String survyorPhone) {
        SurvyorPhone = survyorPhone;
    }

    public String getInspectionDate() {
        return InspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        InspectionDate = inspectionDate;
    }

    public String getWorkStatus() {
        return WorkStatus;
    }

    public void setWorkStatus(String workStatus) {
        WorkStatus = workStatus;
    }

    public String getWorkCompletionPer() {
        return WorkCompletionPer;
    }

    public void setWorkCompletionPer(String workCompletionPer) {
        WorkCompletionPer = workCompletionPer;
    }

    public String getWorkDone() {
        return WorkDone;
    }

    public void setWorkDone(String workDone) {
        WorkDone = workDone;
    }

    public String getObservationCategory() {
        return ObservationCategory;
    }

    public void setObservationCategory(String observationCategory) {
        ObservationCategory = observationCategory;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPhoto1() {
        return Photo1;
    }

    public void setPhoto1(String photo1) {
        Photo1 = photo1;
    }

    public String getPhoto2() {
        return Photo2;
    }

    public void setPhoto2(String photo2) {
        Photo2 = photo2;
    }

    public String getPhoto3() {
        return Photo3;
    }

    public void setPhoto3(String photo3) {
        Photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getComment1() {
        return Comment1;
    }

    public void setComment1(String comment1) {
        Comment1 = comment1;
    }

    public String getComment2() {
        return Comment2;
    }

    public void setComment2(String comment2) {
        Comment2 = comment2;
    }

    public String getComment3() {
        return Comment3;
    }

    public void setComment3(String comment3) {
        Comment3 = comment3;
    }

    public String getComment4() {
        return Comment4;
    }

    public void setComment4(String comment4) {
        Comment4 = comment4;
    }

    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getSCHEME_ID() {
        return SCHEME_ID;
    }

    public void setSCHEME_ID(String SCHEME_ID) {
        this.SCHEME_ID = SCHEME_ID;
    }

    public String getSCHEME_NAME() {
        return SCHEME_NAME;
    }

    public void setSCHEME_NAME(String SCHEME_NAME) {
        this.SCHEME_NAME = SCHEME_NAME;
    }

    public String getTYPE_OF_SCHEME() {
        return TYPE_OF_SCHEME;
    }

    public void setTYPE_OF_SCHEME(String TYPE_OF_SCHEME) {
        this.TYPE_OF_SCHEME = TYPE_OF_SCHEME;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getBlock() {
        return Block;
    }

    public void setBlock(String block) {
        Block = block;
    }

    public String getPanchayat() {
        return Panchayat;
    }

    public void setPanchayat(String panchayat) {
        Panchayat = panchayat;
    }

    public String getSOURCE_OF_WATER() {
        return SOURCE_OF_WATER;
    }

    public void setSOURCE_OF_WATER(String SOURCE_OF_WATER) {
        this.SOURCE_OF_WATER = SOURCE_OF_WATER;
    }

    public String getFund_Type() {
        return Fund_Type;
    }

    public void setFund_Type(String fund_Type) {
        Fund_Type = fund_Type;
    }

    public String getFINANCIAL_YEAR() {
        return FINANCIAL_YEAR;
    }

    public void setFINANCIAL_YEAR(String FINANCIAL_YEAR) {
        this.FINANCIAL_YEAR = FINANCIAL_YEAR;
    }

    public String getAdministrative_Approval_Amount() {
        return Administrative_Approval_Amount;
    }

    public void setAdministrative_Approval_Amount(String administrative_Approval_Amount) {
        Administrative_Approval_Amount = administrative_Approval_Amount;
    }

    public String getNIT_No() {
        return NIT_No;
    }

    public void setNIT_No(String NIT_No) {
        this.NIT_No = NIT_No;
    }

    public String getSC_ST_Majority_Village() {
        return SC_ST_Majority_Village;
    }

    public void setSC_ST_Majority_Village(String SC_ST_Majority_Village) {
        this.SC_ST_Majority_Village = SC_ST_Majority_Village;
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }
}
