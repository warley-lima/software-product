package com.gs.h2oapp.Entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Warley Lima 
 */
public class Company {
    private int id;
    private String nm;
    private int idCA;
    private BigDecimal tx;
    private List<CompanyPaymentTypes> paymentTypeList;
    private BigDecimal vt;
    private BigDecimal av;

    public Company() {
    }

    public Company(int id, String nm, BigDecimal tx) {
        this.id = id;
        this.nm = nm;
        this.tx = tx;
    }

    public Company(int id, String nm, int idCA, BigDecimal tx) {
        this.id = id;
        this.nm = nm;
        this.idCA = idCA;
        this.tx = tx;
    }

    public Company(int id, String nm, int idCA, BigDecimal tx, List<CompanyPaymentTypes> paymentTypeList) {
        this.id = id;
        this.nm = nm;
        this.idCA = idCA;
        this.tx = tx;
        this.paymentTypeList = paymentTypeList;
    }

    public Company(int id, String nm, int idCA, BigDecimal tx, List<CompanyPaymentTypes> paymentTypeList, BigDecimal vt, BigDecimal av) {
        this.id = id;
        this.nm = nm;
        this.idCA = idCA;
        this.tx = tx;
        this.paymentTypeList = paymentTypeList;
        this.vt = vt;
        this.av = av;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public int getIdCA() {
        return idCA;
    }

    public void setIdCA(int idCA) {
        this.idCA = idCA;
    }

    public BigDecimal getTx() {
        return tx;
    }

    public void setTx(BigDecimal tx) {
        this.tx = tx;
    }

    public List<CompanyPaymentTypes> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<CompanyPaymentTypes> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    public BigDecimal getVt() {
        return vt;
    }

    public void setVt(BigDecimal vt) {
        this.vt = vt;
    }

    public BigDecimal getAv() {
        return av;
    }

    public void setAv(BigDecimal av) {
        this.av = av;
    }
}
