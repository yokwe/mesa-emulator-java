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
import yokwe.majuro.symbol.antlr.SymbolParser.DeclContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SymbolContext;
import yokwe.majuro.util.StringUtil;

public class Symbol {
	static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Symbol.class);
	
	public static final String  PATH_RULE_FILE_TYPE = "data/type/Type.symbol";
	public static final String  PATH_RULE_FILE_TEST = "data/type/Test.symbol";
	
	public abstract static class Decl {
		public Constant toCons() {
			if (this instanceof DeclConstant) {
				return ((DeclConstant)this).value;
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		public Type toType() {
			if (this instanceof DeclType) {
				return ((DeclType)this).value;
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
	}
	public static class DeclConstant extends Decl {
		public final Constant value;
		
		public DeclConstant(Constant value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return value.toMesaDecl();
		}
	}
	public static class DeclType extends Decl {
		public final Type value;
		
		public DeclType(Type value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return value.toMesaDecl();
		}
	}
	
	public static Symbol getInstance(String filePath, boolean addPredefinedType) {
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
			logger.error("%s %s", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
		
		final List<Decl> declList = new ArrayList<>();
		// build declList
		{
			if (addPredefinedType) {
				for(var e: Type.map.values()) {
					logger.info("add predefined type  %s", e.name);
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
		
		logger.info("cons %s", Constant.map.size());
		logger.info("type %s", Type.map.keySet().stream().filter(o -> !o.contains("#")).count());
		
		// sanity check
		{
			int needsFixCountCons = Constant.fixAll();
			int needsFixCountType = Type.fixAll();
			
			logger.info("needsFix cons %d", needsFixCountCons);
			logger.info("needsFix type %d", needsFixCountType);
			
			if (needsFixCountCons != 0) {
				for(var e: Constant.map.values()) {
					if (e.needsFix && e.type instanceof TypeReference) {
						logger.info("needsFix cons %s", e);
					}
				}
			}
			if (needsFixCountType != 0) {
				for(var e: Type.map.values()) {
					if (e.needsFix && e instanceof TypeReference) {
						logger.info("needsFix type %s", e);
					}
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
		logger.info("path %s", path);

		Symbol symbol = Symbol.getInstance(path, true);
		logger.info("symbol %s", symbol);
		
		for(var e: Constant.map.values()) {
			if (e.needsFix) logger.info("needsFix cons %s", e);
		}
		for(var e: Type.map.values()) {
			if (e.needsFix) logger.info("needsFix type %s", e);
		}
		
		logger.info("STOP");
	}
}
