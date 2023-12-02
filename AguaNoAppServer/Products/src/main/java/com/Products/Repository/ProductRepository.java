package com.Products.Repository;

import com.Products.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Async
    List<Product> findByIdBrand(int idBrand);

    @Async
     List<Product> findByIdCompanyOrderByProductName(long idCompany);
    @Async
   // List<Product> findByIdCompanyOrderByProductName(long idCompany);
    Page<Product> findByIdCompanyOrderByProductName(long idCompany, Pageable pageable);

    @Async
    //@Query("select p from Product p where p.idCompany =?1 and p.productName like %?2% order by p.productName")
    Page<Product> findByIdCompanyAndProductNameContainingIgnoreCase(long idCompany, String name, Pageable pageable);
    @Async
    List<Product> findByIdCompanyOrderByPriceDesc(long idCompany);
    @Async
    List<Product> findByIdCompanyOrderByPriceAsc(long idCompany);
    @Async
    Product findByIdProduct(long idProdct);


    /* @Async
    List<Product> findByCompany_IdCompanyOrderByProductName(long idCompany);
    @Async
    List<Product> findByCompany_IdCompanyOrderByPriceDesc(long idCompany);
    @Async
    List<Product> findByCompany_IdCompanyOrderByPriceAsc(long idCompany);
    */
}
