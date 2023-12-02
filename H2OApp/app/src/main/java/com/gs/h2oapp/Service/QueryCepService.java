package com.gs.h2oapp.Service;

import com.gs.h2oapp.Entity.AdressQueryCep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Warley Lima
 */
public interface QueryCepService {
    @GET("query/cep/")
    Call<AdressQueryCep> queryCep(@Query("c") String cep);
}
