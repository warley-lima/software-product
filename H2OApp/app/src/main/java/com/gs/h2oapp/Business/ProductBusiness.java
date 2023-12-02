package com.gs.h2oapp.Business;

import com.gs.h2oapp.Entity.PrecoMetadeProduto;
import com.gs.h2oapp.Entity.Product;

import java.util.List;

/**
 * Created by Warley Lima
 */
public interface ProductBusiness {
    Product getProdutoVendaCasada(int id, int idProd2, String camFotoDisco);
    List<PrecoMetadeProduto> getPrecoMetadeProduto(int id);
    float precoDescontoVendaCasada(float precoNormal, float percentualDesconto, int tpDesc);
    List<Product> getProdutosByName(String nome);
    public int updateNcmProduto(int id, String ncm);
    int updateEstoqueRed(int id, String quantidade);
    int updateEstoqueAdd(int id, String quantidade);
    int totalProdutos();
}
