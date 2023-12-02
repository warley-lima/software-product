package com.Web.teste;

import com.Authentication.Service.LoginService;
import com.Brand.Service.BrandPublicService;
import com.Brand.Service.BrandsService;
import com.Company.Service.CompanyActuactionService;
import com.Company.Service.CompanyBrandService;
import com.Company.Service.CompanyLitersService;
import com.Company.Service.CompanyPublicService;
import com.Coupons.Service.CouponPublicService;
import com.Coupons.Service.CouponService;
import com.Order.Service.OrderPublicService;
import com.Order.Service.OrderService;
import com.Products.Service.LitersPublicService;
import com.Products.Service.ProductPublicService;
import com.Products.Service.ProductService;
import com.QueryCep.Service.QueryCepService;
import com.User.Service.UserService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
       // packages("com.Order.Service; com.Authentication.Service");
      //  register(new MyApplicationBinder());  CompanyActuactionService
        register(CorsFilter.class);
        register(ProductService.class);
        register(ProductPublicService.class);
        register(CompanyPublicService.class);

        register(QueryCepService.class);
        register(BrandPublicService.class);
        register(LitersPublicService.class);
        register(BrandsService.class);
        register(UserService.class);
        register(CouponService.class);
        register(CouponPublicService.class);

       // register(OrderServiceEmiter.class);

      //  packages("com.Order.Service");

        register(LoginService.class);
        register(OrderService.class);
        register(OrderPublicService.class);
        register(CompanyBrandService.class);
        register(CompanyActuactionService.class);
        register(CompanyLitersService.class);

    }

}
