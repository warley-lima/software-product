package com.gs.h2oapp.Entity;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by Warley Lima 
 */
public class Coupon {
    private int idC;
    @SerializedName("vc")
    private String cupom;
    @SerializedName("dti")
    private String dateI;
    @SerializedName("dtf")
    private String dateF;
    @SerializedName("vd")
    private BigDecimal desconto;

    public Coupon() {
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public String getDateI() {
        return dateI;
    }

    public void setDateI(String dateI) {
        this.dateI = dateI;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
}
