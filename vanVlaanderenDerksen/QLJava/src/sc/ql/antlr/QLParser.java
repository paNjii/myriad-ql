// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, BOOLEAN_LITERAL=26, MONEY_LITERAL=27, INTEGER_LITERAL=28, ID_LITERAL=29, 
		STRING_LITERAL=30, WS=31, COMMENT=32, LINE_COMMENT=33;
	public static final int
		RULE_form = 0, RULE_formElement = 1, RULE_expression = 2, RULE_type = 3;
	public static final String[] ruleNames = {
		"form", "formElement", "expression", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'endform'", "'='", "'if'", "'('", "')'", "'else'", "'endif'", 
		"'!'", "'*'", "'/'", "'+'", "'-'", "'<'", "'<='", "'>'", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", "'BOOLEAN'", "'INTEGER'", "'MONEY'", "'STRING'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BOOLEAN_LITERAL", "MONEY_LITERAL", "INTEGER_LITERAL", "ID_LITERAL", 
		"STRING_LITERAL", "WS", "COMMENT", "LINE_COMMENT"
	};
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
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
		public List<FormElementContext> formElement() {
			return getRuleContexts(FormElementContext.class);
		}
		public FormElementContext formElement(int i) {
			return getRuleContext(FormElementContext.class,i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(T__0);
			setState(10); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(9);
				formElement();
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==STRING_LITERAL );
			setState(14);
			match(T__1);
			setState(15);
			match(EOF);
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

	public static class FormElementContext extends ParserRuleContext {
		public FormElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formElement; }
	 
		public FormElementContext() { }
		public void copyFrom(FormElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionContext extends FormElementContext {
		public Token label;
		public Token id;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public TerminalNode ID_LITERAL() { return getToken(QLParser.ID_LITERAL, 0); }
		public QuestionContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CalculatedQuestionContext extends FormElementContext {
		public Token label;
		public Token id;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public TerminalNode ID_LITERAL() { return getToken(QLParser.ID_LITERAL, 0); }
		public CalculatedQuestionContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCalculatedQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfThenElseStatementContext extends FormElementContext {
		public FormElementContext formElement;
		public List<FormElementContext> thenBody = new ArrayList<FormElementContext>();
		public List<FormElementContext> elseBody = new ArrayList<FormElementContext>();
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<FormElementContext> formElement() {
			return getRuleContexts(FormElementContext.class);
		}
		public FormElementContext formElement(int i) {
			return getRuleContext(FormElementContext.class,i);
		}
		public IfThenElseStatementContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfThenElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfThenStatementContext extends FormElementContext {
		public FormElementContext formElement;
		public List<FormElementContext> thenBody = new ArrayList<FormElementContext>();
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<FormElementContext> formElement() {
			return getRuleContexts(FormElementContext.class);
		}
		public FormElementContext formElement(int i) {
			return getRuleContext(FormElementContext.class,i);
		}
		public IfThenStatementContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfThenStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormElementContext formElement() throws RecognitionException {
		FormElementContext _localctx = new FormElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formElement);
		int _la;
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new CalculatedQuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(17);
				((CalculatedQuestionContext)_localctx).label = match(STRING_LITERAL);
				setState(18);
				((CalculatedQuestionContext)_localctx).id = match(ID_LITERAL);
				setState(19);
				type();
				setState(20);
				match(T__2);
				setState(21);
				expression(0);
				}
				break;
			case 2:
				_localctx = new QuestionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				((QuestionContext)_localctx).label = match(STRING_LITERAL);
				setState(24);
				((QuestionContext)_localctx).id = match(ID_LITERAL);
				setState(25);
				type();
				}
				break;
			case 3:
				_localctx = new IfThenElseStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				match(T__3);
				setState(27);
				match(T__4);
				setState(28);
				expression(0);
				setState(29);
				match(T__5);
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(30);
					((IfThenElseStatementContext)_localctx).formElement = formElement();
					((IfThenElseStatementContext)_localctx).thenBody.add(((IfThenElseStatementContext)_localctx).formElement);
					}
					}
					setState(33); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__3 || _la==STRING_LITERAL );
				setState(35);
				match(T__6);
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(36);
					((IfThenElseStatementContext)_localctx).formElement = formElement();
					((IfThenElseStatementContext)_localctx).elseBody.add(((IfThenElseStatementContext)_localctx).formElement);
					}
					}
					setState(39); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__3 || _la==STRING_LITERAL );
				setState(41);
				match(T__7);
				}
				break;
			case 4:
				_localctx = new IfThenStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				match(T__3);
				setState(44);
				match(T__4);
				setState(45);
				expression(0);
				setState(46);
				match(T__5);
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(47);
					((IfThenStatementContext)_localctx).formElement = formElement();
					((IfThenStatementContext)_localctx).thenBody.add(((IfThenStatementContext)_localctx).formElement);
					}
					}
					setState(50); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__3 || _la==STRING_LITERAL );
				setState(52);
				match(T__7);
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThenEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GreaterThenEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitGreaterThenEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdLiteralContext extends ExpressionContext {
		public Token literal;
		public TerminalNode ID_LITERAL() { return getToken(QLParser.ID_LITERAL, 0); }
		public IdLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIdLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyLiteralContext extends ExpressionContext {
		public Token literal;
		public TerminalNode MONEY_LITERAL() { return getToken(QLParser.MONEY_LITERAL, 0); }
		public MoneyLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMoneyLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThenEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LessThenEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLessThenEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThenContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GreaterThenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitGreaterThen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExpressionContext {
		public Token literal;
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualsContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerLiteralContext extends ExpressionContext {
		public Token literal;
		public TerminalNode INTEGER_LITERAL() { return getToken(QLParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivideContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DivideContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDivide(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThenContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LessThenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLessThen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplyContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultiplyContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMultiply(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends ExpressionContext {
		public Token literal;
		public TerminalNode BOOLEAN_LITERAL() { return getToken(QLParser.BOOLEAN_LITERAL, 0); }
		public BooleanLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubstractContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SubstractContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitSubstract(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualsNotContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualsNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitEqualsNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			switch (_input.LA(1)) {
			case T__4:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(57);
				match(T__4);
				setState(58);
				expression(0);
				setState(59);
				match(T__5);
				}
				break;
			case T__8:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61);
				match(T__8);
				setState(62);
				expression(18);
				}
				break;
			case BOOLEAN_LITERAL:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				((BooleanLiteralContext)_localctx).literal = match(BOOLEAN_LITERAL);
				}
				break;
			case INTEGER_LITERAL:
				{
				_localctx = new IntegerLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(64);
				((IntegerLiteralContext)_localctx).literal = match(INTEGER_LITERAL);
				}
				break;
			case MONEY_LITERAL:
				{
				_localctx = new MoneyLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				((MoneyLiteralContext)_localctx).literal = match(MONEY_LITERAL);
				}
				break;
			case ID_LITERAL:
				{
				_localctx = new IdLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(66);
				((IdLiteralContext)_localctx).literal = match(ID_LITERAL);
				}
				break;
			case STRING_LITERAL:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(67);
				((StringLiteralContext)_localctx).literal = match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(106);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplyContext(new ExpressionContext(_parentctx, _parentState));
						((MultiplyContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(70);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(71);
						match(T__9);
						setState(72);
						((MultiplyContext)_localctx).right = expression(18);
						}
						break;
					case 2:
						{
						_localctx = new DivideContext(new ExpressionContext(_parentctx, _parentState));
						((DivideContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(73);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(74);
						match(T__10);
						setState(75);
						((DivideContext)_localctx).right = expression(17);
						}
						break;
					case 3:
						{
						_localctx = new AddContext(new ExpressionContext(_parentctx, _parentState));
						((AddContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(76);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(77);
						match(T__11);
						setState(78);
						((AddContext)_localctx).right = expression(16);
						}
						break;
					case 4:
						{
						_localctx = new SubstractContext(new ExpressionContext(_parentctx, _parentState));
						((SubstractContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(79);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(80);
						match(T__12);
						setState(81);
						((SubstractContext)_localctx).right = expression(15);
						}
						break;
					case 5:
						{
						_localctx = new LessThenContext(new ExpressionContext(_parentctx, _parentState));
						((LessThenContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(82);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(83);
						match(T__13);
						setState(84);
						((LessThenContext)_localctx).right = expression(14);
						}
						break;
					case 6:
						{
						_localctx = new LessThenEqualContext(new ExpressionContext(_parentctx, _parentState));
						((LessThenEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(85);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(86);
						match(T__14);
						setState(87);
						((LessThenEqualContext)_localctx).right = expression(13);
						}
						break;
					case 7:
						{
						_localctx = new GreaterThenContext(new ExpressionContext(_parentctx, _parentState));
						((GreaterThenContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(88);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(89);
						match(T__15);
						setState(90);
						((GreaterThenContext)_localctx).right = expression(12);
						}
						break;
					case 8:
						{
						_localctx = new GreaterThenEqualContext(new ExpressionContext(_parentctx, _parentState));
						((GreaterThenEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(91);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(92);
						match(T__16);
						setState(93);
						((GreaterThenEqualContext)_localctx).right = expression(11);
						}
						break;
					case 9:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState));
						((EqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(95);
						match(T__17);
						setState(96);
						((EqualsContext)_localctx).right = expression(10);
						}
						break;
					case 10:
						{
						_localctx = new EqualsNotContext(new ExpressionContext(_parentctx, _parentState));
						((EqualsNotContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(97);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(98);
						match(T__18);
						setState(99);
						((EqualsNotContext)_localctx).right = expression(9);
						}
						break;
					case 11:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						((AndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(100);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(101);
						match(T__19);
						setState(102);
						((AndContext)_localctx).right = expression(8);
						}
						break;
					case 12:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						((OrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(103);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(104);
						match(T__20);
						setState(105);
						((OrContext)_localctx).right = expression(7);
						}
						break;
					}
					} 
				}
				setState(110);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanTypeContext extends TypeContext {
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerTypeContext extends TypeContext {
		public IntegerTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegerType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyTypeContext extends TypeContext {
		public MoneyTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMoneyType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(115);
			switch (_input.LA(1)) {
			case T__21:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				match(T__21);
				}
				break;
			case T__22:
				_localctx = new IntegerTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(T__22);
				}
				break;
			case T__23:
				_localctx = new MoneyTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				match(T__23);
				}
				break;
			case T__24:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				match(T__24);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		case 2:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 9);
		case 9:
			return precpred(_ctx, 8);
		case 10:
			return precpred(_ctx, 7);
		case 11:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#x\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3\"\n\3\r\3\16\3#\3\3\3"+
		"\3\6\3(\n\3\r\3\16\3)\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3\63\n\3\r\3\16\3"+
		"\64\3\3\3\3\5\39\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5"+
		"\4G\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\7\4m\n\4\f\4\16\4p\13\4\3\5\3\5\3\5\3\5\5\5v\n\5\3\5\2\3"+
		"\6\6\2\4\6\b\2\2\u008f\2\n\3\2\2\2\48\3\2\2\2\6F\3\2\2\2\bu\3\2\2\2\n"+
		"\f\7\3\2\2\13\r\5\4\3\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3"+
		"\2\2\2\17\20\3\2\2\2\20\21\7\4\2\2\21\22\7\2\2\3\22\3\3\2\2\2\23\24\7"+
		" \2\2\24\25\7\37\2\2\25\26\5\b\5\2\26\27\7\5\2\2\27\30\5\6\4\2\309\3\2"+
		"\2\2\31\32\7 \2\2\32\33\7\37\2\2\339\5\b\5\2\34\35\7\6\2\2\35\36\7\7\2"+
		"\2\36\37\5\6\4\2\37!\7\b\2\2 \"\5\4\3\2! \3\2\2\2\"#\3\2\2\2#!\3\2\2\2"+
		"#$\3\2\2\2$%\3\2\2\2%\'\7\t\2\2&(\5\4\3\2\'&\3\2\2\2()\3\2\2\2)\'\3\2"+
		"\2\2)*\3\2\2\2*+\3\2\2\2+,\7\n\2\2,9\3\2\2\2-.\7\6\2\2./\7\7\2\2/\60\5"+
		"\6\4\2\60\62\7\b\2\2\61\63\5\4\3\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3"+
		"\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\7\n\2\2\679\3\2\2\28\23\3\2\2"+
		"\28\31\3\2\2\28\34\3\2\2\28-\3\2\2\29\5\3\2\2\2:;\b\4\1\2;<\7\7\2\2<="+
		"\5\6\4\2=>\7\b\2\2>G\3\2\2\2?@\7\13\2\2@G\5\6\4\24AG\7\34\2\2BG\7\36\2"+
		"\2CG\7\35\2\2DG\7\37\2\2EG\7 \2\2F:\3\2\2\2F?\3\2\2\2FA\3\2\2\2FB\3\2"+
		"\2\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2Gn\3\2\2\2HI\f\23\2\2IJ\7\f\2\2Jm\5"+
		"\6\4\24KL\f\22\2\2LM\7\r\2\2Mm\5\6\4\23NO\f\21\2\2OP\7\16\2\2Pm\5\6\4"+
		"\22QR\f\20\2\2RS\7\17\2\2Sm\5\6\4\21TU\f\17\2\2UV\7\20\2\2Vm\5\6\4\20"+
		"WX\f\16\2\2XY\7\21\2\2Ym\5\6\4\17Z[\f\r\2\2[\\\7\22\2\2\\m\5\6\4\16]^"+
		"\f\f\2\2^_\7\23\2\2_m\5\6\4\r`a\f\13\2\2ab\7\24\2\2bm\5\6\4\fcd\f\n\2"+
		"\2de\7\25\2\2em\5\6\4\13fg\f\t\2\2gh\7\26\2\2hm\5\6\4\nij\f\b\2\2jk\7"+
		"\27\2\2km\5\6\4\tlH\3\2\2\2lK\3\2\2\2lN\3\2\2\2lQ\3\2\2\2lT\3\2\2\2lW"+
		"\3\2\2\2lZ\3\2\2\2l]\3\2\2\2l`\3\2\2\2lc\3\2\2\2lf\3\2\2\2li\3\2\2\2m"+
		"p\3\2\2\2nl\3\2\2\2no\3\2\2\2o\7\3\2\2\2pn\3\2\2\2qv\7\30\2\2rv\7\31\2"+
		"\2sv\7\32\2\2tv\7\33\2\2uq\3\2\2\2ur\3\2\2\2us\3\2\2\2ut\3\2\2\2v\t\3"+
		"\2\2\2\13\16#)\648Flnu";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}