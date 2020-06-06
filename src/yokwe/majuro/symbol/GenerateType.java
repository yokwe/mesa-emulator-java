package yokwe.majuro.symbol;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Memory;
import yokwe.majuro.mesa.Type.CARD16;
import yokwe.majuro.mesa.type.ExtraGlobalWord;
import yokwe.majuro.mesa.type.LONG_POINTER;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Field;
import yokwe.majuro.symbol.model.Select;
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
		String typeName = typeSubrange.name;
		String path = String.format("%s/%s.java", PATH_DIR, typeName);
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
			out.println("// %s", typeSubrange.toMesaString());
			if (typeSubrange.baseType.isReference() && !typeSubrange.baseType.baseType.isPredefined()) {
				out.println("// %s: TYPE = %s;", typeSubrange.baseType.baseName, typeSubrange.baseType.baseType.toMesaType());
			}
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeName);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeName);
			out.println();
			out.println("public static final int SIZE      = %d;", typeSubrange.getSize());
			out.println();
			
			switch(typeSubrange.getSize()) {
			case 1:
				out.println("public static final int VALUE_MIN = %d;", typeSubrange.getValueMin());
				out.println("public static final int VALUE_MAX = %d;", typeSubrange.getValueMax());
				out.println("public static final int LENGTH    = VALUE_MAX - VALUE_MIN + 1;");
				out.println();
				
				out.println("public static int get(int base) {");
				out.println("int ret = Memory.fetch(base);");
				out.println("checkValue(ret);");
				out.println("return ret;");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("checkValue(newValue);");
				out.println("Memory.store(base, newValue);");
				out.println("}");
				out.println();

				out.println("public static void checkValue(int value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("if (VALUE_MIN <= value && value <= VALUE_MAX) return;");
				out.println("logger.error(\"value is out of range\");");
				out.println("logger.error(\"  value {}\", value);");
				out.println("throw new UnexpectedException(\"value is out of range\");");
				out.println("}");
				out.println("}");
				out.println();
				
				break;
			case 2:
				out.println("public static final long VALUE_MIN = %dL;", typeSubrange.getValueMin());
				out.println("public static final long VALUE_MAX = %dL;", typeSubrange.getValueMax());
				out.println("public static final long LENGTH    = VALUE_MAX - VALUE_MIN + 1;");
				out.println();
				
				out.println("public static int get(int base) {");
				out.println("int ret = Memory.readDbl(base);");
				out.println("checkValue(ret);");
				out.println("return ret;");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("checkValue(newValue);");
				out.println("Memory.writeDbl(base, newValue);");
				out.println("}");
				out.println();

				out.println("public static void checkValue(int value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("if (VALUE_MIN <= value && value <= VALUE_MAX) return;");
				out.println("logger.error(\"value is out of range\");");
				out.println("logger.error(\"  value {}\", value);");
				out.println("throw new UnexpectedException(\"value is out of range\");");
				out.println("}");
				out.println("}");
				out.println();
				

				out.println("public static void checkValue(long value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("if (VALUE_MIN <= value && value <= VALUE_MAX) return;");
				out.println("logger.error(\"value is out of range\");");
				out.println("logger.error(\"  value {}\", value);");
				out.println("throw new UnexpectedException(\"value is out of range\");");
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
		String typeName = typeArray.name;
		String path = String.format("%s/%s.java", PATH_DIR, typeName);
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
			out.println("// %s", typeArray.toMesaString());
			if (typeArray.indexType.isReference()) {
				out.println("// %s: TYPE = %s;", typeArray.indexType.baseName, typeArray.indexType.baseType.toMesaType());
			}
			if (typeArray.elementType.isReference()) {
				out.println("// %s: TYPE = %s;", typeArray.elementType.baseName, typeArray.elementType.baseType.toMesaType());
			}
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeName);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeName);
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
			
			switch (typeArray.elementType.baseType.kind) {
			case SUBRANGE:
			case ENUM:
				out.println("public static int get(int base, int index) {");
				out.println("return Memory.fetch(getAddress(base, index));");
				out.println("}");

				out.println("public static void set(int base, int index, int newValue) {");
				out.println("checkValue(newValue);");
				out.println("Memory.store(getAddress(base, index), newValue);");
				out.println("}");
				
				out.println("public static void checkValue(int value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("%s.checkValue(value);", typeArray.elementType.baseType.name);
				out.println("}");
				out.println("}");
				break;
			case RECORD:
				// FIXME expand record field
				break;
			default:
				throw new UnexpectedException();
			}

			out.println("public static void checkIndex(int index) {");
			out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
			out.println("if (INDEX_MIN <= index && index <= INDEX_MAX) return;");
			out.println("logger.error(\"index is out of range\");");
			out.println("logger.error(\"  index {}\", index);");
			out.println("throw new UnexpectedException(\"index is out of range\");");
			out.println("}");
			out.println("}");

			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	private static void genType(TypeEnum typeEnum) {
		String typeName = typeEnum.name;
		String path = String.format("%s/%s.java", PATH_DIR, typeName);
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
			out.println("// %s", typeEnum.toMesaString());
			out.println("//");
			out.println();

			out.println("public enum %s {", typeName);
			
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
			
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeName);
			out.println();

			out.println("public static void checkValue(int value) {");
			out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
			out.println("if (VALUE_MIN <= value && value <= VALUE_MAX) return;");
			out.println("logger.error(\"value is out of range\");");
			out.println("logger.error(\"  value {}\", value);");
			out.println("throw new UnexpectedException(\"value is out of range\");");
			out.println("}");
			out.println("}");
			out.println();

			out.println("public static %s getInstance(int value) {", typeName);
			out.println("switch (value) {", typeName);

			for(Element element: typeEnum.elementList) {
				out.println("case %d: return %s.%s;", element.value, typeName, StringUtil.toJavaConstName(element.name));
			}
			
			out.println("}");
			out.println("logger.error(\"Unexpected value = {}\", value);");
			out.println("throw new UnexpectedException();");
			out.println("}");
			out.println();
			out.println("public final int value;");
			out.println();

			out.println("private %s(int value) {", typeName);
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
		String className = typeRecord.name;
		String path = String.format("%s/%s.java", PATH_DIR, className);
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
			out.println("// %s", typeRecord.toMesaString());
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeRecord.name);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeRecord.name);
			out.println();
			out.println("public static final int SIZE = %d;", typeRecord.getSize());
			out.println();
			
			for(Field field: typeRecord.fieldList) {
				String  fieldName = field.name;
				Type    type      = field.type;
				Select  select    = field.select;
				int     size      = field.getSize();
				boolean bitField  = false;;
				int     shift     = 0;
				String  mask      = null;
				{
					if (field.isBitfield()) {
						BitInfo bitInfo = new BitInfo(field.getSize(), field.startPos, field.stopPos);
						bitField = true;
						shift    = bitInfo.shift;
						mask     = bitInfo.mask;
					}
				}
				
				if (type != null) {
					Type  baseType = field.type.isReference() ? ((TypeReference)field.type).baseType : field.type;
					switch(field.fieldKind) {
					case FIELD:
						out.println("//   %s(%d): %s", field.name, field.offset, type.toMesaType());
						break;
					case BIT_FIELD:
						out.println("//   %s(%d:%d..%d): %s", field.name, field.offset, field.startPos, field.stopPos, type.toMesaType());
						break;
					default:
						throw new UnexpectedException();
					}

					out.println("public static final class %s {", field.name);

					out.println("public static final int SIZE   =  %2d;", field.getSize());
					out.println("public static final int OFFSET =  %2d;", field.offset);
					if (bitField) {
						out.println("public static final int SHIFT  =  %2d;", shift);
						out.println("public static final int MASK   =  %s;", mask);
					}
					out.println();
					
					out.println("public static int getAddress(int base) {");
					out.println("return base + OFFSET;");
					out.println("}");
					out.println();

					if (bitField) {
						out.println("public static int getBit(int value) {");
						out.println("return (value & MASK) >>> SHIFT;");
						out.println("}");
						out.println("public static int setBit(int value, int newValue) {");
						out.println("return ((newValue << SHIFT) & MASK) | (value & ~MASK);");
						out.println("}");
						out.println();
						
						switch (baseType.kind) {
						case BOOL:
							out.println("public static boolean get(int base) {");
							out.println("return getBit(Memory.fetch(getAddress(base))) != 0;");
							out.println("}");
							out.println("public static void set(int base, boolean newValue) {");
							out.println("Memory.modify(getAddress(base), %s.%s::setBit, (newValue ? 1 : 0));", className, fieldName);
							out.println("}");
							break;
						case SUBRANGE:
							out.println("public static int get(int base) {");
							out.println("return getBit(Memory.fetch(getAddress(base)));");
							out.println("}");
							out.println("public static void set(int base, int newValue) {");
							out.println("Memory.modify(getAddress(base), %s.%s::setBit, newValue);", className, fieldName);
							out.println("}");
							break;
						case ENUM:
							out.println("public static %s get(int base) {", baseType.name);
							out.println("return %s.getInstance(getBit(Memory.fetch(getAddress(base))));", baseType.name);
							out.println("}");
							out.println("public static void set(int base, %s newValue) {", baseType.name);
							out.println("Memory.modify(getAddress(base), %s.%s::setBit, newValue.value);", className, fieldName);
							out.println("}");
							break;
						case ARRAY:
							// FIXME
							out.println("// FIXME ARRAY");
							break;
						case RECORD:
							// FIXME
							out.println("// FIXME RECORD");
							break;
						default:
							logger.error("type {}", type);
							throw new UnexpectedException();
						}

					} else {
						switch (baseType.kind) {
						case BOOL:
							out.println("public static boolean get(int base) {");
							out.println("return Memory.fetch(getAddress(base)) != 0;");
							out.println("}");
							out.println("public static void set(int base, boolean newValue) {");
							out.println("Memory.store(getAddress(base), newValue ? 1 : 0);");
							out.println("}");
							break;
						case SUBRANGE:
							out.println("public static int get(int base) {");
							out.println("return Memory.fetch(getAddress(base));");
							out.println("}");
							out.println("public static void set(int base, int newValue) {");
							out.println("Memory.store(getAddress(base), newValue);");
							out.println("}");
							break;
						case ENUM:
							out.println("public static %s get(int base) {", baseType.name);
							out.println("return %s.getInstance(Memory.fetch(getAddress(base)));", baseType.name);
							out.println("}");
							out.println("public static void set(int base, %s newValue) {", baseType.name);
							out.println("Memory.store(getAddress(base), newValue.value);");
							out.println("}");
							break;
						case ARRAY:
							// FIXME
							out.println("// FIXME ARRAY");
							break;
						case RECORD:
							// FIXME
							out.println("// FIXME RECORD");
							break;
						default:
							logger.error("type {}", type);
							throw new UnexpectedException();
						}
					}
					
					out.println("}");
				}
				if (select != null) {
					switch(field.fieldKind) {
					case FIELD:
						out.println("//   %s(%d): %s", field.name, field.offset, select.toMesaType());
						break;
					case BIT_FIELD:
						out.println("//   %s(%d:%d..%d): %s", field.name, field.offset, field.startPos, field.stopPos, select.toMesaType());
						break;
					default:
						throw new UnexpectedException();
					}

					
				}
			}			
			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	private static void genType()	{
		for(Type e: Type.map.values()) {
			if (e.isReference()) continue;
			if (e.name.contains("#")) continue;
			
			logger.info("==  {}", e.toMesaString());
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
