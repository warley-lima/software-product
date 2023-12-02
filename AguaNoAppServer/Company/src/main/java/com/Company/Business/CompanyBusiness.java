package com.Company.Business;

// import com.Brand.Model.Brand;
import com.Company.Model.Company;
import com.Company.Model.CompanyActuaction;
import com.Company.Model.CompanyPaymentTypes;
import com.Company.Model.CompanyPublicApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;


public interface CompanyBusiness {
    Company getCompanyById(long id);
    void save(Company company);
    void loadCompanies();
    void loadTypePayments();
    List<Company> getCompaniesByLatLong(double latitude, double longitude);
    List<Company> getCompaniesByIdCity(long id);
    List<Company> getCompaniesByNameCity(String name);
    List<Company> getCompaniesByLatLongCityName(double latitude, double longitude, String nameCity);
    List<CompanyPublicApi> getCompaniesByLatLongUfName(BigDecimal latitude, BigDecimal longitude, String uf);
    List<CompanyPublicApi> getCompaniesByLatLongUfNameBrands(BigDecimal latitude, BigDecimal longitude, String uf, String idBrands);
    List<CompanyPublicApi> getCompaniesByLatLongUfNameLiters(BigDecimal latitude, BigDecimal longitude, String uf, String idLiters);
    List<CompanyPublicApi> getCompaniesByLatLongUfNameBrandsLiters(BigDecimal latitude, BigDecimal longitude, String uf, String idBrands, String idLiters);
    List<CompanyPaymentTypes> getPaymentsTypesByCompany(long idCompany);
    Company getCompanyByCNPJ(String cnpj);
   // List<Brand> getBrandsByIdCompany(long idCompany);
    void saveBrands(Company company);
   // void deleteBrands(long idProd);
}
