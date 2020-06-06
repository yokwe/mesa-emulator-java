package yokwe.majuro.symbol;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Symbol;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypeEnum.Element;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.util.AutoIndentPrintWriter;
import yokwe.util.StringUtil;

public class GenerateType {
	private static final Logger logger = LoggerFactory.getLogger(GenerateType.class);
	
	static final String PATH_DIR = "src/yokwe/majuro/mesa/type";

	private static void sanityCheck(Symbol symbol) {
		// check needsFix() of Type and Constant
		{
			boolean foundProblem = false;
			for(Type e: Type.map.values()) {
				if (e.needsFix()) {
					foundProblem = true;
					switch(e.kind) {
					case SUBRANGE:
						logger.info("needs fix {}", String.format("%-24s %-10s %s", e.name, e.kind, ((TypeSubrange)e).baseType.baseName));
						break;
					case REFERENCE:
						logger.info("needs fix {}", String.format("%-24s %-10s %s", e.name, e.kind, ((TypeReference)e).baseName));
						break;
					default:
						logger.info("needs fix {}", String.format("%-24s %-10s", e.name, e.kind));
						break;
					}
				}
			}
			for(Constant e: Constant.map.values()) {
				if (e.needsFix()) {
					foundProblem = true;
					logger.info("{}", String.format("%-14s %-10s %s", e.name, e.typeName, e.stringValue));
				}
			}
			if (foundProblem) {
				logger.error("has problem that needs fix");
				throw new UnexpectedException("has problem that needs fix");
			}
		}
		{
			for(Type e: Type.map.values()) {
				if (e.isReference()) continue;
				if (e.isPredefined()) continue;
				
				switch(e.kind) {
				case SUBRANGE:
				{
					// FIXME
					TypeSubrange typeSubrange = (TypeSubrange)e;
					
				}
					break;
				case ARRAY:
				{
					// FIXME
					TypeArray typeSubrange = (TypeArray)e;
					
				}
					break;
				case ENUM:
				{
					// FIXME
					TypeEnum typeEnum = (TypeEnum)e;
					
				}
					break;
				case RECORD:
				{
					// FIXME
					TypeRecord typeRecord = (TypeRecord)e;
					
				}
					break;
				default:
					throw new UnexpectedException();
				}
				
			}
		}
	}
	
	private static void genType(TypeSubrange typeSubrange) {
		String path = String.format("%s/%s.java", PATH_DIR, typeSubrange.name);
		logger.info("path {}",path);
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			out.println("import org.slf4j.Logger;");
			out.println("import org.slf4j.LoggerFactory;");
			out.println();
			out.println("import yokwe.majuro.UnexpectedException;");
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println();
			
			out.println("//");
			out.println("// %s", typeSubrange.toMesaType());
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeSubrange.name);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeSubrange.name);
			out.println();
			out.println("public static final int SIZE      = %d;", typeSubrange.getSize());
			out.println();
			
			switch(typeSubrange.getSize()) {
			case 1:
				out.println("public static final int VALUE_MIN = %d;", typeSubrange.getValueMin());
				out.println("public static final int VALUE_MAX = %d;", typeSubrange.getValueMax());
				out.println("public static final int LENGTH    = VALUE_MAX - VALUE_MIN + 1;");
				out.println();
				out.println("public static void checkRange(int value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("if (VALUE_MIN <= value && value <= VALUE_MAX) return;");
				out.println("logger.error(\"value is out of range\");");
				out.println("logger.error(\"  value {}\", value);");
				out.println("throw new UnexpectedException(\"Out of range\");");
				out.println("}");
				out.println("}");
				break;
			case 2:
				out.println("public static final long VALUE_MIN = %dL;", typeSubrange.getValueMin());
				out.println("public static final long VALUE_MAX = %dL;", typeSubrange.getValueMax());
				out.println("public static final long LENGTH    = VALUE_MAX - VALUE_MIN + 1;");
				out.println();
				out.println("public static void checkRange(long value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("if (VALUE_MIN <= value && value <= VALUE_MAX) return;");
				out.println("logger.error(\"value is out of range\");");
				out.println("logger.error(\"  value {}\", value);");
				out.println("throw new UnexpectedException(\"Out of range\");");
				out.println("}");
				out.println("}");
				break;
			default:
				throw new UnexpectedException();
			}
						
			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	private static void genType(TypeArray typeArray) {
		String path = String.format("%s/%s.java", PATH_DIR, typeArray.name);
		logger.info("path {}",path);
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			out.println("import org.slf4j.Logger;");
			out.println("import org.slf4j.LoggerFactory;");
			out.println();
			out.println("import yokwe.majuro.UnexpectedException;");
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println("import yokwe.majuro.mesa.Memory;");
			out.println();
			
			out.println("//");
			out.println("// %s", typeArray.toMesaType());
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeArray.name);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeArray.name);
			out.println();
			
			switch(typeArray.arrayKind) {
			case OPEN:
				out.println("public static final int SIZE         = %d;", typeArray.getSize());
				out.println("public static final int LENGTH       = 0;");
				out.println("public static final int ELEMENT_SIZE = %d;", typeArray.elementType.getSize());
				out.println("public static final int INDEX_MIN    = %s.VALUE_MIN;", typeArray.indexType.baseName);
				out.println("public static final int INDEX_MAX    = %s.VALUE_MAX;", typeArray.indexType.baseName);
				break;
			case SUBRANGE:
				out.println("public static final int SIZE         = %d;", typeArray.getSize());
				out.println("public static final int LENGTH       = %d;", typeArray.length);
				out.println("public static final int ELEMENT_SIZE = %d;", typeArray.elementType.getSize());
				out.println("public static final int INDEX_MIN    = %d;", typeArray.rangeMin);
				out.println("public static final int INDEX_MAX    = %d;", typeArray.rangeMax);
				break;
			case FULL:
				out.println("public static final int SIZE         = %d;", typeArray.getSize());
				out.println("public static final int LENGTH       = %d;", typeArray.length);
				out.println("public static final int ELEMENT_SIZE = %d;", typeArray.elementType.getSize());
				out.println("public static final int INDEX_MIN    = %s.VALUE_MIN;", typeArray.indexType.baseName);
				out.println("public static final int INDEX_MAX    = %s.VALUE_MAX;", typeArray.indexType.baseName);
				break;
			default:
				throw new UnexpectedException();
			}
			out.println();
			
			out.println("public static int getAddress(int base, int index) {");
			out.println("checkIndex(index);");
			out.println("return base + (index * ELEMENT_SIZE);");
			out.println("}");

			out.println("public static int get(int base, int index) {");
			out.println("checkIndex(index);");
			out.println("return Memory.fetch(getAddress(base, index));");
			out.println("}");

			out.println("public static void set(int base, int index, int newValue) {");
			out.println("checkIndex(index);");
			out.println("checkValue(newValue);");
			out.println("Memory.store(getAddress(base, index), newValue);");
			out.println("}");

			
			out.println("public static void checkIndex(int index) {");
			out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
			out.println("if (INDEX_MIN <= index && index <= INDEX_MAX) return;");
			out.println("logger.error(\"index is out of range\");");
			out.println("logger.error(\"  index {}\", index);");
			out.println("throw new UnexpectedException(\"index is out of range\");");
			out.println("}");
			out.println("}");

			out.println("public static void checkValue(int value) {");
			if (typeArray.elementType.isSubrange()) {
				out.println("%s.checkRange(value);", typeArray.elementType.baseName);
			} else {
				out.println("// array element is not subrange");
//				switch(typeArray.elementType.getSize()) {
//				case 1:
//					out.println("UNSPECIFIED.checkRange(value);");
//					break;
//				case 2:
//					out.println("LONG_UNSPECIFIED.checkRange(value);");
//					break;
//				default:
//					logger.error("element {}", typeArray.elementType.baseType);
//					logger.error("        {}", typeArray.elementType.getSize());
//					throw new UnexpectedException();
//				}
			}
			out.println("}");
						
			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	private static void genType(TypeEnum typeEnum) {
		String path = String.format("%s/%s.java", PATH_DIR, typeEnum.name);
		logger.info("path {}",path);
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			out.println("import org.slf4j.Logger;");
			out.println("import org.slf4j.LoggerFactory;");
			out.println();
			out.println("import yokwe.majuro.UnexpectedException;");
			out.println();
			
			out.println("//");
			out.println("// %s", typeEnum.toMesaType());
			out.println("//");
			out.println();

			out.println("public enum %s {", typeEnum.name);
			
			int elementSize = typeEnum.elementList.size();
			for(int i = 0; i < elementSize; i++) {
				Element element = typeEnum.elementList.get(i);
				String sep = (i == (elementSize - 1)) ? ";" : ",";
				
				out.println("%-16s (%d)%s", StringUtil.toJavaConstName(element.name), element.value, sep);
				
			}
			out.println();
			
			out.println("public static final int SIZE      = %d;", typeEnum.getSize());
			out.println();
			out.println("public static final int VALUE_MIN = %d;", typeEnum.valueMin);
			out.println("public static final int VALUE_MAX = %d;", typeEnum.valueMax);
			out.println("public static final int LENGTH    = VALUE_MAX - VALUE_MIN + 1;");
			out.println();
			out.println("public static %s getInstance(int value) {", typeEnum.name);
			out.println("for(%1$s e: %1$s.values()) {", typeEnum.name);
			out.println("if (e.value == value) return e;");
			out.println("}");
			out.println("Logger logger = LoggerFactory.getLogger(XferType.class);");
			out.println("logger.error(\"Unexpected value = {}\", value);");
			out.println("throw new UnexpectedException();");
			out.println("}");
			out.println();
			out.println("public final int value;");
			out.println();

			out.println("private %s(int value) {", typeEnum.name);
			out.println("this.value = value;");
			out.println("}");

			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	private static void genType(TypeRecord typeRecord) {
		// FIXME
	}

	private static void genType()	{
		for(Type e: Type.map.values()) {
			if (e.isReference()) continue;
			if (e.name.contains("#")) continue;
			
			logger.info("    {}", e.toMesaType());
			switch(e.kind) {
			case BOOL:
				break;
			case SUBRANGE:
				genType((TypeSubrange)e);
				break;
			case ARRAY:
				genType((TypeArray)e);
				break;
			case ENUM:
				genType((TypeEnum)e);
				break;
			case RECORD:
				genType((TypeRecord)e);
				break;
			default:
				logger.error("e {}", e);
				throw new UnexpectedException();
			}
			
		}
	}
	public static void main(String[] args) {
		logger.info("START");

		Symbol symbol = Symbol.getInstance(Symbol.PATH_RULE_FILE);
		
		// Sanity check
		sanityCheck(symbol);
		
		genType();
		

		logger.info("STOP");
	}

}
