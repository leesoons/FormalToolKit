package com.lee.plcanalysis.core.verify.work;

import java.util.*;

public class Trace {
    private Map<String,List<Boolean>> ioTrace;
    private final List<String> traceList;

    public Trace(List<String> traceList) {
        this.traceList = traceList;
    }

    /**
     * @return 返回只包含输入输出变量的值路径序列
     */
    public Map<String, List<Boolean>> getIOTrace() {
        if(ioTrace == null){
            this.ioTrace = new HashMap<>();
            for(String state: traceList){
                //System.out.println(state);
                if(state.contains("program1.L0")) {
                    ioGenerator(state, "I0");
                }else if(state.contains("program1.end")){
                    ioGenerator(state, "Q0");
                }
            }
        }
        return ioTrace;
    }

    /**
     * @param state 当前状态语句
     * @param target 识别的变量目标
     */
    public void ioGenerator(String state, String target){
        String[] vars = state.split(" ");
        for (String var : vars) {
            if(!var.contains(target)){
                continue;
            }
            String[] tmp = var.split("=");
            List<Boolean> booleans = ioTrace.getOrDefault(tmp[0], new ArrayList<>());
            booleans.add(tmp[1].equals("1"));
            ioTrace.put(tmp[0], booleans);
        }
    }

}
