package com.lee.plcanalysis.core.plc.base;

import java.util.Set;

public class CodeExpression {
    private String expression;
    private String leftPart;
    private Set<String> rightPart;

    public CodeExpression(String expression, String leftPart, Set<String> rightPart) {
        this.expression = expression;
        this.leftPart = leftPart;
        this.rightPart = rightPart;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getLeftPart() {
        return leftPart;
    }

    public void setLeftPart(String leftPart) {
        this.leftPart = leftPart;
    }

    public Set<String> getRightPart() {
        return rightPart;
    }

    public void setRightPart(Set<String> rightPart) {
        this.rightPart = rightPart;
    }

    @Override
    public String toString() {
        return "CodeExpression{" +
                "expression='" + expression + '\'' +
                ", leftPart='" + leftPart + '\'' +
                ", rightPart=" + rightPart +
                '}';
    }
}
