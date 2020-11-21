package org.example.vladsin.adverboard.dao.config;

import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.dao.repository.UserDao;
import org.example.vladsin.adverboard.dao.repository.impl.AuthUserDaoImpl;
import org.example.vladsin.adverboard.dao.repository.impl.UserDaoImpl;
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
    public UserDao userDao(){
        return new UserDaoImpl(sessionFactory);
    }

    @Bean
    public AuthUserDao authUserDao(){
        return new AuthUserDaoImpl(sessionFactory);
    }
}
