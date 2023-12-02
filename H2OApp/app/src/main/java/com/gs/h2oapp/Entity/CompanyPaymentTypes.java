package com.gs.h2oapp.Entity;

/**
 * Created by Warley Lima 
 */
public class CompanyPaymentTypes {
    private int idType;
    private String nmType;

    public CompanyPaymentTypes() {
    }

    public CompanyPaymentTypes(int idType, String nmType) {
        this.idType = idType;
        this.nmType = nmType;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNmType() {
        return nmType;
    }

    public void setNmType(String nmType) {
        this.nmType = nmType;
    }
}
