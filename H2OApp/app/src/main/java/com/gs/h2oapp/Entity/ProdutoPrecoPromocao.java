package com.gs.h2oapp.Entity;

import java.io.Serializable;

/**
 * Created by Warley Lima 
 */
public class ProdutoPrecoPromocao implements Serializable {
    private int idPromocao;
    private int idProduto;
    private PromocaoPreco promocaoPreco;

    public ProdutoPrecoPromocao() {
    }

    public ProdutoPrecoPromocao(int idPromocao, int idProduto) {
        this.idPromocao = idPromocao;
        this.idProduto = idProduto;
    }

    public PromocaoPreco getPromocaoPreco() {
        return promocaoPreco;
    }

    public void setPromocaoPreco(PromocaoPreco promocaoPreco) {
        this.promocaoPreco = promocaoPreco;
    }

    public int getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPromocao;
        hash += (int) idProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProdutoPrecoPromocao)) {
            return false;
        }
        ProdutoPrecoPromocao other = (ProdutoPrecoPromocao) object;
        if (this.idPromocao != other.idPromocao) {
            return false;
        }
        if (this.idProduto != other.idProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.PDV.Model.ProdutoPrecoPromocao[ idPromocao=" + idPromocao + ", idProduto=" + idProduto + " ]";
    }
}
