package com.Products.Business;

//import com.Company.Business.Company;
import com.Products.Model.Product;
import com.Products.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductBusinessImp implements ProductBusiness {

    @Inject
    private  ProductRepository repository;

    @Transactional
    @Override
    public Product getProductById(long id) {
        return  repository.getOne(id);
    }

    @Transactional
    @Override
    public void save(Product module) {
        repository.save(module);
    }

    @Transactional
    @Override
    public void update(Product module) {
        repository.saveAndFlush(module);
    }

    @Transactional
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void loadProducts(){
        /*loadDist1();
        loadDist2();
        loadDist3();*/

       // List<Product> lstProd = getProductsByIdBrand(1);  ProductService
        List<Product> lstProd = getProductsByIdCompanyOrderPriceAsc(1);
        //Stream<Product> stream = lstProd.stream();
        lstProd.stream().forEach(product -> System.out.println(product.getProductName() + " R$" + product.getPrice()));
        //lstProd.stream().forEach(product -> System.out.println(product.getProductName() + " ---->Company:" + product.getCompany().getFantasyName()));


    }

    @Override
    public List<Product> getProductsByIdBrand(int idBrand) {
        return repository.findByIdBrand(idBrand);
    }

    //@Transactional
    @Override
    public List<Product> getProductsByIdCompany(long idCompany) {
        //return repository.findByCompany_IdCompanyOrderByProductName(idCompany);
        return repository.findByIdCompanyOrderByProductName(idCompany);
    }

    @Override
    public Page<Product> getProductsByIdCompany(long idCompany, Pageable pageable) {
        return repository.findByIdCompanyOrderByProductName(idCompany, pageable);
    }

    @Override
    public Page<Product> getProductsSearchByIdCompany(long idCompany, Pageable pageable, String name) {
        return repository.findByIdCompanyAndProductNameContainingIgnoreCase(idCompany, name, pageable);
    }

    @Override
    public List<Product> getProductsByIdCompanyOrderPriceAsc(long idCompany) {
       // return repository.findByCompany_IdCompanyOrderByPriceAsc(idCompany);
        return repository.findByIdCompanyOrderByPriceAsc(idCompany);
    }

    @Override
    public List<Product> getProductsByIdCompanyOrderPriceDes(long idCompany) {
        //return repository.findByCompany_IdCompanyOrderByPriceDesc(idCompany);
        return repository.findByIdCompanyOrderByPriceDesc(idCompany);
    }

    @Override
    public Product getProduct(long idProdct) {
        return repository.findByIdProduct(idProdct);
    }

    private  void  loadDist1(){
       /* Company company = new Company();
        company.setIdCompany(1);*/
        Product m1 = new Product();
        m1.setProductName("Água mineral Pura sem gás 1,5 Litro");
        m1.setLiters(1.50f);
        m1.setPrice(1.88f);
        m1.setIdBrand(1);
       // m1.setCompany(company);
        m1.setIdCompany(1);
        save(m1);
        Product m2 = new Product();
        m2.setProductName("Água mineral Pura sem gás 5 Litros");
        m2.setLiters(5f);
        m2.setPrice(4.99f);
        m2.setIdBrand(1);
       // m2.setCompany(company);
        m2.setIdCompany(1);
        save(m2);
        Product m3 = new Product();
        m3.setProductName("Água mineral Pura sem gás 10 Litros");
        m3.setLiters(10f);
        m3.setPrice(8.99f);
        m3.setIdBrand(1);
        //m3.setCompany(company);
        m3.setIdCompany(1);
        save(m3);
        Product m4 = new Product();
        m4.setProductName("Água mineral Pura sem gás 20 Litros");
        m4.setLiters(20f);
        m4.setPrice(14.99f);
        m4.setIdBrand(1);
        //m4.setCompany(company);
        m4.setIdCompany(1);
        save(m4);
        Product m5 = new Product();
        m5.setProductName("Água mineral Pura com gás 2 Litros");
        m5.setLiters(2f);
        m5.setPrice(3.99f);
        m5.setIdBrand(1);
        //m5.setCompany(company);
        m5.setIdCompany(1);
        save(m5);
        Product m6 = new Product();
        m6.setProductName("Água mineral Crystal sem gás 20 Litros");
        m6.setLiters(20f);
        m6.setPrice(15.89f);
        m6.setIdBrand(2);
        //m6.setCompany(company);
        m6.setIdCompany(1);
        save(m6);
        Product m7 = new Product();
        m7.setProductName("Água mineral Bioleve sem gás 20 Litros");
        m7.setLiters(20f);
        m7.setPrice(16.89f);
        m7.setIdBrand(3);
        //m7.setCompany(company);
        m7.setIdCompany(1);
        save(m7);
        Product m8 = new Product();
        m8.setProductName("Água mineral Danone sem gás 20 Litros");
        m8.setLiters(20f);
        m8.setPrice(17.39f);
        m8.setIdBrand(4);
       //m8.setCompany(company);
        m8.setIdCompany(1);
        save(m8);
        Product m9 = new Product();
        m9.setProductName("Água mineral Nativa sem gás 20 Litros");
        m9.setLiters(20f);
        m9.setPrice(18.39f);
        m9.setIdBrand(5);
        //m9.setCompany(company);
        m9.setIdCompany(1);
        save(m9);
    }

    private  void  loadDist2(){
       /* Company company = new Company();
        company.setIdCompany(2);*/
        Product m1 = new Product();
        m1.setProductName("Água mineral Pura sem gás 1,5 Litro");
        m1.setLiters(1.50f);
        m1.setPrice(2f);
        m1.setIdBrand(1);
        //m1.setCompany(company);
        m1.setIdCompany(2);
        save(m1);
        Product m2 = new Product();
        m2.setProductName("Água mineral Pura sem gás 5 Litros");
        m2.setLiters(5f);
        m2.setPrice(4.90f);
        m2.setIdBrand(1);
        //m2.setCompany(company);
        m2.setIdCompany(2);
        save(m2);
        Product m3 = new Product();
        m3.setProductName("Água mineral Pura sem gás 10 Litros");
        m3.setLiters(10f);
        m3.setPrice(8.69f);
        m3.setIdBrand(1);
        //m3.setCompany(company);
        m3.setIdCompany(2);
        save(m3);
        Product m4 = new Product();
        m4.setProductName("Água mineral Pura sem gás 20 Litros");
        m4.setLiters(20f);
        m4.setPrice(15.99f);
        m4.setIdBrand(1);
        //m4.setCompany(company);
        m4.setIdCompany(2);
        save(m4);
        Product m5 = new Product();
        m5.setProductName("Água mineral Pura com gás 2 Litros");
        m5.setLiters(2f);
        m5.setPrice(3.49f);
        m5.setIdBrand(1);
        //m5.setCompany(company);
        m5.setIdCompany(2);
        save(m5);
        Product m6 = new Product();
        m6.setProductName("Água mineral Crystal sem gás 20 Litros");
        m6.setLiters(20f);
        m6.setPrice(15.59f);
        m6.setIdBrand(2);
        //m6.setCompany(company);
        m6.setIdCompany(2);
        save(m6);
        Product m7 = new Product();
        m7.setProductName("Água mineral Bioleve sem gás 20 Litros");
        m7.setLiters(20f);
        m7.setPrice(16.99f);
        m7.setIdBrand(3);
       // m7.setCompany(company);
        m7.setIdCompany(2);
        save(m7);
        Product m8 = new Product();
        m8.setProductName("Água mineral Danone sem gás 20 Litros");
        m8.setLiters(20f);
        m8.setPrice(17.49f);
        m8.setIdBrand(4);
        //m8.setCompany(company);
        m8.setIdCompany(2);
        save(m8);
        Product m9 = new Product();
        m9.setProductName("Água mineral Nativa sem gás 20 Litros");
        m9.setLiters(20f);
        m9.setPrice(18.79f);
        m9.setIdBrand(5);
        //m9.setCompany(company);
        m9.setIdCompany(2);
        save(m9);
    }

    private   void  loadDist3(){
        /*Company company = new Company();
        company.setIdCompany(3);*/
        Product m1 = new Product();
        m1.setProductName("Água mineral Pura sem gás 1,5 Litro");
        m1.setLiters(1.50f);
        m1.setPrice(1.78f);
        m1.setIdBrand(1);
        //m1.setCompany(company);
        m1.setIdCompany(3);
        save(m1);
        Product m2 = new Product();
        m2.setProductName("Água mineral Pura sem gás 5 Litros");
        m2.setLiters(5f);
        m2.setPrice(5.49f);
        m2.setIdBrand(1);
       // m2.setCompany(company);
        m2.setIdCompany(3);
        save(m2);
        Product m3 = new Product();
        m3.setProductName("Água mineral Pura sem gás 10 Litros");
        m3.setLiters(10f);
        m3.setPrice(8.59f);
        m3.setIdBrand(1);
        //m3.setCompany(company);
        m3.setIdCompany(3);
        save(m3);
        Product m4 = new Product();
        m4.setProductName("Água mineral Pura sem gás 20 Litros");
        m4.setLiters(20f);
        m4.setPrice(16.99f);
        m4.setIdBrand(1);
       // m4.setCompany(company);
        m4.setIdCompany(3);
        save(m4);
        Product m5 = new Product();
        m5.setProductName("Água mineral Pura com gás 2 Litros");
        m5.setLiters(2f);
        m5.setPrice(4.79f);
        m5.setIdBrand(1);
        //m5.setCompany(company);
        m5.setIdCompany(3);
        save(m5);
        Product m6 = new Product();
        m6.setProductName("Água mineral Crystal sem gás 20 Litros");
        m6.setLiters(20f);
        m6.setPrice(15.79f);
        m6.setIdBrand(2);
        //m6.setCompany(company);
        m6.setIdCompany(3);
        save(m6);
        Product m7 = new Product();
        m7.setProductName("Água mineral Bioleve sem gás 20 Litros");
        m7.setLiters(20f);
        m7.setPrice(16.79f);
        m7.setIdBrand(3);
        //m7.setCompany(company);
        m7.setIdCompany(3);
        save(m7);
        Product m8 = new Product();
        m8.setProductName("Água mineral Danone sem gás 20 Litros");
        m8.setLiters(20f);
        m8.setPrice(17.79f);
        m8.setIdBrand(4);
        //m8.setCompany(company);
        m8.setIdCompany(3);
        save(m8);
        Product m9 = new Product();
        m9.setProductName("Água mineral Nativa sem gás 20 Litros");
        m9.setLiters(20f);
        m9.setPrice(18.79f);
        m9.setIdBrand(5);
        //m9.setCompany(company);
        m9.setIdCompany(3);
        save(m9);
    }
}
