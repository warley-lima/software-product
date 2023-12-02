package com.Order.Repository;

import com.Order.Model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//public interface OrderRepository{

   /* @Async
    List<Order> findByIdOrder(long idOrder);
    @Async
    List<Order> findByIdOrderOrderByDataPedidoServer(long idOrder);
    @Async
    List<Order> findByIdOrderOrderByDataPedidoServerDesc(long idOrder);
    @Async
    List<Order> findByIdOrderOrderByDataPedidoServerAsc(long idOrder);
    @Async
    List<Order> findByIdCompany(long idCompany);*/

    @Async
    Page<Order> findByIdCompanyOrderByIdOrderDesc(long idCompany, Pageable pageable);
    @Async
   // @Query("select p from Order p where p.idCompany =?1 and p.nameClient like %?2% or p.idOrder =?2 order by p.nameClient")
    //@Query("select p from Order p where p.idCompany =?1 and p.nameClient like lower(concat('%', ?2,'%'))  order by p.nameClient")
    Page<Order> findByIdCompanyAndNameClientContainingIgnoreCase(long idCompany, String name, Pageable pageable);
    @Async
    @Transactional
    @Query("select p from Order p JOIN FETCH p.lstProduct where p.idCompany =?1 and p.idOrder =?2 ")
    Order findByIdCompanyAndIdOrder(long idCompany, long idOrder);

    @Modifying
    @Transactional
    @Query("update Order u set u.status = ?1 where u.idOrder = ?2 and u.idCompany = ?3")
    int update(int idStatus, long idOrder, long idCompany);

     /*@Modifying
    @Transactional
    @Query("delete from Rule p where p.idUserFK =?1 ")
    void deleteByIdUserFK(long idUser);*/


}
