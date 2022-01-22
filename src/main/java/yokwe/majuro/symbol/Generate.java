package yokwe.majuro.symbol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Symbol;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.Type.Kind;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Align;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.StringUtil;
import yokwe.majuro.util.AutoIndentPrintWriter.Layout;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	private static final String PACKAGE = "yokwe.majuro.type";
	private static final String PATH_OUTPUT_DIR = String.format("src/main/java/%s", PACKAGE.replace('.', '/'));
	
	private static final Map<Type, String> simpleTypeToJapaMap = new TreeMap<>();
	static {
		simpleTypeToJapaMap.put(Type.BOOLELAN,         "boolean");
		simpleTypeToJapaMap.put(Type.INTEGER,          "short");
		
		simpleTypeToJapaMap.put(Type.CARDINAL,         "char");
		simpleTypeToJapaMap.put(Type.LONG_CARDINAL,    "int");
		
		simpleTypeToJapaMap.put(Type.UNSPECIFIED,      "char");
		simpleTypeToJapaMap.put(Type.LONG_UNSPECIFIED, "int");
		
		simpleTypeToJapaMap.put(Type.POINTER,          "char");
		simpleTypeToJapaMap.put(Type.LONG_POINTER,     "int");
	}
	
	private static String toJavaType(Type type) {
		Type realType = type.getRealType();

		if (simpleTypeToJapaMap.containsKey(realType)) {
			return simpleTypeToJapaMap.get(realType);
		}
		
		if (realType.kind == Kind.RECORD) {
			TypeRecord typeRecord = (TypeRecord)realType;
			if (typeRecord.align == Align.BIT_16 && typeRecord.bitSize() == 16) {
				return "char";
			}
			if (typeRecord.align == Align.BIT_32 && typeRecord.bitSize() == 32) {
				return "int";
			}
		}
		if (realType.kind == Kind.POINTER) {
			TypePointer typePointer = (TypePointer)realType;
			switch(typePointer.size) {
			case SHORT:
				return "char";
			case LONG:
				return "int";
			default:
				break;
			}
		}

		logger.error("Unexpected");
		logger.error("  type     {}", type);
		logger.error("  realType {}", realType);
		throw new UnexpectedException("Unexpected");
	}
	
	private static void genConstant(Symbol symbol) {
		String fileName = "Constant.java";
		String path = String.format("%s/%s", PATH_OUTPUT_DIR, fileName);
		
		logger.info("genConstant {}", path);
		File file = new File(path);
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(new FileWriter(file)))) {
			out.println("package %s;", PACKAGE);
			out.println();
			out.println("public class Constant {");
			
			for(var e: Type.map.values()) {
				if (e.name.contains("#")) continue;
				out.println("// %s: TYPE = %s;", e.name, e.toMesaType());
			}
			
			out.prepareLayout();
			for(var e: Constant.map.values()) {
				String name = e.name;
				Type type = e.type.getRealType();
				long numericValue = e.numericValue;
				
				String javaType = toJavaType(type);
				String javaName = name;
								
				out.println("public static %s %s = %d;", javaType, javaName, numericValue);
			}
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);				

			
			out.println("}");
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	
	
	
	public static void main(String[] args) {
		logger.info("START");
		
		logger.info("path {}", Symbol.PATH_RULE_FILE);
		Symbol symbol = Symbol.getInstance(Symbol.PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		genConstant(symbol);
		
		logger.info("STOP");
	}
}
