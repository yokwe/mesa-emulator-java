package yokwe.majuro.symbol.model;

import java.io.IOException;
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
import yokwe.majuro.symbol.antlr.SymbolParser.DeclContext;
import yokwe.majuro.symbol.antlr.SymbolParser.RangeTypeContext;
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
	
	@Override
	public String toString() {
		return String.format("{%s %d %d}", name, constMap.size(), typeMap.size());
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
	
	private static class TypeVisitors extends SymbolBaseVisitor<Type> {
		public final String name;
		
		public TypeVisitors(String name) {
			this.name = name;
		}
		
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
			RangeTypeContext rangeTypeContext = context.rangeType();
			if (rangeTypeContext != null) {
				String typeName = rangeTypeContext.name.getText();
				
				return new TypeSubrangeFull(name, typeName);
			} else {
				logger.error("Unexpected rangeTypeContext");
				logger.error("  context {}", context.getText());
				throw new UnexpectedException("Unexpected rangeTypeContext");
			}
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
			String baseName = context.name.getText();
			return new TypeReference(name, baseName);
		}
		
		// SIMPLE
		@Override public Type visitTypeBoolean(TypeBooleanContext context) {
			return new TypeReference(name, Type.BOOL);
		}
		@Override public Type visitTypeCardinal(TypeCardinalContext context) {
			return new TypeReference(name, Type.CARDINAL);
		}
		@Override public Type visitTypeLongCardinal(TypeLongCardinalContext context) {
			return new TypeReference(name, Type.LONG_CARDINAL);
		}
		@Override public Type visitTypeInteger(TypeIntegerContext context) {
			return new TypeReference(name, Type.INTEGER);
		}
		@Override public Type visitTypeLongInteger(TypeLongIntegerContext context) {
			return new TypeReference(name, Type.LONG_INTEGER);
		}
		@Override public Type visitTypeUnspecified(TypeUnspecifiedContext context) {
			return new TypeReference(name, Type.UNSPECIFIED);
		}
		@Override public Type visitTypeLongUnspecified(TypeLongUnspecifiedContext context) {
			return new TypeReference(name, Type.LONG_UNSPECIFIED);
		}
		@Override public Type visitTypePointer(TypePointerContext context) {
			return new TypeReference(name, Type.POINTER);
		}
		@Override public Type visitTypeLongPointer(TypeLongPointerContext context) {
			return new TypeReference(name, Type.LONG_POINTER);
		}
	}

	private static final SymbolBaseVisitor<Type> declTypeVisitor = new SymbolBaseVisitor<>() {
		@Override
		public Type visitTypeDecl(SymbolParser.TypeDeclContext context) {
			String name = context.name.getText();
			TypeTypeContext typeType = context.typeType();
			
			logger.info("TYPE   {} == {}", name, typeType.getText());
			logger.info("       {}", name, typeType.getText());
			
			TypeVisitors typeVisitors = new TypeVisitors(name);
			
			Type type = typeVisitors.visit(typeType);
			logger.info("       {}", type);
			
			return type;
		}
	};

	private static final SymbolBaseVisitor<Const> declConstVisitor = new SymbolBaseVisitor<>() {
		@Override
		public Const visitConstDecl(SymbolParser.ConstDeclContext context) {
			String name      = context.name.getText();
			String typeName  = context.constType().getText();
			String value     = context.constValue().getText();			
			return new Const(name, typeName, value);
		}
	};


	void build(SymbolContext tree) {
		for(DeclContext e: tree.body().declList().elements) {
			Const v = declConstVisitor.visit(e);
			if (v != null) this.constMap.put(v.name, v);
		}
		for(DeclContext e: tree.body().declList().elements) {
			Type v = declTypeVisitor.visit(e);
			if (v != null) this.typeMap.put(v.name, v);
		}
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		Symbol symbol = Symbol.getInstance(PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		Type.fixAll();
		
		Type.stats();
		
		logger.info("STOP");
	}
}
