package com.lee.plcanalysis.core.sps.model;

import com.lee.plcanalysis.core.sps.base.Requirement;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * SPS2Obs翻译器
 */
@NoArgsConstructor
public class SPS2Obs {

    private ObserverGroup observerGroup;

    /**
     * @param requirements 需求规约
     * @param priorityArray 优先级队列
     * @return 生成观察者组返回
     */
    public ObserverGroup translate(List<Requirement> requirements, List<List<String>> priorityArray){
        observerGroup = new ObserverGroup();
        requirements.forEach(requirement -> observerGroup.addRequirement(requirement));

        if(observerGroup.hasCircularDependency()){
            System.out.println("Circular Dependency detected!");
            return observerGroup;
        }

        if(priorityArray == null && observerGroup.hasConflict()){
            System.out.println("Please set the priority!");
            return observerGroup;
        }

        if(priorityArray != null){
            observerGroup.handleConflict(priorityArray);
        }

        return observerGroup;
    }
}
