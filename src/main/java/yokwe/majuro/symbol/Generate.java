package yokwe.majuro.symbol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Symbol;
import yokwe.majuro.symbol.model.Symbol.Decl;
import yokwe.majuro.symbol.model.Symbol.DeclConstant;
import yokwe.majuro.symbol.model.Symbol.DeclType;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.symbol.model.TypeBoolean;
import yokwe.majuro.symbol.model.TypeEnum;
import yokwe.majuro.symbol.model.Type.Kind;
import yokwe.majuro.symbol.model.TypeArray;
import yokwe.majuro.symbol.model.TypePointer;
import yokwe.majuro.symbol.model.TypeRecord;
import yokwe.majuro.symbol.model.TypeRecord.Align;
import yokwe.majuro.symbol.model.TypeReference;
import yokwe.majuro.symbol.model.TypeSubrange;
import yokwe.majuro.util.AutoIndentPrintWriter;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	private static final String PACKAGE = "yokwe.majuro.type";
	
	
	/*
	private static final String PATH_OUTPUT_DIR = String.format("src/main/java/%s", PACKAGE.replace('.', '/'));

	private static final Map<Type, String> simpleTypeToJapaMap = new TreeMap<>();
	static {
		simpleTypeToJapaMap.put(Type.BOOLELAN,         "boolean");
		simpleTypeToJapaMap.put(Type.INTEGER,          "short");
		
		simpleTypeToJapaMap.put(Type.CARDINAL,         "char");
		simpleTypeToJapaMap.put(Type.LONG_CARDINAL,    "int");
		
		simpleTypeToJapaMap.put(Type.UNSPECIFIED,      "char");
		simpleTypeToJapaMap.put(Type.LONG_UNSPECIFIED, "int");
		
		simpleTypeToJapaMap.put(Type.POINTER,          "char");
		simpleTypeToJapaMap.put(Type.LONG_POINTER,     "int");
	}
	
	private static String toJavaType(Type type) {
		Type realType = type.getRealType();

		if (simpleTypeToJapaMap.containsKey(realType)) {
			return simpleTypeToJapaMap.get(realType);
		}
		
		if (realType.kind == Kind.RECORD) {
			TypeRecord typeRecord = (TypeRecord)realType;
			if (typeRecord.align == Align.BIT_16 && typeRecord.bitSize() == 16) {
				return "char";
			}
			if (typeRecord.align == Align.BIT_32 && typeRecord.bitSize() == 32) {
				return "int";
			}
		}
		if (realType.kind == Kind.POINTER) {
			TypePointer typePointer = (TypePointer)realType;
			switch(typePointer.size) {
			case SHORT:
				return "char";
			case LONG:
				return "int";
			default:
				break;
			}
		}

		logger.error("Unexpected");
		logger.error("  type     {}", type);
		logger.error("  realType {}", realType);
		throw new UnexpectedException("Unexpected");
	}
	
	private static void genConstant(DeclConstant constant) {
		genConstant(constant.value);
	}
	private static void genConstant(Constant constant) {
		logger.info("{}: {} = {}", constant.name, constant.type.toMesaType(), constant.valueString);
	}


	
	private static void genConstant(List<Decl> declList) {
		String fileName = "Constant.java";
		String path = String.format("%s/%s", PATH_OUTPUT_DIR, fileName);
		
		logger.info("genConstant {}", path);
		File file = new File(path);
		
		try (AutoIndentPrintWriter out = new AutoIndentPrintWriter(new PrintWriter(new FileWriter(file)))) {
			out.println("package %s;", PACKAGE);
			out.println();
			out.println("public class Constant {");
			
			for(var e: declList) {
				if (e instanceof DeclConstant) {
					DeclConstant declConstant = (DeclConstant)e;
					Constant cons = declConstant.value;
					
					String javaType = toJavaType(cons.type.getRealType());
					String javaName = cons.name;
					
					out.println("// %s;", cons.toMesaType());
					out.println("public static final %s %s = %s;", javaType, javaName, cons.valueString);					
				}
			}
			out.println("}");
		} catch (IOException e) {
			String exceptionName = e.getClass().getSimpleName();
			logger.error("{} {}", exceptionName, e);
			throw new UnexpectedException(exceptionName, e);
		}
	}
	*/
	
	private static void genDecl(Type type) {
//		logger.info("{}: TYPE = {};", type.name, type.toMesaType());

		if (type instanceof TypeBoolean) {
			genDecl((TypeBoolean)type);
			return;
		}
		if (type instanceof TypeSubrange) {
			genDecl((TypeSubrange)type);
			return;
		}
		if (type instanceof TypeArray) {
			genDecl((TypeArray)type);
			return;
		}
		if (type instanceof TypeEnum) {
			genDecl((TypeEnum)type);
			return;
		}
		if (type instanceof TypeRecord) {
			genDecl((TypeRecord)type);
			return;
		}
		if (type instanceof TypePointer) {
			genDecl((TypePointer)type);
			return;
		}
		if (type instanceof TypePointer) {
			genDecl((TypePointer)type);
			return;
		}
		if (type instanceof TypeReference) {
			// DO NOTHING
			return;
		}
		
		logger.error("type {}  {}", type.name, type.kind);
		logger.error("mesa {}", type.toMesaType());
		throw new UnexpectedException("Unexpected");
	}
	
	private static void genDecl(TypeBoolean type) {
		logger.error("{}: TYPE = {}", type.name, type.toMesaType());
		throw new UnexpectedException("Unexpected");
	}
	private static void genDecl(TypeSubrange type) {
		// FIXME
	}
	private static void genDecl(TypeArray type) {
		// FIXME
	}
	private static void genDecl(TypeEnum type) {
		// FIXME
	}
	private static void genDecl(TypeRecord type) {
		// FIXME
	}
	private static void genDecl(TypePointer type) {
		// FIXME
	}
	
	private static void genDecl(Constant cons) {
		// FIXME
		// logger.info("{}: {} = {}", cons.name, cons.type.toMesaType(), cons.valueString);
	}
	
	private static void genDecl(Decl decl) {
		if (decl instanceof DeclType) {
			genDecl(((DeclType)decl).value);
			return;
		}
		if (decl instanceof DeclConstant) {
			genDecl(((DeclConstant)decl).value);
			return;
		}
		throw new UnexpectedException("Unexpected");
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
