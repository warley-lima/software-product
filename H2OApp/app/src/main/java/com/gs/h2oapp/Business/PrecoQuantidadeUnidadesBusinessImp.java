package com.gs.h2oapp.Business;

import android.util.Log;

import com.gs.h2oapp.Entity.PrecoQuantidadeUnidades;
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
public class PrecoQuantidadeUnidadesBusinessImp implements PrecoQuantidadeUnidadesBusiness{
    private List<PrecoQuantidadeUnidades> lstPreQuaUniProd = new ArrayList<>();
    private ProductService prodBuss;
    @Override
    public List<PrecoQuantidadeUnidades> getLstPrecoQuantidadeUnidades(int idPr) {
        Call<ArrayList<PrecoQuantidadeUnidades>> call =   new InitializerApi(Configuration.getBaseUrl()).getProductService().getLstPrecoQuantidadeUnidades(idPr);
        call.enqueue(new Callback<ArrayList<PrecoQuantidadeUnidades>>() {
            @Override
            public void onResponse(Call<ArrayList<PrecoQuantidadeUnidades>> call, Response<ArrayList<PrecoQuantidadeUnidades>> response) {
                Log.i("onResponse chamado", "onResponse: " + response.body());
                lstPreQuaUniProd = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<PrecoQuantidadeUnidades>> call, Throwable t) {
                Log.e("onFaillure chamado ", t.getCause().getMessage());
            }
        });
        return lstPreQuaUniProd;
    }

    @Override
    public int getTotalPrecoQuantidadeUnidades(int idPro) {
        return 0;
    }

    @Override
    public PrecoQuantidadeUnidades getPrecoCasadaProduto(int id) {
        return null;
    }
}
