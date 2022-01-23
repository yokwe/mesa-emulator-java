package yokwe.majuro.symbol;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Symbol;
import yokwe.majuro.symbol.model.Symbol.Decl;
import yokwe.majuro.symbol.model.Symbol.DeclConstant;
import yokwe.majuro.symbol.model.Symbol.DeclType;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.Type.Kind;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Align;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.StringUtil;
import yokwe.majuro.util.AutoIndentPrintWriter.Layout;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	private static final String PACKAGE = "yokwe.majuro.type";
	private static final String PATH_OUTPUT_DIR = String.format("src/main/java/%s", PACKAGE.replace('.', '/'));
	
	/*

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
	
	private static void genConstant(DeclConstant constant) {
		genConstant(constant.value);
	}
	private static void genConstant(Constant constant) {
		logger.info("{}: {} = {}", constant.name, constant.type.toMesaType(), constant.valueString);
	}


	
	private static void genConstant(List<Decl> declList) {
		String fileName = "Constant.java";
		String path = String.format("%s/%s", PATH_OUTPUT_DIR, fileName);
		
		logger.info("genConstant {}", path);
		File file = new File(path);
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(new FileWriter(file)))) {
			out.println("package %s;", PACKAGE);
			out.println();
			out.println("public class Constant {");
			
			for(var e: declList) {
				if (e instanceof DeclConstant) {
					DeclConstant declConstant = (DeclConstant)e;
					Constant cons = declConstant.value;
					
					String javaType = toJavaType(cons.type.getRealType());
					String javaName = cons.name;
					
					out.println("// %s;", cons.toMesaType());
					out.println("public static final %s %s = %s;", javaType, javaName, cons.valueString);					
				}
			}
			out.println("}");
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	*/
	
	private static void genDecl(Type type) {
//		logger.info("{}: TYPE = {};", type.name, type.toMesaType());

		if (type instanceof TypeBoolean) {
			genDecl((TypeBoolean)type);
			return;
		}
		if (type instanceof TypeSubrange) {
			genDecl((TypeSubrange)type);
			return;
		}
		if (type instanceof TypeArray) {
			genDecl((TypeArray)type);
			return;
		}
		if (type instanceof TypeEnum) {
			genDecl((TypeEnum)type);
			return;
		}
		if (type instanceof TypeRecord) {
			genDecl((TypeRecord)type);
			return;
		}
		if (type instanceof TypePointer) {
			genDecl((TypePointer)type);
			return;
		}
		if (type instanceof TypePointer) {
			genDecl((TypePointer)type);
			return;
		}
		if (type instanceof TypeReference) {
			// DO NOTHING
			return;
		}
		
		logger.error("type {}  {}", type.name, type.kind);
		logger.error("mesa {}", type.toMesaType());
		throw new UnexpectedException("Unexpected");
	}
	
	private static void genDecl(TypeBoolean type) {
		// DO NOTHING
	}
	private static void genDecl(TypeSubrange type) {
		final String name = StringUtil.toJavaName(type.name);
		
		String path = String.format("%s/%s.java", PATH_OUTPUT_DIR, name);
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(path)) {
			out.println("package %s;", PACKAGE);
			out.println();
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println();
			out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
			out.println("public final class %s {", name);
			out.println("public static final String NAME = \"%s\";", name);
			out.println();
			
			out.println("public static final long MIN_VALUE  = %s;", StringUtil.toJavaString(type.minValue));
			out.println("public static final long MAX_VALUE  = %s;", StringUtil.toJavaString(type.maxValue));
			out.println("public static final long SIZE_VALUE = MAX_VALUE - MIN_VALUE + 1L;");
			out.println();
			
			out.println("private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);");
			out.println();
			
			out.println("public static final void checkValue(long value) {");
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);");
			out.println("}");
			
			// if this type is unsigned, ...
			if (0 <= type.minValue) {
				out.println("public static void checkValue(int value) {");
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));");
				out.println("}");
			}
			
			out.println("}"); // end of class
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	private static void genDecl(TypeArray type) {
		// FIXME
	}
	private static void genDecl(TypeEnum type) {
		final String name = StringUtil.toJavaName(type.name);
		
		String path = String.format("%s/%s.java", PATH_OUTPUT_DIR, name);
		
		List<String> itemList = type.itemList.stream().map(o -> StringUtil.toJavaConstName(o.name)).collect(Collectors.toList());
		String valueList = String.join(", ", itemList);
		String nameList  = String.join(", ", itemList.stream().map(o -> "\"" + o + "\"").collect(Collectors.toList()));

		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(path)) {
			out.println("package %s;", PACKAGE);
			out.println();
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println();
			out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
			out.println("public final class %s {", name);
			out.println("public static final String NAME = \"%s\";", name);
			out.println();
			
			out.prepareLayout();
			for(var e: type.itemList) {
				out.println("public static final char %s = %d;", StringUtil.toJavaConstName(e.name), e.value);
			}
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
			out.println();
			
			out.println("private static final int[] values = {");
			out.println("%s", valueList);
			out.println("};");
			out.println("private static final String[] names = {");
			out.println("%s", nameList);
			out.println("};");
			out.println("private static final EnumContext checkValue = new EnumContext(NAME, values, names);");
			out.println();

			out.println("public static final String toString(int value) {");
			out.println("return checkValue.toString(value);");
			out.println("}");
			out.println();
			out.println("public static final void checkValue(int value) {");
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);");
			out.println("}");
			
			out.println("}"); // end of class
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	private static void genDecl(TypeRecord type) {
		// FIXME
	}
	private static void genDecl(TypePointer type) {
		// FIXME
	}
	
	private static void genDecl(Constant cons) {
		// FIXME
		// logger.info("{}: {} = {}", cons.name, cons.type.toMesaType(), cons.valueString);
	}
	
	private static void genDecl(Decl decl) {
		if (decl instanceof DeclType) {
			genDecl(((DeclType)decl).value);
			return;
		}
		if (decl instanceof DeclConstant) {
			genDecl(((DeclConstant)decl).value);
			return;
		}
		throw new UnexpectedException("Unexpected");
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		logger.info("path {}", Symbol.PATH_RULE_FILE);
		Symbol symbol = Symbol.getInstance(Symbol.PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		for(var e: symbol.declList) {
			genDecl(e);
		}
		
		logger.info("STOP");
	}
}
