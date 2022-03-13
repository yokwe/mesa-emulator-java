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
		OVERLAID=24, PACKED=25, POINTER=26, RECORD=27, SELECT=28, SYMBOL=29, TO=30, 
		TYPE=31, UNSPECIFIED=32, WORD=33, TRUE=34, FALSE=35, ID=36, NUMBER_8=37, 
		NUMBER_10=38, NUMBER_16=39, COMMENT_LINE=40, SPACE=41;
	public static final int
		RULE_number = 0, RULE_positive_number = 1, RULE_constant = 2, RULE_symbol = 3, 
		RULE_header = 4, RULE_body = 5, RULE_declList = 6, RULE_decl = 7, RULE_declType = 8, 
		RULE_typeType = 9, RULE_simpleType = 10, RULE_referenceType = 11, RULE_pointerType = 12, 
		RULE_pointerRefType = 13, RULE_subrangeType = 14, RULE_arrayType = 15, 
		RULE_arrayElementType = 16, RULE_enumType = 17, RULE_enumElementList = 18, 
		RULE_eumElement = 19, RULE_recordType = 20, RULE_recordFieldList = 21, 
		RULE_recordField = 22, RULE_fieldName = 23, RULE_fieldType = 24, RULE_declConstant = 25, 
		RULE_constantType = 26, RULE_constantTypeNumeric = 27, RULE_constantValue = 28, 
		RULE_constantValueQName = 29, RULE_constantValueConstant = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"number", "positive_number", "constant", "symbol", "header", "body", 
			"declList", "decl", "declType", "typeType", "simpleType", "referenceType", 
			"pointerType", "pointerRefType", "subrangeType", "arrayType", "arrayElementType", 
			"enumType", "enumElementList", "eumElement", "recordType", "recordFieldList", 
			"recordField", "fieldName", "fieldType", "declConstant", "constantType", 
			"constantTypeNumeric", "constantValue", "constantValueQName", "constantValueConstant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'='", "'.'", "':'", "';'", "'['", "'..'", "']'", "')'", 
			"'{'", "'}'", "','", "'('", "'ARRAY'", "'BEGIN'", "'BOOLEAN'", "'CARDINAL'", 
			"'END'", "'ENDCASE'", "'FROM'", "'INTEGER'", "'LONG'", "'OF'", "'OVERLAID'", 
			"'PACKED'", "'POINTER'", "'RECORD'", "'SELECT'", "'SYMBOL'", "'TO'", 
			"'TYPE'", "'UNSPECIFIED'", "'WORD'", "'TRUE'", "'FALSE'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ARRAY", "BEGIN", "BOOLEAN", "CARDINAL", "END", "ENDCASE", 
			"FROM", "INTEGER", "LONG", "OF", "OVERLAID", "PACKED", "POINTER", "RECORD", 
			"SELECT", "SYMBOL", "TO", "TYPE", "UNSPECIFIED", "WORD", "TRUE", "FALSE", 
			"ID", "NUMBER_8", "NUMBER_10", "NUMBER_16", "COMMENT_LINE", "SPACE"
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER_8:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				match(NUMBER_8);
				}
				break;
			case T__0:
			case NUMBER_10:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(63);
					match(T__0);
					}
				}

				setState(66);
				match(NUMBER_10);
				}
				break;
			case NUMBER_16:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
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
			setState(70);
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
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case NUMBER_8:
			case NUMBER_10:
			case NUMBER_16:
				_localctx = new ConstNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				number();
				}
				break;
			case ID:
				_localctx = new ConstNameContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
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
			setState(76);
			header();
			setState(77);
			match(T__1);
			setState(78);
			body();
			setState(79);
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
			setState(81);
			((HeaderContext)_localctx).name = match(ID);
			setState(82);
			match(T__3);
			setState(83);
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
			setState(85);
			match(BEGIN);
			setState(86);
			declList();
			setState(87);
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
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89);
				((DeclListContext)_localctx).decl = decl();
				((DeclListContext)_localctx).elements.add(((DeclListContext)_localctx).decl);
				}
				}
				setState(92); 
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
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				declType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
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
			setState(98);
			((DeclTypeContext)_localctx).name = match(ID);
			setState(99);
			match(T__3);
			setState(100);
			match(TYPE);
			setState(101);
			match(T__1);
			setState(102);
			typeType();
			setState(103);
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
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				referenceType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				pointerType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				subrangeType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(109);
				enumType();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(110);
				arrayType();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(111);
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
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(BOOLEAN);
				}
				break;
			case 2:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(INTEGER);
				}
				break;
			case 3:
				_localctx = new TypeCardinalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				match(CARDINAL);
				}
				break;
			case 4:
				_localctx = new TypeLongCardinalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				match(LONG);
				setState(118);
				match(CARDINAL);
				}
				break;
			case 5:
				_localctx = new TypeUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(119);
				match(UNSPECIFIED);
				}
				break;
			case 6:
				_localctx = new TypeLongUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(120);
				match(LONG);
				setState(121);
				match(UNSPECIFIED);
				}
				break;
			case 7:
				_localctx = new TypePointerContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(122);
				match(POINTER);
				}
				break;
			case 8:
				_localctx = new TypeLongPointerContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(123);
				match(LONG);
				setState(124);
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
		public ReferenceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceType; }
	 
		public ReferenceTypeContext() { }
		public void copyFrom(ReferenceTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeReferenceQNameContext extends ReferenceTypeContext {
		public Token module;
		public Token name;
		public List<TerminalNode> ID() { return getTokens(SymbolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SymbolParser.ID, i);
		}
		public TypeReferenceQNameContext(ReferenceTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeReferenceNameContext extends ReferenceTypeContext {
		public Token name;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeReferenceNameContext(ReferenceTypeContext ctx) { copyFrom(ctx); }
	}

	public final ReferenceTypeContext referenceType() throws RecognitionException {
		ReferenceTypeContext _localctx = new ReferenceTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_referenceType);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new TypeReferenceNameContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				((TypeReferenceNameContext)_localctx).name = match(ID);
				}
				break;
			case 2:
				_localctx = new TypeReferenceQNameContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				((TypeReferenceQNameContext)_localctx).module = match(ID);
				setState(129);
				matchWildcard();
				setState(130);
				((TypeReferenceQNameContext)_localctx).name = match(ID);
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
		public PointerRefTypeContext pointerRefType() {
			return getRuleContext(PointerRefTypeContext.class,0);
		}
		public TypePointerShortContext(PointerTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypePointerLongContext extends PointerTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TerminalNode TO() { return getToken(SymbolParser.TO, 0); }
		public PointerRefTypeContext pointerRefType() {
			return getRuleContext(PointerRefTypeContext.class,0);
		}
		public TypePointerLongContext(PointerTypeContext ctx) { copyFrom(ctx); }
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pointerType);
		try {
			setState(140);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case POINTER:
				_localctx = new TypePointerShortContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(POINTER);
				setState(134);
				match(TO);
				setState(135);
				pointerRefType();
				}
				break;
			case LONG:
				_localctx = new TypePointerLongContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(LONG);
				setState(137);
				match(POINTER);
				setState(138);
				match(TO);
				setState(139);
				pointerRefType();
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

	public static class PointerRefTypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public PointerRefTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerRefType; }
	}

	public final PointerRefTypeContext pointerRefType() throws RecognitionException {
		PointerRefTypeContext _localctx = new PointerRefTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pointerRefType);
		try {
			setState(144);
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
				setState(142);
				simpleType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
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
		enterRule(_localctx, 28, RULE_subrangeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__5);
			setState(147);
			((SubrangeTypeContext)_localctx).minValue = constant();
			setState(148);
			match(T__6);
			setState(149);
			((SubrangeTypeContext)_localctx).maxValue = constant();
			setState(150);
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
		enterRule(_localctx, 30, RULE_arrayType);
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new TypeArrayReferenceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(ARRAY);
				setState(153);
				referenceType();
				setState(154);
				match(OF);
				setState(155);
				arrayElementType();
				}
				break;
			case 2:
				_localctx = new TypeArraySubrangeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				match(ARRAY);
				setState(158);
				subrangeType();
				setState(159);
				match(OF);
				setState(160);
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
		enterRule(_localctx, 32, RULE_arrayElementType);
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				pointerType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
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
		enterRule(_localctx, 34, RULE_enumType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__9);
			setState(170);
			enumElementList();
			setState(171);
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
		enterRule(_localctx, 36, RULE_enumElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			((EnumElementListContext)_localctx).eumElement = eumElement();
			((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(174);
				match(T__11);
				setState(175);
				((EnumElementListContext)_localctx).eumElement = eumElement();
				((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
				}
				}
				setState(180);
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
		enterRule(_localctx, 38, RULE_eumElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			((EumElementContext)_localctx).name = match(ID);
			setState(182);
			match(T__12);
			setState(183);
			((EumElementContext)_localctx).value = positive_number();
			setState(184);
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
		enterRule(_localctx, 40, RULE_recordType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(RECORD);
			setState(187);
			match(T__5);
			setState(188);
			recordFieldList();
			setState(189);
			match(T__7);
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
		enterRule(_localctx, 42, RULE_recordFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			((RecordFieldListContext)_localctx).recordField = recordField();
			((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(192);
				match(T__11);
				setState(193);
				((RecordFieldListContext)_localctx).recordField = recordField();
				((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
				}
				}
				setState(198);
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
		enterRule(_localctx, 44, RULE_recordField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			fieldName();
			setState(200);
			match(T__3);
			setState(201);
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
		enterRule(_localctx, 46, RULE_fieldName);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new TypeFieldNameContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				((TypeFieldNameContext)_localctx).name = match(ID);
				setState(204);
				match(T__12);
				setState(205);
				((TypeFieldNameContext)_localctx).offset = positive_number();
				setState(206);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new TypeFieldNameBitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				((TypeFieldNameBitContext)_localctx).name = match(ID);
				setState(209);
				match(T__12);
				setState(210);
				((TypeFieldNameBitContext)_localctx).offset = positive_number();
				setState(211);
				match(T__3);
				setState(212);
				((TypeFieldNameBitContext)_localctx).startBit = positive_number();
				setState(213);
				match(T__6);
				setState(214);
				((TypeFieldNameBitContext)_localctx).stopBit = positive_number();
				setState(215);
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
		enterRule(_localctx, 48, RULE_fieldType);
		try {
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				simpleType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				referenceType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				arrayType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(222);
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
		enterRule(_localctx, 50, RULE_declConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			((DeclConstantContext)_localctx).name = match(ID);
			setState(226);
			match(T__3);
			setState(227);
			constantType();
			setState(228);
			match(T__1);
			setState(229);
			constantValue();
			setState(230);
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
		public ReferenceTypeContext referenceType() {
			return getRuleContext(ReferenceTypeContext.class,0);
		}
		public ConstantTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantType; }
	}

	public final ConstantTypeContext constantType() throws RecognitionException {
		ConstantTypeContext _localctx = new ConstantTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constantType);
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				constantTypeNumeric();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				pointerType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(234);
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
	public static class ConstantTypeNumericCardinalContext extends ConstantTypeNumericContext {
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public ConstantTypeNumericCardinalContext(ConstantTypeNumericContext ctx) { copyFrom(ctx); }
	}
	public static class ConstantTypeNumericLongCardinalContext extends ConstantTypeNumericContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public ConstantTypeNumericLongCardinalContext(ConstantTypeNumericContext ctx) { copyFrom(ctx); }
	}

	public final ConstantTypeNumericContext constantTypeNumeric() throws RecognitionException {
		ConstantTypeNumericContext _localctx = new ConstantTypeNumericContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_constantTypeNumeric);
		try {
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CARDINAL:
				_localctx = new ConstantTypeNumericCardinalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(CARDINAL);
				}
				break;
			case LONG:
				_localctx = new ConstantTypeNumericLongCardinalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(238);
				match(LONG);
				setState(239);
				match(CARDINAL);
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

	public static class ConstantValueContext extends ParserRuleContext {
		public ConstantValueQNameContext constantValueQName() {
			return getRuleContext(ConstantValueQNameContext.class,0);
		}
		public ConstantValueConstantContext constantValueConstant() {
			return getRuleContext(ConstantValueConstantContext.class,0);
		}
		public ConstantValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantValue; }
	}

	public final ConstantValueContext constantValue() throws RecognitionException {
		ConstantValueContext _localctx = new ConstantValueContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_constantValue);
		try {
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				constantValueQName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				constantValueConstant();
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

	public static class ConstantValueQNameContext extends ParserRuleContext {
		public Token ID;
		public List<Token> name = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(SymbolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SymbolParser.ID, i);
		}
		public ConstantValueQNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantValueQName; }
	}

	public final ConstantValueQNameContext constantValueQName() throws RecognitionException {
		ConstantValueQNameContext _localctx = new ConstantValueQNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constantValueQName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			((ConstantValueQNameContext)_localctx).ID = match(ID);
			((ConstantValueQNameContext)_localctx).name.add(((ConstantValueQNameContext)_localctx).ID);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(247);
				match(T__2);
				setState(248);
				((ConstantValueQNameContext)_localctx).ID = match(ID);
				((ConstantValueQNameContext)_localctx).name.add(((ConstantValueQNameContext)_localctx).ID);
				}
				}
				setState(253);
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

	public static class ConstantValueConstantContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantValueConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantValueConstant; }
	}

	public final ConstantValueConstantContext constantValueConstant() throws RecognitionException {
		ConstantValueConstantContext _localctx = new ConstantValueConstantContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_constantValueConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			constant();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u0103\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\5\2C\n\2\3\2\3\2\5\2G\n\2\3\3\3\3\3\4\3\4\5\4M\n\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\6\b]\n\b\r\b\16\b^\3\t\3\t"+
		"\5\tc\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\5\13s\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0080\n"+
		"\f\3\r\3\r\3\r\3\r\5\r\u0086\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5"+
		"\16\u008f\n\16\3\17\3\17\5\17\u0093\n\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00a5\n\21\3\22"+
		"\3\22\3\22\5\22\u00aa\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\7\24\u00b3"+
		"\n\24\f\24\16\24\u00b6\13\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\7\27\u00c5\n\27\f\27\16\27\u00c8\13\27\3\30\3"+
		"\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\5\31\u00dc\n\31\3\32\3\32\3\32\3\32\5\32\u00e2\n\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\5\34\u00ee\n\34\3\35\3\35"+
		"\3\35\5\35\u00f3\n\35\3\36\3\36\5\36\u00f7\n\36\3\37\3\37\3\37\7\37\u00fc"+
		"\n\37\f\37\16\37\u00ff\13\37\3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\4\3\2\')\3\2\n\13\2\u0107\2F\3"+
		"\2\2\2\4H\3\2\2\2\6L\3\2\2\2\bN\3\2\2\2\nS\3\2\2\2\fW\3\2\2\2\16\\\3\2"+
		"\2\2\20b\3\2\2\2\22d\3\2\2\2\24r\3\2\2\2\26\177\3\2\2\2\30\u0085\3\2\2"+
		"\2\32\u008e\3\2\2\2\34\u0092\3\2\2\2\36\u0094\3\2\2\2 \u00a4\3\2\2\2\""+
		"\u00a9\3\2\2\2$\u00ab\3\2\2\2&\u00af\3\2\2\2(\u00b7\3\2\2\2*\u00bc\3\2"+
		"\2\2,\u00c1\3\2\2\2.\u00c9\3\2\2\2\60\u00db\3\2\2\2\62\u00e1\3\2\2\2\64"+
		"\u00e3\3\2\2\2\66\u00ed\3\2\2\28\u00f2\3\2\2\2:\u00f6\3\2\2\2<\u00f8\3"+
		"\2\2\2>\u0100\3\2\2\2@G\7\'\2\2AC\7\3\2\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2"+
		"\2DG\7(\2\2EG\7)\2\2F@\3\2\2\2FB\3\2\2\2FE\3\2\2\2G\3\3\2\2\2HI\t\2\2"+
		"\2I\5\3\2\2\2JM\5\2\2\2KM\7&\2\2LJ\3\2\2\2LK\3\2\2\2M\7\3\2\2\2NO\5\n"+
		"\6\2OP\7\4\2\2PQ\5\f\7\2QR\7\5\2\2R\t\3\2\2\2ST\7&\2\2TU\7\6\2\2UV\7\37"+
		"\2\2V\13\3\2\2\2WX\7\21\2\2XY\5\16\b\2YZ\7\24\2\2Z\r\3\2\2\2[]\5\20\t"+
		"\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\17\3\2\2\2`c\5\22\n\2ac"+
		"\5\64\33\2b`\3\2\2\2ba\3\2\2\2c\21\3\2\2\2de\7&\2\2ef\7\6\2\2fg\7!\2\2"+
		"gh\7\4\2\2hi\5\24\13\2ij\7\7\2\2j\23\3\2\2\2ks\5\26\f\2ls\5\30\r\2ms\5"+
		"\32\16\2ns\5\36\20\2os\5$\23\2ps\5 \21\2qs\5*\26\2rk\3\2\2\2rl\3\2\2\2"+
		"rm\3\2\2\2rn\3\2\2\2ro\3\2\2\2rp\3\2\2\2rq\3\2\2\2s\25\3\2\2\2t\u0080"+
		"\7\22\2\2u\u0080\7\27\2\2v\u0080\7\23\2\2wx\7\30\2\2x\u0080\7\23\2\2y"+
		"\u0080\7\"\2\2z{\7\30\2\2{\u0080\7\"\2\2|\u0080\7\34\2\2}~\7\30\2\2~\u0080"+
		"\7\34\2\2\177t\3\2\2\2\177u\3\2\2\2\177v\3\2\2\2\177w\3\2\2\2\177y\3\2"+
		"\2\2\177z\3\2\2\2\177|\3\2\2\2\177}\3\2\2\2\u0080\27\3\2\2\2\u0081\u0086"+
		"\7&\2\2\u0082\u0083\7&\2\2\u0083\u0084\13\2\2\2\u0084\u0086\7&\2\2\u0085"+
		"\u0081\3\2\2\2\u0085\u0082\3\2\2\2\u0086\31\3\2\2\2\u0087\u0088\7\34\2"+
		"\2\u0088\u0089\7 \2\2\u0089\u008f\5\34\17\2\u008a\u008b\7\30\2\2\u008b"+
		"\u008c\7\34\2\2\u008c\u008d\7 \2\2\u008d\u008f\5\34\17\2\u008e\u0087\3"+
		"\2\2\2\u008e\u008a\3\2\2\2\u008f\33\3\2\2\2\u0090\u0093\5\26\f\2\u0091"+
		"\u0093\5\30\r\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\35\3\2\2"+
		"\2\u0094\u0095\7\b\2\2\u0095\u0096\5\6\4\2\u0096\u0097\7\t\2\2\u0097\u0098"+
		"\5\6\4\2\u0098\u0099\t\3\2\2\u0099\37\3\2\2\2\u009a\u009b\7\20\2\2\u009b"+
		"\u009c\5\30\r\2\u009c\u009d\7\31\2\2\u009d\u009e\5\"\22\2\u009e\u00a5"+
		"\3\2\2\2\u009f\u00a0\7\20\2\2\u00a0\u00a1\5\36\20\2\u00a1\u00a2\7\31\2"+
		"\2\u00a2\u00a3\5\"\22\2\u00a3\u00a5\3\2\2\2\u00a4\u009a\3\2\2\2\u00a4"+
		"\u009f\3\2\2\2\u00a5!\3\2\2\2\u00a6\u00aa\5\26\f\2\u00a7\u00aa\5\32\16"+
		"\2\u00a8\u00aa\5\30\r\2\u00a9\u00a6\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00aa#\3\2\2\2\u00ab\u00ac\7\f\2\2\u00ac\u00ad\5&\24\2"+
		"\u00ad\u00ae\7\r\2\2\u00ae%\3\2\2\2\u00af\u00b4\5(\25\2\u00b0\u00b1\7"+
		"\16\2\2\u00b1\u00b3\5(\25\2\u00b2\u00b0\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\'\3\2\2\2\u00b6\u00b4\3\2\2\2"+
		"\u00b7\u00b8\7&\2\2\u00b8\u00b9\7\17\2\2\u00b9\u00ba\5\4\3\2\u00ba\u00bb"+
		"\7\13\2\2\u00bb)\3\2\2\2\u00bc\u00bd\7\35\2\2\u00bd\u00be\7\b\2\2\u00be"+
		"\u00bf\5,\27\2\u00bf\u00c0\7\n\2\2\u00c0+\3\2\2\2\u00c1\u00c6\5.\30\2"+
		"\u00c2\u00c3\7\16\2\2\u00c3\u00c5\5.\30\2\u00c4\u00c2\3\2\2\2\u00c5\u00c8"+
		"\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7-\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00ca\5\60\31\2\u00ca\u00cb\7\6\2\2\u00cb\u00cc\5"+
		"\62\32\2\u00cc/\3\2\2\2\u00cd\u00ce\7&\2\2\u00ce\u00cf\7\17\2\2\u00cf"+
		"\u00d0\5\4\3\2\u00d0\u00d1\7\13\2\2\u00d1\u00dc\3\2\2\2\u00d2\u00d3\7"+
		"&\2\2\u00d3\u00d4\7\17\2\2\u00d4\u00d5\5\4\3\2\u00d5\u00d6\7\6\2\2\u00d6"+
		"\u00d7\5\4\3\2\u00d7\u00d8\7\t\2\2\u00d8\u00d9\5\4\3\2\u00d9\u00da\7\13"+
		"\2\2\u00da\u00dc\3\2\2\2\u00db\u00cd\3\2\2\2\u00db\u00d2\3\2\2\2\u00dc"+
		"\61\3\2\2\2\u00dd\u00e2\5\26\f\2\u00de\u00e2\5\30\r\2\u00df\u00e2\5 \21"+
		"\2\u00e0\u00e2\5\32\16\2\u00e1\u00dd\3\2\2\2\u00e1\u00de\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\63\3\2\2\2\u00e3\u00e4\7&\2\2"+
		"\u00e4\u00e5\7\6\2\2\u00e5\u00e6\5\66\34\2\u00e6\u00e7\7\4\2\2\u00e7\u00e8"+
		"\5:\36\2\u00e8\u00e9\7\7\2\2\u00e9\65\3\2\2\2\u00ea\u00ee\58\35\2\u00eb"+
		"\u00ee\5\32\16\2\u00ec\u00ee\5\30\r\2\u00ed\u00ea\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee\67\3\2\2\2\u00ef\u00f3\7\23\2\2\u00f0"+
		"\u00f1\7\30\2\2\u00f1\u00f3\7\23\2\2\u00f2\u00ef\3\2\2\2\u00f2\u00f0\3"+
		"\2\2\2\u00f39\3\2\2\2\u00f4\u00f7\5<\37\2\u00f5\u00f7\5> \2\u00f6\u00f4"+
		"\3\2\2\2\u00f6\u00f5\3\2\2\2\u00f7;\3\2\2\2\u00f8\u00fd\7&\2\2\u00f9\u00fa"+
		"\7\5\2\2\u00fa\u00fc\7&\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe=\3\2\2\2\u00ff\u00fd\3\2\2\2"+
		"\u0100\u0101\5\6\4\2\u0101?\3\2\2\2\26BFL^br\177\u0085\u008e\u0092\u00a4"+
		"\u00a9\u00b4\u00c6\u00db\u00e1\u00ed\u00f2\u00f6\u00fd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}