package com.Products.Business;

import com.Products.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductBusiness {
     Product getProductById(long id);
     void save(Product brand);
     void update(Product brand);
     void delete(long idProd);
     void loadProducts();
     List<Product> getProductsByIdBrand(int idBrand);
     List<Product> getProductsByIdCompany(long idCompany);
     Page<Product> getProductsByIdCompany(long idCompany, Pageable pageable);
     Page<Product> getProductsSearchByIdCompany(long idCompany, Pageable pageable, String name);
     List<Product> getProductsByIdCompanyOrderPriceAsc(long idCompany);
     List<Product> getProductsByIdCompanyOrderPriceDes(long idCompany);
     Product getProduct(long idProdct);

}
