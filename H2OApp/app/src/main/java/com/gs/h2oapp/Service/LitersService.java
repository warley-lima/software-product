package com.gs.h2oapp.Service;


import com.gs.h2oapp.Entity.Brand;
import com.gs.h2oapp.Entity.Liters;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Warley Lima
 */
public interface LitersService {
    @GET("literspub/l")
    Call<ArrayList<Liters>> getLiters();
}
