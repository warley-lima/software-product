package com.Products.Model;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private long id;
    private long idCompany;
    private String name;
    private String nameBrand;
    private  float liters;
    private  float price;
    private static final long serialVersionUID = 7526502149208345058L;

    public ProductDto() {
    }

    public ProductDto(long id, long idCompany, String name, String nameBrand, float liters, float price) {
        this.id = id;
        this.idCompany = idCompany;
        this.name = name;
        this.nameBrand = nameBrand;
        this.liters = liters;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public float getLiters() {
        return liters;
    }

    public void setLiters(float liters) {
        this.liters = liters;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
