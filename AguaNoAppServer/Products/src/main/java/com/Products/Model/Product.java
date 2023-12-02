package com.Products.Model;

//import com.Company.Business.Company;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @GenericGenerator(
            name = "productSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "PRODUCT_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "productSequenceGenerator")
    @Id
    //@JsonView(Views.Public.class)
    @Column(name = "id_product", updatable = false, nullable = false)
    @JsonProperty("idP")
    private long idProduct;
    //@JsonView(Views.Public.class)
    @Column(name = "product_name", nullable = false)
    @JsonProperty("nm")
    private  String productName;
    @Column(name = "quant_liters", nullable = false)
    @JsonProperty("lt")
    private  float liters;
    @Column(name = "price", nullable = false)
    @JsonProperty("vl")
    private float price;
    //@JsonView(Views.Internal.class)
   /* @JsonIgnore
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_company")
    private Company company;*/
    @Column(name = "id_company", updatable = false, nullable = false)
    @JsonProperty("idC")
    private long idCompany;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_brand", referencedColumnName="id_brand")
    private Brand brand;*/
    @Column(name = "fk_id_brand", nullable = false)
    @JsonProperty("idB")
    private int idBrand;
    private static final long serialVersionUID = 7526502149208345058L;

    public Product() {
    }

    public Product(int idBrand, float liters, float price, String productName) {
        this.productName = productName;
        this.liters = liters;
        this.price = price;
        this.idBrand = idBrand;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getLiters() {
        return liters;
    }

    public void setLiters(float liters) {
        this.liters = liters;
    }

    /*public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }*/

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    /*public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }*/

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }
}
