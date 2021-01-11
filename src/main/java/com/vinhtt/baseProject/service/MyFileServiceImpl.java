package com.vinhtt.baseProject.service;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.MyFile;
import com.vinhtt.baseProject.repository.MyFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class MyFileServiceImpl implements MyFileService {

    private CommonProperties commonProperties;

    private MyFileRepository myFileRepository;

    public ResponseEntity<?> getFileFromPath(MyFile myFile) throws Exception{
        getChild(myFileRepository.save(new MyFile(myFile.getName(), myFile.getPath(), myFile.isFile(), myFile.getId())));
        return ResponseEntity.ok().body(
                ApiResponse.builder().code(commonProperties.getCODE_SUCCESS())
                        .message(commonProperties.getMESSAGE_SUCCESS())
                        .data(myFileRepository.findAll()).build());
    }

    private void getChild(MyFile myFile) {
        File dir = new File(myFile.getPath());
        if (dir.isDirectory()) {
            System.out.println("Folder: "+ myFile.getPath());
            if(dir.listFiles() != null) {
                for (File listFile : dir.listFiles()) {
                    if(myFileRepository.existsByPath(listFile.getPath())){
                        System.out.println("Folder exists: "+ listFile.getPath());
                        getChild(myFileRepository.findByPath(listFile.getPath()));
                    }else {
                        System.out.println("Folder new: "+ listFile.getPath());
                        getChild(myFileRepository.save(new MyFile(listFile.getName(), listFile.getPath(), listFile.isFile(), myFile.getId())));
                    }
                }
            }
        }else{
            System.out.println("File: "+ myFile.getPath());
        }
    }
}
