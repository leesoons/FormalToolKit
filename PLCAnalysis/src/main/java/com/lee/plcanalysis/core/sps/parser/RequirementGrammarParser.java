// Generated from F:/CodeProject/PLCAnalysis/src/main/java/com/lee/plcanalysis/antlr\RequirementGrammar.g4 by ANTLR 4.9

    package com.lee.plcanalysis.core.sps.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RequirementGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, FLOAT=34, POSITIVE_INT=35, ID=36, WS=37, LINE_COMMENT=38;
	public static final int
		RULE_list = 0, RULE_requirement = 1, RULE_reqID = 2, RULE_scope = 3, RULE_delay = 4, 
		RULE_property = 5, RULE_delayWithEnd1 = 6, RULE_delayWithEnd2 = 7, RULE_delayWithoutEnd = 8, 
		RULE_delayOnBothSides = 9, RULE_delayOnRightSide = 10, RULE_universality = 11, 
		RULE_absence = 12, RULE_existence = 13, RULE_expr = 14, RULE_time = 15, 
		RULE_error = 16, RULE_number = 17, RULE_positiveInt = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"list", "requirement", "reqID", "scope", "delay", "property", "delayWithEnd1", 
			"delayWithEnd2", "delayWithoutEnd", "delayOnBothSides", "delayOnRightSide", 
			"universality", "absence", "existence", "expr", "time", "error", "number", 
			"positiveInt"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'.'", "'['", "']'", "'Globally'", "'After'", "'until'", 
			"'When'", "'between'", "'('", "')'", "'timeUnits'", "'and'", "'within'", 
			"'after'", "'the'", "'property'", "'still'", "'holds'", "'for'", "'end'", 
			"'of'", "'scope'", "'it'", "'is'", "'always'", "'case'", "'that'", "'never'", 
			"'exists'", "'immediately'", "'or'", "'not'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "FLOAT", 
			"POSITIVE_INT", "ID", "WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RequirementGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RequirementGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ListContext extends ParserRuleContext {
		public List<RequirementContext> requirement() {
			return getRuleContexts(RequirementContext.class);
		}
		public RequirementContext requirement(int i) {
			return getRuleContext(RequirementContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				requirement();
				}
				}
				setState(41); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << T__5) | (1L << T__7))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequirementContext extends ParserRuleContext {
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public ReqIDContext reqID() {
			return getRuleContext(ReqIDContext.class,0);
		}
		public DelayContext delay() {
			return getRuleContext(DelayContext.class,0);
		}
		public RequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequirementContext requirement() throws RecognitionException {
		RequirementContext _localctx = new RequirementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_requirement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(43);
				reqID();
				}
			}

			setState(46);
			scope();
			setState(47);
			match(T__0);
			setState(48);
			property();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) {
				{
				setState(49);
				delay();
				}
			}

			setState(52);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReqIDContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ReqIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reqID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterReqID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitReqID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitReqID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReqIDContext reqID() throws RecognitionException {
		ReqIDContext _localctx = new ReqIDContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_reqID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__2);
			setState(55);
			number();
			setState(56);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_scope);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(T__5);
				setState(60);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(T__5);
				setState(62);
				expr(0);
				setState(63);
				match(T__6);
				setState(64);
				expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				match(T__7);
				setState(67);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelayContext extends ParserRuleContext {
		public DelayWithEnd1Context delayWithEnd1() {
			return getRuleContext(DelayWithEnd1Context.class,0);
		}
		public DelayWithEnd2Context delayWithEnd2() {
			return getRuleContext(DelayWithEnd2Context.class,0);
		}
		public DelayWithoutEndContext delayWithoutEnd() {
			return getRuleContext(DelayWithoutEndContext.class,0);
		}
		public DelayOnBothSidesContext delayOnBothSides() {
			return getRuleContext(DelayOnBothSidesContext.class,0);
		}
		public DelayOnRightSideContext delayOnRightSide() {
			return getRuleContext(DelayOnRightSideContext.class,0);
		}
		public DelayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delay; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterDelay(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitDelay(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitDelay(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayContext delay() throws RecognitionException {
		DelayContext _localctx = new DelayContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_delay);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				delayWithEnd1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				delayWithEnd2();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				delayWithoutEnd();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				delayOnBothSides();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				delayOnRightSide();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyContext extends ParserRuleContext {
		public UniversalityContext universality() {
			return getRuleContext(UniversalityContext.class,0);
		}
		public AbsenceContext absence() {
			return getRuleContext(AbsenceContext.class,0);
		}
		public ExistenceContext existence() {
			return getRuleContext(ExistenceContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_property);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				universality();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				absence();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				existence();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelayWithEnd1Context extends ParserRuleContext {
		public List<TimeContext> time() {
			return getRuleContexts(TimeContext.class);
		}
		public TimeContext time(int i) {
			return getRuleContext(TimeContext.class,i);
		}
		public List<ErrorContext> error() {
			return getRuleContexts(ErrorContext.class);
		}
		public ErrorContext error(int i) {
			return getRuleContext(ErrorContext.class,i);
		}
		public DelayWithEnd1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayWithEnd1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterDelayWithEnd1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitDelayWithEnd1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitDelayWithEnd1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayWithEnd1Context delayWithEnd1() throws RecognitionException {
		DelayWithEnd1Context _localctx = new DelayWithEnd1Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_delayWithEnd1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__8);
			setState(83);
			time();
			setState(84);
			match(T__9);
			setState(85);
			error();
			setState(86);
			match(T__10);
			setState(87);
			match(T__11);
			setState(88);
			match(T__12);
			setState(89);
			time();
			setState(90);
			match(T__9);
			setState(91);
			error();
			setState(92);
			match(T__10);
			setState(93);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelayWithEnd2Context extends ParserRuleContext {
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public DelayWithEnd2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayWithEnd2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterDelayWithEnd2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitDelayWithEnd2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitDelayWithEnd2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayWithEnd2Context delayWithEnd2() throws RecognitionException {
		DelayWithEnd2Context _localctx = new DelayWithEnd2Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_delayWithEnd2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__13);
			setState(96);
			time();
			setState(97);
			match(T__9);
			setState(98);
			error();
			setState(99);
			match(T__10);
			setState(100);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelayWithoutEndContext extends ParserRuleContext {
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public DelayWithoutEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayWithoutEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterDelayWithoutEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitDelayWithoutEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitDelayWithoutEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayWithoutEndContext delayWithoutEnd() throws RecognitionException {
		DelayWithoutEndContext _localctx = new DelayWithoutEndContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_delayWithoutEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__14);
			setState(103);
			time();
			setState(104);
			match(T__9);
			setState(105);
			error();
			setState(106);
			match(T__10);
			setState(107);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelayOnBothSidesContext extends ParserRuleContext {
		public List<TimeContext> time() {
			return getRuleContexts(TimeContext.class);
		}
		public TimeContext time(int i) {
			return getRuleContext(TimeContext.class,i);
		}
		public List<ErrorContext> error() {
			return getRuleContexts(ErrorContext.class);
		}
		public ErrorContext error(int i) {
			return getRuleContext(ErrorContext.class,i);
		}
		public DelayOnBothSidesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayOnBothSides; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterDelayOnBothSides(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitDelayOnBothSides(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitDelayOnBothSides(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayOnBothSidesContext delayOnBothSides() throws RecognitionException {
		DelayOnBothSidesContext _localctx = new DelayOnBothSidesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_delayOnBothSides);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__14);
			setState(110);
			time();
			setState(111);
			match(T__9);
			setState(112);
			error();
			setState(113);
			match(T__10);
			setState(114);
			match(T__11);
			setState(115);
			match(T__12);
			setState(116);
			match(T__15);
			setState(117);
			match(T__16);
			setState(118);
			match(T__17);
			setState(119);
			match(T__18);
			setState(120);
			match(T__19);
			setState(121);
			time();
			setState(122);
			match(T__9);
			setState(123);
			error();
			setState(124);
			match(T__10);
			setState(125);
			match(T__11);
			setState(126);
			match(T__14);
			setState(127);
			match(T__15);
			setState(128);
			match(T__20);
			setState(129);
			match(T__21);
			setState(130);
			match(T__15);
			setState(131);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelayOnRightSideContext extends ParserRuleContext {
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public DelayOnRightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayOnRightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterDelayOnRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitDelayOnRightSide(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitDelayOnRightSide(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayOnRightSideContext delayOnRightSide() throws RecognitionException {
		DelayOnRightSideContext _localctx = new DelayOnRightSideContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_delayOnRightSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__12);
			setState(134);
			match(T__15);
			setState(135);
			match(T__16);
			setState(136);
			match(T__17);
			setState(137);
			match(T__18);
			setState(138);
			match(T__19);
			setState(139);
			time();
			setState(140);
			match(T__9);
			setState(141);
			error();
			setState(142);
			match(T__10);
			setState(143);
			match(T__11);
			setState(144);
			match(T__14);
			setState(145);
			match(T__15);
			setState(146);
			match(T__20);
			setState(147);
			match(T__21);
			setState(148);
			match(T__15);
			setState(149);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UniversalityContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RequirementGrammarParser.ID, 0); }
		public UniversalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_universality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterUniversality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitUniversality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitUniversality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UniversalityContext universality() throws RecognitionException {
		UniversalityContext _localctx = new UniversalityContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_universality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__23);
			setState(152);
			match(T__24);
			setState(153);
			match(T__25);
			setState(154);
			match(T__15);
			setState(155);
			match(T__26);
			setState(156);
			match(T__27);
			setState(157);
			match(ID);
			setState(158);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsenceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RequirementGrammarParser.ID, 0); }
		public AbsenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterAbsence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitAbsence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitAbsence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbsenceContext absence() throws RecognitionException {
		AbsenceContext _localctx = new AbsenceContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_absence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(T__23);
			setState(161);
			match(T__24);
			setState(162);
			match(T__28);
			setState(163);
			match(T__15);
			setState(164);
			match(T__26);
			setState(165);
			match(T__27);
			setState(166);
			match(ID);
			setState(167);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistenceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RequirementGrammarParser.ID, 0); }
		public ExistenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterExistence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitExistence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitExistence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExistenceContext existence() throws RecognitionException {
		ExistenceContext _localctx = new ExistenceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_existence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(ID);
			setState(170);
			match(T__29);
			setState(171);
			match(T__30);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(RequirementGrammarParser.ID, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(174);
				match(T__9);
				setState(175);
				expr(0);
				setState(176);
				match(T__10);
				}
				break;
			case T__32:
				{
				setState(178);
				match(T__32);
				setState(179);
				match(ID);
				}
				break;
			case ID:
				{
				setState(180);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(183);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(184);
					_la = _input.LA(1);
					if ( !(_la==T__12 || _la==T__31) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(185);
					expr(4);
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TimeContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ErrorContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			number();
			setState(194);
			match(T__0);
			setState(195);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode POSITIVE_INT() { return getToken(RequirementGrammarParser.POSITIVE_INT, 0); }
		public TerminalNode FLOAT() { return getToken(RequirementGrammarParser.FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_la = _input.LA(1);
			if ( !(_la==FLOAT || _la==POSITIVE_INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PositiveIntContext extends ParserRuleContext {
		public TerminalNode POSITIVE_INT() { return getToken(RequirementGrammarParser.POSITIVE_INT, 0); }
		public PositiveIntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positiveInt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).enterPositiveInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementGrammarListener ) ((RequirementGrammarListener)listener).exitPositiveInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementGrammarVisitor ) return ((RequirementGrammarVisitor<? extends T>)visitor).visitPositiveInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PositiveIntContext positiveInt() throws RecognitionException {
		PositiveIntContext _localctx = new PositiveIntContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_positiveInt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(POSITIVE_INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00cc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\6\2*\n\2\r\2\16\2+\3\3\5\3/\n\3\3\3\3\3\3\3\3"+
		"\3\5\3\65\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3\7\5\7S\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00b8\n\20\3\20\3\20\3\20\7\20\u00bd\n\20\f"+
		"\20\16\20\u00c0\13\20\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\24\2\3\36\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\4\4\2\17"+
		"\17\"\"\3\2$%\2\u00c7\2)\3\2\2\2\4.\3\2\2\2\68\3\2\2\2\bF\3\2\2\2\nM\3"+
		"\2\2\2\fR\3\2\2\2\16T\3\2\2\2\20a\3\2\2\2\22h\3\2\2\2\24o\3\2\2\2\26\u0087"+
		"\3\2\2\2\30\u0099\3\2\2\2\32\u00a2\3\2\2\2\34\u00ab\3\2\2\2\36\u00b7\3"+
		"\2\2\2 \u00c1\3\2\2\2\"\u00c3\3\2\2\2$\u00c7\3\2\2\2&\u00c9\3\2\2\2(*"+
		"\5\4\3\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\3\3\2\2\2-/\5\6\4\2"+
		".-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\5\b\5\2\61\62\7\3\2\2\62\64\5\f"+
		"\7\2\63\65\5\n\6\2\64\63\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\7\4"+
		"\2\2\67\5\3\2\2\289\7\5\2\29:\5$\23\2:;\7\6\2\2;\7\3\2\2\2<G\7\7\2\2="+
		">\7\b\2\2>G\5\36\20\2?@\7\b\2\2@A\5\36\20\2AB\7\t\2\2BC\5\36\20\2CG\3"+
		"\2\2\2DE\7\n\2\2EG\5\36\20\2F<\3\2\2\2F=\3\2\2\2F?\3\2\2\2FD\3\2\2\2G"+
		"\t\3\2\2\2HN\5\16\b\2IN\5\20\t\2JN\5\22\n\2KN\5\24\13\2LN\5\26\f\2MH\3"+
		"\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2N\13\3\2\2\2OS\5\30\r\2"+
		"PS\5\32\16\2QS\5\34\17\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2S\r\3\2\2\2TU\7"+
		"\13\2\2UV\5 \21\2VW\7\f\2\2WX\5\"\22\2XY\7\r\2\2YZ\7\16\2\2Z[\7\17\2\2"+
		"[\\\5 \21\2\\]\7\f\2\2]^\5\"\22\2^_\7\r\2\2_`\7\16\2\2`\17\3\2\2\2ab\7"+
		"\20\2\2bc\5 \21\2cd\7\f\2\2de\5\"\22\2ef\7\r\2\2fg\7\16\2\2g\21\3\2\2"+
		"\2hi\7\21\2\2ij\5 \21\2jk\7\f\2\2kl\5\"\22\2lm\7\r\2\2mn\7\16\2\2n\23"+
		"\3\2\2\2op\7\21\2\2pq\5 \21\2qr\7\f\2\2rs\5\"\22\2st\7\r\2\2tu\7\16\2"+
		"\2uv\7\17\2\2vw\7\22\2\2wx\7\23\2\2xy\7\24\2\2yz\7\25\2\2z{\7\26\2\2{"+
		"|\5 \21\2|}\7\f\2\2}~\5\"\22\2~\177\7\r\2\2\177\u0080\7\16\2\2\u0080\u0081"+
		"\7\21\2\2\u0081\u0082\7\22\2\2\u0082\u0083\7\27\2\2\u0083\u0084\7\30\2"+
		"\2\u0084\u0085\7\22\2\2\u0085\u0086\7\31\2\2\u0086\25\3\2\2\2\u0087\u0088"+
		"\7\17\2\2\u0088\u0089\7\22\2\2\u0089\u008a\7\23\2\2\u008a\u008b\7\24\2"+
		"\2\u008b\u008c\7\25\2\2\u008c\u008d\7\26\2\2\u008d\u008e\5 \21\2\u008e"+
		"\u008f\7\f\2\2\u008f\u0090\5\"\22\2\u0090\u0091\7\r\2\2\u0091\u0092\7"+
		"\16\2\2\u0092\u0093\7\21\2\2\u0093\u0094\7\22\2\2\u0094\u0095\7\27\2\2"+
		"\u0095\u0096\7\30\2\2\u0096\u0097\7\22\2\2\u0097\u0098\7\31\2\2\u0098"+
		"\27\3\2\2\2\u0099\u009a\7\32\2\2\u009a\u009b\7\33\2\2\u009b\u009c\7\34"+
		"\2\2\u009c\u009d\7\22\2\2\u009d\u009e\7\35\2\2\u009e\u009f\7\36\2\2\u009f"+
		"\u00a0\7&\2\2\u00a0\u00a1\7\25\2\2\u00a1\31\3\2\2\2\u00a2\u00a3\7\32\2"+
		"\2\u00a3\u00a4\7\33\2\2\u00a4\u00a5\7\37\2\2\u00a5\u00a6\7\22\2\2\u00a6"+
		"\u00a7\7\35\2\2\u00a7\u00a8\7\36\2\2\u00a8\u00a9\7&\2\2\u00a9\u00aa\7"+
		"\25\2\2\u00aa\33\3\2\2\2\u00ab\u00ac\7&\2\2\u00ac\u00ad\7 \2\2\u00ad\u00ae"+
		"\7!\2\2\u00ae\35\3\2\2\2\u00af\u00b0\b\20\1\2\u00b0\u00b1\7\f\2\2\u00b1"+
		"\u00b2\5\36\20\2\u00b2\u00b3\7\r\2\2\u00b3\u00b8\3\2\2\2\u00b4\u00b5\7"+
		"#\2\2\u00b5\u00b8\7&\2\2\u00b6\u00b8\7&\2\2\u00b7\u00af\3\2\2\2\u00b7"+
		"\u00b4\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00be\3\2\2\2\u00b9\u00ba\f\5"+
		"\2\2\u00ba\u00bb\t\2\2\2\u00bb\u00bd\5\36\20\6\u00bc\u00b9\3\2\2\2\u00bd"+
		"\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\37\3\2\2"+
		"\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\5$\23\2\u00c2!\3\2\2\2\u00c3\u00c4"+
		"\5$\23\2\u00c4\u00c5\7\3\2\2\u00c5\u00c6\5$\23\2\u00c6#\3\2\2\2\u00c7"+
		"\u00c8\t\3\2\2\u00c8%\3\2\2\2\u00c9\u00ca\7%\2\2\u00ca\'\3\2\2\2\n+.\64"+
		"FMR\u00b7\u00be";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}