package com.Company.Business;

import com.Brand.Model.Brand;
import com.Company.Model.CompanyBrands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyBrandsBusiness {

    CompanyBrands getCompanyBrandsById(long id);
    List<CompanyBrands> getCompanyBrandsByIdCompany(long idCompany);
    Page<Brand> getBrandsByIdCompany(long idCompany, Pageable pageable);
    List<Brand> getBrandsByIdCompany(long idCompany);
    void save(List<CompanyBrands> company);
    void delete(long idCompany, int idBrand);

}
