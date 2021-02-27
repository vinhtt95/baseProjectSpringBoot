package com.vinhtt.baseProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private CommonProperties commonProperties;

    @GetMapping(path = "info")
    public Object getAllCard(){
        Resource resource = new ClassPathResource("/static/account.json");
        Object result= new Object();
        try {
            ObjectMapper mapper = new ObjectMapper();
            result =  mapper.readValue(resource.getInputStream(), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
