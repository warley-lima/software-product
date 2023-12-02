package com.gs.h2oapp.Business;

import com.gs.h2oapp.Entity.Cart;
import com.gs.h2oapp.Entity.CompanyPaymentTypes;
import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.Entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Warley Lima
 */
public interface CarrinhoBusiness {

    boolean empty();
    BigDecimal getValor();
    //void setValor();
    BigDecimal getValorTotal();
    BigDecimal getValorEntrega();
    void setValorEntrega(BigDecimal valorEntrega);
    boolean getIsDiscounted();
    void setIsDiscounted(boolean is);
    BigDecimal getValorDescontoCupom();
    void setValorDescontoCupom(BigDecimal valor);
    String getAdress();
    void setAdress(String adress);
    String getBairro();
    void setBairro(String bairro);
    String getComplemento();
    void setComplemento(String complemento);
    String getCity();
    void setCity(String city);
    String getNumber();
    void setNumber(String number);
    String getNameCompany();
    void setNameCompany(String nameCompany);
    int getIdCompany();
    void setIdCompany(int idCompany);
    String getLatitude();
    void setLatitude(String latitude);
    String getLongitude();
    void setLongitude(String longitude);
    List<ItemCart> getItens();
    void setItens(List<ItemCart> itens);
    void addListProdutoCart(List<Product> lstProd);
    int clearCart();
    User getUser();
    void setUser(User u);
    List<CompanyPaymentTypes> getPaymentTypeList();
    void setPaymentTypeList(List<CompanyPaymentTypes> paymentTypeList);
    void insertItemCarrinho(Product produto);
    Cart insertItemCarrinhoSeta(Product produto);
    Cart deleteItemCarrinhoSeta(Product produto);
    Cart deleteItemCarrinho(Product produto);
    //Cart updateQuantidadeItemCarrinho(Product produto, BigDecimal quantidade);
}
