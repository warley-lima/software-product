package com.Order.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
public class ProductOrder {
    @GenericGenerator(
            name = "orderProductSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ORDER_PRODUCT_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "orderProductSequenceGenerator")
    @Id
    @JsonIgnore
    @Column(name = "id_order_product", updatable = false, nullable = false)
    private int id;

    /*@Column(name = "id_order",  nullable = false)
    private long idOrderFK;*/
    @JsonIgnore
    @ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name="id_order_pk", nullable=false)
    private Order order;

  //  @JsonIgnore
    @Column(name = "id_company", nullable = false)
    private long idCompany;
  //  @JsonProperty("idBrand")
    @Column(name = "id_brand", updatable = false, nullable = false)
    private long idBrand;
    @Column(name = "id_product", updatable = false, nullable = false)
   // @JsonIgnore
  //  @JsonProperty("idProduct")
    private long idProduct;
    @Column(name = "name_product", updatable = false, nullable = false)
  //  @JsonProperty("nameProduct")
    private String nameProduct;
    @Column(name = "liters_product", updatable = false, nullable = false)
  //  @JsonProperty("liters")
    private float liters;
    @Column(name = "quantity_product", updatable = false, nullable = false)
  //  @JsonProperty("quantidade")
    private float quantidade;
    @Column(name = "price_unit_product", updatable = false, nullable = false)
   // @JsonProperty("precoUnitarioProduto")
    private float precoUnitarioProduto;

    public ProductOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order o) {
        this.order = o;
    }

    /* public long getIdOrderFK() {
        return idOrderFK;
    }

    public void setIdOrderFK(long idOrderFK) {
        this.idOrderFK = idOrderFK;
    }
    */

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(long idBrand) {
        this.idBrand = idBrand;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getLiters() {
        return liters;
    }

    public void setLiters(float liters) {
        this.liters = liters;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrecoUnitarioProduto() {
        return precoUnitarioProduto;
    }

    public void setPrecoUnitarioProduto(float precoUnitarioProduto) {
        this.precoUnitarioProduto = precoUnitarioProduto;
    }
}
