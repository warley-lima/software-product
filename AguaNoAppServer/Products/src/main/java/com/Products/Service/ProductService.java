package com.Products.Service;


import com.Products.Business.ProductBusiness;
import com.Products.Model.Product;
import com.Products.Model.Return;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

// import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Component
@Path("products")
//@Produces("application/json")
public class ProductService {
    private final ProductBusiness prodBusiness;
   // @Inject
    private OAuth2Authentication auth;
    //@Inject
   // private ClientDetailsService clientDetailsService;
    public ProductService(ProductBusiness prodBusiness) {
        this.prodBusiness = prodBusiness;
        //this.auth = tokenStor;
    }


    @GET
   // @Path("/c/{id}")
    @Path("/lstprod")
    //@JsonView(Views.Public.class)
    @Produces(MediaType.APPLICATION_JSON)
 //   @PreAuthorize("#oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}') or #oauth2.hasScope('USER') or #oauth2.hasScope('S_ADM')"  )

    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
   // public Collection<Product> getProducts(@PathParam("id") long id) {
    public Page<Product> getProducts(@QueryParam("l") int lim, @QueryParam("p") int pag) {
        // OAuth2Authentication auth = tokenStore.readAuthentication(tokenValue);
      //  OAuth2Authentication auth;
      //  System.out.println("PAG--> "+  pag);
    /*    auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        String clientId = auth.getOAuth2Request().getClientId();

       // String clientId2 = SecurityContextHolder.getContext().getAuthentication().getClientId();

      //  System.out.println("JESUS---> "+auth.getDetails());
        System.out.println("TENANT---->" + clientId);
        System.out.println("Principal---> "+auth.getPrincipal());
      //  System.out.println("ID---> "+  auth.getOAuth2Request().getAuthorities());
      */
        //  System.out.println("LIM--> "+  lim);
        //Page<Product> ret = new ArrayList<>();
        Page<Product> ret = null;
       // List<ProductDto> ret = new ArrayList<>();  JwtAccessTokenConverter
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
           // String idCompany = auth.getOAuth2Request().getClientId();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               /* List<Product> lstProd = prodBusiness.getProductsByIdCompany(id);
                ret = lstProd;*/
               // ret = new ArrayList<>();
                Pageable pageable = new PageRequest(pag, lim);
                Page<Product> lstProd = prodBusiness.getProductsByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
                ret = lstProd;
            }
            else{
                ret = null;
            }
           /* //ret = new ArrayList<>();
           // System.out.println("CHEGUEI AQUI CARAIO2------------------------>");
            //lstProd.stream().forEach(product -> System.out.println(product.getProductName() + " R$" + product.getPrice()));
            lstProd.stream().forEach(product -> ret.add(product));
           //lstProd.stream().forEach(p -> ret.add(new ProductDto(p.getIdProduct(),1,p.getProductName(),"TESTE",p.getLiters(),p.getPrice())));
            */
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }


    @GET
    @Path("/lstprodsearch")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<Product> getProductsBySearch(@QueryParam("l") int lim, @QueryParam("p") int pag, @QueryParam("n") String name) {
        Page<Product> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            // String idCompany = auth.getOAuth2Request().getClientId();
          //  System.out.println("PRODUTO CONSULTADO---------------->>>>>>" + name);
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                Pageable pageable = new PageRequest(pag, lim);
                Page<Product> lstProd = prodBusiness.getProductsSearchByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable, name);
                ret = lstProd;
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
    @Path("/p/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") long id) {
        Product prod = null;
        try {
            prod = prodBusiness.getProduct(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return prod;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Collection<Product> getProducts2() {
        List<Product> ret = null;
        try {
            List<Product> lstProd = prodBusiness.getProductsByIdCompany(2);
            ret = lstProd;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @POST
    @Path("/s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @CrossOrigin(origins = "*", maxAge = 3600,
            allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token", "XSRF-TOKEN"})
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Return saveProduct(Product product) {
        Return orderRet = new Return();
        try {
          //  System.out.println("Nome Produto-->" + product.getProductName());
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                // order.setDataPedidoServer(new Date());
                product.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                prodBusiness.save(product);
                orderRet.setRet("S;"); //.concat(Long.toString(order.getIdOrder()));
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
    public Return updateProduct(Product product) {
        Return orderRet = new Return();
        try {
           // System.out.println("Nome Produto-->" + product.getProductName());
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                product.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                prodBusiness.update(product);
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

    @DELETE
    @Path("/d/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Return deleteProduct(@PathParam("id") long id) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
              //  product.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                prodBusiness.delete(id);
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
