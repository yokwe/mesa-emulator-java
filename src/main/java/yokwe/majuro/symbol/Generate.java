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
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Align;
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
			context.success = false;
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
	
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeSubrange type) {
		out.prepareLayout();
		out.println("public static final long MIN_VALUE  = %s;", StringUtil.toJavaString(type.minValue));
		out.println("public static final long MAX_VALUE  = %s;", StringUtil.toJavaString(type.maxValue));
		out.println("public static final long SIZE_VALUE = %s;", StringUtil.toJavaString(type.maxValue - type.minValue + 1));
		out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
		out.println();
		
		out.println("private static final SubrangeContext checkValue = new SubrangeContext(NAME, MIN_VALUE, MAX_VALUE);");
		out.println();
		
		out.println("public static final void checkValue(long value) {");
		out.println("if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);");
		out.println("}");
		
		// if this type is unsigned, ...
		if (0 <= type.minValue) {
			out.println("public static void checkValue(int value) {");
			out.println("if (Debug.ENABLE_CHECK_VALUE) checkValue.check(Integer.toUnsignedLong(value));");
			out.println("}");
		}
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeArray type) {
		// FIXME
		context.success = false; // FIXME
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeEnum type) {
		List<String> itemList = type.itemList.stream().map(o -> StringUtil.toJavaConstName(o.name)).collect(Collectors.toList());
		String valueList = String.join(", ", itemList);
		String nameList  = String.join(", ", itemList.stream().map(o -> "\"" + o + "\"").collect(Collectors.toList()));

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
		out.println("private static final EnumContext checkValue = new EnumContext(NAME, values, names);");
		out.println();

		out.println("public static final String toString(int value) {");
		out.println("return checkValue.toString(value);");
		out.println("}");
		out.println();
		out.println("public static final void checkValue(int value) {");
		out.println("if (Debug.ENABLE_CHECK_VALUE) checkValue.check(value);");
		out.println("}");
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypeRecord type) {
		if (type.bitSize <= 16 && type.align == Align.BIT_16) {
			// single word bit field
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
				
				out.println("public static final int %s_MASK  = %s;", fieldCons, StringUtil.toJavaBinaryString(Integer.toUnsignedLong(mask), type.bitSize));
				out.println("public static final int %s_SHIFT = %d;", fieldCons, shift);
			}
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
			out.println();
			
			out.println("private final MemoryAccess access;");
			out.println("private final int          ra;");
			out.println();
			out.println("// NOTE To reduce type conversion, use int for value");
			out.println("public int value;");
			out.println();
			
			out.println("public %s(char value) {", context.name);
			out.println("this.access = MemoryAccess.NONE;");
			out.println("this.ra     = 0;");
			out.println("this.value  = value;");
			out.println("}");
			
			out.println("public %s(int base, MemoryAccess access) {", context.name);
			out.println("this.access = access;");
			out.println("switch(access) {");
			out.println("case NONE:");
			out.println("this.ra    = 0;");
			out.println("this.value = 0;");
			out.println("break;");
			out.println("case READ:");
			out.println("this.ra    = Mesa.fetch(base);");
			out.println("this.value = Mesa.readReal16(ra);");
			out.println("break;");
			out.println("case READ_WRITE:");
			out.println("this.ra    = Mesa.store(base);");
			out.println("this.value = Mesa.readReal16(ra);");
			out.println("break;");
			out.println("case WRITE:");
			out.println("this.ra    = Mesa.store(base);");
			out.println("this.value = 0;");
			out.println("break;");
			out.println("default:");
			out.println("throw new UnexpectedException(\"Unexpected\");");
			out.println("}");
			out.println("}");
			out.println();
			
			out.println("public void write() {");
			out.println("switch(access) {");
			out.println("case READ_WRITE:");
			out.println("case WRITE:");
			out.println("Mesa.writeReal16(ra, (char)value);");
			out.println("break;");
			out.println("default:");
			out.println("throw new UnexpectedException(\"Unexpected\");");
			out.println("}");
			out.println("}");
			out.println();
			out.println();
			
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
		} else if (type.bitSize == 32 && type.align == Align.BIT_32) {
			// double word bit field
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
				
				logger.info("field {}  start {}  stop {}  bits {}", e.name, start, stop, bits);
				logger.info("type.bitSize {}  shift  {}", type.bitSize, shift);
				
				out.println("public static final int %s_MASK  = %s;", fieldCons, StringUtil.toJavaBinaryString(Integer.toUnsignedLong(mask), type.bitSize));
				out.println("public static final int %s_SHIFT = %d;", fieldCons, shift);
			}
			out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
			out.println();
			
			out.println("private final MemoryAccess access;");
			out.println("private final int          ra0;");
			out.println("private final int          ra1;");
			out.println();
			out.println("public int value;");
			out.println();
			
			out.println("public %s(int value) {", context.name);
			out.println("this.access = MemoryAccess.NONE;");
			out.println("this.ra0    = 0;");
			out.println("this.ra1    = 0;");
			out.println("this.value  = value;");
			out.println("}");
			
			out.println("public %s(int base, MemoryAccess access) {", context.name);
			out.println("this.access = access;");
			out.println("switch(access) {");
			out.println("case NONE:");
			out.println("this.ra0   = 0;");
			out.println("this.ra1   = 0;");
			out.println("this.value = 0;");
			out.println("break;");
			out.println("case READ:");
			out.println("this.ra0   = Mesa.fetch(base);");
			out.println("this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Mesa.fetch(base + 1);");
			out.println("this.value = Mesa.readReal32(ra0, ra1);");
			out.println("break;");
			out.println("case READ_WRITE:");
			out.println("this.ra0   = Mesa.store(base);");
			out.println("this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Mesa.store(base + 1);");
			out.println("this.value = Mesa.readReal32(ra0, ra1);");
			out.println("break;");
			out.println("case WRITE:");
			out.println("this.ra0   = Mesa.store(base);");
			out.println("this.ra1   = Memory.isSamePage(base, base + 1) ? ra0 + 1 : Mesa.store(base + 1);");
			out.println("this.value = 0;");
			out.println("break;");
			out.println("default:");
			out.println("throw new UnexpectedException(\"Unexpected\");");
			out.println("}");
			out.println("}");
			out.println();
			
			out.println("public void write() {");
			out.println("switch(access) {");
			out.println("case READ_WRITE:");
			out.println("case WRITE:");
			out.println("Mesa.writeReal32(ra0, ra1, value);");
			out.println("break;");
			out.println("default:");
			out.println("throw new UnexpectedException(\"Unexpected\");");
			out.println("}");
			out.println("}");
			out.println();
			out.println();

			
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
			context.success = false; // FIXME
		}
	}
	private static void genDecl(Context context, AutoIndentPrintWriter out, TypePointer type) {
		// FIXME
		context.success = false; // FIXME
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
				
				logger.info("{}: TYPE = {};", type.name, type.toMesaType());
				
				out.println("package %s;", PACKAGE);
				out.println();
				out.println("import yokwe.majuro.UnexpectedException;");
				out.println("import yokwe.majuro.mesa.Debug;");
				out.println("import yokwe.majuro.mesa.Memory;");
				out.println("import yokwe.majuro.mesa.Mesa;");

				out.println();
				
				out.println("// %s: TYPE = %s;", type.name, type.toMesaType());
				
				out.println("public final class %s {", context.name);
				out.prepareLayout();
				out.println("public static final String NAME     = \"%s\";", context.name);
				out.println("public static final int    SIZE     = %d;",     (type.bitSize + WORD_SIZE - 1) / WORD_SIZE);
				out.println("public static final int    BIT_SIZE = %d;",     type.bitSize);
				out.layout(Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.LEFT, Layout.RIGHT);
				out.println();
				
				genDecl(context, out, type);
			} else if (decl instanceof DeclConstant) {
				Constant cons = ((DeclConstant)decl).value;
				
				logger.info("{}: {} = {};", cons.name, cons.type.toMesaType(), cons.valueString);
				
				out.println("package %s;", PACKAGE);
				out.println();
				out.println("import yokwe.majuro.mesa.Debug;");
				out.println();
				
				out.println("// %s: %s = %s;", cons.name, cons.type.toMesaType(), cons.valueString);
				
				out.println("public final class %s {", context.name);
				out.println("public static final String NAME = \"%s\";", context.name);
				out.println();

				genDecl(context, out, cons);
				
				context.success = false; // FIXME
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
