package org.example.vladsin.adverboard.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/login/**",
                        "/logout/**",
                        "/register/**",
                        "/adminAuth/**",
                        "/web/**",
                        "/user/**",
                        "/billboard/**",
                        "/group/**",
                        "/auth/**",
                        "/error.html").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/operating/**").hasRole("USER")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
