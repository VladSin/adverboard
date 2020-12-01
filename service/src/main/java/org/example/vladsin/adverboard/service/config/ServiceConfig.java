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
        return new UserServiceImpl(daoConfig.userDao());
    }

    @Bean
    public AuthUserService authUserService(){
        return new AuthUserServiceImpl(daoConfig.authUserDao());
    }

    @Bean
    public AdService adService(){
        return new AdServiceImpl(daoConfig.adDao());
    }

    @Bean
    public LocationService locationService(){
        return new LocationServiceImpl(daoConfig.locationDao());
    }

    @Bean
    public BillboardService billboardService(){
        return new BillboardServiceImpl(daoConfig.billboardDao());
    }

    @Bean
    public GroupBillboardService groupBillboardService(){
        return new GroupBillboardServiceImpl(daoConfig.groupBillboardDao());
    }

    @Bean
    public SecurityService securityService(){
        return new SecurityServiceImpl(
                daoConfig.securityDao(),
                daoConfig.authUserDao());
    }
}
