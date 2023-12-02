package com.Products.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "liters")
public class Liters implements Serializable {
    @GenericGenerator(
            name = "litersSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "LITERS_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "litersSequenceGenerator")
    @Id
    @Column(name = "id_litters", updatable = false, nullable = false)
    @JsonProperty("idL")
    private int id;
    @Column(name = "name", nullable = false)
    @JsonProperty("nmL")
    private String nameQuantidade;
    @Transient
    @JsonIgnore
    private static final long serialVersionUID = 7526502149208345058L;

    public Liters() {
    }

    public Liters(int id, String nameQuantidade) {
        this.id = id;
        this.nameQuantidade = nameQuantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameQuantidade() {
        return nameQuantidade;
    }

    public void setNameQuantidade(String nameQuantidade) {
        this.nameQuantidade = nameQuantidade;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
