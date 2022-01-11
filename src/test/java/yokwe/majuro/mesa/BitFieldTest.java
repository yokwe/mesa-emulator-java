package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BitFieldTest  {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BitFieldTest.class);

	@BeforeAll
	public static void beforeAll() {
//		logger.info("beforeAll");
	}
 
	@AfterAll
	public static void afterAll() {
//		logger.info("afterAll");
	}

	@BeforeEach
	void beforeEach() {
//		logger.info("beforeEach");
	}

	@AfterEach
	void afterEach() {
//		logger.info("afterEach");
	}

	@Test
	void mask16_F000_Test() {
		int startBit = 0;
		int stopBit  = 3;
		
		int expected = 0xF000;
		int actual   = BitField.mask16(startBit, stopBit);
		assertEquals(expected, actual);
	}
	@Test
	void mask16_8000_Test() {
		int startBit = 0;
		int stopBit  = 0;
		
		int expected = 0x8000;
		int actual   = BitField.mask16(startBit, stopBit);
		assertEquals(expected, actual);
		
	}
	@Test
	void mask16_000F_Test() {
		int startBit = 12;
		int stopBit  = 15;
		
		int expected = 0x000F;
		int actual   = BitField.mask16(startBit, stopBit);
		assertEquals(expected, actual);
	}
	@Test
	void mask16_00001_Test() {
		int startBit = 15;
		int stopBit  = 15;
		
		int expected = 0x0001;
		int actual   = BitField.mask16(startBit, stopBit);
		assertEquals(expected, actual);
	}
	
	@Test
	void mask32_F0000000_Test() {
		int startBit = 0;
		int stopBit  = 3;
		
		int expected = 0xF000_0000;
		int actual   = BitField.mask32(startBit, stopBit);
		assertEquals(expected, actual);
		
	}
	@Test
	void mask32_0000000F_Test() {
		int startBit = 28;
		int stopBit  = 31;
		
		int expected = 0x0000_000F;
		int actual   = BitField.mask32(startBit, stopBit);
		assertEquals(expected, actual);
	}
	@Test
	void mask32_80000000_Test() {
		int startBit = 0;
		int stopBit  = 0;
		
		int expected = 0x8000_0000;
		int actual   = BitField.mask32(startBit, stopBit);
		assertEquals(expected, actual);
		
	}
	@Test
	void mask32_00000001_Test() {
		int startBit = 31;
		int stopBit  = 31;
		
		int expected = 0x0000_0001;
		int actual   = BitField.mask32(startBit, stopBit);
		assertEquals(expected, actual);
	}
}
