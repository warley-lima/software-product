package com.Company.Business;

import com.Company.Model.Company;
import com.Company.Model.CompanyPaymentTypes;
import com.Company.Model.CompanyPublicApi;
import com.Company.Model.PaymentType;
import com.Company.Repository.CompanyActuactionRepository;
import com.Company.Repository.CompanyRepository;
import com.Company.Repository.PaymentTypeRepository;
import com.Company.Util.DistanceCalculator;
import com.Security.Repository.OauthClientRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyBusinessImp implements CompanyBusiness {

    @Inject
    private CompanyRepository repository;
    //@Inject
   // private UserRepository userRepository;
   /* @Inject
    private RuleRepository ruleRepository;*/
    @Inject
    private OauthClientRepository oauthClientRepository;
    @Inject
    private CompanyActuactionRepository companyActuactionRepository;
    @Inject
    private PaymentTypeRepository paymentTypeRepositoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Company getCompanyById(long id) {
        return  repository.getOne(id);
    }

    @Transactional
    @Override
    public void save(Company module) {
        repository.save(module);
        repository.flush();
        //repository.saveAndFlush()
    }

    @Transactional
    public Company save2(Company module) {
        return repository.saveAndFlush(module);
    }

    @Override
    public Company getCompanyByCNPJ(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    @Override
    public void loadCompanies(){
       /*Company m1 = new Company();
        Cidade city = new Cidade();
        city.setIdCity(9668);
        m1.setFantasyName("Dist 1");
        m1.setSocialName("EMPORIO CRIAR ARTE PAPELARIA VIRTUAL LTDA - ME");
        m1.setEnabled(1);
        m1.setCidade(city);
        m1.setLatitude(-23.5432742);
        m1.setLongitude(-46.6414281);
        //m1.setRaioAtuacao(6.5);
        m1.setUfInitials("SP");
        Module mod1 = new Module();
        mod1.setId(1);
        Module mod2 = new Module();
        mod2.setId(3);
        Module mod3 = new Module();
        mod3.setId(5);
        List<Module> lstMod = new ArrayList<>();
        lstMod.add(mod1);
        lstMod.add(mod2);
        lstMod.add(mod3);
        m1.setModules(lstMod);
        //List<CompanyActuaction> lstCompActuation = new ArrayList<>();
        //lstCompActuation.add(new CompanyActuaction(m1.getIdCompany(),new BigDecimal(3), BigDecimal.ZERO));
        //lstCompActuation.add(new CompanyActuaction(m1.getIdCompany(),new BigDecimal(5), new BigDecimal(2.5)));
        //lstCompActuation.add(new CompanyActuaction(m1.getIdCompany(),new BigDecimal(10), new BigDecimal(5)));
        //m1.setCompanyActuaction(lstCompActuation);
        save(m1);
        companyActuactionRepository.save(new CompanyActuaction(m1.getIdCompany(),new BigDecimal(3), BigDecimal.ZERO));
        companyActuactionRepository.save(new CompanyActuaction(m1.getIdCompany(),new BigDecimal(5), new BigDecimal(2.5)));
        companyActuactionRepository.save(new CompanyActuaction(m1.getIdCompany(),new BigDecimal(10), new BigDecimal(5)));

        OauthClient tenant1 = new OauthClient();
        tenant1.setTenantName("Tenant_5");
        tenant1.setPassword("secret");
        tenant1.setScope("none");
        tenant1.setAuthorizedGrantTypes("password,refresh_token");
        tenant1.setAccessTokenValidity("300");
        tenant1.setRefreshTokenValidity("600");
        tenant1.setIdCompany(m1.getIdCompany());
        oauthClientRepository.save(tenant1);

        User user1 = new User();
        user1.setName("Warley Lima");
        user1.setUserName("seiya@teste.com");
        user1.setPerfil("S_ADM");
        user1.setPassword("123");
        user1.setIdCompany(m1.getIdCompany());
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Adriele Lima");
        user2.setUserName("adriele@teste.com");
        user2.setPerfil("USER");
        user2.setPassword("123");
        user2.setIdCompany(m1.getIdCompany());
        userRepository.save(user2);
        Rule r1 = new Rule();
        r1.setIdModuleFK(3);
        r1.setIdUserFK(user2.getId());
        r1.setName("{C}");
        Rule r2 = new Rule();
        r2.setIdModuleFK(3);
        r2.setIdUserFK(user2.getId());
        r2.setName("{R}");
        Rule r3 = new Rule();
        r3.setIdModuleFK(3);
        r3.setIdUserFK(user2.getId());
        r3.setName("{U}");
        Rule r4 = new Rule();
        r4.setIdModuleFK(5);
        r4.setIdUserFK(user2.getId());
        r4.setName("{R}");
        List<Rule> lstRules = new ArrayList<>();
        lstRules.add(r1);
        lstRules.add(r2);
        lstRules.add(r3);
        lstRules.add(r4);
        ruleRepository.save(r1);
        ruleRepository.save(r2);
        ruleRepository.save(r3);
        ruleRepository.save(r4);

        User user3 = new User();
        user3.setName("Rychard Paz");
        user3.setUserName("rychard@teste.com");
        user3.setPerfil("ADM");
        user3.setPassword("123");
        user3.setIdCompany(m1.getIdCompany());
        userRepository.save(user3);

        List<User> lstUser = new ArrayList<>();
        lstUser.add(user1);
        lstUser.add(user2);
        lstUser.add(user3);

        Company m2 = new Company();
        Cidade citySbc = new Cidade();
        citySbc.setIdCity(9640);
        m2.setFantasyName("Dist 2");
        m2.setSocialName("ENGESHOP COMERCIO DE FERRAMENTAS LTDA");
        m2.setEnabled(0);
        m2.setCidade(citySbc);
        m2.setLatitude(-23.7018144);
        m2.setLongitude(-46.5692485);
        //m2.setRaioAtuacao(8.5);
        m2.setUfInitials("SP");
        Module mode1 = new Module();
        mode1.setId(2);
        Module mode2 = new Module();
        mode2.setId(4);
        List<Module> lstMod2 = new ArrayList<>();
        lstMod2.add(mode1);
        lstMod2.add(mode2);
        m2.setModules(lstMod2);
        //List<CompanyActuaction> lstCompActuation2 = new ArrayList<>();
        //lstCompActuation2.add(new CompanyActuaction(m2.getIdCompany(),new BigDecimal(5), BigDecimal.ZERO));
        //lstCompActuation2.add(new CompanyActuaction(m2.getIdCompany(),new BigDecimal(10), new BigDecimal(4.5)));
        //lstCompActuation2.add(new CompanyActuaction(m2.getIdCompany(),new BigDecimal(20), new BigDecimal(9)));
        //m2.setCompanyActuaction(lstCompActuation2);
        save(m2);
        companyActuactionRepository.save(new CompanyActuaction(m2.getIdCompany(),new BigDecimal(5), BigDecimal.ZERO));
        companyActuactionRepository.save(new CompanyActuaction(m2.getIdCompany(),new BigDecimal(10), new BigDecimal(4.5)));
        companyActuactionRepository.save(new CompanyActuaction(m2.getIdCompany(),new BigDecimal(20), new BigDecimal(9)));

        OauthClient tenant2 = new OauthClient();
        tenant2.setTenantName("Tenant_1");
        tenant2.setPassword("teste");
        tenant2.setScope("none");
        tenant2.setAuthorizedGrantTypes("password,refresh_token");
        tenant2.setAccessTokenValidity("300");
        tenant2.setRefreshTokenValidity("600");
        tenant2.setIdCompany(m2.getIdCompany());
        oauthClientRepository.save(tenant2);

        User user4 = new User();
        user4.setName("Maykol Paz");
        user4.setUserName("maykol@teste.com");
        user4.setPerfil("ADM");
        user4.setPassword("123");
        user4.setIdCompany(m2.getIdCompany());
        userRepository.save(user4);

        User user5 = new User();
        user5.setName("Kakaroto Paz");
        user5.setUserName("kakaroto@teste.com");
        user5.setPerfil("S_ADM");
        user5.setPassword("321");
        user5.setIdCompany(m2.getIdCompany());
        userRepository.save(user5);
        Rule r5 = new Rule();
        r5.setIdModuleFK(5);
        r5.setIdUserFK(5);
        r5.setName("{R}");
        List<Rule> lstRules2 = new ArrayList<>();
        lstRules2.add(r5);
        ruleRepository.save(lstRules2);



        Company m3 = new Company();
        m3.setFantasyName("Dist 3");
        m3.setSocialName("Distribuidora LTDA");
        m3.setEnabled(1);
        m3.setCidade(citySbc);
        m3.setLatitude(-23.7018144);
        m3.setLongitude(-46.5692485);
       // m3.setRaioAtuacao(2.5);
        m3.setUfInitials("SP");
        Module mode1d2 = new Module();
        mode1d2.setId(2);
        Module mode2d2 = new Module();
        mode2d2.setId(4);
        List<Module> lstMod2d2 = new ArrayList<>();
        lstMod2d2.add(mode1d2);
        lstMod2d2.add(mode2d2);
        m3.setModules(lstMod2d2);
        //List<CompanyActuaction> lstCompActuation3 = new ArrayList<>();
        //lstCompActuation3.add(new CompanyActuaction(m3.getIdCompany(),new BigDecimal(3), BigDecimal.ZERO));
        //lstCompActuation3.add(new CompanyActuaction(m3.getIdCompany(),new BigDecimal(5), new BigDecimal(3.5)));
        //lstCompActuation3.add(new CompanyActuaction(m3.getIdCompany(),new BigDecimal(10), new BigDecimal(7)));
        //m3.setCompanyActuaction(lstCompActuation3);
        save(m3);
        companyActuactionRepository.save(new CompanyActuaction(m3.getIdCompany(),new BigDecimal(3), BigDecimal.ZERO));
        companyActuactionRepository.save(new CompanyActuaction(m3.getIdCompany(),new BigDecimal(5), new BigDecimal(3.5)));
        companyActuactionRepository.save(new CompanyActuaction(m3.getIdCompany(),new BigDecimal(10), new BigDecimal(7)));

        OauthClient tenant3 = new OauthClient();
        tenant3.setTenantName("Tenant_3");
        tenant3.setPassword("teste");
        tenant3.setScope("none");
        tenant3.setAuthorizedGrantTypes("password,refresh_token");
        tenant3.setAccessTokenValidity("300");
        tenant3.setRefreshTokenValidity("600");
        tenant3.setIdCompany(m2.getIdCompany());
        oauthClientRepository.save(tenant3);

        User user6 = new User();
        user6.setName("Admin Dist III");
        user6.setUserName("dist3@teste.com");
        user6.setPerfil("ADM");
        user6.setPassword("123");
        user6.setIdCompany(m3.getIdCompany());
        userRepository.save(user6);*/

        /*
        //List<Company> lstCompanies = getCompaniesByNameCity("São Bernardo do Campo");
        List<Company> lstCompanies = getCompaniesByIdCity(9640);
        lstCompanies.stream().forEach(company -> System.out.println(company.getFantasyName() + " ---> " + company.getIdCompany()));*/


    }

    @Override
    public void loadTypePayments() {
        PaymentType money = new PaymentType("Dinheiro");
        paymentTypeRepositoryRepository.save(money);
        PaymentType credit = new PaymentType("Crédito");
        paymentTypeRepositoryRepository.save(credit);
        PaymentType debit = new PaymentType("Débito");
        paymentTypeRepositoryRepository.save(debit);
    }


   /* @Override
    public List<Brand> getBrandsByIdCompany(long idCompany) {
        List<Brand> ret = null;
        try{
            Company c = repository.getCompanyBrands(idCompany);
            if(null != c) {
                ret = c.getBrands();
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return ret;
    }*/

    @Transactional
    @Override
    public void saveBrands(Company company) {
        em.merge(company);
    }


    @Override
    public List<Company> getCompaniesByLatLong(double latitude, double longitude)
    {
        double dist = DistanceCalculator.distance(latitude,longitude,latitude,longitude,"KM");
        return null;
    }

    @Override
    public List<Company> getCompaniesByIdCity(long id) {
        return repository.findByCidade_IdCity(id);
    }

    @Override
    public List<Company> getCompaniesByNameCity(String name) {
        return repository.findByCidade_Name(name);
    }

    @Override
    public List<Company> getCompaniesByLatLongCityName(double latitude, double longitude, String nameCity) {
        List<Company> reviews = new ArrayList<>();
        try {
            StoredProcedureQuery q = this.em.createNamedStoredProcedureQuery("procedureFindAll");
            q.setParameter(1, new BigDecimal(-23.5432742));
            q.setParameter(2, new BigDecimal(-46.6414281));
            q.setParameter(3,nameCity);
            reviews = q.getResultList();
           /* reviews.stream().forEach(product -> System.out.println(product.getIdCompany() + " --->" + product.getFantasyName() + " ---> R$"
                    + product.getCompanyActuaction().get(0).getTxDelivery()));*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
       // return repository.findByCidade_Name(nameCity);  procedurePaymentsTypesByCompany
    }

    @Override
    public List<CompanyPublicApi> getCompaniesByLatLongUfName(BigDecimal latitude, BigDecimal longitude, String uf) {
        List<CompanyPublicApi> reviews = new ArrayList<>();
        try {
            StoredProcedureQuery q = this.em.createNamedStoredProcedureQuery("procedureFindAll");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            q.setParameter(3,uf);
            reviews = q.getResultList();
           /* reviews.stream().forEach(product -> System.out.println(product.getId() + " --->" + product.getNm() + " ---> R$"
                    + product.getTx()));*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<CompanyPublicApi> getCompaniesByLatLongUfNameBrands(BigDecimal latitude, BigDecimal longitude, String uf, String idBrands) {
        List<CompanyPublicApi> reviews = new ArrayList<>();
        try {
            //Set<String> jes = new<>;
            StoredProcedureQuery q = this.em.createNamedStoredProcedureQuery("findCompaniesFilterBrand");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            q.setParameter(3,uf);
            q.setParameter(4,idBrands);
            reviews = q.getResultList();
            /*reviews.stream().forEach(product -> System.out.println(product.getId() + " --->" + product.getNm() + " ---> R$"
                    + product.getTx()));*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<CompanyPublicApi> getCompaniesByLatLongUfNameLiters(BigDecimal latitude, BigDecimal longitude, String uf, String idLiters) {
        List<CompanyPublicApi> reviews = new ArrayList<>();
        try {
            StoredProcedureQuery q = this.em.createNamedStoredProcedureQuery("findCompaniesFilterLiters");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            q.setParameter(3,uf);
            q.setParameter(4,idLiters);
            reviews = q.getResultList();
          }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<CompanyPublicApi> getCompaniesByLatLongUfNameBrandsLiters(BigDecimal latitude, BigDecimal longitude, String uf, String idBrands, String idLiters) {
        List<CompanyPublicApi> reviews = new ArrayList<>();
        try {
            StoredProcedureQuery q = this.em.createNamedStoredProcedureQuery("findCompaniesFilterBrandsLiters");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            q.setParameter(3,uf);
            q.setParameter(4,idBrands);
            q.setParameter(5,idLiters);
            reviews = q.getResultList();
            reviews.stream().forEach(product -> System.out.println(product.getId() + " --->" + product.getNm() + " ---> R$"
                    + product.getTx()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<CompanyPaymentTypes> getPaymentsTypesByCompany(long idCompany) {
        List<CompanyPaymentTypes> reviews = new ArrayList<>();
        /*try {
            StoredProcedureQuery q2 = this.em.createNamedStoredProcedureQuery("procedurePaymentsTypesByCompany");
            q2.setParameter(1, idCompany);
            reviews = q2.getResultList();
            //reviews.stream().forEach(product -> System.out.println(product.getIdType() + " --->" + product.getNmType()));
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return reviews;
    }
}
