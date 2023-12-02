package com.gs.h2oapp.Service;

import com.gs.h2oapp.Entity.PrecoMetadeProduto;
import com.gs.h2oapp.Entity.PrecoQuantidadeUnidades;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.Entity.VendaCasadaProduto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Warley Lima 
 */
public interface ProductService {
    @GET("productpub/c")
    Call<ArrayList<Product>> loadProducts(@Query("id") int idCompany);
    @GET("productpub/pmp")
    Call<ArrayList<PrecoMetadeProduto>> getPrecoMetadeProduto(@Query("id") int id);
    @GET("productpub/pqu")
    Call<ArrayList<PrecoQuantidadeUnidades>> getLstPrecoQuantidadeUnidades(@Query("id") int id);
    @GET("productpub/vcp")
    Call<ArrayList<VendaCasadaProduto>> getVendasCasadaProduto(@Query("id") int id);


}
