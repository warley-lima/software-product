package com.gs.h2oapp.Business;

import android.support.v7.widget.DefaultItemAnimator;
import android.util.Log;

import com.gs.h2oapp.Adapter.ProductAdapter;
import com.gs.h2oapp.Entity.PrecoMetadeProduto;
import com.gs.h2oapp.Entity.Product;
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
public class ProductBusinessImp implements ProductBusiness {
    private List<PrecoMetadeProduto> lstPreMetProd = new ArrayList<>();
    private ProductService prodBuss;


    @Override
    public List<PrecoMetadeProduto> getPrecoMetadeProduto(int id) {
        Call<ArrayList<PrecoMetadeProduto>> call =   new InitializerApi(Configuration.getBaseUrl()).getProductService().getPrecoMetadeProduto(id);
        call.enqueue(new Callback<ArrayList<PrecoMetadeProduto>>() {
            @Override
            public void onResponse(Call<ArrayList<PrecoMetadeProduto>> call, Response<ArrayList<PrecoMetadeProduto>> response) {
                Log.i("onResponse chamado", "onResponse: " + response.body());
                lstPreMetProd = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<PrecoMetadeProduto>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getCause().getMessage());
            }
        });
        return lstPreMetProd;
    }

    @Override
    public Product getProdutoVendaCasada(int id, int idProd2, String camFotoDisco) {
        return null;
    }

    @Override
    public float precoDescontoVendaCasada(float precoNormal, float percentualDesconto, int tpDesc) {
        return 0;
    }

    @Override
    public List<Product> getProdutosByName(String nome) {
        return null;
    }

    @Override
    public int updateNcmProduto(int id, String ncm) {
        return 0;
    }

    @Override
    public int updateEstoqueRed(int id, String quantidade) {
        return 0;
    }

    @Override
    public int updateEstoqueAdd(int id, String quantidade) {
        return 0;
    }

    @Override
    public int totalProdutos() {
        return 0;
    }
}
