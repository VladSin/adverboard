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
    public UserService userService(){
        return new UserServiceImpl(daoConfig.userRepositoryDao());
    }

    @Bean
    public AuthUserService authUserService(){
        return new AuthUserServiceImpl(daoConfig.authUserRepositoryDao());
    }

    @Bean
    public AdService adService(){
        return new AdServiceImpl(daoConfig.adRepositoryDao());
    }

    @Bean
    public LocationService locationService(){
        return new LocationServiceImpl(daoConfig.locationRepositoryDao());
    }

    @Bean
    public BillboardService billboardService(){
        return new BillboardServiceImpl(daoConfig.billboardRepositoryDao());
    }

    @Bean
    public GroupBillboardService groupBillboardService(){
        return new GroupBillboardServiceImpl(daoConfig.groupBillboardRepositoryDao());
    }

    @Bean
    public SecurityService securityService(){
        return new SecurityServiceImpl(
                daoConfig.securityRepositoryDao(),
                daoConfig.authUserRepositoryDao());
    }
}
