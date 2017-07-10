package com.tr1nks.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация работы с БД
 * пакеты для поиска сущностей
 * пакеты для поиска репозиториев
 * использование параметров из application.properties
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:static/application.properties")
@EnableJpaRepositories({"com.tr1nks.model.repositories"})
public class DataConfig {
    private static final String entityPackage = "com.tr1nks.model.entities";
    //db prop
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    //hibernate prop
    private static final String DB_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String DB_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String DB_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String DB_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(DB_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(DB_URL));
        dataSource.setUsername(environment.getRequiredProperty(DB_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(DB_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(entityPackage);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty(DB_HIBERNATE_DIALECT));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty(DB_HIBERNATE_HBM2DDL_AUTO));
        properties.put("hibernate.show_sql", environment.getRequiredProperty(DB_HIBERNATE_SHOW_SQL));
        properties.put("hibernate.format_sql", environment.getRequiredProperty(DB_HIBERNATE_FORMAT_SQL));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
