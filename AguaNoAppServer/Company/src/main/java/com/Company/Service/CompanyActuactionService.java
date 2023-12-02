package com.Company.Service;

import com.Company.Business.CompanyActuactionBusiness;
import com.Company.Model.CompanyActuaction;
import com.Products.Model.Return;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/company_actuaction")
public class CompanyActuactionService {
    private final CompanyActuactionBusiness companyActuactionBusiness;
    private OAuth2Authentication auth;

    @Inject
    public CompanyActuactionService(CompanyActuactionBusiness prodBusiness) {
        this.companyActuactionBusiness = prodBusiness;
    }

    @GET
    @Path("/lst")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<CompanyActuaction> getPagination(@QueryParam("l") int lim, @QueryParam("p") int pag){
        Page<CompanyActuaction> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                Pageable pageable = new PageRequest(pag, lim);
                ret = companyActuactionBusiness.getCompanyActuactionByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
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
    public Return save(CompanyActuaction product) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                product.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                companyActuactionBusiness.save(product);
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
    public Return update(CompanyActuaction user) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                user.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                companyActuactionBusiness.update(user);
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
    public CompanyActuaction getById(@PathParam("id") long id) {
        CompanyActuaction prod = null;
        try {
            prod = companyActuactionBusiness.getCompanyActuactionById(id);
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
    public Return delete(@PathParam("id") long id) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                companyActuactionBusiness.delete(id);
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
