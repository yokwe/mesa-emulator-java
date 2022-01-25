package yokwe.majuro.symbol;

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
import yokwe.majuro.symbol.model.TypeRecord.Align;
import yokwe.majuro.symbol.model.TypeRecord.Field;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.AutoIndentPrintWriter.Layout;
import yokwe.majuro.util.StringUtil;

import static yokwe.majuro.mesa.Constant.*;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	private static final String PACKAGE = "yokwe.majuro.type";
	private static final String PATH_OUTPUT_DIR = String.format("src/main/java/%s", PACKAGE.replace('.', '/'));
	
	private static class Context {
		final String  name;
		final String  path;
		final File    ouputFile;
		final File    tempFile;
		
		boolean       success;

		public Context(Decl decl) {
			name      = StringUtil.toJavaName(decl.name);
			path      = String.format("%s/%s.java", PATH_OUTPUT_DIR, name);
			ouputFile = new File(path);
			tempFile  = new File("tmp/Generate.java");
			
			success   = true;
		}
		
		public void moveFile() {
			if (success) {
				tempFile.renameTo(ouputFile);
			}
		}
	}
	
	private static void genDecl(Context context, AutoIndentPrintWriter out, Type type) {
		switch(type.kind) {
		case BOOLEAN:
			genDecl(context, out, (TypeBoolean)type);
			break;
		case SUBRANGE:
			genDecl(context, out, (TypeSubrange)type);
			break;
		case ARRAY:
			genDecl(context, out, (TypeArray)type);
			break;
		case ENUM:
			genDecl(context, out, (TypeEnum)type);
			break;
		case RECORD:
			genDecl(context, out, (TypeRecord)type);
			break;
		case POINTER:
			genDecl(context, out, (TypePointer)type);
			break;
		case REFERENCE:
			context.success = false;
			break;
		default:
			logger.error("type {}  {}", type.name, type.kind);
			logger.error("mesa {}", type.toMesaType());
			throw new UnexpectedException("Unexpected");
		}
	}
	
	private static void getnConstructor16(Context context, AutoIndentPrintWriter out) {
		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(char value) {", context.name);
		out.println("super(value);");
		out.println("}");
		
		out.println("public %s(int base, MemoryAccess access) {", context.name);
		out.println("super(base, access);");
		out.println("}");
	}

	private static void getnConstructor32(Context context, AutoIndentPrintWriter out) {
		out.println("//");
		out.println("// Constructor");
		out.println("//");
		out.println("public %s(int value) {", context.name);
		out.println("super(value);");
		out.println("}");
		
		out.println("public %s(int base, MemoryAccess access) {", context.name);
		out.println("super(base, access);");
		out.println("}");
	}

	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeBoolean type) {
		//
		// Generate Constructor
		//
		getnConstructor16(context, out);
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
			getnConstructor16(context, out);
		} else if (type.bitSize <= 32) {
			getnConstructor32(context, out);
		} else {
			logger.error("type {}", type.toMesaType());
			logger.error("type {}", type);
			throw new UnexpectedException("Unexpected");
		}
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeArray type) {
		// FIXME
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
		
		getnConstructor16(context, out);
		out.println();

		out.println("@Override");
		out.println("public String toString() {", context.name);
		out.println("return context.toString(value);");
		out.println("}");
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeRecord type) {
		if (type.align == Align.BIT_16 && type.bitSize <= 16) {
			// single word bit field
			getnConstructor16(context, out);
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
		} else if (type.align == Align.BIT_32 && type.bitSize == 32) {
			// double word bit field
			getnConstructor32(context, out);
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
		} else {
			// multiple word
			// FIXME
			
			out.println("//");
			out.println("// Constants for field access");
			out.println("//");
			out.prepareLayout();
			for(var e: type.fieldList) {
				String fieldConstName = StringUtil.toJavaConstName(e.name);
				
				out.println("public static final int OFFSET_%s = %d;  // %s", fieldConstName, e.offset, e.toMesaType());
			}
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT);
			out.println();
			
			out.println("public final int base;");
			out.println();

			out.println("//");
			out.println("// Constructor");
			out.println("//");
			out.println("public %s(int value) {", context.name);
			out.println("this.base = value;");
			out.println("}");
			out.println();
			
			for(TypeRecord.Field field: type.fieldList) {
				if ((field.startBit % WORD_BITS) == 0 && (field.bitSize % WORD_BITS) == 0) {
					//
				} else if (field.startBit == Field.NO_VALUE) {
					//
				} else {
					logger.warn("Field is not aligned");
					logger.warn("  name {}.{}", type.name, field.name);
					logger.warn("  mesa {}", field.toMesaType());
					continue;
				}
				
				// FIXME

			}

		}
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypePointer type) {
		switch(type.size) {
		case SHORT:
			getnConstructor16(context, out);
			break;
		case LONG:
			getnConstructor32(context, out);
			break;
		default:
			throw new UnexpectedException("Unexpected");
		}
		// FIXME
	}
	
	private static void genDecl(Context context, AutoIndentPrintWriter out, Constant cons) {
		// FIXME
		context.success = false; // FIXME
	}
	
	private static void genDecl(Decl decl) {
		Context context = new Context(decl);

		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(context.tempFile)) {
			if (decl instanceof DeclType) {
				Type type = ((DeclType)decl).value;
				
//				logger.info("{}: TYPE = {};", type.name, type.toMesaType());
				
				out.println("package %s;", PACKAGE);
				out.println();
				out.println("import yokwe.majuro.UnexpectedException;");
				out.println("import yokwe.majuro.mesa.Debug;");
				out.println("import yokwe.majuro.mesa.Memory;");
				out.println("import yokwe.majuro.mesa.Mesa;");

				out.println();
				
				out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
				
				// output "public final class XXX" line
				{
					String parentClass = null;
					
					if (type.bitSize == 0) {
						// OK
					} else if (type.bitSize <= 16) {
						parentClass = "MemoryData16";
					} else if (type.bitSize <= 32) {
						parentClass = "MemoryData32";
					} else {
						// OK
					}
					
					// special for TypeRecord
					if (type instanceof TypeRecord) {
						parentClass = null;
						
						TypeRecord typeRecrd = (TypeRecord)type;
						if (typeRecrd.align == Align.BIT_16) {
							if (typeRecrd.bitSize <= 16) {
								parentClass = "MemoryData16";
							} else {
								// multiple word
								// OK
							}
						} else if (typeRecrd.align == Align.BIT_32)  {
							if (typeRecrd.bitSize == 32) {
								parentClass = "MemoryData32";
							} else {
								throw new UnexpectedException("Unexpected");
							}
						}
					}
					
					// special for TypePointer
					if (type instanceof TypePointer) {
						TypePointer typePointer = (TypePointer)type;
						switch(typePointer.size) {
						case SHORT:
							parentClass = "MemoryData16";
							break;
						case LONG:
							parentClass = "MemoryData32";
							break;
						default:
							throw new UnexpectedException("Unexpected");
						}
					}

					if (parentClass == null) {
						out.println("public final class %s {", context.name);
					} else {
						out.println("public final class %s extends %s {", context.name, parentClass);
					}
				}
				//
				
				out.prepareLayout();
				out.println("public static final String NAME     = \"%s\";", context.name);
				out.println("public static final int    SIZE     = %d;",     type.wordSize());
				out.println("public static final int    BIT_SIZE = %d;",     type.bitSize);
				out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
				out.println();
				
				genDecl(context, out, type);
			} else if (decl instanceof DeclConstant) {
				Constant cons = ((DeclConstant)decl).value;
				
//				logger.info("{}: {} = {};", cons.name, cons.type.toMesaType(), cons.valueString);
				
				out.println("package %s;", PACKAGE);
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
			
			context.moveFile();
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	
	public static void main(String[] args) {
		logger.info("START");
		
		logger.info("path {}", Symbol.PATH_RULE_FILE);
		Symbol symbol = Symbol.getInstance(Symbol.PATH_RULE_FILE);
		logger.info("symbol {}", symbol);
		
		for(var e: symbol.declList) {
			genDecl(e);
		}
		
		logger.info("STOP");
	}
}
