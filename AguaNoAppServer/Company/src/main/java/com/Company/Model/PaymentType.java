package com.Company.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "payments_type")

/*@NamedStoredProcedureQuery(name = "procedurePaymentsTypesByCompany",
        procedureName = "get_companies_payment_types",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)}, resultSetMappings = "mapping_paym_comp")
@SqlResultSetMapping(name = "mapping_paym_comp", classes = {
        @ConstructorResult(targetClass = CompanyPaymentTypes.class, columns = {
                @ColumnResult(name = "id_type_payment", type = Integer.class),
                @ColumnResult(name = "name_payment", type = String.class)
        })
})*/

public class PaymentType {
    @GenericGenerator(
            name = "paymentTypeSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "PAYMENT_TYPE_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "paymentTypeSequenceGenerator")
    @Id
    @Column(name = "id_payments_type", updatable = false, nullable = false)
    @JsonProperty("idpaytype")
    private int idPaymentsType;
    @Column(name = "payment_type", nullable = false)
    @JsonProperty("ptype")
    private String paymentType;

    public PaymentType() {
    }

    public PaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentType(int idPaymentsType, String paymentType) {
        this.idPaymentsType = idPaymentsType;
        this.paymentType = paymentType;
    }

    public int getIdPaymentsType() {
        return idPaymentsType;
    }

    public void setIdPaymentsType(int idPaymentsType) {
        this.idPaymentsType = idPaymentsType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
