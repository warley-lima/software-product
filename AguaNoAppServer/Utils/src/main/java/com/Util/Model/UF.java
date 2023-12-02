package com.Util.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uf")
public class UF {
    @Id
    @GenericGenerator(
            name = "ufSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "UF_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "ufSequenceGenerator")
    @Column(name = "id_uf", updatable = false, nullable = false)
    private long id;
    @Column(name = "name_uf", updatable = false, nullable = false)
    private String name;
    @Column(name = "sigla_uf", updatable = false, nullable = false)
    private String sigla;
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_uf")
    private List<Cidade> cidades;

    public UF() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
