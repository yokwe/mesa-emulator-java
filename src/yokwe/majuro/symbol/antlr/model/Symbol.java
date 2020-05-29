package yokwe.majuro.symbol.antlr.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.antlr.SymbolBaseVisitor;
import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.ConstValueContext;
import yokwe.majuro.symbol.antlr.SymbolParser.DeclContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SymbolContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeBooleanContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeIntegerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongCardinalContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongIntegerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongPointerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeLongUnspecifiedContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypePointerContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeTypeContext;
import yokwe.majuro.symbol.antlr.SymbolParser.TypeUnspecifiedContext;

public class Symbol {
	private static final Logger logger = LoggerFactory.getLogger(Symbol.class);
	
	public static final String  PATH_RULE_FILE = "data/type/MesaType.symbol";

	public final String             name;
	public final Map<String, Const> constMap;
	public final Map<String, Type>  typeMap;
	
	private Symbol(SymbolContext tree) {
		this.name     = tree.header().name.getText();
		this.constMap = new TreeMap<>();
		this.typeMap  = new TreeMap<>();
	}
	
	public static Symbol getInstance(String filePath) {
		try {
			CharStream input = CharStreams.fromFileName(filePath);
			
			SymbolLexer       lexer   = new SymbolLexer(input);
			CommonTokenStream tokens  = new CommonTokenStream(lexer);
			
			tokens.fill();
			
			SymbolParser parser = new SymbolParser(tokens);
			parser.setErrorHandler(new BailErrorStrategy());
			
			SymbolContext tree = parser.symbol();
			
			Symbol symbol = new Symbol(tree);
			logger.info("name {}", symbol.name);
			symbol.build(tree);
			
			return symbol;
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
		
	private static final SymbolBaseVisitor<Type> typeVisitors = new SymbolBaseVisitor<>() {
		// ARRAY
		@Override public Type visitTypeArrayType(SymbolParser.TypeArrayTypeContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeArrayRange(SymbolParser.TypeArrayRangeContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		
		// ENUM
		@Override public Type visitTypeEnum(SymbolParser.TypeEnumContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}

		// SUBRANGE
		@Override public Type visitTypeSubrangeType(SymbolParser.TypeSubrangeTypeContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeSubrangeTypeRange(SymbolParser.TypeSubrangeTypeRangeContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		
		// RECORD
		@Override public Type visitTypeRecordField(SymbolParser.TypeRecordFieldContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeRecord(SymbolParser.TypeRecordContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		
		// REFERENCE
		@Override public Type visitTypeRef(SymbolParser.TypeRefContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		
		// SIMPLE
		@Override public Type visitTypeBoolean(TypeBooleanContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}

		@Override public Type visitTypeCardinal(TypeCardinalContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeLongCardinal(TypeLongCardinalContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeInteger(TypeIntegerContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeLongInteger(TypeLongIntegerContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeUnspecified(TypeUnspecifiedContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeLongUnspecified(TypeLongUnspecifiedContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypePointer(TypePointerContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
		@Override public Type visitTypeLongPointer(TypeLongPointerContext context) {
			logger.info("  {}", context.getClass().getName());
			return null;
		}
	};

	private static final SymbolBaseVisitor<Type> declTypeVisitor = new SymbolBaseVisitor<>() {
		@Override
		public Type visitTypeDecl(SymbolParser.TypeDeclContext context) {
			String name = context.name.getText();
			TypeTypeContext typeType = context.typeType();
			
			logger.info("TYPE   {} == {}", name, typeType.getText());
			logger.info("       {}", name, typeType.getText());
			
			
			typeVisitors.visit(typeType);
			
			return null;
		}
	};

	private static final SymbolBaseVisitor<Const> declConstVisitor = new SymbolBaseVisitor<>() {
		@Override
		public Const visitConstDecl(SymbolParser.ConstDeclContext context) {
//			declConst
//		    :   name=ID ':' constType  '=' constValue ';' # ConstDecl
//		    ;
			String name = context.name.getText();
			ConstTypeContext constType = context.constType();
			ConstValueContext constValue = context.constValue();
			
			logger.info("CONST  {}  {}  {}", name, constType.toStringTree(), constValue.toStringTree());

			return null;
		}
	};


	void build(SymbolContext tree) {
		List<Const> constList = new ArrayList<>();
		List<Type>  typeList  = new ArrayList<>();
		for(DeclContext e: tree.body().declList().elements) {
			constList.add(declConstVisitor.visit(e));
		}
		for(DeclContext e: tree.body().declList().elements) {
			typeList.add(declTypeVisitor.visit(e));
		}
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		{
			Symbol symbol = Symbol.getInstance(PATH_RULE_FILE);
			logger.info("symbol {}", symbol);
		}
		
		logger.info("STOP");
	}
}
