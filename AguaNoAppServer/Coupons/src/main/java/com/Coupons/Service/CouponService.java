package com.Coupons.Service;


import com.Coupons.Business.CouponBusiness;
import com.Coupons.Model.Coupons;
import com.Coupons.Model.Return;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Component
@Path("cupons")
public class CouponService {
    private final CouponBusiness couponBusiness;
    private OAuth2Authentication auth;
    public CouponService(CouponBusiness userBusiness) {
        this.couponBusiness = userBusiness;
    }

    @GET
    @Path("/lstcupons")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<Coupons> getCoupons(@QueryParam("l") int lim, @QueryParam("p") int pag){
        Page<Coupons> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               Pageable pageable = new PageRequest(pag, lim);
                ret = couponBusiness.getCouponsByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
            }
            else{
                ret = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @GET
    @Path("/lstcouponsearch")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<Coupons> getCouponsBySearch(@QueryParam("l") int lim, @QueryParam("p") int pag, @QueryParam("n") String name) {
        Page<Coupons> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                Pageable pageable = new PageRequest(pag, lim);
                ret = couponBusiness.getCouponSearchByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable, name);
            }
            else{
                ret = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @POST
    @Path("/s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Return saveCoupon(Coupons product) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                product.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                couponBusiness.save(product);
                orderRet.setRet("S;");
            }
            else{
                orderRet.setRet("N;");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderRet;
    }

    @PUT
    @Path("/u")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Return updateCoupon(Coupons user) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                user.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                couponBusiness.update(user);
                orderRet.setRet("S;");
            }
            else{
                orderRet.setRet("N;");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderRet;
    }

    @GET
    @Path("/c/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Coupons getCoupon(@PathParam("id") long id) {
        Coupons prod = null;
        try {
            prod = couponBusiness.getCoupon(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return prod;
    }

    @DELETE
    @Path("/d/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Return deleteCoupon(@PathParam("id") long id) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               couponBusiness.delete(id);
                orderRet.setRet("S;");
            }
            else{
                orderRet.setRet("N;");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderRet;
    }


}
