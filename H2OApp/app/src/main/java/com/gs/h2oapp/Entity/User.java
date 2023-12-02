package com.gs.h2oapp.Entity;

/**
 * Created by Warley Lima 
 */
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    private int idUser;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "fone")
    private String fone;

    public User() {
    }

    public User(String name, String fone) {
        this.name = name;
        this.fone = fone;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }
}
