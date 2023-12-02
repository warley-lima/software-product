package com.Products.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class VendaCasadaProduto implements Serializable {
    private int idPrecoCasadaProduto;
    private int idProdutoRelacionado;
    private int idProduto;
    private int idTpDesconto;
    private boolean descontado;
    private BigDecimal valorDesconto;

    public VendaCasadaProduto() {
    }

    public VendaCasadaProduto(int idPrecoCasadaProduto, int idProdutoRelacionado, int idProduto, int idTpDesconto, boolean descontado, BigDecimal valorDesconto) {
        this.idPrecoCasadaProduto = idPrecoCasadaProduto;
        this.idProdutoRelacionado = idProdutoRelacionado;
        this.idProduto = idProduto;
        this.idTpDesconto = idTpDesconto;
        this.descontado = descontado;
        this.valorDesconto = valorDesconto;
    }

    public boolean isDescontado() {
        return descontado;
    }

    public void setDescontado(boolean descontado) {
        this.descontado = descontado;
    }

    public int getIdTpDesconto() {
        return idTpDesconto;
    }

    public void setIdTpDesconto(int idTpDesconto) {
        this.idTpDesconto = idTpDesconto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPrecoCasadaProduto() {
        return idPrecoCasadaProduto;
    }

    public void setIdPrecoCasadaProduto(int idPrecoCasadaProduto) {
        this.idPrecoCasadaProduto = idPrecoCasadaProduto;
    }

    public int getIdProdutoRelacionado() {
        return idProdutoRelacionado;
    }

    public void setIdProdutoRelacionado(int idProdutoRelacionado) {
        this.idProdutoRelacionado = idProdutoRelacionado;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
