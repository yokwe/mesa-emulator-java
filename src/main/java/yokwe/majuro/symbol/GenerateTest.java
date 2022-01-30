package yokwe.majuro.symbol;

public class GenerateTest extends Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GenerateTest.class);

	public static void main(String[] args) {
		logger.info("START");
		
		JavaFile.generateTest();
		
		logger.info("STOP");
	}

}
