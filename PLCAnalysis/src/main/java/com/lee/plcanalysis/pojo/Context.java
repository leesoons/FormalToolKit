package com.lee.plcanalysis.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Context {
    private long projectId;
    private String code;
    private String priorityArray;
    private String description;

    public Context() {
    }

    public Context(long projectId, String code, String priorityArray, String description) {
        this.projectId = projectId;
        this.code = code;
        this.priorityArray = priorityArray;
        this.description = description;
    }

    public List<List<String>> getPriorityArrayList(){
        List<List<String>> priorityArray = new ArrayList<>();
        //优先级数组解析
        /**/
        return priorityArray;
    }
}
