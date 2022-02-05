package yokwe.majuro.study;

import yokwe.majuro.symbol.model.Constant;

public class T105 {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(T105.class);

	public static void main(String[] args) {
		logger.info("START");
		
		{
			String string = "yokwe.majuro.mesa.Constants.cSS";
			long value = Constant.getNumericValue(string);
			logger.info("{} {}", string, value);
		}
		{
			String string = "+123";
			long value = Constant.getNumericValue(string);
			logger.info("{} {}", string, value);
		}
		{
			String string = "-123";
			long value = Constant.getNumericValue(string);
			logger.info("{} {}", string, value);
		}
		
		logger.info("STOP");
	}
}
