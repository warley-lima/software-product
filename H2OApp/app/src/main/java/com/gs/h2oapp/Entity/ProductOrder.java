package com.gs.h2oapp.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Warley Lima 
 */

@Entity(foreignKeys = @ForeignKey(entity = Order.class,
        parentColumns = "id_order",
        childColumns = "id_order_fk",
        onDelete = CASCADE))
public class ProductOrder {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "id_order_fk")
    private long idOrderFK;
    @ColumnInfo(name = "id_company")
    private int idCompany;
    @ColumnInfo(name = "id_brand")
    private int idBrand;
    @ColumnInfo(name = "id_prod")
    private int idProduct;
    @ColumnInfo(name = "name")
    private String nameProduct;
    @ColumnInfo(name = "lts")
    private String liters;
    @ColumnInfo(name = "qtde")
    private String quantidade;
    @ColumnInfo(name = "valor")
    private String precoUnitarioProduto;

    public ProductOrder() {
    }

    /*public ProductOrder(long idOrderFK, int idCompany, int idBrand, int idProduct, String nameProduct, String liters, String quantidade, String precoUnitarioProduto) {
        this.idOrderFK = idOrderFK;
        this.idCompany = idCompany;
        this.idBrand = idBrand;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.liters = liters;
        this.quantidade = quantidade;
        this.precoUnitarioProduto = precoUnitarioProduto;
    }

    public ProductOrder(int id, long idOrderFK, int idCompany, int idBrand, int idProduct, String nameProduct, String liters, String quantidade, String precoUnitarioProduto) {
        this.id = id;
        this.idOrderFK = idOrderFK;
        this.idCompany = idCompany;
        this.idBrand = idBrand;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.liters = liters;
        this.quantidade = quantidade;
        this.precoUnitarioProduto = precoUnitarioProduto;
    }
    */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getIdOrderFK() {
        return idOrderFK;
    }

    public void setIdOrderFK(long idOrderFK) {
        this.idOrderFK = idOrderFK;
    }

    public String getLiters() {
        return liters;
    }

    public void setLiters(String liters) {
        this.liters = liters;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public void setPrecoUnitarioProduto(String precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }
}
