package com.lee.plcanalysis.core.verify.translators;

import com.lee.plcanalysis.core.plc.PLCFacade;
import com.lee.plcanalysis.core.plc.model.ProgramModel;
import com.lee.plcanalysis.core.sps.SPSFacade;
import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.Observer;
import com.lee.plcanalysis.core.sps.model.ObserverGroup;
import com.lee.plcanalysis.core.verify.until.FileGenHelper;

import java.io.*;
import java.util.*;

public class UppaalModel {
    public int modelIndex = 0;

    private final PLCFacade plcFacade;
    private final SPSFacade spsFacade;
    private ObserverGroup observerGroup;

    public UppaalModel() {
        this.plcFacade = new PLCFacade();
        this.spsFacade = new SPSFacade();
    }

    public ObserverGroup getObserverGroup() {
        return observerGroup;
    }

    /**
     * 根据需求和程序的上下文生成完整（经过优化）的uppaal模型，存储成xta格式的文件
     * @return 生成的文件地址列表
     */
    public List<String> getUppaalModel(int n){
        List<String> modelFilePathList = new ArrayList<>();

        List<Map<Requirement, Observer>> req2Obs = this.observerGroup.getGenerateObserverGroup();
        for(Map<Requirement, Observer> group: req2Obs){
            Set<String> requirementVars = new HashSet<>();
            for(Requirement requirement: group.keySet()){
                requirementVars.addAll(requirement.getInfluenceVars());
            }
            ProgramModel programModel = plcFacade.getProgramModel(requirementVars, n);
            modelFilePathList.add(generateCompleteModel(programModel, group));
        }

        return modelFilePathList;
    }

    /**
     * 将程序和需求模块解析出的模型进行拼接，构建出完整模型
     * @param programModel 目标程序模型
     * @param observerMap 目标观察者组（用于模型优化）
     * @return uppaal模型文件地址
     */
    public String generateCompleteModel(ProgramModel programModel, Map<Requirement,Observer> observerMap) {
        StringBuilder completeModel = new StringBuilder();
        List<String> processName = new ArrayList<>(programModel.getProcessName());

        //模型变量和主程序定义部分
        completeModel.append(programModel.getDefinitionPart()).append("\n").append(programModel.getProgramMetaModel());

        //观察者定义部分
        Set<String> obsSet =  new HashSet<>();
        for(Observer observer: observerMap.values()){
            String code = observer.getCode();
            if(!obsSet.contains(code)){
                completeModel.append(code).append("\n");
                obsSet.add(code);
            }
        }

        //程序实例表达式
        for(String instanceExpr: programModel.getInstanceExpression()){
            completeModel.append(instanceExpr).append(";\n");
        }

        //观察者实例表达式
        int index = 0;
        for(Observer observer: observerMap.values()){
            StringBuilder obsInstanceExpr = new StringBuilder();
            String observerName = observer.getName();
            obsInstanceExpr.append(observerName)
                    .append(index)
                    .append("=")
                    .append(observerName)
                    .append("();\n");
            completeModel.append(obsInstanceExpr);
            processName.add(observerName + index++);
        }

        //系统实例化表达式
        completeModel.append("system ");
        processName.forEach(name -> completeModel.append(name).append(", "));
        completeModel.delete(completeModel.length() - 2, completeModel.length()).append(";");

        //System.out.println(completeModel);
        return FileGenHelper.generateFile("F:\\project\\PLCAnalysis\\src\\main\\resources\\modelFile", completeModel.toString(), "model" + (modelIndex++) + ".xta");
    }

    /**
     * 调用程序处理模块进行程序解析
     * @param program 程序输入
     * @param isFile 程序目标是否是文件
     * @throws IOException 不存在目标文件
     */
    public void  parseProgram(String program, boolean isFile) throws IOException {
        if(isFile){
            plcFacade.parseFile(program);
        }else {
            plcFacade.parseString(program);
        }
    }

    /**
     * 调用需求处理模块进行需求解析
     * @param requirements 需求输入
     * @param priorityArray 优先级数组
     * @param isFile 需求目标是否是文件
     * @throws IOException 不存在目标文件
     */
    public void parseRequirement(String requirements, List<List<String>> priorityArray, boolean isFile) throws IOException {
        if(isFile){
            spsFacade.parseFile(requirements);
        }else {
            spsFacade.parseString(requirements);
        }
        this.observerGroup = spsFacade.getObserverGroup(priorityArray);
    }
}
