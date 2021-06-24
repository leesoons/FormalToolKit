package com.lee.plcanalysis.core.sps.base;

import java.util.HashSet;
import java.util.Set;

public class Expression {
    private String expr;

    public Expression() {
    }

    public Expression(String expr) {
        this.expr = expr;
    }

    public String getExpr() {
        return expr.replace(".", "_");
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public Set<String> getRelevantVars(){
        Set<String> vars = new HashSet<>();
        String[] strs = getExpr()
                .replace("(", "")
                .replace(")", "")
                .replace("and", "")
                .replace("not", "")
                .replace("or", "")
                .split(" ");
        for(String str: strs){
            str = str.trim();
            if(!str.isEmpty()){
                vars.add(str);
            }
        }
        return vars;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "expr='" + expr + '\'' +
                '}';
    }
}
