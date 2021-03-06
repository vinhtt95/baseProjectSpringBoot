package com.vinhtt.baseProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.Card;
import com.vinhtt.baseProject.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private CardRepository cardRepository;

    @GetMapping(path = "getAll")
    public Object getAllCard(){
        Resource resource = new ClassPathResource("/static/getAllCard.json");
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
