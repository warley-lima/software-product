package com.Order.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "order")
public class Order implements Serializable {
    @GenericGenerator(
            name = "orderSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ORDER_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "orderSequenceGenerator")
    @Id
    @Column(name = "id_order_pk", updatable = false, nullable = false)
    @JsonProperty("idO")
    private long idOrder;

   /* @Transient
   // @Column(name = "id_order_local", nullable = false)
    @JsonIgnore
    private int idOrderClientLocal;*/
    @Column(name = "name_client", nullable = false)
    @JsonProperty("name")
    private String nameClient;
    @Column(name = "fone_client", nullable = false)
    @JsonProperty("fone")
    private String foneClient;
    @Column(name = "number_house", nullable = false)
    @JsonProperty("numberHouse")
    private String numberHouse;
    @Column(name = "complement")
    @JsonProperty("complement")
    private String complement;
    @Column(name = "adress", nullable = false)
    @JsonProperty("adress")
    private String adress;
    @Column(name = "bairro", nullable = false)
    @JsonProperty("bairro")
    private String bairro;
    @Column(name = "city", nullable = false)
    @JsonProperty("city")
    private String city;
    @Column(name = "uf", nullable = false)
    @JsonProperty("uf")
    private String uf;
    @Column(name = "id_company", nullable = false)
    @JsonProperty("idDist")
    private long idCompany;
   // @JsonFormat(pattern = "#,##", locale = "pt_BR" , shape=JsonFormat.Shape.NUMBER)
   // @Column(name = "value_taxa_delivery", nullable = false, precision=10, scale=2)
    @Column(name = "value_taxa_delivery", nullable = false)
    @JsonIgnore
    @JsonProperty("valorTaxaEntrega")
    private float valorTaxaEntrega;
    @Column(name = "value_subtotal", nullable = true, precision=10, scale=2)
    //@JsonIgnore
    @JsonProperty("valorSubTotal")
    private float valorSubTotal;
    @Column(name = "value_total", nullable = false, precision=10, scale=2)
    @JsonIgnore
    @JsonProperty("valorTotal")
    private float valorTotal;
    @Column(name = "cupom_desconto")
    @JsonProperty("cupomDesconto")
    private String cupomDesconto;
    @Column(name = "forma_payment", nullable = false)
    @JsonProperty("formaPagamento")
    private String formaPagamento;
    @Column(name = "troco", updatable = false)
    @JsonProperty("troco")
    private String troco;
    @Column(name = "tips_delivery")
    @JsonProperty("dicasEntrega")
    private String dicasEntrega;
    @Transient
    @JsonIgnore
   // @JsonProperty("dp")
    private String dataPedido;

    @Column(name = "date_order", nullable = false)
    @JsonProperty("dp")
    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy", locale = "pt_BR", timezone="GMT-2")
    private Date dataPedidoServer;
    @Column(name = "status", nullable = false)
    @JsonProperty("st")
    private int status;
    @Column(name = "value_desc", length=40)
    // @JsonIgnore
    @JsonProperty("valorDesconto")
    private String valorDesconto;

    @Transient
    @JsonProperty("stn")
    private String statusName;

    @Transient
    @JsonProperty("tx")
    private String taxaEntrega;
   /* @Transient
    @JsonProperty("tx")
    private String taxaEntrega;*/
    @JsonProperty("vt")
    @Transient
    private String valorTotal2;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy="order")
   // @JoinColumn(name="id_order")
    //@Transient
    @JsonProperty("lstProduct")
    private List<ProductOrder> lstProduct;


    public Order() {
    }

  /*  public Order(String nameClient, String foneClient, String numberHouse, String complement, String adress, String bairro,
                 String city, String uf, long idCompany, float valorTaxaEntrega, float valorSubTotal, float valorTotal,
                 String cupomDesconto, String formaPagamento, String troco, String dicasEntrega, Date dataPedidoServer,
                 String valorDesconto, String taxaEntrega, List<ProductOrder> lstProduct) {
        this.nameClient = nameClient;
        this.foneClient = foneClient;
        this.numberHouse = numberHouse;
        this.complement = complement;
        this.adress = adress;
        this.bairro = bairro;
        this.city = city;
        this.uf = uf;
        this.idCompany = idCompany;
        this.valorTaxaEntrega = valorTaxaEntrega;
        this.valorSubTotal = valorSubTotal;
        this.valorTotal = valorTotal;
        this.cupomDesconto = cupomDesconto;
        this.formaPagamento = formaPagamento;
        this.troco = troco;
        this.dicasEntrega = dicasEntrega;
        this.dataPedidoServer = dataPedidoServer;
        this.valorDesconto = valorDesconto;
        this.taxaEntrega = taxaEntrega;
        this.lstProduct = lstProduct;
    }*/

    public float getValorSubTotal() {
        return valorSubTotal;
    }

    public void setValorSubTotal(float valorSubTotal) {
        this.valorSubTotal = valorSubTotal;
    }

    public String getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(String valorDesc) {
        this.valorDesconto = valorDesc;
    }

    public String getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(String taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public String getValorTotal2() {
        return valorTotal2;
    }

    public void setValorTotal2(String valorTotal2) {
        this.valorTotal2 = valorTotal2;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    /*public int getIdOrderClientLocal() {
        return idOrderClientLocal;
    }

    public void setIdOrderClientLocal(int idOrderClientLocal) {
        this.idOrderClientLocal = idOrderClientLocal;
    }*/

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getFoneClient() {
        return foneClient;
    }

    public void setFoneClient(String foneClient) {
        this.foneClient = foneClient;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public float getValorTaxaEntrega() {
        return valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(float valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(String cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDicasEntrega() {
        return dicasEntrega;
    }

    public void setDicasEntrega(String dicasEntrega) {
        this.dicasEntrega = dicasEntrega;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
   /* public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }*/

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrderPK) {
        this.idOrder = idOrderPK;
    }

    public Date getDataPedidoServer() {
        return dataPedidoServer;
    }

    public void setDataPedidoServer(Date dataPedidoServer) {
        this.dataPedidoServer = dataPedidoServer;
    }

    public List<ProductOrder> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<ProductOrder> lstProduct) {
        this.lstProduct = lstProduct;
    }
}
