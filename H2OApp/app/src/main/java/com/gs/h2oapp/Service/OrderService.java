package com.gs.h2oapp.Service;

import com.google.gson.JsonObject;
import com.gs.h2oapp.Entity.AdressQueryCep;
import com.gs.h2oapp.Entity.Order;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Warley Lima
 */
public interface OrderService {
    @Headers("Content-Type: application/json")
    @POST("orderpub/s")
    Call<Order> postOrder(@Body JsonObject jsonObject);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("orderpub/s")
    Call<Order> postOrder(@Body Order order);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("orderpub/s")
    Call<String> postOrderString(@Body Order order);

}
