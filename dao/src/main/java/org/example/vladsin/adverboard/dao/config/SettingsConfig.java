package org.example.vladsin.adverboard.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class SettingsConfig {

    @Bean
    public DatasourceSettings datasourceSettings(){
        return new DatasourceSettings();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Properties hibernateProperties(){
        try (final InputStream inputStream = new ClassPathResource("hibernate.properties").getInputStream()){
            final Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
