package com.lee.plcanalysis.core.verify;

import com.lee.plcanalysis.core.plc.PLCFacade;
import com.lee.plcanalysis.core.plc.model.ProgramModel;
import com.lee.plcanalysis.core.sps.SPSFacade;
import com.lee.plcanalysis.core.sps.base.Requirement;
import com.lee.plcanalysis.core.sps.model.Observer;
import com.lee.plcanalysis.core.sps.model.ObserverGroup;
import com.lee.plcanalysis.core.verify.translators.UppaalModel;
import com.lee.plcanalysis.core.verify.translators.VerifyFormula;
import com.lee.plcanalysis.core.verify.work.Trace;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VerifyTest {
    @Test
    public void test() throws IOException {
        /*System.out.println("***************************************************************************************************************");
        System.out.println("***********************************************             SPSPart              ******************************");
        System.out.println("***************************************************************************************************************");

        String str =
                "[1]Globally, it is always the case that Q0.0 holds.\n"
                        + "[2]Globally, it is always the case that Q0.1 holds.\n"
                        + "[3]Globally, it is never the case that Q0.0 holds."
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
        System.out.println("***************************************************************************************************************");
        System.out.println("***********************************************             ProgramPart              ******************************");
        System.out.println("***************************************************************************************************************");


        String filePath = "/test.txt";
        PLCFacade plcFacade = new PLCFacade();
        try {
            plcFacade.parseFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProgramModel programModel = plcFacade.getProgramModel(null, 0);
        System.out.println(programModel.getDefinitionPart());
        System.out.println(programModel.getProgramMetaModel());
        for(String s: programModel.getInstanceExpression()){
            System.out.println(s);
        }*/

        System.out.println("***************************************************************************************************************");
        System.out.println("***********************************************             VerifyPart              ******************************");
        System.out.println("***************************************************************************************************************");

        String reqs =
                "[1]After I0.0 until (I0.1 or I0.2), it is always the case that Q0.0 holds.\n"
                        + "[2]After I0.1, it is always the case that Q0.1 holds within 30(0,0) timeUnits.\n"
                        + "[3]When Q0.0, it is never the case that Q0.1 holds."
                ;
        List<List<String>> priorityArray = new ArrayList<>();
        priorityArray.add(new ArrayList<String>(){{add("3"); add("2");}});

        String programFilePath = "/test.txt";
        VerifyFacade verifyFacade = new VerifyFacade();
        verifyFacade.submitProgram(programFilePath, true);
        verifyFacade.submitRequirement(reqs, priorityArray, false);
        /*verifyFacade.submitVerifyTaskProcess();

        System.out.println(verifyFacade.getResults());
        for (int i = 0; i < verifyFacade.getTraces().size(); i++) {
            Map<String,Trace> map = verifyFacade.getTraces().get(i);
            for (String key: map.keySet()) {
                System.out.println(key +": ");
                for(Map.Entry<String,List<Boolean>> entry :map.get(key).getIOTrace().entrySet()){
                    System.out.println(entry.getKey() + " ==> " + entry.getValue());
                }
            }
        }*/

        List<Integer> select = new ArrayList<>();
        select.add(0);
        select.add(1);
        verifyFacade.submitTestGenTaskProcess(select);
        for(Map.Entry<List<String>,List<Trace>> entry: verifyFacade.getTestCases().entrySet()){
            System.out.println("=======================================");
            System.out.println(entry.getKey() + ": ");
            for(Trace trace: entry.getValue()){
                System.out.println(trace.getIOTrace());
                System.out.println("-------------------");
            }
            System.out.println("========================================");
        }
    }
}
