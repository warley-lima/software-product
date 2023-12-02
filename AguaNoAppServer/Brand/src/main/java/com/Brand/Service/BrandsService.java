package com.Brand.Service;

import com.Brand.Business.BrandBusiness;
import com.Brand.Model.Brand;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Path("/brands")
public class BrandsService {
    private final BrandBusiness brandBus;
    private OAuth2Authentication auth;
    @Inject
    public BrandsService(BrandBusiness brandBusiness) {
        this.brandBus = brandBusiness;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Collection<Brand> getBrands() {
        List<Brand> ret = new ArrayList<>();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                /*List<Product> lstProd = prodBusiness.getProductsByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                ret = lstProd;*/
                ret =  brandBus.getListBrand();
            }
            else{
                ret = null;
            }
           // ret =  brandBus.getListBrand();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
