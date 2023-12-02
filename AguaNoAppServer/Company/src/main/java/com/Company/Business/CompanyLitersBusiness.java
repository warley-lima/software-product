package com.Company.Business;

import com.Brand.Model.Brand;
import com.Company.Model.CompanyBrands;
import com.Company.Model.CompanyLiters;
import com.Products.Model.Liters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyLitersBusiness {

    CompanyLiters getCompanyLitersById(long id);
    List<CompanyLiters> getCompanyLitersByIdCompany(long idCompany);
    Page<Liters> getLitersByIdCompany(long idCompany, Pageable pageable);
    void save(List<CompanyLiters> company);
    void delete(long idCompany, int idLiter);

}
