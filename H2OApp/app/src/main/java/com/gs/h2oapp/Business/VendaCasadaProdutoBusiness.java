package com.gs.h2oapp.Business;

import com.gs.h2oapp.Entity.VendaCasadaProduto;

import java.util.List;

/**
 * Created by Warley Lima on 
 */
public interface VendaCasadaProdutoBusiness {
    List<VendaCasadaProduto> getVendaCasadaProdutos();
    List<VendaCasadaProduto> getVendasCasadaProduto(int idPr);
    int getQuantVendaCasadaProduto();
    VendaCasadaProduto getVendaCasadaProduto(int id);
}
