/*******************************************************************************
 * Copyright (c) 2020, Yasuhiro Hasegawa
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. The name of the author may not be used to endorse or promote products derived
 *      from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *******************************************************************************/
package yokwe.majuro.genType;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.LoggerFactory;

import yokwe.majuro.UnexpectedException;
import yokwe.util.AutoIndentPrintWriter;
import yokwe.util.StringUtil;

public class GenerateType {
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(GenerateType.class);

	static final String PATH_DIR = "src/yokwe/majuro/mesa/type";
	
	static void generateInnerFieldGetSet(AutoIndentPrintWriter out, Context context, String type, String prefix) {
		RecordInfo recordInfo = context.getRecordInfo(type);
		for(FieldInfo fieldInfo: recordInfo.fieldList) {
			if (fieldInfo.isEmpty()) continue;
			String fieldType  = context.getBaseType(fieldInfo.type);
			String qClassName = String.format("%s.%s", recordInfo.name, fieldInfo.name);

			out.println("//   %s  %s", type, fieldInfo.name);
			
			out.println("public static final class %s {", fieldInfo.name);
			out.println("public static final int OFFSET = %s.OFFSET + %2d;", prefix, fieldInfo.offset);
			out.println();
			out.println("public static int getAddress(@LONG_POINTER int base) {");
			out.println("return base + OFFSET;");
			out.println("}");

			switch(fieldType) {
			case "boolean":
				out.println("public static boolean get(@LONG_POINTER int base) {");
				out.println("return %s.get(getAddress(base));", qClassName);
				out.println("}");
				out.println("public static void set(@LONG_POINTER int base, boolean newValue) {");
				out.println("%s.set(getAddress(base), newValue);", qClassName);
				out.println("}");
				break;
			case "CARD8":
			case "CARD16":
			case "CARD32":
			case "POINTER":
			case "LONG_POINTER":
				out.println("public static @%s int get(@LONG_POINTER int base) {", fieldType);
				out.println("return %s.get(getAddress(base));", qClassName);
				out.println("}");
				out.println("public static void set(@LONG_POINTER int base, @%s int newValue) {", fieldType);
				out.println("%s.set(getAddress(base), newValue);", qClassName);
				out.println("}");
				break;
			default:
				if (context.isRecord(fieldType)) {
					generateInnerFieldGetSet(out, context, fieldType, String.format("%s.%s", prefix, fieldInfo.name));
				} else {
					logger.error("nestedType {}", fieldType);
					throw new UnexpectedException();
				}
			}
			out.println("}");
		}
	}
	
	static void generateInnerArrayFieldGetSet(AutoIndentPrintWriter out, Context context, String type, String prefix) {
		RecordInfo recordInfo = context.getRecordInfo(type);
		for(FieldInfo fieldInfo: recordInfo.fieldList) {
			if (fieldInfo.isEmpty()) continue;
			String qClassName = String.format("%s.%s", recordInfo.name, fieldInfo.name);
			String fieldType  = context.getBaseType(fieldInfo.type);
			out.println("// %s  %s  %s", type, fieldInfo.name, fieldType);
			
			out.println("public static final class %s {", fieldInfo.name);
			out.println("public static final int OFFSET = %s.OFFSET + %2d;", prefix, fieldInfo.offset);
			out.println();
			out.println("public static int getAddress(@LONG_POINTER int base, int index) {");
			out.println("return base + OFFSET + (ARRAY_SIZE * index);");
			out.println("}");
			
			switch(fieldType) {
			case "boolean":
				out.println("public static boolean get(@LONG_POINTER int base, int index) {");
				out.println("return %s.get(getAddress(base, index));", qClassName);
				out.println("}");
				out.println("public static void set(@LONG_POINTER int base, int index, boolean newValue) {");
				out.println("%s.set(getAddress(base, index), newValue);", qClassName);
				out.println("}");
				break;
			case "CARD8":
			case "CARD16":
			case "CARD32":
			case "POINTER":
			case "LONG_POINTER":
				out.println("public static @%s int get(@LONG_POINTER int base, int index) {", fieldType);
				out.println("return %s.get(getAddress(base, index));", qClassName);
				out.println("}");
				out.println("public static void set(@LONG_POINTER int base, int index, @%s int newValue) {", fieldType);
				out.println("%s.set(getAddress(base, index), newValue);", qClassName);
				out.println("}");
				break;
			default:
				if (context.isRecord(fieldType)) {
					generateInnerArrayFieldGetSet(out, context, fieldType, String.format("%s.%s", prefix, fieldInfo.name));
				} else {
					logger.error("nestedType {}", fieldType);
					throw new UnexpectedException();
				}
			}
			out.println("}");
		}
	}
	
	static boolean useMemoryClass(Context context, RecordInfo recordInfo) {
		for(FieldInfo fieldInfo: recordInfo.fieldList) {
			if (fieldInfo.isEmpty()) continue;
			switch(fieldInfo.fieldType) {
			case SIMPLE:
			case BIT:
			{
				String type = context.getBaseType(fieldInfo.type);
				switch(type) {
				case "boolean":
				case "CARD8":
				case "CARD16":
				case "CARD32":
				case "POINTER":
				case "LONG_POINTER":
					return true;
				default:
					break;
				}
			}
				break;
			case ARRAY:
			{
				ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)fieldInfo;
				ArrayInfo arrayInfo = arrayFieldInfo.arrayInfo;
				String type = context.getBaseType(arrayInfo.type);
				switch(type) {
				case "CARD16":
					return true;
				case "boolean":
				case "CARD8":
				case "CARD32":
				case "POINTER":
				case "LONG_POINTER":
					throw new UnexpectedException();
				default:
					break;
				}
			}
				break;
			default:
				throw new UnexpectedException();
			}
		}
		return false;
	}

	static void generateRecordClass(Context context, RecordInfo recordInfo) {
		String path = String.format("%s/%s.java", PATH_DIR, recordInfo.name);
		logger.info("path {}",path);
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			
			if (useMemoryClass(context, recordInfo)) {
				out.println("import yokwe.majuro.mesa.Memory;");
			}
			
			out.println("import yokwe.majuro.mesa.Type.*;");
			out.println();
			out.println("public final class %s {", recordInfo.name);
			out.println("public static final int SIZE = %d;", recordInfo.size);
			out.println();
			
			for(FieldInfo fieldInfo: recordInfo.fieldList) {
				out.println("// offset %4d  size %4d  type %-8s  name %s", fieldInfo.offset, fieldInfo.size, fieldInfo.type, fieldInfo.name);
				if (fieldInfo.fieldType == FieldType.ARRAY) {
					ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)fieldInfo;
					ArrayInfo arrayInfo = arrayFieldInfo.arrayInfo;;
					out.println("//   array  index %-16s  size %4d  length %3d  element %s", arrayInfo.indexType, arrayInfo.size, arrayInfo.length, arrayInfo.type);
				}
				if (fieldInfo.fieldType == FieldType.BIT) {
					BitFieldInfo bitFieldInfo = (BitFieldInfo)fieldInfo;
					BitInfo bitInfo = bitFieldInfo.bitInfo;
					out.println("//   bit startBit %2d  stopBit %2d", bitInfo.startBit, bitInfo.stopBit);
				}
			}
			out.println();
			
			for(FieldInfo fieldInfo: recordInfo.fieldList) {
				if (fieldInfo.isEmpty()) continue;

				out.println("public static final class %s {", fieldInfo.name);
				out.println("public static final         int SIZE       = %2d;", fieldInfo.size);
				out.println("public static final         int OFFSET     = %2d;", fieldInfo.offset);
				out.println();

				{
					switch (fieldInfo.fieldType) {
					case SIMPLE:
						break;
					case BIT:
					{
						BitFieldInfo bitFieldInfo = (BitFieldInfo)fieldInfo;
						switch(bitFieldInfo.size) {
						case 1:
							out.println("public static final         int SHIFT      = %2d;", bitFieldInfo.bitInfo.shift);
							out.println("public static final @CARD16 int MASK       = %s;", bitFieldInfo.bitInfo.mask);
							out.println();
							
							out.println("public static @CARD16 int getBit(@CARD16 int value) {");
							out.println("return (value & MASK) >>> SHIFT;");
							out.println("}");
							out.println("public static @CARD16 int setBit(@CARD16 int value, @CARD16 int newValue) {");
							out.println("return ((newValue << SHIFT) & MASK) | (value & ~MASK);");
							out.println("}");
							break;
						case 2:
							out.println("public static final @CARD32 int MASK       = %s;", bitFieldInfo.bitInfo.mask);
							out.println("public static final         int SHIFT      = %d;", bitFieldInfo.bitInfo.shift);
							out.println();
							
							out.println("public static @CARD32 int getBit(@CARD32 int value) {");
							out.println("return (value & MASK) >>> SHIFT;");
							out.println("}");
							out.println("public static @CARD32 int setBit(@CARD32 int value, @CARD32 int newValue) {");
							out.println("return ((newValue << SHIFT) & MASK) | (value & ~MASK);");
							out.println("}");
							break;
						default:
							logger.error("size {}", bitFieldInfo.size);
							throw new UnexpectedException();
						}
					}
						break;
					case ARRAY:
					{
						ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)fieldInfo;

						out.println("public static final         int ARRAY_SIZE = %2d;", arrayFieldInfo.arrayInfo.size);
						out.println("public static final         int ARRAY_LEN  = %2d;", arrayFieldInfo.arrayInfo.length);
						out.println();
					}
						break;
					default:
						throw new UnexpectedException();
					}

				}
				
				//
				// get set methods
				//
				{
					String qClassName = String.format("%s.%s", recordInfo.name, fieldInfo.name);
					switch(fieldInfo.fieldType) {
					case SIMPLE:
					{
						out.println("public static int getAddress(@LONG_POINTER int base) {");
						out.println("return base + OFFSET;");
						out.println("}");
						out.println();

						String type = context.getBaseType(fieldInfo.type);
						switch(type) {
						case "boolean":
							out.println("public static boolean get(@LONG_POINTER int base) {");
							out.println("return Memory.fetch(getAddress(base)) != 0;");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, boolean newValue) {");
							out.println("Memory.store(getAddress(base), (newValue ? 1 : 0));");
							out.println("}");
							break;
						case "CARD8":
						case "CARD16":
						case "POINTER":
							out.println("public static @%s int get(@LONG_POINTER int base) {", type);
							out.println("return Memory.fetch(getAddress(base));");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, @%s int newValue) {", type);
							out.println("Memory.store(getAddress(base), newValue);");
							out.println("}");
							break;
						case "CARD32":
						case "LONG_POINTER":
							out.println("public static @%s int get(@LONG_POINTER int base) {", type);
							out.println("return Memory.readDbl(getAddress(base));");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, @%s int newValue) {", type);
							out.println("Memory.writeDbl(getAddress(base), newValue);");
							out.println("}");
							break;
						default:
							if (context.isRecord(type)) {
								generateInnerFieldGetSet(out, context, type, fieldInfo.name);
							} else {
								logger.error("type {}", type);
								throw new UnexpectedException();
							}
						}
					}
						break;
					case BIT:
					{
						out.println("public static int getAddress(@LONG_POINTER int base) {");
						out.println("return base + OFFSET;");
						out.println("}");
						out.println();

						String type = context.getBaseType(fieldInfo.type);
						switch(type) {
						case "boolean":
							out.println("public static boolean get(@LONG_POINTER int base) {");
							out.println("return getBit(Memory.fetch(getAddress(base))) != 0;");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, boolean newValue) {");
							out.println("Memory.modify(getAddress(base), %1$s::setBit, (newValue ? 1 : 0));", qClassName);
							out.println("}");
							break;
						case "CARD8":
						case "CARD16":
						case "POINTER":
							out.println("public static @%s int get(@LONG_POINTER int base) {", type);
							out.println("return getBit(Memory.fetch(getAddress(base)));");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, @%s int newValue) {", type);
							out.println("Memory.modify(getAddress(base), %1$s::setBit, newValue);", qClassName);
							out.println("}");
							break;
						case "CARD32":
						case "LONG_POINTER":
							out.println("public static @%s int get(@LONG_POINTER int base) {", type);
							out.println("return getBit(Memory.readDbl(getAddress(base)));");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, @%s int newValue) {", type);
							out.println("Memory.modifyDbl(getAddress(base), %1$s::setBit, newValue);", qClassName);
							out.println("}");
							break;
						default:
							logger.error("type {}", type);
							throw new UnexpectedException();
						}
					}
						break;
					case ARRAY:
					{
						out.println("public static int getAddress(@LONG_POINTER int base, int index) {");
						out.println("return base + OFFSET + (ARRAY_SIZE * index);");
						out.println("}");

						ArrayFieldInfo arrayFieldInfo = (ArrayFieldInfo)fieldInfo;
						ArrayInfo arrayInfo = arrayFieldInfo.arrayInfo;
						String type = context.getBaseType(arrayInfo.type);
						
						switch(type) {
						case "CARD16":
						case "POINTER":
							out.println("public static @%s int get(@LONG_POINTER int base, int index) {", type);
							out.println("return Memory.fetch(getAddress(base, index));");
							out.println("}");
							out.println("public static void set(@LONG_POINTER int base, int index, @%s int newValue) {", type);
							out.println("Memory.store(getAddress(base, index), newValue);");
							out.println("}");
							break;
						default:
							if (context.isRecord(type)) {
								generateInnerArrayFieldGetSet(out, context, type, fieldInfo.name);
							} else {
								logger.error("type {}", type);
								throw new UnexpectedException();
							}
						}
					}
						break;
					default:
						throw new UnexpectedException();
					}
				}
				out.println("}");
			}
			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	static void generateEnumClass(Context context, EnumInfo enumInfo) {
		String path = String.format("%s/%s.java", PATH_DIR, enumInfo.name);
		logger.info("path {}",path);
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(path))) {			
			out.println("package yokwe.majuro.mesa.type;");
			out.println();
			out.println("import org.slf4j.Logger;");
			out.println("import org.slf4j.LoggerFactory;");
			out.println();
			out.println("import yokwe.majuro.UnexpectedException;");
			out.println("import yokwe.majuro.mesa.Type.*;");
			out.println();
			out.println("public enum %s {", enumInfo.name);
			
			int elementSize = enumInfo.elementList.size();
			for(int i = 0; i < elementSize; i++) {
				ElementInfo elementInfo = enumInfo.elementList.get(i);
				String sep = (i == (elementSize - 1)) ? ";" : ",";
				
				out.println("%-16s (%d)%s", StringUtil.toJavaConstName(elementInfo.name), elementInfo.value, sep);
				
			}
			out.println();
			
			out.println("public static %s getInstance(int value) {", enumInfo.name);
			out.println("for(%1$s e: %1$s.values()) {", enumInfo.name);
			out.println("if (e.value == value) return e;");
			out.println("}");
			out.println("Logger logger = LoggerFactory.getLogger(XferType.class);");
			out.println("logger.error(\"Unexpected value = {}\", value);");
			out.println("throw new UnexpectedException();");
			out.println("}");
			out.println();
			out.println("public final @CARD16 int value;");
			out.println();

			out.println("private %s(int value) {", enumInfo.name);
			out.println("this.value = value;");
			out.println("}");

			out.println("}");
		} catch (FileNotFoundException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}

	public static void main(String[] args) {
		logger.info("START");
		
		Map<String, RecordInfo> recordMap = new TreeMap<>();
		Map<String, TypeInfo>   typeMap   = new TreeMap<>();
		Map<String, EnumInfo>   enumMap   = new TreeMap<>();
		
		try {
			Data.readData(recordMap, typeMap, enumMap);
			
			Context context = new Context(recordMap, typeMap, enumMap);
			// Sanity check
			context.sanityCheck();
			
			for(RecordInfo e: recordMap.values()) {
				generateRecordClass(context, e);
			}
			for(EnumInfo e: enumMap.values()) {
				generateEnumClass(context, e);
			}
			
			logger.info("STOP");
		} finally {
			System.exit(0);
		}
	}

}
