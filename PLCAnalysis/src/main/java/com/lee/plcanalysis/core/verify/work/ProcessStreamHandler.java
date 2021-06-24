package com.lee.plcanalysis.core.verify.work;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public abstract class ProcessStreamHandler <T> implements Runnable{
    public InputStream is;

    public ProcessStreamHandler(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String tmpResult;
            while((tmpResult = reader.readLine()) != null){
                resultGenerate(tmpResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void resultGenerate(String str);

    public abstract List<T> getResult();
}
