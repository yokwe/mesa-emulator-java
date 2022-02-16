package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static yokwe.majuro.mesa.Constants.*;

import org.junit.jupiter.api.Test;

public class MemoryTest extends Base {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MemoryTest.class);

	@Test
	public void fetch() {
		logger.info("fetch");

		int va = 0x0020_0123;
		
		// prepare
		// execute
		int ra = Memory.fetch(va);
		
		// check result
		assertEquals(va, ra);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}
	
	@Test
	public void store() {
		logger.info("store");

		int va = 0x0020_0123;
		
		// prepare
		// execute
		int ra = Memory.store(va);
		
		// check result
		assertEquals(va, ra);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}
	
	@Test
	public void readReal16() {
		logger.info("readReal16");

		int  ra    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		Memory.writeReal16(ra, value);
		
		// execute
		int actual = Memory.readReal16(ra);
		
		// check result
		assertEquals(value, actual);
	}

	@Test
	public void writeReal16() {
		logger.info("writeReal16");

		int  ra    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		Memory.writeReal16(ra, value);
		
		// execute
		int actual = Memory.readReal16(ra);
		
		// check result
		assertEquals(value, actual);
	}
	
	@Test
	public void readReal32() {
		logger.info("readReal32");

		int ra    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		Memory.writeReal16(ra + 0, (char)value);
		Memory.writeReal16(ra + 1, (char)(value >>> WORD_BITS));
		
		// execute
		int actual = Memory.readReal32(ra + 0, ra + 1);
		
		// check result
		assertEquals(value, actual);
	}

	@Test
	public void writeReal32() {
		logger.info("writeReal32");

		int ra    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		// execute
		Memory.writeReal32(ra + 0, ra + 1, value);
		
		// check result
		assertEquals((char)value,                 Memory.readReal16(ra + 0));
		assertEquals((char)(value >>> WORD_BITS), Memory.readReal16(ra + 1));
	}

	@Test
	public void read16() {
		logger.info("read16");

		int  va    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		Memory.writeReal16(va, value);
		
		// execute
		char actual = Memory.read16(va);
		
		// check result
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}

	@Test
	public void write16() {
		logger.info("write16");

		int  va    = 0x0020_0100;
		char value = 0x1234;
		
		// prepare
		// execute
		Memory.write16(va, value);
		
		// check result
		char actual = Memory.readReal16(va);
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}

	@Test
	public void isSamePage() {
		logger.info("isSamePage");

		// prepare
		// execute
		// check result
		assertEquals(true,  Memory.isSamePage(0x0012_3400, 0x0012_3456));
		assertEquals(false, Memory.isSamePage(0x0012_3500, 0x0012_3456));
	}
	
	@Test
	public void read32() {
		logger.info("read32");

		int va    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		Memory.writeReal32(va + 0, va + 1, value);
		
		// execute
		int actual = Memory.read32(va);
		
		// check result
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(false, map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}

	@Test
	public void write32() {
		logger.info("write32");

		int va    = 0x0020_0100;
		int value = 0x12345678;
		
		// prepare
		// execute
		Memory.write32(va, value);
		
		// check result
		int actual = Memory.readReal32(va + 0, va + 1);
		assertEquals(value, actual);
		
		// check side effect
		Map map = Memory.map(va);
		assertEquals(true,  map.isDirty());
		assertEquals(true,  map.isReferenced());
		assertEquals(false, map.isProtect());
	}

	@Test
	public void lengthenMDS() {
		logger.info("lengthenMDS");

		char sa = 0xFEDC;
		
		// prepare
		// execute
		int va = Memory.lengthenMDS(sa);
		
		// check result
		assertEquals(sa + DEFAULT_MDS, va);
		
		// check side effect
	}
	
	@Test
	public void lengthenPDA() {
		logger.info("lengthenPDA");

		char sa = 0xFEDC;
		
		// prepare
		// execute
		int va = Memory.lengthenPDA(sa);
		
		// check result
		assertEquals(sa + Constants.mPDA, va);
		
		// check side effect
	}

}
