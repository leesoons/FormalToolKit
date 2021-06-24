package com.lee.plcanalysis.service.excption;

import com.lee.plcanalysis.pojo.Context;

public class FailedToUpdateContextException extends RuntimeException{
    public FailedToUpdateContextException(Context context){
        super("Could not submit modification!" + context);
    }
}
