package yokwe.majuro.symbol;

import static yokwe.majuro.mesa.Constant.WORD_BITS;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.LEFT;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.RIGHT;

import java.util.List;
import java.util.stream.Collectors;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.type.MemoryBase;
import yokwe.majuro.type.MemoryData16;
import yokwe.majuro.type.MemoryData32;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.StringUtil;

public class JavaType {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JavaType.class);

	public static void generateFile(JavaFile javaFile) {
		JavaType javaType = new JavaType(javaFile);
		javaType.generate();
	}
	
	private final JavaFile javaFile;
	
	private JavaType(JavaFile javaFile) {
		this.javaFile = javaFile;
	}
	
	private void generate() {
		try (AutoIndentPrintWriter out = javaFile.getAutoIndentPrintWriter()) {
			final Type type = javaFile.type;
			
			if (type instanceof TypeBoolean) {
				typeBoolean(type.toTypeBoolean());
			} else if (type instanceof TypeSubrange) {
				typeSubrange(type.toTypeSubrange());
			} else if (type instanceof TypeEnum) {
				typeEnum(type.toTypeEnum());
			} else if (type instanceof TypePointer) {
				typePointer(type.toTypePointer());
			} else if (type instanceof TypeArray) {
				typeArray(type.toTypeArray());
			} else if (type instanceof TypeRecord) {
				typeRecord(type.toTypeRecord());
			} else if (type instanceof TypeReference) {
				logger.info("genDecl REF {}: TYPE = {}", type.name, type.toMesaType());
				javaFile.success = false;
			} else {
				logger.error("type {}", type.name);
				logger.error("mesa {}", type.toMesaType());
				throw new UnexpectedException("Unexpected");
			}
		}
		javaFile.moveFile();
	}
	
	//
	// Constructor related methods methods
	//
	private void classPreamble(Class<?> parentClass) {
		final var out = javaFile.out;
		
		if (parentClass == null) {
			throw new UnexpectedException("Unexpected");
		}

		out.println("package %s;", javaFile.packageName);
		out.println();
		out.println("import yokwe.majuro.UnexpectedException;");
		out.println("import yokwe.majuro.mesa.Debug;");
		out.println("import yokwe.majuro.mesa.Memory;");
		out.println("import yokwe.majuro.mesa.Mesa;");
		out.println();
		
		out.println("// %s: TYPE = %s;", javaFile.type.name, javaFile.type.toMesaType());
		
		out.println("public final class %s extends %s {", javaFile.name, parentClass.getSimpleName());
		out.println("public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();");
		out.println("public static final String   NAME = SELF.getSimpleName();");
		out.println();
	}
	private void constructor(Class<?> parentClass) {
		final var out = javaFile.out;

		out.println("//");
		out.println("// Constructor");
		out.println("//");
		
		if (parentClass.equals(MemoryData16.class)) {
			out.println("public static final %s value(char value) {", javaFile.name);
			out.println("return new %s(value);", javaFile.name);
			out.println("}");
			out.println("public static final %s longPointer(int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(base, access);", javaFile.name);
			out.println("}");
			out.println("public static final %s pointer(char base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(Mesa.lengthenMDS(base), access);", javaFile.name);
			out.println("}");
			out.println();
			
			out.println("private %s(char value) {", javaFile.name);
			out.println("super(value);");
			out.println("}");
			out.println("private %s(int base, MemoryAccess access) {", javaFile.name);
			out.println("super(base, access);");
			out.println("}");
		} else if (parentClass.equals(MemoryData32.class)) {
			out.println("public static final %s value(int value) {", javaFile.name);
			out.println("return new %s(value);", javaFile.name);
			out.println("}");
			out.println("public static final %s longPointer(int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(base, access);", javaFile.name);
			out.println("}");
			out.println("public static final %s pointer(char base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(Mesa.lengthenMDS(base), access);", javaFile.name);
			out.println("}");
			out.println();

			out.println("private %s(int value) {", javaFile.name);
			out.println("super(value);");
			out.println("}");
			out.println("private %s(int base, MemoryAccess access) {", javaFile.name);
			out.println("super(base, access);");
			out.println("}");
		} else if (parentClass.equals(MemoryBase.class)) {
			out.println("public static final %s longPointer(int base) {", javaFile.name);
			out.println("return new %s(base);", javaFile.name);
			out.println("}");
			out.println("public static final %s pointer(char base) {", javaFile.name);
			out.println("return new %s(Mesa.lengthenMDS(base));", javaFile.name);
			out.println("}");
			out.println();
			
			out.println("private %s(int base) {", javaFile.name);
			out.println("super(base);");
			out.println("}");
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	
	//
	// methods generate java source for corresponding parameter type
	//
	
	private final static Class<?>[] typeBooleanParentClass = {
		MemoryData16.class,
	};
	private void typeBoolean(TypeBoolean type) {
		final var out         = javaFile.out;
		final var parentClass = typeBooleanParentClass[type.wordSize() - 1];
		
		classPreamble(parentClass);

		out.prepareLayout();
		out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();
		
		constructor(parentClass);
		
		// close class body
		out.println("}");
	}
	private final static Class<?>[] typeSubrangeParentClass = {
		MemoryData16.class,
		MemoryData32.class,
	};
	private void typeSubrange(TypeSubrange type) {
		final var out         = javaFile.out;
		final var parentClass = typeSubrangeParentClass[type.wordSize() - 1];
		
		classPreamble(parentClass);

		out.prepareLayout();
		out.println("public static final int  WORD_SIZE = %d;",  type.wordSize());
		out.println("public static final int  BIT_SIZE  = %d;",  type.bitSize());
		out.println();
		out.println("public static final long MIN_VALUE  = %s;", StringUtil.toJavaString(type.minValue));
		out.println("public static final long MAX_VALUE  = %s;", StringUtil.toJavaString(type.maxValue));
		out.println("public static final long SIZE_VALUE = %s;", StringUtil.toJavaString(type.maxValue - type.minValue + 1));
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();
		
		out.println("private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);");
		out.println();
		
		out.println("public static final void checkValue(long value) {");
		out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(value);");
		out.println("}");
		
		// if this type is unsigned, treat value as unsigned int
		if (0 <= type.minValue) {
			out.println("public static void checkValue(int value) {");
			out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(Integer.toUnsignedLong(value));");
			out.println("}");
		}
		out.println();
		
		//
		// Generate Constructor
		//
		constructor(parentClass);
		
		// close class body
		out.println("}");
	}
	private final static Class<?>[] typeEnumParentClass = {
		MemoryData16.class,
	};
	private void typeEnum(TypeEnum type) {
		final var out         = javaFile.out;
		final var parentClass = typeEnumParentClass[type.wordSize() - 1];
		
		classPreamble(parentClass);

		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();

		List<String> itemList = type.itemList.stream().map(o -> StringUtil.toJavaConstName(o.name)).collect(Collectors.toList());
		String valueList = String.join(", ", itemList);
		String nameList  = String.join(", ", itemList.stream().map(o -> "\"" + o + "\"").collect(Collectors.toList()));

		//
		// enum value constants
		//
		out.println("//");
		out.println("// Enum Value Constants");
		out.println("//");
		out.prepareLayout();
		for(var e: type.itemList) {
			out.println("public static final char %s = %s;", StringUtil.toJavaConstName(e.name), StringUtil.toJavaString(e.value));
		}
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();
		
		out.println("private static final int[] values = {");
		out.println("%s", valueList);
		out.println("};");
		out.println("private static final String[] names = {");
		out.println("%s", nameList);
		out.println("};");
		out.println("private static final ContextEnum context = new ContextEnum(NAME, values, names);");
		out.println();

		out.println("public static final void checkValue(int value) {");
		out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(value);");
		out.println("}");
		out.println();
		
		constructor(parentClass);
		out.println();

		out.println("public final String toString(int value) {");
		out.println("return context.toString(value);");
		out.println("}");
		
		// close class body
		out.println("}");
	}
	private final static Class<?>[] typePointerParentClass = {
			MemoryBase.class,
			MemoryBase.class,
		};
	private void typePointer(TypePointer type) {
		if (type.rawPointer()) {
			final var out         = javaFile.out;
			final var parentClass = typePointerParentClass[type.wordSize() - 1];

			classPreamble(parentClass);

			out.prepareLayout();
			out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			constructor(parentClass);
			
			// close class body
			out.println("}");
		} else {
			javaFile.success = false;
		}
	}

	private void arrayData(Type indexType) {
		final var out          = javaFile.out;
		final TypeArray type = javaFile.type.toTypeArray();
				
		Type   elementType     = type.arrayElement.getRealType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		out.println("public final %s get(int index, MemoryAccess access) {", elementTypeName);
		if (!indexType.empty()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
		}
		out.println("return %s.longPointer(base + (%s.WORD_SIZE * index), access);", elementTypeName, elementTypeName);
		out.println("}");
	}
	private void arrayPointer(Type indexType) {
		final var out          = javaFile.out;
		final TypeArray type = javaFile.type.toTypeArray();
		
		Type   elementType     = type.arrayElement.getRealType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		out.println("public final %s get(int index) {", elementTypeName);
		if (!indexType.empty()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
		}
		out.println("return %s.longPointer(base + (%s.WORD_SIZE * index));", elementTypeName, elementTypeName);
		out.println("}");
	}
	private void typeArray(TypeArray type) {
		final var out         = javaFile.out;
		final var parentClass = MemoryBase.class;

		classPreamble(parentClass);
		
		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();
		
		Type indexType;
		{
			boolean immediateSubrange = false;
			if (type instanceof TypeArray.Subrange) {
				// INDEX is IMMEDIATE SUBRANGE
				indexType = type.toSubrange().typeSubrange;
				immediateSubrange = true;
			} else if (type instanceof TypeArray.Reference) {
				Type realType = type.toReference().typeReference.getRealType();
				if (realType instanceof TypeSubrange) {
					// INDEX is REFERENCE of SUBRANGE
					indexType = realType.toTypeSubrange();
				} else if (realType instanceof TypeEnum) {
					// INDEX is REFERENCE of ENUM
					indexType = realType.toTypeEnum();;
				} else {
					throw new UnexpectedException("Unexpected");
				}
			} else {
				throw new UnexpectedException("Unexpected");
			}
			
			if (!indexType.empty()) {
				out.println("//");
				out.println("// Check range of index");
				out.println("//");
				
				String indexTypeName  = StringUtil.toJavaName(indexType.name);
				if (immediateSubrange) {
					TypeSubrange typeSubrange = indexType.toTypeSubrange();
					String minValueString = StringUtil.toJavaString(typeSubrange.minValue);
					String maxValueString = StringUtil.toJavaString(typeSubrange.maxValue);
					
					out.println("private static final ContextSubrange context = new ContextSubrange(\"%s\", %s, %s);",
						javaFile.name, minValueString, maxValueString);
					
					out.println("public static final void checkIndex(int value) {");
					out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(value);", indexTypeName);
					out.println("}");
				} else {
					out.println("public static final void checkIndex(int value) {");
					out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(value);", indexTypeName);
					out.println("}");
				}
			}
		}
		
		constructor(parentClass);		
		
		
		//
		// output element access method
		//
		out.println("//");
		out.println("// Access to Element of Array");
		out.println("//");
		
		// FIXME StatAllocationVector.get() is wrong. need to read pointer value from memory
		
//		if (type.arrayElement instanceof TypeReference) {
//			arrayPointer(indexType);
//		} else 
		{
			Type elementType = type.arrayElement.getRealType();

			if (elementType instanceof TypeArray) {
				// ARRAY of ARRAY
				throw new UnexpectedException("Unexpected");
			} else if (elementType instanceof TypeBoolean) {
				// ARRAY of BOOLEAN
				arrayData(indexType);
			} else if (elementType instanceof TypeEnum) {
				// ARRAY of ENUM
				arrayData(indexType);
			} else if (elementType instanceof TypePointer) {
				// ARRAY of POINTER
				TypePointer typePointer = elementType.toTypePointer();
				if (typePointer.rawPointer()) {
					// ARRAY of RAW POINTER
					// FIXME
				} else {
					Type targetType = typePointer.targetType.getRealType();
					if (targetType instanceof TypeArray) {
						// ARRAY of POINTER to ARRAY
						throw new UnexpectedException("Unexpected");
					} else if (targetType instanceof TypeBoolean) {
						// ARRAY of POINTER to BOOLEAN
						// FIXME
					} else if (targetType instanceof TypeEnum) {
						// ARRAY of POINTER to ENUM
						// FIXME
					} else if (targetType instanceof TypePointer) {
						// ARRAY of POINTER to POINTER
						throw new UnexpectedException("Unexpected");
					} else if (targetType instanceof TypeRecord) {
						// ARRAY of POINTER to RECORD
						if (targetType.bitField16()) {
							// ARRAY of POINTER to BIT FIELD 16
							// FIXME
						} else if (targetType.bitField32()) {
							// ARRAY of POINTER to BIT FIELD 32
							// FIXME
						} else {
							// ARRAY of POINTER to MULTI WORD RECORD
							// FIXME
						}
					} else if (targetType instanceof TypeSubrange) {
						// ARRAY of POINTER to SUBRANGE
						// FIXME
					} else {
						throw new UnexpectedException("Unexpected");
					}
				}
			} else if (elementType instanceof TypeRecord) {
				// ARRAY of RECORD
				if (elementType.bitField16()) {
					// ARRAY of BIT FIELD 16
					arrayData(indexType);
				} else if (elementType.bitField32()) {
					// ARRAY of BIT FIELD 32
					arrayData(indexType);
				} else {
					// ARRAY of MULTI WORD RECORD
					arrayPointer(indexType);
				}
			} else if (elementType instanceof TypeSubrange) {
				// ARRAY of SUBRANGE
				arrayData(indexType);
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		
		// close class body
		out.println("}");
	}
	//
	// methods for Record
	//
	private void bitField16Record(TypeRecord type) {
		final var out         = javaFile.out;
		final var parentClass = MemoryData16.class;
		
		classPreamble(parentClass);
		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();

		// single word bit field
		constructor(parentClass);
		out.println();

		out.println("//");
		out.println("// Bit Field");
		out.println("//");
		out.println();
		
		out.prepareLayout();
		for(var e: type.fieldList) {
			out.println("// %s", e.toMesaType());
		}
		out.layout(LEFT, LEFT, LEFT, LEFT);
		out.println();
		
		out.prepareLayout();
		for(var e: type.fieldList) {
			String fieldCons = StringUtil.toJavaConstName(e.name);

			final int offset = e.offset;
			final int start;
			final int stop;
			if (offset == 0) {
				start  = e.startBit;
				stop   = e.stopBit;
			} else {
				logger.error("field {}", e);
				throw new UnexpectedException("Unexpected");
			}
			
			int bits  = stop - start + 1;
			int pat   = (1 << bits) - 1;
			int shift = type.bitSize() - stop - 1;
			int mask  = (pat << shift);
			
			out.println("private static final int %s_MASK  = %s;",
				fieldCons, StringUtil.toJavaBinaryString(Integer.toUnsignedLong(mask), type.bitSize()));
			out.println("private static final int %s_SHIFT = %d;", fieldCons, shift);
		}
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();
		
		out.println("//");
		out.println("// Bit Field Access Methods");
		out.println("//");
		for(var e: type.fieldList) {
			String fieldName = StringUtil.toJavaName(e.name);
			String fieldCons = StringUtil.toJavaConstName(e.name);
			
			out.println("public final char %s() {", fieldName);
			out.println("return (char)((value & %1$s_MASK) >> %1$s_SHIFT);", fieldCons);
			out.println("}");
			
			out.println("public final void %s(char newValue) {", fieldName);
			out.println("value = (value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK);", fieldCons);
			out.println("}");
			out.println();
		}
		
		// close class body
		out.println("}");
	}
	private void bitField32Record(TypeRecord type) {
		final var out         = javaFile.out;
		final var parentClass = MemoryData32.class;
		
		classPreamble(parentClass);
		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();

		// double word bit field
		constructor(parentClass);
		out.println();

		out.println("//");
		out.println("// Bit Field");
		out.println("//");
		out.println();
		
		out.prepareLayout();
		for(var e: type.fieldList) {
			out.println("// %s", e.toMesaType());
		}
		out.layout(LEFT, LEFT, LEFT, LEFT);
		out.println();

		out.prepareLayout();
		for(var e: type.fieldList) {
			String fieldCons = StringUtil.toJavaConstName(e.name);

			final int offset = e.offset;
			final int start;
			final int stop;
			if (offset == 0) {
				start  = 16 + e.startBit;
				stop   = 16 + e.stopBit;
			} else if (offset == 1) {
				start  = e.startBit;
				stop   = e.stopBit;
			} else {
				throw new UnexpectedException("Unexpected");
			}
			
			int bits  = stop - start + 1;
			int pat   = (1 << bits) - 1;
			int shift = type.bitSize() - stop - 1;
			int mask  = (pat << shift);
			
			out.println("private static final int %s_MASK  = %s;", fieldCons, StringUtil.toJavaBinaryString(Integer.toUnsignedLong(mask), type.bitSize()));
			out.println("private static final int %s_SHIFT = %d;", fieldCons, shift);
		}
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();
		
		out.println("//");
		out.println("// Bit Field Access Methods");
		out.println("//");
		for(var e: type.fieldList) {
			String fieldName = StringUtil.toJavaName(e.name);
			String fieldCons = StringUtil.toJavaConstName(e.name);
			
			out.println("public final int %s() {", fieldName);
			out.println("return (value & %1$s_MASK) >> %1$s_SHIFT;", fieldCons);
			out.println("}");
			
			out.println("public final void %s(int newValue) {", fieldName);
			out.println("value = (value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK);", fieldCons);
			out.println("}");
			out.println();
		}
		
		
		// close class body
		out.println("}");
	}

	private void fieldData(TypeRecord.Field field) {
		final var out         = javaFile.out;

		Type   fieldType      = field.type.getRealType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);

		out.println("public %s %s(MemoryAccess access) {", fieldTypeName, fieldName);
		out.println("return %s.longPointer(base + OFFSET_%s, access);", fieldTypeName, fieldConstName);
		out.println("}");
	}
	private void fieldPointer(TypeRecord.Field field) {
		final var out         = javaFile.out;

		Type   fieldType      = field.type.getRealType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);
		
		out.println("public %s %s() {", fieldTypeName, fieldName);
		out.println("return %s.longPointer(base + OFFSET_%s);", fieldTypeName, fieldConstName);
		out.println("}");
	}
	private void fieldArrayIndexData(TypeRecord.Field field) {
		final var out            = javaFile.out;

		Type   fieldType         = field.type.getRealType();
		String fieldConstName    = StringUtil.toJavaConstName(field.name);
		String fieldName         = StringUtil.toJavaName(field.name);

		Type   elementType       = fieldType.toTypeArray().arrayElement.getRealType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		// FIXME add index range check
		out.println("public %s %s(int index, MemoryAccess access) {", elementTypeName, fieldName);
		out.println("return %1$s.longPointer(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index), access);", elementTypeName, fieldConstName);
		out.println("}");
	}
	private void fieldArrayIndexPointer(TypeRecord.Field field) {
		final var out            = javaFile.out;

		Type   fieldType         = field.type.getRealType();
		String fieldConstName    = StringUtil.toJavaConstName(field.name);
		String fieldName         = StringUtil.toJavaName(field.name);

		Type   elementType       = fieldType.toTypeArray().arrayElement.getRealType();
		String elementTypeString = StringUtil.toJavaName(elementType.name);
		
		// FIXME add index range check
		out.println("public %s %s(int index) {", elementTypeString, fieldName);
		out.println("return %1$s.longPointer(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index));", elementTypeString, fieldConstName);
		out.println("}");
	}
	private void fieldLongPointerData(TypeRecord.Field field) {
		final var out         = javaFile.out;
		
		Type   fieldType      = field.type.getRealType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		
		Type   targetType     = fieldType.toTypePointer().targetType.getRealType();
		String taregTypeName  = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s(MemoryAccess access) {", taregTypeName, fieldName);
		out.println("return %s.longPointer(Mesa.read32(base + OFFSET_%s), access);", taregTypeName, fieldConstName);
		out.println("}");
	}
	private void fieldLongPointerPointer(TypeRecord.Field field) {
		final var out            = javaFile.out;
		
		Type   fieldType         = field.type.getRealType();
		String fieldConstName    = StringUtil.toJavaConstName(field.name);
		String fieldName         = StringUtil.toJavaName(field.name);
		
		Type   targetType        = fieldType.toTypePointer().targetType.getRealType();
		String taregTypeName   = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s() {", taregTypeName, fieldName);
		out.println("return %s.longPointer(Mesa.read32(base + OFFSET_%s));", taregTypeName, fieldConstName);
		out.println("}");
	}
	private void fieldPointerData(TypeRecord.Field field) {
		final var out         = javaFile.out;
		
		Type   fieldType      = field.type.getRealType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		
		Type   targetType     = fieldType.toTypePointer().targetType.getRealType();
		String taregTypeName  = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s(MemoryAccess access) {", taregTypeName, fieldName);
		out.println("return %s.pointer(Mesa.read16(base + OFFSET_%s), access);", taregTypeName, fieldConstName);
		out.println("}");
	}
	private void fieldPointerPointer(TypeRecord.Field field) {
		final var out            = javaFile.out;
		
		Type   fieldType         = field.type.getRealType();
		String fieldConstName    = StringUtil.toJavaConstName(field.name);
		String fieldName         = StringUtil.toJavaName(field.name);
		
		Type   targetType        = fieldType.toTypePointer().targetType.getRealType();
		String taregTypeName   = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s() {", taregTypeName, fieldName);
		out.println("return %s.pointer(Mesa.read16(base + OFFSET_%s));", taregTypeName, fieldConstName);
		out.println("}");
	}
	
	private void multiWordRecord(TypeRecord type) {
		final var out         = javaFile.out;
		final var parentClass = MemoryBase.class;
		
		classPreamble(parentClass);
		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();

		constructor(parentClass);
		out.println();
		
		out.println("//");
		out.println("// Access to Field of Record");
		out.println("//");			
		for(TypeRecord.Field field: type.fieldList) {
			Type   fieldType      = field.type.getRealType();
			String fieldConstName = StringUtil.toJavaConstName(field.name);

			out.println("// %s", field.toMesaType());
			// sanity check
			if ((field.startBit % WORD_BITS) == 0 && (field.bitSize % WORD_BITS) == 0) {
				//
			} else if (field.startBit == Field.NO_VALUE) {
				//
			} else {
				out.println("// FIXME  Field is not aligned");
				logger.warn("Field is not aligned");
				logger.warn("  name {}.{}", type.name, field.name);
				logger.warn("  mesa {}", field.toMesaType());
				continue;
			}
			
			out.println("private static final int OFFSET_%s = %d;", fieldConstName, field.offset);
			
			if (fieldType instanceof TypeArray) {
				// FIELD is ARRAY
				
				// if field is array of reference
				if (field.type instanceof TypeReference) {
					// FIELD is REFERENCE of ARRAY
					//   like BLOCK
					fieldPointer(field);
				} else {
					// FIELD is IMMEDIATE ARRAY
					// index of array can be subrange or reference
					Type elementType = fieldType.toTypeArray().arrayElement.getRealType();
					
					if (elementType instanceof TypeArray) {
						// FIELD is ARRAY of ARRAY
						throw new UnexpectedException("Unexpected");
					} else if (elementType instanceof TypeBoolean) {
						// FIELD is ARRAY of BOOLEAN
						fieldArrayIndexData(field);
					} else if (elementType instanceof TypeEnum) {
						// FIELD is ARRAY of ENUM
						fieldArrayIndexData(field);
					} else if (elementType instanceof TypePointer) {
						// FIELD is ARRAY of POINTER
						throw new UnexpectedException("Unexpected");
					} else if (elementType instanceof TypeRecord) {
						// FIELD is ARRAY of RECORD
						if (elementType.bitField16()) {
							// FIELD is ARRAY of BIT FIELD 16
							fieldArrayIndexData(field);
						} else if (elementType.bitField32()) {
							// FIELD is ARRAY of BIT FIELD 32
							fieldArrayIndexData(field);
						} else {
							// FIELD is ARRAY of MULTI WORD RECORD
							fieldArrayIndexPointer(field);
						}
					} else if (elementType instanceof TypeSubrange) {
						// FIELD is ARRAY of SUBRANGE
						fieldArrayIndexData(field);
					} else {
						throw new UnexpectedException("Unexpected");
					}
				}
			} else if (fieldType instanceof TypeBoolean) {
				// FIELD is BOOLEAN
				fieldData(field);
			} else if (fieldType instanceof TypeEnum) {
				// FIELD is ENUM
				fieldData(field);
			} else if (fieldType instanceof TypePointer) {
				// FIELD is POINTER
				TypePointer typePointer = fieldType.toTypePointer();
				if (typePointer.rawPointer()) {
					// FIELD is RAW POINTER (POINTER or LONG POINTER)
					fieldPointer(field);
				} else {
					Type targetType = typePointer.targetType.getRealType();
					if (typePointer.longPointer()) {
						// FIELD is LONG POINTER
						if (targetType instanceof TypeArray) {
							// FIELD is LONG POINTER to ARRAY
							if (typePointer.targetType instanceof TypeReference) {
								// FIELD is LONG POINTER to REFERNCE of ARRAY
								fieldLongPointerPointer(field);
							} else {
								// FIELD is LONG POINTER to IMMEDIATE ARRAY
								throw new UnexpectedException("Unexpected");
							}
						} else if (targetType instanceof TypeBoolean) {
							// FIELD is LONG POINTER to BOOLEAN
							fieldLongPointerData(field);
						} else if (targetType instanceof TypeEnum) {
							// FIELD is LONG POINTER to ENUM
							fieldLongPointerData(field);
						} else if (targetType instanceof TypePointer) {
							// FIELD is LONG POINTER to POINTER
							throw new UnexpectedException("Unexpected");
						} else if (targetType instanceof TypeRecord) {
							// FIELD is LONG POINTER to RECORD
							if (typePointer.targetType instanceof TypeReference) {
								// FIELD is LONG POINTER to REFERNCE of RECORD
								if (targetType.bitField16()) {
									// FIELD is LONG POINTER TO REFERENCE of BIT FIELD 16
									fieldLongPointerData(field);
								} else if (targetType.bitField32()) {
									// FIELD is LONG POINTER TO REFERENCE of BIT FIELD 32
									fieldLongPointerData(field);
								} else {
									// FIELD is LONG POINTER TO REFERENCE of MULTI WORD RECORD
									fieldLongPointerPointer(field);
								}
							} else {
								// FIELD is LONG POINTER to IMMEDIATE RECORD
								throw new UnexpectedException("Unexpected");
							}
						} else if (targetType instanceof TypeSubrange) {
							// FIELD is LONG POINTER to SUBRANGE
							fieldLongPointerData(field);
						} else {
							throw new UnexpectedException("Unexpected");
						}
					} else if (typePointer.shortPointer()) {
						// FIELD is POINTER
						if (targetType instanceof TypeArray) {
							// FIELD is POINTER to ARRAY
							if (typePointer.targetType instanceof TypeReference) {
								// FIELD is POINTER to REFERNCE of ARRAY
								fieldPointerPointer(field);
							} else {
								// FIELD is POINTER to IMMEDIATE ARRAY
								throw new UnexpectedException("Unexpected");
							}
						} else if (targetType instanceof TypeBoolean) {
							// FIELD is POINTER to BOOLEAN
							fieldPointerData(field);
						} else if (targetType instanceof TypeEnum) {
							// FIELD is POINTER to ENUM
							fieldPointerData(field);
						} else if (targetType instanceof TypePointer) {
							// FIELD is POINTER to POINTER
							throw new UnexpectedException("Unexpected");
						} else if (targetType instanceof TypeRecord) {
							// FIELD is POINTER to RECORD
							if (typePointer.targetType instanceof TypeReference) {
								// FIELD is POINTER to REFERNCE of RECORD
								if (targetType.bitField16()) {
									// FIELD is POINTER TO REFERENCE of BIT FIELD 16
									fieldPointerData(field);
								} else if (targetType.bitField32()) {
									// FIELD is POINTER TO REFERENCE of BIT FIELD 32
									fieldPointerData(field);
								} else {
									// FIELD is POINTER TO REFERENCE of MULTI WORD RECORD
									fieldPointerPointer(field);
								}
							} else {
								// FIELD is POINTER to IMMEDIATE RECORD
								throw new UnexpectedException("Unexpected");
							}
						} else if (targetType instanceof TypeSubrange) {
							// FIELD is POINTER to SUBRANGE
							fieldPointerData(field);
						} else {
							throw new UnexpectedException("Unexpected");
						}
					} else {
						throw new UnexpectedException("Unexpected");
					}
				}
			} else if (fieldType instanceof TypeRecord) {
				// FIELD is RECORD
				if (field.type instanceof TypeReference) {
					// FIELD is REFERENCE of RECORD
					if (fieldType.bitField16()) {
						fieldData(field);
					} else if (fieldType.bitField32()) {
						fieldData(field);
					} else {
						fieldPointer(field);
					}
				} else {
					// FIELD is IMMEDIATE RECORD
					throw new UnexpectedException("Unexpected");
				}
			} else if (fieldType instanceof TypeSubrange) {
				fieldData(field);
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		
		// close class body
		out.println("}");
	}
	private void typeRecord(TypeRecord type) {
		if (type.bitField16()) {
			bitField16Record(type);
		} else if (type.bitField32()) {
			bitField32Record(type);
		} else {
			multiWordRecord(type);
		}
	}

}
