package com.example.sms.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.sms.dao.*;
import com.example.sms.service.*;

@Configuration
@ComponentScan(basePackages = "com.example.sms")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        // H2 in-memory DB
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:smsdb;DB_CLOSE_DELAY=-1;MODE=MySQL");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.example.sms.model");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.hbm2ddl.auto", "update"); // use 'validate' or 'none' for prod
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        sf.setHibernateProperties(props);
        return sf;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory);
        return tx;
    }

    // If you prefer explicit DAO beans instead of @Repository + constructor injection,
    // you could declare them here e.g. studentDAO(sessionFactory), but component-scan will find @Repository classes.
}
