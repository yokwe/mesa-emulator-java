package yokwe.majuro.symbol;

import static yokwe.majuro.mesa.Constants.WORD_BITS;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.LEFT;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.RIGHT;

import java.io.File;
import java.util.Arrays;
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
import yokwe.majuro.symbol.model.TypeArrayRef;
import yokwe.majuro.symbol.model.TypeArraySub;
import yokwe.majuro.symbol.model.TypeBitField16;
import yokwe.majuro.symbol.model.TypeBitField32;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypeMultiWord;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypePointerLong;
import yokwe.majuro.symbol.model.TypePointerShort;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.type.MemoryBase;
import yokwe.majuro.type.MemoryData16;
import yokwe.majuro.type.MemoryData32;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.StringUtil;


public class JavaDecl {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	private static final boolean DEBUG_SHOW_TYPE  = false;
	private static final boolean DEBUG_SHOW_TRACE = false;
	
	abstract static class ProcessType {
		public static void unexpected(Type type) {
			logger.error("type %s", type.toMesaDecl());
			throw new UnexpectedException("Unexpected");
		}
		public static void unexpected(Constant cons) {
			logger.error("const %s", cons.toMesaDecl());
			throw new UnexpectedException("Unexpected");
		}
		
		protected final JavaDecl javaDecl;
		
		protected ProcessType(JavaDecl javaDecl) {
			this.javaDecl = javaDecl;
		}
		
		//
		// Entry point
		//
		public abstract void process();

		//
		// call processTypeXXX depends on type of argument
		//
		public void accept(Type type) {
			if (type instanceof TypeBoolean) {
				processTypeBoolean(type.toTypeBoolean());
			} else if (type instanceof TypeEnum) {
				processTypeEnum(type.toTypeEnum());
			} else if (type instanceof TypeSubrange) {
				processTypeSubrange(type.toTypeSubrange());
			} else if (type instanceof TypeArrayRef) {
				processTypeArrayReference(type.toTypeArrayRef());
			} else if (type instanceof TypeArraySub) {
				processTypeArraySubrange(type.toTypeArraySub());
			} else if (type instanceof TypePointerShort) {
				processTypePointeShort(type.toTypePointerShort());
			} else if (type instanceof TypePointerLong) {
				processTypePointeLong(type.toTypePointerLong());
			} else if (type instanceof TypeBitField16) {
				processTypeBitField16(type.toTypeBitField16());
			} else if (type instanceof TypeBitField32) {
				processTypeBitField32(type.toTypeBitField32());
			} else if (type instanceof TypeMultiWord) {
				processTypeMultiWord(type.toTypeMultiWord());
			} else if (type instanceof TypeReference) {
				processTypeReference(type.toTypeReference());
			} else {
				unexpected(type);
			}
		}
		
		// simple type
		protected abstract void processTypeBoolean       (TypeBoolean  type);
		protected abstract void processTypeEnum          (TypeEnum     type);
		protected abstract void processTypeSubrange      (TypeSubrange type);
		
		// complex type
		protected abstract void processTypeArrayReference(TypeArrayRef type);
		protected abstract void processTypeArraySubrange (TypeArraySub  type);
		
		protected abstract void processTypePointeShort   (TypePointerShort type);
		protected abstract void processTypePointeLong    (TypePointerLong  type);
		
		protected abstract void processTypeBitField16    (TypeBitField16 type);
		protected abstract void processTypeBitField32    (TypeBitField32 type);
		protected abstract void processTypeMultiWord     (TypeMultiWord  type);

		// misc type
		protected abstract void processTypeReference     (TypeReference type);
	}

	abstract static class ProcessField {
		private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

		public static void unexpetected(Field field) {
			logger.error("field %s", field.toMesaType());
			throw new UnexpectedException("Unexpected");
		}
		
		protected final JavaDecl javaDecl;
		
		protected ProcessField(JavaDecl javaDecl) {
			this.javaDecl = javaDecl;
		}

		//
		// Entry point
		//
		public abstract void process();

		//
		// call processTypeXXX depends on type of argument
		//
		public void accept(Field field) {
			Type fieldType = field.type.realType();
			
			if (fieldType instanceof TypeBoolean) {
				processTypeBoolean(field, fieldType.toTypeBoolean());
			} else if (fieldType instanceof TypeEnum) {
				processTypeEnum(field, fieldType.toTypeEnum());
			} else if (fieldType instanceof TypeSubrange) {
				processTypeSubrange(field, fieldType.toTypeSubrange());
			} else if (fieldType instanceof TypeArrayRef) {
				processTypeArrayReference(field, fieldType.toTypeArrayRef());
			} else if (fieldType instanceof TypeArraySub) {
				processTypeArraySubrange(field, fieldType.toTypeArraySub());
			} else if (fieldType instanceof TypePointerShort) {
				processTypePointeShort(field, fieldType.toTypePointerShort());
			} else if (fieldType instanceof TypePointerLong) {
				processTypePointeLong(field, fieldType.toTypePointerLong());
			} else if (fieldType instanceof TypeBitField16) {
				processTypeBitField16(field, fieldType.toTypeBitField16());
			} else if (fieldType instanceof TypeBitField32) {
				processTypeBitField32(field, fieldType.toTypeBitField32());
			} else if (fieldType instanceof TypeMultiWord){
				processTypeMultiWord(field, fieldType.toTypeMultiWord());
			} else {
				unexpetected(field);
			}
		}
		
		// simple type
		protected abstract void processTypeBoolean       (Field field, TypeBoolean  fieldType);
		protected abstract void processTypeEnum          (Field field, TypeEnum     fieldType);
		protected abstract void processTypeSubrange      (Field field, TypeSubrange fieldType);
		
		// complex type
		protected abstract void processTypeArrayReference(Field field, TypeArrayRef fieldType);
		protected abstract void processTypeArraySubrange (Field field, TypeArraySub fieldType);
		
		protected abstract void processTypePointeShort   (Field field, TypePointerShort fieldType);
		protected abstract void processTypePointeLong    (Field field, TypePointerLong  fieldType);
		
		protected abstract void processTypeBitField16    (Field field, TypeBitField16 fieldType);
		protected abstract void processTypeBitField32    (Field field, TypeBitField32 fieldType);
		protected abstract void processTypeMultiWord     (Field field, TypeMultiWord  fieldType);
	}

	
	private final File outputFile;
	private final File tempFile;
	
	private String                name;
	private AutoIndentPrintWriter out;
	
	private Constant cons;
	private Type     type;

	private JavaDecl(String path) {
		outputFile = new File(path);
		tempFile   = new File("tmp/Generate.java");
	}
	private void moveFile() {
		boolean result = tempFile.renameTo(outputFile);
		if (!result) {
			logger.error("Failed to move file");
			logger.error("  tempFile  %s", tempFile.getPath());
			logger.error("  ouputFile %s", outputFile.getPath());
			throw new UnexpectedException();
		}
	}

	void generate(Decl decl) {
		if (decl instanceof DeclConstant) {
			this.cons = decl.toCons();
			this.type = null;
			this.name = StringUtil.toJavaConstName(cons.name);
			
			var processCons = new ProcessConsTop(this);
			processCons.process();
		} else if (decl instanceof DeclType) {
			this.cons = null;
			this.type = decl.toType();
			this.name = StringUtil.toJavaName(type.name);
			
			var processType = new ProcessTypeTop(this);
			processType.process();
		} else {
			throw new UnexpectedException();
		}
	}
	
	
	private static void classPreamble(JavaDecl javaDecl, Class<?> parentClass) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE classPreamble");
		
		final var out = javaDecl.out;
		out.println();
		
		if (parentClass == null) {
			throw new UnexpectedException("Unexpected");
		}
		
		out.println("//");
		out.println("// %s", javaDecl.type.toMesaDecl());
		out.println("//");
		
		out.println("public static final class %s extends %s {", javaDecl.name, parentClass.getSimpleName());
		out.println("public static final String NAME = \"%s\";", javaDecl.name);
		out.println();
	}
	private static void constructor(JavaDecl javaDecl, Class<?> parentClass) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE constructor");
		final var out = javaDecl.out;

		out.println("// Constructor");
		
		if (parentClass.equals(MemoryData16.class)) {
			out.println("public static final %s value(@Mesa.CARD16 int value) {", javaDecl.name);
			out.println("return new %s(value);", javaDecl.name);
			out.println("}");
			out.println("public static final %s value() {", javaDecl.name);
			out.println("return new %s(0);", javaDecl.name);
			out.println("}");
			out.println("public static final %s longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("return new %s(base, access);", javaDecl.name);
			out.println("}");
			out.println("public static final %s pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("return new %s(Memory.lengthenMDS(base), access);", javaDecl.name);
			out.println("}");
			out.println();
			
			out.println("private %s(@Mesa.CARD16 int value) {", javaDecl.name);
			out.println("super(value);");
			out.println("}");
			out.println("private %s(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("super(base, access);");
			out.println("}");
		} else if (parentClass.equals(MemoryData32.class)) {
			out.println("public static final %s value(@Mesa.CARD32 int value) {", javaDecl.name);
			out.println("return new %s(value);", javaDecl.name);
			out.println("}");
			out.println("public static final %s value() {", javaDecl.name);
			out.println("return new %s(0);", javaDecl.name);
			out.println("}");
			out.println("public static final %s longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("return new %s(base, access);", javaDecl.name);
			out.println("}");
			out.println("public static final %s pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("return new %s(Memory.lengthenMDS(base), access);", javaDecl.name);
			out.println("}");
			out.println();

			out.println("private %s(@Mesa.CARD32 int value) {", javaDecl.name);
			out.println("super(value);");
			out.println("}");
			out.println("private %s(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("super(base, access);");
			out.println("}");
		} else if (parentClass.equals(MemoryBase.class)) {
			out.println("public static final %s longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("return new %s(base, access);", javaDecl.name);
			out.println("}");
			out.println("public static final %s pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("return new %s(Memory.lengthenMDS(base), access);", javaDecl.name);
			out.println("}");
			out.println();
			
			out.println("private %s(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaDecl.name);
			out.println("super(base, access);");
			out.println("}");
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}

	// array of not pointer
	private static void arrayElement(JavaDecl javaDecl, Type indexType, Type elementType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayElement 3");
		final var out = javaDecl.out;
		
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		String indexTypeName   = StringUtil.toJavaName(indexType.name);
		
		if (indexTypeName.contains("#")) {
			// fix indexTypeName like ArraySubFixed#index
			String[] tokens = indexTypeName.split("#");
			if (tokens.length == 2) {
				if (tokens[0].equals(javaDecl.type.name) && tokens[1].equals("index")) {
					indexTypeName = "ArrayIndex";
					
					if (indexType.needsRangeCheck()) {					
						TypeSubrange typeSubrange = indexType.toTypeSubrange();
						String minValueString = StringUtil.toJavaString(typeSubrange.minValue);
						String maxValueString = StringUtil.toJavaString(typeSubrange.maxValue);
						
						out.println("private static final class %s {", indexTypeName);
						out.println("private static final ContextSubrange context = new ContextSubrange(NAME, %s, %s);",
							minValueString, maxValueString);
						out.println("private static final void checkValue(int value) {");
						out.println("context.check(value);");
						out.println("}");
						out.println("}");
					}
				} else {
					throw new UnexpectedException("Unexpected");
				}
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
		
		out.println("public final %s get(int index) {", elementTypeName);
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("int longPointer = base + (%s.WORD_SIZE * index);", elementTypeName);
		out.println("return %s.longPointer(longPointer, access);", elementTypeName);
		out.println("}");
	}	
	private static void arrayElement(JavaDecl javaDecl, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayElement 2");
		Type elementType = javaDecl.type.arrayElement().realType();
		arrayElement(javaDecl, indexType, elementType);
	}
	// array of short pointer
	private static void arrayIndirectShort(JavaDecl javaDecl, Type indexType, Type targetType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayIndirectShort 3");
		final var out = javaDecl.out;
		
		String indexTypeName   = StringUtil.toJavaName(indexType.name);
		String elementTypeName = StringUtil.toJavaName(Type.POINTER.name);
		String targetTypeName  = StringUtil.toJavaName(targetType.name);
		
		out.println("public final %s get(int index) {", targetTypeName);
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("int pointer = Memory.read16(base + (%s.WORD_SIZE * index));", elementTypeName);
		out.println("return %s.pointer(pointer, access);", targetTypeName);
		out.println("}");
		
		out.println("public final @Mesa.SHORT_POINTER int getValue(int index) {");
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("return Memory.read16(base + (%s.WORD_SIZE * index));", elementTypeName);
		out.println("}");
		out.println("public final %s getValue(int index, @Mesa.SHORT_POINTER int newValue) {", javaDecl.name);
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("Memory.write16(base + (%s.WORD_SIZE * index), newValue);", elementTypeName);
		out.println("return this;");
		out.println("}");
	}
	private static void arrayIndirectShortRaw(JavaDecl javaDecl, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayIndirectShortRaw");
		arrayIndirectShort(javaDecl, indexType, Type.POINTER);
	}
	private static void arrayIndirectShort(JavaDecl javaDecl, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayIndirectShort 2");
		Type elementType = javaDecl.type.arrayElement().realType();
		Type targetType  = elementType.pointerTarget().realType();
				
		arrayIndirectShort(javaDecl, indexType, targetType);
	}
	// array of long pointer
	private static void arrayIndirectLong(JavaDecl javaDecl, Type indexType, Type targetType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayIndirectLong 3");
		
		final var out = javaDecl.out;
		
		String indexTypeName   = StringUtil.toJavaName(indexType.name);
		String elementTypeName = StringUtil.toJavaName(Type.LONG_POINTER.name);
		String targetTypeName  = StringUtil.toJavaName(targetType.name);
		
		out.println("public final %s get(int index) {", targetTypeName);
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("int longPointer = Memory.read32(base + (%s.WORD_SIZE * index));", elementTypeName);
		out.println("return %s.longPointer(longPointer, access);", targetTypeName);
		out.println("}");
		
		out.println("public final @Mesa.LONG_POINTER int getValue(int index) {");
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("return Memory.read32(base + (%s.WORD_SIZE * index));", elementTypeName);
		out.println("}");
		out.println("public final %s getValue(int index, @Mesa.LONG_POINTER int newValue) {", javaDecl.name);
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("Memory.write32(base + (%s.WORD_SIZE * index), newValue);", elementTypeName);
		out.println("return this;");
		out.println("}");
	}
	private static void arrayIndirectLongRaw(JavaDecl javaDecl, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayIndirectLongRaw");
		arrayIndirectLong(javaDecl, indexType, Type.LONG_POINTER);
	}
	private static void arrayIndirectLong(JavaDecl javaDecl, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE arrayIndirectLong 2");
		Type elementType = javaDecl.type.arrayElement().realType();
		Type targetType  = elementType.pointerTarget().realType();
		
		arrayIndirectLong(javaDecl, indexType, targetType);
	}
	// record field
	private static void recordField(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordField");
		final var out = javaDecl.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);

		out.println("public %s %s() {", fieldTypeName, fieldName);
		out.println("int longPointer = base + OFFSET_%s;", fieldConstName);
		out.println("return %s.longPointer(longPointer, access);", fieldTypeName);
		out.println("}");
	}
	private static void recordFieldBoolean(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldBoolean");
		final var out = javaDecl.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);

		out.println("public final boolean %s() {", fieldName);
		out.println("return ((value & %1$s_MASK) >>> %1$s_SHIFT) != 0;", fieldConstName);
		out.println("}");
		
		out.println("public final %s %s(boolean newValue) {", javaDecl.name, fieldName);
		out.println("value = Types.toCARD16((value & ~%1$s_MASK) | (((newValue ? 1 : 0) << %1$s_SHIFT) & %1$s_MASK));", fieldConstName);
		out.println("return this;");
		out.println("}");
	}
	private static void recordFieldEnum(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldEnum");
		final var out = javaDecl.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);
		
		out.println("// @Mesa.ENUM is %s", fieldTypeName);
		
		out.println("public final @Mesa.ENUM int %s() {", fieldName);
		out.println("return Types.toCARD16((value & %1$s_MASK) >>> %1$s_SHIFT);", fieldConstName);
		out.println("}");
		
		out.println("public final %s %s(@Mesa.ENUM int newValue) {", javaDecl.name, fieldName);
		out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(newValue);", fieldTypeName);
		out.println("value = Types.toCARD16((value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK));", fieldConstName);
		out.println("return this;");
		out.println("}");
	}
	private static void recordFieldSubrange(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldSubrange");
		final var out = javaDecl.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);
		
		String mesaType = "CARD16";
		if (fieldType.bitSize() <= 8) mesaType = "CARD8";
		if (16 < fieldType.bitSize()) mesaType = "CARD32";
		
		out.println("// @Mesa.%s is %s", mesaType, fieldTypeName);
		
		out.println("public final @Mesa.%s int %s() {", mesaType, fieldName);
		out.println("return (value & %1$s_MASK) >>> %1$s_SHIFT;", fieldConstName);
		out.println("}");
		
		out.println("public final %s %s(@Mesa.%s int newValue) {", javaDecl.name, fieldName, mesaType);
		if (fieldType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(newValue);", fieldTypeName);
		}
		out.println("value = (value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK);", fieldConstName);
		out.println("return this;");
		out.println("}");
	}
	private static void recordFieldBitField16(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldBitField");
		final var out = javaDecl.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);
		
		out.println("// %s is BIT FIELD 16", fieldTypeName);
		out.println("public final %s %s() {", fieldTypeName, fieldName);
		out.println("return %1$s.value(Types.toCARD16((value & %2$s_MASK) >>> %2$s_SHIFT));", fieldTypeName, fieldConstName);
		out.println("}");
		
		out.println("public final %s %s(%s newValue) {", javaDecl.name, fieldName, fieldTypeName);
		out.println("value = Types.toCARD16((value & ~%1$s_MASK) | ((newValue.value << %1$s_SHIFT) & %1$s_MASK));", fieldConstName);
		out.println("return this;");
		out.println("}");
	}
	
	
	private static void recordFieldIndirectLong(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldIndirectLong");
		final var out = javaDecl.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		Type   targetType     = field.type.realType().pointerTarget().realType();
		String targetTypeName = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s() {", targetTypeName, fieldName);
		out.println("int longPointer = Memory.read32(base + OFFSET_%s);", fieldConstName);
		out.println("return %s.longPointer(longPointer, access);", targetTypeName);
		out.println("}");
		
		out.println("public final @Mesa.LONG_POINTER int %sValue() {", fieldName);
		out.println("return Memory.read32(base + OFFSET_%s);", fieldConstName);
		out.println("}");
		out.println("public final %s %sValue(@Mesa.LONG_POINTER int newValue) {", javaDecl.name, fieldName);
		out.println("Memory.write32(base + OFFSET_%s, newValue);", fieldConstName);
		out.println("return this;");
		out.println("}");
	}
	private static void recordFieldIndirectShort(JavaDecl javaDecl, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldIndirectShort");
		final var out = javaDecl.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		Type   targetType     = field.type.realType().pointerTarget().realType();
		String targetTypeName = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s() {", targetTypeName, fieldName);
		out.println("int pointer = Memory.read16(base + OFFSET_%s);", fieldConstName);
		out.println("return %s.pointer(pointer, access);", targetTypeName);
		out.println("}");
		
		out.println("public final @Mesa.SHORT_POINTER int %sValue() {", fieldName);
		out.println("return Memory.read16(base + OFFSET_%s);", fieldConstName);
		out.println("}");
		out.println("public final %s %sValue(@Mesa.SHORT_POINTER int newValue) {", javaDecl.name, fieldName);
		out.println("Memory.write16(base + OFFSET_%s, newValue);", fieldConstName);
		out.println("return this;");
		out.println("}");
	}

	private static void recordFieldArrayElement(JavaDecl javaDecl, TypeRecord.Field field, Type indexType, Type elementType) {
		if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE recordFieldArrayElement");
		final var out = javaDecl.out;
		
		String fieldConstName  = StringUtil.toJavaConstName(field.name);
		
		String indexTypeName   = StringUtil.toJavaName(indexType.name);
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		if (indexTypeName.contains("#")) {
			// fix indexTypeName like RecArraySubFixed#card1#index
			String[] tokens = indexTypeName.split("#");
			if (tokens.length == 3) {
				if (tokens[0].equals(javaDecl.type.name) && tokens[1].equals(field.name) && tokens[2].equals("index")) {
					indexTypeName = StringUtil.toJavaClassName(field.name) + "Index";
					
					if (indexType.needsRangeCheck()) {					
						TypeSubrange typeSubrange = indexType.toTypeSubrange();
						String minValueString = StringUtil.toJavaString(typeSubrange.minValue);
						String maxValueString = StringUtil.toJavaString(typeSubrange.maxValue);
						
						out.println("private static final class %s {", indexTypeName);
						out.println("private static final ContextSubrange context = new ContextSubrange(\"%s\", %s, %s);",
								javaDecl.name, minValueString, maxValueString);
						out.println("private static final void checkValue(int value) {");
						out.println("context.check(value);");
						out.println("}");
						out.println("}");
					}
				} else {
					throw new UnexpectedException("Unexpected");
				}
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}

		out.println("public final %s %s(int index) {", elementTypeName, field.name);
		if (indexType.needsRangeCheck()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(index);", indexTypeName);
		}
		out.println("int longPointer = base + OFFSET_%s + (%s.WORD_SIZE * index);", fieldConstName, elementTypeName);
		out.println("return %s.longPointer(longPointer, access);", elementTypeName);
		out.println("}");
	}
	
	//
	// ProcessConsTop
	//
	private static class ProcessConsTop extends ProcessType {
		Constant cons;
		Type     type;
		protected ProcessConsTop(JavaDecl javaDecl) {
			super(javaDecl);
			cons = javaDecl.cons;
			type = javaDecl.cons.type.realType();
		}

		@Override
		public void process() {
			final var out = javaDecl.out;
			out.println();
			out.println("//");
			out.println("// %s", javaDecl.cons.toMesaDecl());
			out.println("//");
			accept(type);
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - BOOLEAN");
			unexpected(cons);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - ENUM");
			unexpected(cons);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - SUBRANGE");
			
			final var out = javaDecl.out;

			if (type.name.equals(Type.CARDINAL.name)) {
				out.println("public static final @Mesa.CARD16 int %s = %s;", StringUtil.toJavaName(cons.name), cons.valueString);
				return;
			}
			
			if (type.name.equals(Type.LONG_CARDINAL.name)) {
				out.println("public static final @Mesa.CARD32 int %s = %s;", StringUtil.toJavaConstName(cons.name), cons.valueString);
				return;
			}
			
			unexpected(cons);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - ARRAY REF");
			unexpected(cons);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - ARRAY SUBRANGE");
			unexpected(cons);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - SHORT POINTER");
			final var out = javaDecl.out;
			
			String targetTypeName = StringUtil.toJavaName(type.pointerTarget().realType().name);
			out.println("public static final %s %s(MemoryAccess access) {", targetTypeName, StringUtil.toJavaName(cons.name));
			out.println("return %s.pointer(%s, access);", targetTypeName, cons.valueString);
			out.println("}");
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - LONG POINTER");
			final var out = javaDecl.out;
			
			String targetTypeName = StringUtil.toJavaName(type.pointerTarget().realType().name);
			out.println("public static final %s %s(MemoryAccess access) {", targetTypeName, StringUtil.toJavaName(cons.name));
			out.println("return %s.longPointer(%s, access);", targetTypeName, cons.valueString);
			out.println("}");
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - BIT FIELD 16");
			unexpected(cons);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - BIT FIELD 32");
			unexpected(cons);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - MULTI WORD");
			unexpected(cons);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// CONS - REFERENCE");
			unexpected(cons);
		}
		
	}
	
	
	//
	// ProcessTypeTop
	//
	private static class ProcessTypeTop extends ProcessType {
		ProcessTypeTop(JavaDecl javaDecl) {
			super(javaDecl);
		}
		
		private static final Class<?>[] typeBooleanParentClass = {
			MemoryData16.class,
		};
		private static final Class<?>[] typeEnumParentClass = {
			MemoryData16.class,
		};
		private static final Class<?>[] typeSubrangeParentClass = {
			MemoryData16.class,
			MemoryData32.class,
		};
		private static final Class<?>[] typePointerParentClass = {
			MemoryBase.class,
			MemoryBase.class,
		};
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			accept(javaDecl.type);
		}
		
		// simple type
		// BOOLEAN
		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			final var out         = javaDecl.out;
			final var parentClass = typeBooleanParentClass[type.wordSize() - 1];
			
			classPreamble(javaDecl, parentClass);

			out.prepareLayout();
			out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			constructor(javaDecl, parentClass);
			
			// close class body
			out.println("}");

		}
		// ENUM
		@Override
		protected void processTypeEnum(TypeEnum type) {
			final var out         = javaDecl.out;
			final var parentClass = typeEnumParentClass[type.wordSize() - 1];
			
			classPreamble(javaDecl, parentClass);

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
				out.println("public static final @Mesa.ENUM int %s = %s;", StringUtil.toJavaConstName(e.name), StringUtil.toJavaString(e.value));
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
			
			constructor(javaDecl, parentClass);
			out.println();

			out.println("public final String toString(int value) {");
			out.println("return context.toString(value);");
			out.println("}");
			
			// close class body
			out.println("}");
		}
		// SUBRANE
		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			final var out         = javaDecl.out;
			final var parentClass = typeSubrangeParentClass[type.wordSize() - 1];
			
			classPreamble(javaDecl, parentClass);

			out.prepareLayout();
			out.println("public static final int  WORD_SIZE = %d;",  type.wordSize());
			out.println("public static final int  BIT_SIZE  = %d;",  type.bitSize());
			if (type.needsRangeCheck()) {
				out.println();
				out.println("public static final int MIN_VALUE  = %s;", StringUtil.toJavaString(type.minValue));
				out.println("public static final int MAX_VALUE  = %s;", StringUtil.toJavaString(type.maxValue));
			}
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			if (type.needsRangeCheck()) {
				out.println("private static final ContextSubrange context = new ContextSubrange(NAME, MIN_VALUE, MAX_VALUE);");
				out.println();
				
				out.println("public static final void checkValue(int value) {");
				out.println("if (Debug.ENABLE_CHECK_VALUE) context.check(value);");
				out.println("}");
				out.println();
			}
			
			//
			// Generate Constructor
			//
			constructor(javaDecl, parentClass);
			
			// close class body
			out.println("}");
		}
		
		// complex type
		// ARRAY
		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			ProcessTypeArray processType = new ProcessTypeArray(javaDecl);
			processType.processArrayRef();
		}
		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			ProcessTypeArray processType = new ProcessTypeArray(javaDecl);
			processType.processArraySub();
		}

		// POINTER
		private void process(TypePointer type) {
			if (type.rawPointer()) {
				final var out         = javaDecl.out;
				final var parentClass = typePointerParentClass[type.wordSize() - 1];

				classPreamble(javaDecl, parentClass);

				out.prepareLayout();
				out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
				out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
				out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
				out.println();
				
				constructor(javaDecl, parentClass);
				
				// close class body
				out.println("}");
			} else {
				if (!type.name.contains("#")) {
					javaDecl.out.println("// IGNORE %s", type.toMesaDecl());
				}
			}
		}
		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			process(type);
		}
		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			process(type);
		}
		
		// RECORD
		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is BIT FIELD 16
			var processType = new ProcessTypeBitField(javaDecl);
			processType.processBitField16();
		}
		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is BIT FIELD 32
			var processType = new ProcessTypeBitField(javaDecl);
			processType.processBitField32();
		}
		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			var processType = new ProcessTypeMultiWord(javaDecl);
			processType.process();
		}

		// reference type
		// REFERENCE
		@Override
		protected void processTypeReference(TypeReference type) {
			if (!type.name.contains("#")) {
				javaDecl.out.println("// IGNORE %s", type.toMesaDecl());
			}
		}
	}
	
	
	//
	// ProcessTypeArray
	//
	private static class ProcessTypeArray extends ProcessType {
		private Type indexType;

		ProcessTypeArray(JavaDecl javaDecl) {
			super(javaDecl);
		}
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		public void processArraySub() {
			if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE ProcessTypeArray.processArraySub");
			final var type        = javaDecl.type;
			final var out         = javaDecl.out;
			final var parentClass = MemoryBase.class;

			classPreamble(javaDecl, parentClass);
			
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			{
				// INDEX is IMMEDIATE SUBRANGE
				indexType = type.toTypeArraySub().typeSubrange;
				
			}
			
			constructor(javaDecl, parentClass);		
			
			//
			// output element access method
			//
			out.println("//");
			out.println("// Access to Element of Array");
			out.println("//");
			
			accept(type.arrayElement().realType());
			
			// close class body
			out.println("}");
		}
		public void processArrayRef() {
			if (DEBUG_SHOW_TRACE) javaDecl.out.println("// TRACE ProcessTypeArray.processArrayRef");
			final var type        = javaDecl.type;
			final var out         = javaDecl.out;
			final var parentClass = MemoryBase.class;

			classPreamble(javaDecl, parentClass);
			
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			Type realType = type.toTypeArrayRef().typeReference.realType();
			if (realType instanceof TypeSubrange) {
				// INDEX is REFERENCE of SUBRANGE
				indexType = realType.toTypeSubrange();
			} else if (realType instanceof TypeEnum) {
				// INDEX is REFERENCE of ENUM
				indexType = realType.toTypeEnum();;
			} else {
				throw new UnexpectedException("Unexpected");
			}
			
			constructor(javaDecl, parentClass);		
			
			//
			// output element access method
			//
			out.println("//");
			out.println("// Access to Element of Array");
			out.println("//");
			
			accept(type.arrayElement().realType());
			
			// close class body
			out.println("}");
		}
		
		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of BOOLEAN");
			arrayElement(javaDecl, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of ENUM");
			arrayElement(javaDecl, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SUBRANGE");
			arrayElement(javaDecl, indexType);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of ARRAY-REFERENCE");
			unexpected(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SUBRANGE");
			unexpected(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of SHORT POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of RAW SHORT POINTER");
				arrayIndirectShortRaw(javaDecl, indexType);
			} else {
				// ARRAY of SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER");
				var processType = new ProcessTypeArrayShortPointer(javaDecl, indexType);
				processType.accept(type.pointerTarget.realType());
			}			
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of LONG POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW LONG POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of RAW LONG POINTER");
				arrayIndirectLongRaw(javaDecl, indexType);
			} else {
				// ARRAY of LONG POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER");
				var processType = new ProcessTypeArrayLongPointer(javaDecl, indexType);
				processType.accept(type.pointerTarget.realType());
			}
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of BIT FIELD 16");
			arrayElement(javaDecl, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of BIT FIELD 32");
			arrayElement(javaDecl, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of MULT WORD RECORD");
			arrayElement(javaDecl, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of REFERENCE");
			unexpected(type);
		}
	}
	
	// ProcessTypeArrayShortPointer
	private static class ProcessTypeArrayShortPointer extends ProcessType {
		private final Type indexType;
		
		protected ProcessTypeArrayShortPointer(JavaDecl javaDecl, Type indexType) {
			super(javaDecl);
			this.indexType = indexType;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of SHORT POINTER to BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to BOOLEAN");
			arrayIndirectShort(javaDecl, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of SHORT POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to ENUM");
			arrayIndirectShort(javaDecl, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SHORT POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to SUBRANGE");
			arrayIndirectShort(javaDecl, indexType);
		}

		private void process(TypeArray type) {
			// ARRAY of SHORT POINTER to ARRAY
			Type arrayElement = type.arrayElement;
			if (arrayElement instanceof TypeReference) {
				// ARRAY of SHORT POINTER to REFERENCE of ARRAY
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to REFERENCE of ARRAY");
				arrayIndirectShort(javaDecl, indexType);
			} else {
				// ARRAY of SHORT POINTER to IMMEDIATE ARRAY
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to IMMEDIATE ARRAY");
				unexpected(type);
			}
		}
		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of SHORT POINTER to ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to ARRAY-REFERENCE");
			process(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to ARRAY-SUBRANGE");
			// ARRAY of SHORT POINTER to ARRAY-SUBRANGE
			process(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of SHORT POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to SHORT POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of SHORT POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to LONG POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of SHORT POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to BIT FIELD 16");
			arrayIndirectShort(javaDecl, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of SHORT POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to BIT FIELD 32");
			arrayIndirectShort(javaDecl, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of SHORT POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to MULTI WORD RECORD");
			arrayIndirectShort(javaDecl, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of SHORT POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of SHORT POINTER to REFERENCE");
			unexpected(type);
		}
	}

	// ProcessArrayLongPointer
	private static class ProcessTypeArrayLongPointer extends ProcessType {
		private Type indexType;

		protected ProcessTypeArrayLongPointer(JavaDecl javaDecl, Type indexType) {
			super(javaDecl);
			this.indexType = indexType;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of LONG POINTER to BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to BOOLEAN");
			arrayIndirectLong(javaDecl, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of LONG POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to ENUM");
			arrayIndirectLong(javaDecl, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of LONG POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to SUBRANGE");
			arrayIndirectLong(javaDecl, indexType);
		}

		private void process(TypeArray type) {
			// ARRAY of LONG POINTER to ARRAY
			Type arrayElement = type.arrayElement;
			if (arrayElement instanceof TypeReference) {
				// ARRAY of LONG POINTER to REFERENCE of ARRAY
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to REFERENCE of ARRAY");
				arrayIndirectLong(javaDecl, indexType);
			} else {
				// ARRAY of SHORT POINTER to IMMEDIATE ARRAY
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to IMMEDIATE ARRAY");
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of LONG POINTER to ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to ARRAY-REFERENCE");
			process(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of LONG POINTER to ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to ARRAY-SUBRANGE");
			process(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of LONG POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to SHORT POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of LONG POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to LONG POINTERs");
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of LONG POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to BIT FIELD 16");
			arrayIndirectLong(javaDecl, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of LONG POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to BIT FIELD 32");
			arrayIndirectLong(javaDecl, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of LONG POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to MULTI WORD RECORD");
			arrayIndirectLong(javaDecl, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of LONG POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - ARRAY of LONG POINTER to REFERENCE");
			unexpected(type);
		}
	}

	
	//
	// ProcessTypeBitField
	//
	private static class ProcessTypeBitField extends ProcessField {
		ProcessTypeBitField(JavaDecl javaDecl) {
			super(javaDecl);
		}
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			throw new UnexpectedException();
		}

		private void processBitField16() {
			processBitField(MemoryData16.class);
		}
		private void processBitField32() {
			processBitField(MemoryData32.class);
		}
		private void processBitField(Class<?> parentClass) {
			final var type        = javaDecl.type.toTypeRecord();
			final var out         = javaDecl.out;
			
			classPreamble(javaDecl, parentClass);
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();

			constructor(javaDecl, parentClass);
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
				final int start  = e.startBit;
				final int stop   = e.stopBit;
				if (offset != 0) {
					logger.error("field %s", e.toMesaType());
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
			for(var field: type.fieldList) {
				accept(field);
				out.println();
			}
			
			// close class body
			out.println("}");
		}

		@Override
		protected void processTypeBoolean(Field field, TypeBoolean fieldType) {
			// RECORD FIELD is BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is BOOLEAN");
			recordFieldBoolean(javaDecl, field);
		}

		@Override
		protected void processTypeEnum(Field field, TypeEnum fieldType) {
			// RECORD FIELD is ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is ENUM");
			recordFieldEnum(javaDecl, field);
		}

		@Override
		protected void processTypeSubrange(Field field, TypeSubrange fieldType) {
			// RECORD FIELD is SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SUBRANGE");
			recordFieldSubrange(javaDecl, field);
		}

		@Override
		protected void processTypeArrayReference(Field field, TypeArrayRef fieldType) {
			// RECORD FIELD is ARRAY-REFERENCE
			throw new UnexpectedException();
		}

		@Override
		protected void processTypeArraySubrange(Field field, TypeArraySub fieldType) {
			// RECORD FIELD is ARRAY-SUBRANGE
			throw new UnexpectedException();
		}

		@Override
		protected void processTypePointeShort(Field field, TypePointerShort fieldType) {
			// RECORD FIELD is SHORT POINTER
			throw new UnexpectedException();
		}

		@Override
		protected void processTypePointeLong(Field field, TypePointerLong fieldType) {
			// RECORD FIELD is LONG POINTER
			throw new UnexpectedException();
		}

		@Override
		protected void processTypeBitField16(Field field, TypeBitField16 fieldType) {
			// RECORD FIELD is BIT FIELD 16
			recordFieldBitField16(javaDecl, field);
		}

		@Override
		protected void processTypeBitField32(Field field, TypeBitField32 fieldType) {
			// RECORD FIELD is BIT FIELD 32
			throw new UnexpectedException();
		}

		@Override
		protected void processTypeMultiWord(Field field, TypeMultiWord fieldType) {
			// RECORD FIELD is MULTI WORD RECORD
			throw new UnexpectedException();
		}
	}

	
	//
	// ProcessTypeMultiWord
	//
	private static class ProcessTypeMultiWord extends ProcessField {
		ProcessTypeMultiWord(JavaDecl javaDecl) {
			super(javaDecl);
		}
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			final var type        = javaDecl.type.toTypeMultiWord();
			final var out         = javaDecl.out;
			final var parentClass = MemoryBase.class;
			
			classPreamble(javaDecl, parentClass);
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();

			constructor(javaDecl, parentClass);
			out.println();
			
			out.println("//");
			out.println("// Access to Field of Record");
			out.println("//");			
			for(TypeRecord.Field field: type.fieldList) {
				String fieldConstName = StringUtil.toJavaConstName(field.name);

				out.println("// %s", field.toMesaType());
				// sanity check
				if ((field.startBit % WORD_BITS) == 0 && (field.bitSize % WORD_BITS) == 0) {
					//
				} else if (field.startBit == Field.NO_VALUE) {
					//
				} else {
					// FIXME
					out.println("// FIXME  Field is not aligned");
					logger.warn("Field is not aligned");
					logger.warn("  name %s.%s", type.name, field.name);
					logger.warn("  mesa %s", field.toMesaType());
					continue;
				}
				
				out.println("private static final int OFFSET_%s = %d;", fieldConstName, field.offset);
				accept(field);
			}
			
			// close class body
			out.println("}");
		}

		@Override
		protected void processTypeBoolean(Field field, TypeBoolean fieldType) {
			// RECORD FIELD is BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is BOOLEAN");
			recordField(javaDecl, field);
		}

		@Override
		protected void processTypeEnum(Field field, TypeEnum fieldType) {
			// RECORD FIELD is ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is ENUM");
			recordField(javaDecl, field);
		}

		@Override
		protected void processTypeSubrange(Field field, TypeSubrange fieldType) {
			// RECORD FIELD is SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SUBRANGE");
			recordField(javaDecl, field);
		}

		@Override
		protected void processTypeArrayReference(Field field, TypeArrayRef fieldType) {
			// RECORD FIELD is ARRAY-REFERENCE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is REFERENCE of ARRAY-REFERENCE");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is IMMEDIATE ARRAY-REFERENCE");
				Type indexType   = fieldType.typeReference.realType();
				Type elementType = fieldType.arrayElement().realType();
				recordFieldArrayElement(javaDecl, field, indexType, elementType);
			}
		}

		@Override
		protected void processTypeArraySubrange(Field field, TypeArraySub fieldType) {
			// RECORD FIELD is ARRAY-SUBRANGE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is REFERENCE of ARRAY-SUBRANGE");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE");
				Type indexType   = fieldType.typeSubrange;
				Type elementType = fieldType.arrayElement().realType();
				recordFieldArrayElement(javaDecl, field, indexType, elementType);
			}
		}

		@Override
		protected void processTypePointeShort(Field field, TypePointerShort fieldType) {
			// RECORD FIELD is SHORT POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is RAW SHORT POINTER");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER");
				var targetType  = fieldType.realType().pointerTarget().realType();
				var processType = new ProcessTypeRecordShortPointer(javaDecl, field);
				processType.accept(targetType);
			}
		}

		@Override
		protected void processTypePointeLong(Field field, TypePointerLong fieldType) {
			// RECORD FIELD is LONG POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW LONG POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is RAW LONG POINTER");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is LONG POINTER
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER");
				var targetType  = fieldType.realType().pointerTarget().realType();
				var processType = new ProcessTypeRecordLongPointer(javaDecl, field);
				processType.accept(targetType);
			}
		}

		@Override
		protected void processTypeBitField16(Field field, TypeBitField16 fieldType) {
			// RECORD FIELD is BIT FIELD 16
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 16
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is REFERENCE of BIT FIELD 16");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 16
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is IMMEDIATE BIT FIELD 16");
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeBitField32(Field field, TypeBitField32 fieldType) {
			// RECORD FIELD is BIT FIELD 32
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 32
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is REFERNCE of BIT FIELD 32");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 32
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is IMMEDIATE BIT FIELD 32");
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeMultiWord(Field field, TypeMultiWord fieldType) {
			// RECORD FIELD is MULTI WORD RECORD
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of MULTI WORD RECORD
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is REFERENCE of MULTI WORD RECORD");
				recordField(javaDecl, field);
			} else {
				// RECORD FIELD is IMMEDIATE MULTI WORD RECORD
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is IMMEDIATE MULT WORD RECORD");
				throw new UnexpectedException("unexpected");
			}
		}
	}

	private static class ProcessTypeRecordShortPointer extends ProcessType {
		private final Field field;
		
		protected ProcessTypeRecordShortPointer(JavaDecl javaDecl, Field field) {
			super(javaDecl);
			this.field = field;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// RECORD FIELD is SHORT POINTER to BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to BOOLEAN");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// RECORD FIELD is SHORT POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to ENUM");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// RECORD FIELD is SHORT POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to SUBRANGE");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// RECORD FIELD is SHORT POINTER to ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to ARRAY-REFERENCE");			
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-REFERENCE");
				recordFieldIndirectShort(javaDecl, field);
			} else {
				// RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-REFERENCE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// RECORD FIELD is SHORT POINTER to ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to ARRAY-SUBRANGE");			
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-SUBRANGE");
				recordFieldIndirectShort(javaDecl, field);
			} else {
				// RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-SUBRANGE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// RECORD FIELD is SHORT POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to SHORT POINTER");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// RECORD FIELD is SHORT POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to LONG POINTER");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is SHORT POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to BIT FIELD 16");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is SHORT POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to BIT FIELD 32");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// RECORD FIELD is SHORT POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to MULTI WORD RECORD");
			recordFieldIndirectShort(javaDecl, field);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// RECORD FIELD is SHORT POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is SHORT POINTER to REFERENCE");
			unexpected(type);
		}
	}
	
	private static class ProcessTypeRecordLongPointer extends ProcessType {
		private final Field field;
		
		protected ProcessTypeRecordLongPointer(JavaDecl javaDecl, Field field) {
			super(javaDecl);
			this.field = field;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// RECORD FIELD is LONG POINTER to BOOLEAN
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to BOOLEAN");
			recordFieldIndirectLong(javaDecl, field);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// RECORD FIELD is LONG POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to ENUM");
			recordFieldIndirectLong(javaDecl, field);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// RECORD FIELD is LONG POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to SUBRANGE");
			recordFieldIndirectLong(javaDecl, field);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// RECORD FIELD is LONG POINTER to ARRAY-REFERENCE
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-REFERENCE");
				recordFieldIndirectLong(javaDecl, field);
			} else {
				// RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// RECORD FIELD is LONG POINTER to ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to ARRAY-SUBRANGE");
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-SUBRANGE");
				recordFieldIndirectLong(javaDecl, field);
			} else {
				// RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// RECORD FIELD is LONG POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to SHORT POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// RECORD FIELD is LONG POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to LONG POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is LONG POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to BIT FIELD 16");
			recordFieldIndirectLong(javaDecl, field);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is LONG POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to BIT FIELD 32");
			recordFieldIndirectLong(javaDecl, field);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// RECORD FIELD is LONG POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to MULTI WORD RECORD");
			recordFieldIndirectLong(javaDecl, field);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// RECORD FIELD is LONG POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaDecl.out.println("// TYPE - RECORD FIELD is LONG POINTER to REFERENCE");
			unexpected(type);
		}
	}

	
	//
	// generate
	//
	public static void generate(String symbolDirPath, String outputDirPath, String packageName) {
		File symbolDir = new File(symbolDirPath);
		
		File[] files = symbolDir.listFiles((d, n) -> n.endsWith(".symbol"));
		Arrays.sort(files);
		for(var e: files) {
			boolean outputPredefinedType = e.getName().equals("PrincOps.symbol");
			generateFile(e.getAbsolutePath(), outputDirPath, packageName, outputPredefinedType);
		}
	}
	public static void generateFile(String symbolFilePath, String outputDirPath, String packageName, boolean outputPredefinedType) {
		logger.info("symbolFilePath  %s", symbolFilePath);
		logger.info("outputDirPath   %s", outputDirPath);
		logger.info("packageName     %s", packageName);
		
		Symbol symbol = Symbol.getInstance(symbolFilePath, outputPredefinedType);		
		
		String name = StringUtil.toJavaName(symbol.name);
		String path = String.format("%s/%s/%s.java", outputDirPath, packageName.replace('.', '/'), name);
		
		JavaDecl javaDecl = new JavaDecl(path);
		try(var out = new AutoIndentPrintWriter(javaDecl.tempFile)) {
			javaDecl.out = out;
			
			out.println("package %s;", packageName);
			out.println();
			out.println("import yokwe.majuro.mesa.Debug;");
			out.println("import yokwe.majuro.mesa.Memory;");
			out.println("import yokwe.majuro.mesa.Mesa;");
			out.println("import yokwe.majuro.mesa.Types;");
			out.println("import yokwe.majuro.type.PrincOps.*;");
			out.println();
			
			out.println("public final class %s {", StringUtil.toJavaName(name));
			
			for(Decl e: symbol.declList) {
				javaDecl.generate(e);
			}
			
			out.println("}");
		}
		javaDecl.moveFile();
	}

	public static void main(String[] args) {
		logger.info("START");
		
		generate("data/type/main", "src/main/java", "yokwe.majuro.type");
		generate("data/type/test", "src/test/java", "yokwe.majuro.type");
		
		logger.info("STOP");
	}


}
