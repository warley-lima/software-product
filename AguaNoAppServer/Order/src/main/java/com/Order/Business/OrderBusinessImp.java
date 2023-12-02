package com.Order.Business;

//import com.Company.Business.Company;
import com.Order.Model.Order;
import com.Order.Model.OrderTable;
import com.Order.Model.ProductOrder;
import com.Order.Repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBusinessImp implements OrderBusiness {

   // @Inject
    private OrderRepository repository;

    @Inject
    public OrderBusinessImp(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Order getOrderById(long id) {
        return repository.getOne(id);
    }

    @Transactional
    @Override
    public Order save(Order order) {
        repository.saveAndFlush(order);
       // repository.flush();
        return order;
    }

    @Transactional
    @Override
    public int update(Order order) {
       // repository.update(order.getStatus(), order.getIdOrder());
        // repository.flush();
        return repository.update(order.getStatus(), order.getIdOrder(), order.getIdCompany());
    }

    @Transactional
    @Override
    public Page<Order> getOrdersByIdCompany(long idCompany, Pageable pageable) {
         return repository.findByIdCompanyOrderByIdOrderDesc(idCompany, pageable);
    }

    @Transactional
    @Override
    public Page<Order> getOrderSearchByIdCompanyAndName(long idCompany, Pageable pageable, String name) {
        return repository.findByIdCompanyAndNameClientContainingIgnoreCase(idCompany, name, pageable);
    }

    @Transactional
    @Override
    public Order getOrder(long idCompany, long idOrder) {
        return repository.findByIdCompanyAndIdOrder(idCompany, idOrder);
    }

    @Transactional
    @Override
    public List<ProductOrder> getProductsOrdersByIdOrder(long idCompany, long idOrder) {
        List<ProductOrder> lstProdsOrders = null;
        try{
            Order o = getOrder(idCompany, idOrder);
            if (null != o ){
                lstProdsOrders = o.getLstProduct();
            }
        }
        catch (Exception e){
            System.out.println("FALHA AO PEGAR OS PRODUTOS DO PEDIDO: " + e.getMessage());
        }
        return lstProdsOrders;
    }

    @Override
    public String nameStatus(int id) {
        String ret = "";
        try{
           switch (id) {
               case 1 :
                    ret = "Gerado";
                  break;
               case 2 :
                   ret = "Entregue";
                   break;
               case 3 :
                   ret = "Enviado";
                   break;
               case 4 :
                   ret = "Cancelado";
                   break;
               case 5 :
                   ret = "Devolvido";
                   break;
               default:
                   ret = "Gerado";
                   break;
           }
        }
        catch (Exception e){
            System.out.println("FALHA AO PEGAR OS PRODUTOS DO PEDIDO: " + e.getMessage());
        }
        return ret;
    }

    @Override
    public Page<OrderTable> getLstTable(Page<Order> ret, Pageable pageable) {
        Page<OrderTable> lstTable = null;
        try{
            List<OrderTable> finalLstTable = new ArrayList<>();
            ret.getContent().stream().forEach(o -> finalLstTable.add(
                    new OrderTable(o.getIdOrder(),
                            o.getNameClient(), o.getFoneClient(),
                            o.getValorTotal(), nameStatus(o.getStatus()),
                            o.getDataPedidoServer())));
            lstTable =  new PageImpl<>(finalLstTable, pageable, ret.getTotalElements());
        }
        catch (Exception e){
            System.out.println("FALHA NO getLstTable---> " + e);
        }
        return lstTable;
    }

  /*  @Transactional
    @Override
    public List<Order> getOrdersByIdOrder(long idOrder) {
        return repository.findByIdOrder(idOrder);
    }

    @Transactional
    @Override
    public List<Order> getOrdersByIdCompany(long idCompany) {
        return repository.findByIdCompany(idCompany);
    }*/


}
