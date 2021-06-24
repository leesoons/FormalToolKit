package com.lee.plcanalysis.service.excption;

import com.lee.plcanalysis.pojo.Requirement;

public class FailedToCreateRequirementException extends RuntimeException{
    public FailedToCreateRequirementException(Requirement requirement){
        super("could not create requirement: " + requirement);
    }
}
