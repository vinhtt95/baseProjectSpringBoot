package com.vinhtt.baseProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@Data
@Entity
@Table(name = "myfiles")
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String path;
    private long folderId;
    private boolean isFile;
    private boolean isDelete = false;

    public MyFile(String name, String path, boolean isFile, long folderId) {
        this.name = name;
        this.path = path;
        this.isFile = isFile;
        this.folderId = folderId;
    }

    public MyFile(File file) {
        this.name = file.getName();
        this.path = file.getPath();
        this.isFile = file.isFile();
    }

}
