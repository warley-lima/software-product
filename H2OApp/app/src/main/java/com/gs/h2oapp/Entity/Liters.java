package com.gs.h2oapp.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Warley Lima
 */
public class Liters {
    @SerializedName("idL")
    private Integer idLiters;
    @SerializedName("nmL")
    private  String litersName;

    public Liters() {
    }

    public Liters(Integer idLiters, String litersName) {
        this.idLiters = idLiters;
        this.litersName = litersName;
    }

    public Integer getIdLiters() {
        return idLiters;
    }

    public void setIdLiters(Integer idLiters) {
        this.idLiters = idLiters;
    }

    public String getLitersName() {
        return litersName;
    }

    public void setLitersName(String litersName) {
        this.litersName = litersName;
    }
}
