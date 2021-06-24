package com.lee.plcanalysis.core.sps.model;

import com.lee.plcanalysis.core.sps.base.Requirement;

import java.util.*;

/**
 * 获取sps包下解析的需求，进行需求分析，并生成相应的观察者组。需求分析包含以下几个方面：
 * 1. 冲突规约识别（设置优先级）
 * 2. 规约分组（为可能的优化做准备）
 */
public class ObserverGroup {

    private final Map<String,ObserverPattern> patternMap = ObserverPattern.loadPattern(ObserverPattern.PATTERN_FILE);
    private final List<Observer> observers;
    private final List<Requirement> requirements;
    private List<List<Requirement>> circularDependencyRequirements;

    private Map<String,List<String>> circularDependencyTable;
    private Map<String,List<String>> conflictedRequirements;
    private Map<String,Requirement> reqId2Requirement;

    private List<Map<Requirement, Observer>> generateObserverGroup;

    public ObserverGroup() {
        this.observers = new ArrayList<>();
        this.requirements = new ArrayList<>();
        this.conflictedRequirements = new HashMap<>();
        this.reqId2Requirement = new HashMap<>();
        this.generateObserverGroup = new ArrayList<>();
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public List<List<Requirement>> getCircularDependencyRequirements() {
        return circularDependencyRequirements;
    }

    public Map<String, List<String>> getCircularDependencyTable() {
        return circularDependencyTable;
    }

    public Map<String, List<String>> getConflictedRequirements() {
        return conflictedRequirements;
    }

    public List<Map<Requirement,Observer>> getGenerateObserverGroup() {
        return generateObserverGroup;
    }

    public Map<String, Requirement> getReqId2Requirement() {
        /*for(Requirement requirement: requirements){
            reqId2Requirement.put(requirement.getReqID(), requirement);
        }*/
        return reqId2Requirement;
    }

    public void addRequirement(Requirement requirement){
        requirements.add(requirement);
        reqId2Requirement.put(requirement.getReqID(), requirement);
    }

    public boolean hasCircularDependency(){
        return false;
    }

    public boolean hasConflict(){
        boolean flag = false;

        for (int i = 0; i < requirements.size(); i++) {
            Requirement req1 = requirements.get(i);
            String tar1 = req1.getProperty().getExpression().getExpr();
            if(conflictedRequirements.get(tar1) == null){
                for (int j = 0; j < requirements.size(); j++) {
                    if(i != j){
                        Requirement req2 = requirements.get(j);
                        String tar2 = req2.getProperty().getExpression().getExpr();
                        if(tar1.equals(tar2)){
                            flag = true;
                            conflictedRequirements.computeIfAbsent(tar1, value -> new ArrayList<>(Collections.singleton(req1.getReqID())));
                            conflictedRequirements.get(tar1).add(req2.getReqID());
                        }
                    }
                }
            }
        }

        return flag;
    }

    /**
     * @param priorityArray 对存在冲突的规约进行分组，根据每组中的规约顺序根据优先级确定
     */
    public void handleConflict(List<List<String>> priorityArray){
        Set<String> reqIdSet = new HashSet<>();
        //将存在优先级的规约分组
        for(List<String> priority: priorityArray){
            Map<Requirement,Observer> observerMap = new HashMap<>();
            for(String reqID: priority){
                getObserverMapEntry(reqID, observerMap);
                reqIdSet.add(reqID);
            }
            generateObserverGroup.add(observerMap);
        }
        //System.out.println(reqIdSet);
        //将剩下的规约分为一组
        Map<Requirement,Observer> observerMap = new HashMap<>();
        for(String reqId: getReqId2Requirement().keySet()){
            if(!reqIdSet.contains(reqId)) {
                getObserverMapEntry(reqId, observerMap);
            }
        }
        generateObserverGroup.add(observerMap);
        reqIdSet = null;
    }

    public void getObserverMapEntry(String reqID, Map<Requirement,Observer> observerMap){
        Requirement requirement = getReqId2Requirement().get(reqID);
        Observer observer = patternMap.get(requirement.key()).getObserver();
        if(observer == null){
            throw new RuntimeException("Pattern " + requirement.key() + " not found!");
        }
        List<String> params = observer.getParam();
        if(params.contains("<Qin>")){
            observer.setCode("<Qin>", requirement.getScope().getExpressions().get(0).getExpr());
        }
        if(params.contains("<Rin>")){
            observer.setCode("<Rin>", requirement.getScope().getExpressions().get(1).getExpr());
        }
        if(params.contains("tL1")){
            int[] delayL = requirement.getDelay().getExpressions().get(0);
            observer.setCode("tL1", (delayL[0] + delayL[2]) + "");
        }
        if(params.contains("tR1")){
            int[] delayR = requirement.getDelay().getExpressions().get(0);
            observer.setCode("tR1", (delayR[0] + delayR[2]) + "");
        }
        if(params.contains("tL2")){
            int[] delayL = requirement.getDelay().getExpressions().get(1);
            observer.setCode("tL2", (delayL[0] + delayL[2]) + "");
        }
        if(params.contains("tR2")){
            int[] delayR = requirement.getDelay().getExpressions().get(1);
            observer.setCode("tR2", (delayR[0] - delayR[2]) + "");
        }
        observerMap.put(requirement, observer);
    }

}
