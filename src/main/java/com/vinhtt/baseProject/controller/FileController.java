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

import javax.validation.Valid;
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

//    @GetMapping(path = "/fromDisc")
//    public ResponseEntity<?> getFileFromDisc(@RequestParam("disc") String disc){
//        File dir = new File(disc+":\\");
//        getChild(dir);
//        return ResponseEntity.ok().body(
//                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
//                        .message(commonProperties.getMESSAGE_SUCCESS())
//                        .data(dir).build());
//    }

    @PostMapping(path = "/fromPath")
    public ResponseEntity<?> getFileFromPath(@Valid @RequestBody MyFile myFile){
//        File dir = new File(myFile.getPath());
        getChild(myFileRepository.save(new MyFile(myFile.getName(), myFile.getPath(), myFile.isFile(), myFile.getId())));
        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(myFileRepository.findAll()).build());
    }

    @GetMapping()
    public ResponseEntity<?> getAllFiles(){
        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(myFileRepository.findAll()).build());
    }

    public void getChild(MyFile myFile) {
        File dir = new File(myFile.getPath());
        if (dir.isDirectory()) {
            System.out.println("Folder: "+ dir.getPath());
            if(dir.listFiles() != null) {
                for (File listFile : dir.listFiles()) {
                    getChild(myFileRepository.save(new MyFile(listFile.getName(), listFile.getPath(), listFile.isFile(), myFile.getId())));
                }
            }
        }else{
            System.out.println("File: "+ myFile.getPath());
        }
    }
}
