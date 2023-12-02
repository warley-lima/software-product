package com.Company.Service;

import com.Company.Business.CompanyBusiness;
import com.Company.Model.Company;
import com.Company.Model.CompanyPublicApi;
import com.Company.Util.UtilsCompany;
import com.Module.Model.Module;
import com.Products.Model.Return;
import com.User.Business.UserBusiness;
import com.User.Model.User;
import com.Util.Business.CitiesBusiness;
import com.Util.Model.Cidade;
import com.Util.Utils.QueryLatitudeLongitude;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Path("/companypub")
public class CompanyPublicService {
    private final CompanyBusiness companyBusiness;
    private final CitiesBusiness cityBusiness;
    private final UserBusiness userBusiness;

    @Inject
    public CompanyPublicService(CompanyBusiness prodBusiness, CitiesBusiness cityBus, UserBusiness userBus) {
        this.companyBusiness = prodBusiness;
        this.cityBusiness = cityBus;
        this.userBusiness = userBus;
    }

    @GET
    @Path("/getListCompanies")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyPublicApi> getProductsById(@QueryParam("lat") BigDecimal lat, @QueryParam("lot") BigDecimal longi, @QueryParam("ct") String nameCity) {
        List<CompanyPublicApi> ret = new ArrayList<>(); //
        //if(!lat.equals(0) && !longi.equals(0) && !lat.equals(null) && !longi.equals(null) &&!nameCity.isEmpty()){
        BigDecimal teste = BigDecimal.ZERO;
       /* System.out.println("Latitude-----> " + lat);
        System.out.println("Longitude-----> " + longi);
        System.out.println("Cidade-----> " + nameCity);*/
        if (lat != teste && longi != teste && lat != null && longi != null && nameCity != null) {
            try {
                List<CompanyPublicApi> lstCompanies = companyBusiness.getCompaniesByLatLongUfName(lat, longi, nameCity);
                if (null != lstCompanies) {
                   // lstCompanies.stream().forEach(comp -> comp.setPaymentTypeList(companyBusiness.getPaymentsTypesByCompany(comp.getId())));
                    lstCompanies.stream().forEach(comp -> comp.setPaymentTypeList(UtilsCompany.convertTypes(comp.getPty())));
                    ret = lstCompanies;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
        *    List<Company> ret = new ArrayList<>(); //getCompaniesByNameCity  getPaymentsTypesByCompany
        try {
           List<Company> lstProd = companyBusiness.getCompaniesByLatLongCityName(lat,longi,nameCity);
            System.out.println("CHEGUEI AQUI, VALORES-------> " + lat +" ---->" + longi + " ----->" + nameCity);
            //List<Company> lstProd = companyBusiness.getCompaniesByNameCity(nameCity);

            ret = lstProd;
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return ret;
    }


    @GET
    @Path("/getCompaniesByBrands")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyPublicApi> getCompaniesByIdBrands(@QueryParam("lat") BigDecimal lat, @QueryParam("lot") BigDecimal longi, @QueryParam("ct") String nameCity, @QueryParam("ids") String idsBrands) {
        List<CompanyPublicApi> ret = new ArrayList<>();
        BigDecimal teste = BigDecimal.ZERO;
        if (!lat.equals(teste) && longi != teste && lat != null && longi != null && nameCity != null && idsBrands != null) {
            try {
                List<CompanyPublicApi> lstCompanies = companyBusiness.getCompaniesByLatLongUfNameBrands(lat, longi, nameCity, idsBrands);
                if (null != lstCompanies) {
                    //lstProd.stream().forEach(comp -> comp.setPaymentTypeList(companyBusiness.getPaymentsTypesByCompany(comp.getId())));
                    lstCompanies.stream().forEach(comp -> comp.setPaymentTypeList(UtilsCompany.convertTypes(comp.getPty())));
                    ret = lstCompanies;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    @GET
    @Path("/getCompaniesByLiters")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyPublicApi> getCompaniesByIdLiters(@QueryParam("lat") BigDecimal lat, @QueryParam("lot") BigDecimal longi, @QueryParam("ct") String nameCity, @QueryParam("ids") String idsLiters) {
        List<CompanyPublicApi> ret = new ArrayList<>();
        BigDecimal teste = BigDecimal.ZERO;
        if (lat != teste && longi != teste && lat != null && longi != null && nameCity != null && idsLiters != null) {
            try {
                List<CompanyPublicApi> lstCompanies = companyBusiness.getCompaniesByLatLongUfNameLiters(lat, longi, nameCity, idsLiters);
                if (null != lstCompanies) {
                    //lstProd.stream().forEach(comp -> comp.setPaymentTypeList(companyBusiness.getPaymentsTypesByCompany(comp.getId())));
                    lstCompanies.stream().forEach(comp -> comp.setPaymentTypeList(UtilsCompany.convertTypes(comp.getPty())));
                    ret = lstCompanies;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    @GET
    @Path("/getCompaniesByBrandsLiters")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyPublicApi> getCompaniesByIdBrandsLiters(@QueryParam("lat") BigDecimal lat, @QueryParam("lot") BigDecimal longi, @QueryParam("ct") String nameCity, @QueryParam("idsb") String idsBrands, @QueryParam("idsl") String idsLiters) {
        List<CompanyPublicApi> ret = new ArrayList<>();
        BigDecimal teste = BigDecimal.ZERO;
        if (lat != teste && longi != teste && lat != null && longi != null && nameCity != null && idsBrands != null && idsLiters != null) {
            try {
                /*System.out.println("Latitude-----> " + lat);
                System.out.println("Longitude-----> " + longi);
                System.out.println("Cidade-----> " + nameCity);
                System.out.println("ID BRANDS-----> " + idsBrands);
                System.out.println("ID LITERS-----> " + idsLiters);*/
                List<CompanyPublicApi> lstCompanies = companyBusiness.getCompaniesByLatLongUfNameBrandsLiters(lat, longi, nameCity, idsBrands, idsLiters);
                if (null != lstCompanies) {
                    //lstProd.stream().forEach(comp -> comp.setPaymentTypeList(companyBusiness.getPaymentsTypesByCompany(comp.getId())));
                    lstCompanies.stream().forEach(comp -> comp.setPaymentTypeList(UtilsCompany.convertTypes(comp.getPty())));
                    ret = lstCompanies;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    @GET
    @Path("/p/{id}")
    @Produces("application/json")
    public Company getProductById(@PathParam("id") long id) {
        Company prod = null;
        try {
            //prod = companyBusiness.getProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prod;
    }

    @POST
    @Path("/s")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Return join(Company company, @QueryParam("cd") String cd) {
        Return orderRet = new Return();
        try {
            if(null != cd){
                Cidade c = this.cityBusiness.getCityByName(cd, company.getUfInitials());
                if(null != c && c.getIdCity() != 0){
                    company.setCidade(c);
                } else{
                    c = new Cidade();
                    c.setIdCity(0);
                    company.setCidade(c);
                }
                //Consultando a Latitude e Longitude
                String adress = company.getAdress();
                adress = adress.concat(" - ");
                adress = adress.concat(company.getNumber());
                adress = adress.concat(", ");
                adress = adress.concat(company.getBairro());
                adress = adress.concat(", ");
                adress = adress.concat(cd);
                adress = adress.concat(" - ");
                adress = adress.concat(company.getUfInitials());
                JSONObject location = QueryLatitudeLongitude.getLatLangByAdress(adress);
                if(null != location){
                   /* System.out.println( "Lat = "+location.get("lat"));
                    System.out.println( "Lng = "+location.get("lng"));
                    System.out.println(location);*/
                   if(null != location.get("lat")) {
                       company.setLatitude(Double.parseDouble(location.get("lat").toString()));
                   }
                   if(null != location.get("lng")) {
                        company.setLongitude(Double.parseDouble(location.get("lng").toString()));
                    }
                }
                //Habilitando a Empresa
                company.setEnabled(1);

                // SALVAR A LISTA DE MODULOS DA EMPRESA
                List<Module> lstMod = new ArrayList<>();
                lstMod.add(new Module(1));
                lstMod.add(new Module(2));
                lstMod.add(new Module(3));
                lstMod.add(new Module(4));
                lstMod.add(new Module(5));
                lstMod.add(new Module(6));
                company.setModules(lstMod);
                companyBusiness.save(company);
                //Salvar o usuário padrão aqui
                User user1 = new User();
                user1.setName(company.getResponsavel());
                user1.setUserName(company.getEmailCadastro());
                user1.setPerfil("S_ADM");
                user1.setPassword(company.getCnpj().substring(0,8));
                user1.setIdCompany(company.getIdCompany());
                userBusiness.save(user1);
                orderRet.setRet("S;");
            }else {
                orderRet.setRet("N;");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderRet;
    }

    @GET
    @Path("/q/latlang")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject queryLatLang(@QueryParam("cn") String cd) {
        JSONObject ret = null;
        try {
            if(null != cd){
                JSONObject location = QueryLatitudeLongitude.getLatLangByAdress(cd);
                if(null != location){
                    ret = location;
                }
            }else {
                ret = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @GET
    @Path("/q/cnpj")
   // @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean queryCNPJ(Company company, @QueryParam("cn") String cd) {
        Boolean ret = true;
        try {
            if(null != cd){
                Company c = this.companyBusiness.getCompanyByCNPJ(cd);
                if(null != c && (null != c.getCnpj() || !c.getCnpj().equals(""))){
                    ret = true;
                } else{
                    ret = false;
                }
            }else {
                ret = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @GET
    @Path("/q/mail")
   // @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean queryMail(Company company, @QueryParam("ma") String cd) {
        Boolean ret = true;
        try {
            if(null != cd){
                User c = this.userBusiness.getUserByMail(cd);
                if(null != c && (null != c.getUserName() || !c.getUserName().equals(""))){
                    ret = true;
                } else{
                    ret = false;
                }
            }else {
                ret = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
