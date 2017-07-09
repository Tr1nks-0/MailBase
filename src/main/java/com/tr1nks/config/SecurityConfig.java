package com.tr1nks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security config
 * права доступа
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * {@inheritDoc}
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().anyRequest().permitAll();//todo fixme
    }
}
