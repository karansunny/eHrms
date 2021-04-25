package bih.in.e_hrms.entity;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;

public class EquipmentEntity implements Serializable {
    public static Class<EquipmentEntity> EquipmentEntity_CLASS = EquipmentEntity.class;

    private String id = "";
    private String name = "";

    private Boolean isSelected = false;

    public EquipmentEntity() {
    }

    @SuppressWarnings("deprecation")
    public EquipmentEntity(SoapObject obj) {

        this.id = obj.getProperty("EquId").toString();
        this.name = obj.getProperty("EquipmentName").toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}

