package com.Company.Model;

import java.math.BigDecimal;
import java.util.List;

public class CompanyPublicApi {
    private long id;
    private String nm;
    private long idCA;
    private BigDecimal tx;
    private String pty;
    private List<CompanyPaymentTypes> paymentTypeList;
    private BigDecimal vt;
    private BigDecimal av;
    public CompanyPublicApi() {
    }

    public CompanyPublicApi(long id, String nm, long idCA, BigDecimal tx) {
        this.id = id;
        this.nm = nm;
        this.idCA = idCA;
        this.tx = tx;
    }

    public CompanyPublicApi(long id, String nm, long idCA, BigDecimal tx, String paymentTypes) {
        this.id = id;
        this.nm = nm;
        this.idCA = idCA;
        this.tx = tx;
        this.pty = paymentTypes;
    }

    public CompanyPublicApi(long id, String nm, long idCA, BigDecimal tx, String pty, BigDecimal vt, BigDecimal av) {
        this.id = id;
        this.nm = nm;
        this.idCA = idCA;
        this.tx = tx;
        this.pty = pty;
        this.vt = vt;
        this.av = av;
    }

    public String getPty() {
        return pty;
    }

    public void setPty(String pty) {
        this.pty = pty;
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

    public List<CompanyPaymentTypes> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<CompanyPaymentTypes> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public long getIdCA() {
        return idCA;
    }

    public void setIdCA(long idCA) {
        this.idCA = idCA;
    }

    public BigDecimal getTx() {
        return tx;
    }

    public void setTx(BigDecimal tx) {
        this.tx = tx;
    }
}
