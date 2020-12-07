package com.vinhtt.baseProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private int id;
    private int parentId;
    private String name;
    private String address;
    private String addressSub;
    private boolean isFile;
}
