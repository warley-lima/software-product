package com.Company.Model;

import com.Brand.Model.Brand;
import com.Module.Model.Module;
import com.Products.Model.Liters;
import com.Products.Model.Product;
import com.Security.Model.OauthClient;
import com.User.Model.User;
import com.Util.Model.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "company")

/*@NamedStoredProcedureQuery(
        name = "procedureFindAll",
        procedureName = "hope4",
        resultClasses = {Company.class},
        parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)})
*/
/*@NamedStoredProcedureQuery(name = "procedureFindAll",
        procedureName = "hope",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)})
*/
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "procedureFindAll",
        procedureName = "get_companies7",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class)},
        resultSetMappings = "mapCompanies"),
@NamedStoredProcedureQuery(name = "findCompaniesFilterBrand",
                procedureName = "get_companies_by_brands7",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class)},
                resultSetMappings = "mapCompanies"),
        @NamedStoredProcedureQuery(name = "findCompaniesFilterLiters",
                procedureName = "get_companies_by_liters",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class)},
                resultSetMappings = "mapCompanies"),
        @NamedStoredProcedureQuery(name = "findCompaniesFilterBrandsLiters",
                //procedureName = "get_companies_by_brands_liters",
                procedureName = "get_companies_by_brands_and_liters",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = BigDecimal.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class)},
                resultSetMappings = "mapCompanies")

})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "mapCompanies", classes = {
                @ConstructorResult(targetClass = CompanyPublicApi.class, columns = {
                        @ColumnResult(name = "id_comp", type = Long.class),
                        @ColumnResult(name = "name_company", type = String.class),
                        @ColumnResult(name = "id_company_actuaction", type = Long.class),
                        @ColumnResult(name = "tx_delivery", type = BigDecimal.class),
                        @ColumnResult(name = "payment_type_company", type = String.class),
                        @ColumnResult(name = "votes", type = BigDecimal.class),
                        @ColumnResult(name = "avaliation", type = BigDecimal.class)
                })
        })/*,
        @SqlResultSetMapping(name = "maoCompaniesByBrands", classes = {
                @ConstructorResult(targetClass = CompanyPublicApi.class, columns = {
                        @ColumnResult(name = "id_comp", type = Long.class),
                        @ColumnResult(name = "name_company", type = String.class),
                        @ColumnResult(name = "id_company_actuaction", type = Long.class),
                        @ColumnResult(name = "tx_delivery", type = BigDecimal.class)
                })
        })*/
})
public class Company implements Serializable {
    @GenericGenerator(
            name = "companySequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "COMPANY_SEQUENCE"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "companySequenceGenerator")
    @Id
    @Column(name = "id_company", updatable = false, nullable = false)
    private long idCompany;
    private int enabled;
    private static final long serialVersionUID = 1L;
    @Column(name = "social_name", nullable = false)
    @JsonProperty("sc")
    private  String socialName;
    @Column(name = "fantasy_name",nullable = false)
    @JsonProperty("fn")
    private  String fantasyName;
    @Column(name = "state",nullable = false)
    @JsonProperty("uf")
    private  String ufInitials;
    @Column(name = "adress", nullable = false)
    @JsonProperty("ad")
    private  String adress;
    @Column(name = "number", nullable = false)
    @JsonProperty("nu")
    private  String number;
    @Column(name = "bairro", nullable = false)
    @JsonProperty("ba")
    private  String bairro;
    @Column(name = "fone", nullable = false)
    @JsonProperty("te")
    private  String fone;
    @Column(name = "cep", nullable = false)
    @JsonProperty("ce")
    private  String cep;
    @Column(name = "responsavel", nullable = false)
    @JsonProperty("re")
    private  String responsavel;
    @Column(name = "cnpj", nullable = false)
    @JsonProperty("cnpj")
    private  String cnpj;
    @JsonProperty("ma")
    @Transient
    private  String emailCadastro;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="company_modules", joinColumns={@JoinColumn(referencedColumnName="id_company")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id_module")})
    @JsonIgnore
    private List<Module> modules;

   /* @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name="company_brands", joinColumns={@JoinColumn(referencedColumnName="id_company")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id_brand")})
    @JsonIgnore
    private List<Brand> brands;
    */

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_company")
    @JsonIgnore
    private List<CompanyBrands> companyBrands;


    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_company")
    @JsonIgnore
    private List<CompanyLiters> liters;


   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="company_liters", joinColumns={@JoinColumn(referencedColumnName="id_company")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id_litters")})
    @JsonIgnore
    private List<Liters> liters;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="company_scores", joinColumns={@JoinColumn(referencedColumnName="id_company")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id_score")})
    @JsonIgnore
    private List<Score> scores;
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_company")
    @JsonIgnore
    private List<User> users;
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_company")
    @JsonIgnore
    private List<Product> products;
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_oauth_client")
    @JsonIgnore
    private OauthClient tenant;
   // @Column(name = "id_cidade", nullable = false)
   // private int idCidade;
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="id_cidade")
    @JsonIgnore
    private Cidade cidade;
    @Column(name = "latitude", nullable = false)
    @JsonProperty("la")
    private double latitude;
    @Column(name = "longitude", nullable = false)
    @JsonProperty("lo")
    private double longitude;
    /*@Column(name = "raio_atuacao", nullable = false)
    private double raioAtuacao;*/
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="id_company")
   // @JsonIgnore
    private List<CompanyActuaction> companyActuaction;
    /*@Column(name = "tx_delivery", nullable = false, insertable = false)
    private BigDecimal txDelivery;*/

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="company_payment_types", joinColumns={@JoinColumn(referencedColumnName="id_company")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id_payments_type")})
    @JsonIgnore
    private List<PaymentType> paymentTypes;*/

    @Column(name = "payment_types")
    @JsonProperty("pa")
    private  String paymentTypesCompany = "{1,2,3}";



    public Company() {
    }

    public Company(int enabled, String socialName, String fantasyName, List<Module> modules) {
        this.enabled = enabled;
        this.socialName = socialName;
        this.fantasyName = fantasyName;
        this.modules = modules;
    }

    /*public Company(int enabled, String socialName, String fantasyName, List<Module> modules, List<PaymentType> paymentTypes) {
        this.enabled = enabled;
        this.socialName = socialName;
        this.fantasyName = fantasyName;
        this.modules = modules;
        this.paymentTypes = paymentTypes;
    }*/


   /* public Company(long idCompany, List<Brand> brands) {
        this.idCompany = idCompany;
        this.brands = brands;
    }*/


    public Company(long idCompany, String fantasyName) {
        this.idCompany = idCompany;
        this.fantasyName = fantasyName;
    }

    public Company(long idCompany, String fantasyName, long idCompanyActuaction, BigDecimal txDelivery) {
        this.idCompany = idCompany;
        this.fantasyName = fantasyName;
        this.companyActuaction = new ArrayList<>();
        this.companyActuaction.add(new CompanyActuaction(idCompanyActuaction, idCompany, txDelivery));
    }


    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public OauthClient getTenant() {
        return tenant;
    }

    public void setTenant(OauthClient tenant) {
        this.tenant = tenant;
    }

   /* public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }*/

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*public double getRaioAtuacao() {
        return raioAtuacao;
    }

    public void setRaioAtuacao(double raioAtuacao) {
        this.raioAtuacao = raioAtuacao;
    }*/

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUfInitials() {
        return ufInitials;
    }

    public void setUfInitials(String ufInitials) {
        this.ufInitials = ufInitials;
    }

    public List<CompanyActuaction> getCompanyActuaction() {
        return companyActuaction;
    }

    public void setCompanyActuaction(List<CompanyActuaction> companyActuaction) {
        this.companyActuaction = companyActuaction;
    }

    /*public List<PaymentType> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentType> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }*/

   /* public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }*/

    public List<CompanyBrands> getCompanyBrands() {
        return companyBrands;
    }

    public void setCompanyBrands(List<CompanyBrands> companyBrands) {
        this.companyBrands = companyBrands;
    }

    /*
    public List<Liters> getLiters() {
        return liters;
    }

    public void setLiters(List<Liters> liters) {
        this.liters = liters;
    }*/

    public List<CompanyLiters> getLiters() {
        return liters;
    }

    public void setLiters(List<CompanyLiters> liters) {
        this.liters = liters;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public String getPaymentTypesCompany() {
        return paymentTypesCompany;
    }

    public void setPaymentTypesCompany(String paymentTypesCompany) {
        this.paymentTypesCompany = paymentTypesCompany;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmailCadastro() {
        return emailCadastro;
    }

    public void setEmailCadastro(String emailCadastro) {
        this.emailCadastro = emailCadastro;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
