package com.Coupons.Model;

import com.Util.Utils.CustomerDateAndTimeDeserialize;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coupon")
public class Coupons {
    @Id
    @GenericGenerator(
            name = "couponsSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "COUPONS_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "couponsSequenceGenerator")
    @Column(name = "id_coupon", updatable = false, nullable = false)
    @JsonProperty("idC")
    private long id;
    @JsonIgnore
    @Column(name = "id_company", updatable = false, nullable = false)
    private long idCompany;
    @Column(name = "value_coupon", updatable = false, nullable = false)
    @JsonProperty("vc")
    private String valor;
    @Column(name = "value_desc", length=40)
    @JsonProperty("vd")
    private String valorDesc;
    /*@Column(name = "active", nullable = false)
    @JsonProperty("at")
    private int ativo;*/
    @Column(name = "date_start")
    @JsonProperty("dti")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", locale = "pt_BR", timezone="GMT-2")
    Date dataInicio;
    @Column(name = "date_end")
    @JsonProperty("dtf")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TimeZone.getDefault(), locale = Locale.getDefault())
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy", locale = "pt_BR", timezone="GMT-2")
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @Temporal(TemporalType.TIMESTAMP)
    Date dataFim;

    public Coupons() {

    }

    public String getValorDesc() {
        return valorDesc;
    }

    public void setValorDesc(String valorDesc) {
        this.valorDesc = valorDesc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

   /* public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }*/


    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
