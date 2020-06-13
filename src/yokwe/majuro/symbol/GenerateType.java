package yokwe.majuro.symbol;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Field;
import yokwe.majuro.symbol.model.Select;
import yokwe.majuro.symbol.model.Select.SelectCase;
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
	
	static class Prefix {
		private LinkedList<String> list;
		
		public Prefix(Prefix prefix, String value) {
			list = new LinkedList<>();
			list.addAll(prefix.list);
			list.addLast(value);
		}
		public Prefix(Prefix prefix, String value1, String value2) {
			list = new LinkedList<>();
			list.addAll(prefix.list);
			list.addLast(value1);
			list.addLast(value2);
		}
		public Prefix(String value) {
			list = new LinkedList<>();
			list.addLast(value);
		}
		public Prefix(String value1, String value2) {
			list = new LinkedList<>();
			list.addLast(value1);
			list.addLast(value2);
		}
		
		public int size() {
			return list.size();
		}
		@Override
		public String toString() {
			return String.join(".", list);
		}
		public String toString(int level) {
			if (0 <= level) {
				logger.error("Unexpected level");
				logger.error("  level {}", level);
				throw new UnexpectedException("Unexpected level");
			}
			
			int limit = list.size() + level;
			if (limit < 0) {
				logger.error("Unexpected level");
				logger.error("  level {}", level);
				logger.error("  list  {}", list.size());
				throw new UnexpectedException("Unexpected level");
			}
			
			StringJoiner stringJoiner = new StringJoiner(".");
			for(int i = 0; i < limit; i++) {
				stringJoiner.add(list.get(i));
			}
			
			return stringJoiner.toString();
		}
	}

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
	
	private static void defineSubrange(TypeSubrange typeSubrange) {
		String className = typeSubrange.name;
		String path = String.format("%s/%s.java", PATH_DIR, className);
		logger.info("path {}",path);
		
		int  size     = typeSubrange.getSize();
		long valueMin = typeSubrange.getValueMin();
		long valueMax = typeSubrange.getValueMax();
		Type baseType = typeSubrange.getBaseType();

		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			out.println("import yokwe.majuro.mesa.Memory;");

			out.println();
			
			out.println("//");
			out.println("//  %s", baseType.toMesaString());
			out.println("//");
			out.println();
			
			out.println("public final class %s {", className);
			out.println("public  static final int  SIZE = %s;", Type.toJavaString(size));
			out.println();
			
			out.println("private static final long MIN  = %s;", Type.toJavaString(valueMin));
			out.println("private static final long MAX  = %s;", Type.toJavaString(valueMax));
			out.println("private static final Subrange SUBRANGE = new Subrange(MIN, MAX);");
			out.println();
			
			out.println("public static int checkValue(int value) {");
			out.println("SUBRANGE.check(value);");
			out.println("return value;");
			out.println("}");
			out.println();
			
			switch(size) {
			case 1:
				out.println("public static int get(int base) {");
				out.println("return checkValue(Memory.fetch(base));");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("Memory.store(base, checkValue(newValue));");
				out.println("}");
				break;
			case 2:
				out.println("public static int get(int base) {");
				out.println("return checkValue(Memory.readDbl(base));");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("Memory.writeDbl(base, checkValue(newValue));");
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

	private static void genRecordInArray(AutoIndentPrintWriter out, Prefix prefix, TypeRecord typeRecord) {
		out.println("// Expand Record in Array");
//		out.println("// prefix %s", prefix);
		out.println("//   %s", typeRecord.toMesaString());
		
		String recordName = typeRecord.name;
		
		for(Field field: typeRecord.fieldList) {
			String  fieldName = field.fieldName.name;
			Type    type      = field.type;
			Select  select    = field.select;
			
			out.println("//     %s", field.toMesaType());
			out.println("public static final class %s {", fieldName);
			
			if (type != null) {
				Type baseType = type.isReference() ? ((TypeReference)type).baseType : type;
				switch(baseType.kind) {
				case BOOL:
					out.println("public static boolean get(int base, int index) {");
					out.println("return %s.%s.get(%s.getAddress(base, index));", recordName, fieldName, prefix);
					out.println("}");
					out.println("public static void set(int base, int index, boolean newValue) {");
					out.println("%s.%s.set(%s.getAddress(base, index), newValue);", recordName, fieldName, prefix);
					out.println("}");
					break;
				case SUBRANGE:
				case ENUM:
					out.println("public static int get(int base, int index) {");
					out.println("return %s.%s.get(%s.getAddress(base, index));", recordName, fieldName, prefix);
					out.println("}");
					out.println("public static void set(int base, int index, int newValue) {");
					out.println("%s.%s.set(%s.getAddress(base, index), newValue);", recordName, fieldName, prefix);
					out.println("}");
					break;
				case RECORD:
					genRecordInArray(out, prefix, (TypeRecord)baseType);
					break;
				default:
					logger.error("field {}", baseType.toMesaType());
					throw new UnexpectedException();
				}

			}
			if (select != null) {
				throw new UnexpectedException();
				// FIXME not tested
//				genFieldSelect(out, recordName, field);
			}
			
			out.println("}");
		}
	}

	private static void genArrayBody(AutoIndentPrintWriter out, Prefix prefix, TypeArray typeArray) {
		Type indexType   = typeArray.indexType.getBaseType();
		Type elementType = typeArray.elementType.getBaseType();

		{
			boolean useName = !typeArray.indexHasSubrange();
			
			if (useName) {
				out.println("private static int checkIndex(int index) {");
				out.println("return %s.checkValue(index);", indexType.name);
				out.println("}");
			} else {
				out.println("private static final int INDEX_MIN    = %d;", typeArray.rangeMin);
				out.println("private static final int INDEX_MAX    = %d;", typeArray.rangeMax);
				out.println("private static final Subrange INDEX_SUBRANGE = new Subrange(INDEX_MIN, INDEX_MAX);");

				out.println("private static int checkIndex(int index) {");
				out.println("INDEX_SUBRANGE.check(index);");
				out.println("return index;");
				out.println("}");
			}
		}
		out.println();
		
		switch(elementType.kind) {
		case ENUM:
		case SUBRANGE:
			out.println("public static int get(int base, int index) {");
			out.println("return %s.get(%s.getAddress(base, checkIndex(index)));", elementType.name, prefix);
			out.println("}");

			out.println("public static void set(int base, int index, int newValue) {");
			out.println("%s.set(%s.getAddress(base, checkIndex(index)), newValue);", elementType.name, prefix);
			out.println("}");
			break;
		case RECORD:
			genRecordInArray(out, prefix, (TypeRecord)elementType);
			break;
		default:
			logger.error("elementType {}", elementType.toMesaString());
			throw new UnexpectedException();
		}
	}

	private static void genArrayInRecord(AutoIndentPrintWriter out, Prefix prefix, TypeArray typeArray) {
		out.println("// %s", typeArray.toMesaString());
		out.println("//   %s: TYPE = %s", typeArray.indexType.baseName,   typeArray.indexType.toMesaType());
		out.println("//   %s: TYPE = %s", typeArray.elementType.baseName, typeArray.elementType.toMesaType());
		
		Type elementType = typeArray.elementType.getBaseType();

		out.println("private static final int ELEMENT_SIZE = %d;", elementType.getSize());
		out.println("public static int getAddress(int base, int index) {");
		out.println("return %s.getAddress(base) + (checkIndex(index) * ELEMENT_SIZE);", prefix);
		out.println("}");
		out.println();
		
		genArrayBody(out, prefix, typeArray);
	}
	
	private static void defineArray(TypeArray typeArray) {
		String className = typeArray.name;
		String path = String.format("%s/%s.java", PATH_DIR, className);
		logger.info("path {}",path);
		
		Type elementType = typeArray.elementType.getBaseType();
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			
			out.println("//");
			out.println("// %s", typeArray.toMesaString());
			out.println("//   %s: TYPE = %s", typeArray.indexType.baseName,   typeArray.indexType.toMesaType());
			out.println("//   %s: TYPE = %s", typeArray.elementType.baseName, typeArray.elementType.toMesaType());
			out.println("//");
			out.println();
			
			out.println("public final class %s {", className);
			out.println("public static final int SIZE = %d;", typeArray.getSize());
			out.println();
			
			out.println("private static final int ELEMENT_SIZE = %d;", elementType.getSize());
			out.println("public static int getAddress(int base, int index) {");
			out.println("return base + (checkIndex(index) * ELEMENT_SIZE);");
			out.println("}");
			out.println();
			
			genArrayBody(out, new Prefix(className), typeArray);
					
			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	private static void defineEnum(TypeEnum typeEnum) {
		String className = typeEnum.name;
		String path = String.format("%s/%s.java", PATH_DIR, className);
		logger.info("path {}",path);
		
		int  size     = typeEnum.getSize();

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

			out.println("public class %s {", className);
			out.println("private static final Logger logger = LoggerFactory.getLogger(%s.class);", className);
			out.println();
			
			out.println("public static final int SIZE = %d;", size);
			out.println();

			out.println("// enum value");
			for(Element element: typeEnum.elementList) {
				out.println("public static final int %-16s = %d;", StringUtil.toJavaConstName(element.name), element.value);
			}
			out.println();
			
			out.println("public static String toString(int value) {");
			out.println("switch(value) {");
			for(Element element: typeEnum.elementList) {
				out.println("case %1$-16s: return \"%1$s\";", StringUtil.toJavaConstName(element.name));
			}
			out.println("default:");
			out.println("logger.error(\"value is out of range\");");
			out.println("logger.error(\"  value {}\", value);");
			out.println("throw new UnexpectedException(\"value is out of range\");");
			out.println("}");
			out.println("}");

			out.println("public static int get(int base) {");
			out.println("return checkValue(Memory.fetch(base));");
			out.println("}");
			out.println("public static void set(int base, int newValue) {");
			out.println("Memory.store(base, checkValue(newValue));");
			out.println("}");

			// Don't use Subrange. Becuase enum can have hole.
			out.println("public static int checkValue(int value) {");
			out.println("if (Debug.ENABLE_TYPE_RANGE_CHECK) {");
			out.println("switch(value) {");
			for(Element element: typeEnum.elementList) {
				out.println("case %s:", StringUtil.toJavaConstName(element.name));
			}
			out.println("break;");
			out.println("default:");
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
	
	private static void genRecordInRecord(AutoIndentPrintWriter out, Prefix prefix, TypeRecord typeRecord) {
		out.println("// Expand Record in Record");
//		out.println("// prefix %s", prefix);
		out.println("//   %s", typeRecord.toMesaString());
		for(Field field: typeRecord.fieldList) {
			out.println("//     %s", field.toMesaType());
			genField(out, prefix, field);
		}
	}
	
	private static void genFieldTypeNotBit(AutoIndentPrintWriter out, Prefix prefix, Field field) {
		String  fieldName = field.fieldName.name;
		boolean bitField  = field.isBitfield();
		if (bitField) throw new UnexpectedException();

		out.println("public static final class %s {", fieldName);
		out.println("public static final int SIZE = %d;", field.getSize());
		out.println();
		
		out.println("private static final int OFFSET = %d;", field.fieldName.offset);
		out.println("public static int getAddress(int base) {");
		if (2 <= prefix.size()) {
			out.println("return %s.getAddress(base) + OFFSET;", prefix);
		} else {
			out.println("return base + OFFSET;", prefix);
		}
		out.println("}");

		Type type = field.type.getBaseType();
		
		switch (type.kind) {
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
			out.println("return %s.get(getAddress(base));", type.name);
			out.println("}");
			out.println("public static void set(int base, int newValue) {");
			out.println("%s.set(getAddress(base), newValue);", type.name);
			out.println("}");
			break;
		case ARRAY:
			genArrayInRecord(out, new Prefix(prefix, fieldName), (TypeArray)type);
			break;
		case RECORD:
			genRecordInRecord(out, new Prefix(prefix, fieldName), (TypeRecord)type);
			break;
		default:
			logger.error("type {}", type);
			throw new UnexpectedException();
		}
		out.println("}");
	}
	private static void genFieldTypeBit(AutoIndentPrintWriter out, Prefix prefix, Field field) {
		String  fieldName = field.fieldName.name;
		boolean bitField  = field.isBitfield();
		if (!bitField) throw new UnexpectedException();

		out.println("public static final class %s {", fieldName);
		out.println("public static final int SIZE = %d;", field.getSize());
		out.println();
		
		out.println("private static final int OFFSET = %d;", field.fieldName.offset);
		out.println("public static int getAddress(int base) {");
		if (2 <= prefix.size()) {
			out.println("return %s.getAddress(base) + OFFSET;", prefix);
		} else {
			out.println("return base + OFFSET;", prefix);
		}
		out.println("}");

		Type type = field.type.getBaseType();		

		BitInfo bitInfo = new BitInfo(field.getSize(), field.fieldName.startPos, field.fieldName.stopPos);

		out.println("private static final int MASK  = %s;", bitInfo.mask);
		out.println("private static final int SHIFT = %d;", bitInfo.shift);
		out.println();

		out.println("private static int getBit(int value) {");
		out.println("return (checkValue(value) & MASK) >>> SHIFT;");
		out.println("}");
		out.println("private static int setBit(int value, int newValue) {");
		out.println("return ((checkValue(newValue) << SHIFT) & MASK) | (value & ~MASK);");
		out.println("}");
		out.println();
		
		out.println("private static final int MAX = MASK >>> SHIFT;");
		out.println("private static final Subrange SUBRANGE = new Subrange(0, MAX);");
		out.println();

		switch (type.kind) {
		case BOOL:
			out.println("public static int checkValue(int value) {");
			out.println("SUBRANGE.check(value);");
			out.println("return value;");
			out.println("}");
			out.println("public static boolean get(int base) {");
			out.println("return getBit(Memory.fetch(getAddress(base))) != 0;");
			out.println("}");
			out.println("public static void set(int base, boolean newValue) {");
			out.println("Memory.modify(getAddress(base), %s.%s::setBit, (newValue ? 1 : 0));", prefix, fieldName);
			out.println("}");
			break;
		case SUBRANGE:
		case ENUM:
			out.println("public static int checkValue(int value) {");
			out.println("SUBRANGE.check(value);");
			out.println("return %s.checkValue(value);", type.name);
			out.println("}");
			
			switch(type.getSize()) {
			case 1:
				out.println("public static int get(int base) {");
				out.println("return getBit(Memory.fetch(getAddress(base)));");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("Memory.modify(getAddress(base), %s.%s::setBit, newValue);", prefix, fieldName);
				out.println("}");
				break;
			case 2:
				out.println("public static int get(int base) {");
				out.println("return getBit(Memory.readDbl(getAddress(base)));");
				out.println("}");
				out.println("public static void set(int base, int newValue) {");
				out.println("Memory.modifyDbl(getAddress(base), %s.%s::setBit, newValue);", prefix, fieldName);
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
	
		out.println("}");
	}
	private static void genFieldType(AutoIndentPrintWriter out, Prefix prefix, Field field) {
		if (field.isBitfield()) {
			genFieldTypeBit(out, prefix, field);
		} else {
			genFieldTypeNotBit(out, prefix, field);
		}
	}

	private static void genFieldSelect(AutoIndentPrintWriter out, Prefix prefix, Field field) {
		String  fieldName = field.fieldName.name;
		Select  select    = field.select;

		out.println("public static final class %s {", fieldName);
		out.println("public static final int SIZE = %d;", field.getSize());
		out.println();
		
		out.println("private static final int OFFSET = %d;", field.fieldName.offset);
		out.println("public static int getAddress(int base) {");
		if (2 <= prefix.size()) {
			out.println("return %s.getAddress(base) + OFFSET;", prefix);
		} else {
			out.println("return base + OFFSET;", prefix);
		}
		out.println("}");

		out.println("// %s", select.toMesaType());
		// FIXME SELECT tag name and tag type
	
		for(SelectCase selectCase: select.selectCaseList) {
			out.println("// %s", selectCase.toMesaType());
			out.println("public static final class %s {", selectCase.selector);
			out.println("public static final int TAG  = %d;", selectCase.value);
			out.println("public static final int SIZE = %d;", selectCase.getSize());
			
			out.println("public static int getAddress(int base) {");
			out.println("return %s.getAddress(base);", String.format("%s.%s", prefix, fieldName));
			out.println("}");

			for(var nestedField: selectCase.fieldList) {
				out.println();
				out.println("// %s", nestedField.toMesaType());
				genField(out, new Prefix(prefix, fieldName, selectCase.selector), nestedField);
			}
			
			out.println("}");
		}
		
		out.println("}");
	}
	
	private static void genField(AutoIndentPrintWriter out, Prefix prefix, Field field) {
		if (field.type   != null) genFieldType  (out, prefix, field);
		if (field.select != null) genFieldSelect(out, prefix, field);
	}

	private static void defineRecord(TypeRecord typeRecord) {
		String className = typeRecord.name;
		String path = String.format("%s/%s.java", PATH_DIR, className);
		logger.info("path {}",path);
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			out.println("import yokwe.majuro.mesa.Memory;");
			out.println();
			
			out.println("//");
			out.println("// %s", typeRecord.toMesaString());
			out.println("//");
			out.println();
			
			out.println("public final class %s {", typeRecord.name);
			out.println("public static final int SIZE = %d;", typeRecord.getSize());
			out.println();
			
			for(Field field: typeRecord.fieldList) {
				String  fieldName = field.fieldName.name;
				Type    type      = field.type;
				Select  select    = field.select;
				
				out.println("// %s", field.toMesaType());
				
				if (type   != null) genFieldType  (out, new Prefix(className), field);
				if (select != null) genFieldSelect(out, new Prefix(className), field);
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
				defineSubrange((TypeSubrange)e);
				break;
			case ARRAY:
				defineArray((TypeArray)e);
				break;
			case ENUM:
				defineEnum((TypeEnum)e);
				break;
			case RECORD:
				defineRecord((TypeRecord)e);
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
