package com.Company.Business;

import com.Brand.Model.Brand;
import com.Company.Model.CompanyBrands;
import com.Company.Repository.CompanyBrandsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyBrandsBusinessImp implements CompanyBrandsBusiness {

    @Inject
    private CompanyBrandsRepository repository;

    @Transactional
    @Override
    public void save(List<CompanyBrands> module) {
        repository.saveAll(module);
    }

    @Transactional
    @Override
    public void delete(long idCompany ,int idBrand) {
        repository.deleteByIdCompanyAndIdBrand(idCompany, idBrand);
    }

    @Transactional
    @Override
    public CompanyBrands getCompanyBrandsById(long idCompany) {
        return repository.findById(idCompany);
    }

    @Transactional
    @Override
    public List<CompanyBrands> getCompanyBrandsByIdCompany(long idCompany) {
        return repository.findByIdCompany(idCompany);
    }

    @Transactional
    @Override
    public Page<Brand> getBrandsByIdCompany(long idCompany, Pageable pageable) {
        return repository.getBrandByIdCompany(idCompany,pageable);
    }


    @Transactional
    @Override
    public List<Brand> getBrandsByIdCompany(long idCompany) {
        return repository.getBrandByIdCompany(idCompany);
    }
}
