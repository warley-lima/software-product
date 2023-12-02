package com.Company.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "company_brands")
public class CompanyBrands {
    @Id
    @GenericGenerator(
            name = "company_brandSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "COMPANY_BRAND_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "company_brandSequenceGenerator")
    @Column(name = "id_company_brand", updatable = false, nullable = false)
    private long id;
    @Column(name = "company_id_company", updatable = false, nullable = false)
    private long idCompany;
    @Column(name = "brands_id_brand", updatable = false, nullable = false)
    private int idBrand;

    public CompanyBrands() {
    }

    public CompanyBrands(long idCompany, int idBrand) {
        this.idCompany = idCompany;
        this.idBrand = idBrand;
    }

    public CompanyBrands(long id, long idCompany, int idBrand) {
        this.id = id;
        this.idCompany = idCompany;
        this.idBrand = idBrand;
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

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }
}
