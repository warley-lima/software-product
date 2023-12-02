package com.Coupons.Service;


import com.Coupons.Business.CouponBusiness;
import com.Coupons.Model.Coupons;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;


@Component
@Path("cuponspub")
public class CouponPublicService {
    private final CouponBusiness couponBusiness;
    public CouponPublicService(CouponBusiness userBusiness) {
        this.couponBusiness = userBusiness;
    }

    @GET
    @Path("/c")
    @Produces(MediaType.APPLICATION_JSON)
    public Coupons getCoupon(@QueryParam("id") long id, @QueryParam("n") String name) {
        Coupons prod = null;
        try {
            prod = couponBusiness.getCoupon(id, name, new Date(), new Date());
            if(null != prod){
                prod.setValorDesc(prod.getValorDesc().replace(".","").replace(",","."));
               /* String valor = prod.getValorDesc().substring(0,prod.getValorDesc().indexOf("."));
                String decimal = prod.getValorDesc().substring(prod.getValorDesc().indexOf(".") + 1);
                if(null == decimal){
                    decimal = prod.getValorDesc().substring(prod.getValorDesc().indexOf(",") + 1);
                }
                if(decimal.length() == 1){

                    prod.setValorDesc(valor.concat(".").concat(decimal).concat("0"));
                }
                else{
                    prod.setValorDesc(valor.concat(".").concat(decimal));
                }*/

            }
          //  prod = couponBusiness.getCoupon(1, "DCad", new Date(), new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
        return prod;
    }



}
