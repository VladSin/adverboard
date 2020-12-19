package org.example.vladsin.adverboard.service.config;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.service.repository.*;
import org.example.vladsin.adverboard.service.repository.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig){
        this.daoConfig = daoConfig;
    }

    @Bean
    public UserRepositoryService userService(){
        return new UserRepositoryServiceImpl(daoConfig.userRepositoryDao());
    }

    @Bean
    public AuthUserRepositoryService authUserService(){
        return new AuthUserRepositoryServiceImpl(daoConfig.authUserRepositoryDao());
    }

    @Bean
    public AdRepositoryService adService(){
        return new AdRepositoryServiceImpl(daoConfig.adRepositoryDao());
    }

    @Bean
    public LocationRepositoryService locationService(){
        return new LocationRepositoryServiceImpl(daoConfig.locationRepositoryDao());
    }

    @Bean
    public BillboardRepositoryService billboardService(){
        return new BillboardRepositoryServiceImpl(daoConfig.billboardRepositoryDao());
    }

    @Bean
    public GroupBillboardRepositoryService groupBillboardService(){
        return new GroupBillboardRepositoryServiceImpl(
                daoConfig.groupBillboardRepositoryDao(),
                daoConfig.billboardRepositoryDao());
    }

    @Bean
    public SecurityRepositoryService securityService(){
        return new SecurityRepositoryServiceImpl(
                daoConfig.securityRepositoryDao(),
                daoConfig.authUserRepositoryDao());
    }
}
