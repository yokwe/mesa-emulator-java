package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class MapFlagTest {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MapFlagTest.class);

	@BeforeAll
	public static void beforeAll() {
		logger.info("beforeAll");
	}
 
	@AfterAll
	public static void afterAll() {
		logger.info("afterAll");
	}

	@BeforeEach
	void beforeEach() {
	}

	@AfterEach
	void afterEach() {
	}
	
	@Test
	void clear() {
		logger.info("clear");

		MapFlag mapFlag = new MapFlag();
		
		// prepare
		mapFlag.clear();
		
		// execute
		// check result
		assertEquals(0, mapFlag.get());
	}
	
	@Test
	void set_char() {
		logger.info("set_char");

		MapFlag mapFlag = new MapFlag();
		char   value    = 0xCAFE;
		
		// prepare
		// execute
		mapFlag.set(value);
		
		// check result
		assertEquals(value, mapFlag.get());
	}
	
	@Test
	void set_int() {
		logger.info("set_int");

		MapFlag mapFlag = new MapFlag();
		int    value    = 0xCAFEBABE;
		
		// prepare
		// execute
		mapFlag.set(value);
		
		// check result
		assertEquals((char)value, mapFlag.get());
	}
	@Test
	void isVacant() {
		logger.info("isVacant");

		MapFlag mapFlag = new MapFlag();

		// prepare
		// execute
		mapFlag.set((char)0x6000);
		
		// check result
		assertEquals(true, mapFlag.isVacant());
	}

}
