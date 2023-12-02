package com.Brand.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {
    @GenericGenerator(
            name = "brandSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "BRAND_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "brandSequenceGenerator")
    @Id
    @Column(name = "id_brand", updatable = false, nullable = false)
    @JsonProperty("id")
    private int idBrand;
    @Column(name = "brand_name", nullable = false)
    @JsonProperty("nm")
    private  String brandName;
    @Column(name = "brand_logo", nullable = true)
    @JsonProperty("lg")
    private  String brandLogo;
    /*@OneToMany(mappedBy="brand")
    private List<Product> lstProducts;*/
    private static final long serialVersionUID = 7526502149208345058L;

    public Brand() {
    }

    public Brand(int idBrand) {
        this.idBrand = idBrand;
    }


    public Brand(String brandName) {
        this.brandName = brandName;
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
