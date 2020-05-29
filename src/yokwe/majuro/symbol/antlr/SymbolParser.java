/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
// Generated from data/type/Symbol.g4 by ANTLR 4.8
package yokwe.majuro.symbol.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SymbolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ARRAY=16, BEGIN=17, 
		BOOLEAN=18, CARDINAL=19, END=20, ENDCASE=21, FROM=22, INTEGER=23, LONG=24, 
		OF=25, OVERLAID=26, PACKED=27, POINTER=28, RECORD=29, SELECT=30, SYMBOL=31, 
		TYPE=32, UNSPECIFIED=33, WORD=34, TRUE=35, FALSE=36, ID=37, NUMBER_8=38, 
		NUMBER_10=39, NUMBER_16=40, COMMENT_PARTIAL=41, COMMENT_LINE=42, SPACE=43;
	public static final int
		RULE_number = 0, RULE_positive_number = 1, RULE_constant = 2, RULE_rangeType = 3, 
		RULE_rangeTypeRange = 4, RULE_qualifiedName = 5, RULE_numberedName = 6, 
		RULE_symbol = 7, RULE_header = 8, RULE_body = 9, RULE_declList = 10, RULE_decl = 11, 
		RULE_declType = 12, RULE_typeType = 13, RULE_arrayType = 14, RULE_arrayTypeType = 15, 
		RULE_arrayTypeRange = 16, RULE_arrayTypeElement = 17, RULE_enumType = 18, 
		RULE_enumElementList = 19, RULE_eumElement = 20, RULE_subrangeType = 21, 
		RULE_recordType = 22, RULE_recordFieldList = 23, RULE_recordField = 24, 
		RULE_fieldName = 25, RULE_bitfieldName = 26, RULE_fieldType = 27, RULE_select = 28, 
		RULE_selectCaseList = 29, RULE_selectCase = 30, RULE_selectCaseSelector = 31, 
		RULE_referenceType = 32, RULE_simpleType = 33, RULE_declConst = 34, RULE_constType = 35, 
		RULE_constValue = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"number", "positive_number", "constant", "rangeType", "rangeTypeRange", 
			"qualifiedName", "numberedName", "symbol", "header", "body", "declList", 
			"decl", "declType", "typeType", "arrayType", "arrayTypeType", "arrayTypeRange", 
			"arrayTypeElement", "enumType", "enumElementList", "eumElement", "subrangeType", 
			"recordType", "recordFieldList", "recordField", "fieldName", "bitfieldName", 
			"fieldType", "select", "selectCaseList", "selectCase", "selectCaseSelector", 
			"referenceType", "simpleType", "declConst", "constType", "constValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'['", "'..'", "']'", "')'", "'.'", "'('", "'='", "':'", 
			"';'", "'{'", "'}'", "','", "'*'", "'=>'", "'ARRAY'", "'BEGIN'", "'BOOLEAN'", 
			"'CARDINAL'", "'END'", "'ENDCASE'", "'FROM'", "'INTEGER'", "'LONG'", 
			"'OF'", "'OVERLAID'", "'PACKED'", "'POINTER'", "'RECORD'", "'SELECT'", 
			"'SYMBOL'", "'TYPE'", "'UNSPECIFIED'", "'WORD'", "'TRUE'", "'FALSE'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "ARRAY", "BEGIN", "BOOLEAN", "CARDINAL", "END", 
			"ENDCASE", "FROM", "INTEGER", "LONG", "OF", "OVERLAID", "PACKED", "POINTER", 
			"RECORD", "SELECT", "SYMBOL", "TYPE", "UNSPECIFIED", "WORD", "TRUE", 
			"FALSE", "ID", "NUMBER_8", "NUMBER_10", "NUMBER_16", "COMMENT_PARTIAL", 
			"COMMENT_LINE", "SPACE"
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
	public String getGrammarFileName() { return "Symbol.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SymbolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class NumberContext extends ParserRuleContext {
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	 
		public NumberContext() { }
		public void copyFrom(NumberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Number10Context extends NumberContext {
		public TerminalNode NUMBER_10() { return getToken(SymbolParser.NUMBER_10, 0); }
		public Number10Context(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitNumber10(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Number8Context extends NumberContext {
		public TerminalNode NUMBER_8() { return getToken(SymbolParser.NUMBER_8, 0); }
		public Number8Context(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitNumber8(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Number16Context extends NumberContext {
		public TerminalNode NUMBER_16() { return getToken(SymbolParser.NUMBER_16, 0); }
		public Number16Context(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitNumber16(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_number);
		int _la;
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER_8:
				_localctx = new Number8Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(NUMBER_8);
				}
				break;
			case T__0:
			case NUMBER_10:
				_localctx = new Number10Context(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(75);
					match(T__0);
					}
				}

				setState(78);
				match(NUMBER_10);
				}
				break;
			case NUMBER_16:
				_localctx = new Number16Context(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				match(NUMBER_16);
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

	public static class Positive_numberContext extends ParserRuleContext {
		public Positive_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positive_number; }
	 
		public Positive_numberContext() { }
		public void copyFrom(Positive_numberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PositiveNumber16Context extends Positive_numberContext {
		public TerminalNode NUMBER_16() { return getToken(SymbolParser.NUMBER_16, 0); }
		public PositiveNumber16Context(Positive_numberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitPositiveNumber16(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PositiveNumber10Context extends Positive_numberContext {
		public TerminalNode NUMBER_10() { return getToken(SymbolParser.NUMBER_10, 0); }
		public PositiveNumber10Context(Positive_numberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitPositiveNumber10(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PositiveNumber8Context extends Positive_numberContext {
		public TerminalNode NUMBER_8() { return getToken(SymbolParser.NUMBER_8, 0); }
		public PositiveNumber8Context(Positive_numberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitPositiveNumber8(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Positive_numberContext positive_number() throws RecognitionException {
		Positive_numberContext _localctx = new Positive_numberContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_positive_number);
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER_8:
				_localctx = new PositiveNumber8Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				match(NUMBER_8);
				}
				break;
			case NUMBER_10:
				_localctx = new PositiveNumber10Context(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(NUMBER_10);
				}
				break;
			case NUMBER_16:
				_localctx = new PositiveNumber16Context(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				match(NUMBER_16);
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

	public static class ConstantContext extends ParserRuleContext {
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	 
		public ConstantContext() { }
		public void copyFrom(ConstantContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstRefContext extends ConstantContext {
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public ConstRefContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitConstRef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstNumberContext extends ConstantContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ConstNumberContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitConstNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constant);
		try {
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case NUMBER_8:
			case NUMBER_10:
			case NUMBER_16:
				_localctx = new ConstNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				number();
				}
				break;
			case ID:
				_localctx = new ConstRefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				match(ID);
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

	public static class RangeTypeContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public RangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitRangeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeTypeContext rangeType() throws RecognitionException {
		RangeTypeContext _localctx = new RangeTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rangeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			((RangeTypeContext)_localctx).name = match(ID);
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

	public static class RangeTypeRangeContext extends ParserRuleContext {
		public Token name;
		public ConstantContext start;
		public ConstantContext stop;
		public Token closeChar;
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public RangeTypeRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeTypeRange; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitRangeTypeRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeTypeRangeContext rangeTypeRange() throws RecognitionException {
		RangeTypeRangeContext _localctx = new RangeTypeRangeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rangeTypeRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(93);
				((RangeTypeRangeContext)_localctx).name = match(ID);
				}
			}

			setState(96);
			match(T__1);
			setState(97);
			((RangeTypeRangeContext)_localctx).start = constant();
			setState(98);
			match(T__2);
			setState(99);
			((RangeTypeRangeContext)_localctx).stop = constant();
			setState(100);
			((RangeTypeRangeContext)_localctx).closeChar = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
				((RangeTypeRangeContext)_localctx).closeChar = (Token)_errHandler.recoverInline(this);
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

	public static class QualifiedNameContext extends ParserRuleContext {
		public Token ID;
		public List<Token> name = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(SymbolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SymbolParser.ID, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			((QualifiedNameContext)_localctx).ID = match(ID);
			((QualifiedNameContext)_localctx).name.add(((QualifiedNameContext)_localctx).ID);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(103);
				match(T__5);
				setState(104);
				((QualifiedNameContext)_localctx).ID = match(ID);
				((QualifiedNameContext)_localctx).name.add(((QualifiedNameContext)_localctx).ID);
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class NumberedNameContext extends ParserRuleContext {
		public Token name;
		public Positive_numberContext value;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public Positive_numberContext positive_number() {
			return getRuleContext(Positive_numberContext.class,0);
		}
		public NumberedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberedName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitNumberedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberedNameContext numberedName() throws RecognitionException {
		NumberedNameContext _localctx = new NumberedNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numberedName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			((NumberedNameContext)_localctx).name = match(ID);
			setState(111);
			match(T__6);
			setState(112);
			((NumberedNameContext)_localctx).value = positive_number();
			setState(113);
			match(T__4);
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

	public static class SymbolContext extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public SymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbol; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitSymbol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_symbol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			header();
			setState(116);
			match(T__7);
			setState(117);
			body();
			setState(118);
			match(T__5);
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

	public static class HeaderContext extends ParserRuleContext {
		public Token name;
		public TerminalNode SYMBOL() { return getToken(SymbolParser.SYMBOL, 0); }
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			((HeaderContext)_localctx).name = match(ID);
			setState(121);
			match(T__8);
			setState(122);
			match(SYMBOL);
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

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(SymbolParser.BEGIN, 0); }
		public DeclListContext declList() {
			return getRuleContext(DeclListContext.class,0);
		}
		public TerminalNode END() { return getToken(SymbolParser.END, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(BEGIN);
			setState(125);
			declList();
			setState(126);
			match(END);
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

	public static class DeclListContext extends ParserRuleContext {
		public DeclContext decl;
		public List<DeclContext> elements = new ArrayList<DeclContext>();
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DeclListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitDeclList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclListContext declList() throws RecognitionException {
		DeclListContext _localctx = new DeclListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				((DeclListContext)_localctx).decl = decl();
				((DeclListContext)_localctx).elements.add(((DeclListContext)_localctx).decl);
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	public static class DeclContext extends ParserRuleContext {
		public DeclTypeContext declType() {
			return getRuleContext(DeclTypeContext.class,0);
		}
		public DeclConstContext declConst() {
			return getRuleContext(DeclConstContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_decl);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				declType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				declConst();
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

	public static class DeclTypeContext extends ParserRuleContext {
		public DeclTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declType; }
	 
		public DeclTypeContext() { }
		public void copyFrom(DeclTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeDeclContext extends DeclTypeContext {
		public Token name;
		public TerminalNode TYPE() { return getToken(SymbolParser.TYPE, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeDeclContext(DeclTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclTypeContext declType() throws RecognitionException {
		DeclTypeContext _localctx = new DeclTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declType);
		try {
			_localctx = new TypeDeclContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			((TypeDeclContext)_localctx).name = match(ID);
			setState(138);
			match(T__8);
			setState(139);
			match(TYPE);
			setState(140);
			match(T__7);
			setState(141);
			typeType();
			setState(142);
			match(T__9);
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

	public static class TypeTypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public EnumTypeContext enumType() {
			return getRuleContext(EnumTypeContext.class,0);
		}
		public SubrangeTypeContext subrangeType() {
			return getRuleContext(SubrangeTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeType);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				arrayType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				enumType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				subrangeType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				recordType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				simpleType();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				referenceType();
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

	public static class ArrayTypeContext extends ParserRuleContext {
		public ArrayTypeTypeContext arrayTypeType() {
			return getRuleContext(ArrayTypeTypeContext.class,0);
		}
		public ArrayTypeRangeContext arrayTypeRange() {
			return getRuleContext(ArrayTypeRangeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayType);
		try {
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				arrayTypeType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				arrayTypeRange();
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

	public static class ArrayTypeTypeContext extends ParserRuleContext {
		public ArrayTypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTypeType; }
	 
		public ArrayTypeTypeContext() { }
		public void copyFrom(ArrayTypeTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeArrayTypeContext extends ArrayTypeTypeContext {
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public RangeTypeContext rangeType() {
			return getRuleContext(RangeTypeContext.class,0);
		}
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayTypeElementContext arrayTypeElement() {
			return getRuleContext(ArrayTypeElementContext.class,0);
		}
		public TypeArrayTypeContext(ArrayTypeTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeTypeContext arrayTypeType() throws RecognitionException {
		ArrayTypeTypeContext _localctx = new ArrayTypeTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arrayTypeType);
		try {
			_localctx = new TypeArrayTypeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(ARRAY);
			setState(157);
			rangeType();
			setState(158);
			match(OF);
			setState(159);
			arrayTypeElement();
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

	public static class ArrayTypeRangeContext extends ParserRuleContext {
		public ArrayTypeRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTypeRange; }
	 
		public ArrayTypeRangeContext() { }
		public void copyFrom(ArrayTypeRangeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeArrayRangeContext extends ArrayTypeRangeContext {
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public RangeTypeRangeContext rangeTypeRange() {
			return getRuleContext(RangeTypeRangeContext.class,0);
		}
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayTypeElementContext arrayTypeElement() {
			return getRuleContext(ArrayTypeElementContext.class,0);
		}
		public TypeArrayRangeContext(ArrayTypeRangeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeArrayRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeRangeContext arrayTypeRange() throws RecognitionException {
		ArrayTypeRangeContext _localctx = new ArrayTypeRangeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arrayTypeRange);
		try {
			_localctx = new TypeArrayRangeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(ARRAY);
			setState(162);
			rangeTypeRange();
			setState(163);
			match(OF);
			setState(164);
			arrayTypeElement();
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

	public static class ArrayTypeElementContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ArrayTypeElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTypeElement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitArrayTypeElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeElementContext arrayTypeElement() throws RecognitionException {
		ArrayTypeElementContext _localctx = new ArrayTypeElementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arrayTypeElement);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case CARDINAL:
			case INTEGER:
			case LONG:
			case POINTER:
			case UNSPECIFIED:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				simpleType();
				}
				break;
			case RECORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				recordType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				referenceType();
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

	public static class EnumTypeContext extends ParserRuleContext {
		public EnumTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumType; }
	 
		public EnumTypeContext() { }
		public void copyFrom(EnumTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeEnumContext extends EnumTypeContext {
		public EnumElementListContext enumElementList() {
			return getRuleContext(EnumElementListContext.class,0);
		}
		public TypeEnumContext(EnumTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeEnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumTypeContext enumType() throws RecognitionException {
		EnumTypeContext _localctx = new EnumTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_enumType);
		try {
			_localctx = new TypeEnumContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__10);
			setState(172);
			enumElementList();
			setState(173);
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

	public static class EnumElementListContext extends ParserRuleContext {
		public EumElementContext eumElement;
		public List<EumElementContext> elements = new ArrayList<EumElementContext>();
		public List<EumElementContext> eumElement() {
			return getRuleContexts(EumElementContext.class);
		}
		public EumElementContext eumElement(int i) {
			return getRuleContext(EumElementContext.class,i);
		}
		public EnumElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumElementList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitEnumElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumElementListContext enumElementList() throws RecognitionException {
		EnumElementListContext _localctx = new EnumElementListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_enumElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			((EnumElementListContext)_localctx).eumElement = eumElement();
			((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(176);
				match(T__12);
				setState(177);
				((EnumElementListContext)_localctx).eumElement = eumElement();
				((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class EumElementContext extends ParserRuleContext {
		public NumberedNameContext numberedName() {
			return getRuleContext(NumberedNameContext.class,0);
		}
		public EumElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eumElement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitEumElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EumElementContext eumElement() throws RecognitionException {
		EumElementContext _localctx = new EumElementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_eumElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			numberedName();
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

	public static class SubrangeTypeContext extends ParserRuleContext {
		public SubrangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subrangeType; }
	 
		public SubrangeTypeContext() { }
		public void copyFrom(SubrangeTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeSubrangeTypeContext extends SubrangeTypeContext {
		public RangeTypeContext rangeType() {
			return getRuleContext(RangeTypeContext.class,0);
		}
		public TypeSubrangeTypeContext(SubrangeTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeSubrangeType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeSubrangeTypeRangeContext extends SubrangeTypeContext {
		public RangeTypeRangeContext rangeTypeRange() {
			return getRuleContext(RangeTypeRangeContext.class,0);
		}
		public TypeSubrangeTypeRangeContext(SubrangeTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeSubrangeTypeRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubrangeTypeContext subrangeType() throws RecognitionException {
		SubrangeTypeContext _localctx = new SubrangeTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_subrangeType);
		try {
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new TypeSubrangeTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				rangeType();
				}
				break;
			case 2:
				_localctx = new TypeSubrangeTypeRangeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				rangeTypeRange();
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

	public static class RecordTypeContext extends ParserRuleContext {
		public RecordTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordType; }
	 
		public RecordTypeContext() { }
		public void copyFrom(RecordTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeRecordContext extends RecordTypeContext {
		public TerminalNode RECORD() { return getToken(SymbolParser.RECORD, 0); }
		public RecordFieldListContext recordFieldList() {
			return getRuleContext(RecordFieldListContext.class,0);
		}
		public TypeRecordContext(RecordTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeRecord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_recordType);
		try {
			_localctx = new TypeRecordContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(RECORD);
			setState(190);
			match(T__1);
			setState(191);
			recordFieldList();
			setState(192);
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

	public static class RecordFieldListContext extends ParserRuleContext {
		public RecordFieldContext recordField;
		public List<RecordFieldContext> elements = new ArrayList<RecordFieldContext>();
		public List<RecordFieldContext> recordField() {
			return getRuleContexts(RecordFieldContext.class);
		}
		public RecordFieldContext recordField(int i) {
			return getRuleContext(RecordFieldContext.class,i);
		}
		public RecordFieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordFieldList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitRecordFieldList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordFieldListContext recordFieldList() throws RecognitionException {
		RecordFieldListContext _localctx = new RecordFieldListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_recordFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			((RecordFieldListContext)_localctx).recordField = recordField();
			((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(195);
				match(T__12);
				setState(196);
				((RecordFieldListContext)_localctx).recordField = recordField();
				((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class RecordFieldContext extends ParserRuleContext {
		public RecordFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordField; }
	 
		public RecordFieldContext() { }
		public void copyFrom(RecordFieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeRecordFieldContext extends RecordFieldContext {
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public FieldTypeContext fieldType() {
			return getRuleContext(FieldTypeContext.class,0);
		}
		public TypeRecordFieldContext(RecordFieldContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeRecordField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordFieldContext recordField() throws RecognitionException {
		RecordFieldContext _localctx = new RecordFieldContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_recordField);
		try {
			_localctx = new TypeRecordFieldContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			fieldName();
			setState(203);
			match(T__8);
			setState(204);
			fieldType();
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

	public static class FieldNameContext extends ParserRuleContext {
		public NumberedNameContext numberedName() {
			return getRuleContext(NumberedNameContext.class,0);
		}
		public BitfieldNameContext bitfieldName() {
			return getRuleContext(BitfieldNameContext.class,0);
		}
		public FieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fieldName);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				numberedName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				bitfieldName();
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

	public static class BitfieldNameContext extends ParserRuleContext {
		public BitfieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitfieldName; }
	 
		public BitfieldNameContext() { }
		public void copyFrom(BitfieldNameContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeBitfieldNameContext extends BitfieldNameContext {
		public Token name;
		public Positive_numberContext offset;
		public Positive_numberContext bitStart;
		public Positive_numberContext bitStop;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public List<Positive_numberContext> positive_number() {
			return getRuleContexts(Positive_numberContext.class);
		}
		public Positive_numberContext positive_number(int i) {
			return getRuleContext(Positive_numberContext.class,i);
		}
		public TypeBitfieldNameContext(BitfieldNameContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeBitfieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitfieldNameContext bitfieldName() throws RecognitionException {
		BitfieldNameContext _localctx = new BitfieldNameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_bitfieldName);
		try {
			_localctx = new TypeBitfieldNameContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			((TypeBitfieldNameContext)_localctx).name = match(ID);
			setState(211);
			match(T__6);
			setState(212);
			((TypeBitfieldNameContext)_localctx).offset = positive_number();
			setState(213);
			match(T__8);
			setState(214);
			((TypeBitfieldNameContext)_localctx).bitStart = positive_number();
			setState(215);
			match(T__2);
			setState(216);
			((TypeBitfieldNameContext)_localctx).bitStop = positive_number();
			setState(217);
			match(T__4);
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

	public static class FieldTypeContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public FieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitFieldType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldTypeContext fieldType() throws RecognitionException {
		FieldTypeContext _localctx = new FieldTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_fieldType);
		try {
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__10:
			case ARRAY:
			case BOOLEAN:
			case CARDINAL:
			case INTEGER:
			case LONG:
			case POINTER:
			case RECORD:
			case UNSPECIFIED:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				typeType();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				select();
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

	public static class SelectContext extends ParserRuleContext {
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
	 
		public SelectContext() { }
		public void copyFrom(SelectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeSelectAnyContext extends SelectContext {
		public TerminalNode SELECT() { return getToken(SymbolParser.SELECT, 0); }
		public TerminalNode OVERLAID() { return getToken(SymbolParser.OVERLAID, 0); }
		public TerminalNode FROM() { return getToken(SymbolParser.FROM, 0); }
		public SelectCaseListContext selectCaseList() {
			return getRuleContext(SelectCaseListContext.class,0);
		}
		public TerminalNode ENDCASE() { return getToken(SymbolParser.ENDCASE, 0); }
		public TypeSelectAnyContext(SelectContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeSelectAny(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_select);
		try {
			_localctx = new TypeSelectAnyContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(SELECT);
			setState(224);
			match(OVERLAID);
			setState(225);
			match(T__13);
			setState(226);
			match(FROM);
			setState(227);
			selectCaseList();
			setState(228);
			match(ENDCASE);
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

	public static class SelectCaseListContext extends ParserRuleContext {
		public SelectCaseContext selectCase;
		public List<SelectCaseContext> elements = new ArrayList<SelectCaseContext>();
		public List<SelectCaseContext> selectCase() {
			return getRuleContexts(SelectCaseContext.class);
		}
		public SelectCaseContext selectCase(int i) {
			return getRuleContext(SelectCaseContext.class,i);
		}
		public SelectCaseListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCaseList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitSelectCaseList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectCaseListContext selectCaseList() throws RecognitionException {
		SelectCaseListContext _localctx = new SelectCaseListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_selectCaseList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			((SelectCaseListContext)_localctx).selectCase = selectCase();
			((SelectCaseListContext)_localctx).elements.add(((SelectCaseListContext)_localctx).selectCase);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(231);
				match(T__12);
				setState(232);
				((SelectCaseListContext)_localctx).selectCase = selectCase();
				((SelectCaseListContext)_localctx).elements.add(((SelectCaseListContext)_localctx).selectCase);
				setState(233);
				match(T__12);
				}
				}
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class SelectCaseContext extends ParserRuleContext {
		public SelectCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCase; }
	 
		public SelectCaseContext() { }
		public void copyFrom(SelectCaseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeSelectCaseListContext extends SelectCaseContext {
		public SelectCaseSelectorContext selectCaseSelector() {
			return getRuleContext(SelectCaseSelectorContext.class,0);
		}
		public RecordFieldListContext recordFieldList() {
			return getRuleContext(RecordFieldListContext.class,0);
		}
		public TypeSelectCaseListContext(SelectCaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeSelectCaseList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeSelectCaseEmptyContext extends SelectCaseContext {
		public SelectCaseSelectorContext selectCaseSelector() {
			return getRuleContext(SelectCaseSelectorContext.class,0);
		}
		public TypeSelectCaseEmptyContext(SelectCaseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeSelectCaseEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectCaseContext selectCase() throws RecognitionException {
		SelectCaseContext _localctx = new SelectCaseContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_selectCase);
		try {
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new TypeSelectCaseEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				selectCaseSelector();
				setState(241);
				match(T__14);
				setState(242);
				match(T__1);
				setState(243);
				match(T__3);
				}
				break;
			case 2:
				_localctx = new TypeSelectCaseListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				selectCaseSelector();
				setState(246);
				match(T__14);
				setState(247);
				match(T__1);
				setState(248);
				recordFieldList();
				setState(249);
				match(T__3);
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

	public static class SelectCaseSelectorContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public NumberedNameContext numberedName() {
			return getRuleContext(NumberedNameContext.class,0);
		}
		public SelectCaseSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCaseSelector; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitSelectCaseSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectCaseSelectorContext selectCaseSelector() throws RecognitionException {
		SelectCaseSelectorContext _localctx = new SelectCaseSelectorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_selectCaseSelector);
		try {
			setState(255);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				((SelectCaseSelectorContext)_localctx).name = match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				numberedName();
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

	public static class ReferenceTypeContext extends ParserRuleContext {
		public ReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceType; }
	 
		public ReferenceTypeContext() { }
		public void copyFrom(ReferenceTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeRefContext extends ReferenceTypeContext {
		public Token name;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeRefContext(ReferenceTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceTypeContext referenceType() throws RecognitionException {
		ReferenceTypeContext _localctx = new ReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_referenceType);
		try {
			_localctx = new TypeRefContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			((TypeRefContext)_localctx).name = match(ID);
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

	public static class SimpleTypeContext extends ParserRuleContext {
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
	 
		public SimpleTypeContext() { }
		public void copyFrom(SimpleTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeCardinalContext extends SimpleTypeContext {
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public TypeCardinalContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeCardinal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLongIntegerContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode INTEGER() { return getToken(SymbolParser.INTEGER, 0); }
		public TypeLongIntegerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeLongInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeBooleanContext extends SimpleTypeContext {
		public TerminalNode BOOLEAN() { return getToken(SymbolParser.BOOLEAN, 0); }
		public TypeBooleanContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeIntegerContext extends SimpleTypeContext {
		public TerminalNode INTEGER() { return getToken(SymbolParser.INTEGER, 0); }
		public TypeIntegerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypePointerContext extends SimpleTypeContext {
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TypePointerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypePointer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeUnspecifiedContext extends SimpleTypeContext {
		public TerminalNode UNSPECIFIED() { return getToken(SymbolParser.UNSPECIFIED, 0); }
		public TypeUnspecifiedContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeUnspecified(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLongPointerContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TypeLongPointerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeLongPointer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLongCardinalContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public TypeLongCardinalContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeLongCardinal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeLongUnspecifiedContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode UNSPECIFIED() { return getToken(SymbolParser.UNSPECIFIED, 0); }
		public TypeLongUnspecifiedContext(SimpleTypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitTypeLongUnspecified(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_simpleType);
		try {
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(BOOLEAN);
				}
				break;
			case 2:
				_localctx = new TypeCardinalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(CARDINAL);
				}
				break;
			case 3:
				_localctx = new TypeLongCardinalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(261);
				match(LONG);
				setState(262);
				match(CARDINAL);
				}
				break;
			case 4:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(263);
				match(INTEGER);
				}
				break;
			case 5:
				_localctx = new TypeLongIntegerContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(264);
				match(LONG);
				setState(265);
				match(INTEGER);
				}
				break;
			case 6:
				_localctx = new TypeUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(266);
				match(UNSPECIFIED);
				}
				break;
			case 7:
				_localctx = new TypeLongUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(267);
				match(LONG);
				setState(268);
				match(UNSPECIFIED);
				}
				break;
			case 8:
				_localctx = new TypePointerContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(269);
				match(POINTER);
				}
				break;
			case 9:
				_localctx = new TypeLongPointerContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(270);
				match(LONG);
				setState(271);
				match(POINTER);
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

	public static class DeclConstContext extends ParserRuleContext {
		public DeclConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declConst; }
	 
		public DeclConstContext() { }
		public void copyFrom(DeclConstContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstDeclContext extends DeclConstContext {
		public Token name;
		public ConstTypeContext constType() {
			return getRuleContext(ConstTypeContext.class,0);
		}
		public ConstValueContext constValue() {
			return getRuleContext(ConstValueContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public ConstDeclContext(DeclConstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitConstDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclConstContext declConst() throws RecognitionException {
		DeclConstContext _localctx = new DeclConstContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_declConst);
		try {
			_localctx = new ConstDeclContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			((ConstDeclContext)_localctx).name = match(ID);
			setState(275);
			match(T__8);
			setState(276);
			constType();
			setState(277);
			match(T__7);
			setState(278);
			constValue();
			setState(279);
			match(T__9);
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

	public static class ConstTypeContext extends ParserRuleContext {
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public ConstTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitConstType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstTypeContext constType() throws RecognitionException {
		ConstTypeContext _localctx = new ConstTypeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_constType);
		try {
			setState(285);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CARDINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				match(CARDINAL);
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				match(POINTER);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 3);
				{
				setState(283);
				match(LONG);
				setState(284);
				match(POINTER);
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

	public static class ConstValueContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ConstValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SymbolVisitor ) return ((SymbolVisitor<? extends T>)visitor).visitConstValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstValueContext constValue() throws RecognitionException {
		ConstValueContext _localctx = new ConstValueContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_constValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			qualifiedName();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0124\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\5\2O\n\2\3\2\3\2\5\2S\n\2"+
		"\3\3\3\3\3\3\5\3X\n\3\3\4\3\4\5\4\\\n\4\3\5\3\5\3\6\5\6a\n\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7l\n\7\f\7\16\7o\13\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\6\f\u0084"+
		"\n\f\r\f\16\f\u0085\3\r\3\r\5\r\u008a\n\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0099\n\17\3\20\3\20\5\20\u009d"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\5\23\u00ac\n\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\7\25\u00b5\n\25\f"+
		"\25\16\25\u00b8\13\25\3\26\3\26\3\27\3\27\5\27\u00be\n\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\31\3\31\3\31\7\31\u00c8\n\31\f\31\16\31\u00cb\13\31\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\5\33\u00d3\n\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\5\35\u00e0\n\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\3\37\3\37\3\37\7\37\u00ee\n\37\f\37\16\37\u00f1\13"+
		"\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00fe\n \3!\3!\5!\u0102\n!\3\""+
		"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0113\n#\3$\3$\3$\3$\3"+
		"$\3$\3$\3%\3%\3%\3%\5%\u0120\n%\3&\3&\3&\2\2\'\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\3\3\2\6\7\2\u0122\2R"+
		"\3\2\2\2\4W\3\2\2\2\6[\3\2\2\2\b]\3\2\2\2\n`\3\2\2\2\fh\3\2\2\2\16p\3"+
		"\2\2\2\20u\3\2\2\2\22z\3\2\2\2\24~\3\2\2\2\26\u0083\3\2\2\2\30\u0089\3"+
		"\2\2\2\32\u008b\3\2\2\2\34\u0098\3\2\2\2\36\u009c\3\2\2\2 \u009e\3\2\2"+
		"\2\"\u00a3\3\2\2\2$\u00ab\3\2\2\2&\u00ad\3\2\2\2(\u00b1\3\2\2\2*\u00b9"+
		"\3\2\2\2,\u00bd\3\2\2\2.\u00bf\3\2\2\2\60\u00c4\3\2\2\2\62\u00cc\3\2\2"+
		"\2\64\u00d2\3\2\2\2\66\u00d4\3\2\2\28\u00df\3\2\2\2:\u00e1\3\2\2\2<\u00e8"+
		"\3\2\2\2>\u00fd\3\2\2\2@\u0101\3\2\2\2B\u0103\3\2\2\2D\u0112\3\2\2\2F"+
		"\u0114\3\2\2\2H\u011f\3\2\2\2J\u0121\3\2\2\2LS\7(\2\2MO\7\3\2\2NM\3\2"+
		"\2\2NO\3\2\2\2OP\3\2\2\2PS\7)\2\2QS\7*\2\2RL\3\2\2\2RN\3\2\2\2RQ\3\2\2"+
		"\2S\3\3\2\2\2TX\7(\2\2UX\7)\2\2VX\7*\2\2WT\3\2\2\2WU\3\2\2\2WV\3\2\2\2"+
		"X\5\3\2\2\2Y\\\5\2\2\2Z\\\7\'\2\2[Y\3\2\2\2[Z\3\2\2\2\\\7\3\2\2\2]^\7"+
		"\'\2\2^\t\3\2\2\2_a\7\'\2\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\7\4\2\2cd"+
		"\5\6\4\2de\7\5\2\2ef\5\6\4\2fg\t\2\2\2g\13\3\2\2\2hm\7\'\2\2ij\7\b\2\2"+
		"jl\7\'\2\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\r\3\2\2\2om\3\2\2"+
		"\2pq\7\'\2\2qr\7\t\2\2rs\5\4\3\2st\7\7\2\2t\17\3\2\2\2uv\5\22\n\2vw\7"+
		"\n\2\2wx\5\24\13\2xy\7\b\2\2y\21\3\2\2\2z{\7\'\2\2{|\7\13\2\2|}\7!\2\2"+
		"}\23\3\2\2\2~\177\7\23\2\2\177\u0080\5\26\f\2\u0080\u0081\7\26\2\2\u0081"+
		"\25\3\2\2\2\u0082\u0084\5\30\r\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2"+
		"\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\27\3\2\2\2\u0087\u008a"+
		"\5\32\16\2\u0088\u008a\5F$\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a"+
		"\31\3\2\2\2\u008b\u008c\7\'\2\2\u008c\u008d\7\13\2\2\u008d\u008e\7\"\2"+
		"\2\u008e\u008f\7\n\2\2\u008f\u0090\5\34\17\2\u0090\u0091\7\f\2\2\u0091"+
		"\33\3\2\2\2\u0092\u0099\5\36\20\2\u0093\u0099\5&\24\2\u0094\u0099\5,\27"+
		"\2\u0095\u0099\5.\30\2\u0096\u0099\5D#\2\u0097\u0099\5B\"\2\u0098\u0092"+
		"\3\2\2\2\u0098\u0093\3\2\2\2\u0098\u0094\3\2\2\2\u0098\u0095\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\35\3\2\2\2\u009a\u009d\5 \21"+
		"\2\u009b\u009d\5\"\22\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d"+
		"\37\3\2\2\2\u009e\u009f\7\22\2\2\u009f\u00a0\5\b\5\2\u00a0\u00a1\7\33"+
		"\2\2\u00a1\u00a2\5$\23\2\u00a2!\3\2\2\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5"+
		"\5\n\6\2\u00a5\u00a6\7\33\2\2\u00a6\u00a7\5$\23\2\u00a7#\3\2\2\2\u00a8"+
		"\u00ac\5D#\2\u00a9\u00ac\5.\30\2\u00aa\u00ac\5B\"\2\u00ab\u00a8\3\2\2"+
		"\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac%\3\2\2\2\u00ad\u00ae"+
		"\7\r\2\2\u00ae\u00af\5(\25\2\u00af\u00b0\7\16\2\2\u00b0\'\3\2\2\2\u00b1"+
		"\u00b6\5*\26\2\u00b2\u00b3\7\17\2\2\u00b3\u00b5\5*\26\2\u00b4\u00b2\3"+
		"\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		")\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\5\16\b\2\u00ba+\3\2\2\2\u00bb"+
		"\u00be\5\b\5\2\u00bc\u00be\5\n\6\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2"+
		"\2\2\u00be-\3\2\2\2\u00bf\u00c0\7\37\2\2\u00c0\u00c1\7\4\2\2\u00c1\u00c2"+
		"\5\60\31\2\u00c2\u00c3\7\6\2\2\u00c3/\3\2\2\2\u00c4\u00c9\5\62\32\2\u00c5"+
		"\u00c6\7\17\2\2\u00c6\u00c8\5\62\32\2\u00c7\u00c5\3\2\2\2\u00c8\u00cb"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\61\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\5\64\33\2\u00cd\u00ce\7\13\2\2\u00ce\u00cf"+
		"\58\35\2\u00cf\63\3\2\2\2\u00d0\u00d3\5\16\b\2\u00d1\u00d3\5\66\34\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3\65\3\2\2\2\u00d4\u00d5\7\'\2"+
		"\2\u00d5\u00d6\7\t\2\2\u00d6\u00d7\5\4\3\2\u00d7\u00d8\7\13\2\2\u00d8"+
		"\u00d9\5\4\3\2\u00d9\u00da\7\5\2\2\u00da\u00db\5\4\3\2\u00db\u00dc\7\7"+
		"\2\2\u00dc\67\3\2\2\2\u00dd\u00e0\5\34\17\2\u00de\u00e0\5:\36\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e09\3\2\2\2\u00e1\u00e2\7 \2\2\u00e2"+
		"\u00e3\7\34\2\2\u00e3\u00e4\7\20\2\2\u00e4\u00e5\7\30\2\2\u00e5\u00e6"+
		"\5<\37\2\u00e6\u00e7\7\27\2\2\u00e7;\3\2\2\2\u00e8\u00ef\5> \2\u00e9\u00ea"+
		"\7\17\2\2\u00ea\u00eb\5> \2\u00eb\u00ec\7\17\2\2\u00ec\u00ee\3\2\2\2\u00ed"+
		"\u00e9\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0=\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\5@!\2\u00f3\u00f4"+
		"\7\21\2\2\u00f4\u00f5\7\4\2\2\u00f5\u00f6\7\6\2\2\u00f6\u00fe\3\2\2\2"+
		"\u00f7\u00f8\5@!\2\u00f8\u00f9\7\21\2\2\u00f9\u00fa\7\4\2\2\u00fa\u00fb"+
		"\5\60\31\2\u00fb\u00fc\7\6\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00f2\3\2\2\2"+
		"\u00fd\u00f7\3\2\2\2\u00fe?\3\2\2\2\u00ff\u0102\7\'\2\2\u0100\u0102\5"+
		"\16\b\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102A\3\2\2\2\u0103\u0104"+
		"\7\'\2\2\u0104C\3\2\2\2\u0105\u0113\7\24\2\2\u0106\u0113\7\25\2\2\u0107"+
		"\u0108\7\32\2\2\u0108\u0113\7\25\2\2\u0109\u0113\7\31\2\2\u010a\u010b"+
		"\7\32\2\2\u010b\u0113\7\31\2\2\u010c\u0113\7#\2\2\u010d\u010e\7\32\2\2"+
		"\u010e\u0113\7#\2\2\u010f\u0113\7\36\2\2\u0110\u0111\7\32\2\2\u0111\u0113"+
		"\7\36\2\2\u0112\u0105\3\2\2\2\u0112\u0106\3\2\2\2\u0112\u0107\3\2\2\2"+
		"\u0112\u0109\3\2\2\2\u0112\u010a\3\2\2\2\u0112\u010c\3\2\2\2\u0112\u010d"+
		"\3\2\2\2\u0112\u010f\3\2\2\2\u0112\u0110\3\2\2\2\u0113E\3\2\2\2\u0114"+
		"\u0115\7\'\2\2\u0115\u0116\7\13\2\2\u0116\u0117\5H%\2\u0117\u0118\7\n"+
		"\2\2\u0118\u0119\5J&\2\u0119\u011a\7\f\2\2\u011aG\3\2\2\2\u011b\u0120"+
		"\7\25\2\2\u011c\u0120\7\36\2\2\u011d\u011e\7\32\2\2\u011e\u0120\7\36\2"+
		"\2\u011f\u011b\3\2\2\2\u011f\u011c\3\2\2\2\u011f\u011d\3\2\2\2\u0120I"+
		"\3\2\2\2\u0121\u0122\5\f\7\2\u0122K\3\2\2\2\27NRW[`m\u0085\u0089\u0098"+
		"\u009c\u00ab\u00b6\u00bd\u00c9\u00d2\u00df\u00ef\u00fd\u0101\u0112\u011f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}