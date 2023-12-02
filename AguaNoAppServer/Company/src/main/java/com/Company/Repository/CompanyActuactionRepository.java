package com.Company.Repository;

import com.Company.Model.CompanyActuaction;
import com.Products.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface CompanyActuactionRepository extends JpaRepository<CompanyActuaction,Long> {
   /* @Async
    Company findByIdCompany(long idCompany);
    @Async
    List<Company> findByCidade_Name(String name);
    @Async
    List<Company> findByCidade_IdCity(long idCity);
    @Procedure("somar")
    int testSum(Integer a, Integer b);
   @Procedure("hope8")
   BigDecimal testCaraio(Integer a);*/

    @Async
    Page<CompanyActuaction> findByIdCompanyOrderByDistance(long idCompany, Pageable pageable);

    @Async
    CompanyActuaction findById(long id);


}
