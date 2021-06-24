package com.lee.plcanalysis.core.verify.work;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VerifyResultStreamHandler  extends ProcessStreamHandler {
    private final List<Boolean> result;

    public VerifyResultStreamHandler(InputStream is) {
        super(is);
        this.result = new ArrayList<>();
    }

    @Override
    public void resultGenerate(String str) {
        if(str.contains("satisfied")){
            if(!str.contains("NOT")){
                result.add(true);
            }else {
                result.add(false);
            }
        }
    }

    @Override
    public List<Boolean> getResult() {
        return result;
    }
}
