package yokwe.majuro.mesa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Base {
	private static final yokwe.majuro.util.FormatLogger logger = yokwe.majuro.util.FormatLogger.getLogger();

	protected static final int DEFAULT_VMBITS = 24;
	protected static final int DEFAULT_RMBITS = 20;
	protected static final int DEFAULT_MDS    = 0x3_0000;
	
	@BeforeAll
	protected static void beforeAll() {
		logger.info("beforeAll");
		Memory.init(DEFAULT_VMBITS, DEFAULT_RMBITS);
		Processor.MDS = DEFAULT_MDS;
	}
	
	@AfterAll
	protected static void afterAll() {
		logger.info("afterAll");
		System.gc();
	}
	
	@BeforeEach
	protected void beforeEach() {
//		logger.info("beforeEach START");
		Memory.clear();
		Processor.MDS = DEFAULT_MDS;
		Perf.clear();
//		logger.info("beforeEach STOP");
	}

	@AfterEach
	protected void afterEach() {
//		Perf.stats();
		System.gc();
	}

}
