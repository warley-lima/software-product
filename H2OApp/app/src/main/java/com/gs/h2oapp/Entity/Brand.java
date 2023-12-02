package com.gs.h2oapp.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Warley Lima
 */
public class Brand {
    @SerializedName("id")
    private int idBrand;
    @SerializedName("nm")
    private  String brandName;
    @SerializedName("lg")
    private  String brandLogo;

    public Brand() {
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }
}
