package com.Brand.Service;

import com.Brand.Model.Brand;

//@Service
public interface BrandService {
    public abstract Brand getBrandById(int id);
    public abstract void save(Brand brand);
    public abstract void loadBrands();
}
