package org.example.vladsin.adverboard.service.config;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.service.repository.AuthUserService;
import org.example.vladsin.adverboard.service.repository.BaseService;
import org.example.vladsin.adverboard.service.repository.UserService;
import org.example.vladsin.adverboard.service.repository.impl.AuthUserServiceImpl;
import org.example.vladsin.adverboard.service.repository.impl.BaseServiceImpl;
import org.example.vladsin.adverboard.service.repository.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig){
        this.daoConfig = daoConfig;
    }

    @Bean
    public BaseService baseService(){
        return new BaseServiceImpl(daoConfig.baseDao());
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl(daoConfig.userDao());
    }

    @Bean
    public AuthUserService authUserService(){
        return new AuthUserServiceImpl(daoConfig.authUserDao());
    }
}
