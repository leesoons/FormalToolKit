package com.lee.plcanalysis.core.verify;

import com.lee.plcanalysis.core.verify.translators.TestCaseGenFormula;
import com.lee.plcanalysis.core.verify.translators.UppaalModel;
import com.lee.plcanalysis.core.verify.translators.VerifyFormula;
import com.lee.plcanalysis.core.verify.work.TaskProcess;
import com.lee.plcanalysis.core.verify.work.Trace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务验证的前端，供服务端调用
 */
public class VerifyFacade {
    private final UppaalModel uppaalModel;
    private TaskProcess taskProcess;
    private List<String> modelFilePath;
    private List<String> queryFilePath;

    public VerifyFacade() {
        this.uppaalModel = new UppaalModel();
        this.modelFilePath = new ArrayList<>();
        this.queryFilePath = new ArrayList<>();
    }

    /**
     * 提交需求进行解析
     * @param requirement 目标需求
     * @param priorityArray 优先级数组
     * @param isFile 需求的形式
     */
    public void submitRequirement(String requirement, List<List<String>> priorityArray, boolean isFile) throws IOException {
        uppaalModel.parseRequirement(requirement, priorityArray, isFile);
    }

    /**
     * 提交程序进行解析
     * @param program 目标程序
     * @param isFile 程序形式
     * @throws IOException 文件不存在
     */
    public void submitProgram(String program, boolean isFile) throws IOException {
        uppaalModel.parseProgram(program, isFile);
    }

    /**
     * 提交验证任务，生成验证进程进行任务调度
     */
    public void submitVerifyTaskProcess(){
        this.modelFilePath = uppaalModel.getUppaalModel(0);
        if(modelFilePath.isEmpty()){
            throw new RuntimeException("模型未成功生成，请保证正确指定程序！");
        }
        VerifyFormula verifyFormula = new VerifyFormula(uppaalModel.getObserverGroup());
        this.queryFilePath = verifyFormula.generateFormula();
        if(queryFilePath.isEmpty()) {
            throw new RuntimeException("验证公式生成失败，请保证正确指定需求！");
        }

        //提交任务进程
        taskProcess = new TaskProcess(modelFilePath, queryFilePath, new HashMap<>(verifyFormula.getQuery2ReqID()));
        taskProcess.submitTask(0);
    }

    /**
     * 提交测试生成进程
     * @param selections 测试生成指定策略
     */
    public void submitTestGenTaskProcess(List<Integer> selections){
        this.modelFilePath = uppaalModel.getUppaalModel(0);
        if(modelFilePath.isEmpty()){
            throw new RuntimeException("模型未成功生成，请保证正确指定程序！");
        }
        TestCaseGenFormula testCaseGenFormula = new TestCaseGenFormula(selections, uppaalModel.getObserverGroup());
        this.queryFilePath = testCaseGenFormula.generateFormula();
        if(queryFilePath.isEmpty()) {
            throw new RuntimeException("验证公式生成失败，请保证正确指定需求！");
        }

        //提交任务进程
        taskProcess = new TaskProcess(modelFilePath, queryFilePath, new HashMap<>(testCaseGenFormula.getQuery2ReqID()));
        taskProcess.submitTask(1);
    }

    /**
     * @return 任务执行后生成的反例路径
     */
    public List<Map<String, Trace>> getTraces(){
        if(taskProcess == null){
            return null;
        }
        return taskProcess.getTracesList();
    }

    /**
     * @return 任务执行后的验证结果
     */
    public List<Map<String,Boolean>> getResults(){
        if(taskProcess == null){
            return  null;
        }
        return taskProcess.getResultList();
    }

    public Map<List<String>,List<Trace>> getTestCases(){
        return taskProcess.getTestCaseList();
    }
}
