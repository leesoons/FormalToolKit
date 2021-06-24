package com.lee.plcanalysis.pojo;

import lombok.Data;

@Data
public class Requirement {
    public enum ReqState{
        CHECK_PASSED,
        CHECK_FAILED,
        NOT_CHECKED
    }

    private long id;
    private String text;
    private String description;
    private ReqState state;
    private long project;
    private String counterExample;

    public Requirement(){}

    public Requirement(String text, String description, ReqState state, long project, String counterExample) {
        this.text = text;
        this.description = description;
        this.state = state;
        this.project = project;
        this.counterExample = counterExample;
    }
}
