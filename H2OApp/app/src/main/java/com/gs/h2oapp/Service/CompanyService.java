package com.gs.h2oapp.Service;

import com.gs.h2oapp.Entity.Company;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Warley Lima 
 */
public interface CompanyService {
    @GET("companypub/getListCompanies")
    //Call<List<Company>> loadCompaniesTeste(@QueryMap Map<String, String> options);
    Call<ArrayList<Company>> loadCompaniesTeste(@Query("lat") String latitude, @Query("lot") String longitude, @Query("ct") String uf);
    @GET("companypub/getCompaniesByBrands")
    Call<ArrayList<Company>> getCompaniesByBrands(@Query("lat") String latitude, @Query("lot") String longitude, @Query("ct") String uf, @Query("ids") String ids);
    @GET("companypub/getCompaniesByLiters")
    Call<ArrayList<Company>> getCompaniesByLiters(@Query("lat") String latitude, @Query("lot") String longitude, @Query("ct") String uf, @Query("ids") String ids);
    @GET("companypub/getCompaniesByBrandsLiters")
    Call<ArrayList<Company>> getCompaniesByBrandsLiters(@Query("lat") String latitude, @Query("lot") String longitude, @Query("ct") String uf, @Query("idsb") String idsb, @Query("idsl") String idsl);
}
