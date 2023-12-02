package com.Util.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cidades")
public class Cidade {
    @Id
    @GenericGenerator(
            name = "cidadesSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CIDADES_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "cidadesSequenceGenerator")
    @Column(name = "id_cidade", updatable = false, nullable = false)
    private long idCity;
    @Column(name = "name_cidade", updatable = false, nullable = false)
    private String name;
    @Column(name = "sigla_uf", updatable = false, nullable = false)
    private String sigla;
    //@Column(name = "name_uf", updatable = false, nullable = false)
    //private String nameUf;
    @Column(name = "id_uf", updatable = false, nullable = false)
    private long idUf;
    @Column(name = "id_pais", updatable = false, nullable = false)
    private int idpais;
    @Column(name = "id_ibge", updatable = false, nullable = false)
    private int idibge;
    public Cidade() {

    }

    public long getIdCity() {
        return idCity;
    }

    public void setIdCity(long idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

   /* public String getNameUf() {
        return nameUf;
    }

    public void setNameUf(String nameUf) {
        this.nameUf = nameUf;
    }*/

    public long getIdUf() {
        return idUf;
    }

    public void setIdUf(long idUf) {
        this.idUf = idUf;
    }

    public int getIdpais() {
        return idpais;
    }

    public void setIdpais(int idpais) {
        this.idpais = idpais;
    }

    public int getIdibge() {
        return idibge;
    }

    public void setIdibge(int idibge) {
        this.idibge = idibge;
    }
}
