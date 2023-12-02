package com.gs.h2oapp.Service;

import com.gs.h2oapp.Entity.Coupon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Warley Lima 
 */
public interface CouponService {
    @GET("cuponspub/c")
    Call<Coupon> getCoupon(@Query("id") int id, @Query("n") String coupon);
}
