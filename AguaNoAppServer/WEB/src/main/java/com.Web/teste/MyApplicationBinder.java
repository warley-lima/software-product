package com.Web.teste;

import com.Authentication.Service.LoginService;
import com.Order.Service.OrderPublicService;
import com.Order.Service.OrderService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(LoginService.class).to(LoginService.class);
        bind(OrderService.class).to(OrderService.class);
        bind(OrderPublicService.class).to(OrderPublicService.class);
    }
}
