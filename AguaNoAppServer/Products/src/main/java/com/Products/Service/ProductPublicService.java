package com.Products.Service;


import com.Products.Business.ProductBusiness;
import com.Products.Model.PrecoMetadeProduto;
import com.Products.Model.PrecoQuantidadeUnidades;
import com.Products.Model.Product;
import com.Products.Model.VendaCasadaProduto;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Path("/productpub")
public class ProductPublicService {
    private final ProductBusiness productBusiness;
    @Inject
    public ProductPublicService(ProductBusiness prodBusiness) {
        this.productBusiness = prodBusiness;
    }

   /* @GET
    @Path("/c/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getProductsById(@PathParam("id") long id) {
        List<Product> ret = new ArrayList<>();
        try {
            List<Product> lstProd = productBusiness.getProductsByIdCompany(id);
            ret = lstProd;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }*/
   @GET
   @Path("/c")
   @Produces(MediaType.APPLICATION_JSON)
   public Collection<Product> getProductsById(@QueryParam("id") long id) {
       List<Product> ret = new ArrayList<>();
       try {
          // List<Product> lstProd = productBusiness.getProductsByIdCompany(id);
           ret =  productBusiness.getProductsByIdCompany(id);
       }catch (Exception e){
           e.printStackTrace();
       }
       return ret;
   }

    @GET
    @Path("/pmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PrecoMetadeProduto> getPrecoMetadeProduto(@QueryParam("id") long id) {
        List<PrecoMetadeProduto> ret = new ArrayList<>();
        try {
           // ret = productBusiness.getProductsByIdCompany(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    @GET
    @Path("/pqu")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PrecoQuantidadeUnidades> getLstPrecoQuantidadeUnidades(@QueryParam("id") long id) {
        List<PrecoQuantidadeUnidades> ret = new ArrayList<>();
        try {
            // ret = productBusiness.getProductsByIdCompany(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @GET
    @Path("/vcp")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<VendaCasadaProduto> getVendasCasadaProduto(@QueryParam("id") long id) {
        List<VendaCasadaProduto> ret = new ArrayList<>();
        try {
            // ret = productBusiness.getProductsByIdCompany(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @GET
    @Path("/p/{id}")
    @Produces("application/json")
    public Product getProductById(@PathParam("id") long id) {
        Product prod = null;
        try {
            prod = productBusiness.getProduct(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return prod;
    }

}
