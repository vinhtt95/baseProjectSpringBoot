package com.vinhtt.baseProject.controller;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.MyFile;
import com.vinhtt.baseProject.repository.MyFileRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FileController {
    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private MyFileRepository myFileRepository;

    @GetMapping(path = "/formPath")
    public ResponseEntity<?> getFileFromPath(@RequestParam("path") String path){
        File dir = new File(path);
        for(File file: dir.listFiles()){
            if(file.isFile()){
                MyFile myFile = new MyFile(file);
                System.out.println(myFile.toString());
            }
        }
        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(dir).build());
    }
}
