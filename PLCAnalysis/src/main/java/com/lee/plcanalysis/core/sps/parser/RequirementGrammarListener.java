// Generated from F:/CodeProject/PLCAnalysis/src/main/java/com/lee/plcanalysis/antlr\RequirementGrammar.g4 by ANTLR 4.9

    package com.lee.plcanalysis.core.sps.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RequirementGrammarParser}.
 */
public interface RequirementGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(RequirementGrammarParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(RequirementGrammarParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void enterRequirement(RequirementGrammarParser.RequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void exitRequirement(RequirementGrammarParser.RequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#reqID}.
	 * @param ctx the parse tree
	 */
	void enterReqID(RequirementGrammarParser.ReqIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#reqID}.
	 * @param ctx the parse tree
	 */
	void exitReqID(RequirementGrammarParser.ReqIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(RequirementGrammarParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(RequirementGrammarParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#delay}.
	 * @param ctx the parse tree
	 */
	void enterDelay(RequirementGrammarParser.DelayContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#delay}.
	 * @param ctx the parse tree
	 */
	void exitDelay(RequirementGrammarParser.DelayContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(RequirementGrammarParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(RequirementGrammarParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#delayWithEnd1}.
	 * @param ctx the parse tree
	 */
	void enterDelayWithEnd1(RequirementGrammarParser.DelayWithEnd1Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#delayWithEnd1}.
	 * @param ctx the parse tree
	 */
	void exitDelayWithEnd1(RequirementGrammarParser.DelayWithEnd1Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#delayWithEnd2}.
	 * @param ctx the parse tree
	 */
	void enterDelayWithEnd2(RequirementGrammarParser.DelayWithEnd2Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#delayWithEnd2}.
	 * @param ctx the parse tree
	 */
	void exitDelayWithEnd2(RequirementGrammarParser.DelayWithEnd2Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#delayWithoutEnd}.
	 * @param ctx the parse tree
	 */
	void enterDelayWithoutEnd(RequirementGrammarParser.DelayWithoutEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#delayWithoutEnd}.
	 * @param ctx the parse tree
	 */
	void exitDelayWithoutEnd(RequirementGrammarParser.DelayWithoutEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#delayOnBothSides}.
	 * @param ctx the parse tree
	 */
	void enterDelayOnBothSides(RequirementGrammarParser.DelayOnBothSidesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#delayOnBothSides}.
	 * @param ctx the parse tree
	 */
	void exitDelayOnBothSides(RequirementGrammarParser.DelayOnBothSidesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#delayOnRightSide}.
	 * @param ctx the parse tree
	 */
	void enterDelayOnRightSide(RequirementGrammarParser.DelayOnRightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#delayOnRightSide}.
	 * @param ctx the parse tree
	 */
	void exitDelayOnRightSide(RequirementGrammarParser.DelayOnRightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#universality}.
	 * @param ctx the parse tree
	 */
	void enterUniversality(RequirementGrammarParser.UniversalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#universality}.
	 * @param ctx the parse tree
	 */
	void exitUniversality(RequirementGrammarParser.UniversalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#absence}.
	 * @param ctx the parse tree
	 */
	void enterAbsence(RequirementGrammarParser.AbsenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#absence}.
	 * @param ctx the parse tree
	 */
	void exitAbsence(RequirementGrammarParser.AbsenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#existence}.
	 * @param ctx the parse tree
	 */
	void enterExistence(RequirementGrammarParser.ExistenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#existence}.
	 * @param ctx the parse tree
	 */
	void exitExistence(RequirementGrammarParser.ExistenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(RequirementGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(RequirementGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#time}.
	 * @param ctx the parse tree
	 */
	void enterTime(RequirementGrammarParser.TimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#time}.
	 * @param ctx the parse tree
	 */
	void exitTime(RequirementGrammarParser.TimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(RequirementGrammarParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(RequirementGrammarParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(RequirementGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(RequirementGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementGrammarParser#positiveInt}.
	 * @param ctx the parse tree
	 */
	void enterPositiveInt(RequirementGrammarParser.PositiveIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementGrammarParser#positiveInt}.
	 * @param ctx the parse tree
	 */
	void exitPositiveInt(RequirementGrammarParser.PositiveIntContext ctx);
}