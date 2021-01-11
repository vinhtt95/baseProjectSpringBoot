package com.vinhtt.baseProject.service;

import com.vinhtt.baseProject.model.MyFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MyFileService {
    public ResponseEntity<?> getFileFromPath(MyFile myFile) throws Exception;
}
