package com.Order.Business;

import com.Order.Model.Order;
import com.Order.Model.OrderTable;
import com.Order.Model.ProductOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderBusiness {
    /* Order getProductById(long id);
     void save(Order brand);
     void loadProducts();
     List<Order> getProductsByIdBrand(int idBrand);
     List<Order> getProductsByIdCompany(long idCompany);
     List<Order> getProductsByIdCompanyOrderPriceAsc(long idCompany);
     List<Order> getProductsByIdCompanyOrderPriceDes(long idCompany);
     Order getProduct(long idProdct);*/


    Order getOrderById(long idOrder);
    Order save(Order brand);
    int update(Order brand);
   /* List<Order> getOrdersByIdOrder(long idOrder);
    List<Order> getOrdersByIdCompany(long idCompany);*/
    Page<Order> getOrdersByIdCompany(long idCompany, Pageable pageable);
    Page<Order> getOrderSearchByIdCompanyAndName(long idCompany, Pageable pageable, String name);
    Order getOrder(long idCompany, long idOrder);
    List<ProductOrder> getProductsOrdersByIdOrder(long idCompany, long idOrder);
    String nameStatus(int id);
    Page<OrderTable> getLstTable(Page<Order> ret, Pageable pageable);
   // List<Order> getProductsByIdCompanyOrderPriceAsc(long idCompany);
   // List<Order> getProductsByIdCompanyOrderPriceDes(long idCompany);

}
