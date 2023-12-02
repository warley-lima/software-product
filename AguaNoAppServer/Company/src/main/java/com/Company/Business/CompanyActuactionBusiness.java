package com.Company.Business;

import com.Company.Model.CompanyActuaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyActuactionBusiness {

    CompanyActuaction getCompanyActuactionById(long id);
    Page<CompanyActuaction> getCompanyActuactionByIdCompany(long idCompany, Pageable pageable);
    void save(CompanyActuaction company);
    void update(CompanyActuaction brand);
    void delete(long idProd);

}
