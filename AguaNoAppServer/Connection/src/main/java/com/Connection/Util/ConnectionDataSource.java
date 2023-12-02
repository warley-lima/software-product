package com.Connection.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;

//@Component("dataSource")
@Component
public class ConnectionDataSource {
    @Inject
    private Environment env;

    public ConnectionDataSource() {
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        /*dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setSchema(env.getProperty("spring.jpa.hibernate.default_schema"));*/
       /* dataSource.setDriverClassName("org.postgresql.Driver");
       dataSource.setUrl("jdbc:postgresql://localhost:5432/h2oapp");
        dataSource.setUsername("postgres");
        dataSource.setPassword("q");
        dataSource.setSchema("h2oapp");*/
        dataSource.setUrl("jdbc:postgresql://mydb.cf9puwrg5wrl.us-east-2.rds.amazonaws.com:5432/aguanoapp");
        dataSource.setUsername("postgres");
        dataSource.setPassword("gr0up50ftM4st3r");
        dataSource.setSchema("h2oapp");



        return dataSource;
    }


}

