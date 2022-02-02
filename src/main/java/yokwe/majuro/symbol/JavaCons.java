package yokwe.majuro.symbol;

import yokwe.majuro.symbol.model.Constant;
import yokwe.majuro.util.AutoIndentPrintWriter;

public class JavaCons {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JavaCons.class);

	public static void generateFile(JavaFile javaFile) {
		JavaCons javaCons = new JavaCons(javaFile);
		javaCons.generate();
	}
	
	private final JavaFile javaFile;
	private JavaCons(JavaFile javaFile) {
		this.javaFile = javaFile;
	}
	
	public void generate() {
		try (AutoIndentPrintWriter out = javaFile.getAutoIndentPrintWriter()) {
			final Constant cons = javaFile.cons;
			logger.info("{}: {} = {};", cons.name, cons.type.toMesaType(), cons.numericValue);
			
			javaFile.success = false;
		}
		javaFile.moveFile();
	}

}
