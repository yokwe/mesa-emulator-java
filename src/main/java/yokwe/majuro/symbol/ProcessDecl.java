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
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	private static final boolean DEBUG_SHOW_TYPE  = false;
	private static final boolean DEBUG_SHOW_TRACE = false;
	
	
	private static void classPreamble(JavaFile javaFile, Class<?> parentClass) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE classPreamble");
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
		out.println("import yokwe.majuro.mesa.Types;");
		out.println();
		
		out.println("// %s", javaFile.type.toMesaDecl());
		
		out.println("public final class %s extends %s {", javaFile.name, parentClass.getSimpleName());
		out.println("public static final String NAME = \"%s\";", javaFile.name);
		out.println();
	}
	private static void constructor(JavaFile javaFile, Class<?> parentClass) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE constructor");
		final var out = javaFile.out;

		out.println("//");
		out.println("// Constructor");
		out.println("//");
		
		if (parentClass.equals(MemoryData16.class)) {
			out.println("public static final %s value(@Mesa.CARD16 int value) {", javaFile.name);
			out.println("return new %s(value);", javaFile.name);
			out.println("}");
			out.println("public static final %s value() {", javaFile.name);
			out.println("return new %s(0);", javaFile.name);
			out.println("}");
			out.println("public static final %s longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(base, access);", javaFile.name);
			out.println("}");
			out.println("public static final %s pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(Memory.lengthenMDS(base), access);", javaFile.name);
			out.println("}");
			out.println();
			
			out.println("private %s(@Mesa.CARD16 int value) {", javaFile.name);
			out.println("super(value);");
			out.println("}");
			out.println("private %s(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("super(base, access);");
			out.println("}");
		} else if (parentClass.equals(MemoryData32.class)) {
			out.println("public static final %s value(@Mesa.CARD32 int value) {", javaFile.name);
			out.println("return new %s(value);", javaFile.name);
			out.println("}");
			out.println("public static final %s value() {", javaFile.name);
			out.println("return new %s(0);", javaFile.name);
			out.println("}");
			out.println("public static final %s longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(base, access);", javaFile.name);
			out.println("}");
			out.println("public static final %s pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(Memory.lengthenMDS(base), access);", javaFile.name);
			out.println("}");
			out.println();

			out.println("private %s(@Mesa.CARD32 int value) {", javaFile.name);
			out.println("super(value);");
			out.println("}");
			out.println("private %s(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("super(base, access);");
			out.println("}");
		} else if (parentClass.equals(MemoryBase.class)) {
			out.println("public static final %s longPointer(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(base, access);", javaFile.name);
			out.println("}");
			out.println("public static final %s pointer(@Mesa.SHORT_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("return new %s(Memory.lengthenMDS(base), access);", javaFile.name);
			out.println("}");
			out.println();
			
			out.println("private %s(@Mesa.LONG_POINTER int base, MemoryAccess access) {", javaFile.name);
			out.println("super(base, access);");
			out.println("}");
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}

	// array of not pointer
	private static void arrayElement(JavaFile javaFile, Type indexType, Type elementType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayElement 3");
		final var out = javaFile.out;
		
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		String indexTypeName   = StringUtil.toJavaName(indexType.name);
		
		if (indexTypeName.contains("#")) {
			// fix indexTypeName like ArraySubFixed#index
			String[] tokens = indexTypeName.split("#");
			if (tokens.length == 2) {
				if (tokens[0].equals(javaFile.type.name) && tokens[1].equals("index")) {
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
	private static void arrayElement(JavaFile javaFile, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayElement 2");
		Type elementType = javaFile.type.arrayElement().realType();
		arrayElement(javaFile, indexType, elementType);
	}
	// array of short pointer
	private static void arrayIndirectShort(JavaFile javaFile, Type indexType, Type targetType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayIndirectShort 3");
		final var out = javaFile.out;
		
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
		
		// FIXME getValue()  getValue(newValue)
		out.println("public final @Mesa.SHORT_POINTER int getValue(int index) {", targetTypeName);
		out.println("return Memory.read16(base + (%s.WORD_SIZE * index));", elementTypeName);
		out.println("}");
		out.println("public final void getValue(int index, @Mesa.SHORT_POINTER int newValue) {", targetTypeName);
		out.println("Memory.write16(base + (%s.WORD_SIZE * index), newValue);", elementTypeName);
		out.println("}");
	}
	private static void arrayIndirectShortRaw(JavaFile javaFile, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayIndirectShortRaw");
		arrayIndirectShort(javaFile, indexType, Type.POINTER);
	}
	private static void arrayIndirectShort(JavaFile javaFile, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayIndirectShort 2");
		Type elementType = javaFile.type.arrayElement().realType();
		Type targetType  = elementType.pointerTarget().realType();
				
		arrayIndirectShort(javaFile, indexType, targetType);
	}
	// array of long pointer
	private static void arrayIndirectLong(JavaFile javaFile, Type indexType, Type targetType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayIndirectLong 3");
		
		final var out = javaFile.out;
		
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
		
		// FIXME getValue()  getValue(newValue)
		out.println("public final @Mesa.LONG_POINTER int getValue(int index) {", targetTypeName);
		out.println("return Memory.read32(base + (%s.WORD_SIZE * index));", elementTypeName);
		out.println("}");
		out.println("public final void getValue(int index, @Mesa.LONG_POINTER int newValue) {", targetTypeName);
		out.println("Memory.write32(base + (%s.WORD_SIZE * index), newValue);", elementTypeName);
		out.println("}");
	}
	private static void arrayIndirectLongRaw(JavaFile javaFile, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayIndirectLongRaw");
		arrayIndirectLong(javaFile, indexType, Type.LONG_POINTER);
	}
	private static void arrayIndirectLong(JavaFile javaFile, Type indexType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE arrayIndirectLong 2");
		Type elementType = javaFile.type.arrayElement().realType();
		Type targetType  = elementType.pointerTarget().realType();
		
		arrayIndirectLong(javaFile, indexType, targetType);
	}
	// record field
	private static void recordField(JavaFile javaFile, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE recordField");
		final var out = javaFile.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);

		out.println("public %s %s() {", fieldTypeName, fieldName);
		out.println("int longPointer = base + OFFSET_%s;", fieldConstName);
		out.println("return %s.longPointer(longPointer, access);", fieldTypeName);
		out.println("}");
	}
	private static void recordFieldIndirectLong(JavaFile javaFile, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE recordFieldIndirectLong");
		final var out = javaFile.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		Type   targetType     = field.type.realType().pointerTarget().realType();
		String targetTypeName = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s() {", targetTypeName, fieldName);
		out.println("int longPointer = Memory.read32(base + OFFSET_%s);", fieldConstName);
		out.println("return %s.longPointer(longPointer, access);", targetTypeName);
		out.println("}");
		
		// FIXME %sValue()  %sValue(newValue)
		out.println("public final @Mesa.LONG_POINTER int %sValue() {", fieldName);
		out.println("return Memory.read32(base + OFFSET_%s);", fieldConstName);
		out.println("}");
		out.println("public final void %sValue(@Mesa.LONG_POINTER int newValue) {", fieldName);
		out.println("Memory.write32(base + OFFSET_%s, newValue);", fieldConstName);
		out.println("}");
	}
	private static void recordFieldIndirectShort(JavaFile javaFile, TypeRecord.Field field) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE recordFieldIndirectShort");
		final var out = javaFile.out;

		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		Type   targetType     = field.type.realType().pointerTarget().realType();
		String targetTypeName = StringUtil.toJavaName(targetType.name);

		out.println("public %s %s() {", targetTypeName, fieldName);
		out.println("int pointer = Memory.read16(base + OFFSET_%s);", fieldConstName);
		out.println("return %s.pointer(pointer, access);", targetTypeName);
		out.println("}");
		
		// FIXME %sValue()  %sValue(newValue)
		out.println("public final @Mesa.SHORT_POINTER int %sValue() {", fieldName);
		out.println("return Memory.read16(base + OFFSET_%s);", fieldConstName);
		out.println("}");
		out.println("public final void %sValue(@Mesa.SHORT_POINTER int newValue) {", fieldName);
		out.println("Memory.write16(base + OFFSET_%s, newValue);", fieldConstName);
		out.println("}");
	}

	private static void recordFieldArrayElement(JavaFile javaFile, TypeRecord.Field field, Type indexType, Type elementType) {
		if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE recordFieldArrayElement");
		final var out = javaFile.out;
		
		String fieldConstName  = StringUtil.toJavaConstName(field.name);
		
		String indexTypeName   = StringUtil.toJavaName(indexType.name);
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		if (indexTypeName.contains("#")) {
			// fix indexTypeName like RecArraySubFixed#card1#index
			String[] tokens = indexTypeName.split("#");
			if (tokens.length == 3) {
				if (tokens[0].equals(javaFile.type.name) && tokens[1].equals(field.name) && tokens[2].equals("index")) {
					indexTypeName = StringUtil.toJavaClassName(field.name) + "Index";
					
					if (indexType.needsRangeCheck()) {					
						TypeSubrange typeSubrange = indexType.toTypeSubrange();
						String minValueString = StringUtil.toJavaString(typeSubrange.minValue);
						String maxValueString = StringUtil.toJavaString(typeSubrange.maxValue);
						
						out.println("private static final class %s {", indexTypeName);
						out.println("private static final ContextSubrange context = new ContextSubrange(\"%s\", %s, %s);",
								javaFile.name, minValueString, maxValueString);
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
	
	public static void generateFile(JavaFile javaFile) {
		if (javaFile.type != null) {
			var processType = new ProcessTypeTop(javaFile);
			processType.process();
		} else if (javaFile.cons != null) {
			logger.info("CONSTANT %s", javaFile.cons.toMesaDecl());
			
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
			constructor(javaFile, parentClass);
			
			// close class body
			out.println("}");
		}
		
		// complex type
		// ARRAY
		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			ProcessTypeArray processType = new ProcessTypeArray(javaFile);
			processType.processArrayRef();
		}
		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			ProcessTypeArray processType = new ProcessTypeArray(javaFile);
			processType.processArraySub();
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
					logger.error("field %s", e);
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
			for(var field: type.fieldList) {
				String fieldName = StringUtil.toJavaName(field.name);
				String fieldCons = StringUtil.toJavaConstName(field.name);
				
				// FIXME handle BOOLEAN, ENUM and Subrange field properly
				Type   fieldType     = field.type.realType();
				String fieldTypeName = StringUtil.toJavaName(fieldType.name);

				if (fieldType instanceof TypeBoolean) {
					out.println("public final boolean %s() {", fieldName);
					out.println("return ((value & %1$s_MASK) >>> %1$s_SHIFT) != 0;", fieldCons);
					out.println("}");
					
					out.println("public final %s %s(boolean newValue) {", javaFile.name, fieldName);
					out.println("value = Types.toCARD16((value & ~%1$s_MASK) | (((newValue ? 1 : 0) << %1$s_SHIFT) & %1$s_MASK));", fieldCons);
					out.println("return this;");
					out.println("}");
					out.println();
					continue;
				}
				if (fieldType instanceof TypeEnum) {
					out.println("// @Mesa.ENUM is %s", fieldTypeName);
					
					out.println("public final @Mesa.ENUM int %s() {", fieldName);
					out.println("return Types.toCARD16((value & %1$s_MASK) >>> %1$s_SHIFT);", fieldCons);
					out.println("}");
					
					out.println("public final %s %s(@Mesa.ENUM int newValue) {", javaFile.name, fieldName);
					out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(newValue);", fieldTypeName);
					out.println("value = Types.toCARD16((value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK));", fieldCons);
					out.println("return this;");
					out.println("}");
					out.println();
					continue;
				}
				if (fieldType instanceof TypeSubrange) {
					out.println("// @Mesa.CARD16 is %s", fieldTypeName);
					
					out.println("public final @Mesa.CARD16 int %s() {", fieldName);
					out.println("return Types.toCARD16((value & %1$s_MASK) >>> %1$s_SHIFT);", fieldCons);
					out.println("}");
					
					out.println("public final %s %s(@Mesa.CARD16 int newValue) {", javaFile.name, fieldName);
					if (fieldType.needsRangeCheck()) {
						out.println("if (Debug.ENABLE_CHECK_VALUE) %s.checkValue(newValue);", fieldTypeName);
					}
					out.println("value = Types.toCARD16((value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK));", fieldCons);
					out.println("return this;");
					out.println("}");
					out.println();
					continue;
				}
				if (fieldType instanceof TypeBitField16) {
					out.println("// %s is BIT FIELD 16", fieldTypeName);
					out.println("public final %s %s() {", fieldTypeName, fieldName);
					out.println("return %1$s.value(Types.toCARD16((value & %2$s_MASK) >>> %2$s_SHIFT));", fieldTypeName, fieldCons);
					out.println("}");
					
					out.println("public final %s %s(%s newValue) {", javaFile.name, fieldName, fieldTypeName);
					out.println("value = Types.toCARD16((value & ~%1$s_MASK) | ((newValue.value << %1$s_SHIFT) & %1$s_MASK));", fieldCons);
					out.println("return this;");
					out.println("}");
					out.println();
					continue;
				}
				
				logger.error("Unexpected field type");
				logger.error("  %s", field.toMesaType());
				throw new UnexpectedException();
			}
			
			// close class body
			out.println("}");
		}
		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is BIT FIELD 32
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
				// need to swap first word and second word
				if (offset == 0) {
					start  = WORD_BITS + e.startBit;
					stop   = WORD_BITS + e.stopBit;
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
				
				// FIXME handle BOOLEAN, ENUM and Subrange field properly
				
				out.println("public final int %s() {", fieldName);
				out.println("return (value & %1$s_MASK) >>> %1$s_SHIFT;", fieldCons);
				out.println("}");
				
				out.println("public final %s %s(int newValue) {", javaFile.name, fieldName);
				out.println("value = (value & ~%1$s_MASK) | ((newValue << %1$s_SHIFT) & %1$s_MASK);", fieldCons);
				out.println("return this;");
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
			throw new UnexpectedException("Unexpected");
		}

		public void processArraySub() {
			if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE ProcessTypeArray.processArraySub");
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
				// INDEX is IMMEDIATE SUBRANGE
				indexType = type.toTypeArraySub().typeSubrange;
				
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
		public void processArrayRef() {
			if (DEBUG_SHOW_TRACE) javaFile.out.println("// TRACE ProcessTypeArray.processArrayRef");
			final var type        = javaFile.type;
			final var out         = javaFile.out;
			final var parentClass = MemoryBase.class;

			classPreamble(javaFile, parentClass);
			
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
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of BOOLEAN");
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of ENUM
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of ENUM");
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SUBRANGE");
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of ARRAY-REFERENCE");
			unexpected(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SUBRANGE");
			unexpected(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of SHORT POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of RAW SHORT POINTER");
				arrayIndirectShortRaw(javaFile, indexType);
			} else {
				// ARRAY of SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER");
				var processType = new ProcessTypeArrayShortPointer(javaFile, indexType);
				processType.accept(type.pointerTarget.realType());
			}			
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of LONG POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW LONG POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of RAW LONG POINTER");
				arrayIndirectLongRaw(javaFile, indexType);
			} else {
				// ARRAY of LONG POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER");
				var processType = new ProcessTypeArrayLongPointer(javaFile, indexType);
				processType.accept(type.pointerTarget.realType());
			}
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of BIT FIELD 16");
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of BIT FIELD 32");
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of MULT WORD RECORD");
			arrayElement(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of REFERENCE");
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
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to BOOLEAN");
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of SHORT POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to ENUM");
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SHORT POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to SUBRANGE");
			arrayIndirectShort(javaFile, indexType);
		}

		private void process(TypeArray type) {
			// ARRAY of SHORT POINTER to ARRAY
			Type arrayElement = type.arrayElement;
			if (arrayElement instanceof TypeReference) {
				// ARRAY of SHORT POINTER to REFERENCE of ARRAY
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to REFERENCE of ARRAY");
				arrayIndirectShort(javaFile, indexType);
			} else {
				// ARRAY of SHORT POINTER to IMMEDIATE ARRAY
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to IMMEDIATE ARRAY");
				unexpected(type);
			}
		}
		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of SHORT POINTER to ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to ARRAY-REFERENCE");
			process(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to ARRAY-SUBRANGE");
			// ARRAY of SHORT POINTER to ARRAY-SUBRANGE
			process(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of SHORT POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to SHORT POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of SHORT POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to LONG POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of SHORT POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to BIT FIELD 16");
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of SHORT POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to BIT FIELD 32");
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of SHORT POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to MULTI WORD RECORD");
			arrayIndirectShort(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of SHORT POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of SHORT POINTER to REFERENCE");
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
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to BOOLEAN");
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of LONG POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to ENUM");
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of LONG POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to SUBRANGE");
			arrayIndirectLong(javaFile, indexType);
		}

		private void process(TypeArray type) {
			// ARRAY of LONG POINTER to ARRAY
			Type arrayElement = type.arrayElement;
			if (arrayElement instanceof TypeReference) {
				// ARRAY of LONG POINTER to REFERENCE of ARRAY
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to REFERENCE of ARRAY");
				arrayIndirectLong(javaFile, indexType);
			} else {
				// ARRAY of SHORT POINTER to IMMEDIATE ARRAY
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to IMMEDIATE ARRAY");
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// ARRAY of LONG POINTER to ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to ARRAY-REFERENCE");
			process(type);
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// ARRAY of LONG POINTER to ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to ARRAY-SUBRANGE");
			process(type);
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// ARRAY of LONG POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to SHORT POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// ARRAY of LONG POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to LONG POINTERs");
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// ARRAY of LONG POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to BIT FIELD 16");
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// ARRAY of LONG POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to BIT FIELD 32");
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// ARRAY of LONG POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to MULTI WORD RECORD");
			arrayIndirectLong(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of LONG POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - ARRAY of LONG POINTER to REFERENCE");
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
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is BOOLEAN");
			// FIXME handle BOOLEAN, ENUM and Subrange field properly
			recordField(javaFile, field);
		}

		@Override
		protected void processTypeEnum(Field field, TypeEnum fieldType) {
			// RECORD FIELD is ENUM
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is ENUM");
			// FIXME handle BOOLEAN, ENUM and Subrange field properly
			recordField(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(Field field, TypeSubrange fieldType) {
			// RECORD FIELD is SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SUBRANGE");
			// FIXME handle BOOLEAN, ENUM and Subrange field properly
			recordField(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(Field field, TypeArrayRef fieldType) {
			// RECORD FIELD is ARRAY-REFERENCE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is REFERENCE of ARRAY-REFERENCE");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is IMMEDIATE ARRAY-REFERENCE");
				Type indexType   = fieldType.typeReference.realType();
				Type elementType = fieldType.arrayElement().realType();
				recordFieldArrayElement(javaFile, field, indexType, elementType);
			}
		}

		@Override
		protected void processTypeArraySubrange(Field field, TypeArraySub fieldType) {
			// RECORD FIELD is ARRAY-SUBRANGE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is REFERENCE of ARRAY-SUBRANGE");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE");
				Type indexType   = fieldType.typeSubrange;
				Type elementType = fieldType.arrayElement().realType();
				recordFieldArrayElement(javaFile, field, indexType, elementType);
			}
		}

		@Override
		protected void processTypePointeShort(Field field, TypePointerShort fieldType) {
			// RECORD FIELD is SHORT POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is RAW SHORT POINTER");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is SHORT POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER");
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
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is RAW LONG POINTER");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is LONG POINTER
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER");
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
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is REFERENCE of BIT FIELD 16");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 16
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is IMMEDIATE BIT FIELD 16");
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeBitField32(Field field, TypeBitField32 fieldType) {
			// RECORD FIELD is BIT FIELD 32
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 32
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is REFERNCE of BIT FIELD 32");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 32
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is IMMEDIATE BIT FIELD 32");
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeMultiWord(Field field, TypeMultiWord fieldType) {
			// RECORD FIELD is MULTI WORD RECORD
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of MULTI WORD RECORD
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is REFERENCE of MULTI WORD RECORD");
				recordField(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE MULTI WORD RECORD
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is IMMEDIATE MULT WORD RECORD");
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
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to BOOLEAN");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// RECORD FIELD is SHORT POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to ENUM");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// RECORD FIELD is SHORT POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to SUBRANGE");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// RECORD FIELD is SHORT POINTER to ARRAY-REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to ARRAY-REFERENCE");			
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-REFERENCE");
				recordFieldIndirectShort(javaFile, field);
			} else {
				// RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-REFERENCE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// RECORD FIELD is SHORT POINTER to ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to ARRAY-SUBRANGE");			
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to REFERENCE of ARRAY-SUBRANGE");
				recordFieldIndirectShort(javaFile, field);
			} else {
				// RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to IMMEDIATE ARRAY-SUBRANGE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// RECORD FIELD is SHORT POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to SHORT POINTER");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// RECORD FIELD is SHORT POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to LONG POINTER");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is SHORT POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to BIT FIELD 16");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is SHORT POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to BIT FIELD 32");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// RECORD FIELD is SHORT POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to MULTI WORD RECORD");
			recordFieldIndirectShort(javaFile, field);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// RECORD FIELD is SHORT POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is SHORT POINTER to REFERENCE");
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
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to BOOLEAN");
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// RECORD FIELD is LONG POINTER to ENUM
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to ENUM");
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// RECORD FIELD is LONG POINTER to SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to SUBRANGE");
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(TypeArrayRef type) {
			// RECORD FIELD is LONG POINTER to ARRAY-REFERENCE
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-REFERENCE");
				recordFieldIndirectLong(javaFile, field);
			} else {
				// RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypeArraySubrange(TypeArraySub type) {
			// RECORD FIELD is LONG POINTER to ARRAY-SUBRANGE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to ARRAY-SUBRANGE");
			Type targetType = field.type.realType().pointerTarget();
			if (targetType instanceof TypeReference) {
				// RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-SUBRANGE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to REFERENCE of ARRAY-SUBRANGE");
				recordFieldIndirectLong(javaFile, field);
			} else {
				// RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE
				if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to IMMEDIATE ARRAY-REFERENCE");
				unexpected(type);
			}
		}

		@Override
		protected void processTypePointeShort(TypePointerShort type) {
			// RECORD FIELD is LONG POINTER to SHORT POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to SHORT POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointerLong type) {
			// RECORD FIELD is LONG POINTER to LONG POINTER
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to LONG POINTER");
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeBitField16 type) {
			// RECORD FIELD is LONG POINTER to BIT FIELD 16
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to BIT FIELD 16");
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeBitField32(TypeBitField32 type) {
			// RECORD FIELD is LONG POINTER to BIT FIELD 32
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to BIT FIELD 32");
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeMultiWord(TypeMultiWord type) {
			// RECORD FIELD is LONG POINTER to MULTI WORD RECORD
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to MULTI WORD RECORD");
			recordFieldIndirectLong(javaFile, field);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// RECORD FIELD is LONG POINTER to REFERENCE
			if (DEBUG_SHOW_TYPE) javaFile.out.println("// TYPE - RECORD FIELD is LONG POINTER to REFERENCE");
			unexpected(type);
		}
	}

}
