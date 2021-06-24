package com.lee.plcanalysis.core.plc.base;

import java.util.Arrays;
import java.util.List;

public class Ladder {
    private final OpCode opCode;
    private final List<String> operatorList;


    public Ladder(OpCode opCode, List<String> operatorList) {
        this.opCode = opCode;
        this.operatorList = operatorList;
    }

    public OpCode getOpCode() {
        return opCode;
    }

    public List<String> getOperatorList() {
        return operatorList;
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "opCode=" + opCode +
                ", operatorList=" + operatorList +
                '}';
    }
}
