package com.lee.plcanalysis.core.sps.base;

import java.util.*;

public class Requirement {
    private String reqID;
    private String text;
    private final Scope scope;
    private final Property property;
    private final Delay delay;

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getText() {
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    public Scope getScope() {
        return scope;
    }

    public Property getProperty() {
        return property;
    }

    public Delay getDelay() {
        return delay;
    }

    public Requirement(Scope scope, Property property) {
        this(scope, property, null);
    }

    public Requirement(Scope scope, Property property, Delay delay) {
        this.scope = scope;
        this.property = property;
        this.delay = delay;
    }

    public String key(){
        return scope.getType() + "." + (property.getType() == Property.Type.EXISTENCE ? "EXISTENCE" : "UNIVERSALITY") + "." + (delay == null ? "null" : delay.getType());
    }

    /**
     * @return 需求包含的相关变量
     */
    public Set<String> getInfluenceVars(){
        Set<String> vars = new HashSet<>();
        List<Expression> expr1 = scope.getExpressions();
        expr1.add(property.getExpression());
        for(Expression expr: expr1){
            vars.addAll(expr.getRelevantVars());
        }
        return vars;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "reqID='" + reqID + '\'' +
                ", text='" + text + '\'' +
                ", scope=" + scope +
                ", property=" + property +
                ", delay=" + delay +
                '}';
    }
}
