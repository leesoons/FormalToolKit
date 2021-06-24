package com.lee.plcanalysis.pojo;

import lombok.Data;

@Data
public class Task {
    private String info;
    //private double timeSpend;

    public Task(String info) {
        this.info = info;
    }
}
