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
		RULE_number = 0, RULE_positive_number = 1, RULE_constant = 2, RULE_symbol = 3, 
		RULE_header = 4, RULE_body = 5, RULE_declList = 6, RULE_decl = 7, RULE_declType = 8, 
		RULE_typeType = 9, RULE_arrayType = 10, RULE_arrayTypeElement = 11, RULE_enumType = 12, 
		RULE_enumElementList = 13, RULE_eumElement = 14, RULE_subrangeType = 15, 
		RULE_recordType = 16, RULE_recordFieldList = 17, RULE_recordField = 18, 
		RULE_fieldName = 19, RULE_fieldType = 20, RULE_select = 21, RULE_selectCaseList = 22, 
		RULE_selectCase = 23, RULE_selectCaseSelector = 24, RULE_referenceType = 25, 
		RULE_predefinedType = 26, RULE_declConst = 27, RULE_constType = 28, RULE_constValue = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"number", "positive_number", "constant", "symbol", "header", "body", 
			"declList", "decl", "declType", "typeType", "arrayType", "arrayTypeElement", 
			"enumType", "enumElementList", "eumElement", "subrangeType", "recordType", 
			"recordFieldList", "recordField", "fieldName", "fieldType", "select", 
			"selectCaseList", "selectCase", "selectCaseSelector", "referenceType", 
			"predefinedType", "declConst", "constType", "constValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'='", "'.'", "':'", "';'", "'['", "'..'", "']'", "')'", 
			"'{'", "'}'", "','", "'('", "'*'", "'=>'", "'ARRAY'", "'BEGIN'", "'BOOLEAN'", 
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER_8:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				match(NUMBER_8);
				}
				break;
			case T__0:
			case NUMBER_10:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(61);
					match(T__0);
					}
				}

				setState(64);
				match(NUMBER_10);
				}
				break;
			case NUMBER_16:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
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
			setState(68);
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
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case NUMBER_8:
			case NUMBER_10:
			case NUMBER_16:
				_localctx = new ConstNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				number();
				}
				break;
			case ID:
				_localctx = new ConstRefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
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
			setState(74);
			header();
			setState(75);
			match(T__1);
			setState(76);
			body();
			setState(77);
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
			setState(79);
			((HeaderContext)_localctx).name = match(ID);
			setState(80);
			match(T__3);
			setState(81);
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
			setState(83);
			match(BEGIN);
			setState(84);
			declList();
			setState(85);
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
			setState(88); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(87);
				((DeclListContext)_localctx).decl = decl();
				((DeclListContext)_localctx).elements.add(((DeclListContext)_localctx).decl);
				}
				}
				setState(90); 
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
		enterRule(_localctx, 14, RULE_decl);
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				declType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
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
		enterRule(_localctx, 16, RULE_declType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			((DeclTypeContext)_localctx).name = match(ID);
			setState(97);
			match(T__3);
			setState(98);
			match(TYPE);
			setState(99);
			match(T__1);
			setState(100);
			typeType();
			setState(101);
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
		public PredefinedTypeContext predefinedType() {
			return getRuleContext(PredefinedTypeContext.class,0);
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
		enterRule(_localctx, 18, RULE_typeType);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				arrayType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				enumType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				subrangeType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(106);
				recordType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(107);
				predefinedType();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(108);
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
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
	 
		public ArrayTypeContext() { }
		public void copyFrom(ArrayTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeArrayTypeContext extends ArrayTypeContext {
		public Token indexName;
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayTypeElementContext arrayTypeElement() {
			return getRuleContext(ArrayTypeElementContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeArrayTypeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeArrayRangeContext extends ArrayTypeContext {
		public Token indexName;
		public ConstantContext startIndex;
		public ConstantContext stopIndex;
		public Token closeChar;
		public TerminalNode ARRAY() { return getToken(SymbolParser.ARRAY, 0); }
		public TerminalNode OF() { return getToken(SymbolParser.OF, 0); }
		public ArrayTypeElementContext arrayTypeElement() {
			return getRuleContext(ArrayTypeElementContext.class,0);
		}
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeArrayRangeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arrayType);
		int _la;
		try {
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new TypeArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				match(ARRAY);
				setState(112);
				((TypeArrayTypeContext)_localctx).indexName = match(ID);
				setState(113);
				match(OF);
				setState(114);
				arrayTypeElement();
				}
				break;
			case 2:
				_localctx = new TypeArrayRangeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(ARRAY);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(116);
					((TypeArrayRangeContext)_localctx).indexName = match(ID);
					}
				}

				setState(119);
				match(T__5);
				setState(120);
				((TypeArrayRangeContext)_localctx).startIndex = constant();
				setState(121);
				match(T__6);
				setState(122);
				((TypeArrayRangeContext)_localctx).stopIndex = constant();
				setState(123);
				((TypeArrayRangeContext)_localctx).closeChar = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
					((TypeArrayRangeContext)_localctx).closeChar = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(124);
				match(OF);
				setState(125);
				arrayTypeElement();
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

	public static class ArrayTypeElementContext extends ParserRuleContext {
		public PredefinedTypeContext predefinedType() {
			return getRuleContext(PredefinedTypeContext.class,0);
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
		enterRule(_localctx, 22, RULE_arrayTypeElement);
		try {
			setState(131);
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
				setState(129);
				predefinedType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
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
		enterRule(_localctx, 24, RULE_enumType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__9);
			setState(134);
			enumElementList();
			setState(135);
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
		enterRule(_localctx, 26, RULE_enumElementList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			((EnumElementListContext)_localctx).eumElement = eumElement();
			((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(138);
				match(T__11);
				setState(139);
				((EnumElementListContext)_localctx).eumElement = eumElement();
				((EnumElementListContext)_localctx).elements.add(((EnumElementListContext)_localctx).eumElement);
				}
				}
				setState(144);
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
		enterRule(_localctx, 28, RULE_eumElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			((EumElementContext)_localctx).name = match(ID);
			setState(146);
			match(T__12);
			setState(147);
			((EumElementContext)_localctx).value = positive_number();
			setState(148);
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

	public static class SubrangeTypeContext extends ParserRuleContext {
		public Token baseName;
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
		public SubrangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subrangeType; }
	}

	public final SubrangeTypeContext subrangeType() throws RecognitionException {
		SubrangeTypeContext _localctx = new SubrangeTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_subrangeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(150);
				((SubrangeTypeContext)_localctx).baseName = match(ID);
				}
			}

			setState(153);
			match(T__5);
			setState(154);
			((SubrangeTypeContext)_localctx).startIndex = constant();
			setState(155);
			match(T__6);
			setState(156);
			((SubrangeTypeContext)_localctx).stopIndex = constant();
			setState(157);
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
		enterRule(_localctx, 32, RULE_recordType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(RECORD);
			setState(160);
			match(T__5);
			setState(161);
			recordFieldList();
			setState(162);
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
		enterRule(_localctx, 34, RULE_recordFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((RecordFieldListContext)_localctx).recordField = recordField();
			((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(165);
				match(T__11);
				setState(166);
				((RecordFieldListContext)_localctx).recordField = recordField();
				((RecordFieldListContext)_localctx).elements.add(((RecordFieldListContext)_localctx).recordField);
				}
				}
				setState(171);
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
		enterRule(_localctx, 36, RULE_recordField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			fieldName();
			setState(173);
			match(T__3);
			setState(174);
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
		public Positive_numberContext startPos;
		public Positive_numberContext stopPos;
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
		enterRule(_localctx, 38, RULE_fieldName);
		try {
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new TypeFieldNameContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				((TypeFieldNameContext)_localctx).name = match(ID);
				setState(177);
				match(T__12);
				setState(178);
				((TypeFieldNameContext)_localctx).offset = positive_number();
				setState(179);
				match(T__8);
				}
				break;
			case 2:
				_localctx = new TypeFieldNameBitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				((TypeFieldNameBitContext)_localctx).name = match(ID);
				setState(182);
				match(T__12);
				setState(183);
				((TypeFieldNameBitContext)_localctx).offset = positive_number();
				setState(184);
				match(T__3);
				setState(185);
				((TypeFieldNameBitContext)_localctx).startPos = positive_number();
				setState(186);
				match(T__6);
				setState(187);
				((TypeFieldNameBitContext)_localctx).stopPos = positive_number();
				setState(188);
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
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public PredefinedTypeContext predefinedType() {
			return getRuleContext(PredefinedTypeContext.class,0);
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
		enterRule(_localctx, 40, RULE_fieldType);
		try {
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARRAY:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
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
				setState(193);
				predefinedType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				referenceType();
				}
				break;
			case SELECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
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
	public static class TypeSelectTagTypeContext extends SelectContext {
		public FieldNameContext tagName;
		public Token tagTypeName;
		public TerminalNode SELECT() { return getToken(SymbolParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(SymbolParser.FROM, 0); }
		public SelectCaseListContext selectCaseList() {
			return getRuleContext(SelectCaseListContext.class,0);
		}
		public TerminalNode ENDCASE() { return getToken(SymbolParser.ENDCASE, 0); }
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeSelectTagTypeContext(SelectContext ctx) { copyFrom(ctx); }
	}
	public static class TypeSelectOverlaidTypeContext extends SelectContext {
		public Token tagTypeName;
		public TerminalNode SELECT() { return getToken(SymbolParser.SELECT, 0); }
		public TerminalNode OVERLAID() { return getToken(SymbolParser.OVERLAID, 0); }
		public TerminalNode FROM() { return getToken(SymbolParser.FROM, 0); }
		public SelectCaseListContext selectCaseList() {
			return getRuleContext(SelectCaseListContext.class,0);
		}
		public TerminalNode ENDCASE() { return getToken(SymbolParser.ENDCASE, 0); }
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeSelectOverlaidTypeContext(SelectContext ctx) { copyFrom(ctx); }
	}
	public static class TypeSelectOverlaidAnonContext extends SelectContext {
		public TerminalNode SELECT() { return getToken(SymbolParser.SELECT, 0); }
		public TerminalNode OVERLAID() { return getToken(SymbolParser.OVERLAID, 0); }
		public TerminalNode FROM() { return getToken(SymbolParser.FROM, 0); }
		public SelectCaseListContext selectCaseList() {
			return getRuleContext(SelectCaseListContext.class,0);
		}
		public TerminalNode ENDCASE() { return getToken(SymbolParser.ENDCASE, 0); }
		public TypeSelectOverlaidAnonContext(SelectContext ctx) { copyFrom(ctx); }
	}
	public static class TypeSelectTagAnonContext extends SelectContext {
		public FieldNameContext tagName;
		public TerminalNode SELECT() { return getToken(SymbolParser.SELECT, 0); }
		public TerminalNode FROM() { return getToken(SymbolParser.FROM, 0); }
		public SelectCaseListContext selectCaseList() {
			return getRuleContext(SelectCaseListContext.class,0);
		}
		public TerminalNode ENDCASE() { return getToken(SymbolParser.ENDCASE, 0); }
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public TypeSelectTagAnonContext(SelectContext ctx) { copyFrom(ctx); }
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_select);
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new TypeSelectOverlaidAnonContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				match(SELECT);
				setState(199);
				match(OVERLAID);
				setState(200);
				match(T__13);
				setState(201);
				match(FROM);
				setState(202);
				selectCaseList();
				setState(203);
				match(ENDCASE);
				}
				break;
			case 2:
				_localctx = new TypeSelectOverlaidTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				match(SELECT);
				setState(206);
				match(OVERLAID);
				setState(207);
				((TypeSelectOverlaidTypeContext)_localctx).tagTypeName = match(ID);
				setState(208);
				match(FROM);
				setState(209);
				selectCaseList();
				setState(210);
				match(ENDCASE);
				}
				break;
			case 3:
				_localctx = new TypeSelectTagAnonContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				match(SELECT);
				setState(213);
				((TypeSelectTagAnonContext)_localctx).tagName = fieldName();
				setState(214);
				match(T__3);
				setState(215);
				match(T__13);
				setState(216);
				match(FROM);
				setState(217);
				selectCaseList();
				setState(218);
				match(ENDCASE);
				}
				break;
			case 4:
				_localctx = new TypeSelectTagTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(220);
				match(SELECT);
				setState(221);
				((TypeSelectTagTypeContext)_localctx).tagName = fieldName();
				setState(222);
				match(T__3);
				setState(223);
				((TypeSelectTagTypeContext)_localctx).tagTypeName = match(ID);
				setState(224);
				match(FROM);
				setState(225);
				selectCaseList();
				setState(226);
				match(ENDCASE);
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
		enterRule(_localctx, 44, RULE_selectCaseList);
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
			while (_la==T__11) {
				{
				{
				setState(231);
				match(T__11);
				setState(232);
				((SelectCaseListContext)_localctx).selectCase = selectCase();
				((SelectCaseListContext)_localctx).elements.add(((SelectCaseListContext)_localctx).selectCase);
				setState(233);
				match(T__11);
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
		public SelectCaseSelectorContext selectCaseSelector() {
			return getRuleContext(SelectCaseSelectorContext.class,0);
		}
		public RecordFieldListContext recordFieldList() {
			return getRuleContext(RecordFieldListContext.class,0);
		}
		public SelectCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCase; }
	}

	public final SelectCaseContext selectCase() throws RecognitionException {
		SelectCaseContext _localctx = new SelectCaseContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_selectCase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			selectCaseSelector();
			setState(241);
			match(T__14);
			setState(242);
			match(T__5);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(243);
				recordFieldList();
				}
			}

			setState(246);
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

	public static class SelectCaseSelectorContext extends ParserRuleContext {
		public SelectCaseSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCaseSelector; }
	 
		public SelectCaseSelectorContext() { }
		public void copyFrom(SelectCaseSelectorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeSelectCaseSelectorValueContext extends SelectCaseSelectorContext {
		public Token selectorName;
		public Positive_numberContext selectorValue;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public Positive_numberContext positive_number() {
			return getRuleContext(Positive_numberContext.class,0);
		}
		public TypeSelectCaseSelectorValueContext(SelectCaseSelectorContext ctx) { copyFrom(ctx); }
	}
	public static class TypeSelectCaseSelectorContext extends SelectCaseSelectorContext {
		public Token selectorName;
		public TerminalNode ID() { return getToken(SymbolParser.ID, 0); }
		public TypeSelectCaseSelectorContext(SelectCaseSelectorContext ctx) { copyFrom(ctx); }
	}

	public final SelectCaseSelectorContext selectCaseSelector() throws RecognitionException {
		SelectCaseSelectorContext _localctx = new SelectCaseSelectorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_selectCaseSelector);
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new TypeSelectCaseSelectorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				((TypeSelectCaseSelectorContext)_localctx).selectorName = match(ID);
				}
				break;
			case 2:
				_localctx = new TypeSelectCaseSelectorValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				((TypeSelectCaseSelectorValueContext)_localctx).selectorName = match(ID);
				setState(250);
				match(T__12);
				setState(251);
				((TypeSelectCaseSelectorValueContext)_localctx).selectorValue = positive_number();
				setState(252);
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
		enterRule(_localctx, 50, RULE_referenceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
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

	public static class PredefinedTypeContext extends ParserRuleContext {
		public PredefinedTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predefinedType; }
	 
		public PredefinedTypeContext() { }
		public void copyFrom(PredefinedTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeCardinalContext extends PredefinedTypeContext {
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public TypeCardinalContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongIntegerContext extends PredefinedTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode INTEGER() { return getToken(SymbolParser.INTEGER, 0); }
		public TypeLongIntegerContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeBooleanContext extends PredefinedTypeContext {
		public TerminalNode BOOLEAN() { return getToken(SymbolParser.BOOLEAN, 0); }
		public TypeBooleanContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeIntegerContext extends PredefinedTypeContext {
		public TerminalNode INTEGER() { return getToken(SymbolParser.INTEGER, 0); }
		public TypeIntegerContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypePointerContext extends PredefinedTypeContext {
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TypePointerContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeUnspecifiedContext extends PredefinedTypeContext {
		public TerminalNode UNSPECIFIED() { return getToken(SymbolParser.UNSPECIFIED, 0); }
		public TypeUnspecifiedContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongPointerContext extends PredefinedTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode POINTER() { return getToken(SymbolParser.POINTER, 0); }
		public TypeLongPointerContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongCardinalContext extends PredefinedTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode CARDINAL() { return getToken(SymbolParser.CARDINAL, 0); }
		public TypeLongCardinalContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}
	public static class TypeLongUnspecifiedContext extends PredefinedTypeContext {
		public TerminalNode LONG() { return getToken(SymbolParser.LONG, 0); }
		public TerminalNode UNSPECIFIED() { return getToken(SymbolParser.UNSPECIFIED, 0); }
		public TypeLongUnspecifiedContext(PredefinedTypeContext ctx) { copyFrom(ctx); }
	}

	public final PredefinedTypeContext predefinedType() throws RecognitionException {
		PredefinedTypeContext _localctx = new PredefinedTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_predefinedType);
		try {
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				match(BOOLEAN);
				}
				break;
			case 2:
				_localctx = new TypeCardinalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(CARDINAL);
				}
				break;
			case 3:
				_localctx = new TypeLongCardinalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				match(LONG);
				setState(261);
				match(CARDINAL);
				}
				break;
			case 4:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(262);
				match(INTEGER);
				}
				break;
			case 5:
				_localctx = new TypeLongIntegerContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(263);
				match(LONG);
				setState(264);
				match(INTEGER);
				}
				break;
			case 6:
				_localctx = new TypeUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(265);
				match(UNSPECIFIED);
				}
				break;
			case 7:
				_localctx = new TypeLongUnspecifiedContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(266);
				match(LONG);
				setState(267);
				match(UNSPECIFIED);
				}
				break;
			case 8:
				_localctx = new TypePointerContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(268);
				match(POINTER);
				}
				break;
			case 9:
				_localctx = new TypeLongPointerContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(269);
				match(LONG);
				setState(270);
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
		enterRule(_localctx, 54, RULE_declConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			((DeclConstContext)_localctx).name = match(ID);
			setState(274);
			match(T__3);
			setState(275);
			constType();
			setState(276);
			match(T__1);
			setState(277);
			constValue();
			setState(278);
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
		enterRule(_localctx, 56, RULE_constType);
		try {
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CARDINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				match(CARDINAL);
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				match(POINTER);
				}
				break;
			case LONG:
				enterOuterAlt(_localctx, 3);
				{
				setState(282);
				match(LONG);
				setState(283);
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
		public Token ID;
		public List<Token> name = new ArrayList<Token>();
		public List<TerminalNode> ID() { return getTokens(SymbolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SymbolParser.ID, i);
		}
		public ConstValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constValue; }
	}

	public final ConstValueContext constValue() throws RecognitionException {
		ConstValueContext _localctx = new ConstValueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			((ConstValueContext)_localctx).ID = match(ID);
			((ConstValueContext)_localctx).name.add(((ConstValueContext)_localctx).ID);
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(287);
				match(T__2);
				setState(288);
				((ConstValueContext)_localctx).ID = match(ID);
				((ConstValueContext)_localctx).name.add(((ConstValueContext)_localctx).ID);
				}
				}
				setState(293);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0129\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\5"+
		"\2A\n\2\3\2\3\2\5\2E\n\2\3\3\3\3\3\4\3\4\5\4K\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\6\b[\n\b\r\b\16\b\\\3\t\3\t\5\ta"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13p\n"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\5\fx\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5"+
		"\f\u0082\n\f\3\r\3\r\5\r\u0086\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\7\17\u008f\n\17\f\17\16\17\u0092\13\17\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\5\21\u009a\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\7\23\u00aa\n\23\f\23\16\23\u00ad\13\23\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\5\25\u00c1\n\25\3\26\3\26\3\26\3\26\5\26\u00c7\n\26\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5"+
		"\27\u00e7\n\27\3\30\3\30\3\30\3\30\3\30\7\30\u00ee\n\30\f\30\16\30\u00f1"+
		"\13\30\3\31\3\31\3\31\3\31\5\31\u00f7\n\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\5\32\u0101\n\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0112\n\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\5\36\u011f\n\36\3\37\3\37\3\37\7\37"+
		"\u0124\n\37\f\37\16\37\u0127\13\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\4\3\2(*\3\2\n\13\2\u0130\2D\3\2"+
		"\2\2\4F\3\2\2\2\6J\3\2\2\2\bL\3\2\2\2\nQ\3\2\2\2\fU\3\2\2\2\16Z\3\2\2"+
		"\2\20`\3\2\2\2\22b\3\2\2\2\24o\3\2\2\2\26\u0081\3\2\2\2\30\u0085\3\2\2"+
		"\2\32\u0087\3\2\2\2\34\u008b\3\2\2\2\36\u0093\3\2\2\2 \u0099\3\2\2\2\""+
		"\u00a1\3\2\2\2$\u00a6\3\2\2\2&\u00ae\3\2\2\2(\u00c0\3\2\2\2*\u00c6\3\2"+
		"\2\2,\u00e6\3\2\2\2.\u00e8\3\2\2\2\60\u00f2\3\2\2\2\62\u0100\3\2\2\2\64"+
		"\u0102\3\2\2\2\66\u0111\3\2\2\28\u0113\3\2\2\2:\u011e\3\2\2\2<\u0120\3"+
		"\2\2\2>E\7(\2\2?A\7\3\2\2@?\3\2\2\2@A\3\2\2\2AB\3\2\2\2BE\7)\2\2CE\7*"+
		"\2\2D>\3\2\2\2D@\3\2\2\2DC\3\2\2\2E\3\3\2\2\2FG\t\2\2\2G\5\3\2\2\2HK\5"+
		"\2\2\2IK\7\'\2\2JH\3\2\2\2JI\3\2\2\2K\7\3\2\2\2LM\5\n\6\2MN\7\4\2\2NO"+
		"\5\f\7\2OP\7\5\2\2P\t\3\2\2\2QR\7\'\2\2RS\7\6\2\2ST\7!\2\2T\13\3\2\2\2"+
		"UV\7\23\2\2VW\5\16\b\2WX\7\26\2\2X\r\3\2\2\2Y[\5\20\t\2ZY\3\2\2\2[\\\3"+
		"\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]\17\3\2\2\2^a\5\22\n\2_a\58\35\2`^\3\2\2"+
		"\2`_\3\2\2\2a\21\3\2\2\2bc\7\'\2\2cd\7\6\2\2de\7\"\2\2ef\7\4\2\2fg\5\24"+
		"\13\2gh\7\7\2\2h\23\3\2\2\2ip\5\26\f\2jp\5\32\16\2kp\5 \21\2lp\5\"\22"+
		"\2mp\5\66\34\2np\5\64\33\2oi\3\2\2\2oj\3\2\2\2ok\3\2\2\2ol\3\2\2\2om\3"+
		"\2\2\2on\3\2\2\2p\25\3\2\2\2qr\7\22\2\2rs\7\'\2\2st\7\33\2\2t\u0082\5"+
		"\30\r\2uw\7\22\2\2vx\7\'\2\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\7\b\2\2z"+
		"{\5\6\4\2{|\7\t\2\2|}\5\6\4\2}~\t\3\2\2~\177\7\33\2\2\177\u0080\5\30\r"+
		"\2\u0080\u0082\3\2\2\2\u0081q\3\2\2\2\u0081u\3\2\2\2\u0082\27\3\2\2\2"+
		"\u0083\u0086\5\66\34\2\u0084\u0086\5\64\33\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\31\3\2\2\2\u0087\u0088\7\f\2\2\u0088\u0089\5\34\17"+
		"\2\u0089\u008a\7\r\2\2\u008a\33\3\2\2\2\u008b\u0090\5\36\20\2\u008c\u008d"+
		"\7\16\2\2\u008d\u008f\5\36\20\2\u008e\u008c\3\2\2\2\u008f\u0092\3\2\2"+
		"\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\35\3\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0093\u0094\7\'\2\2\u0094\u0095\7\17\2\2\u0095\u0096\5\4\3\2"+
		"\u0096\u0097\7\13\2\2\u0097\37\3\2\2\2\u0098\u009a\7\'\2\2\u0099\u0098"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\7\b\2\2\u009c"+
		"\u009d\5\6\4\2\u009d\u009e\7\t\2\2\u009e\u009f\5\6\4\2\u009f\u00a0\t\3"+
		"\2\2\u00a0!\3\2\2\2\u00a1\u00a2\7\37\2\2\u00a2\u00a3\7\b\2\2\u00a3\u00a4"+
		"\5$\23\2\u00a4\u00a5\7\n\2\2\u00a5#\3\2\2\2\u00a6\u00ab\5&\24\2\u00a7"+
		"\u00a8\7\16\2\2\u00a8\u00aa\5&\24\2\u00a9\u00a7\3\2\2\2\u00aa\u00ad\3"+
		"\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac%\3\2\2\2\u00ad\u00ab"+
		"\3\2\2\2\u00ae\u00af\5(\25\2\u00af\u00b0\7\6\2\2\u00b0\u00b1\5*\26\2\u00b1"+
		"\'\3\2\2\2\u00b2\u00b3\7\'\2\2\u00b3\u00b4\7\17\2\2\u00b4\u00b5\5\4\3"+
		"\2\u00b5\u00b6\7\13\2\2\u00b6\u00c1\3\2\2\2\u00b7\u00b8\7\'\2\2\u00b8"+
		"\u00b9\7\17\2\2\u00b9\u00ba\5\4\3\2\u00ba\u00bb\7\6\2\2\u00bb\u00bc\5"+
		"\4\3\2\u00bc\u00bd\7\t\2\2\u00bd\u00be\5\4\3\2\u00be\u00bf\7\13\2\2\u00bf"+
		"\u00c1\3\2\2\2\u00c0\u00b2\3\2\2\2\u00c0\u00b7\3\2\2\2\u00c1)\3\2\2\2"+
		"\u00c2\u00c7\5\26\f\2\u00c3\u00c7\5\66\34\2\u00c4\u00c7\5\64\33\2\u00c5"+
		"\u00c7\5,\27\2\u00c6\u00c2\3\2\2\2\u00c6\u00c3\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c6\u00c5\3\2\2\2\u00c7+\3\2\2\2\u00c8\u00c9\7 \2\2\u00c9\u00ca"+
		"\7\34\2\2\u00ca\u00cb\7\20\2\2\u00cb\u00cc\7\30\2\2\u00cc\u00cd\5.\30"+
		"\2\u00cd\u00ce\7\27\2\2\u00ce\u00e7\3\2\2\2\u00cf\u00d0\7 \2\2\u00d0\u00d1"+
		"\7\34\2\2\u00d1\u00d2\7\'\2\2\u00d2\u00d3\7\30\2\2\u00d3\u00d4\5.\30\2"+
		"\u00d4\u00d5\7\27\2\2\u00d5\u00e7\3\2\2\2\u00d6\u00d7\7 \2\2\u00d7\u00d8"+
		"\5(\25\2\u00d8\u00d9\7\6\2\2\u00d9\u00da\7\20\2\2\u00da\u00db\7\30\2\2"+
		"\u00db\u00dc\5.\30\2\u00dc\u00dd\7\27\2\2\u00dd\u00e7\3\2\2\2\u00de\u00df"+
		"\7 \2\2\u00df\u00e0\5(\25\2\u00e0\u00e1\7\6\2\2\u00e1\u00e2\7\'\2\2\u00e2"+
		"\u00e3\7\30\2\2\u00e3\u00e4\5.\30\2\u00e4\u00e5\7\27\2\2\u00e5\u00e7\3"+
		"\2\2\2\u00e6\u00c8\3\2\2\2\u00e6\u00cf\3\2\2\2\u00e6\u00d6\3\2\2\2\u00e6"+
		"\u00de\3\2\2\2\u00e7-\3\2\2\2\u00e8\u00ef\5\60\31\2\u00e9\u00ea\7\16\2"+
		"\2\u00ea\u00eb\5\60\31\2\u00eb\u00ec\7\16\2\2\u00ec\u00ee\3\2\2\2\u00ed"+
		"\u00e9\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2"+
		"\2\2\u00f0/\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\5\62\32\2\u00f3\u00f4"+
		"\7\21\2\2\u00f4\u00f6\7\b\2\2\u00f5\u00f7\5$\23\2\u00f6\u00f5\3\2\2\2"+
		"\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\n\2\2\u00f9\61"+
		"\3\2\2\2\u00fa\u0101\7\'\2\2\u00fb\u00fc\7\'\2\2\u00fc\u00fd\7\17\2\2"+
		"\u00fd\u00fe\5\4\3\2\u00fe\u00ff\7\13\2\2\u00ff\u0101\3\2\2\2\u0100\u00fa"+
		"\3\2\2\2\u0100\u00fb\3\2\2\2\u0101\63\3\2\2\2\u0102\u0103\7\'\2\2\u0103"+
		"\65\3\2\2\2\u0104\u0112\7\24\2\2\u0105\u0112\7\25\2\2\u0106\u0107\7\32"+
		"\2\2\u0107\u0112\7\25\2\2\u0108\u0112\7\31\2\2\u0109\u010a\7\32\2\2\u010a"+
		"\u0112\7\31\2\2\u010b\u0112\7#\2\2\u010c\u010d\7\32\2\2\u010d\u0112\7"+
		"#\2\2\u010e\u0112\7\36\2\2\u010f\u0110\7\32\2\2\u0110\u0112\7\36\2\2\u0111"+
		"\u0104\3\2\2\2\u0111\u0105\3\2\2\2\u0111\u0106\3\2\2\2\u0111\u0108\3\2"+
		"\2\2\u0111\u0109\3\2\2\2\u0111\u010b\3\2\2\2\u0111\u010c\3\2\2\2\u0111"+
		"\u010e\3\2\2\2\u0111\u010f\3\2\2\2\u0112\67\3\2\2\2\u0113\u0114\7\'\2"+
		"\2\u0114\u0115\7\6\2\2\u0115\u0116\5:\36\2\u0116\u0117\7\4\2\2\u0117\u0118"+
		"\5<\37\2\u0118\u0119\7\7\2\2\u01199\3\2\2\2\u011a\u011f\7\25\2\2\u011b"+
		"\u011f\7\36\2\2\u011c\u011d\7\32\2\2\u011d\u011f\7\36\2\2\u011e\u011a"+
		"\3\2\2\2\u011e\u011b\3\2\2\2\u011e\u011c\3\2\2\2\u011f;\3\2\2\2\u0120"+
		"\u0125\7\'\2\2\u0121\u0122\7\5\2\2\u0122\u0124\7\'\2\2\u0123\u0121\3\2"+
		"\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"=\3\2\2\2\u0127\u0125\3\2\2\2\27@DJ\\`ow\u0081\u0085\u0090\u0099\u00ab"+
		"\u00c0\u00c6\u00e6\u00ef\u00f6\u0100\u0111\u011e\u0125";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}