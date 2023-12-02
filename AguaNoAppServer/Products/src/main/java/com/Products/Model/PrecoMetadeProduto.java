package com.Products.Model;

public class PrecoMetadeProduto {
    private int idPrecoMetadeProduto;
    private int idProdutoRelacionado;
    private int idProduto;
    private boolean descontado;

    public PrecoMetadeProduto() {
    }

    public PrecoMetadeProduto(int idPrecoMetadeProduto, int idProdutoRelacionado, int idProduto, boolean descontado) {
        this.idPrecoMetadeProduto = idPrecoMetadeProduto;
        this.idProdutoRelacionado = idProdutoRelacionado;
        this.idProduto = idProduto;
        this.descontado = descontado;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPrecoMetadeProduto() {
        return idPrecoMetadeProduto;
    }

    public void setIdPrecoMetadeProduto(int idPrecoMetadeProduto) {
        this.idPrecoMetadeProduto = idPrecoMetadeProduto;
    }

    public int getIdProdutoRelacionado() {
        return idProdutoRelacionado;
    }

    public void setIdProdutoRelacionado(int idProdutoRelacionado) {
        this.idProdutoRelacionado = idProdutoRelacionado;
    }

    public boolean isDescontado() {
        return descontado;
    }

    public void setDescontado(boolean descontado) {
        this.descontado = descontado;
    }
}
