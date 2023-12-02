package com.gs.h2oapp.Entity;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by Warley Lima 
 */
public class Product {
    private int idP;
    private String nm;
    private int idC;
    private int idB;
    private int tpDescontoPromocionalProduto;
    private BigDecimal lt;
    private BigDecimal quantProdutoAddCart;
    private BigDecimal precoUnitarioProduto;
    @SerializedName("vl")
    private BigDecimal precoUnitarioNormalProduto;
    private BigDecimal valorDescontoPrecoQuantUnidades;
    private BigDecimal valorDescontoPromocao;
    private BigDecimal descontoPromocionalProduto;
    private BigDecimal valorDescontoPrecoCasada;
    private BigDecimal quantUnidPrecoQuantProduto;
    private BigDecimal precoUnitarioQuantProduto;
    private VendaCasadaProduto vendaCasada;
    private PrecoMetadeProduto precoMetadeProduto;
    private ProdutoPrecoPromocao produtoPromocaoPreco;
    private BigDecimal valorDescontoPrecoMetade;

    public Product() {
    }

    public Product(int idP, String nm, int idC, int idB, BigDecimal lt, BigDecimal vl) {
        this.idP = idP;
        this.nm = nm;
        this.idC = idC;
        this.idB = idB;
        this.lt = lt;
        this.precoUnitarioProduto = vl;
        this.precoUnitarioNormalProduto = vl;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getTpDescontoPromocionalProduto() {
        return tpDescontoPromocionalProduto;
    }

    public void setTpDescontoPromocionalProduto(int tpDescontoPromocionalProduto) {
        this.tpDescontoPromocionalProduto = tpDescontoPromocionalProduto;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public BigDecimal getLt() {
        return lt;
    }

    public void setLt(BigDecimal lt) {
        this.lt = lt;
    }

    public BigDecimal getQuantProdutoAddCart() {
        return quantProdutoAddCart;
    }

    public void setQuantProdutoAddCart(BigDecimal quantProdutoAddCart) {
        this.quantProdutoAddCart = quantProdutoAddCart;
    }

    public BigDecimal getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public void setPrecoUnitarioProduto(BigDecimal precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }

    public BigDecimal getPrecoUnitarioNormalProduto() {
        return precoUnitarioNormalProduto;
    }

    public void setPrecoUnitarioNormalProduto(BigDecimal precoUnitarioNormalProduto) {
        this.precoUnitarioNormalProduto = precoUnitarioNormalProduto;
    }

    public BigDecimal getValorDescontoPrecoQuantUnidades() {
        return valorDescontoPrecoQuantUnidades;
    }

    public void setValorDescontoPrecoQuantUnidades(BigDecimal valorDescontoPrecoQuantUnidades) {
        this.valorDescontoPrecoQuantUnidades = valorDescontoPrecoQuantUnidades;
    }

    public BigDecimal getValorDescontoPromocao() {
        return valorDescontoPromocao;
    }

    public void setValorDescontoPromocao(BigDecimal valorDescontoPromocao) {
        this.valorDescontoPromocao = valorDescontoPromocao;
    }

    public BigDecimal getDescontoPromocionalProduto() {
        return descontoPromocionalProduto;
    }

    public void setDescontoPromocionalProduto(BigDecimal descontoPromocionalProduto) {
        this.descontoPromocionalProduto = descontoPromocionalProduto;
    }

    public BigDecimal getValorDescontoPrecoCasada() {
        return valorDescontoPrecoCasada;
    }

    public void setValorDescontoPrecoCasada(BigDecimal valorDescontoPrecoCasada) {
        this.valorDescontoPrecoCasada = valorDescontoPrecoCasada;
    }

    public BigDecimal getQuantUnidPrecoQuantProduto() {
        return quantUnidPrecoQuantProduto;
    }

    public void setQuantUnidPrecoQuantProduto(BigDecimal quantUnidPrecoQuantProduto) {
        this.quantUnidPrecoQuantProduto = quantUnidPrecoQuantProduto;
    }

    public BigDecimal getPrecoUnitarioQuantProduto() {
        return precoUnitarioQuantProduto;
    }

    public void setPrecoUnitarioQuantProduto(BigDecimal precoUnitarioQuantProduto) {
        this.precoUnitarioQuantProduto = precoUnitarioQuantProduto;
    }

    public BigDecimal getValorDescontoPrecoMetade() {
        return valorDescontoPrecoMetade;
    }

    public void setValorDescontoPrecoMetade(BigDecimal valorDescontoPrecoMetade) {
        this.valorDescontoPrecoMetade = valorDescontoPrecoMetade;
    }

    public VendaCasadaProduto getVendaCasada() {
        return vendaCasada;
    }

    public void setVendaCasada(VendaCasadaProduto vendaCasada) {
        this.vendaCasada = vendaCasada;
    }

    public PrecoMetadeProduto getPrecoMetadeProduto() {
        return precoMetadeProduto;
    }

    public void setPrecoMetadeProduto(PrecoMetadeProduto precoMetadeProduto) {
        this.precoMetadeProduto = precoMetadeProduto;
    }

    public ProdutoPrecoPromocao getProdutoPromocaoPreco() {
        return produtoPromocaoPreco;
    }

    public void setProdutoPromocaoPreco(ProdutoPrecoPromocao produtoPromocaoPreco) {
        this.produtoPromocaoPreco = produtoPromocaoPreco;
    }
}
