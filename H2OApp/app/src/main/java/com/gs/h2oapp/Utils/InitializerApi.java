package com.gs.h2oapp.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gs.h2oapp.Service.BrandService;
import com.gs.h2oapp.Service.CompanyService;
import com.gs.h2oapp.Service.CouponService;
import com.gs.h2oapp.Service.GoogleApiService;
import com.gs.h2oapp.Service.LitersService;
import com.gs.h2oapp.Service.OrderService;
import com.gs.h2oapp.Service.ProductService;
import com.gs.h2oapp.Service.QueryCepService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Warley Lima 
 */
public class InitializerApi {
    private static Retrofit api = null;
    private static Gson gson = null;
    private static OkHttpClient client = null;
    public InitializerApi(String baseUrl) {
        getClient( baseUrl);
    }

    public static Retrofit getClient(String baseUrl) {
        if (api == null) {
             gson = new GsonBuilder()
                    .setLenient()
                    .create();

            client = new OkHttpClient.Builder()
                    .connectTimeout(300, TimeUnit.SECONDS)
                    .writeTimeout(300, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .build();
           
        }
        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return api;
    }

    public  CompanyService getCompanyService() {
       // this.companyService = api.create(CompanyService.class);
        CompanyService companyService = api.create(CompanyService.class);
        return companyService;
    }

    public ProductService getProductService() {
        ProductService productService = api.create(ProductService.class);
        return productService;
    }

    public GoogleApiService getGoogleApiService() {
        GoogleApiService googleApiService = api.create(GoogleApiService.class);
        return googleApiService;
    }

    public QueryCepService getQueryCepService() {
        QueryCepService queryCepService = api.create(QueryCepService.class);
        return queryCepService;
    }

    public OrderService getOrderService() {
        OrderService orderService = api.create(OrderService.class);
        return orderService;
    }

    public BrandService getBrandService() {
        BrandService brandService = api.create(BrandService.class);
        return brandService;
    }


    public LitersService getLitersService() {
        LitersService brandService = api.create(LitersService.class);
        return brandService;
    }


    public CouponService getCouponService() {
        CouponService couponService = api.create(CouponService.class);
        return couponService;
    }
}
