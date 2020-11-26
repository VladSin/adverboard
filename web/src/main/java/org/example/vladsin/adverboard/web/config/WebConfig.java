package org.example.vladsin.adverboard.web.config;

import org.example.vladsin.adverboard.service.config.ServiceConfig;
import org.example.vladsin.adverboard.web.controller.FirstPageController;
import org.example.vladsin.adverboard.web.controller.LoginController;
import org.example.vladsin.adverboard.web.controller.RegistrationController;
import org.example.vladsin.adverboard.web.controller.rest.AuthUserRestController;
import org.example.vladsin.adverboard.web.controller.rest.UserRestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

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
                serviceConfig.authUserService()
        );
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(
                serviceConfig.securityService()
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
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("en"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }
}
