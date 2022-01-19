// Generated from data/type/Symbol.g4 by ANTLR 4.9.3
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
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, ARRAY=14, BEGIN=15, BOOLEAN=16, 
		CARDINAL=17, END=18, ENDCASE=19, FROM=20, INTEGER=21, LONG=22, OF=23, 
		OVERLAID=24, PACKED=25, POINTER=26, RECORD=27, RECORD32=28, SELECT=29, 
		SYMBOL=30, TO=31, TYPE=32, UNSPECIFIED=33, WORD=34, TRUE=35, FALSE=36, 
		ID=37, NUMBER_8=38, NUMBER_10=39, NUMBER_16=40, COMMENT_PARTIAL=41, COMMENT_LINE=42, 
		SPACE=43;
	public static final int
		RULE_number = 0, RULE_positive_number = 1, RULE_constant = 2, RULE_symbol = 3, 
		RULE_header = 4, RULE_body = 5, RULE_declList = 6, RULE_decl = 7, RULE_declType = 8, 
		RULE_typeType = 9, RULE_simpleType = 10, RULE_referenceType = 11, RULE_pointerType = 12, 
		RULE_subrangeType = 13, RULE_arrayType = 14, RULE_arrayElementType = 15, 
		RULE_enumType = 16, RULE_enumElementList = 17, RULE_eumElement = 18, RULE_recordType = 19, 
		RULE_recordFieldList = 20, RULE_recordField = 21, RULE_fieldName = 22, 
		RULE_fieldType = 23, RULE_declConstant = 24, RULE_constantType = 25, RULE_constantTypeNumeric = 26, 
		RULE_constantValue = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"number", "positive_number", "constant", "symbol", "header", "body", 
			"declList", "decl", "declType", "typeType", "simpleType", "referenceType", 
			"pointerType", "subrangeType", "arrayType", "arrayElementType", "enumType", 
			"enumElementList", "eumElement", "recordType", "recordFieldList", "recordField", 
			"fieldName", "fieldType", "declConstant", "constantType", "constantTypeNumeric", 
			"constantValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'='", "'.'", "':'", "';'", "'['", "'..'", "']'", "')'", 
			"'{'", "'}'", "','", "'('", "'ARRAY'", "'BEGIN'", "'BOOLEAN'", "'CARDINAL'", 
			"'END'", "'ENDCASE'", "'FROM'", "'INTEGER'", "'LONG'", "'OF'", "'OVERLAID'", 
			"'PACKED'", "'POINTER'", "'RECORD'", "'RECORD32'", "'SELECT'", "'SYMBOL'", 
			"'TO'", "'TYPE'", "'UNSPECIFIED'", "'WORD'", "'TRUE'", "'FALSE'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ARRAY", "BEGIN", "BOOLEAN", "CARDINAL", "END", "ENDCASE", 
			"FROM", "INTEGER", "LONG", "OF", "OVERLAID", "PACKED", "POINTER", "RECORD", 
			"RECORD32", "SELECT", "SYMBOL", "TO", "TYPE", "UNSPECIFIED", "WORD", 
			"TRUE", "FALSE", "ID", "NUMBER_8", "NUMBER_10", "NUMBER_16", "COMMENT_PARTIAL", 
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
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER_8:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(NUMBER_8);
				}
				break;
			case T__0:
			case NUMBER_10:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(57);
					match(T__0);
					}
				}

				setState(60);
				match(NUMBER_10);
				}
				break;
			case NUMBER_16:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
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
			setState(64);
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
	public static class ConstNameContext extends ConstantContext {
		public Token name;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public ConstNameContext(ConstantContext ctx) { copyFrom(ctx); }
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case NUMBER_8:
			case NUMBER_10:
			case NUMBER_16:
				_localctx = new ConstNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				number();
				}
				break;
			case ID:
				_localctx = new ConstNameContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				((ConstNameContext)_localctx).name = match(ID);
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
		enterRule(_localctx, 6, RULE_symbol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			header();
			setState(71);
			match(T__1);
			setState(72);
			body();
			setState(73);
			match(T__2);
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
		enterRule(_localctx, 8, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			((HeaderContext)_localctx).name = match(ID);
			setState(76);
			match(T__3);
			setState(77);
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
		enterRule(_localctx, 10, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(BEGIN);
			setState(80);
			declList();
			setState(81);
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
		enterRule(_localctx, 12, RULE_declList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				((DeclListContext)_localctx).decl = decl();
				((DeclListContext)_localctx).elements.add(((DeclListContext)_localctx).decl);
				}
				}
				setState(86); 
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
		public DeclConstantContext declConstant() {
			return getRuleContext(DeclConstantContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_decl);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				declType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				declConstant();
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
		enterRule(_localctx, 16, RULE_declType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((DeclTypeContext)_localctx).name = match(ID);
			setState(93);
			match(T__3);
			setState(94);
			match(TYPE);
			setState(95);
			match(T__1);
			setState(96);
			typeType();
			setState(97);
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

	public static class TypeTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public SubrangeTypeContext subrangeType() {
			return getRuleContext(SubrangeTypeContext.class,0);
		}
		public EnumTypeContext enumType() {
			return getRuleContext(EnumTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public TypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeType; }
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeType);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				referenceType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				pointerType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(102);
				subrangeType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(103);
				enumType();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(104);
				arrayType();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(105);
				recordType();
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
		enterRule(_localctx, 20, RULE_simpleType);
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(BOOLEAN);
				}
				break;
			case 2:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(INTEGER);
				}
				break;
			case 3:
				_localctx = new TypeCardinalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				match(CARDINAL);
				}
				break;
			case 4:
				_localctx = new TypeLongCardinalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				match(LONG);
				setState(112);
				match(CARDINAL);
				}
				break;
			case 5:
				_localctx = new TypeUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				match(UNSPECIFIED);
				}
				break;
			case 6:
				_localctx = new TypeLongUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				match(LONG);
				setState(115);
				match(UNSPECIFIED);
				}
				break;
			case 7:
				_localctx = new TypePointerContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(116);
				match(POINTER);
				}
				break;
			case 8:
				_localctx = new TypeLongPointerContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(117);
				match(LONG);
				setState(118);
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
		enterRule(_localctx, 22, RULE_referenceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
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

	public static class PointerTypeContext extends ParserRuleContext {
		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
	 
		public PointerTypeContext() { }
		public void copyFrom(PointerTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypePointerShortContext extends PointerTypeContext {
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TerminalNode TO() { return getToken(SymbolParser.TO, 0); }
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TypePointerShortContext(PointerTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypePointerLongContext extends PointerTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TerminalNode TO() { return getToken(SymbolParser.TO, 0); }
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TypePointerLongContext(PointerTypeContext ctx) { copyFrom(ctx); }
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pointerType);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case POINTER:
				_localctx = new TypePointerShortContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				match(POINTER);
				setState(124);
				match(TO);
				setState(125);
				referenceType();
				}
				break;
			case LONG:
				_localctx = new TypePointerLongContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(LONG);
				setState(127);
				match(POINTER);
				setState(128);
				match(TO);
				setState(129);
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

	public static class SubrangeTypeContext extends ParserRuleContext {
		public ConstantContext minValue;
		public ConstantContext maxValue;
		public Token closeChar;
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public SubrangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subrangeType; }
	}

	public final SubrangeTypeContext subrangeType() throws RecognitionException {
		SubrangeTypeContext _localctx = new SubrangeTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_subrangeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__5);
			setState(133);
			((SubrangeTypeContext)_localctx).minValue = constant();
			setState(134);
			match(T__6);
			setState(135);
			((SubrangeTypeContext)_localctx).maxValue = constant();
			setState(136);
			((SubrangeTypeContext)_localctx).closeChar = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__7 || _la==T__8) ) {
				((SubrangeTypeContext)_localctx).closeChar = (Token)_errHandler.recoverInline(this);
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

	public static class ArrayTypeContext extends ParserRuleContext {
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
	 
		public ArrayTypeContext() { }
		public void copyFrom(ArrayTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeArrayReferenceContext extends ArrayTypeContext {
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayElementTypeContext arrayElementType() {
			return getRuleContext(ArrayElementTypeContext.class,0);
		}
		public TypeArrayReferenceContext(ArrayTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeArraySubrangeContext extends ArrayTypeContext {
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public SubrangeTypeContext subrangeType() {
			return getRuleContext(SubrangeTypeContext.class,0);
		}
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayElementTypeContext arrayElementType() {
			return getRuleContext(ArrayElementTypeContext.class,0);
		}
		public TypeArraySubrangeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayType);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new TypeArrayReferenceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				match(ARRAY);
				setState(139);
				referenceType();
				setState(140);
				match(OF);
				setState(141);
				arrayElementType();
				}
				break;
			case 2:
				_localctx = new TypeArraySubrangeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(ARRAY);
				setState(144);
				subrangeType();
				setState(145);
				match(OF);
				setState(146);
				arrayElementType();
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

	public static class ArrayElementTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ArrayElementTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElementType; }
	}

	public final ArrayElementTypeContext arrayElementType() throws RecognitionException {
		ArrayElementTypeContext _localctx = new ArrayElementTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arrayElementType);
		try {
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				pointerType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
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
		enterRule(_localctx, 32, RULE_enumType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__9);
			setState(156);
			enumElementList();
			setState(157);
			match(T__10);
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
		enterRule(_localctx, 34, RULE_enumElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			((EnumElementListContext)_localctx).eumElement = eumElement();
			((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(160);
				match(T__11);
				setState(161);
				((EnumElementListContext)_localctx).eumElement = eumElement();
				((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
				}
				}
				setState(166);
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
		public Token name;
		public Positive_numberContext value;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public Positive_numberContext positive_number() {
			return getRuleContext(Positive_numberContext.class,0);
		}
		public EumElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eumElement; }
	}

	public final EumElementContext eumElement() throws RecognitionException {
		EumElementContext _localctx = new EumElementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_eumElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			((EumElementContext)_localctx).name = match(ID);
			setState(168);
			match(T__12);
			setState(169);
			((EumElementContext)_localctx).value = positive_number();
			setState(170);
			match(T__8);
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
	public static class TypeRecord32Context extends RecordTypeContext {
		public TerminalNode RECORD32() { return getToken(SymbolParser.RECORD32, 0); }
		public RecordFieldListContext recordFieldList() {
			return getRuleContext(RecordFieldListContext.class,0);
		}
		public TypeRecord32Context(RecordTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeRecord16Context extends RecordTypeContext {
		public TerminalNode RECORD() { return getToken(SymbolParser.RECORD, 0); }
		public RecordFieldListContext recordFieldList() {
			return getRuleContext(RecordFieldListContext.class,0);
		}
		public TypeRecord16Context(RecordTypeContext ctx) { copyFrom(ctx); }
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_recordType);
		try {
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RECORD:
				_localctx = new TypeRecord16Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(RECORD);
				setState(173);
				match(T__5);
				setState(174);
				recordFieldList();
				setState(175);
				match(T__7);
				}
				break;
			case RECORD32:
				_localctx = new TypeRecord32Context(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(RECORD32);
				setState(178);
				match(T__5);
				setState(179);
				recordFieldList();
				setState(180);
				match(T__7);
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
		enterRule(_localctx, 40, RULE_recordFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			((RecordFieldListContext)_localctx).recordField = recordField();
			((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(185);
				match(T__11);
				setState(186);
				((RecordFieldListContext)_localctx).recordField = recordField();
				((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
				}
				}
				setState(191);
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
		enterRule(_localctx, 42, RULE_recordField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			fieldName();
			setState(193);
			match(T__3);
			setState(194);
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
		public FieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
	 
		public FieldNameContext() { }
		public void copyFrom(FieldNameContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeFieldNameBitContext extends FieldNameContext {
		public Token name;
		public Positive_numberContext offset;
		public Positive_numberContext startBit;
		public Positive_numberContext stopBit;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public List<Positive_numberContext> positive_number() {
			return getRuleContexts(Positive_numberContext.class);
		}
		public Positive_numberContext positive_number(int i) {
			return getRuleContext(Positive_numberContext.class,i);
		}
		public TypeFieldNameBitContext(FieldNameContext ctx) { copyFrom(ctx); }
	}
	public static class TypeFieldNameContext extends FieldNameContext {
		public Token name;
		public Positive_numberContext offset;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public Positive_numberContext positive_number() {
			return getRuleContext(Positive_numberContext.class,0);
		}
		public TypeFieldNameContext(FieldNameContext ctx) { copyFrom(ctx); }
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fieldName);
		try {
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new TypeFieldNameContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				((TypeFieldNameContext)_localctx).name = match(ID);
				setState(197);
				match(T__12);
				setState(198);
				((TypeFieldNameContext)_localctx).offset = positive_number();
				setState(199);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new TypeFieldNameBitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				((TypeFieldNameBitContext)_localctx).name = match(ID);
				setState(202);
				match(T__12);
				setState(203);
				((TypeFieldNameBitContext)_localctx).offset = positive_number();
				setState(204);
				match(T__3);
				setState(205);
				((TypeFieldNameBitContext)_localctx).startBit = positive_number();
				setState(206);
				match(T__6);
				setState(207);
				((TypeFieldNameBitContext)_localctx).stopBit = positive_number();
				setState(208);
				match(T__8);
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

	public static class FieldTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public FieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldType; }
	}

	public final FieldTypeContext fieldType() throws RecognitionException {
		FieldTypeContext _localctx = new FieldTypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_fieldType);
		try {
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				referenceType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(214);
				arrayType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215);
				pointerType();
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

	public static class DeclConstantContext extends ParserRuleContext {
		public Token name;
		public ConstantTypeContext constantType() {
			return getRuleContext(ConstantTypeContext.class,0);
		}
		public ConstantValueContext constantValue() {
			return getRuleContext(ConstantValueContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public DeclConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declConstant; }
	}

	public final DeclConstantContext declConstant() throws RecognitionException {
		DeclConstantContext _localctx = new DeclConstantContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_declConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			((DeclConstantContext)_localctx).name = match(ID);
			setState(219);
			match(T__3);
			setState(220);
			constantType();
			setState(221);
			match(T__1);
			setState(222);
			constantValue();
			setState(223);
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

	public static class ConstantTypeContext extends ParserRuleContext {
		public ConstantTypeNumericContext constantTypeNumeric() {
			return getRuleContext(ConstantTypeNumericContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public ConstantTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantType; }
	}

	public final ConstantTypeContext constantType() throws RecognitionException {
		ConstantTypeContext _localctx = new ConstantTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_constantType);
		try {
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CARDINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				constantTypeNumeric();
				}
				break;
			case LONG:
			case POINTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				pointerType();
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

	public static class ConstantTypeNumericContext extends ParserRuleContext {
		public ConstantTypeNumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantTypeNumeric; }
	 
		public ConstantTypeNumericContext() { }
		public void copyFrom(ConstantTypeNumericContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConstantTypeNumericCARDINALContext extends ConstantTypeNumericContext {
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public ConstantTypeNumericCARDINALContext(ConstantTypeNumericContext ctx) { copyFrom(ctx); }
	}

	public final ConstantTypeNumericContext constantTypeNumeric() throws RecognitionException {
		ConstantTypeNumericContext _localctx = new ConstantTypeNumericContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constantTypeNumeric);
		try {
			_localctx = new ConstantTypeNumericCARDINALContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(CARDINAL);
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

	public static class ConstantValueContext extends ParserRuleContext {
		public Token ID;
		public List<Token> name = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(SymbolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SymbolParser.ID, i);
		}
		public ConstantValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantValue; }
	}

	public final ConstantValueContext constantValue() throws RecognitionException {
		ConstantValueContext _localctx = new ConstantValueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_constantValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			((ConstantValueContext)_localctx).ID = match(ID);
			((ConstantValueContext)_localctx).name.add(((ConstantValueContext)_localctx).ID);
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(232);
				match(T__2);
				setState(233);
				((ConstantValueContext)_localctx).ID = match(ID);
				((ConstantValueContext)_localctx).name.add(((ConstantValueContext)_localctx).ID);
				}
				}
				setState(238);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u00f2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\5\2=\n\2\3\2\3\2\5\2"+
		"A\n\2\3\3\3\3\3\4\3\4\5\4G\n\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\b\6\bW\n\b\r\b\16\bX\3\t\3\t\5\t]\n\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13m\n\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fz\n\f\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\5\16\u0085\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0097\n\20\3\21\3\21\3\21"+
		"\5\21\u009c\n\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\7\23\u00a5\n\23\f"+
		"\23\16\23\u00a8\13\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\5\25\u00b9\n\25\3\26\3\26\3\26\7\26\u00be\n"+
		"\26\f\26\16\26\u00c1\13\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00d5\n\30\3\31\3\31"+
		"\3\31\3\31\5\31\u00db\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\5\33\u00e6\n\33\3\34\3\34\3\35\3\35\3\35\7\35\u00ed\n\35\f\35\16\35\u00f0"+
		"\13\35\3\35\2\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668\2\4\3\2(*\3\2\n\13\2\u00f5\2@\3\2\2\2\4B\3\2\2\2\6F\3\2\2\2"+
		"\bH\3\2\2\2\nM\3\2\2\2\fQ\3\2\2\2\16V\3\2\2\2\20\\\3\2\2\2\22^\3\2\2\2"+
		"\24l\3\2\2\2\26y\3\2\2\2\30{\3\2\2\2\32\u0084\3\2\2\2\34\u0086\3\2\2\2"+
		"\36\u0096\3\2\2\2 \u009b\3\2\2\2\"\u009d\3\2\2\2$\u00a1\3\2\2\2&\u00a9"+
		"\3\2\2\2(\u00b8\3\2\2\2*\u00ba\3\2\2\2,\u00c2\3\2\2\2.\u00d4\3\2\2\2\60"+
		"\u00da\3\2\2\2\62\u00dc\3\2\2\2\64\u00e5\3\2\2\2\66\u00e7\3\2\2\28\u00e9"+
		"\3\2\2\2:A\7(\2\2;=\7\3\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>A\7)\2\2?A\7"+
		"*\2\2@:\3\2\2\2@<\3\2\2\2@?\3\2\2\2A\3\3\2\2\2BC\t\2\2\2C\5\3\2\2\2DG"+
		"\5\2\2\2EG\7\'\2\2FD\3\2\2\2FE\3\2\2\2G\7\3\2\2\2HI\5\n\6\2IJ\7\4\2\2"+
		"JK\5\f\7\2KL\7\5\2\2L\t\3\2\2\2MN\7\'\2\2NO\7\6\2\2OP\7 \2\2P\13\3\2\2"+
		"\2QR\7\21\2\2RS\5\16\b\2ST\7\24\2\2T\r\3\2\2\2UW\5\20\t\2VU\3\2\2\2WX"+
		"\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\17\3\2\2\2Z]\5\22\n\2[]\5\62\32\2\\Z\3\2"+
		"\2\2\\[\3\2\2\2]\21\3\2\2\2^_\7\'\2\2_`\7\6\2\2`a\7\"\2\2ab\7\4\2\2bc"+
		"\5\24\13\2cd\7\7\2\2d\23\3\2\2\2em\5\26\f\2fm\5\30\r\2gm\5\32\16\2hm\5"+
		"\34\17\2im\5\"\22\2jm\5\36\20\2km\5(\25\2le\3\2\2\2lf\3\2\2\2lg\3\2\2"+
		"\2lh\3\2\2\2li\3\2\2\2lj\3\2\2\2lk\3\2\2\2m\25\3\2\2\2nz\7\22\2\2oz\7"+
		"\27\2\2pz\7\23\2\2qr\7\30\2\2rz\7\23\2\2sz\7#\2\2tu\7\30\2\2uz\7#\2\2"+
		"vz\7\34\2\2wx\7\30\2\2xz\7\34\2\2yn\3\2\2\2yo\3\2\2\2yp\3\2\2\2yq\3\2"+
		"\2\2ys\3\2\2\2yt\3\2\2\2yv\3\2\2\2yw\3\2\2\2z\27\3\2\2\2{|\7\'\2\2|\31"+
		"\3\2\2\2}~\7\34\2\2~\177\7!\2\2\177\u0085\5\30\r\2\u0080\u0081\7\30\2"+
		"\2\u0081\u0082\7\34\2\2\u0082\u0083\7!\2\2\u0083\u0085\5\30\r\2\u0084"+
		"}\3\2\2\2\u0084\u0080\3\2\2\2\u0085\33\3\2\2\2\u0086\u0087\7\b\2\2\u0087"+
		"\u0088\5\6\4\2\u0088\u0089\7\t\2\2\u0089\u008a\5\6\4\2\u008a\u008b\t\3"+
		"\2\2\u008b\35\3\2\2\2\u008c\u008d\7\20\2\2\u008d\u008e\5\30\r\2\u008e"+
		"\u008f\7\31\2\2\u008f\u0090\5 \21\2\u0090\u0097\3\2\2\2\u0091\u0092\7"+
		"\20\2\2\u0092\u0093\5\34\17\2\u0093\u0094\7\31\2\2\u0094\u0095\5 \21\2"+
		"\u0095\u0097\3\2\2\2\u0096\u008c\3\2\2\2\u0096\u0091\3\2\2\2\u0097\37"+
		"\3\2\2\2\u0098\u009c\5\26\f\2\u0099\u009c\5\32\16\2\u009a\u009c\5\30\r"+
		"\2\u009b\u0098\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009a\3\2\2\2\u009c!"+
		"\3\2\2\2\u009d\u009e\7\f\2\2\u009e\u009f\5$\23\2\u009f\u00a0\7\r\2\2\u00a0"+
		"#\3\2\2\2\u00a1\u00a6\5&\24\2\u00a2\u00a3\7\16\2\2\u00a3\u00a5\5&\24\2"+
		"\u00a4\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7%\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7\'\2\2\u00aa"+
		"\u00ab\7\17\2\2\u00ab\u00ac\5\4\3\2\u00ac\u00ad\7\13\2\2\u00ad\'\3\2\2"+
		"\2\u00ae\u00af\7\35\2\2\u00af\u00b0\7\b\2\2\u00b0\u00b1\5*\26\2\u00b1"+
		"\u00b2\7\n\2\2\u00b2\u00b9\3\2\2\2\u00b3\u00b4\7\36\2\2\u00b4\u00b5\7"+
		"\b\2\2\u00b5\u00b6\5*\26\2\u00b6\u00b7\7\n\2\2\u00b7\u00b9\3\2\2\2\u00b8"+
		"\u00ae\3\2\2\2\u00b8\u00b3\3\2\2\2\u00b9)\3\2\2\2\u00ba\u00bf\5,\27\2"+
		"\u00bb\u00bc\7\16\2\2\u00bc\u00be\5,\27\2\u00bd\u00bb\3\2\2\2\u00be\u00c1"+
		"\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0+\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c2\u00c3\5.\30\2\u00c3\u00c4\7\6\2\2\u00c4\u00c5\5\60"+
		"\31\2\u00c5-\3\2\2\2\u00c6\u00c7\7\'\2\2\u00c7\u00c8\7\17\2\2\u00c8\u00c9"+
		"\5\4\3\2\u00c9\u00ca\7\13\2\2\u00ca\u00d5\3\2\2\2\u00cb\u00cc\7\'\2\2"+
		"\u00cc\u00cd\7\17\2\2\u00cd\u00ce\5\4\3\2\u00ce\u00cf\7\6\2\2\u00cf\u00d0"+
		"\5\4\3\2\u00d0\u00d1\7\t\2\2\u00d1\u00d2\5\4\3\2\u00d2\u00d3\7\13\2\2"+
		"\u00d3\u00d5\3\2\2\2\u00d4\u00c6\3\2\2\2\u00d4\u00cb\3\2\2\2\u00d5/\3"+
		"\2\2\2\u00d6\u00db\5\26\f\2\u00d7\u00db\5\30\r\2\u00d8\u00db\5\36\20\2"+
		"\u00d9\u00db\5\32\16\2\u00da\u00d6\3\2\2\2\u00da\u00d7\3\2\2\2\u00da\u00d8"+
		"\3\2\2\2\u00da\u00d9\3\2\2\2\u00db\61\3\2\2\2\u00dc\u00dd\7\'\2\2\u00dd"+
		"\u00de\7\6\2\2\u00de\u00df\5\64\33\2\u00df\u00e0\7\4\2\2\u00e0\u00e1\5"+
		"8\35\2\u00e1\u00e2\7\7\2\2\u00e2\63\3\2\2\2\u00e3\u00e6\5\66\34\2\u00e4"+
		"\u00e6\5\32\16\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\65\3\2"+
		"\2\2\u00e7\u00e8\7\23\2\2\u00e8\67\3\2\2\2\u00e9\u00ee\7\'\2\2\u00ea\u00eb"+
		"\7\5\2\2\u00eb\u00ed\7\'\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef9\3\2\2\2\u00f0\u00ee\3\2\2\2"+
		"\23<@FX\\ly\u0084\u0096\u009b\u00a6\u00b8\u00bf\u00d4\u00da\u00e5\u00ee";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}