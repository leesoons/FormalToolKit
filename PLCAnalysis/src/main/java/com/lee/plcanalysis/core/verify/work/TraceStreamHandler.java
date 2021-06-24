package com.lee.plcanalysis.core.verify.work;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TraceStreamHandler extends ProcessStreamHandler {
    private final List<Trace> result;
    private String res = "";
    private final List<String> tmpRes = new ArrayList<>();

    public TraceStreamHandler(InputStream is) {
        super(is);
        this.result = new ArrayList<>();
    }

    @Override
    public void resultGenerate(String str) {
        if(!res.contains("#depth")){
            res += str;
        }
        if(str.contains("#depth")){
            tmpRes.add(res.substring(res.indexOf("State")));
            res = "";
        }
    }



    @Override
    public List<Trace> getResult() {
        List<List<String>> res = splitTraces();
        for(List<String> trace: res){
            result.add(new Trace(trace));
        }
        return result;
    }

    /**
     * 将多条反例序列进行分离
     * @return 反例分离序列
     */
    public List<List<String>> splitTraces(){
        int index = 0;
        int last = -1;
        List<List<String>> results = new ArrayList<>();
        for(int i = 0; i < tmpRes.size(); ++i){
            String state = tmpRes.get(i);
            int depth = Integer.parseInt(state.substring(state.indexOf("depth") + 6, state.length() - 1));
            if(depth < last){
                results.add(new ArrayList<>(tmpRes.subList(index, i)));
                index = i;
            }
            last = depth;
        }
        results.add(new ArrayList<>(tmpRes.subList(index, tmpRes.size())));
        return results;
    }
}
