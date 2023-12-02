package com.Products.Service;

import com.Products.Business.LitersBusiness;
import com.Products.Model.Liters;
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
@Path("/literspub")
public class LitersPublicService {
    private final LitersBusiness litersBus;
    @Inject
    public LitersPublicService(LitersBusiness litersBusiness) {
        this.litersBus = litersBusiness;
    }

   @GET
   @Path("/l")
   @Produces(MediaType.APPLICATION_JSON)
   public Collection<Liters> getProductsById() {
       List<Liters> ret = new ArrayList<>();
       try {
           ret =  litersBus.getAllLiters();
       }catch (Exception e){
           e.printStackTrace();
       }
       return ret;
   }
}
