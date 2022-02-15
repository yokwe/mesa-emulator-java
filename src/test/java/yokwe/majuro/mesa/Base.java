package yokwe.majuro.mesa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class Base {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Base.class);

	protected static final int DEFAULT_VMBITS = 24;
	protected static final int DEFAULT_RMBITS = 20;
	protected static final int DEFAULT_MDS    = 0x3_0000;
	
	protected static Memory memory = null;

	@BeforeAll
	protected static void beforeAll() {
		logger.info("beforeAll");
		Mesa.init(DEFAULT_VMBITS, DEFAULT_RMBITS);
		Mesa.memory().mds = DEFAULT_MDS;
		memory = Mesa.memory();
	}
	
	@AfterAll
	protected static void afterAll() {
		logger.info("afterAll");
		memory = null;
		System.gc();
	}
	
	@BeforeEach
	protected void beforeEach() {
//		logger.info("beforeEach START");
		memory.clear();
		memory.mds = DEFAULT_MDS;
		Perf.clear();
//		logger.info("beforeEach STOP");
	}

	@AfterEach
	protected void afterEach() {
//		Perf.stats();
		System.gc();
	}

}
