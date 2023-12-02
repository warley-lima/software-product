package com.Security.Business;

import com.User.Service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.HttpMethod;

//@Configuration
//@EnableWebSecurity
//@Component
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
   @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    @Qualifier("authenticationManagerBean")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/login"
                        ,"/api/productpub/**"
                        ,"/api/brandpub/**"
                        ,"/api/literspub/**"
                        ,"/api/companypub/**"
                        ,"/api/querycep/**"
                        ,"/api/cuponspub/**"
                        ,"/api/orderpub/**"
                        , "/oauth/token").permitAll()
              //  .antMatchers(HttpMethod.POST,"/api/orderpub/**").permitAll()
                .anyRequest().authenticated()
               // .and().formLogin().permitAll()
                .and().csrf().ignoringAntMatchers("/api/orderpub/**") //,"/api/products/**"
                //.and().csrf().disable()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        ;
    }
    */
}
