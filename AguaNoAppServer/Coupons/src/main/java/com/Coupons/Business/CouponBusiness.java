package com.Coupons.Business;


import com.Coupons.Model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface CouponBusiness {
     void save(Coupons brand);
     void update(Coupons brand);
     void delete(long idProd);
     Page<Coupons> getCouponsByIdCompany(long idCompany, Pageable pageable);
     Page<Coupons> getCouponSearchByIdCompany(long idCompany, Pageable pageable, String name);
     Coupons getCoupon(long idCoupon);
     Coupons getCoupon(long idCompany, String name, Date data, Date dataF);

}
