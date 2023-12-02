package com.gs.h2oapp.Entity;

import java.math.BigDecimal;

/**
 * Created by Warley Lima 
 */
public class ItemCart implements Comparable<ItemCart> {
    private int codigo;
    private BigDecimal quantidade = BigDecimal.ZERO;
    private Product product;

    @Override
    public boolean equals(Object obj) {
        ItemCart item = (ItemCart) obj;
       return item.getProduct().getIdP() == this.getProduct().getIdP();
    }

    public BigDecimal getValor() {
        return getProduct().getPrecoUnitarioProduto().multiply(getQuantidade());
    }

    public BigDecimal getValorSubTotalDesconto() {
       // return (getProduct().getPrecoUnitarioNormalProduto().subtract(getProduct().getPrecoUnitarioProduto()).multiply(getQuantidade())) ;
       return (getProduct().getPrecoUnitarioProduto()).multiply(getQuantidade()) ;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product produto) {
        this.product = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(ItemCart o) {
        return this.getProduct().getNm().compareTo(o.getProduct().getNm());
    }
}
