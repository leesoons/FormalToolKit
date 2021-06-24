package com.lee.plcanalysis.core.sps.parser;

import com.lee.plcanalysis.core.sps.base.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.List;

public class RequirementBuilder extends RequirementGrammarBaseListener{

    private final Context context = new Context();

    private String reqID;
    private Scope scope;
    private Property property;
    private Delay delay;

    public ParseTreeProperty<Object> values = new ParseTreeProperty<>();

    public Context getContext() {
        return context;
    }

    @Override
    public void enterRequirement(RequirementGrammarParser.RequirementContext ctx) {
        reqID = null;
        scope = null;
        property = null;
        delay = null;
    }

    @Override
    public void exitRequirement(RequirementGrammarParser.RequirementContext ctx) {
        Requirement req = new Requirement(scope, property, delay);
        String text = ctx.start.getInputStream().getText(new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex()));
        req.setText(text);
        req.setReqID(reqID);
        context.addReq(req);
    }

    @Override
    public void exitReqID(RequirementGrammarParser.ReqIDContext ctx) {
        reqID = ctx.number().getText();
    }

    @Override
    public void exitScope(RequirementGrammarParser.ScopeContext ctx) {
        List<Expression> expressions = getExpressionList(ctx.expr());
        Scope.Type type = null;
        switch (ctx.getChild(0).getText()){
            case "Globally":
                type = Scope.Type.GLOBAL;
                break;
            case "When":
                type = Scope.Type.WHEN;
                break;
            case "After":
                if(expressions.size() == 2) {
                    type = Scope.Type.AFTER_UNTIL;
                } else {
                    type = Scope.Type.AFTER;
                }
                break;
        }
        scope = new Scope(type, expressions);
    }

    @Override
    public void exitDelayWithEnd1(RequirementGrammarParser.DelayWithEnd1Context ctx) {
        delay = new Delay(Delay.Type.WITHEND_TYPE1, setTimes(ctx.time(), ctx.error()));
    }

    @Override
    public void exitDelayWithEnd2(RequirementGrammarParser.DelayWithEnd2Context ctx) {
        delay = new Delay(Delay.Type.WITHEND_TYPE2, setTime(ctx.time(), ctx.error()));
    }

    @Override
    public void exitDelayWithoutEnd(RequirementGrammarParser.DelayWithoutEndContext ctx) {
        delay = new Delay(Delay.Type.WITHOUTEND, setTime(ctx.time(), ctx.error()));
    }

    @Override
    public void exitDelayOnBothSides(RequirementGrammarParser.DelayOnBothSidesContext ctx) {
        delay = new Delay(Delay.Type.ONBOTHSIDES, setTimes(ctx.time(), ctx.error()));
    }

    @Override
    public void exitDelayOnRightSide(RequirementGrammarParser.DelayOnRightSideContext ctx) {
        delay = new Delay(Delay.Type.ONRIGHTSIDE, setTime(ctx.time(), ctx.error()));
    }

    @Override
    public void exitUniversality(RequirementGrammarParser.UniversalityContext ctx) {
        property = new Property(Property.Type.UNIVERSALITY, new Expression(ctx.ID().getText()));
    }

    @Override
    public void exitAbsence(RequirementGrammarParser.AbsenceContext ctx) {
        property = new Property(Property.Type.ABSENCE, new Expression(ctx.ID().getText()));
    }

    @Override
    public void exitExistence(RequirementGrammarParser.ExistenceContext ctx) {
        property = new Property(Property.Type.EXISTENCE, new Expression(ctx.ID().getText()));
    }

    @Override
    public void exitExpr(RequirementGrammarParser.ExprContext ctx) {
        setValues(ctx, new Expression(ctx.getText()));
    }

    @Override
    public void exitTime(RequirementGrammarParser.TimeContext ctx) {
        setValues(ctx, Integer.parseInt(ctx.getText()));
    }

    @Override
    public void exitError(RequirementGrammarParser.ErrorContext ctx) {
        setValues(ctx, ctx.getText());
    }


    /****
    Util
     *****/

    private void setValues(ParseTree node, Object value) {
        values.put(node, value);
    }

    private Expression getExpression(RequirementGrammarParser.ExprContext node){
        return (Expression) values.get(node);
    }

    private List<Expression> getExpressionList(List<RequirementGrammarParser.ExprContext> exprContexts) {
        List<Expression> exprList = new ArrayList<>();

        for (RequirementGrammarParser.ExprContext ctx: exprContexts) {
            exprList.add(getExpression(ctx));
        }

        return exprList;
    }

    private int getTime(RequirementGrammarParser.TimeContext node){
        return (int) values.get(node);
    }

    private List<Integer> getTimeList(List<RequirementGrammarParser.TimeContext> timeContexts){
        List<Integer> timeList = new ArrayList<>();

        for(RequirementGrammarParser.TimeContext ctx: timeContexts){
            timeList.add(getTime(ctx));
        }

        return timeList;
    }

    private int[] getError(RequirementGrammarParser.ErrorContext node){
        int[] error = new int[2];
        String[] str = ((String)values.get(node)).split(",");
        error[0] = Integer.parseInt(str[0]);
        error[1] = Integer.parseInt(str[1]);
        return  error;
    }

    private List<int[]> getErrorList(List<RequirementGrammarParser.ErrorContext> errorContexts){
        List<int[]> errorList = new ArrayList<>();

        for(RequirementGrammarParser.ErrorContext ctx: errorContexts){
            errorList.add(getError(ctx));
        }

        return errorList;
    }

    /**
     * @param tctx 获取时间节点上下文
     * @param ectx 获取误差节点上下文
     * @return 返回所构造的时间列表[()]，形式为一个三元组序列（只包含一个元素），其中每个三元组为一个时间对象，元组中第一个元素为时间，后两个元素分别为左右误差
     */
    private List<int[]> setTime(RequirementGrammarParser.TimeContext tctx, RequirementGrammarParser.ErrorContext ectx){
        List<int[]> expr = new ArrayList<>();
        int time = getTime(tctx);
        int[] error = getError(ectx);
        int[] t1 = new int[3];
        t1[0] = time; t1[1] = error[0]; t1[2] = error[1];
        expr.add(t1);
        return expr;
    }

    /**
     * 获取时间节点上下文
     *      * @param ectx 获取误差节点上下文
     *      * @return 返回所构造的时间列表[()]，形式为一个三元组序列（包含多（两）个元素），其中每个三元组为一个时间对象，元组中第一个元素为时间，后两个元素分别为左右误差
     */
    private List<int[]> setTimes(List<RequirementGrammarParser.TimeContext> tctx, List<RequirementGrammarParser.ErrorContext> ectx){
        List<int[]> expr = new ArrayList<>();
        List<Integer> times = getTimeList(tctx);
        List<int[]> errors = getErrorList(ectx);
        int[] t1 = new int[3];
        int[] t2 = new int[3];
        t1[0] = times.get(0); t1[1] = errors.get(0)[0]; t1[2] = errors.get(0)[1];
        t2[0] = times.get(1); t2[1] = errors.get(1)[0]; t2[2] = errors.get(1)[1];
        expr.add(t1);
        expr.add(t2);
        return expr;
    }
}
