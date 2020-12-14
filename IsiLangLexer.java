// Generated from IsiLang.g4 by ANTLR 4.7.1

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		AP=10, FP=11, SC=12, OP=13, ATTR=14, VIR=15, ACH=16, FCH=17, OPREL=18, 
		ID=19, NUMBER=20, WS=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'declara'", "'texto'", "'numero'", 
		"'leia'", "'escreva'", "'se'", "'senao'", "'('", "')'", "';'", null, "'='", 
		"','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, "AP", "FP", 
		"SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", "WS"
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


		private int _tipo;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared 123");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u009d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0084"+
		"\n\23\3\24\3\24\7\24\u0088\n\24\f\24\16\24\u008b\13\24\3\25\6\25\u008e"+
		"\n\25\r\25\16\25\u008f\3\25\3\25\6\25\u0094\n\25\r\25\16\25\u0095\5\25"+
		"\u0098\n\25\3\26\3\26\3\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27\3"+
		"\2\b\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\""+
		"\"\2\u00a4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2"+
		"\5\66\3\2\2\2\7?\3\2\2\2\tG\3\2\2\2\13M\3\2\2\2\rT\3\2\2\2\17Y\3\2\2\2"+
		"\21a\3\2\2\2\23d\3\2\2\2\25j\3\2\2\2\27l\3\2\2\2\31n\3\2\2\2\33p\3\2\2"+
		"\2\35r\3\2\2\2\37t\3\2\2\2!v\3\2\2\2#x\3\2\2\2%\u0083\3\2\2\2\'\u0085"+
		"\3\2\2\2)\u008d\3\2\2\2+\u0099\3\2\2\2-.\7r\2\2./\7t\2\2/\60\7q\2\2\60"+
		"\61\7i\2\2\61\62\7t\2\2\62\63\7c\2\2\63\64\7o\2\2\64\65\7c\2\2\65\4\3"+
		"\2\2\2\66\67\7h\2\2\678\7k\2\289\7o\2\29:\7r\2\2:;\7t\2\2;<\7q\2\2<=\7"+
		"i\2\2=>\7=\2\2>\6\3\2\2\2?@\7f\2\2@A\7g\2\2AB\7e\2\2BC\7n\2\2CD\7c\2\2"+
		"DE\7t\2\2EF\7c\2\2F\b\3\2\2\2GH\7v\2\2HI\7g\2\2IJ\7z\2\2JK\7v\2\2KL\7"+
		"q\2\2L\n\3\2\2\2MN\7p\2\2NO\7w\2\2OP\7o\2\2PQ\7g\2\2QR\7t\2\2RS\7q\2\2"+
		"S\f\3\2\2\2TU\7n\2\2UV\7g\2\2VW\7k\2\2WX\7c\2\2X\16\3\2\2\2YZ\7g\2\2Z"+
		"[\7u\2\2[\\\7e\2\2\\]\7t\2\2]^\7g\2\2^_\7x\2\2_`\7c\2\2`\20\3\2\2\2ab"+
		"\7u\2\2bc\7g\2\2c\22\3\2\2\2de\7u\2\2ef\7g\2\2fg\7p\2\2gh\7c\2\2hi\7q"+
		"\2\2i\24\3\2\2\2jk\7*\2\2k\26\3\2\2\2lm\7+\2\2m\30\3\2\2\2no\7=\2\2o\32"+
		"\3\2\2\2pq\t\2\2\2q\34\3\2\2\2rs\7?\2\2s\36\3\2\2\2tu\7.\2\2u \3\2\2\2"+
		"vw\7}\2\2w\"\3\2\2\2xy\7\177\2\2y$\3\2\2\2z\u0084\t\3\2\2{|\7@\2\2|\u0084"+
		"\7?\2\2}~\7>\2\2~\u0084\7?\2\2\177\u0080\7?\2\2\u0080\u0084\7?\2\2\u0081"+
		"\u0082\7#\2\2\u0082\u0084\7?\2\2\u0083z\3\2\2\2\u0083{\3\2\2\2\u0083}"+
		"\3\2\2\2\u0083\177\3\2\2\2\u0083\u0081\3\2\2\2\u0084&\3\2\2\2\u0085\u0089"+
		"\t\4\2\2\u0086\u0088\t\5\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a(\3\2\2\2\u008b\u0089\3\2\2\2"+
		"\u008c\u008e\t\6\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0097\3\2\2\2\u0091\u0093\7\60\2\2"+
		"\u0092\u0094\t\6\2\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093"+
		"\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0091\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098*\3\2\2\2\u0099\u009a\t\7\2\2\u009a\u009b\3\2\2\2"+
		"\u009b\u009c\b\26\2\2\u009c,\3\2\2\2\t\2\u0083\u0087\u0089\u008f\u0095"+
		"\u0097\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}