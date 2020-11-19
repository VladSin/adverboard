package org.example.vladsin.adverboard.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(SettingsConfig.class)
public class HibernateConfig {

    private final SettingsConfig settingsConfig;

    public HibernateConfig(SettingsConfig settingsConfig){
        this.settingsConfig = settingsConfig;
    }

    @Bean
    public DataSource dataSource() {
        final DatasourceSettings datasourceSettings = settingsConfig.datasourceSettings();

        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(datasourceSettings.getUrl());
        hikariDataSource.setUsername(datasourceSettings.getUser());
        hikariDataSource.setPassword(datasourceSettings.getPassword());
        hikariDataSource.setDriverClassName(datasourceSettings.getDriver());
        hikariDataSource.setMaximumPoolSize(50);

        return hikariDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        final LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("org.example.airplane.sin.dao.entities");
        sessionFactoryBean.setHibernateProperties(settingsConfig.hibernateProperties());

        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());

        return transactionManager;
    }
}
