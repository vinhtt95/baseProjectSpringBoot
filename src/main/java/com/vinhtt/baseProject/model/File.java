package com.vinhtt.baseProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private long parentId;
    private String name;
    private String address;
    private String addressSub;
    private boolean isFile;

    public File(long parentId, String name, String address, String addressSub, boolean isFile) {
        this.parentId = parentId;
        this.name = name;
        this.address = address;
        this.addressSub = addressSub;
        this.isFile = isFile;
    }
}
