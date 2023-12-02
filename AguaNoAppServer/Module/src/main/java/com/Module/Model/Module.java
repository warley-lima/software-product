package com.Module.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GenericGenerator(
            name = "moduleSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "MODULE_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "moduleSequenceGenerator")
    @Column(name = "id_module", updatable = false, nullable = false)
    private int id;
    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    public Module() {
    }

    public Module(int id) {
        this.id = id;
    }

    public Module(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
