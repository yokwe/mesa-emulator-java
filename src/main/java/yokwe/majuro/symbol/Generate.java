package yokwe.majuro.symbol;

public class Generate {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Generate.class);

	public static void main(String[] args) {
		logger.info("START");
				
		JavaFile.generateType();
		
		logger.info("STOP");
	}
}
