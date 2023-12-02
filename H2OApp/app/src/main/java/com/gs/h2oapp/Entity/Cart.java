package com.gs.h2oapp.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Warley Lima on
 */
public class Cart {

    private List<ItemCart> itens = new ArrayList<>();

    private BigDecimal valorDescExtra = BigDecimal.ZERO;
    private BigDecimal valorDescVista = BigDecimal.ZERO;
    private BigDecimal valorRecebido = BigDecimal.ZERO;
    private BigDecimal valorEntrega = BigDecimal.ZERO;
    private BigDecimal valorDescCumpom = BigDecimal.ZERO;
    private String adress;
    private String number;
    private String bairro;
    private String complemento;
    private String city;
    private String nameCompany;
    private String latitude;
    private String longitude;
    private int idCompany;
    private boolean isDiscounted = false;
    private List<CompanyPaymentTypes> paymentTypeList = null;
    private User user = null;

    public void insert(ItemCart item) {
        BigDecimal result = BigDecimal.ZERO;
        for (ItemCart itm : itens) {
            if (itm.equals(item)) {
                result = item.getProduct().getQuantProdutoAddCart();
                itm.setQuantidade(result.add(itm.getQuantidade()));
                return;
            }
        }
        getItens().add(item);
    }

    public boolean empty() {
        if (getItens().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public BigDecimal getValorTotal() {
        BigDecimal result;
        if(false == isDiscounted){
            result = (getValor().subtract(getValorDescCumpom())).add(getValorEntrega());
        }else{
            result = getValor().add(getValorEntrega());
        }
        return result;
    }

    public BigDecimal getValor() {
        BigDecimal result = BigDecimal.ZERO;
        for (ItemCart item : itens) {
            result = result.add((item.getValor()));
        }
        return result;
    }

    public BigDecimal getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(BigDecimal valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public BigDecimal getValorDescExtra() {
        return valorDescExtra;
    }

    public void setValorDescExtra(BigDecimal vD) {
        this.valorDescExtra = vD;
    }

    public BigDecimal getValorDescVista() {
        return valorDescVista;
    }

    public void setValorDescVista(BigDecimal valorDescVista) {
        this.valorDescVista = valorDescVista;
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public BigDecimal getValorTotalDesconto() {
        BigDecimal result = BigDecimal.ZERO;
        for (ItemCart item : itens) {
            result = result.add(item.getValorSubTotalDesconto());
        }
        return result;
    }

    public BigDecimal getQuantidadeTotal() {
        BigDecimal result = BigDecimal.ZERO;
        for (ItemCart item : itens) {
            result = result.add(item.getQuantidade());
        }
        return result;
    }

    public void delete(ItemCart item) {
        getItens().remove(item);
    }

    public void update(ItemCart item) {
        /*getItens().remove(item);
        getItens().add(item);*/

        BigDecimal result = BigDecimal.ONE;
        for (ItemCart itm : itens) {
            if (itm.equals(item)) {
                //result = item.getProduct().getQuantProdutoAddCart();
                //itm.setQuantidade(itm.getQuantidade().add(result));
                itm.setQuantidade(itm.getQuantidade().subtract(result));
                return;
            }
        }
        getItens().add(item);
    }

    public List<ItemCart> getItens() {
        itens = Collections.synchronizedList(itens);
        return itens;
    }

    public void setItens(List<ItemCart> itens) {
        this.itens = itens;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<CompanyPaymentTypes> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<CompanyPaymentTypes> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    public BigDecimal getValorDescCumpom() {
        return valorDescCumpom;
    }

    public void setValorDescCumpom(BigDecimal valorDescCumpom) {
        this.valorDescCumpom = valorDescCumpom;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean is) {
        this.isDiscounted = is;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
