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
		public TerminalNode NUMBER_8() { return getToken(SymbolParser.NUMBER_8, 0); }
		public TerminalNode NUMBER_10() { return getToken(SymbolParser.NUMBER_10, 0); }
		public TerminalNode NUMBER_16() { return getToken(SymbolParser.NUMBER_16, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
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
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(NUMBER_8);
				}
				break;
			case T__0:
			case NUMBER_10:
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
		public TerminalNode NUMBER_8() { return getToken(SymbolParser.NUMBER_8, 0); }
		public TerminalNode NUMBER_10() { return getToken(SymbolParser.NUMBER_10, 0); }
		public TerminalNode NUMBER_16() { return getToken(SymbolParser.NUMBER_16, 0); }
		public Positive_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positive_number; }
	}

	public final Positive_numberContext positive_number() throws RecognitionException {
		Positive_numberContext _localctx = new Positive_numberContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_positive_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER_8) | (1L << NUMBER_10) | (1L << NUMBER_16))) != 0)) ) {
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
	}
	public static class ConstNumberContext extends ConstantContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ConstNumberContext(ConstantContext ctx) { copyFrom(ctx); }
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constant);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case NUMBER_8:
			case NUMBER_10:
			case NUMBER_16:
				_localctx = new ConstNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				number();
				}
				break;
			case ID:
				_localctx = new ConstRefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
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
	}

	public final RangeTypeContext rangeType() throws RecognitionException {
		RangeTypeContext _localctx = new RangeTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rangeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
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
		public ConstantContext startIndex;
		public ConstantContext stopIndex;
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
	}

	public final RangeTypeRangeContext rangeTypeRange() throws RecognitionException {
		RangeTypeRangeContext _localctx = new RangeTypeRangeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rangeTypeRange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(90);
				((RangeTypeRangeContext)_localctx).name = match(ID);
				}
			}

			setState(93);
			match(T__1);
			setState(94);
			((RangeTypeRangeContext)_localctx).startIndex = constant();
			setState(95);
			match(T__2);
			setState(96);
			((RangeTypeRangeContext)_localctx).stopIndex = constant();
			setState(97);
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
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			((QualifiedNameContext)_localctx).ID = match(ID);
			((QualifiedNameContext)_localctx).name.add(((QualifiedNameContext)_localctx).ID);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(100);
				match(T__5);
				setState(101);
				((QualifiedNameContext)_localctx).ID = match(ID);
				((QualifiedNameContext)_localctx).name.add(((QualifiedNameContext)_localctx).ID);
				}
				}
				setState(106);
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
	}

	public final NumberedNameContext numberedName() throws RecognitionException {
		NumberedNameContext _localctx = new NumberedNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numberedName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			((NumberedNameContext)_localctx).name = match(ID);
			setState(108);
			match(T__6);
			setState(109);
			((NumberedNameContext)_localctx).value = positive_number();
			setState(110);
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
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_symbol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			header();
			setState(113);
			match(T__7);
			setState(114);
			body();
			setState(115);
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
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			((HeaderContext)_localctx).name = match(ID);
			setState(118);
			match(T__8);
			setState(119);
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
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(BEGIN);
			setState(122);
			declList();
			setState(123);
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
	}

	public final DeclListContext declList() throws RecognitionException {
		DeclListContext _localctx = new DeclListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(125);
				((DeclListContext)_localctx).decl = decl();
				((DeclListContext)_localctx).elements.add(((DeclListContext)_localctx).decl);
				}
				}
				setState(128); 
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
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_decl);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				declType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
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
		public Token name;
		public TerminalNode TYPE() { return getToken(SymbolParser.TYPE, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public DeclTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declType; }
	}

	public final DeclTypeContext declType() throws RecognitionException {
		DeclTypeContext _localctx = new DeclTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			((DeclTypeContext)_localctx).name = match(ID);
			setState(135);
			match(T__8);
			setState(136);
			match(TYPE);
			setState(137);
			match(T__7);
			setState(138);
			typeType();
			setState(139);
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
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeType);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				arrayType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				enumType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				subrangeType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				recordType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(145);
				simpleType();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(146);
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
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayType);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				arrayTypeType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
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
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public RangeTypeContext rangeType() {
			return getRuleContext(RangeTypeContext.class,0);
		}
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayTypeElementContext arrayTypeElement() {
			return getRuleContext(ArrayTypeElementContext.class,0);
		}
		public ArrayTypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTypeType; }
	}

	public final ArrayTypeTypeContext arrayTypeType() throws RecognitionException {
		ArrayTypeTypeContext _localctx = new ArrayTypeTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arrayTypeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(ARRAY);
			setState(154);
			rangeType();
			setState(155);
			match(OF);
			setState(156);
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
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public RangeTypeRangeContext rangeTypeRange() {
			return getRuleContext(RangeTypeRangeContext.class,0);
		}
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayTypeElementContext arrayTypeElement() {
			return getRuleContext(ArrayTypeElementContext.class,0);
		}
		public ArrayTypeRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTypeRange; }
	}

	public final ArrayTypeRangeContext arrayTypeRange() throws RecognitionException {
		ArrayTypeRangeContext _localctx = new ArrayTypeRangeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arrayTypeRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(ARRAY);
			setState(159);
			rangeTypeRange();
			setState(160);
			match(OF);
			setState(161);
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
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ArrayTypeElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTypeElement; }
	}

	public final ArrayTypeElementContext arrayTypeElement() throws RecognitionException {
		ArrayTypeElementContext _localctx = new ArrayTypeElementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arrayTypeElement);
		try {
			setState(165);
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
				setState(163);
				simpleType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
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
		public EnumElementListContext enumElementList() {
			return getRuleContext(EnumElementListContext.class,0);
		}
		public EnumTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumType; }
	}

	public final EnumTypeContext enumType() throws RecognitionException {
		EnumTypeContext _localctx = new EnumTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_enumType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(T__10);
			setState(168);
			enumElementList();
			setState(169);
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
	}

	public final EnumElementListContext enumElementList() throws RecognitionException {
		EnumElementListContext _localctx = new EnumElementListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_enumElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			((EnumElementListContext)_localctx).eumElement = eumElement();
			((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(172);
				match(T__12);
				setState(173);
				((EnumElementListContext)_localctx).eumElement = eumElement();
				((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
				}
				}
				setState(178);
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
	}

	public final EumElementContext eumElement() throws RecognitionException {
		EumElementContext _localctx = new EumElementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_eumElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
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
		public RangeTypeRangeContext rangeTypeRange() {
			return getRuleContext(RangeTypeRangeContext.class,0);
		}
		public SubrangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subrangeType; }
	}

	public final SubrangeTypeContext subrangeType() throws RecognitionException {
		SubrangeTypeContext _localctx = new SubrangeTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_subrangeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			rangeTypeRange();
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
		public TerminalNode RECORD() { return getToken(SymbolParser.RECORD, 0); }
		public RecordFieldListContext recordFieldList() {
			return getRuleContext(RecordFieldListContext.class,0);
		}
		public RecordTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordType; }
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_recordType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(RECORD);
			setState(184);
			match(T__1);
			setState(185);
			recordFieldList();
			setState(186);
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
	}

	public final RecordFieldListContext recordFieldList() throws RecognitionException {
		RecordFieldListContext _localctx = new RecordFieldListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_recordFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			((RecordFieldListContext)_localctx).recordField = recordField();
			((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(189);
				match(T__12);
				setState(190);
				((RecordFieldListContext)_localctx).recordField = recordField();
				((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
				}
				}
				setState(195);
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
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public FieldTypeContext fieldType() {
			return getRuleContext(FieldTypeContext.class,0);
		}
		public RecordFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordField; }
	}

	public final RecordFieldContext recordField() throws RecognitionException {
		RecordFieldContext _localctx = new RecordFieldContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_recordField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			fieldName();
			setState(197);
			match(T__8);
			setState(198);
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
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_fieldName);
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				numberedName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
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
		public Token name;
		public Positive_numberContext offset;
		public Positive_numberContext startPos;
		public Positive_numberContext stopPos;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public List<Positive_numberContext> positive_number() {
			return getRuleContexts(Positive_numberContext.class);
		}
		public Positive_numberContext positive_number(int i) {
			return getRuleContext(Positive_numberContext.class,i);
		}
		public BitfieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitfieldName; }
	}

	public final BitfieldNameContext bitfieldName() throws RecognitionException {
		BitfieldNameContext _localctx = new BitfieldNameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_bitfieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			((BitfieldNameContext)_localctx).name = match(ID);
			setState(205);
			match(T__6);
			setState(206);
			((BitfieldNameContext)_localctx).offset = positive_number();
			setState(207);
			match(T__8);
			setState(208);
			((BitfieldNameContext)_localctx).startPos = positive_number();
			setState(209);
			match(T__2);
			setState(210);
			((BitfieldNameContext)_localctx).stopPos = positive_number();
			setState(211);
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
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public FieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldType; }
	}

	public final FieldTypeContext fieldType() throws RecognitionException {
		FieldTypeContext _localctx = new FieldTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_fieldType);
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARRAY:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				arrayType();
				}
				break;
			case BOOLEAN:
			case CARDINAL:
			case INTEGER:
			case LONG:
			case POINTER:
			case UNSPECIFIED:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				simpleType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(215);
				referenceType();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
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
		public TerminalNode SELECT() { return getToken(SymbolParser.SELECT, 0); }
		public TerminalNode OVERLAID() { return getToken(SymbolParser.OVERLAID, 0); }
		public TerminalNode FROM() { return getToken(SymbolParser.FROM, 0); }
		public SelectCaseListContext selectCaseList() {
			return getRuleContext(SelectCaseListContext.class,0);
		}
		public TerminalNode ENDCASE() { return getToken(SymbolParser.ENDCASE, 0); }
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_select);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(SELECT);
			setState(220);
			match(OVERLAID);
			setState(221);
			match(T__13);
			setState(222);
			match(FROM);
			setState(223);
			selectCaseList();
			setState(224);
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
	}

	public final SelectCaseListContext selectCaseList() throws RecognitionException {
		SelectCaseListContext _localctx = new SelectCaseListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_selectCaseList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			((SelectCaseListContext)_localctx).selectCase = selectCase();
			((SelectCaseListContext)_localctx).elements.add(((SelectCaseListContext)_localctx).selectCase);
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(227);
				match(T__12);
				setState(228);
				((SelectCaseListContext)_localctx).selectCase = selectCase();
				((SelectCaseListContext)_localctx).elements.add(((SelectCaseListContext)_localctx).selectCase);
				setState(229);
				match(T__12);
				}
				}
				setState(235);
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
	}
	public static class TypeSelectCaseEmptyContext extends SelectCaseContext {
		public SelectCaseSelectorContext selectCaseSelector() {
			return getRuleContext(SelectCaseSelectorContext.class,0);
		}
		public TypeSelectCaseEmptyContext(SelectCaseContext ctx) { copyFrom(ctx); }
	}

	public final SelectCaseContext selectCase() throws RecognitionException {
		SelectCaseContext _localctx = new SelectCaseContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_selectCase);
		try {
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new TypeSelectCaseEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				selectCaseSelector();
				setState(237);
				match(T__14);
				setState(238);
				match(T__1);
				setState(239);
				match(T__3);
				}
				break;
			case 2:
				_localctx = new TypeSelectCaseListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				selectCaseSelector();
				setState(242);
				match(T__14);
				setState(243);
				match(T__1);
				setState(244);
				recordFieldList();
				setState(245);
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
	}

	public final SelectCaseSelectorContext selectCaseSelector() throws RecognitionException {
		SelectCaseSelectorContext _localctx = new SelectCaseSelectorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_selectCaseSelector);
		try {
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				((SelectCaseSelectorContext)_localctx).name = match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(250);
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
		public Token name;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public ReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceType; }
	}

	public final ReferenceTypeContext referenceType() throws RecognitionException {
		ReferenceTypeContext _localctx = new ReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_referenceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			((ReferenceTypeContext)_localctx).name = match(ID);
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
	}
	public static class TypeLongIntegerContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode INTEGER() { return getToken(SymbolParser.INTEGER, 0); }
		public TypeLongIntegerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeBooleanContext extends SimpleTypeContext {
		public TerminalNode BOOLEAN() { return getToken(SymbolParser.BOOLEAN, 0); }
		public TypeBooleanContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeIntegerContext extends SimpleTypeContext {
		public TerminalNode INTEGER() { return getToken(SymbolParser.INTEGER, 0); }
		public TypeIntegerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypePointerContext extends SimpleTypeContext {
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TypePointerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeUnspecifiedContext extends SimpleTypeContext {
		public TerminalNode UNSPECIFIED() { return getToken(SymbolParser.UNSPECIFIED, 0); }
		public TypeUnspecifiedContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongPointerContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TypeLongPointerContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongCardinalContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public TypeLongCardinalContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongUnspecifiedContext extends SimpleTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode UNSPECIFIED() { return getToken(SymbolParser.UNSPECIFIED, 0); }
		public TypeLongUnspecifiedContext(SimpleTypeContext ctx) { copyFrom(ctx); }
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_simpleType);
		try {
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				match(BOOLEAN);
				}
				break;
			case 2:
				_localctx = new TypeCardinalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				match(CARDINAL);
				}
				break;
			case 3:
				_localctx = new TypeLongCardinalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(257);
				match(LONG);
				setState(258);
				match(CARDINAL);
				}
				break;
			case 4:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(259);
				match(INTEGER);
				}
				break;
			case 5:
				_localctx = new TypeLongIntegerContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(260);
				match(LONG);
				setState(261);
				match(INTEGER);
				}
				break;
			case 6:
				_localctx = new TypeUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(262);
				match(UNSPECIFIED);
				}
				break;
			case 7:
				_localctx = new TypeLongUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(263);
				match(LONG);
				setState(264);
				match(UNSPECIFIED);
				}
				break;
			case 8:
				_localctx = new TypePointerContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(265);
				match(POINTER);
				}
				break;
			case 9:
				_localctx = new TypeLongPointerContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(266);
				match(LONG);
				setState(267);
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
		public Token name;
		public ConstTypeContext constType() {
			return getRuleContext(ConstTypeContext.class,0);
		}
		public ConstValueContext constValue() {
			return getRuleContext(ConstValueContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public DeclConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declConst; }
	}

	public final DeclConstContext declConst() throws RecognitionException {
		DeclConstContext _localctx = new DeclConstContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_declConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			((DeclConstContext)_localctx).name = match(ID);
			setState(271);
			match(T__8);
			setState(272);
			constType();
			setState(273);
			match(T__7);
			setState(274);
			constValue();
			setState(275);
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
	}

	public final ConstTypeContext constType() throws RecognitionException {
		ConstTypeContext _localctx = new ConstTypeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_constType);
		try {
			setState(281);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CARDINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				match(CARDINAL);
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(POINTER);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 3);
				{
				setState(279);
				match(LONG);
				setState(280);
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
	}

	public final ConstValueContext constValue() throws RecognitionException {
		ConstValueContext _localctx = new ConstValueContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_constValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0120\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\5\2O\n\2\3\2\3\2\5\2S\n\2"+
		"\3\3\3\3\3\4\3\4\5\4Y\n\4\3\5\3\5\3\6\5\6^\n\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\7\7i\n\7\f\7\16\7l\13\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\6\f\u0081\n\f\r\f\16\f"+
		"\u0082\3\r\3\r\5\r\u0087\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u0096\n\17\3\20\3\20\5\20\u009a\n\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\5\23\u00a8\n\23"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\7\25\u00b1\n\25\f\25\16\25\u00b4\13"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\7\31\u00c2"+
		"\n\31\f\31\16\31\u00c5\13\31\3\32\3\32\3\32\3\32\3\33\3\33\5\33\u00cd"+
		"\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\5\35\u00dc\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3\37\7\37\u00ea\n\37\f\37\16\37\u00ed\13\37\3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \5 \u00fa\n \3!\3!\5!\u00fe\n!\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#"+
		"\3#\3#\3#\3#\3#\5#\u010f\n#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\5%\u011c"+
		"\n%\3&\3&\3&\2\2\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJ\2\4\3\2(*\3\2\6\7\2\u011c\2R\3\2\2\2\4T\3\2\2\2\6"+
		"X\3\2\2\2\bZ\3\2\2\2\n]\3\2\2\2\fe\3\2\2\2\16m\3\2\2\2\20r\3\2\2\2\22"+
		"w\3\2\2\2\24{\3\2\2\2\26\u0080\3\2\2\2\30\u0086\3\2\2\2\32\u0088\3\2\2"+
		"\2\34\u0095\3\2\2\2\36\u0099\3\2\2\2 \u009b\3\2\2\2\"\u00a0\3\2\2\2$\u00a7"+
		"\3\2\2\2&\u00a9\3\2\2\2(\u00ad\3\2\2\2*\u00b5\3\2\2\2,\u00b7\3\2\2\2."+
		"\u00b9\3\2\2\2\60\u00be\3\2\2\2\62\u00c6\3\2\2\2\64\u00cc\3\2\2\2\66\u00ce"+
		"\3\2\2\28\u00db\3\2\2\2:\u00dd\3\2\2\2<\u00e4\3\2\2\2>\u00f9\3\2\2\2@"+
		"\u00fd\3\2\2\2B\u00ff\3\2\2\2D\u010e\3\2\2\2F\u0110\3\2\2\2H\u011b\3\2"+
		"\2\2J\u011d\3\2\2\2LS\7(\2\2MO\7\3\2\2NM\3\2\2\2NO\3\2\2\2OP\3\2\2\2P"+
		"S\7)\2\2QS\7*\2\2RL\3\2\2\2RN\3\2\2\2RQ\3\2\2\2S\3\3\2\2\2TU\t\2\2\2U"+
		"\5\3\2\2\2VY\5\2\2\2WY\7\'\2\2XV\3\2\2\2XW\3\2\2\2Y\7\3\2\2\2Z[\7\'\2"+
		"\2[\t\3\2\2\2\\^\7\'\2\2]\\\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\4\2\2`a\5"+
		"\6\4\2ab\7\5\2\2bc\5\6\4\2cd\t\3\2\2d\13\3\2\2\2ej\7\'\2\2fg\7\b\2\2g"+
		"i\7\'\2\2hf\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\r\3\2\2\2lj\3\2\2\2"+
		"mn\7\'\2\2no\7\t\2\2op\5\4\3\2pq\7\7\2\2q\17\3\2\2\2rs\5\22\n\2st\7\n"+
		"\2\2tu\5\24\13\2uv\7\b\2\2v\21\3\2\2\2wx\7\'\2\2xy\7\13\2\2yz\7!\2\2z"+
		"\23\3\2\2\2{|\7\23\2\2|}\5\26\f\2}~\7\26\2\2~\25\3\2\2\2\177\u0081\5\30"+
		"\r\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\27\3\2\2\2\u0084\u0087\5\32\16\2\u0085\u0087\5F$\2\u0086"+
		"\u0084\3\2\2\2\u0086\u0085\3\2\2\2\u0087\31\3\2\2\2\u0088\u0089\7\'\2"+
		"\2\u0089\u008a\7\13\2\2\u008a\u008b\7\"\2\2\u008b\u008c\7\n\2\2\u008c"+
		"\u008d\5\34\17\2\u008d\u008e\7\f\2\2\u008e\33\3\2\2\2\u008f\u0096\5\36"+
		"\20\2\u0090\u0096\5&\24\2\u0091\u0096\5,\27\2\u0092\u0096\5.\30\2\u0093"+
		"\u0096\5D#\2\u0094\u0096\5B\"\2\u0095\u008f\3\2\2\2\u0095\u0090\3\2\2"+
		"\2\u0095\u0091\3\2\2\2\u0095\u0092\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0094"+
		"\3\2\2\2\u0096\35\3\2\2\2\u0097\u009a\5 \21\2\u0098\u009a\5\"\22\2\u0099"+
		"\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\37\3\2\2\2\u009b\u009c\7\22\2"+
		"\2\u009c\u009d\5\b\5\2\u009d\u009e\7\33\2\2\u009e\u009f\5$\23\2\u009f"+
		"!\3\2\2\2\u00a0\u00a1\7\22\2\2\u00a1\u00a2\5\n\6\2\u00a2\u00a3\7\33\2"+
		"\2\u00a3\u00a4\5$\23\2\u00a4#\3\2\2\2\u00a5\u00a8\5D#\2\u00a6\u00a8\5"+
		"B\"\2\u00a7\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8%\3\2\2\2\u00a9\u00aa"+
		"\7\r\2\2\u00aa\u00ab\5(\25\2\u00ab\u00ac\7\16\2\2\u00ac\'\3\2\2\2\u00ad"+
		"\u00b2\5*\26\2\u00ae\u00af\7\17\2\2\u00af\u00b1\5*\26\2\u00b0\u00ae\3"+
		"\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		")\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b6\5\16\b\2\u00b6+\3\2\2\2\u00b7"+
		"\u00b8\5\n\6\2\u00b8-\3\2\2\2\u00b9\u00ba\7\37\2\2\u00ba\u00bb\7\4\2\2"+
		"\u00bb\u00bc\5\60\31\2\u00bc\u00bd\7\6\2\2\u00bd/\3\2\2\2\u00be\u00c3"+
		"\5\62\32\2\u00bf\u00c0\7\17\2\2\u00c0\u00c2\5\62\32\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\61\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\5\64\33\2\u00c7\u00c8\7\13"+
		"\2\2\u00c8\u00c9\58\35\2\u00c9\63\3\2\2\2\u00ca\u00cd\5\16\b\2\u00cb\u00cd"+
		"\5\66\34\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\65\3\2\2\2\u00ce"+
		"\u00cf\7\'\2\2\u00cf\u00d0\7\t\2\2\u00d0\u00d1\5\4\3\2\u00d1\u00d2\7\13"+
		"\2\2\u00d2\u00d3\5\4\3\2\u00d3\u00d4\7\5\2\2\u00d4\u00d5\5\4\3\2\u00d5"+
		"\u00d6\7\7\2\2\u00d6\67\3\2\2\2\u00d7\u00dc\5\36\20\2\u00d8\u00dc\5D#"+
		"\2\u00d9\u00dc\5B\"\2\u00da\u00dc\5:\36\2\u00db\u00d7\3\2\2\2\u00db\u00d8"+
		"\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00da\3\2\2\2\u00dc9\3\2\2\2\u00dd"+
		"\u00de\7 \2\2\u00de\u00df\7\34\2\2\u00df\u00e0\7\20\2\2\u00e0\u00e1\7"+
		"\30\2\2\u00e1\u00e2\5<\37\2\u00e2\u00e3\7\27\2\2\u00e3;\3\2\2\2\u00e4"+
		"\u00eb\5> \2\u00e5\u00e6\7\17\2\2\u00e6\u00e7\5> \2\u00e7\u00e8\7\17\2"+
		"\2\u00e8\u00ea\3\2\2\2\u00e9\u00e5\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9"+
		"\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec=\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00ef\5@!\2\u00ef\u00f0\7\21\2\2\u00f0\u00f1\7\4\2\2\u00f1\u00f2\7\6"+
		"\2\2\u00f2\u00fa\3\2\2\2\u00f3\u00f4\5@!\2\u00f4\u00f5\7\21\2\2\u00f5"+
		"\u00f6\7\4\2\2\u00f6\u00f7\5\60\31\2\u00f7\u00f8\7\6\2\2\u00f8\u00fa\3"+
		"\2\2\2\u00f9\u00ee\3\2\2\2\u00f9\u00f3\3\2\2\2\u00fa?\3\2\2\2\u00fb\u00fe"+
		"\7\'\2\2\u00fc\u00fe\5\16\b\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2"+
		"\u00feA\3\2\2\2\u00ff\u0100\7\'\2\2\u0100C\3\2\2\2\u0101\u010f\7\24\2"+
		"\2\u0102\u010f\7\25\2\2\u0103\u0104\7\32\2\2\u0104\u010f\7\25\2\2\u0105"+
		"\u010f\7\31\2\2\u0106\u0107\7\32\2\2\u0107\u010f\7\31\2\2\u0108\u010f"+
		"\7#\2\2\u0109\u010a\7\32\2\2\u010a\u010f\7#\2\2\u010b\u010f\7\36\2\2\u010c"+
		"\u010d\7\32\2\2\u010d\u010f\7\36\2\2\u010e\u0101\3\2\2\2\u010e\u0102\3"+
		"\2\2\2\u010e\u0103\3\2\2\2\u010e\u0105\3\2\2\2\u010e\u0106\3\2\2\2\u010e"+
		"\u0108\3\2\2\2\u010e\u0109\3\2\2\2\u010e\u010b\3\2\2\2\u010e\u010c\3\2"+
		"\2\2\u010fE\3\2\2\2\u0110\u0111\7\'\2\2\u0111\u0112\7\13\2\2\u0112\u0113"+
		"\5H%\2\u0113\u0114\7\n\2\2\u0114\u0115\5J&\2\u0115\u0116\7\f\2\2\u0116"+
		"G\3\2\2\2\u0117\u011c\7\25\2\2\u0118\u011c\7\36\2\2\u0119\u011a\7\32\2"+
		"\2\u011a\u011c\7\36\2\2\u011b\u0117\3\2\2\2\u011b\u0118\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011cI\3\2\2\2\u011d\u011e\5\f\7\2\u011eK\3\2\2\2\25NR"+
		"X]j\u0082\u0086\u0095\u0099\u00a7\u00b2\u00c3\u00cc\u00db\u00eb\u00f9"+
		"\u00fd\u010e\u011b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}