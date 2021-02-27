package com.vinhtt.baseProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinhtt.baseProject.config.CommonProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class ChatController {
    @Autowired
    private CommonProperties commonProperties;

    @GetMapping(path = "/{id}")
    public Object getAllCard(
            @PathVariable("id") String id,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit){
        Resource resource = new ClassPathResource("/static/chat.json");
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
