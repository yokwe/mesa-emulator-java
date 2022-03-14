package yokwe.majuro.symbol.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.JavaDecl;
import yokwe.majuro.symbol.antlr.SymbolLexer;
import yokwe.majuro.symbol.antlr.SymbolParser;
import yokwe.majuro.symbol.antlr.SymbolParser.DeclContext;
import yokwe.majuro.symbol.antlr.SymbolParser.SymbolContext;
import yokwe.majuro.util.StringUtil;

public class Symbol implements Comparable<Symbol> {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
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
	
	public static List<Symbol> getSymbolList(String dirPath) {
		List<Symbol> ret = new ArrayList<>();
		
		File dir = new File(dirPath);
		File[] files = dir.listFiles(f -> (f.isFile() && f.getName().endsWith(".symbol")));
		for(var file : files) {
			String filePath = file.getAbsolutePath();
			logger.info("filePath %s", filePath);
			
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

			final String module = symbolContext.header().name.getText();
			final List<Decl> declList = new ArrayList<>();
			
			if (module.equals("PrincOps")) {
				declList.add(new DeclType(Type.BOOLEAN));
				declList.add(new DeclType(Type.INTEGER));
				declList.add(new DeclType(Type.CARDINAL));
				declList.add(new DeclType(Type.UNSPECIFIED));
				declList.add(new DeclType(Type.LONG_CARDINAL));
				declList.add(new DeclType(Type.LONG_UNSPECIFIED));
				declList.add(new DeclType(Type.POINTER));
				declList.add(new DeclType(Type.LONG_POINTER));
			}
			
			for(DeclContext e: symbolContext.body().declList().elements) {
				if (e.declConstant() != null) {
					Constant value = SymbolUtil.getConstant(module, e.declConstant());
					declList.add(new DeclConstant(value));
				}
				if (e.declType() != null) {
					Type value = SymbolUtil.getType(module, e.declType());
					declList.add(new DeclType(value));
				}
			}

			Symbol symbol = new Symbol(module, declList);
			ret.add(symbol);
		}
		
		logger.info("cons %s", Constant.map.size());
		logger.info("type %s", Type.map.keySet().stream().filter(o -> !o.contains("#")).count());
		
		// sanity check
		{
			int needsFixCountCons = Constant.fixAll();
			int needsFixCountType = Type.fixAll();
			
			logger.info("needsFix cons %d", needsFixCountCons);
			logger.info("needsFix type %d", needsFixCountType);
			
			for(var e: Constant.map.values()) {
				if (e.needsFix && e.type instanceof TypeReference) {
					logger.info("needsFix cons %s", e);
				}
			}
			for(var e: Type.map.values()) {
				if (e.needsFix && e instanceof TypeReference) {
					logger.info("needsFix type %s", e);
				}
			}
			
			if (needsFixCountCons != 0 || needsFixCountType != 0) {
				logger.error("Unexpected");
				throw new UnexpectedException("Unexpected");
			}
		}
		
		Collections.sort(ret);
		return ret;
	}
	
	public final String     module;
	public final List<Decl> declList;
	
	private Symbol(String module, List<Decl> declList) {
		this.module   = module;
		this.declList = declList;
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}
	
	@Override
	public int compareTo(Symbol that) {
		return this.module.compareTo(that.module);
	}
	
	
	public static void generate(String dirPath, String outputDirPath, String packageName) {
		List<Symbol> symbolList = Symbol.getSymbolList(dirPath);
		
		for(var symbol: symbolList) {
			logger.info("Symbol  %-16s  %3d", symbol.module, symbol.declList.size());
			JavaDecl.generateFile(symbol, outputDirPath, packageName);
		}
	}
	

	public static void main(String[] args) {
		logger.info("START");
		
		generate("data/type/main", "src/main/java", "yokwe.majuro.type");
		generate("data/type/test", "src/test/java", "yokwe.majuro.type");
		
		logger.info("STOP");
	}
}
