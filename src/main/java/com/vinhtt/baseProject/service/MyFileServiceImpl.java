package com.vinhtt.baseProject.service;

import com.vinhtt.baseProject.config.CommonProperties;
import com.vinhtt.baseProject.entity.ApiResponse;
import com.vinhtt.baseProject.model.MyFile;
import com.vinhtt.baseProject.repository.MyFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MyFileServiceImpl implements MyFileService {

    private CommonProperties commonProperties;

    private MyFileRepository myFileRepository;

    public ResponseEntity<?> getFileFromPath(MyFile myFile) throws Exception{
        if(myFileRepository.existsByPath(myFile.getPath())){
            System.out.println("Folder exists: "+ myFile.getPath());
            getChild(myFileRepository.findByPath(myFile.getPath()));
        }else {
            System.out.println("Folder new: "+ myFile.getPath());
            getChild(myFileRepository.save(new MyFile(myFile.getName(), myFile.getPath(), myFile.isFile(), myFile.getId())));
        }
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
                checkDeleted(dir, myFile);
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

    private void checkDeleted(File dir, MyFile myFile){
        File[] files = dir.listFiles();
        ArrayList<MyFile> listMyFileChild = myFileRepository.findByFolderId(myFile.getId());

        boolean isDelete;
        for (MyFile file : listMyFileChild) {
            isDelete = true;

            for (File dirFile: files){
                if (file.getPath().equals(dirFile.getPath())){
                    isDelete = false;
                    break;
                }
            }
            if(isDelete){
                System.out.println("Deleted: "+ file.getPath());
            }
        }
    }
}
