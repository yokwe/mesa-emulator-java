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
	private void typeArray(TypeArray type) {
		final var out         = javaFile.out;
		final var parentClass = MemoryBase.class;

		classPreamble(parentClass);
		
		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
		out.println();

		boolean haveCheckIndex = true;
		
		if (type instanceof TypeArray.Reference) {
			out.println("//");
			out.println("// Check range of index");
			out.println("//");

			Type typeRef = type.toReference().typeReference.getRealType();
		    out.println("public static final void checkIndex(int value) {");
		    out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(value);", StringUtil.toJavaName(typeRef.name));
		    out.println("}");
		    
		    if (typeRef.openSubrange()) haveCheckIndex = false;
		} else if (type instanceof TypeArray.Subrange) {
			// immediate subrange
			TypeSubrange typeSubrange = type.toSubrange().typeSubrange;
			if (typeSubrange.openSubrange()) {
				haveCheckIndex = false;
			} else {
				haveCheckIndex = false;
				out.println("//");
				out.println("// Check range of index");
				out.println("//");

				out.println("private static final ContextSubrange contextIndex = new ContextSubrange(NAME + \"#index\", %s, %s);",
				    StringUtil.toJavaString(typeSubrange.minValue), StringUtil.toJavaString(typeSubrange.maxValue));
			    out.println("public static final void checkIndex(long value) {");
			    out.println("if (Debug.ENABLE_CHECK_VALUE) contextIndex.check(value);");
			    out.println("}");
				// if this type is unsigned, ...
				if (0 <= typeSubrange.minValue) {
					out.println("public static final void checkIndex(int value) {");
					out.println("if (Debug.ENABLE_CHECK_VALUE) contextIndex.check(Integer.toUnsignedLong(value));");
					out.println("}");
				}
			}
		} else {
			throw new UnexpectedException("Unexpected");
		}
		
		constructor(parentClass);		
		
		//
		// output element access method
		//
		Type   elementType     = type.arrayElement.getRealType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		String indexTypeName   = elementTypeName;

		out.println("//");
		out.println("// Access to Element of Array");
		out.println("//");
		
		// FIXME StatAllocationVector.get() is wrong. need to read pointer value from memory

		if (elementType.container()) {
			if (elementType instanceof TypePointer) {
				TypePointer typePointer = elementType.toTypePointer();
				if (typePointer.targetType != null) {
					elementType     = typePointer.targetType.getRealType();
					elementTypeName = StringUtil.toJavaName(elementType.name);
				}
				if (typePointer.shortPointer()) {
					indexTypeName = StringUtil.toJavaName(Type.POINTER.name);
				} else if (typePointer.longPointer()) {
					indexTypeName = StringUtil.toJavaName(Type.LONG_POINTER.name);
				} else {
					throw new UnexpectedException("Unexpected");
				}
			} else if (elementType instanceof TypeRecord) {
				//
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		
		// FIXME distinguish short and long pointer
		if (elementType.container()) {
			out.println("public final %s get(int index) {", elementTypeName);
			if (haveCheckIndex) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("return %s.longPointer(base + (%s.WORD_SIZE * index));", elementTypeName, indexTypeName);
			out.println("}");
		} else {
			out.println("public final %s get(int index, MemoryAccess access) {", elementTypeName);
			if (haveCheckIndex) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("return %s.longPointer(base + (%s.WORD_SIZE * index), access);", elementTypeName, indexTypeName);
			out.println("}");
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
		String elementTypeString = StringUtil.toJavaName(elementType.name);
		
		out.println("public %s %s(int index, MemoryAccess access) {", elementTypeString, fieldName);
		out.println("return %1$s.longPointer(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index), access);", elementTypeString, fieldConstName);
		out.println("}");
	}
	private void fieldArrayIndexPointer(TypeRecord.Field field) {
		final var out            = javaFile.out;

		Type   fieldType         = field.type.getRealType();
		String fieldConstName    = StringUtil.toJavaConstName(field.name);
		String fieldName         = StringUtil.toJavaName(field.name);

		Type   elementType       = fieldType.toTypeArray().arrayElement.getRealType();
		String elementTypeString = StringUtil.toJavaName(elementType.name);
		
		out.println("public %s %s(int index) {", elementTypeString, fieldName);
		out.println("return %1$s.longPointer(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index));", elementTypeString, fieldConstName);
		out.println("}");
	}
	private void fieldArrayReference(TypeRecord.Field field) {
		final var out            = javaFile.out;
		
		String fieldConstName    = StringUtil.toJavaConstName(field.name);
		String fieldName         = StringUtil.toJavaName(field.name);

		String elementTypeString = StringUtil.toJavaName(field.type.getRealType().name);
		
		out.println("public %s %s() {", elementTypeString, fieldName);
		out.println("return %1$s.longPointer(base + OFFSET_%2$s);", elementTypeString, fieldConstName);
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

		// multiple word
		// FIXME
		
		constructor(parentClass);
		out.println();
		
		out.println("//");
		out.println("// Access to Field of Record");
		out.println("//");			
		for(TypeRecord.Field field: type.fieldList) {
			Type   fieldType      = field.type.getRealType();
			String fieldConstName = StringUtil.toJavaConstName(field.name);
			String fieldName      = StringUtil.toJavaName(field.name);

			out.println("// %s", field.toMesaType());
			logger.info("// {}", field.toMesaType());
			logger.info("//   {}", fieldType.getClass().getSimpleName());
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
				if (field.type instanceof TypeReference) {
					fieldArrayReference(field);
					continue;
				}
				
				// index of array can be subrange or reference
				Type elementType = fieldType.toTypeArray().arrayElement.getRealType();
				
				if (elementType instanceof TypeArray) {
					throw new UnexpectedException("Unexpected");
				} else if (elementType instanceof TypeBoolean) {
					fieldArrayIndexData(field);
				} else if (elementType instanceof TypeEnum) {
					fieldArrayIndexData(field);
				} else if (elementType instanceof TypePointer) {
					logger.info("field {}", field.toMesaType());
					Type.dumpReference(field.type);
					Type.dumpReference(fieldType.toTypeArray().arrayElement);
					
					throw new UnexpectedException("Unexpected");
				} else if (elementType instanceof TypeRecord) {
					if (elementType.bitField16()) {
						fieldArrayIndexData(field);
					} else if (elementType.bitField32()) {
						fieldArrayIndexData(field);
					} else {
						fieldArrayIndexPointer(field);
					}
				} else if (elementType instanceof TypeSubrange) {
					fieldArrayIndexData(field);
				} else {
					throw new UnexpectedException("Unexpected");
				}
			} else if (fieldType instanceof TypeBoolean) {
				fieldData(field);
			} else if (fieldType instanceof TypeEnum) {
				fieldData(field);
			} else if (fieldType instanceof TypePointer) {
				TypePointer typePointer = fieldType.toTypePointer();
				if (typePointer.rawPointer()) {
					// naked POINTER and LONG POINTER
					fieldPointer(field);
				} else {
					Type typeTarget = typePointer.targetType.getRealType();
					String typeTargetName = StringUtil.toJavaName(typeTarget.name);
					if (typePointer.longPointer()) {
						if (typeTarget.container()) {
							out.println("public %s %s() {", typeTargetName, fieldName);
							out.println("return %s.longPointer(Mesa.read32(base + OFFSET_%s));", typeTargetName, fieldConstName);
							out.println("}");
						} else {
							out.println("public %s %s(MemoryAccess access) {", typeTargetName, fieldName);
							out.println("return %s.longPointer(Mesa.read32(base + OFFSET_%s), access);", typeTargetName, fieldConstName);
							out.println("}");
						}
					} else if (typePointer.shortPointer()) {
						if (typeTarget.container()) {
							out.println("public %s %s() {", typeTargetName, fieldName);
							out.println("return %s.pointer(Mesa.read16(base + OFFSET_%s));", typeTargetName, fieldConstName);
							out.println("}");
						} else {
							out.println("public %s %s(MemoryAccess access) {", typeTargetName, fieldName);
							out.println("return %s.pointer(Mesa.read16(base + OFFSET_%s), access);", typeTargetName, fieldConstName);
							out.println("}");
						}
					} else {
						throw new UnexpectedException("Unexpected");
					}
				}
			} else if (fieldType instanceof TypeRecord) {
				if (fieldType.bitField16()) {
					fieldData(field);
				} else if (fieldType.bitField32()) {
					fieldData(field);
				} else {
					if (field.type instanceof TypeReference) {
						fieldArrayReference(field);
						continue;
					}
					
					fieldPointer(field);
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
