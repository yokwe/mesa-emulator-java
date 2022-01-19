// Generated from data/type/Symbol.g4 by ANTLR 4.9.3
package yokwe.majuro.symbol.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SymbolLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "ARRAY", "BEGIN", "BOOLEAN", "CARDINAL", 
			"END", "ENDCASE", "FROM", "INTEGER", "LONG", "OF", "OVERLAID", "PACKED", 
			"POINTER", "RECORD", "RECORD32", "SELECT", "SYMBOL", "TO", "TYPE", "UNSPECIFIED", 
			"WORD", "TRUE", "FALSE", "CHAR_ALPHA", "CHAR_AF", "CHAR_DEC", "CHAR_OCT", 
			"ID", "NUMBER_8", "NUMBER_10", "NUMBER_16", "COMMENT_PARTIAL", "COMMENT_LINE", 
			"SPACE"
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


	public SymbolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Symbol.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u015d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3"+
		"%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3*\7*\u0121\n*\f*\16*\u0124"+
		"\13*\3+\6+\u0127\n+\r+\16+\u0128\3+\3+\3,\6,\u012e\n,\r,\16,\u012f\3-"+
		"\3-\3-\7-\u0135\n-\f-\16-\u0138\13-\3-\3-\3.\3.\3.\3.\7.\u0140\n.\f.\16"+
		".\u0143\13.\3.\3.\3.\3.\3.\3/\3/\3/\3/\7/\u014e\n/\f/\16/\u0151\13/\3"+
		"/\5/\u0154\n/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\u0141\2\61\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\2M\2O\2Q\2S\'U(W)Y*[+],_-\3\2\n\5\2C\\c|~~\5\2CHch~~\3\2\62;\3\2"+
		"\629\4\2DDdd\4\2ZZzz\4\2\f\f\17\17\5\2\13\f\17\17\"\"\2\u0162\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2"+
		"\2\2_\3\2\2\2\3a\3\2\2\2\5c\3\2\2\2\7e\3\2\2\2\tg\3\2\2\2\13i\3\2\2\2"+
		"\rk\3\2\2\2\17m\3\2\2\2\21p\3\2\2\2\23r\3\2\2\2\25t\3\2\2\2\27v\3\2\2"+
		"\2\31x\3\2\2\2\33z\3\2\2\2\35|\3\2\2\2\37\u0082\3\2\2\2!\u0088\3\2\2\2"+
		"#\u0090\3\2\2\2%\u0099\3\2\2\2\'\u009d\3\2\2\2)\u00a5\3\2\2\2+\u00aa\3"+
		"\2\2\2-\u00b2\3\2\2\2/\u00b7\3\2\2\2\61\u00ba\3\2\2\2\63\u00c3\3\2\2\2"+
		"\65\u00ca\3\2\2\2\67\u00d2\3\2\2\29\u00d9\3\2\2\2;\u00e2\3\2\2\2=\u00e9"+
		"\3\2\2\2?\u00f0\3\2\2\2A\u00f3\3\2\2\2C\u00f8\3\2\2\2E\u0104\3\2\2\2G"+
		"\u0109\3\2\2\2I\u010e\3\2\2\2K\u0114\3\2\2\2M\u0116\3\2\2\2O\u0118\3\2"+
		"\2\2Q\u011a\3\2\2\2S\u011c\3\2\2\2U\u0126\3\2\2\2W\u012d\3\2\2\2Y\u0131"+
		"\3\2\2\2[\u013b\3\2\2\2]\u0149\3\2\2\2_\u0159\3\2\2\2ab\7/\2\2b\4\3\2"+
		"\2\2cd\7?\2\2d\6\3\2\2\2ef\7\60\2\2f\b\3\2\2\2gh\7<\2\2h\n\3\2\2\2ij\7"+
		"=\2\2j\f\3\2\2\2kl\7]\2\2l\16\3\2\2\2mn\7\60\2\2no\7\60\2\2o\20\3\2\2"+
		"\2pq\7_\2\2q\22\3\2\2\2rs\7+\2\2s\24\3\2\2\2tu\7}\2\2u\26\3\2\2\2vw\7"+
		"\177\2\2w\30\3\2\2\2xy\7.\2\2y\32\3\2\2\2z{\7*\2\2{\34\3\2\2\2|}\7C\2"+
		"\2}~\7T\2\2~\177\7T\2\2\177\u0080\7C\2\2\u0080\u0081\7[\2\2\u0081\36\3"+
		"\2\2\2\u0082\u0083\7D\2\2\u0083\u0084\7G\2\2\u0084\u0085\7I\2\2\u0085"+
		"\u0086\7K\2\2\u0086\u0087\7P\2\2\u0087 \3\2\2\2\u0088\u0089\7D\2\2\u0089"+
		"\u008a\7Q\2\2\u008a\u008b\7Q\2\2\u008b\u008c\7N\2\2\u008c\u008d\7G\2\2"+
		"\u008d\u008e\7C\2\2\u008e\u008f\7P\2\2\u008f\"\3\2\2\2\u0090\u0091\7E"+
		"\2\2\u0091\u0092\7C\2\2\u0092\u0093\7T\2\2\u0093\u0094\7F\2\2\u0094\u0095"+
		"\7K\2\2\u0095\u0096\7P\2\2\u0096\u0097\7C\2\2\u0097\u0098\7N\2\2\u0098"+
		"$\3\2\2\2\u0099\u009a\7G\2\2\u009a\u009b\7P\2\2\u009b\u009c\7F\2\2\u009c"+
		"&\3\2\2\2\u009d\u009e\7G\2\2\u009e\u009f\7P\2\2\u009f\u00a0\7F\2\2\u00a0"+
		"\u00a1\7E\2\2\u00a1\u00a2\7C\2\2\u00a2\u00a3\7U\2\2\u00a3\u00a4\7G\2\2"+
		"\u00a4(\3\2\2\2\u00a5\u00a6\7H\2\2\u00a6\u00a7\7T\2\2\u00a7\u00a8\7Q\2"+
		"\2\u00a8\u00a9\7O\2\2\u00a9*\3\2\2\2\u00aa\u00ab\7K\2\2\u00ab\u00ac\7"+
		"P\2\2\u00ac\u00ad\7V\2\2\u00ad\u00ae\7G\2\2\u00ae\u00af\7I\2\2\u00af\u00b0"+
		"\7G\2\2\u00b0\u00b1\7T\2\2\u00b1,\3\2\2\2\u00b2\u00b3\7N\2\2\u00b3\u00b4"+
		"\7Q\2\2\u00b4\u00b5\7P\2\2\u00b5\u00b6\7I\2\2\u00b6.\3\2\2\2\u00b7\u00b8"+
		"\7Q\2\2\u00b8\u00b9\7H\2\2\u00b9\60\3\2\2\2\u00ba\u00bb\7Q\2\2\u00bb\u00bc"+
		"\7X\2\2\u00bc\u00bd\7G\2\2\u00bd\u00be\7T\2\2\u00be\u00bf\7N\2\2\u00bf"+
		"\u00c0\7C\2\2\u00c0\u00c1\7K\2\2\u00c1\u00c2\7F\2\2\u00c2\62\3\2\2\2\u00c3"+
		"\u00c4\7R\2\2\u00c4\u00c5\7C\2\2\u00c5\u00c6\7E\2\2\u00c6\u00c7\7M\2\2"+
		"\u00c7\u00c8\7G\2\2\u00c8\u00c9\7F\2\2\u00c9\64\3\2\2\2\u00ca\u00cb\7"+
		"R\2\2\u00cb\u00cc\7Q\2\2\u00cc\u00cd\7K\2\2\u00cd\u00ce\7P\2\2\u00ce\u00cf"+
		"\7V\2\2\u00cf\u00d0\7G\2\2\u00d0\u00d1\7T\2\2\u00d1\66\3\2\2\2\u00d2\u00d3"+
		"\7T\2\2\u00d3\u00d4\7G\2\2\u00d4\u00d5\7E\2\2\u00d5\u00d6\7Q\2\2\u00d6"+
		"\u00d7\7T\2\2\u00d7\u00d8\7F\2\2\u00d88\3\2\2\2\u00d9\u00da\7T\2\2\u00da"+
		"\u00db\7G\2\2\u00db\u00dc\7E\2\2\u00dc\u00dd\7Q\2\2\u00dd\u00de\7T\2\2"+
		"\u00de\u00df\7F\2\2\u00df\u00e0\7\65\2\2\u00e0\u00e1\7\64\2\2\u00e1:\3"+
		"\2\2\2\u00e2\u00e3\7U\2\2\u00e3\u00e4\7G\2\2\u00e4\u00e5\7N\2\2\u00e5"+
		"\u00e6\7G\2\2\u00e6\u00e7\7E\2\2\u00e7\u00e8\7V\2\2\u00e8<\3\2\2\2\u00e9"+
		"\u00ea\7U\2\2\u00ea\u00eb\7[\2\2\u00eb\u00ec\7O\2\2\u00ec\u00ed\7D\2\2"+
		"\u00ed\u00ee\7Q\2\2\u00ee\u00ef\7N\2\2\u00ef>\3\2\2\2\u00f0\u00f1\7V\2"+
		"\2\u00f1\u00f2\7Q\2\2\u00f2@\3\2\2\2\u00f3\u00f4\7V\2\2\u00f4\u00f5\7"+
		"[\2\2\u00f5\u00f6\7R\2\2\u00f6\u00f7\7G\2\2\u00f7B\3\2\2\2\u00f8\u00f9"+
		"\7W\2\2\u00f9\u00fa\7P\2\2\u00fa\u00fb\7U\2\2\u00fb\u00fc\7R\2\2\u00fc"+
		"\u00fd\7G\2\2\u00fd\u00fe\7E\2\2\u00fe\u00ff\7K\2\2\u00ff\u0100\7H\2\2"+
		"\u0100\u0101\7K\2\2\u0101\u0102\7G\2\2\u0102\u0103\7F\2\2\u0103D\3\2\2"+
		"\2\u0104\u0105\7Y\2\2\u0105\u0106\7Q\2\2\u0106\u0107\7T\2\2\u0107\u0108"+
		"\7F\2\2\u0108F\3\2\2\2\u0109\u010a\7V\2\2\u010a\u010b\7T\2\2\u010b\u010c"+
		"\7W\2\2\u010c\u010d\7G\2\2\u010dH\3\2\2\2\u010e\u010f\7H\2\2\u010f\u0110"+
		"\7C\2\2\u0110\u0111\7N\2\2\u0111\u0112\7U\2\2\u0112\u0113\7G\2\2\u0113"+
		"J\3\2\2\2\u0114\u0115\t\2\2\2\u0115L\3\2\2\2\u0116\u0117\t\3\2\2\u0117"+
		"N\3\2\2\2\u0118\u0119\t\4\2\2\u0119P\3\2\2\2\u011a\u011b\t\5\2\2\u011b"+
		"R\3\2\2\2\u011c\u0122\5K&\2\u011d\u0121\5K&\2\u011e\u0121\5O(\2\u011f"+
		"\u0121\7a\2\2\u0120\u011d\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2"+
		"\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123"+
		"T\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\5Q)\2\u0126\u0125\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2"+
		"\2\2\u012a\u012b\t\6\2\2\u012bV\3\2\2\2\u012c\u012e\5O(\2\u012d\u012c"+
		"\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"X\3\2\2\2\u0131\u0136\5O(\2\u0132\u0135\5O(\2\u0133\u0135\5M\'\2\u0134"+
		"\u0132\3\2\2\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2"+
		"\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139"+
		"\u013a\t\7\2\2\u013aZ\3\2\2\2\u013b\u013c\7\61\2\2\u013c\u013d\7\61\2"+
		"\2\u013d\u0141\3\2\2\2\u013e\u0140\n\b\2\2\u013f\u013e\3\2\2\2\u0140\u0143"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0144\3\2\2\2\u0143"+
		"\u0141\3\2\2\2\u0144\u0145\7/\2\2\u0145\u0146\7/\2\2\u0146\u0147\3\2\2"+
		"\2\u0147\u0148\b.\2\2\u0148\\\3\2\2\2\u0149\u014a\7/\2\2\u014a\u014b\7"+
		"/\2\2\u014b\u014f\3\2\2\2\u014c\u014e\n\b\2\2\u014d\u014c\3\2\2\2\u014e"+
		"\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0153\3\2"+
		"\2\2\u0151\u014f\3\2\2\2\u0152\u0154\7\17\2\2\u0153\u0152\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\7\f\2\2\u0156\u0157\3\2"+
		"\2\2\u0157\u0158\b/\2\2\u0158^\3\2\2\2\u0159\u015a\t\t\2\2\u015a\u015b"+
		"\3\2\2\2\u015b\u015c\b\60\2\2\u015c`\3\2\2\2\f\2\u0120\u0122\u0128\u012f"+
		"\u0134\u0136\u0141\u014f\u0153\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}