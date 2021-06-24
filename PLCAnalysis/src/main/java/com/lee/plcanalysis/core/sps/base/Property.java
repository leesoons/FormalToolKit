package com.lee.plcanalysis.core.sps.base;

public class Property {

    public enum Type{
        UNIVERSALITY,
        ABSENCE,
        EXISTENCE
    };

    private final Type type;

    private Expression expression;

    public Property(Type type, Expression expression) {
        this.type = type;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(String expr){
        expression = new Expression(expr);
    }

    @Override
    public String toString() {
        return "Property{" +
                "type=" + type +
                ", expression=" + expression +
                '}';
    }
}
