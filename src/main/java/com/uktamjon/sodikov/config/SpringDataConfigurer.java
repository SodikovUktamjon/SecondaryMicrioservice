package com.uktamjon.sodikov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@ComponentScan("com.uktamjon.sodikov.repositories")
public class SpringDataConfigurer {

    private final Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public SimpleMongoClientDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory("spring.data.mongodb.uri");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.uktamjon.sodikov");
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setJpaProperties(properties());

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }


    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getRequiredProperty("spring.jpa.show-sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("spring.jpa.database-platform"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        return properties;
    }

}