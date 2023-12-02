package com.Order.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OrderTable {
    @JsonProperty("idO")
    private long idOrder;
    @JsonProperty("nm")
    private String nameClient;
    @JsonProperty("fn")
    private String foneClient;
    @JsonProperty("vt")
    private float valueTotal;
    @JsonProperty("st")
    private String statusOrder;
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy", locale = "pt_BR", timezone="GMT-2")
    @JsonProperty("dtp")
    private Date dateOrder;

    public OrderTable() {
    }

    public OrderTable(long idOrder, String nameClient, String foneClient, float valueTotal, String statusOrder, Date dateOrder) {
        this.idOrder = idOrder;
        this.nameClient = nameClient;
        this.foneClient = foneClient;
        this.valueTotal = valueTotal;
        this.statusOrder = statusOrder;
        this.dateOrder = dateOrder;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getFoneClient() {
        return foneClient;
    }

    public void setFoneClient(String foneClient) {
        this.foneClient = foneClient;
    }

    public float getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(float valueTotal) {
        this.valueTotal = valueTotal;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }
}
