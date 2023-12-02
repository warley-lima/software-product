package com.Company.Business;

import com.Company.Model.CompanyLiters;
import com.Company.Repository.CompanyLitersRepository;
import com.Products.Model.Liters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyLitersBusinessImp implements CompanyLitersBusiness {

    @Inject
    private CompanyLitersRepository repository;

    @Transactional
    @Override
    public void save(List<CompanyLiters> module) {
        repository.saveAll(module);
    }

    @Transactional
    @Override
    public void delete(long idCompany ,int idBrand) {
        repository.deleteByIdCompanyAndIdLiters(idCompany, idBrand);
    }

    @Transactional
    @Override
    public CompanyLiters getCompanyLitersById(long idCompany) {
        return repository.findById(idCompany);
    }

    @Transactional
    @Override
    public List<CompanyLiters> getCompanyLitersByIdCompany(long idCompany) {
        return repository.findByIdCompany(idCompany);
    }

    @Transactional
    @Override
    public Page<Liters> getLitersByIdCompany(long idCompany, Pageable pageable) {
        return repository.getLitersByIdCompany(idCompany,pageable);
    }
}
