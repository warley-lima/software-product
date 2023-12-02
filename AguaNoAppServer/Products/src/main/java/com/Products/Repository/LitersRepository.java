package com.Products.Repository;

import com.Products.Model.Liters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LitersRepository extends JpaRepository<Liters,Integer> {
    //@Async
    //List<Liters> findBy(int idBrand);
   /* @Async
    List<Product> findByIdCompanyOrderByProductName(long idCompany);
    @Async
    List<Product> findByIdCompanyOrderByPriceDesc(long idCompany);
    @Async
    List<Product> findByIdCompanyOrderByPriceAsc(long idCompany);
    @Async
    Product findByIdProduct(long idProdct);*/

}
