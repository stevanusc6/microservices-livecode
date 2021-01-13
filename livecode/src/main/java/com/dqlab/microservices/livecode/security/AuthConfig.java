package com.dqlab.microservices.livecode.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthConfig.class);

    @Value("${http.auth-token-header-name:X-Auth-Token}")
    private String apiKeyAuthHeader;

    @Value("${http.auth-token:4ec792fe-521f-420e-b4d6-88ab326f16e8}")
    private String apiKeyAuthToken;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthFilter filter = new AuthFilter(apiKeyAuthHeader);
        filter.setAuthenticationManager(new AuthManager(apiKeyAuthToken));

        http.antMatcher("/api/**")
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
