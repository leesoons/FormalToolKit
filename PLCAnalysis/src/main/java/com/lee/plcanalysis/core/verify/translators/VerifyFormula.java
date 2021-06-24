package com.lee.plcanalysis.core.verify.translators;

import com.lee.plcanalysis.core.sps.base.Property;
import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.Observer;
import com.lee.plcanalysis.core.sps.model.ObserverGroup;
import com.lee.plcanalysis.core.verify.until.FileGenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerifyFormula implements Formula{
    private int formulaIndex = 0;
    private final ObserverGroup observerGroup;
    private final Map<String,List<String>> query2ReqID;

    public Map<String, List<String>> getQuery2ReqID() {
        return query2ReqID;
    }

    public VerifyFormula(ObserverGroup observerGroup) {
        this.observerGroup = observerGroup;
        this.query2ReqID = new HashMap<>();
    }

    /**
     * 生成查询公式
     * @return 查询文件的地址
     */
    @Override
    public List<String> generateFormula() {
        List<String> queryFilePath = new ArrayList<>();

        for(Map<Requirement,Observer> observerMap: observerGroup.getGenerateObserverGroup()){
            StringBuilder formulas = new StringBuilder();
            StringBuilder last = new StringBuilder();

            List<String> obsNames = getObserverName(observerMap);
            System.out.println(obsNames);
            List<String> property = getProperty(observerMap);
            for(int i = 0; i < obsNames.size(); ++i){
                StringBuilder formula = new StringBuilder();
                formula.append("A[] (program1.end and ");
                formula.append(last).append(obsNames.get(i)).append(".in) imply ").append(property.get(i));
                formulas.append(formula).append("\n");
                last.append("not ").append(obsNames.get(i)).append(".in").append(" and ");
            }

            List<String> reqIDList = new ArrayList<>();
            observerMap.keySet().forEach(requirement -> reqIDList.add(requirement.getReqID()));
            query2ReqID.put("model" + formulaIndex + ".q", reqIDList);

            queryFilePath.add(FileGenHelper.generateFile("F:\\project\\PLCAnalysis\\src\\main\\resources\\queryFile", formulas.toString(), "model" + (formulaIndex++) + ".q"));
        }
        return queryFilePath;
    }

    /*public List<String> getProperty(Map<Requirement,Observer> observerMap){
        List<String> property = new ArrayList<>();

        for(Requirement requirement: observerMap.keySet()){
            if(requirement.getProperty().getType() == Property.Type.UNIVERSALITY){
                property.add(requirement.getProperty().getExpression().getExpr());
            }else if(requirement.getProperty().getType() == Property.Type.ABSENCE){
                property.add("not " + requirement.getProperty().getExpression().getExpr());
            }
        }

        return property;
    }

    public List<String> getObserverName(Map<Requirement,Observer> observerMap){
        List<String> obsName = new ArrayList<>();

        int index = 0;
        for(Observer observer: observerMap.values()){
            obsName.add(observer.getName() + index++);
        }
        return obsName;
    }*/
}
