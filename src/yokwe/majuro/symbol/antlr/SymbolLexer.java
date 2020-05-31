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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "ARRAY", "BEGIN", 
			"BOOLEAN", "CARDINAL", "END", "ENDCASE", "FROM", "INTEGER", "LONG", "OF", 
			"OVERLAID", "PACKED", "POINTER", "RECORD", "SELECT", "SYMBOL", "TYPE", 
			"UNSPECIFIED", "WORD", "TRUE", "FALSE", "CHAR_ALPHA", "CHAR_AF", "CHAR_DEC", 
			"CHAR_OCT", "ID", "NUMBER_8", "NUMBER_10", "NUMBER_16", "COMMENT_PARTIAL", 
			"COMMENT_LINE", "SPACE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u0156\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3"+
		"#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*"+
		"\3*\7*\u011a\n*\f*\16*\u011d\13*\3+\6+\u0120\n+\r+\16+\u0121\3+\3+\3,"+
		"\6,\u0127\n,\r,\16,\u0128\3-\3-\3-\7-\u012e\n-\f-\16-\u0131\13-\3-\3-"+
		"\3.\3.\3.\3.\7.\u0139\n.\f.\16.\u013c\13.\3.\3.\3.\3.\3.\3/\3/\3/\3/\7"+
		"/\u0147\n/\f/\16/\u014a\13/\3/\5/\u014d\n/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\u013a\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\2O\2Q\2S\'U(W)Y*[+],_-\3\2\n\5"+
		"\2C\\c|~~\5\2CHch~~\3\2\62;\3\2\629\4\2DDdd\4\2ZZzz\4\2\f\f\17\17\5\2"+
		"\13\f\17\17\"\"\2\u015b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2\2\5c\3\2\2\2\7e\3"+
		"\2\2\2\th\3\2\2\2\13j\3\2\2\2\rl\3\2\2\2\17n\3\2\2\2\21p\3\2\2\2\23r\3"+
		"\2\2\2\25t\3\2\2\2\27v\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2\35|\3\2\2\2\37"+
		"~\3\2\2\2!\u0081\3\2\2\2#\u0087\3\2\2\2%\u008d\3\2\2\2\'\u0095\3\2\2\2"+
		")\u009e\3\2\2\2+\u00a2\3\2\2\2-\u00aa\3\2\2\2/\u00af\3\2\2\2\61\u00b7"+
		"\3\2\2\2\63\u00bc\3\2\2\2\65\u00bf\3\2\2\2\67\u00c8\3\2\2\29\u00cf\3\2"+
		"\2\2;\u00d7\3\2\2\2=\u00de\3\2\2\2?\u00e5\3\2\2\2A\u00ec\3\2\2\2C\u00f1"+
		"\3\2\2\2E\u00fd\3\2\2\2G\u0102\3\2\2\2I\u0107\3\2\2\2K\u010d\3\2\2\2M"+
		"\u010f\3\2\2\2O\u0111\3\2\2\2Q\u0113\3\2\2\2S\u0115\3\2\2\2U\u011f\3\2"+
		"\2\2W\u0126\3\2\2\2Y\u012a\3\2\2\2[\u0134\3\2\2\2]\u0142\3\2\2\2_\u0152"+
		"\3\2\2\2ab\7/\2\2b\4\3\2\2\2cd\7]\2\2d\6\3\2\2\2ef\7\60\2\2fg\7\60\2\2"+
		"g\b\3\2\2\2hi\7_\2\2i\n\3\2\2\2jk\7+\2\2k\f\3\2\2\2lm\7\60\2\2m\16\3\2"+
		"\2\2no\7*\2\2o\20\3\2\2\2pq\7?\2\2q\22\3\2\2\2rs\7<\2\2s\24\3\2\2\2tu"+
		"\7=\2\2u\26\3\2\2\2vw\7}\2\2w\30\3\2\2\2xy\7\177\2\2y\32\3\2\2\2z{\7."+
		"\2\2{\34\3\2\2\2|}\7,\2\2}\36\3\2\2\2~\177\7?\2\2\177\u0080\7@\2\2\u0080"+
		" \3\2\2\2\u0081\u0082\7C\2\2\u0082\u0083\7T\2\2\u0083\u0084\7T\2\2\u0084"+
		"\u0085\7C\2\2\u0085\u0086\7[\2\2\u0086\"\3\2\2\2\u0087\u0088\7D\2\2\u0088"+
		"\u0089\7G\2\2\u0089\u008a\7I\2\2\u008a\u008b\7K\2\2\u008b\u008c\7P\2\2"+
		"\u008c$\3\2\2\2\u008d\u008e\7D\2\2\u008e\u008f\7Q\2\2\u008f\u0090\7Q\2"+
		"\2\u0090\u0091\7N\2\2\u0091\u0092\7G\2\2\u0092\u0093\7C\2\2\u0093\u0094"+
		"\7P\2\2\u0094&\3\2\2\2\u0095\u0096\7E\2\2\u0096\u0097\7C\2\2\u0097\u0098"+
		"\7T\2\2\u0098\u0099\7F\2\2\u0099\u009a\7K\2\2\u009a\u009b\7P\2\2\u009b"+
		"\u009c\7C\2\2\u009c\u009d\7N\2\2\u009d(\3\2\2\2\u009e\u009f\7G\2\2\u009f"+
		"\u00a0\7P\2\2\u00a0\u00a1\7F\2\2\u00a1*\3\2\2\2\u00a2\u00a3\7G\2\2\u00a3"+
		"\u00a4\7P\2\2\u00a4\u00a5\7F\2\2\u00a5\u00a6\7E\2\2\u00a6\u00a7\7C\2\2"+
		"\u00a7\u00a8\7U\2\2\u00a8\u00a9\7G\2\2\u00a9,\3\2\2\2\u00aa\u00ab\7H\2"+
		"\2\u00ab\u00ac\7T\2\2\u00ac\u00ad\7Q\2\2\u00ad\u00ae\7O\2\2\u00ae.\3\2"+
		"\2\2\u00af\u00b0\7K\2\2\u00b0\u00b1\7P\2\2\u00b1\u00b2\7V\2\2\u00b2\u00b3"+
		"\7G\2\2\u00b3\u00b4\7I\2\2\u00b4\u00b5\7G\2\2\u00b5\u00b6\7T\2\2\u00b6"+
		"\60\3\2\2\2\u00b7\u00b8\7N\2\2\u00b8\u00b9\7Q\2\2\u00b9\u00ba\7P\2\2\u00ba"+
		"\u00bb\7I\2\2\u00bb\62\3\2\2\2\u00bc\u00bd\7Q\2\2\u00bd\u00be\7H\2\2\u00be"+
		"\64\3\2\2\2\u00bf\u00c0\7Q\2\2\u00c0\u00c1\7X\2\2\u00c1\u00c2\7G\2\2\u00c2"+
		"\u00c3\7T\2\2\u00c3\u00c4\7N\2\2\u00c4\u00c5\7C\2\2\u00c5\u00c6\7K\2\2"+
		"\u00c6\u00c7\7F\2\2\u00c7\66\3\2\2\2\u00c8\u00c9\7R\2\2\u00c9\u00ca\7"+
		"C\2\2\u00ca\u00cb\7E\2\2\u00cb\u00cc\7M\2\2\u00cc\u00cd\7G\2\2\u00cd\u00ce"+
		"\7F\2\2\u00ce8\3\2\2\2\u00cf\u00d0\7R\2\2\u00d0\u00d1\7Q\2\2\u00d1\u00d2"+
		"\7K\2\2\u00d2\u00d3\7P\2\2\u00d3\u00d4\7V\2\2\u00d4\u00d5\7G\2\2\u00d5"+
		"\u00d6\7T\2\2\u00d6:\3\2\2\2\u00d7\u00d8\7T\2\2\u00d8\u00d9\7G\2\2\u00d9"+
		"\u00da\7E\2\2\u00da\u00db\7Q\2\2\u00db\u00dc\7T\2\2\u00dc\u00dd\7F\2\2"+
		"\u00dd<\3\2\2\2\u00de\u00df\7U\2\2\u00df\u00e0\7G\2\2\u00e0\u00e1\7N\2"+
		"\2\u00e1\u00e2\7G\2\2\u00e2\u00e3\7E\2\2\u00e3\u00e4\7V\2\2\u00e4>\3\2"+
		"\2\2\u00e5\u00e6\7U\2\2\u00e6\u00e7\7[\2\2\u00e7\u00e8\7O\2\2\u00e8\u00e9"+
		"\7D\2\2\u00e9\u00ea\7Q\2\2\u00ea\u00eb\7N\2\2\u00eb@\3\2\2\2\u00ec\u00ed"+
		"\7V\2\2\u00ed\u00ee\7[\2\2\u00ee\u00ef\7R\2\2\u00ef\u00f0\7G\2\2\u00f0"+
		"B\3\2\2\2\u00f1\u00f2\7W\2\2\u00f2\u00f3\7P\2\2\u00f3\u00f4\7U\2\2\u00f4"+
		"\u00f5\7R\2\2\u00f5\u00f6\7G\2\2\u00f6\u00f7\7E\2\2\u00f7\u00f8\7K\2\2"+
		"\u00f8\u00f9\7H\2\2\u00f9\u00fa\7K\2\2\u00fa\u00fb\7G\2\2\u00fb\u00fc"+
		"\7F\2\2\u00fcD\3\2\2\2\u00fd\u00fe\7Y\2\2\u00fe\u00ff\7Q\2\2\u00ff\u0100"+
		"\7T\2\2\u0100\u0101\7F\2\2\u0101F\3\2\2\2\u0102\u0103\7V\2\2\u0103\u0104"+
		"\7T\2\2\u0104\u0105\7W\2\2\u0105\u0106\7G\2\2\u0106H\3\2\2\2\u0107\u0108"+
		"\7H\2\2\u0108\u0109\7C\2\2\u0109\u010a\7N\2\2\u010a\u010b\7U\2\2\u010b"+
		"\u010c\7G\2\2\u010cJ\3\2\2\2\u010d\u010e\t\2\2\2\u010eL\3\2\2\2\u010f"+
		"\u0110\t\3\2\2\u0110N\3\2\2\2\u0111\u0112\t\4\2\2\u0112P\3\2\2\2\u0113"+
		"\u0114\t\5\2\2\u0114R\3\2\2\2\u0115\u011b\5K&\2\u0116\u011a\5K&\2\u0117"+
		"\u011a\5O(\2\u0118\u011a\7a\2\2\u0119\u0116\3\2\2\2\u0119\u0117\3\2\2"+
		"\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011cT\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0120\5Q)\2\u011f\u011e"+
		"\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123\u0124\t\6\2\2\u0124V\3\2\2\2\u0125\u0127\5O(\2\u0126"+
		"\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2"+
		"\2\2\u0129X\3\2\2\2\u012a\u012f\5O(\2\u012b\u012e\5O(\2\u012c\u012e\5"+
		"M\'\2\u012d\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e\u0131\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0132\3\2\2\2\u0131\u012f\3\2"+
		"\2\2\u0132\u0133\t\7\2\2\u0133Z\3\2\2\2\u0134\u0135\7/\2\2\u0135\u0136"+
		"\7/\2\2\u0136\u013a\3\2\2\2\u0137\u0139\n\b\2\2\u0138\u0137\3\2\2\2\u0139"+
		"\u013c\3\2\2\2\u013a\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d\3\2"+
		"\2\2\u013c\u013a\3\2\2\2\u013d\u013e\7/\2\2\u013e\u013f\7/\2\2\u013f\u0140"+
		"\3\2\2\2\u0140\u0141\b.\2\2\u0141\\\3\2\2\2\u0142\u0143\7/\2\2\u0143\u0144"+
		"\7/\2\2\u0144\u0148\3\2\2\2\u0145\u0147\n\b\2\2\u0146\u0145\3\2\2\2\u0147"+
		"\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014c\3\2"+
		"\2\2\u014a\u0148\3\2\2\2\u014b\u014d\7\17\2\2\u014c\u014b\3\2\2\2\u014c"+
		"\u014d\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\7\f\2\2\u014f\u0150\3\2"+
		"\2\2\u0150\u0151\b/\2\2\u0151^\3\2\2\2\u0152\u0153\t\t\2\2\u0153\u0154"+
		"\3\2\2\2\u0154\u0155\b\60\2\2\u0155`\3\2\2\2\f\2\u0119\u011b\u0121\u0128"+
		"\u012d\u012f\u013a\u0148\u014c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}