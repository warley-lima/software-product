package com.Web.teste;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

// @Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
       // responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
      //  responseContext.getHeaders().add("Access-Control-Allow-Origin", "https://empresa1.aguanoapp.com.br");
       // responseContext.getHeaders().add("Access-Control-Allow-Origin", "https://empresa2.aguanoapp.com.br");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization, XSRF-TOKEN, X-CSRF-Token, x-xsrf-token, X-XSRF-TOKEN");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "API, GET, POST, PUT, DELETE, OPTIONS, HEAD");
      //  System.out.println("--------------------------------HOPE INSIDE----------------------------------------------");
    }
}
