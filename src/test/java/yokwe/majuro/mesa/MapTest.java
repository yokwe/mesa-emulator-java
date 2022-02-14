package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class MapTest extends Base {	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MapTest.class);

	@Test
	void clear() {
		logger.info("clear");

		Map map = new Map();
		
		// prepare
		map.clear();
		
		// execute
		// check result
		assertEquals(0, map.getFlags());
		assertEquals(0, map.getRealPage());
	}
	
	@Test
	void setFlags() {
		logger.info("setFlags");

		Map map = new Map();
		int valueFlags = 0x1234;
		
		// prepare
		// execute
		map.setFlags(valueFlags);
		
		// check result
		assertEquals(valueFlags, map.getFlags());
		assertEquals(0, map.getRealPage());
	}
	
	@Test
	void setRealPage() {
		logger.info("setRealPage");

		Map map = new Map();
		int valueReaPage = 0x5678;
		
		// prepare
		// execute
		map.setRealPage(valueReaPage);
		
		// check result
		assertEquals(0, map.getFlags());
		assertEquals(valueReaPage, map.getRealPage());
	}
	@Test
	void isVacant() {
		logger.info("isVacant");

		Map map = new Map();

		// prepare
		// execute
		map.setFlags(6);
		
		// check result
		assertEquals(true, map.isVacant());
	}

}
