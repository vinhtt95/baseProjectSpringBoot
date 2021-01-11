package com.vinhtt.baseProject.controller;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.MyFile;
import com.vinhtt.baseProject.repository.MyFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vinhtt.baseProject.service.MyFileServiceImpl;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FileController {
    @Autowired
    private CommonProperties commonProperties;

    @Autowired
    private MyFileServiceImpl myFileService;

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
        try{
          return myFileService.getFileFromPath(myFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .code(commonProperties.getCODE_UNDEFINE_ERROR())
                            .message(commonProperties.getMESSAGE_UNDEFINE_ERROR()).build()
            );
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllFiles(){
        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(myFileRepository.findAll()).build());
    }
}
