package yokwe.majuro.symbol;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.mesa.Debug;
import yokwe.majuro.mesa.type.BytePair;
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
import yokwe.util.FileUtil;
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
			if (typeSubrange.isPredefined()) {
				out.println("// %s: TYPE = [%d..%d);", typeSubrange.name, typeSubrange.getValueMin(), typeSubrange.getValueMax() + 1);
			} else {
				out.println("// %s", typeSubrange.toMesaString());
			}
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeName);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeName);
			out.println();
			out.println("public static final int SIZE = %d;", typeSubrange.getSize());
			out.println();
			
			switch(typeSubrange.getSize()) {
			case 1:
				out.println("public static int get(int base) {");
				out.println("return checkValue(Memory.fetch(base));");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("Memory.store(base, checkValue(newValue));");
				out.println("}");

				out.println("public static int checkValue(int value) {");
				out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
				out.println("if (value < %d || %d < value) {", typeSubrange.getValueMin(), typeSubrange.getValueMax());
				out.println("logger.error(\"value is out of range\");");
				out.println("logger.error(\"  value {}\", value);");
				out.println("throw new UnexpectedException(\"value is out of range\");");
				out.println("}");
				out.println("}");
				out.println("return value;");
				out.println("}");
				break;
			case 2:
				out.println("public static int get(int base) {");
				out.println("return checkValue(Memory.readDbl(base));");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("Memory.writeDbl(base, checkValue(newValue));");
				out.println("}");
				
				out.println("public static int checkValue(int value) {");
				out.println("return value;");
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

	private static void genNestedArrayRecord(AutoIndentPrintWriter out, String prefix, TypeRecord typeRecord) {
		out.println("// Expand %s", typeRecord.toMesaString());
		
		String recordName = typeRecord.name;
		
		for(Field field: typeRecord.fieldList) {
			String  fieldName = field.fieldName.name;
			Type    type      = field.type;
			Select  select    = field.select;
			
			out.println("//   %s", field.toMesaType());
			out.println("public static final class %s {", fieldName);
			
			if (type != null) {
				Type baseType = type.isReference() ? ((TypeReference)type).baseType : type;
				switch(baseType.kind) {
				case BOOL:
					out.println("public static boolean get(int base, int index) {");
					out.println("return %s.%s.get(getAddress(base, index));", recordName, fieldName);
					out.println("}");
					out.println("public static void set(int base, int index, boolean newValue) {");
					out.println("%s.%s.set(getAddress(base, index), newValue);", recordName, fieldName);
					out.println("}");
					break;
				case SUBRANGE:
				case ENUM:
					out.println("public static int get(int base, int index) {");
					out.println("return %s.%s.get(getAddress(base, index));", recordName, fieldName);
					out.println("}");
					out.println("public static void set(int base, int index, int newValue) {");
					out.println("%s.%s.set(getAddress(base, index), newValue);", recordName, fieldName);
					out.println("}");
					break;
				case RECORD:
					genNestedArrayRecord(out, String.format("%s.%s", recordName, fieldName), (TypeRecord)baseType);
					break;
				default:
					logger.error("field {}", baseType.toMesaType());
					throw new UnexpectedException();
				}

			}
			if (select != null) {
				// FIXME
				logger.warn("### SKIP NESTED ARRAY RECORD SELECT");
				out.println("// FIXME NESTED ARRAY RECORD SELECT");
			}
			
			out.println("}");
		}
	}

	private static void genType(TypeArray typeArray) {
		String className = typeArray.name;
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
			out.println("// %s", typeArray.toMesaString());
			if (!typeArray.indexType.baseType.isPredefined()) {
				out.println("//   %s: TYPE = %s;", typeArray.indexType.baseName, typeArray.indexType.baseType.toMesaType());
			}
			if (!typeArray.elementType.baseType.isPredefined()) {
				out.println("//   %s: TYPE = %s;", typeArray.elementType.baseName, typeArray.elementType.baseType.toMesaType());
			}
			out.println("//");
			out.println();
			
			out.println("public final class %s {", className);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", className);
			out.println();
			out.println("public static final int SIZE         = %d;", typeArray.getSize());
			

			out.println("public static final int ELEMENT_SIZE = %d;", typeArray.elementType.getSize());
			out.println("public static final int INDEX_LEN    = %d;", typeArray.length);
			out.println();
			
			out.println("public static int getAddress(int base, int index) {");
			out.println("return base + (checkIndex(index) * ELEMENT_SIZE);");
			out.println("}");
			
			out.println("public static int checkIndex(int index) {");
			out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
			out.println("if (index < %d || %d < index) {", typeArray.rangeMin, typeArray.rangeMax);
			out.println("logger.error(\"index is out of range\");");
			out.println("logger.error(\"  index {}\", index);");
			out.println("throw new UnexpectedException(\"index is out of range\");");
			out.println("}");
			out.println("}");
			out.println("return index;");
			out.println("}");
			out.println();

			switch (typeArray.elementType.baseType.kind) {
			case SUBRANGE:
			case ENUM:
				out.println("public static int get(int base, int index) {");
				out.println("return checkValue(Memory.fetch(getAddress(base, index)));");
				out.println("}");

				out.println("public static void set(int base, int index, int newValue) {");
				out.println("Memory.store(getAddress(base, index), checkValue(newValue));");
				out.println("}");
				
				out.println("public static int checkValue(int value) {");
				out.println("return %s.checkValue(value);", typeArray.elementType.baseType.name);
				out.println("}");
				break;
			case RECORD:
				genNestedArrayRecord(out, className, (TypeRecord)typeArray.elementType.baseType);
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
			out.println("import yokwe.majuro.mesa.Memory;");
			out.println();
			
			out.println("//");
			out.println("// %s", typeEnum.toMesaString());
			out.println("//");
			out.println();

			out.println("public class %s {", typeName);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", typeName);
			out.println();
			
			out.println("public static final int SIZE = %d;", typeEnum.getSize());
			out.println();

			out.println("// enum value");
			for(Element element: typeEnum.elementList) {
				out.println("public static final int %-16s = %d;", StringUtil.toJavaConstName(element.name), element.value);
			}
			out.println();
			
			out.println("public static String toString(int value) {");
			out.println("switch(value) {");
			for(Element element: typeEnum.elementList) {
				out.println("case %d: return \"%s\";", element.value, StringUtil.toJavaConstName(element.name));
			}
			out.println("}");
			out.println("logger.error(\"value is out of range\");");
			out.println("logger.error(\"  value {}\", value);");
			out.println("throw new UnexpectedException(\"value is out of range\");");
			out.println("}");

			out.println("public static int get(int base) {");
			out.println("return checkValue(Memory.fetch(base));");
			out.println("}");
			out.println("public static void set(int base, int newValue) {");
			out.println("Memory.store(base, checkValue(newValue));");
			out.println("}");

			out.println("public static int checkValue(int value) {");
			out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
			out.println("if (value < %d || %d < value) {", typeEnum.valueMin, typeEnum.valueMax);
			out.println("logger.error(\"value is out of range\");");
			out.println("logger.error(\"  value {}\", value);");
			out.println("throw new UnexpectedException(\"value is out of range\");");
			out.println("}");
			out.println("}");
			out.println("return value;");
			out.println("}");

			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	
	private static void genNestedArray(AutoIndentPrintWriter out, String prefix, TypeArray typeArray) {
		out.println("public static final int ELEMENT_SIZE = %d;", typeArray.elementType.getSize());
		out.println("public static final int INDEX_LEN    = %d;", typeArray.length);
		out.println();
		
		out.println("public static int getAddress(int base, int index) {");
		out.println("return getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);");
		out.println("}");
		
		out.println("public static int checkIndex(int index) {");
		out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
		out.println("if (index < %d || %d < index) {", typeArray.rangeMin, typeArray.rangeMax);
		out.println("logger.error(\"index is out of range\");");
		out.println("logger.error(\"  index {}\", index);");
		out.println("throw new UnexpectedException(\"index is out of range\");");
		out.println("}");
		out.println("}");
		out.println("return index;");
		out.println("}");
		out.println();

		Type elementType = typeArray.elementType.baseType;
		switch(elementType.kind) {
		case ENUM:
		case SUBRANGE:
			out.println("public static int get(int base, int index) {");
			out.println("return %s.get(getAddress(base, index));", elementType.name);
			out.println("}");

			out.println("public static void set(int base, int index, int newValue) {");
			out.println("%s.set(getAddress(base, index), newValue);", elementType.name);
			out.println("}");
			break;
		default:
			throw new UnexpectedException();
		}
	}
	
	private static void genNestedRecord(AutoIndentPrintWriter out, String prefix, TypeRecord typeRecord) {
		out.println("// Expand %s", typeRecord.toMesaString());
		
		String recordName = typeRecord.name;
		
		for(Field field: typeRecord.fieldList) {
			String  fieldName = field.fieldName.name;
			Type    type      = field.type;
			Select  select    = field.select;
			
			out.println("//   %s", field.toMesaType());
			out.println("public static final class %s {", fieldName);
			
			if (type != null) {
				Type baseType = type.isReference() ? ((TypeReference)type).baseType : type;
				switch(baseType.kind) {
				case BOOL:
					out.println("public static boolean get(int base) {");
					out.println("return %s.%s.get(getAddress(base));", recordName, fieldName);
					out.println("}");
					out.println("public static void set(int base, boolean newValue) {");
					out.println("%s.%s.set(getAddress(base), newValue);", recordName, fieldName);
					out.println("}");
					break;
				case SUBRANGE:
				case ENUM:
					out.println("public static int get(int base) {");
					out.println("return %s.%s.get(getAddress(base));", recordName, fieldName);
					out.println("}");
					out.println("public static void set(int base, int newValue) {");
					out.println("%s.%s.set(getAddress(base), newValue);", recordName, fieldName);
					out.println("}");
					break;
				default:
					throw new UnexpectedException();
				}

			}
			if (select != null) {
				// FIXME
				logger.warn("### SKIP NESTED RECORD SELECT");
				out.println("// FIXME NESTED RECORD SELECT");
			}
			
			out.println("}");
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
			out.println("public static final int SIZE   = %d;", typeRecord.getSize());
			out.println();
			
			for(Field field: typeRecord.fieldList) {
				String  fieldName = field.fieldName.name;
				Type    type      = field.type;
				Select  select    = field.select;
				boolean bitField  = field.isBitfield();
				int     shift     = 0;
				String  mask      = null;
				{
					if (bitField) {
						BitInfo bitInfo = new BitInfo(field.getSize(), field.fieldName.startPos, field.fieldName.stopPos);
						shift    = bitInfo.shift;
						mask     = bitInfo.mask;
					}
				}
				
				out.println("// %s", field.toMesaType());
				
				if (type != null) {
					Type  baseType = type.isReference() ? ((TypeReference)type).baseType : type;

					out.println("public static final class %s {", field.fieldName.name);
					out.println("public static final int OFFSET =  %2d;", field.fieldName.offset);
					out.println("public static final int SIZE   =  %2d;", field.getSize());
					if (bitField) {
						out.println("public static final int MASK   =  %s;", mask);
						out.println("public static final int SHIFT  =  %2d;", shift);
						if (baseType.isPredefined()) {
							// predefined type needs BITS field
							out.println("public static final int BITS   =  MASK >>> SHIFT;");
						}
					}
					out.println();
					
					out.println("public static int getAddress(int base) {");
					out.println("return base + OFFSET;");
					out.println("}");

					if (bitField) {
						out.println("public static int getBit(int value) {");
						out.println("return (checkValue(value) & MASK) >>> SHIFT;");
						out.println("}");
						out.println("public static int setBit(int value, int newValue) {");
						out.println("return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);");
						out.println("}");
						
						
						switch (baseType.kind) {
						case BOOL:
							out.println("public static int checkValue(int value) {");
							out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
							out.println("if (value < 0 || BITS < value) {");
							out.println("logger.error(\"value is out of range\");");
							out.println("logger.error(\"  value {}\", value);");
							out.println("throw new UnexpectedException(\"value is out of range\");");
							out.println("}");
							out.println("}");
							out.println("return value;");
							out.println("}");
							
							out.println("public static boolean get(int base) {");
							out.println("return getBit(Memory.fetch(getAddress(base))) != 0;");
							out.println("}");
							out.println("public static void set(int base, boolean newValue) {");
							out.println("Memory.modify(getAddress(base), %s.%s::setBit, (newValue ? 1 : 0));", className, fieldName);
							out.println("}");
							break;
						case SUBRANGE:
						case ENUM:
							if (baseType.isPredefined()) {
								out.println("public static int checkValue(int value) {");
								out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
								out.println("if (value < 0 || BITS < value) {");
								out.println("logger.error(\"value is out of range\");");
								out.println("logger.error(\"  value {}\", value);");
								out.println("throw new UnexpectedException(\"value is out of range\");");
								out.println("}");
								out.println("}");
								out.println("return value;");
								out.println("}");
							} else {
								out.println("public static int checkValue(int value) {");
								out.println("return %s.checkValue(value);", baseType.name);
								out.println("}");
							}

							switch(baseType.getSize()) {
							case 1:
								out.println("public static int get(int base) {");
								out.println("return getBit(Memory.fetch(getAddress(base)));");
								out.println("}");
								out.println("public static void set(int base, int newValue) {");
								out.println("Memory.modify(getAddress(base), %s.%s::setBit, newValue);", className, fieldName);
								out.println("}");
								break;
							case 2:
								out.println("public static int get(int base) {");
								out.println("return getBit(Memory.readDbl(getAddress(base)));");
								out.println("}");
								out.println("public static void set(int base, int newValue) {");
								out.println("Memory.modifyDbl(getAddress(base), %s.%s::setBit, newValue);", className, fieldName);
								out.println("}");
								break;
							default:
								throw new UnexpectedException();
							}
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
						case ENUM:
							out.println("public static int get(int base) {");
							out.println("return %s.get(getAddress(base));", baseType.name);
							out.println("}");
							out.println("public static void set(int base, int newValue) {");
							out.println("%s.set(getAddress(base), newValue);", baseType.name);
							out.println("}");
							break;
						case ARRAY:
							genNestedArray(out, String.format("%s.%s", className, fieldName), (TypeArray)baseType);
							break;
						case RECORD:
							genNestedRecord(out, String.format("%s.%s", className, fieldName), (TypeRecord)baseType);
							break;
						default:
							logger.error("type {}", type);
							throw new UnexpectedException();
						}
					}
					out.println("}");
				}
				if (select != null) {
					// FIXME
					logger.warn("### SKIP RECORD SELECT");
					out.println("// FIXME RECORD SELECT");
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
