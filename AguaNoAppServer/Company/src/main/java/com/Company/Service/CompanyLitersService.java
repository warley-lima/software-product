package com.Company.Service;

import com.Company.Business.CompanyLitersBusiness;
import com.Company.Model.CompanyLiters;
import com.Company.Model.Return;
import com.Products.Model.Liters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/company_liters")
public class CompanyLitersService {
    private final CompanyLitersBusiness companyBusiness;
    private OAuth2Authentication auth;

    @Inject
    public CompanyLitersService(CompanyLitersBusiness prodBusiness) {
        this.companyBusiness = prodBusiness;
    }

    @GET
    @Path("/lst")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<Liters> getPagination(@QueryParam("l") int lim, @QueryParam("p") int pag){
        Page<Liters> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                Pageable pageable = new PageRequest(pag, lim);
                ret = companyBusiness.getLitersByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
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
    @Path("/lstliterselected")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public int[] getlstLitersSelected(){
        int[] ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                List<CompanyLiters> lstCompanyBrands = companyBusiness.getCompanyLitersByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                if(null != lstCompanyBrands){
                    ret = new int[lstCompanyBrands.size()];
                    for (int i = 0; i < lstCompanyBrands.size(); i++) {
                        ret[i] = lstCompanyBrands.get(i).getIdLiters();
                    }

                }
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
    public Return save(int[] lstLiters) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                List lstLiters2 = new ArrayList<CompanyLiters>();
                CompanyLiters companyBrands;
                int idCom = Integer.parseInt(auth.getOAuth2Request().getClientId());
                for (int i = 0; i < lstLiters.length; i++) {
                    companyBrands = new CompanyLiters();
                    companyBrands.setIdLiters(lstLiters[i]);
                    companyBrands.setIdCompany(idCom);
                    lstLiters2.add(companyBrands);
                }
                companyBusiness.save(lstLiters2);
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
    public Return delete(@PathParam("id") int id) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                companyBusiness.delete(Integer.parseInt(auth.getOAuth2Request().getClientId()), id);
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
