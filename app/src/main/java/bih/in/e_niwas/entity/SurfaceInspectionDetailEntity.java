package bih.in.e_niwas.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class SurfaceInspectionDetailEntity implements KvmSerializable, Serializable {

    public static Class<SurfaceInspectionDetailEntity> SurfaceInspectionDetailEntity_CLASS = SurfaceInspectionDetailEntity.class;

    boolean isAuthenticated = true;

    private String SN;
    private String INSPECTION_ID;
    private String INSPECTION_BY_NAME;
    private String INSPECTION_BY_Phone;
    private String DESIGNATION;
    private String INSPECTION_DATE;
    private String COMMENT;
    private String Work_Competion_In_Presentage;
    private String Observetion_Category;
    private String Work_Done_as_Previous_Comment;
    private String SCHEME_ID;


    public SurfaceInspectionDetailEntity(SoapObject res1) {
        this.isAuthenticated = Boolean.parseBoolean(res1.getProperty("IS_authenticate").toString());
        this.SN=res1.getProperty("SN").toString();
        this.INSPECTION_ID=res1.getProperty("INSPECTION_ID").toString();
        this.INSPECTION_BY_NAME=res1.getProperty("INSPECTION_BY_NAME").toString();
        this.INSPECTION_BY_Phone=res1.getProperty("INSPECTION_BY_Phone").toString();
        this.DESIGNATION=res1.getProperty("DESIGNATION").toString();
        this.INSPECTION_DATE=res1.getProperty("INSPECTION_DATE").toString();
        this.COMMENT=res1.getProperty("COMMENT").toString();
        this.Work_Competion_In_Presentage=res1.getProperty("Work_Competion_In_Presentage").toString();
        this.Observetion_Category=res1.getProperty("Observetion_Category").toString();
        this.Work_Done_as_Previous_Comment=res1.getProperty("Work_Done_as_Previous_Comment").toString();
        this.SCHEME_ID=res1.getProperty("SCHEME_ID").toString();
    }

    public String getWork_Competion_In_Presentage() {
        return Work_Competion_In_Presentage;
    }

    public void setWork_Competion_In_Presentage(String work_Competion_In_Presentage) {
        Work_Competion_In_Presentage = work_Competion_In_Presentage;
    }

    public static Class<SurfaceInspectionDetailEntity> getSurfaceInspectionDetailEntity_CLASS() {
        return SurfaceInspectionDetailEntity_CLASS;
    }

    public static void setSurfaceInspectionDetailEntity_CLASS(Class<SurfaceInspectionDetailEntity> surfaceInspectionDetailEntity_CLASS) {
        SurfaceInspectionDetailEntity_CLASS = surfaceInspectionDetailEntity_CLASS;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
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

    public String getINSPECTION_DATE() {
        return INSPECTION_DATE;
    }

    public void setINSPECTION_DATE(String INSPECTION_DATE) {
        this.INSPECTION_DATE = INSPECTION_DATE;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
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

    public String getSCHEME_ID() {
        return SCHEME_ID;
    }

    public void setSCHEME_ID(String SCHEME_ID) {
        this.SCHEME_ID = SCHEME_ID;
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
