package com.lee.plcanalysis.service.excption;

public class RequirementNotFoundException extends RuntimeException{
    public RequirementNotFoundException(Long id){
        super("Could not found requirement: " + id);
    }
}
