package yokwe.majuro.symbol.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.SymbolParser.*;
import yokwe.majuro.util.StringUtil;

public class Symbol {
	static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Symbol.class);
	
	public static final String  PATH_RULE_FILE_TYPE = "data/type/Type.symbol";
	public static final String  PATH_RULE_FILE_TEST = "data/type/Test.symbol";
	
	public static Symbol getInstance(String filePath) {
		try {
			CharStream input = CharStreams.fromFileName(filePath);
			
			SymbolLexer       lexer   = new SymbolLexer(input);
			CommonTokenStream tokens  = new CommonTokenStream(lexer);
			
			tokens.fill();
			
			SymbolParser parser = new SymbolParser(tokens);
			parser.setErrorHandler(new DefaultErrorStrategy());
			
			SymbolContext tree = parser.symbol();
			
			Symbol symbol = new Symbol(tree);
			symbol.build(tree);
			
			return symbol;
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	
	public abstract static class Decl {
		public final String name;
		
		public Decl(String name) {
			this.name = name;
		}
	}
	public static class DeclConstant extends Decl {
		public final Constant value;
		
		public DeclConstant(Constant value) {
			super(value.name);
			this.value = value;
		}
		@Override
		public String toString() {
			return value.toMesaType();
		}
	}
	public static class DeclType extends Decl {
		public final Type value;
		
		public DeclType(Type value) {
			super(value.name);
			this.value = value;
		}
		@Override
		public String toString() {
			return value.toMesaType();
		}
	}
	
	public final String name;
	public final List<Decl> declList;
	
	private Symbol(SymbolContext tree) {
		this.name = tree.header().name.getText();
		this.declList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	public void build(SymbolContext tree) {
		logger.info("build START");
		
		// append builtin types
		declList.add(new DeclType(Type.BOOLELAN));
		declList.add(new DeclType(Type.INTEGER));
		declList.add(new DeclType(Type.CARDINAL));
		declList.add(new DeclType(Type.LONG_CARDINAL));
		declList.add(new DeclType(Type.UNSPECIFIED));
		declList.add(new DeclType(Type.LONG_UNSPECIFIED));
		declList.add(new DeclType(Type.POINTER));
		declList.add(new DeclType(Type.LONG_POINTER));
		
		logger.info("build constant and type");
		for(DeclContext e: tree.body().declList().elements) {
			if (e.declConstant() != null) {
				Constant value = getConstant(e.declConstant());
				declList.add(new DeclConstant(value));
			}
			if (e.declType() != null) {
				Type value = getType(e.declType());
				declList.add(new DeclType(value));
			}
		}
		
		logger.info("cons {}", Constant.map.size());
		logger.info("type {}", Type.map.keySet().stream().filter(o -> !o.contains("#")).count());
		
		{
			int needsFixCountCons = Constant.fixAll();
			int needsFixCountType = Type.fixAll();
			
			logger.info("needsFix cons {}", needsFixCountCons);
			logger.info("needsFix type {}", needsFixCountType);
			
			if (needsFixCountCons != 0) {
				for(var e: Constant.map.values()) {
					logger.info("needsFix cons {}", e);
				}
			}
			if (needsFixCountType != 0) {
				for(var e: Type.map.values()) {
					logger.info("needsFix type {}", e);
				}
			}
			
			if (needsFixCountCons != 0 || needsFixCountType != 0) {
				logger.error("Unexpected");
				throw new UnexpectedException("Unexpected");
			}
		}
		
		logger.info("build STOP");
	}
	
	
	//
	// Constant
	//
	private Constant getConstant(DeclConstantContext declConstant) {
		String name = declConstant.name.getText();
		String value = String.join(".", declConstant.constantValue().name.stream().map(o -> o.getText()).toArray(String[]::new));

		ConstantTypeContext type = declConstant.constantType();
		if (type.constantTypeNumeric() != null) {
			return getConstant(name, type.constantTypeNumeric(), value);
		}
		if (type.pointerType() != null) {
			return getConstant(name, type.pointerType(), value);			
		}
		
		logger.error("Unexpected");
		logger.error("  declConstant  {}", declConstant.getText());
		throw new UnexpectedException("Unexpected");
	}
	private Constant getConstant(String name, ConstantTypeNumericContext type, String value) {
		// FIXME
		if (type instanceof ConstantTypeNumericCARDINALContext) {
			return new Constant(name, Type.CARDINAL, value);
		}
		
		logger.error("Unexpected");
		logger.error("  name  {}", name);
		logger.error("  type  {}", type);
		logger.error("  value {}", value);
		throw new UnexpectedException("Unexpected");
	}
	private Constant getConstant(String name, PointerTypeContext type, String value) {
		// FIXME
		Type pointerType = SymbolUtil.getType(name + "#pointer", type);
		return new Constant(name, pointerType, value);
	}
	
	
	//
	// Type
	//
	private Type getType(DeclTypeContext declType) {
		String name = declType.name.getText();
		TypeTypeContext type = declType.typeType();
		
		if (type.simpleType() != null) {
			return SymbolUtil.getType(name, type.simpleType());
		}
		if (type.referenceType() != null) {
			return SymbolUtil.getType(name, type.referenceType());
		}
		if (type.pointerType() != null) {
			return SymbolUtil.getType(name, type.pointerType());
		}
		if (type.subrangeType() != null) {
			return SymbolUtil.getType(name, type.subrangeType());
		}
		if (type.enumType() != null) {
			return SymbolUtil.getType(name, type.enumType());
		}
		if (type.arrayType() != null) {
			return SymbolUtil.getType(name, type.arrayType());
		}
		if (type.recordType() != null) {
			return SymbolUtil.getType(name, type.recordType());
		}

		logger.error("Unexpected");
		logger.error("  declType  {}", declType.getText());
		throw new UnexpectedException("Unexpected");
	}
	
	
	public static void main(String[] args) {
		logger.info("START");
		
		String path = PATH_RULE_FILE_TYPE;
//		String path = PATH_RULE_FILE_TEST;
		logger.info("path {}", path);

		Symbol symbol = Symbol.getInstance(path);
		logger.info("symbol {}", symbol);
		
		for(var e: Constant.map.values()) {
			if (e.needsFix) logger.info("needsFix cons {}", e);
		}
		for(var e: Type.map.values()) {
			if (e.needsFix) logger.info("needsFix type {}", e);
		}
		
		logger.info("STOP");
	}
}
