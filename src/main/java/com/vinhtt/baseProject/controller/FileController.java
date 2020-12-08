package com.vinhtt.baseProject.controller;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.File;
import com.vinhtt.baseProject.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FileController {
    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private FileRepository fileRepository;

    @GetMapping
    public ResponseEntity<?> getAllFile(){
        List<File> files = fileRepository.findAll();

        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(files).build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getFileByParentId(
            @PathVariable(value = "id") long id){
        File files = fileRepository.findById(id).orElse(null);

        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(files).build());
    }

    @GetMapping(path = "/sub_file/{id}")
    public ResponseEntity<?> getSubFileByParentId(
            @PathVariable(value = "id") long id){
        List<File> files = fileRepository.findAllByParentId(id);

        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(files).build());
    }
}
