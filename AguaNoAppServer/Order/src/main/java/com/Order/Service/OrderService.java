package com.Order.Service;


import com.Order.Business.OrderBusiness;
import com.Order.Model.Order;
import com.Order.Model.OrderTable;
import com.Order.Model.Return;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DecimalFormat;

@Component
//@Service
@Path("/order")
public class OrderService {
   private final OrderBusiness orderBusiness;
   private OAuth2Authentication auth;
   public OrderService(OrderBusiness ordBusiness) {
       this.orderBusiness = ordBusiness;
   }

   @GET
   @Path("/lstorder")
   @Produces(MediaType.APPLICATION_JSON)
   @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
   public Page<OrderTable> getOrders(@QueryParam("l") int lim, @QueryParam("p") int pag){
       Page<OrderTable> lstTable = null;
       try {
           auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
           if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               Pageable pageable = new PageRequest(pag, lim);
               Page<Order> ret = orderBusiness.getOrdersByIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable);
               if(null != ret){
                   lstTable = orderBusiness.getLstTable(ret, pageable);
                  /* List<OrderTable> finalLstTable = new ArrayList<>();
                   ret.getContent().stream().forEach(o -> finalLstTable.add(
                           new OrderTable(o.getIdOrder(),
                           o.getNameClient(), o.getFoneClient(),
                           o.getValorTotal(), orderBusiness.nameStatus(o.getStatus()),
                           o.getDataPedidoServer())));
                //   lstTable = finalLstTable;
                  // lstTable.getContent().addAll(finalLstTable);
                   //lstTable =  ret;

                   lstTable =  new PageImpl<>(finalLstTable, pageable, ret.getTotalElements());
                 //  lstTable =  new PageImpl<>(finalLstTable, pageable, finalLstTable.size());*/

               }
              // lstTable = new ArrayList<>();
               else{
                   lstTable = null;
               }
           }
           else{
               lstTable = null;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return lstTable;
   }


   @GET
   @Path("/lstordersearch")
   @Produces(MediaType.APPLICATION_JSON)
   @PreAuthorize("#oauth2.hasScope('ADM') or #oauth2.hasScope('S_ADM') or (#oauth2.hasScope('USER') and #oauth2.hasScope('1{R}') or #oauth2.hasScope('3{R}')) "  )
   public Page<OrderTable> getOrdersBySearch(@QueryParam("l") int lim, @QueryParam("p") int pag, @QueryParam("n") String name) {
       Page<OrderTable> lstTable = null;
       try {
           auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
           if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               Pageable pageable = new PageRequest(pag, lim);
               Page<Order> ret = orderBusiness.getOrderSearchByIdCompanyAndName(Integer.parseInt(auth.getOAuth2Request().getClientId()), pageable, name);
               if(null != ret){
                  lstTable = orderBusiness.getLstTable(ret, pageable);
               }
               else{
                   lstTable = null;
               }
           }
           else{
               lstTable = null;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return lstTable;
   }

   @PUT
   @Path("/u")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Return updateOrder(OrderTable order) {
       Return orderRet = new Return();
       try {
           auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
           if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
             //  order.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
               Order o = new Order();
               o.setIdCompany(Integer.parseInt(auth.getOAuth2Request().getClientId()));
               o.setIdOrder(order.getIdOrder());
               o.setStatus(Integer.parseInt(order.getStatusOrder()));
             int r =  orderBusiness.update(o);
             if( 1 == r) {
                 System.out.println("RETORNO DO UPDATE----------->" + r);
                 orderRet.setRet("S;");
             }
             else{
                 orderRet.setRet("N; 0");
             }
           }
           else{
               orderRet.setRet("N;");
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return orderRet;
   }

   @GET
   @Path("/o/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Order getOrder(@PathParam("id") long id) {
      Order ret = null;
       try {
           auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
           if(null != auth.getOAuth2Request().getClientId() && auth.getOAuth2Request().getClientId().matches("[0-9]+") ){
               ret = orderBusiness.getOrder(Integer.parseInt(auth.getOAuth2Request().getClientId()), id);
               ret.setStatusName( orderBusiness.nameStatus(ret.getStatus()));
               DecimalFormat twoPlaces = new DecimalFormat("0.00");
             //  System.out.println(twoPlaces.format(ret.getValorTaxaEntrega()));
              // List<ProductOrder> lstProdsOrders = ret.getLstProduct();
             //  ret.setLstProduct(lstProdsOrders);
               ret.setTaxaEntrega(twoPlaces.format(ret.getValorTaxaEntrega()));
               ret.setValorTotal2(twoPlaces.format(ret.getValorTotal()));
           }
           else{
               ret = null;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return ret;
   }


}
