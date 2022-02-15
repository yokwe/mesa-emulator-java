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
	
	//
	// real page
	//
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
		assertEquals(valueReaPage << 8, map.getRealAddress());
	}
	
	//
	// flags
	//
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
	void isVacant_A() {
		logger.info("isVacant_A");

		Map map = new Map();
		
		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isVacant());
	}
	@Test
	void isVacant_B() {
		logger.info("isVacant_B");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_PROTECT | Map.MASK_DIRTY);
		// execute
		
		// check result
		assertEquals(true, map.isVacant());
	}
	@Test
	void isProtect_A() {
		logger.info("isProtect_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isProtect());
	}
	@Test
	void isProtect_B() {
		logger.info("isProtect_B");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_PROTECT);
		// execute
		
		// check result
		assertEquals(true, map.isProtect());
	}
	@Test
	void isReferenced_A() {
		logger.info("isReferenced_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isReferenced());
	}
	@Test
	void isReferenced_B() {
		logger.info("isReferenced_B");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_REFERENCED);
		// execute
		
		// check result
		assertEquals(true, map.isReferenced());
	}
	@Test
	void isDirty_A() {
		logger.info("isDirty_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isDirty());
	}
	@Test
	void isDirty_B() {
		logger.info("isDirty_B");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_DIRTY);
		// execute
		
		// check result
		assertEquals(true, map.isDirty());
	}
	@Test
	void isNotReferenced_A() {
		logger.info("isNotReferenced_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(true, map.isNotReferenced());
	}
	@Test
	void isNotReferenced_B() {
		logger.info("isNotReferenced_B");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_REFERENCED);
		// execute
		
		// check result
		assertEquals(false, map.isNotReferenced());
	}
	@Test
	void isNotReferencedDirty_A() {
		logger.info("isNotReferencedDirty_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(true, map.isNotReferencedDirty());
	}
	@Test
	void isNotReferencedDirty_B() {
		logger.info("isNotReferencedDirty_B");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_REFERENCED);
		// execute
		
		// check result
		assertEquals(true, map.isNotReferencedDirty());
	}
	@Test
	void isNotReferencedDirty_C() {
		logger.info("isNotReferencedDirty_C");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_DIRTY);
		// execute
		
		// check result
		assertEquals(true, map.isNotReferencedDirty());
	}
	@Test
	void isNotReferencedDirty_D() {
		logger.info("isNotReferencedDirty_D");

		Map map = new Map();

		// prepare
		map.setFlags(Map.MASK_DIRTY | Map.MASK_REFERENCED);
		
		// execute
		
		// check result
		assertEquals(false, map.isNotReferencedDirty());
	}
	@Test
	void setProtect_A() {
		logger.info("setProtect_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isProtect());
	}
	@Test
	void setProtect_B() {
		logger.info("setProtect_B");

		Map map = new Map();

		// prepare
		map.setProtect();
		// execute
		
		// check result
		assertEquals(true, map.isProtect());
	}
	@Test
	void setReferenced_A() {
		logger.info("setReferenced_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isReferenced());
	}
	@Test
	void setReferenced_B() {
		logger.info("setReferenced_B");

		Map map = new Map();

		// prepare
		map.setReferenced();
		// execute
		
		// check result
		assertEquals(true, map.isReferenced());
	}
	@Test
	void setReferencedDirty_A() {
		logger.info("setReferencedDirty_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isProtect());
		assertEquals(false, map.isDirty());
		assertEquals(false, map.isReferenced());
	}
	@Test
	void setReferencedDirty_B() {
		logger.info("setReferencedDirty_B");

		Map map = new Map();

		// prepare
		map.setReferencedDirty();
		// execute
		
		// check result
		assertEquals(false, map.isProtect());
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
	}
	@Test
	void setVacant_A() {
		logger.info("setVacant_A");

		Map map = new Map();

		// prepare
		// execute
		
		// check result
		assertEquals(false, map.isProtect());
		assertEquals(false, map.isDirty());
		assertEquals(false, map.isReferenced());
	}
	@Test
	void setVacant_B() {
		logger.info("setVacant_B");

		Map map = new Map();

		// prepare
		map.setVacant();
		// execute
		
		// check result
		assertEquals(true,  map.isProtect());
		assertEquals(true,  map.isDirty());
		assertEquals(false, map.isReferenced());
	}


}
