package org.example.vladsin.adverboard.dao.config;

import org.example.vladsin.adverboard.dao.repository.*;
import org.example.vladsin.adverboard.dao.repository.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public UserRepositoryDao userRepositoryDao(){
        return new UserRepositoryDaoImpl(sessionFactory);
    }

    @Bean
    public AuthUserRepositoryDao authUserRepositoryDao(){
        return new AuthUserRepositoryDaoImpl(sessionFactory);
    }

    @Bean
    public SecurityRepositoryDao securityRepositoryDao(){
        return new AuthUserRepositoryDaoImpl(sessionFactory);
    }

    @Bean
    public AdRepositoryDao adRepositoryDao(){
        return new AdRepositoryDaoImpl(sessionFactory);
    }

    @Bean
    public LocationRepositoryDao locationRepositoryDao(){
        return new LocationRepositoryDaoImpl(sessionFactory);
    }

    @Bean
    public BillboardRepositoryDao billboardRepositoryDao(){
        return new BillboardRepositoryDaoImpl(sessionFactory);
    }

    @Bean
    public GroupBillboardRepositoryDao groupBillboardRepositoryDao(){
        return new GroupBillboardRepositoryDaoImpl(sessionFactory);
    }
}
