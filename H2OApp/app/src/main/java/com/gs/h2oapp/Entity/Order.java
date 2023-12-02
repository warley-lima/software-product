package com.gs.h2oapp.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by Warley Lima
 */
@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_order")
    private int idOrder;
    @ColumnInfo(name = "id_order_server")
    private int idOrderServer;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "fone")
    private String fone;
    @ColumnInfo(name = "numeber")
    private String numberHouse;
    @ColumnInfo(name = "complement")
    private String complement;
    @ColumnInfo(name = "adress")
    private String adress;
    @ColumnInfo(name = "bairro")
    private String bairro;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "uf")
    private String uf;
    @ColumnInfo(name = "id_dist")
    private int idDist;
    @ColumnInfo(name = "name_dist")
    private String nameDist;
    @ColumnInfo(name = "value_tx_entrega")
    private String valorTaxaEntrega;
    @ColumnInfo(name = "value_desc")
    private String valorDesconto;
    @ColumnInfo(name = "value_subtotal")
    private String valorSubTotal;
    @ColumnInfo(name = "value_total")
    private String valorTotal;
    @ColumnInfo(name = "cupom_desconto")
    private String cupomDesconto;
    @ColumnInfo(name = "forma_pagamento")
    private String formaPagamento;
    @ColumnInfo(name = "troco")
    private String troco;
    @ColumnInfo(name = "dicas_entrega")
    private String dicasEntrega;
    @ColumnInfo(name = "data_pedido")
    private String dataPedido;
    @Ignore
    private List<ProductOrder> lstProduct;
    public Order() {
    }

    /*public Order(String name, String fone, String numberHouse, String complement, String adress, String bairro, String city, String uf, String nameDist, String valorTaxaEntrega, String valorTotal) {
        this.name = name;
        this.fone = fone;
        this.numberHouse = numberHouse;
        this.complement = complement;
        this.adress = adress;
        this.bairro = bairro;
        this.city = city;
        this.uf = uf;
        this.nameDist = nameDist;
        this.valorTaxaEntrega = valorTaxaEntrega;
        this.valorTotal = valorTotal;
    }


    public Order(int idOrder, String name, String fone, String numberHouse, String complement, String adress, String bairro, String city, String uf, String nameDist, String valorTaxaEntrega, String valorTotal) {
        this.idOrder = idOrder;
        this.name = name;
        this.fone = fone;
        this.numberHouse = numberHouse;
        this.complement = complement;
        this.adress = adress;
        this.bairro = bairro;
        this.city = city;
        this.uf = uf;
        this.nameDist = nameDist;
        this.valorTaxaEntrega = valorTaxaEntrega;
        this.valorTotal = valorTotal;
    }*/

    public int getIdOrderServer() {
        return idOrderServer;
    }

    public void setIdOrderServer(int idOrderServer) {
        this.idOrderServer = idOrderServer;
    }

    public String getNameDist() {
        return nameDist;
    }

    public void setNameDist(String nameDist) {
        this.nameDist = nameDist;
    }

    public String getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(String valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getValorTaxaEntrega() {
        return valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(String valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public String getValorSubTotal() {
        return valorSubTotal;
    }

    public void setValorSubTotal(String valorSubTotal) {
        this.valorSubTotal = valorSubTotal;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdDist() {
        return idDist;
    }

    public void setIdDist(int idDist) {
        this.idDist = idDist;
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

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(String cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDicasEntrega() {
        return dicasEntrega;
    }

    public void setDicasEntrega(String dicasEntrega) {
        this.dicasEntrega = dicasEntrega;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public List<ProductOrder> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<ProductOrder> lstProduct) {
        this.lstProduct = lstProduct;
    }
}
