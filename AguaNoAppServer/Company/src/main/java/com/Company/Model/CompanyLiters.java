package com.Company.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "company_liters")
public class CompanyLiters {
    @Id
    @GenericGenerator(
            name = "company_litersSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "COMPANY_LITERS_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "company_litersSequenceGenerator")
    @Column(name = "id_company_liters", updatable = false, nullable = false)
    private long id;
    @Column(name = "company_id_company", updatable = false, nullable = false)
    private long idCompany;
    @Column(name = "liters_id_litters", updatable = false, nullable = false)
    private int idLiters;

    public CompanyLiters() {
    }

    public CompanyLiters(long idCompany, int idLiters) {
        this.idCompany = idCompany;
        this.idLiters = idLiters;
    }

    public CompanyLiters(long id, long idCompany, int idLiters) {
        this.id = id;
        this.idCompany = idCompany;
        this.idLiters = idLiters;
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

    public int getIdLiters() {
        return idLiters;
    }

    public void setIdLiters(int idLiters) {
        this.idLiters = idLiters;
    }
}
