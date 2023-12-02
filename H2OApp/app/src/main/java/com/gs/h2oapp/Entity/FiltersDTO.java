package com.gs.h2oapp.Entity;

/**
 * Created by Warley Lima 
 */
public class FiltersDTO {
    private int idType;
    private int idObject;
    private String nameObject;

    public FiltersDTO() {
    }

    public FiltersDTO(int idType, int idObject, String nameObject) {
        this.idType = idType;
        this.idObject = idObject;
        this.nameObject = nameObject;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdObject() {
        return idObject;
    }

    public void setIdObject(int idObject) {
        this.idObject = idObject;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }
}
