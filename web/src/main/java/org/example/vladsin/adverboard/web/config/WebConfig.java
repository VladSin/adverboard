package org.example.vladsin.adverboard.web.config;

import org.example.vladsin.adverboard.service.config.ServiceConfig;
import org.example.vladsin.adverboard.web.controller.FirstPageController;
import org.example.vladsin.adverboard.web.controller.LoginController;
import org.example.vladsin.adverboard.web.controller.RegistrationController;
import org.example.vladsin.adverboard.web.controller.UserOperatingController;
import org.example.vladsin.adverboard.web.controller.rest.AuthUserRestController;
import org.example.vladsin.adverboard.web.controller.rest.BillboardRestController;
import org.example.vladsin.adverboard.web.controller.rest.GroupBillboardsRestController;
import org.example.vladsin.adverboard.web.controller.rest.UserRestController;
import org.example.vladsin.adverboard.web.controller.view.AdminController;
import org.example.vladsin.adverboard.web.controller.view.WebController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public FirstPageController firstPageController(){
        return new FirstPageController();
    }

    @Bean
    public RegistrationController registrationController(){
        return new RegistrationController(
                serviceConfig.userService(),
                serviceConfig.authUserService(),
                serviceConfig.securityService()
        );
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(
                serviceConfig.securityService(),
                serviceConfig.userService()
        );
    }

    @Bean
    public UserRestController userRestController(){
        return new UserRestController(
                serviceConfig.userService()
        );
    }

    @Bean
    public AuthUserRestController authUserRestController(){
        return new AuthUserRestController(
                serviceConfig.authUserService()
        );
    }

    @Bean
    public BillboardRestController billboardRestController(){
        return new BillboardRestController(
                serviceConfig.billboardService()
        );
    }

    @Bean
    public GroupBillboardsRestController groupBillboardsRestController(){
        return new GroupBillboardsRestController(
                serviceConfig.groupBillboardService(),
                serviceConfig.billboardService(),
                serviceConfig.adService()
        );
    }

    @Bean
    public UserOperatingController userOperatingController(){
        return new UserOperatingController(
                serviceConfig.billboardService(),
                serviceConfig.groupBillboardService(),
                serviceConfig.locationService(),
                serviceConfig.adService()
        );
    }

    @Bean
    public WebController webController(){
        return new WebController(
                serviceConfig.billboardService(),
                serviceConfig.locationService(),
                serviceConfig.adService()
        );
    }

    @Bean
    public AdminController adminController(){
        return new AdminController(
                serviceConfig.userService(),
                serviceConfig.securityService(),
                serviceConfig.authUserService(),
                serviceConfig.billboardService(),
                serviceConfig.locationService(),
                serviceConfig.groupBillboardService()
        );
    }



    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("en"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }
}
