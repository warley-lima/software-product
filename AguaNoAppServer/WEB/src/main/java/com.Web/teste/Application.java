package com.Web.teste;
import com.Company.Business.CompanyBusiness;
import com.Module.Service.ModuleService;
import com.Products.Business.ProductBusiness;
import com.Util.Business.ServerSecurityConfig;
import com.Util.Utils.QueryLatitudeLongitude;
import com.Web.Business.MethodSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com"})
@EntityScan(basePackages = {"com"})
@ComponentScan(basePackages = {"com"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true )
@Import({ ServerSecurityConfig.class, MethodSecurityConfiguration.class })
public class Application extends SpringBootServletInitializer {
   /* @Inject
    private ModuleService serviceModule;
    @Inject
    private CompanyBusiness serviceCompany;
   // @Inject
  //  private BrandService serviceBrand;
    @Inject
    private ProductBusiness serviceProd;*/

    @Bean
    protected String hope() {
        String ret = null;
        try {
        //    System.out.println("--------------------------Jesus is LORD VIII------------------------");
            //serviceModule.loadModules();
           /* serviceCompany.loadCompanies();
            serviceBrand.loadBrands();
          // serviceCompany.loadCompanies();
            serviceProd.loadProducts();*/
          // serviceCompany.loadTypePayments();

         //   System.out.println("--------------------------------------Jesus is LORD VII-----------------------");
            ret = "SUCESSO!";
        }
        catch (Exception e) {
            e.printStackTrace();
            ret = "FRACASSO!";
        }
        return  ret;
    }

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for images
      /*  registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/resources/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }*/

    public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
        //new Application().configure(new SpringApplicationBuilder(Application.class)).run(args);
    }

}
