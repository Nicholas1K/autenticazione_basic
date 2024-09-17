package com.dan_vega_tutorial.spring_security_jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;



@RestController("/")
public class HomeController {
    
    @GetMapping
    public String home(Principal principal){
        return "Hello, " + principal.getName();
    }
    
}
