package com.Company.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "score")

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

public class Score {
    @GenericGenerator(
            name = "scoreSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "SCORE_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "scoreSequenceGenerator")
    @Id
    @Column(name = "id_score", updatable = false, nullable = false)
    @JsonProperty("idscore")
    private int idScore;
    @Column(name = "value_score", nullable = false)
    @JsonProperty("valscore")
    private String valueScore;

    public Score() {
    }

    public Score(String valueScore) {
        this.valueScore = valueScore;
    }

    public Score(int idScore,String valueScore) {
        this.valueScore = valueScore;
        this.idScore = idScore;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public String getValueScore() {
        return valueScore;
    }

    public void setValueScore(String valueScore) {
        this.valueScore = valueScore;
    }
}
