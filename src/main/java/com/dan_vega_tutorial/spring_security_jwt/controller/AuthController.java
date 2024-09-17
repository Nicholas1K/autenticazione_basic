package com.dan_vega_tutorial.spring_security_jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dan_vega_tutorial.spring_security_jwt.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class AuthController {
    
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService){
        this.tokenService = tokenService;
    }

    /*POST CHE RESTITUISCE IL TOKEN FORNENDO LE CREDENZIALI  */
    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token request for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted {}", token);
        return token;
    }
    
}
