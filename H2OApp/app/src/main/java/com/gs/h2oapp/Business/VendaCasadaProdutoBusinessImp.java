package com.gs.h2oapp.Business;

import android.util.Log;

import com.gs.h2oapp.Entity.VendaCasadaProduto;
import com.gs.h2oapp.Entity.VendaCasadaProduto;
import com.gs.h2oapp.Service.ProductService;
import com.gs.h2oapp.Utils.Configuration;
import com.gs.h2oapp.Utils.InitializerApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Warley Lima 
 */
public class VendaCasadaProdutoBusinessImp implements VendaCasadaProdutoBusiness {

    private List<VendaCasadaProduto> lstCompraConjunta = new ArrayList<>();
    private ProductService prodBuss;
    @Override
    public List<VendaCasadaProduto> getVendaCasadaProdutos() {
        return null;
    }

    @Override
    public List<VendaCasadaProduto> getVendasCasadaProduto(int idPr) {
        Call<ArrayList<VendaCasadaProduto>> call = new InitializerApi(Configuration.getBaseUrl()).getProductService().getVendasCasadaProduto(idPr);
        call.enqueue(new Callback<ArrayList<VendaCasadaProduto>>() {
            @Override
            public void onResponse(Call<ArrayList<VendaCasadaProduto>> call, Response<ArrayList<VendaCasadaProduto>> response) {
                Log.i("onResponse chamado", "onResponse: " + response.body());
                lstCompraConjunta = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<VendaCasadaProduto>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getCause().getMessage());
            }
        });
        return lstCompraConjunta;
    }

    @Override
    public int getQuantVendaCasadaProduto() {
        return 0;
    }

    @Override
    public VendaCasadaProduto getVendaCasadaProduto(int id) {
        return null;
    }
}
