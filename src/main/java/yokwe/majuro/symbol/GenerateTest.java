package yokwe.majuro.symbol;

public class GenerateTest extends Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GenerateTest.class);

	public static void main(String[] args) {
		logger.info("START");
		
		Generate.process(TEST_RULE_FILE_PATH, TEST_OUTPUT_DIR_PATH, TEST_PACKAGE_NAME);
		
		logger.info("STOP");
	}

}
