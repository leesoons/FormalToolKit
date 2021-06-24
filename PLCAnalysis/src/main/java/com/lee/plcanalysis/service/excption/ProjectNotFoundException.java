package com.lee.plcanalysis.service.excption;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Long id){
        super("Could not found Project" + id);
    }
}
