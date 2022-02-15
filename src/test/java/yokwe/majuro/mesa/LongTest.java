package yokwe.majuro.mesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import yokwe.majuro.type.Long;
import yokwe.majuro.type.MemoryAccess;

public class LongTest extends Base {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LongTest.class);

	@Test
	public void valueA() {
		logger.info("valueA");

		int value = 0x89AB_CDEF;

		// prepare
		// execute
		Long object = Long.value(value);
		
		// check result
		assertEquals(value & 0xFFFF, object.low());
		assertEquals(value >>> 16, object.high());
	}
	@Test
	public void valueB() {
		logger.info("valueB");

		int  value = 0xabcd1234;
		char low   = Mesa.lowHalf(value);
		char high  = Mesa.highHalf(value);

		// prepare
		// execute
		Long object = Long.value(0);
		object.low(low);
		object.high(high);
		
		// check result
		assertEquals(low,  object.low());
		assertEquals(high, object.high());
		assertEquals(value, object.value);
	}
	
	@Test
	public void readA() {
		logger.info("valueA");

		int va    = 0x20_1234;
		int value = 0x89AB_CDEF;

		// prepare
		Mesa.write32(va, value);
		
		// execute
		Long object = Long.longPointer(va, MemoryAccess.READ);
		
		// check result
		assertEquals(Mesa.lowHalf(value), object.low());
		assertEquals(Mesa.highHalf(value), object.high());
		assertEquals(value, object.value);
	}


}
