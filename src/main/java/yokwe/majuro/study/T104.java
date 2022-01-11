package yokwe.majuro.study;

import java.nio.ByteOrder;

public class T104 {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(T104.class);

	public static void main(String[] args) {
		logger.info("START");
		
		logger.info("native byte order {}", ByteOrder.nativeOrder().toString());
		
		logger.info("STOP");
	}
}
