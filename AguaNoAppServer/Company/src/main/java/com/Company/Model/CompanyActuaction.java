package com.Company.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "company_actuaction")
public class CompanyActuaction {
    @Id
    @GenericGenerator(
            name = "company_actuactionSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "COMPANY_ACTUACTION_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "company_actuactionSequenceGenerator")
    @Column(name = "id_company_actuaction", updatable = false, nullable = false)
    private long id;
    @Column(name = "id_company", updatable = false, nullable = false)
    private long idCompany;
    @Column(name = "distance", nullable = false)
    private BigDecimal distance;
    @Column(name = "tx_delivery", nullable = false)
    private BigDecimal txDelivery;

    public CompanyActuaction() {
    }

    public CompanyActuaction(long idCompany, BigDecimal distance, BigDecimal txDelivery) {
        this.idCompany = idCompany;
        this.distance = distance;
        this.txDelivery = txDelivery;
    }

    public CompanyActuaction(long id, long idCompany, BigDecimal txDelivery) {
        this.id = id;
        this.idCompany = idCompany;
        this.txDelivery = txDelivery;
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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getTxDelivery() {
        return txDelivery;
    }

    public void setTxDelivery(BigDecimal txDelivery) {
        this.txDelivery = txDelivery;
    }
}
