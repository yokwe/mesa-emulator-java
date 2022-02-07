package yokwe.majuro.symbol;

import static yokwe.majuro.mesa.Constants.WORD_BITS;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.LEFT;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.RIGHT;

import java.util.List;
import java.util.stream.Collectors;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeArrayRef;
import yokwe.majuro.symbol.model.TypeArraySub;
import yokwe.majuro.symbol.model.TypeBitField16;
import yokwe.majuro.symbol.model.TypeBitField32;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointerLong;
import yokwe.majuro.symbol.model.TypeMultiWord;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypePointerShort;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.type.MemoryBase;
import yokwe.majuro.type.MemoryData16;
import yokwe.majuro.type.MemoryData32;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.StringUtil;

public class ProcessDecl {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProcessDecl.class);

	// for java code generation
	private static boolean javaDataClass(Type type) {
		if (type instanceof TypeBoolean)    return true;
		if (type instanceof TypeEnum)       return true;
		if (type instanceof TypeSubrange)   return true;
		if (type instanceof TypeBitField16) return true;
		if (type instanceof TypeBitField32) return true;
		return false;
	}

	private static void classPreamble(JavaFile javaFile, Class<?> parentClass) {
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
		
		out.println("// %s", javaFile.type.toMesaDecl());
		
		out.println("public final class %s extends %s {", javaFile.name, parentClass.getSimpleName());
		out.println("public static final Class<?> SELF = java.lang.invoke.MethodHandles.lookup().lookupClass();");
		out.println("public static final String   NAME = SELF.getSimpleName();");
		out.println();
	}
	private static void constructor(JavaFile javaFile, Class<?> parentClass) {
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

	// array of not pointer
	private static void arrayElement(JavaFile javaFile, Type indexType, Type elementType) {
		final var out = javaFile.out;
		
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		if (javaDataClass(elementType)) {
			out.println("public final %s get(int index, MemoryAccess access) {", elementTypeName);
			if (indexType.bitSize() != 0) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("int longPointer = base + (%s.WORD_SIZE * index);", elementTypeName);
			out.println("return %s.longPointer(longPointer, access);", elementTypeName);
			out.println("}");
		} else {
			out.println("public final %s get(int index) {", elementTypeName);
			if (indexType.bitSize() != 0) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("int longPointer = base + (%s.WORD_SIZE * index);", elementTypeName);
			out.println("return %s.longPointer(longPointer);", elementTypeName, elementTypeName);
			out.println("}");
		}
	}	
	private static void arrayElement(JavaFile javaFile, Type indexType) {
		Type elementType = javaFile.type.arrayElement().realType();
		arrayElement(javaFile, indexType, elementType);
	}
	// array of short pointer
	private static void arrayIndirectShort(JavaFile javaFile, Type indexType, Type targetType) {
		final var out = javaFile.out;
		
		String elementTypeName = StringUtil.toJavaName(Type.POINTER.name);
		String targetTypeName  = StringUtil.toJavaName(targetType.name);
		
		if (javaDataClass(targetType)) {
			out.println("public final %s get(int index, MemoryAccess access) {", targetTypeName);
			if (indexType.bitSize() != 0) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("char pointer = Mesa.read16(base + (%s.WORD_SIZE * index));", elementTypeName);
			out.println("return %s.pointer(pointer, access);", targetTypeName);
			out.println("}");
		} else {
			out.println("public final %s get(int index) {", targetTypeName);
			if (indexType.bitSize() != 0) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("char pointer = Mesa.read16(base + (%s.WORD_SIZE * index));", elementTypeName);
			out.println("return %s.pointer(pointer);", targetTypeName);
			out.println("}");
		}
	}
	private static void arrayIndirectShortRaw(JavaFile javaFile, Type indexType) {
		arrayIndirectShort(javaFile, indexType, Type.POINTER);
	}
	private static void arrayIndirectShort(JavaFile javaFile, Type indexType) {
		Type elementType = javaFile.type.arrayElement().realType();
		Type targetType  = elementType.pointerTarget().realType();
				
		arrayIndirectShort(javaFile, indexType, targetType);
	}
	// array of long pointer
	private static void arrayIndirectLong(JavaFile javaFile, Type indexType, Type targetType) {
		final var out = javaFile.out;
		
		String elementTypeName = StringUtil.toJavaName(Type.LONG_POINTER.name);
		String targetTypeName  = StringUtil.toJavaName(targetType.name);
		
		if (javaDataClass(targetType)) {
			out.println("public final %s get(int index, MemoryAccess access) {", targetTypeName);
			if (indexType.bitSize() != 0) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("int longPointer = Mesa.read32(base + (%s.WORD_SIZE * index));", elementTypeName);
			out.println("return %s.longPointer(longPointer, access);", targetTypeName);
			out.println("}");
		} else {
			out.println("public final %s get(int index) {", targetTypeName);
			if (indexType.bitSize() != 0) {
				out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
			}
			out.println("int longPointer = Mesa.read32(base + (%s.WORD_SIZE * index));", elementTypeName);
			out.println("return %s.longPointer(longPointer);", targetTypeName);
			out.println("}");
		}
	}
	private static void arrayIndirectLongRaw(JavaFile javaFile, Type indexType) {
		arrayIndirectLong(javaFile, indexType, Type.LONG_POINTER);
	}
	private static void arrayIndirectLong(JavaFile javaFile, Type indexType) {
		Type elementType = javaFile.type.arrayElement().realType();
		Type targetType  = elementType.pointerTarget().realType();
		
		arrayIndirectLong(javaFile, indexType, targetType);
	}
	// record field
	private static void recordField(JavaFile javaFile, TypeRecord.Field field) {
		final var out = javaFile.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);

		if (javaDataClass(fieldType)) {
			out.println("public %s %s(MemoryAccess access) {", fieldTypeName, fieldName);
			out.println("int longPointer = base + OFFSET_%s;", fieldConstName);
			out.println("return %s.longPointer(longPointer, access);", fieldTypeName);
			out.println("}");
		} else {
			out.println("public %s %s() {", fieldTypeName, fieldName);
			out.println("int longPointer = base + OFFSET_%s;", fieldConstName);
			out.println("return %s.longPointer(longPointer);", fieldTypeName);
			out.println("}");
		}
	}
	private static void recordFieldIndirectLong(JavaFile javaFile, TypeRecord.Field field) {
		final var out = javaFile.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		Type   targetType     = field.type.realType().pointerTarget().realType();
		String targetTypeName = StringUtil.toJavaName(targetType.name);

		if (javaDataClass(targetType)) {
			out.println("public %s %s(MemoryAccess access) {", targetTypeName, fieldName);
			out.println("int longPointer = Mesa.read32(base + OFFSET_%s);", fieldConstName);
			out.println("return %s.longPointer(longPointer, access);", targetTypeName);
			out.println("}");
		} else {
			out.println("public %s %s() {", targetTypeName, fieldName);
			out.println("int longPointer = Mesa.read32(base + OFFSET_%s);", fieldConstName);
			out.println("return %s.longPointer(longPointer);", targetTypeName);
			out.println("}");
		}
	}
	private static void recordFieldIndirectShort(JavaFile javaFile, TypeRecord.Field field) {
		final var out = javaFile.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		Type   targetType     = field.type.realType().pointerTarget().realType();
		String targetTypeName = StringUtil.toJavaName(targetType.name);

		if (javaDataClass(targetType)) {
			out.println("public %s %s(MemoryAccess access) {", targetTypeName, fieldName);
			out.println("char pointer = Mesa.read16(base + OFFSET_%s);", fieldConstName);
			out.println("return %s.pointer(pointer, access);", targetTypeName);
			out.println("}");
		} else {
			out.println("public %s %s() {", targetTypeName, fieldName);
			out.println("char pointer = Mesa.read16(base + OFFSET_%s);", fieldConstName);
			out.println("return %s.pointer(pointer);", targetTypeName);
			out.println("}");
		}
	}


	
	public static void generateFile(JavaFile javaFile) {
		if (javaFile.type != null) {
			var processType = new ProcessTypeTop(javaFile);
			processType.process();
		} else if (javaFile.cons != null) {
			logger.info("CONSTANT {}", javaFile.cons.toMesaDecl());
			
			var processCons = new ProcessConsTop(javaFile);
			processCons.process();
		} else {
			throw new UnexpectedException("Unexpected");
		}
		
	}
	
	
	//
	// ProcessConsTop
	//
	private static class ProcessConsTop extends ProcessType {
		protected ProcessConsTop(JavaFile javaFile) {
			super(javaFile);
		}

		@Override
		public void process() {
			try (AutoIndentPrintWriter out = javaFile.getAutoIndentPrintWriter()) {
				// FIXME
				javaFile.success = false;
			}
			javaFile.moveFile();
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// TODO Auto-generated method stub
		}
		
	}
	
	
	//
	// ProcessTypeTop
	//
	private static class ProcessTypeTop extends ProcessType {
		ProcessTypeTop(JavaFile javaFile) {
			super(javaFile);
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
			try (AutoIndentPrintWriter out = javaFile.getAutoIndentPrintWriter()) {
				accept(javaFile.type);
			}
			javaFile.moveFile();
		}
		
		// simple type
		// BOOLEAN
		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			final var out         = javaFile.out;
			final var parentClass = typeBooleanParentClass[type.wordSize() - 1];
			
			classPreamble(javaFile, parentClass);

			out.prepareLayout();
			out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			constructor(javaFile, parentClass);
			
			// close class body
			out.println("}");

		}
		// ENUM
		@Override
		protected void processTypeEnum(TypeEnum type) {
			final var out         = javaFile.out;
			final var parentClass = typeEnumParentClass[type.wordSize() - 1];
			
			classPreamble(javaFile, parentClass);

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
			
			constructor(javaFile, parentClass);
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
			final var out         = javaFile.out;
			final var parentClass = typeSubrangeParentClass[type.wordSize() - 1];
			
			classPreamble(javaFile, parentClass);

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
			constructor(javaFile, parentClass);
			
			// close class body
			out.println("}");
		}
		
		// complex type
		// ARRAY
		private void process(TypeArray type) {
			ProcessTypeArray processType = new ProcessTypeArray(javaFile);
			processType.process();
		}
		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			process(type);
		}
		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			process(type);
		}

		// POINTER
		private void process(TypePointer type) {
			if (type.rawPointer()) {
				final var out         = javaFile.out;
				final var parentClass = typePointerParentClass[type.wordSize() - 1];

				classPreamble(javaFile, parentClass);

				out.prepareLayout();
				out.println("public static final int WORD_SIZE = %d;",     type.wordSize());
				out.println("public static final int BIT_SIZE  = %d;",     type.bitSize());
				out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
				out.println();
				
				constructor(javaFile, parentClass);
				
				// close class body
				out.println("}");
			} else {
				javaFile.success = false;
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
			final var out         = javaFile.out;
			final var parentClass = MemoryData16.class;
			
			classPreamble(javaFile, parentClass);
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();

			// single word bit field
			constructor(javaFile, parentClass);
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
		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is BIT FIELD 16
			final var out         = javaFile.out;
			final var parentClass = MemoryData32.class;
			
			classPreamble(javaFile, parentClass);
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();

			// double word bit field
			constructor(javaFile, parentClass);
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
		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			var processType = new ProcessTypeMultiWord(javaFile);
			processType.process();
		}

		// reference type
		// REFERENCE
		@Override
		protected void processTypeReference(TypeReference type) {
			javaFile.success = false;
		}
	}
	
	
	//
	// ProcessTypeArray
	//
	private static class ProcessTypeArray extends ProcessType {
		private Type indexType;

		ProcessTypeArray(JavaFile javaFile) {
			super(javaFile);
		}
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			final var type        = javaFile.type;
			final var out         = javaFile.out;
			final var parentClass = MemoryBase.class;

			classPreamble(javaFile, parentClass);
			
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();
			
			{
				boolean immediateSubrange = false;
				if (type instanceof TypeArraySub) {
					// INDEX is IMMEDIATE SUBRANGE
					indexType = type.toTypeArraySub().typeSubrange;
					immediateSubrange = true;
				} else if (type instanceof TypeArrayRef) {
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
				} else {
					throw new UnexpectedException("Unexpected");
				}
				
				if (indexType.bitSize() != 0) {
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
			
			constructor(javaFile, parentClass);		
			
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
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of ENUM
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SUBRANGE
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of ARRAY-REFERENCE
			unexpected(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of ARRAY-SUBRANGE
			unexpected(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of SHORT POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW SHORT POINTER
				arrayIndirectShortRaw(javaFile, indexType);
			} else {
				// ARRAY of SHORT POINTER
				var processType = new ProcessTypeArrayShortPointer(javaFile, indexType);
				processType.accept(type.pointerTarget.realType());
			}			
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of LONG POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW LONG POINTER
				arrayIndirectLongRaw(javaFile, type);
			} else {
				// ARRAY of LONG POINTER
				var processType = new ProcessTypeArrayLongPointer(javaFile, indexType);
				processType.accept(type.pointerTarget.realType());
			}
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of BIT FIELD 16
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of BIT FIELD 32
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of MULTI WORD RECORD
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of REFERENCE
			unexpected(type);
		}
	}
	
	// ProcessTypeArrayShortPointer
	private static class ProcessTypeArrayShortPointer extends ProcessType {
		private final Type indexType;
		
		protected ProcessTypeArrayShortPointer(JavaFile javaFile, Type indexType) {
			super(javaFile);
			this.indexType = indexType;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of SHORT POINTER to BOOLEAN
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of SHORT POINTER to ENUM
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SHORT POINTER to SUBRANGE
			arrayIndirectShort(javaFile, indexType);
		}

		private void process(TypeArray type) {
			// ARRAY of SHORT POINTER to ARRAY
			Type arrayElement = type.arrayElement;
			if (arrayElement instanceof TypeReference) {
				// ARRAY of SHORT POINTER to REFERENCE of ARRAY
				arrayIndirectShort(javaFile, indexType);
			} else {
				// ARRAY of SHORT POINTER to IMMEDIATE ARRAY
				unexpected(type);
			}
		}
		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of SHORT POINTER to ARRAY-REFERENCE
			process(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of SHORT POINTER to ARRAY-SUBRANGE
			process(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of SHORT POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of SHORT POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of SHORT POINTER to BIT FIELD 16
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of SHORT POINTER to BIT FIELD 32
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of SHORT POINTER to MULTI WORD RECORD
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of SHORT POINTER to REFERENCE
			unexpected(type);
		}
	}

	// ProcessArrayLongPointer
	private static class ProcessTypeArrayLongPointer extends ProcessType {
		private Type indexType;

		protected ProcessTypeArrayLongPointer(JavaFile javaFile, Type indexType) {
			super(javaFile);
			this.indexType = indexType;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of LONG POINTER to BOOLEAN
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of LONG POINTER to ENUM
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of LONG POINTER to SUBRANGE
			arrayIndirectLong(javaFile, indexType);
		}

		private void process(TypeArray type) {
			// ARRAY of LONG POINTER to ARRAY
			Type arrayElement = type.arrayElement;
			if (arrayElement instanceof TypeReference) {
				// ARRAY of LONG POINTER to REFERENCE of ARRAY
				arrayIndirectLong(javaFile, indexType);
			} else {
				// ARRAY of SHORT POINTER to IMMEDIATE ARRAY
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of LONG POINTER to ARRAY-REFERENCE
			process(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of LONG POINTER to ARRAY-SUBRANGE
			process(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of LONG POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of LONG POINTER to LONG POINTER
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of LONG POINTER to BIT FIELD 16
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of LONG POINTER to BIT FIELD 32
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of LONG POINTER to MULTI WORD RECORD
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of LONG POINTER to REFERENCE
			unexpected(type);
		}
	}

	
	//
	// ProcessTypeMultiWord
	//
	private static class ProcessTypeMultiWord extends ProcessField {
		ProcessTypeMultiWord(JavaFile javaFile) {
			super(javaFile);
		}
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			final var type        = javaFile.type.toTypeMultiWord();
			final var out         = javaFile.out;
			final var parentClass = MemoryBase.class;
			
			classPreamble(javaFile, parentClass);
			out.prepareLayout();
			out.println("public static final int    WORD_SIZE = %d;",     type.wordSize());
			out.println("public static final int    BIT_SIZE  = %d;",     type.bitSize());
			out.layout(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT, RIGHT);
			out.println();

			constructor(javaFile, parentClass);
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
					out.println("// FIXME  Field is not aligned");
					logger.warn("Field is not aligned");
					logger.warn("  name {}.{}", type.name, field.name);
					logger.warn("  mesa {}", field.toMesaType());
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
			recordField(javaFile, field);
		}

		@Override
		protected void processTypeEnum(Field field, TypeEnum fieldType) {
			// RECORD FIELD is ENUM
			recordField(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(Field field, TypeSubrange fieldType) {
			// RECORD FIELD is SUBRANGE
			recordField(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(Field field, TypeArrayRef fieldType) {
			// RECORD FIELD is ARRAY-REFERENCE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-REFERENCE
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-REFERENCE
				javaFile.out.println("// FIXME RECORD FIELD is IMMEDIATE ARRAY-REFERENCE"); // FIXME
				// FIXME use existing check method for check index value
			}
		}

		@Override
		protected void processTypeArraySubrange(Field field, TypeArraySub fieldType) {
			// RECORD FIELD is ARRAY-SUBRANGE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-SUBRANGE
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE
				javaFile.out.println("// FIXME RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE"); // FIXME
				// FIXME add ContextSubrange and use it for check index value
			}
		}

		@Override
		protected void processTypePointeShort(Field field, TypePointerShort fieldType) {
			// RECORD FIELD is SHORT POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW SHORT POINTER
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is SHORT POINTER
				var targetType  = fieldType.realType().pointerTarget().realType();
				var processType = new ProcessTypeRecordShortPointer(javaFile, field);
				processType.accept(targetType);
			}
		}

		@Override
		protected void processTypePointeLong(Field field, TypePointerLong fieldType) {
			// RECORD FIELD is LONG POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW LONG POINTER
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is LONG POINTER
				var targetType  = fieldType.realType().pointerTarget().realType();
				var processType = new ProcessTypeRecordLongPointer(javaFile, field);
				processType.accept(targetType);
			}
		}

		@Override
		protected void processTypeBitField16(Field field, TypeBitField16 fieldType) {
			// RECORD FIELD is BIT FIELD 16
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 16
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 16
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeBitField32(Field field, TypeBitField32 fieldType) {
			// RECORD FIELD is BIT FIELD 32
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 32
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 32
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeMultiWord(Field field, TypeMultiWord fieldType) {
			// RECORD FIELD is MULTI WORD RECORD
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of MULTI WORD RECORD
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE MULTI WORD RECORD
				throw new UnexpectedException("unexpected");
			}
		}
	}

	private static class ProcessTypeRecordShortPointer extends ProcessType {
		private final Field field;
		
		protected ProcessTypeRecordShortPointer(JavaFile javaFile, Field field) {
			super(javaFile);
			this.field = field;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// RECORD FIELD is SHORT POINTER to BOOLEAN
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// RECORD FIELD is SHORT POINTER to ENUM
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// RECORD FIELD is SHORT POINTER to SUBRANGE
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// RECORD FIELD is SHORT POINTER to ARRAY-REFERENCE
			javaFile.out.println("// FIXME RECORD FIELD is SHORT POINTER to ARRAY-REFERENCE"); // FIXME
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// RECORD FIELD is SHORT POINTER to ARRAY-SUBRANGE
			javaFile.out.println("// FIXME RECORD FIELD is SHORT POINTER to ARRAY-SUBRANGE"); // FIXME
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// RECORD FIELD is SHORT POINTER to SHORT POINTER
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// RECORD FIELD is SHORT POINTER to LONG POINTER
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is SHORT POINTER to BIT FIELD 16
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is SHORT POINTER to BIT FIELD 32
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// RECORD FIELD is SHORT POINTER to MULTI WORD RECORD
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// RECORD FIELD is SHORT POINTER to REFERENCE
			unexpected(type);
		}
	}
	
	private static class ProcessTypeRecordLongPointer extends ProcessType {
		private final Field field;
		
		protected ProcessTypeRecordLongPointer(JavaFile javaFile, Field field) {
			super(javaFile);
			this.field = field;
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// RECORD FIELD is LONG POINTER to BOOLEAN
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// RECORD FIELD is LONG POINTER to ENUM
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// RECORD FIELD is LONG POINTER to SUBRANGE
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// RECORD FIELD is LONG POINTER to ARRAY-REFERENCE
			javaFile.out.println("// FIXME RECORD FIELD is LONG POINTER to ARRAY-REFERENCE"); // FIXME
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// RECORD FIELD is LONG POINTER to ARRAY-SUBRANGE
			javaFile.out.println("// FIXME RECORD FIELD is LONG POINTER to ARRAY-SUBRANGE"); // FIXME
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// RECORD FIELD is LONG POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// RECORD FIELD is LONG POINTER to LONG POINTER
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is LONG POINTER to BIT FIELD 16
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is LONG POINTER to BIT FIELD 32
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// RECORD FIELD is LONG POINTER to MULTI WORD RECORD
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// RECORD FIELD is LONG POINTER to REFERENCE
			unexpected(type);
		}
	}

}
