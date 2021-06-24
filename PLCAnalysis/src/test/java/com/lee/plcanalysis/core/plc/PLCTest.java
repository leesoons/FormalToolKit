package com.lee.plcanalysis.core.plc;

import com.lee.plcanalysis.core.plc.base.CodeExpression;
import com.lee.plcanalysis.core.plc.base.Ladder;
import com.lee.plcanalysis.core.plc.model.ProgramModel;
import com.lee.plcanalysis.core.plc.parser.CodeContext;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

public class PLCTest {
    @Test
    public void test(){
        String code = "# This is the IL code for the PLC program.\n" +
                "\n" +
                "# [1]After I0.1(复合按钮按下), it is always the case that Q0.1(制动运行) holds within 30 timeUnits.\n" +
                "LD\tI0.1\n" +
                "O\tM5.0\n" +
                "AN\tT37\n" +
                "=\tM5.0\n" +
                "LD\tM5.0\n" +
                "TON\tT37,30\n" +
                "LD\tM5.0\n" +
                "=\tM7.1\n" +
                "\n" +
                "# [4]After I0.0(电动机启动) until (I0.2(过载保护启动) or I0.1(复合按钮按下)), it is always the case that Q0.0(电动机运转) holds.\n" +
                "LD\tI0.0\n" +
                "O\tQ0.0\n" +
                "LDN\tI0.2\n" +
                "AN\tI0.1\n" +
                "ALD\t\n" +
                "=\tQ0.0\n" +
                "\n" +
                "# [3]When Q0.0(电动机运转), it is never the case that Q0.1(制动运行) holds.\n" +
                "LD\tQ0.0\n" +
                "=\tM6.0\n" +
                "LDN\tM6.0\n" +
                "=\tM7.0\n" +
                "\n" +
                "# Priority combination code: (3<1)\n" +
                "LD\tM7.0\n" +
                "O\tM7.1\n" +
                "=\tQ0.1";
        String filePath = "/test.txt";
        PLCFacade plcFacade = new PLCFacade();
        try {
            plcFacade.parseFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*List<Ladder> list = plcFacade.getParser().getLadders();
        System.out.println("------------------------------------------------------");
        System.out.println("                       All Ladder                     ");
        System.out.println("------------------------------------------------------");
        for(Ladder ladder: list){
            System.out.println("|  " + ladder);
        }
        System.out.println("------------------------------------------------------");
        System.out.println("                       Main Ladder                     ");
        System.out.println("------------------------------------------------------");
        CodeContext context = plcFacade.getCodeContext();
        for(List<Ladder> ladderList: plcFacade.getBuilder().getMainLadderList()){
            for(Ladder ladder: ladderList){
                System.out.println("|  " + ladder);
            }
            System.out.println("------------------------------------------------------");
        }
        System.out.println("------------------------------------------------------");
        System.out.println("                      Timer Ladder                    ");
        System.out.println("------------------------------------------------------");
        for(List<Ladder> ladderList: plcFacade.getBuilder().getTimerLadderList()){
            for(Ladder ladder: ladderList){
                System.out.println("|  " + ladder);
            }
            System.out.println("------------------------------------------------------");
        }
        System.out.println("------------------------------------------------------");
        System.out.println("                     Main Expression                  ");
        System.out.println("------------------------------------------------------");
        for(CodeExpression expr: context.getILMainExpression()){
            System.out.println("|  " + expr);
        }
        System.out.println("------------------------------------------------------");
        System.out.println("                     Timer Expression                 ");
        System.out.println("------------------------------------------------------");
        for(CodeExpression expr: context.getILTimeExpression()){
            System.out.println("|  " + expr);
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("|  CycleTime: " + context.getCycleTime());
        System.out.println("-------------------------------------------------------");

        for(String str: context.getVariablesTab()){
            System.out.println(str);
        }*/
        ProgramModel programModel = plcFacade.getProgramModel(null, 0);
        System.out.println(programModel.getDefinitionPart());
        System.out.println(programModel.getProgramMetaModel());
        for(String s: programModel.getInstanceExpression()){
            System.out.println(s);
        }
    }
}
