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
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();
	
	public static final String  TYPE_RULE_FILE_PATH      = Symbol.PATH_RULE_FILE_TYPE;
	public static final String  TYPE_OUTPUT_DIR_PATH     = "src/main/java";
	public static final String  TYPE_PACKAGE_NAME        = "yokwe.majuro.type";
	public static final boolean TYPE_ADD_PREDEFINED_TYPE = true;
	
	public static final String  TEST_RULE_FILE_PATH     = Symbol.PATH_RULE_FILE_TEST;
	public static final String  TEST_OUTPUT_DIR_PATH    = "src/test/java";
	public static final String  TEST_PACKAGE_NAME       = "yokwe.majuro.type";
	public static final boolean TEST_ADD_PREDEFINED_TYPE = false;

	
	public static void main(String[] args) {
		logger.info("START");
				
		generateType();
		
		logger.info("STOP");
	}
	
	public static void generateType() {
		generate(TYPE_RULE_FILE_PATH, TYPE_OUTPUT_DIR_PATH, TYPE_PACKAGE_NAME, TYPE_ADD_PREDEFINED_TYPE);
	}
	public static void generateTest() {
		generate(TEST_RULE_FILE_PATH, TEST_OUTPUT_DIR_PATH, TEST_PACKAGE_NAME, TEST_ADD_PREDEFINED_TYPE);
	}

	public static void generate(String ruleFilePath, String outputDirPath, String packageName, boolean addPredefinedType) {
		logger.info("ruleFilePath  %s",  ruleFilePath);
		logger.info("outputDirPath %s", outputDirPath);
		logger.info("packageName   %s",   packageName);
		
		Symbol symbol = Symbol.getInstance(ruleFilePath, addPredefinedType);
		
		for(var e: symbol.declList) {
			JavaFile javaFile = new JavaFile(e, outputDirPath, packageName);
			ProcessDecl.generateFile(javaFile);
		}
	}

	final String   packageName;
	final Constant cons;
	final Type     type;
	final String   name;
	
	final File     ouputFile;
	final File     tempFile;
	
	boolean               success;
	AutoIndentPrintWriter out;

	public JavaFile(Decl decl, String outputDirPath, String packageName_) {
		packageName = packageName_;
		if (decl instanceof DeclConstant) {
			cons = decl.toCons();
			type = null;
			name = StringUtil.toJavaName(cons.name);
		} else if (decl instanceof DeclType) {
			cons = null;
			type = decl.toType();
			name = StringUtil.toJavaName(type.name);
		} else {
			throw new UnexpectedException("Unexpected");
		}
		success     = true;
		out         = null;
		
		String path = String.format("%s/%s/%s.java", outputDirPath, packageName.replace('.', '/'), name);
		
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
				logger.error("  tempFile  %s", tempFile.getPath());
				logger.error("  ouputFile %s", ouputFile.getPath());
				throw new UnexpectedException("Unexpected");
			}
		}
	}
}
