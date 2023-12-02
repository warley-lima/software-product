package com.Company.Repository;

import com.Brand.Model.Brand;
import com.Company.Model.CompanyBrands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface CompanyBrandsRepository extends JpaRepository<CompanyBrands,Long> {

    @Async
    List<CompanyBrands> findByIdCompany(long idCompany);
    @Async
    @Query("select p from Brand p inner join CompanyBrands cb on cb.idBrand = p.idBrand where cb.idCompany =?1 order by p.brandName")
    Page<Brand> getBrandByIdCompany(long idCompany, Pageable pageable);
    @Async
    @Query("select p from Brand p inner join CompanyBrands cb on cb.idBrand = p.idBrand where cb.idCompany =?1 order by p.brandName")
    List<Brand> getBrandByIdCompany(long idCompany);
    @Async
    void deleteByIdCompanyAndIdBrand(long idCompany, int id);
    @Async
    CompanyBrands findById(long id);


}
