package com.Company.Business;

import com.Company.Model.CompanyActuaction;
import com.Company.Repository.CompanyActuactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
public class CompanyActuactionBusinessImp implements CompanyActuactionBusiness {

    @Inject
    private CompanyActuactionRepository repository;

    @Transactional
    @Override
    public void save(CompanyActuaction module) {
        repository.save(module);
    }

    @Transactional
    @Override
    public void update(CompanyActuaction module) {
        repository.saveAndFlush(module);
    }

    @Transactional
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public CompanyActuaction getCompanyActuactionById(long idCompany) {
        return repository.findById(idCompany);
    }

    @Transactional
    @Override
    public Page<CompanyActuaction> getCompanyActuactionByIdCompany(long idCompany, Pageable pageable) {
        return repository.findByIdCompanyOrderByDistance(idCompany, pageable);
    }
}
