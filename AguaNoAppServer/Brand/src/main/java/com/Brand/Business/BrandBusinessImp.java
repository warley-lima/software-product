package com.Brand.Business;

import com.Brand.Model.Brand;
import com.Brand.Repository.BrandRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BrandBusinessImp implements BrandBusiness{
    @Inject
    private BrandRepository repository;
    @Override
    public List<Brand> getListBrand() {
        return repository.findAll();
    }
}
