package com.lee.plcanalysis.pojo;

import lombok.Data;

@Data
public class Project {
    private Long id;
    private String name;
    private String description;
    private Long userId;

    public Project() {

    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, Long userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
