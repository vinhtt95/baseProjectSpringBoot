package com.vinhtt.baseProject.controller;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.File;
import com.vinhtt.baseProject.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class Welcome {

    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private FileRepository fileRepository;

    @GetMapping
    public ResponseEntity<?> welcomeToBaseProject() {
        List<File> files = fileRepository.findAll();
//        files.add(new File(2,1,"Folder02","root/Folder02","",false));
        
        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(files).build());
    }
}
