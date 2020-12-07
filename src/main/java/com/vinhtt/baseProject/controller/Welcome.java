package com.vinhtt.baseProject.controller;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Welcome {

    @Autowired
    private CommonProperties commonProperties;

    @GetMapping
    public ResponseEntity<?> welcomeToBaseProject() {
        Map<String, Object> reponse = new HashMap<>();
        reponse.put("id", 2);
        reponse.put("parentId", 1);
        reponse.put("name", "Folder02");
        reponse.put("address", "root/Folder02");
        reponse.put("addressSub", "");
        reponse.put("isFile", false);


        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(reponse).build());
    }
}
