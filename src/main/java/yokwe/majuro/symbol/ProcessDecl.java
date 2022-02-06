package yokwe.majuro.symbol;

import static yokwe.majuro.mesa.Constants.WORD_BITS;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.LEFT;
import static yokwe.majuro.util.AutoIndentPrintWriter.Layout.RIGHT;

import java.util.List;
import java.util.stream.Collectors;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypeArray.Reference;
import yokwe.majuro.symbol.model.TypeArray.Subrange;
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

public class ProcessDecl {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProcessDecl.class);

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

	private static void arrayData(JavaFile javaFile, Type indexType) {
		final var out          = javaFile.out;
		final TypeArray type = javaFile.type.toTypeArray();
				
		Type   elementType     = type.arrayElement.realType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		out.println("public final %s get(int index, MemoryAccess access) {", elementTypeName);
		if (!indexType.empty()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
		}
		out.println("return %s.longPointer(base + (%s.WORD_SIZE * index), access);", elementTypeName, elementTypeName);
		out.println("}");
	}
	private static void arrayPointer(JavaFile javaFile, Type indexType) {
		final var out          = javaFile.out;
		final TypeArray type = javaFile.type.toTypeArray();
		
		Type   elementType     = type.arrayElement.realType();
		String elementTypeName = StringUtil.toJavaName(elementType.name);
		
		out.println("public final %s get(int index) {", elementTypeName);
		if (!indexType.empty()) {
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkIndex(index);");
		}
		out.println("return %s.longPointer(base + (%s.WORD_SIZE * index));", elementTypeName, elementTypeName);
		out.println("}");
	}

	private static void fieldData(JavaFile javaFile, TypeRecord.Field field) {
		final var out         = javaFile.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);

		out.println("public %s %s(MemoryAccess access) {", fieldTypeName, fieldName);
		out.println("return %s.longPointer(base + OFFSET_%s, access);", fieldTypeName, fieldConstName);
		out.println("}");
	}
	private static void fieldPointer(JavaFile javaFile, TypeRecord.Field field) {
		final var out         = javaFile.out;

		Type   fieldType      = field.type.realType();
		String fieldConstName = StringUtil.toJavaConstName(field.name);
		String fieldName      = StringUtil.toJavaName(field.name);
		String fieldTypeName  = StringUtil.toJavaName(fieldType.name);
		
		out.println("public %s %s() {", fieldTypeName, fieldName);
		out.println("return %s.longPointer(base + OFFSET_%s);", fieldTypeName, fieldConstName);
		out.println("}");
	}


	public static void generateFile(JavaFile javaFile) {
		if (javaFile.type != null) {
			ProcessTypeTop processTypeTop = new ProcessTypeTop(javaFile);
			processTypeTop.process();
		} else if (javaFile.cons != null) {
			logger.info("CONSTANT {}", javaFile.cons.toMesaDecl());
			
			ProcessConsTop processConsTop = new ProcessConsTop(javaFile);
			processConsTop.process();
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
		protected void processTypeArrayReference(Reference type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeArraySubrange(Subrange type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypePointeShort(TypePointer type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypePointeLong(TypePointer type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeBitField16(TypeRecord type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeBitField32(TypeRecord type) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void processTypeMultiWord(TypeRecord type) {
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
			ProcessTypeArray processArray = new ProcessTypeArray(javaFile);
			processArray.process();
		}
		@Override
		protected void processTypeArrayReference(Reference type) {
			process(type);
		}
		@Override
		protected void processTypeArraySubrange(Subrange type) {
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
		protected void processTypePointeShort(TypePointer type) {
			process(type);
		}
		@Override
		protected void processTypePointeLong(TypePointer type) {
			process(type);
		}
		
		// RECORD
		@Override
		protected void processTypeBitField16(TypeRecord type) {
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
		protected void processTypeBitField32(TypeRecord type) {
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
		protected void processTypeMultiWord(TypeRecord type) {
			ProcessTypeRecord processRecord = new ProcessTypeRecord(javaFile);
			processRecord.process();
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
		ProcessTypeArray(JavaFile javaFile) {
			super(javaFile);
		}
		
		//
		// Entry Point
		//
		private Type indexType;

		@Override
		public void process() {
			final var type        = javaFile.type.toTypeArray();
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
				if (type instanceof TypeArray.Subrange) {
					// INDEX is IMMEDIATE SUBRANGE
					indexType = type.toSubrange().typeSubrange;
					immediateSubrange = true;
				} else if (type instanceof TypeArray.Reference) {
					Type realType = type.toReference().typeReference.realType();
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
			
			constructor(javaFile, parentClass);		
			
			//
			// output element access method
			//
			out.println("//");
			out.println("// Access to Element of Array");
			out.println("//");
			
			Type elementType = type.arrayElement.realType();

			accept(elementType);
			
			// close class body
			out.println("}");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of BOOLEAN
			arrayData(javaFile, indexType);
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of ENUM
			arrayData(javaFile, indexType);
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SUBRANGE
			arrayData(javaFile, indexType);
		}

		@Override
		protected void processTypeArrayReference(Reference type) {
			// ARRAY of ARRAY-REFERENCE
			unexpected(type);
		}

		@Override
		protected void processTypeArraySubrange(Subrange type) {
			// ARRAY of ARRAY-SUBRANGE
			unexpected(type);
		}

		@Override
		protected void processTypePointeShort(TypePointer type) {
			// ARRAY of SHORT POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW SHORT POINTER
				javaFile.out.println("// FIXME ARRAY of RAW SHORT POINTER"); // FIXME
			} else {
				// ARRAY of SHORT POINTER
				ProcessTypeArrayPointerShort processArrayPointerShort = new ProcessTypeArrayPointerShort(javaFile);
				processArrayPointerShort.accept(type.targetType.realType());
			}			
		}

		@Override
		protected void processTypePointeLong(TypePointer type) {
			// ARRAY of LONG POINTER
			if (type.rawPointer()) {
				// ARRAY of RAW LONG POINTER
				javaFile.out.println("// FIXME ARRAY of RAW LONG POINTER"); // FIXME
			} else {
				// ARRAY of LONG POINTER
				ProcessTypeArrayPointerLong processArrayPointerLong = new ProcessTypeArrayPointerLong(javaFile);
				processArrayPointerLong.accept(type.targetType.realType());
			}
		}

		@Override
		protected void processTypeBitField16(TypeRecord type) {
			// ARRAY of BIT FIELD 16
			arrayData(javaFile, indexType);
		}

		@Override
		protected void processTypeBitField32(TypeRecord type) {
			// ARRAY of BIT FIELD 32
			arrayData(javaFile, indexType);
		}

		@Override
		protected void processTypeMultiWord(TypeRecord type) {
			// ARRAY of MULTI WORD RECORD
			arrayPointer(javaFile, indexType);
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of REFERENCE
			unexpected(type);
		}
	}
	
	// ProcessTypeArrayShortPointer
	private static class ProcessTypeArrayPointerShort extends ProcessType {
		protected ProcessTypeArrayPointerShort(JavaFile javaFile) {
			super(javaFile);
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of SHORT POINTER to BOOLEAN
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to BOOLEAN"); // FIXME
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of SHORT POINTER to ENUM
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to ENUM"); // FIXME
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of SHORT POINTER to SUBRANGE
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to SUBRANGE"); // FIXME
		}

		@Override
		protected void processTypeArrayReference(Reference type) {
			// ARRAY of SHORT POINTER to ARRAY-REFERENCE
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to ARRAY-REFERENCE"); // FIXME
		}

		@Override
		protected void processTypeArraySubrange(Subrange type) {
			// ARRAY of SHORT POINTER to ARRAY-SUBRANGE
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to ARRAY-SUBRANGE"); // FIXME
		}

		@Override
		protected void processTypePointeShort(TypePointer type) {
			// ARRAY of SHORT POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointer type) {
			// ARRAY of SHORT POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeRecord type) {
			// ARRAY of SHORT POINTER to BIT FIELD 16
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to BIT FIELD 16"); // FIXME
		}

		@Override
		protected void processTypeBitField32(TypeRecord type) {
			// ARRAY of SHORT POINTER to BIT FIELD 32
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to BIT FIELD 32"); // FIXME
		}

		@Override
		protected void processTypeMultiWord(TypeRecord type) {
			// ARRAY of SHORT POINTER to MULTI WORD RECORD
			javaFile.out.println("// FIXME ARRAY of SHORT POINTER to MULTI WORD RECORD"); // FIXME
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of SHORT POINTER to REFERENCE
			unexpected(type);
		}
	}

	// ProcessArrayLongPointer
	private static class ProcessTypeArrayPointerLong extends ProcessType {
		protected ProcessTypeArrayPointerLong(JavaFile javaFile) {
			super(javaFile);
		}

		@Override
		public void process() {
			throw new UnexpectedException("Unexpected");
		}

		@Override
		protected void processTypeBoolean(TypeBoolean type) {
			// ARRAY of LONG POINTER to BOOLEAN
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to BOOLEAN"); // FIXME
		}

		@Override
		protected void processTypeEnum(TypeEnum type) {
			// ARRAY of LONG POINTER to ENUM
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to ENUM"); // FIXME
		}

		@Override
		protected void processTypeSubrange(TypeSubrange type) {
			// ARRAY of LONG POINTER to SUBRANGE
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to SUBRANGE"); // FIXME
		}

		@Override
		protected void processTypeArrayReference(Reference type) {
			// ARRAY of LONG POINTER to ARRAY-REFERENCE
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to ARRAY-REFERENCE"); // FIXME
		}

		@Override
		protected void processTypeArraySubrange(Subrange type) {
			// ARRAY of LONG POINTER to ARRAY-SUBRANGE
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to ARRAY-SUBRANGE"); // FIXME
		}

		@Override
		protected void processTypePointeShort(TypePointer type) {
			// ARRAY of LONG POINTER to SHORT POINTER
			unexpected(type);
		}

		@Override
		protected void processTypePointeLong(TypePointer type) {
			// ARRAY of LONG POINTER to LONG POINTER
			unexpected(type);
		}

		@Override
		protected void processTypeBitField16(TypeRecord type) {
			// ARRAY of LONG POINTER to BIT FIELD 16
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to BIT FIELD 16"); // FIXME
		}

		@Override
		protected void processTypeBitField32(TypeRecord type) {
			// ARRAY of LONG POINTER to BIT FIELD 32
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to BIT FIELD 32"); // FIXME
		}

		@Override
		protected void processTypeMultiWord(TypeRecord type) {
			// ARRAY of LONG POINTER to MULTI WORD RECORD
			javaFile.out.println("// FIXME ARRAY of LONG POINTER to MULTI WORD RECORD"); // FIXME
		}

		@Override
		protected void processTypeReference(TypeReference type) {
			// ARRAY of LONG POINTER to REFERENCE
			unexpected(type);
		}
	}

	
	//
	// ProcessTypeRecord -- MULTI WORD RECORD
	//
	private static class ProcessTypeRecord extends ProcessField {
		ProcessTypeRecord(JavaFile javaFile) {
			super(javaFile);
		}
		
		//
		// Entry Point
		//
		@Override
		public void process() {
			final var type        = javaFile.type.toTypeRecord();
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
			fieldData(javaFile, field);
		}

		@Override
		protected void processTypeEnum(Field field, TypeEnum fieldType) {
			// RECORD FIELD is ENUM
			fieldData(javaFile, field);
		}

		@Override
		protected void processTypeSubrange(Field field, TypeSubrange fieldType) {
			// RECORD FIELD is SUBRANGE
			fieldData(javaFile, field);
		}

		@Override
		protected void processTypeArrayReference(Field field, TypeArray.Reference fieldType) {
			// RECORD FIELD is ARRAY-REFERENCE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-REFERENCE
				fieldPointer(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-REFERENCE
				javaFile.out.println("// FIXME RECORD FIELD is IMMEDIATE ARRAY-REFERENCE"); // FIXME
			}
		}

		@Override
		protected void processTypeArraySubrange(Field field, TypeArray.Subrange fieldType) {
			// RECORD FIELD is ARRAY-SUBRANGE
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of ARRAY-SUBRANGE
				fieldPointer(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE
				javaFile.out.println("// FIXME RECORD FIELD is IMMEDIATE ARRAY-SUBRANGE"); // FIXME
			}
		}

		@Override
		protected void processTypePointeShort(Field field, TypePointer fieldType) {
			// RECORD FIELD is SHORT POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW SHORT POINTER
				fieldPointer(javaFile, field);
			} else {
				// RECORD FIELD is SHORT POINTER
				javaFile.out.println("// FIXME RECORD FIELD is SHORT POINTER"); // FIXME
			}
		}

		@Override
		protected void processTypePointeLong(Field field, TypePointer fieldType) {
			// RECORD FIELD is LONG POINTER
			if (fieldType.rawPointer()) {
				// RECORD FIELD is RAW LONG POINTER
				fieldPointer(javaFile, field);
			} else {
				// RECORD FIELD is LONG POINTER
				javaFile.out.println("// FIXME RECORD FIELD is LONG POINTER"); // FIXME
			}
		}

		@Override
		protected void processTypeBitField16(Field field, TypeRecord fieldType) {
			// RECORD FIELD is BIT FIELD 16
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 16
				fieldData(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 16
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeBitField32(Field field, TypeRecord fieldType) {
			// RECORD FIELD is BIT FIELD 32
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of BIT FIELD 32
				fieldData(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE BIT FIELD 32
				throw new UnexpectedException("unexpected");
			}
		}

		@Override
		protected void processTypeMultiWord(Field field, TypeRecord fieldType) {
			// RECORD FIELD is MULTI WORD RECORD
			if (field.type instanceof TypeReference) {
				// RECORD FIELD is REFERENCE of MULTI WORD RECORD
				fieldPointer(javaFile, field);
			} else {
				// RECORD FIELD is IMMEDIATE MULTI WORD RECORD
				throw new UnexpectedException("unexpected");
			}
		}
	}

}
