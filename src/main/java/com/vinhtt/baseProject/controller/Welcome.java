package com.vinhtt.baseProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public String welcomeToBaseProject(){
        return "Welcome to Base Spring Boot Project";
    }
}
