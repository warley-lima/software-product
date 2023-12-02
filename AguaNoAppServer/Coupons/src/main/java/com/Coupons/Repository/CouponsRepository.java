package com.Coupons.Repository;

import com.Coupons.Model.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface CouponsRepository extends JpaRepository<Coupons,Long> {
    /*@Modifying
    @Transactional
    @Query("delete from Rule p where p.idUserFK =?1 ")
    void deleteByIdUserFK(long idUser);*/

    @Async
    Page<Coupons> findByIdCompanyOrderById(long idCompany, Pageable pageable);
    @Async
    //@Query("select p from Coupons p where p.idCompany =?1 and p.valor like %?2% order by p.valor")
    Page<Coupons> findByIdCompanyAndValorContainingIgnoreCase(long idCompany, String name, Pageable pageable);
    @Async
    Coupons findById(long idUser);
    @Async
   // Coupons findByIdCompanyAndValorAndDataInicioIsBetweenAndDataFimIsBetween(long idUser, String valor, Date data);
    Coupons findByIdCompanyAndValorAndDataInicioBeforeAndDataFimAfter(long idUser, String valor, Date dataI, Date dataF);
}
