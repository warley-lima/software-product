package com.Company.Service;

import com.Brand.Model.Brand;
import com.Company.Business.CompanyBrandsBusiness;
import com.Company.Business.CompanyBusiness;
import com.Company.Model.Company;
import com.Company.Model.CompanyActuaction;
import com.Company.Model.CompanyBrands;
import com.Company.Model.Return;
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
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
@Path("/company_brand")
public class CompanyBrandService {
    private final CompanyBrandsBusiness companyBusiness;
    private OAuth2Authentication auth;

    @Inject
    public CompanyBrandService(CompanyBrandsBusiness prodBusiness) {
        this.companyBusiness = prodBusiness;
    }

    @GET
    @Path("/lst")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public Page<Brand> getPagination(@QueryParam("l") int lim, @QueryParam("p") int pag){
        Page<Brand> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                Pageable pageable = new PageRequest(pag, lim);
                ret = companyBusiness.getBrandsByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
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
    @Path("/lstbrands")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public List<Brand> getBrandsByIdCompany(){
        List<Brand> ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                ret = companyBusiness.getBrandsByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
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
    @Path("/lstbrandselected")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
    public int[] getLstBrandSelected(){
         int[] ret = null;
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
                List<CompanyBrands> lstCompanyBrands = companyBusiness.getCompanyBrandsByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                if(null != lstCompanyBrands){
                    ret = new int[lstCompanyBrands.size()];
                    for (int i = 0; i < lstCompanyBrands.size(); i++) {
                        ret[i] = lstCompanyBrands.get(i).getIdBrand();
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
    public Return save(int[] lstBrand) {
        Return orderRet = new Return();
        try {
            auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               // Company comp = new Company();
               // comp.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
                List lstBrands = new ArrayList<CompanyBrands>();
                CompanyBrands companyBrands;
                int idCom = Integer.parseInt(auth.getOAuth2Request().getClientId());
                for (int i = 0; i < lstBrand.length; i++) {
                    companyBrands = new CompanyBrands();
                    companyBrands.setIdBrand(lstBrand[i]);
                    companyBrands.setIdCompany(idCom);
                    lstBrands.add(companyBrands);
                }
              //  band.add(new Brand(6));
               /* band.add(brand);
                comp.setBrands(band);
                companyBusiness.saveBrands(comp);*/

                companyBusiness.save(lstBrands);
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
