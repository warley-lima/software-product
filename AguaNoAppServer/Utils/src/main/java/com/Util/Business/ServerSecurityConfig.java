package com.Util.Business;

import com.User.Service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

//@Configuration
//@EnableWebSecurity
//@Component
//@EnableGlobalMethodSecurity(securedEnabled = true )
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true )
@Configuration
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    @Qualifier("authenticationManagerBean")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
   @Bean
    public UserDetailsService userDetailsService2() {
        return new UserDetailsServiceImp();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public static PasswordEncoder passwordEncoder2() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService2());
        authProvider.setPasswordEncoder(passwordEncoder2());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        //auth.userDetailsService(userDetailsService2()).passwordEncoder(passwordEncoder2());
        auth.authenticationProvider(authProvider());
    }

   /* @Override
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
