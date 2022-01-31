package yokwe.majuro.symbol;

import java.io.File;

import yokwe.majuro.UnexpectedException;
import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.symbol.model.Symbol;
import yokwe.majuro.symbol.model.Symbol.Decl;
import yokwe.majuro.symbol.model.Symbol.DeclConstant;
import yokwe.majuro.symbol.model.Symbol.DeclType;
import yokwe.majuro.symbol.model.Type;
import yokwe.majuro.util.AutoIndentPrintWriter;
import yokwe.majuro.util.StringUtil;

public class JavaFile {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JavaFile.class);
	
	public static final String  TYPE_RULE_FILE_PATH  = Symbol.PATH_RULE_FILE_TYPE;
	public static final String  TYPE_OUTPUT_DIR_PATH = "src/main/java";
	public static final String  TYPE_PACKAGE_NAME    = "yokwe.majuro.type";
	public static final boolean TYPE_ADD_SIMPLE_TYPE = true;
	
	public static final String  TEST_RULE_FILE_PATH  = Symbol.PATH_RULE_FILE_TEST;
	public static final String  TEST_OUTPUT_DIR_PATH = "src/test/java";
	public static final String  TEST_PACKAGE_NAME    = "yokwe.majuro.type";
	public static final boolean TEST_ADD_SIMPLE_TYPE = false;

	
	public static void main(String[] args) {
		logger.info("START");
				
		generateType();
		
		logger.info("STOP");
	}
	
	public static void generateType() {
		generate(TYPE_RULE_FILE_PATH, TYPE_OUTPUT_DIR_PATH, TYPE_PACKAGE_NAME, TYPE_ADD_SIMPLE_TYPE);
	}
	public static void generateTest() {
		generate(TEST_RULE_FILE_PATH, TEST_OUTPUT_DIR_PATH, TEST_PACKAGE_NAME, TEST_ADD_SIMPLE_TYPE);
	}

	public static void generate(String ruleFilePath, String outputDirPath, String packageName, boolean addBuiltinType) {
		logger.info("ruleFilePath  {}",  ruleFilePath);
		logger.info("outputDirPath {}", outputDirPath);
		logger.info("packageName   {}",   packageName);
		
		Symbol symbol = Symbol.getInstance(ruleFilePath, addBuiltinType);
		
		for(var e: symbol.declList) {
			JavaFile javaFile = new JavaFile(e, outputDirPath, packageName);
			
			if (javaFile.type != null) {
				JavaType.generateFile(javaFile);
			} else if (javaFile.cons != null) {
				JavaCons.generateFile(javaFile);
			} else {
				throw new UnexpectedException("Unexpected");
			}
		}
	}

	final String   packageName;
	final String   name;
	final Type     type;
	final Constant cons;		
	
	final File   ouputFile;
	final File   tempFile;
	
	boolean               success;
	AutoIndentPrintWriter out;

	public JavaFile(Decl decl, String outputDirPath, String packageName_) {
		packageName = packageName_;
		name        = StringUtil.toJavaName(decl.name);
		type        = (decl instanceof DeclType)     ? ((DeclType)decl).value     : null;
		cons        = (decl instanceof DeclConstant) ? ((DeclConstant)decl).value : null;
		success     = true;
		out         = null;
		
		String name      = StringUtil.toJavaName(decl.name);
		String path      = String.format("%s/%s/%s.java", outputDirPath, packageName.replace('.', '/'), name);
		ouputFile = new File(path);
		tempFile  = new File("tmp/Generate.java");
	}
	
	public AutoIndentPrintWriter getAutoIndentPrintWriter() {
		if (out == null) {
			// save for later use
			out = new AutoIndentPrintWriter(tempFile);
			return out;
		} else {
			throw new UnexpectedException("Unexpected");
		}
	}
	
	public void moveFile() {
		if (success) {
			boolean success = tempFile.renameTo(ouputFile);
			if (!success) {
				logger.error("Failed to move file");
				logger.error("  tempFile  {}", tempFile.getPath());
				logger.error("  ouputFile {}", ouputFile.getPath());
				throw new UnexpectedException("Unexpected");
			}
		}
	}
}
