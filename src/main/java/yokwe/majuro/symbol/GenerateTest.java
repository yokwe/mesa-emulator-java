package yokwe.majuro.symbol;

public class GenerateTest extends Generate {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	public static void main(String[] args) {
		logger.info("START");
		
		JavaFile.generateTest();
		
		logger.info("STOP");
	}

}
