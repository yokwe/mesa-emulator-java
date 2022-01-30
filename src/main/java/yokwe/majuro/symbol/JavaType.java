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
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.type.LONG_POINTER;
import yokwe.majuro.type.POINTER;
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
			final String parentClass;
			{
				if (type.container()) {
					parentClass = "MemoryBase";
				} else {
					if (type.bitSize() == 0) {
						logger.error("%s: TYPE = %s;", type.name, type.toMesaType());
						throw new UnexpectedException("Uneexpected");
					} else if (type.bitSize() <= 16) {
						parentClass = "MemoryData16";
					} else if (type.bitSize() <= 32) {
						parentClass = "MemoryData32";
					} else {
						logger.error("%s: TYPE = %s;", type.name, type.toMesaType());
						throw new UnexpectedException("Uneexpected");
					}
				}
			}
			// start of class
			out.println("public final class %s extends %s {", javaFile.name, parentClass);
			
			out.prepareLayout();
			out.println("public static final String NAME      = \"%s\";", javaFile.name);
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
			out.println();
			
			generateType(type);

			// end of class
			out.println("}");
		}
		javaFile.moveFile();
	}

	private void generateType(Type type) {
		switch(type.kind) {
		case BOOLEAN:
			typeBoolean(type.toTypeBoolean());
			break;
		case SUBRANGE:
			typeSubrange(type.toTypeSubrange());
			break;
		case ENUM:
			typeEnum(type.toTypeEnum());
			break;
		case POINTER:
			typePointer(type.toTypePointer());
			break;
		case ARRAY:
			typeArray(type.toTypeArray());
			break;
		case RECORD:
			typeRecord(type.toTypeRecord());
			break;
		case REFERENCE:
			logger.info("genDecl REF {}: TYPE = {}", type.name, type.toMesaType());
			javaFile.success = false;
			break;
		default:
			logger.error("type {}  {}", type.name, type.kind);
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
		out.println("public %s(char value) {", javaFile.name);
		out.println("super(value);");
		out.println("}");
		
		out.println("public %s(int base, MemoryAccess access) {", javaFile.name);
		out.println("super(base, access);");
		out.println("}");
	}
	private void constructor32() {
		final var out = javaFile.out;

		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(int value) {", javaFile.name);
		out.println("super(value);");
		out.println("}");
		
		out.println("public %s(int base, MemoryAccess access) {", javaFile.name);
		out.println("super(base, access);");
		out.println("}");
	}
	private void constructorBase() {
		final var out = javaFile.out;

		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(int base) {", javaFile.name);
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
			
			out.println("public int %s() {", fieldName);
			out.println("return (value & %1$s_MASK) >> %1$s_SHIFT;", fieldCons);
			out.println("}");
			
			out.println("public void %s(int newValue) {", fieldName);
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
			
			out.println("public int %s() {", fieldName);
			out.println("return (value & %1$s_MASK) >> %1$s_SHIFT;", fieldCons);
			out.println("}");
			
			out.println("public void %s(int newValue) {", fieldName);
			out.println("value = (value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK);", fieldCons);
			out.println("}");
			out.println();
		}
	}
	
	//
	// methods generate java source for corresponding parameter type
	//
	
	private void typeBoolean(TypeBoolean type) {
		constructor16();
	}
	private void typeSubrange(TypeSubrange type) {
		final var out = javaFile.out;

		out.prepareLayout();
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
		
		// if this type is unsigned, ...
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

		out.println("public static final void checkValue(int value) {");
		out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(value);");
		out.println("}");
		out.println();
		
		constructor16();
		out.println();

		out.println("@Override");
		out.println("public String toString() {", javaFile.name);
		out.println("return context.toString(value);");
		out.println("}");
	}
	private void typePointer(TypePointer type) {
		// FIXME pointer to array of X
		final String targetTypeName;
		if (type.targetType != null) {
			targetTypeName = StringUtil.toJavaName(type.targetType.getRealType().name);
		} else {
			targetTypeName = StringUtil.toJavaName(type.name);
		}
		constructorBase();
	}
	private void typeArray(TypeArray type) {
		final var out = javaFile.out;
		
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
					out.println("public static void checkIndex(int value) {");
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
		String IndexTypeName = elementTypeName;

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
				switch(typePointer.pointerSize) {
				case SHORT:
					IndexTypeName = POINTER.NAME;
					break;
				case LONG:
					IndexTypeName = LONG_POINTER.NAME;
					break;
				default:
					throw new UnexpectedException("Unexpected");
				}
			} else if (elementType instanceof TypeRecord) {
				//
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		
		if (elementType.container()) {
			out.println("public %s element(int index) {", elementTypeName);
			out.println("return new %s(base + (%s.WORD_SIZE * index));", elementTypeName, IndexTypeName);
			out.println("}");
		} else {
			out.println("public %s element(int index, MemoryAccess memoryAccess) {", elementTypeName);
			out.println("return new %s(base + (%s.WORD_SIZE * index), memoryAccess);", elementTypeName, IndexTypeName);
			out.println("}");
		}
	}
	private void typeRecord(TypeRecord type) {
		final var out = javaFile.out;

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
								out.println("return new %1$s(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index));", elementTypeString, fieldConstName);
								out.println("}");
							} else {
								out.println("public %s %s() {", fieldTypeName, fieldName);
								out.println("return new %1$s(base + OFFSET_%2$s);", fieldTypeName, fieldConstName);
								out.println("}");
							}
							
						} else {
							out.println("public %s %s(int index, MemoryAccess memoryAccess) {", elementTypeString, fieldName);
							out.println("return new %1$s(base + OFFSET_%2$s + (%1$s.WORD_SIZE * index), memoryAccess);", elementTypeString, fieldConstName);
							out.println("}");
						}
						
					} else if (fieldType instanceof TypePointer) {
						TypePointer typePointer = fieldType.toTypePointer();
						if (typePointer.targetType == null) {
							// naked POINTER and LONG POINTER
							String typeTargetName = StringUtil.toJavaName(typePointer.name);
							out.println("public %s %s() {", typeTargetName, fieldName);
							out.println("return new %s(base + OFFSET_%s);", typeTargetName, fieldConstName);
							out.println("}");
						} else {
							Type typeTarget = typePointer.targetType.getRealType();
							String typeTargetName = StringUtil.toJavaName(typeTarget.name);
							// 
							if (typeTarget.container()) {
								out.println("public %s %s() {", typeTargetName, fieldName);
								out.println("return new %s(base + OFFSET_%s);", typeTargetName, fieldConstName);
								out.println("}");
							} else {
								out.println("public %s %s(MemoryAccess memoryAccess) {", typeTargetName, fieldName);
								out.println("return new %s(base + OFFSET_%s, memoryAccess);", typeTargetName, fieldConstName);
								out.println("}");
							}
						}
					} else if (fieldType instanceof TypeRecord) {
						out.println("public %s %s() {", fieldTypeName, fieldName);
						out.println("return new %s(base + OFFSET_%s);", fieldTypeName, fieldConstName);
						out.println("}");
					} else {
						throw new UnexpectedException("Unexpected");
					}
				} else {
					// can be boolean, enum, bitfield16, bitfield32 or subrange
					out.println("public %s %s(MemoryAccess memoryAccess) {", fieldTypeName, fieldName);
					out.println("return new %s(base + OFFSET_%s, memoryAccess);", fieldTypeName, fieldConstName);
					out.println("}");
				}
			}
		}
	}

}
