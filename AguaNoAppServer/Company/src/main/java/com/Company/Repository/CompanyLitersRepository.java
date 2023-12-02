package com.Company.Repository;

import com.Company.Model.CompanyLiters;
import com.Products.Model.Liters;
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
public interface CompanyLitersRepository extends JpaRepository<CompanyLiters,Long> {

    @Async
    List<CompanyLiters> findByIdCompany(long idCompany);
    @Async
    @Query("select p from Liters p inner join CompanyLiters cb on cb.idLiters = p.id where cb.idCompany =?1 order by p.nameQuantidade")
    Page<Liters> getLitersByIdCompany(long idCompany, Pageable pageable);
    @Async
    void deleteByIdCompanyAndIdLiters(long idCompany, int id);
    @Async
    CompanyLiters findById(long id);


}
