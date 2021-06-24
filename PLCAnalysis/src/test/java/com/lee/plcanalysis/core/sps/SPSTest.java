package com.lee.plcanalysis.core.sps;

import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.Observer;
import com.lee.plcanalysis.core.sps.model.ObserverGroup;
import com.lee.plcanalysis.core.sps.parser.RequirementGrammarLexer;
import com.lee.plcanalysis.core.sps.parser.RequirementGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.ibatis.javassist.ClassPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SPSTest {
    @Test
    public void test() {
        String str =
                "[1]After I0.0, it is always the case that Q0.0 holds within 30(0,0) timeUnits.\n"
                        + "[2]When O0.0, it is never the case that O0.1 holds between 10(1,1) timeUnits and 20(0,2) timeUnits.\n"
                        + "[3]After I0.0 until (I0.1 or I0.2), it is always the case that Q0.0 holds."
                ;
        ANTLRInputStream in = new ANTLRInputStream(str);
        RequirementGrammarLexer lexer = new RequirementGrammarLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RequirementGrammarParser parser = new RequirementGrammarParser(tokens);
        parser.list();
    }

    @Test
    public void test2() {
        String str =
                "[1]After I0.0, it is always the case that Q0.0 holds within 30(0,0) timeUnits.\n"
                        + "[2]When O0.0, it is never the case that O0.1 holds between 10(1,1) timeUnits and 20(0,2) timeUnits.\n"
                        + "[3]After I0.0 until (I0.1 or I0.2), it is always the case that Q0.0 holds."
                ;
        SPSFacade spsFacade = new SPSFacade();
        spsFacade.parseString(str);
        List<Requirement> reqs = spsFacade.getRequirements();

        for(Requirement r: reqs){
            System.out.println(r);
        }

        List<List<String>> priorityArray = new ArrayList<>();
        priorityArray.add(new ArrayList(){{add("1"); add("3");}});

        ObserverGroup observerGroup = spsFacade.getObserverGroup(priorityArray);
        List<Map<Requirement, Observer>> observerGroupList = observerGroup.getGenerateObserverGroup();
        for(int i = 0; i < observerGroupList.size(); ++i){
            Map<Requirement,Observer> obsMap = observerGroupList.get(i);
            System.out.println("-------------------");
            for (Map.Entry<Requirement,Observer> entry: obsMap.entrySet()){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
        System.out.println("*******************************");
    }
}
