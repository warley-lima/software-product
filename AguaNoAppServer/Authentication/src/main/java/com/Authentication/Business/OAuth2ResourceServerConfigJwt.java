package com.Authentication.Business;


import com.User.Service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



import javax.inject.Inject;

@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfigJwt  extends ResourceServerConfigurerAdapter {
    @Inject
    private CustomAccessTokenConverter customAccessTokenConverter;

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(HttpSecurity http) throws Exception {
       /* http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().*/
        http.authorizeRequests()
                .antMatchers("/api/login"
                        ,"/api/productpub/**"
                        ,"/api/brandpub/**"
                        ,"/api/literspub/**"
                        ,"/api/companypub/**"
                        ,"/api/query/**"
                        ,"/api/cuponspub/**"
                        ,"/api/orderpub/**"
                        ,"/api/oauth/token**"
                        , "/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers("/api/orderpub/**") //,"/api/products/**"
               // .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               // .and()
               // .csrf().disable()
                .and()
               .csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                .addFilterAfter(csrfHeaderFilter(), SessionManagementFilter.class)
        ;
      // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
   }


    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain) throws ServletException, IOException {

                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                    String token = csrf.getToken();
                    if (cookie == null || token != null
                            && !token.equals(cookie.getValue())) {

                        // Token is being added to the XSRF-TOKEN cookie.
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public TokenStore tokenStore2() {
        return new JwtTokenStore(accessTokenConverter2());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter2() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(customAccessTokenConverter);
        converter.setSigningKey("123");
        return converter;
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.resourceId(RESOURCE_ID).stateless(true);
        config.tokenServices(tokenServices2());
    }

    @Bean
    //@Primary
    public DefaultTokenServices tokenServices2() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore2());
        return defaultTokenServices;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

}
