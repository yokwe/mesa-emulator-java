package yokwe.majuro.symbol;

import static yokwe.majuro.mesa.Constant.WORD_BITS;

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
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.AutoIndentPrintWriter.Layout;
import yokwe.majuro.util.StringUtil;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	public static final String TYPE_RULE_FILE_PATH  = Symbol.PATH_RULE_FILE_TYPE;
	public static final String TYPE_OUTPUT_DIR_PATH = "src/main/java";
	public static final String TYPE_PACKAGE_NAME    = "yokwe.majuro.type";
	
	public static final String TEST_RULE_FILE_PATH  = Symbol.PATH_RULE_FILE_TEST;
	public static final String TEST_OUTPUT_DIR_PATH = "src/test/java";
	public static final String TEST_PACKAGE_NAME    = "yokwe.majuro.type";
	
	
	private static class Context {
		final String   name;
		
		final Type     type;
		final Constant cons;		
		
		boolean       success;

		public Context(Decl decl) {
			name      = StringUtil.toJavaName(decl.name);
			
			if (decl instanceof DeclType) {
				type = ((DeclType)decl).value;
				cons = null;
			} else if (decl instanceof DeclConstant) {
				type      = null;
				cons      = ((DeclConstant)decl).value;
			} else {
				throw new UnexpectedException("Unexpected");
			}
			
			success   = true;
		}
	}
	
	private static void genDecl(Context context, AutoIndentPrintWriter out, Type type) {
		switch(type.kind) {
		case BOOLEAN:
			genDecl(context, out, type.toTypeBoolean());
			break;
		case SUBRANGE:
			genDecl(context, out, type.toTypeSubrange());
			break;
		case ARRAY:
			genDecl(context, out, type.toTypeArray());
			break;
		case ENUM:
			genDecl(context, out, type.toTypeEnum());
			break;
		case RECORD:
			genDecl(context, out, type.toTypeRecord());
			break;
		case POINTER:
			genDecl(context, out, type.toTypePointer());
			break;
		case REFERENCE:
			logger.info("genDecl REF {}: TYPE = {}", type.name, type.toMesaType());
			context.success = false;
			break;
		default:
			logger.error("type {}  {}", type.name, type.kind);
			logger.error("mesa {}", type.toMesaType());
			throw new UnexpectedException("Unexpected");
		}
	}
	
	private static void getConstructor16(Context context, AutoIndentPrintWriter out) {
		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(char value) {", context.name);
		out.println("super(value);");
		out.println("}");
		
		out.println("public %s(int base, MemoryAccess access) {", context.name);
		out.println("super(base, access);");
		out.println("}");
		
		out.println("public %s(int base, int index, MemoryAccess access) {", context.name);
		out.println("super(base + (WORD_SIZE * index), access);");
		out.println("}");
	}

	private static void getConstructor32(Context context, AutoIndentPrintWriter out) {
		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(int value) {", context.name);
		out.println("super(value);");
		out.println("}");
		
		out.println("public %s(int base, MemoryAccess access) {", context.name);
		out.println("super(base, access);");
		out.println("}");
		
		out.println("public %s(int base, int index, MemoryAccess access) {", context.name);
		out.println("super(base + (WORD_SIZE * index), access);");
		out.println("}");
	}

	private static void getConstructorBase(Context context, AutoIndentPrintWriter out, String elementSize) {
		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(int base) {", context.name);
		out.println("super(base);");
		out.println("}");
		
		if (!elementSize.equals("ELEMENT_WORD_SIZE")) {
			out.println("public %s(int base, int index) {", context.name);
			out.println("super(base + (%s * index));", elementSize);
			out.println("}");
		}
	}

	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeBoolean type) {
		//
		// Generate Constructor
		//
		getConstructor16(context, out);
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeSubrange type) {
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
		if (type.bitSize <= 16) {
			getConstructor16(context, out);
		} else if (type.bitSize <= 32) {
			getConstructor32(context, out);
		} else {
			logger.error("type {}", type.toMesaType());
			logger.error("type {}", type);
			throw new UnexpectedException("Unexpected");
		}
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeArray type) {
		// FIXME
		Type   elementType     = type.arrayElement.getRealType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		String elementWordSize = Integer.toString(elementType.wordSize());
		String indexMinValue   = StringUtil.toJavaString(type.minValue);
		String indexMaxValue   = StringUtil.toJavaString(type.maxValue);
		
		if (!elementTypeName.contains("#")) {
			elementWordSize = String.format("%s.WORD_SIZE", elementTypeName);
		}
		if (elementType instanceof TypePointer) {
			TypePointer typePointer = elementType.toTypePointer();
			switch(typePointer.pointerSize) {
			case LONG:
				elementWordSize = "LONG_POINER.WORD_SIZE";
				break;
			case SHORT:
				elementWordSize = "POINTER.WORD_SIZE";
				break;
			default:
				throw new UnexpectedException("Unexpected");
			}
		}
		if (type instanceof TypeArray.Reference) {
			Type typeIndex = ((TypeArray.Reference)type).typeReference.getRealType();
			String typeIndexName = StringUtil.toJavaName(typeIndex.name);
			if (typeIndex instanceof TypeEnum) {
				indexMinValue = String.format("%s.MIN_VALUE", typeIndexName);
				indexMaxValue = String.format("%s.MAX_VALUE", typeIndexName);
			} else if (typeIndex instanceof TypeSubrange) {
				indexMinValue = String.format("(int)%s.MIN_VALUE", typeIndexName);
				indexMaxValue = String.format("(int)%s.MAX_VALUE", typeIndexName);
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		if (type instanceof TypeArray.Subrange) {
			TypeSubrange typeSub = ((TypeArray.Subrange)type).typeSubrange;
			// special for open subrange
			if (typeSub.isOpenSubrange()) {
				indexMinValue = "(int)INTEGER.MIN_VALUE";
				indexMaxValue = "(int)CARDINAL.MAX_VALUE";
			}
		}
		
		out.prepareLayout();
		out.println("public static final int INDEX_MIN_VALUE   = %s;", indexMinValue);
		out.println("public static final int INDEX_MAX_VALUE   = %s;", indexMaxValue);
		out.println("public static final int ELEMENT_WORD_SIZE = %s;", elementWordSize);
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
		out.println();
		
		// FIXME use original Context of index
		out.println("public static final ContextSubrange context = new ContextSubrange(\"%s#index\", INDEX_MIN_VALUE, INDEX_MAX_VALUE);", type.name);
		
		getConstructorBase(context, out, "ELEMENT_WORD_SIZE");
		out.println();
		
		
		out.println("//");
		out.println("// Access to Element of Array");
		out.println("//");
		if (elementType.container()) {
			if (elementType instanceof TypePointer) {
				TypePointer typePointer = elementType.toTypePointer();
				switch (typePointer.pointerSize) {
				case LONG:
					elementTypeName = "LONG_POINTER";
					break;
				case SHORT:
					elementTypeName = "POINTER";
					break;
				default:
					throw new UnexpectedException("Unexpected");
				}
			}
			out.println("public %s element(int index) {", elementTypeName);
			out.println("return new %s(base + (ELEMENT_WORD_SIZE * index));", elementTypeName);
			out.println("}");
		} else {
			out.println("public %s element(int index, MemoryAccess memoryAccess) {", elementTypeName);
			out.println("return new %s(base + (ELEMENT_WORD_SIZE * index), memoryAccess);", elementTypeName);
			out.println("}");
		}
		
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeEnum type) {
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
		
		getConstructor16(context, out);
		out.println();

		out.println("@Override");
		out.println("public String toString() {", context.name);
		out.println("return context.toString(value);");
		out.println("}");
	}
	
	private static void genDeclBitField16(Context context, AutoIndentPrintWriter out, TypeRecord type) {
		// single word bit field
		getConstructor16(context, out);
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
			int shift = type.bitSize - stop - 1;
			int mask  = (pat << shift);
			
			out.println("private static final int %s_MASK  = %s;",
				fieldCons, StringUtil.toJavaBinaryString(Integer.toUnsignedLong(mask), type.bitSize));
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
	private static void genDeclBitField32(Context context, AutoIndentPrintWriter out, TypeRecord type) {
		// double word bit field
		getConstructor32(context, out);
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
			int shift = type.bitSize - stop - 1;
			int mask  = (pat << shift);
			
			out.println("private static final int %s_MASK  = %s;", fieldCons, StringUtil.toJavaBinaryString(Integer.toUnsignedLong(mask), type.bitSize));
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
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeRecord type) {
		if (type.isBitField16()) {
			genDeclBitField16(context, out, type);
		} else if (type.isBitField32()) {
			genDeclBitField32(context, out, type);
		} else {
			// multiple word
			// FIXME
			
			getConstructorBase(context, out, "WORD_SIZE");
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
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypePointer type) {
		// FIXME
		final String targetTypeName;
		if (type.targetType != null) {
			targetTypeName = StringUtil.toJavaName(type.targetType.getRealType().name);
		} else {
			targetTypeName = StringUtil.toJavaName(type.name);
		}
		getConstructorBase(context, out, String.format("%s.WORD_SIZE", targetTypeName));
	}
	
	private static void genDecl(Context context, AutoIndentPrintWriter out, Constant cons) {
		// FIXME
		context.success = false; // FIXME
	}
	
	private static void genDecl(Decl decl, String outputDirPath, String packageName) {
		String name      = StringUtil.toJavaName(decl.name);
		String path      = String.format("%s/%s/%s.java", outputDirPath, packageName.replace('.', '/'), name);
		File   ouputFile = new File(path);
		File   tempFile  = new File("tmp/Generate.java");
		
		Context context = new Context(decl);

		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(tempFile)) {
			if (context.type != null) {
				Type type = context.type;
				
//				logger.info("{}: TYPE = {};", type.name, type.toMesaType());
				
				out.println("package %s;", packageName);
				out.println();
				out.println("import yokwe.majuro.UnexpectedException;");
				out.println("import yokwe.majuro.mesa.Debug;");
				out.println("import yokwe.majuro.mesa.Memory;");
				out.println("import yokwe.majuro.mesa.Mesa;");

				out.println();
				
				out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
				
				// output "public final class XXX" line
				{
					final String parentClass;
					
					if (type.container()) {
						parentClass = "MemoryBase";
					} else {
						if (type.bitSize == 0) {
							logger.error("%s: TYPE = %s;", type.name, type.toMesaType());
							throw new UnexpectedException("Uneexpected");
						} else if (type.bitSize <= 16) {
							parentClass = "MemoryData16";
						} else if (type.bitSize <= 32) {
							parentClass = "MemoryData32";
						} else {
							logger.error("%s: TYPE = %s;", type.name, type.toMesaType());
							throw new UnexpectedException("Uneexpected");
						}
					}
					
					out.println("public final class %s extends %s {", context.name, parentClass);
				}
				//
				
				out.prepareLayout();
				out.println("public static final String NAME      = \"%s\";", context.name);
				out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
				out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
				out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
				out.println();
				
				genDecl(context, out, type);
			} else if (context.cons != null) {
				Constant cons = context.cons;
				
//				logger.info("{}: {} = {};", cons.name, cons.type.toMesaType(), cons.valueString);
				
				out.println("package %s;", packageName);
				out.println();
				out.println("import yokwe.majuro.mesa.Debug;");
				out.println();
				
				out.println("// %s: %s = %s;", cons.name, cons.type.toMesaType(), cons.valueString);
				
				out.println("public final class %s {", context.name);
				out.println("public static final String NAME = \"%s\";", context.name);
				out.println();

				genDecl(context, out, cons);
			} else {
				throw new UnexpectedException("Unexpected");
			}
			
			out.println("}"); // end of class
			
			if (context.success) {
				boolean success = tempFile.renameTo(ouputFile);
				if (!success) {
					logger.error("Failed to move file");
					logger.error("  tempFile  {}", tempFile.getPath());
					logger.error("  ouputFile {}", ouputFile.getPath());
					throw new UnexpectedException("Unexpected");
				}
			}
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	
	public static void process(String ruleFilePath, String outputDirPath, String packageName) {
		logger.info("ruleFilePath  {}",  ruleFilePath);
		logger.info("outputDirPath {}", outputDirPath);
		logger.info("packageName   {}",   packageName);
		
		Symbol symbol = Symbol.getInstance(ruleFilePath);
		
		for(var e: symbol.declList) {
			genDecl(e, outputDirPath, packageName);
		}
	}
	public static void main(String[] args) {
		logger.info("START");
				
		process(TYPE_RULE_FILE_PATH, TYPE_OUTPUT_DIR_PATH, TYPE_PACKAGE_NAME);
		
		logger.info("STOP");
	}
}
