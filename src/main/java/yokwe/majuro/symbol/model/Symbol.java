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
	
	public static Symbol getInstance(String filePath, boolean addSimpleType) {
		final SymbolContext symbolContext;
		// build symbolContext
		try {
			CharStream input = CharStreams.fromFileName(filePath);
			
			SymbolLexer       lexer   = new SymbolLexer(input);
			CommonTokenStream tokens  = new CommonTokenStream(lexer);
			
			tokens.fill();
			SymbolParser parser = new SymbolParser(tokens);
			// report error while parsing
			parser.setErrorHandler(new DefaultErrorStrategy());
			
			symbolContext = parser.symbol();
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
		
		final List<Decl> declList = new ArrayList<>();
		// build declList
		{
			if (addSimpleType) {
				for(var e: Type.map.values()) {
					logger.info("add bui type  {}", e.name);
					declList.add(new DeclType(e));
				}
			}
			
			logger.info("build constant and type");
			for(DeclContext e: symbolContext.body().declList().elements) {
				if (e.declConstant() != null) {
					Constant value = SymbolUtil.getConstant(e.declConstant());
					declList.add(new DeclConstant(value));
				}
				if (e.declType() != null) {
					Type value = SymbolUtil.getType(e.declType());
					declList.add(new DeclType(value));
				}
			}
		}
		
		Symbol symbol = new Symbol(symbolContext.header().name.getText(), declList);
		
		logger.info("cons {}", Constant.map.size());
		logger.info("type {}", Type.map.keySet().stream().filter(o -> !o.contains("#")).count());
		
		// sanity check
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
		
		return symbol;
	}
	
	
	public final String     name;
	public final List<Decl> declList;
	
	private Symbol(String name, List<Decl> declList) {
		this.name     = name;
		this.declList = declList;
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	
	public static void main(String[] args) {
		logger.info("START");
		
		String path = PATH_RULE_FILE_TYPE;
//		String path = PATH_RULE_FILE_TEST;
		logger.info("path {}", path);

		Symbol symbol = Symbol.getInstance(path, true);
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
