package com.lee.plcanalysis.core.sps.base;

import java.util.List;

public class Scope {
    public enum Type{
        GLOBAL,
        AFTER,
        AFTER_UNTIL,
        WHEN
    };

    private final Type type;

    private final List<Expression> expressions;

    public Scope(Type type, List<Expression> expressions) {
        this.type = type;
        this.expressions = expressions;
    }

    public Type getType() {
        return type;
    }

    public List<Expression> getExpressions() {
        for(Expression expr: expressions){
            String str = expr.getExpr().replace("or", " or ").replace("and", " and ").replace("not", " not");
            expr.setExpr(str);
        }
        return expressions;
    }

    @Override
    public String toString() {
        return "Scope{" +
                "type=" + type +
                ", expressions=" + expressions +
                '}';
    }
}
