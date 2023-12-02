package com.gs.h2oapp.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Warley Lima 
 */
@Entity
public class Adress {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_adress")
    private int idAdress;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "cep")
    private String cep;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "complement")
    private String complement;
    @ColumnInfo(name = "adress")
    private String adress;
    @ColumnInfo(name = "bairro")
    private String bairro;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "complemento")
    private String complemento;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @ColumnInfo(name = "uf")
    private String uf;

    public Adress() {
    }

    public int getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(int idAdress) {
        this.idAdress = idAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
