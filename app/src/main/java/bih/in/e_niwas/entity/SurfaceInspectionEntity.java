package bih.in.e_niwas.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class SurfaceInspectionEntity implements KvmSerializable, Serializable {

    public static Class<SurfaceInspectionEntity> SurfaceInspectionEntity_CLASS = SurfaceInspectionEntity.class;

    boolean isAuthenticated = true;

    private String SCHEME_ID;
    private String INSPECTION_ID;
    private String INSPECTION_BY_NAME;
    private String INSPECTION_BY_Phone;
    private String DESIGNATION;
    private String OTHER_DETAIL;
    private String INSPECTION_DATE;
    private String INSPECTION_DATE_Tmp;
    private String INSPECTION_IMAGE1;
    private String INSPECTION_IMAGE2;
    private String INSPECTION_IMAGE3;
    private String INSPECTION_IMAGE4;
    private String COMMENT;
    private String COMMENT2;
    private String COMMENT3;
    private String COMMENT4;
    private String Upload_Date;
    private String Upload_Date_Tmp;
    private String Work_Competion_In_Presentage;
    private String Observetion_Category;
    private String Work_Done_as_Previous_Comment;
    private String Work_Status;
    private String Cross_Verification;

    public SurfaceInspectionEntity(SoapObject res1) {
        this.isAuthenticated = Boolean.parseBoolean(res1.getProperty("IS_authenticate").toString());
        this.SCHEME_ID=res1.getProperty("SCHEME_ID").toString();
        this.INSPECTION_ID=res1.getProperty("INSPECTION_ID").toString();
        this.INSPECTION_BY_NAME=res1.getProperty("INSPECTION_BY_NAME").toString();
        this.INSPECTION_BY_Phone=res1.getProperty("INSPECTION_BY_Phone").toString();
        this.DESIGNATION=res1.getProperty("DESIGNATION").toString();
        this.INSPECTION_DATE=res1.getProperty("INSPECTION_DATE").toString();
        this.OTHER_DETAIL=res1.getProperty("OTHER_DETAIL").toString();
        this.INSPECTION_DATE_Tmp=res1.getProperty("INSPECTION_DATE_Tmp").toString();
        this.INSPECTION_IMAGE1=res1.getProperty("INSPECTION_IMAGE1").toString();
        this.INSPECTION_IMAGE2=res1.getProperty("INSPECTION_IMAGE2").toString();
        this.INSPECTION_IMAGE3=res1.getProperty("INSPECTION_IMAGE3").toString();
        this.INSPECTION_IMAGE4=res1.getProperty("INSPECTION_IMAGE4").toString();
        this.COMMENT=res1.getProperty("COMMENT").toString();
        this.COMMENT2=res1.getProperty("COMMENT2").toString();
        this.COMMENT3=res1.getProperty("COMMENT3").toString();
        this.COMMENT4=res1.getProperty("COMMENT4").toString();
        this.Upload_Date=res1.getProperty("Upload_Date").toString();
        this.Upload_Date_Tmp=res1.getProperty("Upload_Date_Tmp").toString();
        this.Work_Competion_In_Presentage=res1.getProperty("Work_Competion_In_Presentage").toString();
        this.Observetion_Category=res1.getProperty("Observetion_Category").toString();
        this.Work_Done_as_Previous_Comment=res1.getProperty("Work_Done_as_Previous_Comment").toString();
        this.Work_Status=res1.getProperty("Work_Status").toString();
        this.Cross_Verification=res1.getProperty("Cross_Verification").toString();
    }

    public SurfaceInspectionEntity() {
    }

    public static Class<SurfaceInspectionEntity> getSurfaceInspectionEntity_CLASS() {
        return SurfaceInspectionEntity_CLASS;
    }

    public static void setSurfaceInspectionEntity_CLASS(Class<SurfaceInspectionEntity> surfaceInspectionEntity_CLASS) {
        SurfaceInspectionEntity_CLASS = surfaceInspectionEntity_CLASS;
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

    public String getINSPECTION_ID() {
        return INSPECTION_ID;
    }

    public void setINSPECTION_ID(String INSPECTION_ID) {
        this.INSPECTION_ID = INSPECTION_ID;
    }

    public String getINSPECTION_BY_NAME() {
        return INSPECTION_BY_NAME;
    }

    public void setINSPECTION_BY_NAME(String INSPECTION_BY_NAME) {
        this.INSPECTION_BY_NAME = INSPECTION_BY_NAME;
    }

    public String getINSPECTION_BY_Phone() {
        return INSPECTION_BY_Phone;
    }

    public void setINSPECTION_BY_Phone(String INSPECTION_BY_Phone) {
        this.INSPECTION_BY_Phone = INSPECTION_BY_Phone;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public String getOTHER_DETAIL() {
        return OTHER_DETAIL;
    }

    public void setOTHER_DETAIL(String OTHER_DETAIL) {
        this.OTHER_DETAIL = OTHER_DETAIL;
    }

    public String getINSPECTION_DATE() {
        return INSPECTION_DATE;
    }

    public void setINSPECTION_DATE(String INSPECTION_DATE) {
        this.INSPECTION_DATE = INSPECTION_DATE;
    }

    public String getINSPECTION_DATE_Tmp() {
        return INSPECTION_DATE_Tmp;
    }

    public void setINSPECTION_DATE_Tmp(String INSPECTION_DATE_Tmp) {
        this.INSPECTION_DATE_Tmp = INSPECTION_DATE_Tmp;
    }

    public String getINSPECTION_IMAGE1() {
        return INSPECTION_IMAGE1;
    }

    public void setINSPECTION_IMAGE1(String INSPECTION_IMAGE1) {
        this.INSPECTION_IMAGE1 = INSPECTION_IMAGE1;
    }

    public String getINSPECTION_IMAGE2() {
        return INSPECTION_IMAGE2;
    }

    public void setINSPECTION_IMAGE2(String INSPECTION_IMAGE2) {
        this.INSPECTION_IMAGE2 = INSPECTION_IMAGE2;
    }

    public String getINSPECTION_IMAGE3() {
        return INSPECTION_IMAGE3;
    }

    public void setINSPECTION_IMAGE3(String INSPECTION_IMAGE3) {
        this.INSPECTION_IMAGE3 = INSPECTION_IMAGE3;
    }

    public String getINSPECTION_IMAGE4() {
        return INSPECTION_IMAGE4;
    }

    public void setINSPECTION_IMAGE4(String INSPECTION_IMAGE4) {
        this.INSPECTION_IMAGE4 = INSPECTION_IMAGE4;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
    }

    public String getCOMMENT2() {
        return COMMENT2;
    }

    public void setCOMMENT2(String COMMENT2) {
        this.COMMENT2 = COMMENT2;
    }

    public String getCOMMENT3() {
        return COMMENT3;
    }

    public void setCOMMENT3(String COMMENT3) {
        this.COMMENT3 = COMMENT3;
    }

    public String getCOMMENT4() {
        return COMMENT4;
    }

    public void setCOMMENT4(String COMMENT4) {
        this.COMMENT4 = COMMENT4;
    }

    public String getUpload_Date() {
        return Upload_Date;
    }

    public void setUpload_Date(String upload_Date) {
        Upload_Date = upload_Date;
    }

    public String getUpload_Date_Tmp() {
        return Upload_Date_Tmp;
    }

    public void setUpload_Date_Tmp(String upload_Date_Tmp) {
        Upload_Date_Tmp = upload_Date_Tmp;
    }

    public String getWork_Competion_In_Presentage() {
        return Work_Competion_In_Presentage;
    }

    public void setWork_Competion_In_Presentage(String work_Competion_In_Presentage) {
        Work_Competion_In_Presentage = work_Competion_In_Presentage;
    }

    public String getObservetion_Category() {
        return Observetion_Category;
    }

    public void setObservetion_Category(String observetion_Category) {
        Observetion_Category = observetion_Category;
    }

    public String getWork_Done_as_Previous_Comment() {
        return Work_Done_as_Previous_Comment;
    }

    public void setWork_Done_as_Previous_Comment(String work_Done_as_Previous_Comment) {
        Work_Done_as_Previous_Comment = work_Done_as_Previous_Comment;
    }

    public String getWork_Status() {
        return Work_Status;
    }

    public void setWork_Status(String work_Status) {
        Work_Status = work_Status;
    }

    public String getCross_Verification() {
        return Cross_Verification;
    }

    public void setCross_Verification(String cross_Verification) {
        Cross_Verification = cross_Verification;
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
