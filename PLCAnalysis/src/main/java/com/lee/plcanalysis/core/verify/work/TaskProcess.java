package com.lee.plcanalysis.core.verify.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成任务命令，提交任务进程
 */
public class TaskProcess{
    private final List<String> modelPathList;
    private final List<String> queryPathList;
    private final Map<String,List<String>> query2ReqId;
    private final List<Map<String,Trace>> tracesList;
    private final List<Map<String,Boolean>> resultList;
    private final Map<List<String>,List<Trace>> testCaseList;

    public TaskProcess(List<String> modelPathList, List<String> queryPathList, Map<String,List<String>> query2ReqId) {
        this.modelPathList = modelPathList;
        this.queryPathList = queryPathList;
        this.query2ReqId = query2ReqId;
        this.tracesList = new ArrayList<>();
        this.resultList = new ArrayList<>();
        this.testCaseList = new HashMap<>();
    }

    public List<Map<String, Trace>> getTracesList() {
        return tracesList;
    }

    public List<Map<String, Boolean>> getResultList() {
        return resultList;
    }

    public Map<List<String>,List<Trace>> getTestCaseList() {
        return testCaseList;
    }

    /**
     * 提交任务进程
     */
    public void submitTask(int x){
        int n = modelPathList.size();
        for(int i = 0; i < n; ++i){
            try {
                String cmd = "E:/Uppaal/uppaal-4.1.24/bin-Windows/verifyta.exe -t 1 " + modelPathList.get(i) + " " + queryPathList.get(i);
                Process process = Runtime.getRuntime().exec(cmd);
                VerifyResultStreamHandler psh1 = new VerifyResultStreamHandler(process.getInputStream());
                TraceStreamHandler psh2 = new TraceStreamHandler(process.getErrorStream());
                new Thread(psh1).start();
                new Thread(psh2).start();

                process.getOutputStream().close();

                int exitValue = process.waitFor();
                System.out.println(exitValue == 0 ? "执行成功！" : "执行失败！");
                System.out.println("*********************");

                if(x == 0){
                    reqIDMapToVerifyResult(psh1.getResult(), psh2.getResult(), "model" + i + ".q");
                }else{
                    reqIDMapToTestCase(psh2.getResult(), "testGen" + i + ".q");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void reqIDMapToTestCase(List<Trace> traces, String queryFile){
        List<String> reqIDList = query2ReqId.get(queryFile);
        System.out.println(queryFile);
        if(reqIDList == null){
            throw new RuntimeException("不存在该查询文件");
        }
        testCaseList.put(reqIDList, traces);
    }

    /**
     * 将需求ID与验证结果绑定
     * @param result 执行结果序列
     * @param traces 反例路径序列
     * @param queryFile 当前查询的文件
     */
    public void reqIDMapToVerifyResult(List<Boolean> result, List<Trace> traces, String queryFile){
        List<String> reqIDList = query2ReqId.get(queryFile);
        if(reqIDList == null){
            throw new RuntimeException("不存在该查询文件");
        }
        int index = 0;
        for (int i = 0; i < reqIDList.size(); ++i){
            boolean res = result.get(i);
            String id = reqIDList.get(i);
            HashMap<String,Boolean> reqID2Result = new HashMap<>();
            reqID2Result.put(id, res);
            resultList.add(reqID2Result);

            if(!res){
                HashMap<String,Trace> reqID2trace = new HashMap<>();
                reqID2trace.put(id, traces.get(index++));
                tracesList.add(reqID2trace);
            }
        }
    }
}
