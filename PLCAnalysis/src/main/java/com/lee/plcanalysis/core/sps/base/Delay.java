package com.lee.plcanalysis.core.sps.base;

import java.util.List;

public class Delay {
    public enum Type{
        WITHOUTEND, // delayL != 0, delayR == 0
        WITHEND_TYPE1, // delayL != 0, delayR != 0
        WITHEND_TYPE2, // delayL = 0, delayR != 0
        ONRIGHTSIDE, // delayL = 0, delayRE != 0
        ONBOTHSIDES, // delayL != 0, delayRE != 0
    }

    public final Type type;

    public final List<int[]> expressions;

    public Delay(Type type, List<int[]> expressions) {
        this.type = type;
        this.expressions = expressions;
    }

    public Type getType() {
        return type;
    }

    public List<int[]> getExpressions() {
        return expressions;
    }

    @Override
    public String toString() {
        return "Delay{" +
                "type=" + type +
                ", expressions=" + getDelay(expressions) +
                '}';
    }

    public String getDelay(List<int[]> expressions){
        String res = "[";
        for(int[] expr: expressions){
            res += "(" + expr[0] + ", " + expr[1] + ", " + expr[2] + "),";
        }
        return res.substring(0,res.length() - 1) + "]";
    }
}
