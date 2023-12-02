package com.gs.h2oapp.Business;

import com.gs.h2oapp.Entity.Cart;
import com.gs.h2oapp.Entity.CompanyPaymentTypes;
import com.gs.h2oapp.Entity.ItemCart;
import com.gs.h2oapp.Entity.PrecoMetadeProduto;
import com.gs.h2oapp.Entity.PrecoQuantidadeUnidades;
import com.gs.h2oapp.Entity.Product;
import com.gs.h2oapp.Entity.User;
import com.gs.h2oapp.Entity.VendaCasadaProduto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Warley Lima 
 */
public class CarrinhoBusinessImp implements CarrinhoBusiness  {
    private static Cart cart;
    private ItemCart item;
    private List<PrecoQuantidadeUnidades> lstPreQuaUniProd;
    private List<PrecoMetadeProduto> lstPreMetProd;
    private List<VendaCasadaProduto> lstCompraConjunta;
    private boolean testeDescPromo = false;
    private boolean produtoPai = false;
   // private boolean isDiscounted = false;
    private ProductBusiness prodBuss = null;
    private PrecoQuantidadeUnidadesBusiness preQuaUniProdBuss = null;
    private VendaCasadaProdutoBusiness compraConjuntaBus = null;

    public CarrinhoBusinessImp() {
        if (cart == null) {
            cart = new Cart();
        }
        if (prodBuss == null) {
            prodBuss = new ProductBusinessImp();
        }
        if (preQuaUniProdBuss == null) {
            preQuaUniProdBuss = new PrecoQuantidadeUnidadesBusinessImp();
        }
        if (compraConjuntaBus == null) {
            compraConjuntaBus = new VendaCasadaProdutoBusinessImp();
        }
    }

    public static Cart getCart() {
        return cart;
    }

    public static void setCart(Cart c) {
        CarrinhoBusinessImp.cart = new Cart();
    }
    @Override
    public List<CompanyPaymentTypes> getPaymentTypeList() {
        return CarrinhoBusinessImp.cart.getPaymentTypeList();
    }
    @Override
    public void setPaymentTypeList(List<CompanyPaymentTypes> paymentTypeList) {
        CarrinhoBusinessImp.cart.setPaymentTypeList(paymentTypeList);
    }

    @Override
    public BigDecimal getValorEntrega() {
        return CarrinhoBusinessImp.cart.getValorEntrega();
    }

    @Override
    public void setValorEntrega(BigDecimal valorEntrega) {
        CarrinhoBusinessImp.cart.setValorEntrega(valorEntrega);
    }

    @Override
    public boolean getIsDiscounted() {
        return CarrinhoBusinessImp.cart.isDiscounted();
    }

    @Override
    public void setIsDiscounted(boolean is) {
        CarrinhoBusinessImp.cart.setDiscounted(is);
    }

    @Override
    public BigDecimal getValorDescontoCupom() {
        return CarrinhoBusinessImp.cart.getValorDescCumpom();
    }

    @Override
    public void setValorDescontoCupom(BigDecimal valor) {
        CarrinhoBusinessImp.cart.setValorDescCumpom(valor);
    }

    @Override
    public String getNameCompany() {
        return CarrinhoBusinessImp.cart.getNameCompany();
    }

    @Override
    public void setNameCompany(String nameCompany) {
        CarrinhoBusinessImp.cart.setNameCompany(nameCompany);
    }

    @Override
    public int getIdCompany() {
        return CarrinhoBusinessImp.cart.getIdCompany();
    }

    @Override
    public void setIdCompany(int idCompany) {
        CarrinhoBusinessImp.cart.setIdCompany(idCompany);
    }

    @Override
    public String getAdress() {
        return CarrinhoBusinessImp.cart.getAdress();
    }

    @Override
    public void setAdress(String adress) {
        CarrinhoBusinessImp.cart.setAdress(adress);
    }

    @Override
    public String getBairro() {
        return CarrinhoBusinessImp.cart.getBairro();
    }

    @Override
    public void setBairro(String bairro) {
        CarrinhoBusinessImp.cart.setBairro(bairro);
    }

    @Override
    public String getComplemento() {
        return CarrinhoBusinessImp.cart.getComplemento();
    }

    @Override
    public void setComplemento(String complemento) {
        CarrinhoBusinessImp.cart.setComplemento(complemento);
    }

    @Override
    public String getCity() {
        return CarrinhoBusinessImp.cart.getCity();
    }

    @Override
    public void setCity(String city) {
        CarrinhoBusinessImp.cart.setCity(city);
    }

    @Override
    public String getNumber() {
        return CarrinhoBusinessImp.cart.getNumber();
    }

    @Override
    public void setNumber(String number) {
        CarrinhoBusinessImp.cart.setNumber(number);
    }

    @Override
    public String getLatitude() {
        return CarrinhoBusinessImp.cart.getLatitude();
    }

    @Override
    public void setLatitude(String latitude) {
        CarrinhoBusinessImp.cart.setLatitude(latitude);
    }

    @Override
    public String getLongitude() {
        return CarrinhoBusinessImp.cart.getLongitude();
    }

    @Override
    public void setLongitude(String longitude) {
        CarrinhoBusinessImp.cart.setLongitude(longitude);
    }

    @Override
    public boolean empty() {
        return CarrinhoBusinessImp.cart.empty();
    }

    @Override
    public BigDecimal getValor() {
        return CarrinhoBusinessImp.cart.getValor();
    }

    @Override
    public BigDecimal getValorTotal() {
        return CarrinhoBusinessImp.cart.getValorTotal();
    }


    @Override
    public List<ItemCart> getItens() {
        return CarrinhoBusinessImp.cart.getItens();
    }

    @Override
    public void setItens(List<ItemCart> itens) {
        cart.setItens(itens);
    }

    @Override
    public void addListProdutoCart(List<Product> lstProd) {
        for (Product produto : lstProd) {
            item = new ItemCart();
            item.setProduct(produto);
            CarrinhoBusinessImp.cart.insert(item);
        }
    }
    @Override
    public int clearCart() {
        int a = 0;
        try {
          //  CarrinhoBusinessImp.cart.setItens(null);
            cart.getItens().removeAll(cart.getItens());
            //LIMPANDO O VALOR DA ENTREGA;
           // cart.setValorEntrega(BigDecimal.ZERO);
            a = 7;
        } catch (Exception ignored) {
        }
        return a;
    }

    @Override
    public User getUser() {
        return CarrinhoBusinessImp.cart.getUser();
    }

    @Override
    public void setUser(User u) {
        CarrinhoBusinessImp.cart.setUser(u);
    }

    @Override
    public void insertItemCarrinho(Product produto) {
        item = new ItemCart();
        testeDescPromo = false;
        produtoPai = false;
        boolean produtoInCart = false;
        lstPreQuaUniProd = preQuaUniProdBuss.getLstPrecoQuantidadeUnidades(produto.getIdP());
        VendaCasadaProduto pcp = new VendaCasadaProduto();
        pcp.setValorDesconto(BigDecimal.ZERO);
        pcp.setDescontado(false);
        PrecoMetadeProduto pmp = new PrecoMetadeProduto();
        pmp.setDescontado(false);
        if (getItens().isEmpty()) {
            produto.setVendaCasada(pcp);
            produto.setPrecoMetadeProduto(pmp);
            produto.setValorDescontoPrecoCasada(BigDecimal.ZERO);
            for (int i = 0; i < lstPreQuaUniProd.size(); i++) {
               if (produto.getQuantProdutoAddCart().compareTo(new BigDecimal(lstPreQuaUniProd.get(i).getQuantidadeUnidades())) >= 0 && produto.getQuantProdutoAddCart().compareTo(new BigDecimal(lstPreQuaUniProd.get(i).getQuantidadeUnidadesMax())) <= 0) {
                    produto.setValorDescontoPrecoQuantUnidades(produto.getPrecoUnitarioProduto().subtract(lstPreQuaUniProd.get(i).getPrecoUnitarioPorQuantidade()));
                    produto.setPrecoUnitarioProduto(lstPreQuaUniProd.get(i).getPrecoUnitarioPorQuantidade());
                    i = lstPreQuaUniProd.size();
                }
            }
            if (produto.getTpDescontoPromocionalProduto() == 1 && !testeDescPromo) {
                produto.setValorDescontoPromocao(produto.getDescontoPromocionalProduto());
                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
            } else if (produto.getDescontoPromocionalProduto().compareTo(BigDecimal.ZERO) > 0 && produto.getTpDescontoPromocionalProduto() == 2 && !testeDescPromo) {
                produto.setValorDescontoPromocao(produto.getPrecoUnitarioProduto().multiply(produto.getDescontoPromocionalProduto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
            }
            item.setQuantidade(produto.getQuantProdutoAddCart());
            item.setProduct(produto);
            CarrinhoBusinessImp.cart.insert(item);
        } else {
            for (int i = 0; i < getItens().size(); i++) {
                if (getItens().get(i).getProduct().getIdP() == produto.getIdP()) {
                    produtoInCart = true;
                }
            }
            if (true == produtoInCart) {
                CarrinhoBusinessImp.cart = insertItemCarrinhoSeta(produto);
            } else {
                produto.setVendaCasada(pcp);
                produto.setPrecoMetadeProduto(pmp);
                 produto.setValorDescontoPrecoCasada(BigDecimal.ZERO);
                for (int j = 0; j < lstPreQuaUniProd.size(); j++) {
                    if (produto.getIdP() == lstPreQuaUniProd.get(j).getFkIdProduto()) {
                        if (produto.getQuantProdutoAddCart().compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidades())) >= 0 && produto.getQuantProdutoAddCart().compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidadesMax())) <= 0) {
                             produto.setValorDescontoPrecoQuantUnidades(produto.getPrecoUnitarioProduto().subtract(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade()));
                            produto.setPrecoUnitarioProduto(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade());
                            if (produto.getTpDescontoPromocionalProduto() == 1) {
                                produto.setValorDescontoPromocao(produto.getDescontoPromocionalProduto());
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                                testeDescPromo = true;
                            } else if (produto.getDescontoPromocionalProduto().compareTo(BigDecimal.ZERO) > 0 && produto.getTpDescontoPromocionalProduto() == 2) {
                                produto.setValorDescontoPromocao(produto.getPrecoUnitarioProduto().multiply(produto.getDescontoPromocionalProduto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                                testeDescPromo = true;
                            }
                            j = lstPreQuaUniProd.size();
                        }
                    }
                }
                if (produto.getTpDescontoPromocionalProduto() == 1 && !testeDescPromo) {
                    produto.setValorDescontoPromocao(produto.getDescontoPromocionalProduto());
                    produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                } else if (produto.getDescontoPromocionalProduto().compareTo(BigDecimal.ZERO) > 0 && produto.getTpDescontoPromocionalProduto() == 2 && !testeDescPromo) {
                    produto.setValorDescontoPromocao(produto.getPrecoUnitarioProduto().multiply(produto.getDescontoPromocionalProduto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                    produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                }
                lstCompraConjunta = compraConjuntaBus.getVendasCasadaProduto(produto.getIdP());
                if (lstCompraConjunta.size() > 0) {
                    for (int i = 0; i < getItens().size(); i++) {
                        for (int j = 0; j < lstCompraConjunta.size(); j++) {
                            if (getItens().get(i).getProduct().getIdP() == lstCompraConjunta.get(j).getIdProdutoRelacionado() && !getItens().get(i).getProduct().getVendaCasada().isDescontado()) {
                                getItens().get(i).getProduct().setVendaCasada(lstCompraConjunta.get(j));
                                if (lstCompraConjunta.get(j).getIdTpDesconto() == 1) {
                                    getItens().get(i).getProduct().setValorDescontoPrecoCasada(lstCompraConjunta.get(j).getValorDesconto());
                                    if (getItens().get(i).getProduct().getValorDescontoPrecoQuantUnidades().compareTo(BigDecimal.ZERO) > 0) {
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto((getItens().get(i).getProduct().getPrecoUnitarioQuantProduto().subtract(getItens().get(i).getProduct().getValorDescontoPromocao())).subtract(getItens().get(i).getProduct().getValorDescontoPrecoCasada()));
                                    } else {
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto((getItens().get(i).getProduct().getPrecoUnitarioNormalProduto().subtract(getItens().get(i).getProduct().getValorDescontoPromocao())).subtract(getItens().get(i).getProduct().getValorDescontoPrecoCasada()));
                                    }
                                    if (getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado()) {
                                        getItens().get(i).getProduct().setValorDescontoPrecoMetade(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getValorDescontoPrecoMetade());
                                    }
                                } else {
                                    if (getItens().get(i).getProduct().getValorDescontoPrecoQuantUnidades().compareTo(BigDecimal.ZERO) > 0) {
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioQuantProduto().subtract(getItens().get(i).getProduct().getValorDescontoPromocao()));
                                        getItens().get(i).getProduct().setValorDescontoPrecoCasada(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(lstCompraConjunta.get(j).getValorDesconto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().subtract(getItens().get(i).getProduct().getValorDescontoPrecoCasada()));
                                    } else {
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioNormalProduto().subtract(getItens().get(i).getProduct().getValorDescontoPromocao()));
                                        getItens().get(i).getProduct().setValorDescontoPrecoCasada(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(lstCompraConjunta.get(j).getValorDesconto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().subtract(getItens().get(i).getProduct().getValorDescontoPrecoCasada()));
                                    }
                                    if (getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado()) {
                                        getItens().get(i).getProduct().setValorDescontoPrecoMetade(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getValorDescontoPrecoMetade());
                                    }
                                }
                                getItens().get(i).getProduct().getVendaCasada().setDescontado(true);
                            } else if (produto.getIdP() == lstCompraConjunta.get(j).getIdProdutoRelacionado() && getItens().get(i).getProduct().getIdP() == lstCompraConjunta.get(j).getIdProduto()) {
                                produto.setVendaCasada(lstCompraConjunta.get(j));
                                if (lstCompraConjunta.get(j).getIdTpDesconto() == 1) {
                                    produto.setValorDescontoPrecoCasada(lstCompraConjunta.get(j).getValorDesconto());
                                    produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoCasada()));
                                } else {
                                    produto.setValorDescontoPrecoCasada(produto.getPrecoUnitarioProduto().multiply(lstCompraConjunta.get(j).getValorDesconto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                    produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoCasada()));
                                }
                                produto.getVendaCasada().setDescontado(true);
                            }
                        }
                    }
                }
                lstPreMetProd = prodBuss.getPrecoMetadeProduto(produto.getIdP());
                if (lstPreMetProd.size() > 0) {
                    for (int i = 0; i < getItens().size(); i++) {
                        for (int a = 0; a < lstPreMetProd.size(); a++) {
                            if (getItens().get(i).getProduct().getIdP() == lstPreMetProd.get(a).getIdProduto() || produto.getIdP() == lstPreMetProd.get(a).getIdProduto()) {
                                produtoPai = true;
                                a = lstPreMetProd.size();
                                i = getItens().size();
                            }
                        }
                    }
                    for (int i = 0; i < getItens().size(); i++) {
                        for (int j = 0; j < lstPreMetProd.size(); j++) {
                            if (getItens().get(i).getProduct().getIdP() == lstPreMetProd.get(j).getIdProdutoRelacionado() && !getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado() && produtoPai) {
                                getItens().get(i).getProduct().setValorDescontoPrecoMetade(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                                getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().subtract(getItens().get(i).getProduct().getValorDescontoPrecoMetade()));
                                getItens().get(i).getProduct().getPrecoMetadeProduto().setDescontado(true);
                            } else if (produto.getIdP() == lstPreMetProd.get(j).getIdProdutoRelacionado() && getItens().get(i).getProduct().getIdP() == lstPreMetProd.get(j).getIdProduto()) {
                                produto.setValorDescontoPrecoMetade(produto.getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoMetade()));
                                produto.setPrecoMetadeProduto(lstPreMetProd.get(j));
                                produto.getPrecoMetadeProduto().setDescontado(true);
                            }
                        }
                    }
                }
                item.setProduct(produto);
                item.setQuantidade(produto.getQuantProdutoAddCart());
                CarrinhoBusinessImp.cart.insert(item);
            }
        }
    }

    @Override
    public Cart insertItemCarrinhoSeta(Product produto) {
        ItemCart item2 = new ItemCart();
        testeDescPromo = false;
        produtoPai = false;
        for (int i = 0; i < getItens().size(); i++) {
            if (produto.getIdP() == getItens().get(i).getProduct().getIdP()) {
                produto.setVendaCasada(getItens().get(i).getProduct().getVendaCasada());
                i = getItens().size();
            }
        }
        lstPreQuaUniProd = preQuaUniProdBuss.getLstPrecoQuantidadeUnidades(produto.getIdP());
        for (int i = 0; i < getItens().size(); i++) {
            for (int j = 0; j < lstPreQuaUniProd.size(); j++) {
                if (getItens().get(i).getProduct().getIdP() == lstPreQuaUniProd.get(j).getFkIdProduto()) {
                     if (new BigDecimal("1.00").add(getItens().get(i).getQuantidade()).compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidades())) >= 0 && getItens().get(i).getQuantidade().compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidadesMax())) <= 0) {
                        produto.setValorDescontoPrecoQuantUnidades(produto.getPrecoUnitarioNormalProduto().subtract(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade()));
                        produto.setPrecoUnitarioProduto(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade());
                        if (produto.getTpDescontoPromocionalProduto() == 1) {
                            produto.setValorDescontoPromocao(produto.getDescontoPromocionalProduto());
                            produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                            testeDescPromo = true;
                        } else if (produto.getDescontoPromocionalProduto().compareTo(BigDecimal.ZERO) > 0 && produto.getTpDescontoPromocionalProduto() == 2) {
                            produto.setValorDescontoPromocao(produto.getPrecoUnitarioProduto().multiply(produto.getDescontoPromocionalProduto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                            produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                            testeDescPromo = true;
                        }
                        if (produto.getVendaCasada().getIdTpDesconto() == 1) {
                            produto.setValorDescontoPrecoCasada(produto.getVendaCasada().getValorDesconto());
                        } else {
                            produto.setValorDescontoPrecoCasada(produto.getPrecoUnitarioProduto().multiply(produto.getVendaCasada().getValorDesconto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                        }
                        produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoCasada()));
                        getItens().get(i).getProduct().setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto());
                        getItens().get(i).getProduct().setValorDescontoPrecoQuantUnidades(produto.getValorDescontoPrecoQuantUnidades());
                        getItens().get(i).getProduct().setValorDescontoPrecoCasada(produto.getValorDescontoPrecoCasada());
                        getItens().get(i).getProduct().setQuantUnidPrecoQuantProduto(new BigDecimal("1.00").add(getItens().get(i).getQuantidade()));
                        getItens().get(i).getProduct().setPrecoUnitarioQuantProduto(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade());
                        if (getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado()) {
                            getItens().get(i).getProduct().setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                            getItens().get(i).getProduct().setValorDescontoPrecoMetade(produto.getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                            getItens().get(i).getProduct().getPrecoMetadeProduto().setDescontado(true);
                        }
                        i = getItens().size();
                        j = lstPreQuaUniProd.size();
                    }
                }
            }
        }
        item2.setProduct(produto);
        CarrinhoBusinessImp.cart.insert(item2);
        return CarrinhoBusinessImp.cart;
    }

    @Override
    public Cart deleteItemCarrinhoSeta(Product produto) {
        item = new ItemCart();
        testeDescPromo = false;
        produtoPai = false;
        BigDecimal quant = BigDecimal.ZERO;
        for (int i = 0; i < getItens().size(); i++) {
            if (produto.getIdP() == getItens().get(i).getProduct().getIdP()) {
                quant = getItens().get(i).getQuantidade();
                i = getItens().size();
            }
        }
        BigDecimal quantidade = quant;
        if (quantidade.compareTo(BigDecimal.ZERO) == 1) {
            quantidade = quantidade.subtract(new BigDecimal("1.00"));
            if (quantidade.compareTo(BigDecimal.ZERO) == 1) {
                lstPreQuaUniProd = preQuaUniProdBuss.getLstPrecoQuantidadeUnidades(produto.getIdP());
                for (int i = 0; i < getItens().size(); i++) {
                    for (int j = 0; j < lstPreQuaUniProd.size(); j++) {
                        if (getItens().get(i).getProduct().getIdP() == lstPreQuaUniProd.get(j).getFkIdProduto()) {
                            if (quantidade.compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidades())) == -1 && getItens().get(i).getQuantidade().compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidadesMax())) == 1) {
                                produto.setValorDescontoPrecoQuantUnidades(BigDecimal.ZERO);
                                produto.setPrecoUnitarioQuantProduto(BigDecimal.ZERO);
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioNormalProduto());
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                                testeDescPromo = true;
                                if (produto.getValorDescontoPrecoCasada().compareTo(BigDecimal.ZERO) > 0) {
                                    if (produto.getVendaCasada().getIdTpDesconto() == 1) {
                                        produto.setValorDescontoPrecoCasada(produto.getVendaCasada().getValorDesconto());
                                    } else {
                                        produto.setValorDescontoPrecoCasada(produto.getPrecoUnitarioProduto().multiply(produto.getVendaCasada().getValorDesconto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                    }
                                }
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoCasada()));
                                getItens().get(i).getProduct().setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto());
                                if (getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado()) {
                                    produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                                    produto.setValorDescontoPrecoMetade(produto.getPrecoUnitarioProduto());
                                    produto.getPrecoMetadeProduto().setDescontado(true);
                                }
                                i = getItens().size();
                                j = lstPreQuaUniProd.size();
                            }
                        }
                    }
                }
                produto.setQuantUnidPrecoQuantProduto(quantidade);
                item.setProduct(produto);
                item.setQuantidade(quantidade);
                CarrinhoBusinessImp.cart.update(item);
            } else {
                lstCompraConjunta = compraConjuntaBus.getVendasCasadaProduto(produto.getIdP());
                if (lstCompraConjunta.size() > 0) {
                    for (int i = 0; i < getItens().size(); i++) {
                        for (int j = 0; j < lstCompraConjunta.size(); j++) {
                            if (getItens().get(i).getProduct().getIdP() == lstCompraConjunta.get(j).getIdProdutoRelacionado()) {
                                if (lstCompraConjunta.get(j).getIdTpDesconto() == 1) {
                                    getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().add(lstCompraConjunta.get(j).getValorDesconto()));
                                } else {
                                    BigDecimal ca = getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(lstCompraConjunta.get(j).getValorDesconto()).divide((new BigDecimal("100.00").subtract(lstCompraConjunta.get(j).getValorDesconto())), 2, RoundingMode.HALF_DOWN);
                                    getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().add(ca));
                                }
                                getItens().get(i).getProduct().setValorDescontoPrecoCasada(BigDecimal.ZERO);
                                getItens().get(i).getProduct().getVendaCasada().setDescontado(false);
                            }
                        }
                    }
                }
                lstPreMetProd = prodBuss.getPrecoMetadeProduto(produto.getIdP());
                if (lstPreMetProd.size() > 0) {
                    for (int i = 0; i < getItens().size(); i++) {
                        for (int j = 0; j < lstPreMetProd.size(); j++) {
                            if (getItens().get(i).getProduct().getIdP() == lstPreMetProd.get(j).getIdProdutoRelacionado()) {
                                getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(new BigDecimal("2.00")));
                                getItens().get(i).getProduct().setValorDescontoPrecoMetade(BigDecimal.ZERO);
                                getItens().get(i).getProduct().getPrecoMetadeProduto().setDescontado(false);
                            }
                        }
                    }
                }
                item.setProduct(produto);
                CarrinhoBusinessImp.cart.delete(item);
            }
        }
        return CarrinhoBusinessImp.cart;
    }

    @Override
    public Cart deleteItemCarrinho(Product produto) {
        ItemCart item2 = new ItemCart();
        lstCompraConjunta = compraConjuntaBus.getVendasCasadaProduto(produto.getIdP());
        if (lstCompraConjunta.size() > 0) {
            for (int i = 0; i < getItens().size(); i++) {
                for (int j = 0; j < lstCompraConjunta.size(); j++) {
                    if (getItens().get(i).getProduct().getIdP() == lstCompraConjunta.get(j).getIdProdutoRelacionado()) {
                        if (lstCompraConjunta.get(j).getIdTpDesconto() == 1) {
                            getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().add(lstCompraConjunta.get(j).getValorDesconto()));
                        } else {
                            BigDecimal ca = getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(lstCompraConjunta.get(j).getValorDesconto()).divide((new BigDecimal("100.00").subtract(lstCompraConjunta.get(j).getValorDesconto())), 2, RoundingMode.HALF_DOWN);
                            getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().add(ca));
                        }
                        if (getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado()) {
                            getItens().get(i).getProduct().setValorDescontoPrecoMetade(getItens().get(i).getProduct().getPrecoUnitarioProduto());
                        }
                        getItens().get(i).getProduct().setValorDescontoPrecoCasada(BigDecimal.ZERO);
                        getItens().get(i).getProduct().getVendaCasada().setDescontado(false);
                    }
                }
            }
        }
        lstPreMetProd = prodBuss.getPrecoMetadeProduto(produto.getIdP());
        if (lstPreMetProd.size() > 0) {
            for (int i = 0; i < getItens().size(); i++) {
                for (int j = 0; j < lstPreMetProd.size(); j++) {
                    if (getItens().get(i).getProduct().getIdP() == lstPreMetProd.get(j).getIdProdutoRelacionado()) {
                        getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(new BigDecimal("2.00")));
                        getItens().get(i).getProduct().setValorDescontoPrecoMetade(BigDecimal.ZERO);
                        getItens().get(i).getProduct().getPrecoMetadeProduto().setDescontado(false);
                    }
                }
            }
        }
        item2.setProduct(produto);
        CarrinhoBusinessImp.cart.delete(item2);
        return CarrinhoBusinessImp.cart;
    }

   /* @Override
    public Cart updateQuantidadeItemCarrinho(Product produto, BigDecimal quantidade) {
        item = new ItemCart();
        testeDescPromo = false;
        produtoPai = false;
        if (quantidade.compareTo(BigDecimal.ZERO) > 0) {
            lstPreQuaUniProd = preQuaUniProdBuss.getLstPrecoQuantidadeUnidades(produto.getIdP());
            for (int i = 0; i < getItens().size(); i++) {
                for (int j = 0; j < lstPreQuaUniProd.size(); j++) {
                    if (getItens().get(i).getProduct().getIdP() == lstPreQuaUniProd.get(j).getFkIdProduto()) {
                        if (quantidade.compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidades())) >= 0 && quantidade.compareTo(new BigDecimal(lstPreQuaUniProd.get(j).getQuantidadeUnidadesMax())) <= 0) {
                            produto.setValorDescontoPrecoQuantUnidades(produto.getPrecoUnitarioNormalProduto().subtract(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade()));
                            produto.setPrecoUnitarioProduto(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade());
                            if (produto.getTpDescontoPromocionalProduto() == 1) {
                                produto.setValorDescontoPromocao(produto.getDescontoPromocionalProduto());
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                                testeDescPromo = true;
                            } else if (produto.getDescontoPromocionalProduto().compareTo(BigDecimal.ZERO) > 0 && produto.getTpDescontoPromocionalProduto() == 2) {
                                produto.setValorDescontoPromocao(produto.getPrecoUnitarioProduto().multiply(produto.getDescontoPromocionalProduto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPromocao()));
                                testeDescPromo = true;
                            }
                            if (produto.getValorDescontoPrecoCasada().compareTo(BigDecimal.ZERO) > 0) {
                                if (produto.getVendaCasada().getIdTpDesconto() == 1) {
                                    produto.setValorDescontoPrecoCasada(produto.getVendaCasada().getValorDesconto());
                                } else {
                                    produto.setValorDescontoPrecoCasada(produto.getPrecoUnitarioProduto().multiply(produto.getVendaCasada().getValorDesconto()).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_DOWN));
                                }
                            }
                            produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoCasada()));
                            getItens().get(i).getProduct().setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto());
                            getItens().get(i).getProduct().setValorDescontoPrecoQuantUnidades(produto.getValorDescontoPrecoQuantUnidades());
                            getItens().get(i).getProduct().setQuantUnidPrecoQuantProduto(new BigDecimal("1.00").add(getItens().get(i).getQuantidade()));
                            getItens().get(i).getProduct().setPrecoUnitarioQuantProduto(lstPreQuaUniProd.get(j).getPrecoUnitarioPorQuantidade());
                            if (getItens().get(i).getProduct().getPrecoMetadeProduto().isDescontado() == true) {
                                produto.setValorDescontoPrecoMetade(produto.getPrecoUnitarioProduto().multiply(new BigDecimal("0.50")));
                                produto.setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto().subtract(produto.getValorDescontoPrecoMetade()));
                                getItens().get(i).getProduct().setValorDescontoPrecoMetade(produto.getValorDescontoPrecoMetade());
                                getItens().get(i).getProduct().setPrecoUnitarioProduto(produto.getPrecoUnitarioProduto());
                                getItens().get(i).getProduct().getPrecoMetadeProduto().setDescontado(true);
                            }
                            i = getItens().size();
                            j = lstPreQuaUniProd.size();
                        }
                    }
                }
            }
            item.setProduct(produto);
            item.setQuantidade(quantidade);
            CarrinhoBusinessImp.cart.update(item);
        } else {
            lstCompraConjunta = compraConjuntaBus.getVendasCasadaProduto(produto.getIdP());
            if (lstCompraConjunta.size() > 0) {
                for (int i = 0; i < getItens().size(); i++) {
                    for (int j = 0; j < lstCompraConjunta.size(); j++) {
                        if (getItens().get(i).getProduct().getIdP() == lstCompraConjunta.get(j).getIdProdutoRelacionado()) {
                            if (lstCompraConjunta.get(j).getIdTpDesconto() == 1) {
                                getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().add(lstCompraConjunta.get(j).getValorDesconto()));
                            } else {
                                BigDecimal ca = getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(lstCompraConjunta.get(j).getValorDesconto()).divide((new BigDecimal("100.00").subtract(lstCompraConjunta.get(j).getValorDesconto())), 2, RoundingMode.HALF_DOWN);
                                getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().add(ca));
                            }
                            getItens().get(i).getProduct().setValorDescontoPrecoCasada(BigDecimal.ZERO);
                            getItens().get(i).getProduct().getVendaCasada().setDescontado(false);
                        }
                    }
                }
            }
            lstPreMetProd = prodBuss.getPrecoMetadeProduto(produto.getIdP());
            if (lstPreMetProd.size() > 0) {
                for (int i = 0; i < getItens().size(); i++) {
                    for (int j = 0; j < lstPreMetProd.size(); j++) {
                        if (getItens().get(i).getProduct().getIdP() == lstPreMetProd.get(j).getIdProdutoRelacionado()) {
                            getItens().get(i).getProduct().setPrecoUnitarioProduto(getItens().get(i).getProduct().getPrecoUnitarioProduto().multiply(new BigDecimal("2.00")));
                            getItens().get(i).getProduct().setValorDescontoPrecoMetade(BigDecimal.ZERO);
                            getItens().get(i).getProduct().getPrecoMetadeProduto().setDescontado(false);
                        }
                    }
                }
            }
            item.setProduct(produto);
            CarrinhoBusinessImp.cart.delete(item);
        }
        return CarrinhoBusinessImp.cart;
    }*/
}
