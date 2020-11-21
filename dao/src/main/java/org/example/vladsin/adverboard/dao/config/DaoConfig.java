package org.example.vladsin.adverboard.dao.config;

import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.dao.repository.BaseDao;
import org.example.vladsin.adverboard.dao.repository.UserDao;
import org.example.vladsin.adverboard.dao.repository.impl.AuthUserDaoImpl;
import org.example.vladsin.adverboard.dao.repository.impl.BaseDaoImpl;
import org.example.vladsin.adverboard.dao.repository.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private final EntityManagerFactory entityManagerFactory;

    public DaoConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public BaseDao baseDao(){
        return new BaseDaoImpl();
    }

    @Bean
    public UserDao userDao(){
        return new UserDaoImpl();
    }

    @Bean
    public AuthUserDao authUserDao(){
        return new AuthUserDaoImpl();
    }
}
