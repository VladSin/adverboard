package org.example.vladsin.adverboard.web.config;

import org.example.vladsin.adverboard.dao.config.DaoConfig;
import org.example.vladsin.adverboard.service.config.ServiceConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebApplicationInitialization extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class,
                WebSecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class,
                ServiceConfig.class,
                DaoConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
        delegateFilterProxy.setTargetBeanName("springSecurityFilterChain");
        return new Filter[]{delegateFilterProxy};
    }
}
