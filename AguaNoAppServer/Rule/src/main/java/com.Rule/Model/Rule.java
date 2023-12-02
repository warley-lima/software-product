package com.Rule.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
//@Table(name = "user_access_module" , schema = "tenant5")
@Table(name = "rule")
public class Rule {
    @Id
    @GenericGenerator(
            name = "roleSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ROLE_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "roleSequenceGenerator")
    @Column(name = "id_rule", updatable = false, nullable = false)
    private long id;
    @Column(name = "name_rule", updatable = false, nullable = false)
    private String name;
    @Column(name = "id_module", nullable = false)
    private int idModuleFK;
    @Column(name = "id_user")
    private long idUserFK;
    /*@Column(name = "id_pega")
    private int pega;*/
   // private User user;
    public Rule() {

    }

   /* public int getPega() {
        return pega;
    }

    public void setPega(int pega) {
        this.pega = pega;
    }*/

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

    public int getIdModuleFK() {
        return idModuleFK;
    }

    public void setIdModuleFK(int idModuleFK) {
        this.idModuleFK = idModuleFK;
    }

    public long getIdUserFK() {
        return idUserFK;
    }

    public void setIdUserFK(long idUserFK) {
        this.idUserFK = idUserFK;
    }
}
