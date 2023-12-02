package com.Brand.Service;


import com.Brand.Business.BrandBusiness;
import com.Brand.Model.Brand;
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
@Path("/brandpub")
public class BrandPublicService {
    private final BrandBusiness brandBus;
    @Inject
    public BrandPublicService(BrandBusiness brandBusiness) {
        this.brandBus = brandBusiness;
    }

   @GET
   @Path("/m")
   @Produces(MediaType.APPLICATION_JSON)
   public Collection<Brand> getProductsById() {
       List<Brand> ret = new ArrayList<>();
       try {
           ret =  brandBus.getListBrand();
       }catch (Exception e){
           e.printStackTrace();
       }
       return ret;
   }
}
