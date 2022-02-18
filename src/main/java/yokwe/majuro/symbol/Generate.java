package yokwe.majuro.symbol;

public class Generate {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	public static void main(String[] args) {
		logger.info("START");
				
		JavaFile.generateType();
		
		logger.info("STOP");
	}
}
