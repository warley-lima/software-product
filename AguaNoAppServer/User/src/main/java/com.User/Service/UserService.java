package com.User.Service;


import com.User.Business.UserBusiness;
import com.User.Model.Return;
import com.User.Model.User;
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


@Component
@Path("users")
public class UserService {
    private final UserBusiness userBusiness;
    private OAuth2Authentication auth;
    public UserService(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }


    @GET
    @Path("/lstuser")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<User> getUsers(@QueryParam("l") int lim, @QueryParam("p") int pag){
      //  System.out.println("PAG--> "+  pag);
     //   System.out.println("LIM--> "+  lim);
        Page<User> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               Pageable pageable = new PageRequest(pag, lim);
                Page<User> lstProd = userBusiness.getUsersByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
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
    @Path("/lstuserearch")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<User> getUsersBySearch(@QueryParam("l") int lim, @QueryParam("p") int pag, @QueryParam("n") String name) {
        Page<User> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
         //   System.out.println("Usuario CONSULTADO---------------->>>>>>" + name);
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                Pageable pageable = new PageRequest(pag, lim);
                Page<User> lstProd = userBusiness.getUserSearchByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable, name);
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

    @POST
    @Path("/s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Return saveUser(User product) {
        Return orderRet = new Return();
        try {
          //  System.out.println("Nome Usuario-->" + product.getUserName());
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                product.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                userBusiness.save(product);
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
    public Return updateUser(User user) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                user.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
              //  user.getRules().clear();
                userBusiness.update(user);
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
    @Path("/u/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") long id) {
        User prod = null;
        try {
            prod = userBusiness.getUser(id);
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
    public Return deleteUser(@PathParam("id") long id) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               userBusiness.delete(id);
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
