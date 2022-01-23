package yokwe.majuro.symbol;

import java.io.File;
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
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.AutoIndentPrintWriter.Layout;
import yokwe.majuro.util.StringUtil;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	private static final String PACKAGE = "yokwe.majuro.type";
	private static final String PATH_OUTPUT_DIR = String.format("src/main/java/%s", PACKAGE.replace('.', '/'));
	
	
	private static void genDecl(Type type) {
//		logger.info("{}: TYPE = {};", type.name, type.toMesaType());
		
		String  name             = StringUtil.toJavaName(type.name);
		String  path             = String.format("%s/%s.java", PATH_OUTPUT_DIR, name);
		File    ouputFile        = new File(path);
		boolean deleteOutputFile = false;
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(ouputFile)) {
			out.println("package %s;", PACKAGE);
			out.println();
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println();
			out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
			out.println("public final class %s {", name);
			out.println("public static final String NAME = \"%s\";", name);
			out.println();
			
			switch(type.kind) {
			case BOOLEAN:
				deleteOutputFile = true;
				break;
			case SUBRANGE:
				genDecl(out, (TypeSubrange)type);
				break;
			case ARRAY:
				genDecl(out, (TypeArray)type);
				break;
			case ENUM:
				genDecl(out, (TypeEnum)type);
				break;
			case RECORD:
				genDecl(out, (TypeRecord)type);
				break;
			case POINTER:
				genDecl(out, (TypePointer)type);
				break;
			case REFERENCE:
				deleteOutputFile = true;
				break;
			default:
				logger.error("type {}  {}", type.name, type.kind);
				logger.error("mesa {}", type.toMesaType());
				throw new UnexpectedException("Unexpected");
			}
			
			out.println("}"); // end of class
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
		
		if (deleteOutputFile) {
			ouputFile.delete();
		}
	}
	
	private static void genDecl(AutoIndentPrintWriter out, TypeSubrange type) {
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
	}
	private static void genDecl(AutoIndentPrintWriter out, TypeArray type) {
		// FIXME
	}
	private static void genDecl(AutoIndentPrintWriter out, TypeEnum type) {
		List<String> itemList = type.itemList.stream().map(o -> StringUtil.toJavaConstName(o.name)).collect(Collectors.toList());
		String valueList = String.join(", ", itemList);
		String nameList  = String.join(", ", itemList.stream().map(o -> "\"" + o + "\"").collect(Collectors.toList()));

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
	}
	private static void genDecl(AutoIndentPrintWriter out, TypeRecord type) {
		// FIXME
	}
	private static void genDecl(AutoIndentPrintWriter out, TypePointer type) {
		// FIXME
	}
	
	private static void genDecl(Constant cons) {
		// FIXME
	}
	
	private static void genDecl(Decl decl) {
		if (decl instanceof DeclType) {
			genDecl(((DeclType)decl).value);
		} else if (decl instanceof DeclConstant) {
			genDecl(((DeclConstant)decl).value);
		} else {
			throw new UnexpectedException("Unexpected");
		}
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
