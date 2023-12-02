package com.User.Model;

import com.Rule.Model.Rule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable{
    @GenericGenerator(
            name = "userSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "USER_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "userSequenceGenerator")
    @Id
    @Column(name = "id_user", updatable = false, nullable = false)
    @JsonProperty("idU")
    private long id;
    @JsonIgnore
    @Column(name = "id_company", updatable = false, nullable = false)
    private long idCompany;
    //private int enabled;
    private static final long serialVersionUID = 1L;
    @JsonProperty("nm")
    private  String name;
    @JsonProperty("em")
    @Column(name = "username", nullable = false)
    private String userName;
    private String perfil;
    @JsonProperty("pa")
    private String password;
   // @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToMany( orphanRemoval = true, fetch = FetchType.EAGER)
    //@OneToMany( orphanRemoval = true, fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
   /* @Cascade({
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})*/
    @JoinColumn( name="id_user")
    private List<Rule> rules;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> roles) {
        this.rules = roles;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }
}
