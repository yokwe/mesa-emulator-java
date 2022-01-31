package yokwe.majuro.symbol;

import static yokwe.majuro.mesa.Constant.WORD_BITS;

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
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.AutoIndentPrintWriter.Layout;
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
	
	private static String parentClass(Type type) {
		Type realType = type.getRealType();
		int wordSize = realType.wordSize();
		
		if (realType instanceof TypeBoolean) {
			if (wordSize == 1) return "MemoryData16";
		}
		if (realType instanceof TypeEnum) {
			if (wordSize == 1) return "MemoryData16";
		}
		if (realType instanceof TypeSubrange) {
			if (wordSize == 1) return "MemoryData16";
			if (wordSize == 2) return "MemoryData32";
		}
		if (realType instanceof TypeRecord) {
			TypeRecord typeRecord = realType.toTypeRecord();
			if (typeRecord.isBitField16()) return "MemoryData16";
			if (typeRecord.isBitField32()) return "MemoryData32";
			return "MemoryBase";
		}
		if (realType instanceof TypePointer) {
			TypePointer typePointer = realType.toTypePointer();
			return typePointer.rawPointer() ? "MemoryBase" : null;
		}
		if (realType instanceof TypeArray) {
			return "MemoryBase";
		}
		throw new UnexpectedException("Uneexpected");
	}
	
	private void generate() {
		try (AutoIndentPrintWriter out = javaFile.getAutoIndentPrintWriter()) {
			final Type type = javaFile.type;
//			logger.info("{}: TYPE = {};", type.name, type.toMesaType());

			out.println("package %s;", javaFile.packageName);
			out.println();
			out.println("import yokwe.majuro.UnexpectedException;");
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println("import yokwe.majuro.mesa.Memory;");
			out.println("import yokwe.majuro.mesa.Mesa;");
			out.println();
			
			out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
			
			// output "public final class" line
			final String parentClass = parentClass(type);
			// start of class
			if (parentClass == null) {
				out.println("public final class %s {", javaFile.name);				
			} else {
				out.println("public final class %s extends %s {", javaFile.name, parentClass);
			}
			out.println("public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();");
			out.println("public static final String   NAME = SELF.getSimpleName();");
			out.println();
			
			generateType(type);

			// end of class
			out.println("}");
		}
		javaFile.moveFile();
	}

	private void generateType(Type type) {
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
	
	//
	// common methods
	//
	private void constructor16() {
		final var out = javaFile.out;
		
		out.println("//");
		out.println("// Constructor");
		out.println("//");
		
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
	}
	private void constructor32() {
		final var out = javaFile.out;

		out.println("//");
		out.println("// Constructor");
		out.println("//");
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
	}
	private void constructorBase() {
		final var out = javaFile.out;

		out.println("//");
		out.println("// Constructor");
		out.println("//");
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
	}
	
	//
	// methods for Record
	//
	private void bitField16() {
		final var out  = javaFile.out;
		final var type = javaFile.type.toTypeRecord();

		// single word bit field
		constructor16();
		out.println();

		out.println("//");
		out.println("// Bit Field");
		out.println("//");
		out.println();
		
		out.prepareLayout();
		for(var e: type.fieldList) {
			out.println("// %s", e.toMesaType());
		}
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT);
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
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
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
	}
	private void bitField32() {
		final var out  = javaFile.out;
		final var type = javaFile.type.toTypeRecord();

		// double word bit field
		constructor32();
		out.println();

		out.println("//");
		out.println("// Bit Field");
		out.println("//");
		out.println();
		
		out.prepareLayout();
		for(var e: type.fieldList) {
			out.println("// %s", e.toMesaType());
		}
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT);
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
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
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
	}
	
	//
	// methods generate java source for corresponding parameter type
	//
	
	private void typeBoolean(TypeBoolean type) {
		final var out = javaFile.out;

		out.prepareLayout();
		out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
		out.println();
		
		constructor16();
	}
	private void typeSubrange(TypeSubrange type) {
		final var out = javaFile.out;

		out.prepareLayout();
		out.println("public static final int  WORD_SIZE = %d;",  type.wordSize());
		out.println("public static final int  BIT_SIZE  = %d;",  type.bitSize());
		out.println();
		out.println("public static final long MIN_VALUE  = %s;", StringUtil.toJavaString(type.minValue));
		out.println("public static final long MAX_VALUE  = %s;", StringUtil.toJavaString(type.maxValue));
		out.println("public static final long SIZE_VALUE = %s;", StringUtil.toJavaString(type.maxValue - type.minValue + 1));
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
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
		if (type.bitSize() <= 16) {
			constructor16();
		} else if (type.bitSize() <= 32) {
			constructor32();
		} else {
			logger.error("type {}", type.toMesaType());
			logger.error("type {}", type);
			throw new UnexpectedException("Unexpected");
		}
	}
	private void typeEnum(TypeEnum type) {
		final var out = javaFile.out;

		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
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
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
		out.println();
		
		out.println("private static final int[] values = {");
		out.println("%s", valueList);
		out.println("};");
		out.println("private static final String[] names = {");
		out.println("%s", nameList);
		out.println("};");
		out.println("private static final ContextEnum context = new ContextEnum(NAME, values, names);");
		out.println();

		out.println("public static final void checkValue(char value) {");
		out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(value);");
		out.println("}");
		out.println();
		
		constructor16();
		out.println();

		out.println("public final String toString(char value) {");
		out.println("return context.toString(value);");
		out.println("}");
	}
	private void typePointer(TypePointer type) {
		final var out = javaFile.out;

		if (type.rawPointer()) {
			out.prepareLayout();
			out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
			out.println();
			
			constructorBase();
		} else {
			javaFile.success = false;
		}
	}
	private void typeArray(TypeArray type) {
		final var out = javaFile.out;
		
		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
		out.println();

		if (type instanceof TypeArray.Reference) {
			out.println("//");
			out.println("// Check range of index");
			out.println("//");

			var typeRef = type.toReference().typeReference.getRealType();
		    out.println("public static final void checkIndex(int value) {");
		    out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(value);", StringUtil.toJavaName(typeRef.name));
		    out.println("}");
		} else if (type instanceof TypeArray.Subrange) {
			// immediate subrange
			TypeSubrange typeSubrange = type.toSubrange().typeSubrange;
			if (!typeSubrange.isOpenSubrange()) {
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
		
		constructorBase();		
		
		//
		// output element access method
		//
		Type   elementType     = type.arrayElement.getRealType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		String indexTypeName   = elementTypeName;

		out.println("//");
		out.println("// Access to Element of Array");
		out.println("//");
		if (elementType.container()) {
			if (elementType instanceof TypePointer) {
				TypePointer typePointer = elementType.toTypePointer();
				if (typePointer.targetType != null) {
					elementType     = typePointer.targetType.getRealType();
					elementTypeName = StringUtil.toJavaName(elementType.name);
				}
				if (typePointer.shortPointer()) {
					indexTypeName = "POINTER";
				} else if (typePointer.longPointer()) {
					indexTypeName = "LONG POINTER";
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
			out.println("return %s.longPointer(base + (%s.WORD_SIZE * index));", elementTypeName, indexTypeName);
			out.println("}");
		} else {
			out.println("public final %s get(int index, MemoryAccess access) {", elementTypeName);
			out.println("return %s.longPointer(base + (%s.WORD_SIZE * index), access);", elementTypeName, indexTypeName);
			out.println("}");
		}
	}
	private void typeRecord(TypeRecord type) {
		final var out = javaFile.out;

		out.prepareLayout();
		out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
		out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
		out.println();

		if (type.isBitField16()) {
			bitField16();
		} else if (type.isBitField32()) {
			bitField32();
		} else {
			// multiple word
			// FIXME
			
			constructorBase();
			out.println();
			
			out.println("//");
			out.println("// Access to Field of Record");
			out.println("//");			
			for(TypeRecord.Field field: type.fieldList) {
				Type   fieldType      = field.type.getRealType();
				String fieldConstName = StringUtil.toJavaConstName(field.name);
				String fieldName      = StringUtil.toJavaName(field.name);
				String fieldTypeName  = StringUtil.toJavaName(fieldType.name);

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

				if (fieldType.container()) {
					// can be array, pointer or multiple word record
					if (fieldType instanceof TypeArray) {
						// index of array can be subrange or reference
						Type elementType = fieldType.toTypeArray().arrayElement.getRealType();
						String elementTypeString = StringUtil.toJavaName(elementType.name);
						
						if (elementType.container()) {
							if (fieldTypeName.contains("#")) {
								out.println("public %s %s(int index) {", elementTypeString, fieldName);
								out.println("return %1$s.longPointer(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index));", elementTypeString, fieldConstName);
								out.println("}");
							} else {
								out.println("public %s %s() {", fieldTypeName, fieldName);
								out.println("return %1$s.longPointer(base + OFFSET_%2$s);", fieldTypeName, fieldConstName);
								out.println("}");
							}
						} else {
							out.println("public %s %s(int index, MemoryAccess access) {", elementTypeString, fieldName);
							out.println("return %1$s.longPointer(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index), access);", elementTypeString, fieldConstName);
							out.println("}");
						}
						
					} else if (fieldType instanceof TypePointer) {
						TypePointer typePointer = fieldType.toTypePointer();
						if (typePointer.rawPointer()) {
							// naked POINTER and LONG POINTER
							String typeTargetName = StringUtil.toJavaName(typePointer.name);
							out.println("public %s %s() {", typeTargetName, fieldName);
							out.println("return %s.longPointer(base + OFFSET_%s);", typeTargetName, fieldConstName);
							out.println("}");
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
						out.println("public %s %s() {", fieldTypeName, fieldName);
						out.println("return %s.longPointer(base + OFFSET_%s);", fieldTypeName, fieldConstName);
						out.println("}");
					} else {
						throw new UnexpectedException("Unexpected");
					}
				} else {
					// can be boolean, enum, bitfield16, bitfield32 or subrange
					out.println("public %s %s(MemoryAccess access) {", fieldTypeName, fieldName);
					out.println("return %s.longPointer(base + OFFSET_%s, access);", fieldTypeName, fieldConstName);
					out.println("}");
				}
			}
		}
	}

}
