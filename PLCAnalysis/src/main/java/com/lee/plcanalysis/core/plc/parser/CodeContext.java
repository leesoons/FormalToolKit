package com.lee.plcanalysis.core.plc.parser;

import com.lee.plcanalysis.core.plc.base.CodeExpression;

import java.util.*;

/**
 * IL代码的类高级语言的表达式形式，分为程序主体表达式和定时器相关表达式
 */
public class CodeContext {
    private double cycleTime;
    private final Set<String> variables;
    private final List<CodeExpression> iLMainExpressionList;
    private final List<CodeExpression> iLTimeExpressionList;

    public CodeContext() {
        iLMainExpressionList = new ArrayList<>();
        iLTimeExpressionList = new ArrayList<>();
        variables = new HashSet<>();
    }

    public double getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(double cycleTime) {
        this.cycleTime = cycleTime;
    }

    public List<CodeExpression> getILMainExpression() {
        return iLMainExpressionList;
    }

    public List<CodeExpression> getILTimeExpression() {
        return iLTimeExpressionList;
    }

    public Set<String> getVariablesTab() {
        return variables;
    }

    public void addVar(String var){
        variables.add(var);
    }

    public void addExpr(CodeExpression expr, boolean isMain){
        if (isMain){
            iLMainExpressionList.add(expr);
        }else {
            iLTimeExpressionList.add(expr);
        }
    }

}
