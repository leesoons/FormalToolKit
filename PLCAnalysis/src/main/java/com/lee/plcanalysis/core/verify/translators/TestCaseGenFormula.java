package com.lee.plcanalysis.core.verify.translators;

import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.Observer;
import com.lee.plcanalysis.core.sps.model.ObserverGroup;
import com.lee.plcanalysis.core.verify.until.FileGenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试覆盖公式生成，目前考虑的测试覆盖标准：
 * 1. 需求公式覆盖：对组合需求（存在控制冲突的需求）进行公式子句覆盖，以生成包含优先级影响关系的测试用例
 * 2. 观察者覆盖：对观察者的所有路径都进行覆盖，同时对观察者进入事件范围时不同的状态进行覆盖
 * 需要注意的是，这里生成的用例仅用于生成输入输出的值，对时间不进行记录，这是因为测试时不可能对时间进行准确的覆盖。
 * 因此实时测试必须要与‘测试者（Tester）’结合，以一种运行时的方式对系统进行验证。
 * 需要注意的是，观察者虽然与测试者的目标类似，但观察者仅仅针对范围进行监视，通过验证公式来检验规约是否成立；
 * 而Tester需要监视系统的输出是否满足规范，因此必须将属性和范围集合考虑，这里的难点在于优先级的处理
 */
public class TestCaseGenFormula implements Formula {
    private int formulaIndex = 0;
    private final ObserverGroup observerGroup;
    private final Map<String,List<String>> query2ReqID;
    private final List<Integer> selections;
    List<String> queryFilePath = new ArrayList<>();

    public TestCaseGenFormula(List<Integer> selections, ObserverGroup observerGroup) {
        this.observerGroup = observerGroup;
        this.selections = selections;
        this.query2ReqID = new HashMap<>();
    }

    public Map<String,List<String>> getQuery2ReqID(){
        return query2ReqID;
    }

    @Override
    public List<String> generateFormula() {


        if(selections.contains(0)){
            queryFilePath.addAll(requirementCover());
        }

        if(selections.contains(1)){
            queryFilePath.addAll(observerCover());
        }

        return queryFilePath;
    }


    public List<String> requirementCover(){
        List<String> paths = new ArrayList<>();

        int index = 1;
        for(Map<Requirement, Observer> observerMap: observerGroup.getGenerateObserverGroup()){
            StringBuilder formulas = new StringBuilder();
            StringBuilder last = new StringBuilder();
            List<String> formulaList = new ArrayList<>();
            List<String> reqIDList = new ArrayList<>();

            List<String> obsNames = getObserverName(observerMap);
            List<String> property = getProperty(observerMap);

            if(obsNames.size() == 1){
                formulas.append("E<> (program1.end and ").append(obsNames.get(0)).append(".in)").append("\n");
            }else{
                /*
                * 构造属性覆盖公式集
                * */
                for(int i = 0; i < obsNames.size(); ++i){
                    StringBuilder formula = new StringBuilder();
                    formula.append("A[] (program1.end and ");
                    formula.append(last).append(obsNames.get(i)).append(".in) imply ").append(property.get(i));
                    formulaList.add(formula.toString());
                    last.append("not ").append(obsNames.get(i)).append(".in").append(" and ");
                }

                List<String> newFormulaList = new ArrayList<>();
                for (int i = 1; i < formulaList.size(); ++i){
                    for(int j = 0; j < i; ++j){
                        newFormulaList.add(formulaList.get(i).replace(obsNames.get(j)+".in", "false"));
                    }
                }

                newFormulaList.forEach(formula -> formulas.append(formula).append("\n"));

            }

            observerMap.keySet().forEach(requirement -> reqIDList.add(requirement.getReqID()));

            queryFileGen(paths, reqIDList, formulas, index++);
        }
        return paths;
    }

    public List<String> observerCover(){
        List<String> paths = new ArrayList<>();

        int index = 1;
        for(Map<Requirement, Observer> observerMap: observerGroup.getGenerateObserverGroup()) {
            List<String> reqIDList = new ArrayList<>();
            StringBuilder obsTrans = new StringBuilder();
            List<String> obsNames = getObserverName(observerMap);

            int id = 0;
            for(Map.Entry<Requirement,Observer> entry: observerMap.entrySet()){
                Observer obs = entry.getValue();
                for(int i = 0; i < obs.getTransNum(); ++i){
                    obsTrans.append(" and ").append(obsNames.get(id)).append(".").append("e").append(i);
                }
                /*
                * 事件条件覆盖（考虑通过在观察者模型中构建辅助函数来函数实现）
                * */
                //obsTrans.append(obs.getFuncName());
                reqIDList.add(entry.getKey().getReqID());
                ++id;
            }

            StringBuilder formulas = new StringBuilder();
            formulas.append("E<> program1.end").append(obsTrans).append("\n");

            queryFileGen(paths, reqIDList, formulas, index++);
        }
        return paths;
    }

    /**
     * 生成测试所用的查询文件，如果所生成的查询公式所针对的需求组已经生成过文件，那么直接将该公式加入对应的文件
     * @param paths 文件路径列表
     * @param reqIDList 需求ID列表
     * @param formulas 公式表达式
     * @param index 文件下标
     */
    public void queryFileGen(List<String> paths, List<String> reqIDList, StringBuilder formulas, int index){
        if(queryFilePath.size() < index){
            query2ReqID.put("testGen" + formulaIndex + ".q", reqIDList);
            paths.add(FileGenHelper.generateFile("F:\\project\\PLCAnalysis\\src\\main\\resources\\queryFile", formulas.toString(), "testGen" + (formulaIndex++) + ".q"));
        }else{
            FileGenHelper.addTextToFile(formulas.toString(), queryFilePath.get(index - 1));
        }
    }
}
