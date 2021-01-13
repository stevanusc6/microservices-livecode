package com.dqlab.microservices.livecode.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthManager implements AuthenticationManager {

    private static final Logger logger = LoggerFactory.getLogger(AuthManager.class);

    private final String apiKeyToken;

    public AuthManager(String apiKeyToken) {
        this.apiKeyToken = apiKeyToken;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        if (!apiKeyToken.equals(principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        } else {
            authentication.setAuthenticated(true);
            return authentication;
        }
    }
}
