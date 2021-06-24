package com.lee.plcanalysis.core.verify.translators;

import com.lee.plcanalysis.core.sps.base.Property;
import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Formula {
    List<String> generateFormula();

    default List<String> getProperty(Map<Requirement, Observer> observerMap){
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

    default List<String> getObserverName(Map<Requirement,Observer> observerMap){
        List<String> obsName = new ArrayList<>();

        int index = 0;
        for(Observer observer: observerMap.values()){
            obsName.add(observer.getName() + index++);
        }
        return obsName;
    }
}
