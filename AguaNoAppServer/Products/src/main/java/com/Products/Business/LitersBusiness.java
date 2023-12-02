package com.Products.Business;

import com.Products.Model.Liters;

import java.util.List;

public interface LitersBusiness {
     //Product getProductById(long id);
     void save(Liters brand);
     /*void loadProducts();
     List<Product> getProductsByIdBrand(int idBrand);
     List<Product> getProductsByIdCompany(long idCompany);
     List<Product> getProductsByIdCompanyOrderPriceAsc(long idCompany);*/
     List<Liters> getAllLiters();
     Liters getliter(int idProdct);

}
