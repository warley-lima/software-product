package com.Products.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PrecoQuantidadeUnidades implements Serializable {
    private int idPrecoQuantidadeUnidade;
    private int fkIdProduto;
    private int quantidadeUnidades;
    private int quantidadeUnidadesMax;
    private BigDecimal precoUnitarioPorQuantidade;
    private List<PrecoQuantidadeUnidades> precoQuantidadeUnidades;

    public PrecoQuantidadeUnidades() {
    }

    public PrecoQuantidadeUnidades(int idPrecoQuantidadeUnidade, int fkIdProduto, int quantidadeUnidades, int quantidadeUnidadesMax, BigDecimal precoUnitarioPorQuantidade, List<PrecoQuantidadeUnidades> precoQuantidadeUnidades) {
        this.idPrecoQuantidadeUnidade = idPrecoQuantidadeUnidade;
        this.fkIdProduto = fkIdProduto;
        this.quantidadeUnidades = quantidadeUnidades;
        this.quantidadeUnidadesMax = quantidadeUnidadesMax;
        this.precoUnitarioPorQuantidade = precoUnitarioPorQuantidade;
        this.precoQuantidadeUnidades = precoQuantidadeUnidades;
    }

    public int getQuantidadeUnidadesMax() {
        return quantidadeUnidadesMax;
    }

    public void setQuantidadeUnidadesMax(int quantidadeUnidadesMax) {
        this.quantidadeUnidadesMax = quantidadeUnidadesMax;
    }

    public int getIdPrecoQuantidadeUnidade() {
        return idPrecoQuantidadeUnidade;
    }

    public void setIdPrecoQuantidadeUnidade(int idPrecoQuantidadeUnidade) {
        this.idPrecoQuantidadeUnidade = idPrecoQuantidadeUnidade;
    }

    public int getFkIdProduto() {
        return fkIdProduto;
    }

    public void setFkIdProduto(int fkIdProduto) {
        this.fkIdProduto = fkIdProduto;
    }

    public int getQuantidadeUnidades() {
        return quantidadeUnidades;
    }

    public void setQuantidadeUnidades(int quantidadeUnidades) {
        this.quantidadeUnidades = quantidadeUnidades;
    }

    public BigDecimal getPrecoUnitarioPorQuantidade() {
        return precoUnitarioPorQuantidade;
    }

    public void setPrecoUnitarioPorQuantidade(BigDecimal precoUnitarioPorQuantidade) {
        this.precoUnitarioPorQuantidade = precoUnitarioPorQuantidade;
    }

    public List<PrecoQuantidadeUnidades> getPrecoQuantidadeUnidades() {
        return precoQuantidadeUnidades;
    }

    public void setPrecoQuantidadeUnidades(List<PrecoQuantidadeUnidades> precoQuantidadeUnidades) {
        this.precoQuantidadeUnidades = precoQuantidadeUnidades;
    }
}
