package com.gs.h2oapp.Service;

/**
 * Created by Warley Lima
 */

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface GoogleApiService {

    @GET("geocode/json")
    Call<JsonObject> getAdressByLatLng(@Query("latlng") String latlng, @Query("result_type") String resultType, @Query("key") String key);

}
